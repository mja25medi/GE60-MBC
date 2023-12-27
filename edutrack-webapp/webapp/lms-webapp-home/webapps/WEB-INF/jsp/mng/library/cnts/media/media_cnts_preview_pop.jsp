<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:if test="${playerDiv eq 'kollus' }">
<c:if test="${uldStsCd eq 'complete'}">
	<iframe id='previewFrame' name='previewFrame' style="width:100%; height:100%" frameborder='0' scrolling='no' src='${playerUrl}/s?key=${mediaToken}'/>
</c:if>
<c:if test="${uldStsCd ne 'complete'}">
	<div class="col-md-6">
		<div class="well" style="margin-top:20px;line-height:30px;">
			<spring:message code="library.message.contents.media.incompletate.conversion"/><br/>
			<spring:message code="library.message.contents.media.not.play"/>
		</div>
		<div class="text-center">
			<a href="javascript:window.close();" class="btn btn-default btn-sm"><spring:message code="button.close"/></a>
		</div>
	</div>
</c:if>
</c:if>

<c:if test="${playerDiv eq 'common' }">
	<c:choose>
		<c:when test="${fileExt eq 'mp4' }">
<%
	String agent = request.getHeader("User-Agent");
	String brType = "";
	if(agent.indexOf("Trident")>0) brType="IE";//-- IE 에서 전체화면시 하단 진행바 동작 이상을 감추어 주기 위해 판단
	request.setAttribute("brType", brType);
	String osMac = "";
	if(agent.indexOf("Mac") > -1) osMac = "Mac";
	request.setAttribute("osMac", osMac);

	request.setAttribute("wowzaUse", Constants.WOWZA_USE);
	request.setAttribute("wowzaUrlStmp", Constants.WOWZA_URL_RTMP);
	request.setAttribute("wowzaUrlStsp", Constants.WOWZA_URL_RTSP);
	request.setAttribute("wowzaUrlHttp", Constants.WOWZA_URL_HTTP);
	request.setAttribute("mediaUse", Constants.MEDIA_USE);
	request.setAttribute("mediaUrl", Constants.MEDIA_URL);
	request.setAttribute("flowplayerKey", Constants.FLOWPLAYER_KEY);
%>
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
<div id="playerLayer" data-key="${flowplayerKey}"
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
		<c:if test="${wowzaUse eq 'use'}">
		<source type="video/flash" src="mp4:${filePath}/${fileName}"><%-- 와우자 서버를 사용하는경우(vod_smart_yn=Y) 기본 적으로 flash engine을 통해 rtmp 스트리밍을 지원하도록 설정 해 준다.--%>
			<c:if test="${osMac eq 'Mac' }">
		<source type="video/mp4" src="${wowzaUrlHttp}${filePath}/${fileName}/playlist.m3u8"> <!-- MAC OS 만 통과-->
			</c:if>
			<c:if test="${osMac ne 'Mac' }">
		<source type="video/mp4" src="${filePath}/${fileName}">   <!-- Mac 빼고 나머지는 http 전송 -->
			</c:if>
		</c:if>
		<c:if test="${wowzaUse ne 'use'}">
			<c:if test="${cntsTypeCd ne 'CDN'}">
				<source type="video/mp4" src="${filePath}/${fileName}">
			</c:if>
			<c:if test="${cntsTypeCd eq 'CDN'}">
				<source type="video/mp4" src="${filePath}">
			</c:if>
		</c:if>
	</c:if>
	<c:if test="${USER_DEVICE ne 'PC'}"> <!-- 모바일 또는 디바이스 확인 안되는 경우  -->
		<c:if test="${wowzaUse eq 'use' && USER_OS eq 'ios'}">
		<source type="video/mp4" src="${wowzaUrlHttp}${filePath}/${fileName}/playlist.m3u8"> <!-- ios , android 4.3 통과 -->
		</c:if>
		<c:if test="${wowzaUse ne 'use' ||  USER_OS ne 'ios'}">
		<%-- <source type="video/mp4" src="${filePath}/${fileName}"> android가 스트리밍 시 단말기 및 브라우저에 따라 오류 발생하는 양상이 다르게 나오므로  wowzq+mp4+ios 조건  이외는 모두 http 전송 --%>
			<c:if test="${cntsTypeCd ne 'CDN'}">
				<source type="video/mp4" src="${filePath}/${fileName}">
			</c:if>
			<c:if test="${cntsTypeCd eq 'CDN'}">
				<source type="video/mp4" src="${filePath}">
			</c:if>
		</c:if>
	</c:if>
	</video>
</div>

		</c:when>
		<c:when test="${fileExt eq 'mp3' }">
