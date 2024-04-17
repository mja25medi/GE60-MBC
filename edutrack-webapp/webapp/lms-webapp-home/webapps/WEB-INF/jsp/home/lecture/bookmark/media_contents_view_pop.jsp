<!DOCTYPE html>
<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<c:url var="img_base" value="/img/home" />
<c:set var="bookmarkVO" value="${bookmarkVO}"/>
<c:set var="credit" value="credit"/>
<c:set var="cmiMode" value="normal"/>
<html>
<%
	String agent = request.getHeader("User-Agent");
	String brType = "";
	if(agent.indexOf("Trident")>0) brType="IE";//-- IE 에서 전체화면시 하단 진행바 동작 이상을 감추어 주기 위해 판단
	request.setAttribute("brType", brType);
	String osMac = "";
	if(agent.indexOf("Mac") > -1) osMac = "Mac";
	request.setAttribute("osMac", osMac);

	request.setAttribute("mediaStreamUse", Constants.MEDIA_STREAM_USE);
	request.setAttribute("mediaStreamUrl", Constants.MEDIA_STREAM_URL);
	request.setAttribute("mediaStreamHls", Constants.MEDIA_STREAM_HLS);
	request.setAttribute("flowplayerKey", Constants.FLOWPLAYER_KEY);
%>
<head>
<title>Flow player mp4</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<link rel="stylesheet" href="/libs/flowplayer7/skin/skin.css">
<meditag:css href="tpl/bootstrap/bower_components/bootstrap/dist/css/bootstrap.min.css"/>
<!-- <link rel="stylesheet" href="/tpl/002/css/video.css"> -->
<link rel="stylesheet" href="/tpl/${COLOR_TPL}/fonts/xeicon.min.css"> 

    <link rel="stylesheet" href="/tpl/${COLOR_TPL}/css/hrd_common.css">
    <link rel="stylesheet" href="/tpl/${COLOR_TPL}/css/webfonts.css">
    <link rel="stylesheet" href="/tpl/${COLOR_TPL}/css/board.css">
    <link rel="stylesheet" href="/tpl/${COLOR_TPL}/css/contents.css">
    <link rel="stylesheet" href="/tpl/${COLOR_TPL}/css/class_layout.css">
    <link rel="stylesheet" href="/tpl/${COLOR_TPL}/css/class_content.css">
	<link rel="stylesheet" href="/tpl/${COLOR_TPL}/css/sidebar.min.css"> 

