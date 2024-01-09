<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>

	<c:if test="${not empty ALERT_MESSAGE}">
	<div id="sessionflashmsg" style="display:none;">${ALERT_MESSAGE}</div>
		<c:set var="flashMsg" value="${ALERT_MESSAGE}"/><c:remove var="ALERT_MESSAGE" scope="session"/>
		<script type="text/javascript">
			//$.growlUI('알림', '${flashMsg}');
			$(document).ready(function() {
				alert('${flashMsg}');
			});
		</script>
	</c:if>
	<div class="login-box">
  		<div class="login-logo">
    		<a href="/mindex.jsp" target="_top">
				<c:if test="${not empty ADM_LOGOSN}">
				<img src="<c:url value="/app/file/view2/${ADM_LOGOSN}"/>" alt="<spring:message code="user.title.login.title.manage"/>" style="max-width: 320px;"/>
				</c:if>
				<c:if test="${empty ADM_LOGOSN}">
				<span style="line-height:40px;color:#fff;font-size:16px;font-weight: bold;">${USER_ORGNM}</span>
				</c:if>
    		</a>
  		</div>
  		<div class="login-box-body">
    		<p class="login-box-msg" style="font-size:20px;">${USER_DOMAINNM}</p>
			<form name="mainForm" id="mainForm" method="POST">
			<input type="hidden" name="certKey" id="certKey" />
			<input type="hidden" name="encryptData" id="encryptData" />
      		<div class="form-group has-feedback">
      			<input type="text" name="userId" id="inputId" style="ime-mode:disabled;" placeholder="<spring:message code="user.title.login.userid"/>" autofocus class="form-control enterBind" />
        		<span class="glyphicon glyphicon glyphicon-user form-control-feedback"></span>
      		</div>
      		<div class="form-group has-feedback">
      			<input type="password" name="userPass" id="inputPwd" placeholder="<spring:message code="user.title.login.password"/>" value="" autocomplete="off" class="form-control enterBind" />
        		<span class="glyphicon glyphicon-lock form-control-feedback"></span>
      		</div>
      		<div class="row">
        		<div class="col-xs-8">
          			<div class="radio">
            			<label class="pl0">
            				<input name="remember" id="rememberMe" type="checkbox" value="Remember Me"/>&nbsp;<spring:message code="user.title.login.rememberme" />
            			</label>
          			</div>
        		</div>
        		<!-- /.col -->
        		<div class="col-xs-4">
        			<button class="btn btn-primary btn-block btn-flat" id="btnLogin"><spring:message code="user.title.login.login"/></button>
        		</div>
        		<!-- /.col -->
      		</div>
    		</form>

    		<div class="social-auth-links text-center">

    		</div>
    		<!-- /.social-auth-links -->

		</div>
	</div>
<script type="text/javascript">

	var ssoKey = "";
	var ssoIdx = "";
	var returnLogin = "";
	$(document).ready(function() {
		// block tag a, input event
		$('a, input').bind('keydown', function(event) {
			if ($M.Check.Event.isClickEnter(event)) {
				event.preventDefault();
			}
		});
		// get defalut value in cookie
		rememberGet();
	
		// ID/PASS enter event
		$('.enterBind').bind('click keydown', function(event) {
			if ($M.Check.Event.isEnter(event)) {
				if($('#divLoginSubmit').css('display') == 'none') {
					$('#btnLoginConfirm').trigger('click');
				} else {
					$('#btnLogin').trigger('click');
				}
			}
		});
	
		// login button event
		$('#btnLogin').bind('click keydown', function(event) {
			if ($M.Check.Event.isClickEnter(event)) {
				rememberSet();
				if(isEmpty($("#inputId").val())) {
					alert("<spring:message code="user.message.login.alert.input.userid"/>");
					return false;
				}
				if(isEmpty($("#inputPwd").val())) {
					alert("<spring:message code="user.message.login.alert.input.password"/>");
					return false;
				}
				// encrypt data
				var log_id = $('#inputId').val();
				var log_pwd = $('#inputPwd').val();
				var encrypt = makeSendInfo(log_id, log_pwd);
				if (!isEmpty(encrypt)) {
					$('#inputId').val("");
					$('#inputPwd').val("");
				}
				// 암호화 데이터를 hidden 항목에 삽입하고 submit
				$('#encryptData').val(encrypt);
				$("#cmd").val("login");
				$("#mainForm").attr("action","/mng/main/login");
				//$("#mainForm").submit(); 

				
			}
		});
	
		$("#inputId").focus();
	});
	
	
	function rememberSet() {
		// ID 저장하기
		if ($('#rememberMe:checked').length > 0) {
			setCookie('rememberMe', $('#inputId').val(), 1); // 하루 저장
		} else {
			setCookie('rememberMe', '', 0);
		}
	}
	
	function rememberGet() {
		// 쿠키에 저장된 기본값 입력
		if (isNotNull(getCookie('rememberMe'))) {
			$('#inputId').val(getCookie('rememberMe'));
			$('#rememberMe').attr('checked', 'checked');
		}
	}
	

	 function JSONtoString(object) {
		    var results = [];
		    for (var property in object) {
		        var value = object[property];
		        if (value)
		            results.push(property.toString() + ': ' + value);
		        }
		                
		        return '{' + results.join(', ') + '}';
		}
	 
	// sso 로그인 처리
	function ssoLogin(userId, userPass, key, idx) {
		var map = {};
		map.cmd = "securityPwd";
		map.userId = userId;
		map.userPass = userPass;
		map.ssoKey = Base64.decode(key);
		
		$.getJSON("<c:url value="/mng/main"/>", map, function(resultVO){
				var jsonObj = new Object();
				jsonObj.login_id = userId;
				jsonObj.login_pw = resultVO.encUserPass;
// 				jsonObj.idx = ssoIdx;
				jsonObj.idx = idx;
				sendSSOJson("login", jsonObj, "/AdminLogin");
		});
	}
	
</script>