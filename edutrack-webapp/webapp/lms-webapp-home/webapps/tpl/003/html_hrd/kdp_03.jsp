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
                                    <li><span class="current">인사말</span></li>
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
                            <li class="active"><a href="#">인사말</a></li> 
                            <li><a href="#">찾아오시는 길</a></li>   
                            <li><a href="#">조직도</a></li>  
                        </ul>
                    </div>
                    <!-- //2depth--> 
                    
                    <h3 class="subMenu_title">인사말</h3>
                    <div class="KDP_greeting">
                        <div class="row">
                            <div class="col-sm-4">
                                <div class="txt_tit">AI 메타버스센터 </div>
                            </div>
                            <div class="col-sm-8">
                                <div class="txt_big">
                                    <p>
                                    최근 인공지능 기술은 지능형 실감영상, 디지털 가상현실, 사물인터넷과 접목되면서 <br>
                                    디지털 세상과 물리적 세상이 융합되는 메타버스 세상이 구현되고 있습니다.<br>
                                    메타버스를 이해하고 활용할 수 있는 역량은 미래의 새로운 세상을 살아가기 위한 <br>
                                    중요한 자산이라 할 수 있습니다.
                                    </p>
                                    <p>
                                    <strong class="fcBlue">AI 메타버스센터</strong>는 디지털 전환 시대에 <strong>누구나 디지털 신기술을 활용할 수 있도록 <br>
                                    메타버스 체험 프로그램과 AI, 빅데이터 교육을 실시</strong>하고 있습니다.
                                    </p> 
                                    <p>      
                                    이를 통해 지역사회의 가치를 창출하고 공유와 개방의 가치를 알려 <br>
                                    <strong>지역사회에 디지털 허브기관</strong>으로 거듭나겠습니다.
                                    </p>     
                                    <strong>AI 메타버스센터 직원 일동</strong> 
                                </div>
                            </div>
                        </div>
                        <ul>
                            <li>
                                <div class="card v01">
                                    <h5>메타버스 체험 프로그램</h5>
                                </div>
                            </li>
                            <li>
                                <div class="card v02">
                                    <h5>AI, 빅데이터 교육</h5>
                                </div>
                            </li>
                            <li>
                                <div class="card v03">
                                    <h5>시설장비 공유 및 개방</h5>
                                </div>
                            </li>
                        </ul>    

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