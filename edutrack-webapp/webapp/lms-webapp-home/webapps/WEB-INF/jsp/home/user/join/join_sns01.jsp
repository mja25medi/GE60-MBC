<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>
    
<c:url var="urlJoinStep3" value="/home/user/joinSns02_Main"/>
<c:url var="img_base" value="/img/home" />
<c:url var="cancel_url" value="/home/user/joinCancel" />

                <!-- content -->
                <div id="content" class="content">
                    <div class="member">
                        <div class="flex-container">
                            <div class="join_cont">
                        	<form id="agreementForm" name="agreementForm" method="post" onsubmit="return false;" action="${urlJoinStep3}" >
                                <ol class="join_step">
                                    <li class="active">               
                                        <span class="title"><small>Step 1</small> <b>약관동의</b></span>
                                    </li>
                                    <li>
                                        <span class="title"><small>Step 2</small> <b>정보입력</b></span>                                       
                                    </li>                            
                                    <li>
                                        <span class="title"><small>Step 3</small> <b>가입완료</b></span>                                       
                                    </li>                                     
                                </ol>
                            
                                <div class="terms_area">
                                    <h5 class="title_h2">이용약관</h5>
                                    <div class="box terms">
                                       ${pageVO1.pageCts}
                                    </div>                    
                                    <div class="terms_agree">                        
		                                <span class="custom-input">
		                                    <input type="radio" name="agree1" id="agree1Y">
		                                    <label for="agree1Y">동의</label>
		                                </span>                    
		                                <span class="custom-input">
		                                    <input type="radio" name="agree1" id="agree1N">
		                                    <label for="agree1N">동의하지않음</label>
		                                </span>                        
		                            </div>
                                </div>
                                <!-- //terms_area -->  
        
                                <div class="terms_area">
                                    <h5 class="title_h2">개인정보처리방침</h5>
                                    <div class="box terms">
                                        ${pageVO2.pageCts}
                                    </div>                    
                                    <div class="terms_agree">                        
		                                <span class="custom-input">
		                                    <input type="radio" name="agree2" id="agree2Y">
		                                    <label for="agree2Y">동의</label>
		                                </span>                    
		                                <span class="custom-input">
		                                    <input type="radio" name="agree2" id="agree2N">
		                                    <label for="agree2N">동의하지않음</label>
		                                </span>                        
		                            </div>
                                </div>
                                <!-- //terms_area -->  
        
                               <div class="all_terms">
		                            <span class="custom-input"><input type="checkbox" id="allCheck">
		                            <label for="allCheck">약관 및 개인정보 수집·이용에 전체 동의합니다.</label></span>
		                        </div>
		
		                        <div class="btns">
		                            <button type="button" class="btn type5" onclick="cancel_join('${cancel_url}');">취소</button>
		                            <button type="button" class="btn type1" id="btnAgreement" onclick="goNextStep()" >다음</button>
		                        </div>
              				</form>
                         </div>

                    </div>
                </div>
             </div>
       
    </div>
</body>
<script type="text/javascript">
	var certificated = false;
	
	$(document).ready(function() {
		$('#allCheck').bind("click", function(event) {
			allCheck();
		});
		
		$(".subMenu_title").hide();
	});
	
	function cancel_join(url){
		location.href = url;
	}
	
	function allCheck(){
		if($("#allCheck").is(":checked")){
			$("#agree1Y").prop("checked","true");
			$("#agree2Y").prop("checked","true");
		}
	}
	
	function goNextStep() {
		if(!validateAgreement()) {
			alert("<spring:message code="user.message.login.joinin.alert.check.agree"/>");
			return
		}
		document.agreementForm.submit();
	}
	
	function validateAgreement() {
		return $("#agree1Y").is(':checked') && $("#agree2Y").is(':checked');
	}
	
	function validateCert() {
		return certificated;
	}
	
</script>

