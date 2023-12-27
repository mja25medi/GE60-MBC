<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>

	<div class="callout callout-warning" style="margin-bottom: 10!important;">
		<span style="color:red;font-weight:bold;">* </span><spring:message code="common.message.input.required"/>
	</div>
	<form name="userInfoForm" id="userInfoForm" onsubmit="return false" method="POST" >
	<input type="hidden" name="userNo" id="userNo" value="${vo.userNo}"/>
	<input type="hidden" name="orgCd" id="orgCd" value="${vo.orgCd}"/>
	<input type="hidden" name="userSts" id="userSts" value="${vo.userSts}"/>
	<input type="hidden" name="adminAuthGrpCd" id="adminAuthGrpCd" value="${vo.adminAuthGrpCd}"/>
	<input type="hidden" name="wwwAuthGrpCd" id="wwwAuthGrpCd" value="${vo.wwwAuthGrpCd}"/>
	<input type="hidden" name="mngAuthGrpCd" id="mngAuthGrpCd" value="${vo.mngAuthGrpCd}"/>
	<input type="hidden" name="sexCd" id="sexCd" value = "${vo.sexCd }" />
	<input type="hidden" name="msgRecv" id="msgRecv" value = "Y" />
		<table summary="<spring:message code="user.title.userinfo.manage"/>" class="table table-bordered wordbreak">
		<colgroup>
			<col width="18%"/>
			<col width="32%"/>
			<col width="18%"/>
			<col width="32%"/>
		</colgroup>
		<tr>
			<th scope="row"><span style="color:red;">* </span><label for="userNm">이름</label></th>
			<td>
				<input type="text" name="userNm" id="userNm" class="form-control input-sm" value="${vo.userNm }" isNull="N" lenCheck="500" maxlength="500" style="vertical-align: middle; width: 200px;" />
			</td>
			<th scope="row"><span style="color:red;">* </span><label for="userId">아이디</label></th>
			<td>
				<div class="input-group" style="width:80%;">
					<input type="text" name="userId" id="userId" class="form-control input-sm" value="${vo.userId }" isNull="N" lenCheck="500" maxlength="500" onkeyup="resetIdChk();" style="vertical-align: middle; width: 200px;" />
					<span class="input-group input-group-btn" id="idChkBtn" style="display:none;">
	      				<button class="btn btn-info btn-sm" type="button" onclick="idDupCheck();">중복체크</button>
	    			</span>	
    			</div>
			</td>	
		</tr>
		<c:if test="${gubun eq 'A'}">
		<tr>
			<th scope="row"><span style="color:red;">* </span><label for="userPass">비밀번호</label></th>
			<td>
				<div class="input-group" style="float:left;">
					<input type="password" maxlength="16" name="userPass" id="userPass" value="" class="inputDate form-control input-sm inputNumber" style="width: 200px;"/>
				</div>			
			</td>
			<th scope="row"><span style="color:red;">* </span><label for="userPassChk">비밀번호 확인</label></th>
			<td>
				<div class="input-group" style="float:left;">
					<input type="password" maxlength="16" name="userPassChk" id="userPassChk" value="" class="inputDate form-control input-sm inputNumber" style="width: 200px;"/>
				</div>			
			</td>
		</tr>
		</c:if>
		<tr>
			<th scope="row" ><span style="color:red;">* </span><label for="email">이메일</label></th>
			<td>
				<div class="input-group" style="width:80%">
					<input type="text" name="email" id="email" class="form-control input-sm" value="${vo.email}" onkeyup="resetEmailChk();" style="width: 200px;"/>		
					<span class="input-group input-group-btn" id="emailChkBtn" style="display:none;">
	      				<button class="btn btn-info btn-sm" type="button" onclick="dupEmailChk();">중복체크</button>
	    			</span>		
				</div>	    								
			</td>
			<th scope="row"><label for="juminNo">주민등록번호</label></th>
			<td>
				<input type="hidden" name="juminNo" id="juminNo" />
				<div class="input-group" style="float:left;width:150px;">
					<input type="text" maxlength="6" name="juminNo1" id="juminNo1" value="${fn:substring(vo.juminNo,0,6) }" class="inputDate form-control input-sm inputNumber"/>
				</div>
				<div class="input-group text-center" style="float:left;padding-left:10px; padding-right:10px;"> - </div>
				<div class="input-group" style="float:left;width:150px;">
					<input type="password" maxlength="7" name="juminNo2" id="juminNo2" value="${fn:substring(vo.juminNo,6,13) }" class="inputDate form-control input-sm inputNumber"/>
				</div>						
			</td>
		</tr>

		<tr>
			<th scope="row"><span style="color:red;">* </span><label for="faxNo1">휴대폰 번호</label></th>
			<td>
				<div class="input-group" style="float:left;width:80px;">
					<c:set var="firstMobileNo" value="${fn:substring(vo.mobileNo,0,3) }" />
					<input type="hidden" name="mobileNo" id="mobileNo" />
	                <select class="form-control input-sm" id="mobileNo1">
	                	<option value="">선택</option>
						<c:forEach var="item" items="${mobileCdList }">
							<option value="${item.codeCd }" <c:if test="${firstMobileNo eq item.codeCd }">selected</c:if>>${item.codeNm }</option>
						</c:forEach>	                
	                </select> 
				</div>
				<div class="input-group text-center" style="float:left;padding-left:10px; padding-right:10px;"> - </div>
				<div class="input-group" style="float:left;width:60px;">
					<input class="inputDate form-control input-sm inputNumber" type="text" name="title" value="${fn:substring(vo.mobileNo,3,7) }" id="mobileNo2" title="전화번호 두번째자리">
				</div>
				<div class="input-group text-center" style="float:left;padding-left:10px; padding-right:10px;"> - </div>
				<div class="input-group" style="float:left;width:60px;">
					<input class="inputDate form-control input-sm inputNumber" type="text" name="title" value="${fn:substring(vo.mobileNo,7,11) }" id="mobileNo3" title="전화번호 세번째자리">
				</div>						
			</td>
			<th scope="row"><label for="phoneno1">전화번호</label></th>
			<td>
				<div class="input-group" style="float:left;width:65px;">
				<input type="hidden" name="homePhoneno" id="homePhoneno" />
				<c:set var="phoneNoArray" value="${fn:split(vo.homePhoneno,'-') }"/>
				<select name="phoneno1" id="phoneno1" class="form-control input-sm">
					<c:forEach var="item" items="${localPhoneCdList }">
						<option value="${item.codeCd }" <c:if test="${phoneNoArray[0] eq item.codeCd }">selected</c:if>>${item.codeNm }</option>
					</c:forEach>	
				</select>
				</div>
				<div class="input-group text-center" style="float:left;padding-left:10px; padding-right:10px;"> - </div>
				<div class="input-group" style="float:left;width:60px;">
					<input type="text" maxlength="4" name="phoneno2" id="phoneno2" value="${phoneNoArray[1] }" class="inputDate form-control input-sm inputNumber"/>
				</div>
				<div class="input-group text-center" style="float:left;padding-left:10px; padding-right:10px;"> - </div>
				<div class="input-group" style="float:left;width:60px;">
					<input type="text" maxlength="4" name="phoneno3" id="phoneno3" value="${phoneNoArray[2] }" class="inputDate form-control input-sm inputNumber"/>
				</div>						
			</td>
		</tr>
		<tr>
			<th scope="row"><c:if test="${cfgItem.requiredYn eq 'Y'}"><span style="color:red;">* </span></c:if><label for="birth"><spring:message code="user.title.userinfo.birth"/></label></th>
			<td>
				<input type="hidden" name="birth" id="birth" />
				<c:set var="birthYear" value="${fn:substring(vo.birth,0,4)}"/>
				<c:set var="birthMonth" value="${fn:substring(vo.birth,4,6)}"/>
				<c:set var="birthDay" value="${fn:substring(vo.birth,6,8)}"/>
				<select name="birthYear" id="birthYear" class="form-control input-sm" style="width:80px;float:left">
				<c:forEach var="year" items="${yearList}">
					<option value="${year}" <c:if test="${birthYear eq year}">selected</c:if>>${year}</option>
				</c:forEach>
				</select>
				<select name="birthMonth" id="birthMonth" class="form-control input-sm" style="width:60px;float:left" onchange="checkBirthDay()">
					<option value="01" <c:if test="${birthMonth eq '01' }">selected</c:if>>01</optin>
					<option value="02" <c:if test="${birthMonth eq '02' }">selected</c:if>>02</optin>
					<option value="03" <c:if test="${birthMonth eq '03' }">selected</c:if>>03</optin>
					<option value="04" <c:if test="${birthMonth eq '04' }">selected</c:if>>04</optin>
					<option value="05" <c:if test="${birthMonth eq '05' }">selected</c:if>>05</optin>
					<option value="06" <c:if test="${birthMonth eq '06' }">selected</c:if>>06</optin>
					<option value="07" <c:if test="${birthMonth eq '07' }">selected</c:if>>07</optin>
					<option value="08" <c:if test="${birthMonth eq '08' }">selected</c:if>>08</optin>
					<option value="09" <c:if test="${birthMonth eq '09' }">selected</c:if>>09</optin>
					<option value="10" <c:if test="${birthMonth eq '10' }">selected</c:if>>10</optin>
					<option value="11" <c:if test="${birthMonth eq '11' }">selected</c:if>>11</optin>
					<option value="12" <c:if test="${birthMonth eq '12' }">selected</c:if>>12</optin>
				</select>
				<select name="birthDay" id="birthDay" class="form-control input-sm" style="width:60px;float:left">
				</select>
			</td>
			<th scope="row"><span style="color:red;">* </span><label for="deptCd">소속기업</label></th>
			<td>
				<input type="hidden" name="deptNm" id="deptNm" />
				<select name="deptCd" id="deptCd" class="form-control input-sm" style="width: 200px;">
					<option value="">선택</option>
					<c:forEach var="item" items="${deptList }">
						<option value="${item.deptCd }" <c:if test="${item.deptCd eq vo.deptCd}">selected</c:if>>${item.deptNm }</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<th scope="row"><label for="sexCd">성별</label></th>
			<td>
				<input type="radio" name="sexCd" value="M" <c:if test="${vo.sexCd eq 'M'}">checked</c:if> /> 남성
				<input type="radio" name="sexCd" value="F" <c:if test="${vo.sexCd eq 'F'}">checked</c:if> /> 여성
			</td>
		</tr>
		<tr>
			<th scope="row"><label for="emailRecv">메일수신여부</label></th>
			<td>
				<input type="radio" name="emailRecv" value="Y" <c:if test="${vo.emailRecv ne 'N'}">checked</c:if> /> 수신
				<input type="radio" name="emailRecv" value="N" <c:if test="${vo.emailRecv eq 'N'}">checked</c:if> /> 수신거부
			</td>		
			<th scope="row"><label for="smsRecv">문자수신여부</label></th>
			<td>
				<input type="radio" name="smsRecv" value="Y" <c:if test="${vo.smsRecv ne 'N'}">checked</c:if> /> 수신
				<input type="radio" name="smsRecv" value="N" <c:if test="${vo.smsRecv eq 'N'}">checked</c:if> /> 수신거부
			</td>
		</tr>				
		<tr>
			<th scope="row"><label for="homePostNo">주소</label></th>
			<td colspan="3">
			<div class="input-group" >
				<input type="text" name="homePostNo" id="homePostNo" class="form-control input-sm" value="${vo.homePostNo }" style="width:200px;" placeholder="우편번호"/>
					<button class="btn btn-info btn-sm" type="button" onclick="goJusoPopup();">우편검색</button>
			</div>
			<div class="input-group" >
				<input type="text" name="homeAddr1" id="homeAddr1" class="form-control input-sm" value="${vo.homeAddr1 }" style="width:600px;" placeholder="기본주소"/>
			</div>
			<div class="input-group" >
				<input type="text" name="homeAddr2" id="homeAddr2" class="form-control input-sm" value="${vo.homeAddr2 }" style="width:600px;" placeholder="상세주소"/>
			</div>
			</td>
		</tr>
		<tr>
			<th scope="row"><label for="stdDivCd">훈련생구분</label></th>
			<td>
				<select name="stdDivCd" id="stdDivCd" class="form-control input-sm" style="width: 200px;">
					<c:forEach var="item" items="${stdDivCdList }">
						<option value="${item.codeCd }">${item.codeNm }</option>
					</c:forEach>
				</select>
			</td>
			<th scope="row"><label for="nonReguDivCd">비정규직구분</label></th>
			<td>
				<select name="nonReguDivCd" id="nonReguDivCd" class="form-control input-sm" style="width: 200px;">
					<c:forEach var="item" items="${nonReguDivCdList }">
						<option value="${item.codeCd }">${item.codeNm }</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<th scope="row"><label for="costCompNo">비용수급사업장번호</label></th>
			<td>
				<input type="text" name="costCompNo" id="costCompNo" class="form-control input-sm" placeholder="비용수급사업장번호" isNull="Y" value="${vo.costCompNo }"/>
			</td>
		</tr>
	</table>
	<table summary="<spring:message code="user.title.userinfo.manage"/>" class="table table-bordered">
		<colgroup>
			<col width="20%"/>
			<col width="80%"/>
		</colgroup>
		<tr>
			<th scope="row" class="top"><span style="color:red;">* </span><label for="compNm"><spring:message code="user.title.userinfo.auth"/></label></th>
			<td>
				<c:forEach var="item" items="${mngAuthList}">
				<c:set var="authGrpNm" value="${item.authGrpNm}"/>
				<c:forEach var="lang" items="${item.authGrpLangList}">
					<c:if test="${lang.langCd eq LOCALEKEY}">
						<c:set var="authGrpNm" value="${lang.authGrpNm}"/>
					</c:if>
				</c:forEach>
				<label style="font-weight:normal; float:left; margin-right:15px;"><input type="checkbox" onclick="doRemoveCheck(this);" name="mngUserAuth" value="${item.authGrpCd}" <c:if test="${fn:contains(userAuthGrpCd, item.authGrpCd)}">checked</c:if> id="authGrp_${item.authGrpCd}"/> ${authGrpNm}</label>
				</c:forEach>
			</td>
		</tr>
	</table>
	<div class="text-right" style="line-height:30px;">
		<c:if test="${gubun eq 'A' }">
			<button class="btn btn-primary btn-sm" onclick="addUser()" ><i class="fa fa-check"></i> <spring:message code="button.add"/></button>
		</c:if>
		<c:if test="${gubun eq 'E' }">
			<c:if test="${vo.userSts eq 'D'}">
			<a class="btn btn-primary btn-sm" htef="javascript:editUser2()" ><i class="fa fa-refresh"></i> <spring:message code="button.revitalize"/></a>
			</c:if>
			<c:if test="${vo.userSts ne 'D'}">
			<a class="btn btn-primary btn-sm" href="javascript:editUser()" ><i class="fa fa-check"></i> <spring:message code="button.add"/></a>
			</c:if>
			<a class="btn btn-info btn-sm" href="javascript:resetPass('NEW')" ><i class="fa fa-key"></i> <spring:message code="button.reset.password"/></a>
			<a class="btn btn-info btn-sm" href="javascript:resetPass('ID')" ><i class="fa fa-key"></i> <spring:message code="button.reset.password.fromid"/></a>
			<c:if test="${vo.userSts eq 'U' }">
			<a class="btn btn-warning btn-sm" href="javascript:stopUser()" ><i class="fa fa-power-off"></i> <spring:message code="button.stopuse"/></a>
			</c:if>
			<c:if test="${vo.userSts eq 'C' }">
			<a class="btn btn-warning btn-sm" href="javascript:startUser()" ><i class="fa fa-refresh"></i> <spring:message code="button.reuse"/></a>
			</c:if>
			<c:if test="${vo.userSts ne 'D' }">
			<a class="btn btn-warning btn-sm" href="javascript:joinOutUser()" ><i class="fa fa-power-off"></i> <spring:message code="button.leave.member"/></a>
			</c:if>
		</c:if>
		<a class="btn btn-default btn-sm" href="javascript:parent.modalBoxClose();" ><i class="fa fa-close"></i> <spring:message code="button.close"/></a>
	</div>
	</form>

