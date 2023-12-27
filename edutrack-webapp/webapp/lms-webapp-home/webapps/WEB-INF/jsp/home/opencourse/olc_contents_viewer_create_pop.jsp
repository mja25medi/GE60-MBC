<!DOCTYPE html>
<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<mhtml:class_html>
<mhtml:class_head>
	<meditag:css href="libs/daumeditor/css/_org/contents4view.css"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//parent.edutrackAPI.SetPage('${olcCntsDTO.cntsCd}','${pageRatio}');
		});
	</script>
</mhtml:class_head>
<mhtml:frm_body cssTag="background-color:#ffffff;">
	<div style="width:100%;height:100%;margin: 0 auto;paddig:5px;">
		${olcCntsVO.cntsCts}
	</div>
</mhtml:frm_body>
</mhtml:class_html>