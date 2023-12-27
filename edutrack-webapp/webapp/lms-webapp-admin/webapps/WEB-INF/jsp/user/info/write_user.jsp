<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>

<c:set var="usePhoto" value=""/>
<c:set var="useUserid" value=""/>
<c:set var="useUsernm" value=""/>
<c:set var="useBirth" value=""/>
<c:set var="useUsernmkana" value=""/>
<c:set var="useSex" value=""/>
<c:set var="useUsernmeng" value=""/>
<c:set var="useArea" value=""/>
<c:set var="useUserdiv" value=""/>
<c:set var="useEmail" value=""/>
<c:set var="useDept" value=""/>
<c:set var="useMobileno" value=""/>
<c:set var="usePhoneno" value=""/>
<c:set var="useFaxno" value=""/>
<c:set var="useCompphone" value=""/>
<c:set var="useMessage" value=""/>
<c:set var="useEtcphone" value=""/>
<c:set var="useCompaddr" value=""/>
<c:set var="useHomeaddr" value=""/>
<c:set var="useDisablility" value=""/>
<c:set var="useInterest" value=""/>
<c:set var="useMemo" value=""/>
<c:set var="requiredPhoto" value=""/>
<c:set var="requiredUserid" value=""/>
<c:set var="requiredUsernm" value=""/>
<c:set var="requiredBirth" value=""/>
<c:set var="requiredUsernmkana" value=""/>
<c:set var="requiredSex" value=""/>
<c:set var="requiredUsernmeng" value=""/>
<c:set var="requiredArea" value=""/>
<c:set var="requiredUserdiv" value=""/>
<c:set var="requiredEmail" value=""/>
<c:set var="requiredDept" value=""/>
<c:set var="requiredMobileno" value=""/>
<c:set var="requiredPhoneno" value=""/>
<c:set var="requiredFaxno" value=""/>
<c:set var="requiredCompphone" value=""/>
<c:set var="requiredMessage" value=""/>
<c:set var="requiredEtcphone" value=""/>
<c:set var="requiredCompaddr" value=""/>
<c:set var="requiredHomeaddr" value=""/>
<c:set var="requiredDisablility" value=""/>
<c:set var="requiredInterest" value=""/>
<c:set var="requiredMemo" value=""/>

<div style="float:left;width:100%;height:25px;">
	<span style="color:red;font-weight:bold;">* </span><spring:message code="common.message.input.required"/>
</div>
<form name="userInfoForm" id="userInfoForm" onsubmit="return false" method="POST">
<input type="hidden" name="idCheck" id="idCheck" value="N" />
<input type="hidden" name="userNo" id="userNo" value="${vo.userNo}"/>
<input type="hidden" name="userSts" id="userSts" value="${vo.userSts}"/>
<input type="hidden" name="adminAuthGrpCd" id="adminAuthGrpCd" value="${vo.adminAuthGrpCd}"/>
<input type="hidden" name="wwwAuthGrpCd" id="wwwAuthGrpCd" value="${vo.wwwAuthGrpCd}"/>
<input type="hidden" name="mngAuthGrpCd" id="mngAuthGrpCd" value="${vo.mngAuthGrpCd}"/>
<input type="hidden" name="email"  id="email" value="${vo.email}"/>
<input type="hidden" name="photoFileSn" id="photoFileSn" value="${vo.photoFileSn}"/>
<input type="hidden" name="orgCd"  id="orgCd" value="${vo.orgCd}"/>
<div id="userInfoArea">
	<table summary="<spring:message code="user.title.userinfo.manage"/>" class="table table-bordered">
		<colgroup>
			<col width="18%"/>
			<col width="32%"/>
			<col width="18%"/>
			<col width="32%"/>
		</colgroup>
