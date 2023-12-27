<%@ page language="java" pageEncoding="utf-8" %>
<%@	page import="egovframework.edutrack.Constants"%>
<% 
	request.setAttribute("PRODUCT_DOMAIN",Constants.PRODUCT_DOMAIN);
	request.setAttribute("MANAGE_DOMAIN",Constants.MGR_SITE_DOMAIN);  
%>
<!DOCTYPE html>
<html>
<head>
</head>
<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.2.js" charset="utf-8"></script>
<body>
<div id="naver_id_login" style="display: none;"></div>

<script type="text/javascript">

	var naver_id_login = new naver_id_login("${SNS_KEY}", "http://${MANAGE_DOMAIN}/home/user/joinSnsStep02?snsDiv=NAVER&");
	var state = naver_id_login.getUniqState();
	naver_id_login.init_naver_id_login();
	
	location.href=$("#naver_id_login").find("a").attr("href");
</script>

</body>
</html>

