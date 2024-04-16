<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<%@ taglib prefix="meditag" uri="tag-utils" %>

<!DOCTYPE html>
<html lang="ko">
<head>
 <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <meta name="description" content="페이지 설명">
    <link rel="apple-touch-icon-precomposed" sizes="57x57" href="../img/common/apple-touch-icon-57x57.png">
    <link rel="icon" type="image/png" href="../img/common/favicon-16x16.png" sizes="16x16">
    <link rel="stylesheet" href="/tpl/${COLOR_TPL}/css/hrd_common.css">
    <link rel="stylesheet" href="/tpl/${COLOR_TPL}/css/webfonts.css">
    <link rel="stylesheet" href="/tpl/${COLOR_TPL}/css/layout.css">    
    <link rel="stylesheet" href="/tpl/${COLOR_TPL}/css/effect_slick.css">
    
    <script src="/tpl/${COLOR_TPL}/jquery/jquery-3.2.1.min.js"></script>
    <script defer src="/tpl/${COLOR_TPL}/jquery/slick.min.js"></script>
    <script defer src="/tpl/${COLOR_TPL}/js/func.min.js"></script>
    <script defer src="/tpl/${COLOR_TPL}/js/common.js"></script>
    <!-- <script defer src="/tpl/${COLOR_TPL}/js/main.js"></script> -->
    <script src="/tpl/${COLOR_TPL}/jquery/jquery.cycle2.js"></script>
    <script defer src="/tpl/${COLOR_TPL}/js/sub.js"></script>
    <script src="/tpl/${COLOR_TPL}/js/iframeResizer.min.js"></script>
    <script src="/tpl/${COLOR_TPL}/js/iframeResizer.contentWindow.min.js"></script>
<link rel="stylesheet" href="/tpl/${COLOR_TPL}/css/sub.css"><!-- sub 페이지에서 사용 -->
	<%-- 이 데코레이터는 메인페이지에서 모달팝업을 부르는 경우이며, iframe이 아니고 .load로 호출하기 때문에 css나 js를 부를 필요가 없다. 필요한 경우 해당 페이지에서 head 태그에 추가 --%>
</head>
<body class="scroll_custom" id="main_popup">
    <div class="modal_cont">
	<sitemesh:write property="body"/>
	</div>
</body>
</html>