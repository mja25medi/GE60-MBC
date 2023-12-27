<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<%@ taglib prefix="meditag" uri="tag-utils" %>

<!DOCTYPE html>
<html lang="ko">

<c:set var="titleName" value="메디오피아테크 HRD센터" scope="request" />
<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<meta name="description" content="Mediopia Tech Edutrack Global" />
	<meta name="author"	content="Mediopia Tech" />
	<title>${titleName}</title>

	<!-- Bootstrap Core	CSS	-->
	<meditag:css href="tpl/bootstrap/bower_components/bootstrap/dist/css/bootstrap.min.css"/>
	<!-- MetisMenu CSS -->
	<meditag:css href="tpl/bootstrap/bower_components/metisMenu/dist/metisMenu.min.css"/>
	<!-- Custom	Fonts -->
	<meditag:css href="tpl/bootstrap/bower_components/font-awesome/css/font-awesome.min.css"/>
	<!-- Jquery Ui CSS -->
	<meditag:css href="css/jquery-ui-1.11.0.custom/jquery-ui.css"/>
	<!-- Edutrack CSS -->
	<meditag:css href="css/edutrack.css"/>
	<!-- Custom	CSS	-->
	<%-- <meditag:css href="bootstrap/dist/css/sb-admin-2.css"/> --%>
	<meditag:css href="tpl/001/css/edutrack_base.css"/>
	<!-- Custom	CSS	-->
	<link rel="stylesheet" href="/tpl/002/css/common.css">
    <link rel="stylesheet" href="/tpl/002/css/webfonts.css">
    <link rel="stylesheet" href="/tpl/002/css/board.css">
    <link rel="stylesheet" href="/tpl/002/css/class_layout.css">
    <link rel="stylesheet" href="/tpl/002/css/like_table.css">

	<script src="/app/js/Context.js"></script>
	<script type="text/javascript">
		var localeKey = "${LOCALEKEY}"; // javascript용 localeKey 전역 변수 설정
		var MENUCODE = "menu_${MENUCODE}";
		var API_1484_11 = null;
		var edutrackAPI = null;
	</script>
	<script src="/js/common.js"></script>
	<script src="/js/common_conf.js"></script>
	<script src="/js/common_util.js"></script>
	<script src="/js/common_function.js"></script>

	<!-- jQuery	-->
	<script type="text/javascript" src="/tpl/002/js/jquery/jquery.min.js"></script>
	<script src="/js/jquery/jquery-1.11.1.min.js"></script>
	<script src="/js/jquery/jquery-ui-1.11.0.custom/jquery-ui.min.js"></script>
	<script src="/js/jquery/jquery-custom/jquery.input-1.0.js"></script>
	<script src="/js/jquery/jquery.ui.touch-punch.min.js"></script>
	<script src="/tpl/002/js/jquery/jquery.form.js"></script>
	<script src="${CONTEXT_ROOT }/libs/admin-lte/bootstrap/js/bootstrap.min.js"></script>

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
</c:if><c:if test="${not empty commonPaging}">
	<meditag:js src="/js/common_paging.js"/>
</c:if><c:if test="${not empty pageInfo or not empty paging}">
	<meditag:css href="css/paginator3000.css"/>
	<meditag:js src="/js/paginator3000.js"/>
</c:if><c:if test="${not empty fileupload}">
	<meditag:js src="/js/jquery/jquery-fileupload/vendor/jquery.ui.widget.js"/>
	<meditag:js src="/js/jquery/jquery-fileupload/jquery.iframe-transport.js"/>
	<meditag:js src="/js/jquery/jquery-fileupload/jquery.fileupload.js"/>
	<meditag:js src="/js/common_fileupload.js"/>
</c:if><c:if test="${not empty uploadify}">
	<meditag:css href="css/uploadify.css"/>
	<meditag:js src="/js/jquery/jquery.uploadify.v2.1.4.min.js"/>
	<meditag:js src="/js/jquery/swfobject.js"/>
