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
                        <h3>시험등록</h3>
                    </div>
                </div>
                <div class="segment">
                    <div class="tstyle">
                        <ul class="dbody">
                            <li>
                                <div class="row">
                                    <label for="typeSelect" class="form-label col-sm-2">시험유형</label>
                                    <div class="col-sm-4">
                                        <div class="form-row">
                                            <select class="form-select" id="typeSelect">
                                                <option value="온라인">온라인</option>
                                                <option value="오프라인">오프라인</option>
                                                <option value="혼합">혼합</option>
                                            </select>
                                        </div>             
                                    </div>
                                    <label for="typeSelect2" class="form-label col-sm-2">응시유형</label>
                                    <div class="col-sm-4">
                                        <div class="form-row">
                                            <select class="form-select" id="typeSelect2">
                                                <option value="정규시험">정규시험</option>
                                                <option value="모의시험">모의시험</option>
                                            </select>
                                        </div>             
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="row">
                                    <label for="titleInput" class="form-label col-sm-2">시험제목</label>
                                    <div class="col-sm-10">
                                        <div class="form-row">
                                            <input class="form-control" type="text" name="titleInput" id="titleInput" value="" maxlength="100" placeholder="제목을 입력하세요"> 
                                        </div>             
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="row">
                                    <label for="testCalender" class="form-label col-sm-2"><span>시험기간</span></label>
                                    <div class="col-sm-10">
                                        <div class="form-inline">
                                            <input type="date" class="form-control md" id="testCalender">
                                            <div class="input_btn">
                                                <input class="form-control sm" type="text" maxlength="2"><label>시</label>
                                            </div>
                                            <div class="input_btn">
                                                <input class="form-control sm" type="text" maxlength="2"><label>분</label>
                                            </div><span class="ruffle_sign">~</span>
                                        </div>
                                        <div class="form-inline">
                                            <input type="date" class="form-control md" id="testCalender">
                                            <div class="input_btn">
                                                <input class="form-control sm" type="text" maxlength="2"><label>시</label>
                                            </div>
                                            <div class="input_btn">
                                                <input class="form-control sm" type="text" maxlength="2"><label>분</label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="row">
                                    <label for="resultCalender" class="form-label col-sm-2"><span>결과확인일</span></label>
                                    <div class="col-sm-10">
                                        <div class="form-inline">
                                            <input type="date" class="form-control md" id="resultCalender">
                                            <div class="input_btn">
                                                <input class="form-control sm" type="text" maxlength="2"><label>시</label>
                                            </div>
                                            <div class="input_btn">
                                                <input class="form-control sm" type="text" maxlength="2"><label>분</label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="row">
                                    <label for="timeSelect" class="form-label col-sm-2">시간제한</label>
                                    <div class="col-sm-4">
                                        <div class="form-row">
                                            <select class="form-select" id="timeSelect">
                                                <option value="사용함">사용함</option>
                                                <option value="사용안함">사용안함</option>
                                            </select>
                                        </div>             
                                    </div>
                                    <label for="timeInput" class="form-label col-sm-2">시간</label>
                                    <div class="col-sm-4">
                                        <div class="form-row">
                                            <div class="input_btn">
                                                <input class="form-control sm" id="timeInput" type="text" maxlength="2"><label>분</label>
                                            </div>
                                        </div>             
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="row">
                                    <label for="rateSelect" class="form-label col-sm-2">응시가능진도율</label>
                                    <div class="col-sm-4">
                                        <div class="form-row">
                                            <div class="input_btn">
                                                <input class="form-control md" id="rateSelect" type="text" maxlength="3"><label>%이상</label>
                                            </div>
                                        </div>             
                                    </div>
                                    <label for="limitInput" class="form-label col-sm-2">응시제한횟수</label>
                                    <div class="col-sm-4">
                                        <div class="form-row">
                                            <div class="input_btn">
                                                <input class="form-control sm" id="limitInput" type="text" maxlength="2"><label>회</label>
                                            </div>
                                        </div>             
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="row">
                                    <label for="pointInput" class="form-label col-sm-2">시험배점</label>
                                    <div class="col-sm-10">
                                        <div class="form-row">
                                            <div class="res_tbl_wrap">
                                                <table>
                                                    <caption>시험배점 목록</caption>
                                                    <thead>
                                                        <tr>
                                                            <th scope="col">종류</th>
                                                            <th scope="col">문항수</th>
                                                            <th scope="col">배점</th>
                                                            <th scope="col">합(문항수X배점)</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <tr>
                                                            <td scope="row" data-label="종류">선택형</td>
                                                            <td data-label="문항수"><input class="form-control w50" id="pointInput" type="text" maxlength="3"></td>
                                                            <td data-label="배점"><input class="form-control w50" type="text" maxlength="3"></td>
                                                            <td data-label="합(문항수X배점)"><input class="form-control w50" type="text" maxlength="3" disabled></td>
                                                        </tr>
                                                        <tr>
                                                            <td scope="row" data-label="종류">단답형</td>
                                                            <td data-label="문항수"><input class="form-control w50" type="text" maxlength="3"></td>
                                                            <td data-label="배점"><input class="form-control w50" type="text" maxlength="3"></td>
                                                            <td data-label="합(문항수X배점)"><input class="form-control w50" type="text" maxlength="3" disabled></td>
                                                        </tr>
                                                        <tr>
                                                            <td scope="row" data-label="종류">서술형</td>
                                                            <td data-label="문항수"><input class="form-control w50" type="text" maxlength="3"></td>
                                                            <td data-label="배점"><input class="form-control w50" type="text" maxlength="3"></td>
                                                            <td data-label="합(문항수X배점)"><input class="form-control w50" type="text" maxlength="3" disabled></td>
                                                        </tr>
                                                        <tr>
                                                            <td scope="row" data-label="종류">총합</td>
                                                            <td></td>
                                                            <td></td>
                                                            <td data-label="합(문항수X배점)"><input class="form-control w50" type="text" maxlength="3" disabled></td>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                            </div>             
                                        </div>             
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="row">
                                    <label for="contTextarea" class="form-label col-sm-2">시험설명</label>
                                    <div class="col-sm-10">
                                        <div class="form-row">
                                            <textarea class="form-control" rows="10" id="contTextarea"></textarea>
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