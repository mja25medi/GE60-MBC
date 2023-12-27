<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<mhtml:mng_html>
<mhtml:mng_head titleName="${MENUNAME}">

</mhtml:mng_head>
<mhtml:frm_body cssTag="background-color:#ffffff;">
	<form id="studentForm" name="studentForm" onsubmit="return false" method="post">
	<input type="hidden" name="stdNo" value="${studentVO.stdNo }" />
	<%-- <input type="hidden" name="oldEnrlSts" value="${vo.enrlSts }" /> --%>
	<div>
		<h5><i class="fa fa-check" aria-hidden="true"></i>수강생 정보</h5>
		<table class="table table-bordered wordbreak">
			<colgroup>
				<col style="width:18%"/>
				<col style="width:32%"/>
				<col style="width:18%"/>
				<col style="width:32%"/>
			</colgroup>
			<tr>
				<th scope="row"><spring:message code="student.title.student.stdno"/></th>
				<td>${studentVO.stdNo}</td>
				<th scope="row"><spring:message code="user.title.userinfo.name"/></th>
				<td>${studentVO.userNm}</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="course.title.createcourse.edu.startdate"/></th>
				<td><meditag:dateformat type="0" delimeter="." property="${studentVO.enrlStartDttm}" /></td>
				<th scope="row"><spring:message code="course.title.createcourse.edu.enddate"/></th>
				<td><meditag:dateformat type="0" delimeter="." property="${studentVO.enrlEndDttm}" /></td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="student.title.student.enrolldate"/></th>
				<td><meditag:dateformat type="0" delimeter="." property="${studentVO.enrlAplcDttm}" /></td>
				<th scope="row"><spring:message code="student.title.student.canceldate"/></th>
				<td>
					<c:if test="${studentVO.enrlSts eq 'N' }">
						<meditag:dateformat type="0" delimeter="." property="${studentVO.enrlCancelDttm}" />
					</c:if>
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="student.title.student.confirmdate"/></th>
				<td><meditag:dateformat type="0" delimeter="." property="${studentVO.enrlCertDttm}" /></td>
				<th scope="row"><spring:message code="student.title.student.completedate"/></th>
				<td><meditag:dateformat type="0" delimeter="." property="${studentVO.enrlCpltDttm}" /></td>
			</tr>
			<c:if test="${studentVO.stdPrice > 0}">
			<tr>
				<th scope="row"><spring:message code="student.title.student.payment.mthd"/></th>
				<td><meditag:codename code="${studentVO.paymMthdCd}" category="PAYM_MTHD_CD"/></td>
				<th scope="row"><spring:message code="student.title.student.payment.fee"/></th>
				<td>
					<fmt:formatNumber value="${studentVO.stdPrice}" type="number"/>
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="student.title.student.payment.status"/></th>
				<td>
					<c:choose>
						<c:when test="${studentVO.repayStsCd eq 'REFUND001' }">환불 요청</c:when>
						<c:when test="${studentVO.repayStsCd eq 'REFUND003' }">환불 완료</c:when>
						<c:when test="${studentVO.enrlSts eq 'E' }">결제 대기</c:when>
						<c:when test="${studentVO.enrlSts eq 'N' }">결제 취소</c:when>
						<c:when test="${studentVO.enrlSts eq 'S' || studentVO.enrlSts eq 'C' || studentVO.enrlSts eq 'F' }">결제완료</c:when>
						<c:otherwise>-</c:otherwise>
					</c:choose>
					<%-- <c:if test="${studentVO.enrlSts eq 'S' }">
						<a href="javascript:editCancelStudent()" class="btn btn-danger btn-sm">결제 취소 처리</a>
					</c:if>
					<c:if test="${studentVO.enrlSts eq 'E' || studentVO.enrlSts eq 'N' }">
						<a href="javascript:editConFirmStudent()" class="btn btn-success btn-sm">결제 완료 처리</a>
					</c:if> --%>
					<%-- 입금완료 했으나 가상계좌 입금여부 수신이 안되는 경우 직접 완료 처리 --%>
					<c:if test="${studentVO.enrlSts eq 'E' }">
						<a href="javascript:editConFirmStudent()" class="btn btn-success btn-sm">결제 완료 처리</a>
					</c:if>
				</td>
				<th scope="row"><spring:message code="student.title.student.payment.date"/></th>
				<td><meditag:dateformat type="0" delimeter="." property="${studentVO.paymDttm}" /></td>
			</tr>
			</c:if>
			<tr>
				<th scope="row"><spring:message code="student.title.student.completeno"/></th>
				<td colspan="3">${studentVO.cpltNo}</td>
			</tr>
		</table>
		<c:if test="${not empty studentVO.tid}">
			<h5><i class="fa fa-check" aria-hidden="true"></i>이니시스 정보</h5>
			<table class="table table-bordered wordbreak">
				<colgroup>
					<col style="width:18%"/>
					<col style="width:32%"/>
					<col style="width:18%"/>
					<col style="width:32%"/>
				</colgroup>
				<tr>
					<th scope="row">거래번호(TID)</th>
					<td>${studentVO.tid}</td>
					<th scope="row">결제방법</th>
					<td>
						<c:choose>
							<c:when test="${studentVO.payMethod eq 'Card' }">신용카드(안심클릭) (PC결제)</c:when>
							<c:when test="${studentVO.payMethod eq 'VCard' }">신용카드(ISP) (PC결제)</c:when>
							<c:when test="${studentVO.payMethod eq 'DirectBank' }">실시간계좌이체 (PC결제)</c:when>
							<c:when test="${studentVO.payMethod eq 'VBank' }">가상계좌(무통장입금) (PC결제)</c:when>
							<c:when test="${studentVO.payMethod eq 'CARD' }">신용카드(안심클릭,ISP) (모바일결제)</c:when>
							<c:when test="${studentVO.payMethod eq 'BANK' }">실시간계좌이체 (모바일결제)</c:when>
							<c:when test="${studentVO.payMethod eq 'VBANK' }">가상계좌(무통장입금) (모바일결제)</c:when>
							<c:otherwise></c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<th scope="row">주문번호</th>
					<td>${studentVO.moid}</td>
					<th scope="row">결제금액(이니시스)</th>
					<td>${studentVO.totPrice}</td>
				</tr>
				<tr>
					<th scope="row">승일일자</th>
					<td><meditag:dateformat type="1" delimeter="." property="${studentVO.applDate}"/> <meditag:dateformat type="10" delimeter="." property="${studentVO.applTime}" /></td>
					<th scope="row">카드승인번호</th>
					<td>${studentVO.applNum}</td>
				</tr>
				<tr>
					<th scope="row">결제 디바이스</th>
					<td>${studentVO.deviceType }</td>
					<th scope="row"><meditag:codename code="${studentVO.vactBankCode}" category="BANK_CD"/></th>
					<td></td>
				</tr>
			</table>
			
			<c:if test="${studentVO.paymMthdCd eq 'PAYM003' && not empty studentVO.vactNum}">
				<h5><i class="fa fa-check" aria-hidden="true"></i>이니시스 가상계좌 정보</h5>
				<table class="table table-bordered wordbreak">
					<colgroup>
						<col style="width:18%"/>
						<col style="width:32%"/>
						<col style="width:18%"/>
						<col style="width:32%"/>
					</colgroup>
					<tr>
						<th scope="row">가상계좌번호</th>
						<td>${studentVO.vactNum}</td>
						<th scope="row">입금은행명</th>
						<td>${studentVO.vactBankName}</td>
					</tr>
					<tr>
						<th scope="row">예금주명</th>
						<td>${studentVO.vactName}</td>
						<th scope="row">송금자명(결제창 내 입력한 송금자명, 없으면 회원명)</th>
						<td>${studentVO.vactInputName}</td>
					</tr>
					<tr>
						<th scope="row">입금기한</th>
						<td><meditag:dateformat type="8" delimeter="." property="${studentVO.vactDate}${studentVO.vactTime}"/> </td>
						<th scope="row"></th>
						<td></td>
					</tr>
				</table>
			</c:if>
		</c:if>
		<c:if test="${not empty studentVO.repayStsCd}">
			<h5><i class="fa fa-check" aria-hidden="true"></i>환불정보</h5>
			<table class="table table-bordered wordbreak">
				<colgroup>
					<col style="width:18%"/>
					<col style="width:32%"/>
					<col style="width:18%"/>
					<col style="width:32%"/>
				</colgroup>
				<tr>
					<th scope="row">총 결제 금액</th>
					<td>${studentVO.paymPrice}원</td>
					<th scope="row">결제 금액</th>
					<td>${studentVO.stdPrice}원</td>
				</tr>
				<%-- 환불 요청 --%>
				<c:if test="${studentVO.repayStsCd eq 'REFUND001' }">
				<tr>
					<th scope="row">환불금액</th>
					<td>
						<input name="repayPrice" id="repayPrice" type="text" class="form-control inputNumber" value="${studentVO.stdPrice}" maxlength="6"/>
					</td>
					<%-- <th scope="row">환불방법</th>
					<td>${studentVO.repayMthdCd}</td> --%>
				</tr>
				<tr>
					<th scope="row">환불상태</th>
					<td>환불요청</td>
					<th scope="row">환불요청일시</th>
					<td>${studentVO.repayReqDttm}</td>
				</tr>
				<tr>
					<th scope="row">환불은행명</th>
					<td><input type="text" name="repayBankNm" id="repayBankNm" class="form-control" value="${studentVO.repayBankNm}" maxlength="10"/></td>
					<th scope="row">환불계좌번호</th>
					<td><input type="text" name="repayAcntNo" id="repayAcntNo" class="form-control" value="${studentVO.repayAcntNo}" maxlength="20"/></td>
				</tr>
				<tr>
					<th scope="row">환불계좌 사용자명</th>
					<td colspan="3"><input type="text" name="repayUserNm" id="repayUserNm" class="form-control" value="${studentVO.repayUserNm}" maxlength="10" style="max-width: 280px;"/></td>
				</tr>
				<tr>
					<th scope="row">환불사유</th>
					<td colspan="3">${studentVO.repayReason }</td>
				</tr>
				<tr>
					<th scope="row">환불메모</th>
					<td colspan="3">
						<div class="form-group">
						  <textarea name="repayMemo" class="form-control" rows="5" id="repayMemo" style="resize:none;">${studentVO.repayMemo }</textarea>
						</div>
					</td>
				</tr>
				<tr>
					<th scope="row">환불처리</th>
					<td colspan="3">
							<a href="javascript:editRefundComplete()" class="btn btn-danger btn-sm">직접 환불 처리</a>
							<a href="javascript:editRefundCancel()" class="btn btn-success btn-sm">환불 취소 처리</a>
							
							<%-- 이니시스 결제 --%>
							<%-- 카드결제의 경우, 결제 기준 부분취소 가능여부 값 존재 --%>
							<c:if test="${not empty studentVO.tid }">
							<c:choose>
								<c:when test="${ studentVO.payMethod eq 'Card' || studentVO.payMethod eq 'VCard' || studentVO.payMethod eq 'CARD' || studentVO.payMethod eq 'DirectBank'  || studentVO.payMethod eq 'BANK'}">
									<a href="javascript:editIniRefund()" class="btn btn-danger btn-sm">이니시스 환불 처리</a>
								</c:when>
								<c:when test="${studentVO.payMethod eq 'VBank' || studentVO.payMethod eq 'VBANK' }">
									
								</c:when>
								<c:otherwise></c:otherwise>
							</c:choose>
							</c:if>
						
					</td>
				</tr>
				</c:if>
				
				<%-- 환불완료 --%>
				<c:if test="${studentVO.repayStsCd eq 'REFUND003' }">
				<tr>
					<th scope="row">환불금액</th>
					<td>
						${studentVO.repayPrice}
					</td>
				</tr>
				<tr>
					<th scope="row">환불상태</th>
					<td>환불완료</td>
					<th scope="row">환불요청일시</th>
					<td>${studentVO.repayReqDttm}</td>
				</tr>
				<tr>
					<th scope="row">환불은행명</th>
					<td>${studentVO.repayBankNm}</td>
					<th scope="row">환불계좌번호</th>
					<td>${studentVO.repayAcntNo}</td>
				</tr>
				<tr>
					<th scope="row">환불계좌 사용자명</th>
					<td colspan="3">${studentVO.repayUserNm}</td>
				</tr>
				<tr>
					<th scope="row">환불사유</th>
					<td colspan="3">${studentVO.repayReason }</td>
				</tr>
				<tr>
					<th scope="row">환불메모</th>
					<td colspan="3">
						<div class="form-group">
						  <%-- <textarea name="repayMemo" class="form-control" rows="5" id="repayMemo" style="resize:none;">${studentVO.repayMemo }</textarea> --%>
						  ${studentVO.repayMemo }
						</div>
					</td>
				</tr>
				</c:if>
			</table>
		</c:if>
		
		<div class="text-right">
			<%-- <a href="javascript:editStudent()" class="btn btn-primary btn-sm"><spring:message code="button.edit"/> </a> --%>
			<a href="javascript:parent.modalBoxClose()" class="btn btn-default btn-sm"><spring:message code="button.close"/></a>
		</div>
	</div>
	</form>

