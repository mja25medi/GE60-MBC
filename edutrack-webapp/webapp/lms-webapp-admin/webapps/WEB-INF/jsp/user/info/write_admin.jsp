<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>

	<div style="float:left;width:100%;height:25px;">
		<span style="color:red;font-weight:bold;">* </span><spring:message code="common.message.input.required"/>
	</div>
	<form name="userInfoForm" id="userInfoForm" onsubmit="return false" method="POST">
	<input type="hidden" name="idCheck" id="idCheck" value="N" />
	<input type="hidden" name="userNo" id="userNo" value="${vo.userNo}"/>
	<input type="hidden" name="orgCd" id="orgCd" value="${vo.orgCd}"/>
	<input type="hidden" name="userSts" id="userSts" value="${vo.userSts}"/>
	<input type="hidden" name="adminAuthGrpCd" id="adminAuthGrpCd" value="${vo.adminAuthGrpCd}"/>
	<input type="hidden" name="wwwAuthGrpCd" id="wwwAuthGrpCd" value="${vo.wwwAuthGrpCd}"/>
	<input type="hidden" name="mngAuthGrpCd" id="mngAuthGrpCd" value="${vo.mngAuthGrpCd}"/>

	<table summary="<spring:message code="user.title.userinfo.manage"/>" class="table table-bordered">
		<colgroup>
			<col width="18%"/>
			<col width="32%"/>
			<col width="18%"/>
			<col width="32%"/>
		</colgroup>
		<c:choose>
		<c:when test="${gubun eq 'A'}">
		<tr>
			<th scope="row"><span style="color:red;">* </span><label for="userId"><spring:message code="user.title.userinfo.userid"/></label></th>
			<td colspan="3">
				<div>
					<div class="input-group" style="float:left; width: 200px;">
						<input type="text" name="userId" id="userId" maxlength="14" required="required" title="<spring:message code="user.title.userinfo.userid"/>" class="form-control input-sm"/> 
						<div class="input-group-btn">
							<a class="btn btn-info btn-sm" href="javascript:idDupCheck()"><spring:message code="button.userid.duplicate.check"/></a>
						</div>
					</div>
					<div style="float:left;margin-left:10px;line-height:30px;">
						<%-- <spring:message code="user.message.userinfo.userid.check"/> --%>
					</div>
				</div>
			</td>
		</tr>
		</c:when>
		<c:otherwise>
		<tr>
			<th scope="row"><span style="color:red;">* </span><label for="userId"><spring:message code="user.title.userinfo.userid"/></label></th>
			<td>
				${vo.userId}
				<input type="hidden" name="userId" id="userId" value="${vo.userId}"/>
			</td>
			<th scope="row"><spring:message code="user.title.userinfo.stats"/></th>
			<td><meditag:codename code="${vo.userSts}" category="USER_STS"/></td>
		</tr>
		</c:otherwise>
		</c:choose>
		<tr>
			<th scope="row"><span style="color:red;">* </span><label for="userNm"><spring:message code="user.title.userinfo.name"/></label></th>
			<td colspan="3">
				<c:choose>
					<c:when test="${gubun eq 'A'}">
						<input type="text" name="userNm" id="userNm" maxlength="50" required="required" title="<spring:message code="user.title.userinfo.name"/>" value="${vo.userNm}" class="form-control input-sm" style="width:200px"/>
					</c:when>
					<c:otherwise>
						${vo.userNm} <input type="hidden" name="userNm" id="userNm" value="${vo.userNm}"/>
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<c:if test="${gubun eq 'A'}">
		<tr>
			<th scope="row"><label for="userPass"><span style="color:red;">* </span><spring:message code="user.title.login.password"/></label></th>
			<td colspan="3">
				<input type="password" name="userPass" id="userPass" maxlength="14" required="required" title="<spring:message code="user.title.login.password"/>" class="form-control input-sm" style="width:200px;"/>
				<spring:message code="user.message.userinfo.password.check"/>
			</td>
		</tr>
		<tr>
			<th scope="row"><label for="mobileNo"><span style="color:red;">* </span><spring:message code="user.title.login.password2"/></label></th>
			<td colspan="3">
				<input type="password" name="userPassChk" id="userPassChk" maxlength="14" required="required" title="<spring:message code="user.title.login.password2"/>" class="form-control input-sm" style="width:200px;"/>
			</td>
		</tr>
		</c:if>
		<tr>
			<th scope="row"><label for="email"><span style="color:red;">* </span><spring:message code="user.title.userinfo.email"/></label></th>
			<td colspan="3">
				<input type="text" name="email" id="email" maxlength="100" required="required" value="${vo.email }" title="<spring:message code="user.title.userinfo.email"/>" class="form-control input-sm"/>
			</td>
		</tr>
		<tr>
			<th scope="row"><spring:message code="user.title.userinfo.message"/></th>
			<td colspan="3">
				<c:if test="${MSG_EMAIL eq 'Y' }">
				<label style="font-weight: normal; float:left; margin-right:30px;">
					<input type="checkbox" name="emailRecv" id="emailRecv" value="Y" <c:if test="${vo.emailRecv eq 'Y'}">checked</c:if> /><spring:message code="user.title.userinfo.receive.email"/>
				</label>
				</c:if>
				<c:if test="${MSG_SMS eq 'Y' }">
				<label style="font-weight: normal; float:left; margin-right:30px;">
					<input type="checkbox" name="smsRecv" id="smsRecv" value="Y" <c:if test="${vo.smsRecv eq 'Y'}">checked</c:if> /><spring:message code="user.title.userinfo.receive.sms"/>
				</label>
				</c:if>
				<c:if test="${MSG_NOTE eq 'Y' }">
				<input type="hidden" name="msgRecv" id="msgRecv" value="Y" />
				</c:if>
				</ul>
			</td>
		</tr>
		<tr>
			<th scope="row"><label for="homePhoneno"><spring:message code="user.title.userinfo.phoneno"/></label></th>
			<td>
				<input type="text" name="homePhoneno" id="homePhoneno" maxlength="" value="${vo.mobileNo }" title="<spring:message code="user.title.userinfo.phoneno"/>" class="form-control input-sm"/>
			</td>
			<th scope="row"><label for="mobileNo"><spring:message code="user.title.userinfo.mobileno"/></label></th>
			<td>
				<input type="text" name="mobileNo" id="mobileNo" maxlength="" value="${vo.homePhoneno }" title="<spring:message code="user.title.userinfo.mobileno"/>" class="form-control input-sm"/>
			</td>
		</tr>
	</table>
	<table summary="<spring:message code="user.title.userinfo.manage"/>" class="table table-bordered">
		<colgroup>
			<col width="18%"/>
			<col width="82%"/>
		</colgroup>
		<tr>
			<th scope="row" class="top"><label for="compNm"><spring:message code="user.title.userinfo.auth"/></label></th>
			<td>
				<c:forEach var="item" items="${admAuthList}">
				<c:set var="authGrpNm" value="${item.authGrpNm}"/>
				<c:forEach var="lang" items="${item.authGrpLangList}">
					<c:if test="${lang.langCd eq LOCALEKEY}">
						<c:set var="authGrpNm" value="${lang.authGrpNm}"/>
					</c:if>
				</c:forEach>
				<label style="font-weight:normal; float:left; margin-right:15px;"><input type="checkbox" name="admUserAuth" value="${item.authGrpCd}" <c:if test="${fn:contains(userAuthGrpCd, item.authGrpCd)}">checked</c:if> id="authGrp_${item.authGrpCd}"/> ${authGrpNm}</label>
				</c:forEach>
			</td>
		</tr>
	</table>
	<div class="text-right">
		<c:if test="${gubun eq 'A' }">
			<button class="btn btn-primary btn-sm" onclick="addUser()" ><spring:message code="button.add"/></button>
		</c:if>
		<c:if test="${gubun eq 'E' }">
			<c:if test="${userSts eq 'D'}">
			<a class="btn btn-primary btn-sm" htef="javascript:editUser2()" ><spring:message code="button.revitalize"/></a>
			</c:if>
			<c:if test="${userSts ne 'D'}">
			<a class="btn btn-primary btn-sm" href="javascript:editUser()" ><spring:message code="button.add"/></a>
			</c:if>
			<a class="btn btn-info btn-sm" href="javascript:resetPass('NEW')" ><spring:message code="button.reset.password"/></a>
			<a class="btn btn-info btn-sm" href="javascript:resetPass('ID')" ><spring:message code="button.reset.password.fromid"/></a>
			<c:if test="${userSts eq 'U' }">
			<a class="btn btn-warning btn-sm" href="javascript:stopUser()" ><spring:message code="button.stopuse"/></a>
			</c:if>
			<c:if test="${userSts eq 'C' }">
			<a class="btn btn-warning btn-sm" href="javascript:startUser()" ><spring:message code="button.reuse"/></a>
			</c:if>
			<c:if test="${userSts ne 'D' }">
			<a class="btn btn-warning btn-sm" href="javascript:joinOutUser()" ><spring:message code="button.leave.member"/></a>
			</c:if>
		</c:if>
		<a class="btn btn-default btn-sm" href="javascript:parent.modalBoxClose();" ><spring:message code="button.close"/></a>
	</div>
	</form>

