<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">
<%@ include file="../inc/class_common.jsp" %>
<script src="../js/iframeResizer.contentWindow.min.js"></script>
<body class="scroll_custom">
    <div class="modal_cont">
        <div class="table_list">
            <ul class="list">
                <li class="head"><label>문제선택</label></li>
                <li>
                    <div class="form-inline w100">
                        <select class="form-select" id="outingTime">
                            <option value="9시">(서술형)(응시:0명) 1. 21세기에서 부의 흐름 방향과 중앙은행에서 어떠한 전략을 수립해야 하는지 간략히 적으시오.</option>
                            <option value="10시">10시</option>
                        </select>
                    </div>
                </li>
            </ul>           
            <ul class="list">
                <li class="head"><label>내용</label></li>
                <li>내용을 작성하세요</li>
            </ul>
            <ul class="list">
                <li class="head"><label>정답</label></li>
                <li>내용을 작성하세요</li>
            </ul>
            <ul class="list">
                <li class="head"><label>해설</label></li>
                <li>해설 내용이 나옵니다.</li>
            </ul>
        </div>
        <div class="btns mt20">
            <button class="btn type4">점수저장</button>
            <button class="btn type2">닫기</button>
        </div>

        <div class="res_tbl_wrap mt30">
            <table>
                <caption>평가관리 목록</caption>
                <thead>
                    <tr>
                        <th scope="col" width="8%">번호</th>
                        <th scope="col" width="10%">이름</th>
                        <th scope="col" width="14%">아이디</th>
                        <th scope="col">학습자답</th>
                        <th scope="col" width="10%">모사율</th>
                        <th scope="col" width="10%">점수</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td scope="row" data-label="번호">3</td>
                        <td data-label="이름">학습자1</td>
                        <td data-label="아이디">goldhead0822</td>
                        <td class="title" data-label="학습자답">LMS시스템에 대한 이해도</td>
                        <td data-label="모사율">-</td>
                        <td data-label="점수">80점</td>
                    </tr>
                    <tr>
                        <td scope="row" data-label="번호">2</td>
                        <td data-label="이름">학습자2</td>
                        <td data-label="아이디">lycos72</td>
                        <td class="title" data-label="학습자답">LMS시스템에 대한 이해도</td>
                        <td data-label="모사율">-</td>
                        <td data-label="점수">50점</td>
                    </tr>
                    <tr>
                        <td scope="row" data-label="번호">3</td>
                        <td data-label="이름">학습자3</td>
                        <td data-label="아이디">medistu1</td>
                        <td class="title" data-label="학습자답">미응시</td>
                        <td data-label="모사율">-</td>
                        <td data-label="점수">0점</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
    
</body>
</html>