<script type="text/javascript">
	$(document).ready(function() {
		$('.inputNumber').inputNumber();	// 숫자만 입력되도록 설정.
		$('.inputSpecial').inputSpecial();  // 특수키가 입력 false return;
	});

	function editCancelStudent(){
		if(confirm('결제취소(수강취소) 처리하시겠습니까?')){
			process("editCancelStdPay");	
		}
	}
	
	function editConFirmStudent(){
		if(confirm('결제완료(수강승인) 처리하시겠습니까?')){
			process("editConfirmStdPay");	
		}
	}
	
	function editRefundComplete(){
		if(isNull($("#repayPrice").val())){
			alert('환블 금액을 입력바랍니다.');
			$("#repayPrice").focus();
			return;
		}
		if(parseInt($("#repayPrice").val()) == 0){
			alert('환블 금액은 0원보다 커야합니다.');
			$("#repayPrice").focus();
			return;
		}
		if(isEmpty($("#repayBankNm").val())){
			alert('환불은행명을 입력바랍니다.');
			$("#repayBankNm").focus();
			return;
		}
		
		if(isEmpty($("#repayAcntNo").val())){
			alert('환불계좌번호를 입력바랍니다.');
			$("#repayAcntNo").focus();
			return;
		}
		
		if(isEmpty($("#repayUserNm").val())){
			alert('환불계좌 사용자명을 입력바랍니다.');
			$("#repayUserNm").focus();
			return;
		}
		
		if(confirm('수강생에게 직접 입금 환불 후 시스템에서 환불 처리를 진행합니다.\n환불완료 처리하시겠습니까?') && confirm('환불완료 처리 후 수정 불가능합니다.\n정말로 환불완료 처리하시겠습니까?')){
			process("editRefund");	
		}
	}
	
	function editRefundCancel(){
		if(confirm('신청한 환불 건을 취소 처리하시겠습니까?\n수강생은 수강이 가능한 상태로 변경됩니다.') && confirm('정말로 환불 취소 처리하시겠습니까?')){
			process("editRefundCancel");	
		}
	}
	
	function editRefundMemo(){
		if(confirm('환불 건에 대해 환불 메모만 변경됩니다.')){
			process("editRefundMemo");	
		}
	}
	function editIniRefund(){
		if(isNull($("#repayPrice").val())){
			alert('환블 금액을 입력바랍니다.');
			return;
		}
		if(parseInt($("#repayPrice").val()) == 0){
			alert('환블 금액은 0원보다 커야합니다.');
			return;
		}
		if(confirm('이니시스를 통해 결제한 금액을 환불처리합니다.\n(카드결제, 실시간계좌이체)\n이미 이니시스 상점관리자를 통해 환불 처리를 한 경우 시스템에서 정상 처리되지 않으니 주의바랍니다.\n환불처리(결제취소)를 하시겠습니까?') && confirm('이니시스를 통해 결제한 금액을 수강생에게 환불처리합니다.\n\n정말로 환불처리(결제취소) 하시겠습니까?')){
			process("editIniRefund");	
		}
	}

	function process(cmd) {

		if(validate(document.studentForm) ==false) return ;

		$('#studentForm').attr("action" , "/mng/std/student/" + cmd);
		$('#studentForm').ajaxSubmit(processCallback);
	}

	/**
	* 처리 결과 표시 콜백
	*/
	function processCallback(resultDTO) {
		alert(resultDTO.message);
		if(resultDTO.result >= 0) {
			parent.listStuPayInfo();
			parent.modalBoxClose();
			// 정상 처리
		} else {
			// 비정상 처리
		}
	}

	//Date Type YYYYMMDD
	function chgDate(date){
		var chgDate = date.replace(/\./g,"");
		return chgDate;
	}

	//날짜 체크하기.
	function dateCheck(startDate , endDate){
		if(startDate > endDate) {
			return false;
		}else{
			return true;
		}
	}

	function chkPaymSts(str) {
		if(str == 'Y') $("#paymDttm").val("${vo.paymDttm}");
		else $("#paymDttm").val("");
	}
</script>
</mhtml:frm_body>
</mhtml:mng_html>