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
	<div id ="viewStd">
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
				<td>${studentVO.enrlStartDttm}</td>
				<th scope="row"><spring:message code="course.title.createcourse.edu.enddate"/></th>
				<td>${studentVO.enrlEndDttm}</td>
			</tr>
			<c:if test="${createCourseVO.crsOperMthd eq 'ON' || createCourseVO.crsOperMthd eq 'BL'}">
			<tr>
				<th scope="row"><spring:message code="course.title.createcourse.endday.audit"/></th>
				<td colspan="3">${studentVO.auditEndDttm}</td>
			</tr>
			</c:if>
			<tr>
				<th scope="row"><spring:message code="student.title.student.enrolldate"/></th>
				<td>${studentVO.enrlAplcDttm}</td>
				<th scope="row"><spring:message code="student.title.student.canceldate"/></th>
				<td>${studentVO.enrlCancelDttm}</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="student.title.student.confirmdate"/></th>
				<td>${studentVO.enrlCertDttm}</td>
				<th scope="row"><spring:message code="student.title.student.completedate"/></th>
				<td>${studentVO.enrlCpltDttm}</td>
			</tr>
			<%-- <c:if test="${createCourseVO.eduPrice > 0}">
			<tr>
				<th scope="row"><spring:message code="student.title.student.payment.mthd"/></th>
				<td><meditag:codename code="${studentVO.paymMthdCd}" category="PAYM_MTHD_CD"/></td>
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
			</c:if> --%>
			<tr>
				<th scope="row"><spring:message code="student.title.student.completeno"/></th>
				<td colspan="3">${studentVO.cpltNo}</td>
			</tr>
		</table>
		<div class="text-right">
			<a href="javascript:editFormStudent()" class="btn btn-primary btn-sm"><spring:message code="button.edit"/> </a>
			<a href="javascript:parent.modalBoxClose()" class="btn btn-default btn-sm"><spring:message code="button.close"/> </a>
		</div>
	</div>
	<div id ="editStd" style="display:none">
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
				<th scope="row"><spring:message code="course.title.createcourse.duration.edu"/></th>
				<td colspan="3">
					<div class="input-group" style="float:left;width:128px;">
						<input type="text" dispName="<spring:message code="course.title.createcourse.edu.startdate"/>" maxlength="50" isNull="N" lenCheck="50" name="enrlStartDttm" id="enrlStartDttm" class="form-control input-sm" value="${vo.enrlStartDttm }" />
						<span class="input-group-addon btn-sm" onclick="_clickCalendar('enrlStartDttm')" style="cursor:pointer">
							<i class="fa fa-calendar"></i>
						</span>
					</div>
					<span style="float:left;line-height:30px;padding-left:10px;padding-right:10px;">~</span>
					<div class="input-group" style="float:left;width:128px;">
						<input type="text" dispName="<spring:message code="course.title.createcourse.edu.enddate"/>" maxlength="50" isNull="N" lenCheck="50" name="enrlEndDttm" id="enrlEndDttm" class="form-control input-sm" value="${vo.enrlEndDttm }" />
						<span class="input-group-addon btn-sm" onclick="_clickCalendar('enrlEndDttm')" style="cursor:pointer">
							<i class="fa fa-calendar"></i>
						</span>
					</div>
					<meditag:datepicker name1="enrlStartDttm" name2="enrlEndDttm"/>
				</td>
			</tr>
			<c:if test="${createCourseVO.crsOperMthd eq 'ON' || createCourseVO.crsOperMthd eq 'BL'}">
			<tr>
				<th scope="row"><spring:message code="course.title.createcourse.endday.audit"/></th>
				<td colspan="3">
					<div class="input-group" style="float:left;width:128px;">
						<input type="text" dispName="<spring:message code="course.title.createcourse.endday.audit"/>" maxlength="50" isNull="N" lenCheck="50" name="auditEndDttm" id="auditEndDttm" class="form-control input-sm" value="${vo.auditEndDttm }" />
						<span class="input-group-addon btn-sm" onclick="_clickCalendar('auditEndDttm')" style="cursor:pointer">
							<i class="fa fa-calendar"></i>
						</span>
					</div>
					<meditag:datepicker name1="auditEndDttm"/>
				</td>
			</tr>
			</c:if>
			<tr>
				<th scope="row"><spring:message code="student.title.student.enrolldate"/></th>
				<td>
					${studentVO.enrlAplcDttm} <input type="hidden" name="enrlAplcDttm" value="${vo.enrlAplcDttm }" />
				</td>
				<th scope="row"><spring:message code="student.title.student.canceldate"/></th>
				<td>
					${studentVO.enrlCancelDttm} <input type="hidden" name="enrlCancelDttm" value="${vo.enrlCancelDttm }" />
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="student.title.student.confirmdate"/></th>
				<td>
					${studentVO.enrlCertDttm}
					<input type="hidden" name="enrlCertDttm" value="${vo.enrlCertDttm }" />
				</td>
				<th scope="row"><spring:message code="student.title.student.completedate"/></th>
				<td>
					${studentVO.enrlCpltDttm}
					<input type="hidden" name="enrlCpltDttm" value="${vo.enrlCpltDttm }" />
				</td>
			</tr>
			<%-- <c:if test="${createCourseVO.eduPrice > 0}">
			<tr>
				<th scope="row"><spring:message code="student.title.student.payment.mthd"/></th>
				<td>
					<select name="paymMthdCd" class="form-control input-sm">
						<c:forEach var="code" items="${codeList}">
						<c:set var="codeName" value="${code.codeNm}"/>
						<c:forEach var="lang" items="${code.codeLangList}">
							<c:if test="${lang.langCd eq LOCALEKEY }"><c:set var="codeName" value="${lang.codeNm}"/></c:if>
						</c:forEach>
						<option value="${code.codeCd}" <c:if test="${code.codeCd eq studentVO.paymMthdCd}">selected</c:if>>${codeName}</option>
						</c:forEach>
					</select>
				</td>
				<th scope="row"><spring:message code="student.title.student.payment.fee"/></th>
				<td>
					<c:if test="${sessionScope.MNTRYPOS eq 'PR'}">
					<span style="float:left;line-height:30px;padding-left:5px;">${sessionScope.MNTRYUNIT}</span>
					</c:if>
					<input type="textg" style="width:114px;text-align:right;float:left;" dispName="<spring:message code="student.title.student.payment.fee"/>" maxlength="10" isNull="N" lenCheck="10" name="paymPrice" value="${vo.paymPrice }" class="form-control input-sm" id="paymPrice" />
					<c:if test="${sessionScope.MNTRYPOS eq 'PO'}">
					<span style="float:left;line-height:30px;padding-left:5px;">${sessionScope.MNTRYUNIT}</span>
					</c:if>
					<input type="hidden" name="paymNo" value="${vo.paymNo }" />
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="student.title.student.payment.status"/></th>
				<td>
					<label style="font-weight: normal;"><input type="radio" style="border:0" property="paymStsCd" value="Y" id="paymStsCdY" onclick="chkPaymSts('Y');" <c:if test="${vo.paymStsCd eq 'Y'}">checked</c:if>/> <spring:message code="student.title.student.payment.complete"/></label>
					<label style="font-weight: normal;margin-left:10px;"><input type="radio" style="border:0" property="paymStsCd" value="N" id="paymStsCdN" onclick="chkPaymSts('N');" <c:if test="${vo.paymStsCd eq 'N'}">checked</c:if>/> <spring:message code="student.title.student.payment.nocomplete"/></label>
				
				</td>
				<th scope="row"><spring:message code="student.title.student.payment.date"/></th>
				<td>
					<c:set var="paymDttm" value="${studentVO.paymDttm}"/><c:if test="${studentVO.paymStsCd ne 'Y' }"><c:set var="paymDttm" value=""/></c:if>
					<div class="input-group" style="float:left;width:128px;">
						<input type="text" name="paymDttm" value="${paymDttm}" class="form-control input-sm" id="paymDttm" />
						<span class="input-group-addon btn-sm" onclick="_clickCalendar('paymDttm')" style="cursor:pointer">
							<i class="fa fa-calendar"></i>
						</span>
					</div>
					<meditag:datepicker name1="paymDttm"/>
				</td>
			</tr>
			</c:if>
			<c:if test="${createCourseVO.eduPrice == 0}">
				<input type="hidden" property="paymMthdCd" value='FREE' />
				<input type="hidden" property="paymPrice" value="${vo.paymPrice }" />
				<input type="hidden" property="paymStsCd" value="${vo.paymStsCd }" />
			</c:if> --%>
			<tr>
				<th scope="row"><spring:message code="student.title.student.completeno"/></th>
				<td colspan="3">
					${studentVO.cpltNo}
				</td>
			</tr>
		</table>
		<div class="text-right">
			<a href="javascript:editStudent()" class="btn btn-primary btn-sm"><spring:message code="button.add"/> </a>
			<a href="javascript:cancelStudent()" class="btn btn-warning btn-sm"><spring:message code="button.cancel"/> </a>
			<a href="javascript:parent.modalBoxClose()" class="btn btn-default btn-sm"><spring:message code="button.close"/> </a>
		</div>
	</div>
	</form>

<script type="text/javascript">
	$(document).ready(function() {
		$('#editStd').hide();
		$('#viewStd').show();

	});

	function editFormStudent(){
		$('#editStd').show();
		$('#viewStd').hide();
	}

	function cancelStudent(){
		$('#editStd').hide();
		$('#viewStd').show();
	}

	/**
	* 변경된 정보를 저장한다.
	*
	*/
	function editStudent(){
		var f = document.studentForm;
		var enrlStartDttm = chgDate(f["enrlStartDttm"].value);
		var enrlEndtDttm = chgDate(f["enrlEndDttm"].value);

		<c:if test="${(courseVO.crsOperMthd eq 'ON' || courseVO.crsOperMthd eq 'BL') && courseVO.crsOperType eq 'R'}">
		var auditEndDttm = chgDate(f["auditEndDttm"].value);
		if(!dateCheck(enrlEndtDttm, auditEndDttm)) {
			alert("<spring:message code="course.message.createcourse.alert.auditdate"/>");
			return;
		}
		</c:if>

		process("editStudent");
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
			parent.subWorkFrame.listStudent('1');
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