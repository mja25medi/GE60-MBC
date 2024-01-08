<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">
<%@ include file="../inc/home_common.jsp" %>
<script src="../js/iframeResizer.contentWindow.min.js"></script>
<link rel="stylesheet" href="../css/sub.css"><!-- sub 페이지에서 사용 -->

<body class="scroll_custom">
    <div class="modal_cont">

        <div class="modal_pad">
            <div class="tstyle txt_find">
                <ul class="dbody">
                    <li>                                   
                        <label for="nameInput" class="form-label col-sm-3">이름</label>
                        <div class="col-sm-12">
                            <div class="form-row">
                                <input class="form-control w100" type="text" name="name" id="nameInput" value="">
                            </div>
                            <small class="note">이름을 입력하세요</small>     
                        </div>                                   
                    </li>      
                    <li>                                    
                        <label for="nameInput" class="form-label col-sm-3">이메일주소</label>
                        <div class="col-sm-12">
                            <div class="form-row">
                                <input class="form-control w100" type="text" name="name" value="" id="inputEmail">
                            </div>
                            <small class="note">회원가입시 등록한 이메일주소를 입력하세요.</small> 
                        </div>                                    
                    </li>   
                </ul>
            </div>
        </div>

    </div>
    <div class="modal_btns">
        <button type="button" class="btn">취소</button>
        <button type="button" class="btn type4">다음으로</button>
    </div>
</body>
</html>