
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="subjectList" value="${subjectList}"/>

	<br/>
	<form name="Search" id="Search" action="javascript:listExam()">
	<div class="text-right">
		<a class="btn btn-primary btn-sm" href="javascript:examWrite();">진행단계 평가 등록</a>
	</div>
	<div id="examList" style="margin-top:5px;">
		<table summary="<spring:message code="lecture.title.exam.manage"/>" class="table table-bordered wordbreak">
			<colgroup>
				<col style="width:50px"/>
				<col style="width:auto"/>
				<col style="width:auto"/>
				<col style="width:auto"/>
				<col style="width:auto"/>
				<col style="width:auto"/>
				<col style="width:80px"/>
				<col style="width:50px"/>
				<col style="width:72px"/>
			</colgroup>
			<thead>
				<tr>
					<th scope="col"><spring:message code="common.title.no"/></th>
					<th scope="col"><spring:message code="lecture.title.exam.name"/></th>
					<th scope="col"><spring:message code="lecture.title.exam.duration"/></th>
					<th scope="col"><spring:message code="lecture.title.exam.examtype"/></th>
					<th scope="col"><spring:message code="lecture.title.exam.ansrtype"/></th>
					<th scope="col"><spring:message code="lecture.title.exam.timelimit"/></th>
					<th scope="col"><spring:message code="lecture.title.exam.regyn"/></th>
					<th scope="col"><spring:message code="common.title.edit"/></th>
					<th scope="col"><spring:message code="common.title.manage"/></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td colspan="9"><spring:message code="lecture.message.exam.nodata"/></td>
				</tr>
			</tbody>
		</table>
	</div>
	</form>

<script type="text/javascript">

	var ItemDTO = new Object();

	$(document).ready(function() {
		ItemDTO.crsCreCd = '${vo.crsCreCd}';
		ItemDTO.semiExamYn = '${vo.semiExamYn}';
		listExam();
	});

	function parentResize() {
		var iframeObj = parent.document.getElementById("subWorkFrame");
		parent.resizeIframe3(iframeObj, $(document).height());
	}

	/**
	 * 시험 목록 조회
	 */
	function listExam(page) {
		var crsCreCd = ItemDTO.crsCreCd;
		$("#examList").load(
			cUrl("/mng/lecture/exam/listExam"),		// url
			{ 
			  "curPage" : page ,
			  "crsCreCd" : crsCreCd,
			  "semiExamYn" : "Y"
			},
			function() {
				parentResize();
			}
		);
	}


	/**
	 * 시험 정보 등록 폼
	 */
	function examWrite() {
		var addContent  = "<iframe id='addExamFrame' name='addExamFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='yes' src='<c:url value="/mng/lecture/exam/addExamPop"/>"
			+ "?crsCreCd="+ItemDTO.crsCreCd+"&semiExamYn="+ItemDTO.semiExamYn+"'/>"; 
		parent.modalBox.clear();
		parent.modalBox.addContents(addContent);
		parent.modalBox.resize(700, 600);
		parent.modalBox.setTitle("진행단계평가 등록");
		parent.modalBox.show();
	}
	
	

	/**
	 * 시험 정보 수정 폼
	 */
	function examEdit(examSn) {
		var addContent  = "<iframe id='addExamFrame' name='addExamFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='yes' src='<c:url value="/mng/lecture/exam/editExamPop"/>"
			+ "?crsCreCd="+ItemDTO.crsCreCd+"&amp;examSn="+examSn+"&semiExamYn="+ItemDTO.semiExamYn+"'/>"; 

		parent.modalBox.clear();
		parent.modalBox.addContents(addContent);
		parent.modalBox.resize(700, 600);
		parent.modalBox.setTitle("진행단계평가 수정");
		parent.modalBox.show();
	}


	/**
	 * 시험지 미리보기
	 */
	function preview(examSn) {

		var url = cUrl("/mng/lecture/exam/previewExamForm")+"?crsCreCd="+ItemDTO.crsCreCd+"${AMPERSAND}examSn="+examSn;
		var winOption = "left=0,top=0,toolbar=0,location=0,directories=0,status=yes,menubar=0,scrollbars=yes,resizable=yes,width=860,height=600";
		var examLookWin = window.open(url, "examLookWin", winOption);
		examLookWin.focus();
	}

</script>