<c:forEach items="${userInfoCfgList}" var="cfgItem" varStatus="status">
	<c:if test="${cfgItem.fieldNm eq 'USERID' }">
		<c:choose>
		<c:when test="${gubun eq 'A'}">
		<tr>
			<th scope="row"><span style="color:red;">* </span><label for="userId"><spring:message code="user.title.userinfo.userid"/></label></th>
			<td colspan="3">
				<div>
					<div class="input-group" style="width:300px;float:left;">
						<input type="text" name="userId" id="userId" required="required" title="<spring:message code="user.title.userinfo.userid"/>" class="form-control input-sm" onkeydown="resetIdCheck();"/> 
						<span class="input-group input-group-btn">
        					<button class="btn btn-info btn-sm" type="button" onclick="idDupCheck()"><spring:message code="button.userid.duplicate.check"/></button>
      					</span>
					</div>
					<div style="float:left;margin-left:10px;">
						<c:if test="${mbrIdType eq 'EMAIL'}">
						&nbsp;<spring:message code="user.message.userinfo.userid.check.email"/>
						</c:if>
						<c:if test="${mbrIdType ne 'EMAIL'}">
						&nbsp;<spring:message code="user.message.userinfo.userid.check.char"/>
						</c:if>
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

		<c:if test="${gubun eq 'A'}">
		<tr>
			<th scope="row"><label for="userPass"><span style="color:red;">* </span><spring:message code="user.title.login.password"/></label></th>
			<td>
				<input type="password" name="userPass" id="userPass" required="required" title="" class="from-control input-sm"/>
				<spring:message code="user.message.userinfo.password.check"/>
			</td>
			<th scope="row"><label for="userPassChk"><span style="color:red;">* </span><spring:message code="user.title.login.password2"/></label></th>
			<td>
				<input type="password" name="userPassChk" id="userPassChk" required="required" class="form-control input-sm"/>
			</td>
		</tr>
		</c:if>
	</c:if>
	<c:if test="${cfgItem.fieldNm eq 'PHOTO' }">
		<c:set var="usePhoto" value="${cfgItem.useYn }"/>
		<c:set var="requiredPhoto" value="${cfgItem.requiredYn }"/>
		<c:if test="${cfgItem.requiredYn eq 'Y'}"><c:set var="required" value="" /></c:if>
		<c:if test="${cfgItem.requiredYn eq 'N'}"><c:set var="required" value="required='required'" /></c:if>
		<tr <c:if test="${cfgItem.useYn eq 'N'}">style="display:none;"</c:if>>
			<th scope="row"><c:if test="${cfgItem.requiredYn eq 'Y'}"><span style="color:red;">* </span></c:if><spring:message code="board.title.editor.photo"/></th>
			<td colspan="3">
				<div class="upload">
					<div class="upload_inbox">
						<a href="javascript:uploderclick('usrPhotouploader');" class="btn btn-primary btn-xs"><spring:message code="button.select.file"/></a>
						<input type="file" class="file" name="usrPhotouploader" id="usrPhotouploader" multiple style="display:none" accept="image/*" /><%-- 첨부파일 버튼 --%>
						<div id="usrPhotoprogress" class="progress">
						<div class="progress-bar progress-bar-success"></div>
						</div>
					</div>
					<div id="usrPhotofiles" class="multi_inbox"></div>
				</div>				
			</td>
		</tr>
	</c:if>
	<c:if test="${cfgItem.fieldNm eq 'USERNM' }">
		<c:set var="useUsernm" value="${cfgItem.useYn }"/>
		<c:set var="requiredUsernm" value="${cfgItem.requiredYn }"/>
		<c:if test="${cfgItem.requiredYn eq 'N'}"><c:set var="required" value="" /></c:if>
		<c:if test="${cfgItem.requiredYn eq 'Y'}"><c:set var="required" value="required='required'" /></c:if>
		<tr <c:if test="${cfgItem.useYn eq 'N'}">style="display:none;"</c:if>>
			<th scope="row"><c:if test="${cfgItem.requiredYn eq 'Y'}"><span style="color:red;">* </span></c:if><label for="userNm"><spring:message code="user.title.userinfo.name"/></label></th>
			<td colspan="3">
				<c:choose>
					<c:when test="${gubun eq 'A'}">
						<input type="text" name="userNm" id="userNm" ${required} title="<spring:message code="user.title.userinfo.name"/>" class="form-control input-sm" style="width:300px;"/>
					</c:when>
					<c:otherwise>
						${vo.userNm} <input type="hidden" name="userNm" id="userNm" value="${vo.userNm}"/>
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
	</c:if>
	<c:if test="${cfgItem.fieldNm eq 'BIRTH' }">
		<c:set var="useBirth" value="${cfgItem.useYn }"/>
		<c:set var="requiredBirth" value="${cfgItem.requiredYn }"/>
		<c:if test="${cfgItem.requiredYn eq 'Y'}"><c:set var="required" value="" /></c:if>
		<c:if test="${cfgItem.requiredYn eq 'N'}"><c:set var="required" value="required='required'" /></c:if>
		<tr <c:if test="${cfgItem.useYn eq 'N'}">style="display:none;"</c:if>>
			<th scope="row"><c:if test="${cfgItem.requiredYn eq 'Y'}"><span style="color:red;">* </span></c:if><label for="birth"><spring:message code="user.title.userinfo.birth"/></label></th>
			<td colspan="3">
				<input type="hidden" name="birth" id="birth" />
				<c:set var="birthYear" value="${fn:substring(userInfoDTO.birth,0,4)}"/>
				<c:set var="birthMonth" value="${fn:substring(userInfoDTO.birth,4,6)}"/>
				<c:set var="birthDay" value="${fn:substring(userInfoDTO.birth,6,8)}"/>
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
		</tr>
	</c:if>

	<c:if test="${cfgItem.fieldNm eq 'USERNMKANA' }">
		<c:set var="useUsernmkana" value="${cfgItem.useYn }"/>
		<c:set var="requiredUsernmkana" value="${cfgItem.requiredYn }"/>
		<c:if test="${cfgItem.requiredYn eq 'N'}"><c:set var="required" value="" /></c:if>
		<c:if test="${cfgItem.requiredYn eq 'Y'}"><c:set var="required" value="required='required'" /></c:if>
		<tr <c:if test="${cfgItem.useYn eq 'N'}">style="display:none;"</c:if>>
			<th scope="row"><c:if test="${cfgItem.requiredYn eq 'Y'}"><span style="color:red;">* </span></c:if><label for="userNmGana"><spring:message code="user.title.userinfo.name.kana"/></label></th>
			<td colspan="3">
				<input type="text" name="userNmGana" id="userNmGana" ${required} title="<spring:message code="user.title.userinfo.name.kana"/>" class="form-control input-sm" value=""${vo.userNmGana}" style="width:300px;"/>
			</td>
		</tr>
	</c:if>

	<c:if test="${cfgItem.fieldNm eq 'SEX' }">
		<c:set var="useSex" value="${cfgItem.useYn }"/>
		<c:set var="requiredSex" value="${cfgItem.requiredYn }"/>
		<c:if test="${cfgItem.requiredYn eq 'N'}"><c:set var="required" value="" /></c:if>
		<c:if test="${cfgItem.requiredYn eq 'Y'}"><c:set var="required" value="required='required'" /></c:if>
		<tr <c:if test="${cfgItem.useYn eq 'N'}">style="display:none;"</c:if>>
			<th scope="row"><c:if test="${cfgItem.requiredYn eq 'Y'}"><span style="color:red;">* </span></c:if><spring:message code="user.title.userinfo.sex"/></label></th>
			<td colspan="3">
				<label style="font-weight: normal;"><input type="radio" name="sexCd" id="sexCdM" value="M" style="border:0" <c:if test="${vo.sexCd eq 'M' }"> checked</c:if> /> <spring:message code="user.title.userinfo.sex_m"/></label>
				<label style="font-weight: normal; margin-left:10px;"><input type="radio" name="sexCd" id="sexCdF" value="F" style="border:0" <c:if test="${vo.sexCd eq 'F' }"> checked</c:if> /> <spring:message code="user.title.userinfo.sex_f"/></label>
			</td>
		</tr>
	</c:if>

	<c:if test="${cfgItem.fieldNm eq 'USERNMENG' }">
		<c:set var="useUsernmeng" value="${cfgItem.useYn }"/>
		<c:set var="requiredUsernmeng" value="${cfgItem.requiredYn }"/>
		<c:if test="${cfgItem.requiredYn eq 'N'}"><c:set var="required" value="" /></c:if>
		<c:if test="${cfgItem.requiredYn eq 'Y'}"><c:set var="required" value="required='required'" /></c:if>
		<tr <c:if test="${cfgItem.useYn eq 'N'}">style="display:none;"</c:if>>
			<th scope="row"><c:if test="${cfgItem.requiredYn eq 'Y'}"><span style="color:red;">* </span></c:if><label for="userNmEng"><spring:message code="user.title.userinfo.name.eng"/></label></th>
			<td colspan="3">
				<input type="text" name="userNmEng" id="userNmEng" ${required} title="<spring:message code="user.title.userinfo.name.eng"/>" class="form-control input-sm" style="width:300px;" value="${vo.userNmEng}" />
			</td>
		</tr>
	</c:if>

	<c:if test="${cfgItem.fieldNm eq 'AREA' }">
		<c:set var="useArea" value="${cfgItem.useYn }"/>
		<c:set var="requiredArea" value="${cfgItem.requiredYn }"/>
		<c:if test="${cfgItem.requiredYn eq 'N'}"><c:set var="required" value="" /></c:if>
		<c:if test="${cfgItem.requiredYn eq 'Y'}"><c:set var="required" value="required='required'" /></c:if>
		<tr <c:if test="${cfgItem.useYn eq 'N'}">style="display:none;"</c:if>>
			<th scope="row"><c:if test="${cfgItem.requiredYn eq 'Y'}"><span style="color:red;">* </span></c:if><label for="areaCd"><spring:message code="user.title.area"/></label></th>
			<td colspan="3">
				<select name="areaCd" id="areaCd" class="form-control input-sm" style="width:300px;" onchange="setTitle(this)">
					<option value=""><spring:message code="board.title.editor.table.select.line"/></option>
				<c:forEach items="${areaCode}" var="item">
					<c:set var="codeName" value="${item.codeNm}"/>
					<c:forEach var="lang" items="${item.codeLangList}">
						<c:if test="${lang.langCd eq LOCALEKEY }"><c:set var="codeName" value="${lang.codeNm}"/></c:if>
					</c:forEach>
					<option value="${item.codeCd}" <c:if test="${item.codeCd eq userInfoDTO.areaCd }"> selected</c:if>>${codeName}</option>
				</c:forEach>
				</select>
			</td>
		</tr>
	</c:if>

	<c:if test="${cfgItem.fieldNm eq 'USERDIV' }">
		<c:set var="useUserdiv" value="${cfgItem.useYn }"/>
		<c:set var="requiredUserdiv" value="${cfgItem.requiredYn }"/>
		<c:if test="${cfgItem.requiredYn eq 'N'}"><c:set var="required" value="" /></c:if>
		<c:if test="${cfgItem.requiredYn eq 'Y'}"><c:set var="required" value="required='required'" /></c:if>
		<tr <c:if test="${cfgItem.useYn eq 'N'}">style="display:none;"</c:if>>
			<th scope="row"><c:if test="${cfgItem.requiredYn eq 'Y'}"><span style="color:red;">* </span></c:if><label for="userDivCd"> <spring:message code="common.title.type"/></label></th>
			<td colspan="3">
				<ul style="list-style-type: none;padding-left:0px;">
					<li style="float:left;margin-right:10px;">
						<select name="userDivCd" id="userDivCd" class="form-control input-sm" style="width:300px;" onchange="setTitle(this)">
							<option value=""><spring:message code="board.title.editor.table.select.line"/></option>
						<c:forEach items="${codeList}" var="item">
							<c:set var="codeName" value="${item.codeNm}"/>
							<c:forEach var="lang" items="${item.codeLangList}">
								<c:if test="${lang.langCd eq LOCALEKEY }"><c:set var="codeName" value="${lang.codeNm}"/></c:if>
							</c:forEach>
							<option value="${item.codeCd}" <c:if test="${item.codeCd eq userInfoDTO.userDivCd }"> selected</c:if>>${codeName}</option>
						</c:forEach>
					</select>
					</li>
				</ul>
			</td>
		</tr>
	</c:if>

	<c:if test="${cfgItem.fieldNm eq 'EMAIL' }">
		<c:set var="useEmail" value="${cfgItem.useYn }"/>
		<c:set var="requiredEmail" value="${cfgItem.requiredYn }"/>
		<c:if test="${cfgItem.requiredYn eq 'N'}"><c:set var="required" value="" /></c:if>
		<c:if test="${cfgItem.requiredYn eq 'Y'}"><c:set var="required" value="required='required'" /></c:if>
		<tr <c:if test="${cfgItem.useYn eq 'N'}">style="display:none;"</c:if>>
			<th scope="row"><c:if test="${cfgItem.requiredYn eq 'Y'}"><span style="color:red;">* </span></c:if><label for="email"><spring:message code="user.title.userinfo.email"/></label></th>
			<td colspan="3">
				<input type="text" name="email_id" id="email_id" class="form-control input-sm" value="${fn:split(vo.email,'@')[0]}" style="width:120px;float:left" maxlength="100" />
				<span style="float:left">@</span>
				<input type="text" name="email_domain_text" id="email_domain_text" class="form-control input-sm" value="${fn:split(vo.email,'@')[1]}" style="width:120px;float:left" maxlength="100"  readonly />
				<select name="email_domain_sel" id="email_domain_sel" class="form-control input-sm" style="width:120px;float:left" onchange="emailSet()">
				<c:set var="emailDomain" value="${fn:split(vo.email,'@')[1] }"/>
				<c:forEach items="${emailCode}" var="item">
					<c:set var="codeName" value="${item.codeNm}"/>
					<c:forEach var="lang" items="${item.codeLangList}">
						<c:if test="${lang.langCd eq LOCALEKEY }"><c:set var="codeName" value="${lang.codeNm}"/></c:if>
					</c:forEach>
					<option value="${item.codeCd}" <c:if test="${item.codeCd eq fn:split(emailDomain,'.')[0] }"> selected</c:if>>${codeName}</option>
				</c:forEach>
				</select>
			</td>
		</tr>
	</c:if>

	<c:if test="${cfgItem.fieldNm eq 'DEPT' }">
		<c:set var="useDept" value="${cfgItem.useYn }"/>
		<c:set var="requiredDept" value="${cfgItem.requiredYn }"/>
		<c:if test="${cfgItem.requiredYn eq 'N'}"><c:set var="required" value="" /></c:if>
		<c:if test="${cfgItem.requiredYn eq 'Y'}"><c:set var="required" value="required='required'" /></c:if>
		<tr <c:if test="${cfgItem.useYn eq 'N'}">style="display:none;"</c:if>>
			<th scope="row"><c:if test="${cfgItem.requiredYn eq 'Y'}"><span style="color:red;">* </span></c:if><label for="deptNm"><spring:message code="user.title.userinfo.deptname"/></label></th>
			<td colspan="3">
				<div class="input-group" style="width:300px;float:left;">
					<input type="text" name="deptNm" id="deptNm" maxlength="50" ${required} title="<spring:message code="user.title.userinfo.deptname"/>" class="form-control input-sm" style="width:" value="${vo.deptNm}" /> 
					<input type="hidden" name="deptCd" id="deptCd" />
					<span class="input-group input-group-btn">
						<a href="#_none" onclick="javascript:deptSearch();" class="btn btn-info btn-sm"><spring:message code="button.search"/></a>
					</span>
				</div>
			</td>
		</tr>
	</c:if>

	<c:if test="${cfgItem.fieldNm eq 'MOBILENO' }">
		<c:set var="useMobileno" value="${cfgItem.useYn }"/>
		<c:set var="requiredMobileno" value="${cfgItem.requiredYn }"/>
		<c:if test="${cfgItem.requiredYn eq 'Y'}"><c:set var="required" value="required='required'" /></c:if>
		<c:if test="${cfgItem.requiredYn eq 'N'}"><c:set var="required" value="" /></c:if>
		<tr <c:if test="${cfgItem.useYn eq 'N'}">style="display:none;"</c:if>>
			<th scope="row"><c:if test="${cfgItem.requiredYn eq 'Y'}"><span style="color:red;">* </span></c:if><label for="mobileNo"><spring:message code="user.title.userinfo.mobileno"/></label></th>
			<td colspan="3">
				<input type="text" name="mobileNo" id="mobileNo" maxlength="14" ${required} title="<spring:message code="user.title.userinfo.mobileno"/>" class="form-control input-sm inputSpecial" onkeydown="return onlyTelNo(this,event)" style="width:300px;" value="${vo.mobileNo}"/>
			</td>
		</tr>
	</c:if>

	<c:if test="${cfgItem.fieldNm eq 'PHONENO' }">
		<c:set var="usePhoneno" value="${cfgItem.useYn }"/>
		<c:set var="requiredPhoneno" value="${cfgItem.requiredYn }"/>
		<c:if test="${cfgItem.requiredYn eq 'N'}"><c:set var="required" value="" /></c:if>
		<c:if test="${cfgItem.requiredYn eq 'Y'}"><c:set var="required" value="required='required'" /></c:if>
		<tr <c:if test="${cfgItem.useYn eq 'N'}">style="display:none;"</c:if>>
			<th scope="row"><c:if test="${cfgItem.requiredYn eq 'Y'}"><span style="color:red;">* </span></c:if><label for="homePhoneno"><spring:message code="user.title.userinfo.phoneno"/></label></th>
			<td colspan="3">
				<input type="text" name="homePhoneno" id="homePhoneno" maxlength="14"  ${required} title="<spring:message code="user.title.userinfo.phoneno"/>" class="form-control input-sm inputSpecial" onkeydown="return onlyTelNo(this,event)" style="width:300px;" value="${vo.homePhoneno}"/>
			</td>
		</tr>
	</c:if>

	<c:if test="${cfgItem.fieldNm eq 'FAXNO' }">
		<c:set var="useFaxno" value="${cfgItem.useYn }"/>
		<c:set var="requiredFaxno" value="${cfgItem.requiredYn }"/>
		<c:if test="${cfgItem.requiredYn eq 'N'}"><c:set var="required" value="" /></c:if>
		<c:if test="${cfgItem.requiredYn eq 'Y'}"><c:set var="required" value="required='required'" /></c:if>
		<tr <c:if test="${cfgItem.useYn eq 'N'}">style="display:none;"</c:if>>
			<th scope="row"><c:if test="${cfgItem.requiredYn eq 'Y'}"><span style="color:red;">* </span></c:if><label for="compFaxNo"><spring:message code="user.title.userinfo.fax"/></label></th>
			<td colspan="3">
				<input type="text" name="compFaxNo" id="compFaxNo" maxlength="14"  ${required} title="<spring:message code="user.title.userinfo.fax"/>" class="form-contol input-sm inputSpecial" onkeydown="return onlyTelNo(this,event)" style="width:300px;" value="${vo.compFaxNo}"/>
			</td>
		</tr>
	</c:if>

	<c:if test="${cfgItem.fieldNm eq 'COMPPHONE' }">
		<c:set var="useCompphone" value="${cfgItem.useYn }"/>
		<c:set var="requiredCompphone" value="${cfgItem.requiredYn }"/>
		<c:if test="${cfgItem.requiredYn eq 'N'}"><c:set var="required" value="" /></c:if>
		<c:if test="${cfgItem.requiredYn eq 'Y'}"><c:set var="required" value="required='required'" /></c:if>
		<tr <c:if test="${cfgItem.useYn eq 'N'}">style="display:none;"</c:if>>
			<th scope="row"><c:if test="${cfgItem.requiredYn eq 'Y'}"><span style="color:red;">* </span></c:if><label for="compPhoneno"><spring:message code="user.title.userinfo.dept.phoneno"/></label></th>
			<td colspan="3">
				<input type="text" name="compPhoneno" id="compPhoneno" maxlength="14"  ${required} title="<spring:message code="user.title.userinfo.dept.phoneno"/>" class="form-contol input-sm inputSpecial" onkeydown="return onlyTelNo(this,event)" style="width:300px;" value="${vo.compPhoneno}"/>			
			</td>
		</tr>
	</c:if>

	<c:if test="${cfgItem.fieldNm eq 'MESSAGE' }">
		<c:set var="useMessage" value="${cfgItem.useYn }"/>
		<c:set var="requiredMessage" value="${cfgItem.requiredYn }"/>
		<c:if test="${cfgItem.requiredYn eq 'Y'}"><c:set var="required" value="" /></c:if>
		<c:if test="${cfgItem.requiredYn eq 'N'}"><c:set var="required" value="required='required'" /></c:if>
		<tr height="33" <c:if test="${cfgItem.useYn eq 'N'}">style="display:none;"</c:if>>
			<th scope="row"><c:if test="${cfgItem.requiredYn eq 'Y'}"><span style="color:red;">* </span></c:if><spring:message code="user.title.userinfo.message"/></th>
			<td colspan="3">
				<c:if test="${MSG_EMAIL eq 'Y' }">
				<label style="font-weight: normal;margin-right: 10px;">
					<input type="checkbox" name="emailRecv" id="emailRecv" value="Y" <c:if test="${vo.emailRecv eq 'Y'}">checked</c:if> /> <spring:message code="user.title.userinfo.receive.email"/>
				</label>
				</c:if>
				<c:if test="${MSG_SMS eq 'Y' }">
				<label style="font-weight: normal;margin-right: 10px;">
					<input type="checkbox" name="smsRecv" id="smsRecv" value="Y" <c:if test="${vo.smsRecv eq 'Y'}">checked</c:if> /> <spring:message code="user.title.userinfo.receive.sms"/>
				</label>
				</c:if>
				<c:if test="${MSG_NOTE eq 'Y' }">
				<input type="hidden" name="msgRecv" value="Y"/>
				</c:if>
			</td>
		</tr>
	</c:if>

	<c:if test="${cfgItem.fieldNm eq 'ETCPHONE' }">
		<c:set var="useEtcphone" value="${cfgItem.useYn }"/>
		<c:set var="requiredEtcphone" value="${cfgItem.requiredYn }"/>
		<c:if test="${cfgItem.requiredYn eq 'N'}"><c:set var="required" value="" /></c:if>
		<c:if test="${cfgItem.requiredYn eq 'Y'}"><c:set var="required" value="required='required'" /></c:if>
		<tr <c:if test="${cfgItem.useYn eq 'N'}">style="display:none;"</c:if>>
			<th scope="row"><c:if test="${cfgItem.requiredYn eq 'Y'}"><span style="color:red;">* </span></c:if><label for="etcPhoneno"><spring:message code="user.title.userinfo.dept.etc"/></label></th>
			<td colspan="3">
				<input type="text" name="etcPhoneno" id="etcPhoneno" maxlength="14"  ${required} title="<spring:message code="user.title.userinfo.dept.etc"/>" class="form-control input-sm inputSpecial" onkeydown="return onlyTelNo(this,event)" style="width:300px;" value="${vo.etcPhoneno}"/>			
			</td>
		</tr>

	</c:if>

	<c:if test="${cfgItem.fieldNm eq 'COMPADDR' }">
		<c:set var="useCompaddr" value="${cfgItem.useYn }"/>
		<c:set var="requiredCompaddr" value="${cfgItem.requiredYn }"/>
		<c:if test="${cfgItem.requiredYn eq 'N'}"><c:set var="required" value="" /></c:if>
		<c:if test="${cfgItem.requiredYn eq 'Y'}"><c:set var="required" value="required='required'" /></c:if>
		<tr <c:if test="${cfgItem.useYn eq 'N'}">style="display:none;"</c:if>>
			<th scope="row"><c:if test="${cfgItem.requiredYn eq 'Y'}"><span style="color:red;">* </span></c:if><label for="compAddr1"><spring:message code="user.title.userinfo.dept.address"/></label></th>
			<td colspan="3">
				<input type="text" name="compAddr1" id="compAddr1" maxlength="250"  ${required} title="<spring:message code="user.title.userinfo.dept.address"/>" class="form-control input-sm" value="${vo.compAddr1}"/>
				<p><spring:message code="user.message.telno.addr.info"/></p>
			</td>
		</tr>

	</c:if>

	<c:if test="${cfgItem.fieldNm eq 'HOMEADDR' }">
		<c:set var="useHomeaddr" value="${cfgItem.useYn }"/>
		<c:set var="requiredHomeaddr" value="${cfgItem.requiredYn }"/>
		<c:if test="${cfgItem.requiredYn eq 'N'}"><c:set var="required" value="" /></c:if>
		<c:if test="${cfgItem.requiredYn eq 'Y'}"><c:set var="required" value="required='required'" /></c:if>
		<tr <c:if test="${cfgItem.useYn eq 'N'}">style="display:none;"</c:if>>
			<th scope="row"><c:if test="${cfgItem.requiredYn eq 'Y'}"><span style="color:red;">* </span></c:if><label for="homeAddr1"><spring:message code="user.title.userinfo.home.address"/></label></th>
			<td colspan="3">
				<input type="text" name="homeAddr1" id="homeAddr1" maxlength="250"  ${required} title="<spring:message code="user.title.address"/>" class="form-control input-sm" value="${vo.homeAddr1}"/>
				<spring:message code="user.message.telno.addr.info"/>
			</td>
		</tr>
	</c:if>

	<c:if test="${cfgItem.fieldNm eq 'DISABLILITY' }">
		<c:set var="useDisablility" value="${cfgItem.useYn }"/>
		<c:set var="requiredDisablility" value="${cfgItem.requiredYn }"/>
		<c:if test="${cfgItem.requiredYn eq 'N'}"><c:set var="required" value="" /></c:if>
		<c:if test="${cfgItem.requiredYn eq 'Y'}"><c:set var="required" value="required='required'" /></c:if>
		<tr <c:if test="${cfgItem.useYn eq 'N'}">style="display:none;"</c:if>>
			<th scope="row"><c:if test="${cfgItem.requiredYn eq 'Y'}"><span style="color:red;">* </span></c:if><label for="disablilityYn"><spring:message code="user.title.userinfo.disablility"/></label></th>
			<td colspan="3">
				<label style="font-weight: normal;"><input type="radio" name="disablilityYn" id="disablilityYnY" value="Y" style="border:0" <c:if test="${vo.disablilityYn eq 'Y' }"> checked</c:if> /> <spring:message code="user.title.userinfo.disablility_y"/></label>
				<label style="font-weight: normal; margin-left:10px;"><input type="radio" name="disablilityYn" id="disablilityYnN" value="N" style="border:0" <c:if test="${vo.disablilityYn eq 'N' }"> checked</c:if> /> <spring:message code="user.title.userinfo.disablility_n"/></label>
			</td>
		</tr>
	</c:if>

	<c:if test="${cfgItem.fieldNm eq 'INTEREST' }">
		<c:set var="useInterest" value="${cfgItem.useYn }"/>
		<c:set var="requiredInterest" value="${cfgItem.requiredYn }"/>
		<c:if test="${cfgItem.requiredYn eq 'N'}"><c:set var="required" value="" /></c:if>
		<c:if test="${cfgItem.requiredYn eq 'Y'}"><c:set var="required" value="required='required'" /></c:if>
		<tr <c:if test="${cfgItem.useYn eq 'N'}">style="display:none;"</c:if>>
			<th scope="row"><c:if test="${cfgItem.requiredYn eq 'Y'}"><span style="color:red;">* </span></c:if><label for="interestField"><spring:message code="user.title.userinfo.interestField"/></label></th>
			<td colspan="3">
				<ul style="list-style-type: none;padding-left:0px;">
					<li style="float:left;margin-right:10px;">
						<select name="interestFieldCd" id="interestFieldCd" ${required} class="form-control input-sm" style="width:270px;" onchange="setTitle(this)">
							<option value=""><spring:message code="board.title.editor.table.select.line"/></option>
						<c:forEach items="${interestCode}" var="item">
							<c:set var="codeName" value="${item.codeNm}"/>
							<c:forEach var="lang" items="${item.codeLangList}">
								<c:if test="${lang.langCd eq LOCALEKEY }"><c:set var="codeName" value="${lang.codeNm}"/></c:if>
							</c:forEach>
							<option value="${item.codeCd}" <c:if test="${item.codeCd eq userInfoDTO.interestFieldCd }"> selected</c:if>>${codeName}</option>
						</c:forEach>
						</select>
					</li>
					<li style="float:left; width:300px">
						<input type="text" name="interestField" id="interestField" maxlength="100" ${required} title="<spring:message code="user.title.userinfo.interestField"/>"  class="form-control input-sm" value="${vo.interestField}" style="display:none"/>
					</li>
				</ul>
			</td>
		</tr>
	</c:if>

	<c:if test="${cfgItem.fieldNm eq 'MEMO' }">
		<c:set var="useMemo" value="${cfgItem.useYn }"/>
		<c:set var="requiredMemo" value="${cfgItem.requiredYn }"/>
		<c:if test="${cfgItem.requiredYn eq 'N'}"><c:set var="required" value="" /></c:if>
		<c:if test="${cfgItem.requiredYn eq 'Y'}"><c:set var="required" value="required='required'" /></c:if>
		<tr <c:if test="${cfgItem.useYn eq 'N'}">style="display:none;"</c:if>>
			<th scope="row"><c:if test="${cfgItem.requiredYn eq 'Y'}"><span style="color:red;">* </span></c:if><label for="memo"><spring:message code="user.title.userinfo.memo"/></label></th>
			<td colspan="3">
				<textarea name="meno" id="memo" maxlength="1000" ${required} title="<spring:message code="user.title.userinfo.memo"/>" class="form-control input-sm">${vo.memo}</textarea>
			</td>
		</tr>
	</c:if>
