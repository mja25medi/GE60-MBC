<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>
<html>
	<head>
	
	</head>
	<body>
	<c:set var="USER_ORGNM" value="${sessionScope.ORGNM}"/>
	<c:set var="SUB_IMGSN" value="${sessionScope.SUBIMGSN}"/>
						${vo.pageCts }
	</body>
</html>