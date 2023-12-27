<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<c:url var="img_base" value="/img/home" />
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
		<div class="member">
                        <div class="flex-container">
                            
                            <div class="join_cont">
                                <ol class="join_step">
                                    <li>               
                                        <span class="title"><small>Step 1</small> <b>약관동의</b></span>
                                    </li>
                                    <li class="active">
                                        <span class="title"><small>Step 2</small> <b>정보입력</b></span>                                       
                                    </li>                            
                                    <li>
                                        <span class="title"><small>Step 3</small> <b>가입완료</b></span>                                       
                                    </li>                                     
                                </ol>
                            
                                <div class="txt_info"><i class="icon_star"></i>필수 입력 사항입니다.</div>
                                <form name="userInfoLoginForm" id="userInfoLoginForm" method="POST" action ="/home/user/joinUser">
								<input type="hidden" name="idCheck" id="idCheck" value="N" />
								<input type="hidden" name="emailCheck" id="emailCheck" value="Y" />
								<input type="hidden" name="userSts" id="userSts" value=""/>
								<input type="hidden" name="adminAuthGrpCd" id="adminAuthGrpCd" value=""/>
								<input type="hidden" name="wwwAuthGrpCd" id="wwwAuthGrpCd" value="/MEMBER"/>
								<input type="hidden" name="mngAuthGrpCd" id="mngAuthGrpCd" value=""/>
								<input type="hidden" name="email"  id="email" value=""/>
								<input type="hidden" name="photoFileSn" id="photoFileSn" value=""/>
								<input type="hidden" name="orgCd"  id="orgCd" value="${vo.orgCd}"/>
								<input type="hidden" name="mobileNo"  id="mobileNo" value=""/>
								<input type="hidden" name="homePhoneno"  id="homePhoneno" value=""/>
								<input type="hidden" name="msgRecv" id="msgRecv" value="Y"/>
                                <div class="join_form">
                                    <div class="tstyle mb30">
                                        <ul class="dbody">
                                        <!-- 정보 입력 시작 -->
										<c:forEach items="${userInfoCfgList}" var="cfgItem" varStatus="status">
										<c:if test="${cfgItem.fieldNm eq 'USERID' }">
										<c:set var="useUserid" value="${cfgItem.useYn }"/>
										<c:set var="requiredUserid" value="${cfgItem.requiredYn }"/>
										<c:if test="${cfgItem.requiredYn eq 'Y'}"><c:set var="isNull" value="N" /></c:if>
										<c:if test="${cfgItem.requiredYn eq 'N'}"><c:set var="isNull" value="Y" /></c:if>
                                            <li class="first" <c:if test="${cfgItem.useYn eq 'N'}">style="display:none;"</c:if>>
                                                <div class="row">
                                                    <label for="idInput" class="form-label col-sm-2">아이디
				                                        <c:if test="${cfgItem.requiredYn eq 'Y'}">
				                                        	<i class="icon_star" aria-hidden="true"></i>
				                                        </c:if>
			                                        </label>
                                                    <div class="col-sm-10">
                                                        <div class="form-row">
                                                           <input class="form-control w50" type="text" name="userId" id="userId" value="" placeholder="아이디를 입력하세요" onchange="resetIdCheck()"> <%-- onkeydown="inputUserId()"  --%>
			                                               <button type="button" id="idChkButton" class="btn gray2 ml5" onclick="idDupCheck();">중복확인</button>
                                                        </div>
                                                        <!-- <small class="note">
                                                            <span class="fcBlue">사용가능한 아이디 입니다.</span>
                                                            <span class="fcRed">이미 가입된 아이디 입니다.</span>
                                                        </small> -->
                                                    </div>
                                                </div>
                                            </li>     
                                            <li id="pw">
                                                <div class="row">
                                                    <label for="pwInput" class="form-label col-sm-2">비밀번호<i class="icon_star" aria-hidden="true"></i></label>
                                                    <div class="col-sm-10">
                                                        <div class="form-row">
                                                            <input class="form-control w50" type="password" name="userPass" id="userPass" maxlength="16" value="" autocomplete="off">
                                                        </div> 
                                                        <small class="note">* 비밀번호는 최소 8자리, 특수문자+숫자+영문 조합입니다.</small>                                               
                                                    </div>
                                                </div> 
                                            </li>    
                                            <li id="pwChk">
                                                <div class="row">   
                                                    <label for="pwInputConfirm" class="form-label col-sm-2">새비밀번호                                                        <i class="icon_star" aria-hidden="true"></i></label>
                                                    <div class="col-sm-10">
                                                        <div class="form-row">
                                                            <input class="form-control w50" type="password" name="userPassChk" id="userPassChk" maxlength="16" value=""  placeholder="" autocomplete="off">
                                                        </div>                                                       
                                                    </div>
                                                </div>
                                            </li>
                                            </c:if>
                                            <c:if test="${cfgItem.fieldNm eq 'USERNM' }">
											<c:set var="useUsernm" value="${cfgItem.useYn }"/>
											<c:set var="requiredUsernm" value="${cfgItem.requiredYn }"/>
											<c:if test="${cfgItem.requiredYn eq 'Y'}"><c:set var="isNull" value="N" /></c:if>
											<c:if test="${cfgItem.requiredYn eq 'N'}"><c:set var="isNull" value="Y" /></c:if>
                                            <li <c:if test="${cfgItem.useYn eq 'N'}">style="display:none;"</c:if>>
                                                <div class="row">
                                                    <label for="nameInput" class="form-label col-sm-2">이름<i class="icon_star" aria-hidden="true"></i></label>
                                                    <div class="col-sm-10">
                                                        <div class="form-row">
                                                            <input class="form-control w50" name="userNm" type="text" id="userNm" value="${joinUsrUserInfoVO.userNm }" readonly>
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                            </c:if>
                                            <c:if test="${cfgItem.fieldNm eq 'EMAIL' }">
											<c:set var="useEmail" value="${cfgItem.useYn }"/>
											<c:set var="requiredEmail" value="${cfgItem.requiredYn }"/>
											<c:if test="${cfgItem.requiredYn eq 'Y'}"><c:set var="isNull" value="N" /></c:if>
											<c:if test="${cfgItem.requiredYn eq 'N'}"><c:set var="isNull" value="Y" /></c:if>  
                                            <li <c:if test="${cfgItem.useYn eq 'N'}">style="display:none;"</c:if>>
                                                <div class="row">
                                                    <label for="inputEmail1" class="form-label col-sm-2"><span>이메일주소</span><i class="icon_star" aria-hidden="true"></i></label>
                                                    <div class="col-sm-10">
                                                        <div class="form-inline">
                                                            <input class="form-control mr5" type="text" name="email_id" id="email_id" onchange="resetEmailCheck()">
			                                                <span class="mr5">@</span>
			                                                <input class="form-control mr10" type="text" name="email_domain_text" id="email_domain_text" onchange="resetEmailCheck()" title="이메일 주소 뒷자리" placeholder="">
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
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                            </c:if>
                                            <c:if test="${cfgItem.fieldNm eq 'BIRTH' }">
											<c:set var="useBirth" value="${cfgItem.useYn }"/>
											<c:set var="requiredBirth" value="${cfgItem.requiredYn }"/>
											<c:if test="${cfgItem.requiredYn eq 'Y'}"><c:set var="isNull" value="N" /></c:if>
											<c:if test="${cfgItem.requiredYn eq 'N'}"><c:set var="isNull" value="Y" /></c:if>                           
                                            <li <c:if test="${cfgItem.useYn eq 'N'}">style="display:none;"</c:if>>	
                                                <div class="row">
                                                    <label for="birthdayInput" class="form-label col-sm-2">생년월일<i class="icon_star" aria-hidden="true"></i></label>
                                                    <div class="col-sm-10">
                                                        <div class="form-row">
                                                            <input class="form-control w50" type="text" name="birth" id="birth" value="${joinUsrUserInfoVO.birth }" maxlength="7" readonly>
                                                        </div>
                                                        <!-- <small class="note">* 생년월일 6자리와 주민번호 앞 1자리를 입력하세요.</small> -->
                                                    </div>
                                                </div>
                                            </li>
                                            </c:if>
                                            <c:if test="${cfgItem.fieldNm eq 'MOBILENO' }">
											<c:set var="useMobileno" value="${cfgItem.useYn }"/>
											<c:set var="requiredMobileno" value="${cfgItem.requiredYn }"/>
											<c:if test="${cfgItem.requiredYn eq 'Y'}"><c:set var="isNull" value="N" /></c:if>
											<c:if test="${cfgItem.requiredYn eq 'N'}"><c:set var="isNull" value="Y" /></c:if>
                                            <li <c:if test="${cfgItem.useYn eq 'N'}">style="display:none;"</c:if>>
                                                <div class="row">
                                                    <label for="selectPhone1" class="form-label col-sm-2"><span>휴대폰번호</span><i class="icon_star" aria-hidden="true"></i></label>
                                                    <div class="col-sm-10">
                                                        <div class="form-inline">
                                                        <select class="form-select w20 mr20" id="mobileNo1" disabled>
			                                                <option value="">선택</option>
			                                                <option value="010" <c:if test="${fn:substring(joinUsrUserInfoVO.mobileNo,0,3) eq '010'}">selected</c:if>>010</option>
			                                                <option value="011" <c:if test="${fn:substring(joinUsrUserInfoVO.mobileNo,0,3) eq '011'}">selected</c:if>>011</option>
			                                                <option value="016" <c:if test="${fn:substring(joinUsrUserInfoVO.mobileNo,0,3) eq '016'}">selected</c:if>>016</option>
			                                                <option value="017" <c:if test="${fn:substring(joinUsrUserInfoVO.mobileNo,0,3) eq '017'}">selected</c:if>>017</option>
			                                                <option value="018" <c:if test="${fn:substring(joinUsrUserInfoVO.mobileNo,0,3) eq '018'}">selected</c:if>>018</option>
			                                                <option value="019" <c:if test="${fn:substring(joinUsrUserInfoVO.mobileNo,0,3) eq '019'}">selected</c:if>>019</option>
			                                            </select>                                                                      
                                                        <input class="form-control mr5" type="text" name="title" value="${fn:substring(joinUsrUserInfoVO.mobileNo,3,7) }" id="mobileNo2" title="전화번호 두번째자리" disabled>
			                                            <input class="form-control" type="text" name="title" value="${fn:substring(joinUsrUserInfoVO.mobileNo,7,11) }" id="mobileNo3" title="전화번호 세번째자리" disabled>       
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                            </c:if>
                                            <c:if test="${cfgItem.fieldNm eq 'HOMEADDR' }">
											<c:set var="useHomeaddr" value="${cfgItem.useYn }"/>
											<c:set var="requiredHomeaddr" value="${cfgItem.requiredYn }"/>
											<c:if test="${cfgItem.requiredYn eq 'Y'}"><c:set var="isNull" value="N" /></c:if>
											<c:if test="${cfgItem.requiredYn eq 'N'}"><c:set var="isNull" value="Y" /></c:if>
                                            <li <c:if test="${cfgItem.useYn eq 'N'}">style="display:none;"</c:if>>
                                                <div class="row">
                                                    <label for="inputAddress1" class="form-label col-sm-2"><span>주소</span><i class="icon_star" aria-hidden="true"></i></label>
                                                    <div class="col-sm-10">                                            
                                                        <div class="form-inline mb10">
                                                            <input class="form-control" type="text" name="homePostNo" id="inputAddress1" placeholder="우편번호" onclick="searchAddress()" readonly style="background-color: white;">
			                                                <button type="button" class="btn gray2 ml5" onclick="searchAddress()">검색</button>
                                                        </div>
                                    
                                                        <div class="form-row">
                                                            <input class="form-control mr5" type="text" name="homeAddr1" id="inputAddress2" title="시군구 주소를 입력하세요" placeholder="시군구 주소" onclick="searchAddress()" readonly style="background-color: white;">
                                                        </div>
                                                        <div class="form-row">
                                                            <input class="form-control mr5" type="text" name="homeAddr2" id="inputAddress3" title="시군구 상세 주소를 입력하세요" placeholder="상세 주소">
                                                        </div>
                                    
                                                    </div>
                                                </div>
                                            </li>
                                            </c:if>
                                            
                                            </c:forEach>
                                            <li style="display: none;">
				                                <div class="row">
				                                    <span class="form-label col-sm-2"><span>메일수신여부</span><i class="icon_star" aria-hidden="true"></i></span>
				                                    <div class="col-sm-10 form-inline">
				                                        <span class="custom-input">
				                                            <input type="radio" name="emailRecv" id="checkMailY" value="Y" >
				                                            <label for="checkMailY">수신</label>
				                                        </span>
				                                        <span class="custom-input ml5">
				                                            <input type="radio" name="emailRecv" id="checkMailN" value="N" checked>
				                                            <label for="checkMailN">수신거부</label>
				                                        </span>
				                                    </div>
				                                </div>
				                            </li>
                                            <li style="display: none;">
				                                <div class="row">
				                                    <span class="form-label col-sm-2"><span>문자수신여부</span><i class="icon_star" aria-hidden="true"></i></span>
				                                    <div class="col-sm-10 form-inline">
				                                        <span class="custom-input">
				                                            <input type="radio" name="smsRecv" id="smsRecvY" value="Y" >
				                                            <label for="smsRecvY">수신</label>
				                                        </span>
				                                        <span class="custom-input ml5">
				                                            <input type="radio" name="smsRecv" id="smsRecvN" value="N" checked>
				                                            <label for="smsRecvN">수신거부</label>
				                                        </span>
				                                    </div>
				                                </div>						                            
				                            </li>                                            
                                        </ul>
                                    </div>
                                </div>
                         </form>
												<div class="btns">
						                            <button type="button" class="btn type5" onclick="cancel_join()">취소</button>
						                            <button type="button" class="btn type4" id="btn_ok">다음</button>
						                        </div>
						</div>


