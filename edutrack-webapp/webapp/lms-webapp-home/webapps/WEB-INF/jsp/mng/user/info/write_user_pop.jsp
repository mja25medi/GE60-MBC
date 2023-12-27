<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
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


<div class="callout callout-warning" style="margin-bottom: 10!important;">
	<span style="color:red;font-weight:bold;">* </span><spring:message code="common.message.input.required"/>
</div>
<form name="userInfoForm" id="userInfoForm" onsubmit="return false" method="POST">
<input type="hidden" name="userNo" id="userNo" value="${vo.userNo}"/>
<input type="hidden" name="userSts" id="userSts" value="${vo.userSts}"/>
<input type="hidden" name="adminAuthGrpCd" id="adminAuthGrpCd" value="${vo.adminAuthGrpCd}"/>
<input type="hidden" name="wwwAuthGrpCd" id="wwwAuthGrpCd" value="${vo.wwwAuthGrpCd}"/>
<input type="hidden" name="mngAuthGrpCd" id="mngAuthGrpCd" value="${vo.mngAuthGrpCd}"/>
<input type="hidden" name="photoFileSn" id="photoFileSn" value="${vo.photoFileSn}"/>
<input type="hidden" name="orgCd"  id="orgCd" value="${vo.orgCd}"/>
<input type="hidden" name="msgRecv"  id="msgRecv" value="Y"/>
<div id="userInfoArea">
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
					<input type="password" maxlength="16" name="userPass" id="userPass" value="" class="form-control input-sm" style="width: 200px;"/>
				</div>			
			</td>
			<th scope="row"><span style="color:red;">* </span><label for="userPassChk">비밀번호 확인</label></th>
			<td>
				<div class="input-group" style="float:left;">
					<input type="password" maxlength="16" name="userPassChk" id="userPassChk" value="" class="form-control input-sm" style="width: 200px;"/>
				</div>			
			</td>
		</tr>
		</c:if>
		<tr>
			<th scope="row" ><span style="color:red;">* </span><label for="deptCd">이메일</label></th>
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
						<option value="${item.codeCd }" <c:if test="${vo.stdDivCd eq item.codeCd }">selected</c:if>>${item.codeNm }</option>
					</c:forEach>
				</select>
			</td>
			<th scope="row"><label for="nonReguDivCd">비정규직구분</label></th>
			<td>
				<select name="nonReguDivCd" id="nonReguDivCd" class="form-control input-sm" style="width: 200px;">
					<c:forEach var="item" items="${nonReguDivCdList }">
						<option value="${item.codeCd }" <c:if test="${vo.nonReguDivCd eq item.codeCd }">selected</c:if>>${item.codeNm }</option>
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
	</table><br/>

	<table summary="<spring:message code="user.title.userinfo.manage"/>" class="table table-bordered">
		<colgroup>
			<col width="30%"/>
			<col width="70%"/>
		</colgroup>
		<tr>
			<th class="top"><label for="compNm"><spring:message code="user.title.userinfo.auth"/></label></th>
			<td>
				<div class="radio">
					<span class="mr10"><spring:message code="user.title.userinfo.member"/></span>
				<c:forEach var="item" items="${wwwAuthList}">
					<c:if test="${item.authGrpCd ne 'MEMBER' && item.authGrpCd ne 'GUEST' }">
					<c:set var="authGrpNm" value="${item.authGrpNm}"/>
					<c:forEach var="lang" items="${item.authGrpLangList}">
						<c:if test="${lang.langCd eq LOCALEKEY}">
							<c:set var="authGrpNm" value="${lang.authGrpNm}"/>
						</c:if>
					</c:forEach>
					<label class="mr10"><input type="checkbox" name="wwwUserAuth" value="${item.authGrpCd}" <c:if test="${fn:contains(userAuthGrpCd, item.authGrpCd)}">checked</c:if>  id="authGrp_${item.authGrpCd}"/> ${authGrpNm}</label>
					</c:if>
				</c:forEach>
 				<c:forEach var="item" items="${mngAuthList}"> 
 					<c:set var="authGrpNm" value="${item.authGrpNm}"/> 
 					<c:forEach var="lang" items="${item.authGrpLangList}"> 
 						<c:if test="${lang.langCd eq LOCALEKEY}"> 
 							<c:set var="authGrpNm" value="${lang.authGrpNm}"/> 
 						</c:if> 
 					</c:forEach> 
 					<label class="mr10"><input type="checkbox" name="mngUserAuth" value="${item.authGrpCd}" <c:if test="${fn:contains(userAuthGrpCd, item.authGrpCd)}">checked</c:if> id="authGrp_${item.authGrpCd}"/> ${authGrpNm}</label> 
 				</c:forEach> 
				</div>
			</td>
		</tr>
	</table>
	<div class="text-right" style="margin-top:10px;">
		<c:if test="${gubun eq 'A'}">
			<button class="btn btn-primary btn-sm" onclick="addUser()"><i class="fa fa-check"></i> <spring:message code="button.add"/></button>
		</c:if>
		<c:if test="${gubun eq 'E' }">
		<!-- 소셜 분기 -->
			<c:choose>
				<c:when test="${not empty vo.snsDiv }">
					<c:if test="${vo.userSts ne 'D'}">
					<button class="btn btn-primary btn-sm" onclick="editUser()"><i class="fa fa-check"></i> <spring:message code="button.edit"/></button>
					</c:if>
					<c:if test="${vo.userSts eq 'U' }">
					<button class="btn btn-warning btn-sm" onclick="stopUser()"><i class="fa fa-stop"></i> <spring:message code="button.stopuse"/></button>
					</c:if>
					<c:if test="${vo.userSts ne 'D' }">
					<button class="btn btn-warning btn-sm" onclick="joinOutUser()"><i class="fa fa-stop"></i> <spring:message code="button.leave.member"/></button>
					</c:if>
				</c:when>
				<c:otherwise>
					<c:if test="${vo.userSts eq 'D'}">
					<button class="btn btn-primary btn-sm" onclick="editUser2()"><i class="fa fa-check"></i> <spring:message code="button.revitalize"/></button>
					</c:if>
					<c:if test="${vo.userSts ne 'D'}">
					<button class="btn btn-primary btn-sm" onclick="editUser()"><i class="fa fa-check"></i> <spring:message code="button.edit"/></button>
					</c:if>
					<button class="btn btn-info btn-sm" onclick="resetPass('NEW')"><i class="fa fa-key"></i> <spring:message code="button.reset.password"/></button>
					<button class="btn btn-info btn-sm" onclick="resetPass('ID')"><i class="fa fa-key"></i> <spring:message code="button.reset.password.fromid"/></button>
					<c:if test="${vo.userSts eq 'U' }">
					<button class="btn btn-warning btn-sm" onclick="stopUser()"><i class="fa fa-stop"></i> <spring:message code="button.stopuse"/></button>
					</c:if>
					<c:if test="${vo.userSts eq 'C' }">
					<button class="btn btn-warning btn-sm" onclick="startUser()"><i class="fa fa-paly"></i> <spring:message code="button.reuse"/></button>
					</c:if>
					<c:if test="${vo.userSts ne 'D' }">
					<button class="btn btn-warning btn-sm" onclick="joinOutUser()"><i class="fa fa-stop"></i> <spring:message code="button.leave.member"/></button>
					</c:if>
				</c:otherwise>
			</c:choose>
		</c:if>
		<button class="btn btn-default btn-sm" onclick="parent.modalBoxClose()"><i class="fa fa-close"></i> <spring:message code="button.close"/></button>
	</div>
