<!DOCTYPE html>
<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="bookmarkVO" value="${bookmarkVO}" />
<%
	String agent 		= request.getHeader("User-Agent");
	String brType = "";
	if(agent.indexOf("Trident")>0) brType="IE";//-- IE 에서 전체화면시 하단 진행바 동작 이상을 감추어 주기 위해 판단
	request.setAttribute("brType", brType);
	String osMac = "";
	if(agent.indexOf("Mac") > -1) osMac = "Mac";
	request.setAttribute("osMac", osMac);

	request.setAttribute("mediaStreamUse", Constants.MEDIA_STREAM_USE);
	request.setAttribute("mediaStreamUrl", Constants.MEDIA_STREAM_URL);
	request.setAttribute("mediaStreamHls", Constants.MEDIA_STREAM_HLS);
%>
<mhtml:class_html>
<mhtml:class_head>

</mhtml:class_head>
<body>


</body>
</mhtml:class_html>