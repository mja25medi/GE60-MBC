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
	document.domain = "${PRODUCT_DOMAIN}";
	var err = "${param.error}";
	var naver_id_login;
	if(err != ""){
		alert(err);
		self.close();
	}else{
		naver_id_login = new naver_id_login("${SNS_KEY}", "http://${MANAGE_DOMAIN}/home/user/joinSnsStep02?snsDiv=NAVER&orgCd=${param.orgCd}&");
		naver_id_login.get_naver_userprofile("getUser()");	//프로필을 가져오는 function을 호출함
	}
	
	function getUser(){
		/******************************************************************************
		*	http://developer.naver.com/wiki/pages/NaverLogin_spec 참고
		*
		*	result/resultcode		호출 결과 코드			
		*	result/message			호출 결과 메시지	
		*	response/enc_id			사용자 확인 값	
		*	response/nickname		사용자 닉네임	
		*	response/email			이메일(ID@naver.com)	
		*	response/gender			성별					F:여성/M:남성/U:확인불가
		*	response/age			연령대	
		*	response/birthday		생일					MM-DD 형식으로 반환
		*	response/profile_image	프로필 사진				프로필 사진 URL 정보
		*
		********************************************************************************/
		
		var snsKey = naver_id_login.getProfileData('enc_id');	//통신 후 받은 ID
		var userNm = naver_id_login.getProfileData('nickname');	//통신 후 받은 Name

		callBack(snsKey, userNm);
	}
	
	function callBack(snsKey, userNm){
		
		var snsLogin = "${_snsLogin}";
		if(snsLogin == 'Y'){
			opener.snsLogin(snsKey, "NAVER");
			self.close();
		}else{
			$.getJSON(("<c:url value="/home/user/joinSnsStep03"/>"), 
					{	 "orgCd" : '${_orgCd}'
						, "userNm"	: userNm
						, "snsDiv" : "NAVER"
						, "snsKey" : snsKey
					}, function(result){
						alert(result.message);
						if(result.result > 0) {
							opener.snsLogin(snsKey, "NAVER");
							close();
						}
					});
		}
	}
</script>

</body>
</html>

