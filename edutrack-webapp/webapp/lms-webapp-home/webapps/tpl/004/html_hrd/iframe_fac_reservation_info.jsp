<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">
<%@ include file="../inc/home_common.jsp" %>
<script src="../js/iframeResizer.contentWindow.min.js"></script>
<link rel="stylesheet" href="../css/sub.css"><!-- sub 페이지에서 사용 -->

<body class="scroll_custom">
    <div class="modal_cont">
        <div class="board_top">
            <h4>예약 정보</h4>
        </div>
        <div class="table_list">
            <ul class="list">
                <li class="head"><label>예약시설</label></li>
                <li>설문명이 표시 됩니다.</li>
            </ul>
            <ul class="list">
                <li class="head"><label>진행상태</label></li>
                <li><label class="btn3 sm solid fcDarkgray">관리자 승인대기</label></li>
            </ul>
            <ul class="list">
                <li class="head"><label>예약날짜</label></li>
                <li>2023년 2월20일 11:00 ~ 12:00</li>
            </ul>
            <ul class="list">
                <li class="head"><label>사용목적</label></li>
                <li>
                    * 사용목적 문구 표시 됩니다.<br>
                    * 사용목적 문구 표시 됩니다.<br>
                    * 사용목적 문구 표시 됩니다.<br>
                </li>
            </ul>
            <ul class="list">
                <li class="head"><label>사용인원</label></li>
                <li>10명</li>
            </ul>
        </div>
      
    </div>
    <div class="modal_btns">
        <button type="button" class="btn type2">취소</button>
        <button type="button" class="btn type4">예약취소</button>
    </div>
</body>
</html>