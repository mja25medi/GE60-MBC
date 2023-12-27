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
                                    <li>과정검색</li>
                                    <li><span class="current">결과</span></li>
                                </ul>
                            </nav>                                                   
                        </div>
                        <!-- <h4 class="title_h1">과정검색</h4> -->
                    </div>
                    <!-- //h1_area --> 

                   
                    
                    <h3 class="subMenu_title">검색결과</h3>                  

                    <div class="board_info">
                        <div class="page_info">
                            <span class="total_page">전체 <b>0</b>개 </span>
                        </div>
                        <form name="srhForm" method="post" action="board_list" class="board_search">
                            <fieldset class="form-row">
                            <legend class="blind">과정 정렬</legend>
                            <select class="form-select" name="keyField" id="select" title="정렬 순서를 선택하세요.">
                                <option value="" selected="selected">과정별</option>
                                <option value="">원격훈련과정</option>
                                <option value="">집체훈련과정</option>
                                <option value="">혼합훈련과정</option>
                            </select>
                            <select class="form-select" name="keyField" id="select" title="정렬 순서를 선택하세요.">
                                <option value="" selected="selected">선택</option>
                                <option value="">최근 등록순</option>
                                <option value="">높은 가격순</option>
                                <option value="">낮은 가격순</option>
                            </select>
                                                    
                            </fieldset>
                        </form>
                    </div><!-- //board_info -->

                    <!--no content-->
                    <div class="no_content">
                        <div class="no_text">
                            <i class="icon-cont-none ico f170"></i>
                            <span>찾으시는 검색 결과가 없습니다. </span>
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