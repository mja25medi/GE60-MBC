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

<c:if test="${bookmarkVO.todayStudyYn ne 'Y' }">
<script type="text/javascript">
	alert("<spring:message code="lecture.message.contents.alert.onedaystudy"/>");
	self.close();
</script>
</c:if>

<c:if test="${empty bookmarkVO.unitFilePath}">
<script type="text/javascript" defer="defer">
	alert("콘텐츠 준비중입니다.");
	self.close();
</script>
</c:if>

<c:if test="${bookmarkVO.todayStudyYn eq 'Y' }">
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
	var endStudyBtnCheck = true;
	var isMobile = /Mobi/i.test(window.navigator.userAgent);

	$(window).bind("beforeunload", function (e){
		if(endStudyBtnCheck){
			endStudy();
		}
	});
	
	function endStudy() {
		endStudyBtnCheck = false;
		edutrackAPI.SetConnPageCnt(parseInt(currentIdx));
		edutrackAPI.Commit("");
		opener.listContents();
		setTimeout(function() {
			top.window.close();
		}, 500);
	}
	
	var currentIdx = 1;
	var subContentsList;
	var subContentsSize;
	var viewTime = 0;
		$(document).ready(function() {
			
			if(isMobile == false){
				$("#zoomArea").hide();
			}else{
				$("#zoomArea").show();
			}
			//resizeWin();
			// 여기서 ajax로 contents list 한 번 불러서 current_index 초기화
			 
			if("${contentsList.size()}" > 1){
				var interval = setInterval(function() {
					$.ajax({
						url : '/lec/bookmark/addRedisCrs'
						,data : {
							'userId' : "${USERID}"
							,'crsCreCd' : "${CRSCRECD}"
							,'classUserType' : "${CLASSUSERTYPE}"
						}
						,success : function(resultListVO) {
							var result = resultListVO.returnVO.gubun;
							if(result == 'N'){
								window.opener.alert("이미 수강중인 강의가 있어서 이전 수강창은 종료합니다.");
								top.window.close();
							}
						}
						,error : function(request,status,error) {
						}
					});
				}, 15000);
				
				$.ajax({
					url : '/lec/bookmark/listSubContents'
					,data : {
						'stdNo' : "${STUDENTNO}"
						,'sbjCd' : "${bookmarkVO.sbjCd}"
						,'unitCd' : "${bookmarkVO.unitCd}"
					}
					,success : function(resultListVO) {
						subContentsList = resultListVO.returnList;
						subContentsSize = resultListVO.returnList.length;
						$("#next").attr("class","btn next");
						
						if(subContentsList[parseInt(currentIdx)].critTm > 0 && subContentsList[parseInt(currentIdx)].studyYn == 'N'){
							
							$("#next").attr("class","btn next disabled");
							$("#next").attr("href","#");
							var interval = setInterval(function() {
									//if(($("#contentsMain").contents().find("#player1")[0].getCurrentTime()/60) > subContentsList[currentIdx].critTm){
									viewTime++;	
									if((viewTime/60) > subContentsList[parseInt(currentIdx)].critTm){
										$("#next").attr("class","btn next");
										$("#next").attr("href","javascript:moveNextChapter();");
										clearInterval(interval);
										subContentsList[parseInt(currentIdx)].studyYn = 'Y';
									}
								}, 1000);
						}
							
						$.ajax({
							url : '/lec/bookmark/addBookmarkDetail'
							,data : {
								'stdNo' : "${STUDENTNO}"
								,'sbjCd' : "${bookmarkVO.sbjCd}"
								,'unitCd' : subContentsList[parseInt(currentIdx)].unitCd
								,'parUnitCd' : subContentsList[parseInt(currentIdx)].parUnitCd
							}
							,success : function(resultListVO) {
							}
							,error : function(request,status,error) {
							}
						});
						
					}
					,error : function(request,status,error) {
						alert("페이지 목록을 불러오는데 실패하였습니다.");
					}
				});
			}
			 
		});

		var result = "";
		var seekTime = "0";
		var prgrRatio = "0";
		//var edutrackAPI = window.dialogArguments.edutrackAPI;
		//var edutrackAPI = opener.edutrackAPI;
		//var edutrackAPI = edutrackAPIFrame.edutrackAPI;
		//var edutrackAPI = $("#edutrackAPIFrame").contentWindow.document.edutrackAPI;
		var edutrackAPI = null;

		function initEdutrackAPI() {
			if(edutrackAPI != null) {
				var api = edutrackAPI;
				api.SetStdNo("${STUDENTNO}");
				api.SetSbjCd("${bookmarkVO.sbjCd}");
				api.SetUnitCd("${bookmarkVO.unitCd}");
				api.SetRegNo("${USERNO}");
				api.SetBookmarkType("${bookmarkVO.prgrChkType}");
				api.SetTotalPageCnt("${bookmarkVO.totalPage}");
				api.SetShowTime("${bookmarkVO.critTm}");
				api.SetCredit("${credit}");
				try {
					result = api.Initialize("");
					seekTime = api.GetSeekTime();
					prgrRatio = api.GetValue("prgrRatio");
				} catch (e) {
					alert("<spring:message code="lecture.message.bookmark.alert.isnot.initialized.edutrackadaptor"/> 1");
				}
			} else {
				alert("<spring:message code="lecture.message.bookmark.alert.isnot.called.edutrackadaptor"/> 2");
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
			
		}
		
		function onloadApiFrame() {
			initAPI();
			initEdutrackAPI();
		}
		
		window.onunload = onunloadFunction;
		window.onbeforeunload = onunloadFunction;

		function onunloadFunction() {
			edutrackAPI.SetConnPageCnt(parseInt(currentIdx));
			edutrackAPI.Commit("");
			opener.listContents();
			window.close();
		}

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
			if(subContentsList.length-1 != parseInt(currentIdx)){
				viewTime = 0;
				$.ajax({
					url : '/lec/bookmark/editDetailStudyYn'
					,data : {
						'stdNo' : "${STUDENTNO}"
						,'sbjCd' : "${bookmarkVO.sbjCd}"
						,'unitCd' : subContentsList[parseInt(currentIdx)].unitCd
					}
					,success : function(resultListVO) {
					}
					,error : function(request,status,error) {
					}
				});
				edutrackAPI.SetConnPageCnt(parseInt(currentIdx));
				edutrackAPI.Terminate('');
				
				currentIdx++;
				$("#contentsMain").attr("src",subContentsList[parseInt(currentIdx)].unitFilePath);
				if(subContentsList[parseInt(currentIdx)].critTm > 0 && subContentsList[parseInt(currentIdx)].studyYn == 'N'){
					$("#next").attr("class","btn next disabled");
					$("#next").attr("href","#");
					var interval = setInterval(function() {
							viewTime++;	
							if((viewTime/60) > subContentsList[parseInt(currentIdx)].critTm){
								$("#next").attr("class","btn next");
								$("#next").attr("href","javascript:moveNextChapter();");
								clearInterval(interval);
								subContentsList[parseInt(currentIdx)].studyYn = 'Y';
							}
						}, 1000);
				}else{
					if((parseInt(currentIdx) + 1) == parseInt(subContentsSize)){
						$("#next").attr("class","btn next disabled");
						$("#next").attr("href","#");
					}
					if((parseInt(currentIdx) + 1) > 1){
						$("#before").attr("class","btn prev");
						$("#before").attr("href","javascript:moveBeforeChapter();");
					}
				}
				
				$.ajax({
					url : '/lec/bookmark/addBookmarkDetail'
					,data : {
						'stdNo' : "${STUDENTNO}"
						,'sbjCd' : "${bookmarkVO.sbjCd}"
						,'unitCd' : subContentsList[parseInt(currentIdx)].unitCd
						,'parUnitCd' : subContentsList[parseInt(currentIdx)].parUnitCd
					}
					,success : function(resultListVO) {
					}
					,error : function(request,status,error) {
					}
				});
			}else{
				alert("마지막페이지입니다. 학습종료를 클릭하세요.")
			}
		}
		
		var nowZoom = 100;

		function zoomOut() {   // 화면크기축소
		   nowZoom = nowZoom - 10;
		   zooms();
		}

		function zoomIn() {   // 화면크기확대
		   nowZoom = nowZoom + 10;
		   zooms();
		}

		function zoomReset() {
		   nowZoom = 100;   // 원래 화면크기로 되돌아가기
		   zooms();
		}
		
		function zooms() {
			//document.body.style.zoom = nowZoom + "%";
			window.frames[0].document.body.style.zoom =  nowZoom + "%";
		}
	</script>
