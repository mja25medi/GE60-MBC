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
                        <label for="titleInput" class="form-label col-sm-2">문제제목<i class="icon_star" aria-hidden="true"></i></label>
                        <div class="col-sm-10">
                            <div class="form-row">
                                <input class="form-control" type="text" name="titleInput" id="titleInput" value="" placeholder="제목을 입력하세요"> 
                            </div>             
                        </div>
                    </div>
                </li>
                <li>
                    <div class="row">
                        <label for="numInput" class="form-label col-sm-2">첨부파일</label>
                        <div class="col-sm-10 attach_area">
                            <input type="file" class="input_attach sr-only" id="attchFile" multiple=""><label for="attchFile" class="btn gray2">파일 찾기</label> 
                            <div class="attach_list">
                                <div class="attach_name">첨부파일첨부파일첨부파일.png<button type="button" class="attach_close"><i class="xi-close" aria-hidden="true"></i><span class="sr-only">파일삭제</span></button></div>
                                <div class="attach_name">동물관련종사자_입력폼.jpg<button type="button" class="attach_close"><i class="xi-close" aria-hidden="true"></i><span class="sr-only">파일삭제</span></button></div>
                                <div class="attach_name">KakaoTalk_20210729_131327900.png<button type="button" class="attach_close"><i class="xi-close" aria-hidden="true"></i><span class="sr-only">파일삭제</span></button></div>
                            </div>                                            
                        </div>
                    </div>
                </li>
                <li>
                    <div class="row">
                        <div class="col-sm-12">
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
        <button type="button" class="btn type2">저장</button>
        <button type="button" class="btn type9">삭제</button>
        <button type="button" class="btn">닫기</button>
    </div>
</body>
</html>