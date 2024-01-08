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
                                    <li>시설·장비 공유</li>
                                    <li><span class="current">예약현황</span></li>
                                </ul>
                            </nav>                                                   
                        </div>
                        <h4 class="title_h1">시설·장비 공유</h4>
                    </div>
                    <!-- //h1_area --> 
                    
                    <div class="subMenu">
                        <ul class="menu">
                            <li><a href="#">시설장비 이용방법</a></li>
                            <li><a href="#">대관/대여 신청</a></li>  
                            <li class="active"><a href="#">예약현황</a></li> 
                            <li><a href="#">예약조회</a></li>     
                        </ul>
                    </div>
                    <!-- //2depth--> 
                    
                    <h3 class="subMenu_title">예약현황</h3>

                    <ul class="tabs mb0 two_list">
                        <li class="active"><a href="#" title="선택된 탭메뉴">시설</a></li>
                        <li><a href="#" title="비활성 탭메뉴">장비</a></li>
                    </ul>
                    
                    <div class="factit_box">
                        <h5><i class="xi-check"></i> 교육실 - 오픈랩(OPEN LAB)</h5>
                    </div>
                    달력


                </div>
                <!-- //content -->
            </div>
            <!-- //contentWrap -->
              

        </main>

        <!-- modal popup 보여주기 버튼(개발시 팝업id 삭제) -->
        <button class="modal__btn" id="popup1">시설예약 팝업</button>
        <button class="modal__btn" id="popup2">시설예약상세 팝업</button>
        <script type="text/javascript">
            $("#popup1").click(function() {
                $('body').css("overflow", "hidden");
                $("#fac_reservation_write").css('display','flex').hide().fadeIn();
                iFrameResize({ scrolling: true }, '#iframe1')
            })

            $("#popup2").click(function() {
                $('body').css("overflow", "hidden");
                $("#fac_reservation_info").css('display','flex').hide().fadeIn();
                iFrameResize({ scrolling: true }, '#iframe2')
            });
        </script>
        <!-- 시설 예약 popup -->
        <div id="fac_reservation_write" class="modal_popup_wrap">                             
            <div class="modal_popup m_large">
                <h4 class="modal_title">시설 예약 시간 선택</h4>
                <iframe id="iframe1" src="iframe_fac_reservation_write.jsp"></iframe>
                <button type="button" class="modal_close"><i class="xi-close-thin"></i><span class="sr-only">팝업닫기</span></button>
            </div>
        </div>
        <!-- 시설예약상세 popup -->
        <div id="fac_reservation_info" class="modal_popup_wrap">                             
            <div class="modal_popup modal_medium">
                <h4 class="modal_title">시설 예약 상세</h4>
                <iframe id="iframe2" src="iframe_fac_reservation_info.jsp"></iframe>
                <button type="button" class="modal_close"><i class="xi-close-thin"></i><span class="sr-only">팝업닫기</span></button>
            </div>
        </div>
        <!-- //modal popup -->

        <%@ include file="../inc/hrd_footer.jsp" %>
       
    </div>
</body>

</html>