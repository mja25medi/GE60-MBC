<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>
<script defer src="/tpl/002/js/common.js"></script>	
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
    <link rel="stylesheet" href="/tpl/${COLOR_TPL}/css/hrd_common.css">
    <link rel="stylesheet" href="/tpl/${COLOR_TPL}/css/webfonts.css">
    <link rel="stylesheet" href="/tpl/${COLOR_TPL}/css/layout.css">    
    <link rel="stylesheet" href="/tpl/${COLOR_TPL}/css/effect_slick.css">
    <link rel="stylesheet" href="/tpl/${COLOR_TPL}/css/sub.css"><!-- sub 페이지에서 사용 -->
		<form name="studentForm" id="studentForm" method="post">
			<input type="hidden" name="stdNo" id="stdNo" value="${studentVO.stdNo }"/>
			<div class="table txt_small">
				<table>
					<caption>환불 신청 폼</caption>
					<colgroup>
						<col class="w25">                                       
						<col>                                        
					</colgroup>                                                                
					<tbody>
					<tr>
						<th>과정</th>
						<td class="txt_left">
							${studentVO.crsCreNm }
						</td>                                        
					</tr>
					<tr>
						<th>결제 금액</th>
						<td class="txt_left">
							${studentVO.stdPrice }
						</td>                                        
					</tr>
					<tr>
						<th>결제 수단</th>
						<td class="txt_left">
							${studentVO.paymMthdNm }
						</td>                                        
					</tr>
					<tr>
						<th>환불은행명</th>
						<td class="txt_left"><input class="form-control w100" type="text" name="repayBankNm" id="repayBankNm" value="" maxlength="10">
						</td>                              
					</tr>
					<tr>
						<th>환불계좌</th>
						<td class="txt_left"><input class="form-control w100" type="text" name="repayAcntNo" id="repayAcntNo" value="" maxlength="20">
						</td>                              
					</tr>
					<tr>
						<th>환불계좌 사용자명</th>
						<td class="txt_left"><input class="form-control w100" type="text" name="repayUserNm" id="repayUserNm" value="${USERNAME }"  maxlength="10">
						</td>                              
					</tr>
					<tr>
						<th>환불사유</th>
						<td class="txt_left"><input class="form-control w100" type="text" name="repayReason" id="repayReason" value=""  maxlength="500">
						</td>                              
					</tr>
					</tbody>
				</table>
			</div>
		</form>
		<div class="modal_btns">
			<button type="button" class="btn" onclick="javascript:parent.refundModalBoxClose();">닫기</button>
			<button type="button" class="btn type2" onclick="javascript:addRefund();">환불신청</button>
		</div>
<script type="text/javascript">
	$(document).ready(function() {
	});

	function addRefund() {
		if(isEmpty($("#repayBankNm").val())) {
			alert('환불은행을 입력바랍니다.');
			return;
		}
		if(isEmpty($("#repayAcntNo").val())) {
			alert('환불계좌를 입력바랍니다.');
			return;
		}
		if(isEmpty($("#repayUserNm").val())) {
			alert('환불계좌 사용자명을 입력바랍니다.');
			return;
		}
		if(isEmpty($("#repayReason").val())) {
			alert('환불사유를 입력바랍니다.');
			return;
		}
		
/* 		$('#studentForm').attr("action","/home/student/addRefund");
		
		$('#studentForm').ajaxSubmit(
			function(result) {
				alert(result.message);
				if(result.result > 0){
					parent.location.reload();
					parent.refundModalBoxClose();	
				}
			}); */
		var studentForm = $("#studentForm").serialize();
		
		$.ajax({
			type: "post",
			url : '/home/student/addRefund',
			data : studentForm,
			success : function(result) {
				alert(result.message);
				if(result.result > 0){
					parent.location.reload();
					parent.refundModalBoxClose();	
				}
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
  