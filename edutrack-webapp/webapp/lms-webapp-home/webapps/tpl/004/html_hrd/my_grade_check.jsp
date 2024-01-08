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
                       
                    <div class="res_tbl_wrap">
                        <table>
                            <caption>성적조회 목록</caption>
                            <thead>
                                <tr>
                                    <th scope="col" width="7%">번호</th>
                                    <th scope="col">과정명</th>
                                    <th scope="col" width="7%">진도</th>
                                    <th scope="col" width="7%">시험</th>
                                    <th scope="col" width="7%">과제</th>
                                    <th scope="col" width="7%">기타1</th>
                                    <th scope="col" width="7%">총점</th>
                                    <th scope="col" width="8%">수료여부</th>
                                    <th scope="col" width="9%">수료증</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td scope="row" data-label="번호">40</td>
                                    <td class="title" data-label="과정명">파이썬 설치 및 강의 환경 설정</td>
                                    <td data-label="진도">10%</td>
                                    <td data-label="시험">30점</td>
                                    <td data-label="과제">60점</td>
                                    <td data-label="기타1">10점</td>
                                    <td data-label="총점">100점</td>
                                    <td data-label="수료여부">수료</td>
                                    <td data-label="수료증"><button class="btn type6">보기</button></td>
                                </tr>
                                <tr>
                                    <td scope="row" data-label="번호">40</td>
                                    <td class="title" data-label="과정명">파이썬 설치 및 강의 환경 설정</td>
                                    <td data-label="진도">10%</td>
                                    <td data-label="시험">30점</td>
                                    <td data-label="과제">60점</td>
                                    <td data-label="기타1">10점</td>
                                    <td data-label="총점">100점</td>
                                    <td data-label="수료여부">수료</td>
                                    <td data-label="수료증"><button class="btn type6">보기</button></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>

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