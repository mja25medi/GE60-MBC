<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
<link rel="apple-touch-icon-precomposed" sizes="57x57" href="../img/common/apple-touch-icon-57x57.png">
<link rel="icon" type="image/png" href="/img/common/favicon-16x16.png" sizes="16x16">
<link rel="stylesheet" href="/tpl/003/css/hrd_common.css">
<link rel="stylesheet" href="/tpl/003/css/webfonts.css">
<link rel="stylesheet" href="/tpl/003/css/board.css">
<link rel="stylesheet" href="/tpl/003/css/contents.css">
<link rel="stylesheet" href="/tpl/003/css/class_layout.css">
<link rel="stylesheet" href="/tpl/003/css/class_content.css">

<script src="/tpl/003/jquery/jquery-3.2.1.min.js"></script>
<script src="/tpl/003/js/func.min.js"></script>
<script src="/tpl/003/js/class.js"></script>
<script src="/tpl/003/js/iframeResizer.min.js"></script>

<script src="/app/js/Context.js"></script>
<script type="text/javascript">
	var localeKey = "${LOCALEKEY}"; // javascript용 localeKey 전역 변수 설정
	var MENUCODE = "menu_${MENUCODE}";
	var API_1484_11 = null;
	var edutrackAPI = null;
</script>
<script src="/js/common.js"></script>
<script src="/js/common_conf.js"></script>
<script src="/js/common_util.js"></script>
<script src="/js/common_function.js"></script>

<!-- jQuery	-->
<script src="/tpl/003/jquery/jquery.form.js"></script>

<script src="/js/jquery/jquery-1.11.1.min.js"></script>
<script src="/js/jquery/jquery-ui-1.11.0.custom/jquery-ui.min.js"></script>
<script src="/js/jquery/jquery-custom/jquery.input-1.0.js"></script>
<script src="/js/jquery/jquery.ui.touch-punch.min.js"></script>

<title>:: jsQR 테스트 ::</title>

<!-- 다운받은 jsQR.js 를 사용한다. -->

<script type="text/javascript" src="/js/jsQR.js"></script>


</head>
<c:set var="gubun" value="${gubun }"/>

<body class="scroll_custom">
    <div class="modal_cont">
    <div id="output" style="text-align: center;">
	    <div id="outputMessage">
			No QR code detected.
		</div>
	</div>
    <div style="text-align: center;">
        <canvas id="canvas"></canvas>
    </div>
        <div class="enter_box">
            <div id="loadingMessage" class="disc"></div>
            <input type="password" id="qNum_1" maxlength="1" class="form-control">
            <input type="password" id="qNum_2" maxlength="1" class="form-control">
            <input type="password" id="qNum_3" maxlength="1" class="form-control">
            <input type="password" id="qNum_4" maxlength="1" class="form-control">
        </div>
    </div>
    <div class="modal_btns">
    	<c:choose>
				<c:when test="${gubun eq 'enter'}">
			 		<button class="btn type2" type="button" onclick="enterClass()">출석하기</button>
			 	</c:when>
			 	<c:when test="${gubun eq 'quit' }">
			 		<button class="btn type2" type="button" onclick="quitClass()">퇴실하기</button>
			 	</c:when>
		    </c:choose>
    </div>
</body>

<script type="text/javascript">	

	document.addEventListener("DOMContentLoaded", function() {
		var video = document.createElement("video");		
		var canvasElement = document.getElementById("canvas");
		var canvas = canvasElement.getContext("2d");
		var loadingMessage = document.getElementById("loadingMessage");
		var outputContainer = document.getElementById("output");
		var outputMessage = document.getElementById("outputMessage");

		function drawLine(begin, end, color) {
			canvas.beginPath();
			canvas.moveTo(begin.x, begin.y);
			canvas.lineTo(end.x, end.y);
			canvas.lineWidth = 4;
			canvas.strokeStyle = color;
			canvas.stroke();
        }
		
       	// 카메라 사용시
		navigator.mediaDevices.getUserMedia({ video: { facingMode: "environment" } }).then(function(stream) {
 		        video.srcObject = stream;
 		        video.setAttribute("playsinline", true);      // iOS 사용시 전체 화면을 사용하지 않음을 전달
     			video.play();
 		        requestAnimationFrame(tick);
		});

		function tick() {
			loadingMessage.innerText = "⌛ 스캔 기능을 활성화 중입니다."

			if(video.readyState === video.HAVE_ENOUGH_DATA) {
	 		      loadingMessage.hidden = true;
	 		      canvasElement.hidden = false;
	 		      outputContainer.hidden = true;
	
	 		      // 읽어들이는 비디오 화면의 크기
	 		      canvasElement.height = video.videoHeight;
	 	 	      canvasElement.width = video.videoWidth;
	
	 		      canvas.drawImage(video, 0, 0, canvasElement.width, canvasElement.height);
	 	
	 		      var imageData = canvas.getImageData(0, 0, canvasElement.width, canvasElement.height);
	 		      var code = jsQR(imageData.data, imageData.width, imageData.height, {
	                             inversionAttempts : "dontInvert",
 		      });


          // QR코드 인식에 성공한 경우
          if(code) {
                 // 인식한 QR코드의 영역을 감싸는 사용자에게 보여지는 테두리 생성
                drawLine(code.location.topLeftCorner, code.location.topRightCorner, "#FF0000");
                drawLine(code.location.topRightCorner, code.location.bottomRightCorner, "#FF0000");
                drawLine(code.location.bottomRightCorner, code.location.bottomLeftCorner, "#FF0000");
                drawLine(code.location.bottomLeftCorner, code.location.topLeftCorner, "#FF0000");

               	$.getJSON( 
               		cUrl(code.data), 
               		function(data) {
				 	  	if(data.result >= 0) {
				 	  		alert(data.message);
				 	  		window.opener.location.reload();
				 	  		window.close();
						} else {
							alert(data.message);
							window.opener.location.reload();
							window.close();
						}
               		}
               	);
                // return을 써서 함수를 빠져나가면 QR코드 프로그램이 종료된다.
                return;
          }
          // QR코드 인식에 실패한 경우 
          else {
        	  outputMessage.hidden = false;
          }

      }
	      requestAnimationFrame(tick);
	}

	});
	
function enterClass(){
	if($("#qNum_1").val() == null || $("#qNum_1").val() == "" ||
		$("#qNum_2").val() == null || $("#qNum_2").val() == "" ||
		$("#qNum_3").val() == null || $("#qNum_3").val() == "" || 
		$("#qNum_4").val() == null || $("#qNum_4").val() == "" ) {
		alert("올바르지 않은 번호입니다.")
		return false;
	} 
	var qrNum = $("#qNum_1").val() + $("#qNum_2").val() + $("#qNum_3").val() + $("#qNum_4").val()
	$.getJSON( 
		cUrl("/lec/attend/enterClass"),
		{ "qrNum" : qrNum },
 		function(data) {
	 	  	if(data.result >= 0) {
	 	  		alert(data.message);
	 	  		window.opener.location.reload();
	 	  		window.close();
			} else {
				alert(data.message);
				window.opener.location.reload();
				window.close();
			}
   		}
   	);
}

</script>