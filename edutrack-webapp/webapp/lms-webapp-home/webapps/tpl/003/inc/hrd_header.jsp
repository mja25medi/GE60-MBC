<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<header class="header">            
    <div class="logo_search">
        <div class="container">
            <h1 class="logo">
                <a href="#0" class="link">
                    <span class="sr-only">스마트인재개발원</span>
                </a>
            </h1>
            <button id="btn_sch" class="btn_sch_open">
                <i class="xi-search" aria-hidden="true"></i><span class="sr-only">검색버튼</span><!-- 모바일 검색버튼-->
            </button> 
            <div class="sch_layer">                            
                <form class="sch_form" name="sch_form" id="schForm">                               
                    <label for="allKeyWord" class="title">통합검색</label>
                    <input type="text" name="allKeyWord" id="allKeyWord" placeholder="과정명을 검색하세요">
                    <button id="btnSch" class="btn_sch">
                        <i class="xi-search" aria-hidden="true"></i><span class="sr-only">검색버튼</span>
                    </button>
                </form>
                <button type="button" class="btn_sch_close">
                    <i class="xi-close-thin" aria-hidden="true"></i><span class="sr-only">검색 닫기</span>
                </button>                            
            </div>
            <div class="gnb_util">
                
                <div class="member_area">
                    <div class="login">
                        <ul class="nav"> 
                            <li><a href="" class=""><span>로그인</span></a></li>
                            <li><a href="" class=""><span>회원가입</span></a></li>
                            
                        </ul>
                    </div>
                    <!-- <div class="logout">
                        <span class="welcome"><span class="text"><strong>홍길동</strong>님</span> <em class="badge">3</em></span>
                        <ul class="nav">
                            <li><a href="" class=""><span>로그아웃</span></a></li>
                            <li><a href="" class=""><span>개인정보수정</span></a></li>
                        </ul>
                    </div> -->
                </div>
                <!-- //member_area -->
                                  
            </div>
            <!-- //gnb_util -->
        </div>
        <!-- //container -->
    </div>
    <!--//logo_search-->

    <div class="gnb_area">
        <nav class="nav container" id="gnb">
            <button type="button" class="btn_allmenu"><span class="sr-only">전체메뉴보기</span><i class="xi-bars"></i></button>
            <h2 class="pop_title">전체메뉴</h2>
            
            <ul id="head_menu" class="topmenu">
                <li class="depth1">
                    <a href="#0"><span>KDP센터 소개</span></a>
                    <div class="submenu">                    
                        <ul class="depth2">             
                            <li><a href="#0">KDP 사업소개</a></li>
                            <li><a href="#0">KDP 센터소개</a></li>
                            <li><a href="#0">인사말</a></li>
                            <li><a href="#0">찾아오시는 길</a></li>
                            <li><a href="#0">조직도</a></li>
                        </ul>
                    </div>
                </li>
                <li class="depth1">
                    <a href="#0"><span>개발원 소개</span></a>
                    <div class="submenu">                    
                        <ul class="depth2">       
                            <li><a href="#0">개발원 이야기</a></li>      
                            <li><a href="#0">조직현황/강사진</a></li>
                            <li><a href="#0">세미콜론 매거진</a></li>
                            <li><a href="#0">인재채용</a></li>
                            <li><a href="#0">뉴스룸</a></li>
                            <li><a href="#0">오시는길</a></li>
                        </ul>
                    </div>
                </li>
                <li class="depth1">
                    <a href="#0"><span>교육안내</span></a>
                    <div class="submenu">                       
                        <ul class="depth2">    
                            <li><a href="#0">훈련과정</a></li>         
                            <li><a href="#0">고용보험 환급제도</a></li>
                            <li><a href="#0">훈련비 환급방법</a></li>
                            <li><a href="#0">훈련 진행절차</a></li>
                            <li><a href="#0">훈련진행 유의사항</a></li>
                            <li><a href="#0">모사답안 처리기준</a></li>
                        </ul>
                    </div>
                </li>
                <li class="depth1">
                    <a href="#0"><span>수강신청</span></a>
                    <div class="submenu">                       
                        <ul class="depth2">             
                            <li><a href="#0">원격훈련과정</a></li>
                            <li><a href="#0">집체훈련과정</a></li>
                            <li><a href="#0">혼합훈련과정</a></li>
                        </ul>
                    </div>
                </li>
                <li class="depth1">
                    <a href="#0"><span>시설·장비 공유</span></a>
                    <div class="submenu">                       
                        <ul class="depth2">             
                            <li><a href="#0">시설·장비 이용방법</a></li>
                            <li><a href="#0">대관·대여 신청</a></li>
                            <li><a href="#0">예약현황</a></li>
                            <li><a href="#0">예약조회</a></li>
                        </ul>
                    </div>
                </li>                                        
                <li class="depth1">
                    <a href="#0"><span>나의 강의실</span></a>
                    <div class="submenu">                        
                        <ul class="depth2">
                            <li><a href="#0">나의 학습</a></li>
                            <li><a href="#0">종료과정 성적조회</a></li>
                            <li><a href="#0">수료증</a></li>
                            <li><a href="#0">설문</a></li>
                            <li><a href="#0">과정별 학습 문의</a></li>
                        </ul>
                    </div>
                </li> 
                <li class="depth1">
                    <a href="#0"><span>마이 페이지</span></a>
                    <div class="submenu">                        
                        <ul class="depth2">
                            <li><a href="#0">장바구니</a></li>
                            <li><a href="#0">결제내역</a></li>
                            <li><a href="#0">문의내역</a></li>
                            <li><a href="#0">받은쪽지</a></li>
                            <li><a href="#0">개인정보 수정</a></li>
                        </ul>
                    </div>
                </li> 
                <li class="depth1">
                    <a href="#0"><span>지원센터</span></a>
                    <div class="submenu">                        
                        <ul class="depth2">
                            <li><a href="#0">공지사항</a></li>
                            <li><a href="#0">학습자료실</a></li>
                            <li><a href="#0">1:1문의하기</a></li>
                            <li><a href="#0">FAQ</a></li>
                        </ul>
                    </div>
                </li>                                                                                  
            </ul>
            <button type="button" class="pop_close"><i class="xi-close"></i><span class="sr-only">전체메뉴 레이어
                    닫기</span></button>
        </nav>
    </div>
    <!-- //gnb_area -->
</header>
