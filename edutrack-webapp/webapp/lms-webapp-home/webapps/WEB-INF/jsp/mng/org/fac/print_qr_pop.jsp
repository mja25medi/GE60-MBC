<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="img_base" value="/img/" />
<html>
<head>
	<title>수료증</title>
	<script type="text/javascript">
	<c:if test="${msie6 eq 'Y'}">
		function printWindow() {
	   		factory.printing.header = "";   //머릿말 설정
	   		factory.printing.footer = "";   //꼬릿말 설정
	   		factory.printing.portrait = true;     //출력방향 설정: true-가로, false-세로
	   		factory.printing.leftMargin = 10;    //왼쪽 여백 설정
	   		factory.printing.topMargin = 10;     //왼쪽 여백 설정
	   		factory.printing.rightMargin = 10;   //왼쪽 여백 설정
	   		factory.printing.bottomMargin = 10;  //왼쪽 여백 설정
	   		factory.printing.Print(true, window);
		}
	</c:if>
	<c:if test="${msie6 ne 'Y'}">
		function printWindow() {
			window.print();
		}
	</c:if>
	</script>
	<style type="text/css">
 		@page {
    		size:21cm 29.7cm; /*A4*/
    		margin:0;
		}
		html, body {
			border:0;
			margin:0;
			padding:20 15 10 15;
			/* font-family:바탕, 궁서, AppleMyungjo; */
		}
</style>



</head>
<c:if test="${msie6 eq 'Y'}">
<!-- 프린트용 오브젝트(body tag 안에서 삽입시킴)  -->
<object id="factory" viewastext  style="display:none"
        classid="clsid:1663ed61-23eb-11d2-b92f-008048fdd814"
        codebase="/libs/smsx.cab#Version=6,5,439,30">
</object>
</c:if>
<body bgcolor="#FFFFFF" text="#000000" onLoad="printWindow()">
<center>
<table border=0 cellpadding=0 cellspacing=0 align="center" >
	<tr>
		<td><img src="${img_base}/common/qr_background.png" width="100%"></td>
	</tr>
</table>
</center>
	<div id="table" style="position:absolute;left:299px;top:610px;">
		<table border="0" style="width:190px;">
			<td><img src="${img_base}/common/${imgName}" width="100%"></td>
		</table>
	</div>
</body>
</html>