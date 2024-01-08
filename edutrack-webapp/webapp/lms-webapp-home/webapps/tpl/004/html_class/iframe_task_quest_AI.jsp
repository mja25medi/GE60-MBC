<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">
<%@ include file="../inc/class_common.jsp" %>
<script src="../js/iframeResizer.contentWindow.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bodymovin/5.6.6/lottie.min.js"></script>

<body class="scroll_custom">
    <div class="modal_cont">
        <div class="row AI_task">
            <div class="col-sm-6">
                <div class="segment">
                    <div class="board_top">
                        <h4>AI 출제 문제</h4>
                    </div>
                    <div class="AI_task_txt">
                        <strong>문제제목</strong><br>
                        java 코딩 문제 입니다.<br><br>

                        <strong>문제 설명</strong><br>
    주어진 정수 n이 홀수인지 짝수인지 확인하는 프로그램을 작성하시오<br><br>

    <strong>제한사항</strong><br>
    테스트케이스1. 변수n에 13이라는 정수값을 대입하기<br>
    테이스케이스2. if~else 문을 사용하여 코드를 작성하기<br>
    테스트케이스3. 조건문으로 n을 2로 나눈 나머지가 0인지 아닌지로 판단하기<br>
    테스트케이스4. 코드를 컴파일 해서 결과값으로 '홀수'를 출력하기
                    </div>

                    <div class="AI_task_wave">
                        <div data-aos="fade-right" data-aos-anchor=".info-element" id="load-ing" class="lottie_motion"></div>
                        <script>
                            var animData = {
                                container: document.getElementById('load-ing'),
                                animType: 'svg',
                                loop: true,
                                prerender: false,
                                autoplay: true,
                                path: '../img/class/Animation_load.json'

                            };
                            var anim = bodymovin.loadAnimation(animData);
                            window.onresize = anim.resize.bind(anim);
                        </script>
                        AI 문제 출제 중
                    </div>
                    <div class="AI_task_wave">
                        <div data-aos="fade-right" data-aos-anchor=".info-element" id="wave-ing" class="lottie_motion"></div>
                        <script>
                            var animData = {
                                container: document.getElementById('wave-ing'),
                                animType: 'svg',
                                loop: true,
                                prerender: false,
                                autoplay: true,
                                path: '../img/class/Animation_wave.json'

                            };
                            var anim = bodymovin.loadAnimation(animData);
                            window.onresize = anim.resize.bind(anim);
                        </script>
                        AI 문제를 출제 해 주세요.
                    </div>
                </div>
            </div>
         
            <div class="col-sm-6">
                <div class="tstyle">
                    <ul class="dbody">
                        <li>
                            <div class="row">
                                <label for="titleInput" class="form-label col-sm-3">문제 제목 <i class="icon_star"></i></label>
                                <div class="col-sm-9">
                                    <div class="form-row">
                                        <input class="form-control" type="text" name="titleInput" id="titleInput" value="" placeholder="제목을 입력하세요"> 
                                    </div>             
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="row">
                                <label for="levelInput" class="form-label col-sm-3">난이도<i class="icon_star"></i></label>
                                <div class="col-sm-9">
                                    <div class="form-row">
                                        <select class="form-select w40" id="levelInput">
                                            <option value="상">상</option>
                                            <option value="중">중</option>
                                            <option value="하">하</option>
                                        </select>
                                    </div>             
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="row">
                                <label for="lnglInput" class="form-label col-sm-3">테스트 언어<i class="icon_star"></i></label>
                                <div class="col-sm-9">
                                    <div class="form-row">
                                        <select class="form-select w40" id="lnglInput">
                                            <option value="java">java</option>
                                            <option value="python">python</option>
                                            <option value="C">C</option>
                                            <option value="html">html</option>
                                        </select>
                                    </div>             
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="row">
                                <label for="infolInput" class="form-label col-sm-3">설명<i class="icon_star"></i></label>
                                <div class="col-sm-9">
                                    <div class="form-row">
                                        <textarea class="form-control" rows="5" id="infolInput"></textarea>
                                    </div>
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="row">
                                <label for="condiInput" class="form-label col-sm-3">제한조건</label>
                                <div class="col-sm-9">     
                                    <small class="note">* 입력후 엔터(Enter)나 우측 추가(+) 버튼을 눌러주세요</small>                  
                                    <div class="form-row">
                                        <input type="text" id="condiInput" class="form-control condition_input">
                                        <button type="button" class="condition_add"><i class="xi-plus"></i><span class="sr-only">해시태그 추가</span></button> 
                                    </div>                                            
                                    <div class="condition_area">
                                        <span class="sr-only">해시태그추가영역</span>
                                    </div>        
                                </div>
                            </div>
                        </li>
                    </ul>
                </div>
                <div class="txt-right mt20">
                    <button class="btn type4">AI 문제출제</button>
                    <button class="btn type2">문제 등록</button>
                </div>
            </div>
        </div>
    </div>
    
</body>
</html>