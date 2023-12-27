<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="egovframework.edutrack.Constants"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring"  uri="http://www.springframework.org/tags" %>
<html xmlns="http://www.w3.org/1999/xhtml" lang="<%=Constants.LANG_DEFAULT%>">
<head>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<title>[<%=Constants.HOST_NAME%>]</title>
	<script type="text/javascript">
		document.location.href = "<c:url value="/mng/main/loginMain"/>";
	</script>
</head>
<body></body>
</html>