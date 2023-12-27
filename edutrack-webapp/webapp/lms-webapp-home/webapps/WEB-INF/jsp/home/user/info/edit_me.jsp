<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<c:set var="vo" value="${vo}"/>
<c:set var="userInfoCfgList" value="${userInfoCfgList}"/>
			<form name="userInfoForm" id="userInfoForm" method="POST" >
				<input type="hidden" name="userNo" id="userNo" value="${vo.userNo}"/>
				<input type="hidden" name="userSts" id="userSts" value="${vo.userSts}"/>
				<input type="hidden" name="orgCd"  id="orgCd" value="${vo.orgCd}"/>
				<input type="hidden" name="mobileNo"  id="mobileNo" value="${vo.mobileNo }" />
				<input type="hidden" name="msgRecv"  id="msgRecv" value="Y" />

                    <div class="txt_info"><i class="icon_star"></i>필수 입력 사항입니다.</div>
                    <div class="join_form">
                        <div class="tstyle mb30">
                            <ul class="dbody">
                              <c:forEach items="${userInfoCfgList}" var="cfgItem" varStatus="status">     
                                <c:if test="${cfgItem.fieldNm eq 'USERNM' }">
									<c:set var="useUsernm" value="${cfgItem.useYn }"/>
									<c:set var="requiredUsernm" value="${cfgItem.requiredYn }"/>
									<c:if test="${cfgItem.requiredYn eq 'Y'}"><c:set var="isNull" value="N" /></c:if>
									<c:if test="${cfgItem.requiredYn eq 'N'}"><c:set var="isNull" value="Y" /></c:if>
									<li <c:if test="${cfgItem.useYn eq 'N'}">style="display:none;"</c:if>>
	                                    <div class="row">
	                                        <label for="nameInput" class="form-label col-sm-2">이름
	                                        <c:if test="${cfgItem.requiredYn eq 'Y'}">
	                                        	<i class="icon_star" aria-hidden="true"></i>
	                                        </c:if>
	                                        </label>
	                                        </label>
	                                        <div class="col-sm-10">
	                                            <div class="form-row">
	                                                <input class="form-control w50" name="userNm" type="text" id="userNm" value="${vo.userNm }" readonly>
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
		                                        <label for="birthdayInput" class="form-label col-sm-2">생년월일
		                                        <c:if test="${cfgItem.requiredYn eq 'Y'}">
		                                        <i class="icon_star" aria-hidden="true"></i>
		                                        </c:if>
		                                        </label>
		                                        <div class="col-sm-10">
		                                            <div class="form-row">
		                                                <input class="form-control w50" type="text" name="birth" id="birth" value="${vo.birth }" maxlength="7" readonly>
		                                            </div>                                            
		                                        </div>
		                                    </div>
                                	</li>
                                	</c:if>
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
	                                                <input class="form-control w50" type="text" name="userId" id="userId" value="${vo.userId }" readOnly>
	                                            </div>                                           
	                                        </div>
	                                    </div>
	                                </li>
	                                <li>
	                                    <div class="row">
	                                        <label for="pwInput" class="form-label col-sm-2">비밀번호<i class="icon_star" aria-hidden="true"></i></label>
	                                        <div class="col-sm-10">
	                                            <div class="form-row">
	                                                <button type="button" id="" class="btn gray2 ml5" onclick="changePasswd()">비밀번호 변경</button>
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
	                                        <label for="inputEmail1" class="form-label col-sm-2"><span>이메일주소</span>
	                                        <c:if test="${cfgItem.requiredYn eq 'Y'}">
	                                        	<i class="icon_star" aria-hidden="true"></i>
	                                        </c:if>
	                                        </label>
	                                        <div class="col-sm-10">
	                                            <div class="form-row">
	                                                <input class="form-control w50" type="text" name="email" id="inputEmail" value="${vo.email }" readOnly>
	                                                <button type="button" class="btn gray2 ml5" onclick="changeEmailPop()">이메일 변경</button>
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
	                                                <input class="form-control" type="text" name="homePostNo" id="inputAddress1" placeholder="우편번호" onclick="searchAddress()" value="${vo.homePostNo }" readonly style="background-color: white;">
	                                                <button type="button" class="btn gray2 ml5" onclick="searchAddress()">검색</button>
	                                            </div>
	                        
	                                            <div class="form-row">
	                                                <input class="form-control mr5" type="text" name="homeAddr1" id="inputAddress2" title="시군구 주소를 입력하세요" placeholder="시군구 주소" onclick="searchAddress()" value="${vo.homeAddr1 }" readonly style="background-color: white;">
	                                            </div>
	                                            <div class="form-row">
	                                                <input class="form-control mr5" type="text" name="homeAddr2" id="inputAddress3" title="시군구 상세 주소를 입력하세요" placeholder="상세 주소" value="${vo.homeAddr2 }" maxlength="30">
	                                            </div>
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
	                                            <select class="form-select w20" id="mobileNo1">
	                                                <option value="">선택</option>
	                                                <option value="010" <c:if test="${fn:substring(vo.mobileNo,0,3) eq '010'}">selected</c:if>>010</option>
	                                                <option value="016" <c:if test="${fn:substring(vo.mobileNo,0,3) eq '016'}">selected</c:if>>016</option>
	                                            </select>                                                                        
	                                            <input class="form-control mr5" type="text" name="title" value="${fn:substring(vo.mobileNo,3,7) }" id="mobileNo2" title="전화번호 두번째자리">
	                                            <input class="form-control" type="text" name="title" value="${fn:substring(vo.mobileNo,7,11) }" id="mobileNo3" title="전화번호 세번째자리">                           
	                                            </div>
	                                        </div>
	                                    </div>
	                                </li>
	                                </c:if>
                                </c:forEach>
                                <li>
                                    <div class="row">
                                        <span class="form-label col-sm-2"><span>메일수신여부</span><i class="icon_star" aria-hidden="true"></i></span>
                                        <div class="col-sm-10 form-inline">
                                            <span class="custom-input">
                                                <input type="radio" name="emailRecv" id="emailRecvY" value="Y" <c:if test="${vo.emailRecv ne 'N' }">checked</c:if>>
                                                <label for="emailRecvY">수신</label>
                                            </span>
                                            <span class="custom-input ml5">
                                                <input type="radio" name="emailRecv" id="emailRecvN" value="N" <c:if test="${vo.emailRecv eq 'N' }">checked</c:if>>
                                                <label for="emailRecvN">수신거부</label>
                                            </span>
                                        </div>
                                    </div>
                                </li> 
                                <li>
                                    <div class="row">
                                        <span class="form-label col-sm-2"><span>문자수신여부</span><i class="icon_star" aria-hidden="true"></i></span>
                                        <div class="col-sm-10 form-inline">
                                            <span class="custom-input">
                                                <input type="radio" name="smsRecv" id="smsRecvY" value="Y" <c:if test="${vo.smsRecv ne 'N' }">checked</c:if>>
                                                <label for="smsRecvY">수신</label>
                                            </span>
                                            <span class="custom-input ml5">
                                                <input type="radio" name="smsRecv" id="smsRecvN" value="N" <c:if test="${vo.smsRecv eq 'N' }">checked</c:if>>
                                                <label for="smsRecvN">수신거부</label>
                                            </span>
                                        </div>
                                    </div>
                                </li>                                         
                            </ul>
                            
                        </div>
                    </div>
                    <!-- // 정보입력 --> 
                    
                    <div class="btns mt20">
                        
                            <button class="btn type4" onclick="editUser();">확인</button>
                            <button class="btn" onclick="<c:url value="/home/main/goMenuPage"/>?mcd=MC0000000000">취소</button>
                            <button class="btn gray2" onclick="joinOutUser()">회원탈퇴</button>
                        
                    </div>
		</form>
