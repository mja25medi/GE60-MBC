<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">
    <%@ include file="../inc/home_common.jsp" %>
    <link rel="stylesheet" href="../css/sub.css"><!-- sub 페이지에서 사용 -->
    <script type="text/javascript" src="//openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=x2dpflxt4x"></script>
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
                                    <li>개발원소개</li>
                                    <li><span class="current">오시는 길</span></li>
                                </ul>
                            </nav>                                                   
                        </div>
                        <h4 class="title_h1">개발원소개</h4>
                    </div>
                    <!-- //h1_area --> 

                    <div class="subMenu">
                        <ul class="menu">
                            <li><a href="#">개발원 이야기</a></li>
                            <li><a href="#">조직현황/강사진</a></li>  
                            <li><a href="#">세미콜론 매거진</a></li> 
                            <li><a href="#">인재채용</a></li> 
                            <li><a href="#">뉴스룸</a></li> 
                            <li class="active"><a href="#">오시는 길</a></li>        
                        </ul>
                    </div>
                    <!-- //2depth--> 
                    
                    <h3 class="subMenu_title">오시는 길</h3>
                    
                    <div class="info_map">
                        <div class="row">
                            <div class="col-sm-8">
                                <div class="map_content_all">
                                    <div class="map_view">지도</div>
                                </div>
                            </div>
                            <div class="col-sm-4">
                                <div class="map_right">
                                    <ul>
                                        <li class="on"><a href="#tab-anchor"><strong>광주 동구본점</strong><br />
                                        광주광역시 동구 예술길 31-15 광주아트센터 3~5, 7층</a></li>
                                        <li><a href="#tab-anchor"><strong>광주 남구점</strong><br />
                                        광주광역시 남구 송암로 60 광주CGI센터 2층</a></li>
                                        <li><a href="#tab-anchor"><strong>서울 서초점</strong><br />
                                        서울특별시 서초구 동작대로 132 안석빌딩 9층</a></li>
                                        <li><a href="#tab-anchor"><strong>순천점</strong><br />
                                        전라남도 순천시 중앙로 260 (KT 북순천지사)</a></li>
                                        <li><a href="#tab-anchor"><strong>목포점</strong><br />
                                        전남 목포시 산정로212번길 13 (구, 청호중학교)</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <script>
                            (function($) {
                                $(document).ready(function() {
                                    $(".map_right ul li").click(function() {
                                        var idx = $(this).index();
                                        $(".map_right ul li").removeClass("on");
                                        $(".map_right ul li").eq(idx).addClass("on");
                                        $(".tab_title li").removeClass("on");
                                        $(".tab_title li").eq(idx).addClass("on");
                                        $(".tab_cont .vc_row").removeClass("on");
                                        $(".tab_cont .vc_row").eq(idx).addClass("on");
                        
                                        setTimeout(function() {
                                            window.dispatchEvent(new Event('resize'));
                                        }, 0);
                                    })
                        
                                    $(".tab_title li").click(function() {
                                        var idx = $(this).index();
                                        $(".map_right ul li").removeClass("on");
                                        $(".map_right ul li").eq(idx).addClass("on");
                                        $(".tab_title li").removeClass("on");
                                        $(".tab_title li").eq(idx).addClass("on");
                                        $(".tab_cont .vc_row").removeClass("on");
                                        $(".tab_cont .vc_row").eq(idx).addClass("on");
                        
                                        setTimeout(function() {
                                            window.dispatchEvent(new Event('resize'));
                                        }, 0);
                                    })
                                });
                            })(jQuery);
                        
                        </script>
                        

                        <div class="tab_title">
                            <ul>
                                <li class="on">광주 본점</li>
                                <li>광주 남구점</li>
                                <li>서울 서초점</li>
                                <li>순천점</li>
                                <li>목포점</li>
                            </ul>
                        </div>

                        <div class="tab_cont">
                            <div class="vc_row on">
                                <div class="page_tit">
                                    <h1>광주 본점</h1>
                                </div>
                                <div class="map_content">
                                    <div class="map_view">지도</div>
                                </div>
                                
                                <div class="contact_address">
                                    <p>광주광역시 동구 예술길 31-15 광주아트센터 3~5, 7층</p>
                                </div> 

                                <h2>오시는 길</h2>
                                <dl>
                                    <dt>버스정류장</dt>
                                    <dd>전남여고, 국립아시아문화전당, 예술의거리 입구, 문화전당역</dd>
                                </dl>
                                <dl>
                                    <dt>지하철 역</dt>
                                    <dd>국립아시아문화전당역(1호선, 4번출구)</dd>
                                </dl>

                                <h2>전경사진</h2>
                                <div class="hub_photo_list">
                                    <ul class="slider_photo">
                                        <li><img src="https://smhrd.or.kr/wp-content/uploads/2022/02/hq-6-700x467.jpg" class="thumb"></li>
                                        <li><img src="https://smhrd.or.kr/wp-content/uploads/2022/02/hq-1-768x512.jpg" class="thumb"></li>
                                        <li><img src="https://smhrd.or.kr/wp-content/uploads/2022/02/hq-2-700x467.jpg" class="thumb"></li>
                                        <li><img src="https://smhrd.or.kr/wp-content/uploads/2022/02/hq-3-700x467.jpg" class="thumb"></li>
                                        <li><img src="https://smhrd.or.kr/wp-content/uploads/2022/02/hq-4-768x512.jpg" class="thumb"></li>
                                        <li><img src="https://smhrd.or.kr/wp-content/uploads/2022/02/hq-5-700x467.jpg" class="thumb"></li>
                                    </ul>                   
                                </div>
                            </div>
                        
                            <div class="vc_row">
                                <div class="page_tit">
                                    <h1>광주 남구점</h1>
                                </div>
                                <div class="map_content">
                                    <div class="map_view">지도</div>
                                </div>
                                
                                <div class="contact_address">
                                    <p>광주광역시 남구 송암로 60 광주CGI센터 2층</p>
                                </div> 

                                <h2>오시는 길</h2>
                                <dl>
                                    <dt>버스정류장</dt>
                                    <dd>송원대 (노선 정보 : 31, 47, 28, 17, 76, 68, 72, 73, 74, 75, 03, 07)</dd>
                                </dl>
                                <dl>
                                    <dt>지하철 역</dt>
                                    <dd>2호선 공사중</dd>
                                </dl>    
                                
                                <h2>전경사진</h2>
                                <div class="hub_photo_list">
                                    <ul class="slider_photo">
                                        <li><img src="https://smhrd.or.kr/wp-content/uploads/2022/02/chain1-10-768x512.png" class="thumb"></li>
                                        <li><img src="https://smhrd.or.kr/wp-content/uploads/2022/02/chain1-1-700x467.png" class="thumb"></li>
                                        <li><img src="https://smhrd.or.kr/wp-content/uploads/2022/02/chain1-2-700x467.png" class="thumb"></li>
                                        <li><img src="https://smhrd.or.kr/wp-content/uploads/2022/02/chain1-3-768x512.png" class="thumb"></li>
                                        <li><img src="https://smhrd.or.kr/wp-content/uploads/2022/02/chain1-4-700x467.png" class="thumb"></li>
                                        <li><img src="https://smhrd.or.kr/wp-content/uploads/2022/02/chain1-5-768x512.png" class="thumb"></li>
                                        <li><img src="https://smhrd.or.kr/wp-content/uploads/2022/02/chain1-6-700x467.png" class="thumb"></li>
                                        <li><img src="https://smhrd.or.kr/wp-content/uploads/2022/02/chain1-7-700x467.png" class="thumb"></li>
                                        <li><img src="https://smhrd.or.kr/wp-content/uploads/2022/02/chain1-8-700x467.png" class="thumb"></li>
                                        <li><img src="https://smhrd.or.kr/wp-content/uploads/2022/02/chain1-9-700x467.png" class="thumb"></li>
                                    </ul>                   
                                </div>
                            </div>
                       
                            <div class="vc_row">
                                <div class="page_tit">
                                    <h1>서울 서초점</h1>
                                </div>
                                <div class="map_content">
                                    <div class="map_view">지도</div>
                                </div>
                                
                                <div class="contact_address">
                                    <p>서울시 서초구 동작대로 132 안석빌딩 9층</p>      
                                </div> 

                                <h2>오시는 길</h2>
                                <dl>
                                    <dt>지하철 역</dt>
                                    <dd>총신대입구(4호선), 이수역 (7호선)</dd>
                                </dl>
                                <dl>
                                    <dt>버스정류장</dt>
                                    <dd>총신대입구역.남성시장입구앞 정류장</dd>
                                </dl>  
                            </div>
                       
                            <div class="vc_row">
                                <div class="page_tit">
                                    <h1>순천점</h1>
                                </div>
                                <div class="map_content">
                                    <div class="map_view">지도</div>
                                </div>
                                
                                <div class="contact_address">
                                    <p>전라남도 순천시 중앙로 260 (KT 북순천지사)</p>         
                                </div> 

                                <h2>오시는 길</h2>
                                <dl>
                                    <dt>버스정류장</dt>
                                    <dd>순천대학교</dd>
                                </dl>
                                <dl>
                                    <dt>버스노선</dt>
                                    <dd>1, 14, 15, 16, 18, 30~35, 60~64 등 다수노선</dd>
                                </dl> 
                            </div>
                       
                            <div class="vc_row">
                                <div class="page_tit">
                                    <h1>목포점</h1>
                                </div>
                                <div class="map_content">
                                    <div class="map_view">지도</div>
                                </div>
                                
                                <div class="contact_address">
                                    <p>전남 목포시 산정로212번길 13 (구, 청호중학교)</p>  
                                </div> 

                                <h2>오시는 길</h2>
                                <dl>
                                    <dt>버스정류장</dt>
                                    <dd>목포고등학교, 연동파출소</dd>
                                </dl>
                                <dl>
                                    <dt>버스노선</dt>
                                    <dd>1, 1-2, 1A, 간선2, 6, 10, 20, 33, 60, 108, 150, 200, 300, 300A, 900</dd>
                                </dl>     
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