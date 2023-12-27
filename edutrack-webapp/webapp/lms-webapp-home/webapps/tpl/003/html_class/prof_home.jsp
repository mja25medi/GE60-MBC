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
                        <h3>강의실 Home</h3>
                    </div>
                </div>
                <div class="segment">
                    <div class="course_info prof">
                        <label class="category">인공지능</label>
                        <div class="item">
                            <div class="class_row">
                                <h2>[A-2코스] 융합 서비스개발자과정 / 광주점</h2>
                                <div class="course_btn">
                                    <button type="button" class="btn type3">코딩학습 도움주기</button>
                                </div>
                            </div>
                            <ul>
                                <li><span>교육기간</span>2023.01.01 ~ 2023.01.31</li>
                                <li><span>성적열람 시작일</span>2023.01.01</li>
                                <li><span><i class="xi-calendar-check" aria-hidden="true"></i>전체 2개의 차시</span></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="segment">
                    <div class="scroll_table_w">
                        <div class="scroll_table">
                            <table>
                                <caption>미채점현황 목록</caption>
                                <thead>
                                    <tr>
                                        <th scope="col">기수명</th>
                                        <th scope="col" width="20%">과정명</th>
                                        <th scope="col">개강일</th>
                                        <th scope="col">종강일</th>
                                        <th scope="col">분반</th>
                                        <th scope="col">총 학습자수</th>
                                        <th scope="col">시험채점<br><small>(완료/응시자/전체)</small></th>
                                        <th scope="col">과제채점<br><small>(완료/응시자/전체)</small></th>
                                        <th scope="col">진행단계채점<br><small>(완료/전체)</small></th>
                                        <th scope="col">질의응답<br><small>(완료/전체)</small></th>
                                        <th scope="col">자료게시</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td scope="row">2023년도 1기</td>
                                        <td class="title"><a href="#0" class="text-flow">스마트선박 DF엔진 및 DC전기추진시스템 시작하기</a></td>
                                        <td>2023.11.30(목)</td>
                                        <td>2023.11.30(목)</td>
                                        <td>1반</td>
                                        <td>2명</td>
                                        <td>
                                            <ul class="table_unit">
                                                <li>1/1/2<br><button class="btn">채점하기</button></li>
                                            </ul>    
                                        </td>
                                        <td>
                                            <ul class="table_unit">
                                                <li>3/3/3<br><button class="btn">채점하기</button></li>
                                            </ul>    
                                        </td>
                                        <td>
                                            <ul class="table_unit">
                                                <li>0/0/3<br><button class="btn">채점하기</button></li>
                                            </ul>    
                                        </td>
                                        <td>0/1</td>
                                        <td>0</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                        <div class="scroll_img_w">
                            <div class="scroll_img">
                                <p class="txt">가로로 스와이프하여 <br>내용을 확인해주세요</p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="segment-row">
                    <div class="segment">
                        <div class="board_top">
                            <h4>내 쪽지함</h4>
                        </div>
                        <div class="res_tbl_wrap">
                            <table>
                                <caption>목록</caption>
                                <thead>
                                    <tr>
                                        <th scope="col" width="25%">보낸사람</th>
                                        <th scope="col">내용</th>
                                        <th scope="col" width="25%">보낸일</th>
                                        <th scope="col" width="15%">확인</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td scope="row" data-label="보낸사람">강사</td>
                                        <td class="title" data-label="내용"><a href="#0">질문 쪽지테스트</a></td>
                                        <td data-label="보낸일">2023.10.28</td>
                                        <td data-label="확인"><label class="btn3 sm solid fcBlue">확인</label></td>
                                    </tr>
                                    <tr>
                                        <td scope="row" data-label="보낸사람">관리자</td>
                                        <td class="title" data-label="내용"><a href="#0">관리자 쪽지</a></td>
                                        <td data-label="보낸일">2023.10.28</td>
                                        <td data-label="확인"><label class="btn3 sm solid fcBlue">확인</label></td>
                                    </tr>
                                    <tr>
                                        <td scope="row" data-label="보낸사람">학습자2</td>
                                        <td class="title" data-label="내용"><a href="#0">쪽지테스트</a></td>
                                        <td data-label="보낸일">2023.10.28</td>
                                        <td data-label="확인"><label class="btn3 sm solid fcDarkgray">미확인</label></td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="segment">
                        <div class="board_top">
                            <h4>전체 공지사항</h4>
                        </div>
                        <div class="res_tbl_wrap">
                            <table>
                                <caption>목록</caption>
                                <thead>
                                    <tr>
                                        <th scope="col">제목</th>
                                        <th scope="col" width="25%">작성일</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td scope="row" class="title" data-label="제목"><a href="#0">사이트 오픈 공지 입니다.</a></td>
                                        <td data-label="작성일">2023.10.28</td>
                                    </tr>
                                    <tr>
                                        <td scope="row" class="title" data-label="제목"><a href="#0">로그인이 잘 안될때 해결 방법입니다.</a></td>
                                        <td data-label="작성일">2023.10.28</td>
                                    </tr>
                                    <tr>
                                        <td scope="row" class="title" data-label="제목"><a href="#0">패스워드 찾기 관련 안내</a></td>
                                        <td data-label="작성일">2023.10.28</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- modal popup 보여주기 버튼(개발시 팝업id 삭제) -->
        <button class="modal__btn" id="popup1">페어코딩 요청 확인 팝업</button>
        <button class="modal__btn" onclick="window.open('iframe_learn_coding4.jsp')">코딩학습창(공유) 새창</button>

        <script type="text/javascript">
            $("#popup1").click(function() {
                $('body').css("overflow", "hidden");
                $("#paircoding_list").css('display','flex').hide().fadeIn();
                iFrameResize({ scrolling: true }, '#iframe1')
            })
           
        </script>
        <!-- SMS popup -->
        <div id="paircoding_list" class="modal_popup_wrap">                             
            <div class="modal_popup modal_medium">
                <h4 class="modal_title">페어코딩 요청 확인</h4>
                <iframe id="iframe1" src="iframe_paircoding_list.jsp"></iframe>
                <button type="button" class="modal_close"><i class="xi-close-thin"></i><span class="sr-only">팝업닫기</span></button>
            </div>
        </div>
        <!-- //modal popup -->
    </div>
</body>
</html>