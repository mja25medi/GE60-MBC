<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>						
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
    <link rel="stylesheet" href="/tpl/003/css/hrd_common.css">
    <link rel="stylesheet" href="/tpl/003/css/webfonts.css">
    <link rel="stylesheet" href="/tpl/003/css/layout.css">    
    <link rel="stylesheet" href="/tpl/003/css/effect_slick.css">
    <link rel="stylesheet" href="/tpl/003/css/sub.css"><!-- sub 페이지에서 사용 -->

	<div class="modal_cont">
	    <div class="modal_pad">
	        <div class="tstyle txt_find">
	            <ul class="dbody">
	            	<li>                                   
	                    <label for="nameInput" class="form-label col-sm-3">아이디</label>
	                    <div class="col-sm-12">
	                        <div class="form-row">
	                            <input class="form-control w100" type="text" name="userId" id="passSearchUserID">
	                        </div>
	                        <small class="note">아이디를 입력하세요</small>     
	                    </div>                                   
	                </li>    
	                <li>                                   
	                    <label for="nameInput" class="form-label col-sm-3">이름</label>
	                    <div class="col-sm-12">
	                        <div class="form-row">
	                            <input class="form-control w100" type="text" name="userName" id="passSearchUserName">
	                        </div>
	                        <small class="note">이름을 입력하세요</small>     
	                    </div>                                   
	                </li>      
	                <li>                                    
	                    <label for="nameInput" class="form-label col-sm-3">이메일주소</label>
	                    <div class="col-sm-12">
	                        <div class="form-row">
	                            <input class="form-control w100" type="text" name="email" value="" id="passSearchEmail">
	                        </div>
	                        <small class="note">회원가입시 등록한 이메일주소를 입력하세요.</small> 
	                    </div>                                    
	                </li>   
	            </ul>
	        </div>
	    </div>
	</div>
										
    <div class="modal_btns">
        <button type="button" class="btn" onclick="javascript:parent.modalBoxClose2();">취소</button>
        <button type="button" class="btn type4" onclick="javascript:passSearch();">다음으로</button>
    </div>
    
<script type="text/javascript">
	parent.ItemVO1 = {};
	$(document).ready(function() {
		parent.ItemVO1.searchPass 	= 'F';
		parent.ItemVO1.motpPass 	= 'F';
		parent.ItemVO1.returnMethod 	= 'F';
	});

	function passSearch() {
		parent.ItemVO1.returnMethod = 'passSearch';
		if(parent.ItemVO1.searchPass == 'F'){
			var userId = $("#passSearchUserID").val();
			var userNm = $("#passSearchUserName").val();
			var email = $("#passSearchEmail").val();

			if(userId == "" ) {
				alert('<spring:message code="user.message.login.search.alert.input.userid"/>');
				return;
			}
			if(userNm == "") {
				alert('<spring:message code="user.message.login.search.alert.input.name"/>');
				return;
			}
			if(email == "") {
				alert('<spring:message code="user.message.login.search.alert.input.email"/>');
				return;
			}

 			if(parent.ItemVO1.motpPass == 'F'){
				pop_motp();
			}else{
				pop_motp_close();
			}
		}else if(parent.ItemVO1.searchPass == 'S'){
			pop_motp_close();
		}
	}

	function reloadPage() {
		document.location.reload();
	}
	
	function pop_motp(){
		var addContent = "<iframe id='viewMotpForm' name='viewMotpForm' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/mng/etc/HrdApi/viewMotp"/>'/>";
		parent.modalBox2.close();
		parent.modalBox.clear();
		parent.modalBox.resize(500, 550);
		parent.modalBox.addContents(addContent);
		parent.modalBox.setTitle("mOTP 인증");
		parent.modalBox.show();
	}
	
	function pop_motp_close(){

		var userId = $("#passSearchUserID").val();
		var userNm = $("#passSearchUserName").val();
		var email = $("#passSearchEmail").val();
		
/* 		$("#passSearchResult")
		.load(
			cUrl("/home/user/findPass"),
			{
				"userId":userId,
				"userNm":userNm,
				"email":email
			}
			) */
			
		$.ajax({
			url : '/home/user/findPass'
			,data : {
				"userId":userId,
				"userNm":userNm,
				"email":email
			}
			,success : function(result) {
				$(".modal_btns").hide();
				$(".dbody").hide();

				$(".tstyle").append(result);

			}
			,error : function(request,status,error) {
			}
		});

		parent.ItemVO1.searchPass 	= 'S';
	}

</script>
  