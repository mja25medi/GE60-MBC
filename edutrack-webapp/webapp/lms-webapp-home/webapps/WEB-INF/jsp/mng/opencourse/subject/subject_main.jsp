<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="openCrsSbjVO" value="${vo}"/>
<section class="content">
	<div class="row" id="content">
		<div class="box">
			<div class="box-body">
			<table summary='<spring:message code="course.title.course.manage.subject"/>' class="table table-bordered wordbreak">
				<colgroup>
					<col style="width:15%"/>
					<col style="width:35%"/>
					<col style="width:15%"/>
					<col style="width:35%"/>
				</colgroup>
				<tr>
					<th scope="row"><spring:message code="course.open.title.course.name"/></th>
					<td>
						<span id="ctgrNm">${openCrsVO.crsNm }</span>
					</td>
					<th scope="row"><spring:message code="course.open.title.course.category.name"/></th>
					<td>
						<span id="ctgrNm">${openCrsVO.ctgrNm }</span>
					</td>
				</tr>
			</table>
			<div>
				<div class="input-group" style="width:100%">
					<div style="float:left;line-height:30px;">
						<span id="courseTitle"></span>
					</div>
					<div class="text-right">
						<button class="btn btn-primary btn-sm" onclick="sbjWrite()"><spring:message code="course.title.subject.write"/></button>
						<button class="btn btn-default btn-sm" onclick="goCrsList()"><spring:message code="button.list.course"/></button>
					</div>
				</div>
				<div id="ctgrList" style="margin-top:5px;width:100%;"></div>
			</div>
			<div id="subjectList">
				<table summary='<spring:message code="course.title.course.manage.subject"/>' class="table table-bordered wordbreak">
					<colgroup>
						<col style="width:auto"/>
						<col style="width:auto"/>
						<col style="width:auto"/>
						<col style="width:auto"/>
						<col style="width:auto"/>
					</colgroup>
					<thead>
						<tr>
							<th scope="col"><spring:message code="common.title.no"/></th>
							<th scope="col"><spring:message code="course.title.subject.code"/></th>
							<th scope="col"><spring:message code="course.open.title.course.name"/></th>
							<th scope="col"><spring:message code="course.title.subject.contents.cnt"/></th>
							<th scope="col"><spring:message code="common.title.delete"/></th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${empty openCrsList}">
						<tr>
							<td colspan="5"><spring:message code="common.message.nodata"/></td>
						</tr>
						</c:if>
					</tbody>
				</table>
			</div>
			</div>
		</div>
	</div>
</section>			

<script type="text/javascript">
	var modalBox = null;
	var ItemDTO = new Object;

	/** 관련사이트 초기화 */
	$(document).ready(function(){
		modalBox = new $M.ModalDialog({
			"modalid" : "modal1"
		});
		ItemDTO.crsCd = "";
		ItemDTO.crsTitle = "";
		listOpenCourseSubjectList();
	});

	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}

	/**
	 * 과목등록 팝업
	 */
	function sbjWrite() {
		var addContent  = "<iframe id='addSubjectFrame' name='addSubjectFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='no' src='<c:url value="/mng/course/courseSubject/openCourseOnlnSbjPop"/>"
			+ "?crsCd=${openCrsSbjVO.crsCd}'/>";
		parent.modalBox.clear();
		parent.modalBox.resize(620, 480);
		parent.modalBox.addContents(addContent);
		parent.modalBox.setTitle("<spring:message code="course.title.subject.write"/>");
		parent.modalBox.show();
	}

	/**
	 * 과목삭제
	 */
	function sbjDelete(crsCd, sbjCd) {
		if(confirm("<spring:message code="course.open.message.subject.confirm.delete"/>")){
			$.getJSON( cUrl( "/mng/openCourse/subject/remove"),
					   { "crsCd" : crsCd,"sbjCd" : sbjCd },			// params
					   function(data) {
							if(data.result >= 0) {
								//-- 정상 처리
								alert("<spring:message code="course.open.message.subject.delete.success"/>");
								listOpenCourseSubjectList();
				 	  		} else {
				 	  			//-- 비정상 처리
				 	  			alert("<spring:message code="course.open.message.subject.delete.failed"/>");
				 	  		}
						}
					);
		}
	}

	/**
	 * 공개과정 과정>과목목록 조회
	 */
	function listOpenCourseSubjectList(){
		$('#subjectList').load(cUrl("/mng/openCourse/subject/list"), { "crsCd": "${openCrsSbjVO.crsCd}"});
	}

	/**
	 * 공개과정 분류 과정 순서 변경 폼
	 */
	function sbjSort(sortString) {
		$.getJSON( cUrl( "/mng/openCourse/subject/sort"),
				{  "crsCd": "${openCrsSbjVO.crsCd}", "sbjCd" : sortString },			// params
				function(data) {
					alert(data.message);
					if(data.result >= 0) {
						//-- 정상 처리
		 	  		} else {
		 	  			//-- 비정상 처리
						listOnline();
		 	  		}
				}
			);
	}

	function goCrsList() {
		location.href = cUrl("/mng/openCourse/main")+"?ctgrCd=${openCrsVO.ctgrCd}";
	}
</script>
