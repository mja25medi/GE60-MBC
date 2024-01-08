<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<%@ taglib prefix="meditag" uri="tag-utils" %>

<!DOCTYPE html>
<html lang="ko">
	
	<head>
	    <meta charset="utf-8">
	    <title>${USER_ORGNM }</title>
	    <meta name="viewport" content="width=device-width,initial-scale=1.0">
	    <meta name="description" content="페이지 설명">
	    <link rel="apple-touch-icon-precomposed" sizes="57x57" href="/tpl/${COLOR_TPL}/img/common/apple-touch-icon-57x57.png">
	    <link rel="icon" type="image/png" href="/tpl/${COLOR_TPL}/img/common/favicon-16x16.png" sizes="16x16">
	    <link rel="stylesheet" href="/tpl/${COLOR_TPL}/css/hrd_common.css">
	    <link rel="stylesheet" href="/tpl/${COLOR_TPL}/css/webfonts.css">
	    <link rel="stylesheet" href="/tpl/${COLOR_TPL}/css/layout.css">    
        <link rel="stylesheet" href="/tpl/${COLOR_TPL}/css/main.css"><!-- main 페이지에서 사용 -->
	    <link rel="stylesheet" href="/tpl/${COLOR_TPL}/css/effect_slick.css">
	
	    <script src="/tpl/${COLOR_TPL}/jquery/jquery-3.2.1.min.js"></script>
	    <script defer src="/tpl/${COLOR_TPL}/jquery/slick.min.js"></script>
	    <script defer src="/tpl/${COLOR_TPL}/js/func.min.js"></script>
	    <script defer src="/tpl/${COLOR_TPL}/js/common.js"></script>
	    <script defer src="/tpl/${COLOR_TPL}/js/main.js"></script>
	    <script src="/tpl/${COLOR_TPL}/jquery/jquery.cycle2.js"></script>
    	<script src="/tpl/${COLOR_TPL}/js/iframeResizer.min.js"></script>

    <!-- Summernote Editor -->
    <link rel="stylesheet" href="/tpl/${COLOR_TPL}/js/summernote/summernote-lite.css" />
    <link rel="stylesheet" href="/tpl/${COLOR_TPL}/js/summernote/plugin/math/katex.min.css" />
    <link rel="stylesheet" href="/tpl/${COLOR_TPL}/js/summernote/plugin/emoji/css/emoji.css" />

	<!-- Summernote Editor -->
    <script src="/tpl/${COLOR_TPL}/js/summernote/summernote-lite.js"></script>
    <script src="/tpl/${COLOR_TPL}/js/summernote/lang/summernote-ko-KR.js"></script>
    <script src="/tpl/${COLOR_TPL}/js/summernote/plugin/math/katex.min.js"></script>
    <script src="/tpl/${COLOR_TPL}/js/summernote/plugin/math/summernote-math.js"></script>
    <script src="/tpl/${COLOR_TPL}/js/summernote/plugin/emoji/js/config.js"></script>
    <script src="/tpl/${COLOR_TPL}/js/summernote/plugin/emoji/js/tam-emoji.js"></script>
	<script src="/tpl/${COLOR_TPL}/js/summernote/plugin/uploadcare.js"></script>



	    <meditag:js src="/app/js/Context.js"/>
		<meditag:js src="/js/common_conf.js"/>
	    
	 
		<script src="/app/js/Context.js"></script>
		<script src="/tpl/${COLOR_TPL}/js/common_conf.js"></script>
		<script src="/tpl/${COLOR_TPL}/js/common_util.js"></script>
		<script src="/tpl/${COLOR_TPL}/js/common_function.js"></script>
	
		<script src="/js/nuguya/nice.nuguya.oivs.crypto.js"></script>
		<script src="/js/nuguya/nice.nuguya.oivs.msg.js"></script>
		<script src="/js/nuguya/nice.nuguya.oivs.util.js"></script>
	
<c:if test="${not empty daumeditor}">
	<meditag:css href="libs/daumeditor/css/editor.css"/>
	<meditag:js src="/libs/daumeditor/js/editor_loader.js"/>
	<meditag:js src="/libs/daumeditor/js/editor.js"/>
	<script src="http://google-code-prettify.googlecode.com/svn/trunk/src/prettify.js"></script>
	<meditag:js src="/js/common_daumeditor.js"/>
