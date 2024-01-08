<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">
<%@ include file="../inc/class_common.jsp" %>
<script src="../js/iframeResizer.contentWindow.min.js"></script>
<body class="scroll_custom">
    <div class="modal_cont">
        <div class="info_box">
            <div class="title">시험응시 유의사항 안내</div>
            <ol class="text">
                <li><span class="num">1.</span>시험 최초 응시 시간부터 1시간 이내 제출하여야 합니다.</li>
                <li><span class="num">2.</span>시험 응시 중 제한 시간이 종료될 경우 작성한 답안으로 자동 제출되며, 수정 및 재응시 불가합니다.</li>
                <li class="fcRed"><span class="num">3.</span>시험은 총 3회까지 재응시가 가능하며 임시저장 후 닫을 경우 재응시가 자능하지만 제출하기를 할 경우 재응시가 불가하므로 신중하게 응시해주시기 바랍니다.</li>
                <li class="fcRed"><span class="num">4.</span>시험 응시 시 비정상 제출된 경우 반드시 HRD팀을 연락 주시기 바랍니다.</li>
            </ol>
        </div>
        <div class="custom-input mt30">
            <input type="checkbox" name="agree1" id="agree1Y">
            <label for="agree1Y">위 시험응시 약관에 동의합니다.</label>
        </div>
    </div>
    <div class="modal_btns">
        <button type="button" class="btn">응시취소</button>
        <button type="button" class="btn type2">다음으로</button>
    </div>
</body>
</html>