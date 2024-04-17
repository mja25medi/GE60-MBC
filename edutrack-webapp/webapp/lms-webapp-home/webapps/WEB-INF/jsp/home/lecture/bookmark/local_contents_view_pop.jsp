<!DOCTYPE html>
<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<c:url var="img_base" value="/img/home" />
<c:set var="bookmarkVO" value="${bookmarkVO}"/>
<c:set var="credit" value="credit"/>
<c:set var="cmiMode" value="normal"/>
<html>
<head>
<title>강의보기</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<link rel="stylesheet" href="/libs/flowplayer7/skin/skin.css">
<meditag:css href="tpl/bootstrap/bower_components/bootstrap/dist/css/bootstrap.min.css"/>
<!-- <link rel="stylesheet" href="/tpl/002/css/video.css"> -->
<link rel="stylesheet" href="/tpl/${COLOR_TPL}/fonts/xeicon.min.css"> 

	<link rel="stylesheet" href="/tpl/${COLOR_TPL}/css/hrd_common.css" />
	<link rel="stylesheet" href="/tpl/${COLOR_TPL}/css/webfonts.css" />
	<link rel="stylesheet" href="/tpl/${COLOR_TPL}/css/board.css" />
	<link rel="stylesheet" href="/tpl/${COLOR_TPL}/css/contents.css" />
	<link rel="stylesheet" href="/tpl/${COLOR_TPL}/css/class_layout.css" />
	<link rel="stylesheet" href="/tpl/${COLOR_TPL}/css/class_content.css" />
	<link rel="stylesheet" href="/tpl/${COLOR_TPL}/css/sidebar.min.css">
<style type="text/css">
	/* custom player skin */
		#lecQnaForm {padding-bottom: 30px;}
		/* .vod_header .top_inner .title {padding-left: 308px;} 
		.vod_header {margin-top: -24px;}*/
</style>

<meditag:js src="/js/jquery/jquery-1.11.1.min.js"/>
<meditag:js src="/js/jquery/jquery.form.js"/>
<meditag:js	src="/app/js/Context.js" />
<meditag:js src="/js/modaldialog.js"/>
<meditag:js src="/tpl/bootstrap/bower_components/bootstrap/dist/js/bootstrap.min.js"/>


    <script src="/tpl/${COLOR_TPL}/jquery/jquery-3.2.1.min.js"></script>
    <script src="/tpl/${COLOR_TPL}/js/func.min.js"></script>
    <script src="/tpl/${COLOR_TPL}/js/common.js"></script>
    <script src="/tpl/${COLOR_TPL}/js/class.js"></script>
    <script src="/tpl/${COLOR_TPL}/js/iframeResizer.min.js"></script>
	<script src="/tpl/${COLOR_TPL}/js/sidebar.min.js"></script>

	<script src="/js/common.js"></script>
	<script src="/js/jquery/jquery.form.js"></script>
	


<iframe  id="edutrackAPIFrame" name="edutrackAPIFrame" style="display:none; width:0; height:0;" frameborder="0" src="<c:url value="/jsp/bookmark/study_edutrack_adapter.jsp"/>" onload="onloadApiFrame()"></iframe>
<script type="text/javascript" defer="defer">

var isPlayed = 0;
var mediaTotalTime = 0;
var sessionPlayTime = 0;
var sessionSeekTime;
var credit = "${credit}";
var isStartPlay	= false; //-- 동영상 재생이 시작되었는지에 대한 체크 , 크롬에서 전체화면/일반화면 전환시 새로 ready를 호출되어 이어보기 메시지가 계속 뜨는 현상 방지

var result;
var seekTime = 0;
var prgrRatio = 0;
 
var halfTime			= 0;
var modalBox = null;

$(document).ready(function() {
	
	listQstn();
	
	/********** wide-inbox sidebar **********/
	$('.inbox-inquiry').sidebar({dimPage: false, closable: false, exclusive: true, context: '.vod_content'})
	.sidebar('attach events', '.inquiry-button', 'toggle')

	$('.inbox-lecture').sidebar({dimPage: false, closable: false, exclusive: true, context: '.vod_content'})
	.sidebar('attach events', '.lecture-button', 'toggle')
	
	$('.inbox-exam').sidebar({dimPage: false, closable: false, exclusive: true, context: '.vod_content'})
	.sidebar('attach events', '.exam-button', 'toggle')
	
	$('.inbox-report').sidebar({dimPage: false, closable: false, exclusive: true, context: '.vod_content'})
	.sidebar('attach events', '.report-button', 'toggle')
	
	var sourcesType = "video/mp4";
	var sourcesSrc = "";

	
	modalBox = new $M.ModalDialog({
		"modalid" : "modal1"
	});
});

