<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">
<%@ include file="../inc/class_common.jsp" %>
<body>
    <div id="wrap">
        <%@ include file="../inc/class_header.jsp" %>
        <div class="container">
            <%@ include file="../inc/class_lnb_prof.jsp" %>
            <div id="content">
                <div class="learn_top">
                    <div class="left_title">
                        <h3>미채점현황</h3>
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
                                        <th scope="col">시험채점<br /><small>(완료/응시자/전체)</small></th>
                                        <th scope="col">과제채점<br /><small>(완료/응시자/전체)</small></th>
                                        <th scope="col">진행단계채점<br /><small>(완료/전체)</small></th>
                                        <th scope="col">질의응답<br /><small>(완료/전체)</small></th>
                                        <th scope="col">자료게시</th>
                                        <th scope="col">채점완료 수</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td scope="row">2023년도 12기</td>
                                        <td class="title"><a href="#0" class="text-flow">데이터 사이언스(비환급)</a></td>
                                        <td>2023.11.30(목)</td>
                                        <td>2023.11.30(목)</td>
                                        <td>1</td>
                                        <td>2</td>
                                        <td>
                                            <ul class="table_unit">
                                                <li>1/1/2<br /><button class="btn">채점하기</button></li>
                                            </ul>    
                                        </td>
                                        <td>
                                            <ul class="table_unit">
                                                <li>1/1/2<br /><button class="btn">채점하기</button></li>
                                            </ul>    
                                        </td>
                                        <td>0/2</td>
                                        <td>0/1</td>
                                        <td>0</td>
                                        <td>1</td>
                                    </tr>
                                    <tr>
                                        <td scope="row">2023년도 11기</td>
                                        <td class="title"><a href="#0" class="text-flow">데이터 사이언스(비환급)</a></td>
                                        <td>2023.11.28(화)</td>
                                        <td>2023.11.30(목)</td>
                                        <td>2</td>
                                        <td>3</td>
                                        <td>
                                            <ul class="table_unit">
                                                <li>1/2/3<br /><button class="btn">채점하기</button></li>
                                                <li>1/2/3<br /><button class="btn">채점하기</button></li>
                                            </ul>
                                        </td>
                                        <td>
                                            <ul class="table_unit">
                                                <li>1/1/3<br /><button class="btn">채점하기</button></li>
                                            </ul>    
                                        </td>
                                        <td>0/3</td>
                                        <td>0/1</td>
                                        <td>1</td>
                                        <td>1</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                        <div class="scroll_img_w">
                            <div class="scroll_img">
                                <p class="txt">가로로 스와이프하여 <br />내용을 확인해주세요</p>
                            </div>
                        </div>
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
        <script type="text/javascript">
            $("#popup1").click(function() {
                $('body').css("overflow", "hidden");
                $("#test_info").css('display','flex').hide().fadeIn();
                iFrameResize({ scrolling: true }, '#iframe1')
            });
        </script>
        <!-- 시험정보 popup -->
        <div id="test_info" class="modal_popup_wrap">                             
            <div class="modal_popup modal_medium">
                <h4 class="modal_title">성적조회</h4>
                <iframe id="iframe1" src="iframe_test_inquiry.jsp"></iframe>
                <button type="button" class="modal_close"><i class="xi-close-thin"></i><span class="sr-only">팝업닫기</span></button>
            </div>
        </div>
        <!-- //modal popup -->
    </div>
</body>
</html>