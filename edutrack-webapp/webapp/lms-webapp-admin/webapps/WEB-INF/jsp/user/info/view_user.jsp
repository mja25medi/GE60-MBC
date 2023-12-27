<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>

	<div class="tabbox" style="width:100%;float:left;position: fixed; background-color:#FFFFFF; ">
		<ul class="nav nav-tabs">
  			<li class="active"><a href="#"><spring:message code="user.title.userinfo.user.info.tab"/></a></li>
  			<%-- <li><a href="/UserInfoAdmin.do?cmd=viewUserEnroll&userInfoDTO.userNo=${vo.userNo}"><spring:message code="user.title.userinfo.study.history.tab"/></a></li> --%>
  			<c:if test="${tutorYn eq 'Y' || teacherYn eq 'Y'}">
  			<%-- <li><a href="/UserInfoAdmin.do?cmd=viewTchIng&userInfoDTO.userNo=${vo.userNo}"><spring:message code="user.title.userinfo.tch.history.tab"/></a></li> --%>
  			<li><a href="/adm/user/info/viewTchIngPop?userNo=${vo.userNo}"><spring:message code="user.title.userinfo.tch.history.tab"/></a></li>
  			</c:if>
		</ul>
	</div>
	<div id="infoArea" style="float:left;width:100%; margin-top:50px;">
		<table summary="<spring:message code="user.title.userinfo.user.info"/>" class="table table-bordered wordbreak">
			<colgroup>
				<col width="20%"/>
				<col width="30%"/>
				<col width="20%"/>
				<col width="30%"/>
			</colgroup>
			<tr>
				<th scope="row"><spring:message code="user.title.userinfo.userid"/> (<spring:message code="user.title.userinfo.userno"/>)</th>
				<td >${vo.userId} (${vo.userNo})</td>
				<th scope="row"><spring:message code="user.title.userinfo.name"/></th>
				<td >${vo.userNm}</td>
			</tr>
<c:forEach items="${userInfoCfgList}" var="cfgItem" varStatus="status">

<c:if test="${cfgItem.fieldNm eq 'PHOTO'  && cfgItem.useYn eq 'Y' }">

			<tr class="option_view">
				<th scope="row"><spring:message code="board.title.editor.photo"/></th>
				<td colspan="3">
					<c:if test="${not empty userInfoDTO.photoFileSn && userInfoDTO.photoFileSn > 0}">
					<img src="<c:url value="/app/file/thumb/${vo.photoFileSn}"/>" style="height:85px;width: 130px;" alt="${item.userNm}" class="media-object"/>
					</c:if>
				</td>
			</tr>

</c:if>

<c:if test="${cfgItem.fieldNm eq 'BIRTH' && cfgItem.useYn eq 'Y' }">
			<tr class="option_view">
				<th scope="row"><spring:message code="user.title.userinfo.birth"/></th>
				<td colspan="3"><meditag:dateformat type="1" delimeter="-" property="${vo.birth}"/></td>
			</tr>
</c:if>

<c:if test="${cfgItem.fieldNm eq 'USERNMKANA' && cfgItem.useYn eq 'Y' }">
			<tr class="option_view">
				<th scope="row"><spring:message code="user.title.userinfo.name.kana"/></th>
				<td colspan="3">${vo.userNmGana}</td>
			</tr>
</c:if>

<c:if test="${cfgItem.fieldNm eq 'SEX' && cfgItem.useYn eq 'Y' }">
			<tr class="option_view">
				<th scope="row"><spring:message code="user.title.userinfo.sex"/></th>
				<td colspan="3">
					<c:if test="${vo.sexCd eq 'M' }"><spring:message code="user.title.userinfo.sex_m"/></c:if>
					<c:if test="${vo.sexCd eq 'F' }"><spring:message code="user.title.userinfo.sex_f"/></c:if>
				</td>
			</tr>
</c:if>

<c:if test="${cfgItem.fieldNm eq 'USERNMENG' && cfgItem.useYn eq 'Y' }">
			<tr class="option_view">
				<th scope="row"><spring:message code="user.title.userinfo.name.eng"/></th>
				<td colspan="3">${vo.userNmEng}</td>
			</tr>
</c:if>

<c:if test="${cfgItem.fieldNm eq 'AREA' && cfgItem.useYn eq 'Y' }">
			<tr class="option_view">
				<th scope="row"><spring:message code="user.title.area"/></th>
				<td colspan="3"><meditag:orgcodename code="${vo.areaCd}" category="AREA_CD" orgCd="${vo.orgCd }"/></td>
			</tr>
</c:if>

<c:if test="${cfgItem.fieldNm eq 'USERDIV' && cfgItem.useYn eq 'Y' }">
			<tr class="option_view">
				<th scope="row"><spring:message code="common.title.type"/></th>
				<td colspan="3"><meditag:orgcodename code="${vo.userDivCd}" category="USER_DIV_CD" orgCd="${vo.orgCd }"/></td>
			</tr>
</c:if>

<c:if test="${cfgItem.fieldNm eq 'EMAIL' && cfgItem.useYn eq 'Y' }">
			<tr class="option_view">
				<th scope="row"><spring:message code="user.title.userinfo.email"/></th>
				<td colspan="3">${vo.email}</td>
			</tr>
</c:if>

<c:if test="${cfgItem.fieldNm eq 'DEPT' && cfgItem.useYn eq 'Y' }">
			<tr class="option_view">
				<th scope="row"><spring:message code="user.title.userinfo.deptname"/></th>
				<td colspan="3">${vo.deptNm}</td>
			</tr>
