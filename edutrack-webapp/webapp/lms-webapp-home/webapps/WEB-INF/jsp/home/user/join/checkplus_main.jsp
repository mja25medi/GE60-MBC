<%@ page language="java" contentType="text/html;charset=euc-kr" %>
<html>
<head>
	<title>NICE������ - CheckPlus �Ƚɺ������� �׽�Ʈ</title>
</head>
<body>
	<!-- �������� ���� �˾��� ȣ���ϱ� ���ؼ��� ������ ���� form�� �ʿ��մϴ�. -->
	<form name="form_chk" method="post">
		<input type="hidden" name="m" value="checkplusService">						<!-- �ʼ� ����Ÿ��, �����Ͻø� �ȵ˴ϴ�. -->
		<input type="hidden" name="EncodeData" value="${encData}">		<!-- ������ ��ü������ ��ȣȭ �� ����Ÿ�Դϴ�. -->
	    
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