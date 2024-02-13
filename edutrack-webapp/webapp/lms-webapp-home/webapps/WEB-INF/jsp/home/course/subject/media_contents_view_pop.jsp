<!DOCTYPE html>
<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
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
<link rel="stylesheet" href="/tpl/002/css/video.css"> 

<style type="text/css">
	/* custom player skin */
		.flowplayer { width: 100%; background-color: #222; background-size: cover;  }
		.flowplayer.fp-controls {background-color: rgba(0, 0, 0, 0.4)}/* 소리 조절 보여주기*/
</style>

<meditag:js src="/js/jquery/jquery-1.11.1.min.js"/>
<meditag:js src="/js/jquery/jquery.form.js"/>
<meditag:js	src="/app/js/Context.js" />
<meditag:js src="/js/modaldialog.js"/>
<meditag:js src="/tpl/bootstrap/bower_components/bootstrap/dist/js/bootstrap.min.js"/>

<!-- 2. flowplayer -->
<script src="/libs/flowplayer7/flowplayer.js"></script>
<script src="/libs/flowplayer7/hls.light.min.js"></script>

</head>
<body>
<c:if test="${not empty filePath}">

<iframe id='edutrackAPIFrame' name='edutrackAPIFrame' width='0' height='0' frameborder='0' ></iframe>
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
	  splash: true,
	  keyboard: false,
      clip: {
	    	autoplay: true,
	        sources: [
	            {
	                type: sourcesType,
					src: sourcesSrc
	            }
	        ],
	    }
	});
	parent.playerType = "mp4";
});

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


function modalBoxClose() {
	modalBox.clear();
	modalBox.close();
}

</script>
   <div id="wrap">
        <div class="container">
			<div id="player" ></div>
            <div class="txt-right mt20 mb40">
               	<button onclick="window.close()" id="closeBtn" type="button" class="btn type5 ">닫기</button>
            </div>
            <div id="qstnWriteFormDiv"></div> 
             
        </div>
    </div>
            <!-- // contents -->
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

	