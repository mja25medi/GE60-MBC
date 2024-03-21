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
<link rel="stylesheet" href="/tpl/${COLOR_TPL}/fonts/xeicon.min.css"> 
<link rel="stylesheet" href="/tpl/${COLOR_TPL}/css/hrd_common.css">
<link rel="stylesheet" href="/tpl/${COLOR_TPL}/css/webfonts.css">
<link rel="stylesheet" href="/tpl/${COLOR_TPL}/css/board.css">
<link rel="stylesheet" href="/tpl/${COLOR_TPL}/css/contents.css">
<link rel="stylesheet" href="/tpl/${COLOR_TPL}/css/class_layout.css">
<link rel="stylesheet" href="/tpl/${COLOR_TPL}/css/class_content.css">
<link rel="stylesheet" href="/tpl/${COLOR_TPL}/js/codemirror/lib/codemirror.css">
<link rel="stylesheet" href="/tpl/${COLOR_TPL}/js/codemirror/theme/darcula.css">

<style type="text/css">
	/* custom player skin */
		.flowplayer { width: 100%; background-color: #222; background-size: cover;  }
		.flowplayer.fp-controls {background-color: rgba(0, 0, 0, 0.4)}/* 소리 조절 보여주기*/
		.vod_header .top_inner .title {padding-left: 308px;}
		.vod_header {margin-top: -24px;}
</style>

<meditag:js src="/js/jquery/jquery-1.11.1.min.js"/>
<script src="/tpl/${COLOR_TPL}/js/sidebar.min.js"></script>
<meditag:js src="/js/jquery/jquery.form.js"/>
<meditag:js	src="/app/js/Context.js" />
<meditag:js src="/js/modaldialog.js"/>
<meditag:js src="/tpl/bootstrap/bower_components/bootstrap/dist/js/bootstrap.min.js"/>

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
<script src="/tpl/${COLOR_TPL}/js/pdfobject.js"></script>
<script src="/tpl/${COLOR_TPL}/js/jquery-resizable.js"></script>
<script src="/tpl/${COLOR_TPL}/js/codemirror/lib/codemirror.js"></script>
<script src="/tpl/${COLOR_TPL}/js/codemirror/mode/sql/sql.js"></script>
</head>
<body>
<iframe id='edutrackAPIFrame' name='edutrackAPIFrame' style="height:0;width:0;border:0;border:none;visibility:hidden;display:block;" src="<c:url value="/jsp/bookmark/study_edutrack_adapter.jsp"/>" onload="onloadApiFrame()"></iframe>
<c:if test="${not empty filePath}">

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
var review = '${review}';

$(document).ready(function() {

	/********** wide-inbox sidebar **********/
	$('.inbox-inquiry').sidebar({dimPage: false, closable: false, exclusive: true, context: '.vod_content'})
	.sidebar('attach events', '.inquiry-button', 'toggle')

	$('.inbox-lecture').sidebar({dimPage: false, closable: false, exclusive: true, context: '.vod_content'})
	.sidebar('attach events', '.lecture-button', 'toggle')
	
	var mediaStreamUse = '${mediaStreamUse}';
	var sourcesType = "video/mp4";
	
	<c:if test="${cntsTypeCd ne 'CDN'}">
		var sourcesSrc = "/contents${filePath}/${fileName}";
	</c:if>
	<c:if test="${cntsTypeCd eq 'CDN'}">
		var sourcesSrc = "${filePath}";
	</c:if>
	
	if(mediaStreamUse == "use"){
		sourcesType = "application/x-mpegurl";
		<c:if test="${mediaStreamHls ne ''}">
			sourcesType = "application/x-mpegurl";
			<c:if test="${cntsTypeCd ne 'CDN'}">
				sourcesSrc = "${mediaStreamUrl}${orgCntsPath}${filePath}/${fileName}/${mediaStreamHls}";
			</c:if>
		</c:if>
		<c:if test="${mediaStreamHls eq ''}">
		sourcesSrc = "${mediaStreamUrl}${orgCntsPath}${filePath}/${fileName}";
		</c:if>		

	}
   
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
		opener.listContents();
		self.close();
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

</script>
</c:if>



    <div class="coding_wrap">
        <div class="coding_header">
            <h1>${contentsVO.unitNm }</h1>
            <div class="right_util">
            	<button type="button" onclick="onunloadFunction();" class="btn">학습종료</button>
            </div>
        </div>
        <div class="panel-container">
            <div class="panel-left">
                <div class="panel-top">
                    <div id="player" ></div>
                </div>
                <div class="splitter-horizontal"></div>
                <div class="panel-bottom">
                    <div class="inner_top flex_end">
                        <a href="${contentsVO.atchFilePath }" download ><button type="button" class="btn">PDF 다운로드</button></a>
                    </div>
                    <div class="inner_cont">
                        <div id="pdf-viewer"></div>
                        <script>PDFObject.embed("${contentsVO.atchFilePath }", "#pdf-viewer");</script>
                    </div>
                </div>
            </div>
            <div class="splitter"></div>
            <div class="panel-right">
            	<iframe src="${studentVO.ideUrl }" style="height: 100%;"></iframe>
            </div>
        </div>
    </div>
    <script>
         $(".panel-left").resizable({
             handleSelector: ".splitter",
             resizeHeight: false
         });
         $(".panel-top").resizable({
             handleSelector: ".splitter-horizontal",
             resizeWidth: false
         });
    </script>
</body>
</html>