<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<html>
<mhtml:head titleName="Multimedia">
	<meditag:js src="/libs/daumeditor/js/popup.js"/>
	<meditag:css href="libs/daumeditor/css/popup.css"/>
	<script type="text/javascript">
		$(document).ready(function() {
			initEmbeder();
			selectType('codeSource');
		});

		function initEmbeder() {
			var _opener = PopupUtil.getOpener();
			if(!_opener) {
				alert('잘못된 경로로 접근하셨습니다.');
				return;
			}

			var _embeder = getEmbeder('media');
			window.execEmbed = _embeder.embedHandler;
		}

		// 코드 삽입 완료 후
		function done() {
			var _data = {};
			var types = document.getElementsByName("codeType");
			if (types[1].checked) {
				_data.url = document.getElementsByName("url")[0].value.trim();
			} else if (types[0].checked) {
				_data.code = document.getElementsByName("source")[0].value.trim();
			}
			if (typeof(execEmbed) == 'undefined') { //Virtual Function
		        return;
		    }
			execEmbed(_data);
			closeWindow();
		}

		function selectType(id){
			var textArea = document.getElementsByName("source")[0];
			var input = document.getElementsByName("url")[0];
			if ( id == 'codeUrl' ){
				textArea.value = "";
				textArea.disabled = true;
				input.disabled=false;
				input.focus();
			}else{
				textArea.disabled = false;
				input.value = "";
				input.disabled=true;
				textArea.focus();
			}
		}

		function closeWindow() {
			top.close();
		}
	</script>
</mhtml:head>
<mhtml:frm_body>
	<div class="header">
		<h1>외부컨텐츠 삽입</h1>
	</div>
	<div style="padding:10px; width:100%">
		<div class="well well-sm">
			<p class="desc">아래 <span>멀티미디어 </span> 등의 삽입 방식을 선택한 후, 주소를 입력하세요.</p>
			<label style="font-weight: normal;"><input type="radio" id="type_source" value="source" name="codeType" onclick="selectType('codeSource');" checked="checked" style="border:none" /> html(embed,object 소스입력)</label>
			<label style="font-weight: normal;"><input type="radio" id="type_url" value="url" name="codeType" onclick="selectType('codeUrl');" style="border:none" /> 멀티미디어 링크</label>
			<table border="0" style="width:100%;">
				<tbody>
					<tr>
						<th><label>소스입력 </label></th>
						<td><textarea rows="4" name="source" id="codeSource" class="form-control input-sm"></textarea></td>
					</tr>
					<tr>
						<th><label>링크입력 </label></th>
						<td><input type="text" name="url" id="codeUrl" class="form-control input-sm" /></td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="text-right">
			<a href="#" onclick="done();" title="<spring:message code="button.add"/>" class="btnlink btn btn-primary btn-xs"><spring:message code="button.add"/></a>
			<a href="#" onclick="closeWindow();" title="<spring:message code="button.close"/>" class="btn btn-default btn-xs"><spring:message code="button.close"/></a>
		</div>
	</div>
</mhtml:frm_body>
</html>