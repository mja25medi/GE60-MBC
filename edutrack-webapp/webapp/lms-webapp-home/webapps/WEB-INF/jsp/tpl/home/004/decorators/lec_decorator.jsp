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
    <link rel="stylesheet" href="/tpl/${COLOR_TPL}/css/board.css">
    <link rel="stylesheet" href="/tpl/${COLOR_TPL}/css/contents.css">
    <link rel="stylesheet" href="/tpl/${COLOR_TPL}/css/class_layout.css">
    <link rel="stylesheet" href="/tpl/${COLOR_TPL}/css/class_content.css">

    <script src="/tpl/${COLOR_TPL}/jquery/jquery-3.2.1.min.js"></script>
    <script defer src="/tpl/${COLOR_TPL}/js/func.min.js"></script>
    <script defer src="/tpl/${COLOR_TPL}/js/class.js"></script>
	<script src="/js/jquery/jquery-custom/jquery.input-1.0.js"></script>

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
	<script src="/tpl/${COLOR_TPL}/jquery/jquery.form.js"></script>
	
	<script src="/js/jquery/jquery-1.11.1.min.js"></script>
	<script src="/js/jquery/jquery-ui-1.11.0.custom/jquery-ui.min.js"></script>
	<script src="/js/jquery/jquery-custom/jquery.input-1.0.js"></script>
	<script src="/js/jquery/jquery.ui.touch-punch.min.js"></script>
	<script src="/tpl/002/js/jquery/jquery.form.js"></script>

<c:if test="${not empty daumeditor}">
	<meditag:css href="libs/daumeditor/css/editor.css"/>
	<meditag:js src="/libs/daumeditor/js/editor_loader.js"/>
	<meditag:js src="/libs/daumeditor/js/editor.js"/>
	<script src="http://google-code-prettify.googlecode.com/svn/trunk/src/prettify.js"></script>
	<meditag:js src="/js/common_daumeditor.js"/>
</c:if><c:if test="${not empty summernote}">
 	<%--    <link href="/libs/bootstrap-3.3.5/css/bootstrap.css" rel="stylesheet">
       <script src="/libs/bootstrap-3.3.5/js/bootstrap.js"></script>
	<!-- include summernote css/js-->
	<meditag:css href="libs/summernote/summernote.css"/>
	<meditag:css href="css/summernote_custom.css"/>
	<meditag:js src="/libs/summernote/summernote.js"/>
	<meditag:js src="/libs/summernote/lang/summernote-ko-KR.js"/>
	<meditag:js src="/libs/summernote/lang/summernote-ja-JP.js"/>
	 --%> 
	<meditag:js src="/js/common_summernote.js"/>
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
	<meditag:js src="/js/modaldialog1.js"/>

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
<div id="skipNav">
    <a href="#content">본문으로 바로가기</a>
    <a href="#gnb">주메뉴 바로가기</a>
</div>
<body>
    <div id="wrap">
	    	<c:import url="/WEB-INF/jsp/tpl/home/${COLOR_TPL}/inc/lec_header.jsp" charEncoding="UTF-8"/>
        <div class="container">
			<c:import url="/WEB-INF/jsp/tpl/home/${COLOR_TPL}/inc/lec_menu.jsp" charEncoding="UTF-8"/>
            <div id="content">
            		 <div class="segment" id="course"></div>
					<sitemesh:write property="body"/>
            </div>
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


<script type="text/javascript">
	$(document).ready(function(){
		 viewCourseInfo()
	});

    	
	function viewCourseInfo(){
			$('#course').load(cUrl("/lec/main/courseInfo"),{});
	}
	
</script>
	    
</body>
</html>