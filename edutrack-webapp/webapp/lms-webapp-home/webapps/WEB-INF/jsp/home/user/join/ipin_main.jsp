<%@ page contentType="text/html;charset=euc-kr" %> 

<html>
<head>
	<meta charset="EUC-KR">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>NICE������ �����ֹι�ȣ ����</title>	
</head>
<body>
	<form name="form_ipin" method="post">
		<input type="hidden" name="m" value="pubmain">	
		<input type="hidden" name="enc_data" value="${encData}">		
	</form>
	
	<script>
		sendForm();
		function sendForm() {
			var url = "https://cert.vno.co.kr/ipin.cb";
			document.form_ipin.action = url;
			window.form_ipin.submit();
		}

	</script>
</body>
</html>