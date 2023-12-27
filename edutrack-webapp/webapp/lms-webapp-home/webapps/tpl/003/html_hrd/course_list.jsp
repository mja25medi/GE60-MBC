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
                                    <li>수강신청</li>
                                    <li><span class="current">원격훈련과정</span></li>
                                </ul>
                            </nav>                                                   
                        </div>
                        <h4 class="title_h1">수강신청</h4>
                    </div>
                    <!-- //h1_area --> 

                    <div class="subMenu">
                        <ul class="menu">
                            <li class="active"><a href="#">원격훈련과정</a></li>
                            <li><a href="#">집체훈련과정</a></li>  
                            <li><a href="#">혼합훈련과정</a></li>        
                        </ul>
                    </div>
                    <!-- //2depth--> 
                    
                    <h3 class="subMenu_title">원격훈련과정</h3>                  

                    <div class="board_info">
                        <div class="page_info">
                            <span class="total_page">강의수 <b>12</b>개 </span>
                        </div>
                        <form name="srhForm" method="post" action="board_list" class="board_search">
                            <fieldset class="form-row">
                            <legend class="blind">과정 정렬</legend>
                            <select class="form-select" name="keyField" id="select" title="정렬 순서를 선택하세요.">
                                <option value="" selected="selected">선택</option>
                                <option value="">최근 등록순</option>
                                <option value="">높은 가격순</option>
                                <option value="">낮은 가격순</option>
                            </select>
                            <select class="form-select" name="keyField" id="select" title="리스트수를 선택하세요.">
                                <option value="" selected="selected">리스트수</option>
                                <option value="">10</option>
                                <option value="">20</option>
                                <option value="">50</option>
                            </select>                            
                            </fieldset>
                        </form>
                    </div><!-- //board_info -->
                    
                    <div class="course_list">
                        <ul class="courseList">
                            <li>
                                <div class="course_con">
                                    <div class="course_img">
                                        <a href="#0"><img src="../img/contents/thumb_01.png" alt="[A-1코스] K-디지털 빅데이터 과정"></a>      
                                    </div>
                                    <div class="course_info">
                                        <div class="card_label">
                                            <span class="label basic bcBlue">인공지능</span>
                                        </div>
                                        <div class="item">
                                            <a href="#0"><h2>[A-2코스] 융합 서비스개발자과정 / 광주점</h2></a>
                                            <ul>                                            
                                                <li><span>교육신청기간</span>2023.01.01 ~ 2023.01.31</li>
                                                <li><span>수강기간</span>2023.02.01 ~ 2023.07.31 (960시간)</li>
                                                <li><span>교육비용</span><strong class="price">무료 / 300,000원</strong></li>
                                            </ul>
                                        </div>                    
                                    </div>
                                    <div class="btn_apply">
                                        <button class="btn type4">수강신청</button>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="course_con">
                                    <div class="course_img">
                                        <a href="#0"><img src="../img/contents/thumb_02.png" alt="[A-1코스] K-디지털 빅데이터 과정"></a>     
                                    </div>
                                    <div class="course_info">
                                        <div class="card_label">
                                            <span class="label basic bcBlue">인공지능</span>
                                        </div>
                                        <div class="item">
                                            <a href="#0"><h2>[A-2코스] 융합 서비스개발자과정 / 광주점</h2></a>
                                            <ul>                                            
                                                <li><span>교육신청기간</span>2023.01.01 ~ 2023.01.31</li>
                                                <li><span>수강기간</span>2023.02.01 ~ 2023.07.31 (960시간)</li>
                                                <li><span>교육비용</span><strong class="price">무료 / 300,000원</strong></li>
                                            </ul>
                                        </div>                    
                                    </div>
                                    <div class="btn_apply">
                                        <button class="btn type4">수강신청</button>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="course_con">
                                    <div class="course_img">
                                        <a href="#0"><img src="../img/contents/thumb_03.png" alt="[A-1코스] K-디지털 빅데이터 과정"></a>      
                                    </div>
                                    <div class="course_info">
                                        <div class="card_label">
                                            <span class="label basic bcBlue">인공지능</span>
                                        </div>
                                        <div class="item">
                                            <a href="#0"><h2>[A-2코스] 융합 서비스개발자과정 / 광주점</h2></a>
                                            <ul>                                            
                                                <li><span>교육신청기간</span>2023.01.01 ~ 2023.01.31</li>
                                                <li><span>수강기간</span>2023.02.01 ~ 2023.07.31 (960시간)</li>
                                                <li><span>교육비용</span><strong class="price">무료 / 300,000원</strong></li>
                                            </ul>
                                        </div>                    
                                    </div>
                                    <div class="btn_apply">
                                        <button class="btn type4">수강신청</button>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="course_con">
                                    <div class="course_img">
                                        <a href="#0"><img src="../img/contents/thumb_04.png" alt="[A-1코스] K-디지털 빅데이터 과정"></a>   
                                    </div>
                                    <div class="course_info">
                                        <div class="card_label">
                                            <span class="label basic bcBlue">인공지능</span>
                                        </div>
                                        <div class="item">
                                            <a href="#0"><h2>[A-2코스] 융합 서비스개발자과정 / 광주점</h2></a>
                                            <ul>                                            
                                                <li><span>교육신청기간</span>2023.01.01 ~ 2023.01.31</li>
                                                <li><span>수강기간</span>2023.02.01 ~ 2023.07.31 (960시간)</li>
                                                <li><span>교육비용</span><strong class="price">무료 / 300,000원</strong></li>
                                            </ul>
                                        </div>                    
                                    </div>
                                    <div class="btn_apply">
                                        <button class="btn type4">수강신청</button>
                                    </div>
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