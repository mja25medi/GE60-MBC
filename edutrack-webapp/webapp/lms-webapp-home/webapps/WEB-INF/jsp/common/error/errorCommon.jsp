<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<%
// String errorCode = exception.getMessage();
// try {
// 	errorCode = exception.getMessage().split(":")[1].replaceAll(" ", "");
// } catch (Exception e) {}
// request.setAttribute("errorCode", errorCode);
%>
<!DOCTYPE html>
<html lang="ko">
	<jsp:include page="common.jsp" />
<body>
    
    <section class="extras errorBasic">
        <div class="flex-container">
            <div class="cont-none">
                <img src="/tpl/003/img/error_img.png" alt="알림" aria-hidden="true"/>
                <div class="tit-header"><strong>이용에 불편을 드려 죄송합니다.</strong></div>
                <div class="text">
                    <p class="fcBlue">정상적인 홈페이지 접속이 아니거나 세션이 종료 되었습니다.</p>
                    <p>확인버튼을 클릭하여 다시 접속해 주세요.</p>
                </div>
                <div class="btns center mt30">
                    <a href="/" class="btn">홈으로</a>
                    <a href="javascript:history.back(-1);" class="btn gray2" >이전화면 바로가기</a>
                </div>
            </div>
        </div>
    </section>
    
</body>

</html>