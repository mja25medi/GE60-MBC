<!DOCTYPE html>
<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="img_base" value="/img/home" />
<c:set var="bookmarkVO" value="${vo}"/>
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
			overflow:hidden;
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
		window.resizeTo("${subjectVO.winWidth}", "${subjectVO.winHeight}");
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
			api.SetSbjCd("${vo.sbjCd}");
			api.SetUnitCd("${vo.unitCd}");
			api.SetRegNo("${USERNO}");
			api.SetBookmarkType("${vo.prgrChkType}");
			api.SetTotalPageCnt("${vo.totalPage}");
			api.SetShowTime("${vo.critTm}");
			api.SetCredit("${credit}");
			result = api.Initialize("");
			seekTime = api.GetSeekTime();
			prgrRatio = api.GetValue("prgrRatio");
		}
	}

	function setSeekTime(time) {
		edutrackAPI.SetSeekTime(time);
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
<c:if test="${subjectVO.winMenuUseYn eq 'Y'}">
<table border="0" cellpadding="0" cellspacing="0" style="width:100%; height:100%; background-color:#1E1F33;" >
	<tr height="45px">
		<td colspan="2" background="${img_base}/skin/title_bg.gif"><p style="margin-left:50px"><b><font color=white SIZE=4 face='맑은고딕'>${subjectVO.sbjNm}</font></b></p></td>
	</tr>
	<tr height="620px">
		<td style="width:190px;">

	<table width="190" height="620" border="0" cellpadding="0" cellspacing="0" >
		<tr>
			<td width="28" valign="top" background="${img_base}/skin/hide_bg.gif">
				<table width="28" height="100" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td></td>
					</tr>
				</table>
			</td>
			<td width="190">
				<table width="162" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td height="620" align="left" valign="top" background="${img_base}/skin/left_bg.gif">
							<table width="162" border=0>
	<c:set var="lineCnt" value="0"/>
	<c:forEach items="${contentsList}" var="item">
		<c:set var="lineCnt" value="${lineCnt + 1}"/>
		<c:if test="${lineCnt eq 1}">
								<tr>
									<td height="74" valign="top" background="${img_base}/skin/left_top.gif" style="padding:30 0 0 10;"><img src="${img_base}/skin/title_icon.gif" width="9" height="9">
										<b><font style="font-size=10pt;">${item.unitNm}</b></td>
								</tr>
								<tr>
									<td height=10></td>
								</tr>

		</c:if>
		<c:if test="${lineCnt ne 1}">
			<c:if test="${item.unitLvl eq 2}">


								<tr>
									<td height="20" valign="top" style="padding:2 0 0 15;"><img src="${img_base}/skin/chap_icon.png" width="9" height="9" />
										<c:if test="${item.unitType eq 'L'}"><b><a href="<c:url value="/home/course/viewContents?sbjCd=${item.sbjCd}&amp;unitCd=${item.unitCd}"/>" >${item.unitNm}</a></b></c:if>
										<c:if test="${item.unitType ne 'L'}"><b>${item.unitNm}</b></c:if>
									</td>
								</tr>
								<tr>
									<td height="1" style="background:url(${img_base}/skin/line.png)" ></td>
								</tr>
			</c:if>
			<c:if test="${item.unitLvl > 2}">
								<tr>
									<td valign="top" style="padding:6 0 0 20;">
										<table width="135" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td width="10" height="18" valign="top" style="padding:4 0 0 0;">
													<img src="${img_base}/skin/chap_icon03.gif" width="6" height="6" /></td>
													<td width="130">
														<c:if test="${item.unitType eq 'L'}"><a href="<c:url value="/home/course/viewContents?sbjCd=${item.sbjCd}&amp;unitCd=${item.unitCd}"/>" >${item.unitNm}</a></c:if>
														<c:if test="${item.unitType ne 'L'}">${item.unitNm}</c:if>
													</td>
											</tr>
										</table>
									</td>
								</tr>
			</c:if>
		</c:if>
	</c:forEach>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>




		</td>
		<td valign="top">
			<iframe src="<c:url value="/home/course/viewContentsMain?sbjCd=${vo.sbjCd}&amp;stdNo=${STUDENTNO}&amp;unitCd=${vo.unitCd}"/>" name="contentsMain" marginheight="0" marginwidth="0" scrolling="no" width="100%" height="620" frameborder="0"></iframe>
		</td>
	</tr>
	<tr>
		<td colspan="2" background="${img_base}/skin/bottom_bg.gif">&nbsp;</td>
	</tr>
</table>
</c:if>
<c:if test="${subjectVO.winMenuUseYn ne 'Y'}">
<iframe src="<c:url value="/home/openCourse/viewCnts?sbjCd=${vo.sbjCd}&amp;unitCd=${vo.unitCd}"/>" id="contentsMain" name="contentsMain" marginheight="0" marginwidth="0" scrolling="auto" width="100%" height="100%" frameborder="0" style="padding:0px;"></iframe>
</c:if>
</body>
</html>