<script type="text/javascript">
	var originOrgCd = '${vo.orgCd}';
	var daumAddTheme = {
			   searchBgColor: "#0B65C8", //검색창 배경색
			   queryTextColor: "#FFFFFF" //검색창 글자색
			};
	var daumAddress = null;
	var snsJoin = '<%= (String)session.getAttribute("snsJoin")%>';
$(document).ready(function() {
	
	daumAddress = new daum.Postcode({
		theme : daumAddTheme,
        oncomplete: function(data) {
            $("#inputAddress1").val(data.zonecode);
            if(data.userSelectedType == "R") {
            	$("#inputAddress2").val(data.roadAddress + " " + data.buildingName);
            } else {
            	$("#inputAddress2").val(data.jibunAddress + " " + data.buildingName);
            }
            $("#inputAddress3").focus();
        }
	});
	
	$("#email_domain_sel").val('DIRECT');
	emailSet();
	
	//소셜 회원가입일때
	<%-- if(snsJoin =='Y') {
		var ssoType = '<%= (String)session.getAttribute("ssoType")%>';
		var birthyear = '<%=(String)session.getAttribute("birthyear")%>';
		var birthday = '<%=(String)session.getAttribute("birthday")%>';
		var mobile = '<%=(String)session.getAttribute("mobile")%>';
		var name = '<%=(String)session.getAttribute("name")%>';
		var phone = mobile.split('-');
		
		$("#userNm").val(name);
		$("#birth").val(birthyear + birthday);
		$("#mobileNo1").val(phone[0]);
		$("#mobileNo2").val(phone[1]);
		$("#mobileNo3").val(phone[2]);
		
	} --%>
});

