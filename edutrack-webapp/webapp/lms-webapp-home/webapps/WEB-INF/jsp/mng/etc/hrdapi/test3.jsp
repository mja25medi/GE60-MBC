<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<script type="text/javascript">
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
				USRID      : "ty1329",
				SESSIONID  : "",
				EXIP       : "210.101.173.142",
				COURSE_AGENT_PK  : "52",
				CLASS_AGENT_PK  : "947",
				EVAL_CD    : "04",
				EVAL_TYPE  : "진행평가",
				CLASS_TME  : "01",
				USRDT      : getTimeStamp2()
			},success:function(data){
				//console.log("data==="+JSON.stringify(data));
				if(data.code == 200) {
					//console.log(data.status);
					if ("${evalCd}" == "00")
					{
						alert("mOTP 인증을 통하여 본인인증에 성공하였습니다.");
						$.ajax({
							url : "/mypage/ajax_auth_ok.asp",
							data : {"log_idx":"", "cls_idx":"947", "lec_idx":"52", "eval_cd":"04"},
							dataType : "json",
							beforeSend : function(){
								
							},
							success : function(retVal){
								opener.location.replace("/mypage/mypage.asp");
								self.close()
							},
							error : function(info, xhr){
								if(info.readyState == '4'){
									//alert("서버와의 통신에 문제가 발생되었습니다.\n상태는 "+info.status+"\n"+info.responseText)
									//return;
								}
								else{
									//alert('문제가 발생했습니다.\n잠시후 다시 시도해 주세요.');
								}
								
							}
						});
					}
					else if ("${evalCd}" == "02")
					{
						alert("OTP 인증에 성공하였습니다.");
						$.ajax({
							url : "/mypage/ajax_auth_ok.asp",
							data : {"log_idx":"", "cls_idx":"947", "lec_idx":"52", "auth_type":"2", "eval_cd":"04"},
							dataType : "json",
							beforeSend : function(){
								
							},
							success : function(retVal){
								alert("시험창으로 이동합니다.")	
								location.replace("/mypage/pop_exam.asp?log_idx=&lec_idx=52&cls_idx=947");
							},
							error : function(info, xhr){
								if(info.readyState == '4'){
									//alert("서버와의 통신에 문제가 발생되었습니다.\n상태는 "+info.status+"\n"+info.responseText)
									//return;
								}
								else{
									//alert('문제가 발생했습니다.\n잠시후 다시 시도해 주세요.');
								}
								
							}
						});
					}
					else
					{
						alert("OTP 인증에 성공하였습니다.\n다시한번 시청 버튼을 클릭해 주시기 바랍니다.");
						opener.ItemDTO.motpPass 	= 'S';
						self.close()
					}
				} 
				else if (data.code == "AP001") {
					//OTP 번호 오류
					alert("otp번호가 일치하지 않습니다.확인 후 다시 입력해 주세요.")
					$("#otpNo").val('');
					//console.log(data.status)
				}
				else if (data.code == "AP009") {
					//OTP 번호 오류 5회 이상으로 계정 잠금 상태
					alert("otp번호 인증이 5회 실패하였습니다.\n본인인증을 통해 인증제한을 해제해 주시기 바랍니다.")
					$("#otpNo").val('');
					$(".content_03").show();
				}
				else {
					//시스템 장애 또는 파라미터 오류로 인한 장애
					alert("장애가 발생하였습니다.(코드:"+data.code+")\n잠시 후에 이용해 주시기 바랍니다.")
					//opener.pop_otp('', '947', '52', '04', '01');
					self.close()
				}
			},error:function(e){
				alert("ERROR==="+JSON.stringify(e));
				//$("#display").prepend("ERROR==="+JSON.stringify(e));
				//alert("장애가 발생하였습니다.(코드:"+data.code+")\n잠시 후에 이용해 주시기 바랍니다.")
				//opener.pop_otp('', '947', '52', '04', '01');
				//self.close()
			}
			
		});
		
	}
	
	function fn_userReset(m_retCD, m_trnID) {
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
				AGTID     : "edulife",
				USRID     : "ty1329",
				M_RET     : "T",
				M_RETCD   : m_retCD,
				M_TRNID   : m_trnID,
				M_TRNDT   : getTimeStamp2()
			},
			success:function(data){
				console.log("data==="+JSON.stringify(data));
				if(data.code == 200) {
					alert("OTP 초기화가 성공적으로 이루어졌습니다.");
					$(".content_03").hide();
					$("#otpNo").val('');
					
				} else {
					//console.log(data.status)
					alert("OTP 제한 해제에 실패하였습니다.\n다시 한번 본인인증을 통해 인증제한을 해제해 주시기 바랍니다.");
					$("#otpNo").val('');
				}
			},error:function(e){
				alert("ERROR==="+JSON.stringify(e));
				//alert("장애가 발생하였습니다.(코드:"+data.code+")\n잠시 후에 이용해 주시기 바랍니다.")
				//opener.pop_otp('', '947', '52', '04', '01');
				self.close()
			}
			
		});
		
	}
	
	function emon_auth_motp(m_retCD, m_trnID) {
		
		var AGTID = "edulife";
		var USRID = "ty1329";
		var COURSE_AGENT_PK = "52";
		var CLASS_AGENT_PK = "947";
		var m_trnDT = getTimeStamp2();
	
		$.ajax({
			type : "POST",
			url  : "https://emon.hrdkorea.or.kr/EAIServer/SOURCE/ExConn/LMS/pSubOtpLog.jsp",
			crossDomain: true,
			data : {
				AGTID : AGTID,
				USRID : USRID,
				COURSE_AGENT_PK : COURSE_AGENT_PK,
				CLASS_AGENT_PK : CLASS_AGENT_PK,
				EVAL_CD    : "04",
				EVAL_TYPE  : "진행평가",
				CLASS_TME  : "01",
				m_Ret : "T",
				m_retCD : m_retCD,
				m_trnID : m_trnID,
				m_trnDT : m_trnDT
			},
			dataType : "xml",
			success : function(xml) {					
				if($(xml).find("RetVal").text() == "101") {
					alert("OTP 대체인증으로 휴대폰 인증에 성공하였습니다.\n다시한번 시청 버튼을 클릭해 주시기 바랍니다.");
					opener.auth_ins();
					self.close()
				} else {
					alert("본인인증 전송이 실패하였습니다.\r\n" + $(xml).find("RetMsg").text() + "\r\n" + $(xml).find("Remark").text());
					return;
				}					
			},
			error : function(xhr, textStatus, error) {
				alert("오류발생 : " + error);
				return;
			}
		});
		
		
	}
	
	
	function pop_otp(){
		var retVal = confirm("정말로 WEBOTP 창으로 이동하시겠습니까?");	
		if (retVal) {
			opener.pop_otp('','947','52','04','1');
			self.close();
		} 
		else 
		{
			return;
		}
		
	}
	
	function sms_auth(){
		window.open("", "auth_popup", "width=430,height=590,scrollbar=yes");
			
		var f = document.f_auth;
		f.auth_type.value = 'reset';
		f.target = "auth_popup";
		f.submit();
	}
	
	function sms_auth_motp(){
		window.open("", "auth_popup_motp", "width=430,height=590,scrollbar=yes");
			
		var f = document.f_auth;
		f.auth_type.value = 'motp';
		f.target = "auth_popup_motp";
		f.submit();
	}
	
	function getTimeStamp() {
		var d = new Date();
		var s =
			leadingZeros(d.getFullYear(), 4) + '-' +
			leadingZeros(d.getMonth() + 1, 2) + '-' +
			leadingZeros(d.getDate(), 2);
	
		return s;
	}
	
	function getTimeStamp2() {
		var d = new Date();
		var s =
			leadingZeros(d.getFullYear(), 4) + '-' +
			leadingZeros(d.getMonth() + 1, 2) + '-' +
			leadingZeros(d.getDate(), 2) + ' ' +
	
			leadingZeros(d.getHours(), 2) + ':' +
			leadingZeros(d.getMinutes(), 2) + ':' +
			leadingZeros(d.getSeconds(), 2);
	
		return s;
	}
	
	function leadingZeros(n, digits) {
		var zero = '';
		n = n.toString();
	
		if (n.length < digits) {
			for (i = 0; i < digits - n.length; i++)
				zero += '0';
			}
		return zero + n;
	}
