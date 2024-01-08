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
                                    <li>KDP센터 소개</li>
                                    <li><span class="current">KDP 사업소개</span></li>
                                </ul>
                            </nav>                                                   
                        </div>
                        <h4 class="title_h1">KDP센터 소개</h4>
                    </div>
                    <!-- //h1_area --> 
                    
                    <div class="subMenu">
                        <ul class="menu">
                            <li class="active"><a href="#">KDP 사업소개</a></li>
                            <li><a href="#">KDP 센터소개</a></li>  
                            <li><a href="#">인사말</a></li> 
                            <li><a href="#">찾아오시는 길</a></li>   
                            <li><a href="#">조직도</a></li>  
                        </ul>
                    </div>
                    <!-- //2depth--> 
                    
                    <h3 class="subMenu_title">KDP 사업소개</h3>
                    
                    <div class="KDP_intro">
                        <div class="txt_big"> 
                            <p>
                                전환 및 경제 구조 변화 등에 따라 노동시장 참여자 누구나 기초 디지털 직무능력을 확보하고 <br>
                                디지털 신기술을 현장에서 활용할 핵심 실무인재가 될 수 있도록 디지털 융합 훈련시설·장비를 구축하고 지원합니다.
                            </p>
                            <p>
                                <strong class="fcBlue">K-Digital Platform 구축 및 운영</strong>을 통한 디지털 격차 해소와 산업 수요를 반영하여 <br>
                                <strong>디지털 교육을 통한 재직자 역량강화 및 청년 일자리 창출</strong>에 기여하고자 합니다.
                            </p>
                        </div>
                        <div class="KDP_bg">
                            <div class="grad">K-Digital Platform</div> 
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