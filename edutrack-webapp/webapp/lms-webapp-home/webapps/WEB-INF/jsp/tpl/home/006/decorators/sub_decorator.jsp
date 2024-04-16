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
	    <link rel="stylesheet" href="/tpl/${COLOR_TPL}/css/layout2.css">    
	    <link rel="stylesheet" href="/tpl/${COLOR_TPL}/css/contents.css" />
	    <link rel="stylesheet" href="/tpl/${COLOR_TPL}/css/effect_slick.css">
	    <link rel="stylesheet" href="/tpl/${COLOR_TPL}/css/sub.css"><!-- sub 페이지에서 사용 -->
	
    	<script src="/tpl/${COLOR_TPL}/jquery/jquery-3.2.1.min.js"></script>
		<script async src="/tpl/${COLOR_TPL}/jquery/slick.min.js"></script>
		<script defer src="/tpl/${COLOR_TPL}/js/func.min2.js"></script>
		<script defer src="/tpl/${COLOR_TPL}/js/common.js"></script>
		<script defer src="/tpl/${COLOR_TPL}/js/main.js"></script>
		<script src="/tpl/${COLOR_TPL}/jquery/jquery.cycle2.js"></script>
		<script defer src="/tpl/${COLOR_TPL}/js/sub.js"></script>
		<script src="/tpl/${COLOR_TPL}/js/iframeResizer.min.js"></script>
	
		<script src="/js/nuguya/nice.nuguya.oivs.crypto.js"></script>
		<script src="/js/nuguya/nice.nuguya.oivs.msg.js"></script>
		<script src="/js/nuguya/nice.nuguya.oivs.util.js"></script>
			
		<meditag:css href="css/jquery-ui-1.11.0.custom/jquery-ui.css"/>
		<script src="/tpl/002/js/jquery/jquery.min.js"></script>
		<script src="/tpl/002/js/jquery/jquery-ui.min.js"></script>
	
		<script src="/app/js/Context.js"></script>
		<script src="/tpl/${COLOR_TPL}/js/common_conf.js"></script>
		<script src="/tpl/${COLOR_TPL}/js/common_util.js"></script>
		<script src="/tpl/${COLOR_TPL}/js/common_function.js"></script>
	
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
	
    <c:if test="${not empty fileupload}">
		<meditag:js src="/js/jquery/jquery-fileupload/vendor/jquery.ui.widget.js"/>
		<meditag:js src="/js/jquery/jquery-fileupload/jquery.iframe-transport.js"/>
		<meditag:js src="/js/jquery/jquery-fileupload/jquery.fileupload.js"/>
		<meditag:js src="/js/common_fileupload.js"/>
	</c:if>
	<meditag:js src="/tpl/${COLOR_TPL}/js/modaldialog2.js"/>
	<meditag:js src="/js/jquery/jquery.form.js"/>
   
	    
		<sitemesh:write property="head"/>
	
	</head>
	<div id="skipNav">
	    <a href="#content">본문으로 바로가기</a>
	    <a href="#gnb">주메뉴 바로가기</a>
	</div>

<body>
    
    <div id="wrap">
		<c:import url="/WEB-INF/jsp/tpl/home/${COLOR_TPL}/inc/header.jsp" charEncoding="UTF-8"/>

        <main class="main">
            <div id="contentWrap" class="container">

                <!-- content -->
                <div id="content" class="content">
	                <!-- snb -->
	            	<c:import url="/WEB-INF/jsp/tpl/home/${COLOR_TPL}/inc/home_location.jsp" charEncoding="UTF-8"/>
	                 <!-- //snb -->
					<c:import url="/WEB-INF/jsp/tpl/home/${COLOR_TPL}/inc/menu.jsp" charEncoding="UTF-8"/>                    <!-- //h1_area --> 
                    <!-- //2depth--> 
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
		$(document).ready(function() {
			alert(`${flashMsg}`);
		});
	</script>
	</c:if>
</body>

</html>