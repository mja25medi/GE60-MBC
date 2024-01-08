<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">
<%@ include file="../inc/class_common.jsp" %>
<script src="../js/iframeResizer.contentWindow.min.js"></script>
<body class="scroll_custom">
    <div class="modal_cont">
        <ul class="class_tab">
            <li class="active"><a href="#">외출하기</a></li>
            <li><a href="#">조퇴하기</a></li>
        </ul>
        <div class="tstyle">
            <ul class="dbody">
                <li>
                    <div class="row">
                        <label for="outingTime" class="form-label col-sm-2"><span>외출시간</span></label>
                        <div class="col-sm-10">
                            <div class="form-inline">
                                <select class="form-select" id="outingTime">
                                    <option value="9시">9시</option>
                                    <option value="10시">10시</option>
                                </select><span class="ruffle_sign">~</span>
                                <select class="form-select" id="outingTime">
                                    <option value="9시">9시</option>
                                    <option value="10시">10시</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </div>
    <div class="modal_cont">
        <ul class="class_tab">
            <li><a href="#">외출하기</a></li>
            <li class="active"><a href="#">조퇴하기</a></li>
        </ul>
        <div class="tstyle">
            <ul class="dbody">
                <li>
                    <div class="row">
                        <label for="outingTime" class="form-label col-sm-2"><span>조퇴시간</span></label>
                        <div class="col-sm-10">
                            <div class="form-inline">
                                <select class="form-select" id="outingTime">
                                    <option value="9시">9시</option>
                                    <option value="10시">10시</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </div>
    <div class="modal_btns">
        <button type="button" class="btn type2">등록</button>
        <button type="button" class="btn">닫기</button>
    </div>
</body>
</html>