<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<html>
<mhtml:home_head titleName="${MENUNAME}">
	<meditag:css href="libs/daumeditor/css/contents_view.css"/>
</mhtml:home_head>

<c:set var="minHeight" value="${brdPopupNoticeVO.ySize }"/>
<c:if test="${brdPopupNoticeVO.popupTypeCd eq 'PWIN' }"><c:set var="minHeight" value="${brdPopupNoticeVO.ySize - 10 }"/></c:if>
<c:if test="${brdPopupNoticeVO.popupTypeCd eq 'PBOX' }"><c:set var="minHeight" value="${brdPopupNoticeVO.ySize - 20 }"/></c:if>
<body style="background-color:#fff;padding-top:0px;">
<div style="position:relative;height:${minHeight}px;">
	<div id="ctsArea" style="min-height: ${minHeight}px; " class="wordbreak">${brdPopupNoticeVO.cts}</div>
	<div class="popup-footer text-right" style="height:30px;bottom:0px;width:100%;padding:5px 10px 5px 10px;background-color:#dedede;">
		<label><spring:message code="board.title.popup.noopen"/> <input type="checkbox" name="chk" value="Y" onClick="cookieSeting()" style="border:0" / ></label>
	</div>
</div>
<script type="text/javascript">
	var Item = new Object();
	Item.popupType = "${brdPopupNoticeVO.popupTypeCd}";

	/** 페이지 초기화 */
	$(document).ready(function(){
		<c:if test="${brdPopupNoticeVO.popupTypeCd eq 'PWIN'}">
		document.title = '${brdPopupNoticeVO.title}';
		</c:if>
	});	// end ready..

	function cookieSeting() {
		if(Item.popupType == 'PBOX') {
			setCookiePopupNotice( 'PN${brdPopupNoticeVO.popupSn}', 'No', 1 );
			parent.noticePopBox${brdPopupNoticeVO.popupSn}.remove();
		} else {
			setCookiePopupNotice( 'PN${brdPopupNoticeVO.popupSn}', 'No', 1 );
			window.close();
		}
	}

	function setCookiePopupNotice( name, value, expiredays ) {
		var todayDate = new Date();
		todayDate.setDate( todayDate.getDate() + expiredays );
		document.cookie = name + "=" + escape( value ) + "; path=/; expires=" + todayDate.toGMTString() + ";"
	}
</script>
</body>
</html>