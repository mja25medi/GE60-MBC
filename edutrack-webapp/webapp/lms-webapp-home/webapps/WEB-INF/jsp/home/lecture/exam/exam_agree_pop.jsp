<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="img_base" value="/img/home" />
<c:set var="vo" value="${vo}"/>

<form id="examAgreeForm" name="examAgreeForm" onsubmit="return false" >
		<input type="hidden" name="examSn" id="examSn" value="${vo.examSn}" />
		<input type="hidden" name="stareCnt" id="stareCnt" value="${vo.stareCnt}" />
		<input type="hidden" name="semiExamYn" id="semiExamYn" value="${vo.semiExamYn}" />
</form>
	${orgTermsVO.pageCts }

<form class="pt10 pb10">
	<div class="custom-input mt30">
	      <input type="checkbox" id="examAgree" name="examAgree" value="Y"/>
	      <label for="examAgree">위 시험응시 약관에 동의합니다.</label>
	</div>
</form>	

		
<div class="modal_btns" style="text-align:center;width:100%;margin-top:10px;">
	<button class="btn" onclick="parent.modalBoxClose()" ><spring:message code="lecture.title.exam.agree.cancel"/></button>
	<button class="btn type2" onclick="goExamPaperPop()" ><spring:message code="lecture.title.exam.agree.next"/></button>
</div>

<script type="text/javascript">
var ItemVO1 = {};

$(document).ready(function() {
});

function goExamPaperPop(){
	var agreeYn = $("input[name=examAgree]:checked").val();
	var examSn  = $("#examSn").val();
	if(agreeYn != "Y"){
		alert("약관에 동의하셔야 시험을 응시하실 수 있습니다.");
		return;
	}else{
		exceptIdCheck();
	}
}

function examPaperPop(){
	pop_motp_close();
	var agreeYn = $("input[name=examAgree]:checked").val();
	var examSn  = $("#examSn").val();
	parent.examPaperStare(examSn, agreeYn);
}

function exceptIdCheck(){
	ItemVO1.returnMethod = 'examPaperPop';

	$.getJSON( 
		"/home/user/selectExceptionIdCheck",{ },			
		function(data) {
			if(data.idCheck == 'Y') {
				var agreeYn = $("input[name=examAgree]:checked").val();
				var examSn  = $("#examSn").val();
				parent.examPaperStare(examSn, agreeYn);
			} else {
				 pop_motp($("#examSn").val());
			}
		}
	);
}


function pop_motp(examSn){
	parent.modalBox.close();
	var evalCd = "02";
	var semiExamYn = $("#semiExamYn").val();
	if(semiExamYn == "Y"){
		evalCd = "04";
	}
	
	var url = generateUrl("/mng/etc/HrdApi/viewMotp",{ "evalCd": evalCd ,"examSn":examSn,"stdNo":"${vo.stdNo}","crsCreCd":"${vo.crsCreCd}"});
	var addContent  = "<iframe id='viewMotpFrame' name='viewMotpFrame' width='100%' height='100%' "
	+ "frameborder='0' scrolling='auto' src='"+url+"'/>";
	parent.otpModalBox.clear();
	parent.otpModalBox.addContents(addContent);
	parent.otpModalBox.resize(500, 680);
	parent.otpModalBox.setTitle("M-OTP 인증");
	parent.otpModalBox.show();
	
}

function pop_motp_close(){
	parent.otpModalBox.clear();
	parent.otpModalBox.close();
	parent.modalBox.show();
}


</script>
</body>
