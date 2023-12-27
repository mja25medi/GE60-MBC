<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>

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
					<div class="text-right" style="line-height:30px;">
						<c:if test="${gubun eq 'E' }">
							<c:if test="${vo.userSts ne 'D'}">
							<a class="btn btn-primary btn-sm" href="javascript:editUser()" ><i class="fa fa-check"></i> <spring:message code="button.add"/></a>
							</c:if>
						</c:if>
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
		$('#userInfoForm').attr("action" , "/adm/user/info/" + cmd);
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
		process("editUser");	// cmd
	}
	
	function changePasswd() {
		var addContent  = "<iframe id='userPasswordFrame' name='userPasswordFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='no' src='/adm/user/info/editPassPop"
			+ "?'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(500, 340);
		modalBox.setTitle("<spring:message code="user.title.userinfo.change.password"/>");
		modalBox.show();
	}
</script>