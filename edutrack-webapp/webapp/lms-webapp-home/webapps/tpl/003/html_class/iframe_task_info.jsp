<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">
<%@ include file="../inc/class_common.jsp" %>
<script src="../js/iframeResizer.contentWindow.min.js"></script>
<body class="scroll_custom">
    <div class="modal_cont">
        <div class="table_list">
            <ul class="list">
                <li class="head"><label>과제유형</label></li>
                <li><label class="btn3 sm solid fcGreen">온라인</label><label class="btn3 sm solid fcViolet">오프라인</label><label class="btn3 sm solid fcRed">혼합</label></li>
                <li class="head"><label>과제출제유형</label></li>
                <li>랜덤출제</li>
            </ul>
            <ul class="list">
                <li class="head"><label>과제제목</label></li>
                <li>1주차 네트워크구성도 만들어 보기</li>
            </ul>
            <ul class="list">
                <li class="head"><label>과제기간</label></li>
                <li>2023.10.12 00:00~2023.10.30 23:59</li>
            </ul>
            <ul class="list">
                <li class="head"><label>제출연장일</label></li>
                <li>2023.10.31 00:00</li>
            </ul>
            <ul class="list">
                <li class="head"><label>제출횟수</label></li>
                <li>10회</li>
                <li class="head"><label>제출가능진도율</label></li>
                <li>1%</li>
            </ul>
            <ul class="list">
                <li class="head"><label>첨부파일</label></li>
                <li>
                    <ul class="add_file">
                        <li>                    
                            <a href="#" class="file_down">
                                <img src="../../_img/board/file_doc.png" alt="">
                                <span class="text">첨부파일명마우스오버 시.doc</span>
                                <span class="fileSize">(6KB)</span>
                            </a>                    
                            <span class="link">
                                <a class="btn-line btn-down" href="#" title="154873973477000.png 다운로드">다운로드<i></i></a>
                            </span>
                        </li>
                        <li>
                            <a href="#" class="file_down">
                                <img src="../../_img/board/file_img.png" alt="">
                                <span class="text">154873973477000.jpg</span>
                                <span class="fileSize">(6KB)</span>
                            </a>
                            <span class="link">
                                <a class="btn-line btn-down" href="#" title="154873973477000.png 다운로드">다운로드<i></i></a>
                            </span>
                        </li>
                    </ul>
                </li>
            </ul>
            <ul class="list">
                <li class="head"><label>내용</label></li>
                <li>온라인 과제2</li>
            </ul>
            <ul class="list">
                <li class="head"><label>공개여부</label></li>
                <li>등록</li>
            </ul>
        </div>
    </div>
    <div class="modal_btns">
        <button type="button" class="btn">닫기</button>
    </div>
</body>
</html>