</head>
<body>
 <div id="wrap">
        <div class="container">
        	<div class="txt-right mt20 mb40" id="zoomArea">
                <button onclick="zoomIn();" id="before" type="button" class="btn type5 "><i class="xi-plus sm" aria-hidden="true"></i> 확대</button>
                <button onclick="zoomReset();" id="before" type="button" class="btn gray2 ">기본설정</button>
                <button onclick="zoomOut();" id="before" type="button" class="btn type5 "><i class="xi-minus sm" aria-hidden="true"></i> 축소</button>
            </div>
            <figure>
					<c:choose>
						<c:when test="${contentsList.size() > 1}">
							<iframe src="${contentsList[1].unitFilePath}" id="contentsMain" name="contentsMain" marginheight="0" marginwidth="0" scrolling="no" width="100%" height="100%" frameborder="0" style="padding:0px;"></iframe>
						</c:when>
						<c:otherwise>
							<iframe src="${bookmarkVO.unitFilePath}" id="contentsMain" name="contentsMain" marginheight="0" marginwidth="0" scrolling="no" width="100%" height="100%" frameborder="0" style="padding:0px;"></iframe>
						</c:otherwise>
					</c:choose>
			</figure>
                 <!-- 
                <video poster="../img/contents/thumbnail.png" controls>
                   <source src="../img/contents/CK_ta01060000289_1080p.mp4" type="video/mp4">
                    지원하지 않는 브라우저입니다.
                </video>
                 -->
			<iframe id='edutrackAPIFrame' name='edutrackAPIFrame' width='0' height='0' frameborder='0' src="<c:url value="/jsp/bookmark/study_edutrack_adapter.jsp"/>" onload="onloadApiFrame()"></iframe>
			
            <div class="txt-right mt20 mb40">
            	<c:if test="${CLASSUSERTYPE eq 'STU' }">
                	<button onclick="loadForm()" type="button" class="btn gray2 fl">문의하기</button>
				</c:if>
                <button onclick="moveBeforeChapter()" id="before" type="button" class="btn type5 "><i class="xi-angle-left sm" aria-hidden="true"></i> 이전</button>
            	<c:if test="${CLASSUSERTYPE eq 'STU' }">
                	<button onclick="onunloadFunction()" id="closeBtn" type="button" class="btn gray2 ">학습종료</button>
				</c:if>
				<c:if test="${CLASSUSERTYPE ne 'STU' }">               
                	<button onclick="onunloadFunction()" id="closeBtn" type="button" class="btn type5 ">닫기</button>
                </c:if>
                <button onclick="moveNextChapter()" id="next" type="button" class="btn type5 ">다음 <i class="xi-angle-right sm" aria-hidden="true"></i></button>
            </div>
            <div id="qstnWriteFormDiv"></div> 
             
        </div>
    </div>

	<script>
		function loadForm() {
			$("#qstnWriteFormDiv").load("/lec/qna/writeQstnFromCnts");
			$("#qstnWriteFormDiv").show();
		}
	
	
		function unloadForm() {
			$("#qstnWriteFormDiv").empty();
			$("#qstnWriteFormDiv").hide();
		}
	</script>

</body>
</c:if>