</c:if>

<c:if test="${cfgItem.fieldNm eq 'MOBILENO' && cfgItem.useYn eq 'Y' }">
			<tr class="option_view">
				<th scope="row"><spring:message code="user.title.userinfo.mobileno"/></th>
				<td colspan="3">${vo.mobileNo}</td>
			</tr>
</c:if>

<c:if test="${cfgItem.fieldNm eq 'PHONENO' && cfgItem.useYn eq 'Y' }">
			<tr class="option_view">
				<th scope="row"><spring:message code="user.title.userinfo.phoneno"/></th>
				<td colspan="3">${vo.homePhoneno}</td>
			</tr>
</c:if>

<c:if test="${cfgItem.fieldNm eq 'FAXNO' && cfgItem.useYn eq 'Y' }">
			<tr class="option_view">
				<th scope="row"><spring:message code="user.title.userinfo.fax"/></th>
				<td colspan="3">${vo.compFaxNo}</td>
			</tr>
</c:if>

<c:if test="${cfgItem.fieldNm eq 'COMPPHONE' && cfgItem.useYn eq 'Y' }">
			<tr class="option_view">
				<th scope="row"><spring:message code="user.title.userinfo.dept.phoneno"/></th>
				<td colspan="3">${vo.compPhoneno}</td>
			</tr>
</c:if>

<c:if test="${cfgItem.fieldNm eq 'MESSAGE' && cfgItem.useYn eq 'Y' }">
			<tr class="option_view">
				<th scope="row"><spring:message code="user.title.userinfo.message"/></th>
				<td colspan="3">
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
</c:if>

<c:if test="${cfgItem.fieldNm eq 'ETCPHONE' && cfgItem.useYn eq 'Y' }">
			<tr class="option_view">
				<th scope="row"><spring:message code="user.title.userinfo.dept.etc"/></th>
				<td colspan="3">${vo.etcPhoneno}</td>
			</tr>
</c:if>

<c:if test="${cfgItem.fieldNm eq 'COMPADDR' && cfgItem.useYn eq 'Y' }">
			<tr class="option_view">
				<th scope="row"><spring:message code="user.title.userinfo.dept.address"/></th>
				<td colspan="3">${vo.compAddr1}</td>
			</tr>
</c:if>

<c:if test="${cfgItem.fieldNm eq 'HOMEADDR' && cfgItem.useYn eq 'Y' }">
			<tr class="option_view">
				<th scope="row"><spring:message code="user.title.userinfo.home.address"/></th>
				<td colspan="3">${vo.homeAddr1}</td>
			</tr>
</c:if>

<c:if test="${cfgItem.fieldNm eq 'DISABLILITY' && cfgItem.useYn eq 'Y' }">
			<tr class="option_view">
				<th scope="row"><spring:message code="user.title.userinfo.disablility"/></th>
				<td colspan="3">
					<c:if test="${vo.disablilityYn eq 'Y' }"><spring:message code="user.title.userinfo.disablility_y"/></c:if>
					<c:if test="${vo.disablilityYn eq 'N' }"><spring:message code="user.title.userinfo.disablility_n"/></c:if>
				</td>
			</tr>
</c:if>

<c:if test="${cfgItem.fieldNm eq 'INTEREST' && cfgItem.useYn eq 'Y' }">
			<tr class="option_view">
				<th scope="row"><spring:message code="user.title.userinfo.interestField"/></th>
				<td colspan="3"><meditag:orgcodename code="${vo.interestFieldCd}" category="INTEREST_FIELD_CD" orgCd="${vo.orgCd }"/></td>
			</tr>
</c:if>

<c:if test="${cfgItem.fieldNm eq 'MEMO' && cfgItem.useYn eq 'Y' }">
			<tr class="option_view">
				<th scope="row"><spring:message code="user.title.userinfo.memo"/></th>
				<td colspan="3">${vo.memo}</td>
			</tr>
</c:if>
</c:forEach>

		</table>
		<table summary="<spring:message code="user.title.userinfo.permissions"/>" class="table table-bordered">
			<colgroup>
				<col width="20%"/>
				<col width="80%"/>
			</colgroup>
			<tr>
				<th scope="row" class="top"><spring:message code="user.title.userinfo.permissions"/></th>
				<td class="top" style="padding:5px 5px 5px 5px;">
					<ul style="width:100%">
<%--
					<c:forEach var="item" items="${userAuthList}">
						<li style="float:left;min-width:150px;">${item.authGrpNm}</li>
					</c:forEach>
 --%>
 					<li style="float:left;min-width:150px;"><spring:message code="user.title.userinfo.member"/></li>
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
		<div class="text-right">
			<a href="javascript:userInfoEdit()" class="btn btn-primary btn-sm"><spring:message code="button.edit"/> </a>
			<button class="btn btn-default" onclick="parent.modalBoxClose()"><spring:message code="button.close"/></button>
		</div>
	</div>


<script type="text/javascript">
	var systemPopBox = PopupBox("", "", "", "width=600,height=550,style=normal,bgcolor=,modal=yes,resizable=no,move=yes,titlebar=yes,position=center,top=0,left=0","set2");
	var popBox = systemPopBox;

	var originOrgCd = '${vo.orgCd}';
	// 페이지 초기화
	$(document).ready(function() {

	});

	function userInfoEdit() {
		parent.modalBox.setTitle("<spring:message code="user.title.userinfo.edit"/>");
		document.location.href = cUrl("/adm/user/info/editFormAdminPop")+"?userNo=${vo.userNo}&orgCd=${vo.orgCd}";
	}
</script>
