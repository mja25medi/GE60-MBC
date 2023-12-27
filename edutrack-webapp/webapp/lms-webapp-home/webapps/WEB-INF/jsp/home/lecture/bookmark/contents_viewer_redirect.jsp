<!DOCTYPE html>
<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="bookmarkVO" value="${vo}" />

<html>
<head>
	<script type="text/javascript">
		document.location.href = "${vo.unitFilePath}";
	</script>
</head>
</html>