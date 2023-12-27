<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">
<%@ include file="../inc/home_common.jsp" %>
<script src="../js/iframeResizer.contentWindow.min.js"></script>
<link rel="stylesheet" href="../css/sub.css"><!-- sub 페이지에서 사용 -->

<body class="scroll_custom">
    <div class="modal_cont">
        <div class="board_top">
            <h4>환불 안내</h4>
        </div>
        <table class="tbl w100">
            <colgroup>
                <col width="15%">
                <col width="15%">
                <col width="40%">
                <col width="30%">
            </colgroup>
            <thead>
                <tr>
                    <th colspan="2">반환 사유</th>
                    <th>반환 사유 발생 시점</th>
                    <th>반환금액</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td colspan="2">평생교육법 제28조 제4항 제1호 및<br> 제2호에 해당하는 경우</td>
                    <td>수업을 할 수 없거나, 수업 장소를 제공할 수 없을 때</td>
                    <td>이미 낸 학습비를 일 단위로 계산한 금액</td>
                </tr>
                <tr>
                    <td rowspan="6">평생교육법<br>제28조 제4항<br>제3호에 해당하는 경우</td>
                    <td rowspan="4">학습기간이 1개월<br>이내인 경우</td>
                    <td>수업시작 전일까지</td>
                    <td>학습비 전액</td>
                </tr>
                <tr>
                    <td class="line-left">수업 시작일로부터<br>총 수업시간의 1/3경과 전</td>
                    <td>학습비의 3분의 2에 해당하는 금액</td>
                </tr>
                <tr>
                    <td class="line-left">총 수업시간의 1/3이상부터<br>1/2 미만까지의 기간 동안</td>
                    <td>학습비의 1분의 2에 해당하는 금액</td>
                </tr>
                <tr>
                    <td class="line-left">총 수업시간의 1/2이상 경과</td>
                    <td>반환하지 않음</td>
                </tr>
                <tr>
                    <td rowspan="2">학습기간이<br>1개월 초과인 경우</td>
                    <td>수업시작 전일까지</td>
                    <td>학습비 전액</td>
                </tr>
                <tr>
                    <td>수업시작 이후</td>
                    <td>반환사유가 발생한 그 달의 반환<br>대상 학습비와 나머지 달의 학습비<br>전액을 합산한 금액</td>
                </tr>                                    
            </tbody>
        </table>
        <div class="mb40">                           
            <span><i class="xi-error mr5" aria-hidden="true"></i> (단. 원격교육의 경우 학습비 반환금액은 이미 낸 학습비에서 실제 학습한 부분에 해당하는 학습비를 뺀 금액으로 함)</span><br>
            <span><i class="xi-error mr5" aria-hidden="true"></i>(수강신청 및 결제 완료 이후 수강변경을 희망할 경우 수강 취소 및 환불 진행 후 진행하여야 함)</span>
        </div>

        <div class="board_top">
            <h4>수강취소 규정 및 환불안내</h4>
        </div>
        <ul class="list-dot">
            <li>학습비 반환은 『평생교육법 시행령』 학습비 반환기준 제23조에 따라 처리됩니다.</li>
            <li>취소 요청 후 반드시 환불신청서를 접수해주셔야 최종 접수가 완료되며, 서류 접수일로부터 5일 이내로 처리됩니다. (주말, 공휴일 제외)</li>
            <li>제출서류 : 환불신청서 (무통장입금, 실시간계좌이체 결제 시 본인계좌가 아닌 계좌로 환불받을 경우 가족관계증명서 또는 등본 제출 필수)</li>
            <li>카드결제 : 환불신청서만 회신</li>
            <li>무통장입금/실시간계좌이체 결제 : 환불신청서, 본인명의 통장사본 회신 <a href="#0" class="btn pad-sm" title="환불신청서"><i class="xi-paper-o xi-x" aria-hidden="true"></i>환불신청서 다운로드</a></li>
            <li>신청방법 : 팩스 062-655-3510로 전송 혹은 이메일 <a href="mailto:smhrd@smhrd.or.kr" class="btn pad-sm" title="담당자메일발송">smhrd@smhrd.or.kr</a>로 회신</li>
            <li>제출서류 미비로 인해 생기는 처리 지연 등의 책임은 신청자에게 있습니다.</li>
            <li>수강신청 시 이용한 결제수단에 따라 환불방법이 달라지며, 환불 금액은 학습 진행 정도에 따라 달라질 수 있습니다.</li>
            <li>계좌입금 수수료 등 금융기관에서 공제하는 금액은 환불 받을 수 없습니다.</li>
            <li>문의 : 062-655-3506, 9</li>
        </ul>
      
    </div>
    <div class="modal_btns">        
        <button type="button" class="btn type2">닫기</button>
    </div>
</body>
</html>