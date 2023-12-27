<!DOCTYPE html>
<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="bookmarkVO" value="${bookmarkVO}" />

<html>
<head>
	<script type="text/javascript">
		document.location.href = "${bookmarkVO.unitFilePath}";
	</script>
</head>
</html>