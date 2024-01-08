<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
        <main class="main">
            

            <div id="content">
                
                <!-- mainvisual -->
                <div class="mainvisual">
                    
                    <!-- search -->
                    <div class="sch_layer">                            
                        <form class="sch_form" name="sch_form" id="schForm">                               
                            <label for="allKeyWord" class="title">통합검색</label>
                            <input type="text" name="allKeyWord" id="allKeyWord" placeholder="강좌명을 검색하세요">
                            <button id="btnSch" class="btn_sch">
                                <i class="xi-search" aria-hidden="true"></i><span class="sr-only">검색버튼</span>
                            </button>
                        </form>
                        <button type="button" class="btn_sch_close">
                            <i class="xi-close-thin" aria-hidden="true"></i><span class="sr-only">검색 닫기</span>
                        </button>                            
                    </div>

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
                        <div href="#" class="slide vm01" style="background: #1F1F1F url(/tpl/004/img/main/visual_01.jpg) center top no-repeat;">
                            <div class="txt-box">
                                <p class="tit">DONGGUK UNIVERSITY WISE</p>
                                <p class="content">OPEN CAMPUS</p>
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
                             
            </div>
            <!-- //mainvisual -->

            <!-- 공지, 분류별강좌 -->
            <div class="section section01">
                <div class="container">

                    
                        <div class="boardList">
                            <div class="title">
                                <h2>공지사항</h2>
                                <a href="#" class="more"><span class="sr_only">더보기</span><i class="xi-plus" aria-hidden="true"></i></a> 
                            </div>
                            <ul class="board-latest">
                                <li><a href="#">
                                    <span class="boardTxt">
                                        <span class="notice">[특강]</span>
                                        집콕클라쓰 멘토썸머특강
                                    </span>
                                    <span class="boardDate">2023.12.23</span>
                                </a></li>
                                <li><a href="#">
                                    <span class="boardTxt">
                                        <span class="notice">[공지]</span>
                                        맞춤형멘토링 우수팀 인터뷰 1편
                                    </span>
                                    <span class="boardDate">2023.10.05</span>
                                </a></li>
                                <li><a href="#">
                                    <span class="boardTxt">
                                        <span class="notice">[특강]</span>
                                        학습법특강(선배라떼 질의응답 특강)
                                    </span>
                                    <span class="boardDate">2023.08.16</span>
                                </a></li>
                                <li><a href="#">
                                    <span class="boardTxt">
                                        <span class="notice">[공지]</span>
                                        2023-1학기 원격강좌 수강 안내
                                    </span>
                                    <span class="boardDate">2023.08.16</span>
                                </a></li>
                                
                            </ul>                            
                        </div>  
                        
                        <div class="iconList">
                            <ul class="list_group">
                                <li>
                                    <h2 class="list_tit">분류별 <br>공개강좌</h2> 
                                </li>
                                <li>
                                    <a href="#">
                                        <img src="/tpl/004/img/main/sect02_icon01.svg" alt="인문과학 아이콘">
                                        <p class="card_tit">인문과학</p>                                        
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <img src="/tpl/004/img/main/sect02_icon02.svg" alt="사회과학 아이콘">
                                        <p class="card_tit">사회과학</p>                                       
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <img src="/tpl/004/img/main/sect02_icon03.svg" alt="공학 아이콘">
                                        <p class="card_tit">공학</p>                                        
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <img src="/tpl/004/img/main/sect02_icon04.svg" alt="자연과학 아이콘">
                                        <p class="card_tit">자연과학</p>                                        
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <img src="/tpl/004/img/main/sect02_icon05.svg" alt="교육학 아이콘">
                                        <p class="card_tit">교육학</p>                                        
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <img src="/tpl/004/img/main/sect02_icon06.svg" alt="의약학 아이콘">
                                        <p class="card_tit">의약학</p>                                        
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <img src="/tpl/004/img/main/sect02_icon07.svg" alt="예술체육 아이콘">
                                        <p class="card_tit">예술체육</p>                                        
                                    </a>
                                </li>
                            </ul>
                                                     
                        </div>
                    

                </div>
            </div>
           
            <!-- 전액무료 국비과정-->
            <div class="section section02">
                <div class="container">                        
                   
                    <div class="con_area">
                        <div class="slider_info">
                            <h2>교육과정</h2>                            
                            <a href="#" class="more"><span class="sr_only">더보기</span><i class="xi-angle-right" aria-hidden="true"></i></a>
                        
                            <div class="sort">
                                <button type="button" class="active">전체</button>
                                <button type="button">자격증과정</button>
                                <button type="button">전문교육과정</button>
                                <button type="button">교양교육과정</button>
                                <button type="button">WISE 아카데미</button>
                                <button type="button">위탁교육과정</button>
                            </div>
                                                             
                        </div>
                        <ul class="lecture_list">
                            <li>
                                <a href="#0">
                                    <div class="card_item">
                                        <div class="image_area">
                                            <div class="img_area">
                                                <img src="/tpl/004/img/contents/thumb_01.png" alt="실무에 바로 적용하는 엑셀 2023">
                                            </div>
                                        </div>
                                        <div class="des">
                                            <p class="des1">실무에 바로 적용하는 엑셀 2023</p>
                                            <div class="card_labels">
                                                <span>OA</span>
                                            </div>                                                                              
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="#0">
                                    <div class="card_item">
                                        <div class="image_area">
                                            <div class="img_area">
                                                <img src="/tpl/004/img/contents/thumb_02.png" alt="[공공특화] 공공데이터 분석에서 보고서 작성까지 All in One">
                                            </div>
                                        </div>
                                        <div class="des">
                                            <p class="des1">[공공특화] 공공데이터 분석에서 보고서 작성까지 All in One</p> 
                                            <div class="card_labels">
                                                <span>데이터분석</span>
                                            </div>                                                                              
                                        </div>
                                    </div>
                                </a>
                            </li>                                                        
                            <li>
                                <a href="#0">
                                    <div class="card_item">
                                        <div class="image_area">
                                            <div class="img_area">
                                                <img src="/tpl/004/img/contents/thumb_03.png" alt="(신규)MBTI 심리상담지도자 2급과정">
                                            </div>
                                        </div>
                                        <div class="des">
                                            <p class="des1">(신규)MBTI 심리상담지도자 2급과정</p>
                                            <div class="card_labels">
                                                <span>심리상담</span>
                                            </div>                                                                               
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="#0">
                                    <div class="card_item">
                                        <div class="image_area">
                                            <div class="img_area">
                                                <img src="/tpl/004/img/contents/thumb_04.png" alt="사회지도자 전문교육과정">
                                            </div>
                                        </div>
                                        <div class="des">
                                            <p class="des1">사회지도자 전문교육과정</p>  
                                            <div class="card_labels">
                                                <span>인문</span>
                                                <span>자격증</span>
                                            </div>                                                                   
                                        </div>
                                    </div>
                                </a>
                            </li>                        
                            <li>
                                <a href="#0">
                                    <div class="card_item">
                                        <div class="image_area">
                                            <div class="img_area">
                                                <img src="/tpl/004/img/contents/thumb_05.png" alt="테마로 보는 서양미술의 흐름">
                                            </div>
                                        </div>
                                        <div class="des">
                                            <p class="des1">테마로 보는 서양미술의 흐름</p> 
                                            <div class="card_labels">
                                                <span>미술</span>
                                                <span>교양교육</span>
                                            </div>                                                                        
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="#0">
                                    <div class="card_item">
                                        <div class="image_area">
                                            <div class="img_area">
                                                <img src="/tpl/004/img/contents/thumb_01.png" alt="실무에 바로 적용하는 엑셀 2023">
                                            </div>
                                        </div>
                                        <div class="des">
                                            <p class="des1">실무에 바로 적용하는 엑셀 2023</p>
                                            <div class="card_labels">
                                                <span>OA</span>
                                            </div>                                                                              
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="#0">
                                    <div class="card_item">
                                        <div class="image_area">
                                            <div class="img_area">
                                                <img src="/tpl/004/img/contents/thumb_02.png" alt="[공공특화] 공공데이터 분석에서 보고서 작성까지 All in One">
                                            </div>
                                        </div>
                                        <div class="des">
                                            <p class="des1">[공공특화] 공공데이터 분석에서 보고서 작성까지 All in One</p> 
                                            <div class="card_labels">
                                                <span>데이터분석</span>
                                            </div>                                                                              
                                        </div>
                                    </div>
                                </a>
                            </li>                                                        
                            <li>
                                <a href="#0">
                                    <div class="card_item">
                                        <div class="image_area">
                                            <div class="img_area">
                                                <img src="/tpl/004/img/contents/thumb_03.png" alt="(신규)MBTI 심리상담지도자 2급과정">
                                            </div>
                                        </div>
                                        <div class="des">
                                            <p class="des1">(신규)MBTI 심리상담지도자 2급과정</p>
                                            <div class="card_labels">
                                                <span>심리상담</span>
                                            </div>                                                                               
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
                        <h2>인기 강의</h2>                            
                        <div class="desc">가장 많이 찾아본 콘텐츠</div>
                        <div class="tags">
                            <a href="#0" class="tag">#인공지능</a>
                            <a href="#0" class="tag">#메타버스</a>
                            <a href="#0" class="tag">#4차산업혁명</a>
                            <a href="#0" class="tag">#경제</a>
                            <a href="#0" class="tag">#중국어</a>
                        </div>
                    </div>
                    
                    <ul class="best_list">
                        <li>
                            <a href="#0">
                                <div class="card_item">                                    
                                    <div class="img_area">
                                        <img src="/tpl/004/img/main/best_01.png" alt="인공지능 융합 서비스개발자과정">
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li>
                            <a href="#0">
                                <div class="card_item">                                    
                                    <div class="img_area">
                                        <img src="/tpl/004/img/main/best_02.png" alt="빅데이터 분석서비스 개발자과정">
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li>
                            <a href="#0">
                                <div class="card_item">                                    
                                    <div class="img_area">
                                        <img src="/tpl/004/img/main/best_03.png" alt="지능형 Full-Stack IoT 전문가과정">
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li>
                            <a href="#0">
                                <div class="card_item">                                    
                                    <div class="img_area">
                                        <img src="/tpl/004/img/main/best_04.png" alt="빅데이터">
                                    </div>
                                </div>
                            </a>
                        </li>
                           
                    </ul>
                </div>
            </div>

           
           
        </main>
	