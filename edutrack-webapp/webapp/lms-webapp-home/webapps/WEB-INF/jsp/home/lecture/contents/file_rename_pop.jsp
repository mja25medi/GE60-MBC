<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>

	<form id="contentsForm" name="contentsForm" onsubmit="return false" >
	<input type="hidden" name="fileDir" value="${vo.fileDir }" />
	<input type="hidden" name="linkDir" value="${vo.linkDir }" />
	<input type="hidden" name="gubun" value="${vo.gubun }" />
	<table summary="<spring:message code="course.title.contents.rename"/>" class="table table-bordered wordbreak">
		<colgroup>
			<col style="width:40%"/>
			<col style="width:60%"/>
		</colgroup>
		<tr>
			<th scope="row"><label for="fileName"><spring:message code="course.title.contents.filefolder.name"/></label></th>
			<td >
				<c:choose>
					<c:when test="${vo.gubun eq 'FOLDER'}">
						<input type="text" dispName="<spring:message code="course.title.contents.filefolder.name"/>" maxlength="200" value="${vo.fileDir }" isNull="N" lenCheck="200" name="fileName" class="form-control input-sm" id="fileName"/>
					</c:when>
					<c:otherwise>
						<input type="text" dispName="<spring:message code="course.title.contents.filefolder.name"/>" maxlength="200" isNull="N" lenCheck="200" name="fileName" value="${vo.fileName }" class="form-control input-sm" id="fileName"/>
					</c:otherwise>
				</c:choose>


				<%-- <input type="text" dispName="<spring:message code="course.title.contents.filefolder.name"/>" maxlength="200" value="${vo.fileDir }" isNull="N" lenCheck="200" name="fileName" class="form-control input-sm" id="fileName"/> --%>
			</td>
		</tr>
		<tr>
			<th scope="row"><spring:message code="course.title.contents.filefolder.path"/></th>
			<td>
				${vo.fileDir}
			</td>
		</tr>
	</table>
	<p style="margin-left:10px;">* <spring:message code="common.message.file.name.policy"/></p>
	<div class="text-right">
		<a href="javascript:renameFile()" class="btn btn-primary"><spring:message code="button.add"/> </a>
		<a href="javascript:parent.modalBoxChildClose()" class="btn btn-default"><spring:message code="button.close"/> </a>
	</div>
	<input type="submit" value="submit" style="display:none" />
	</form>

<script type="text/javascript">

	$(document).ready(function() {
	});

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		if(!validate(document.contentsForm)) return;

		$('#contentsForm').attr("action","/lec/cnts/" + cmd);
		$('#contentsForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		if(resultDTO.message != ""){
			alert(resultDTO.message);
		}else{
			alert("<spring:message code="course.message.contents.alert.nochange.name"/>");
		}
		if(resultDTO.result >= 0) {
			// 정상 처리
			parent.listDir();
			parent.modalBoxChildClose();
		} else {
			// 비정상 처리
		}
	}

	/**
	 * 과목 등록
	 */
	function renameFile() {
		process("renameFile");	// cmd
	}
</script>
