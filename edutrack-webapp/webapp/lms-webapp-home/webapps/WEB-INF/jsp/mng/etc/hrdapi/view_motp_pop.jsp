<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>

	<%-- 강의실 motp는 iframe이어서 css와 js 선언이 필요하고 그 외 motp 인증 방식은 .load 방식이라 parent페이지의 css와 js를 사용하기 때문에 필요없다. --%>
	
    <!-- <link rel="stylesheet" href="/tpl/002/css/common.css"> -->
    <link rel="stylesheet" href="/tpl/002/css/hrd_common.css">
    <link rel="stylesheet" href="/tpl/002/css/webfonts.css">
    <link rel="stylesheet" href="/tpl/002/css/layout.css">
    <link rel="stylesheet" href="/tpl/002/css/sub_layout.css">
    <link rel="stylesheet" href="/tpl/002/css/sub.css">
    <link rel="stylesheet" href="/tpl/002/css/contents.css">
    
    <meditag:js src="/app/js/Context.js"/>
	<meditag:js src="/js/common_conf.js"/>
    <script src="/tpl/002/js/jquery/jquery-3.2.1.min.js"></script>
	<script src="/tpl/002/js/jquery/jquery.min.js"></script>
    <!-- <script async src="/tpl/002/js/jquery/slick.min.js"></script> -->
    <script defer src="/tpl/002/js/func.min.js"></script>
    <script defer src="/tpl/002/js/common.js"></script>
    <script defer src="/js/common.js"></script>
    <script defer src="/tpl/002/js/common_function.js"></script>
    <script defer src="/tpl/002/js/common_util.js"></script>
    <script src="/tpl/002/js/common_conf.js"></script>
    <script src="/tpl/002/js/jquery/jquery.form.js"></script>
    <!-- <script src="/js/common_paging.js"></script> -->
    <script defer src="/tpl/002/js/sub.js"></script>


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
	<%-- 강의실에서는 motp 클래스 추간 --%>
	<div class="motp">
	<!-- <div> -->
		<!-- <form id="agreementForm" name="agreementForm" method="post" onsubmit="return false;" action="" > -->
			<div class="join_cont" id="agreementForm">
	            <div class="join_area cert_div" style="width:100%;">
	                <div class="item otp_cert">
	                    <div class="vert_wrap">
	                        <h5 class="title">mOTP 본인인증</h5>
	                        <i class="icon" aria-hidden="true"></i>
	                    </div>
	                    <button type="button" name="cert_button" onclick="selectAgreement()">인증하기</button>
	                </div>  
	                
	                <div class="item phone_cert">                            
	                    <div class="vert_wrap">
	                        <h5 class="title">휴대폰 본인인증</h5>
	                        <i class="icon" aria-hidden="true"></i>
	                        
	                    </div>
	                    <button type="button" name="cert_button" onclick="smsAuth();">인증하기</button>
	                    <!-- 인증완료 시 버튼 클래스 적용<button type="button" class="disabled"><i class="xi-check" aria-hidden="true"></i>인증완료</button> -->
	                </div>
	            </div>
	            <div class="join_center">                           
	                <i class="xi-error mr5" aria-hidden="true"></i>본인인증을 위해 입력하신 개인정보는 본인인증기관에서 수집하는 정보이며, 수집된 정보는 본인인증 외 어떠한 용도로 이용되거나 별도 저장되지 않습니다.
	            </div>
	        </div>
		<!-- </form> -->
		<div id="motpArea" style="display: none;">
			<div class="table txt_small">
				<div class="content_01">
					<!-- <img src="/img/img0.png" alt="sample_motp_img" style="margin-bottom:50px;"> -->
					<a href="#" ><img src="/img/img1-1.png" alt=""></a>
				</div>
				<table>
					<caption>motp인증에 관한 폼입니다.</caption>
					<colgroup>
						<col class="w25">                                       
						<col>                                        
					</colgroup>                                                                
					<tbody>
					<tr>
						<th>이름</th>
						<td class="txt_left">
							<input class="form-control" type="text"  name="userNm" id="userNm" value="${vo.userNm }" placeholder="회원 이름" <c:if test="${vo.userNm ne ''}"> readonly</c:if>>   
							<small class="note mt5">이름을 입력하세요.</small>
						</td>                                        
					</tr>
					<tr>
						<th>전화번호</th>
						<td class="txt_left"><input class="form-control w100" type="text" name="userTel" id="userTel" value="${vo.userTel }" placeholder="회원 전화번호" <c:if test="${vo.userTel ne ''}"> readonly</c:if>>
							<small class="note mt5">전화번호를 입력하세요.</small>
						</td>                              
					</tr>
					<tr>
						<th>otp번호</th>
						<td class="txt_left"><input class="form-control w100" type="text" name="otpNo" id="otpNo" value="" placeholder="otp 번호">
							<small class="note mt5">otp번호를 입력하세요.</small>
						</td>                              
					</tr>
					</tbody>
				</table>
			</div>
			<div class="modal_btns" style="text-align: center;">
				<div class="inputBox">
					<button type="button" class="btn type2" onclick="otpAccredit();">인증</button>
					<button type="button" class="btn type2" onclick="testEdit();" style="display: none;">테스트용 임시 이름번호수정</button>
					<button type="button" class="btn content_03" onclick="smsAuth();" style="display:none">OTP 제한 해제</button>
				</div>
				<div class="btn_wrap" style="display:none">
					<button class="btn_02"><a href="javascript:smsAuth();">
						<p class="img"><img src="/img/phone.png" alt="" style="width:40px;"></p>
						<p class="txt">휴대폰 인증</p>
					</a></button>
				</div>
			</div>
			<!-- <button type="button" class="modal_close"  onclick="$('.modal_popup_wrap').fadeOut();"><i class="xi-close-thin"></i><span class="sr-only">팝업닫기</span></button> -->
		</div>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		// block tag a, input event
		$("#motpArea").hide();
		$("#agreementForm").show();
		$('#otpNo').bind('keydown', function(event) {
			if ((event.type == 'keydown' && event.keyCode == 13)) {
				otpAccredit();
			}
		});
	});
	
	function testEdit(){
		$("#userNm").prop("readonly",false);
		$("#userTel").prop("readonly",false);
	}
	
	function selectAgreement(){
		$("#motpArea").show();
		$("#agreementForm").hide();
	}


	function otpAccredit() {
		
		var name= $("#userNm").val();//"홍길순";
		var tel = $("#userTel").val();//"01012341234";
		var otp = $("#otpNo").val();
		
		if (name == "" || tel == "" || otp == ""){
			alert("이름 / 전화번호 / OTP No는 필수 입니다.");
			return false;
		}
		var courseAgentPk = "";
		var classAgentPk = "";
		if("${vo.sbjCd}" != ""){
			courseAgentPk = "${vo.sbjCd}";
			classAgentPk = "${vo.crsCreCd}";
		}
		var usrid = "notnull";
		if("${vo.usrid}" != ""){
			usrid = "${vo.usrid}";
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
				USRID      : usrid,
				SESSIONID  : "${vo.sessionid}",
				EXIP       : "${vo.exip}",
				COURSE_AGENT_PK  : courseAgentPk,
				CLASS_AGENT_PK  : classAgentPk,
				EVAL_CD    : "${vo.evalCd}",
				EVAL_TYPE  : "${vo.evalType}",
				CLASS_TME  : "${vo.classTme}",
				USRDT      : getTimeStamp2()
			},success:function(data){
				//alert("data==="+JSON.stringify(data));
				if(data.code == 200) {
					//alert(data.status);
					if ("${vo.evalCd}" == "04") {
						//01:진도, 02:시험 , 03:과제,04:진행평가,05:토론,06:기타평가,07:보충자료학습,52:오프라인 시험, 53:오프라인 과제, 57:보충자료학습,99:기타
						alert("mOTP 인증을 통하여 본인인증에 성공하였습니다.");
						//로그 저장
						addMotpLog("${vo.evalCd}");
						//진행평가 함수호출
					} else if ("${vo.evalCd}" == "03") {
						alert("OTP 인증에 성공하였습니다.");
						//로그 저장
						addMotpLog("${vo.evalCd}");
						//과제 함수 호출
					} else if ("${vo.evalCd}" == "02") {
						alert("OTP 인증에 성공하였습니다.");
						//로그 저장
						addMotpLog("${vo.evalCd}");
						//시험 함수 호출
					} else if ("${vo.evalCd}" == "01") {
						// 02:최종평가, 03:과제, 04:중간평가, 99:기타+
						//로그 저장
						addMotpLog("${vo.evalCd}");
					} else {
						//나머지
						//alert("OTP 인증에 성공하였습니다.\n다시한번 시청 버튼을 클릭해 주시기 바랍니다.");
						alert("OTP 인증에 성공하였습니다.");
						//로그저장
						addMotpLog("00");
						parent.ItemVO1.motpPass 	= 'S';
					} 
					
					//스마트인재개발원이전 motp부분
					/* if(typeof parent.ItemVO1 == 'undefined'){
						for (var i = 0; i < parent.frames.length; i++) {
							if(typeof parent.frames[i].ItemVO1 != 'undefined'){
								window.parent[i][parent.frames[i].ItemVO1.returnMethod]();
								break;
							}
						}
					}else{
						//window.parent[parent.ItemVO1.returnMethod]();
						window.parent["showModalBox"]();
					} 
					*/
					
					if(typeof window.parent.showModalBox == 'undefined'){
						if(typeof parent.ItemVO1 == 'undefined'){
							for (var i = 0; i < parent.frames.length; i++) {
								if(typeof parent.frames[i].ItemVO1 != 'undefined'){
									window.parent[i][parent.frames[i].ItemVO1.returnMethod]();
									break;
								}
							}
						}else{
							if(parent.ItemVO1.returnMethod == 'viewContents'){
								window.parent[parent.ItemVO1.returnMethod](parent.ItemVO1.sbjCd,parent.ItemVO1.unitCd, parent.ItemVO1.review, parent.ItemVO1.cntsTypeCd, parent.ItemVO1.asmtSn);
							}else if(parent.ItemVO1.returnMethod == 'goMeta'){
								window.parent[parent.ItemVO1.returnMethod](parent.ItemVO1.avatar,parent.ItemVO1.userNm, parent.ItemVO1.sbjCd, parent.ItemVO1.unitCd, parent.ItemVO1.roomId);
							}
							else{
								window.parent[parent.ItemVO1.returnMethod]();
							}
						}
					}else{
						window.parent["showModalBox"]();
					}
					 
					
				}else if (data.code == "AP001") {// OTP 번호가 서버와 다를 경우
					//OTP 번호 오류
					alert("otp번호가 일치하지 않습니다.확인 후 다시 입력해 주세요.")
					$("#otpNo").val('');
					//alert(data.status)
				} else if (data.code == "AP009") {
					//OTP 번호 오류 5회 이상으로 계정 잠금 상태
					alert("otp번호 인증이 5회 실패하였습니다.\n본인인증을 통해 인증제한을 해제해 주시기 바랍니다.")
					$("#otpNo").val('');
					$(".content_03").show();
					smsAuth();
				}  else if (data.code == "AP002") { // user_nm 공백
					alert(data.msg);	
				} else if (data.code == "AP003") { // user_tel 공백
					alert(data.msg);					
				} else if (data.code == "AP004") { // OTP 공백
					alert(data.msg);	
				} else if (data.code == "AP005") { // 등록되지 않은 사용자
					alert(data.msg);	
				} else if (data.code == "AP006") { // 이미 등록된 사용자
					alert(data.msg);	
				} else if (data.code == "AP007") { // API 호출 에러
					alert(data.msg);	
				} else if (data.code == "AP008") { // 사용자 등록 중 에러 발생
					alert(data.msg);	
				} else if (data.code == "AP010") { // 패스워드 초기화 실패
					alert(data.msg);	
				} else if (data.code == "AP011") { 
					alert(data.msg);	
				} else if (data.code == "AP012") { // OTP 자릿수 오류[6자리만 가능] 
					alert(data.msg);	
				} else if (data.code == "WE005") { // 과도한 요청 시도시
					alert(data.msg);
				} else if (data.code == "WE001") { // API 전송시 header 의 content-type 에러
					alert(data.msg);
				} else if (data.code == "WE002") { // http 메소드가 post 가 아닌 경우
					alert(data.msg);
				} else if (data.code == "WE003") { // api header 정보가 잘못된 경우
					alert(data.msg);
				} else if (data.code == "WE004") { // 접속 로그를 작성 하는중 user_nm 이 잘 못된 경우
					alert(data.msg);
				} else if (data.code == "IE001") { // api 서버 내부 오류
					alert(data.msg);
				} else if (data.code == "CE001") { // api 서버 내부 오류
					alert(data.msg);
				} else if (data.code == "CE002") { // api 서버 내부 오류
					alert(data.msg);
				} else if (data.code == "CE003") { // api 서버 내부 오류
					alert(data.msg);
				} else {
					//시스템 장애 또는 파라미터 오류로 인한 장애
					alert("장애가 발생하였습니다.(코드:"+data.code+")\n잠시 후에 이용해 주시기 바랍니다.")
				}
			},error:function(e){
				alert("ERROR==="+JSON.stringify(e));
			}
			
		});
		
	}
	
	function otpUserReset(m_retCD, m_trnID) {
		var name= $("#userNm").val();//"홍길순";
		var tel = $("#userTel").val();//"01012341234";
		
		if (name == "" || tel == ""){
			alert("이름 / 전화번호는 필수 입니다.");
			return false;
		}
		var usrid = "notnull";
		if("${vo.usrid}" != ""){
			usrid = "${vo.usrid}";
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
				USRID     : usrid,
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
				}  else if (data.code == "AP001") { // OTP 번호가 서버와 다를 경우
					alert(data.msg);	
				}  else if (data.code == "AP002") { // user_nm 공백
					alert(data.msg);	
				} else if (data.code == "AP003") { // user_tel 공백
					alert(data.msg);					
				} else if (data.code == "AP004") { // OTP 공백
					alert(data.msg);	
				} else if (data.code == "AP005") { // 등록되지 않은 사용자
					alert(data.msg);	
				} else if (data.code == "AP006") { // 이미 등록된 사용자
					alert(data.msg);	
				} else if (data.code == "AP007") { // API 호출 에러
					alert(data.msg);	
				} else if (data.code == "AP008") { // 사용자 등록 중 에러 발생
					alert(data.msg);	
				} else if (data.code == "AP009") { // 패스워드 실페
					alert(data.msg);
				} else if (data.code == "AP010") { // 패스워드 초기화 실패
					alert(data.msg);	
				} else if (data.code == "AP011") { 
					alert(data.msg);	
				} else if (data.code == "AP012") { // OTP 자릿수 오류[6자리만 가능] 
					alert(data.msg);	
				} else if (data.code == "WE005") { // 과도한 요청 시도시
					alert(data.msg);
				} else if (data.code == "WE001") { // API 전송시 header 의 content-type 에러
					alert(data.msg);
				} else if (data.code == "WE002") { // http 메소드가 post 가 아닌 경우
					alert(data.msg);
				} else if (data.code == "WE003") { // api header 정보가 잘못된 경우
					alert(data.msg);
				} else if (data.code == "WE004") { // 접속 로그를 작성 하는중 user_nm 이 잘 못된 경우
					alert(data.msg);
				} else if (data.code == "IE001") { // api 서버 내부 오류
					alert(data.msg);
				} else if (data.code == "CE001") { // api 서버 내부 오류
					alert(data.msg);
				} else if (data.code == "CE002") { // api 서버 내부 오류
					alert(data.msg);
				} else if (data.code == "CE003") { // api 서버 내부 오류
					alert(data.msg);
				} else {
					//alert(data.status)
					alert("OTP 제한 해제에 실패하였습니다.\n다시 한번 본인인증을 통해 인증제한을 해제해 주시기 바랍니다.");
					$("#otpNo").val('');
				}
			},error:function(e){
				alert("ERROR==="+JSON.stringify(e));
			}
			
		});
		
	}
	
	function emonAuthMotp(m_retCD, m_trnID,m_Ret,evalCd) {
		var AGTID = "edulife";
		var USRID = "notnull";
		var COURSE_AGENT_PK = "notnull";
		var CLASS_AGENT_PK = "notnull";
		if("${vo.sbjCd}" != ""){
			COURSE_AGENT_PK = "${vo.sbjCd}";
			CLASS_AGENT_PK = "${vo.crsCreCd}";
		}
		if("${vo.usrid}" != ""){
			USRID = "${vo.usrid}";
		}
		
		var m_trnDT = getTimeStamp2();
		var url = "";
		if(evalCd == "00"){
			url = "https://emon.hrdkorea.or.kr/EAIServer/SOURCE/ExConn/LMS/pAuthLog.jsp";
		}else{
			url = "https://emon.hrdkorea.or.kr/EAIServer/SOURCE/ExConn/LMS/pSubOtpLog.jsp";
		}
	
		$.ajax({
			type : "POST",
			url  : url,
			crossDomain: true,
			data : {
				AGTID : AGTID,
				USRID : USRID,
				COURSE_AGENT_PK : COURSE_AGENT_PK,
				CLASS_AGENT_PK : CLASS_AGENT_PK,
				EVAL_CD    : evalCd,
				EVAL_TYPE  : "${vo.evalType}",
				CLASS_TME  : "${vo.classTme}",
				m_Ret : m_Ret,
				m_retCD : m_retCD,
				m_trnID : m_trnID,
				m_trnDT : m_trnDT
			},
			dataType : "xml",
			success : function(xml) {
				if($(xml).find("RetVal").text() == "102" ) {
					alert("오류가 발생하였습니다.");
				} else if($(xml).find("RetVal").text() == "101") {
					//alert("OTP 대체인증으로 휴대폰 인증에 성공하였습니다.\n다시한번 시청 버튼을 클릭해 주시기 바랍니다.");
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
	
	function smsAuth(){
		//본인인증 팝업호출
		var checkUrl = cUrl("/home/etc/niceCheck/viewUserCheck");
		window.open(checkUrl, 'popupChk', 'width=10, height=10, top=110, left=110, fullscreen=no, menubar=no, status=no, toolbar=no, titlebar=yes, location=no, scrollbar=no');
	}
	
	//휴대폰인증 성공함수
	window.completeCert = function() {
		alert("인증이 완료되었습니다.");
		var name= $("#userNm").val();//"홍길순";
		var tel = $("#userTel").val();//"01012341234";
		if (name != "" && tel != ""){
			otpUserReset('notnull','notnull');
		}
		emonAuthMotp('notnull','notnull','T','${vo.evalCd}');
		if(typeof parent.ItemVO1 == 'undefined'){
			for (var i = 0; i < parent.frames.length; i++) {
				if(typeof parent.frames[i].ItemVO1 != 'undefined'){
					window.parent[i][parent.frames[i].ItemVO1.returnMethod]();
					break;
				}
			}
		}else{
			parent.ItemVO1.motpPass 	= 'S';
			//window.parent[parent.ItemVO1.returnMethod]();
			window.parent["showModalBox"]();
		}
	}
	
	//휴대폰인증 실패함수
	window.failCert = function(errorCode) {
		alert("인증이 실패하였습니다. 에러코드 : " + errorCode);
		emonAuthMotp('notnull','notnull','F','${vo.evalCd}');
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
	
	//otp 인증 후 로그 저장
	function addMotpLog(evalCd){
		var evalType = "OTP";
		$.getJSON( cUrl("/mng/etc/HrdApi/addMotpLog"),{ "evalCd" : evalCd , "evalType" : evalType},			
			function(data) {
			
			}
		);
	}
</script>