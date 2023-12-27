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
                        <h3>이의제기</h3>
                    </div>
                </div>
                <ul class="class_tab">
                    <li class="active"><a href="#">전체</a></li>
                    <li><a href="#">처리중</a></li>
                    <li><a href="#">처리완료</a></li>
                </ul>
                <div class="segment">
                    <div class="res_tbl_wrap">
                        <table>
                            <caption>이의제기 목록</caption>
                            <thead>
                                <tr>
                                    <th scope="col" width="8%">번호</th>
                                    <th scope="col" width="15%">상태</th>
                                    <th scope="col">제목</th>
                                    <th scope="col" width="15%">이름</th>
                                    <th scope="col" width="25%">등록일</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td scope="row" data-label="번호">5</td>
                                    <td data-label="상태"><label class="btn3 sm solid fcViolet">처리중</label></td>
                                    <td class="title" data-label="제목"><a href="#0">시험에 대해서 문의 드립니다.</a></td>
                                    <td data-label="이름">학습자3</td>
                                    <td data-label="등록일">2023.10.01 14:26:17</td>
                                </tr>
                                <tr>
                                    <td scope="row" data-label="번호">4</td>
                                    <td data-label="상태"><label class="btn3 sm solid fcViolet">처리중</label></td>
                                    <td class="title" data-label="제목"><a href="#0">시험에 대해서 문의 드립니다.</a></td>
                                    <td data-label="이름">학습자2</td>
                                    <td data-label="등록일">2023.10.01 14:26:17</td>
                                </tr>
                                <tr>
                                    <td scope="row" data-label="번호">3</td>
                                    <td data-label="상태"><label class="btn3 sm solid fcBlack">처리완료</label></td>
                                    <td class="title" data-label="제목"><a href="#0">시험에 대해서 문의 드립니다.</a></td>
                                    <td data-label="이름">학습자1</td>
                                    <td data-label="등록일">2023.10.01 14:26:17</td>
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
    </div>
</body>
</html>