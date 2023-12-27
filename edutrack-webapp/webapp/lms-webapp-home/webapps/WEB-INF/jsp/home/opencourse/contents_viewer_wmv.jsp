<!DOCTYPE html>
<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="bookmarkVO" value="${vo}" />
<%
	String agent 		= request.getHeader("User-Agent");
	String brType = "";
	if(agent.indexOf("Trident")>0) brType="IE";//-- IE 에서 전체화면시 하단 진행바 동작 이상을 감추어 주기 위해 판단
	request.setAttribute("brType", brType);
	String osMac = "";
	if(agent.indexOf("Mac") > -1) osMac = "Mac";
	request.setAttribute("osMac", osMac);

	request.setAttribute("wowzaUse", Constants.WOWZA_USE);
	request.setAttribute("wowzaUrlStmp", Constants.WOWZA_URL_RTMP);
	request.setAttribute("wowzaUrlStsp", Constants.WOWZA_URL_RTSP);
	request.setAttribute("wowzaUrlHttp", Constants.WOWZA_URL_HTTP);
	request.setAttribute("mediaUse", Constants.MEDIA_USE);
	request.setAttribute("mediaUrl", Constants.MEDIA_URL);
%>
<mhtml:class_html>
<mhtml:class_head>

</mhtml:class_head>
<body>


</body>
</mhtml:class_html>