<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>
<mhtml:home_html>
	<mhtml:home_head mainPage="y" titleName="${USER_ORGNM}">
		<mhtml:head_module encryptjs="y"/>
	</mhtml:home_head>
	<mhtml:home_body subContent="Y" subMenu="Y">
				
				<c:if test="${!empty sessionScope.USERNAME }">
				<div class="content-section">
					<div class="contentWrap">
						<form id="applyForm" name="applyForm" action="/home/org/openApply">
						<div class="contDesc">
							<spring:message code="home.open.openmessage1"/>
							<a href="/home/main/goMenuPage?mcd=${openApplyMcd }" class="btn mt5"><spring:message code="home.open.detailinfo"/></a>
						</div>
						<div class="agreeBox">
							<div class="section">
								<h4><spring:message code="home.open.privateinfouseagree"/></h4>
								<div class="terms">
									<div class="inner">
										${pageVO1.pageCts }
									</div>
								</div>
								<div class="agree">
									<input type="checkbox" id="agree1" name="agree" class="icon">
									<label for="agree1" class="checkbox"><spring:message code="home.open.privateinfouseagreey"/></label>
								</div>
							</div>
							<div class="section">
								<h4><spring:message code="home.open.publicuseagree"/></h4>
								<div class="terms">
									<div class="inner">
										${pageVO2.pageCts }
									</div>
								</div>
								<div class="agree">											
									<input type="checkbox" id="agree2" name="agree" class="icon">
									<label for="agree2" class="checkbox"><spring:message code="home.open.publicuseagreey"/></label>
								</div>
							</div>
						</div>
						<div class="contTit">
							<h4><spring:message code="home.open.inputinfo"/></h4>
							<div class="infoRight">
								<span class="icon"><img src="<c:url value="/001/img/ico_required.gif"/>" alt="<spring:message code="home.open.required"/>"></span><spring:message code="home.open.required"/> / <spring:message code="home.open.requiredmessage1"/>
							</div>
						</div>
						<ul class="tbl">
							<li>
								<dl>
									<dt><spring:message code="home.open.name"/></dt>
									<dd>${sessionScope.USERNAME }</dd>
								</dl>
							</li>
							<li>
								<dl>
									<dt><label for="aplcOrgNm" class="required"><img src="<c:url value="/001/img/ico_required.gif"/>" alt="<spring:message code="home.open.required"/>"><spring:message code="home.open.useorgname"/></label></dt>
									<dd>
									<input name="aplcOrgNm" type="text" id="aplcOrgNm" title="<spring:message code="home.open.orgname"/>" maxlength="16">									
									</dd>
								</dl>
							</li>
							<li>
								<dl>
									<dt><label for="mobile1" class="required"><img src="<c:url value="/001/img/ico_required.gif"/>" alt="<spring:message code="home.open.required"/>"><spring:message code="home.open.phoneno"/></label></dt>
									<dd class="pu_num">
									<spring:message code="home.open.mobilephone"/> :
									<select name="mobileNo1" id="mobileNo1" class="mobileField" onchange="" title="<spring:message code="home.open.mobilecompno"/>">
										<option value="010">010</option>
										<option value="011">011</option>
										<option value="016">016</option>
										<option value="017">017</option>
										<option value="018">018</option>
										<option value="019">019</option>
									</select> - 
									<input name="mobileNo2" id="mobileNo2" type="text" class="mobileField w100 inputNumber" title="<spring:message code="home.open.midmobileno"/>" maxlength="4"> - 
									<input name="mobileNo3" id="mobileNo3" type="text" class="mobileField w100 inputNumber" title="<spring:message code="home.open.lastmobileno"/>" maxlength="4">
									<div class="clearfix mt10"></div>	
									<spring:message code="home.open.normalphone"/> :
									<select name="phoneno1" id="phoneno1" class="mobileField" onchange="" title="<spring:message code="home.open.phoneareano"/>">
										<option value="02">02</option>
										<option value="031">031</option>
										<option value="032">032</option>
										<option value="041">041</option>
										<option value="042">042</option>
										<option value="043">043</option>
										<option value="044">044</option>
										<option value="051">051</option>
										<option value="052">052</option>
										<option value="053">053</option>
										<option value="054">054</option>
										<option value="055">055</option>
										<option value="061">061</option>
										<option value="062">062</option>
										<option value="063">063</option>
										<option value="064">064</option>
									</select> - 
									<input name="phoneno2" id="phoneno2" type="text" class="mobileField w100 inputNumber" title="<spring:message code="home.open.midmobileno"/>" maxlength="4"> - 
									<input name="phoneno3" id="phoneno3" type="text" class="mobileField w100 inputNumber" title="<spring:message code="home.open.lastmobileno"/>" maxlength="4">
									</dd>
								</dl>
							</li>
							<li>
								<dl>
									<dt><label for="email1" class="required"><img src="<c:url value="/001/img/ico_required.gif"/>" alt="<spring:message code="home.open.required"/>"><spring:message code="home.open.receiveemail"/></label></dt>
									<dd>
									<input name="email1" type="text" id="email1" class="emailField w150" title="<spring:message code="home.user.emailid"/>" maxlength="100">	
									<span> @ </span>
									<input name="email2" type="text" id="email2" class="emailField w150" title="<spring:message code="home.user.emaildomain"/>" maxlength="100">
									</dd>
								</dl>
							</li>
							<li>
								<dl>
									<dt><label for="domainNm" class="required"><img src="<c:url value="/001/img/ico_required.gif"/>" alt="<spring:message code="home.open.required"/>"><spring:message code="home.open.orgname"/></label></dt>
									<dd>
									<input name="domainNm" type="text" id="domainNm" title="<spring:message code="home.open.orgname"/>" maxlength="80">									
									</dd>
								</dl>
							</li>
							<li>
								<dl>
									<dt><label for="id" class="required"><img src="<c:url value="/001/img/ico_required.gif"/>" alt="<spring:message code="home.open.required"/>"><spring:message code="home.open.domain"/></label></dt>
									<dd>
									<input name="domain" type="text" id="domain" class="w150" title="<spring:message code="home.open.domain"/>" maxlength="33">