<style type="text/css">
	/* custom player skin */
		.flowplayer { width: 100%; background-color: #222; background-size: cover;  }
		.flowplayer.fp-controls {background-color: rgba(0, 0, 0, 0.4)}/* 소리 조절 보여주기*/
		#lecQnaForm {padding-bottom: 30px;}
		/* .vod_header .top_inner .title {padding-left: 308px;} 
		.vod_header {margin-top: -24px;}*/
</style>

<meditag:js src="/js/jquery/jquery-1.11.1.min.js"/>
<script src="/tpl/${COLOR_TPL}/js/sidebar.min.js"></script>
<meditag:js src="/js/jquery/jquery.form.js"/>
<meditag:js	src="/app/js/Context.js" />
<meditag:js src="/js/modaldialog.js"/>
<meditag:js src="/tpl/bootstrap/bower_components/bootstrap/dist/js/bootstrap.min.js"/>

<!-- 2. flowplayer -->
<script src="/libs/flowplayer7/flowplayer.js"></script>
<script src="/libs/flowplayer7/hls.light.min.js"></script>
<!-- 배속 기능 -->
<script src="/libs/flowplayer7/flowplayer.speed-menu.min.js"></script>

    <script src="/tpl/${COLOR_TPL}/jquery/jquery-3.2.1.min.js"></script>
    <script src="/tpl/${COLOR_TPL}/js/func.min.js"></script>
    <script src="/tpl/${COLOR_TPL}/js/common.js"></script>
    <script src="/tpl/${COLOR_TPL}/js/class.js"></script>
    <script src="/tpl/${COLOR_TPL}/js/iframeResizer.min.js"></script>
    <script src="/tpl/${COLOR_TPL}/js/sidebar.min.js"></script>

	<script src="/js/common.js"></script>
	<script src="/js/jquery/jquery.form.js"></script>

</head>
<body>
<c:if test="${not empty filePath}">

<iframe id='edutrackAPIFrame' name='edutrackAPIFrame' width='0' height='0' frameborder='0' src="<c:url value="/jsp/bookmark/study_edutrack_adapter.jsp"/>" onload="onloadApiFrame()"></iframe>
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

	
	<c:if test="${mediaStreamUse eq 'use'}">
		<c:if test="${cntsTypeCd eq 'VOD'}">
			<c:if test="${mediaStreamHls ne ''}">
				sourcesType = "application/x-mpegurl";
				sourcesSrc = "${mediaStreamUrl}${orgCntsPath}${filePath}/${fileName}/${mediaStreamHls}";
			</c:if>
			<c:if test="${mediaStreamHls eq ''}">
				sourcesSrc = "${mediaStreamUrl}${orgCntsPath}${filePath}/${fileName}";
			</c:if>
		</c:if>		
		<c:if test="${cntsTypeCd ne 'VOD' && cntsTypeCd ne 'CDN'}">
			sourcesSrc = "${filePath}/${fileName}";
		</c:if>	
		<c:if test="${cntsTypeCd eq 'CDN'}">
			sourcesSrc = "${filePath}";
		</c:if>
	</c:if>
	<c:if test="${mediaStreamUse ne 'use'}">
		<c:if test="${cntsTypeCd ne 'CDN'}">
			sourcesSrc = "${filePath}/${fileName}";
		</c:if>	
		<c:if test="${cntsTypeCd eq 'CDN'}">
			sourcesSrc = "${filePath}";
		</c:if>
	</c:if>
	
   
	modalBox = new $M.ModalDialog({
		"modalid" : "modal1"
	});
	
	// 플로플레이어 인증키
	var flowerPlayerKey = '${flowplayerKey}';

	<%-- key: '<%=Constants.FLOWPLAYER_KEY%>', --%>
	flowplayer("#player",{
	  key: flowerPlayerKey,
	  autoplay: true,
	  share: false,
	  hlsQualities: false,
//	  splash: true,
	  keyboard: false,
      clip: {
	    	autoplay: true,
	        sources: [
	            {
	                type: sourcesType,
					src: sourcesSrc
	            }
	        ],
	        speeds: [0.5, 1, 2]
	    }
	});
	parent.playerType = "mp4";
});

$(document).on("click","#qnaSave",function(){
	$('#lecQnaForm').toggle();
	$('#qnaBtns').toggle();
	$('#qnaTitle').focus();
})

<c:if test="${review eq 'Y'}">
	flowplayer(function(api, root) {
		// when a new video is about to be loaded
		api.bind("load", function() {
			//$("#fpengine").text(api.engine);
		});
		api.bind("load", function (e, api, video) {
			//alert(" \n load engine: "+ api.engine+" \n video.src: "+ video.src);
			//$("#videosrc").text(video.src);
		});
		// when a video is loaded and ready to play
		api.bind("ready", function() {
			api.play(); //-- 크롬에서 전체화면/일반화면 전환시 새로 ready를 호출되어 동영상 재생이 멈추는 현상 방지, 단 전체화면 상태에서 esc 를 통해 돌아오는 경우에는 정지가 됨
		});
		api.bind("progress", function() {	    	// Play중 시간 갱신
			//checkPlayTime(api.video.time);
		});
		api.bind("finish", function() {	      	// Play 종료시
			//alert('Play End!!');
		});
	});
	
	function completeFunction(compType) {		
		if(compType != "NC") {
			window.close();
		}
		opener.location.reload();
	}
	
</c:if>

