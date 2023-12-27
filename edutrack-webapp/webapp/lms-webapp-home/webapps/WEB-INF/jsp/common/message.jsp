<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="img_base" value="/img/home" />
<c:set var="model" value="${MODEL}" />
<html>
<head>

</head>
<body>
	<c:if test="${not empty ALERT_MESSAGE}">
	<div id="sessionflashmsg" style="display:none;">${ALERT_MESSAGE}</div>
	<c:set var="flashMsg" value="${ALERT_MESSAGE}"/><c:remove var="ALERT_MESSAGE" scope="session"/>
	
	<%--세션 메시지 --%>
	<script type="text/javascript">
		$(document).ready(function() {
			alert('${flashMsg}');
		});
	</script>
	</c:if>
	
	<%-- 파라미터 메시지 --%>
	<c:if test="${not empty MESSAGE }">
		<script type="text/javascript">
			$(document).ready(function() {
				alert('${MESSAGE}');
			});
		</script>
	</c:if>

	<%-- parent reload  --%>
	<c:if test="${parentReloadYn eq 'Y'}">
		<script type="text/javascript">
			parent.document.location.reload();
		</script>
	</c:if>
	
	<%-- 모달 닫기 유무 --%>
	<c:if test="${modelCloseYn eq 'Y'}">
		<script type="text/javascript">
			parent.$('.modal').modal('hide');
		</script>
	</c:if>
	
</body>
</html>
