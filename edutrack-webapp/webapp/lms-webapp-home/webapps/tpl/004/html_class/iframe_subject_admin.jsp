<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">
<%@ include file="../inc/class_common.jsp" %>
<script src="../js/iframeResizer.contentWindow.min.js"></script>
<body class="scroll_custom">
    <div class="modal_cont">
        <div class="board_top type2">
            <div class="left_title">
                <h4>학습 목차 설정: 1차시 1-1차시 파이썬 설치 및 가상환경 구축</h4>
            </div>
            <div class="page_btn">
                <button type="button" class="btn type2">저장</button>
                <button type="button" class="btn type9">삭제</button>
                <button type="button" class="btn">닫기</button>
            </div>
        </div>
        <div class="row subject_pop">
            <div class="col-sm-6">
                <div class="segment">
                    <ul class="class_tab">
                        <li class="active"><a href="#">VOD</a></li>
                        <li><a href="#">CDN</a></li>
                        <li><a href="#">영상링크</a></li>
                        <li><a href="#">콘텐츠파일</a></li>
                    </ul>
                   
                    <div class="type-multi">
                        <select class="form-select mr5" id="typeSelect">
                            <option value="TEST">TEST</option>
                            <option value="TEST2">TEST2</option>
                        </select>
                        <input class="form-control mr5" type="text" name="typeInput" id="typeInput" value="" placeholder=""> 
                        <form name="srhForm" method="post" action="" class="board_search ">
                            <fieldset class="form-row">
                                <legend class="blind">검색</legend>
                                <input type="text" class="form-control" name="keyWord" placeholder="" title="검색어를 입력하세요" value="">
                                <button type="submit" class="btn type2">검색</button>
                            </fieldset>
                        </form>
                    </div>
                    <div class="subject-area scroll_custom">
                        <ul>
                            <li>
                                데이터분석을 위한 파이썬 시작하기 5강-4
                                <span class="btns">
                                    <button class="btn3 sm basic fcDarkgray">미리보기</button>
                                    <button class="btn3 sm basic fcBlue">선택</button>
                                </span>
                            </li>
                            <li>
                                데이터분석을 위한 파이썬 시작하기 5강-4
                                <span class="btns">
                                    <button class="btn3 sm basic fcDarkgray">미리보기</button>
                                    <button class="btn3 sm basic fcBlue">선택</button>
                                </span>
                            </li>
                            <li>
                                데이터분석을 위한 파이썬 시작하기 5강-4
                                <span class="btns">
                                    <button class="btn3 sm basic fcDarkgray">미리보기</button>
                                    <button class="btn3 sm basic fcBlue">선택</button>
                                </span>
                            </li>
                            <li>
                                데이터분석을 위한 파이썬 시작하기 5강-4
                                <span class="btns">
                                    <button class="btn3 sm basic fcDarkgray">미리보기</button>
                                    <button class="btn3 sm basic fcBlue">선택</button>
                                </span>
                            </li>
                            <li>
                                데이터분석을 위한 파이썬 시작하기 5강-4
                                <span class="btns">
                                    <button class="btn3 sm basic fcDarkgray">미리보기</button>
                                    <button class="btn3 sm basic fcBlue">선택</button>
                                </span>
                            </li>
                            <li>
                                데이터분석을 위한 파이썬 시작하기 5강-4
                                <span class="btns">
                                    <button class="btn3 sm basic fcDarkgray">미리보기</button>
                                    <button class="btn3 sm basic fcBlue">선택</button>
                                </span>
                            </li>
                            <li>
                                데이터분석을 위한 파이썬 시작하기 5강-4
                                <span class="btns">
                                    <button class="btn3 sm basic fcDarkgray">미리보기</button>
                                    <button class="btn3 sm basic fcBlue">선택</button>
                                </span>
                            </li>
                            <li>
                                데이터분석을 위한 파이썬 시작하기 5강-4
                                <span class="btns">
                                    <button class="btn3 sm basic fcDarkgray">미리보기</button>
                                    <button class="btn3 sm basic fcBlue">선택</button>
                                </span>
                            </li>
                            <li>
                                데이터분석을 위한 파이썬 시작하기 5강-4
                                <span class="btns">
                                    <button class="btn3 sm basic fcDarkgray">미리보기</button>
                                    <button class="btn3 sm basic fcBlue">선택</button>
                                </span>
                            </li>
                        </ul>
                    </div>
                    
                </div>
            </div>
            <div class="col-sm-6">
                <div class="segment subject_pop">
                    <div class="tstyle">
                        <ul class="dbody">                            
                            <li>
                                <div class="row">
                                    <label for="titleInput" class="form-label col-sm-2">차시명</label>
                                    <div class="col-sm-10">
                                        <div class="form-row">
                                            <input class="form-control" type="text" name="titleInput" id="titleInput" value="1-1차시 파이썬 설치 및 가상환경 구축" maxlength="100" placeholder=""> 
                                        </div>             
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="row">
                                    <label for="conType" class="form-label col-sm-2">콘텐츠 타입</label>
                                    <div class="col-sm-10">
                                        <div class="form-row">
                                            <div class="ra_inline">
                                                <span class="custom-input">
                                                    <input type="radio" name="conType" id="conTypeA" value="Y" checked="">
                                                    <label for="conTypeA">VOD</label>
                                                </span>
                                                <span class="custom-input ml5">
                                                    <input type="radio" name="conType" id="conTypeB" value="N">
                                                    <label for="conTypeB">CDN</label>
                                                </span>
                                                <span class="custom-input ml5">
                                                    <input type="radio" name="conType" id="conTypeC" value="N">
                                                    <label for="conTypeC">코딩강의</label>
                                                </span>
                                                <span class="custom-input ml5">
                                                    <input type="radio" name="conType" id="conTypeD" value="N">
                                                    <label for="conTypeD">코딩실습</label>
                                                </span>
                                            </div>
                                        </div>             
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="row">
                                    <label for="ZoomInput" class="form-label col-sm-2">Zoom URL</label>
                                    <div class="col-sm-10">
                                        <div class="form-row">
                                            <input class="form-control" type="text" name="ZoomInput" id="ZoomInput" value="" maxlength="100" placeholder="입력해주세요"> 
                                        </div>             
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="row">
                                    <label for="conInput" class="form-label col-sm-2">콘텐츠 경로</label>
                                    <div class="col-sm-10">
                                        <div class="form-row">
                                            <div class="input_btn full">
                                                <input class="form-control" id="conInput" type="text"><label><a href="#"><i class="xi-close"></i></a></label>
                                            </div>
                                        </div>             
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="row">
                                    <label for="pdfInput" class="form-label col-sm-2">교안파일경로(PDF)</label>
                                    <div class="col-sm-10">
                                        <div class="form-row">
                                            <div class="input_btn full">
                                                <input class="form-control" id="pdfInput" type="text" disabled><label><i class="xi-close"></i></label>
                                            </div>
                                        </div>             
                                    </div>
                                </div>
                            </li>
                                       
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
</body>
</html>