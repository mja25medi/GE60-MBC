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
                        <label class="form-label col-sm-2">대상자수</label>
                        <div class="col-sm-10">1명</div>
                    </div>
                </li>
                <li>
                    <div class="row">
                        <label class="form-label col-sm-2">수신자</label>
                        <div class="col-sm-10">학습자1(USR0000000003)</div>
                    </div>
                </li>
                <li>
                    <div class="row">
                        <label class="form-label col-sm-2">수신거부자</label>
                        <div class="col-sm-10">
                            <ul class="message_box">
                               <li>수신거부자에게는 메일이 발송되지 않습니다.</li>
                            </ul>
                        </div>
                    </div>
                </li>
                <li>
                    <div class="row">
                        <label for="titleInput" class="form-label col-sm-2">제목</label>
                        <div class="col-sm-10">
                            <div class="form-row">
                                <input class="form-control" type="text" name="titleInput" id="titleInput" value="" maxlength="100" placeholder="제목을 입력하세요">
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
            </ul>
        </div>
    </div>
    <div class="modal_btns">
        <button type="button" class="btn type2">발송</button>
        <button type="button" class="btn">닫기</button>
    </div>
</body>
</html>