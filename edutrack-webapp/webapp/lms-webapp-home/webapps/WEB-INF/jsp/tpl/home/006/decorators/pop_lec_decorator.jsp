<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<%@ taglib prefix="meditag" uri="tag-utils" %>

<!DOCTYPE html>
<c:set var="titleName" value="${titleName}" scope="request" />
<html lang="ko">
	<head>
	    <meta charset="utf-8">
	    <title>${system_name}</title>
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
	    <link rel="stylesheet" href="/tpl/${COLOR_TPL}/js/codemirror/lib/codemirror.css">
		<link rel="stylesheet" href="/tpl/${COLOR_TPL}/js/codemirror/theme/darcula.css">
	
	    <script src="/tpl/${COLOR_TPL}/jquery/jquery-3.2.1.min.js"></script>
	    <script defer src="/tpl/${COLOR_TPL}/js/func.min.js"></script>
	    <script defer src="/tpl/${COLOR_TPL}/js/class.js"></script>
		<script src="/js/jquery/jquery-1.11.1.js"></script>
		<script src="/js/jquery/jquery-1.11.1.min.js"></script>
	    <script src="/js/jquery/jquery-custom/jquery.input-1.0.js"></script>
    	<script src="/app/js/Context.js"></script>
	    <script defer src="/js/common.js"></script>
	    <script defer src="/tpl/${COLOR_TPL}/js/common_function.js"></script>
		<meditag:js src="/js/jquery/jquery.form.js"/>
		<script src="/tpl/${COLOR_TPL}/js/iframeResizer.min.js"></script>
	    
	    <script src="/tpl/${COLOR_TPL}/js/common_conf.js"></script>
		<script src="/tpl/${COLOR_TPL}/js/common_util.js"></script>
		<meditag:js src="/js/modaldialog1.js"/>
		
		<script src="/tpl/${COLOR_TPL}/js/codemirror/lib/codemirror.js"></script>
		<script src="/tpl/${COLOR_TPL}/js/codemirror/mode/sql/sql.js"></script>
		<script src="/tpl/${COLOR_TPL}/js/jquery-resizable.js"></script>
		<script src="/tpl/${COLOR_TPL}/js/pdfobject.js"></script>
		
		<script src="https://cdnjs.cloudflare.com/ajax/libs/bodymovin/5.6.6/lottie.min.js"></script><!-- Animation 추가 -->
		<script src="/tpl/${COLOR_TPL}/js/iframeResizer.contentWindow.min.js"></script>

<c:if test="${not empty popup}">
	<meditag:js src="/js/popupbox.js"/>
</c:if><c:if test="${not empty daumeditor}">
	<meditag:css href="libs/daumeditor/css/editor.css"/>
	<meditag:js src="/libs/daumeditor/js/editor_loader.js"/>
	<meditag:js src="/libs/daumeditor/js/editor.js"/>
	<script src="http://google-code-prettify.googlecode.com/svn/trunk/src/prettify.js"></script>
	<meditag:js src="/js/common_daumeditor.js"/>
</c:if><c:if test="${not empty summernote}">
 	  <!--  <link href="/libs/bootstrap-3.3.5/css/bootstrap.css" rel="stylesheet">
       <script src="/libs/bootstrap-3.3.5/js/bootstrap.js"></script>
	
		<link rel="stylesheet" href="/tpl/002/css/summernote.min.css" />	
		include summernote css/js
		<link rel="stylesheet" href="/libs/summernote/summernote.css" type="text/css" />
		<link rel="stylesheet" href="/css/summernote_custom.css" type="text/css" />
		<script type="text/JavaScript" src="/libs/summernote/summernote.js"></script>
		<script type="text/JavaScript" src="/libs/summernote/lang/summernote-ko-KR.js"></script>
		<script type="text/JavaScript" src="/libs/summernote/lang/summernote-ja-JP.js"></script> -->
		<script type="text/JavaScript" src="/js/common_summernote.js"></script>
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
</c:if><c:if test="${not empty jstree}">
	<script type="text/JavaScript" src="/js/jquery/jquery-1.11.1.min.js"></script>
	<meditag:js src="/js/jquery/jquery.form.js"/>
	<meditag:js src="/js/jquery/jquery.cookie.js"/>
	<meditag:js src="/js/jquery/jquery.hotkeys.js"/>
	<meditag:js src="/js/jquery/jquery.jstree.js"/>
</c:if><c:if test="${playerDiv eq 'examPaper' }">
<style type="text/css">
        #layer_fixed
        {
            height:75px;
            width:100%;
            position:fixed;
            z-index:999;
            top:0px;
            left:0px;
            -webkit-box-shadow: 0 1px 2px 0 #777;
            box-shadow: 0 1px 2px 0 #777;
            background-color:#fff;
        }
    </style>
</c:if><c:if test="${playerDiv eq 'common' }">	
	<c:choose>
		<c:when test="${fileExt eq 'mp4' }">

		<link rel="stylesheet" href="/libs/flowplayer7/skin/skin.css">
		<style type="text/css">
			/* custom player skin */
				/* custom player skin */
				.flowplayer { width: 100%; background-color: #222; background-size: cover; max-width: 1000px; }
				.flowplayer .fp-controls {background-color: rgba(0, 0, 0, 0.4)}/* 소리 조절 보여주기*/
				<c:if test="${bookmarkDTO.prgrRatio < 100}">
		   		/* 학습 상태가 complete 가 아니면 seek를 하지 못하도록 함 */
				.flowplayer .fp-buffer, .flowplayer .fp-progress {  cursor: default; }/* Disable seeking */
				</c:if>
				#lecQnaForm {padding-bottom: 30px;}
				.vod_header .top_inner .title {padding-left: 308px;}
				.vod_header {margin-top: -24px;}
		</style>
		<!-- 2. flowplayer -->
		<script src="/libs/flowplayer7/flowplayer.js"></script>
		<script src="/libs/flowplayer7/hls.light.min.js"></script>

		</c:when>
		<c:when test="${fileExt eq 'mp3' }">
	<link href="/libs/jplayer/skin/blue.monday/css/jplayer.blue.monday.min.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="/libs/jplayer/jplayer/jquery.jplayer.min.js"></script>
		</c:when>
	</c:choose>
</c:if>		
<c:if test="${not empty pwdmeter}">
	<meditag:js src="/js/pwdmeter.js" />
</c:if>		
	<script type="text/javascript">
		$.ajaxSetup({
			beforeSend: function(xhr) {
				xhr.setRequestHeader("AJAX",true);
			},
			error : function (xhr, status, err) {
				<c:if test="${AJAXMESSAGE eq 'use'}">
				alert("Ajax Error! \n" +
						+ 'Error URL  : ' + status.url + '\n'
						+ 'Response : ' + xhr.status + " : " + xhr.statusText + '\n'
						+ 'Exception: ' + err);				
				</c:if>
			}
		});
		
		
		CONTEXT_ROOT =  "<c:if test="${not empty CONTEXT_ROOT}">/</c:if>${CONTEXT_ROOT }";
		
		
	</script>
	
	</head>

<body class="scroll_custom">
		<sitemesh:write property="body"/>
</body>


</html>