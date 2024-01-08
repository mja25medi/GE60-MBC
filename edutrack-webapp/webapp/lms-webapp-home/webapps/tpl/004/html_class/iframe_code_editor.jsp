<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">
<%@ include file="../inc/class_common.jsp" %>
<link rel="stylesheet" href="../js/codemirror/lib/codemirror.css">
<link rel="stylesheet" href="../js/codemirror/theme/darcula.css">
<script src="../js/iframeResizer.contentWindow.min.js"></script>
<script src="../js/codemirror/lib/codemirror.js"></script>
<script src="../js/codemirror/mode/sql/sql.js"></script>
<body style="height: 100%;">
    <div class="code_box">
        <div class="top">
            <div class="page">
                <button type="button" class="btn">Main.java</button>
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
</body>
</html>