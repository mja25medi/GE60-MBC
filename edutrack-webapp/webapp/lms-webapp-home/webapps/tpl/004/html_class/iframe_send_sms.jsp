<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">
<%@ include file="../inc/class_common.jsp" %>
<script src="../js/iframeResizer.contentWindow.min.js"></script>
<body class="scroll_custom">
    <div class="modal_cont">
        <div class="tstyle">
            <ul class="dbody">
                <li>
                    <div class="row">
                        <label for="titleInput" class="form-label col-sm-2">발송대상 인원수</label>
                        <div class="col-sm-10">1명</div>
                    </div>
                </li>
                <li>
                    <div class="row">
                        <label for="callerInput" class="form-label col-sm-2">발신번호</label>
                        <div class="col-sm-10">
                            <div class="form-row">
                                <input class="form-control" type="text" name="callerInput" id="callerInput" value="027862330" maxlength="15" placeholder="-없이 숫자만 입력하세요" disabled>
                            </div>
                        </div>
                    </div>
                </li>
                <li>
                    <div class="row">
                        <label for="reservChoice" class="form-label col-sm-2">예약발송</label>
                        <div class="col-sm-10">
                            <div class="form-row">
                                <span class="custom-input">
                                    <input type="radio" name="reservChoice" id="reservChoice">
                                    <label for="reservChoice">즉시발송</label>
                                </span>
                            </div>
                        </div>
                    </div>
                </li>
                <li>
                    <div class="row">
                        <label for="contTextarea" class="form-label col-sm-2">내용</label>
                        <div class="col-sm-10">
                            <div class="form-row">
                                <textarea class="form-control" rows="10" id="contTextarea"></textarea>
                            </div>
                        </div>
                    </div>
                </li>
                <li>
                    <div class="form-row">
                        <div class="res_tbl_wrap">
                            <table>
                                <caption>수신자 목록</caption>
                                <thead>
                                    <tr>
                                        <th scope="col">이름</th>
                                        <th scope="col">전화번호</th>
                                        <th scope="col">수신거부</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td scope="row" data-label="이름">학습자3</td>
                                        <td data-label="전화번호">01085772380</td>
                                        <td data-label="수신거부">-</td>
                                    </tr>
                                    <tr>
                                        <td scope="row" data-label="이름">학습자2</td>
                                        <td data-label="전화번호">01085772380</td>
                                        <td data-label="수신거부">-</td>
                                    </tr>
                                    <tr>
                                        <td scope="row" data-label="이름">학습자1</td>
                                        <td data-label="전화번호">01072522880</td>
                                        <td data-label="수신거부">-</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </li>                              
            </ul>
        </div>
    </div>
    <div class="modal_btns">
        <button type="button" class="btn type2">발송</button>
        <button type="button" class="btn">닫기</button>
    </div>
</body>
</html>