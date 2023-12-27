<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<%@ taglib prefix="meditag" uri="tag-utils" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="Mediopia Tech Edutrack Global">
	<meta name="author"	content="Mediopia Tech">
	<title>${MENUNAME}</title>

	<!-- Bootstrap Core	CSS	-->
	<meditag:css href="tpl/bootstrap/bower_components/bootstrap/dist/css/bootstrap.min.css"/>
	<!-- MetisMenu CSS -->
	<meditag:css href="tpl/bootstrap/bower_components/metisMenu/dist/metisMenu.min.css"/>
	<!-- Custom	Fonts -->
	<meditag:css href="tpl/bootstrap/bower_components/font-awesome/css/font-awesome.min.css"/>
	<!-- Jquery Ui CSS -->
	<meditag:css href="css/jquery-ui-1.11.0.custom/jquery-ui.css"/>
	<!-- Edutrack CSS -->
	<meditag:css href="tpl/${COLOR_TPL}/css/edutrack.css"/>
	<meditag:css href="tpl/${COLOR_TPL}/css/tpl_main.css"/>
	<meditag:css href="tpl/${COLOR_TPL}/css/edutrack_base.css"/>
	<meditag:css href="tpl/${COLOR_TPL}/css/tpl_base.css"/>


	<meditag:js src="/app/js/Context.js"/>
	<script type="text/javascript">
		var localeKey = "${LOCALEKEY}"; // javascript용 localeKey 전역 변수 설정
		var MENUCODE = "menu_${MENUCODE}";
	</script>
	<meditag:js src="/js/common.js"/>
	<meditag:js src="/js/common_conf.js"/>
	<meditag:js src="/js/common_util.js"/>
	<meditag:js src="/js/common_function.js"/>

	<!-- jQuery	-->
	<meditag:js src="/js/jquery/jquery-1.11.1.min.js"/>
	<meditag:js src="/tpl/bootstrap/bower_components/jquery/dist/jquery.form.js"/>
	<meditag:js src="/js/jquery/jquery-ui-1.11.0.custom/jquery-ui.min.js"/>
	<meditag:js src="/js/jquery/jquery-custom/jquery.input-1.0.js"/>
	<meditag:js src="/js/jquery/jquery.ui.touch-punch.min.js"/>
	<meditag:js src="/tpl/bootstrap/bower_components/bootstrap/dist/js/bootstrap.min.js"/>
	<meditag:js src="/tpl/bootstrap/bower_components/metisMenu/dist/metisMenu.min.js"/>
	<meditag:js src="/js/edutrack_main.js"/>
	<meditag:js src="/js/jquery/jquery.cycle2.js"/>
	<meditag:js src="/js/jquery/jquery.bxslider.min.js"/>
	<meditag:js src="/js/edutrack_home.js"/>
	<meditag:js src="/js/nuguya/nice.nuguya.oivs.crypto.js"/>
	<meditag:js src="/js/nuguya/nice.nuguya.oivs.msg.js"/>
	<meditag:js src="/js/nuguya/nice.nuguya.oivs.util.js"/>

	<!-- HTML5 Shim	and	Respond.js IE8 support of HTML5	elements and media queries -->
	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	<!--[if	lt IE 9]>
		<script	src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
		<script	src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
	<![endif]-->
	<!-- ${COLOR_TPL} -->

<c:if test="${not empty daumeditor}">
	<meditag:css href="libs/daumeditor/css/editor.css"/>
	<meditag:js src="/libs/daumeditor/js/editor_loader.js"/>
	<meditag:js src="/libs/daumeditor/js/editor.js"/>
	<script src="http://google-code-prettify.googlecode.com/svn/trunk/src/prettify.js"></script>
	<meditag:js src="/js/common_daumeditor.js"/>
