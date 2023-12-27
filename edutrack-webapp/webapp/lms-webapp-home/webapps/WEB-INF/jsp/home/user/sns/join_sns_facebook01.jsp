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
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
	document.domain = "${PRODUCT_DOMAIN}";
	window.fbAsyncInit = function() {
		FB.init({
			appId      : '${SNS_KEY}',
			cookie     : true,
			xfbml      : true,
			version    : 'v2.5'
		});
		
		FB.getLoginStatus(function(response) {
			statusChangeCallback(response);
		});
	};
	
	(function(d, s, id) {
		var js, fjs = d.getElementsByTagName(s)[0];
		if (d.getElementById(id)) return;
		js = d.createElement(s); js.id = id;
		js.src = "//connect.facebook.net/en_US/sdk.js";
		fjs.parentNode.insertBefore(js, fjs);
	}(document, 'script', 'facebook-jssdk'));
	

	//로그인 상태를 요청한다.
	function checkLoginState() {
		FB.getLoginStatus(function(response) {
			statusChangeCallback(response);
		});
	}
	
	//로그인 상태를 받는다
	function statusChangeCallback(response) {
		if (response.status === 'connected') {	//로그인 되어있는경우
			isLogin();
//	 	} else if (response.status === 'not_authorized') {	//페이스북에 로그인 되어있으나 앱 연결이 안되어있는 경우
	
		} else {	//로그인 안된경우
			//로그인 시켜줌
			FB.login(function(response){
				//콜백 함수
				isLogin();
			});
		}
	}
	
	//로그인 후 로직
	function isLogin() {
		FB.api('/me', function(response) {
			addUser(response);
		});
	}
	
	function addUser(response){
		if(response.id == null){
			self.close();
			return;
		}
		var userNm = response.name;
		var snsDiv = "FACEBOOK";
		var snsKey = response.id
		
		var snsLogin = "${param._snsLogin}";
		if(snsLogin == 'Y'){
			opener.snsLogin(snsKey, snsDiv);
			self.close();
		}else{
			$.getJSON(("<c:url value="/home/user/joinSnsStep03"/>"), 
				{	 "orgCd" : '${param._orgCd}'
					, "userNm"	: userNm
					, "snsDiv" : snsDiv
					, "snsKey" : snsKey
				}, function(result){
					alert(result.message);
					if(result.result > 0) {
						opener.snsLogin(snsKey, snsDiv);
						self.close();
					}
				});
		}
	}
</script>
<body>
</body>
</html>