<script type="text/javascript">
//LMS 용
var isStartPlay			= false; //-- mp3 재생이 시작되었는지에 대한 체크 , 크롬에서 이어보기 설정시 canplay 상태가 지속적으로  호출되어 이어보기 메시지가 계속 뜨는 현상 방지
var mediaDuration = 0;
var prevSeekTime = 0; //-- 이전 학습 위치를 설정한다.
var curPlayPosition = 0;
var maxPlayPosition = 0;
/**
 * 재생 위치(초)를 지속적으로 갱신 시켜줌
 */
function checkPlayTime(play_time) {
	curPlayPosition = parseInt(play_time);
	//parent.setSeekTime(curPlayPosition);
	//-- 아래 maxPlayPosition 갱신은  callEndProcess()에서 사용하기 위해..
	if(maxPlayPosition < curPlayPosition)
		maxPlayPosition = curPlayPosition;
}
/**
 * Android 재생 종료 체크가 정상적이지 못하여 한번더 체크 할 수 있도록 함.
 */
function callEndProcess(){
	//-- 학습상태가 complete가 아닌경우 정상적으로 마지막까지 보았는지 확인하여 학습완료 처리
	if(mediaDuration <= maxPlayPosition){
		//-- 여기에서 parent에 정상 학습 완료 상태를 기록하는 명령 수행 해야 함.
		//alert("Play End !! ^0^");
	}

}

var jPlayerAndroidFix = (function($) {
	var fix = function(id, media, options) {
		this.playFix = false;
		this.init(id, media, options);
	};
	fix.prototype = {
		init: function(id, media, options) {
			var self = this;

			// Store the params
			this.id = id;
			this.media = media;
			this.options = options;

			// Make a jQuery selector of the id, for use by the jPlayer instance.
			this.player = $(this.id);

			// Make the ready event to set the media to initiate.
			this.player.bind($.jPlayer.event.ready, function(event) {
				// Use this fix's setMedia() method.
				self.setMedia(self.media);
			});

			/* 2014.11.27 bschoi add start */
			/**
			* mp3 재생준비 완료
			*/
			this.player.bind($.jPlayer.event.canplay, function(event) {
				//- 여기에 이전 학습 기록이 있는경우 가져오는 기능을 넣어야지.
				if(!$.jPlayer.platform.android){
					if (!isStartPlay) {
						mediaDuration = parseInt(event.jPlayer.status.duration);
						if(prevSeekTime > 0  && mediaDuration > prevSeekTime){
							var st = confirm("<spring:message code="lecture.message.contents.learn.continue"/>");
							if(st){
								$(this).jPlayer('play',prevSeekTime);
							}
						}

					}
					isStartPlay = true;
				}// android if End
			});
			/**
			* mp3 재생중인경우
			*/
			this.player.bind($.jPlayer.event.timeupdate, function(event) {
				checkPlayTime(event.jPlayer.status.currentTime);

			});
			/**
			* mp3 재생이 끝난 경우
			*/
			this.player.bind($.jPlayer.event.ended, function(event) {
				if(!$.jPlayer.platform.android){
					callEndProcess();
				}
			});
			/* 2014.11.27 bschoi add end */

			// Apply Android fixes
			if($.jPlayer.platform.android) {

				// Fix playing new media immediately after setMedia.
				this.player.bind($.jPlayer.event.progress, function(event) {
					if(self.playFixRequired) {
						/* bschoi start */
						if (!isStartPlay) {
							mediaDuration = parseInt(event.jPlayer.status.duration);
							/* if(prevSeekTime > 0  && mediaDuration > prevSeekTime && confirm('android 이전 학습기록이 있습니다. 이어서 학습하시겠습니까?'))
								$(this).jPlayer('play',prevSeekTime); */
							if(prevSeekTime > 0  && mediaDuration > prevSeekTime){
								var st = confirm("<spring:message code="lecture.message.contents.learn.continue"/>");
								if(st){
									$(this).jPlayer('play',prevSeekTime);
								}
							}
						}
						isStartPlay = true;
						/* bschoi end */

						self.playFixRequired = false;

						// Enable the contols again
						// self.player.jPlayer('option', 'cssSelectorAncestor', self.cssSelectorAncestor);

						// Play if required, otherwise it will wait for the normal GUI input.
						if(self.playFix) {
							self.playFix = false;
							$(this).jPlayer("play");
						}
					}
				});
				// Fix missing ended events.
				this.player.bind($.jPlayer.event.ended, function(event) {
					if(self.endedFix) {
						/* bschoi start*/
						callEndProcess();
						/* bschoi end */

						self.endedFix = false;
						setTimeout(function() {
							self.setMedia(self.media);
						},0);
						// what if it was looping?
					}
				});
				this.player.bind($.jPlayer.event.pause, function(event) {
					if(self.endedFix) {
						var remaining = event.jPlayer.status.duration - event.jPlayer.status.currentTime;
						if(event.jPlayer.status.currentTime === 0 || remaining < 1) {
							// Trigger the ended event from inside jplayer instance.
							setTimeout(function() {
								self.jPlayer._trigger($.jPlayer.event.ended);
							},0);
						}
					}
				});
			}

			// Instance jPlayer
			this.player.jPlayer(this.options);

			// Store a local copy of the jPlayer instance's object
			this.jPlayer = this.player.data('jPlayer');

			// Store the real cssSelectorAncestor being used.
			this.cssSelectorAncestor = this.player.jPlayer('option', 'cssSelectorAncestor');

			// Apply Android fixes
			this.resetAndroid();

			return this;
		},
		setMedia: function(media) {
			this.media = media;

			// Apply Android fixes
			this.resetAndroid();

			// Set the media
			this.player.jPlayer("setMedia", this.media);
			return this;
		},
		play: function() {
			// Apply Android fixes
			if($.jPlayer.platform.android && this.playFixRequired) {
				// Apply Android play fix, if it is required.
				this.playFix = true;
			} else {
				// Other browsers play it, as does Android if the fix is no longer required.
				this.player.jPlayer("play");
			}
		},
		resetAndroid: function() {
			// Apply Android fixes
			if($.jPlayer.platform.android) {
				this.playFix = false;
				this.playFixRequired = true;
				this.endedFix = true;
				// Disable the controls
				// this.player.jPlayer('option', 'cssSelectorAncestor', '#NeverFoundDisabled');
			}
		}
	};
	return fix;
})(jQuery);