$(document).on("click","#qnaSave",function(){
	$('#lecQnaForm').toggle();
	$('#qnaBtns').toggle();
	$('#qnaTitle').focus();
})

	var edutrackAPI = null;
	
	function initEdutrackAPI() {
		if(edutrackAPI != null) {
			var eapi = edutrackAPI;
			eapi.SetStdNo("${STUDENTNO}");
			eapi.SetSbjCd("${bookmarkVO.sbjCd}");
			eapi.SetUnitCd("${bookmarkVO.unitCd}");
			eapi.SetRegNo("${USERNO}");
			eapi.SetBookmarkType("${bookmarkVO.prgrChkType}");
			eapi.SetTotalPageCnt("${bookmarkVO.subCnt}");
			eapi.SetShowTime("${bookmarkVO.critTm}");
			eapi.SetCredit("${credit}");
			//eapi.SetControlUse("true");
			try {
				result = eapi.Initialize("");
				seekTime = eapi.GetSeekTime();
				prgrRatio = eapi.GetValue("prgrRatio");
				
			} catch (e) {
				alert("<spring:message code="lecture.message.bookmark.alert.isnot.initialized.edutrackadaptor"/>");
			}
		} else {
			alert("<spring:message code="lecture.message.bookmark.alert.isnot.called.edutrackadaptor"/>");
		}
	}
	
	
	window.onunload = onunloadFunction;
	window.onbeforeunload = onunloadFunction;
	
	function onunloadFunction() {
		edutrackAPI.SetDetailBookmarkCd(subContentsList[parseInt(currentIdx)].unitCd);
		edutrackAPI.SetDetailStudyTm(viewTime);
		edutrackAPI.SetConnPageCnt(parseInt(currentIdx));
		edutrackAPI.Commit("");
		opener.listContents();
		//window.close();
	}
	
	//-- 동영생 재생 위치를 넘겨 준다.
	function setSeekTime(video_time) {
		if(prgrRatio < 100) {
			prgrRatio = Math.floor( parseInt(video_time) / (parseInt('${bookmarkVO.critTm}') * 60) * 100,0);
			if(prgrRatio > 100) prgrRatio = 100;
		} else {
			prgrRatio = 100;
		}
		sessionSeekTime = video_time;
	}
	
	function checkPlayTime() {
		if(isPlayed == 1) {
			sessionPlayTime++;
		}
		setTimeout(function(){
			checkPlayTime();
		},1000);
		
	/* 			setTimeout(function(){
			edutrackAPI.Commit();
		},10000); */
	}

	
	function completeFunction(compType) {
		edutrackAPI.SetValue("prgrRatio", prgrRatio);
		edutrackAPI.SetSeekTime(sessionSeekTime); //--seek 시간 설정
		result = edutrackAPI.Terminate("");
		
		if(compType != "NC") {
			window.close();
		}
		opener.location.reload();
	}
	

//문의하기
function unloadForm() {
	$("#qnaTitle").val("");
	$("#qnaCts").val("");
	$("#lecQnaForm").hide();
	$("#qnaBtns").hide();
}

/* 서브밋 처리 */
function process(cmd) {
	
	var qnaSn = $("#qnaSn").val();
	
	$('#lecQnaForm').attr("action","/lec/qna/"+cmd);
	if(!validate(document.lecQnaForm)) return false;
	
	$('#lecQnaForm').ajaxSubmit(
		function(data) {
			if(data.result > 0 ) {
				alert(data.message);
				listQstn();
			} else {
				alert(data.message);
			}
		}
	);
}

/** 문의리스트 */
function listQstn() {

 		$.ajax({
			url : '/lec/bookmark/listLecQstn'
			,data : {
				"unitCd":"${param.unitCd}"
			}
			,success : function(result) {
				$('#contentsList').empty();
				$('#contentsList').append(result);
				$('#lecQnaForm').hide();
				$('#qnaBtns').hide();
			}
			,error : function(request,status,error) {
			}
		});
		
}

