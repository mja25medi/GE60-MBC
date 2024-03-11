<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>

<body>
			<small class="fcDarkGray"><i class="xi-error mr5"></i>휴대폰 인증 및 비밀번호 변경 완료 후에 서비스 이용가능합니다.</small>
            <div class="table txt_small tablet">
            	<table>
            		<tr>
            			<th>휴대폰 본인인증</th>
            			<td><button type="button" class="btn" name="cert_button" onclick="viewUserCheckPop();">인증하기</button></td>
            		</tr>
            	</table>
            </div>

			<small class="fcDarkGray"><i class="xi-error mr5"></i>8~16자의 영문, 숫자, 특수문자를 혼합하여 입력하세요.</small>

			<form  onsubmit="return false" id="userChangePassForm" name="userChangePassForm" method="post">
				<input type="hidden" name="phoneVeriYn" id="phoneVeriYn"/>
				<input type="hidden" name="userNo" value="${vo.userNo }" id="userNo"/>
				<input type="hidden" name="userId" value="${vo.userId }" id="userId"/>
				<input type="hidden" name="encryptData" id="encryptData"/>
				
			<div class="table txt_small tablet">
				<table style="width:100%;">
					<caption class="sr-only"><spring:message code="user.title.userinfo.change.password"/></caption>
					<colgroup>
						<col class="w25" />
						<col />
					</colgroup>
					<tbody>
						<tr>
							<th scope="row"><label for="userPassConfim"><spring:message code="user.title.userinfo.password.old"/></label></th>
							<td>
								<input type="password" maxlength="100" id="userPassConfirm" name="userPassConfirm" class="form-control input-sm" title="user.title.userinfo.password.old" autocomplete="off" style="width: 100%;"/>
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="newUserPass"><spring:message code="user.title.userinfo.password.new"/></label></th>
							<td>
								<input type="password" maxlength="16" id="newUserPass" name="newUserPass" class="form-control input-sm" title="user.title.userinfo.password.new" autocomplete="off" style="float:left;width: 100%;"/>
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="idnum"><spring:message code="user.title.userinfo.password.confirm"/></label></th>
							<td>
								<input type="password" maxlength="16" id="newUserPassConfirm" name="newUserPassConfirm" class="form-control input-sm" title="user.title.userinfo.password.confirm" autocomplete="off" style="width: 100%;"/>
							</td>
						</tr>
					</tbody>
				</table>				
			</div>
			<div class="text-center fr btn-group" style="margin-top:10px;">
				<a href="javascript:initVerify();" class="btn solid dark"><spring:message code="button.ok" /></a>
			</div>			
			</form>
<script type="text/javascript">
	var certificated = false;
	$(".modal_close").remove();
	/**
	 * 처리 결과 표시 콜백
	 */
	function changePwdCallback(resultDTO) {
		if(resultDTO.result == 1) {
			alert(resultDTO.message);
			//modalBoxClose();
			parent.modalBox.close();
			parent.document.location.reload();
		}else {
			alert(resultDTO.message);
		} 
		return;
	}
	
	function checkPassword(pwd){
		  // 비밀번호 포맷 확인(영문, 특수문자, 숫자 포함 8자 이상, 16자 이하)
		  if(!/^(?=.*[a-zA-Z])(?=.*\d)(?=.*\W).{8,16}$/.test(pwd)){
		    return false;
		  }
		  return true;
	}
	
	/**
	 * 설정 수정
	 */
	function initVerify() {
		if(isEmpty($("#userChangePassForm #newUserPass").val())) {
			alert("<spring:message code="user.message.userinfo.alert.input.password"/>");
			return;
		}
		if(!checkPassword($('#userChangePassForm #newUserPass').val())) {
			alert("비밀번호는 영문과 특수문자 숫자를 포함하며 8자 이상, 16자 이하 이어야 합니다.");
			return;
		}
		if(isEmpty($("#userChangePassForm #newUserPassConfirm").val())) {
			alert("<spring:message code="user.message.userinfo.alert.input.password2"/>");
			return;
		}
		if( $('#userChangePassForm #newUserPass').val() != $('#userChangePassForm #newUserPassConfirm').val() ) {
			alert("<spring:message code="user.message.userinfo.notmatch.password"/>");
			return;
		}
		if(!validateCert()) {
			alert("본인 인증을 완료해주십시오.");
			return
		}
	
		// 데이터 암호화 시도.
		var userNo   = $('#userChangePassForm #userNo').val();
		var userPassConfirm = $('#userChangePassForm #userPassConfirm').val(); //기존 비밀번호
		var newUserPass = $('#userChangePassForm #newUserPass').val();
		var log_pwd  = $('#userChangePassForm #newUserPassConfirm').val();    //바뀐비밀번호
	
		// 암호화.
		var encrypt = makeSendInfo(userNo,userPassConfirm,newUserPass,log_pwd);
	
		if(!isEmpty(encrypt)) {
			// 암호화에 성공했으면 평문정보가 전송되지 않도록 폼 클리어
			$('#userChangePassForm #userChangePassForm').clearForm();
		}
		// 암호화 데이터를 hidden 항목에 삽입하고 submit
	
		$('#userChangePassForm #encryptData').val(encrypt);
		
		if(!validate(document.userChangePassForm)) return;
		
		$("#phoneVeriYn").val("Y");
		
		$('#userChangePassForm').attr("action", "/home/user/initVerify");
		$('#userChangePassForm').ajaxSubmit(changePwdCallback);
	}
	
	function validateCert() {
		return certificated;
	}
	
	function viewUserCheckPop() {
		var checkUrl = cUrl("/home/etc/niceCheck/viewUserCheck");
		window.open(checkUrl, 'popupChk', 'width=400, height=400, top=110, left=110, fullscreen=no, menubar=no, status=no, toolbar=no, titlebar=yes, location=no, scrollbar=no');
	}
	
	window.completeCert = function() {
		alert("인증이 완료되었습니다.");
		$("button[name=cert_button]").addClass("disabled");
		$("button[name=cert_button]").empty();
		$("button[name=cert_button]").append("<i class='xi-check' aria-hidden='true'></i> 인증완료");
		certificated = true;
	}
	
	window.failCert = function(errorCode) {
		alert("인증이 실패하였습니다. 에러코드 : " + errorCode);
	}
	
	function validateCert() {
		return certificated;
	}
</script>
</body>
