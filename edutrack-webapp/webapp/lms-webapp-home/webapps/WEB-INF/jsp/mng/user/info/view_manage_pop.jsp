<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<style>
	#userDetailInfoTable th {background-color: #eee;}
	#permissionTable th {background-color: #eee;} 
</style>

	<%-- <div class="tabbox" style="width:100%;float:left;position: fixed; background-color:#FFFFFF; ">
		<ul class="nav nav-tabs">
  			<li class="active"><a href="#"><spring:message code="user.title.userinfo.user.info.tab"/></a></li>
  			<c:if test="${teacherYn eq 'Y'}">
  				<li><a href="<c:url value="/mng/tch/info/viewTchPop?userNo=${vo.userNo}"/>"><spring:message code="user.title.userinfo.teacher.into"/></a></li>
  			</c:if>
		</ul>
	</div> --%>
	<div id="infoArea" style="float:left;width:100%; margin-top:50px;">
		<table summary="<spring:message code="user.title.userinfo.user.info"/>" id="userDetailInfoTable" class="table table-bordered wordbreak">
			<colgroup>
				<col width="20%"/>
				<col width="auto"/>
			</colgroup>
			<tr>
				<th scope="row"><spring:message code="user.title.userinfo.userid"/> (<spring:message code="user.title.userinfo.userno"/>)</th>
				<td >${vo.userId} (${vo.userNo})</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="user.title.userinfo.name"/></th>
				<td >${vo.userNm}</td>
			</tr>
			<tr class="option_view">
				<th scope="row"><spring:message code="user.title.userinfo.email"/></th>
				<td>${vo.email}</td>
			</tr>
			
			<tr class="option_view">
				<th scope="row"><spring:message code="user.title.userinfo.mobileno"/></th>
				<td>${vo.mobileNo}</td>
			</tr>
			
			<tr class="option_view">
				<th scope="row"><spring:message code="user.title.userinfo.phoneno"/></th>
				<td>${vo.homePhoneno}</td>
			</tr>
			
			<tr class="option_view">
				<th scope="row"><spring:message code="user.title.userinfo.message"/></th>
				<td>
					<ul style="list-style-type: none;padding-left:0px;">
					<c:if test="${vo.emailRecv eq 'Y' }">
					<li style="float:left;min-width:150px;"><spring:message code="user.title.userinfo.receive.email"/></li>
					</c:if>
					<c:if test="${vo.smsRecv eq 'Y' }">
					<li style="float:left;min-width:150px;"><spring:message code="user.title.userinfo.receive.sms"/></li>
					</c:if>
					</ul>
				</td>
			</tr>

		</table>
		<table summary="<spring:message code="user.title.userinfo.permissions"/>" id="permissionTable" class="table table-bordered">
			<colgroup>
				<col width="20%"/>
				<col width="80%"/>
			</colgroup>
			<tr>
				<th scope="row" class="top"><spring:message code="user.title.userinfo.permissions"/></th>
				<td class="top" style="padding:5px 5px 5px 5px;">
					<ul style="width:100%">
					 <c:forEach var="item" items="${wwwAuthList}">
						<c:if test="${item.authGrpCd ne 'MEMBER' && item.authGrpCd ne 'GUEST' }">
						<c:set var="authGrpNm" value="${item.authGrpNm}"/>
						<c:forEach var="lang" items="${item.authGrpLangList}">
							<c:if test="${lang.langCd eq LOCALEKEY}">
								<c:set var="authGrpNm" value="${lang.authGrpNm}"/>
							</c:if>
						</c:forEach>

						<c:if test="${fn:contains(userAuthGrpCd, item.authGrpCd)}"><li style="float:left;min-width:150px;">${authGrpNm}</li></c:if>
						</c:if>
					</c:forEach>

					<c:forEach var="item" items="${mngAuthList}">
						<c:set var="authGrpNm" value="${item.authGrpNm}"/>
						<c:forEach var="lang" items="${item.authGrpLangList}">
							<c:if test="${lang.langCd eq LOCALEKEY}">
								<c:set var="authGrpNm" value="${lang.authGrpNm}"/>
							</c:if>
						</c:forEach>

						<c:if test="${fn:contains(userAuthGrpCd, item.authGrpCd)}"><li style="float:left;min-width:150px;">${authGrpNm}</li></c:if>
					</c:forEach>
					</ul>
				</td>
			</tr>
		</table>
	<%-- 	<div class="text-right">
			<a href="javascript:userInfoEdit()" class="btn btn-primary btn-sm"><spring:message code="button.edit"/> </a>
			<button class="btn btn-default" onclick="parent.modalBoxClose()"><spring:message code="button.close"/></button>
		</div> --%>
	</div>

<script type="text/javascript">
// 	var systemPopBox = PopupBox("", "", "", "width=600,height=550,style=normal,bgcolor=,modal=yes,resizable=no,move=yes,titlebar=yes,position=center,top=0,left=0","set2");
// 	var popBox = systemPopBox;

	var originOrgCd = '${vo.orgCd}';
	// 페이지 초기화
	$(document).ready(function() {

	});

	function userInfoEdit() {
		parent.modalBox.setTitle("<spring:message code="user.title.userinfo.edit"/>");
		document.location.href = cUrl("/mng/user/userInfo/editManagePop")+"?userNo=${vo.userNo}&orgCd=${vo.orgCd}";
	}
</script>
