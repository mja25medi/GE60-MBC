<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
	<link rel="stylesheet" href="/tpl/003/css/class_content.css">
    <link rel="stylesheet" href="/tpl/003/css/hrd_common.css">
    <link rel="stylesheet" href="/tpl/003/css/board.css">
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
	 	<div class="course_img" style="text-align: center;">
            <img src="/app/file/view/${createCourseVO.qrFileSn }" alt="이미지" aria-hidden="true" onerror="this.style.display='none'">     
        </div>
       
        <div style="text-align: center; margin-top: 50px;">
			<a href="#none" onclick="parent.modalBoxClose();" class="btn btn-sm btn-default"><spring:message code="button.close"/></a>
		</div>
    </div>
    
</mhtml:frm_body>
</mhtml:mng_html>
