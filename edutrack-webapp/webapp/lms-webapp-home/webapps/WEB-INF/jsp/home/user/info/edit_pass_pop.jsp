<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>

	<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
	<script src="/js/nuguya/nice.nuguya.oivs.crypto.js"></script>
	<script src="/js/nuguya/nice.nuguya.oivs.msg.js"></script>
	<script src="/js/nuguya/nice.nuguya.oivs.util.js"></script>	
	<script src="/tpl/002/js/jquery/jquery.form.js"></script>

    <link rel="stylesheet" href="/tpl/003/css/hrd_common.css">
    <link rel="stylesheet" href="/tpl/003/css/webfonts.css">
    <link rel="stylesheet" href="/tpl/003/css/layout.css">    
    <link rel="stylesheet" href="/tpl/003/css/effect_slick.css">
    <link rel="stylesheet" href="/tpl/003/css/sub.css"><!-- sub 페이지에서 사용 -->

			<div class="modal_pad">
				<div class="well well-sm">
					<ul class="list2" style="line-height:20px;list-style-type:none;padding:0px;">
						<li><spring:message code="user.message.userinfo.change.password.msg1"/></li>
						<li>8~16자의 영문, 숫자, 특수문자 혼합하여 입력하세요.</li>
					</ul>
				</div>
        		<div class="tstyle">
        			<form  onsubmit="return false" id="userChangePassForm" name="userChangePassForm">
						<input type="hidden" name="userNo" value="${vo.userNo }" id="userNo"/>
						<input type="hidden" name="userId" value="${vo.userId }" id="userId"/>
						<input type="hidden" name="encryptData" id="encryptData"/>
	            		<ul class="dbody">
	            			 <li>
			                    <div class="row">
			                        <label class="form-label col-sm-2"><spring:message code="user.title.userinfo.password.old"/></label>
			                        <div class="col-sm-10">
			                        	<div class="form-row">
				                        	<input class="form-control" type="password" name="userPassConfirm" id="userPassConfirm" value="" maxlength="100" title="user.title.userinfo.password.old" autocomplete="off">
			                        	</div>
			                        </div>
			                    </div>
			                </li>
	            			 <li>
			                    <div class="row">
			                        <label class="form-label col-sm-2"><spring:message code="user.title.userinfo.password.new"/></label>
			                        <div class="col-sm-10">
			                        	<div class="form-row">
			                        		<input class="form-control" type="password" name="newUserPass" id="newUserPass" value="" maxlength="16" title="user.title.userinfo.password.new" autocomplete="off">
			                        	</div>
			                        </div>
			                    </div>
			                </li>
	            			 <li>
			                    <div class="row">
			                        <label class="form-label col-sm-2"><spring:message code="user.title.userinfo.password.confirm"/></label>
			                        <div class="col-sm-10">
			                        	<div class="form-row">
			                        		<input class="form-control" type="password" name="newUserPassConfirm" id="newUserPassConfirm" value="" maxlength="16" title="user.title.userinfo.password.confirm" autocomplete="off">
			                        	</div>
			                        </div>
			                    </div>
			                </li>
	            		</ul>
            		</form>
            	</div>
            </div>
		    <div class="modal_btns">
		        <button type="button" class="btn type4" onclick="editPass();"><spring:message code="button.ok" /></button>
	        	<button type="button" class="btn" onclick="javascript:parent.modalBoxClose();">취소</button>
		    </div>

<script type="text/javascript">
	/**
	 * 처리 결과 표시 콜백
	 */
	function changePwdCallback(resultDTO) {
		if(resultDTO.message == "CHECK_PASS") {
			alert("<spring:message code="user.message.userinfo.alert.check.oldpassword"/>");
			return;
		}else {
			alert(resultDTO.message);
			modalBoxClose();
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
	function editPass() {
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
	
		// 데이터 암호화 시도.
		var userNo   = $('#userChangePassForm #userNo').val();
		var userPassConfirm = $('#userChangePassForm #userPassConfirm').val(); //기존 비밀번호
		var newUserPass = $('#userChangePassForm #newUserPass').val();
		var log_pwd  = $('#userChangePassForm #newUserPassConfirm').val();    //바뀐비밀번호
	
		// 암호화.
		var encrypt = makeSendInfo(userNo,userPassConfirm,newUserPass,log_pwd);
	
		if(!isEmpty(encrypt)) {
			// 암호화에 성공했으면 평문정보가 전송되지 않도록 폼 클리어
			//$('#userChangePassForm').clearForm();
			$('input[type="text"],​input[type="password"]').val('');
		}
		
		// 암호화 데이터를 hidden 항목에 삽입하고 submit
		$('#encryptData').val(encrypt);

		//if(!validate(document.userChangePassForm)) return;	
		//$('#userChangePassForm').attr("action", "/home/user/editPass");
		//$('#userChangePassForm').ajaxSubmit(changePwdCallback);
		
		var userChangePassForm = $("#userChangePassForm").serialize();
		
		$.ajax({
			url : '/home/user/editPass',
			data : userChangePassForm,
			success : function(result) {
				if(result.message == "CHECK_PASS") {
					alert("기존 비밀번호를 확인해 주세요.");
					return;
				}else {
					alert("비밀번호 변경이 완료되었습니다.");
					parent.modalBoxClose();
				} 
				return;
			}
			,error : function(request,status,error) {
			}
		});
		
	}
	
	function isEmpty(str) {
		for (var i = 0; i < str.length; i++) {
			if (str.substring(i, i+1) != " ") {
				return false;
			}
		}
		return true;
	}

</script>
</body>
