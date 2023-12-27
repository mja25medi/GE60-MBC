<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="studentVO" value="${vo}"/>
<mhtml:mng_html>
<mhtml:mng_head titleName="${MENUNAME}">

</mhtml:mng_head>

<mhtml:frm_body cssTag="background-color:#ffffff;">
	<form id="studentForm" name="studentForm" onsubmit="return false">
	<input type="hidden" name="stdNo" value="${vo.stdNo }" />
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
			<td>${studentVO.userNm} (${studentVO.userId})</td>
		</tr>
		<tr>
			<th scope="row"><spring:message code="course.title.createcourse.duration.edu"/></th>
			<td>${studentVO.enrlStartDttm} ~ ${studentVO.enrlEndDttm}</td>
			<th scope="row"><spring:message code="student.title.student.enrolldate"/></th>
			<td>${studentVO.enrlAplcDttm}</td>
		</tr>
		<tr>
			<th scope="row"><spring:message code="student.title.student.confirmdate"/></th>
			<td>${studentVO.enrlCertDttm}</td>
			<th scope="row"><spring:message code="student.title.student.completedate"/></th>
			<td>${studentVO.enrlCancelDttm}</td>
		</tr>
		<c:if test="${createCourseVO.eduPrice > 0}">
		<tr>
			<th scope="row"><spring:message code="student.title.student.payment.mthd"/></th>
			<td>
				<meditag:codename code="${studentVO.paymMthdCd}" category="PAYM_MTHD_CD"/>
			</td>
			<th scope="row"><spring:message code="student.title.student.payment.fee"/></th>
			<td>
				<c:if test="${sessionScope.MNTRYPOS eq 'PR'}">${sessionScope.MNTRYUNIT}</c:if><fmt:formatNumber value="${studentVO.paymPrice}" type="number"/><c:if test="${sessionScope.MNTRYPOS eq 'PO'}">${sessionScope.MNTRYUNIT}</c:if>
			</td>
		</tr>
		<tr>
			<th scope="row"><spring:message code="student.title.student.payment.status"/></th>
			<td>
				<c:if test="${studentVO.paymStsCd eq 'Y'}"><spring:message code="student.title.student.payment.complete"/></c:if>
				<c:if test="${studentVO.paymStsCd ne 'Y'}"><spring:message code="student.title.student.payment.nocomplete"/></c:if>
			</td>
			<th scope="row"><spring:message code="student.title.student.payment.date"/></th>
			<td>${studentVO.paymDttm}</td>
		</tr>
		<tr>
			<th scope="row"><spring:message code="student.title.student.repay.status"/></th>
			<td>
				<select name="repayStsCd" id="repayStsCd" class="form-control input-sm">
					<c:forEach var="item" items="${repayStsCdList}" varStatus="status">
					<option value="${item.codeCd}" <c:if test="${vo.repayStsCd eq item.codeCd}">checked</c:if> >${item.codeNm}</option>
					</c:forEach>				
				</select>
			</td>
			<th scope="row"><spring:message code="student.title.student.reapy.reqdate"/></th>
			<td>${studentVO.repayReqDttm}</td>
		</tr>
		<tr id="reapyDttmArea" style="display:none;">
			<th scope="row"><spring:message code="student.title.student.repay.enddate"/></th>
			<td colspan="3">
				<div class="input-group" style="float:left;width:128px;">
					<input type="text" name="repayDttm" value="${item.repayDttm}" class="form-control input-sm" id="repayDttm"  />
					<span class="input-group-addon btn-sm" onclick="_clickCalendar('repayDttm')" style="cursor:pointer">
						<i class="fa fa-calendar"></i>
					</span>
				</div>
				<meditag:datepicker name1="repayDttm"/>
			</td>
		</tr>
		</c:if>
	</table>
	<div class="text-right">
		<c:if test="${studentVO.paymStsCd eq 'Y'}">
			<a href="javascript:editStudent()" class="btn btn-primary btn-sm"><spring:message code="button.add"/> </a>
		</c:if>
		<a href="javascript:parent.modalBoxClose()" class="btn btn-default btn-sm"><spring:message code="button.close"/> </a>
	</div>
	</form>
<script type="text/javascript">
	var repayDttm = "";
	$(document).ready(function() {
		changeSts();

	});

	function changeSts() {
		var repaySts = $("#repayStsCd option:selected").val();
		if(!isEmpty($("#repayDttm").val())) {
			repayDttm = $("#repayDttm").val();
		}
		if(repaySts == 'REFUND003') {
			$("#reapyDttmArea").show();
			$("#repayDttm").val(repayDttm);
		} else {
			$("#reapyDttmArea").hide();
			$("#repayDttm").val("");
		}


	}

	/**
	* 변경된 정보를 저장한다.
	*
	*/
	function editStudent(){
		process("editRepay");
	}

	function process(cmd) {
		if(validate(document.studentForm) ==false) return ;
		$('#studentForm').attr("action", "/mng/std/student/" + cmd);
		$('#studentForm').ajaxSubmit(processCallback);
	}

	/**
	* 처리 결과 표시 콜백
	*/
	function processCallback(resultDTO) {
		alert(resultDTO.message);
		if(resultDTO.result >= 0) {
			parent.subWorkFrame.listStudent(1);
			parent.modalBoxClose();
			// 정상 처리
		} else {
			// 비정상 처리
		}
	}
</script>
</mhtml:frm_body>
</mhtml:mng_html>