<script type="text/javascript">

	var originOrgCd = '${orgCd}';
	// 페이지 초기화
	$(document).ready(function() {

	});

	function idDupCheck() {
		var userId = $("#userId").val();
		if(userId == "") {
			alert("<spring:message code="user.message.userinfo.input.userid"/>");
			return;
		} else {
			$.getJSON( cUrl("/adm/user/info/userIdCheck"),
					{"userId" : userId },			// params
					function(data) {
							$("#idCheck").val(data.isUseable);
							if(data.isUseable == 'Y') {
								alert('<spring:message code="user.message.userinfo.userid.possible"/>');
							} else {
								alert('ERROR! \n<spring:message code="user.message.userinfo.userid.impossible"/>');
							}
						}
					);
		}
	}


	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		$('#userInfoForm').attr("action", cmd);
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
		if($("#idCheck").val() == 'N') {
			alert("<spring:message code="user.message.userinfo.nodupchek"/>");
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
		var admAuthGrp = "/"+$("#userInfoForm input[name='admUserAuth']:checked").stringValues("/");
		if(admAuthGrp == '/') admAuthGrp = '';
		$("#adminAuthGrpCd").val(admAuthGrp);
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
		process("/adm/user/info/editUser.do");	// cmd
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
		process("/adm/user/info/editUser.do");	// cmd
	}

	/**
	 * 사용자 삭제
	 */
	function delUser() {
		process("delAdmin");	// cmd
	}

	//-- 사용자 상태 정지
	function stopUser() {
		var userNo = $("#userNo").val();
		$.getJSON( cUrl("/adm/user/info/stopUser"), {"userNo" : userNo },			// params
							function(data) {
				 	  			if(data.returnResult >= 0) {
									alert('<spring:message code="user.message.userinfo.nouse.success"/>');
									parent.listPageing(1);
									parent.modalBoxClose();
								} else {
									alert('ERROR! \n <spring:message code="user.message.userinfo.nouse.failed"/>');
							}
						});

	}

	//-- 사용자 상태 정지
	function startUser() {
		var userNo = $("#userNo").val();
		$.getJSON( cUrl("/adm/user/info/startUser"), {"userNo" : userNo },			// params
							function(data) {
				 	  			if(data.returnResult >= 0) {
									alert('<spring:message code="user.message.userinfo.use.success"/>');
									listPageing(1);
									parent.modalBoxClose();
								} else {
									alert('ERROR! \n <spring:message code="user.message.userinfo.use.failed"/>');
							}
						});

	}

	//-- 사용자 탈퇴 처리
	function joinOutUser() {
		var userNo = $("#userNo").val();
		$.getJSON( cUrl("/adm/user/info/joinOut"), {"userNo" : userNo },			// params
							function(data) {
				 	  			if(data.result >= 0) {
									alert('<spring:message code="user.message.userinfo.leave.success"/>');
									parent.listPageing(1);
									parent.modalBoxClose();
								} else {
									alert('ERROR! \n<spring:message code="user.message.userinfo.leave.failed"/>');
							}
						});

	}


	function resetPass(str) {
		$.getJSON( cUrl("/adm/user/info/resetPass"), 
				   {"newUserPassConfirm": str, "userId" : "${vo.userId}", "userNo" : "${vo.userNo}" },
				   function(data) {
						alert(data.messageDetail);
				   });
	}

</script>
