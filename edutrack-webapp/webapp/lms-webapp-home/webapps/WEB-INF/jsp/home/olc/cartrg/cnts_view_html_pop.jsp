<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="olcCntsVO" value="${vo}"/>
<c:set var="cntsBase" value="/contents/usercnts/"/>
<mhtml:home_html>
<mhtml:home_head titleName="${MENUNAME}">
	<meditag:css href="libs/daumeditor/css/_org/contents4view.css"/>
</mhtml:home_head>
<body style="padding-top: 0px;">
<script type="text/javascript">
	$(document).ready(function() {
		$("body").css("background-color",parent.contentColor);
		document.location.href = "${cntsBase}/${vo.cntsFilePath}";
	});
</script>
</body>
</mhtml:home_html>