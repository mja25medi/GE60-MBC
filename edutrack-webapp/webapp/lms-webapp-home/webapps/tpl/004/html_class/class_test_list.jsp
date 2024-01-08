<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">
<%@ include file="../inc/class_common.jsp" %>
<body>
    <div id="wrap">
        <%@ include file="../inc/class_header.jsp" %>
        <div class="container">
            <%@ include file="../inc/class_lnb.jsp" %>
            <div id="content">
                <div class="segment">
                    <div class="course_info">
                        <label class="category">인공지능</label>
                        <div class="item">
                            <h2>[A-2코스] 융합 서비스개발자과정 / 광주점</h2>
                            <ul>
                                <li><span>교육기간</span>2023.01.01 ~ 2023.01.31</li>
                                <li><span>성적열람 시작일</span>2023.01.01</li>
                                <li><span><i class="xi-calendar-check" aria-hidden="true"></i>전체 2개의 차시 중</span>0개 수강 완료</li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="learn_top">
                    <div class="left_title">
                        <h3>시험정보</h3>
                    </div>
                </div>
                <div class="segment">
                    <div class="learn_info">
                        <div class="header">
                            <label class="online">온라인</label>
                            <h4>한국 산업의 발전 방향 중간고사</h4>
                        </div>
                    </div>
                    <div class="course_list test_custom">
                        <div class="item">
                            <ul class="info_disc">
                                <li><strong>시험기간</strong>2023.10.12 00:00~2023.10.30 23:59</li>
                                <li><strong>시험시간</strong>10분</li>
                                <li><strong>응시횟수</strong>0/10</li>
                            </ul>
                            <div class="button_group">
                                <button type="button" class="primary">응시하기</button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="segment">
                    <div class="learn_info">
                        <div class="header">
                            <label class="offline">오프라인</label>
                            <h4>1주차 네트워크구성도 만들어 보기</h4>
                        </div>
                    </div>
                    <div class="course_list test_custom">
                        <div class="item">
                            <ul class="info_disc">
                                <li><strong>시험기간</strong>2023.10.12 00:00~2023.10.30 23:59</li>
                                <li><strong>시험시간</strong>10분</li>
                                <li><strong>응시횟수</strong>0/10</li>
                            </ul>
                            <div class="button_group">
                                <button type="button" class="default">응시완료</button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="segment">
                    <div class="learn_info">
                        <div class="header">
                            <label class="offline">오프라인</label>
                            <h4>네트워크 이해 기말고사</h4>
                        </div>
                    </div>
                    <div class="course_list test_custom">
                        <div class="item">
                            <ul class="info_disc">
                                <li><strong>시험기간</strong>2023.10.12 00:00~2023.10.30 23:59</li>
                                <li><strong>시험시간</strong>10분</li>
                                <li><strong>응시횟수</strong>0/10</li>
                            </ul>
                            <div class="button_group">
                                <button type="button" class="default">응시완료</button>
                                <button type="button" class="secondary">결과보기</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- modal popup 보여주기 버튼(개발시 팝업id 삭제) -->
        <button class="modal__btn" id="popup1">응시하기 팝업</button>
        <button class="modal__btn" id="popup2">시험응시 팝업</button>
        <button class="modal__btn" id="popup3">시험결과정보 팝업</button>
        <script type="text/javascript">
            $("#popup1").click(function() {
                $('body').css("overflow", "hidden");
                $("#test_agree_check").css('display','flex').hide().fadeIn();
                iFrameResize({ scrolling: true }, '#iframe1')
            });
            $("#popup2").click(function() {
                $('body').css("overflow", "hidden");
                $("#test_question").css('display','flex').hide().fadeIn();
                iFrameResize({ scrolling: true }, '#iframe2')
            });
            $("#popup3").click(function() {
                $('body').css("overflow", "hidden");
                $("#test_result").css('display','flex').hide().fadeIn();
                iFrameResize({ scrolling: true }, '#iframe3')
            });
        </script>
        <!-- 응시하기 popup -->
        <div id="test_agree_check" class="modal_popup_wrap">                             
            <div class="modal_popup">
                <h4 class="modal_title">시험응시약관</h4>
                <iframe id="iframe1" src="iframe_test_agree_check.jsp"></iframe>
                <button type="button" class="modal_close"><i class="xi-close-thin"></i><span class="sr-only">팝업닫기</span></button>
            </div>
        </div>
        <!-- 시험응시 popup -->
        <div id="test_question" class="modal_popup_wrap">                             
            <div class="modal_popup modal_wide">
                <h4 class="modal_title">시험응시</h4>
                <iframe id="iframe2" src="iframe_test_question.jsp"></iframe>
                <button type="button" class="modal_close"><i class="xi-close-thin"></i><span class="sr-only">팝업닫기</span></button>
            </div>
        </div>
        <!-- 시험결과정보 popup -->
        <div id="test_result" class="modal_popup_wrap">                             
            <div class="modal_popup modal_wide">
                <h4 class="modal_title">시험정보</h4>
                <iframe id="iframe3" src="iframe_test_result.jsp"></iframe>
                <button type="button" class="modal_close"><i class="xi-close-thin"></i><span class="sr-only">팝업닫기</span></button>
            </div>
        </div>
        <!-- //modal popup -->
    </div>
</body>
</html>