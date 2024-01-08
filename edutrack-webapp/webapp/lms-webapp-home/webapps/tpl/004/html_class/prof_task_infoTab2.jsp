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
                    <li><a href="#">문제관리</a></li>
                    <li class="active"><a href="#">평가관리</a></li>
                </ul>
                <div class="segment">
                    <div class="board_top">
                        <div class="page_btn flex-none">
                            <button type="button" class="btn">SMS</button>
                            <button type="button" class="btn">Email</button>
                            <button type="button" class="btn">쪽지</button>
                        </div>
                        <form name="srhForm" method="post" action="board_list" class="board_search">
                            <fieldset class="form-row">
                            <legend class="blind">게시판 검색</legend>
                            <input type="text" class="form-control" name="keyWord" placeholder="이름" title="검색어를 입력하세요" value="">
                            <button type="submit" class="btn type2">검색</button>
                            <select class="form-select type-num" name="keyField" id="select" title="페이지당 리스트수를 선택하세요.">
                                <option value="ALL" selected="selected">20</option>
                                <option value="40">40</option>
                                <option value="100">100</option>
                                <option value="200">200</option>
                            </select>
                            </fieldset>
                        </form>
                    </div>
                    <div class="res_tbl_wrap">
                        <table>
                            <caption>평가관리 목록</caption>
                            <thead>
                                <tr>
                                    <th scope="col" width="4%"><span class="custom-input onlychk"><input type="checkbox" id="chkall"><label for="chkall"></label></span></th>
                                    <th scope="col" width="8%">번호</th>
                                    <th scope="col">이름</th>
                                    <th scope="col">아이디</th>
                                    <th scope="col">제출여부</th>
                                    <th scope="col">최종제출일자</th>
                                    <th scope="col">점수</th>
                                    <th scope="col">모사율</th>
                                    <th scope="col">평가여부</th>
                                    <th scope="col">평가/결과</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td scope="row" data-label="선택"><span class="custom-input onlychk"><input type="checkbox" id="chk3"><label for="chk3"></label></span></td>
                                    <td scope="row" data-label="번호">3</td>
                                    <td class="title" data-label="이름">학습자1</td>
                                    <td class="title" data-label="아이디">goldhead0822</td>
                                    <td data-label="제출여부">미제출</td>
                                    <td data-label="응시일시">2023.10.21 10:08</td>
                                    <td data-label="점수">0.0점</td>
                                    <td data-label="모사율">-</td>
                                    <td data-label="평가여부">-</td>
                                    <td data-label="평가/결과">-</td>
                                </tr>
                                <tr>
                                    <td scope="row" data-label="선택"><span class="custom-input onlychk"><input type="checkbox" id="chk2"><label for="chk2"></label></span></td>
                                    <td scope="row" data-label="번호">2</td>
                                    <td class="title" data-label="이름">학습자2</td>
                                    <td class="title" data-label="아이디">lycos72</td>
                                    <td data-label="응시여부">미제출</td>
                                    <td data-label="응시일시">2023.10.21 10:08</td>
                                    <td data-label="점수">0.0점</td>
                                    <td data-label="모사율">-</td>
                                    <td data-label="평가여부">-</td>
                                    <td data-label="평가/결과">-</td>
                                </tr>
                                <tr>
                                    <td scope="row" data-label="선택"><span class="custom-input onlychk"><input type="checkbox" id="chk1"><label for="chk1"></label></span></td>
                                    <td scope="row" data-label="번호">1</td>
                                    <td class="title" data-label="이름">학습자3</td>
                                    <td class="title" data-label="아이디">medistu1</td>
                                    <td data-label="응시여부">제출</td>
                                    <td data-label="응시일시">2023.10.21 10:08</td>
                                    <td data-label="점수">0.0점</td>
                                    <td data-label="모사율">-</td>
                                    <td data-label="평가여부">-</td>
                                    <td data-label="평가/결과">-</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="board_pager">
                        <span class="inner">
                            <a href="#0" class="page_first" title="첫페이지"><i class="xi-angle-left-min" aria-hidden="true"></i></a>
                            <a href="#0" class="page_prev" title="이전페이지"><i class="xi-angle-left-min" aria-hidden="true"></i></a>
                            <a href="#0" class="page_now" title="1페이지"><strong>1</strong></a>
                            <a href="#0" class="page_none" title="2페이지">2</a>
                            <a href="#0" class="page_none" title="3페이지">3</a>
                            <a href="#0" class="page_next" title="다음페이지"><i class="xi-angle-right-min" aria-hidden="true"></i></a>
                            <a href="#0" class="page_last" title="마지막페이지"><i class="xi-angle-right-min" aria-hidden="true"></i></a>
                        </span>
                    </div>
                </div>
            </div>
        </div>

        <!-- modal popup 보여주기 버튼(개발시 팝업id 삭제) -->
        <button class="modal__btn" id="popup1">과제평가 팝업</button>
        <script type="text/javascript">
            $("#popup1").click(function() {
                $('body').css("overflow", "hidden");
                $("#task_assessment").css('display','flex').hide().fadeIn();
                iFrameResize({ scrolling: true }, '#iframe1')
            })
        </script>
        <!-- modal popup -->
        <div id="task_assessment" class="modal_popup_wrap">                             
            <div class="modal_popup modal_wide">
                <h4 class="modal_title">과제평가</h4>
                <iframe id="iframe1" src="iframe_task_assessment.jsp"></iframe>
                <button type="button" class="modal_close"><i class="xi-close-thin"></i><span class="sr-only">팝업닫기</span></button>
            </div>
        </div>
        <!-- //modal popup -->
    </div>
</body>
</html>