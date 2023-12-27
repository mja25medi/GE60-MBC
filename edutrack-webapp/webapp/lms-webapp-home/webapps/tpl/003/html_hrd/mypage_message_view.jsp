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
                                    <li>마이 페이지</li>
                                    <li><span class="current">받은쪽지</span></li>
                                </ul>
                            </nav>                                                   
                        </div>
                        <h4 class="title_h1">마이 페이지</h4>
                    </div>
                    <!-- //h1_area --> 
                    
                    <div class="subMenu">
                        <ul class="menu">
                            <li><a href="#">장바구니</a></li>
                            <li><a href="#">결제내역</a></li>  
                            <li><a href="#">문의내역</a></li> 
                            <li class="active"><a href="#">받은쪽지</a></li>    
                            <li><a href="#">개인정보 수정</a></li>  
                            <li><a href="#">아바타 편집</a></li>      
                        </ul>
                    </div>
                    <!-- //2depth--> 
                    
                    <h3 class="subMenu_title">받은 쪽지</h3>
                                   
                    <div class="table_list">
                        <ul class="list">
                            <li class="head"><label>제목</label></li>
                            <li>쪽지를 확인해주세요.</li>                          
                        </ul>
                        <ul class="list">
                            <li class="head"><label>보낸사람</label></li>
                            <li>관리자</li>
                            <li class="head"><label>보낸날짜</label></li>
                            <li>2023.10.28 10:00:00</li>
                        </ul>
                        <ul class="list">
                            <li class="head"><label>내용</label></li>
                            <li>K-디지털 빅데이터 과정 관련하여 안내 드립니다.<br> 
                                현재 강의 진행하고 있는 강의실 변경이 있습니다. <br> 
                                204호에서 201로 변경되오니 확인 후 참석해주세요.<br><br>
                                변경날짜: 12월 4일(월) ~ 강의 종료까지 <br><br> 
                                감사합니다.
                            </li>                          
                        </ul>                                     
                    </div>
                    <div class="btns mt30">
                        <button type="button" class="btn gray2">목록</button>
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