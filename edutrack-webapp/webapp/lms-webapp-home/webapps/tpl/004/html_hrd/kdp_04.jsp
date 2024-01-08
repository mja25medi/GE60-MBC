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
                                    <li><span class="current">찾아오시는 길</span></li>
                                </ul>
                            </nav>                                                   
                        </div>
                        <h4 class="title_h1">KDP센터 소개</h4>
                    </div>
                    <!-- //h1_area --> 
                    
                    <div class="subMenu">
                        <ul class="menu">
                            <li><a href="#">KDP 사업소개</a></li>
                            <li><a href="#">KDP 센터소개</a></li>  
                            <li><a href="#">인사말</a></li> 
                            <li class="active"><a href="#">찾아오시는 길</a></li>   
                            <li><a href="#">조직도</a></li>  
                        </ul>
                    </div>
                    <!-- //2depth--> 
                    
                    <h3 class="subMenu_title">찾아오시는 길</h3>
                    
                    <div class="KDP_map">
                        <!-- 카카오맵 임시 -->
                        <div id="daumRoughmapContainer1701760560602" class="root_daum_roughmap root_daum_roughmap_landing"></div>
                        <script charset="UTF-8" class="daum_roughmap_loader_script" src="https://ssl.daumcdn.net/dmaps/map_js_init/roughmapLoader.js"></script>

                        <script charset="UTF-8">
                            new daum.roughmap.Lander({
                                "timestamp" : "1701760560602",
                                "key" : "2h4b8"
                            }).render();
                        </script>

                        <div class="address_box">
                            <h5><i class="xi-location-arrow" aria-hidden="true"></i> 전남 목포시 산정로 212번길 13(구. 청호중학교)</h5>
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