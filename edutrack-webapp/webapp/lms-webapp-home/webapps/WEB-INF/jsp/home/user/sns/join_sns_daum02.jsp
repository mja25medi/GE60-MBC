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
	document.domain = "${PRODUCT_DOMAIN}";
	var urlArr = location.href.split("#");
	if(urlArr.length == 2){
		//urlArr[1] 중 access_token만 추출
		var indexOfNextParam = urlArr[1].indexOf("&", 0);
		var param = urlArr[1].substring(0, indexOfNextParam);
	
		var indexOfParamValue = param.indexOf("=", 0) + 1;
		var accesstToken = param.substring(indexOfParamValue, param.length);
	
		$.ajax({
			url : "https://apis.daum.net/user/v1/show.json?",
			data : {"access_token" : accesstToken},
			jsonp : "callback",
			dataType : "jsonp",
			success:function(data){
				//성공 시
				if(data.message == "OK"){
					var snsKey = data.result.id;
					var userNm = data.result.nickname;
					
					callBack(snsKey, userNm);
				}
			}
		})
	}
	
	function callBack(snsKey, userNm){
		
		var snsLogin = "${_snsLogin}";
		if(snsLogin == 'Y'){
			opener.snsLogin(snsKey, "DAUM");
			self.close();
		}else{
			$.getJSON(("<c:url value="/home/user/joinSnsStep03"/>"), 
					{
						 "orgCd" : '${_orgCd}'
						, "userNm"	: userNm
						, "snsDiv" : "DAUM"
						, "snsKey" : snsKey
					}, function(result){
						alert(result.message);
						if(result.result > 0) {
							opener.snsLogin(snsKey, "DAUM");
							close();
						}
					});
		}
	}
</script>
</body>
</html>