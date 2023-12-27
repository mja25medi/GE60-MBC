<!DOCTYPE html>
<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<mhtml:class_html>
<mhtml:class_head>
	<meditag:css href="libs/daumeditor/css/_org/contents4view.css"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("body").css("background-color","rgba(255,255,255,.7)");
		});
	</script>
</mhtml:class_head>
<mhtml:frm_body cssTag="background-color:#ffffff;">
	<div style="width:100%;height:100%;margin: 0 auto;paddig:5px;" class="wordbreak">
		<c:if test="${not empty clibOlcPageVO.attachFiles }">
		<div class="text-right" style="margin-bottom:10px;">
			<spring:message code="common.title.atachfile"/> :
			<c:forEach var="fileItem" items="${clibOlcPageVO.attachFiles}" varStatus="status">
				<a href="<c:url value="/app/file/download/${fileItem.fileSn}"/>" onclick="javascript:fileDown('${fileItem.fileSn}');" title="${fileItem.fileNm}">
				<i class="glyphicon glyphicon-floppy-save"></i>${fileItem.fileNm}
				</a>
			</c:forEach>
		</div>
		</c:if>
		${clibShareOlcPageVO.pageCts}
	</div>

</mhtml:frm_body>
</mhtml:class_html>