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
                <div class="learn_top">
                    <div class="left_title">
                        <h3>시험채점</h3>
                    </div>
                    <div class="right_btn">
                        <button type="button" class="btn type4">시험등록</button>
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
                                <li><strong>공개여부</strong>비공개</li>
                            </ul>
                            <div class="button_group">
                                <button type="button" class="basic">시험정보</button>
                                <button type="button" class="basic">수정</button>
                                <button type="button" class="secondary">채점</button>
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
                                <li><strong>공개여부</strong>비공개</li>
                            </ul>
                            <div class="button_group">
                                <button type="button" class="basic">시험정보</button>
                                <button type="button" class="basic">수정</button>
                                <button type="button" class="secondary">채점</button>
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
                                <li><strong>공개여부</strong>공개</li>
                            </ul>
                            <div class="button_group">
                                <button type="button" class="basic">시험정보</button>
                                <button type="button" class="basic">수정</button>
                                <button type="button" class="secondary">채점</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- modal popup 보여주기 버튼(개발시 팝업id 삭제) -->
        <button class="modal__btn" id="popup1">시험정보 팝업</button>
        <script type="text/javascript">
            $("#popup1").click(function() {
                $('body').css("overflow", "hidden");
                $("#test_info").css('display','flex').hide().fadeIn();
            });
        </script>
        <!-- 시험정보 popup -->
        <div id="test_info" class="modal_popup_wrap">                             
            <div class="modal_popup modal_medium">
                <h4 class="modal_title">시험정보</h4>
                <div class="modal_cont">
                    <div class="table_list">
                        <ul class="list">
                            <li class="head"><label>시험유형</label></li>
                            <li><label class="btn3 sm solid fcGreen">온라인</label><label class="btn3 sm solid fcViolet">오프라인</label></li>
                            <li class="head"><label>응시유형</label></li>
                            <li>정규시험</li>
                        </ul>
                        <ul class="list">
                            <li class="head"><label>시험제목</label></li>
                            <li>1주차 네트워크구성도 만들어 보기</li>
                        </ul>
                        <ul class="list">
                            <li class="head"><label>시험기간</label></li>
                            <li>2023.10.12 00:00~2023.10.30 23:59</li>
                        </ul>
                        <ul class="list">
                            <li class="head"><label>결과확인일</label></li>
                            <li>2023.10.31 00:00</li>
                        </ul>
                        <ul class="list">
                            <li class="head"><label>시간제한</label></li>
                            <li>사용함</li>
                            <li class="head"><label>시험시간</label></li>
                            <li>10분</li>
                        </ul>
                        <ul class="list">
                            <li class="head"><label>응시가능진도율</label></li>
                            <li>1% 이상</li>
                            <li class="head"><label>응시제한횟수</label></li>
                            <li>10회</li>
                        </ul>
                        <ul class="list">
                            <li class="head"><label>시험설명</label></li>
                            <li></li>
                        </ul>
                    </div>
                </div>
                <div class="modal_btns">
                    <button type="button" class="btn">닫기</button>
                </div>
                <button type="button" class="modal_close"><i class="xi-close-thin"></i><span class="sr-only">팝업닫기</span></button>
            </div>
        </div>
        <!-- //modal popup -->
    </div>
</body>
</html>