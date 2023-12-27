<%@ page language="java" pageEncoding="utf-8" %>
<%@	page import="egovframework.edutrack.Constants"%>
<% 
	request.setAttribute("PRODUCT_DOMAIN",Constants.PRODUCT_DOMAIN);
	request.setAttribute("MANAGE_DOMAIN",Constants.MGR_SITE_DOMAIN); 
%>
<html>
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js" ></script>
<meta name="google-signin-client_id" content="${SNS_KEY}"></meta>
<script type="text/javascript">
	document.domain = "${PRODUCT_DOMAIN}";
	var auth2 = {};
	var helper = (function() {
		return {
			//인증 후 콜백 func
			onSignInCallback: function(authResult) {
				//로그인 되어 있으면
				if (authResult.isSignedIn.get()) {
					helper.profile();
				} else {
					authResult.signIn();
				}
			},
			disconnect: function() {
				auth2.disconnect();
			},
			people: function() {
				gapi.client.plus.people.list({
					'userId': 'me',
					'collection': 'visible'
				}).then(function(res) {
					var people = res.result;
					console.log("people", people);
				});
			},
			profile: function(){
				gapi.client.plus.people.get({
					'userId': 'me'
				}).then(function(res) {
					var profile = res.result;
					addUser(profile);
				});
			}
		}
	})();
	
	$(document).ready(function(){
		
	});
	
	var updateSignIn = function(){
	    helper.onSignInCallback(gapi.auth2.getAuthInstance());
	}
	function startApp() {
		gapi.load('auth2', function() {
			gapi.client.load('plus','v1').then(function() {
				gapi.auth2.init({fetch_basic_profile: false
					, scope:'https://www.googleapis.com/auth/plus.login'}).then(
					function (){
						auth2 = gapi.auth2.getAuthInstance();
						auth2.isSignedIn.listen(updateSignIn);
						auth2.then(updateSignIn);
					});
			});
		});
	}
	
	function addUser(response){
		var userNm = response.displayName;
		var snsDiv = "GOOGLE";
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
<script src="https://apis.google.com/js/client:platform.js?onload=startApp"></script>
</html>