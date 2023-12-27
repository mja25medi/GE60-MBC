<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>
<html>
<head>
<script type="text/javascript">

<c:if test="${result ne 0 }">
alert("<spring:message code="home.open.certmessage1"/>");
window.close();
</c:if>
<c:if test="${result eq 0}">
alert("<spring:message code="home.open.certmessage2"/>");
window.close();
</c:if>
</script>
</head>

<body>
</body>
</html>