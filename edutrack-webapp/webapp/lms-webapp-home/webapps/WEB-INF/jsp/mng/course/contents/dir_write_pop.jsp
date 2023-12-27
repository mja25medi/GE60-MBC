<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>

	<form id="contentsForm" name="contentsForm" onsubmit="return false" >
	<input type="hidden" name="fileDir" value="${vo.fileDir }" />
	<table summary="<spring:message code="course.title.contents.manage.folder"/>" class="table table-bordered wordbreak">
		<colgroup>
			<col style="width:40%"/>
			<col style="width:60%"/>
		</colgroup>
		<tr>
			<th scope="row"><label for="fileName"><spring:message code="course.title.contents.folder.name"/></label></th>
			<td class="top">
				<input type="text" dispName="<spring:message code="course.title.contents.folder.name"/>" maxlength="200" isNull="N" lenCheck="200" name="fileName" value="${vo.fileName }" class="form-control input-sm" id="fileName" />
			</td>
		</tr>
		<tr>
			<th scope="row"><spring:message code="course.title.contents.folder.path"/></th>
			<td>
				${vo.fileDir}
			</td>
		</tr>
	</table>
	<p style="margin-left:10px;">* <spring:message code="common.message.file.name.policy"/></p>
	<div class="text-right">
		<a href="javascript:addDir()" class="btn btn-primary btn-sm"><spring:message code="button.create"/> </a>
		<a href="javascript:parent.modalBoxClose()" class="btn btn-default btn-sm"><spring:message code="button.close"/> </a>
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

		$('#contentsForm').attr("action","/mng/course/contents/" + cmd);
		$('#contentsForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		alert(resultDTO.message);

		if(resultDTO.result >= 0) {
			// 정상 처리
			parent.listDir();
			parent.modalBoxClose();
		} else {
			// 비정상 처리
		}
	}

	/**
	 * 과목 등록
	 */
	function addDir() {
		process("addDir");	// cmd
	}
</script>
