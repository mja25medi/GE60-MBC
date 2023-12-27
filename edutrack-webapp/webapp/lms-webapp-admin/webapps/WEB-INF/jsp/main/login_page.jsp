<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>

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
    <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading" style="padding:5px 15px 5px 15px;">
                    	<img src="<c:url value="/img/common/login_logo.gif"/>" alt="${system_name}" />
                    </div>
                    <form name="mainForm" id="mainForm" method="POST" >
					<input type="hidden" name="encryptData" id="encryptData" />
                    <div class="panel-body" id="divIdPass">
                    	<h3 style="margin-top:0px;text-shadow: 2px 2px 0px white, -2px 2px 0px white, -2px -2px 0px white, 2px -2px 0px white, 2px 0px 0px white, -2px 0px 0px white, 0px 2px 0px white, 0px -2px 0px white, 3px 3px 7px black">${system_name}</h3>
						<fieldset>
							<div class="form-group">
								<input type="text" name="userId" id="inputId" style="ime-mode: disabled;" placeholder="<spring:message code="user.title.login.userid"/>" autofocus class="form-control" />
							</div>
							<div class="form-group">
								<input type="password" name="userPass" id="inputPwd" placeholder="<spring:message code="user.title.login.password"/>" value="" autocomplete="off" class="form-control" />
							</div>
							<div class="checkbox">
								<label>
									<input name="remember" id="rememberMe" type="checkbox" value="Remember Me"><spring:message code="user.title.login.rememberme" />
								</label>
							</div>
							<!-- Change this to a button or input when using this as a form -->
							<button class="btn btn-lg btn-success btn-block" id="btnLogin"><spring:message code="user.title.login.login"/></button>
						</fieldset>
					</div>
					</form>
				</div>
			</div>
		</div>
	</div>

<script type="text/javascript">

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
		$('#divIdPass input').bind('click keydown', function(event) {
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
				$("#mainForm").attr("action","/adm/main/login");
				//$("#mainForm").submit(); submit이 2번 나가서 주석 처리 해줌.
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
		if (isNotNull(GetCookie('rememberMe'))) {
			$('#inputId').val(GetCookie('rememberMe'));
			$('#rememberMe').attr('checked', 'checked');
		}
	}
</script>