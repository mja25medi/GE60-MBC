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
                        <h3>시험채점</h3>
                        <button type="button" class="list">목록</button>
                    </div>
                </div>
                <div class="segment">
                    <div class="board_top">
                        <h4>시험정보</h4>
                    </div>
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
                            <button type="button" class="btn type4">문제은행보기</button>
                            <button type="button" class="btn type1">공개하기</button>
                        </div>
                    </div>
                    <div class="res_tbl_wrap">
                        <table>
                            <caption>문제관리 목록</caption>
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
        <!-- modal popup 보여주기 버튼(개발시 팝업id 삭제) -->
        <button class="modal__btn" id="popup6">시험문제 보기 팝업</button>
        <button class="modal__btn" id="popup1">시험문제은행 팝업</button>
        <button class="modal__btn" id="popup2">서술형 문제추가 팝업</button>
        <button class="modal__btn" id="popup3">선택형 문제추가 팝업</button>
        <button class="modal__btn" id="popup4">진위형 문제추가 팝업</button>
        <button class="modal__btn" id="popup5">단답형 문제추가 팝업</button>
        <script type="text/javascript">
            $("#popup1").click(function() {
                $('body').css("overflow", "hidden");
                $("#test_bank").css('display','flex').hide().fadeIn();
            })
            
            $("#popup2").click(function() {
                $('body').css("overflow", "hidden");
                $("#quest_essay").css('display','flex').hide().fadeIn();
            })
            
            $("#popup3").click(function() {
                $('body').css("overflow", "hidden");
                $("#quest_choice").css('display','flex').hide().fadeIn();
            })
            
            $("#popup4").click(function() {
                $('body').css("overflow", "hidden");
                $("#quest_ox").css('display','flex').hide().fadeIn();
            })
            
            $("#popup5").click(function() {
                $('body').css("overflow", "hidden");
                $("#quest_short").css('display','flex').hide().fadeIn();
            })

            $("#popup6").click(function() {
                $('body').css("overflow", "hidden");
                $("#send_sms").css('display','flex').hide().fadeIn();
                iFrameResize({ scrolling: true }, '#iframe1')
            })
        </script>

        <!-- 시험문제 보기 popup -->
        <div id="send_sms" class="modal_popup_wrap">                             
            <div class="modal_popup modal_wide">
                <h4 class="modal_title">시험문제 보기</h4>
                <iframe id="iframe1" src="iframe_quest_view.jsp"></iframe>
                <button type="button" class="modal_close"><i class="xi-close-thin"></i><span class="sr-only">팝업닫기</span></button>
            </div>
        </div>


        <!-- modal popup -->
        <div id="test_bank" class="modal_popup_wrap">                             
            <div class="modal_popup modal_wide">
                <h4 class="modal_title">시험문제은행</h4>
                <div class="modal_cont">
                    <div class="row mb20">
                        <label for="typeSelect" class="form-label col-sm-2">상위분류선택</label>
                        <div class="col-sm-4">
                            <div class="form-row">
                                <select class="form-select" id="typeSelect">
                                    <option value="테스트1">테스트1</option>
                                    <option value="테스트2">테스트2</option>
                                    <option value="테스트3">테스트3</option>
                                </select>
                            </div>             
                        </div>
                        <label for="typeSelect2" class="form-label col-sm-2">하위분류선택</label>
                        <div class="col-sm-4">
                            <div class="form-row">
                                <select class="form-select" id="typeSelect2">
                                    <option value="시험1">시험1</option>
                                    <option value="시험2">시험2</option>
                                    <option value="시험3">시험3</option>
                                </select>
                            </div>             
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
                                    <th scope="col" width="10%"><span class="custom-input onlychk"><input type="checkbox" id="chkall"><label for="chkall"></label></span></th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td scope="row" data-label="번호">1</td>
                                    <td class="title" data-label="시험문제">문학이 현대사회에서 미치는 영향을 고르시오.</td>
                                    <td data-label="문제유형">선택형</td>
                                    <td data-label="선택"><span class="custom-input onlychk"><input type="checkbox" id="chk1"><label for="chk1"></label></span></td>
                                </tr>
                                <tr>
                                    <td scope="row" data-label="번호">2</td>
                                    <td class="title" data-label="시험문제">21세기에서 부의 흐름 방향과 중앙은행에서 어떠한 전략을 수립해야 하는지 간략히 적으시오.</td>
                                    <td data-label="문제유형">서술형</td>
                                    <td data-label="선택"><span class="custom-input onlychk"><input type="checkbox" id="chk2"><label for="chk2"></label></span></td>
                                </tr>
                                <tr>
                                    <td scope="row" data-label="번호">3</td>
                                    <td class="title" data-label="시험문제">아래 보기에 대한 설명을 참고할 때, 숫자에 들어갈 단어는 무엇인가?</td>
                                    <td data-label="문제유형">단답형</td>
                                    <td data-label="선택"><span class="custom-input onlychk"><input type="checkbox" id="chk3"><label for="chk3"></label></span></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="modal_btns">
                    <button type="button" class="btn type2">저장</button>
                    <button type="button" class="btn">닫기</button>
                </div>
                <button type="button" class="modal_close"><i class="xi-close-thin"></i><span class="sr-only">팝업닫기</span></button>
            </div>
        </div>
        <div id="quest_essay" class="modal_popup_wrap">                             
            <div class="modal_popup modal_wide">
                <h4 class="modal_title">서술형 문제추가</h4>
                <div class="modal_cont">
                    <div class="tstyle">
                        <ul class="dbody">
                            <li>
                                <div class="row">
                                    <label for="titleInput" class="form-label col-sm-2">제목<i class="icon_star" aria-hidden="true"></i></label>
                                    <div class="col-sm-10">
                                        <div class="form-row">
                                            <input class="form-control" type="text" name="titleInput" id="titleInput" value="" placeholder="제목을 입력하세요"> 
                                        </div>             
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="row">
                                    <label for="numInput" class="form-label col-sm-2">문제번호</label>
                                    <div class="col-sm-10">
                                        <div class="form-row">
                                            <input class="form-control w20" type="text" name="numInput" id="numInput" value="" maxlength="3"> 
                                        </div>             
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class="form-row">
                                            <textarea class="form-control" rows="10" id="contTextarea"></textarea>
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="row">
                                    <label for="correctInput" class="form-label col-sm-2">정답<i class="icon_star" aria-hidden="true"></i></label>
                                    <div class="col-sm-10">
                                        <div class="form-row">
                                            <input class="form-control" type="text" name="correctInput" id="correctInput" value=""> 
                                        </div>             
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="row">
                                    <label for="commentInput" class="form-label col-sm-2">해설</label>
                                    <div class="col-sm-10">
                                        <div class="form-row">
                                            <input class="form-control" type="text" name="commentInput" id="commentInput" value=""> 
                                        </div>             
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="modal_btns">
                    <button type="button" class="btn type2">저장</button>
                    <button type="button" class="btn">닫기</button>
                </div>
                <button type="button" class="modal_close"><i class="xi-close-thin"></i><span class="sr-only">팝업닫기</span></button>
            </div>
        </div>
        <div id="quest_choice" class="modal_popup_wrap">                             
            <div class="modal_popup modal_wide">
                <h4 class="modal_title">선택형 문제추가</h4>
                <div class="modal_cont">
                    <div class="tstyle">
                        <ul class="dbody">
                            <li>
                                <div class="row">
                                    <label for="titleInput" class="form-label col-sm-2">제목<i class="icon_star" aria-hidden="true"></i></label>
                                    <div class="col-sm-10">
                                        <div class="form-row">
                                            <input class="form-control" type="text" name="titleInput" id="titleInput" value="" placeholder="제목을 입력하세요"> 
                                        </div>             
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="row">
                                    <label for="numInput" class="form-label col-sm-2">문제번호</label>
                                    <div class="col-sm-10">
                                        <div class="form-row">
                                            <input class="form-control w20" type="text" name="numInput" id="numInput" value="" maxlength="3"> 
                                        </div>             
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class="form-row">
                                            <textarea class="form-control" rows="10" id="contTextarea"></textarea>
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="row">
                                    <label for="examInput1" class="form-label col-sm-2">보기1</label>
                                    <div class="col-sm-10">
                                        <div class="form-row">
                                            <input class="form-control" type="text" name="examInput1" id="examInput1" value=""> 
                                        </div>             
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="row">
                                    <label for="examInput2" class="form-label col-sm-2">보기2</label>
                                    <div class="col-sm-10">
                                        <div class="form-row">
                                            <input class="form-control" type="text" name="examInput2" id="examInput2" value=""> 
                                        </div>             
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="row">
                                    <label for="examInput3" class="form-label col-sm-2">보기3</label>
                                    <div class="col-sm-10">
                                        <div class="form-row">
                                            <input class="form-control" type="text" name="examInput3" id="examInput3" value=""> 
                                        </div>             
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="row">
                                    <label for="examInput4" class="form-label col-sm-2">보기4</label>
                                    <div class="col-sm-10">
                                        <div class="form-row">
                                            <input class="form-control" type="text" name="examInput4" id="examInput4" value=""> 
                                        </div>             
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="row">
                                    <label for="examInput5" class="form-label col-sm-2">보기5</label>
                                    <div class="col-sm-10">
                                        <div class="form-row">
                                            <input class="form-control" type="text" name="examInput5" id="examInput5" value=""> 
                                        </div>             
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="row">
                                    <label for="exam1" class="form-label col-sm-2">정답<i class="icon_star" aria-hidden="true"></i></label>
                                    <div class="col-sm-10">
                                        <div class="form-row m_flex_column">
                                            <span class="custom-input">
                                                <input type="radio" name="examChoice" id="exam1">
                                                <label for="exam1">보기1</label>
                                            </span>
                                            <span class="custom-input">
                                                <input type="radio" name="examChoice" id="exam2">
                                                <label for="exam2">보기2</label>
                                            </span>
                                            <span class="custom-input">
                                                <input type="radio" name="examChoice" id="exam3">
                                                <label for="exam3">보기3</label>
                                            </span>
                                            <span class="custom-input">
                                                <input type="radio" name="examChoice" id="exam4">
                                                <label for="exam4">보기4</label>
                                            </span>
                                            <span class="custom-input">
                                                <input type="radio" name="examChoice" id="exam5">
                                                <label for="exam5">보기5</label>
                                            </span>
                                        </div>             
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="row">
                                    <label for="commentInput" class="form-label col-sm-2">해설</label>
                                    <div class="col-sm-10">
                                        <div class="form-row">
                                            <input class="form-control" type="text" name="commentInput" id="commentInput" value=""> 
                                        </div>             
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="modal_btns">
                    <button type="button" class="btn type2">저장</button>
                    <button type="button" class="btn">닫기</button>
                </div>
                <button type="button" class="modal_close"><i class="xi-close-thin"></i><span class="sr-only">팝업닫기</span></button>
            </div>
        </div>
        <div id="quest_ox" class="modal_popup_wrap">                             
            <div class="modal_popup modal_wide">
                <h4 class="modal_title">진위형 문제추가</h4>
                <div class="modal_cont">
                    <div class="tstyle">
                        <ul class="dbody">
                            <li>
                                <div class="row">
                                    <label for="titleInput" class="form-label col-sm-2">제목<i class="icon_star" aria-hidden="true"></i></label>
                                    <div class="col-sm-10">
                                        <div class="form-row">
                                            <input class="form-control" type="text" name="titleInput" id="titleInput" value="" placeholder="제목을 입력하세요"> 
                                        </div>             
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="row">
                                    <label for="numInput" class="form-label col-sm-2">문제번호</label>
                                    <div class="col-sm-10">
                                        <div class="form-row">
                                            <input class="form-control w20" type="text" name="numInput" id="numInput" value="" maxlength="3"> 
                                        </div>             
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class="form-row">
                                            <textarea class="form-control" rows="10" id="contTextarea"></textarea>
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="row">
                                    <label for="exam21" class="form-label col-sm-2">정답<i class="icon_star" aria-hidden="true"></i></label>
                                    <div class="col-sm-10">
                                        <div class="form-row">
                                            <span class="custom-input">
                                                <input type="radio" name="examChoice2" id="exam21">
                                                <label for="exam21">O</label>
                                            </span>
                                            <span class="custom-input">
                                                <input type="radio" name="examChoice2" id="exam22">
                                                <label for="exam22">X</label>
                                            </span>
                                        </div>             
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="row">
                                    <label for="commentInput" class="form-label col-sm-2">해설</label>
                                    <div class="col-sm-10">
                                        <div class="form-row">
                                            <input class="form-control" type="text" name="commentInput" id="commentInput" value=""> 
                                        </div>             
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="modal_btns">
                    <button type="button" class="btn type2">저장</button>
                    <button type="button" class="btn">닫기</button>
                </div>
                <button type="button" class="modal_close"><i class="xi-close-thin"></i><span class="sr-only">팝업닫기</span></button>
            </div>
        </div>
        <div id="quest_short" class="modal_popup_wrap">                             
            <div class="modal_popup modal_wide">
                <h4 class="modal_title">단답형 문제추가</h4>
                <div class="modal_cont">
                    <div class="tstyle">
                        <ul class="dbody">
                            <li>
                                <div class="row">
                                    <label for="titleInput" class="form-label col-sm-2">제목<i class="icon_star" aria-hidden="true"></i></label>
                                    <div class="col-sm-10">
                                        <div class="form-row">
                                            <input class="form-control" type="text" name="titleInput" id="titleInput" value="" placeholder="제목을 입력하세요"> 
                                        </div>             
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="row">
                                    <label for="numInput" class="form-label col-sm-2">문제번호</label>
                                    <div class="col-sm-10">
                                        <div class="form-row">
                                            <input class="form-control w20" type="text" name="numInput" id="numInput" value="" maxlength="3"> 
                                        </div>             
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class="form-row">
                                            <textarea class="form-control" rows="10" id="contTextarea"></textarea>
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="row">
                                    <label for="correctInput5" class="form-label col-sm-2">정답<i class="icon_star" aria-hidden="true"></i></label>
                                    <div class="col-sm-10">
                                        <div class="form-row">
                                            <input class="form-control" type="text" name="correctInput" id="correctInput5" value=""> 
                                        </div>             
                                        <ul class="message_box mt10">
                                           <li>단답형의 중복답안 처리에 대한 답안 구분은 파이프('|' Shift+\)로 해 주세요.</li>
                                        </ul>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="row">
                                    <label for="commentInput" class="form-label col-sm-2">해설</label>
                                    <div class="col-sm-10">
                                        <div class="form-row">
                                            <input class="form-control" type="text" name="commentInput" id="commentInput" value=""> 
                                        </div>             
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="modal_btns">
                    <button type="button" class="btn type2">저장</button>
                    <button type="button" class="btn">닫기</button>
                </div>
                <button type="button" class="modal_close"><i class="xi-close-thin"></i><span class="sr-only">팝업닫기</span></button>
            </div>
        </div>
        <!-- //modal popup -->
    </div>
</body>
</html>