function cancel_join(){
	location.href = "/home/user/joinCancel";
}

/*id 검증*/
function validateId(){
	var userId = $("#userId").val();
	if(userId == "") {
		alert("<spring:message code="user.message.userinfo.input.userid"/>");
		return false;
	}

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
	return true;
}

function validateEmail() {
	return !(isEmpty($("#email_id").val()) || isEmpty($("#email_domain_text").val()));
}

//비밀번호 적합성 체크
function validatePass() {
	if (validateUserPass() && validateUserPassChk()) {
		return true;
	} else {
		return false;
	}
}

function checkPassword(pwd){
	  // 비밀번호 포맷 확인(영문, 특수문자, 숫자 포함 8자 이상, 16자 이하)
	  if(!/^(?=.*[a-zA-Z])(?=.*\d)(?=.*\W).{8,16}$/.test(pwd)){
	    return false;
	  }
	  return true;
}

//비밀번호 적합성 체크
function validateUserPass() {
	if(isEmpty($("#userPass").val())) {
		$("#userPassInfo span.sec").hide();
		$("#userPassInfo #invalidInputUserPassMsg").show();
		alert("비밀번호를 입력 해주세요.");
		return false;
	}
	
	$("#userPassInfo span.sec").hide();
	if(!checkPassword($("#userPass").val())){
		alert("비밀번호는 영문과 특수문자 숫자를 포함하며 8자 이상, 16자 이하 이어야 합니다.");
		$("#userPassInfo #invalidInputUserPassMsg").show();
		$("#userPass").val("");
		return false;
	} else {
		$("#userPassInfo #inputUserPassMsg").show();
	}
	
	$("#userPassChkInfo span.sec").hide();
	if(isEmpty($("#userPassChk").val())) {
		alert("비밀번호 확인 란을 입력해주세요.");
		return false;
	}
	
	if($("#userPass").val() != $("#userPassChk").val()) {
		$("#userPassChkInfo #invalidInputUserPassChkMsg").show();
		alert("비밀번호가 일치하지 않습니다.");
		return false;
	} else {
		$("#userPassChkInfo #inputUserPassChkMsg").show();
	}
	
	return true;
}

