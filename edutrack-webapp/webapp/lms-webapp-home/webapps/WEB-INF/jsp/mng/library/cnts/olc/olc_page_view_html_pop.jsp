<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="clibOlcPageDTO" value="${clibOlcCntsForm.clibOlcPageDTO}"/>
<c:set var="cntsBase" value="/contents/usercnts"/>
<mhtml:mng_html>
<mhtml:mng_head titleName="${MENUNAME}">
	<meditag:css href="libs/daumeditor/css/_org/contents4view.css"/>
</mhtml:mng_head>
<mhtml:frm_body cssTag="background-color:#ffffff;">

<script type="text/javascript">
	$(document).ready(function() {
		$("body").css("background-color","rgba(255,255,255,.7)");
		var cntsBase = "${cntsBase}";
		var filePath = "${clibOlcPageDTO.filePath}";
		var usrno = "${USER_NO}";
		if(filePath.indexOf("USR") == -1){
			cntsBase = cntsBase+"/"+usrno;
		}
		document.location.href = cntsBase + "/${clibOlcPageDTO.filePath}";
	});
</script>
</mhtml:frm_body>
</mhtml:mng_html>