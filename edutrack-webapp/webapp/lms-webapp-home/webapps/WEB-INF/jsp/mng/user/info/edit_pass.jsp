<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<!-- 	<div class="well well-sm"> -->
<!-- 		<ul class="list2" style="line-height: 20px; list-style-type: none; padding: 0px;"> -->
<%-- 			<li><spring:message code="user.message.userinfo.change.password.msg1" /></li> --%>
<%-- 			<li><spring:message code="user.message.userinfo.change.password.msg3" /></li> --%>
<!-- 		</ul> -->
<!-- 	</div> -->
	<form  onsubmit="return false" id="userInfoForm" name="userInfoForm">
		<input type="hidden" name="userNo" value="${vo.userNo }" id="userNo"/>
		<input type="hidden" name="encryptData" id="encryptData"/>
		<table class="table table-bordered wordbreak">
			<colgroup>
				<col style="width: 30%;" />
				<col />
			</colgroup>
			<tbody>
				<tr>
					<th scope="row"><label for="userPassConfirm"><spring:message code="user.title.userinfo.password.old" /></label></th>
					<td><input type="password" maxlength="100" id="userPassConfirm" name="userPassConfirm" class="form-control input-sm" title="user.title.userinfo.password.old" /></td>
				</tr>
				<tr>
					<th scope="row"><label for="newUserPass"><spring:message code="user.title.userinfo.password.new" /></label></th>
					<td><input type="password" maxlength="16" id="newUserPass" name="newUserPass" class="form-control input-sm" title="user.title.userinfo.password.new" /></td>
				</tr>
				<tr>
					<th scope="row"><label for="newUserPassConfirm"><spring:message code="user.title.userinfo.password.confirm" /></label></th>
					<td><input type="password" maxlength="16" id="newUserPassConfirm" name="newUserPassConfirm" class="form-control input-sm" title="user.title.userinfo.password.confirm" /></td>
				</tr>
			</tbody>
		</table>
		<div class="text-center" style="margin-top: 10px;">
			<a href="#" onclick="editPass()" class="btn btn-primary btn-sm"><spring:message code="button.ok" /></a> 
			<a href="javascript:parent.modalBoxClose()" class="btn btn-default btn-sm"><spring:message code="button.close" /></a>
		</div>
	</form>

<script type="text/javascript">

	//페이지 초기화
	$(document).ready(function() {
	
	});
	
	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		if(!validate(document.userInfoForm)) return;
		$('#userInfoForm').attr("action" , "/mng/user/userInfo/" + cmd);
		$('#userInfoForm').ajaxSubmit(processCallback);
	}
	
	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		
		if(resultDTO.message == "CHECK_PASS") {
			// 정상 처리
			//parent.listCategory();
			alert("<spring:message code="user.message.userinfo.alert.check.oldpassword"/>");
			return;
		}else {
			alert(resultDTO.message);
			parent.modalBoxClose();
		} 
		return;
	}
	
	
	/**
	 * 설정 수정
	 */
	function editPass() {
		if(isEmpty($("#newUserPass").val())) {
			alert("<spring:message code="user.message.userinfo.alert.input.password"/>");
			return false;
		}
		if(isEmpty($("#newUserPassConfirm").val())) {
			alert("<spring:message code="user.message.userinfo.alert.input.password2"/>");
			return false;
		}
		if( $('#newUserPass').val() != $('#newUserPassConfirm').val() ) {
			alert("<spring:message code="user.message.userinfo.notmatch.password"/>");
			return false;
		}
	
		// 데이터 암호화 시도.
		var userNo   = $('#userNo').val();
		var userPassConfirm = $('#userPassConfirm').val(); //기존 비밀번호
		var newUserPass = $('#newUserPass').val();
		var log_pwd  = $('#newUserPassConfirm').val();    //바뀐비밀번호
	
		// 암호화.
		var encrypt = makeSendInfo(userNo,userPassConfirm,newUserPass,log_pwd);
	
		if(!isEmpty(encrypt)) {
			// 암호화에 성공했으면 평문정보가 전송되지 않도록 폼 클리어
			$('#userInfoForm').clearForm();
		}
		// 암호화 데이터를 hidden 항목에 삽입하고 submit
	
		$('#encryptData').val(encrypt);
		
		process("editPass"); // cmd
	
	}

</script>