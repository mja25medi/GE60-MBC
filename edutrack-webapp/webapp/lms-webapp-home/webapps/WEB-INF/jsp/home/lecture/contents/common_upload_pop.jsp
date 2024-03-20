<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
	<form name="FileUpload">
		<input type="hidden" name="category_key" id="category" value="${kollusCtgrCd}"/>

		<div class="form-group">
			<label for="upload-file">Upload file</label>
			<div id="progress" class="progress">
				<div class="progress-bar progress-bar-success"></div>
			</div>
			<input type="file" class="form-control" id="uploader" name="uploader" placeholder="Upload File" style="height: 40px;">
		</div>

		<!-- <button type="submit" class="btn btn-default" autocomplete="off" id="submit-btn"><i class="fa fa-upload"></i> Upload</button> -->
	</form>

<script type="text/javascript">
	var ItemDTO = new Object();
	var fileForm;
	$(document).ready(function() {
		$('#uploader').fileupload({
			'url'			: cUrl('/app/jquery/upload'),
			'dataType'		: 'json',
			'formData'		: { 'fileDir': '${filePath}',
								"organization"	: "${USER_ORGCD}",
								'type'		: 'contents'},			// 추가 파라매터
			'add'			: function (e, data) {
								var fileName = data.files[0].name.split(".");
								var fileExt = fileName[1].toLowerCase();
								if(fileExt != "mp4" && fileExt != "flv" && fileExt != "mp3") {
									alert('<spring:message code="common.message.file.alert.nouse.fileformat"/>');
									return;
								}
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
		var fileName = "";
		$.each(data.files, function (index, file) {
			fileName = file.name;
		});
		$("#filePath",parent.document).val('${filePath}');
		$("#fileNm",parent.document).val(fileName);
		$("#playerDiv",parent.document).val("common");
		$("#uldStsCd",parent.document).val("complete");
		$("#media-file-name",parent.document).val(fileName);
		$("#media-file-name",parent.document).show();
		$("#media-file-upload-button",parent.document).hide();
		parent.attachModalBoxClose();
	}

	function uploderclick(str) {
		$("#"+str).click();
	}
</script>
