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
                                    <li>교육신청</li>
                                    <li><span class="current">전체</span></li>
                                </ul>
                            </nav>                                                   
                        </div>
                        <h4 class="title_h1">교육신청</h4>
                    </div>
                    <!-- //h1_area --> 

                    <div class="subMenu">
                        <ul class="menu">
                            <li class="active" style="background: linear-gradient(145deg, #34aaff 15%, #0094fb 100%);"><a href="#">전체</a></li>
                            <li><a href="#">필수교육</a></li>
                            <li><a href="#">직무교육</a></li>  
                            <li><a href="#">인증제 교육</a></li>               
                        </ul>
                    </div>
                    <!-- //2depth--> 
                    
                    <h3 class="subMenu_title">전체</h3>                  

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
                                        <a href="#0"><img src="../img/contents/thumb_06.png" alt="[A-1코스] K-디지털 빅데이터 과정"></a>      
                                    </div>
                                    <div class="course_info">
                                        <div class="card_label">
                                            <span class="label basic bcBlue">필수교육</span>
                                        </div>
                                        <div class="item">
                                            <a href="#0"><h2>[2024년] 보수 B_임상시험코디네이터</h2></a>
                                            <ul>                                            
                                                <li><span>교육신청기간</span>2023.01.01 ~ 2023.01.31</li>
                                                <li><span>수강기간</span>2023.02.01 ~ 2023.07.31 (24시간)</li>
                                                <li><span>교육비용</span><strong class="price">무료</strong></li>
                                            </ul>
                                        </div>                    
                                    </div>
                                    <div class="btn_apply">
                                        <button class="btn type4" style="background-color: #003876; border-color:#003876;">수강신청</button>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="course_con">
                                    <div class="course_img">
                                        <a href="#0"><img src="../img/contents/thumb_07.png" alt="[A-1코스] K-디지털 빅데이터 과정"></a>     
                                    </div>
                                    <div class="course_info">
                                        <div class="card_label">
                                            <span class="label basic bcBlue">직무교육</span>
                                        </div>
                                        <div class="item">
                                            <a href="#0"><h2>소화기내과 8th GSTEC 심포지엄(온라인)</h2></a>
                                            <ul>                                            
                                                <li><span>교육신청기간</span>2023.01.01 ~ 2023.01.31</li>
                                                <li><span>수강기간</span>2023.02.01 ~ 2023.07.31 (4시간)</li>
                                                <li><span>교육비용</span><strong class="price">무료</strong></li>
                                            </ul>
                                        </div>                    
                                    </div>
                                    <div class="btn_apply">
                                        <button class="btn type4" style="background-color: #003876; border-color:#003876;">수강신청</button>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="course_con">
                                    <div class="course_img">
                                        <a href="#0"><img src="../img/contents/thumb_08.png" alt="[A-1코스] K-디지털 빅데이터 과정"></a>      
                                    </div>
                                    <div class="course_info">
                                        <div class="card_label">
                                            <span class="label basic bcBlue">직무교육</span>
                                        </div>
                                        <div class="item">
                                            <a href="#0"><h2>강남세브란스병원 간호국 환자대상 교육프로그램</h2></a>
                                            <ul>                                            
                                                <li><span>교육신청기간</span>2023.01.01 ~ 2023.01.31</li>
                                                <li><span>수강기간</span>2023.02.01 ~ 2023.07.31 (9시간)</li>
                                                <li><span>교육비용</span><strong class="price">80,000원</strong></li>
                                            </ul>
                                        </div>                    
                                    </div>
                                    <div class="btn_apply">
                                        <button class="btn type4" style="background-color: #003876; border-color:#003876;">수강신청</button>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="course_con">
                                    <div class="course_img">
                                        <a href="#0"><img src="../img/contents/thumb_09.png" alt="[A-1코스] K-디지털 빅데이터 과정"></a>   
                                    </div>
                                    <div class="course_info">
                                        <div class="card_label">
                                            <span class="label basic bcBlue">인증제 교육</span>
                                        </div>
                                        <div class="item">
                                            <a href="#0"><h2>대상증후군, 합병증과 예방법 강좌</h2></a>
                                            <ul>                                            
                                                <li><span>교육신청기간</span>2023.01.01 ~ 2023.01.31</li>
                                                <li><span>수강기간</span>2023.02.01 ~ 2023.07.31 (6시간)</li>
                                                <li><span>교육비용</span><strong class="price">40,000원</strong></li>
                                            </ul>
                                        </div>                    
                                    </div>
                                    <div class="btn_apply">
                                        <button class="btn type4" style="background-color: #003876; border-color:#003876;">수강신청</button>
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