//비밀번호 적합성 체크
function validateUserPassChk() {
	$("#userPassChkInfo span.sec").hide();
	if(isEmpty($("#userPassChk").val())) {
		$("#userPassChkInfo #invalidInputUserPassChkMsg").show();			
		return false;
	}
	
	if($("#userPass").val() != $("#userPassChk").val()) {
		$("#userPassChkInfo #invalidInputUserPassChkMsg").show();
		return false;
	} else {
		$("#userPassChkInfo #inputUserPassChkMsg").show();
	}
	
	return true;
}

function inputUserId() {
	$('#idCheck').val('N');
	$("#userIdInfo span.sec").hide();
}

// ID 중복 체크
function idDupCheck() {
	if(validateId()){
		var userId = $("#userId").val();
		$.getJSON( 
			"/home/user/userIdCheck",
			{"userId" : userId },			// params
			function(data) {
				$("#idCheck").val(data.isUseable);
				if(data.isUseable == 'Y') {
					$("#idChkButton").hide();
					alert("<spring:message code="home.user.ableinputidmessage1"/>");
				} else {
					alert("<spring:message code="home.user.disableinputidmessage1"/>");
				}
			}
		);
	} /* else {
		alert("올바른 형식이 아닙니다.");
	} */
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
		} else {
			$("#userDivNm").val($('#userDivCd option:selected').text());
		}
	</c:if>
	/* 소속 체크 */
	/* <c:if test="${useDept eq 'Y' && requiredDept eq 'Y' }">
		if(isEmpty($("#deptCd option:selected").val())){
			alert("<spring:message code="user.message.login.alert.input.deptcd"/>");
			$('#deptCd').focus();
			return false;
		}
	</c:if> */
	/* 직업 체크 */
	/* <c:if test="${useJob eq 'Y' && requiredJob eq 'Y' }">
		if(isEmpty($("#jobCd option:selected").val())){
			alert("<spring:message code="user.message.login.alert.input.jobcd"/>");
			$('#jobCd').focus();
			return false;
		} else {
			$("#jobNm").val($("#jobCd option:selected").text());
		}
	</c:if> */
		return true;
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

