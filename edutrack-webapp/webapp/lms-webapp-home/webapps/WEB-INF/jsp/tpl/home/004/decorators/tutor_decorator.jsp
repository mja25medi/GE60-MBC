<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<%@ taglib prefix="meditag" uri="tag-utils" %>

<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="utf-8">
    <title>스마트인재개발원</title>
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <meta name="description" content="페이지 설명">
    <link rel="apple-touch-icon-precomposed" sizes="57x57" href="/tpl/003/img/common/apple-touch-icon-57x57.png">
    <link rel="icon" type="image/png" href="/tpl/003/img/common/favicon-16x16.png" sizes="16x16">
    <link rel="stylesheet" href="/tpl/003/css/hrd_common.css">
    <link rel="stylesheet" href="/tpl/003/css/webfonts.css">
    <link rel="stylesheet" href="/tpl/003/css/layout.css">    
    <link rel="stylesheet" href="/tpl/003/css/sub.css">
    <link rel="stylesheet" href="/tpl/003/css/effect_slick.css">
    
    <script src="/app/js/Context.js"></script>
    <script src="/tpl/003/jquery/jquery-3.2.1.min.js"></script>
    <script defer src="/tpl/003/jquery/slick.min.js"></script>
    <script defer src="/tpl/003/js/func.min.js"></script>
    <script defer src="/tpl/003/js/common.js"></script>
    <script defer src="/js/common.js"></script>
	<script src="/tpl/003/js/common_function.js"></script>
	<script src="/tpl/003/js/common_util.js"></script>
	<script src="/tpl/003/js/common_conf.js"></script>
    <script src="/tpl/003/jquery/jquery.form.js"></script>
    <script defer src="/tpl/003/js/main.js"></script>
    <script src="/tpl/003/jquery/jquery.cycle2.js"></script>
    <script defer src="/tpl/003/js/sub.js"></script>
    <script src="/tpl/003/js/iframeResizer.min.js"></script>

    <!-- <script defer src="/js/common_util.js"></script> -->
    <script src="/js/nuguya/nice.nuguya.oivs.crypto.js"></script>
	<script src="/js/nuguya/nice.nuguya.oivs.msg.js"></script>
	<script src="/js/nuguya/nice.nuguya.oivs.util.js"></script>


    <c:if test="${not empty summernote}">
	    <!-- jQuery	-->
		<meditag:js src="/js/jquery/jquery-1.11.1.min.js"/>
		<meditag:js src="/js/jquery/jquery-ui-1.11.0.custom/jquery-ui.min.js"/>
		<meditag:js src="/js/jquery/jquery-custom/jquery.input-1.0.js"/>
		<meditag:js src="/js/jquery/jquery.ui.touch-punch.min.js"/>
		
		
	
		<link rel="stylesheet" href="/tpl/002/css/summernote.min.css" />	
		<!-- include libraries(jQuery, bootstrap, fontawesome) -->
		<link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.min.css" rel="stylesheet">
		<!-- include summernote css/js-->
		<meditag:css href="libs/summernote/summernote.css"/>
		<meditag:css href="css/summernote_custom.css"/>
		<meditag:js src="/libs/summernote/summernote.js"/>
		<meditag:js src="/libs/summernote/lang/summernote-ko-KR.js"/>
		<meditag:js src="/libs/summernote/lang/summernote-ja-JP.js"/>
		<meditag:js src="/js/common_summernote.js"/>
		<!-- Bootstrap 3.3.6 -->
		<script src="${CONTEXT_ROOT }/libs/admin-lte/bootstrap/js/bootstrap.min.js"></script>
		<!-- FastClick -->
		<script src="${CONTEXT_ROOT }/libs/admin-lte/plugins/fastclick/fastclick.js"></script>
		<!-- AdminLTE App -->
		<script src="${CONTEXT_ROOT }/libs/admin-lte/dist/js/app.min.js"></script>
		<!-- SlimScroll 1.3.0 -->
		<script src="${CONTEXT_ROOT }/libs/admin-lte/plugins/slimScroll/jquery.slimscroll.min.js"></script>
		<meditag:js src="/libs/admin-lte/plugins/flot/excanvas.min.js"/>
		<meditag:js src="/libs/admin-lte/plugins/flot/jquery.flot.js"/>
		<meditag:js src="/libs/admin-lte/plugins/flot/jquery.flot.pie.js"/>
		<meditag:js src="/libs/admin-lte/plugins/flot/jquery.flot.resize.js"/>
		<meditag:js src="/libs/admin-lte/plugins/flot/jquery.flot.time.js"/>
		<meditag:js src="/libs/admin-lte/plugins/flot/jquery.flot.tooltip.min.js"/>
	</c:if>
	
    <c:if test="${not empty fileupload}">
		<meditag:js src="/js/jquery/jquery-fileupload/vendor/jquery.ui.widget.js"/>
		<meditag:js src="/js/jquery/jquery-fileupload/jquery.iframe-transport.js"/>
		<meditag:js src="/js/jquery/jquery-fileupload/jquery.fileupload.js"/>
		<meditag:js src="/js/common_fileupload.js"/>
	</c:if>
	<meditag:js src="/tpl/003/js/modaldialog2.js"/>
	<meditag:js src="/js/jquery/jquery.form.js"/>
	

	<sitemesh:write property="head"/>
</head>


<body>
    
    <div id="wrap" class="prof">
       <c:import url="/WEB-INF/jsp/tpl/home/${COLOR_TPL}/inc/tutor_header.jsp" charEncoding="UTF-8"/>


        <main class="main">
            <div id="contentWrap" class="container">
                 <!-- content -->
                <div id="content" class="content">
                    <sitemesh:write property="body"/>
                </div>
                <!-- //content -->
            </div>
            <!-- //contentWrap -->
              

        </main>

        <c:import url="/WEB-INF/jsp/tpl/home/${COLOR_TPL}/inc/footer.jsp" charEncoding="UTF-8"/>
       
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

</body>

</html>



