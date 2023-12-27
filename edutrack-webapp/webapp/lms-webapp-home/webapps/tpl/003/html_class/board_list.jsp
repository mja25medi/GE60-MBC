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
                        <h3>과목 공지사항</h3>
                    </div>
                </div>
                <div class="segment">
                    <div class="board_info">
                        <div class="page_info">
                            <span class="total_page">전체 <b>12</b>건 </span>
                            <span class="current_page">현재 페이지 <strong>1</strong>/10</span>
                        </div>
                        <form name="srhForm" method="post" action="board_list" class="board_search">
                            <fieldset class="form-row">
                            <legend class="blind">공지사항 검색</legend>
                            <select class="form-select" name="keyField" id="select" title="검색어 분류를 선택하세요.">
                                <option value="ALL" selected="selected">전체</option>
                                <option value="TITLE">제목</option>
                                <option value="CONTENT">내용</option>
                                <option value="NAME">작성자</option>
                            </select>
                            <input type="text" class="form-control w50" name="keyWord" placeholder="검색어를 입력하세요" title="검색어를 입력하세요" value="">
                            <button type="submit" class="btn type2">검색</button>
                            </fieldset>
                        </form>
                        <div class="page_btn">
                            <button type="button" class="btn type4">글쓰기</button>
                        </div>
                    </div>
                    <table class="tstyle list">
                        <caption>공지사항 테스트 목록</caption>
                        <colgroup>
                            <col class="w8 m_hidden">
                            <col>
                            <col class="w8 m_hidden">
                            <col class="w10 m_hidden">
                            <col class="w10 m_hidden">
                            <col class="w8 m_hidden">
                        </colgroup>
                        <thead class="dhead">
                            <tr>
                                <th scope="col" class="m_hidden">번호</th>
                                <th scope="col" class="title">제목</th>
                                <th scope="col" class="m_hidden">첨부파일</th>
                                <th scope="col" class="m_hidden">등록일</th>
                                <th scope="col" class="m_hidden">작성자</th>
                                <th scope="col" class="m_hidden">조회수</th>
                            </tr>
                        </thead>
                        <tbody class="dbody" id="listView">
                            <tr class="notice">
                                <td class="m_hidden"><i class="icon_notice">공지</i></td>
                                <td class="title"><a href="#">온라인교육 개인정보변경 관련</a></td>
                                <td class="m_hidden" data-title="첨부파일"><i class="xi-attachment" aria-hidden="true"></i><span class="sr-only">첨부파일</span></td>
                                <td class="m_hidden" data-title="작성자">강사3</td>
                                <td class="m_hidden" data-title="등록일">2023.10.28</td>
                                <td class="m_hidden" data-title="조회수">651</td>
                            </tr>
                            <tr>
                                <td class="m_hidden" data-title="번호">17</td>
                                <td class="title" data-title="타이틀"> <a href="#">사이트 오픈 공지 입니다.<i class="xi-new" aria-hidden="true"></i><span class="sr-only">새글</span></a></td>
                                <td class="m_hidden" data-title="첨부파일"></td>
                                <td class="m_hidden" data-title="작성자">강사2</td>
                                <td class="m_hidden" data-title="등록일">2023.10.28</td>
                                <td class="m_hidden" data-title="조회수">33</td>
                            </tr>
                            <tr>
                                <td class="m_hidden">16</td>
                                <td class="title"><a href="#">웹표준 브라우저 설치 안내에 대해 알려드립니다.</a></td>
                                <td class="m_hidden" data-title="첨부파일"><i class="xi-attachment" aria-hidden="true"></i><span class="sr-only">첨부파일</span></td>
                                <td class="m_hidden" data-title="작성자">강사1</td>
                                <td class="m_hidden" data-title="등록일">2023.10.28</td>
                                <td class="m_hidden" data-title="조회수">456</td>
                            </tr>
                        </tbody>
                    </table>
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