</script>
	<!-- 휴대폰 본인확인 팝업 처리결과 정보 = phone_popup3 에서 값 입력 -->
	<form id="kcbResultForm" name="kcbResultForm" method="post">
		<input type="hidden" name="m_menu" value="">
		<input type="hidden" name="CP_CD">
		<input type="hidden" name="TX_SEQ_NO">
		<input type="hidden" name="RSLT_CD">
		<input type="hidden" name="RSLT_MSG">
		
		<input type="hidden" id="RSLT_NAME" name="RSLT_NAME">
		<input type="hidden" id="RSLT_BIRTHDAY" name="RSLT_BIRTHDAY">
		<input type="hidden" name="RSLT_SEX_CD">
		<input type="hidden" name="RSLT_NTV_FRNR_CD">
		
		<input type="hidden" name="DI">
		<input type="hidden" name="CI">
		<input type="hidden" name="CI_UPDATE">
		<input type="hidden" name="TEL_COM_CD">
		<input type="hidden" id="TEL_NO" name="TEL_NO">
		
		<input type="hidden" name="RETURN_MSG">
	</form>
	<div class="tabbox" style="width:100%;float:left;position: fixed; background-color:#FFFFFF; ">
		<ul class="nav nav-tabs">
  			<li class="active"><a href="#">MOTP</a></li>
		</ul>
	</div>
	<div id="infoArea" style="float:left;width:100%; margin-top:50px;">
		<div class="content_01">
			<img src="/img/img0.png" alt="sample_motp_img" style="margin-bottom:50px;">
			<a href="/board/board_view" target="_blank"><img src="/img/img1-1.png" alt=""></a>
		</div>
		<div class="input-div content_02">
			<input class="input01" type="text" id="userNm" value="이재연" placeholder="회원 이름"><br>
			<input class="input01" type="text" id="userTel" value="01025786033" placeholder="회원 전화번호"><br>
			<div class="inputBox">
				<input type="text" id="otpNo" placeholder="otp 번호">
				<button type="button" onclick="fn_otpAccredit();">인증</button>
			</div>
		</div>
		<div class="content_03" style="display:none">
			<h3><span>OTP 제한 해제</span> </h3>
			<button type="button" onclick="sms_auth();">휴대폰 본인인증</button>
		</div>
		
		<div class="content_04">
			<div class="btn_wrap">
			
				<button class="btn_01" style="display:none"><a href="#">
					<p class="img"><img src="/img/ipin_3.png" alt="" style="width:100px;"></p>
					<p class="txt">아이핀 인증</p>
				</a></button>
			
				<button class="btn_03"><a href="javascript:pop_otp();">
					<p class="img"><img src="/img/webotp.png" alt="" style="width:68px;"></p>
					<p class="txt">WebOtp 인증</p>
				</a></button>
			
				<button class="btn_02"><a href="javascript:sms_auth_motp();">
					<p class="img"><img src="/img/phone.png" alt="" style="width:40px;"></p>
					<p class="txt">휴대폰 인증</p>
				</a></button>
			</div>
		</div>
	</div>