</c:if><c:if test="${not empty uploadify}">
	<meditag:css href="css/uploadify.css"/>
	<meditag:js src="/js/jquery/jquery.uploadify.v2.1.4.min.js"/>
	<meditag:js src="/js/jquery/swfobject.js"/>
</c:if><c:if test="${not empty nyromodal}">
	<meditag:css href="/css/nyroModal.css"/>
	<meditag:js src="/js/jquery/jquery.nyroModal-1.6.2.min.js"/>
</c:if><c:if test="${not empty encryptjs}">
	<meditag:js src="/js/nuguya/nice.nuguya.oivs.crypto.js"/>
	<meditag:js src="/js/nuguya/nice.nuguya.oivs.msg.js"/>
	<meditag:js src="/js/nuguya/nice.nuguya.oivs.util.js"/>
</c:if><c:if test="${not empty classroom}">
	<meditag:css href="css/home/classroom.css" />
</c:if><c:if test="${not empty paging}">
	<meditag:css href="css/paginator3000.css"/>
	<meditag:js src="/js/paginator3000.js"/>
</c:if><c:if test="${not empty fileupload}">
	<meditag:js src="/js/jquery/jquery-fileupload/vendor/jquery.ui.widget.js"/>
	<meditag:js src="/js/jquery/jquery-fileupload/jquery.iframe-transport.js"/>
	<meditag:js src="/js/jquery/jquery-fileupload/jquery.fileupload.js"/>
	<meditag:js src="/js/common_fileupload.js"/>
</c:if><c:if test="${not empty colorpicker}">
	<meditag:css href="libs/jquery.colorpicker/css/colorpicker.css"/>
	<meditag:css href="libs/jquery.colorpicker/css/layout.css"/>
	<meditag:js src="/libs/jquery.colorpicker/js/colorpicker.js"/>
	<meditag:js src="/libs/jquery.colorpicker/js/eye.js"/>
	<meditag:js src="/libs/jquery.colorpicker/js/utils.js"/>
	<%-- <meditag:js src="/libs/jquery.colorpicker/js/layout.js?ver=1.0.2"/> --%>
</c:if><c:if test="${not empty d3chart}">
	<![if (!IE) | (gte IE 9)]>
	<meditag:js src="/libs/d3chart/d3.js"/>
	<meditag:js src="/libs/d3chart/liquidFillGauge_custom.js"/>
	<![endif]>
</c:if><c:if test="${not empty asterplot}">
	<![if (!IE) | (gte IE 9)]>
	<meditag:js src="/libs/d3chart/aster_plot/d3.v3.min.js"/>
	<meditag:js src="/libs/d3chart/aster_plot/d3.tip.v0.6.3.js"/>
	<%-- <meditag:js src="/libs/d3chart/aster_plot/draw.js"/> --%>
	<meditag:css href="libs/d3chart/aster_plot/aster_plot.css"/>
	<![endif]>
