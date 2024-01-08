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
                    <div class="h1_area">
                        <div class="location_bar">
                            <nav class="location">
                                <ul>
                                    <li><i class="xi-home-o" aria-hidden="true"></i><span class="sr-only">Home</span></li>
                                    <li>마이 페이지</li>
                                    <li><span class="current">문의내역</span></li>
                                </ul>
                            </nav>                                                   
                        </div>
                        <h4 class="title_h1">마이 페이지</h4>
                    </div>
                    <!-- //h1_area --> 
                    
                    <div class="subMenu">
                        <ul class="menu">
                            <li><a href="#">장바구니</a></li>
                            <li><a href="#">결제내역</a></li>  
                            <li><a href="#">문의내역</a></li> 
                            <li><a href="#">받은쪽지</a></li>    
                            <li><a href="#">개인정보 수정</a></li>  
                            <li class="active"><a href="#">아바타 편집</a></li>      
                        </ul>
                    </div>
                    <!-- //2depth--> 
                    
                    <h3 class="subMenu_title">아바타 편집</h3>

                    <div class="member">
                        <div class="flex-container">
                            <div class="con_avatar">
                                <div class="title">나의 아바타</div>
                                <div class="avatar_area">
                                    <i class="xi-man"></i>
                                    <!-- 아바타 -->
                                </div>
                                <div class="entry_btn">
                                    <button type="submit" title="로그인">등록하기</button>
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
        <button class="modal__btn" id="popup1">아바타 편집하기 팝업</button>
        <script type="text/javascript">
            $("#popup1").click(function() {
                $('body').css("overflow", "hidden");
                $("#avatar_setting").css('display','flex').hide().fadeIn();
                iFrameResize({ scrolling: true }, '#iframe1')
            })
        </script>
        <!-- modal popup -->
        <div id="avatar_setting" class="modal_popup_wrap">                             
            <div class="modal_popup">
                <h4 class="modal_title">아바타 편집하기</h4>
                <iframe id="iframe1" src="iframe_avatar_setting.jsp"></iframe>
                <button type="button" class="modal_close"><i class="xi-close-thin"></i><span class="sr-only">팝업닫기</span></button>
            </div>
        </div>
        <!-- //modal popup -->

        <%@ include file="../inc/hrd_footer.jsp" %>
       
    </div>
</body>

</html>