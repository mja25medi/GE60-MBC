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
                                    <h2 class="title">회원가입 하기</h2>
                                    <div class="desc">ID/PW를 입력하여 직접 회원 가입 또는 소셜 회원가입이 가능합니다.</div>
                                </div>

                                <div class="entry_btn">
                                    <button onclick="#" title="일반 회원 가입" id="logincheck">일반 회원 가입</button>
                                </div>
                                <div class="ui horizontal divider">또는</div>
                                <div class="btns-join center mt30">
                                    <a href="#0" class="btn kakao">카카오로 시작하기</a>
                                    <a href="#0" class="btn naver">네이버로 시작하기</a>                         
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