</c:if>
	<meditag:js src="/tpl/${COLOR_TPL}/js/modaldialog2.js"/>


	<c:if test="${AJAXMESSAGE eq 'use'}">
	<script type="text/javascript">
		// jQuery ajax 처리 오류 바인딩. (개발환경에서만 사용)
		$(document).ajaxError(function(e, xhr, settings, exception) {
			alert("Ajax 처리중 오류 발생 \n" +
					+ '오류URL  : ' + settings.url + '\n'
					+ '서버응답 : ' + xhr.status + " : " + xhr.statusText + '\n'
					+ 'Exception: ' + exception);
		});
	</script>
	</c:if>
	
	    
		<sitemesh:write property="head"/>
	
	</head>

	<body>
	    
	    <div id="wrap">
			<c:import url="/WEB-INF/jsp/tpl/home/${COLOR_TPL}/inc/header.jsp" charEncoding="UTF-8"/>
	        <main class="main">
	 			<sitemesh:write property="body"/>
	        </main>
       		<jsp:include page="/WEB-INF/jsp/tpl/home/${COLOR_TPL}/inc/footer.jsp" />
       
    	</div>

	<%-- 세션이나 리퀘스트에 ALERT_MESSAGE 가 있으면 알려주고 내용을 삭제한다. --%>
	<c:if test="${not empty ALERT_MESSAGE}">
	<div id="sessionflashmsg" style="display:none;">${ALERT_MESSAGE}</div>
		<c:set var="flashMsg" value="${ALERT_MESSAGE}"/><c:remove var="ALERT_MESSAGE" scope="session"/>
		<script type="text/javascript">
			//$.growlUI('알림', '${flashMsg}');
			$(document).ready(function() {
				alert(`${flashMsg}`);
			});
		</script>
	</c:if>
	<script type="text/javascript">
	
	$(document).ready(function() {
		/* popupNotice(); */
		chkGrpIdVerified();
	})
	//--------------- 팝업 공지사항 관련 처리 ------------------------------------------------------------------------------
	<c:forEach items="${popupNoticeList}" var="item">
	<c:if test="${item.popupTypeCd eq 'PBOX'}">
	var noticePopBox${item.popupSn};
	</c:if>
	</c:forEach>

	function popupNotice() {
<c:forEach items="${popupNoticeList}" var="item">
	<c:if test="${item.scrollYn eq 'Y'}">
		<c:set var="scrollYnNm" value="yes"/>
	</c:if>
	<c:if test="${item.scrollYn eq 'N'}">
		<c:set var="scrollYnNm" value="no"/>
	</c:if>

		if(getCookiePopupNotice('PN${item.popupSn}') != 'No') {
			var url = generateUrl("/home/brd/popup/indexReadPop2",{"popupSn":"${item.popupSn}"});
	<c:if test="${item.popupTypeCd eq 'PBOX'}">
			noticePopBox${item.popupSn} = new $M.ModalDialog({
				"modalid" : "noticeModal${item.popupSn}",
				"nomargin" : true
			});
			var addContent  = "<iframe id='popupFrame' name='popupFrame' width='100%' height='100%' "
				+ "frameborder='0' scrolling='auto' src='"+url+"'/>";
			noticePopBox${item.popupSn}.clear();
			noticePopBox${item.popupSn}.addContents(addContent);
			noticePopBox${item.popupSn}.resize("${item.xSize}","${item.ySize}");
			noticePopBox${item.popupSn}.move("${item.xPos}","${item.yPos}");
			noticePopBox${item.popupSn}.show();
			
			
			
			
	</c:if>
	<c:if test="${item.popupTypeCd eq 'PWIN'}">
			var noticePopBox${item.popupSn} = window.open(url, 'pnBox${item.popupSn}','width=${item.xSize}, height=${item.ySize+25}, top=${item.yPos}, left=${item.xPos}, scrollbars=auto');
	</c:if>
		}
</c:forEach>
	}

	function setCookiePopupNotice( name, value, expiredays ) {
		var todayDate = new Date();
		todayDate.setDate( todayDate.getDate() + expiredays );
		document.cookie = name + "=" + escape( value ) + "; path=/; expires=" + todayDate.toGMTString() + ";"
	}

	function getCookiePopupNotice( name ) {
		var nameOfCookie = name + "=";
		var x = 0;
		while ( x <= document.cookie.length ) {
			var y = (x+nameOfCookie.length);
			if ( document.cookie.substring( x, y ) == nameOfCookie ) {
				if ( (endOfCookie=document.cookie.indexOf( ";", y )) == -1 )
					endOfCookie = document.cookie.length;
				return unescape( document.cookie.substring( y, endOfCookie ) );
			}
			x = document.cookie.indexOf( " ", x ) + 1;
			if ( x == 0 )
				break;
		}
		return "";
	}
	//--------------- 팝업 공지사항 관련 처리 끝 ------------------------------------------------------------------------------
	function chkGrpIdVerified() {
		var verifiedId = '${sessionScope.verifiedId}';
		if(verifiedId == "false") {
			initVerfiyPop();
			alert("인증되지 않은 회원입니다.\n휴대폰 인증 및 비밀번호 변경 완료 후에 서비스 이용가능합니다.");
		}
	}
	
	function initVerfiyPop() {
		var addContent  = '<c:url value="/home/user/initVerifyPop2"/>';
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.setTitle("본인인증 및 비밀번호 변경");
		modalBox.show();	
	}

	</script>

	</body>
	
</html>
	