</c:forEach>
	</table><br/>

	<table summary="<spring:message code="user.title.userinfo.manage"/>" class="table table-bordered">
		<colgroup>
			<col width="18%"/>
			<col width="82%"/>
		</colgroup>
		<tr>
			<th class="top"><label for="compNm"><spring:message code="user.title.userinfo.auth"/></label></th>
			<td>
				<span style="margin-right:10px;"><spring:message code="user.title.userinfo.member"/></span>
				<c:forEach var="item" items="${wwwAuthList}">
					<c:if test="${item.authGrpCd ne 'MEMBER' && item.authGrpCd ne 'GUEST' }">
					<c:set var="authGrpNm" value="${item.authGrpNm}"/>
					<c:forEach var="lang" items="${item.authGrpLangList}">
						<c:if test="${lang.langCd eq LOCALEKEY}">
							<c:set var="authGrpNm" value="${lang.authGrpNm}"/>
						</c:if>
					</c:forEach>
				<label style="font-weight:normal; margin-right:10px;"><input type="checkbox" name="wwwUserAuth" value="${item.authGrpCd}" <c:if test="${fn:contains(userAuthGrpCd, item.authGrpCd)}">checked</c:if>  id="authGrp_${item.authGrpCd}"/> ${authGrpNm}</label>
					</c:if>
				</c:forEach>
				<c:forEach var="item" items="${mngAuthList}">
					<c:set var="authGrpNm" value="${item.authGrpNm}"/>
					<c:forEach var="lang" items="${item.authGrpLangList}">
						<c:if test="${lang.langCd eq LOCALEKEY}">
							<c:set var="authGrpNm" value="${lang.authGrpNm}"/>
						</c:if>
					</c:forEach>
				<label style="font-weight:normal; margin-right:10px;"><input type="checkbox" name="mngUserAuth" value="${item.authGrpCd}" <c:if test="${fn:contains(userAuthGrpCd, item.authGrpCd)}">checked</c:if> id="authGrp_${item.authGrpCd}"/> ${authGrpNm}</label>
				</c:forEach>
			</td>
		</tr>
	</table>
	<div class="text-right" style="margin-top:10px;">
		<c:if test="${gubun eq 'A'}">
			<button class="btn btn-primary" onclick="addUser()"><spring:message code="button.add"/></button>
		</c:if>
		<c:if test="${gubun eq 'E'}">
			<c:if test="${vo.userSts eq 'D'}">
			<button class="btn btn-primary" onclick="editUser2()"><spring:message code="button.revitalize"/></button>
			</c:if>
			<c:if test="${vo.userSts ne 'D'}">
			<button class="btn btn-primary" onclick="editUser()"><spring:message code="button.edit"/></button>
			</c:if>
			<button class="btn btn-info" onclick="resetPass('NEW')"><spring:message code="button.reset.password"/></button>
			<button class="btn btn-info" onclick="resetPass('ID')"><spring:message code="button.reset.password.fromid"/></button>
			<c:if test="${vo.userSts eq 'U' }">
			<button class="btn btn-warning" onclick="stopUser()"><spring:message code="button.stopuse"/></button>
			</c:if>
			<c:if test="${vo.userSts eq 'C' }">
			<button class="btn btn-warning" onclick="startUser()"><spring:message code="button.reuse"/></button>
			</c:if>
			<c:if test="${vo.userSts ne 'D' }">
			<button class="btn btn-warning" onclick="joinOutUser()"><spring:message code="button.leave.member"/></button>
			</c:if>
		</c:if>
		<button class="btn btn-default" onclick="parent.modalBoxClose()"><spring:message code="button.close"/></button>
	</div>
