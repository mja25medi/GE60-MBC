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
                                    <li><span class="current">대관/대여 신청</span></li>
                                </ul>
                            </nav>                                                   
                        </div>
                        <h4 class="title_h1">시설·장비 공유</h4>
                    </div>
                    <!-- //h1_area --> 
                    
                    <div class="subMenu">
                        <ul class="menu">
                            <li><a href="#">시설장비 이용방법</a></li>
                            <li class="active"><a href="#">대관/대여 신청</a></li>  
                            <li><a href="#">예약현황</a></li> 
                            <li><a href="#">예약조회</a></li>     
                        </ul>
                    </div>
                    <!-- //2depth--> 
                    
                    <h3 class="subMenu_title">대관/대여 신청</h3>

                    <ul class="tabs mb0 two_list">
                        <li class="active"><a href="#" title="선택된 탭메뉴">시설</a></li>
                        <li><a href="#" title="비활성 탭메뉴">장비</a></li>
                    </ul>
                    <div class="board_info fac_select">
                        <h3 class="subMenu_title"></h3>
                        <form name="srhForm" method="post" action="board_list" class="board_search">
                            <fieldset class="form-row">
                            <select class="form-select" name="keyField" id="select" title="검색어 분류를 선택하세요.">
                                <option value="ALL" selected="selected">시설 분류 전체</option>
                                <option value="회의실">회의실</option>
                                <option value="대강당">대강당</option>
                            </select>
                            </fieldset>
                        </form>
                    </div>
                    <div class="fac_area">
                        <div class="row">
                            <div class="col-sm-4">
                                <div class="">
                                    <ul class="facList">
                                        <li><img src="https://smhrd.or.kr/wp-content/uploads/2022/02/hq-1-768x512.jpg" class="thumb"></li>
                                        <li><img src="https://smhrd.or.kr/wp-content/uploads/2022/02/hq-2-700x467.jpg" class="thumb"></li>
                                        <li><img src="https://smhrd.or.kr/wp-content/uploads/2022/02/hq-3-700x467.jpg" class="thumb"></li>
                                        <li><img src="https://smhrd.or.kr/wp-content/uploads/2022/02/hq-4-768x512.jpg" class="thumb"></li>
                                        <li><img src="https://smhrd.or.kr/wp-content/uploads/2022/02/hq-5-700x467.jpg" class="thumb"></li>
                                    </ul>                   
                                </div>
                            </div>
                            <div class="col-sm-8">
                                <div class="table2">
                                    <div class="row">
                                        <div class="col">
                                            <span class="title">시설 분류</span>
                                            <div>교육실</div>
                                        </div>
                                        <div class="col">
                                            <span class="title">시설명</span>
                                            <div>오픈랩(OPEN LAB)</div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col">
                                            <span class="title">위치
                                            </span>
                                            <div>메디오피아테크 </div>
                                        </div>
                                        <div class="col">
                                            <span class="title">면적</span>
                                            <div>
                                                55 ㎡
                                            </div>
                                        </div>
                                    </div>
                                                            
                                    <div class="row">
                                        <div class="col">
                                            <span class="title">수용인원</span>
                                            <div>
                                                20명
                                            </div>
                                        </div>
                                        <div class="col">
                                            <span class="title">용도</span>
                                            <div>
                                                교육
                                            </div>
                                        </div>
                                    </div>
    
                                    <div class="row">
                                        <div class="col">
                                            <span class="title">운영시간</span>
                                            <div>
                                                09:00 - 18:00
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col">
                                            <span class="title">부속시설</span>
                                            <div>
                                                빔프로젝트, 강연대
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="btns right mt20">
                                    <button class="btn gray2">시설대관신청</button>                       
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="fac_area">
                        <div class="row">
                            <div class="col-sm-4">
                                <div class="">
                                    <ul class="facList">
                                        <li><img src="../img/contents/501.jpg" class="thumb"></li>
                                        <li><img src="../img/contents/507.jpg" class="thumb"></li>
                                    </ul>                   
                                </div>
                            </div>
                            <div class="col-sm-8">
                                <div class="table2">
                                    <div class="row">
                                        <div class="col">
                                            <span class="title">시설 분류</span>
                                            <div>회의실</div>
                                        </div>
                                        <div class="col">
                                            <span class="title">시설명</span>
                                            <div>회의실 (5층)</div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col">
                                            <span class="title">위치
                                            </span>
                                            <div>메디오피아테크</div>
                                        </div>
                                        <div class="col">
                                            <span class="title">면적</span>
                                            <div>
                                                12 ㎡
                                            </div>
                                        </div>
                                    </div>
                                                            
                                    <div class="row">
                                        <div class="col">
                                            <span class="title">수용인원</span>
                                            <div>
                                                6명
                                            </div>
                                        </div>
                                        <div class="col">
                                            <span class="title">용도</span>
                                            <div>
                                                회의, 발표 등
                                            </div>
                                        </div>
                                    </div>
    
                                    <div class="row">
                                        <div class="col">
                                            <span class="title">운영시간</span>
                                            <div>
                                                09:00 - 18:00
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col">
                                            <span class="title">부속시설</span>
                                            <div>
                                                회의용 대형 모니터
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="btns right mt20">
                                    <button class="btn gray2">시설대관신청</button>                       
                                </div>
                            </div>
                        </div>
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