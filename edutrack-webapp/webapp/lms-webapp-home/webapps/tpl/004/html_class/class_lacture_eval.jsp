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
                        <h3>강의평가</h3>
                    </div>
                </div>
                <div class="segment">
                    <div class="learn_info">
                        <div class="header">
                            <label class="online">온라인</label>
                            <h4>대학생활에서 가장 중요시 되는 점에 대한 설문 조사입니다.</h4>
                        </div>
                    </div>
                    <div class="course_list test_custom">
                        <div class="item">
                            <ul class="info_disc">
                                <li><strong>설문기간</strong>2023.10.12 00:00~2023.10.30 23:59</li>
                            </ul>
                            <div class="button_group">
                                <button type="button" class="primary">참여하기</button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="segment">
                    <div class="learn_info">
                        <div class="header">
                            <label class="offline">오프라인</label>
                            <h4>교수님의 수업내용 및 수업방법에 대하여 개선을 위한 설문입니다.</h4>
                        </div>
                    </div>
                    <div class="course_list test_custom">
                        <div class="item">
                            <ul class="info_disc">
                                <li><strong>설문기간</strong>2023.10.12 00:00~2023.10.30 23:59</li>
                            </ul>
                            <div class="button_group">
                                <button type="button" class="default">참여완료</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- modal popup 보여주기 버튼(개발시 팝업id 삭제) -->
        <button class="modal__btn" id="popup1">과제정보 팝업</button>
        <script type="text/javascript">
            $("#popup1").click(function() {
                $('body').css("overflow", "hidden");
                $("#task_info").css('display','flex').hide().fadeIn();
                iFrameResize({ scrolling: true }, '#iframe1')
            });
        </script>
        <div id="task_info" class="modal_popup_wrap">                             
            <div class="modal_popup modal_medium">
                <h4 class="modal_title">과제정보</h4>
                <iframe id="iframe1" src="iframe_task_info.jsp"></iframe>
                <button type="button" class="modal_close"><i class="xi-close-thin"></i><span class="sr-only">팝업닫기</span></button>
            </div>
        </div>
        <!-- //modal popup -->
    </div>
</body>
</html>