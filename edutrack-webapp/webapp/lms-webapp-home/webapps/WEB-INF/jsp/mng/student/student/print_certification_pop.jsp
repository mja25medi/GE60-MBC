<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="img_base" value="/tpl/003/img/" />
<html>
<head>
	<title>수료증</title>
	<link rel="stylesheet" href="/tpl/003/css/hrd_common.css">
	<link rel="stylesheet" href="/tpl/003/css/sub.css">
	<link rel="stylesheet" href="/tpl/003/css/contents.css">
	<link rel="stylesheet" href="/tpl/003/fonts/xeicon.css">
	
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
    		padding: 0;
		}

	.print_wrap .print_box .table_wrap .table_box {position: absolute; top:5%; height: 80%;}
	.print_wrap .print_box .icon_box {width: 882px;}
	
	</style>
</head>
<%-- <c:if test="${msie6 eq 'Y'}">
<!-- 프린트용 오브젝트(body tag 안에서 삽입시킴)  -->
<object id="factory" viewastext  style="display:none"
        classid="clsid:1663ed61-23eb-11d2-b92f-008048fdd814"
        codebase="/libs/smsx.cab#Version=6,5,439,30">
</object>
</c:if>
<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onLoad="printWindow()">
<center>
<table border=0 cellpadding=0 cellspacing=0 align="center">
	<tr>
		<td><img src="${img_base}/common/${codeVO.codeOptn}" width="95%"></td>
	</tr>
</table>
</center>
	<div id="certNo" style="position:absolute;left:80px;top:136px;">
		<span style="font-size:11pt;font-family:바탕, 궁서, AppleMyungjo;"><strong>${studentVO.cpltNo}</strong></span>
	</div>
	<div id="table" style="position:absolute;left:120px;top:310px;">
		<table border=0 cellpadding=0 cellspacing=0 width="540px">
			<tr>
				<td style="padding-right:20px;padding-top:10px"><span style="font-size:18pt;font-family:궁서, 바탕, AppleMyungjo;"><strong>센&nbsp;&nbsp;&nbsp;&nbsp;터</strong></span></td>
				<td style="padding-left:20px;padding-top:10px"><span style="font-size:18pt;font-family:궁서, 바탕, AppleMyungjo;"><strong>${studentVO.deptOrgNm}&nbsp;${studentVO.deptDeptNm}</strong></span></td>
			</tr>
			<tr>
				<td style="padding-right:20px;padding-top:10px"><span style="font-size:18pt;font-family:궁서, 바탕, AppleMyungjo;"><strong>직&nbsp;&nbsp;&nbsp;&nbsp;무</strong></span></td>
				<td style="padding-left:20px;padding-top:10px"><span style="font-size:18pt;font-family:궁서, 바탕, AppleMyungjo;"><strong>${studentVO.posngNm}</strong></span></td>
			</tr>
			<tr>
				<td style="padding-right:20px;padding-top:10px"><span style="font-size:18pt;font-family:궁서, 바탕, AppleMyungjo;"><strong>성&nbsp;&nbsp;&nbsp;&nbsp;명</strong></span></td>
				<td style="padding-left:20px;padding-top:10px"><span style="font-size:18pt;font-family:궁서, 바탕, AppleMyungjo;"><strong>${studentVO.userNm}</strong></span></td>
			</tr>
			<tr>
				<td style="padding-right:20px;padding-top:10px"><span style="font-size:18pt;font-family:궁서, 바탕, AppleMyungjo;"><strong>과&nbsp;&nbsp;&nbsp;&nbsp;정</strong></span></td>
				<td style="padding-left:20px;padding-top:10px"><span style="font-size:18pt;font-family:궁서, 바탕, AppleMyungjo;"><strong>${createCourseVO.crsCreNm}</strong></span></td>
			</tr>
			<tr>
				<td style="padding-right:20px;padding-top:10px"><span style="font-size:18pt;font-family:궁서, 바탕, AppleMyungjo;"><strong>기&nbsp;&nbsp;&nbsp;&nbsp;간</strong></span></td>
				<td style="padding-left:20px;padding-top:10px"><span style="font-size:18pt;font-family:궁서, 바탕, AppleMyungjo;"><strong>${createCourseVO.enrlStartDttm} ~ ${createCourseVO.enrlEndDttm}</strong></span></td>
			</tr>
			<tr>
				<td style="padding-right:20px;padding-top:10px"><span style="font-size:18pt;font-family:궁서, 바탕, AppleMyungjo;"><strong>&nbsp;&nbsp;&nbsp;&nbsp;</strong></span></td>
				<td style="padding-left:20px;padding-top:10px"><span style="font-size:16pt;font-family:궁서, 바탕, AppleMyungjo;"><strong><c:if test="${not empty createCourseVO.eduCertTm}">(교육인정시간 : ${createCourseVO.eduCertTm} 시간<c:if test="${courseVO.scorePrnYn eq 'Y'}">, 점수 : ${studentVO.totScore}점</c:if>)</c:if></strong></span></td>
			</tr>
		</table>
	</div>
	<div id="date" style="position:absolute;left:420px;top:800px;">
		<span style="font-size:16pt;font-family:궁서, 바탕, AppleMyungjo;"><strong>${year}년 ${month}월 ${day}일</strong></span>
	</div>
</body> --%>

<body class="print_wrap" >
	<div class="print_box">
    <!-- A4 사이즈에 감싸는 태그 -->
        <div class="QR_box">
            <!-- QR코드 위치 조정 태그 -->
            <div class="QR_cord">
                제 ${studentVO.cpltNo} 호
            </div>
        </div>
        <!-- 상단에 표기될 다운로드/인쇄 아이콘 줄, 인쇄시 사라짐 -->
        <div class="icon_box">
            <!-- <i class="xi-download"></i> -->
            <i class="xi-print" onclick="window.print()"></i>
        </div>
        <img src="${img_base}/contents/cer.png" alt="수료증 배경">
        <div class="table_wrap">
            <div class="table_box">
                <table>
                    <caption>수료정보</caption>
                    <colgroup>
                        <col>
                        <col>
                    </colgroup>
                    <tbody> 
                        <tr>
                            <td colspan="2" class="user">
                                <dl>
                                    <dt><span>성</span><span>명</span></dt>
                                    <dd>${vo.userNm}</dd>
                                </dl>
                                <dl>
                                    <dt><span>생</span><span>년</span><span>월</span><span>일</span></dt>
                                    <dd>${fn:substring(vo.birth,0,4)}년 ${fn:substring(vo.birth,5,6)}월 ${fn:substring(vo.birth,7,8)}일</dd>
                                </dl>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2" class="txt">상기인은 「${createCourseVO.crsCreNm}」을 수료(${createCourseVO.enrlStartDttm} ~ ${createCourseVO.enrlEndDttm} / ${createCourseVO.eduTm}h)하였기에 
                                이 증서를 드립니다.
                        </tr>
                        <tr>
                            <!-- 날짜 -->
                            <td colspan="2" class="date">${year}년 ${month}월 ${day}일</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
 
    </div>
</body>
</html>