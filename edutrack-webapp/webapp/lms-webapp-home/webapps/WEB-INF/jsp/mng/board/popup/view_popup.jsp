<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<%pageContext.setAttribute("crlf", "\n");%>

<c:set var="minHeight" value="${vo.ySize }"/>
<c:if test="${vo.popupTypeCd eq 'PWIN' }"><c:set var="minHeight" value="${vo.ySize - 10 }"/></c:if>
<c:if test="${vo.popupTypeCd eq 'PBOX' }"><c:set var="minHeight" value="${vo.ySize - 20 }"/></c:if>
<body>
<div style="position:relative;height:${minHeight}px;">
	<div id="ctsArea" style="min-height: ${minHeight}px; " class="wordbreak">${vo.cts}</div>
	<div style="position:absolute; right:0px; bottom:0px;" class="pull-right mr20">
		<input type="button" value="<spring:message code="button.close"/>" title="<spring:message code="button.close"/>" onclick="closeWin();">
	</div>
</div>

<script type="text/javascript">
	/** 페이지 초기화 */
	$(document).ready(function(){
		<c:if test="${vo.popupTypeCd eq 'PWIN'}">
		document.title = '${vo.title}';
		if($("#ctsArea").height() > window.innerHeight) {
			window.scrollbars;
		}
		</c:if>
		var size = getDisplayInfo();
	});	// end ready..

	function closeWin() {
		<c:if test="${vo.popupTypeCd eq 'PBOX'}">
			parent.modalBoxClose();
		</c:if>
		<c:if test="${vo.popupTypeCd eq 'PWIN'}">
			window.close();
		</c:if>
	}

	function getDisplayInfo() {
		var size = {
		  width: window.innerWidth || document.body.clientWidth,
		  height: window.innerHeight || document.body.clientHeight
		}
		return size;
	}
</script>
</body>
