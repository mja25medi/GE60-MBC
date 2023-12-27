<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="img_base" value="/img/home" />
<c:set var="model" value="${MODEL}" />
<%--
	Map model = (Map)request.getAttribute("MODEL");
	String mode=(String)model.get("ALERT_MODE");
	String type=(String)model.get("MSG_TYPE");
	String alert=(String)model.get("ALERT");
	String url=(String)model.get("URL");
	String param=(String)model.get("PARAM");
	String go=(String)model.get("GO");
	String close=(String)model.get("CLOSE");
	String closePopName = "";
	closePopName=(String)model.get("POPUPNAME");
	String message=(String)model.get("MSG");
	String reload=(String)model.get("RELOAD");
	String exception=(String)model.get("EXCEPTION");
 --%>
<html>
<c:url var="linkUrl" value="/" />
<c:if test="${not empty requestScope.goUrl}">
	<c:url var="linkUrl" value="${requestScope.goUrl}" />
</c:if>
<mhtml:head titleName="Error Page">
<script type="text/javascript">
<!--
	$(document).ready(function() {
		var width = document.body.clientWidth;
		var height =  document.body.clientHeight;
		try {
			if(width < 500) {
				parent.modalBox.resize(500, height);
			}
		} catch (err) {	}
	});

	function goConfirm() {
		if(opener != undefined) {
			window.close();
		} else {
			try {
				parent.modalBoxClose();
			} catch (err){
				if('${linkUrl}' == '/') top.document.location.href = '/';
				else document.location.href = '${linkUrl}';
			}
		}
	}
//-->
</script>
</mhtml:head>
<mhtml:body>
<div style="width:100%;height:100%;vertical-align: middle;">
	<table border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td style="background-image:url(<c:url value="/img/new_error_msg.jpg"/>);background-position: right;" width="690" height="157">
				<p style="padding:0px 10px 0px 10px;font-size:12pt;line-height: 25px;">
				<spring:message code="${model.MSG}"/><br/>
				<c:if test="${model.MSG eq 'common.message.security.badrequest' || model.MSG eq 'common.message.security.badrequest' }">
				<a href="mailto:support@eduknotz.com" >support@eduknotz.com</a>
				</c:if>
				</p>
			</td>
		</tr>
		<tr>
			<td height="50" align="center" >
			<a href="javascript:goConfirm();" class="btn btn-primary"><spring:message code="button.ok"/></a>
			</td>
		</tr>
	</table>
</div>
</mhtml:body>
</html>
