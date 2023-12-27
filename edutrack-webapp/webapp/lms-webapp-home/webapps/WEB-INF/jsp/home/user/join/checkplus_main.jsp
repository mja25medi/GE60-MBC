<%@ page language="java" contentType="text/html;charset=euc-kr" %>
<html>
<head>
	<title>NICE평가정보 - CheckPlus 안심본인인증 테스트</title>
</head>
<body>
	<!-- 본인인증 서비스 팝업을 호출하기 위해서는 다음과 같은 form이 필요합니다. -->
	<form name="form_chk" method="post">
		<input type="hidden" name="m" value="checkplusService">						<!-- 필수 데이타로, 누락하시면 안됩니다. -->
		<input type="hidden" name="EncodeData" value="${encData}">		<!-- 위에서 업체정보를 암호화 한 데이타입니다. -->
	    
		<script>
			sendForm();
			function sendForm() {
				var url = "https://nice.checkplus.co.kr/CheckPlusSafeModel/checkplus.cb";
				document.form_chk.action = url;
				window.form_chk.submit();
			}
		</script>
	</form>
</body>
</html>