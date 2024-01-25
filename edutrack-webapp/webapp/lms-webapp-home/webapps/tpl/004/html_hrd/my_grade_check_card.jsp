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
                                    <li>나의 강의실</li>
                                    <li><span class="current">종료과정 성적조회</span></li>
                                </ul>
                            </nav>                                                   
                        </div>
                        <h4 class="title_h1">나의 강의실</h4>
                    </div>
                    <!-- //h1_area --> 
                    
                    <div class="subMenu">
                        <ul class="menu">
                            <li><a href="#">나의 학습</a></li>
                            <li class="active"><a href="#">종료과정 성적조회</a></li>  
                            <li><a href="#">설문</a></li> 
                            <li><a href="#">과정별 학습 문의</a></li>        
                        </ul>
                    </div>
                    <!-- //2depth--> 
                    
                    <h3 class="subMenu_title">종료과정 성적조회</h3>
                       
                    <div class="myCourseList col4 finish">
                        <ul class="list_ul">
                            <li class="list_li">
                                <div class="item">
                                    <div class="item_txt">  
                                        <div class="card_label">
                                            <span class="label basic bcBlue">인공지능</span>
                                        </div> 
                                        <div class="title">[A-2코스] 융합 서비스개발자과정</div> 
                                        <ul class="grade_info">
                                            <li><span>진도</span>80%</li>
                                            <li><span>시험</span>30점</li>
                                            <li><span>과제</span>50점</li>
                                            <li><span>수업태도</span>5점</li>
                                            <li><span>참여도</span>5점</li>
                                            <li class="total"><span>총점</span>90점</li>
                                            <li class="cer_info"><span>수료여부</span>수료</li>
                                        </ul>
                                    </div>
                                    <div class="bottom_button">
                                        <button class="go">수료증 보기</button>
                                    </div>                                        
                                </div>
                            </li>
                            <li class="list_li">
                                <div class="item">
                                    <div class="item_txt">  
                                        <div class="card_label">
                                            <span class="label basic bcBlue">인공지능</span>
                                        </div> 
                                        <div class="title">[[A-1코스] K-디지털 빅데이터 과정</div> 
                                        <ul class="grade_info">
                                            <li><span>진도</span>10%</li>
                                            <li><span>시험</span>10점</li>
                                            <li><span>과제</span>20점</li>
                                            <li><span>수업태도</span>5점</li>
                                            <li><span>참여도</span>5점</li>
                                            <li><span>기타</span>0점</li>
                                            <li class="total"><span>총점</span>40점</li>
                                            <li class="cer_info"><span>수료여부</span>미수료</li>
                                        </ul>
                                    </div>
                                    <div class="bottom_button">
                                        <button class="go disabled">수료증 보기</button>
                                    </div>                                        
                                </div>
                            </li>
                            <li class="list_li">
                                <div class="item">
                                    <div class="item_txt">  
                                        <div class="card_label">
                                            <span class="label basic bcBlue">인공지능</span>
                                        </div> 
                                        <div class="title">파이썬 설치 및 강의 환경 설정</div> 
                                        <ul class="grade_info">
                                            <li><span>진도</span>10%</li>
                                            <li><span>시험</span>10점</li>
                                            <li><span>과제</span>20점</li>
                                            <li><span>기타1</span>0점</li>
                                            <li><span>기타2</span>0점</li>
                                            <li class="total"><span>총점</span>40점</li>
                                            <li class="cer_info"><span>수료여부</span>미수료</li>
                                        </ul>
                                    </div>
                                    <div class="bottom_button">
                                        <button class="go disabled">수료증 보기</button>
                                    </div>                                        
                                </div>
                            </li>
                            <li class="list_li">
                                <div class="item">
                                    <div class="item_txt">  
                                        <div class="card_label">
                                            <span class="label basic bcBlue">인공지능</span>
                                        </div> 
                                        <div class="title">융합 서비스개발자과정</div> 
                                        <ul class="grade_info">
                                            <li><span>진도</span>80%</li>
                                            <li><span>시험</span>30점</li>
                                            <li><span>과제</span>50점</li>
                                            <li><span>수업태도</span>5점</li>
                                            <li><span>참여도</span>5점</li>
                                            <li class="total"><span>총점</span>90점</li>
                                            <li class="cer_info"><span>수료여부</span>수료</li>
                                        </ul>
                                    </div>
                                    <div class="bottom_button">
                                        <button class="go">수료증 보기</button>
                                    </div>                                        
                                </div>
                            </li>
                        </ul>
                    </div><!--//myCourseList-->

                    <div class="board_pager">
                        <span class="inner">
                            <a href="" class="page_first" title="첫페이지"><i class="xi-angle-left-min"></i><span class="sr_only">첫페이지</span></a>
                            <a href="" class="page_prev" title="이전페이지"><i class="xi-angle-left-min"></i><span class="sr_only">이전페이지</span></a>
                            <a href="" class="page_now" title="1페이지"><strong>1</strong></a>
                            <a href="" class="page_none" title="2페이지">2</a>
                            <a href="" class="page_none" title="3페이지">3</a>
                            <a href="" class="page_next" title="다음페이지"><i class="xi-angle-right-min"></i><span class="sr_only">다음페이지</span></a>
                            <a href="" class="page_last" title="마지막페이지"><i class="xi-angle-right-min"></i><span class="sr_only">마지막페이지</span></a>
                        </span>
                    </div>
                    <!-- //board_pager -->
                       

                </div>
                <!-- //content -->
            </div>
            <!-- //contentWrap -->
              

        </main>

        <%@ include file="../inc/hrd_footer.jsp" %>
       
    </div>
</body>

</html>