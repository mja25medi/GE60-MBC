<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>

<section class="content">
	<div class="row">
		<div class="col-md-12">
			<div class="box">
				<div class="box-body">
					<div class="callout callout-warning" style="margin-bottom: 10!important;">
						<span style="color:red;font-weight:bold;">* </span><spring:message code="common.message.input.required"/>
					</div>
					<form name="userInfoForm" id="userInfoForm" onsubmit="return false" method="POST" >
					<input type="hidden" name="idCheck" id="idCheck" value="N" />
					<input type="hidden" name="userNo" id="userNo" value="${vo.userNo}"/>
					<input type="hidden" name="orgCd" id="orgCd" value="${vo.orgCd}"/>
					<input type="hidden" name="userSts" id="userSts" value="${vo.userSts}"/>
					<input type="hidden" name="adminAuthGrpCd" id="adminAuthGrpCd" value="${vo.adminAuthGrpCd}"/>
					<input type="hidden" name="wwwAuthGrpCd" id="wwwAuthGrpCd" value="${vo.wwwAuthGrpCd}"/>
					<input type="hidden" name="mngAuthGrpCd" id="mngAuthGrpCd" value="${vo.mngAuthGrpCd}"/>
					<table summary="<spring:message code="user.title.userinfo.manage"/>" class="table table-bordered">
						<colgroup>
							<col width="20%"/>
							<col width="80%"/>
						</colgroup>
						<tr>
							<th scope="row"><span style="color:red;">* </span><label for="userId"><spring:message code="user.title.userinfo.userid"/></label></th>
							<td>
								${vo.userId}
								<input type="hidden" name="userId" id="userId" value="${vo.userId}"/>
							</td>
						</tr>
						<tr>
							<th scope="row"><spring:message code="user.title.userinfo.stats"/></th>
							<td>
							<meditag:codename code="${vo.userSts}" category="USER_STS"/>
							</td>
						</tr>
						<tr>
							<th scope="row"><span style="color:red;">* </span><label for="userNm"><spring:message code="user.title.userinfo.name"/></label></th>
							<td>
								<c:choose>
									<c:when test="${gubun eq 'A'}">
										<input type="text" name="userNm" id="userNm" maxlength="50" required="required" title="<spring:message code="user.title.userinfo.name"/>" value="${vo.userNm}" class="form-control input-sm " style="width:200px"/>
									</c:when>
									<c:otherwise>
										${vo.userNm} <input type="hidden" name="userNm" id="userNm" value="${vo.userNm}"/>
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
						<c:if test="${vo.snsDiv ne 'SSO'}">
						<tr>
							<th scope="row"><span style="color: red;">* </span><label for="password"><spring:message code="user.title.login.password" /></label></th>
								<td colspan="3">
									<a class="btn btn-info btn-xs" href="javascript:changePasswd();">
									<spring:message code="user.title.userinfo.change.password" /></a></td>
						</tr>
						</c:if>	
						<tr>
							<th scope="row"><label for="email"><span style="color:red;">* </span><spring:message code="user.title.userinfo.email"/></label></th>
							<td>
								<input type="text" name="email" id="email" maxlength="100" required="required" title="<spring:message code="user.title.userinfo.email"/>" class="form-control input-sm " value="${vo.email}"/>
							</td>
						</tr>
						<tr>
							<th scope="row"><spring:message code="user.title.userinfo.message"/></th>
							<td>
								<c:if test="${MSG_EMAIL eq 'Y' }">
								<label style="font-weight: normal; float:left; margin-right:30px;">
									<input type="checkbox" name="emailRecv" id="emailRecv" value="Y" <c:if test="${vo.emailRecv eq 'Y'}">checked</c:if> /><spring:message code="user.title.userinfo.receive.email"/>
								</label>
								</c:if>
								<c:if test="${MSG_SMS eq 'Y' }">
				<!-- 				<label style="font-weight: normal; float:left; margin-right:30px;"> -->
				<%-- 					<input type="checkbox" name="smsRecv" id="smsRecv" value="Y" <c:if test="${vo.smsRecv eq 'Y'}">checked</c:if> /><spring:message code="user.title.userinfo.receive.sms"/> --%>
				<!-- 				</label> -->
								</c:if>
								<c:if test="${MSG_NOTE eq 'Y' }">
				<!-- 				<input type="hidden" name="msgRecv" id="msgRecv" value="Y" /> -->
								</c:if>
								</ul>
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="homePhoneno"><spring:message code="user.title.userinfo.phoneno"/></label></th>
							<td>
								<input type="text" name="homePhoneno" id="homePhoneno" maxlength="" title="<spring:message code="user.title.userinfo.phoneno"/>" class="form-control input-sm " value="${vo.homePhoneno}"/>
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="mobileNo"><spring:message code="user.title.userinfo.mobileno"/></label></th>
							<td>
								<input type="text" name="mobileNo" id="mobileNo" maxlength="" title="<spring:message code="user.title.userinfo.mobileno"/>" class="form-control input-sm " value="${vo.mobileNo}"/>
							</td>
						</tr>
					</table>
					<table summary="<spring:message code="user.title.userinfo.manage"/>" class="table table-bordered" style="display: none;">
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
								<label style="font-weight:normal; margin-right:10px;"><input type="checkbox" onclick="doRemoveCheck(this)" name="mngUserAuth" value="${item.authGrpCd}" <c:if test="${fn:contains(userAuthGrpCd, item.authGrpCd)}">checked</c:if> id="authGrp_${item.authGrpCd}"/> ${authGrpNm}</label>
								</c:forEach>
							</td>
						</tr>
					</table>
					<div class="text-right" style="line-height:30px;">
						<c:if test="${gubun eq 'A' }">
							<button class="btn btn-primary btn-sm" onclick="addUser()" ><i class="fa fa-check"></i> <spring:message code="button.add"/></button>
						</c:if>
						<c:if test="${gubun eq 'E' }">
				<%-- 			<c:if test="${vo.userSts eq 'D'}"> --%>
				<%-- 			<a class="btn btn-primary btn-sm" htef="javascript:editUser2()" ><i class="fa fa-refresh"></i> <spring:message code="button.revitalize"/></a> --%>
				<%-- 			</c:if> --%>
							<c:if test="${vo.userSts ne 'D'}">
							<a class="btn btn-primary btn-sm" href="javascript:editUser()" ><i class="fa fa-check"></i> <spring:message code="button.add"/></a>
							</c:if>
				<%-- 			<a class="btn btn-info btn-sm" href="javascript:resetPass('NEW')" ><i class="fa fa-key"></i> <spring:message code="button.reset.password"/></a> --%>
