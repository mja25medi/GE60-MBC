<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">
<%@ include file="../inc/class_common.jsp" %>
<script src="../js/iframeResizer.contentWindow.min.js"></script>
<body class="scroll_custom">
    <div class="modal_cont">
        <div class="list_view_box mt0">
            <div class="list_title mt0">
                <div class="type"><span class="num">1.</span>선택문항</div>
                <div class="score">선택형 : 10.0 / 10.0점</div>
            </div>
            <div class="list_item">
                <div class="quest">문학이 현대사회에서 미치는 영향을 고르시오.</div>
                <ol class="field">
                    <li class="ui checkbox">
                        <input type="radio" id="check01" name="quest01" class="hidden">
                        <label for="check01" class="choice">
                            <div class="form-row">
                                <input class="form-control " type="text" name="check01" id="check01" value="21세기의 문학도들이 보다 쉽게 문학에 다가설 수 있도록 하였다." placeholder="내용을 작성하세요">
                            </div>
                        </label>
                    </li>
                    <li class="ui checkbox">
                        <input type="radio" id="check02" name="quest01" class="hidden">
                        <label for="check02" class="choice">
                            <div class="form-row">
                                <input class="form-control " type="text" name="check02" id="check02" value="한 사회의 거울이며 현실의 반영물인 문학에 대한 논의가 가능해질 것이다." placeholder="내용을 작성하세요">
                            </div>
                        </label>
                    </li>
                    <li class="ui checkbox">
                        <input type="radio" id="check03" name="quest01" class="hidden">
                        <label for="check03" class="choice">
                            <div class="form-row">
                                <input class="form-control " type="text" name="check03" id="check03" value="한국 문학의 영역과 갈래가 확연히 구분되었다." placeholder="내용을 작성하세요">
                            </div>
                        </label>
                    </li>
                    <li class="ui checkbox">
                        <input type="radio" id="check04" name="quest01" class="hidden">
                        <label for="check04" class="choice">
                            <div class="form-row">
                                <input class="form-control " type="text" name="check04" id="check04" value="문학의 개념이 개인주의에서 사회주의로 바뀌었다." placeholder="내용을 작성하세요">
                            </div>
                        </label>
                    </li>
                    <li class="ui checkbox">
                        <input type="radio" id="check05" name="quest01" class="hidden">
                        <label for="check05" class="choice">
                            <div class="form-row">
                                <input class="form-control " type="text" name="check05" id="check05" value="문학과 현대사회는 아무런 관련이 없다." placeholder="내용을 작성하세요">
                            </div>
                        </label>
                    </li>
                </ol>
                <ul class="quest_write">
                    <li><strong for="explanArea">해설</strong>
                        <div class="form-row">
                            <textarea class="form-control" rows="3" id="explanArea" placeholder="내용을 작성하세요"></textarea>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
        <div class="list_view_box">
            <div class="list_title">
                <div class="type"><span class="num">2.</span>OX문항</div>
                <div class="score">OX형 : 10.0 / 10.0점</div>
            </div>
            <div class="list_item">
                <div class="quest">세상에서 가장 알기쉬운 문학퀴즈에 대해 여러분 도움이 된다고 생각하나요?</div>
                
                <ul class="quest_write">
                    <li><strong for="rightArea">정답</strong>
                        <div class="checkImg">
                            <input id="imgChk_true" type="radio" name="imgChk">
                            <label class="imgChk true" for="imgChk_true"></label>
                            <input id="imgChk_false" type="radio" name="imgChk">
                            <label class="imgChk false" for="imgChk_false"></label>
                        </div>
                    </li>
                    <li><strong for="explanArea">해설</strong>
                        <div class="form-row">
                            <textarea class="form-control" rows="3" id="explanArea" placeholder="내용을 작성하세요"></textarea>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
        <div class="list_view_box">
            <div class="list_title">
                <div class="type"><span class="num">3.</span>단답문항</div>
                <div class="score">단답형 : 10.0 / 10.0점</div>
            </div>
            <div class="list_item">
                <div class="quest">아래 보기에 대한 설명을 참고할 때, 숫자에 들어갈 단어는 무엇인가?</div>
                <div class="form-row">
                    <textarea rows="5" class="form-control" placeholder="내용을 작성하세요">18세기 낭만주의의 태동과 철학자 (1)에 의해 제기된 것으로 인간의 순수 이성으로는 표현이 불가능한 것이 있다는 전제하에, 문학은 인간의 (2) 본능에서 시작되었다는 것이다. 정해진 의도를 벗어나는 탐구에 관해서 책의 저자인 (3)는 ‘우리는 책을 통해서 우리의 정당성을 밝혀야 한다고 생각한다. 비록 먼 훗날 우리가 과오를 저질렀다는 판정이 내린다 해도 미리부터 과오를 두려워해서는 안 된다고 생각한다.</textarea>
                </div>
                <ul class="quest_write">
                    <li><strong for="rightArea">정답</strong>
                        <div class="form-row">
                            <input class="form-control" type="text" name="rightArea" id="rightArea" value="태동" placeholder="내용을 작성하세요">
                        </div>
                    </li>
                    <li><strong for="explanArea">해설</strong>
                        <div class="form-row">
                            <textarea class="form-control" rows="3" id="explanArea" placeholder="내용을 작성하세요"></textarea>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
        <div class="list_view_box">
            <div class="list_title">
                <div class="type"><span class="num">4.</span>서술문항</div>
                <div class="score">서술형 : 10.0 / 10.0점</div>
            </div>
            <div class="list_item">
                <div class="quest">21세기에서 부의 흐름 방향과 중앙은행에서 어떠한 전략을 수립해야 하는지 간략히 적으시오.</div>
                <div class="form-row">
                    <textarea rows="5" class="form-control" placeholder="내용을 작성하세요"></textarea>
                </div>
                <ul class="quest_write">
                    <li><strong for="rightArea">정답</strong>
                        <div class="form-row">
                            <textarea class="form-control" rows="3" id="rightArea" placeholder="내용을 작성하세요"></textarea>
                        </div>
                    </li>
                    <li><strong for="explanArea">해설</strong>
                        <div class="form-row">
                            <textarea class="form-control" rows="3" id="explanArea" placeholder="내용을 작성하세요"></textarea>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
        
    </div>
    <div class="modal_btns">
        <button type="button" class="btn type2">저장</button>
        <button type="button" class="btn">닫기</button>
    </div>
</body>
</html>