</c:if><c:if test="${not empty asterplot}">
	<![if (!IE) | (gte IE 9)]>
	<meditag:js src="/libs/d3chart/aster_plot/d3.v3.min.js"/>
	<meditag:js src="/libs/d3chart/aster_plot/d3.tip.v0.6.3.js"/>
	<%-- <meditag:js src="/libs/d3chart/aster_plot/draw.js"/> --%>
	<meditag:css href="libs/d3chart/aster_plot/aster_plot.css"/>
	<![endif]>
</c:if><c:if test="${not empty fileupload}">
	<meditag:js src="/js/jquery/jquery-fileupload/vendor/jquery.ui.widget.js"/>
	<meditag:js src="/js/jquery/jquery-fileupload/jquery.iframe-transport.js"/>
	<meditag:js src="/js/jquery/jquery-fileupload/jquery.fileupload.js"/>
	<meditag:js src="/js/common_fileupload.js"/>
</c:if>
<c:if test="${not empty paging}">
	<meditag:css href="css/paginator3000.css"/>
	<meditag:js src="/js/paginator3000.js"/>
</c:if>
	<meditag:js src="/js/modaldialog.js"/>

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
</head>

<body>
	<div id="wrap" class="${fn:toLowerCase(CLASSUSERTYPE) } pusher">
		<header>
	    	<c:import url="/WEB-INF/jsp/tpl/home/${COLOR_TPL}/inc/lec_header.jsp" charEncoding="UTF-8"/>
	    </header>
	    
		<div id="class-lnb">
			<c:import url="/WEB-INF/jsp/tpl/home/${COLOR_TPL}/inc/lec_menu.jsp" charEncoding="UTF-8"/>
		</div>
		
		<div class="overlay"></div>
		
		<div id="container">
            <div class="nav">
                <div class="nav1">
                    <button class="class-menu-btn"><i class="xi-bars"></i></button>                    
                </div>
                <div class="nav2">
                    <a href="/lec/main/goMenuPage?mcd=ML01000000"><img src="/tpl/002/img/home_icon.png" alt="홈"></a>
                </div>
                <div class="nav4">${MENUNAME }</div>
            </div>
            <div class="sub_title_2 ohddien">${MENUNAME }</div>

			<div class="content">
				<%-- <c:import url="/WEB-INF/jsp/tpl/home/${COLOR_TPL}/inc/lec_location.jsp" charEncoding="UTF-8"/> --%>
				<sitemesh:write property="body"/>
			</div>
		</div>
		
		<%-- 세션이나 리퀘스트에 ALERT_MESSAGE 가 있으면 알려주고 내용을 삭제한다. --%>
		<c:if test="${not empty ALERT_MESSAGE}">
		<div id="sessionflashmsg" style="display:none;">${ALERT_MESSAGE}</div>
			<c:set var="flashMsg" value="${ALERT_MESSAGE}"/><c:remove var="ALERT_MESSAGE" scope="session"/>
			
			<script type="text/javascript">
				$(document).ready(function() {
					alert(`${flashMsg}`);
				});
			</script>
		</c:if>
		
		<script>
            $(function() {
                /********** NAV 메뉴 **********/
                $('.class-menu-btn').on('click', function() {
                    $('#class-lnb').toggleClass('push-left');
                    $('#container').toggleClass('push-left');
                });

                $('#class-lnb > ul > li').each(function() {
                    if ($(this).find('ul').length == true) {
                        $(this).addClass('sub-menu');
                    };
                });
                $('#class-lnb > ul > li').click(function() {
                    if ($(this).hasClass("open") != true) {
                        $('#class-lnb > ul > li').removeClass("open");
                        $(this).addClass("open");
                    } else {
                        $('#class-lnb > ul > li').removeClass("open");
                    }
                });
                $('.class-menu-btn').click(function() {
                    $('.ui.sticky').sticky('refresh');
                });

                /********** admin menuPush **********/
                var overlay = $('.overlay');

                $('.menu-btn').click(function() {
                    $(this).parents().find('#class-lnb').toggleClass('active');
                    overlay.show();
                });
                overlay.click(function() {
                    $(this).parents().find('#class-lnb').toggleClass('active');
                    overlay.hide();
                });
            });
        </script>
    </div>
</body>

</html>