<script type="text/javascript">
	var idCheck = "N";
	var emailCheck = "N";
	var originOrgCd = '${orgCd}';
	// 페이지 초기화
	$(document).ready(function() {
		initSetting();
	});
	
	function initSetting() {
		var gubun = '${gubun}';
		if(gubun == 'A') {
			$("#idChkBtn").show();
			$("#emailChkBtn").show(); 
		} else if(gubun == 'E') {
			emailCheck = "Y"
			idCheck = "Y"
		}
	}
	
	function idDupCheck() {
		var userId = $("#userId").val();
		if(userId == "") {
			alert("<spring:message code="user.message.userinfo.input.userid"/>");
			return;
		} else {
			$.getJSON( 
				cUrl("/mng/user/userInfo/userIdCheck"), 
				{ "userId" : userId},			// params
				function(data) {
					$("#idCheck").val(data.isUseable);
					if(data.isUseable == 'Y') {
						alert('<spring:message code="user.message.userinfo.userid.possible"/>');
						idCheck = "Y";
						$("#idChkBtn").hide();
					} else {
						alert('ERROR! \n<spring:message code="user.message.userinfo.userid.impossible"/>');
					}
				}
			);
		}
	}
	
	function resetIdChk() {
		idCheck = "N";
		$("#idChkBtn").show();
	}

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		$('#userInfoForm').attr("action" ,"/mng/user/userInfo/" + cmd);
		$('#userInfoForm').ajaxSubmit(processCallback);
	}
	
	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultVO) {
		alert(resultVO.message);
		if(resultVO.result >= 0) {
			// 정상 처리
			parent.listPageing(1);
			parent.modalBoxClose();
		} else {
			// 비정상 처리
		}
	}
	
	/**
	 * 사용자 추가
	 */
	function addUser() {
		if(!validate(document.userInfoForm)) return;
		if(idCheck == 'N') {
			alert("<spring:message code="user.message.userinfo.nodupchek"/>");
			return;
		}
		if(emailCheck == 'N') {
			alert("이메일 중복체크를 완료해 주십시오.");
			return;
		}
		if(isEmpty($("#userPass").val())) {
			alert("<spring:message code="user.message.userinfo.alert.input.password"/>");
			return;
		}
		if(isEmpty($("#userPassChk").val())) {
			alert("<spring:message code="user.message.userinfo.alert.input.password2"/>");
			return;
		}
		if($("#userPass").val() != $("#userPassChk").val()) {
			alert("<spring:message code="user.message.userinfo.notmatch.password"/>");
			return;
		}
	
		//-- 사용자 권한 셋팅
		var mngAuthGrp = "/"+$("#userInfoForm input[name='mngUserAuth']:checked").stringValues("/");
		if(mngAuthGrp == '/') mngAuthGrp = '';
		$("#mngAuthGrpCd").val(mngAuthGrp);
		
		if(!validateMobileNo()) {
			alert("휴대폰 번호 형식이 올바르지 않습니다.");
			return;
		}
		setMobileNo();
		setHomeNo();
		
		//권한 필수값으로
		if($("input[name=mngUserAuth]:checked").length == 0){
			alert("<spring:message code="user.message.login.alert.input.auth"/>");
			return;
		}
		setJuminNo();
		
		if($("#deptCd").val() == '') {
			alert("소속 기업을 선택해 주십시오.");
			return;
		}
		$("#deptNm").val($("#deptCd option:selected").text());
		
		process("addUser");	// cmd
	}
	
	/**
	 * 사용자 수정
	 */
	function editUser() {
		if(!validate(document.userInfoForm)) return;
	
		//-- 사용자 권한 셋팅
		var wwwAuthGrp = "";
		$("#userInfoForm input[name='wwwUserAuth']").each(function(i){
			if(this.checked) wwwAuthGrp += "/" + $(this).val();
		});
		if(wwwAuthGrp == '/') wwwAuthGrp = "";
		var mngAuthGrp = "";
		$("#userInfoForm input[name='mngUserAuth']").each(function(i){
			if(this.checked) mngAuthGrp += "/" + $(this).val();
		});
		if(mngAuthGrp == '/') mngAuthGrp = '';
		var admAuthGrp = "";
		$("#userInfoForm input[name='admUserAuth']").each(function(i){
			if(this.checked) admAuthGrp += "/" + $(this).val();
		});
		if(admAuthGrp == '/') admAuthGrp = '';
	
		$("#wwwAuthGrpCd").val(wwwAuthGrp);
		$("#mngAuthGrpCd").val(mngAuthGrp);
		$("#adminAuthGrpCd").val(admAuthGrp);
		
		if(!validateMobileNo()) {
			alert("휴대폰 번호 형식이 올바르지 않습니다.");
			return;
		}
		setMobileNo();
		setHomeNo();
		
		if(idCheck == 'N') {
			alert("<spring:message code="user.message.userinfo.nodupchek"/>");
			return;
		}
		if(emailCheck == 'N') {
			alert("이메일 중복체크를 완료해 주십시오.");
			return;
		}
		
		if($("#deptCd").val() == '') {
			alert("소속 기업을 선택해 주십시오.");
			return;
		}
		$("#deptNm").val($("#deptCd option:selected").text());
		
		//주민번호세팅
		setJuminNo();
		
		process("editUser");	// cmd
	}
	
	/**
	 * 사용자 활성화
	 */
	function editUser2() {
		if(!validate(document.userInfoForm)) return;
		$("#userSts").val("U"); //-- 사용자 활성화..
		//-- 사용자 권한 셋팅
		var wwwAuthGrp = "";
		$("#userInfoForm input[name='wwwUserAuth']").each(function(i){
			if(this.checked) wwwAuthGrp += "/" + $(this).val();
		});
		if(wwwAuthGrp == '/') wwwAuthGrp = "";
		var mngAuthGrp = "";
		$("#userInfoForm input[name='mngUserAuth']").each(function(i){
			if(this.checked) mngAuthGrp += "/" + $(this).val();
		});
		if(mngAuthGrp == '/') mngAuthGrp = '';
		var admAuthGrp = "";
		$("#userInfoForm input[name='admUserAuth']").each(function(i){
			if(this.checked) admAuthGrp += "/" + $(this).val();
		});
		if(admAuthGrp == '/') admAuthGrp = '';
		$("#wwwAuthGrpCd").val(wwwAuthGrp);
		$("#mngAuthGrpCd").val(mngAuthGrp);
		$("#adminAuthGrpCd").val(admAuthGrp);
		
		process("editUser");	// cmd
	}
	
	//-- 사용자 상태 정지
	function stopUser() {
		var userNo = $("#userNo").val();
		$.getJSON( 
			cUrl("/mng/user/userInfo/stopUser"), 
			{ "userNo" : userNo },			// params
			function(data) {
				alert(data.message);
				parent.listPageing(1);
				parent.modalBoxClose();
	//				if(data.result >= 0) {
	//					alert('<spring:message code="user.message.userinfo.nouse.success"/>');
	//					listPageing(1);
	//					parent.modalBoxClose();
	//				} else {
	//					alert('ERROR! \n <spring:message code="user.message.userinfo.nouse.failed"/>');
	//				}
			}
		);
	
	}
	
	//-- 사용자 상태 정지해제
	function startUser() {
		var userNo = $("#userNo").val();
		$.getJSON( 
			cUrl("/mng/user/userInfo/startUser"), 
			{ "userNo" : userNo },			// params
			function(data) {
				alert(data.message);
				parent.listPageing(1);
				parent.modalBoxClose();
	//				if(data.result >= 0) {
	//					alert('<spring:message code="user.message.userinfo.use.success"/>');
	//					listPageing(1);
	//					parent.modalBoxClose();
	//				} else {
	//					alert('ERROR! \n <spring:message code="user.message.userinfo.use.failed"/>');
	//				}
			}
		);
	
	}
	
	//-- 사용자 탈퇴 처리
	function joinOutUser() {
		var userNo = $("#userNo").val();
		var userId = $("#userId").val();
		$.getJSON( 
			cUrl("/mng/user/userInfo/joinOut"), 
			{"userNo" : userNo, "userId" : userId},			// params
			function(data) {
				alert(data.message);
				parent.listPageing(1);
				parent.modalBoxClose();
	//				if(data.result >= 0) {
	//					alert('<spring:message code="user.message.userinfo.leave.success"/>');
	//					listPageing(1);
	//					parent.modalBoxClose();
	//				} else {
	//					alert('ERROR! \n<spring:message code="user.message.userinfo.leave.failed"/>');
	//				}
			}
		);
	
	}
	
	function resetEmailChk() {
		emailCheck = "N";
		$("#emailChkBtn").show();
	}
	
	function dupEmailChk() {
		var email = $("#email").val();
		if(validateEmail(email)){
			$.getJSON( 
					"/home/user/emailCheck",
					{"email" : email },			// params
					function(data) {
						if(data.isUseable == 'Y') {
							emailCheck = "Y";
							$("#emailChkBtn").hide();
							alert("사용가능한 이메일 입니다.");
						} else {
							alert("중복된 이메일 입니다.");
						}
					}
				);	
		}
		else {
			alert("이메일 형식이 맞지 안습니다.");
			$("#email").focus();
		}
			
	}
	
	function validateEmail(email) {
		var emailRule = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
		if(!emailRule.test(email)) {            
            return false;
		}
		return true;
	}
	
	function setJuminNo() {
		var juminNo = $("#juminNo1").val() + $("#juminNo2").val();
		$("#juminNo").val(juminNo);
	}
	
	function validateMobileNo() {
		if($("#mobileNo1").val().length != 3) return false;
		if($("#mobileNo2").val().length != 4) return false;
		if($("#mobileNo3").val().length != 4) return false;
		return true;
	}
	
	function setMobileNo() {
		var mobileNo = $("#mobileNo1").val() + $("#mobileNo2").val() + $("#mobileNo3").val();
		$("#mobileNo").val(mobileNo);
	}
	
	function setHomeNo() {
		var homePhoneno = $("#phoneno1").val() + $("#phoneno2").val() + $("#phoneno3").val();
		$("#homePhoneno").val(homePhoneno);
	}
	
	function resetPass(str) {
		$.getJSON( 
			cUrl("/mng/user/userInfo/resetPass"), 
			{ "newUserPassConfirm": str, "userId" : "${vo.userId}", "userNo" : "${vo.userNo}"},
			function(data) {
				alert(data.messageDetail);
			}
		);
	}
	
	function doRemoveCheck(obj){
		$("input[name=mngUserAuth]").prop("checked", false);
		$(obj).prop("checked", true);
	}
	
	function goJusoPopup(){
		window.name="jusoPopup";
		// 주소검색을 수행할 팝업 페이지를 호출합니다.
		// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(http://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
		var pop = window.open("/mng/user/deptInfo/jusoDeptPopup","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 	
	}
	
	function jusoDeptCallBack(roadFullAddr,roadAddrPart1,addrDetail,roadAddrPart2,engAddr, jibunAddr, zipNo, admCd, rnMgtSn, bdMgtSn,detBdNmList,bdNm,bdKdcd,siNm,sggNm,emdNm,liNm,rn,udrtYn,buldMnnm,buldSlno,mtYn,lnbrMnnm,lnbrSlno,emdNo){
		$("#homePostNo").val(zipNo);
		$("#homeAddr1").val(roadAddrPart1);
		$("#homeAddr2").val(addrDetail);
	}	
</script>
