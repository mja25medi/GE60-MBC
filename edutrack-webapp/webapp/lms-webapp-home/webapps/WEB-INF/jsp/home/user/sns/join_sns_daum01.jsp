<%@ page language="java" pageEncoding="utf-8" %>
<%@	page import="egovframework.edutrack.Constants"%>
<% 
	request.setAttribute("PRODUCT_DOMAIN",Constants.PRODUCT_DOMAIN);
	request.setAttribute("MANAGE_DOMAIN",Constants.MGR_SITE_DOMAIN); 
%>
<!doctype html>
<html lang="ko">
<head>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
</head>
<body>
<script type="text/javascript">
	$(function(){
		location.href="https://apis.daum.net/oauth2/authorize?client_id=${SNS_KEY}&redirect_uri=http://${MANAGE_DOMAIN}/home/user/joinSnsStep02?response_type=token";
	})
</script>
</body>
</html>