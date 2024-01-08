<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">
<%@ include file="../inc/home_common.jsp" %>
<script src="../js/iframeResizer.contentWindow.min.js"></script>
<link rel="stylesheet" href="../css/sub.css"><!-- sub 페이지에서 사용 -->

<body class="scroll_custom">
    <div class="modal_cont">
        <div class="tstyle mb30">
            <ul class="dbody">                       
                <li>
                    <div class="row">
                        <label for="a1" class="form-label col-sm-2">시설명</label>
                        <div class="col-sm-10">
                            <div class="form-row">
                                <select id="a1" class="form-control w50">
                                    <option>선택하세요</option>
                                    <option>test1</option>
                                    <option>test2</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </li> 
                <li>
                    <div class="row">
                        <label for="a2" class="form-label col-sm-2">날짜</label>
                        <div class="col-sm-10">
                            <div class="form-row">
                                <input type="date" class="form-control w50" id="testCalender">
                            </div>
                        </div>
                    </div>
                </li> 
                <li>
                    <div class="row">
                        <label for="a3" class="form-label col-sm-2">시간</label>
                        <div class="col-sm-10">
                            <div class="form-row">
                                <select id="a3" class="form-control w40" >
                                    <option>시작시간</option>
                                    <option>00:00</option>
                                    <option>01:00</option>
                                </select>
                                <select id="a4" class="form-control w40" >
                                    <option>종료시간</option>
                                    <option>00:00</option>
                                    <option>01:00</option>
                                </select>
                                <button type="button" class="btn solid dark ml5">조회</button>
                            </div>
                        </div>
                    </div>
                </li> 
                <li>
                    <div class="row">
                        <label for="contTextarea" class="form-label col-sm-2">사용목적</label>
                        <div class="col-sm-10">
                            <div class="form-row">
                                <textarea class="form-control" rows="5" id="contTextarea"></textarea>
                            </div>
                        </div>
                    </div>
                </li>   
                <li>
                    <div class="row">
                        <label for="a6" class="form-label col-sm-2">사용인원</label>
                        <div class="col-sm-10">
                            <div class="form-row">
                                <select id="a6" class="form-control w50">
                                    <option>선택하세요</option>
                                    <option>10</option>
                                    <option>20</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </li>                        
            </ul>
            
        </div>
      
    </div>
    <div class="modal_btns">
        <button type="button" class="btn type2">취소</button>
        <button type="button" class="btn type4">확인</button>
    </div>
</body>
</html>