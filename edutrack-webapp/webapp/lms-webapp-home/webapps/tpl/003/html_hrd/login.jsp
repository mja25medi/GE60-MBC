<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">
    <%@ include file="../inc/home_common.jsp" %>
    <link rel="stylesheet" href="../css/sub.css"><!-- sub 페이지에서 사용 -->
<body>
    
    <div id="wrap">
        <%@ include file="../inc/hrd_header.jsp" %>

        <main class="main">
            <div id="contentWrap" class="container">
                
                <!-- content -->
                <div id="content" class="content">
                    
                    <div class="member">
                        <div class="flex-container">
                            <div class="con">
                           
                                <div class="tit-header">
                                    <h2 class="title">로그인</h2>
                                </div>
                                <div class="login_form">
                                    <div class="login_input">
                                        <span class="form-floating">
                                            <input type="text" id="loginInputId" class="form-control" placeholder="아이디를 입력해주세요.">
                                            <label for="loginInputId">아이디를 입력해주세요.</label>                            
                                        </span>
                                        <span class="form-floating">
                                            <input type="password" id="loginInputPw" class="form-control" placeholder="비밀번호를 입력해주세요.">
                                            <label for="loginInputPw">비밀번호를 입력해주세요.</label>                            
                                        </span>
                                    </div>     
                                    <div class="custom-input idchk_save"><input type="checkbox" id="loginChkIdSave"><label for="loginChkIdSave">아이디 저장</label></div>                           

                                    <div class="entry_btn">
                                        <button type="submit" title="로그인">로그인</button>
                                    </div>                              
                                </div>
                                <div class="login_foot">
                                    <ul class="list_dot">
                                        <li>
                                            <a href="#" class="btn"><span>회원가입</span></a>
                                        </li>
                                        <li>
                                            <a href="#" class="btn"><span>아이디/비밀번호 찾기</span></a>
                                        </li>
                                    </ul>
                                </div>
                                <div class="ui horizontal divider">간편 로그인</div>

                                <div class="btns-join center mt0">
                                    <a href="#0" class="btn kakao">카카오 아이디로 로그인</a>
                                    <a href="#0" class="btn naver">네이버 아이디로 로그인</a>                         
                                </div>
                            </div>

                        </div>
                    </div>
                       
                        
                </div>
                <!-- //content -->
            </div>
            <!-- //contentWrap -->
              

        </main>

        <!-- modal popup 보여주기 버튼(개발시 팝업id 삭제) -->
        <button class="modal__btn" id="popup1">아이디/비밀번호 찾기</button>
        <script type="text/javascript">
            $("#popup1").click(function() {
                $("#find_id_pw").css('display','flex').hide().fadeIn();
                iFrameResize({ scrolling: true }, '#iframe1')
            })
        </script>
        <!-- modal popup -->
        <div id="find_id_pw" class="modal_popup_wrap">                             
            <div class="modal_popup">
                <h4 class="modal_title">아이디/비밀번호 찾기</h4>
                <iframe id="iframe1" src="iframe_find_id_pw.jsp"></iframe>
                <button type="button" class="modal_close"><i class="xi-close-thin"></i><span class="sr-only">팝업닫기</span></button>
            </div>
        </div>
        <!-- //modal popup -->



        <%@ include file="../inc/hrd_footer.jsp" %>
       
    </div>
</body>

</html>