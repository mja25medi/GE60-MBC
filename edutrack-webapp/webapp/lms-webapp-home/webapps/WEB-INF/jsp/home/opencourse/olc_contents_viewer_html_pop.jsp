<!DOCTYPE html>
<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="cntsBase" value="/contents/usercnts/${olcCntsVO.userNo}"/>
<mhtml:class_html>
<mhtml:class_head>
	<script type="text/javascript">
		$(document).ready(function() {
			//parent.edutrackAPI.SetPage('${olcCntsDTO.cntsCd}','${pageRatio}');
			document.location.href = "${cntsBase}/${olcCntsVO.cntsCts}";
		});
	</script>
</mhtml:class_head>
<mhtml:frm_body cssTag="background-color:#ffffff;">

</mhtml:frm_body>
</mhtml:class_html>