</div>

<div id="userDeptArea">
</div>
</form>

<script type="text/javascript">
	var idCheck = "N";
	var emailCheck = "N";
	var originOrgCd = '${vo.orgCd}';
	// 페이지 초기화
	$(document).ready(function() {
		// 이미지 파일 첨부 바인딩 및 초기화
		initSetting();

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

		<c:if test="${useArea eq 'Y' }">setTitle(document.getElementById("areaCd"));</c:if>
		<c:if test="${useUserdiv eq 'Y' }">setTitle(document.getElementById("userDivCd"));</c:if>
		<c:if test="${useInterest eq 'Y' }">setTitle(document.getElementById("interestFieldCd"));</c:if>
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

	/*id 검증*/
	function validateId(){
		var userId = $("#userId").val();
		if(userId == "") {
			alert("<spring:message code="user.message.userinfo.input.userid"/>");
			return false;
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
		if((userId.length < 6) || (userId.length > 12) ){
			alert("<spring:message code="user.message.userinfo.validation.alert.userid.length2"/>");
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

	//비밀번호 적합성 체크
	function validatePass(){
		
		if(isEmpty($("#userPass").val())) {
			alert("<spring:message code="user.message.userinfo.alert.input.password"/>");
			$("#memo").addClass("validerr");
			return false;
		}
		
		if(($("#userPass").val().length < 6) || ($("#userPass").val().length > 12) ){
			alert("<spring:message code="user.message.userinfo.validation.alert.password2"/>");
			$("#userPass").val("");
			$("#userPass").focus();
			return false;
		}
		
		if(isEmpty($("#userPassChk").val())) {
			alert("<spring:message code="user.message.userinfo.alert.input.password2"/>");
			$("#userPass").focus();
			return false;
		}
		
		if($("#userPass").val() != $("#userPassChk").val()) {
			alert("<spring:message code="user.message.userinfo.notmatch.password"/>");
			$("#userPass").focus();
			return false;
		}
		return true;
	}

	function idDupCheck() {
		
		if(validateId()){
			var userId = $("#userId").val();
			$.getJSON( 
				cUrl("/mng/user/userInfo/userIdCheck"), 
				{ "userId" : userId },			// params
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
		
	<c:if test="${usePhoto eq 'Y' }">
		$(':input:hidden[name=photoFileSn]').val(usrPhotoFile.getFileSnAll());

		<c:if test="${requiredPhoto eq 'Y' }">
		if(usrPhotoFile.getFileSnAll() == ""){
			alert("<spring:message code="user.message.login.alert.photo"/>");
			return;
		}
		</c:if>
	</c:if>
	<c:if test="${useMessage eq 'Y' && requiredMessage eq 'Y' }">
		if(!$("#emailRecv").is(":checked")){
			alert("<spring:message code="user.message.userinfo.alert.select.message"/>");
			return;
		}
	</c:if>


		if(!gubunValidation()){
			return;
		}

		$('#userInfoForm').attr("action" ,"/mng/user/userInfo/" + cmd);
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
			var userDivCd = $('#userDivCd option:selected').val();
			if(userDivCd == ''){
				alert("<spring:message code="user.message.userinfo.alert.input.divcd2"/>");
				$('#userDivCd').focus();
				return false;
			}
		</c:if>
		/* 소속 체크 */
		<c:if test="${useDept eq 'Y' && requiredDept eq 'Y' }">
			if(isEmpty($("#deptCd option:selected").val())){
				alert("<spring:message code="user.message.login.alert.input.deptcd"/>");
				$('#deptCd').focus();
				return false;
			}
		</c:if>
		/* 직업 체크 */
		<c:if test="${useJob eq 'Y' && requiredJob eq 'Y' }">
			if(isEmpty($("#jobCd option:selected").val())){
				alert("<spring:message code="user.message.login.alert.input.jobcd"/>");
				$('#jobCd').focus();
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
		//필수값 체크
		if(!validate(document.userInfoForm)) return;
		//아이디 적합성 체크
		if(!validateId()) return;
		
		if(!validatePass()) return;
		
		var year = $("#birthYear option:selected").val();
		var month = $("#birthMonth option:selected").val();
		var day = $("#birthDay option:selected").val();
		$("#birth").val(year+month+day);
		if( !isDate($('#birth').val())){
			alert("<spring:message code="user.message.userinfo.birth.notmatch"/>");
			$("#birth").focus();
			return;
		}

		if($("#orgCd > option:selected").val() == '') {
			alert("<spring:message code="user.message.userinfo.select.organization"/>");
			return;
		}
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
	<c:if test="${useSex eq 'Y' && requiredSex eq 'Y' }">
		if(!$("#sexCdM").is(":checked") && !$("#sexCdF").is(":checked") ){
			alert("<spring:message code="user.message.userinfo.choice.sex"/>");
			return;
		}
	</c:if>
	<c:if test="${useDisablility eq 'Y' && requiredDisablility eq 'Y' }">
		if(!$("#disablilityYnY").is(":checked") && !$("#disablilityYnN").is(":checked") ){
			alert("<spring:message code="user.message.userinfo.choice.disablility"/>");
			return;
		}
	</c:if>	
		//-- 사용자 권한 셋팅
		var wwwAuthGrp = "/MEMBER";
		$("#userInfoForm input[name='wwwUserAuth']").each(function(i){
			if(this.checked) wwwAuthGrp += "/" + $(this).val();
		});
		var mngAuthGrp = "";
		$("#userInfoForm input[name='mngUserAuth']").each(function(i){
			if(this.checked) mngAuthGrp += "/" + $(this).val();
		});

/*  	담임, 부담임 권한의 경우 front 권한도 필요하여 아래 로직 주석 철리함.. (2023.11.06)
 * 		기능 체크 필요.......
 */		
/*
		if(mngAuthGrp != '') {
			if(!confirm("관리자로 등록 시 기본권한(일반회원, 강사)은 삭제 됩니다.\n관리자로 등록하시겠습니까?)")) return;
			wwwAuthGrp = '';
		}
 */		$("#wwwAuthGrpCd").val(wwwAuthGrp);
		$("#mngAuthGrpCd").val(mngAuthGrp);
		
		if($("#deptCd").val() == '') {
			alert("소속 기업을 선택해 주십시오.");
			return;
		}
		$("#deptNm").val($("#deptCd option:selected").text());
		
		if(!validateMobileNo()) {
			alert("휴대폰 번호 형식이 올바르지 않습니다.");
			return;
		}
		setMobileNo();
		setHomeNo();
		setJuminNo();
		
		process("addUser");	// cmd
	}

	/**
	 * 사용자 수정
	 */
	function editUser() {
		//필수값 체크
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

		//-- 사용자 권한 셋팅
		var wwwAuthGrp = "/MEMBER";
		$("#userInfoForm input[name='wwwUserAuth']").each(function(i){
			if(this.checked) wwwAuthGrp += "/" + $(this).val();
		});
		var mngAuthGrp = "";
		$("#userInfoForm input[name='mngUserAuth']").each(function(i){
			if(this.checked) mngAuthGrp += "/" + $(this).val();
		});
		if(mngAuthGrp != '') {
			if(!confirm("관리자로 등록 시 기본권한(일반회원, 강사)은 삭제 됩니다.\n관리자로 등록하시겠습니까?)")) return;
			wwwAuthGrp = '';
		}

		$("#wwwAuthGrpCd").val(wwwAuthGrp);
		$("#mngAuthGrpCd").val(mngAuthGrp);
		
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
		
		if(!validateMobileNo()) {
			alert("휴대폰 번호 형식이 올바르지 않습니다.");
			return;
		}
		setMobileNo();
		setHomeNo();
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
		var wwwAuthGrp = "/MEMBER";
		$("#userInfoForm input[name='wwwUserAuth']").each(function(i){
			if(this.checked) wwwAuthGrp += "/" + $(this).val();
		});
		var mngAuthGrp = "";
		$("#userInfoForm input[name='mngUserAuth']").each(function(i){
			if(this.checked) mngAuthGrp += "/" + $(this).val();
		});
		if(mngAuthGrp != '') {
			if(!confirm("관리자로 등록 시 기본권한(일반회원, 강사)은 삭제 됩니다.\n관리자로 등록하시겠습니까?)")) return;
			wwwAuthGrp = '';
		}

		$("#wwwAuthGrpCd").val(wwwAuthGrp);
		$("#mngAuthGrpCd").val(mngAuthGrp);
		
		process("editUser");	// cmd
	}

	/**
	 * 사용자 삭제
	 */
	function delUser() {
		process("removeUser");	// cmd
	}

	//-- 사용자 상태 정지
	function stopUser() {
		var userNo = $("#userNo").val();
		$.getJSON( cUrl("/mng/user/userInfo/stopUser"), 
							{ "userNo" : userNo },			// params
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

	//-- 사용자 상태 정지해제
	function startUser() {
		var userNo = $("#userNo").val();
		$.getJSON( cUrl("/mng/user/userInfo/startUser"), 
				{"userNo" : userNo },			// params
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
		$.getJSON( cUrl("/mng/user/userInfo/joinOut"), 
				{ "userNo" : userNo },				// params
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
		$.getJSON( cUrl("/mng/user/userInfo/resetPass"), 
			{ "newUserPassConfirm": str, "userId" : "${vo.userId}", "userNo" : "${vo.userNo}"},			// params
			function(data) {
				alert(data.messageDetail);
			});
	}


	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
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
	
	function setJuminNo() {
		var juminNo = $("#juminNo1").val() + $("#juminNo2").val();
		$("#juminNo").val(juminNo);
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
	
	function setTitle(obj){
		var selectObj = obj;
		var selectTitle = "";
		selectTitle = selectObj.options[selectObj.selectedIndex].text;
		$(selectObj).attr("title",selectTitle);
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
	
	//아이디 중복체크 초기화
	function resetIdCheck(){
		$("#idCheck").val('N');
	}
	
	function uploderclick(str) {
		$("#"+str).click();
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