function resetEmailCheck() {
	$("#emailCheck").val('N');
	$("#emailChkBtn").show();
}


function setTitle(obj){
	var selectObj = obj;
	var selectTitle = "";
	selectTitle = selectObj.options[selectObj.selectedIndex].text;
	$(selectObj).attr("title",selectTitle);
}

//아이디 중복체크 초기화
function resetIdCheck(){
	$("#idCheck").val('N');
	$("#idChkButton").show();
}

function uploderclick(str) {
	$("#"+str).click();
}

function searchAddress() {
	daumAddress.open();
}

function viewSearchDeptPop() {
	modalBox.clear();
	modalBox.addContents('<c:url value="/home/user/viewSearchDeptPop2"/>');
	modalBox.setTitle("기업 검색");
	modalBox.show();
}

//확인(사용자 정보 전송)
$('#btn_ok').bind("click keydown",function(event) {
	if($M.Check.Event.isClickEnter(event)) {
		event.preventDefault();
		
		if( $('#idCheck').val() == 'N' ) {
			alert("<spring:message code="user.message.userinfo.nodupchek"/>");
			return;
		}
		
			if(isEmpty($("#userPass").val())) {
				alert("<spring:message code="user.message.userinfo.alert.input.password"/>");
				$("#userPass").focus();
				return;
			}
	
			if(isEmpty($("#userPassChk").val())) {
				alert("<spring:message code="user.message.userinfo.alert.input.password2"/>");
				$("#userPassChk").focus();
				return;
			}
			
			if(!validatePass()) return ;
	
			if( $('#userPass').val() != $('#userPassChk').val() ) {
				alert("<spring:message code="user.message.userinfo.notmatch.password"/>");
				return;
			}
		<c:if test="${useEmail eq 'Y' && requiredEmail eq 'Y' }">
			if(isEmpty($("#email_id").val()) || isEmpty($("#email_domain_text").val()) ){
				alert("<spring:message code="user.message.login.search.alert.input.email"/>");
				return;
			}
			$("#email").val($("#email_id").val()+"@"+$("#email_domain_text").val());

			if($('#emailCheck').val() == 'N') {
				alert("이메일 중복 확인이 완료되지 않았습니다.");
				return;
			}
		</c:if>
		
		if(isEmpty($("#inputAddress1").val())) {
			alert("주소를 입력해 주세요.");
			$("#inputAddress1").focus();
			return;
		}
		
		if(isEmpty($("#inputAddress2").val())) {
			alert("주소를 입력해 주세요.");
			$("#inputAddress2").focus();
			return;
		}
		
		if(isEmpty($("#inputAddress3").val())) {
			alert("상세주소를 입력해 주세요.");
			$("#inputAddress3").focus();
			return;
		}
		
		
		if(!validateMobileNo()) {
			alert("휴대폰 번호를 올바르게 입력해 주세요.");
			return;
		}
		setMobileNo();
		setHomeNo();
		
		/* <c:if test="${useDept eq 'Y' && requiredDept eq 'Y' }">
		if(isEmpty($("#deptCd").val())){
			alert("소속 기업을 선택하세요.");
			$('#deptNm').focus();
			return false;
		}
	</c:if> */

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
<c:if test="${useUserdiv eq 'Y' && requiredUserdiv eq 'Y' }">
		if($("#userDivCd").val() == ""){
			alert("<spring:message code="user.message.userinfo.choice.userdivcd"/>");
			return;
		}
</c:if>
<c:if test="${useArea eq 'Y' && requiredArea eq 'Y' }">
		if($('#areaCd').val() == ''){
			alert("<spring:message code="user.message.alert.area"/>");
			$('#areaCd').focus();
			return;
		}
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
		if($("#memo").val() =="" ) {
			alert("<spring:message code="user.message.userinfo.alert.input.memo"/>");
			return;
		}
</c:if>
	$("#userInfoLoginForm").submit();
	}
});

function validateMobileNo() {
	if($("#mobileNo1").val().length != 3) return false;
	if($("#mobileNo2").val().length != 4) return false;
	if($("#mobileNo3").val().length != 4) return false;
	return true;
}

function setMobileNo() {
	var mobileNo = $("#mobileNo1").val() + $("#mobileNo2").val() + $("#mobileNo3").val()
	$("#mobileNo").val(mobileNo);
}

function setHomeNo() {
	var homePhoneno = $("#homePhoneno1").val() + $("#homePhoneno2").val() + $("#homePhoneno3").val();
	$("#homePhoneno").val(homePhoneno);
}
</script>