</c:if><c:if test="${not empty summernote}">
	<!-- include libraries(jQuery, bootstrap, fontawesome) -->
	<link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.min.css" rel="stylesheet">
	<!-- include summernote css/js-->
	<meditag:css href="libs/summernote/summernote.css"/>
	<meditag:css href="css/summernote_custom.css"/>
	<meditag:js src="/libs/summernote/summernote.js"/>
	<meditag:js src="/libs/summernote/lang/summernote-ko-KR.js"/>
	<meditag:js src="/libs/summernote/lang/summernote-ja-JP.js"/>
	<meditag:js src="/js/common_summernote.js"/>
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
	<style type="text/css">
	.panel-title {font-size:18px; font-weight:100;}
	.player .player-title {position:absolute; top:15px; left:30px; font-size:18px; font-weight:100; color:#fff; z-index:1;}
	.navbar-default .navbar-nav > li > a {font-size:18px;font-weight: 100;color:#636363;}
	</style>

</head>
<body>
	<div id="wrap">
	    <c:import url="/WEB-INF/jsp/tpl/home/${COLOR_TPL}/inc/header.jsp" charEncoding="UTF-8"/>
	    <sitemesh:write property="body"/>
	</div>


	<!-- footer -->
	<jsp:include page="/WEB-INF/jsp/tpl/home/${COLOR_TPL}/inc/footer.jsp" />
	<!-- //footer -->


<script type="text/javascript">
	$(function() {


		calendarDiv();

		//메인이미지 슬라이드
		var $slideshow = $(".cycle-slideshow");
		var $figures = $slideshow.find(".slide");
		var length = $figures.length;
		<c:forEach var="mainImg" items="${orgImgList}" varStatus="status">
		$('#adv-custom-pager a').eq(${status.count -1});
		</c:forEach>
		$(".cycle-pager span").wrapInner("<strong></strong>");

		//메인슬라이드 버튼
		$('.pagerWrap .btnStop').click(function() {
			$(this).addClass('blind');
			$('.pagerWrap .btnPlay').removeClass('blind');
		});
		$('.pagerWrap .btnPlay').click(function() {
			$(this).addClass('blind');
			$('.pagerWrap .btnStop').removeClass('blind');
		});

		// initiates responsive slide gallery
		var settings = function() {
			var settings1 = {
				pager		: false,
				auto 		:true,
				maxSlides	: 5,
				moveSlides	: 1,
				slideWidth	: 200,
				slideMargin	: 10
			};
			var settings2 = {
				pager		: false,
				auto 		:true,
				maxSlides	: 3,
				moveSlides	: 1,
				slideWidth	: 200,
				slideMargin	: 10
			};
			return ($(window).width()>1200 || navigator.userAgent.indexOf("MSIE 8") > -1) ? settings1 : settings2;
		}

		var mySlider;

		function tourLandingScript() {
			mySlider.reloadSlider(settings());
		}

		mySlider = $('.footInf #banner ul').bxSlider(settings());
		$(window).resize(tourLandingScript);

		//컨텐츠 상단 이동
		var offset = 300,
		offset_opacity = 1200,
		scroll_top_duration = 300,
		$back_to_top = $('#toTop');

		$back_to_top.on('click', function(event){
		event.preventDefault();
			$('body,html').animate({scrollTop: 0 ,}, scroll_top_duration);
		});

		//REPONSIVE 레이아웃 위치 이동
		$(window).resize(function() {
		var ww = $(window).width();
		if (ww > 1200){
			$("#mv_shift").insertAfter("#cd_shift");
			}
		else {
			$("#mv_shift").insertBefore("#cd_shift");
			}
		});
		$(window).load(function() {
		var ww = $(window).width();
		if (ww > 1200){
			$("#mv_shift").insertAfter("#cd_shift");
			}
		else {
			$("#mv_shift").insertBefore("#cd_shift");
			}
		});

		popupNotice();

	});


	function calendarDiv(yearMonth) {
		if(yearMonth == undefined) yearMonth = "";
		$("#cd_shift").load(cUrl("/home/course/indexCalendarDiv"), {"yearMonth":yearMonth});
	}

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
			var url = generateUrl("/home/brd/popup/indexRead",{"popupNoticeDTO.popupSn":"${item.popupSn}"});
	<c:if test="${item.popupTypeCd eq 'PBOX'}">
			noticePopBox${item.popupSn} = new $M.ModalDialog({
				"modalid" : "modal${item.popupSn}",
				"nomargin" : true
			});
			var addContent  = "<iframe id='popupFrame' name='popupFrame' width='100%' height='100%' "
				+ "frameborder='0' scrolling='auto' src='"+url+"'/>";
			noticePopBox${item.popupSn}.clear();
			noticePopBox${item.popupSn}.addContents(addContent);
			noticePopBox${item.popupSn}.resize("${item.xSize}","${item.ySize+20}");
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

	function topCourseSearch() {
		var searchValue = $("#courseSearchValue").val();
		document.location.href = cUrl("/home/course/listSearchCourseFullMain")+"?mcd=${searchFullMcd}${AMPERSAND}searchValue="+searchValue;
	}
</script>

</body>


</html>