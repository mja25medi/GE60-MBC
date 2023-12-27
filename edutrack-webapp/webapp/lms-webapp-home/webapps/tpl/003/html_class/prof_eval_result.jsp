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
                        <h3>강의평가관리</h3>
                        <button type="button" class="list">목록</button>
                    </div>
                    <div class="right_btn">
                        <button type="button" class="btn">엑셀다운로드</button>
                    </div>
                </div>
                <div class="segment">
                    <div class="table_list">
                        <ul class="list">
                            <li class="head"><label>설문제목</label></li>
                            <li>콘텐츠에 대한 만족도는?</li>
                        </ul>
                        <ul class="list">
                            <li class="head"><label>설문기간</label></li>
                            <li>2023.10.12 10:52:39 ~ 2023.10.12 11:42:03</li>
                            <li class="head"><label>공개여부</label></li>
                            <li>공개</li>
                        </ul>
                    </div>
                </div>
                <div class="segment">
                    <div class="list_view_box mt0">
                        <div class="list_item">
                            <div class="survey">1. 문학이 현대사회에서 미치는 영향을 고르시오.</div>
                            <ol class="cont_list">
                                <li>
                                    <div class="text"><span class="circle_num">1</span>매우 그렇다.</div>
                                    <div class="progress">
                                        <div class="inner">
                                            <div class="bar" style="width: 100%;"></div>
                                        </div><small>100%</small>
                                    </div>
                                </li>
                                <li>
                                    <div class="text"><span class="circle_num">2</span>그렇다.</div>
                                    <div class="progress">
                                        <div class="inner">
                                            <div class="bar" style="width: 80%;"></div>
                                        </div><small>80%</small>
                                    </div>
                                </li>
                                <li>
                                    <div class="text"><span class="circle_num">3</span>보통이다</div>
                                    <div class="progress">
                                        <div class="inner">
                                            <div class="bar" style="width: 20%;"></div>
                                        </div><small>20%</small>
                                    </div>
                                </li>
                                <li>
                                    <div class="text"><span class="circle_num">4</span>아니다.</div>
                                    <div class="progress">
                                        <div class="inner">
                                            <div class="bar" style="width: 0%;"></div>
                                        </div><small>0%</small>
                                    </div>
                                </li>
                                <li>
                                    <div class="text"><span class="circle_num">5</span>절대 아니다.</div>
                                    <div class="progress">
                                        <div class="inner">
                                            <div class="bar" style="width: 0%;"></div>
                                        </div><small>0%</small>
                                    </div>
                                </li>
                            </ol>
                        </div>
                        <div class="list_item">
                            <div class="survey">2. 콘텐츠 품질은 어떤가요?</div>
                            <ol class="cont_list">
                                <li>
                                    <div class="text"><span class="circle_num">1</span>매우 그렇다.</div>
                                    <div class="progress">
                                        <div class="inner">
                                            <div class="bar" style="width: 100%;"></div>
                                        </div><small>100%</small>
                                    </div>
                                </li>
                                <li>
                                    <div class="text"><span class="circle_num">2</span>그렇다.</div>
                                    <div class="progress">
                                        <div class="inner">
                                            <div class="bar" style="width: 80%;"></div>
                                        </div><small>80%</small>
                                    </div>
                                </li>
                                <li>
                                    <div class="text"><span class="circle_num">3</span>보통이다</div>
                                    <div class="progress">
                                        <div class="inner">
                                            <div class="bar" style="width: 20%;"></div>
                                        </div><small>20%</small>
                                    </div>
                                </li>
                                <li>
                                    <div class="text"><span class="circle_num">4</span>아니다.</div>
                                    <div class="progress">
                                        <div class="inner">
                                            <div class="bar" style="width: 0%;"></div>
                                        </div><small>0%</small>
                                    </div>
                                </li>
                                <li>
                                    <div class="text"><span class="circle_num">5</span>절대 아니다.</div>
                                    <div class="progress">
                                        <div class="inner">
                                            <div class="bar" style="width: 0%;"></div>
                                        </div><small>0%</small>
                                    </div>
                                </li>
                            </ol>
                        </div>
                        <div class="list_item">
                            <div class="survey">3. 콘텐츠 품질 평가에 대해 자유롭게 의견을 적으세요.</div>
                            <button type="button" class="btn type4">결과보기</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- modal popup 보여주기 버튼(개발시 팝업id 삭제) -->
        <button class="modal__btn" id="popup1">서술형결과보기 팝업</button>
        <script type="text/javascript">
            $("#popup1").click(function() {
                $('body').css("overflow", "hidden");
                $("#prof_result_essay").css('display','flex').hide().fadeIn();
                iFrameResize({ scrolling: true }, '#iframe1')
            })
        </script>
        <!-- modal popup -->
        <div id="prof_result_essay" class="modal_popup_wrap">                             
            <div class="modal_popup modal_medium">
                <h4 class="modal_title">설문결과</h4>
                <iframe id="iframe1" src="iframe_prof_result_essay.jsp"></iframe>
                <button type="button" class="modal_close"><i class="xi-close-thin"></i><span class="sr-only">팝업닫기</span></button>
            </div>
        </div>
        <!-- //modal popup -->
    </div>
</body>
</html>