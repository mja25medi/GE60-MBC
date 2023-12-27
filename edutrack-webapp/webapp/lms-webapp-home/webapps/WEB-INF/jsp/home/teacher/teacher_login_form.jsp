<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>
                    <div class="member">
                        <div class="flex-container">
                            <div class="con">
                           
                                <div class="tit-header">
                                    <h2 class="title">강사 로그인</h2>
                                </div>
                                <div class="login_form">
                                    <div class="login_input">
                                        <span class="form-floating">
                                        	<input type="text" id="login_id" class="form-control" placeholder="아이디를 입력해주세요.">
                                            <label for="login_id">아이디를 입력해주세요.</label>                            
                                        </span>
                                        <span class="form-floating">
                                        	<input type="password" id="login_pwd" class="form-control _enterBind" placeholder="비밀번호를 입력해주세요.">
                                             <label for="login_pwd">비밀번호를 입력해주세요.</label>                            
                                        </span>
                                    </div>     
                                     <div class="custom-input idchk_save"></div>                           

                                    <div class="entry_btn">
                                        <button type="button" title="로그인" onclick="login()">로그인</button>
                                    </div>                              
                                </div>
                                
                               
                            </div>

                        </div>
                    </div>

                    <form name="loginForm" id="loginForm" method="post" action="/home/tch/info/login">
						<input type="hidden" name="encryptData" id="encryptData" />
                    </form>
<script type="text/javascript">
	// 페이지 초기화
	$(document).ready(function() {
		$("._enterBind").bind("keydown", function(event){
			if($M.Check.Event.isEnter(event)) {
				login();
			}
		});
	});
	
	function login() {
		if(isEmpty($("#login_id").val())) {
			alert("<spring:message code="user.message.login.alert.input.userid"/>");
			$("#login_id").focus();
			return false;
		}
		if(isEmpty($("#login_pwd").val())) {
			alert("<spring:message code="user.message.login.alert.input.password"/>");
			$("#login_pwd").focus();
			return false;
		}
		var log_id   = $('#login_id').val();
		var log_pwd  = $('#login_pwd').val();
		var encrypt = makeSendInfo(log_id, log_pwd);

		$('#encryptData').val(encrypt);
		document.loginForm.submit();
	}
</script>