<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set scope="request" var="attachType" value="${param.type eq 'image' ? 'image'  : 'file'}"/>
<c:set scope="request" var="attachName" value="${param.type eq 'image' ? 'common.title.attach.image' : 'common.title.attach.file'}"/>
<c:set scope="request" var="repository" value="${empty param.repository ? 'GENERAL' : param.repository}" />
<html>
<mhtml:head titleName="${attachName}">
	<meditag:js src="/libs/daumeditor/js/popup.js"/>
	<meditag:js src="/libs/daumeditor/popup_customize.js"/>
	<meditag:css href="libs/daumeditor/css/popup.css"/>
	<mhtml:head_module fileupload="Y"/>
	<script type="text/javascript">
	$(document).ready(function() {
		$('#uploader').fileupload({
			'url'			: cUrl('/app/file/upload'),
			'dataType'		: 'json',
			'formData'		: { 'repository': '${repository}',
								"organization"	: "${USER_ORGCD}",
								'type'		: '${attachType}'},			// 추가 파라매터
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
							},
		}).prop('disabled', !$.support.fileInput)
	    .parent().addClass($.support.fileInput ? undefined : 'disabled');
		$('#progress').hide();
		initUploader('${attachType}');	// ${attachName} 업로더 초기화.
	});

	// ${attachName} 업로드 완료 콜백
	function onCompleteCallback(e, data) {
		$('#progress').hide();
		AttachFiles.append(data.result);	// 첨부파일 추가
	}

	function uploderclick(str) {
		$("#"+str).click();
	}
	</script>
</mhtml:head>
<mhtml:frm_body>
	<div class="header">
		<h1><spring:message code="${attachName}"/></h1>
	</div>
	<div style="padding:10px; width:100%">
		<div style="float:left;width:50%;">
			<a href="javascript:uploderclick('uploader');" class="btn btn-primary btn-xs"><spring:message code="button.select.file"/></a>
			<input type="file" name="uploader" id="uploader" multiple style="display:none" accept="image/*"/>
		</div>
		<div class="text-right">
			<a href="#" onclick="done();" title="등록" class="btnlink btn btn-primary btn-xs" class="display:none">등록</a>
			<a href="#" onclick="cancle();" title="취소" class="btnlink btn btn-warning btn-xs" id="btnCancel" class="display:none">취소</a>
			<a href="#" onclick="cancle();" title="닫기" class="btn btn-default btn-xs">닫기</a>
		</div>
		<div id="progress" class="progress">
			<div class="progress-bar progress-bar-success"></div>
		</div>
		<ul class="list-group" id="fileList" style="margin-top:10px; border-top:2px solid #e3e3e3;"></ul>
	</div>
</mhtml:frm_body>
</html>
