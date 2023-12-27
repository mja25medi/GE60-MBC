<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="olcCntsVO" value="${olcCntsVO}"/>
<c:set var="cntsBase" value="/contents/usercnts"/>
<mhtml:mng_html>
<mhtml:mng_head titleName="${MENUNAME}">
	<meditag:css href="libs/daumeditor/css/_org/contents4view.css"/>
</mhtml:mng_head>
<mhtml:frm_body cssTag="background-color:#ffffff;">

<script type="text/javascript">
	$(document).ready(function() {
		$("body").css("background-color",parent.contentColor);
		document.location.href = "${cntsBase}/${olcCntsVO.cntsFilePath}";
	});
</script>
</mhtml:frm_body>
</mhtml:mng_html>