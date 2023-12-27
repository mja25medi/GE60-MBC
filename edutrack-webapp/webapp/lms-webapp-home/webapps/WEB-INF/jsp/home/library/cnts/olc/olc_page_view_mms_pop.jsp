<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="clibOlcPageDTO" value="${clibOlcCntsForm.clibOlcPageDTO}"/>
<mhtml:home_html>
<mhtml:home_head titleName="${MENUNAME}">
	<meditag:css href="libs/daumeditor/css/_org/contents4view.css"/>
</mhtml:home_head>

<body style="padding-top: 0px;">
	<div style="width:100%;height:100%;margin: 0 auto;">
		${clibOlcPageDTO.filePath}
	</div>
	<div style="width:100%;margin: 0 auto;paddig:5px; text-align: left; color: #000;" class="wordbreak">
		${clibOlcPageDTO.pageCts}
	</div>
<script type="text/javascript">
	$(document).ready(function() {
		$("body").css("background-color","rgba(255,255,255,.7)");
	});
</script>
</body>
</mhtml:home_html>