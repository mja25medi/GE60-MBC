<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">
<%@ include file="../inc/home_common.jsp" %>

<link rel="stylesheet" href="../css/sub.css"><!-- sub 페이지에서 사용 -->

<body class="print_wrap">
    <div class="print_box">
        <!-- A4 사이즈에 감싸는 태그 -->
        <div class="QR_box">
            <!-- QR코드 위치 조정 태그 -->
            <div class="QR_cord">
                제 C02637-314769 호
            </div>
        </div>
        <!-- 상단에 표기될 다운로드/인쇄 아이콘 줄, 인쇄시 사라짐 -->
        <div class="icon_box">
            <i class="xi-download"></i>
            <i class="xi-print" onclick="window.print()"></i>
        </div>
        <img src="../img/contents/cer.png" alt="">
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
                                    <dd>홍길동</dd>
                                </dl>
                                <dl>
                                    <dt><span>생</span><span>년</span><span>월</span><span>일</span></dt>
                                    <dd>2000년 12월 25일</dd>
                                </dl>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2" class="txt">상기인은 「2022 K-디지털 핵심실무인재 양성사업 - 지능형 빅데이터 분석서비스 개발자과정(NCS-4회차)」을 수료(2022.12.07. ~ 2023.05.10. / 838h)하였기에 
                                이 증서를 드립니다.
                        </tr>
                        <tr>
                            <!-- 날짜 -->
                            <td colspan="2" class="date">2023년 5월 10일</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>