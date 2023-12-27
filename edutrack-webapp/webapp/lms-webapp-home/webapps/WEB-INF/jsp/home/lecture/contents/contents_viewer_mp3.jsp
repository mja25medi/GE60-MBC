<!DOCTYPE html>
<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="bookmarkVO" value="${bookmarkVO}" />

<mhtml:class_html>
<mhtml:class_head>
<link href="/libs/jplayer/skin/blue.monday/css/jplayer.blue.monday.min.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/libs/jplayer/jplayer/jquery.jplayer.min.js"></script>
<script type="text/javascript">
//LMS 용
var isStartPlay			= false; //-- mp3 재생이 시작되었는지에 대한 체크 , 크롬에서 이어보기 설정시 canplay 상태가 지속적으로  호출되어 이어보기 메시지가 계속 뜨는 현상 방지
var mediaDuration = 0;
var prevSeekTime = parent.getSeekTime(); //-- 이전 학습 위치를 설정한다.
var curPlayPosition = 0;
var maxPlayPosition = 0;
/**
 * 재생 위치(초)를 지속적으로 갱신 시켜줌
 */
function checkPlayTime(play_time) {
	curPlayPosition = parseInt(play_time);
	parent.setSeekTime(curPlayPosition);
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
	parent.window.resizeTo(490, 200);
	parent.resizeIframe(460, 130);

	var id = "#jquery_jplayer_1";
	var GETjPlay = {
		title: "${bookmarkVO.unitNm}",
		mp3: "${bookmarkVO.unitFilePath}"
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

	checkProgress();
});

function checkProgress() {
	if(parseInt(parent.getPrgrRatio(),10) >= 100) {
		$("#jplayer-seek-bar").addClass("jp-seek-bar");
		$("#repeat-button").show();
	} else {
		$("#jplayer-seek-bar").removeClass("jp-seek-bar");
		$("#repeat-button").hide();
	}
}
//]]>
</script>
</mhtml:class_head>
<body>
<div style="margin:5px auto; width:422px;">
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
</body>
</mhtml:class_html>