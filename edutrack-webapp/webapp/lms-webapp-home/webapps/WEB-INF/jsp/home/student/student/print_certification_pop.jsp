<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="img_base" value="/tpl/003/img/" />
<html>
<head>
	<title>수료증</title>
	<link rel="stylesheet" href="/tpl/003/css/hrd_common.css">
	<link rel="stylesheet" href="/tpl/003/css/sub.css">
	<link rel="stylesheet" href="/tpl/003/css/contents.css">
	
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

		.print_wrap .print_box > img { width: 21cm!important; height: 29.7cm!important; }
		.print_wrap .print_box .table_wrap {position:absolute; top:0; width: 21cm!important; height: 29.7cm!important; }
		
	</style>


</head>
<%-- <c:if test="${msie6 eq 'Y'}">
<!-- 프린트용 오브젝트(body tag 안에서 삽입시킴)  -->
<object id="factory" viewastext  style="display:none"
        classid="clsid:1663ed61-23eb-11d2-b92f-008048fdd814"
        codebase="/libs/smsx.cab#Version=6,5,439,30">
</object>
</c:if> --%>
<%-- <body bgcolor="#FFFFFF" text="#000000" onLoad="printWindow()">
<center>
<table border=0 cellpadding=0 cellspacing=0 align="center" style="margin-top:10px;">
	<tr>
		<td>
			<!-- 기본배경화면 주석 -->
			<img src="${img_base}/common/certification_default.jpg" width="95%">
		</td>
	</tr>
</table>
</center>
	<div id="certNo" style="position:absolute;left:80px;top:140px;">
		<span style="font-size:12pt;"><spring:message code="org.title.certificate.cpltno" arguments="${vo.cpltNo}"/></span>
	</div>
	<div id="title" style="position:absolute;left:350px;top:210px;">
		<span style="font-size:40pt;font-weight:bold">수           료           증</span>
	</div>
	<div id="table" style="position:absolute;left:160px;top:350px;">
		<table border="0" style="width:750px;">
			<colgroup>
				<col style="width:150px;"/>
				<col style="width:auto"/>
			</colgroup>
			<tr style="height: 50px;">
				<td><span style="font-size:20pt;font-weight:bold"><spring:message code="org.title.certificate.username"/></span></td>
				<td><span style="font-size:20pt;font-weight:bold">: ${vo.userNm}</span></td>
			<tr>
			<tr style="height: 50px;">
				<td><span style="font-size:20pt;font-weight:bold"><spring:message code="org.title.certificate.birthday"/></span></td>
				<td><span style="font-size:20pt;font-weight:bold">: ${vo.birth}</span></td>
			</tr>
			<c:choose>
				<c:when test="${fn:length(createCourseVO.crsCreNm) < 20}">
					<tr style="height: 50px;">
						<td><span style="font-size:20pt;font-weight:bold"><spring:message code="org.title.certificate.hrd.course"/></span></td>
						<td><span style="font-size:20pt;font-weight:bold">: ${createCourseVO.crsCreNm}</span></td>
					</tr>
				</c:when>
				<c:otherwise>
					<tr style="height: 50px;">
						<td><span style="font-size:20pt;font-weight:bold"><spring:message code="org.title.certificate.hrd.course"/></span></td>
						<td><span style="font-size:20pt;font-weight:bold">: ${fn:substring(createCourseVO.crsCreNm,0,20)}</span></td>
					</tr>
					<tr style="height: 50px;">
						<td><span style="font-size:20pt;font-weight:bold"></span></td>
						<td><span style="font-size:20pt;font-weight:bold">  ${fn:substring(createCourseVO.crsCreNm,20,fn:length(createCourseVO.crsCreNm))}</span></td>
					</tr>
				</c:otherwise>
			</c:choose>
			<tr style="height: 50px;">
				<td><span style="font-size:20pt;font-weight:bold"><spring:message code="org.title.certificate.hrd.course.type"/></span></td>
				<td><span style="font-size:20pt;font-weight:bold">: ${createCourseVO.sbjCtgrNm}</span></td>
			</tr>
			<tr style="height: 50px;">
				<td><span style="font-size:20pt;font-weight:bold"><spring:message code="org.title.certificate.hrd.period"/></span></td>
				<td><span style="font-size:18pt;font-weight:bold">: ${createCourseVO.enrlStartDttm} ~ ${createCourseVO.enrlEndDttm} / <spring:message code="org.title.certificate.hrd.time" arguments="${createCourseVO.eduTm}"/></span></td>
			</tr>
		</table>
	</div>
	<div id="ment" style="position:absolute;left:120px;top:750px;">
		<table border="0" style="width:700px;">
			<tr style="height: 80px;text-align: center;">
				<td>
					<span style="font-size:20pt;font-weight:bold;line-height:1.0em">
						<strong>
							위 사람은 메디오피아테크에서 실시하는 훈련을
						</strong> 
					</span>
				</td>
			</tr>
			<tr style="height: 80px;text-align: center;">
				<td>
					<span style="font-size:20pt;font-weight:bold;line-height:1.0em">
						<strong>
							 위와 같이 수료하였으므로 이 증서를 수여합니다.
						</strong> 
					</span>
				</td>
			</tr>
		</table>
	</div>
	<div id="date" style="position:absolute;left:360px;top:1050px;">
		<span style="font-size:18pt;"><strong>${year}년 ${month}월 ${day}일</strong></span>
	</div>
	<div id="deptNm" style="position:absolute;left:280px;top:1100px;">
		<span style="font-size:20pt;font-weight:bold;">메디오피아테크</span>
	</div>
	<div id="sign" style="position:absolute;left:650px;top:1060px;">
		<span style="height: 100px;"><img src="${img_base}/common/mark2.png" width="100px"> </span>
	</div>
</body> --%>

<body class="print_wrap" onLoad="printWindow()" >
	<div class="print_box">
    <!-- A4 사이즈에 감싸는 태그 -->
        <div class="QR_box">
            <!-- QR코드 위치 조정 태그 -->
            <div class="QR_cord">
                제 ${vo.cpltNo} 호
            </div>
        </div>
        <!-- 상단에 표기될 다운로드/인쇄 아이콘 줄, 인쇄시 사라짐 -->
<!--         <div class="icon_box">
            <i class="xi-download"></i>
            <i class="xi-print" onclick="window.print()"></i>
        </div> -->
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