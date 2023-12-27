<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="clibOlcPageDTO" value="${clibOlcCntsForm.clibOlcPageDTO}"/>
<c:set var="cntsBase" value="/contents/usercnts"/>
<mhtml:home_html>
<mhtml:home_head titleName="${MENUNAME}">
	<meditag:css href="libs/daumeditor/css/_org/contents4view.css"/>
</mhtml:home_head>

<body style="padding-top: 0px;">

<script type="text/javascript">
	$(document).ready(function() {
		$("body").css("background-color","rgba(255,255,255,.7)");
		var cntsBase = "${cntsBase}";
		var filePath = "${clibOlcPageDTO.filePath}";
		var usrno = "${clibOlcPageDTO.regNo }";
		if(filePath.indexOf("USR") == -1){
			cntsBase = cntsBase+"/"+usrno;
		}
		document.location.href = cntsBase + "/${clibOlcPageDTO.filePath}";
	});
</script>
</body>
</mhtml:home_html>