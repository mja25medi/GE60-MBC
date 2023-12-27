<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">
<%@ include file="../inc/class_common.jsp" %>
<script src="../js/iframeResizer.contentWindow.min.js"></script>
<body class="scroll_custom">
    <div class="modal_cont">
        <div class="board_top">
            <h4>3. 콘텐츠 품질 평가에 대해 자유롭게 의견을 적으세요.</h4>
        </div>
        <div class="res_tbl_wrap">
            <table>
                <caption>설문결과 목록</caption>
                <thead>
                    <tr>
                        <th scope="col" width="8%">번호</th>
                        <th scope="col">아이디</th>
                        <th scope="col">이름</th>
                        <th scope="col" width="50%">답변</th>
                        <th scope="col" width="15%">등록일시</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td scope="row" data-label="번호">3</td>
                        <td class="title" data-label="아이디">goldhead0822</td>
                        <td class="title" data-label="이름">학습자3</td>
                        <td class="title" data-label="답변">Lorem ipsum dolor sit amet consectetur adipisicing elit. Fugit temporibus commodi non impedit! Ab fuga ipsum eum id velit accusantium, et excepturi illum tenetur quos sapiente, molestias dolor quibusdam iure.</td>
                        <td data-label="등록일시">2023.10.21 10:08</td>
                    </tr>
                    <tr>
                        <td scope="row" data-label="번호">2</td>
                        <td class="title" data-label="아이디">lycos72</td>
                        <td class="title" data-label="이름">학습자2</td>
                        <td class="title" data-label="답변">Lorem ipsum dolor sit amet consectetur adipisicing elit. Fugit temporibus commodi non impedit! Ab fuga ipsum eum id velit accusantium, et excepturi illum tenetur quos sapiente, molestias dolor quibusdam iure.</td>
                        <td data-label="등록일시">2023.10.21 10:08</td>
                    </tr>
                    <tr>
                        <td scope="row" data-label="번호">1</td>
                        <td class="title" data-label="아이디">medistu1</td>
                        <td class="title" data-label="이름">학습자1</td>
                        <td class="title" data-label="답변">Lorem ipsum dolor sit amet consectetur adipisicing elit. Fugit temporibus commodi non impedit! Ab fuga ipsum eum id velit accusantium, et excepturi illum tenetur quos sapiente, molestias dolor quibusdam iure.</td>
                        <td data-label="등록일시">2023.10.21 10:08</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
    <div class="modal_btns">
        <button type="button" class="btn">닫기</button>
    </div>
</body>
</html>