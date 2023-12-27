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
                            <div class="class_row">
                                <h2>[A-2코스] 융합 서비스개발자과정 / 광주점</h2>
                                <div class="course_btn">
                                    <button type="button" class="btn type3">출석하기</button>
                                    <button type="button" class="btn type3">퇴실하기</button>
                                    <button type="button" class="btn type3">외출/조퇴하기</button>
                                </div>
                            </div>
                            <ul>
                                <li><span>교육기간</span>2023.01.01 ~ 2023.01.31</li>
                                <li><span>성적열람 시작일</span>2023.01.01</li>
                                <li><span><i class="xi-calendar-check" aria-hidden="true"></i>전체 2개의 차시 중</span>0개 수강 완료</li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="segment grid_2">
                    <div class="prog_rate">
                        <h3>학습진도율</h3>
                        <ul>
                            <li>
                                <span class="header">나의 진도율</span><span class="meta">20%</span>
                                <div class="progress">
                                    <div class="bar red_type" style="width: 20%;"></div>
                                </div>
                            </li>
                            <li>
                                <span class="header">권장 진도율</span><span class="meta">35%</span>
                                <div class="progress">
                                    <div class="bar blue_type" style="width: 35%;"></div>
                                </div>
                            </li>
                        </ul>
                    </div>
                    <div class="test_part">
                        <h3>성적적용비율(%)</h3>
                        <ul>
                            <li><span class="header">진도</span>25%</li>
                            <li><span class="header">시험</span>25%</li>
                            <li><span class="header">과제</span>25%</li>
                            <li><span class="header">기타</span>25%</li>
                        </ul>
                    </div>
                </div>
                <div class="learn_top">
                    <div class="left_title">
                        <h3>과목별 리스트</h3>
                    </div>
                </div>
                <div class="segment">
                    <div class="learn_info">
                        <div class="header">
                            <label class="offline">오프라인</label>
                            <h4>기초프로그래밍</h4>
                        </div>
                        <span class="location">강의실 제 3 강의실</span>
                    </div>
                    <div class="course_list">
                        <div class="item">
                            <div class="title prog_end"><h5>과목 학습완료</h5></div>
                            <div class="button_group">
                                <button type="button" class="default">학습완료</button>
                            </div>
                        </div>
                        <div class="item">
                            <div class="title prog_ing"><h5>과목 학습중</h5></div>
                            <div class="button_group">
                                <button type="button" class="primary">학습하기</button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="learn_top">
                    <div class="left_title">
                        <h3>학습목차</h3>
                        <button type="button" class="list">목록</button>
                    </div>
                    <ul class="right_state">
                        <li class="step01">학습 미진행</li>
                        <li class="step02">학습 진행중</li>
                        <li class="step03">학습완료</li>
                    </ul>
                </div>
                <div class="segment">
                    <div class="learn_info">
                        <div class="header">
                            <label class="offline">오프라인</label>
                            <label class="online">온라인</label>
                            <label class="mixing">혼합</label>
                            <h4>기초프로그래밍</h4>
                        </div>
                        <span class="location">강의실 제 3 강의실</span>
                    </div>
                    <div class="course_list">
                        <div class="item step03">
                            <label class="chasi">1차시</label>
                            <div class="title">
                                <h5><span>파이썬 설치 및 강의 환경 설정</span><img src="../img/class/icon_course_play.svg" alt="icon" /><img src="../img/class/icon_course_code.svg" alt="icon" /></h5>
                                <div class="disc">강사: 정형 / 강의 시간: 2023.10.31 09:00~11:30  / 진도율: <strong class="fcBlue">80%</strong></div>
                            </div>
                            <div class="button_group">
                                <button type="button" class="basic data">학습자료</button>
                                <button type="button" class="default">복습하기</button>
                                <button type="button" class="secondary">출석/퇴실하기</button>
                            </div>
                        </div>
                        <div class="item step02">
                            <label class="chasi">2차시</label>
                            <div class="title">
                                <h5><span>변수 네이밍 규칙</span><img src="../img/class/icon_course_code.svg" alt="icon" /></h5>
                                <div class="disc">강사: 차현석 / 강의 시간:  2023.10.31 13:00~15:30  / 진도율: <strong class="fcBlue">80%</strong></div>
                            </div>
                            <div class="button_group">
                                <button type="button" class="basic data">학습자료</button>
                                <button type="button" class="primary">학습하기</button>
                                <button type="button" class="gcolor">Zoom 입장</button>
                            </div>
                        </div>
                        <div class="item step01">
                            <label class="chasi">3차시</label>
                            <div class="title">
                                <h5><span>시퀀스 타입 인덱싱</span><img src="../img/class/icon_course_video.svg" alt="icon" /></h5>
                                <div class="disc">강사: 정형 / 강의 시간: 2023.10.31 09:00~11:30  / 진도율: <strong class="fcBlue">50%</strong></div>
                            </div>
                            <div class="button_group">
                                <button type="button" class="basic data">학습자료</button>
                                <button type="button" class="primary">학습하기</button>
                                <button type="button" class="gcolor">Zoom 입장</button>
                            </div>
                        </div>
                        <div class="item step01">
                            <label class="chasi">4차시</label>
                            <div class="title">
                                <h5><span>파이썬 설치 및 강의 환경 설정</span><img src="../img/class/icon_course_code.svg" alt="icon" /></h5>
                                <div class="disc">강사: 정형 / 강의 시간: 2023.10.31 09:00~11:30  / 진도율: <strong class="fcBlue">30%</strong></div>
                            </div>
                            <div class="button_group">
                                <button type="button" class="basic data">학습자료</button>
                                <button type="button" class="primary">학습하기</button>
                                <button type="button" class="gcolor">Zoom 입장</button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="segment">
                    <div class="learn_info">
                        <div class="header">
                            <label class="online">온라인</label>
                            <h4>파이썬을 활용한 머신러닝</h4>
                        </div>
                    </div>
                    <div class="course_list">
                        <div class="item step03">
                            <label class="chasi">1차시</label>
                            <div class="title">
                                <h5><span>파이썬 설치 및 강의 환경 설정</span><img src="../img/class/icon_course_play.svg" alt="icon" /></h5>
                                <div class="disc">강사: 정형 / 강의 시간: 2023.10.31 09:00~11:30  / 진도율: <strong class="fcBlue">100%</strong></div>
                            </div>
                            <div class="button_group">
                                <button type="button" class="basic data">학습자료</button>
                                <button type="button" class="default">복습하기</button>
                            </div>
                        </div>
                        <div class="item step02">
                            <label class="chasi">2차시</label>
                            <div class="title">
                                <h5><span>변수 네이밍 규칙</span><img src="../img/class/icon_course_code.svg" alt="icon" /></h5>
                                <div class="disc">강사: 차현석 / 강의 시간:  2023.10.31 13:00~15:30  / 진도율: <strong class="fcBlue">30%</strong></div>
                            </div>
                            <div class="button_group">
                                <button type="button" class="basic data">학습자료</button>
                                <button type="button" class="primary">학습하기</button>
                            </div>
                        </div>
                        <div class="item step01">
                            <label class="chasi">3차시</label>
                            <div class="title">
                                <h5><span>시퀀스 타입 인덱싱</span><img src="../img/class/icon_course_video.svg" alt="icon" /></h5>
                                <div class="disc">강사: 정형 / 강의 시간: 2023.10.31 09:00~11:30  / 진도율: <strong class="fcBlue">0%</strong></div>
                            </div>
                            <div class="button_group">
                                <button type="button" class="basic data">학습자료</button>
                                <button type="button" class="primary">학습하기</button>
                            </div>
                        </div>
                        <div class="item step01">
                            <label class="chasi">4차시</label>
                            <div class="title">
                                <h5><span>파이썬 설치 및 강의 환경 설정</span><img src="../img/class/icon_course_code.svg" alt="icon" /></h5>
                                <div class="disc">강사: 정형 / 강의 시간: 2023.10.31 09:00~11:30  / 진도율: <strong class="fcBlue">0%</strong></div>
                            </div>
                            <div class="button_group">
                                <button type="button" class="basic data">학습자료</button>
                                <button type="button" class="primary">학습하기</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- modal popup 보여주기 버튼(개발시 팝업id 삭제) -->
        <button class="modal__btn" id="popup1">모바일 출석체크 팝업</button>
        <button class="modal__btn" id="popup2">외출/조퇴 팝업</button>
        <button class="modal__btn" id="popup3">과제제출결과 팝업</button>
        <button class="modal__btn" onclick="window.open('iframe_learn_vod.jsp')">학습하기 새창</button>
        <button class="modal__btn" onclick="window.open('iframe_learn_coding.jsp')">코딩학습창(vod) 새창</button>
        <button class="modal__btn" onclick="window.open('iframe_learn_coding2.jsp')">코딩학습창(내용보기) 새창</button>
        <script type="text/javascript">
            $("#popup1").click(function() {
                $('body').css("overflow", "hidden");
                $("#mobile_attend_check").css('display','flex').hide().fadeIn();
                iFrameResize({ scrolling: true }, '#iframe1')
            })
            
            $("#popup2").click(function() {
                $('body').css("overflow", "hidden");
                $("#class_outing_check").css('display','flex').hide().fadeIn();
                iFrameResize({ scrolling: true }, '#iframe2')
            })

            $("#popup3").click(function() {
                $('body').css("overflow", "hidden");
                $("#task_result").css('display','flex').hide().fadeIn();
                iFrameResize({ scrolling: true }, '#iframe3')
            })
        </script>
        <!-- modal popup -->
        <div id="mobile_attend_check" class="modal_popup_wrap">                             
            <div class="modal_popup">
                <h4 class="modal_title">출석 체크</h4>
                <iframe id="iframe1" src="iframe_mobile_attend_check.jsp"></iframe>
                <button type="button" class="modal_close"><i class="xi-close-thin"></i><span class="sr-only">팝업닫기</span></button>
            </div>
        </div>
        <div id="class_outing_check" class="modal_popup_wrap">                             
            <div class="modal_popup">
                <h4 class="modal_title">외출/조퇴</h4>
                <iframe id="iframe2" src="iframe_class_outing_check.jsp"></iframe>
                <button type="button" class="modal_close"><i class="xi-close-thin"></i><span class="sr-only">팝업닫기</span></button>
            </div>
        </div>
        <div id="task_result" class="modal_popup_wrap">                             
            <div class="modal_popup modal_wide">
                <h4 class="modal_title">과제 제출 결과</h4>
                <iframe id="iframe3" src="iframe_task_result.jsp"></iframe>
                <button type="button" class="modal_close"><i class="xi-close-thin"></i><span class="sr-only">팝업닫기</span></button>
            </div>
        </div>
        <!-- //modal popup -->
    </div>
</body>
</html>