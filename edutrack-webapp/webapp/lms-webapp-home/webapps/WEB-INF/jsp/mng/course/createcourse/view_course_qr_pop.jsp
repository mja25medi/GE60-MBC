<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
	<link rel="stylesheet" href="/tpl/${COLOR_TPL}/css/class_content.css">
    <link rel="stylesheet" href="/tpl/${COLOR_TPL}/css/hrd_common.css">
    <link rel="stylesheet" href="/tpl/${COLOR_TPL}/css/board.css">
<c:url var="img_base" value="/img/home" />
<mhtml:mng_html>
<mhtml:mng_head titleName="${MENUNAME}">
<style>
.enter_box input[type="text"] {
    width: 5rem;
    height: 5rem;
    font-size: 3rem;
    text-align: center;
    margin-top: 20px;
}

input[type="password"], input[type="text"], input[type="number"], select, textarea {
    padding: 0.6rem 1.2rem;
    min-height: 3.7rem;
    line-height: 1.4;
    color: #555;
    background-color: #fff;
    background-image: none;
    border: 1px solid #ccc;
    transition: border-color ease-in-out 0.15s, box-shadow ease-in-out 0.15s;
}
</style>

</mhtml:mng_head>
<mhtml:frm_body cssTag="background-color:#fff;">
    <div class="modal_cont">
        <div class="dummy_box">
            <img id="qrImg" src="/app/file/view/${createCourseVO.qrFileSn }" alt="이미지" aria-hidden="true" onerror="noFile()">    
        </div>
        <div class="enter_box">
            <div class="disc fcBlack">출석 확인을 위해 HRD 어플로 QR 코드를 찍어주세요</div>            
        </div>
    </div>
<script>
function noFile() {
	alert("파일이 존재하지 않습니다.");
	$("#qrImg").css('display', 'none');
}

</script>
    
</mhtml:frm_body>
</mhtml:mng_html>
