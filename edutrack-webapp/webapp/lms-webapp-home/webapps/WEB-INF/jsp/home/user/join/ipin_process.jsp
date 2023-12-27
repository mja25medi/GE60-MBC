<%@ page contentType="text/html;charset=euc-kr" %>

<html>
<head>
	<meta charset="EUC-KR">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>NICE평가정보 가상주민번호 서비스</title>
</head>
<body>
	<form name="vnoform" method="post">
		<input type="hidden" name="enc_data">								
	</form>
	<script>
		fnLoad();
		function fnLoad()
		{
			// 귀사의 환경 및 프로세스에 맞게 정의하시기 바랍니다
			document.vnoform.enc_data.value = "${responseData}";
			
			// 인증결과 데이터를 최종 수신하는 결과페이지 URL (형식:절대주소, 필수항목:프로토콜) 
			document.vnoform.action ="${resultUrl}";
			window.vnoform.submit();
		}
	</script>
</body>
</html>