$(document).ready(function() {

	var id = "#jquery_jplayer_1";
	var GETjPlay = {
		title: "${fileName}",
		mp3: "${filePath}/${fileName}"
	};

	var options = {
		swfPath: "/libs/jplayer/jplayer",
		supplied: "mp3",
		wmode: "window",
		useStateClassSkin: true,
		autoBlur: false,
		smoothPlayBar: true,
		keyEnabled: true,
		//remainingDuration: true, //-- true 인 경우는 mp3 전체 시간이 아닌 남아 있는 시간 표시 하게 됨 2014.11.27 bschoi
		toggleDuration: true
	};

	var myAndroidFix = new jPlayerAndroidFix(id, GETjPlay, options);

	$('#setMedia-GETjPlay').click(function() {
		myAndroidFix.setMedia(GETjPlay);
	});
	$('#setMedia-GETjPlay-play').click(function() {
		myAndroidFix.setMedia(GETjPlay).play();
	});
	$("body").css("background-color",parent.contentColor);
});

//]]>
</script>

<div style="margin:50px auto; width:422px;">
	<div id="jquery_jplayer_1" class="jp-jplayer"></div>
	<div id="jp_container_1" class="jp-audio" role="application" aria-label="media player">
		<div class="jp-type-single">
			<div class="jp-gui jp-interface">
				<div class="jp-controls">
					<button class="jp-play" role="button" tabindex="0">play</button>
					<button class="jp-stop" role="button" tabindex="0">stop</button>
				</div>
				<div class="jp-progress">
					<div class="jp-seek-bar" id="jplayer-seek-bar">
						<div class="jp-play-bar"></div>
					</div>
				</div>
				<div class="jp-volume-controls">
					<button class="jp-mute" role="button" tabindex="0">mute</button>
					<button class="jp-volume-max" role="button" tabindex="0">max volume</button>
					<div class="jp-volume-bar">
						<div class="jp-volume-bar-value"></div>
					</div>
				</div>
				<div class="jp-time-holder">
					<div class="jp-current-time" role="timer" aria-label="time">&nbsp;</div>
					<div class="jp-duration" role="timer" aria-label="duration">&nbsp;</div>
					<div class="jp-toggles">
						<button id="repeat-button" class="jp-repeat" role="button" tabindex="0">repeat</button>
					</div>
				</div>
			</div>
			<div class="jp-details">
				<div class="jp-title" aria-label="title">&nbsp;</div>
			</div>
			<div class="jp-no-solution">
				<span>Update Required</span>
				To play the media you will need to either update your browser to a recent version or update your <a href="http://get.adobe.com/flashplayer/" target="_blank">Flash plugin</a>.
			</div>
		</div>
	</div>
</div>

<div style="width:800px;height:100%;margin: 0 auto;paddig:5px; text-align: left; color: #000;" class="wordbreak">
	${olcCntsDTO.cntsCts}
</div>
		</c:when>
		<c:otherwise>
	<div class="col-md-6">
		<div class="well" style="margin-top:20px;line-height:30px;">
			<spring:message code="library.message.contents.media.not.play"/>
		</div>
		<div class="text-center">
			<a href="javascript:window.close();" class="btn btn-default btn-sm"><spring:message code="button.close"/></a>
		</div>
	</div>

		</c:otherwise>
	</c:choose>
</c:if>
