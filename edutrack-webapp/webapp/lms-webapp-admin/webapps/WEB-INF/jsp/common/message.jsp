<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="model" value="${MODEL}" />
<c:set var="img_base" value="/img/${LOCALEKEY}"/>
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
<mhtml:html>
<mhtml:head titleName="Error Page">

</mhtml:head>

<mhtml:body>

<table border="0" cellpadding="0" cellspacing="0" style="width:100%;height:100%">
	<tr>
		<td align="center" width="100%" height="100%" style="padding-top:100px">
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td style="background-image:url(${img_base}/common/error_msg.jpg"  width="690" height="157">
						<p style="padding-top:20px;padding-left:30px;font-weight:bold;font-size:12pt"><meditag:message messageKey="${model.MSG}"/></p>
					</td>
				</tr>
				<tr>
					<td height="50" align="center" >
					<c:url var="linkUrl" value="/" />
					<a href="${linkUrl}" target="_top"><img src="${img_base}/common/btn_entry01.gif"/></a>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>

</mhtml:body>
</mhtml:html>
