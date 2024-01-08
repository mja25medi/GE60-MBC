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

                    <table class="tstyle list">
                        <caption>설문 목록</caption>
                        <colgroup>
                            <col class="w8 m_hidden">
                            <col>
                            <col class="w25 m_hidden">
                            <col class="w15 m_hidden">
                        </colgroup>
                        <thead class="dhead">
                            <tr>
                                <th scope="col" class="m_hidden">번호</th>
                                <th scope="col" class="title">설문명</th>
                                <th scope="col" class="m_hidden">설문 기간</th>
                                <th scope="col" class="m_hidden">참여/결과</th>
                            </tr>
                        </thead>
                        <tbody class="dbody" id="listView">                            
                            <tr>
                                <td class="m_hidden" data-title="번호">3</td>
                                <td class="title" data-title="설문명">
                                    <a href="#">설문명이 표시 됩니다.</a>
                                </td>
                                <td class="m_hidden" data-title="설문 기간">2023.10.01 ~ 2023.10.31</td>
                                <td class="m_hidden" data-title="참여/결과">설문종료</td>
                            </tr>
                            <tr>
                                <td class="m_hidden" data-title="번호">2</td>
                                <td class="title" data-title="설문명">
                                    <a href="#">설문명이 표시 됩니다.</a>
                                </td>
                                <td class="m_hidden" data-title="설문 기간">2023.10.01 ~ 2023.10.31</td>
                                <td class="m_hidden" data-title="참여/결과"><button class="btn type6">참여하기</button></td>
                            </tr> 
                            <tr>
                                <td class="m_hidden" data-title="번호">1</td>
                                <td class="title" data-title="설문명">
                                    <a href="#">설문명이 표시 됩니다.</a>
                                </td>
                                <td class="m_hidden" data-title="설문 기간">2023.10.01 ~ 2023.10.31</td>
                                <td class="m_hidden" data-title="참여/결과"><button class="btn type6">참여하기</button></td>
                            </tr>                           
                        </tbody>
                    </table>

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

        <!-- modal popup 보여주기 버튼(개발시 팝업id 삭제) -->
        <button class="modal__btn" id="popup1">설문정보 팝업</button>
        <script type="text/javascript">
            $("#popup1").click(function() {
                $('body').css("overflow", "hidden");
                $("#survey_info").css('display','flex').hide().fadeIn();
                iFrameResize({ scrolling: true }, '#iframe1')
            })
        </script>
        <!-- modal popup -->
        <div id="survey_info" class="modal_popup_wrap">                             
            <div class="modal_popup modal_medium">
                <h4 class="modal_title">설문정보</h4>
                <iframe id="iframe1" src="iframe_survey_info.jsp"></iframe>
                <button type="button" class="modal_close"><i class="xi-close-thin"></i><span class="sr-only">팝업닫기</span></button>
            </div>
        </div>
        <!-- //modal popup -->

        

        <%@ include file="../inc/hrd_footer.jsp" %>
       
    </div>
</body>

</html>