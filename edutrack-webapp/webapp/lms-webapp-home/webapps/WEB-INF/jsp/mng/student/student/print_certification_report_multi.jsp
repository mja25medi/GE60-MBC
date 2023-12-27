<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<mhtml:html>
<mhtml:head titleName="${MENUNAME}">
<script type="text/javascript">
	$(document).ready(function() {
		document.location.href = "${reportUrl}?rex_rptname=${reportFile}${AMPERSAND}crsCreCd=${crsCreCd}";
	});
</script>
</mhtml:head>
</mhtml:html>