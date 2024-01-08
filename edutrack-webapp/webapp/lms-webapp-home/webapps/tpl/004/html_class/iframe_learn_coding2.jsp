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
            <div class="number"><button type="button"><i class="xi-angle-left" aria-hidden="true"></i></button>1번과제<button type="button"><i class="xi-angle-right" aria-hidden="true"></i></button></div>
            <div class="total">과제<strong>5문제</strong></div>
        </div>
        <div class="panel-container">
            <div class="panel-left">
                <div class="panel-bottom">
                    <div class="inner_top">
                        <select class="form-select">
                            <option value="강의내용보기">강의내용보기</option>
                            <option value="PDF보기">PDF보기</option>
                        </select>
                    </div>
                    <div class="inner_cont">
                        <div id="lecture-viewer">강의내용보기는 이 안에 넣으시길 바랍니다.<br><br>웹 PDF 뷰어 https://pdfobject.com/<br>코드에디터 https://codemirror.net/<br>강의<br>강의<br>강의<br>강의<br>강의<br>강의<br>강의<br>강의<br>강의<br>강의<br>강의<br>강의<br>강의<br>강의<br>강의<br>강의<br>강의<br>강의<br>강의<br>강의<br>강의<br>강의<br>강의<br>강의<br>강의<br>강의<br>강의<br>강의<br>강의<br>강의<br>강의<br>강의<br>강의<br>강의<br>강의<br>강의<br>강의<br>강의<br>강의<br>강의<br>강의<br>강의<br>강의<br>강의<br>강의<br>강의<br>강의<br>강의<br>강의<br>강의<br>강의<br>강의<br>강의<br>강의<br>강의<br>강의<br>강의<br>강의<br>강의<br>강의<br>강의<br>강의<br>강의<br>강의<br>강의<br>강의<br>강의<br>강의<br>강의<br>강의<br>강의<br>강의<br>강의<br>강의<br>강의</div>
                    </div>
                </div>
            </div>
            <div class="splitter"></div>
            <div class="panel-right">
                <div class="panel-top">
                    <div class="inner_top">
                        <select class="form-select">
                            <option value="" disabled selected>java</option>
                            <option value="C">C</option>
                            <option value="C++">C++</option>
                            <option value="C#">C#</option>
                            <option value="java">java</option>
                            <option value="Python">Python</option>
                            <option value="PHP">PHP</option>
                            <option value="javascript">javascript</option>
                        </select>
                        <div class="request">코딩 요청중입니다. 잠시만 기다려 주세요.<strong><i class="xi-alarm-clock-o" aria-hidden="true"></i>00:00</strong></div>
                    </div>
                    <div class="code_box">
                        <div class="top">
                            <div class="page">
                                <button type="button" class="btn">Main.java</button>
                            </div>
                            <div class="group_btn">
                                <button type="button" class="btn type2">페어코딩요청</button>
                                <button type="button" class="btn type4">저장</button>
                            </div>
                        </div>
                        <div class="cont">
                            <textarea id="code" name="code"><foobar>
  <blah>Enter your xml here and press the button below to display
    it as highlighted by the CodeMirror XML mode</blah>
  <tag2 foo="2" bar="&amp;quot;bar&amp;quot;"/>
</foobar></textarea>
                            <script>
                                // textarea 태그의 element를 지정
                                var textarea = document.getElementById('code');
                                // 에디터 설정
                                var editor = CodeMirror.fromTextArea(textarea, {
                                    lineNumbers: true,  //왼쪽 라인넘버 표기
                                    lineWrapping: true, //줄바꿈. 음.. break-word;
                                    theme: "darcula",   //테마는 맘에드는 걸로.
                                    mode: 'text/x-sql', //모드는 sql 모드
                                    val: textarea.value
                                });
                                editor.setSize('100%', '100%');
                            </script>
                        </div>
                    </div>
                </div>
                <div class="splitter-horizontal"></div>
                <div class="panel-bottom">
                    <div class="code_box">
                        <div class="top flex_end">
                            <div class="group_btn">
                                <button type="button" class="btn type4">실행</button>
                                <button type="button" class="btn">멈춤</button>
                            </div>
                        </div>
                        <div class="cont">
                            <pre>실행결과</pre>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script>
         $(".panel-left").resizable({
             handleSelector: ".splitter",
             resizeHeight: false
         });
         $(".panel-top").resizable({
             handleSelector: ".splitter-horizontal",
             resizeWidth: false
         });
    </script>
</body>
</html>