<script type="text/javascript">
	var originOrgCd = '${vo.orgCd}';
	var daumAddTheme = {
			   searchBgColor: "#0B65C8", //검색창 배경색
			   queryTextColor: "#FFFFFF" //검색창 글자색
			};
	var daumAddress = null;
	// 페이지 초기화
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
		modalBox = new $M.ModalDialog({
			"modalid" : "modal1"
		});
		
		$( "#changePasswd-modal" ).dialog({
			autoOpen: false,
			modal: true ,
			width:'550',
			height:'auto' 
		});
		
		$( "#changePwdBtn" ).click(function() {
			$("#changePasswd-modal").load("<c:url value="/home/user/editPassForm"/>", function(){
				$( "#changePasswd-modal" ).dialog( "open" );
			});
		});
		
		
		// 이미지 파일 첨부 바인딩 및 초기화

		/*
		$('.inputNumber').inputNumber();	// 숫자만 입력되도록 설정.
		$('.inputSpecial').inputSpecial();  // 특수키가 입력이 들어오묜 false return;
		*/
		
		/* usrPhotoFile = new $M.JqueryFileUpload({
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
						}); */
		
		checkBirthDay();
		emailSet();
		
	});
	
	function searchAddress() {
		daumAddress.open();
	}

	function changePasswd() {
		var addContent  = "<iframe id='viewPagePop2' name='viewPagePop2' style='width:100%; height:410px'"
			+ "frameborder='0' src='/home/user/editPassFormPop2'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.setTitle("<spring:message code="user.title.userinfo.change.password"/>");
		modalBox.show();	
	}
	
	function changeEmailPop() {
		var addContent  = "<iframe id='viewPagePop2' name='viewPagePop2' style='width:100%; height:210px'"
			+ "frameborder='0' src='/home/user/editEmailPop2'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.setTitle("이메일 변경");
		$(".modal_popup").css("width","120rem")
		modalBox.show();	
	}
	
	function viewSearchDeptPop() {
		modalBox.clear();
		modalBox.addContents('<c:url value="/home/user/viewSearchDeptPop2"/>');
		modalBox.setTitle("기업 검색");
		modalBox.show();
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
	function validatePass() {
		if (validateUserPass() && validateUserPassChk()) {
			return true;
		} else {
			return false;
		}
	}
	
	//비밀번호 적합성 체크
	function validateUserPass() {
		if(isEmpty($("#userPass").val())) {
			$("#userPassInfo span.sec").hide();
			$("#userPassInfo #invalidInputUserPassMsg").show();
			return false;
		}
		
		$("#userPassInfo span.sec").hide();
		if(($("#userPass").val().length < 6) || ($("#userPass").val().length > 12) ){
			$("#userPassInfo #invalidInputUserPassMsg").show();
			$("#userPass").val("");
			return false;
		} else {
			$("#userPassInfo #inputUserPassMsg").show();
		}
		
		$("#userPassChkInfo span.sec").hide();
		if(isEmpty($("#userPassChk").val())) {
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
	
	function validateEmail() {
		return !(isEmpty($("#email_id").val()) || isEmpty($("#email_domain_text").val()));
	}
	
	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		$('#userInfoForm').attr("action" ,"/home/user/" + cmd);
		$('#userInfoForm').ajaxSubmit(userUpdateCallback);
	}
	
	function userUpdateCallback(resultVO) {
		alert(resultVO.message);

		if(resultVO.result >= 0) {
			// 정상 처리
			window.location.reload();
		} else {
			// 비정상 처리
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
			return true;
	}
	
	/**
	 * 사용자 수정
	 */
	function editUser() {
		//필수값 체크
		if(!validate(document.userInfoForm)) return;
<c:if test="${not IS_SSO}">

	<c:if test="${useSex eq 'Y' && requiredSex eq 'Y' }">
		if(!$("#sexCdM").is(":checked") && !$("#sexCdF").is(":checked") ){
			alert("<spring:message code="user.message.userinfo.choice.sex"/>");
			return;
		}
	</c:if>
</c:if>	
		if(!validateMobileNo()) {
			alert("휴대폰 번호를 올바르게 입력해 주세요.");
			return;
		}
		setMobileNo();
		setHomeNo();
		if(confirm("<spring:message code="home.user.edituserconfirm"/>")){
			process("editUserInfo");	// cmd
		}
	}
	
	
	function setTitle(obj){
		var selectObj = obj;
		var selectTitle = "";
		selectTitle = selectObj.options[selectObj.selectedIndex].text;
		$(selectObj).attr("title",selectTitle);
	}
	
	function uploderclick(str) {
		$("#"+str).click();
	}
	
	function emailVal(email){
		$("#inputEmail").val(email);
	}
	
	//-- 사용자 탈퇴 처리
	function joinOutUser() {
		if(!confirm("탈퇴진행 시 모든 개인정보가 삭제되어 복구가 불가능하며, 동일한 아이디로 재가입이 불가능합니다.\n탈퇴 하시겠습니까?")){
			return;
		}
		
		var userNo = $("#userInfoForm #userNo").val();
		
		if(userNo != '${USERNO}'){
			alert("회원정보가 일치하지 않습니다. 새로고침 후 다시 시도해주세요.");
			return;
		}
		
		$.getJSON( "<c:url value="/home/user/joinOut"/>", 
				{"userNo" : userNo },				// params
							function(data) {
				 	  			if(data.result >= 0) {
									alert('<spring:message code="user.message.userinfo.leave.success"/>');
									location.href="/home/main/indexMain";
								} else {
									alert('ERROR! \n<spring:message code="user.message.userinfo.leave.failed"/>');
							}
						});

	}
	
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
		var homeNo = $("#homePhoneno1").val() + $("#homePhoneno2").val() + $("#homePhoneno3").val();
		$("#homePhoneno").val(homeNo);
	}
	
	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}
	
</script>