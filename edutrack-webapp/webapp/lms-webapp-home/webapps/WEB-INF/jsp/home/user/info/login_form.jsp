<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>
                    
                <!-- content -->
                <div id="content" class="content">
                    
                    <div class="member">
                        <div class="flex-container">
                            <div class="con">
                           
                                <div class="tit-header">
                                    <h2 class="title">로그인</h2>
                                </div>
                                <div class="login_form">
                                    <div class="login_input">
                                        <span class="form-floating">
                                            <input type="text" id="login_id" class="form-control _enterBind" placeholder="아이디를 입력해주세요.">
                                            <label for="login_id">아이디를 입력해주세요.</label>                            
                                        </span>
                                        <span class="form-floating">
                                            <input type="password" id="login_pwd" class="form-control _enterBind" placeholder="비밀번호를 입력해주세요.">
                                            <label for="login_pwd">비밀번호를 입력해주세요.</label>                            
                                        </span>
                                    </div>     
                                    <div class="custom-input idchk_save"><input type="checkbox" id="loginChkIdSave"><label for="loginChkIdSave">아이디 저장</label></div>                           

                                    <div class="entry_btn">
                                        <button type="submit" title="로그인" class="type1 enterLogin" id="login_button" onclick="login()">로그인</button>
                                    </div>                              
                                </div>
                                <div class="login_foot">
                                    <ul class="list_dot">
                                        <li>
                                            <a href="/home/main/goMenuPage?mcd=HM04002000" class="btn"><span>회원가입</span></a>
                                        </li>
                                        <li>
                                            <a href="javascript:searchIdModal();" class="btn"><span>아이디 찾기</span></a>
                                        </li>
                                        <li>
                                            <a href="javascript:searchPwModal();" class="btn"><span>비밀번호 찾기</span></a>
                                        </li>
                                    </ul>
                                </div>
                                <div class="ui horizontal divider">간편 로그인</div>

                                <div class="btns-join center mt0">
                                    <a href="#0" class="btn kakao" onClick="javascript:joinKakao()">카카오 아이디로 로그인</a>
               						<a href="#0" class="btn naver" onClick="javascript:joinNaver()">네이버 아이디로 로그인</a>                         
                                </div>
                            </div>

                        </div>
                    </div>
                       
                        
                </div>
                <!-- //content -->
              
                <form name="loginForm" id="loginForm" method="post" action="/home/user/login">
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
		
		$(".subMenu_title").hide();
		
		rememberGet();
	});
	
	function login() {
		
		rememberSet();
		
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

 <script type="text/javascript">
	var modalBox = null;
	var modalBox2 = null;
	var ItemVO1 = {};
	
	ItemVO1.searchId 	= 'F';
	ItemVO1.searchPass 	= 'F';
	ItemVO1.motpPass 	= 'F';
	ItemVO1.returnMethod 	= 'F';
	$(document).ready(function() {
		modalBox = new $M.ModalDialog({
			"modalid" : "modal1",
			"nomargin" : false //m_large
		});
		modalBox2 = new $M.ModalDialog({
			"modalid" : "modal2",
			"nomargin" : false //m_large
		});
	});
	
	function rememberSet() {
		// ID 저장하기
		if ($('#loginChkIdSave:checked').length > 0) {
			setCookie('loginChkIdSave', $('#login_id').val(), 1); // 하루 저장
		} else {
			setCookie('loginChkIdSave', '', 0);
		}
	}

	function rememberGet() {
		// 쿠키에 저장된 기본값 입력
		if (isNotNull(GetCookie('loginChkIdSave'))) {
			$('#login_id').val(GetCookie('loginChkIdSave'));
			$('#loginChkIdSave').attr('checked', 'checked');
		}
	}
	
	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}
	
	function modalBoxClose2() {
		modalBox2.clear();
		modalBox2.close();
	}
	
	function searchIdModal(){
		var addContent  = "<iframe id='searchId' name='searchId' style='width:100%; height:310px'"
			+ "frameborder='0' src='<c:url value="/home/user/searchIdModalPop2"/>"
			+ "'/>";
		modalBox.clear();
		//modalBox2.addContents('<c:url value="/home/user/searchIdModalPop2"/>');
		modalBox2.addContents(addContent);
		modalBox2.setTitle("아이디 찾기");
		modalBox2.show();
	}
	function showModalBox() {
		modalBoxClose();
		modalBox2.show();
		modalBox2.func(ItemVO1.returnMethod);
	}
	
	function searchPwModal(){
		var addContent  = "<iframe id='searchId' name='searchId' style='width:100%; height:410px'"
			+ "frameborder='0' src='<c:url value="/home/user/searchPwModalPop2"/>"
			+ "'/>";
		modalBox2.clear();
		/* modalBox2.addContents('<c:url value="/home/user/searchPwModalPop2"/>'); */
		modalBox2.addContents(addContent);
		modalBox2.setTitle("비밀번호 찾기");
		modalBox2.show();
	}
	
	function goLoginCheck(){
		alert("로그인이 필요한 서비스입니다.");
		document.location.href = "/home/main/goMenuPage?mcd=HM04001000";
	}
	

	
    function viewRecvMsgPop() {
		modalBox.clear();
		modalBox.addContents('<c:url value="/home/message/listRecvPop2"/>');
		modalBox.setTitle("받은 쪽지함");
		modalBox.show();
    }

    function joinKakao() {
    	window.open('${kakaoAuthUrl}', 'kakaologinpop', 'titlebar=1, resizable=1, scrollbars=yes, width=600, height=550');
    }

    function joinNaver() {
    	window.open('${naverAuthUrl}', 'naverloginpop', 'titlebar=1, resizable=1, scrollbars=yes, width=600, height=550');
    }


</script>