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
	<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<meta name="description" content="Edutrack NGLMS" />
	<meta name="author"	content="Mediopia Tech" />
	<meta http-equiv="Expires" content="-1" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Cache-Control" content="No-Cache" />
	<title>${system_name}</title>

	<!-- Bootstrap Core	CSS	-->
	<meditag:css href="libs/admin-lte/bootstrap/css/bootstrap.min.css"/>
	<!-- Font Awesome -->
	<meditag:css href="libs/font-awesome/css/font-awesome.min.css"/>
	<!-- Ionicons -->
	<meditag:css href="libs/ionicons/css/ionicons.min.css"/>
	<!-- Theme style -->
	<meditag:css href="libs/admin-lte/dist/css/AdminLTE.min.css"/>
	<meditag:css href="libs/admin-lte/dist/css/skins/_all-skins.min.css"/>

	<!-- Jquery Ui CSS -->
	<meditag:css href="css/jquery-ui-1.11.0.custom/jquery-ui.css"/>
	<!-- Edutrack CSS -->
	<meditag:css href="css/edutrack.css"/>
	
	<meditag:css href="tpl/001/css/edutrack_base.css"/>
	<!-- Custom	CSS	-->
	<link rel="stylesheet" href="/tpl/002/css/common.css">
    <link rel="stylesheet" href="/tpl/002/css/webfonts.css">
    <link rel="stylesheet" href="/tpl/002/css/board.css">
    <link rel="stylesheet" href="/tpl/002/css/class_layout.css">
    <link rel="stylesheet" href="/tpl/002/css/like_table.css">

  	<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  	<!--[if lt IE 9]>
  	<script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  	<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  	<![endif]-->
	
	<meditag:js src="/app/js/Context.js"/>
	<meditag:js src="/js/common.js"/>
	<meditag:js src="/js/common_conf.js"/>
	<meditag:js src="/js/common_util.js"/>
	<meditag:js src="/js/common_function.js"/>

	<!-- jQuery	-->
	<meditag:js src="/js/jquery/jquery-1.11.1.min.js"/>
	<meditag:js src="/js/jquery/jquery.form.js"/>
	<meditag:js src="/js/jquery/jquery-ui-1.11.0.custom/jquery-ui.min.js"/>
	<meditag:js src="/js/jquery/jquery-custom/jquery.input-1.0.js"/>
	<meditag:js src="/js/jquery/jquery.ui.touch-punch.min.js"/>

	<meditag:js src="/js/modaldialog.js"/>
	<!-- Bootstrap 3.3.6 -->
	<meditag:js src="/libs/admin-lte/bootstrap/js/bootstrap.min.js"/>
<c:if test="${not empty popup}">
	<meditag:js src="/js/popupbox.js"/>
</c:if><c:if test="${not empty daumeditor}">
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
</c:if><c:if test="${not empty jstree}">
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
	<!-- 1. skin -->
	<link rel="stylesheet" href="/libs/flowplayer/skin/minimalist.css">
	<style type="text/css">
		/* site specific styling */
		body {
			font: 12px "Myriad Pro", "Lucida Grande", "Helvetica Neue", sans-serif;
			text-align: center;
			padding-top: 1%;
			color: #999;
			background-color: #333333;
		}

		/* custom player skin */
		.flowplayer { width: 100%; background-color: #222; background-size: cover; max-width: 1000px; }
		.flowplayer .fp-controls {background-color: rgba(0, 0, 0, 0.4)}/* 소리 조절 보여주기*/
		<c:if test="${bookmarkDTO.prgrRatio < 100}">
   		/* 학습 상태가 complete 가 아니면 seek를 하지 못하도록 함 */
		.flowplayer .fp-buffer, .flowplayer .fp-progress {  cursor: default; }/* Disable seeking */
		</c:if>
		/*.flowplayer { background-image: url("https://farm4.staticflickr.com/3169/2972817861_73ae53c2e5_b.jpg")}*/
		/*.flowplayer { background-image: url("3.jpg")}*/
	</style>

	<!-- 2. flowplayer -->
	<script src="/libs/flowplayer/flowplayer.min.js"></script>
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
<body class="hold-transition">
		<sitemesh:write property="body"/>
</body>


</html>