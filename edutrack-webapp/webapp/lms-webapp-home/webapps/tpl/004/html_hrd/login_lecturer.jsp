<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">
    <%@ include file="../inc/home_common.jsp" %>
    <link rel="stylesheet" href="../css/sub.css"><!-- sub 페이지에서 사용 -->
<body>
    
    <div id="wrap" class="prof">
        <%@ include file="../inc/hrd_header_prof.jsp" %>

        <main class="main">
            <div id="contentWrap" class="container">
                
                <!-- content -->
                <div id="content" class="content">
                    
                    <div class="member">
                        <div class="flex-container">
                            <div class="con">
                           
                                <div class="tit-header">
                                    <h2 class="title">강사 로그인</h2>
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
                                
                               
                            </div>

                        </div>
                    </div>
                       
                        
                </div>
                <!-- //content -->
            </div>
            <!-- //contentWrap -->
              

        </main>

        <%@ include file="../inc/hrd_footer.jsp" %>
       
    </div>
</body>

</html>