<%-- 									.kedx.${productDomain }							 --%>
									</dd>
								</dl>
							</li>
							<li>
								<dl>
									<dt><label for="purp" class="required"><img src="<c:url value="/001/img/ico_required.gif"/>" alt="<spring:message code="home.open.required"/>"><spring:message code="home.open.usepurpose"/></label></dt>
									<dd>										
										<textarea id="purp" name="purp" rows="3" class="wmax" title="<spring:message code="home.open.usepurpose"/>" maxlength="1300"></textarea>
									</dd>
								</dl>
							</li>
						</ul>
						<div class="wrap_desc">
							※ <spring:message code="home.open.sendmailmessage1"/> &nbsp;로그인 후에 신청하실 수 있습니다.
						</div>
						<div class="wrap_btn">
							<a href="javascript:void(0);" class="btn large btn-blue" onclick="doApply();"><spring:message code="home.open.doapply"/></a>
						</div>
						</form>	
					</div>
				</div>
				</c:if>
				
	</mhtml:home_body>
	
<script type="text/javascript">

	$(function(){
		$('.inputNumber').inputNumber();
		if('${sessionScope.USERNAME}' == null || '${sessionScope.USERNAME}' == ""){
			document.location.href = "/home/main/indexMain";
			alert("로그인 후 이용 가능합니다.");
// 			var loginCheck = confirm("로그인 후에 신청 가능합니다. \n로그인하시겠습니까?");
// 			if(loginCheck){
// 				loginFormDiv();
// 			}
		}
	})

	$.fn.inputNumber = function() {
		if (!this) return false;
		return this.each(function() {
			$(this).keydown(function(event) {
				if(!isNumberInput(event.keyCode)) {
					return false;
				}
			});
		});
	};
	
	function isNumberInput(key) {
		if(!( key==8 ||key==9||key==13||key==46||key==144||(key>=48&&key<=57)||(key>=96&&key<=105)||key==110||key==190||(key>=37&&key<=40))) {
			return false;
		}
		return true;
	}

	function doApply(){
		
		if($("input[name=agree]:checked").length < 2) {
			alert("<spring:message code="home.open.bothuseagreemessage1"/>");
			return;
		}
		
		var aplcOrgNm = $.trim($("#aplcOrgNm").val());
		
		var mobileNo1 = $("#mobileNo1").val();
		var mobileNo2 = $.trim($("#mobileNo2").val());
		var mobileNo3 = $.trim($("#mobileNo3").val());
		var mobileNo = mobileNo1 + mobileNo2 + mobileNo3;
		
		var phoneno1 =  $("#phoneno1").val();
		var phoneno2 =  $.trim($("#phoneno2").val());
		var phoneno3 =  $.trim($("#phoneno3").val());
		var phoneno = phoneno1 + phoneno2 + phoneno3
		
		var email1 = $.trim($("#email1").val());
		var email2 = $.trim($("#email2").val());
		var emailAddr = email1 +"@"+ email2;
		
		var domainNm = $.trim($("#domainNm").val());
		var domain = $.trim($("#domain").val());
		
		var purp = $.trim($("#purp").val());
		
		if(aplcOrgNm == ""){
			alert("<spring:message code="home.open.inputuseorgnamemessage1"/>");
			return;
		}
		
		if(mobileNo2 == "" || mobileNo3 == "" || phoneno2 == "" || phoneno3 == ""){
			alert("<spring:message code="home.open.inputphoneno"/>");
			return;
		}
		
		if(email1 == "" || email2 == ""){
			alert("<spring:message code="home.open.inputreceiveemail"/>");
			return;
		}
		
		if(domainNm == ""){
			alert("<spring:message code="home.open.inputorgname"/>");
			return;
		}
		
		if(domain == ""){
			alert("<spring:message code="home.open.inputdomain"/>");
			return;
		}
		
		if(purp == ""){
			alert("<spring:message code="home.open.inputusepurpose"/>");
			return;
		}
		
		$.getJSON("<c:url value="/home/org/openApply/joinAddApply"/>", {
				  "aplcOrgNm"	: aplcOrgNm
				, "mobileNo" : mobileNo
				, "phoneno" : phoneno
				, "emailAddr" : emailAddr
				, "domainNm" : domainNm
				, "domain" : domain
				, "purp" : purp
			}, function(resultVO){
			if(resultVO.result > 0) {
				alert("<spring:message code="home.open.applysuccess"/>");
				window.location.reload();
			}else{
				alert("<spring:message code="home.open.applyfail"/>");
			}
		});
	}

</script>	
	
</mhtml:home_html>