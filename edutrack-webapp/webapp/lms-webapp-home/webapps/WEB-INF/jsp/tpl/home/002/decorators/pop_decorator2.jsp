<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<%@ taglib prefix="meditag" uri="tag-utils" %>

<!DOCTYPE html>
<html lang="ko">
	<%-- 이 데코레이터는 메인페이지에서 모달팝업을 부르는 경우이며, iframe이 아니고 .load로 호출하기 때문에 css나 js를 부를 필요가 없다. 필요한 경우 해당 페이지에서 head 태그에 추가 --%>
	<sitemesh:write property="head"/>
</head>
<body class="hold-transition">
	<sitemesh:write property="body"/>
</body>
</html>