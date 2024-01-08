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
                        <h3>강의관리</h3>
                    </div>
                </div>
                <ul class="class_tab">
                    <li><a href="#">과목1</a></li>
                    <li class="active"><a href="#">과목1</a></li>
                    <li><a href="#">과목3</a></li>
                </ul>
                <div class="segment">
                    <div class="board_top">
                        <h4>기본정보</h4>
                    </div>                
                    <div class="tstyle">
                        <ul class="dbody">
                            <li>
                                <div class="row">
                                    <label for="titleInput" class="form-label col-sm-2">과목명<i class="icon_star"></i></label>
                                    <div class="col-sm-10">
                                        <div class="form-row">
                                            (오프라인)  Python 실습
                                        </div>             
                                    </div>
                                </div>
                            </li>                            
                            <li>
                                <div class="row">
                                    <span class="form-label col-sm-2">교육기간<i class="icon_star"></i></span>
                                    <div class="col-sm-10">
                                        <div class="form-inline">
                                            <input type="date" class="form-control md" id="testCalender">
                                            <span class="ml5 mr5">~</span>
                                            <input type="date" class="form-control md" id="testCalender">
                                        </div>                                      
                                    </div> 
                                </div>
                            </li>                              
                        </ul>
                    </div>
                </div>
                
                <div class="segment">
                    <div class="board_top">
                        <div class="left_title">
                            <h4>학습 목차 설정</h4>
                        </div>
                        <div class="right_btn">
                            <button type="button" class="btn type4">목차추가</button>
                        </div>
                    </div>
                    <div class="res_tbl_wrap lecture_setting">
                        <table>
                            <caption>학습 목차 설정 목록</caption>
                            <thead>
                                <tr>
                                    <th scope="col" width="15%">목차</th>
                                    <th scope="col">목차명</th>
                                    <th scope="col" width="15%">설정</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td scope="row" data-label="목차">1차시</td>
                                    <td class="title ico_type" data-label="목차명">
                                        <input class="form-control w80" id="pointInput" type="text" value="목차명이 표시 됩니다.">
                                        <img src="../img/class/icon_course_link.svg" alt="icon" title="영상링크">
                                        <img src="../img/class/icon_course_cdn.svg" alt="icon" title="CDN">
                                    </td>
                                    <td data-label="설정"><button class="btn type3">설정</button></td>
                                </tr>
                                <tr>
                                    <td scope="row" data-label="목차">2차시</td>
                                    <td class="title ico_type" data-label="목차명">
                                        <input class="form-control w80" id="pointInput" type="text" value="목차명이 표시 됩니다.">
                                        <img src="../img/class/icon_course_video.svg" alt="icon" title="Zoom">
                                        <img src="../img/class/icon_course_code.svg" alt="icon" title="코딩학습">
                                    </td>
                                    <td data-label="설정"><button class="btn type3">설정</button></td>
                                </tr>
                                <tr>
                                    <td scope="row" data-label="목차">2차시</td>
                                    <td class="title ico_type" data-label="목차명">
                                        <input class="form-control w80" id="pointInput" type="text" value="목차명이 표시 됩니다.">
                                        <img src="../img/class/icon_course_play.svg" alt="icon" title="VOD">
                                        <img src="../img/class/icon_course_code.svg" alt="icon" title="코딩학습">
                                    </td>
                                    <td data-label="설정"><button class="btn type3">설정</button></td>
                                </tr>
                            </tbody>
                        </table>
                    </div> 
                    <div class="btns mt30">
                        <button type="button" class="btn gray2">저장</button>
                        <button type="button" class="btn type5">취소</button>
                    </div>
                </div>

            
            </div>
        </div>
    </div>
</body>
</html>