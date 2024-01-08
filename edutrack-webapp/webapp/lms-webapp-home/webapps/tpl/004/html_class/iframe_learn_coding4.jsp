<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">
<%@ include file="../inc/class_common.jsp" %>
<link rel="stylesheet" href="../js/codemirror/lib/codemirror.css">
<link rel="stylesheet" href="../js/codemirror/theme/darcula.css">
<script src="../js/codemirror/lib/codemirror.js"></script>
<script src="../js/codemirror/mode/sql/sql.js"></script>
<script src="../js/jquery-resizable.js"></script>
<script src="../js/pdfobject.js"></script>
<body>
    <div class="coding_wrap">
        <div class="coding_header">
            <h1>java 기초 프로그래밍 강좌 1강</h1>
            <div class="right_util">
                <div class="request pair_ing"><i class="xi-catched" style="font-size: 2.2rem;"></i>홍길동 학생과 코딩 학습 공유중 </div>
                <button type="button" class="btn">종료</button>
            </div>
        </div>
        <div class="panel-container coding-test prof_mo">
            <div class="panel-left">
                <div class="test_field">
                    <button type="button" class="indent_btn prof_c"></button>
                    <ul class="left_number">
                       <li></li>
                    </ul>
                    <div class="right_content">
                        <div class="board_top">
                            <h4>1번 문제</h4>
                        </div>
                        <div class="cont">
                            <label class="h_title">문제 설명</label>
                            <div class="mb20">주어진 정수 n이 홀수인지 짝수인지 확인하는 프로그램을 작성하시오</div>
                            <label class="h_title">제한사항</label>
                            <div class="mb20">테스트케이스1. 변수n에 13이라는 정수값을 대입하기<br />테이스케이스2. if~else 문을 사용하여 코드를 작성하기<br />테스트케이스3. 조건문으로 n을 2로 나눈 나머지가 0인지 아닌지로 판단하기
                            </div>
                        </div>
                        
                    </div>
                </div>
            </div>
            <div class="splitter"></div>
            <div class="panel-right2">
                <div class="panel-top">
                    <iframe src="iframe_code_editor.jsp" style="height: 100%;"></iframe>
                </div>
                
            </div>
        </div>
    </div>
    <script>
         $(".panel-left").resizable({
             handleSelector: ".splitter",
             resizeHeight: false
         });
         
    </script>

    <!-- modal popup 보여주기 버튼(개발시 팝업id 삭제) -->
    <button class="modal__btn" id="popup1" style="width: 100%;">채점결과 팝업</button>
    <script type="text/javascript">
        $("#popup1").click(function() {
            $('body').css("overflow", "hidden");
            $("#coding_test_result").css('display','flex').hide().fadeIn();
            iFrameResize({ scrolling: true }, '#iframe1')
        })
    </script>
    <!-- modal popup -->
    <div id="coding_test_result" class="modal_popup_wrap">                             
        <div class="modal_popup modal_medium">
            <h4 class="modal_title">채점결과</h4>
            <iframe id="iframe1" src="iframe_coding_test_result.jsp"></iframe>
            <button type="button" class="modal_close"><i class="xi-close-thin"></i><span class="sr-only">팝업닫기</span></button>
        </div>
    </div>
</body>
</html>