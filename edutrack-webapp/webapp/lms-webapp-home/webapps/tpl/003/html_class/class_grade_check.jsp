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
                            <h2>[A-2코스] 융합 서비스개발자과정 / 광주점</h2>
                            <ul>
                                <li><span>교육기간</span>2023.01.01 ~ 2023.01.31</li>
                                <li><span>성적열람 시작일</span>2023.01.01</li>
                                <li><span><i class="xi-calendar-check" aria-hidden="true"></i>전체 2개의 차시 중</span>0개 수강 완료</li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="learn_top">
                    <div class="left_title">
                        <h3>성적조회</h3>
                    </div>
                </div>
                <div class="segment">
                    <div class="res_tbl_wrap">
                        <table>
                            <caption>성적조회 목록</caption>
                            <thead>
                                <tr>
                                    <th scope="col">과정명</th>
                                    <th scope="col" width="10%">시험</th>
                                    <th scope="col" width="15%">진행단계평가</th>
                                    <th scope="col" width="10%">총점</th>
                                    <th scope="col" width="10%">수료여부</th>
                                    <th scope="col" width="15%">이의제기</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td scope="row" class="title" data-label="과정명"><a href="#0">파이썬 설치 및 강의 환경 설정</a></td>
                                    <td data-label="시험">40</td>
                                    <td data-label="진행단계평가">30</td>
                                    <td data-label="총점">70</td>
                                    <td data-label="수료여부">수강승인</td>
                                    <td data-label="이의제기"><button class="btn type3">이의제기</button></td>
                                </tr>
                                <tr>
                                    <td scope="row" class="title" data-label="과정명"><a href="#0">변수 네이밍 규칙</a></td>
                                    <td data-label="시험">40</td>
                                    <td data-label="진행단계평가">30</td>
                                    <td data-label="총점">70</td>
                                    <td data-label="수료여부">수강승인</td>
                                    <td data-label="이의제기"><button class="btn type3">이의제기</button></td>
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