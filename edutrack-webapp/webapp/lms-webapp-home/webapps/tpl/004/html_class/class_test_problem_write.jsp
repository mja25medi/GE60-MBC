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
                        <h3>이의제기</h3>
                    </div>
                </div>
                <div class="segment">
                    <div class="tstyle">
                        <ul class="dbody">
                            <li>
                                <div class="row">
                                    <label for="titleInput" class="form-label col-sm-2">제목</label>
                                    <div class="col-sm-10">
                                        <div class="form-row">
                                            <input class="form-control" type="text" name="titleInput" id="titleInput" value="" maxlength="100" placeholder="제목을 입력하세요"> 
                                        </div>             
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="row">
                                    <label for="contTextarea" class="form-label col-sm-2">내용</label>
                                    <div class="col-sm-10">
                                        <div class="form-row">
                                            <textarea class="form-control" rows="10" id="contTextarea"></textarea>
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="row">
                                    <span class="form-label col-sm-2">첨부파일</span>
                                    <div class="col-sm-10 attach_area">
                                        <input type="file" class="input_attach sr-only" id="attchFile" multiple><label for="attchFile" class="btn gray2">파일 찾기</label> 
                                        <div class="attach_list">
                                            <div class='attach_name'>첨부파일첨부파일첨부파일.png<button type='button' class='attach_close'><i class='xi-close'></i><span class='sr-only'>파일삭제</span></button></div>
                                            <div class='attach_name'>동물관련종사자_입력폼.jpg<button type='button' class='attach_close'><i class='xi-close'></i><span class='sr-only'>파일삭제</span></button></div>
                                            <div class='attach_name'>KakaoTalk_20210729_131327900.png<button type='button' class='attach_close'><i class='xi-close'></i><span class='sr-only'>파일삭제</span></button></div>
                                        </div>                                            
                                    </div> 
                                </div>
                            </li>                              
                        </ul>
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