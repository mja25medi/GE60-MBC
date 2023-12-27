<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="img_base" value="/img/home" />

<c:set var="bookmarkVO" value="${bookmarkVO}"/>
<c:set var="credit" value="credit"/>
<c:set var="cmiMode" value="normal"/>

<c:if test="${CLASSUSERTYPE ne 'STU'}">
	<c:set var="credit" value="no-credit"/>
	<c:set var="cmiMode" value="browse"/>
</c:if>

<head>
	<base target="_self"></base>
	<meta http-equiv="Content-Type"	content="text/html;	charset=UTF-8">
	<meta http-equiv="Expires" content="-1">
	<meta http-equiv="Pragma" content="no-cache">
	<meta http-equiv="Cache-Control" content="No-Cache">
	
	<meta name="viewport" content="width=device-width,initial-scale=1.0"> 

	<title>Contents View Page</title>
	
	<link rel="stylesheet" href="/tpl/002/css/video.css"> 

	<meditag:js src="/js/jquery/jquery-1.11.1.min.js"/>
	<meditag:js	src="/js/jquery/jquery.form.js" />
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

	<script type="text/javascript">
	var currentIdx = 1;
	var subContentsList;
	var subContentsSize;
		$(document).ready(function() {
			//resizeWin();
			// 여기서 ajax로 contents list 한 번 불러서 current_index 초기화
			 
			if("${contentsList.size()}" > 1){
				$.ajax({
					url : '/home/course/subject/listSubContents'
					,data : {
						'sbjCd' : "${contentsVO.sbjCd}"
						,'unitCd' : "${contentsVO.unitCd}"
					}
					,success : function(resultListVO) {
						subContentsList = resultListVO.returnList;
						subContentsSize = resultListVO.returnList.length;
						$("#contentsMain").attr("src",subContentsList[currentIdx].unitFilePath);
						$("#next").attr("class","btn next");
					}
					,error : function(request,status,error) {
						alert("페이지 목록을 불러오는데 실패하였습니다.");
					}
				});
			}
			 
		});

		function resizeWin() {
			window.resizeTo("${subjectVO.winWidth}", "${subjectVO.winHeight}");
		}


		function MM_findObj(n, d) { //v4.01
			var p,i,x;
			if(!d) d=document;
			if((p=n.indexOf("?"))>0&&parent.frames.length) {
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

		function closePop(){
			window.close();
		}

		function moveBeforeChapter(){
			currentIdx--;
			$("#contentsMain").attr("src",subContentsList[currentIdx].unitFilePath);
			if((currentIdx - 1) == 0){
				$("#before").attr("class","btn prev disabled");
				$("#before").attr("href","#");
			}
			if((currentIdx - 1) < subContentsSize){
				$("#next").attr("class","btn next");
				$("#next").attr("href","javascript:moveNextChapter();");
			}
		}

		function moveNextChapter(){
			currentIdx++;
			$("#contentsMain").attr("src",subContentsList[currentIdx].unitFilePath);
				if((currentIdx + 1) == subContentsSize){
					$("#next").attr("class","btn next disabled");
					$("#next").attr("href","#");
				}
				if((currentIdx + 1) > 1){
					$("#before").attr("class","btn prev");
					$("#before").attr("href","javascript:moveBeforeChapter();");
				}
		}
		
		function onunloadFunction() {
			window.close();
		}
	</script>
</head>
<body>
	<div id="wrap">
	     <div class="container">
	     	<figure>
				<c:choose>
					<c:when test="${contentsList.size() > 1}">
						<iframe src="" id="contentsMain" name="contentsMain" marginheight="0" marginwidth="0" scrolling="no" width="100%" height="100%" frameborder="0" style="padding:0px;"></iframe>
					</c:when>
					<c:otherwise>
						<iframe src="${bookmarkVO.unitFilePath}" id="contentsMain" name="contentsMain" marginheight="0" marginwidth="0" scrolling="no" width="100%" height="100%" frameborder="0" style="padding:0px;"></iframe>
					</c:otherwise>
				</c:choose>
	     	</figure>
		
            <div class="txt-right mt20 mb40">
                <button onclick="moveBeforeChapter()" id="before" type="button" class="btn type5 "><i class="xi-angle-left sm" aria-hidden="true"></i> 이전</button>
                <button onclick="onunloadFunction()" id="closeBtn" type="button" class="btn type5 ">닫기</button>
                <button onclick="moveNextChapter()" id="next" type="button" class="btn type5 ">다음 <i class="xi-angle-right sm" aria-hidden="true"></i></button>
            </div>    
	     </div>
	</div>
</body>
