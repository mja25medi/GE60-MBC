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
                                    <li><span class="current">설문</span></li>
                                </ul>
                            </nav>                                                   
                        </div>
                        <h4 class="title_h1">나의 강의실</h4>
                    </div>
                    <!-- //h1_area --> 
                    
                    <div class="subMenu">
                        <ul class="menu">
                            <li><a href="#">나의 학습</a></li>
                            <li><a href="#">종료과정 성적조회</a></li>  
                            <li class="active"><a href="#">설문</a></li> 
                            <li><a href="#">과정별 학습 문의</a></li>        
                        </ul>
                    </div>
                    <!-- //2depth--> 
                    
                    <h3 class="subMenu_title">설문</h3>                   

                    <div class="table_list">
                        <ul class="list">
                            <li class="head"><label>설문명</label></li>
                            <li>교수님의 수업내용 및 수업방법에 대하여 개선을 위한 설문입니다.</li>
                        </ul>
                        <ul class="list">
                            <li class="head"><label>설문기간</label></li>
                            <li>2023.10.12 00:00~2023.10.30 23:59</li>
                        </ul>
                        <ul class="list">
                            <li class="head"><label>설문내용</label></li>
                            <li>설문내용입니다.</li>
                        </ul>
                    </div>

                    <div class="survey_area mt30">
                        <div class="survey_list">
                            <div class="title">1. 수업이 강의 계획서에 따라 진행 되었는가?<small>(선택형)</small></div>
                            <ol>
                                <li>
                                    <div class="custom-input">
                                        <input type="radio" name="survey1" id="chk0101">
                                        <label for="chk0101">매우그렇다</label>
                                    </div>
                                </li>
                                <li>
                                    <div class="custom-input">
                                        <input type="radio" name="survey1" id="chk0102">
                                        <label for="chk0102">그렇다</label>
                                    </div>
                                </li>
                                <li>
                                    <div class="custom-input">
                                        <input type="radio" name="survey1" id="chk0103">
                                        <label for="chk0103">보통이다</label>
                                    </div>
                                </li>
                                <li>
                                    <div class="custom-input">
                                        <input type="radio" name="survey1" id="chk0104">
                                        <label for="chk0104">아니다</label>
                                    </div>
                                </li>
                                <li>
                                    <div class="custom-input">
                                        <input type="radio" name="survey1" id="chk0105">
                                        <label for="chk0105">절대아니다</label>
                                    </div>
                                </li>
                            </ol>
                        </div>
                        <div class="survey_list">
                            <div class="title">2. 수업시간 준수는 잘 하였는가?<small>(선택형)</small></div>
                            <ol>
                                <li>
                                    <div class="custom-input">
                                        <input type="radio" name="survey2" id="chk0201">
                                        <label for="chk0201">매우그렇다</label>
                                    </div>
                                </li>
                                <li>
                                    <div class="custom-input">
                                        <input type="radio" name="survey2" id="chk0202">
                                        <label for="chk0202">그렇다</label>
                                    </div>
                                </li>
                                <li>
                                    <div class="custom-input">
                                        <input type="radio" name="survey2" id="chk0203">
                                        <label for="chk0203">보통이다</label>
                                    </div>
                                </li>
                                <li>
                                    <div class="custom-input">
                                        <input type="radio" name="survey2" id="chk0204">
                                        <label for="chk0204">아니다</label>
                                    </div>
                                </li>
                                <li>
                                    <div class="custom-input">
                                        <input type="radio" name="survey2" id="chk0205">
                                        <label for="chk0205">절대아니다</label>
                                    </div>
                                </li>
                            </ol>
                        </div>
                        <div class="survey_list">
                            <div class="title">3. 교수는 열정적으로 수업을 진행 하였는가?<small>(선택형)</small></div>
                            <ol>
                                <li>
                                    <div class="custom-input">
                                        <input type="radio" name="survey3" id="chk0301">
                                        <label for="chk0301">매우그렇다</label>
                                    </div>
                                </li>
                                <li>
                                    <div class="custom-input">
                                        <input type="radio" name="survey3" id="chk0302">
                                        <label for="chk0302">그렇다</label>
                                    </div>
                                </li>
                                <li>
                                    <div class="custom-input">
                                        <input type="radio" name="survey3" id="chk0303">
                                        <label for="chk0303">보통이다</label>
                                    </div>
                                </li>
                                <li>
                                    <div class="custom-input">
                                        <input type="radio" name="survey3" id="chk0304">
                                        <label for="chk0304">아니다</label>
                                    </div>
                                </li>
                                <li>
                                    <div class="custom-input">
                                        <input type="radio" name="survey3" id="chk0305">
                                        <label for="chk0305">절대아니다</label>
                                    </div>
                                </li>
                            </ol>
                        </div>
                        <div class="survey_list">
                            <div class="title">4. 학습하신 과정이 개선되었으면 하는 점을 자유롭게 적어주세요.<small>(서술형/최대 1000자)</small></div>
                            <textarea class="form-control" rows="5" id="contTextarea"></textarea>
                        </div>
                        <div class="btns mt30">
                            <button type="button" class="btn gray2">저장</button>
                            <button type="button" class="btn type5">취소</button>
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