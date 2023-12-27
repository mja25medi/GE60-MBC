<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<meditag:js src="/app/js/Context.js"/>
<meditag:js src="/js/common.js"/>
<meditag:js src="/js/common_conf.js"/>
<meditag:js src="/js/common_util.js"/>
<meditag:js src="/js/common_function.js"/>

<meditag:js src="/js/jquery/jquery-1.11.1.min.js"/>
<meditag:js src="/js/jquery/jquery.form.js"/>
<meditag:js src="/js/jquery/jquery-ui-1.11.0.custom/jquery-ui.min.js"/>
<meditag:js src="/js/jquery/jquery-custom/jquery.input-1.0.js"/>
<meditag:js src="/js/jquery/jquery.ui.touch-punch.min.js"/>

<meditag:js src="/js/modaldialog.js"/>
<script>
var modalBox = null;

$(document).ready(function() {
	modalBox = new $M.ModalDialog({
		"modalid" : "modal1"
	});
});

function modalBoxClose() {
	modalBox.clear();
	modalBox.close();
}

function fn_otpAccredit() {
	var name= $("#userNm").val();//"홍길순";
	var tel = $("#userTel").val();//"01012341234";
	var otp = $("#otpNo").val();
	
	if (name == "" || tel == "" || otp == ""){
		alert("이름 / 전화번호 / OTP No는 필수 입니다.");
		return false;
	}
	
	$.ajax({
		url:"https://emonotp.hrdkorea.or.kr/api/v2/otp_accredit",
		type:"POST", 
		timeout:10000,    		
		contentType:"application/x-www-form-urlencoded",
		data:{
			USER_NM    : name,
			USER_TEL   : tel,
			OTPNO      : otp,
			AGTID      : "edulife",
			USRID      : "user001",
			SESSIONID  : "",
			EXIP       : "127.0.0.1",
			COURSE_AGENT_PK  : "Y",
			CLASS_AGENT_PK  : "Y",
			EVAL_CD    : "99",
			EVAL_TYPE  : "기타",
			CLASS_TME  : "999",
			USRDT      : "2022-07-18 15:34:00"
		},success:function(data){
			console.log("data==="+JSON.stringify(data));
			$("#display").prepend(JSON.stringify(data)+"</br>");
			if(data.code == 200) {
				console.log(data.status);
			} else {
				console.log(data.status)
			}
		},error:function(e){
			$("#display").prepend("ERROR==="+JSON.stringify(e));
		}
		
	});
	
}

function fn_userReset() {
	var name= $("#userNm").val();//"홍길순";
	var tel = $("#userTel").val();//"01012341234";
	
	if (name == "" || tel == ""){
		alert("이름 / 전화번호는 필수 입니다.");
		return false;
	}

	
	$.ajax({
		url:"https://emonotp.hrdkorea.or.kr/api/v2/user_reset",
		type:"POST", 
		contentType:"application/x-www-form-urlencoded",
		timeout:10000,
		data:{
			USER_NM   : name,
			USER_TEL  : tel,
			AGTID     : "C123",
			USRID     : "cocojia",
			M_RET     : "T",
			M_RETCD   : "asdasdasd",
			M_TRNID   : "aaaY",
			M_TRNDT   : "2021-12-07 12:11:33"
		},
		success:function(data){
			$("#display").prepend(JSON.stringify(data)+"</br>");
			if(data.code == 200) {
				console.log(data.status);
			} else {
				console.log(data.status)
			}
		},error:function(e){
			$("#display").prepend("ERROR==="+JSON.stringify(e));
		}
		
	});
	
}

function fn_Api() {
	$.ajax({
		url:"/mng/etc/HrdApi/callRestTemplateHrdApi",
		type:"POST", 
		dataType: "json",
		data:{
			"seq" : "1"
		},
		success:function(data){
			if(data.result == 1) {
				console.log(1);
			} else {
				console.log(-1);
			}
		},error:function(e){
				console.log(-1);
		}
		
	});
	
}

function pop_motp(){
	/* var url = "/mng/etc/HrdApi/viewMotpPop";
	//var url = generateUrl("/mng/etc/HrdApi/viewMotpPop",{ "orgCd":orgCd});
	var addContent  = "<iframe id='motpFrame' name='motpFrame' width='100%' height='100%' "
		+ "frameborder='0' scrolling='auto' src='"+url+"'/>";
	modalBox.clear();
	modalBox.addContents(addContent);
	modalBox.resize(800, 620);
	modalBox.setTitle("MOTP");
	modalBox.show(); */
	/* 
	var url = cUrl("/mng/etc/HrdApi/viewMotpPop");
	var winOption = "left=0,top=0,toolbar=0,location=0,directories=0,status=0,menubar=0,scrollbars=auto,resizable=yes,width=620,height=800";
	var contentsWin = window.open(url, "contentsWin", winOption);
	contentsWin.focus(); 
	*/
	
	$("#idSearchArea")
	.load(
		cUrl("/mng/etc/HrdApi/viewMotpPop"),
		{
		}
	)

}
</script>
</head>
<body>
<h2>MOTP 앱을 실행시켜 OTP 번호를 입력해주세요.</h2>
<img src="/img/motp_sample_img.png" alt="sample_motp_img"/>
<hr/>
<div class="input-div">
	<h3>OTP 인증 부분</h3>
    <input type="text" id="userNm" placeholder="회원 이름"/><br/>
    <input type="text" id="userTel" placeholder="회원 전화번호"/><br/>
    <a>회원 이름과 회원 전화번호는 로그인된 사용자의 이름과 전화번호를 넣어주세요</a><br/>
	<input type="text" id="otpNo" placeholder="otp 번호"/>
	<button type="button" onclick="fn_otpAccredit();">인증</button>
	<button type="button" onclick="fn_Api();">api인증</button>
	<button type="button" onclick="pop_motp();">otp인증</button>
    <hr/>
</div>
<div>
	<h3>OTP 제한 해제 부분</h3>
	<button type="button" onclick="fn_userReset();">제한해제</button>
	<hr/>
</div>
<!-- 테스트용 공간 시작 -->
<div>
	<h3>테스트 결과 표출 화면</h3>
	<textarea id="display" style="width: 350px; height: 200px;"></textarea>
</div>
<!-- /테스트용 공간 끝 -->
<div id="idSearchArea"></div>
</body>
</html>