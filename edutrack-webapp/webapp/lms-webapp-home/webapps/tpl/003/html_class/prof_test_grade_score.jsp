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
                <div class="learn_top m_column_row">
                    <div class="left_title">
                        <h3>시험채점</h3>
                        <button type="button" class="list">목록</button>
                    </div>
                </div>
                <div class="segment">
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
                            <li class="head"><label>응시가능진도율</label></li>
                            <li>1% 이상</li>
                            <li class="head"><label>응시제한횟수</label></li>
                            <li>10회</li>
                        </ul>
                        <ul class="list">
                            <li class="head"><label>시간제한</label></li>
                            <li>10분</li>
                        </ul>
                        <ul class="list">
                            <li class="head"><label>시험출제문항수/배점</label></li>
                            <li>선택형 : 1개/50점<br />서술형 : 1개/50점</li>
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
                            <li class="head"><label>공개여부</label></li>
                            <li>미등록</li>
                        </ul>
                        <ul class="list">
                            <li class="head"><label>시험설명</label></li>
                            <li></li>
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
                            <button type="button" class="btn">서술형 문제추가</button>
                            <button type="button" class="btn">선택형 문제추가</button>
                            <button type="button" class="btn">진위형 문제추가</button>
                            <button type="button" class="btn">단답형 문제추가</button>
                            <button type="button" class="btn type1">문제은행보기</button>
                            <button type="button" class="btn type4">공개하기</button>
                        </div>
                    </div>
                    <div class="res_tbl_wrap">
                        <table>
                            <caption>시험문제 목록</caption>
                            <thead>
                                <tr>
                                    <th scope="col" width="8%">번호</th>
                                    <th scope="col">시험문제</th>
                                    <th scope="col" width="15%">문제유형</th>
                                    <th scope="col" width="15%">관리</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td scope="row" data-label="번호">1</td>
                                    <td class="title" data-label="시험문제">문학이 현대사회에서 미치는 영향을 고르시오.</td>
                                    <td data-label="문제유형">선택형</td>
                                    <td data-label="관리"><button class="btn type3">수정</button></td>
                                </tr>
                                <tr>
                                    <td scope="row" data-label="번호">2</td>
                                    <td class="title" data-label="시험문제">21세기에서 부의 흐름 방향과 중앙은행에서 어떠한 전략을 수립해야 하는지 간략히 적으시오.</td>
                                    <td data-label="문제유형">서술형</td>
                                    <td data-label="관리"><button class="btn type3">수정</button></td>
                                </tr>
                                <tr>
                                    <td scope="row" data-label="번호">3</td>
                                    <td class="title" data-label="시험문제">아래 보기에 대한 설명을 참고할 때, 숫자에 들어갈 단어는 무엇인가?</td>
                                    <td data-label="문제유형">단답형</td>
                                    <td data-label="관리"><button class="btn type3">수정</button></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>