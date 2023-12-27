<!DOCTYPE html>
<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="cntsBase" value="/contents/usercnts/${clibShareOlcPageVO.regNo}"/>
<mhtml:class_html>
<mhtml:class_head>
	<script type="text/javascript">
		$(document).ready(function() {
			$("body").css("background-color","rgba(255,255,255,.7)");
			document.location.href = "${cntsBase}/${clibShareOlcPageVO.filePath}";
		});
	</script>
</mhtml:class_head>
<mhtml:frm_body cssTag="background-color:#ffffff;">

</mhtml:frm_body>
</mhtml:class_html>