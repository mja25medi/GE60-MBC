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
                        <h3>수강자관리</h3>
                    </div>
                </div>
                <div class="segment">
                    <div class="board_top">
                        <div class="page_btn flex-none">
                            <button type="button" class="btn">SMS발송</button>
                            <button type="button" class="btn">Email발송</button>
                            <button type="button" class="btn">쪽지발송</button>
                            <button type="button" class="btn">엑셀다운로드</button>
                        </div>
                        <form name="srhForm" method="post" action="board_list" class="board_search">
                            <fieldset class="form-row">
                            <legend class="blind">게시판 검색</legend>
                            <input type="text" class="form-control" name="keyWord" placeholder="이름 또는 ID로 검색하세요." title="검색어를 입력하세요" value="">
                            <button type="submit" class="btn type2">검색</button>
                            </fieldset>
                        </form>
                    </div>
                    <div class="res_tbl_wrap">
                        <table>
                            <caption>수강자 목록</caption>
                            <thead>
                                <tr>
                                    <th scope="col" width="4%"><span class="custom-input onlychk"><input type="checkbox" id="chkall"><label for="chkall"></label></span></th>
                                    <th scope="col" width="8%">번호</th>
                                    <th scope="col" width="15%">기수</th>
                                    <th scope="col">과정</th>
                                    <th scope="col" width="15%">이름(ID)</th>
                                    <th scope="col" width="8%">관리</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td scope="row" data-label="선택"><span class="custom-input onlychk"><input type="checkbox" id="chk3"><label for="chk3"></label></span></td>
                                    <td scope="row" data-label="번호">3</td>
                                    <td data-label="기수">2023년도 12기</td>
                                    <td class="title" data-label="과정"><a href="#0" class="text-flow">데이터 사이언스(비환급)</a></td>
                                    <td class="title" data-label="이름(ID)">학습자1 (goldhead0822)</td>
                                    <td data-label="관리">
                                        <ul class="vertical_btn">
                                            <li><button class="btn icon type3"><i class="xi-chart-pyramid" aria-hidden="true"></i></button></li>
                                            <li><button class="btn icon type3"><i class="xi-note-o" aria-hidden="true"></i></button></li>
                                        </ul>
                                    </td>
                                </tr>
                                <tr>
                                    <td scope="row" data-label="선택"><span class="custom-input onlychk"><input type="checkbox" id="chk2"><label for="chk2"></label></span></td>
                                    <td scope="row" data-label="번호">2</td>
                                    <td data-label="기수">2023년도 12기</td>
                                    <td class="title" data-label="과정"><a href="#0" class="text-flow">데이터 사이언스(비환급)</a></td>
                                    <td class="title" data-label="이름(ID)">학습자2 (lycos72)</td>
                                    <td data-label="관리">
                                        <ul class="vertical_btn">
                                            <li><button class="btn icon type3"><i class="xi-chart-pyramid" aria-hidden="true"></i></button></li>
                                            <li><button class="btn icon type3"><i class="xi-note-o" aria-hidden="true"></i></button></li>
                                        </ul>
                                    </td>
                                </tr>
                                <tr>
                                    <td scope="row" data-label="선택"><span class="custom-input onlychk"><input type="checkbox" id="chk1"><label for="chk1"></label></span></td>
                                    <td scope="row" data-label="번호">1</td>
                                    <td data-label="기수">2023년도 12기</td>
                                    <td class="title" data-label="과정"><a href="#0" class="text-flow">데이터 사이언스(비환급)</a></td>
                                    <td class="title" data-label="이름(ID)">학습자3 (medistu1)</td>
                                    <td data-label="관리">
                                        <ul class="vertical_btn">
                                            <li><button class="btn icon type3"><i class="xi-chart-pyramid" aria-hidden="true"></i></button></li>
                                            <li><button class="btn icon type3"><i class="xi-note-o" aria-hidden="true"></i></button></li>
                                        </ul>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="board_pager">
                        <span class="inner">
                            <a href="" class="page_first" title="첫페이지"><i class="xi-angle-left-min" aria-hidden="true"></i><span class="sr_only">첫페이지</span></a>
                            <a href="" class="page_prev" title="이전페이지"><i class="xi-angle-left-min" aria-hidden="true"></i><span class="sr_only">이전페이지</span></a>
                            <a href="" class="page_now" title="1페이지"><strong>1</strong></a>
                            <a href="" class="page_none" title="2페이지">2</a>
                            <a href="" class="page_none" title="3페이지">3</a>
                            <a href="" class="page_next" title="다음페이지"><i class="xi-angle-right-min" aria-hidden="true"></i><span class="sr_only">다음페이지</span></a>
                            <a href="" class="page_last" title="마지막페이지"><i class="xi-angle-right-min" aria-hidden="true"></i><span class="sr_only">마지막페이지</span></a>
                        </span>
                    </div>
                </div>
            </div>
        </div>
        <!-- modal popup 보여주기 버튼(개발시 팝업id 삭제) -->
        <button class="modal__btn" id="popup1">성적조회 팝업</button>
        <button class="modal__btn" id="popup2">SMS 팝업</button>
        <button class="modal__btn" id="popup3">쪽지 팝업</button>
        <button class="modal__btn" id="popup4">메일 팝업</button>
        <script type="text/javascript">
            $("#popup1").click(function() {
                $('body').css("overflow", "hidden");
                $("#test_info").css('display','flex').hide().fadeIn();
                iFrameResize({ scrolling: true }, '#iframe1')
            });
            $("#popup2").click(function() {
                $('body').css("overflow", "hidden");
                $("#send_sms").css('display','flex').hide().fadeIn();
                iFrameResize({ scrolling: true }, '#iframe2')
            });
            $("#popup3").click(function() {
                $('body').css("overflow", "hidden");
                $("#send_msg").css('display','flex').hide().fadeIn();
                iFrameResize({ scrolling: true }, '#iframe3')
            });
            $("#popup4").click(function() {
                $('body').css("overflow", "hidden");
                $("#send_email").css('display','flex').hide().fadeIn();
                iFrameResize({ scrolling: true }, '#iframe4')
            });
        </script>
        <!-- 성적조회 popup -->
        <div id="test_info" class="modal_popup_wrap">                             
            <div class="modal_popup modal_medium">
                <h4 class="modal_title">성적조회</h4>
                <iframe id="iframe1" src="iframe_test_inquiry.jsp"></iframe>
                <button type="button" class="modal_close"><i class="xi-close-thin"></i><span class="sr-only">팝업닫기</span></button>
            </div>
        </div>
        <!-- SMS popup -->
        <div id="send_sms" class="modal_popup_wrap">                             
            <div class="modal_popup modal_medium">
                <h4 class="modal_title">SMS 보내기</h4>
                <iframe id="iframe2" src="iframe_send_sms.jsp"></iframe>
                <button type="button" class="modal_close"><i class="xi-close-thin"></i><span class="sr-only">팝업닫기</span></button>
            </div>
        </div>
        <!-- 쪽지 popup -->
        <div id="send_msg" class="modal_popup_wrap">                             
            <div class="modal_popup modal_medium">
                <h4 class="modal_title">쪽지 보내기</h4>
                <iframe id="iframe3" src="iframe_send_msg.jsp"></iframe>
                <button type="button" class="modal_close"><i class="xi-close-thin"></i><span class="sr-only">팝업닫기</span></button>
            </div>
        </div>
        <!-- 메일 popup -->
        <div id="send_email" class="modal_popup_wrap">                             
            <div class="modal_popup modal_medium">
                <h4 class="modal_title">메일 보내기</h4>
                <iframe id="iframe4" src="iframe_send_email.jsp"></iframe>
                <button type="button" class="modal_close"><i class="xi-close-thin"></i><span class="sr-only">팝업닫기</span></button>
            </div>
        </div>
        <!-- //modal popup -->
    </div>
</body>
</html>