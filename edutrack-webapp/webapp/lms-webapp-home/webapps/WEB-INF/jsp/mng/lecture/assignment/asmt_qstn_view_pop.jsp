<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="gubun" value="${gubun}"/>
<c:set var="assignmentSubVO" value="${vo}" />
	<form id="assignmentForm" name="assignmentForm" onsubmit="return false" action="/mng/lecture/assignment">
	<input type="hidden" name="crsCreCd" value="${vo.crsCreCd }" />
	<input type="hidden" name="asmtSn" value="${vo.asmtSn }" />
	<input type="hidden" name="asmtSubSn" value="${vo.asmtSubSn }" />
	<input type="hidden" name="attachFileSns" value="${vo.attachFileSns }" />
	<input type="hidden" name="attachImageSns" value="${vo.attachImageSns }" />
	<input type="hidden" name="editorYn" value="Y"/>
	<table summary="<spring:message code="lecture.title.assignment.question.manage"/>" class="table table-bordered wordbreak">
		<colgroup>
			<col style="width:20%"/>
			<col style="width:80%"/>
		</colgroup>
		<tbody>
			<tr>
				<th scope="row"><spring:message code="lecture.title.assignment.question.title"/></th>
				<td>
					${assignmentSubVO.asmtTitle }
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="common.title.atachfile"/></th>
				<td>
					<c:if test="${not empty assignmentSubVO.attachFiles}">
						<c:forEach var="fileItem" items="${assignmentSubVO.attachFiles}" varStatus="status">
							<div style="margin-left: 5px; float: left">${fileItem.downloadTag}</div>
						</c:forEach>
					</c:if>
				</td>
			</tr>
			<tr>
				<td colspan="2" style="padding:10px;">
					${assignmentSubVO.asmtCts}
				</td>
			</tr>
		</tbody>
	</table>
	<div class="text-right">
		<a href="javascript:parent.modalBoxClose()" class="btn btn-default btn-sm"><spring:message code="button.close"/></a>
	</div>
	</form>
<script type="text/javascript">

	var atchFiles; // 첨부파일 목록

	$(document).ready(function() {
		// 파일 첨부 업로더 및 바인딩 및 초기화 @see /js/common_function.js 참조.
		atchFiles = new $M.JqueryFileUpload({
							"varName"			: "atchFiles",
							"files" 			: $.parseJSON('${assignmentSubVO.attachFilesJson}'),
							"uploaderId"		: "atchuploader",
							"fileListId"		: "atchfiles",
							"progressId"		: "atchprogress",
							"maxcount"			: 3,
							"uploadSetting"		: {
								'formData'		: { 'repository': 'ASMT_SUB',
													'organization' : "${USER_ORGCD}",
													'type'		: 'file' }
							}
						});
	});


</script>
