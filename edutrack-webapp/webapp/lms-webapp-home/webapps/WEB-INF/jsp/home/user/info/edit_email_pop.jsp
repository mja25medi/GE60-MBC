<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>

	<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js"></script>

    <link rel="stylesheet" href="/tpl/003/css/hrd_common.css">
    <link rel="stylesheet" href="/tpl/003/css/webfonts.css">
    <link rel="stylesheet" href="/tpl/003/css/layout.css">    
    <link rel="stylesheet" href="/tpl/003/css/effect_slick.css">
    <link rel="stylesheet" href="/tpl/003/css/sub.css"><!-- sub 페이지에서 사용 -->
    
	<c:set var="vo" value="${vo}"/>
		<div class="modal_pad">
			<div class="tstyle">
				<ul class="dbody">
	     			<li>
						<div class="row">
			                <label class="form-label col-sm-2">이메일주소</label>
	                        <div class="col-sm-10">
	                        	<div class="form-row">
									<input class="form-control mr5" type="text" name="email_id" id="email_id" onchange="resetEmailCheck()">	                        	
									<span class="mr5">@</span>
			                    	<input class="form-control mr5" type="text" name="email_domain_text" id="email_domain_text" onchange="resetEmailCheck()" title="이메일 주소 뒷자리" placeholder="">
			                    	<select class="form-select" name="email_domain_sel" id="email_domain_sel" onchange="emailSet()">
			                        	<c:forEach items="${emailCode}" var="item">
											<c:set var="codeName" value="${item.codeNm}"/>
											<c:forEach var="lang" items="${item.codeLangList}">
												<c:if test="${lang.langCd eq LOCALEKEY }"><c:set var="codeName" value="${lang.codeNm}"/></c:if>
											</c:forEach>
											<option value="${item.codeCd}" <c:if test="${item.codeCd eq fn:split(emailDomain,'.')[0] }"> selected</c:if>>${codeName}</option>
										</c:forEach>
			                        </select>
			                        <button id="emailChkBtn" type="button" class="btn gray2 ml5" onclick="emailDupCheck()">중복확인</button>
		                        	<!-- <input class="form-control" type="password" name="userPassConfirm" id="userPassConfirm" value="" maxlength="100" title="user.title.userinfo.password.old" autocomplete="off"> -->
	                        	</div>
	                        </div>
			            </div>
	           		</li>
	        	</ul>
			</div>
		</div>
		
         <div class="modal_btns">
	        <button type="button" class="btn type4" onclick="changeEmail();">변경</button>
	        <button type="button" class="btn" onclick="javascript:parent.modalBoxClose();">취소</button>
	     </div>
				    
         <input type="hidden" name="emailCheck" id="emailCheck" value="N" />



<script type="text/javascript">
	// 페이지 초기화
	$(document).ready(function() {
		$("#email_domain_sel").val('DIRECT');
		emailSet();
	});
	
	function changeEmail() {
		if($('#emailCheck').val() == 'N') {
			alert("이메일 중복 확인을 완료해주세요.");
			return;
		}
		var email = $("#email_id").val()+"@"+$("#email_domain_text").val();
		parent.emailVal(email);
		parent.modalBoxClose();
	}
	
	function emailSet(){
		var email_domain_sel = $("#email_domain_sel").val();
		if(email_domain_sel == 'DIRECT' ){
			$("#email_domain_text").attr("readonly", false);
		} else {
			$("#email_domain_text").attr("readonly", true);
			$("#email_domain_text").val($("#email_domain_sel :selected").text());
		}
		resetEmailCheck();
	}
	
	function emailDupCheck() {
		if(validateEmail()){
			var email = $("#email_id").val() + "@" + $("#email_domain_text").val() ;

			var pattern = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-za-z0-9\-]+/;

			if(pattern.test(email) === false) { alert("올바른 이메일 형식이 아닙니다."); return false; }
			
			$.getJSON( 
				"/home/user/emailCheck",
				{"email" : email },			// params
				function(data) {
					if(data.isUseable == 'Y') {
						$("#emailCheck").val('Y');
						alert("사용가능한 이메일 입니다.");
						$("#emailChkBtn").hide();
					} else {
						alert("중복된 이메일 입니다.");
					}
				}
			);
		} else {
			alert("올바른 형식이 아닙니다.");
		}
	}

	function resetEmailCheck() {
		$("#emailCheck").val('N');
		$("#emailChkBtn").show();
	}
	
	function validateEmail() {
		return !($("#email_id").val() == "" || $("#email_domain_text").val() == "");
	}
	
</script>