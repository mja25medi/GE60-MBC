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
                        <h3>과제관리</h3>
                        <button type="button" class="list">목록</button>
                    </div>
                </div>
                <div class="segment">
                    <div class="board_top">
                        <h4>과제정보</h4>
                    </div>
                    <div class="table_list">
                        <ul class="list">
                            <li class="head"><label>과제제목</label></li>
                            <li>1주차 네트워크구성도 만들어 보기</li>
                        </ul>
                        <ul class="list">
                            <li class="head"><label>과제유형</label></li>
                            <li><label class="btn3 sm solid fcGreen">온라인</label><label class="btn3 sm solid fcViolet">오프라인</label><label class="btn3 sm solid fcRed">혼합</label></li>
                            <li class="head"><label>과제출제유형</label></li>
                            <li>선택출제</li>
                        </ul>
                        <ul class="list">
                            <li class="head"><label>과제기간</label></li>
                            <li>2023.10.12 00:00~2023.10.30 23:59</li>
                        </ul>
                        <ul class="list">
                            <li class="head"><label>제출연장일</label></li>
                            <li>2023.11.03 23:59</li>
                            <li class="head"><label>제출횟수</label></li>
                            <li>10회</li>
                        </ul>
                        <ul class="list">
                            <li class="head"><label>첨부파일</label></li>
                            <li>
                                <ul class="add_file">
                                    <li>                    
                                        <a href="#" class="file_down">
                                            <img src="../../_img/board/file_doc.png" alt="">
                                            <span class="text">첨부파일명마우스오버 시.doc</span>
                                            <span class="fileSize">(6KB)</span>
                                        </a>                    
                                        <span class="link">
                                            <a class="btn-line btn-down" href="#" title="154873973477000.png 다운로드">다운로드<i></i></a>
                                        </span>
                                    </li>
                                    <li>
                                        <a href="#" class="file_down">
                                            <img src="../../_img/board/file_img.png" alt="">
                                            <span class="text">154873973477000.jpg</span>
                                            <span class="fileSize">(6KB)</span>
                                        </a>
                                        <span class="link">
                                            <a class="btn-line btn-down" href="#" title="154873973477000.png 다운로드">다운로드<i></i></a>
                                        </span>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                        <ul class="list">
                            <li class="head"><label>내용</label></li>
                            <li>온라인 과제2</li>
                        </ul>
                        <ul class="list">
                            <li class="head"><label>공개여부</label></li>
                            <li>등록</li>
                        </ul>
                    </div>
                </div>
                    <ul class="class_tab">
                        <li class="active"><a href="#">문제관리</a></li>
                        <li><a href="#">평가관리</a></li>
                    </ul>
                <div class="segment">
                    <div class="board_top">
                        <div class="page_btn">
                            <button type="button" class="btn type4">과제등록</button>
                            <button type="button" class="btn type1">공개하기</button>
                        </div>
                    </div>
                    <div class="res_tbl_wrap">
                        <table>
                            <caption>문제관리 목록</caption>
                            <thead>
                                <tr>
                                    <th scope="col" width="8%">번호</th>
                                    <th scope="col">과제문제</th>
                                    <th scope="col" width="15%">수정</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td scope="row" data-label="번호">1</td>
                                    <td class="title" data-label="과제문제">컴퓨터 실기 과제</td>
                                    <td data-label="관리"><button class="btn type3">관리</button></td>
                                </tr>
                                <tr>
                                    <td scope="row" data-label="번호">2</td>
                                    <td class="title" data-label="과제문제">프로그래밍언어론을 배우는 이유</td>
                                    <td data-label="관리"><button class="btn type3">관리</button></td>
                                </tr>
                                <tr>
                                    <td scope="row" data-label="번호">3</td>
                                    <td class="title" data-label="과제문제">프로그래밍 영역</td>
                                    <td data-label="관리"><button class="btn type3">관리</button></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        
        <!-- modal popup 보여주기 버튼(개발시 팝업id 삭제) -->
        <button class="modal__btn" id="popup1">과제문제관리 팝업</button>
        <button class="modal__btn" id="popup2">AI 과제문제관리 팝업</button>
        <script type="text/javascript">
            $("#popup1").click(function() {
                $('body').css("overflow", "hidden");
                $("#task_quest").css('display','flex').hide().fadeIn();
                iFrameResize({ scrolling: true }, '#iframe1')
            })
            
            $("#popup2").click(function() {
                $('body').css("overflow", "hidden");
                $("#task_quest_AI").css('display','flex').hide().fadeIn();
                iFrameResize({ scrolling: true }, '#iframe2')
            })
        </script>
        <!-- modal popup -->
        <div id="task_quest" class="modal_popup_wrap">                             
            <div class="modal_popup modal_medium">
                <h4 class="modal_title">과제문제관리</h4>
                <iframe id="iframe1" src="iframe_task_quest.jsp"></iframe>
                <button type="button" class="modal_close"><i class="xi-close-thin"></i><span class="sr-only">팝업닫기</span></button>
            </div>
        </div>
        <div id="task_quest_AI" class="modal_popup_wrap">                             
            <div class="modal_popup modal_wide">
                <h4 class="modal_title">AI 과제문제관리</h4>
                <iframe id="iframe2" src="iframe_task_quest_AI.jsp"></iframe>
                <button type="button" class="modal_close"><i class="xi-close-thin"></i><span class="sr-only">팝업닫기</span></button>
            </div>
        </div>
        <!-- //modal popup -->
    </div>
</body>
</html>