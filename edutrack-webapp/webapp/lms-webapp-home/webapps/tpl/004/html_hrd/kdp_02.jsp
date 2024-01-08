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
                                    <li><span class="current">KDP 센터소개</span></li>
                                </ul>
                            </nav>                                                   
                        </div>
                        <h4 class="title_h1">KDP센터 소개</h4>
                    </div>
                    <!-- //h1_area --> 
                    
                    <div class="subMenu">
                        <ul class="menu">
                            <li><a href="#">KDP 사업소개</a></li>
                            <li class="active"><a href="#">KDP 센터소개</a></li>  
                            <li><a href="#">인사말</a></li> 
                            <li><a href="#">찾아오시는 길</a></li>   
                            <li><a href="#">조직도</a></li>  
                        </ul>
                    </div>
                    <!-- //2depth--> 
                    
                    <h3 class="subMenu_title">KDP 센터소개</h3>
                    <div class="KDP_center">
                        <div class="txt_big">
                            <p>
                            한국산업인력공단의 지원을 받은 스마트인재개발원이 목포시와 협력해<br>  
                            조성한 <strong class="fcBlue">'AI 메타버스 센터'</strong>는 AI 및 빅데이터 분야에 특화된 교육 및 체험 등을 <br>
                            제공하는 <strong>융합형 온·오프라인 플랫폼</strong>입니다. 
                            </p>    
                            <p>
                            이를 통해 지역 주민, 기업 재직자, 그리고 구직자들은<br> 어디에서나 시간과 공간의 제약 없이 
                            AI, 빅데이터, 메타버스 등 <br>디지털 신기술을 학습하고 체험할 수 있습니다.
                            </p>
                            <p>
                            디지털 전환 시대에서 요구하는 디지털 역량을 개발하기 위해<br> 디지털 교육 및 체험 등을 제공함으로써<br> 
                            전 국민이 인공지능을 생활화할 수 있는 허브 역할을 수행하고자 합니다.
                            </p>
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