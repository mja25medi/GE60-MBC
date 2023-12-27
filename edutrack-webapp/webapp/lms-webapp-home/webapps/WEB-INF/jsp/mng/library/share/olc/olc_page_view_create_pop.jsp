<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../common/page_init.jsp" %>
<c:set var="clibShareOlcPageVO" value="${clibShareOlcPageVO}"/>
<mhtml:mng_html>
<mhtml:mng_head titleName="${MENUNAME}">
	<meditag:css href="libs/daumeditor/css/_org/contents4view.css"/>
</mhtml:mng_head>

<mhtml:frm_body cssTag="background-color:#ffffff;">
	<div id="pageCts" style="width:100%;height:100%;margin: 0 auto;padding:15px; text-align: left; color: #000;" class="wordbreak">
		<c:if test="${not empty clibShareOlcPageVO.attachFiles }">
		<div class="text-right" style="margin-bottom:10px;">
			<spring:message code="common.title.atachfile"/> :
			<c:forEach var="fileItem" items="${clibShareOlcPageVO.attachFiles}" varStatus="status">
				<a href="<c:url value="/app/file/download/${fileItem.fileSn}"/>" onclick="javascript:fileDown('${fileItem.fileSn}');" title="${fileItem.fileNm}">
				<i class="glyphicon glyphicon-floppy-save"></i>${fileItem.fileNm}
				</a>
			</c:forEach>
		</div>
		</c:if>
		${clibShareOlcPageVO.pageCts}
	</div>
<script type="text/javascript">
	$(document).ready(function() {
		$("body").css("background-color","rgba(255,255,255,.7)");
	});
</script>
</mhtml:frm_body>
</mhtml:mng_html>