<c:if test="${review ne 'Y'}">
	var edutrackAPI = null;
	
	function initEdutrackAPI() {
		if(edutrackAPI != null) {
			var eapi = edutrackAPI;
			eapi.SetStdNo("${STUDENTNO}");
			eapi.SetSbjCd("${bookmarkVO.sbjCd}");
			eapi.SetUnitCd("${bookmarkVO.unitCd}");
			eapi.SetRegNo("${USERNO}");
			eapi.SetBookmarkType("${bookmarkVO.prgrChkType}");
			eapi.SetTotalPageCnt("${bookmarkVO.totalPage}");
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
	
	function onloadApiFrame() {
		initEdutrackAPI();
	}
	
	window.onunload = onunloadFunction;
	window.onbeforeunload = onunloadFunction;
	
	function onunloadFunction() {
		edutrackAPI.SetValue("prgrRatio", prgrRatio);
		edutrackAPI.SetSeekTime(sessionSeekTime); //--seek 시간 설정
		result = edutrackAPI.Terminate("");
		//opener.listContents();
	}
	
	function setShowControl() {
		$(".flowplayer .fp-timeline").show();
	}
	
	function setHideControl() {
		$(".flowplayer .fp-timeline").hide();
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

	flowplayer(function(api, root) {
		// when a new video is about to be loaded
		api.bind("load", function() {
			//$("#fpengine").text(api.engine);
		});
		api.bind("load", function (e, api, video) {
			//alert(" \n load engine: "+ api.engine+" \n video.src: "+ video.src);
			//$("#videosrc").text(video.src);
		});
		// when a video is loaded and ready to play
		api.bind("ready", function() {
			mediaTotalTime  = api.video.duration;
			$("#mediaTotalTime").html(Math.round(Math.floor(mediaTotalTime / 60))+":"+ Math.round(Math.floor(mediaTotalTime % 60))); 
			if(credit == 'no-credit') {
				setShowControl();
			} else {
				if(prgrRatio < 100)
					setHideControl();
				else
					setShowControl();
			}
			if(seekTime > 0) {
				var st = confirm("<spring:message code="lecture.message.contents.learn.continue"/>");
				if(st){
					api.seek(seekTime, function(){});
				}
			}
			api.play(); //-- 크롬에서 전체화면/일반화면 전환시 새로 ready를 호출되어 동영상 재생이 멈추는 현상 방지, 단 전체화면 상태에서 esc 를 통해 돌아오는 경우에는 정지가 됨
			checkPlayTime();
		});
		api.bind("begin", function() {	    	// Play중 시간 갱신
			alert('begin');
		});
		api.bind("pause", function() {	    	// 일시정지 시 
			isPlayed = 0;
			edutrackAPI.SetValue("prgrRatio", prgrRatio);
			edutrackAPI.SetSeekTime(sessionSeekTime); //--seek 시간 설정
			result = edutrackAPI.Terminate("");
		});
		api.bind("progress", function() {	    	// Play중 시간 갱신
			isPlayed = 1;
			setSeekTime(api.video.time);
			
			var videioTimeSec = Math.round(Math.floor(api.video.time % 60));
			
			$("#seekTime").text(Math.round(Math.floor(api.video.time / 60))+":"+  (videioTimeSec < 10 ? '0'+ videioTimeSec : videioTimeSec) );
			
		});
		api.bind("finish", function() {	      	// Play 종료시
			prgrRatio = 100; //-- 완료가 되면 무조건 100%로 셋팅
			setShowControl();
			isPlayed = 0;
		});
	});
	
	function completeFunction(compType) {
		edutrackAPI.SetValue("prgrRatio", prgrRatio);
		edutrackAPI.SetSeekTime(sessionSeekTime); //--seek 시간 설정
		result = edutrackAPI.Terminate("");
		
		if(compType != "NC") {
			window.close();
		}
		opener.location.reload();
	}
	
	</c:if>

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

</script>
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
			<div id="player" ></div>
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
<%--    <div id="wrap">
        <div class="container">
        	<div id="mediaContents" class="media-area">
				<div id="player" ></div>
					<div class="testbottom">
					<c:if test="${CLASSUSERTYPE eq 'STU' }">
						<p class="txt-right mt10">학습 종료 시 <span class="fcRed">반드시 학습종료 버튼</span>을 클릭하시기 바랍니다.</p>
					</c:if>
            		<div class="buttons flex flex-wrap mt10">
            			<c:if test="${CLASSUSERTYPE eq 'STU' }">
                			<button onclick="loadForm()" type="button" class="btn gray2 mra">문의하기</button>
						</c:if>
            			<c:if test="${CLASSUSERTYPE eq 'STU' }">
                			<button onclick="completeFunction('C')" id="closeBtn" type="button" class="btn gray2 ">학습종료</button>
						</c:if>
						<c:if test="${CLASSUSERTYPE ne 'STU' }">               
                			<button onclick="window.close()" id="closeBtn" type="button" class="btn type5 ">닫기</button>
                		</c:if>
            		</div>
            		</div>
           	 </div>
            <div id="qstnWriteFormDiv" class="form-area"></div> 
             
        </div>
    </div> --%>

</c:if>
<c:if test="${empty filePath}">
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

</body>
</html>

	