/* 글 저장 */
function addQstn() {
	if($("#qnaTitle").val() == ""){alert("제목을 입력해 주세요."); return false;}
	if($("#qnaCts").val() == ""){alert("내용을 입력해 주세요."); return false;}
	process("writeQstn");
}

/* 차시 이동 */
function goUnitCd(unitCd,type,prgrRatio) {
	var studyMthd = '${sbjVO.studyMthd}'; 
	
	if(unitCd == ''){
		if(type == 'p'){alert("첫번째 차시입니다.")};
		if(type == 'n'){alert("마지막 차시입니다.")};
		return false;	
	}
	
	var classUserType = '${CLASSUSERTYPE}';
	
	if(type == 'n' && prgrRatio < 100 && studyMthd == 'SE'  && classUserType == 'STU'){
		alert("해당 차시를 완료해야 다음 학습이 가능합니다.");
		return false;
	}
	
	var sbjCd = '${contentsVO.sbjCd}';
	location.href = "/lec/bookmark/viewContents"+"?sbjCd="+sbjCd+"&unitCd="+unitCd+"&deviceType=${param.deviceType}&browserType=${param.browserType}";
}

function goList(url){
	opener.location.href = url;
	self.close();
}

/* ------------------------------------------------------------------- */
var endStudyBtnCheck = true;
var isMobile = /Mobi/i.test(window.navigator.userAgent);
/* 
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
 */
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
		
		if(currentIdx == 1){
			$("#before").attr("class","btn type4 prev disabled");
			$("#before").attr("href","#");
		}
		//resizeWin();
		// 여기서 ajax로 contents list 한 번 불러서 current_index 초기화
		var initUnitFilePath = '{bookmarkVO.unitFilePath}';
		if("${contentsList.size()}" > 1){
			initUnitFilePath = isMobile ? '${contentsList[1].mobileFilePath}' : '${contentsList[1].unitFilePath}';
			$("#contentsMain").attr("src", initUnitFilePath);
			if(isMobile) {
				var sourceChkArr = initUnitFilePath.split('.');
				if(sourceChkArr[sourceChkArr.length -1] == 'mp4') $("#zoomArea").hide();
				else $("#zoomArea").show();
			}
			
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
					$("#next").attr("class","btn type4 next");
					$("#timeChkDiv").hide();
					
					if(subContentsList[parseInt(currentIdx)].critTm > 0 && subContentsList[parseInt(currentIdx)].studyYn == 'N'){
						$("#next").attr("class","btn type4 next disabled");
						$("#next").attr("href","#");
						viewTime = subContentsList[parseInt(currentIdx)].detailStudyTm;
						var currentStudyTm;
						$("#critTm").text(subContentsList[parseInt(currentIdx)].critTm);
						var interval = setInterval(function() {
								//if(($("#contentsMain").contents().find("#player1")[0].getCurrentTime()/60) > subContentsList[currentIdx].critTm){
								viewTime++;
								
								currentStudyTm = viewTime/60;
								$("#currentStudyTm").text(Math.floor(currentStudyTm));
								if((currentStudyTm) > subContentsList[parseInt(currentIdx)].critTm){
									$("#next").attr("class","btn type4 next");
									$("#next").attr("href","javascript:moveNextChapter();");
									clearInterval(interval);
									subContentsList[parseInt(currentIdx)].studyYn = 'Y';
								}
							}, 1000);
						$("#timeChkDiv").show();
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

	function moveBeforeChapter(){
		currentIdx--;
		$("#timeChkDiv").hide();
		
		var source = isMobile ? subContentsList[currentIdx].mobileFilePath : subContentsList[currentIdx].unitFilePath;
		
		if(isMobile){
			var sourceChkArr = source.split('.');
			if(sourceChkArr[sourceChkArr.length -1] == 'mp4') $("#zoomArea").hide();
			else $("#zoomArea").show();
		}

		$("#contentsMain").attr("src", source);
		if(currentIdx == 1){
			$("#before").attr("class","btn type4 prev disabled");
			$("#before").attr("href","#");
		}
		if((currentIdx - 1) < subContentsSize){
			$("#next").attr("class","btn type4 next");
			$("#next").attr("href","javascript:moveNextChapter();");
		}
	}

	function moveNextChapter(){
		if(subContentsList.length-1 != parseInt(currentIdx)){
			var detailStudyTm = viewTime;
			viewTime = 0;
			$.ajax({
				url : '/lec/bookmark/editDetailStudyYn'
				,data : {
					'stdNo' : "${STUDENTNO}"
					,'sbjCd' : "${bookmarkVO.sbjCd}"
					,'unitCd' : subContentsList[parseInt(currentIdx)].unitCd
					,'detailStudyTm' : detailStudyTm
				}
				,success : function(resultListVO) {
					edutrackAPI.SetConnPageCnt(parseInt(currentIdx));
					edutrackAPI.Terminate('');
				}
				,error : function(request,status,error) {
				}
			});
			
			currentIdx++;
			
			var source = isMobile ? subContentsList[currentIdx].mobileFilePath : subContentsList[currentIdx].unitFilePath;

			if(isMobile){
				var sourceChkArr = source.split('.');
				if(sourceChkArr[sourceChkArr.length -1] == 'mp4') $("#zoomArea").hide();
				else $("#zoomArea").show();
			}

			$("#contentsMain").attr("src", source);
			$("#timeChkDiv").hide();
			if(subContentsList[parseInt(currentIdx)].critTm > 0 && subContentsList[parseInt(currentIdx)].studyYn == 'N'){
				$("#next").attr("class","btn type4 next disabled");
				$("#next").attr("href","#");
				viewTime = subContentsList[parseInt(currentIdx)].detailStudyTm;
				$("#critTm").text(subContentsList[parseInt(currentIdx)].critTm);
				var currentStudyTm;
				var interval = setInterval(function() {
						viewTime++;	
						currentStudyTm = viewTime/60;
						$("#currentStudyTm").text(Math.floor(currentStudyTm));
						if((currentStudyTm) > subContentsList[parseInt(currentIdx)].critTm){
							$("#next").attr("class","btn type4 next");
							$("#next").attr("href","javascript:moveNextChapter();");
							clearInterval(interval);
							subContentsList[parseInt(currentIdx)].studyYn = 'Y';
						}
					}, 1000);
				$("#timeChkDiv").show();
			}else{
				if((parseInt(currentIdx) + 1) == parseInt(subContentsSize)){
					$("#next").attr("class","btn type4 next disabled");
					$("#next").attr("href","#");
				}
			}
			if((parseInt(currentIdx) + 1) > 1){
				$("#before").attr("class","btn type4 prev");
				$("#before").attr("href","javascript:moveBeforeChapter();");
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
<c:if test="${ bookmarkVO.unitType ne 'C' && empty bookmarkVO.unitFilePath}">
<script type="text/javascript" defer="defer">

var isPlayed = 0;
var mediaTotalTime = 0;
var sessionPlayTime = 0;
var sessionSeekTime;
var credit = "${credit}";
var isStartPlay	= false; //-- 동영상 재생이 시작되었는지에 대한 체크 , 크롬에서 전체화면/일반화면 전환시 새로 ready를 호출되어 이어보기 메시지가 계속 뜨는 현상 방지

var result;
var seekTime = 0;
var prgrRatio = 0;
 
 
var halfTime			= 0;
var modalBox = null;

$(document).ready(function() {
	alert("콘텐츠 준비중입니다.");
	window.close();

});

</script>
</c:if>
</head>
<body>
<div class="vod_wrap">
        <div class="vod_header">
            <div class="top_inner">
                <div class="title">
	                <button type="button" onclick="goUnitCd('${contentsVO.preUnitCd}','p')"><i class="xi-angle-left" aria-hidden="true"></i></button>
	                ${contentsVO.unitNm }
	                <button type="button" onclick="goUnitCd('${contentsVO.nextUnitCd}','n','${contentsVO.prgrRatio}')"><i class="xi-angle-right" aria-hidden="true"></i></button>
                </div>
                <div class="group_btn">
                    <button type="button" class="btn-line inquiry-button">문의하기</button>
                    <button type="button" class="btn-line lecture-button">목차열기</button>
                    <button type="button" class="btn-line exam-button">시험목록열기</button>
                    <button type="button" class="btn-line report-button">과제목록열기</button>
                    <c:if test="${CLASSUSERTYPE eq 'STU' }">
                		<button onclick="completeFunction('C')" id="closeBtn" type="button" class="btn-line lecture-button">학습종료</button>
					</c:if>
                </div>
            </div>
        </div>
        <div class="vod_content">
			<iframe src="" id="contentsMain" name="contentsMain" marginheight="0" marginwidth="0" scrolling="no" width="100%" height="100%" frameborder="0" style="padding:0px;" allowfullscreen></iframe>
			<div class="vod_btnArea">
					<div class="btn_box">
						 <button onclick="moveBeforeChapter()" id="before" type="button" class="btn type4 "><i class="xi-arrow-left"></i>이전</button>
						 <button onclick="moveNextChapter()" id="next" type="button" class="btn type4 ">다음<i class="xi-arrow-right"></i></button>
					</div>
				</div>
            <div class="ui right wide sidebar inbox-inquiry">
                <!------------ 문의목록 ------------>
                <c:if test="${CLASSUSERTYPE eq 'STU'}">
	                <div class="board_top">
	                    <div class="page_btn">
	                        <button type="button" class="btn type4" id="qnaSave">등록</button>
	                    </div>
	                </div>
	                <div class="grid-row" id="contentsList"></div> 	
	                <div class="btns" id="qnaBtns" style="margin-bottom: 30px;">
		                <button type="button" class="btn gray2" onclick="addQstn()">저장</button>
		                <button type="button" class="btn type5" onclick="unloadForm()">취소</button>
		            </div> 
	            </c:if>
	            <c:if test="${CLASSUSERTYPE ne 'STU'}">
	            	<div class="grid-row" id="contentsList"></div> 
	            </c:if>
            </div>
            <div class="ui right wide sidebar inbox-lecture">
                <!------------ 강의목록 ------------>
                <div class="learn_top">
                    <ul class="right_state">
                        <li class="step01">학습 미진행</li>
                        <li class="step02">학습 진행중</li>
                        <li class="step03">학습완료</li>
                    </ul>
                </div>
                <div class="course_list">
                	<c:forEach items="${bookmarkList }" var="contentsItem" varStatus="status">
                	<c:if test="${contentsItem.unitLvl eq 1}">
	                    <div class="item
	                 		<c:choose>
	                        	<c:when test="${contentsItem.prgrRatio eq 0}">step01</c:when>
	                        	<c:when test="${contentsItem.prgrRatio eq 100}">step03</c:when>
	                        	<c:otherwise>step02</c:otherwise>
	                       	</c:choose>">
	                        <div class="title">
	                            <h5>
	                            
	                    		<c:choose>
	                    			<c:when test="${CLASSUSERTYPE eq 'STU'}">
			                    		<!-- 순차/랜덤 학습 체크 -->
			                    		<c:choose>
			                    			<c:when test="${status.count ne '0' and beforePrgr < 100 and sbjVO.studyMthd eq 'SE' and contentsCnt > 0  }">
			                    				<span>${contentsItem.unitNm }</span>
			                    			</c:when>
			                    			<c:otherwise>
			                    				<c:choose>
			                    					<c:when test="${contentsItem.cntsTypeCd eq 'CODING_L' or contentsItem.cntsTypeCd eq 'CODING_T' or  contentsItem.cntsTypeCd eq 'META'}">
			                    						<a href="javascript:alert('코딩강의 및 메타버스 강의는 학습목차에서 이동을 지원하지 않습니다.');" >
			                    							<span>${contentsItem.unitNm }</span>
			                    						</a>
			                    					</c:when>
			                    					<c:otherwise>
			                    						<a href="javascript:void(0);" onclick="goUnitCd('${contentsItem.unitCd}','')" >	                            
					                            			<span>${contentsItem.unitNm }</span>
					                            		</a>
			                    					</c:otherwise>
			                    				</c:choose>
			                    			</c:otherwise>
			                    		</c:choose>
	                    			</c:when>
	                    			<c:otherwise>
	                    				<a href="javascript:void(0);" onclick="goUnitCd('${contentsItem.unitCd}','')" >	                            
			                            	<span>${contentsItem.unitNm }</span>
			                            </a>
	                    			</c:otherwise>
	                    		</c:choose>
		                            
	                            </h5>
		                        <c:if test="${CLASSUSERTYPE eq 'STU'}">
		                            <div class="prog_rate">
		                                <ul>
			                                    <li>
			                                        <span class="header">진도율</span><span class="meta">${contentsItem.prgrRatio }%</span>
			                                        <div class="progress">
			                                            <div class="bar red_type" style="width: ${contentsItem.prgrRatio }%;"></div>
			                                        </div>
			                                    </li>
		                                    <c:set var="min"><fmt:formatNumber value="${contentsItem.seekTime/60}" pattern="0"/></c:set>	                                    
		                                    <li>${min }분/${contentsItem.critTm }분</li>
		                                </ul>
		                            </div>
		                         </c:if>
	                        </div>
	                    </div>
	                    <c:set var="contentsCnt" value="${contentsCnt+1 }"/>
	                    <c:set var="beforePrgr" value="${contentsItem.prgrRatio }"/>
	                    </c:if>
                    </c:forEach>
                </div>
            </div>
            <div class="ui right wide sidebar inbox-exam">
                <!------------ 시험목록 ------------>
                <c:if test="${CLASSUSERTYPE eq 'STU'}">
	                <c:forEach items="${examListVO }" var="item" varStatus="status">
	                <div class="course_list" style="padding: 3%;">
	                	<div class="item step02">
							<div class="title">
		                    	<h5>   
	                  		        <a href="javascript:void(0);" onclick="goList('/lec/main/goMenuPage?mcd=MC10000022')">	                            
	                            		<span>${item.examTitle }</span>
	                            	</a>
	                            </h5>            
	                            <div class="prog_rate">
	                                <ul>
	                                    <li style="font-size:16px; display: block;">
	                                        <span class="header" style="width: 100%;">시험 날짜</span>
	                                        <span class="meta">${item.examStartDttm} ${item.examStartHour }:${item.examStartMin }~</span>
	                                    </li>
	                                    <li style="font-size:16px; display: unset;">
	                                        <span class="meta" style="margin-right: 5px;">${item.examEndDttm} ${item.examEndHour }:${item.examEndMin }</span>(${item.examStareTm}분)
	                                    </li>
	                                </ul>
	                            </div>
		                    </div> 
	                    </div>   
	                    </div>            
                	</c:forEach>        
	                <c:if test="${empty examListVO }">
	                	<div class="table_list" id="" style="border-bottom: 2px solid #222;">
							<p style="text-align: center; margin: 50px;">등록된 시험이 없습니다.</p>
						</div>
		            </c:if>
	            </c:if>
            </div>
            <div class="ui right wide sidebar inbox-report">
                <!------------과제목록 ------------>
                <c:if test="${CLASSUSERTYPE eq 'STU'}">
	                <c:forEach items="${assignmentListVO }" var="item" varStatus="status">
	                <div class="course_list" style="padding: 3%;">
	                	<div class="item step02">
							<div class="title">
		                    	<h5>   
	                  		        <a href="javascript:void(0);" onclick="goList('/lec/assignment/addSendMain?mcd=MC10000025&asmtSn=${item.asmtSn}')">	                            
	                            		<span>${item.asmtTitle }</span>
	                            	</a>
	                            </h5>            
	                            <div class="prog_rate">
	                                <ul>
	                                    <li style="font-size:16px; display: unset;">
	                                        <span class="header" style="width: 100%;">과제 기간</span>
	                                        <span class="meta">${item.asmtStartDttm}~</span>
	                                    </li>
	                                    <li style="font-size:16px; display: unset;">
	                                        <span class="meta">${item.asmtEndDttm}</span>
	                                    </li>
	                                </ul>
	                            </div>
		                    </div> 
	                    </div>   
	                    </div>            
                	</c:forEach>        
	                <c:if test="${empty assignmentListVO }">
	                	<div class="table_list" id="" style="border-bottom: 2px solid #222;">
							<p style="text-align: center; margin: 50px;">등록된 과제가 없습니다.</p>
						</div>
		            </c:if>
	            </c:if>
            </div>
        </div>
    </div>



</body>
</html>

	