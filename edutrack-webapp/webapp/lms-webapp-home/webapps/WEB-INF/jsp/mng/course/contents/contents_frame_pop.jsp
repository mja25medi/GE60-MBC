<!DOCTYPE html>
<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:url var="img_base" value="/img/home" />
<c:set var="bookmarkVO" value="${bookmarkVO}"/>
<c:set var="credit" value="nocredit"/>
<c:set var="cmiMode" value="browse"/>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<base target="_self"></base>
	<meta http-equiv="Content-Type"	content="text/html;	charset=UTF-8">
	<meta http-equiv="Expires" content="-1">
	<meta http-equiv="Pragma" content="no-cache">
	<meta http-equiv="Cache-Control" content="No-Cache">

	<title>Contents View Page</title>

	<meditag:js	src="/bootstrap/bower_components/jquery/dist/jquery.min.js" />
	<meditag:js	src="/bootstrap/bower_components/jquery/dist/jquery.form.js" />
	<meditag:js	src="/js/scorm/bookmark.js" />

	<!--[if	IE 6]>
	<script	type="text/javascript" src="/uniedu/js/DD_belatedPNG_0.0.8a.js"	charset="UTF-8"></script>
	<script	type="text/javascript">DD_belatedPNG.fix('img');</script>
	<![endif]-->
	<!--[if	IE 6]>
	<meditag:css href="css/home/ie6.css" />
	<![endif]-->
	<!--[if	IE 7]>
	<meditag:css href="css/home/ie7.css" />
	<![endif]-->
	<style type="text/css">
	<!--
		html,body {
			height:100%;
			overflowhidden;
		}
		A:link    {color:#666666;text-decoration:none;font-size:9pt;}
		A:visited {color:#666666;text-decoration:nonefont-size:9pt;}
		A:active  {color:#666666;text-decoration:nonefont-size:9pt;}
		A:hover   {color:#003399;text-decoration:underline;font-size:9pt;}
		td {font-family: 돋움; font-size:9pt; color:#333333}
	-->
	</style>

	<script type="text/javascript">

	$(document).ready(function() {
		resizeWin();
		initAPI();
		initEdutrackAPI();
	});

	function resizeWin() {
		//window.resizeTo("${subjectVO.winWidth}", "${subjectVO.winHeight}");
		// 다이얼 로그로 호출시 사용
		var marginY = 0;
		var marginX = 0;
		var width = 0;
		var height = 0;

		if (navigator.userAgent.indexOf("MSIE 6") > 0) {
			marginY = 50;        // IE 6.x
			marginX = 4;
		} else if(navigator.userAgent.indexOf("MSIE 7") > 0) {
			marginY = -70;    // IE 7.x
			marginX = -14;
		} else if(navigator.userAgent.indexOf("Trident/4.0") > 0) {
			marginY = 0;    // IE 8.x
			marginX = 0;
		} else if(navigator.userAgent.indexOf("Trident/5.0") > 0) {
			marginY = 0;    // IE 9.x
			marginX = 0;
		} else if(navigator.userAgent.indexOf("Trident/6.0") > 0) {
			marginY = 0;    // IE 10.x
			marginX = 0;
		} else if(navigator.userAgent.indexOf("Trident/7.0") > 0) {
			marginY = -74;    // IE 11.x
			marginX = -18;
		}else if(navigator.userAgent.indexOf("Firefox") > 0) {
			marginY = 0;   // FF
			marginX = 45;
		} else if(navigator.userAgent.indexOf("Opera") > 0) {
			marginY = 30;     // Opera
			marginX = 0;
		} else if(navigator.userAgent.indexOf("Netscape") > 0) {
			marginY = 0;  // Netscape
			marginX = 0;
		} else if(navigator.userAgent.indexOf("Safari") > 0) {
			marginY = -2;  // Safafi
			marginX = 0;
		} else if(navigator.userAgent.indexOf("Chrome") > 0) {
			marginY = -60;  // Chrome
			marginX = -10;
		}

		width = ${subjectVO.winWidth} + marginX;
		height = ${subjectVO.winHeight}+marginY;

		window.resizeTo(width, height);
	}

	var result = "";
	var seekTime = "0";
	var prgrRatio = "0";
	//var edutrackAPI = window.dialogArguments.edutrackAPI;
	var edutrackAPI = opener.edutrackAPI;
	function initEdutrackAPI() {
		if(edutrackAPI != null) {
			var api = edutrackAPI;
			api.SetStdNo("");
			api.SetSbjCd("${bookmarkVO.sbjCd}");
			api.SetUnitCd("${bookmarkVO.unitCd}");
			api.SetRegNo("${USERNO}");
			api.SetBookmarkType("${bookmarkVO.prgrChkType}");
			api.SetTotalPageCnt("${bookmarkVO.totalPage}");
			api.SetShowTime("${bookmarkVO.critTm}");
			api.SetCredit("${credit}");
			result = api.Initialize("");
			seekTime = api.GetSeekTime();
			prgrRatio = api.GetValue("prgrRatio");
		}
	}

	function setSeekTime(time) {
		//edutrackAPI.SetSeekTime(time);
	}

	function getSeekTime() {
		return seekTime;
	}

	function getPrgrRatio() {
		return prgrRatio;
	}

	function initAPI() {}

	function evtOnUnload() {
		result = edutrackAPI.Terminate("");
	}

	window.onunload = function(){
		evtOnUnload();
	}

	function MM_findObj(n, d) { //v4.01
	  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
		d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
	  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
	  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
	  if(!x && d.getElementById) x=d.getElementById(n); return x;
	}

	function MM_preloadImages() { //v3.0
	  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
		var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
		if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
	}

	function MM_swapImgRestore() { //v3.0
	  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
	}

	function MM_swapImage() { //v3.0
	  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
	   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
	}

	function resizeIframe(width, height) {
		$("#contentsMain").css("width",width+"px");
		$("#contentsMain").css("height",height+"px");
	}
</script>
</head>
<body style="margin:0px 0px 0px 0px">
<iframe src="<c:url value="/mng/course/contents/viewCnts?bookmarkVO.sbjCd=${bookmarkVO.sbjCd}&amp;bookmarkVO.unitCd=${bookmarkVO.unitCd}"/>" id="contentsMain" name="contentsMain" marginheight="0" marginwidth="0" scrolling="auto" width="100%" height="100%" frameborder="0" style="padding:0px;"></iframe>
</body>
</html>