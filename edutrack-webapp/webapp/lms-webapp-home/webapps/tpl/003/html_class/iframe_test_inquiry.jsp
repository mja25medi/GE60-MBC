<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">
<%@ include file="../inc/class_common.jsp" %>
<script src="../js/iframeResizer.contentWindow.min.js"></script>
<body class="scroll_custom">
    <div class="modal_cont">
        <div class="board_top">
            <h4>반영율</h4>
        </div>
        <div class="table_list">
            <ul class="list">
                <li class="head"><label>과정명</label></li>
                <li>데이터 사이언스</li>
            </ul>
            <ul class="list">
                <li class="head"><label>진도</label></li>
                <li>20%</li>
                <li class="head"><label>시험</label></li>
                <li>50%</li>
            </ul>
            <ul class="list">
                <li class="head"><label>과제</label></li>
                <li>10%</li>
                <li class="head"><label>진행단계</label></li>
                <li>20%</li>
            </ul>
            <ul class="list">
                <li class="head"><label>합계</label></li>
                <li><div class="total">100%</div></li>
            </ul>
        </div>
        <div class="board_top mt30">
            <h4>성적</h4>
        </div>
        <div class="table_list">
            <ul class="list">
                <li class="head"><label>수강생명</label></li>
                <li>학습자1</li>
            </ul>
            <ul class="list">
                <li class="head"><label>진도</label></li>
                <li>100.0%</li>
                <li class="head"><label>시험</label></li>
                <li>10.0점</li>
            </ul>
            <ul class="list">
                <li class="head"><label>과제</label></li>
                <li>10.0점</li>
                <li class="head"><label>진행단계</label></li>
                <li>0.0점</li>
            </ul>
            <ul class="list">
                <li class="head"><label>총점</label></li>
                <li><div class="total">20.0점</div></li>
            </ul>
        </div>
        <ul class="message_box mt10">
            <li>필수 수료기준 : <span class="fcRed">진도율 80% 이상</span>, 환산 수료 점수 <span class="fcRed">60점 이상</span></li>
        </ul>
    </div>
    <div class="modal_btns">
        <button type="button" class="btn">닫기</button>
    </div>
</body>
</html>