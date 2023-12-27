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
                            
                            <div class="join_cont">
                                <ol class="join_step">
                                    <li>               
                                        <span class="title"><small>Step 1</small> <b>약관동의</b></span>
                                    </li>
                                    <li>
                                        <span class="title"><small>Step 2</small> <b>정보입력</b></span>                                       
                                    </li>                            
                                    <li class="active">
                                        <span class="title"><small>Step 3</small> <b>가입완료</b></span>                                       
                                    </li>                                     
                                </ol>
                                
                                <div class="join_area join_complete">
                                    <h5 class="title_h2"><strong>회원가입</strong>을 축하드립니다.</h5>
                                    <img src="../img/contents/picto_join.png" alt="" class="picto">
                                    <p class="desc">
                                        로그인 후 다양한 서비스를 이용하 실 수 있습니다. 
                                    </p>
                                    <p class="desc2"> 아이디는 <strong>hongildong1234</strong> 입니다.</p>
                                </div>
                                
                                <div class="btns">
                                    <button type="button" class="btn type5">메인으로</button>
                                    <button type="button" class="btn type4">로그인 하기</button>
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