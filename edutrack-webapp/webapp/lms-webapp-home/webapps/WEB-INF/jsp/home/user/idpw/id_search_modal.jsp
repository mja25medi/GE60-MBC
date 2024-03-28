<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
    <link rel="stylesheet" href="/tpl/${COLOR_TPL}/css/hrd_common.css">
    <link rel="stylesheet" href="/tpl/${COLOR_TPL}/css/webfonts.css">
    <link rel="stylesheet" href="/tpl/${COLOR_TPL}/css/layout.css">    
    <link rel="stylesheet" href="/tpl/${COLOR_TPL}/css/effect_slick.css">
    <link rel="stylesheet" href="/tpl/${COLOR_TPL}/css/sub.css"><!-- sub 페이지에서 사용 -->

	<div class="modal_cont">
	    <div class="modal_pad">
	        <div class="tstyle txt_find">
	            <ul class="dbody">
	                <li>                                   
	                    <label for="nameInput" class="form-label col-sm-3">이름</label>
	                    <div class="col-sm-12">
	                        <div class="form-row">
	                            <input class="form-control w100" type="text" name="userName" id="idSearchUserName">
	                        </div>
	                        <small class="note">이름을 입력하세요</small>     
	                    </div>                                   
	                </li>      
	                <li>                                    
	                    <label for="nameInput" class="form-label col-sm-3">이메일주소</label>
	                    <div class="col-sm-12">
	                        <div class="form-row">
	                            <input class="form-control w100" type="text" name="email" value="" id="idSearchEmail">
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
        <button type="button" class="btn type4" onclick="javascript:idSearch();">다음으로</button>
    </div>



<script type="text/javascript">

function idSearch() {
	parent.ItemVO1.returnMethod = 'idSearch';

	if(parent.ItemVO1.searchId == 'F'){

		var userNm = document.getElementById('idSearchUserName');
		var email = document.getElementById('idSearchEmail');

		if(userNm.value == "") {
			alert('<spring:message code="user.message.login.search.alert.input.name"/>');
			return;
		}
		if(email.value == "") {
			alert('<spring:message code="user.message.login.search.alert.input.email"/>');
			return;
		}
		
		//pop_motp_close();
  		if(parent.ItemVO1.motpPass == 'F'){
			pop_motp();
		}else{
			pop_motp_close();
		}
	}else if(parent.ItemVO1.searchId == 'S'){
		pop_motp_close();
	}
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

	var userNm = $("#idSearchUserName").val();
	var email = $("#idSearchEmail").val();
	
/* 	$("#idSearchResult")
		.load(
			cUrl("/home/user/findId"),
			{
				"userNm":userNm,
				"email":email
			}
		) */
		
		$.ajax({
			url : '/home/user/findId'
			,data : {
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
		
		parent.ItemVO1.searchId 	= 'S';
}
</script>
  