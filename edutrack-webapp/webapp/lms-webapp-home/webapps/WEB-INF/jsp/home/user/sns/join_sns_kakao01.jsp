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
<script src="http://developers.kakao.com/sdk/js/kakao.min.js"></script>

<a id="kakao-login-btn"></a>
<a href="http://alpha-developers.kakao.com/logout"></a>
<div id="console"></div>
<script type="text/javascript">
	document.domain = "${PRODUCT_DOMAIN}";
	Kakao.init('${SNS_KEY}');
	// 로그인 창을 띄움
	Kakao.Auth.login({
		//로그인 성공 시
		success: function(authObj) {
			Kakao.API.request({
				url: '/v1/user/me',
				success: function(res) {
					var userNm = res.properties.nickname;
					var snsDiv = "KAKAO"
					var snsKey = res.id;
					
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
				},
				fail: function(error) {
					alert("ERROR : " + JSON.stringify(error));
					self.close();
				}
			});
		},
		
		//로그인 실패 시
		fail: function(err) {
			alert("ERROR : " + JSON.stringify(error));
			self.close();
		}
	});
</script>
<body>
</body>
</html>