<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<%
	String agent 		= request.getHeader("User-Agent");
	String brType = "";
	if(agent.indexOf("Trident")>0) brType="IE";//-- IE 에서 전체화면시 하단 진행바 동작 이상을 감추어 주기 위해 판단
	request.setAttribute("brType", brType);
	String osMac = "";
	if(agent.indexOf("Mac") > -1) osMac = "Mac";
	request.setAttribute("osMac", osMac);

	String cntsBase = "/contents/usercnts";

	request.setAttribute("mediaStreamUse", Constants.MEDIA_STREAM_USE);
	request.setAttribute("mediaStreamUrl", Constants.MEDIA_STREAM_URL);
	request.setAttribute("mediaStreamHls", Constants.MEDIA_STREAM_HLS);
	request.setAttribute("flowplayerKey", Constants.FLOWPLAYER_KEY);
	request.setAttribute("cntsBase", cntsBase);
%>
<c:set var="olcCntsVO" value="${vo}"/>
<mhtml:home_html>
<mhtml:home_head titleName="${MENUNAME}">
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
		/*.flowplayer { background-image: url("https://farm4.staticflickr.com/3169/2972817861_73ae53c2e5_b.jpg")}*/
		/*.flowplayer { background-image: url("3.jpg")}*/
	</style>

	<!-- 2. flowplayer -->
	<script src="/libs/flowplayer/flowplayer.min.js"></script>
	<script type="text/javascript">
		var isStartPlay	= false; //-- 동영상 재생이 시작되었는지에 대한 체크 , 크롬에서 전체화면/일반화면 전환시 새로 ready를 호출되어 이어보기 메시지가 계속 뜨는 현상 방지

		$(document).ready(function() {
			$("body").css("background-color",parent.contentColor);
		});

		//-- 동영생 재생 위치를 넘겨 준다.
		function checkPlayTime(video_time) {

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
				api.play(); //-- 크롬에서 전체화면/일반화면 전환시 새로 ready를 호출되어 동영상 재생이 멈추는 현상 방지, 단 전체화면 상태에서 esc 를 통해 돌아오는 경우에는 정지가 됨
			});
			api.bind("progress", function() {	    	// Play중 시간 갱신
				//checkPlayTime(api.video.time);
			});
			api.bind("finish", function() {	      	// Play 종료시
				//alert('Play End!!');
			});
		});
	</script>
</mhtml:home_head>

<body style="padding-top: 0px;">
<div data-key="${flowplayerKey}"
	<c:if test="${USER_DEVICE eq 'PC'}">
	 data-swf="<c:url value="/libs/flowplayer/flowplayer.swf"/>"
	 data-engine="flash" data-flashfit="true"
	 data-fullscreen="true"
	</c:if>
	 data-native_fullscreen="false"
	 data-embed="false"
	 data-volume="0.5"
	 data-keyboard="false"
	 class="flowplayer aside-time ">
	<video>
	<c:if test="${USER_DEVICE eq 'PC'}">
		<c:if test="${mediaStreamUse eq 'use'}">
		<source type="video/flash" src="mp4:${cntsBase}/${vo.userNo}/${vo.cntsFilePath}"><%-- 와우자 서버를 사용하는경우(vod_smart_yn=Y) 기본 적으로 flash engine을 통해 rtmp 스트리밍을 지원하도록 설정 해 준다.--%>
			<c:if test="${osMac eq 'Mac' }">
		<source type="video/mp4" src="${mediaStreamUrl}${cntsBase}/${vo.userNo}/${vo.cntsFilePath}/${mediaStreamHls}"> <!-- MAC OS 만 통과-->
			</c:if>
			<c:if test="${osMac ne 'Mac' }">
		<source type="video/mp4" src="${cntsBase}/${vo.userNo}/${vo.cntsFilePath}">   <!-- Mac 빼고 나머지는 http 전송 -->
			</c:if>
		</c:if>
		<c:if test="${mediaStreamUse ne 'use'}">
		<source type="video/mp4" src="${cntsBase}/${vo.userNo}/${vo.cntsFilePath}">
		</c:if>
	</c:if>
	<c:if test="${USER_DEVICE ne 'PC'}"> <!-- 모바일 또는 디바이스 확인 안되는 경우  -->
		<c:if test="${mediaStreamUse eq 'use' && USER_OS eq 'ios'}">
		<source type="video/mp4" src="${mediaStreamUrl}${cntsBase}/${vo.userNo}/${vo.cntsCts}/${mediaStreamHls}"> <!-- ios , android 4.3 통과 -->
		</c:if>
		<c:if test="${mediaStreamUse ne 'use' ||  USER_OS ne 'ios'}">
		<source type="video/mp4" src="${cntsBase}/${vo.userNo}/${vo.cntsCts}"> <%-- android가 스트리밍 시 단말기 및 브라우저에 따라 오류 발생하는 양상이 다르게 나오므로  wowzq+mp4+ios 조건  이외는 모두 http 전송 --%>
		</c:if>
	</c:if>
	</video>
</div>

<div style="width:800px;height:100%;margin: 0 auto;paddig:5px; text-align: left; color: #000;" class="wordbreak">
	${vo.cntsCts}
</div>
</body>
</mhtml:home_html>