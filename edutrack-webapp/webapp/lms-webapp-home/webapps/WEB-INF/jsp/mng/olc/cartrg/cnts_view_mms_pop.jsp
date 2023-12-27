<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="olcCntsVO" value="${olcCntsVO}"/>
<mhtml:mng_html>
<mhtml:mng_head titleName="${MENUNAME}">
	<meditag:css href="libs/daumeditor/css/_org/contents4view.css"/>
</mhtml:mng_head>

<mhtml:frm_body cssTag="background-color:#ffffff;">
	<div style="width:800px;height:100%;margin: 0 auto;">
		${olcCntsVO.cntsFilePath}
	</div>
	<div style="width:800px;height:100%;margin: 0 auto;paddig:5px; text-align: left; color: #000;" class="wordbreak">
		${olcCntsVO.cntsCts}
	</div>
<script type="text/javascript">
	$(document).ready(function() {

	});
</script>
</mhtml:frm_body>
</mhtml:mng_html>