<%-- 							<a class="btn btn-info btn-sm" href="javascript:resetPass('ID')" ><i class="fa fa-key"></i> <spring:message code="button.reset.password.fromid"/></a> --%>
<%-- 							<c:if test="${vo.userSts eq 'U' }"> --%>
<%-- 							<a class="btn btn-warning btn-sm" href="javascript:stopUser()" ><i class="fa fa-power-off"></i> <spring:message code="button.stopuse"/></a> --%>
				<%-- 			</c:if> --%>
				<%-- 			<c:if test="${vo.userSts eq 'C' }"> --%>
				<%-- 			<a class="btn btn-warning btn-sm" href="javascript:startUser()" ><i class="fa fa-refresh"></i> <spring:message code="button.reuse"/></a> --%>
				<%-- 			</c:if> --%>
				<%-- 			<c:if test="${vo.userSts ne 'D' }"> --%>
				<%-- 			<a class="btn btn-warning btn-sm" href="javascript:joinOutUser()" ><i class="fa fa-power-off"></i> <spring:message code="button.leave.member"/></a> --%>
				<%-- 			</c:if> --%>
						</c:if>
				<%-- 		<a class="btn btn-default btn-sm" href="javascript:parent.modalBoxClose();" ><i class="fa fa-close"></i> <spring:message code="button.close"/></a> --%>
					</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	</section>

<script type="text/javascript">

	var originOrgCd = '${orgCd}';
	var modalBox = null;
	// 페이지 초기화
	$(document).ready(function() {
		modalBox = new $M.ModalDialog({
			"modalid" : "modal1"
		});
	});

	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		$('#userInfoForm').attr("action" , "/mng/user/userInfo/" + cmd);
		$('#userInfoForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultVO) {
		alert(resultVO.message);
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
		process("editUser");	// cmd
	}
	
	function doRemoveCheck(obj){
		$("input[name=mngUserAuth]").prop("checked", false);
		$(obj).prop("checked", true);
	}
	
	function changePasswd() {
		var addContent  = "<iframe id='userPasswordFrame' name='userPasswordFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='no' src='/mng/user/userInfo/editPassPop"
			+ "?'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(500, 340);
		modalBox.setTitle("<spring:message code="user.title.userinfo.change.password"/>");
		modalBox.show();
	}
</script>