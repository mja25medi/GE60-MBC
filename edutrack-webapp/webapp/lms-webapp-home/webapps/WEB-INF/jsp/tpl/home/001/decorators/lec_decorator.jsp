<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<%@ taglib prefix="meditag" uri="tag-utils" %>

<!DOCTYPE html>
<html lang="ko">

<c:set var="titleName" value="${titleName}" scope="request" />
<head>
	<meta charset="UTF-8" />
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
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
	<meditag:css href="tpl/001/css/tpl_home.css"/>
	<meditag:css href="tpl/001/css/tpl_base.css"/>

	<!-- HTML5 Shim	and	Respond.js IE8 support of HTML5	elements and media queries -->
	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	<!--[if	lt IE 9]>
		<script	src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
		<script	src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
	<![endif]-->
<c:if test="${not empty textareaResize}">
<style type="text/css">
textarea {resize:${textareaResize};}
</style>
</c:if>
	<meditag:js src="/app/js/Context.js"/>
	<script type="text/javascript">
		var localeKey = "${LOCALEKEY}"; // javascript용 localeKey 전역 변수 설정
		var MENUCODE = "menu_${MENUCODE}";
		var API_1484_11 = null;
		var edutrackAPI = null;
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
</c:if><c:if test="${not empty pageInfo}">
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
	<meditag:js src="/js/edutrack_lec.js"/>

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
	<c:if test="${not empty d3chart}">
	<![if (!IE) | (gte IE 9)]>
	<script type="text/JavaScript" src="/libs/d3chart/d3.js"></script>
	<script type="text/JavaScript" src="/libs/d3chart/liquidFillGauge_custom.js"></script>
	<![endif]>
	</c:if>


</head>
<c:set var="headerBg" value="/tpl/001/img/common/bg_lecture_none.jpg"/>
<c:set var="headerBgColor" value="#fff;"/>
<c:if test="${LECIMGSN > 0 }"><c:set var="headerBg" value="/app/file/view2/${LECIMGSN}"/></c:if>
<c:if test="${LECCOLORSTR ne '' && LECCOLORSTR ne null }"><c:set var="headerBgColor" value="#${LECCOLORSTR }"/></c:if>
<body >
<c:set var="titleFont" value=""/>
	<div id="wrap">
	    <c:import url="/WEB-INF/jsp/tpl/home/001/inc/lec_header.jsp" charEncoding="UTF-8"/>
		<div class="container repair">
			<div class="col-md-3 lnb">
			<c:import url="/WEB-INF/jsp/tpl/home/001/inc/lec_menu.jsp" charEncoding="UTF-8"/>
			</div>
			<div class="col-md-9 subCon">
				<div class="sv m01 text-right" style="background:url(${headerBg});padding:60px 10px 10px 10px;color:${headerBgColor}">
<c:choose>
	<c:when test="${ (fn:length(CRSCRENM) > 39)  && (fn:length(CRSCRENM) <= 50)}"><c:set var="titleFont" value="font-size:15px;"/></c:when>
	<c:when test="${ (fn:length(CRSCRENM) > 32)  && (fn:length(CRSCRENM) <= 39)}"><c:set var="titleFont" value="font-size:20px;"/></c:when>
	<c:when test="${ (fn:length(CRSCRENM) > 26)  && (fn:length(CRSCRENM) <= 32)}"><c:set var="titleFont" value="font-size:25px;"/></c:when>
	<c:otherwise><c:set var="titleFont" value=""/></c:otherwise>
</c:choose>
					<h2 style="color:${headerBgColor} ${titleFont}">${CRSCRENM}</h2>
					${CREYEAR}<spring:message code="common.title.year"/>/${CRETERM}
				</div>
				<div id="content">
					<c:import url="/WEB-INF/jsp/tpl/home/001/inc/lec_location.jsp" charEncoding="UTF-8"/>
					<sitemesh:write property="body"/>
				</div>
			</div>
	    </div>
    </div>
    <c:import url="/WEB-INF/jsp/tpl/home/001/inc/footer.jsp" charEncoding="UTF-8"/>
	<%-- 세션이나 리퀘스트에 ALERT_MESSAGE 가 있으면 알려주고 내용을 삭제한다. --%>
	<c:if test="${not empty ALERT_MESSAGE}">
	<div id="sessionflashmsg" style="display:none;">${ALERT_MESSAGE}</div>
	<c:set var="flashMsg" value="${ALERT_MESSAGE}"/><c:remove var="ALERT_MESSAGE" scope="session"/>
	</c:if>
	<script type="text/javascript">
		//$.growlUI('알림', '${flashMsg}');
		$(document).ready(function() {
			if($("#sessionflashmsg").length > 0) {
				alert('${flashMsg}');
			}
			if($("#menu_btn").length > 0) {
				//모바일 서브메뉴 toggle (160321)추가
				$('#menu_btn').click(function(){
					$('.nav-stacked').toggle();
					$(this).hasClass('active');
					$(this).toggleClass('active');
				});
				$(window).resize(function() {
					var ww = $(window).width();
					if (ww >= 768){
						$('.nav-stacked').show();
					}
					else {
						$('.nav-stacked').hide();
					}
				});
			}
		});
	</script>
</body>

</html>