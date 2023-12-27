<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="img_base" value="/img/home" />
<html>
<head>
	<title>수료증</title>
	<script type="text/javascript">
		document.location.href = "${reportUrl}?rex_rptname=${reportFile}${AMPERSAND}stdNo=${stdNo}";
	</script>
</head>
</html>