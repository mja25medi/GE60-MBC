<%@ page contentType="text/html;charset=euc-kr" %>

<html>
<head>
	<meta charset="EUC-KR">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>NICE������ �����ֹι�ȣ ����</title>
</head>
<body>
	<form name="vnoform" method="post">
		<input type="hidden" name="enc_data">								
	</form>
	<script>
		fnLoad();
		function fnLoad()
		{
			// �ͻ��� ȯ�� �� ���μ����� �°� �����Ͻñ� �ٶ��ϴ�
			document.vnoform.enc_data.value = "${responseData}";
			
			// ������� �����͸� ���� �����ϴ� ��������� URL (����:�����ּ�, �ʼ��׸�:��������) 
			document.vnoform.action ="${resultUrl}";
			window.vnoform.submit();
		}
	</script>
</body>
</html>