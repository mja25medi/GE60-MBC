<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="img_base" value="/img/" />
<html>
<head>
	<title>자격증</title>
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
			font-family:바탕, 궁서, AppleMyungjo;
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
<table border=0 cellpadding=0 cellspacing=0 align="center" style="margin-top:10px;">
	<tr>
		<td><img src="${img_base}/common/certification_default.jpg" width="95%"></td>
	</tr>
</table>
</center>
	<div id="certNo" style="position:absolute;left:80px;top:140px;">
		<span style="font-size:12pt;">제 <strong>${vo.cpltNo}</strong> 호</span>
	</div>
	<div id="table" style="position:absolute;left:250px;top:210px;">
		<span style="font-size:40pt;font-weight:bold">자 격 증</span>
	</div>
	<div id="table" style="position:absolute;left:140px;top:350px;">
		<table border="0" style="width:450px;">
			<colgroup>
				<col style="width:100px;"/>
				<col style="width:auto"/>
			</colgroup>
			<tr>
				<td><span style="font-size:20pt;font-weight:bold">이 름</span></td>
				<td><span style="font-size:20pt;font-weight:bold">: ${vo.userNm}</span></td>
			<tr>
			<tr>
				<td><span style="font-size:20pt;font-weight:bold">과 정</span></td>
				<td><span style="font-size:20pt;font-weight:bold">: ${createCourseVO.crsCreNm} [${courseVO.crsNm}]</span></td>
			</tr>
		</table>
	</div>
	<div id="table" style="position:absolute;left:115px;top:500px;">
		<table border="0" style="width:500px;">
			<tr>
				<td>
					<span style="font-size:30pt;font-weight:bold;line-height:1.7em">
					위의 과정을 수료 하였음을 확인 합니다.
					</span>
				</td>
			</tr>
		</table>
	</div>
	<div id="date" style="position:absolute;left:420px;top:700px;">
		<span style="font-size:18pt;"><strong>${year}년 ${month}월 ${day}일</strong></span>
	</div>
	<div id="date" style="position:absolute;left:180px;top:810px;">
		<span style="font-size:40pt;font-weight:bold;">메디오피아테크</span>
	</div>
</body>
</html>