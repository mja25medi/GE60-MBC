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
                                    <li>시설·장비 공유</li>
                                    <li><span class="current">예약조회</span></li>
                                </ul>
                            </nav>                                                   
                        </div>
                        <h4 class="title_h1">시설·장비 공유</h4>
                    </div>
                    <!-- //h1_area --> 
                    
                    <div class="subMenu">
                        <ul class="menu">
                            <li><a href="#">시설장비 이용방법</a></li>
                            <li><a href="#">대관/대여 신청</a></li>  
                            <li><a href="#">예약현황</a></li> 
                            <li class="active"><a href="#">예약조회</a></li>     
                        </ul>
                    </div>
                    <!-- //2depth--> 
                    
                    <h3 class="subMenu_title">예약조회</h3>

                    <ul class="tabs mb0 two_list">
                        <li class="active"><a href="#" title="선택된 탭메뉴">시설</a></li>
                        <li><a href="#" title="비활성 탭메뉴">장비</a></li>
                    </ul>
                    
                    <div class="res_tbl_wrap mt20">
                        <table>
                            <caption>예약조회 목록</caption>
                            <thead>
                                <tr>
                                    <th scope="col" width="10%">번호</th>
                                    <th scope="col">시설명</th>
                                    <th scope="col" width="10%">이용인원</th>
                                    <th scope="col" width="20%">이용날짜/시간</th>
                                    <th scope="col" width="10%">신청자</th>
                                    <th scope="col" width="12%">신청일시</th>
                                    <th scope="col" width="10%">상태</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td scope="row" data-label="번호">3</td>
                                    <td scope="row" class="title" data-label="시설명">오픈랩 (OPEN LAB)</td>
                                    <td data-label="이용인원">10명</td>
                                    <td data-label="이용날짜/시간">2023.11.03(금)  09:00 ~ 10:00</td>
                                    <td data-label="신청자">홍길동</td>
                                    <td data-label="신청일시">2023.10.28</td>
                                    <td data-label="상태"><label class="btn3 sm solid fcDarkgray">예약대기</label></td>
                                </tr>
                                <tr>
                                    <td scope="row" data-label="번호">2</td>
                                    <td scope="row" class="title" data-label="시설명">강의실 (202호)</td>
                                    <td data-label="이용인원">10명</td>
                                    <td data-label="이용날짜/시간">2023.11.03(금)  09:00 ~ 10:00</td>
                                    <td data-label="신청자">홍길동</td>
                                    <td data-label="신청일시">2023.10.28</td>
                                    <td data-label="상태"><label class="btn3 sm solid fcBlue">예약완료</label></td>
                                </tr>
                                <tr>
                                    <td scope="row" data-label="번호">1</td>
                                    <td scope="row" class="title" data-label="시설명">회의실 (2층)</td>
                                    <td data-label="이용인원">10명</td>
                                    <td data-label="이용날짜/시간">2023.11.03(금)  09:00 ~ 10:00</td>
                                    <td data-label="신청자">홍길동</td>
                                    <td data-label="신청일시">2023.10.28</td>
                                    <td data-label="상태"><label class="btn3 sm solid fcBlue">예약완료</label></td>
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