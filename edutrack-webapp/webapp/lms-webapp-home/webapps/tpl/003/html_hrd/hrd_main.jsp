<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">
    <%@ include file="../inc/home_common.jsp" %>
    <link rel="stylesheet" href="../css/main.css"><!-- main 페이지에서 사용 -->
<body>
    
    <div id="wrap">
        <%@ include file="../inc/hrd_header.jsp" %>
        

        <main class="main">
            <div id="content">
                <!-- mv_banner -->
                <div class="mv_banner">
                    <div class="ban">
                        <a href="#0">
                            <i class="xi-message big"></i>
                            <i class="xi-full-moon colorA"></i>
                            <i class="xi-full-moon colorB"></i>
                            <i class="xi-full-moon colorC"></i>
                            <i class="xi-full-moon colorD"></i>
                            <i class="xi-full-moon colorE"></i>
                            <i class="xi-full-moon colorF"></i>
                            <strong><span>메타버스</span><span>캠퍼스</span></strong>
                            
                        </a>
                    </div>
                </div>
                
                <!-- mainvisual -->
                <div class="mainvisual">
                    <div class="cycle-slideshow"
                        data-cycle-slides="> .slide"
                        data-cycle-speed="1000"
                        data-cycle-timeout="5000"
                        data-cycle-pause-on-hover="true"
                        data-cycle-stop=".btnStop"
                        data-cycle-next=".btnPlay"
                        data-cycle-pager="#adv-custom-pager"
                        data-cycle-pager-template="<a href='#'></a>"
                    >
                        <div href="#" class="slide vm01" style="background: #1F1F1F url(../img/main/visual_01.jpg) center top no-repeat;">
                            <div class="txt-box">
                                <p class="tit">인공지능 All-in-One 실무캠프 <br>Chat-GPT · 자율주행 · 풀스택 과정 </p>
                                <p class="content">완전히 달라진 커리큘럼으로 만나보세요</p>
                            </div>
                        </div>
                        <div href="#" class="slide vm02" style="background: #1F1F1F url(../img/main/visual_02.jpg) center top no-repeat;">
                            <div class="txt-box">
                                <p class="tit">인공지능 융합 서비스개발자과정</p>
                                <p class="content">실무까지 탄탄하게 국비교육으로 배울 수
                                    있는<br> 인공지능 특화 취업연계과정</p>
                            </div>
                        </div>
                    </div>
                    <div class="cont-box">
                        <div class="pagerWrap">
                            <span id="adv-custom-pager" title="롤링배너 버튼"></span>
                            <button class="btnStop" data-cycle-cmd="pause">슬라이드 멈춤</button>
                            <button class="btnPlay blind" data-cycle-cmd="resume">슬라이드 다시 시작</button>
                        </div>
                        <script type="text/javascript">
                        //bigSlide button
                        $('.pagerWrap .btnStop').click(function(){
                            $(this).addClass('blind');
                            $('.pagerWrap .btnPlay').removeClass('blind');
                        });
                        $('.pagerWrap .btnPlay').click(function(){
                            $(this).addClass('blind');
                            $('.pagerWrap .btnStop').removeClass('blind');
                        });
                        </script>   
                    </div>
                                        
                </div>

                

                <!-- countdown -->
                <div class="countdown">
                    <div class="container">
                        <span class="info"><strong>10/19</strong> 취업연계과정 모집마감까지</span> 
                        
                        <div class="time_area">
                            <span><i class="xi-alarm-o" aria-hidden="true"></i></span>
                            <span class="time">7일</span>
                            <span class="time">18:59:39</span>
                            <span>남았어요 !</span>
                        </div>
                    </div>
                </div>
                <!-- //countdown -->
                             
            </div>
            <!-- //mainvisual -->
           
            <!-- 전액무료 국비과정-->
            <div class="section section01">
                <div class="container">                        
                   
                    <div class="con_area">
                        <div class="slider_info">
                            <h2>전액무료! 국비과정</h2>                            
                            <a href="#" class="more"><span class="sr_only">더보기</span><i class="xi-angle-right" aria-hidden="true"></i></a>
                            <div class="desc">4~6개월동안 빅데이터, AI, IoT, 풀스텍코딩에 특화된 실무교육과정으로 전액 무료로 수강할 수 있는 과정입니다.</div>
                            
                            <div class="sort">
                                <button type="button" class="active">전체</button>
                                <button type="button">빅데이터</button>
                                <button type="button">인공지능</button>
                                <button type="button">IoT</button>
                            </div>
                                                             
                        </div>
                        <ul class="slider_list">
                            <li>
                                <a href="#0">
                                    <div class="card_item">
                                        <div class="image_area">
                                            <div class="img_area">
                                                <img src="../img/contents/thumb_01.png" alt="[A-2코스] K-디지털 - 인공지능
                                                과정 / 광주점">
                                            </div>
                                        </div>
                                        <div class="des">
                                            <div class="card_labels">
                                                <span>스테디코스</span>
                                                <span>인공지능특화</span>
                                            </div> 
                                            <p class="des1">[A-2코스] K-디지털 - 인공지능
                                                과정 / 광주점</p>
                                            <p class="des2">스마트인재개발원 광주점</p>                                                                                
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="#0">
                                    <div class="card_item">
                                        <div class="image_area">
                                            <div class="img_area">
                                                <img src="../img/contents/thumb_02.png" alt="[A-1코스] K-디지털 빅데이터 과정 / 순천점">
                                            </div>
                                        </div>
                                        <div class="des">
                                            <div class="card_labels">
                                                <span>빅데이터특화</span>
                                                <span>스테디코스</span>
                                            </div> 
                                            <p class="des1">[A-1코스] K-디지털 빅데이터 과정 / 순천점</p>
                                            <p class="des2">스마트인재개발원 순천점</p>                                                                                
                                        </div>
                                    </div>
                                </a>
                            </li>                                                        
                            <li>
                                <a href="#0">
                                    <div class="card_item">
                                        <div class="image_area">
                                            <div class="img_area">
                                                <img src="../img/contents/thumb_03.png" alt="[D코스] 인공지능 All-in-One 실무캠프 / 광주점">
                                            </div>
                                        </div>
                                        <div class="des">
                                            <div class="card_labels">
                                                <span>AI실무트렌드</span>
                                                <span>호남유일</span>
                                            </div> 
                                            <p class="des1">[D코스] 인공지능 All-in-One 실무캠프 / 광주점</p>
                                            <p class="des2">스마트인재개발원 광주점</p>                                                                                
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="#0">
                                    <div class="card_item">
                                        <div class="image_area">
                                            <div class="img_area">
                                                <img src="../img/contents/thumb_04.png" alt="[A-3코스] K-디지털 - 풀스택 IoT 과정 / 광주점">
                                            </div>
                                        </div>
                                        <div class="des">
                                            <div class="card_labels">
                                                <span>IoT/코딩</span>
                                                <span>전액무료</span>
                                            </div> 
                                            <p class="des1">[A-3코스] K-디지털 - 풀스택 IoT 과정 / 광주점</p>
                                            <p class="des2">스마트인재개발원 순천점</p>                                                                                
                                        </div>
                                    </div>
                                </a>
                            </li>                        
                            <li>
                                <a href="#0">
                                    <div class="card_item">
                                        <div class="image_area">
                                            <div class="img_area">
                                                <img src="../img/contents/thumb_01.png" alt="[A-2코스] K-디지털 - 인공지능
                                                과정 / 광주점">
                                            </div>
                                        </div>
                                        <div class="des">
                                            <div class="card_labels">
                                                <span>스테디코스</span>
                                                <span>인공지능특화</span>
                                            </div> 
                                            <p class="des1">[A-2코스] K-디지털 - 인공지능
                                                과정 / 광주점</p>
                                            <p class="des2">스마트인재개발원 광주점</p>                                                                                
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="#0">
                                    <div class="card_item">
                                        <div class="image_area">
                                            <div class="img_area">
                                                <img src="../img/contents/thumb_02.png" alt="[A-1코스] K-디지털 빅데이터 과정 / 순천점">
                                            </div>
                                        </div>
                                        <div class="des">
                                            <div class="card_labels">
                                                <span>빅데이터특화</span>
                                                <span>스테디코스</span>
                                            </div> 
                                            <p class="des1">[A-1코스] K-디지털 빅데이터 과정 / 순천점</p>
                                            <p class="des2">스마트인재개발원 순천점</p>                                                                                
                                        </div>
                                    </div>
                                </a>
                            </li> 
                        </ul>                           

                    </div>
                </div>                   
            </div>    

            <!-- Best 강의-->     
            <div class="section section03">
                <div class="container">  
                    <div class="title_info">
                        <h2>Best 강의</h2>                            
                        <div class="desc">스마트인재개발원의 대표 강의들을 만나보세요.</div>
                    </div>
                    <ul class="best_list">
                        <li>
                            <a href="#0">
                                <div class="card_item">                                    
                                    <div class="img_area">
                                        <img src="../img/main/best_01.png" alt="인공지능 융합 서비스개발자과정">
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li>
                            <a href="#0">
                                <div class="card_item">                                    
                                    <div class="img_area">
                                        <img src="../img/main/best_02.png" alt="빅데이터 분석서비스 개발자과정">
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li>
                            <a href="#0">
                                <div class="card_item">                                    
                                    <div class="img_area">
                                        <img src="../img/main/best_03.png" alt="지능형 Full-Stack
                                        IoT 전문가과정">
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li>
                            <a href="#0">
                                <div class="card_item">                                    
                                    <div class="img_area">
                                        <img src="../img/main/best_04.png" alt="파이썬 중급 데이터 분석">
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li>
                            <a href="#0">
                                <div class="card_item">                                    
                                    <div class="img_area">
                                        <img src="../img/main/best_05.png" alt="빅데이터 특화 SW캠프">
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li>
                            <a href="#0">
                                <div class="card_item">                                    
                                    <div class="img_area">
                                        <img src="../img/main/best_06.png" alt="인공지능 All-in-One 실무">
                                    </div>
                                </div>
                            </a>
                        </li>   
                        <li>
                            <a href="#0">
                                <div class="card_item">                                    
                                    <div class="img_area">
                                        <img src="../img/main/best_07.png" alt="Python기초 프로그래밍 시작"> 
                                    </div>
                                </div>     
                            </a>
                        </li>   
                        <li>
                            <a href="#0">
                                <div class="card_item">                                    
                                    <div class="img_area">
                                        <img src="../img/main/best_08.png" alt="인공지능 1DAY CLASS">
                                    </div>
                                </div>
                            </a>
                        </li>           
                    </ul>
                </div>
            </div>

            <section class="section section05">
                <div class="container">
                    <div class="popzone">
                        <ul class="pop-latest">
                            <li><a href="#"><img src="../img/main/banner_01.png" class="thumb" alt="진행중인 EVENT 참여해보세요"></a></li>
                            <li><a href="#"><img src="../img/main/banner_02.png" class="thumb" alt="진행중인 EVENT 참여해보세요"></a></li>
                        </ul>                   
                    </div>
                </div>
            </section>

            <!-- 수강후기-->
            <section class="section section04 bgGray">
                <div class="container">        
                    <div class="left_area">
                        <div class="title">
                            <h2>REVIEW</h2>
                            <div class="title_txt">
                                <span>강의 만족도는</span> 
                                <span>실제 수료생의 후기가</span>
                                <span>증명합니다!</span>
                            </div>
                            <div class="desc">
                                <span>홈페이지와 각종 SNS에 있는</span> 
                                <span>후기로 확인하세요!</span>                                 
                            </div>
                            <ul class="btn_list">
                                <li><a href="#0">수강/취업후기 더 보기</a></li>
                                <li><a href="#0">수강생 인터뷰</a></li>
                            </ul>
                        </div>    
                    </div> 
                    <div class="right_area">
                        <div class="review_wrap">
                                                       
                            <ul class="review">
                                <li>
                                    <div class="review_name">                                  
                                        <p class="review_subject">안 맞는 전공이라고만 생각했었습니다.</p>
                                        <p class="review_content">
                                            처음에 수강신청 할때 미래자동차공학인 제 전공을 전공이라고 써야할지 고민할정도로 IT업계에 도전하기엔 애매한 전공이었습니다. 
                                            기초부터 실무까지 탄탄히 다져진 지금은 이제 자신감 있게 전공자라고 말할 수 있게 되었습니다.
                                        </p>
                                        <div class="photo"><img src="../img/main/stu_01.png"></div>
                                        <div class="stu">
                                            <div class="name">유*영 수료생</div>
                                            <div class="txt_info">2022 빅데이터 분석서비스 개발자과정 15기</div>
                                        </div>                                        
                                    </div>
                                </li>
                                <li>
                                    <div class="review_name">                                  
                                        <p class="review_subject">비전공자인데 수료하고 
                                            2주만에 취업을 했습니다!</p>
                                        <p class="review_content">
                                            저는 비전공자인데 수료하고 무려 2주 만에 취업을 했어요! 교육을 들으면서 조금 더 빨리 시작해 볼걸 후회했답니다. 
                                            비전공자라고 망설이는 분들께서 빨리 도전해보세요.. 단언컨데 저도 했으면.. 여러분도 하실 수 있습니다!
                                        </p>                                                                            
                                        <div class="photo"><img src="../img/main/stu_02.png"></div>
                                        <div class="stu">
                                            <div class="name">선경주 수료생</div>
                                            <div class="txt_info">2022 인공지능 융합서비스 개발자 과정 - 1기</div>
                                        </div>                                        
                                    </div>
                                </li>
                                <li>
                                    <div class="review_name">                                  
                                        <p class="review_subject">여자친구랑 같이 와서, 같이 취업했습니다!</p>
                                        <p class="review_content">
                                            여자친구와 저도 비전공자여서 같이 들었는데, 여자친구는 지역 내 규모있는 IT기업 여보야에 취업을 했고 
                                            저는 저를 세심하게 지도했던 선생님과 같은 사람이 되고 싶어 스마트인재개발원에서 강사로 일하고 있습니다.
                                        </p>
                                        <div class="photo"><img src="../img/main/stu_03.png"></div>
                                        <div class="stu">
                                            <div class="name">김민수 수료생</div>
                                            <div class="txt_info">2022 인공지능 융합서비스 개발자과정 12기</div>
                                        </div>                                        
                                    </div>
                                </li>
                            </ul>                                               
                        </div>                                                      
                    </div>                                         
                </div>

            </section>

            <!-- NEWS -->     
            <div class="section section06">
                <div class="container"> 
                    <div class="title_header">
                        <div class="title_info">
                            <h2>스마트인재개발원의 HOT NEWS</h2>                            
                            <div class="desc">성장해나가는 스마트인재개발원의 언론보도 자료와 소식입니다.</div>
                        </div>
                        <div class="btn_area">
                            <a href="#0">뉴스룸</a>
                            <a href="#0">세미콜론 매거진</a>
                        </div>

                    </div>
                    <ul class="news_slider_list">
                        <li>
                            <a href="#0">
                                <div class="card_item">
                                    <div class="image_area">
                                        <div class="img_area">
                                            <img src="https://smhrd.or.kr/wp-content/uploads/2023/07/제목을-입력해주세요_-002-1.jpg">
                                        </div>
                                    </div>
                                    <div class="des">                                       
                                        <p class="des1">세계 44개국에 '광주형 IT·AI 교육 노하우' 전수된다… 
                                            "차현석 스마트인재개발원 
                                            기획실장, 세계 정부·기관 대상 강연 나서!"</p>                            
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li>
                            <a href="#0">
                                <div class="card_item">
                                    <div class="image_area">
                                        <div class="img_area">
                                            <img src="https://smhrd.or.kr/wp-content/uploads/2023/03/우수훈련기관.png">
                                        </div>
                                    </div>
                                    <div class="des">                                       
                                        <p class="des1">스마트인재개발원, 
                                            '직업능력개발훈련 
                                            우수훈련기관 5년인증' 획득</p>                            
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li>
                            <a href="#0">
                                <div class="card_item">
                                    <div class="image_area">
                                        <div class="img_area">
                                            <img src="https://smhrd.or.kr/wp-content/uploads/2022/02/hrd.jpg">
                                        </div>
                                    </div>
                                    <div class="des">                                       
                                        <p class="des1">스마트인재개발원만 가능한 2021년도 수료 3개과정 HRD-Net 만족도 5.0기록!!</p>                            
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li>
                            <a href="#0">
                                <div class="card_item">
                                    <div class="image_area">
                                        <div class="img_area">
                                            <img src="https://smhrd.or.kr/wp-content/uploads/2022/02/aitimes.jpg">
                                        </div>
                                    </div>
                                    <div class="des">                                       
                                        <p class="des1">수강생 프로젝트 작품소개 
                                            및 프로젝트 후기까지! 
                                            AI타임스 언론사 인터뷰</p>                            
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li>
                            <a href="#0">
                                <div class="card_item">
                                    <div class="image_area">
                                        <div class="img_area">
                                            <img src="https://smhrd.or.kr/wp-content/uploads/2022/02/staff.jpg">
                                        </div>
                                    </div>
                                    <div class="des">                                       
                                        <p class="des1">수강생관리 및 강의품질로 직결되는 스마트인재개발원 내부 강사진을 소개합니다!</p>                            
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li>
                            <a href="#0">
                                <div class="card_item">
                                    <div class="image_area">
                                        <div class="img_area">
                                            <img src="https://smhrd.or.kr/wp-content/uploads/2022/02/hrd.jpg">
                                        </div>
                                    </div>
                                    <div class="des">                                       
                                        <p class="des1">수강생 프로젝트 작품소개 
                                            및 프로젝트 후기까지! 
                                            AI타임스 언론사 인터뷰</p>                            
                                    </div>
                                </div>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>

        </main>
        <%@ include file="../inc/hrd_footer.jsp" %>

        <!-- 팝업 보기 -->
        <div class="popup-wrap on">
            <div class="inner-box">
                <div class="head-box">
                    <a href="#0">오늘 하루 열지 않기 <i class="xi-close"></i></a>
                </div>
                <ul id="slides-main">
                    <li>
                        <iframe id="iframe1" src="iframe_main_popup.jsp"></iframe>
                    </li>
                    <li>
                        <iframe id="iframe1" src="iframe_main_popup2.jsp"></iframe>
                    </li>
                    <li>
                        <iframe id="iframe1" src="iframe_main_popup3.jsp"></iframe>
                    </li>
                </ul>
            </div>
            <div class="popup-overlay"></div>
        </div>
        <div class="popup-btn-box">
            <a href="#0" class="popup-close"><i class="xi-close-min"></i></a>
            <a href="#0" class="popup-open"><i class="xi-library-books-o"></i><label class="ui blue small circular label">3</label></a>
        </div>

        <script type="text/javascript">
            $(document).ready(function() {
                var slider = $('#slides-main').slick({
                    arrows: true,
                    autoplay: true,
                    slidesToShow: 2,
                    slidesToScroll: 1,
                    responsive: [
                        { 
                            breakpoint: 950,
                            settings: {
                            slidesToShow: 1
                            }
                        },
                        { 
                            breakpoint: 480,
                            settings: {
                            slidesToShow: 1,
                            arrows: true,
                            dots: true
                            }
                        }

                    ]
                });
            });
        </script>
        
    </div>
</body>

</html>