</div>

<div id="userDeptArea">
</div>
</form>

<script type="text/javascript">

	var originOrgCd = '${vo.orgCd}';
	// 페이지 초기화
	$(document).ready(function() {
		// 이미지 파일 첨부 바인딩 및 초기화


		$('.inputNumber').inputNumber();	// 숫자만 입력되도록 설정.
		$('.inputSpecial').inputSpecial();  // 특수키가 입력이 들어오묜 false return;

		usrPhotoFile = new $M.JqueryFileUpload({
							"varName"			: "usrPhotoFile",
							"files" 			: $.parseJSON('${vo.photoFileJson}'),
							"uploaderId"		: "usrPhotouploader",
							"fileListId"		: "usrPhotofiles",
							"progressId"		: "usrPhotoprogress",
							"maxcount"			: 1,
							"previewImage"		: true,
							"uploadSetting"	: {
								'formData'		: { 'repository': 'USR_PHOTO',
													'organization' : "${USER_ORGCD}",
													'type'		: 'usrPhoto' }
							}
						});
		checkBirthDay();
		emailSet();

		<c:if test="${useArea eq 'Y' }">setTitle(document.getElementById("areaCd"));</c:if>
		<c:if test="${useUserdiv eq 'Y' }">setTitle(document.getElementById("userDivCd"));</c:if>
		<c:if test="${useInterest eq 'Y' }">setTitle(document.getElementById("interestFieldCd"));</c:if>
	});

	function uploderclick(str) {
		$("#"+str).click();
	}

	/*id 검증*/
	function validateId(){
		var userId = $("#userId").val();
		if(userId == "") {
			alert("<spring:message code="user.message.userinfo.input.userid"/>");
			return;
		}

		<c:if test="${mbrIdType eq 'EMAIL'}">
		//-- 이메일로 아이디 입력하도록 변경함.
		// 2015.06.11 jamfam
		if(!emailCheck(userId)){
			return false;
		}
		</c:if>
		<c:if test="${mbrIdType ne 'EMAIL'}">
		var count = 0;  //한글 및 특수 문자 검증
		var num =0;     //숫자 포함 검증
		var chr = 0;    //영문자 포함 검증

		////////////////////////////////////////////////////////////
		////////////////// id 길이 체크 ////////////////////////////
		////////////////////////////////////////////////////////////
		if((userId.length < 4) || (userId.length > 15) ){
			alert("<spring:message code="user.message.userinfo.validation.alert.userid.length"/>");
			$("#userId").val("");
			$("#userId").focus();
			return false;
		}


		////////////////////////////////////////////////////////
		//////////// 특수문자 검사  ////////////////////////////
		////////////////////////////////////////////////////////
		for (i=0;i<userId.length;i++){
			ls_one_char = userId.charAt(i);
			if(ls_one_char.search(/[0-9|a-z|A-Z]/) == -1) {
				count++;
			}//end of if
		} // end of for

		if(count!=0) {
		  	alert("<spring:message code="user.message.userinfo.validation.alert.userid.digit"/>");
			$("#userId").val("");
			$("#userId").focus();
			return false;
		} //특수문자가 발견될경우 return;

		/////////////////////////////////////////////////////////
		//////////////// 영문자 숫자 혼용 검증 //////////////////
		/////////////////////////////////////////////////////////
		for (i=0;i<userId.length;i++){
			ls_one_char = userId.charAt(i);
		  	if(ls_one_char.search(/[0-9]/) == -1) {
				num++;
			}//end of if
			if(ls_one_char.search(/[a-z|A-Z]/) == -1) {
				chr++;
			}
		} // end of for

		if(num == 0 || chr == 0) {
			alert("<spring:message code="user.message.userinfo.validation.alert.userid.char"/>");
			$("#userId").val("");
			$("#userId").focus();
			return false;
		} //혼용이 아닐경우 false return
		</c:if>
		return true;
	}

	function idDupCheck() {
		if(validateId()){
			var userId = $("#userId").val();
			$.getJSON( cUrl("/adm/user/info/userIdCheck"), {
				 	  		"userId" : userId },			// params
							function(data) {
								$("#idCheck").val(data.isUseable);
								if(data.isUseable == 'Y') {
									alert('<spring:message code="user.message.userinfo.userid.possible"/>');
								} else {
									alert('ERROR! \n<spring:message code="user.message.userinfo.userid.impossible"/>');
							}
						});

		}
	}


	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
	<c:if test="${usePhoto eq 'Y' }">
		$(':input:hidden[name=userInfoDTO\\.photoFileSn]').val(usrPhotoFile.getFileSnAll());

		<c:if test="${requiredPhoto eq 'Y' }">
		if(usrPhotoFile.getFileSnAll() == ""){
			alert("<spring:message code="user.message.login.alert.photo"/>");
			return;
		}
		</c:if>
	</c:if>
	<c:if test="${useUserdiv eq 'Y' && requiredUserdiv eq 'Y' }">
		var interestFieldCd = $('#interestFieldCd').val();
		if(interestFieldCd == ''){
			alert("<spring:message code="user.message.alert.interestfieldcd"/>");
			return false;
		}
	</c:if>
	<c:if test="${useMessage eq 'Y' && requiredMessage eq 'Y' }">
		if(!$("#emailRecv").is(":checked") && !$("#smsRecv").is(":checked") ){
			alert("<spring:message code="user.message.userinfo.alert.select.message"/>");
			return;
		}
	</c:if>
	<c:if test="${useMemo eq 'Y' && requiredMemo eq 'Y' }">
		if(isEmpty($("#memo").val())) {
			alert("<spring:message code="user.message.userinfo.alert.input.memo"/>");
			return;
		}
	</c:if>


		if(!gubunValidation()){
			return;
		}
		$('#userInfoForm').attr("action", cmd);
		$('#userInfoForm').ajaxSubmit(processCallback);
	}

	function gubunValidation(){
	<c:if test="${useArea eq 'Y' && requiredArea eq 'Y' }">
		if($('#areaCd').val() == ''){
			alert("<spring:message code="user.message.alert.area"/>");
			$('#areaCd').focus();
			return false;
		}
	</c:if>
	<c:if test="${useUserdiv eq 'Y' && requiredUserdiv eq 'Y' }">
		var userDivCd = $('#userDivCd').val();
		if(userDivCd == ''){
			alert("<spring:message code="user.message.alert.gubun"/>");
			$('#userDivCd').focus();
			return false;
		}
	</c:if>
		return true;
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

	<c:if test="${useBirth	 eq 'Y' && requiredBirth eq 'Y' }">
		var year = $("#birthYear option:selected").val();
		var month = $("#birthMonth option:selected").val();
		var day = $("#birthDay option:selected").val();
		$("#birth").val(year+month+day);
		if( !isDate($('#birth').val())){
			alert("<spring:message code="user.message.userinfo.birth.notmatch"/>");
			$("#birth").focus();
			return;
		}
	</c:if>
		if($("#orgCd > option:selected").val() == '') {
			alert("<spring:message code="user.message.userinfo.select.organization"/>");
			return;
		}
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
	<c:if test="${useSex eq 'Y' && requiredSex eq 'Y' }">
		if(!$("#sexCdM").is(":checked") && !$("#sexCdF").is(":checked") ){
			alert("<spring:message code="user.message.userinfo.choice.sex"/>");
			return;
		}
	</c:if>
	<c:if test="${useEmail eq 'Y' && requiredEmail eq 'Y' }">
		if(isEmpty($("#email_id").val()) || isEmpty($("#email_domain_text").val()) ){
			alert("<spring:message code="user.message.login.search.alert.input.email"/>");
			return;
		}
	</c:if>
	<c:if test="${useDisablility eq 'Y' && requiredDisablility eq 'Y' }">
		if(!$("#disablilityYnY").is(":checked") && !$("#disablilityYnN").is(":checked") ){
			alert("<spring:message code="user.message.userinfo.choice.disablility"/>");
			return;
		}
	</c:if>
		$("#email").val($("#email_id").val()+"@"+$("#email_domain_text").val());
		//-- 사용자 권한 셋팅
		var wwwAuthGrp = "/MEMBER";
		$("#userInfoForm input[name='wwwUserAuth']").each(function(i){
			if(this.checked) wwwAuthGrp += "/" + $(this).val();
		});
		var mngAuthGrp = "";
		$("#userInfoForm input[name='mngUserAuth']").each(function(i){
			if(this.checked) mngAuthGrp += "/" + $(this).val();
		});
		if(mngAuthGrp == '/') mngAuthGrp = '';
		if(mngAuthGrp == '/') mngAuthGrp = '';
		$("#wwwAuthGrpCd").val(wwwAuthGrp);
		$("#mngAuthGrpCd").val(mngAuthGrp);

		process("/adm/user/info/addUser");	// cmd
	}

	/**
	 * 사용자 수정
	 */
	function editUser() {
		if(!validate(document.userInfoForm)) return;
		var year = $("#birthYear option:selected").val();
		var month = $("#birthMonth option:selected").val();
		var day = $("#birthDay option:selected").val();
		$("#birth").val(year+month+day);
		
		if( !isDate($('#birth').val())){
			alert("<spring:message code="user.message.userinfo.birth.notmatch"/>");
			$("#birth").focus();
			return;
		}
	<c:if test="${useSex eq 'Y' && requiredSex eq 'Y' }">
		if(!$("#sexCdM").is(":checked") && !$("#sexCdF").is(":checked") ){
			alert("<spring:message code="user.message.userinfo.choice.sex"/>");
			return;
		}
	</c:if>
	<c:if test="${useEmail eq 'Y' && requiredEmail eq 'Y' }">
		if(isEmpty($("#email_id").val()) || isEmpty($("#email_domain_text").val()) ){
			alert("<spring:message code="user.message.login.search.alert.input.email"/>");
			return;
		}
	</c:if>
		$("#email").val($("#email_id").val()+"@"+$("#email_domain_text").val());

		//-- 사용자 권한 셋팅
		var wwwAuthGrp = "/MEMBER";
		$("#userInfoForm input[name='wwwUserAuth']").each(function(i){
			if(this.checked) wwwAuthGrp += "/" + $(this).val();
		});
		var mngAuthGrp = "";
		$("#userInfoForm input[name='mngUserAuth']").each(function(i){
			if(this.checked) mngAuthGrp += "/" + $(this).val();
		});
		$("#wwwAuthGrpCd").val(wwwAuthGrp);
		$("#mngAuthGrpCd").val(mngAuthGrp);
		process("/adm/user/info/editUser");	// cmd
	}

	/**
	 * 사용자 활성화
	 */
	function editUser2() {
		if(!validate(document.userInfoForm)) return;
		$("#userSts").val("U"); //-- 사용자 활성화..
		//-- 사용자 권한 셋팅
		var wwwAuthGrp = "/MEMBER";
		$("#userInfoForm input[name='wwwUserAuth']").each(function(i){
			if(this.checked) wwwAuthGrp += "/" + $(this).val();
		});
		var mngAuthGrp = "";
		$("#userInfoForm input[name='mngUserAuth']").each(function(i){
			if(this.checked) mngAuthGrp += "/" + $(this).val();
		});
		$("#wwwAuthGrpCd").val(wwwAuthGrp);
		$("#mngAuthGrpCd").val(mngAuthGrp);
		process("/adm/user/info/editUser");	// cmd
	}

	/**
	 * 사용자 삭제
	 */
	function delUser() {
		process("/adm/user/info/removeUser");	// cmd
	}

	//-- 사용자 상태 정지
	function stopUser() {
		var userNo = $("#userNo").val();
		$.getJSON( cUrl("/adm/user/info/stopUser"), {
				 	  		"userNo" : userNo },			// params
							function(data) {
				 	  			if(data.result >= 0) {
				 	  				alert("<spring:message code="user.message.userinfo.nouse.success"/>");
									parent.listPageing(1);
									parent.modalBoxClose();
								} else {
									alert('ERROR! \n<spring:message code="user.message.userinfo.nouse.failed"/>');
							}
						});

	}

	//-- 사용자 상태 정지
	function startUser() {
		var userNo = $("#userNo").val();
		$.getJSON( cUrl("/adm/user/info/startUser"), {
				 	  		"userNo" : userNo },			// params
							function(data) {
				 	  			if(data.result >= 0) {
									alert('<spring:message code="user.message.userinfo.use.success"/>');
									parent.listPageing(1);
									parent.modalBoxClose();
								} else {
									alert('ERROR! \n<spring:message code="user.message.userinfo.use.failed"/>');
							}
						});

	}

	//-- 사용자 탈퇴 처리
	function joinOutUser() {
		var userNo = $("#userNo").val();
		$.getJSON( cUrl("/adm/user/info/joinOut"), {
				 	  		"userNo" : userNo },			// params
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
		$.getJSON( cUrl("/adm/user/info/resetPass"), {
			"cmd" : "resetPass",
			"newUserPassConfirm": str,
			"userId" : "${vo.userId}",
			"userNo" : "${vo.userNo}" },			// params
			function(data) {
				alert(data.messageDetail);
			});
	}


	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}
	function deptSearch(){
		$('#userInfoArea').hide();
		$('#userDeptArea').load(
			cUrl("/UserInfoAdmin.do"),
			{"cmd":"deptSearchForm"
			,"userDeptInfoDTO.orgCd":"${vo.orgCd}"
			}
		);
		$('#userDeptArea').show();
	}

	function setDeptData(deptCd,deptNm, phoneNo, faxNo, deptAddr){
		$('#deptCd').val(deptCd);
		$('#deptNm').val(deptNm);
		$('#compPhoneno').val(phoneNo);
		$('#compFaxNo').val(faxNo);
		$('#compAddr1').val(deptAddr);

		$('#userInfoArea').show();
		$('#userDeptArea').hide();
	}

	function searchClose(){
		$('#userInfoArea').show();
		$('#userDeptArea').hide();
	}

	function emailSet(){
		var email_domain_sel = $("#email_domain_sel").val();
		if(email_domain_sel == 'DIRECT' ){
			$("#email_domain_text").attr("readonly", false);
		} else if(email_domain_sel == ''){
			$("#email_domain_text").attr("readonly", true);
		} else {
			$("#email_domain_text").attr("readonly", true);
			$("#email_domain_text").val($("#email_domain_sel :selected").text());
		}
	}

	function checkBirthDay() {
		var year = $("#birthYear option:selected").val();
		var month = parseInt($("#birthMonth option:selected").val(),10);
		var length = 0;

		if(month == 4 || month == 6 || month == 9 || month == 11) length = 30;
		else if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) length = 31;
		else if((year%4==0) && (year%100 !=0) || (year%400 == 0)) length = 29;
		else length = 28;

		$("#birthDay option").remove();
		for(var i=1; i <= length; i++ ) {
			if(i < 10) day = "0"+i;
			else day = i;
			if(day == '${birthDay}')
				$("#birthDay").append("<option value='"+day+"' selected>"+day+"</option>");
			else
				$("#birthDay").append("<option value='"+day+"'>"+day+"</option>");
		}
	}
	
	//아이디 중복체크 초기화
	function resetIdCheck(){
		$("#idCheck").val('N');
	}
</script>
</mhtml:html>