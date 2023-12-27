<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="egovframework.edutrack.Constants"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring"  uri="http://www.springframework.org/tags" %>
<html xmlns="http://www.w3.org/1999/xhtml" lang="<%=Constants.LANG_DEFAULT%>">

<head>
	<meta http-equiv="Expires" content="-1" />
    <meta http-equiv="Pragma" content="No-Cache" />
    <meta http-equiv="Cache-Control" content="No-Cache" />

    <meta http-equiv="X-UA-Compatible" content="IE=edge" />

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" href="/libs/flowplayer7/skin/skin.css">
	<link rel="stylesheet" href="/tpl/002/css/video.css"> 
	
	<style type="text/css" media="screen">
       div {
        width: 100%;
        height: 1000px;
        
        border: 1px solid #000;
	    }
	    div.left {
	        width: 50%;
	        float: left;
	        box-sizing: border-box;
	        
	        background: #4A4557;
	    }
	    div.right {
	        width: 50%;
	        float: right;
	        box-sizing: border-box;
	        
	        background: #4A4557;
	    }

        iframe {
            width:100%;
            height: 400px !important;
        }
        
        /* custom player skin */
		.flowplayer { width: 100%; background-color: #222; background-size: cover;  }
		.flowplayer.fp-controls {background-color: rgba(0, 0, 0, 0.4)}/* 소리 조절 보여주기*/
		
		video {
		    width: 100%;
		    height: 400px;
		    object-fit: fill;
		}
		
		
		.w-btn {
		    position: relative;
		    border: none;
		    display: inline-block;
		    padding: 15px 20px;
		    border-radius: 15px;
		    font-family: "paybooc-Light", sans-serif;
		    box-shadow: 0 15px 35px rgba(0, 0, 0, 0.2);
		    text-decoration: none;
		    font-weight: 600;
		    transition: 0.25s;
		}
		
		.w-btn-outline {
		    position: relative;
		    padding: 15px 30px;
		    border-radius: 15px;
		    font-family: "paybooc-Light", sans-serif;
		    box-shadow: 0 15px 35px rgba(0, 0, 0, 0.2);
		    text-decoration: none;
		    font-weight: 600;
		    transition: 0.25s;
		}
		
		.w-btn-indigo {
		    background-color: aliceblue;
		    color: #1e6b7b;
		}
		
		.w-btn-indigo-outline {
		    border: 3px solid aliceblue;
		    color: #1e6b7b;
		}
		
		.w-btn-indigo-outline:hover {
		    color: #1e6b7b;
		    background: aliceblue;
		}
		
		.w-btn-green {
		    background-color: #77af9c;
		    color: #d7fff1;
		}
		
		.w-btn-green2 {
		    background-color: #519d9e;
		    color: #9dc8c8;
		}
		
		.w-btn-green-outline {
		    border: 3px solid #77af9c;
		    color: darkgray;
		}
		
		.w-btn-green2-outline {
		    border: 3px solid #519d9e;
		    color: darkgray;
		}
		
		.w-btn-green-outline:hover {
		    background-color: #77af9c;
		    color: #d7fff1;
		}
		
		.w-btn-green2-outline:hover {
		    background-color: #519d9e;
		    color: #9dc8c8;
		}
		
		.w-btn-brown {
		    background-color: #ce6d39;
		    color: #ffeee4;
		}
		
		.w-btn-brown-outline {
		    border: 3px solid #ce6d39;
		    color: #b8b8b8;
		}
		
		.w-btn-brown-outline:hover {
		    background-color: #ce6d39;
		    color: #ffeee4;
		}
		
		.w-btn-blue {
		    background-color: #6aafe6;
		    color: #d4dfe6;
		}
		
		.w-btn-pink {
		    background-color: #f199bc;
		    color: #d4dfe6;
		}
		
		.w-btn-gray {
		    background-color: #a3a1a1;
		    color: #e3dede;
		}
		
		.w-btn-red {
		    background-color: #ff5f2e;
		    color: #e1eef6;
		}
		
		.w-btn-skin {
		    background-color: #f8e6e0;
		    color: #6e6e6e;
		}
		
		.w-btn-yellow {
		    background-color: #fce205;
		    color: #6e6e6e;
		}
		
		.w-btn-blue-outline {
		    border: 3px solid #6aafe6;
		    color: #6e6e6e;
		}
		
		.w-btn-pink-outline {
		    border: 3px solid #f199bc;
		    color: #6e6e6e;
		}
		
		.w-btn-gray-outline {
		    border: 3px solid #a3a1a1;
		    color: #6e6e6e;
		}
		
		.w-btn-red-outline {
		    border: 3px solid #ff5f2e;
		    color: #6e6e6e;
		}
		
		.w-btn-skin-outline {
		    border: 3px solid #f8e6e0;
		    color: #6e6e6e;
		}
		
		.w-btn-yellow-outline {
		    border: 3px solid #fce205;
		    color: #6e6e6e;
		}
		
		
		
		.w-btn-gra1 {
		    background: linear-gradient(-45deg, #33ccff 0%, #ff99cc 100%);
		    color: white;
		}
		
		.w-btn-gra2 {
		    background: linear-gradient(-45deg, #ee7752, #e73c7e, #23a6d5, #23d5ab);
		    color: white;
		}
		
		.w-btn-gra3 {
		    background: linear-gradient(
		        45deg,
		        #ff0000,
		        #ff7300,
		        #fffb00,
		        #48ff00,
		        #00ffd5,
		        #002bff,
		        #7a00ff,
		        #ff00c8,
		        #ff0000
		    );
		    color: white;
		}
		
		.w-btn-gra-anim {
		    background-size: 400% 400%;
		    animation: gradient1 5s ease infinite;
		}
		
		@keyframes gradient1 {
		    0% {
		        background-position: 0% 50%;
		    }
		    50% {
		        background-position: 100% 50%;
		    }
		    100% {
		        background-position: 0% 50%;
		    }
		}
		
		@keyframes gradient2 {
		    0% {
		        background-position: 100% 50%;
		    }
		    50% {
		        background-position: 0% 50%;
		    }
		    100% {
		        background-position: 100% 50%;
		    }
		}
		
		@keyframes ring {
		    0% {
		        width: 30px;
		        height: 30px;
		        opacity: 1;
		    }
		    100% {
		        width: 300px;
		        height: 300px;
		        opacity: 0;
		    }
		}
		
		.w-btn-neon2 {
		    position: relative;
		    border: none;
		    min-width: 200px;
		    min-height: 50px;
		    background: linear-gradient(
		        90deg,
		        rgba(129, 230, 217, 1) 0%,
		        rgba(79, 209, 197, 1) 100%
		    );
		    border-radius: 1000px;
		    color: darkslategray;
		    cursor: pointer;
		    box-shadow: 12px 12px 24px rgba(79, 209, 197, 0.64);
		    font-weight: 700;
		    transition: 0.3s;
		}
		
		.w-btn-neon2:hover {
		    transform: scale(1.2);
		}
		
		.w-btn-neon2:hover::after {
		    content: "";
		    width: 30px;
		    height: 30px;
		    border-radius: 100%;
		    border: 6px solid #00ffcb;
		    position: absolute;
		    z-index: -1;
		    top: 50%;
		    left: 50%;
		    transform: translate(-50%, -50%);
		    animation: ring 1.5s infinite;
		}
		
		textarea,
		.fake_textarea{
		  resize: none;
		  width: 95%;
		  height: 300px;
		  
		  border-radius: 4px;
		  border: 1px solid white;
		  
		  font-size: 15px;
		  font-weight: normal;
		  font-family: '굴림';
		  
		  overflow: hidden;
		  white-space: nowrap;
		  text-overflow: ellipsis;
		  display:inline-block;
		  white-space: pre-wrap;
    		background: #EEE;
		  
		}
		
		
    </style>
    <script src="/js/jquery/jquery-1.11.1.min.js" type="text/javascript" charset="utf-8"></script>
    <!-- 2. flowplayer -->
	<script src="/libs/flowplayer7/flowplayer.js"></script>
	<script src="/libs/flowplayer7/hls.light.min.js"></script>
	<script type="text/javascript" defer="defer">
		$(document).ready(function() {
				
			// 플로플레이어 인증키
			var flowerPlayerKey = '${flowplayerKey}';

			<%-- key: '<%=Constants.FLOWPLAYER_KEY%>', --%>
			flowplayer("#player",{
			  key: flowerPlayerKey,
		      clip: {
			    	autoplay: true,
			        sources: [
			            {
			                type:  "video/mp4",
							src: "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
			            }
			        ],
			    }
			});
			parent.playerType = "mp4";
			
			
			$("#language").change(function(){
				if($(this).val() == ""){
					alert("언어 선택은 필수입니다.");
				} else {
					$('#editor_frame').attr('src', '/monacoeditor.html?language='+$(this).val());
				}
             
            });
			
			
			
		});
		
		function submit(){
			alert("컴파일 기능은 준비중입니다.");
		}
		
		flowplayer(function(api, root) {
			/* // when a new video is about to be loaded
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
			}); */
		});
	</script>
</head>
<body>

<div>
        <div class="left">
        	<video style="width: 95%; text-align: center; margin-top: 100px; margin-left: 22px;" controls src="https://github.com/mediaelement/mediaelement-files/blob/master/big_buck_bunny.mp4?raw=true"></video>
        		<!-- <div id="player" style="overflow: hidden; height: 50%;"></div> -->
        </div>
        <div class="right">
        	<select id="language" style="width: 100px; margin-left: 30px; margin-top: 50px;">
        		<option value="">언어선택</option>
        		<option value="javascript">javascript</option>
        		<option value="java">java</option>
        	</select>
        	<iframe id="editor_frame" style="width: 95%; text-align: center; margin-top: 6px; margin-left: 22px;" src="/monacoeditor.html"></iframe>
        	<br/>
        	<button class="w-btn w-btn-brown" type="button" style="margin-left: 30px; margin-top: 6px;" onclick="submit();">
		        실행
		    </button>
        	<br/><br/>
        	<!-- <textarea  style="margin-left: 28px; margin-top: 10px;" rows="" cols="" >sdfsdf</textarea> -->
			  <div contenteditable class="fake_textarea" style="margin-left: 30px; background-color: white;">컴파일준비중</div>
        </div>
    </div>

</body>
</html>

<!-- 
Compilers API
token
a4ddb9e3ab7128ba6f73f7a1fdd0d19c
Endpoint
53b4078d.compilers.sphere-engine.com
API address
https://53b4078d.compilers.sphere-engine.com/api/v4

0. api 설명
https://docs.sphere-engine.com/compilers/api/overview-version-4#test__curl
https://docs.sphere-engine.com/compilers/api/overview-version-4#createSubmission
1. 엑세스토큰
https://53b4078d.compilers.sphere-engine.com/api/v4/test?access_token=a4ddb9e3ab7128ba6f73f7a1fdd0d19c
2. 지원되는 컴파일러 목록 반환
https://53b4078d.compilers.sphere-engine.com/api/v4/compilers?access_token=a4ddb9e3ab7128ba6f73f7a1fdd0d19c
3. 제출
https://53b4078d.compilers.sphere-engine.com/api/v4/submissions?access_token=a4ddb9e3ab7128ba6f73f7a1fdd0d19c


java 컴파일러아이디
10


Problems API
token
34290fc4d732f2dc9514010bde286d95
Endpoint
53b4078d.problems.sphere-engine.com
API address
https://53b4078d.problems.sphere-engine.com/api/v4


Containers API
token
2c333f1ffedf4b7eb8bb433e230475bb
Endpoint
53b4078d.containers.sphere-engine.com
API address
https://53b4078d.containers.sphere-engine.com/api/v1


https://53b4078d.containers.sphere-engine.com/api/v1/test?access_token=2c333f1ffedf4b7eb8bb433e230475bb
 -->