
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="creCrsDeclsVO" value="${vo}"/>
	<br/>
	<div style="width:100%;">
		<div id="writeButton" style="float:right; display:none;height:37px;">
			<button class="btn btn-primary btn-sm" onclick="addDecls()"><spring:message code="button.write.decls"/></button>
		</div>
	</div>
	<div id="declsList" >
		<table summary="<spring:message code="course.title.decls.manage"/>" class="table table-bordered wordbreak">
			<colgroup>
				<col style="width:auto"/>
				<col style="width:auto"/>
				<col style="width:auto"/>
				<col style="width:auto"/>
				<col style="width:auto"/>
			</colgroup>
			<thead>
				<tr>
					<th scope="col"><spring:message code="course.title.decls.name"/></th>
					<th scope="col"><spring:message code="course.title.decls.student"/></th>
					<th scope="col"><spring:message code="course.title.decls.enrlstd"/></th>
					<th scope="col"><spring:message code="course.title.decls.confstd"/></th>
					<th scope="col"><spring:message code="common.title.delete"/></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td colspan="5"><spring:message code="course.message.decls.nodata"/></td>
				</tr>
			</tbody>
		</table>
	</div>
	<div style="margin-bottom:30px;"></div>

<script type="text/javascript">

	var declsCnt = 0;

	$(document).ready(function() {
		listDecls();
	});

	function listDecls() {
		$('#declsList').load(
			cUrl("/mng/course/createCourse/decls/list"),
			{ "crsCreCd": "${creCrsDeclsVO.crsCreCd}"},
			function() {
				resizeForm();
				writeButton();
			}
		);
	}

	function writeButton() {
		if(declsCnt >= 100) $("#writeButton").hide();
		else $("#writeButton").show();
	}

	function resizeForm() {
		var iframeObj = parent.document.getElementById("subWorkFrame");
		parent.resizeIframe3(iframeObj, $(document).height());
	}

	/**
	 * 분반 추가
	 */
	function addDecls() {
		if(confirm("<spring:message code="course.message.decls.confirm.add"/>")) {
			$.getJSON( cUrl( "/mng/course/createCourse/decls/add"),
					   {  "crsCreCd": "${creCrsDeclsVO.crsCreCd}" },			// params
					   function(data) {
							if(data.result >= 0) {
								//-- 정상 처리
								alert("<spring:message code="course.message.decls.add.success"/>");
								listDecls();
				 	  		} else {
				 	  			//-- 비정상 처리
				 	  			alert("<spring:message code="course.message.decls.add.failed"/>");
				 	  		}
						}
					);
		} else return;
	}

	/**
	 * 분반 삭제
	 */
	function delDecls(declsNo) {
/* 		if(stuCnt > 0) {
			alert("<spring:message code="course.message.decls.alert.delete1"/>");
			return;
		} */
		if(confirm("<spring:message code="course.message.decls.confirm.delete"/>")) {
			$.getJSON( cUrl( "/mng/course/createCourse/decls/remove"),
					   { "crsCreCd": "${creCrsDeclsVO.crsCreCd}", "declsNo": declsNo},			// params
					   function(data) {
							if(data.result >= 0) {
								//-- 정상 처리
								alert("<spring:message code="course.message.decls.delete.success"/>");
								listDecls();
				 	  		} else {
				 	  			//-- 비정상 처리
				 	  			alert("<spring:message code="course.message.decls.delete.failed"/>");
				 	  		}
						}
					);
		} else return;
	}

</script>
