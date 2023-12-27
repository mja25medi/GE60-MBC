<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
	<form name="FileUpload">
	<table summary="<spring:message code="course.title.contents.manage.folder"/>" class="table table-bordered wordbreak">
		<colgroup>
			<col style="width:40%"/>
			<col style="width:60%"/>
		</colgroup>
		<tr>
			<th scope="row"><label for="fileName"><spring:message code="course.title.contents.folder.name"/></label></th>
			<td>
				${filePath}
			</td>
		</tr>
		<tr>
			<th scope="row"><spring:message code="button.select.file"/></th>
			<td colspan="2" style="height:100;padding-top:5px;padding-bottom:5px" valign=top>
				<a href="javascript:uploderclick('uploader');" class="btn btn-primary btn-xs"><spring:message code="button.select.file"/></a>
				<input type="file" name="uploader" id="uploader" multiple style="display:none" accept="*/*"/>
				<div id="progress" class="progress">
					<div class="progress-bar progress-bar-success"></div>
				</div>
			</td>
		</tr>
	</table>
	<p style="margin-left:10px;">* <spring:message code="common.message.file.name.policy"/></p>
	<div class="text-right">
		<a href="javascript:parent.modalBoxChildClose()" class="btn btn-default btn-sm"><spring:message code="button.close"/> </a>
	</div>
	<input type="submit" value="submit" style="display:none" />
	</form>

<script type="text/javascript">

	var ItemDTO = new Object();

	$(document).ready(function() {
		
		
		
		$('#uploader').fileupload({
			'url'			: cUrl('/app/jquery/upload'),
			'dataType'		: 'json',
			'formData'		: { 'fileDir': '${filePath}',
								"organization"	: "${USER_ORGCD}",
								'type'		: 'contents'},			// 추가 파라매터
			'add'			: function (e, data) {
								$('#progress').show();
								data.submit();
							},
			'done'			: onCompleteCallback,	// 단일 ${attachName} 전송후 콜백함수
			'progressall' 	: function (e, data) {
								var progress = parseInt(data.loaded / data.total * 100, 10);
								$('#progress .progress-bar').css(
									'width',
									progress + '%'
								)
							}
		}).prop('disabled', !$.support.fileInput)
	    .parent().addClass($.support.fileInput ? undefined : 'disabled');
		$('#progress').hide();
	});

	// ${attachName} 업로드 완료 콜백
	function onCompleteCallback(e, data) {
		$('#progress').hide();
		//parent.listDir();
		parent.openNodeFileTree('${fileId}');
		parent.modalBoxChildClose();
	}

	function uploderclick(str) {
		$("#"+str).click();
	}
</script>
