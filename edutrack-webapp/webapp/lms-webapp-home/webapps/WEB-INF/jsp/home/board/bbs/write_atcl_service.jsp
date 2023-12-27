<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/common/page_init.jsp"%>
<% // 서브 메뉴 체크 %>
<style>
input::-webkit-outer-spin-button,
input::-webkit-inner-spin-button {
  -webkit-appearance: none;
}
</style>
<form id="frm" name="frm">
	<input type="hidden" name="bbsCd" id="bbsCd" value="${vo.bbsCd}" />
	<div class="detail_cont write">
		기본정보
		<div class="tstyle" style="margin-bottom: 30px;">
			<ul class="dbody">
				<li>
					<div class="row">
						<label for="nameInput" class="form-label col-sm-2">이름</label>
						<div class="col-sm-4">
							<div class="form-row">
								<input class="form-control" name="serviceRegNm" type="text" id="serviceRegNm" class="wmax" title="<spring:message code="home.board.title"/>">
							</div>
						</div>
					</div>
				</li>
				<li>
					<div class="row">
						<label for="emailInput" class="form-label col-sm-2">이메일</label>
						<div class="col-sm-4">
							<div class="form-row">
								<input class="form-control" name="serviceEmail" type="text" id="serviceEmail" class="wmax" title="<spring:message code="home.board.title"/>">
							</div>
						</div>
						<label for="telInput" class="form-label col-sm-2">연락처</label>
						<div class="col-sm-4">
							<div class="form-row">
								<input class="form-control" name="serviceTel" type="hidden" id="serviceTel" class="wmax">
								<input class="form-control" name="serviceTel_F" maxlength="4" type="number" id="serviceTel_F" class="wmax">
								&nbsp;&nbsp;-&nbsp;&nbsp; <input class="form-control" maxlength="4" name="serviceTel_M" type="number" id="serviceTel_M" class="wmax">
								&nbsp;&nbsp;-&nbsp;&nbsp; <input class="form-control" maxlength="4" name="serviceTel_L" type="number" id="serviceTel_L" class="wmax">
							</div>
						</div>
					</div>
				</li>
			</ul>
		</div>
		게시물정보
		<div class="tstyle">
			<ul class="dbody">
				<li>
					<div class="row">
						<label for="titleInput" class="form-label col-sm-2">제목</label>
						<div class="col-sm-10">
							<div class="form-row">
								<input class="form-control" maxlength="100" name="atclTitle" type="text" id="atclTitle" class="wmax" title="<spring:message code="home.board.title"/>">
							</div>
						</div>
					</div>
				</li>
				<li>
					<div class="row">
						<label for="contTextarea" class="form-label col-sm-2">내용</label>
						<div class="col-sm-10">
							<div class="form-row">
								<textarea name="atclCts" id="atclCts" rows="10" style="width: 100%"></textarea>
							</div>
						</div>
					</div>
				</li>
				<li>
					<div class="row">
						<span class="form-label col-sm-2">상담가능날짜</span>
						<div class="col-sm-10">
							<input class="datepicker" name="serviceEnableDate" id="serviceEnableDate">
							<img class="ui-datepicker-trigger" src="/img/framework/icon/icon_calendar.gif" alt="달력" title="달력" onclick="datepickerClick();">
						</div>
					</div>
				</li>
				<li>
					<div class="row">
						<span class="form-label col-sm-2">상담가능시간</span>
						<div class="col-sm-10">
							<select name="serviceEnableTime" class="select-custom1">
								<option value="10">오전 10&nbsp;시</option>
								<option value="11">오전 11&nbsp;시</option>
								<option value="13">오후 1&nbsp;시</option>
								<option value="14">오후 2&nbsp;시</option>
								<option value="15">오후 3&nbsp;시</option>
								<option value="16">오후 4&nbsp;시</option>
								<option value="17">오후 5&nbsp;시</option>
								<option value="18">오후 6&nbsp;시</option>
							</select>
						</div>
					</div>
				</li>
			</ul>
		</div>
		<div class="btns mt30">
			<button type="button" onclick="javascript:addAtcl();" class="btn gray2">등록</button>
			<button type="button" onclick="javascript:viewAtcl();" class="btn type5">취소</button>
		</div>
	</div>
</form>

<script type="text/javascript">
	// 페이지 초기화
	$(document).ready(function() {
		$('.datepicker').datepicker({
			minDate: 1
		});
	});

	function datepickerClick(){
		$(".datepicker").focus();
	}
	
	/* 서브밋 처리 */
	function process(cmd) {
		$('#frm').attr("action","/home/brd/bbs/"+cmd);
 		
		if(!validate(document.frm)) return false;
		
		var password = $("#servicePw").val(); 
		var serviceRegTel = $("#serviceTel_F").val() + $("#serviceTel_M").val() + $("#serviceTel_L").val();
		$("#serviceTel").val(serviceRegTel);
		
		if($("#serviceRegNm").val()==""){
			alert("이름을 입력하세요.");
			return;
		}
		
		if($('#servicePw').val()==""){
			alert("패스워드를 입력하세요.");
			return;
		}
		
		var email = $("#serviceEmail").val();
		if(email==""){
			alert("이메일을 입력하세요.");
			return;
		}
		
		var emailChkRegx = new RegExp("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
		if(!emailChkRegx.test(email)){
			alert("이메일 형식이 올바르지 않습니다.\n다시 입력해주세요.")
			return;
		}
		
		if($("#serviceTel").val()==""){
			alert("연락처을 입력하세요.");
			return;
		}
		
		if($("#atclTitle").val()==""){
			alert("제목을 입력하세요.");
			return;
		}
		
		if($("#atclCts").val()==""){
			alert("내용을 입력하세요.");
			return;
		}
		
		if($("#serviceEnableDate").val()==""){
			alert("상담가능날짜를 입력하세요.");
			return;
		}
		
		var encrypt = makeSendInfo(password);
		
		$('#encryptData').val(encrypt);
		
		$('#frm')[0].submit();
	}

	function addAtcl() {
		process("addAtclService");
	}

	function listAtcl(){
		$("#frm").attr("action","/home/brd/bbs/listAtclMain");
		document.frm.submit();
	}

	</script>