<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<mhtml:home_html>
<mhtml:home_head titleName="${MENUNAME}">
<script type="text/javascript">

</script>
	<meditag:js src="/libs/kollus/cors-upload.js"/>
</mhtml:home_head>
<body cssTag="background-color:#fff;">
	<form name="clibMediaFileForm" method="post"  onsubmit="return false;">
		<input type="hidden" name="category_key" id="category" value="${kollusCtgrCd}"/>
		<input type="hidden" name="is_audio_upload" value="0" id="is_audio">

		<ul class="nav nav-tabs" id="tabMenu">
			<li id="videoTab"><a href="javascript: changeType('M')"><spring:message code="library.title.media.upload.video"/></a></li>
			<li id="audioTab"><a href="javascript: changeType('A')"><spring:message code="library.title.media.upload.audio"/></a></li>
		</ul><br/>

		<p id="infoVideo" style="display: none;"><spring:message code="library.message.media.upload.audio.info"/></p>
		<p id="infoAudio" style="display: none;"><spring:message code="library.message.media.upload.video.info"/></p>

		<div class="form-group">
			<label for="upload-file">Upload file</label>
			<div class="progress" style="display: none;"></div>
			<input type="file" class="form-control" id="upload-file" name="upload-file" placeholder="Upload File" style="height: 40px;">
		</div>
		<!--
		<div class="checkbox">
			<label>
				<input type="checkbox" name="is_encryption_upload" value="1"> Is encryption
			</label>
		</div>

		<div class="checkbox">
			<label>
				<input type="checkbox" name="is_audio_upload" value="1"> Is audio
			</label>
		</div>
		 -->
		<button type="button" class="btn btn-default" data-action="upload-file" autocomplete="off" data-loading-text="" onclick="checkFile(); return false;"><i class="fa fa-upload"></i> Upload</button>
	</form>
</body>
<script type="text/javascript">
$(document).ready(function() {
	$("#videoTab").addClass("active");
	$("#infoVideo").show();
});

function changeType(type){
	$("#videoTab").removeClass("active");
	$("#audioTab").removeClass("active");
	$("#infoVideo").hide();
	$("#infoAudio").hide();
	$("#is_audio").val("0");

	if("M" == type){
		$("#videoTab").addClass("active");
		$("#infoVideo").show();
		$("#is_audio").val("0");
	} else if("A"==type){
		$("#audioTab").addClass("active");
		$("#infoAudio").show();
		$("#is_audio").val("1");

	}
}

function checkFile(){
	if($("#upload-file").val() == ""){
		alert("<spring:message code="olc.message.design.alert.uploadfile"/>");
		return;
	} else {
		document.clibMediaFileForm.action="/home/library/clibMediaCnts/getUploadInfo";
		document.clibMediaFileForm.method="post";
		document.clibMediaFileForm.ajaxSubmit(processCallback);

	}
}


/**
 * 처리 결과 표시 콜백
 */
function processCallback(resultDTO) {
	alert(resultDTO);
	if(resultDTO.result >= 0) {

	} else {
		// 비정상 처리
	}
}
</script>
</mhtml:home_html>
