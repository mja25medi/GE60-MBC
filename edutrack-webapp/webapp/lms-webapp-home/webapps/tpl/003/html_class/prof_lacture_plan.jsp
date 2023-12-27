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
                        <h3>강의계획서</h3>
                    </div>
                </div>
                <div class="segment">
                    <div class="board_top">
                        <h4>과정정보</h4>
                    </div>
                    <div class="table_list">
                        <ul class="list">
                            <li class="head"><label>과정명</label></li>
                            <li>내일배움 카드 테스트 과목</li>
                        </ul>
                        <ul class="list">
                            <li class="head"><label>기수명</label></li>
                            <li>2023년도 8기수</li>
                            <li class="head"><label>교육비</label></li>
                            <li>120,000원</li>
                        </ul>
                        <ul class="list">
                            <li class="head"><label>학습정원</label></li>
                            <li>100명</li>
                            <li class="head"><label>1일 수강 제한</label></li>
                            <li><strong class="fcRed">1일 8차시만</strong> 수강 가능</li>
                        </ul>
                        <ul class="list">
                            <li class="head"><label>학습일</label></li>
                            <li>30일</li>
                            <li class="head"><label>학습시간</label></li>
                            <li>120시간</li>
                        </ul>
                        <ul class="list">
                            <li class="head"><label>학습개요</label></li>
                            <li>테스트</li>
                        </ul>
                        <ul class="list">
                            <li class="head"><label>학습목표</label></li>
                            <li>테스트</li>
                        </ul>
                        <ul class="list">
                            <li class="head"><label>학습대상</label></li>
                            <li>수강생</li>
                        </ul>
                        <ul class="list">
                            <li class="head"><label>수료기준</label></li>
                            <li>-</li>
                        </ul>
                        <ul class="list">
                            <li class="head"><label>교강사소개</label></li>
                            <li>-</li>
                        </ul>
                    </div>
                </div>
                <div class="segment">
                    <div class="board_top">
                        <h4>회차별 강의정보</h4>
                    </div>
                    <div class="res_tbl_wrap">
                        <table>
                            <caption>성적조회 목록</caption>
                            <thead>
                                <tr>
                                    <th scope="col" width="8%">회차</th>
                                    <th scope="col">회차명</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td data-label="회차">3</td>
                                    <td scope="row" class="title" data-label="회차명"><a href="#0">파이썬 설치 및 강의 환경 설정</a></td>
                                </tr>
                                <tr>
                                    <td data-label="회차">2</td>
                                    <td scope="row" class="title" data-label="회차명"><a href="#0">변수 네이밍 규칙</a></td>
                                </tr>
                                <tr>
                                    <td data-label="회차">1</td>
                                    <td scope="row" class="title" data-label="회차명"><a href="#0">시퀀스 타입 인덱싱</a></td>
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