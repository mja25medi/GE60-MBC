<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>

<form id="adminLoginForm" name="adminLoginForm" action="${redirectUrl }" method="post">
	<input type="hidden" name="userNo" value="${vo.userNo}">
	<input type="hidden" name="orgCd" value="${USER_ORGCD}">
	<input type="hidden" name="mngAuthGrpCd" value="${MNGTYPE}">
</form>

<script type="text/javascript">
	document.adminLoginForm.submit();
</script>