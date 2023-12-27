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
		<div class="tstyle" style="margin-bottom: 30px;">
			<ul class="dbody">
				<li>
					<div class="row">
						<label for="nameInput" class="form-label col-sm-2">기업명</label>
						<div class="col-sm-4">
							<div class="form-row">
								<input class="form-control" name="companyName" type="text" id="companyName" class="wmax" maxlength="30">
							</div>
						</div>
					</div>
				</li>
				<li>
					<div class="row">
						<label for="nameInput" class="form-label col-sm-2">담당자</label>
						<div class="col-sm-4">
							<div class="form-row">
								<input class="form-control" name="serviceRegNm" type="text" id="serviceRegNm" class="wmax" maxlength="30">
							</div>
						</div>
					</div>
				</li>
				<li>
					<div class="row">
						<label for="telInput" class="form-label col-sm-2">연락처</label>
						<div class="col-sm-4">
							<div class="form-row">
								<input class="form-control" name="serviceTel" type="number" id="serviceTel" class="wmax" maxlength="30">
							</div>
						</div>
					</div>
				</li>
				<li>
					<div class="row">
						<label for="emailInput" class="form-label col-sm-2">이메일</label>
						<div class="col-sm-4">
							<div class="form-row">
								<input class="form-control" name="serviceEmail" type="text" id="serviceEmail" class="wmax" maxlength="30">
							</div>
						</div>
					</div>
				</li>
				<li>
					<div class="row">
						<label for="emailInput" class="form-label col-sm-2">희망교육과정</label>
						<div class="col-sm-4">
							<div class="form-row">
								<input class="form-control" name="hopeCurriculum" type="text" id="hopeCurriculum" class="wmax" maxlength="50">
							</div>
						</div>
					</div>
				</li>
			</ul>
		</div>
		
		<div class="btns mt30">
			<button type="button" onclick="javascript:addAtcl();" class="btn gray2">등록</button>
			<button type="button" onclick="javascript:cancel();" class="btn type5">취소</button>
		</div>
	</div>
</form>

<script type="text/javascript">
	// 페이지 초기화
	$(document).ready(function() {
	});

	/* 서브밋 처리 */
	function process(cmd) {
		$('#frm').attr("action","/home/brd/bbs/"+cmd);
 		
		if(!validate(document.frm)) return false;
		
		if($('#companyName').val()==""){
			alert("기업명을 입력하세요.");
			return;
		}
		
		if($("#serviceRegNm").val()==""){
			alert("담당자를 입력하세요.");
			return;
		}
		
		if($("#serviceTel").val()==""){
			alert(" - 을 제외한 연락처를 입력하세요.");
			return;
		}
		
		var email = $("#serviceEmail").val();
		if(email == ""){
			alert("이메일을 입력하세요.");
			return;
		}
		
		var emailChkRegx = new RegExp("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
		if(!emailChkRegx.test(email)){
			alert("이메일 형식이 올바르지 않습니다.\n다시 입력해주세요.")
			return;
		}		
		
		if($("#hopeCurriculum").val()==""){
			alert("희망교육과정을 입력하세요.");
			return;
		}
		
		$('#frm')[0].submit();
	}

	function addAtcl() {
		process("addAtclService");
	}

	function cancel(){
		document.location.href = "/home/main/indexMain";
	}

	</script>