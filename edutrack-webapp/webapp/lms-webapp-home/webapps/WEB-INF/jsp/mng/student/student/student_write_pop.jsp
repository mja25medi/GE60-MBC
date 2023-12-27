<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="createCourseVO" value="${createCourseVO}" />
<mhtml:mng_html>
<mhtml:mng_head titleName="${MENUNAME}">

</mhtml:mng_head>

<mhtml:frm_body cssTag="background-color:#ffffff;">
	<form id="studentForm" name="studentForm" onsubmit="return false">
	<input type="hidden" name="crsCreCd" id="crsCreCd" value="${vo.crsCreCd }" />
	<input type="hidden" name="userNo" id="userNo" value="${vo.userNo }" />
	<table summary="<spring:message code="student.title.student.manage"/>" class="table table-bordered wordbreak">
		<colgroup>
			<col style="width:20%"/>
			<col style="width:30%"/>
			<col style="width:20%"/>
			<col style="width:30%"/>
		</colgroup>
		<tr>
			<th scope="row"><spring:message code="course.title.course.name"/></th>
			<td>
				${createCourseVO.crsCreNm}
			</td>
			<th scope="row"><spring:message code="course.title.createcourse.creterm"/></th>
			<td>
				${createCourseVO.creTerm}
			</td>
		</tr>
		<!-- 23.12.19 메디 -->
		<!-- 유/무료 과정 상관없이 수강생 등록시 무료수강 처리 -->
		<%-- <c:if test="${createCourseVO.eduPrice > 0}">
		<tr>
			<th scope="row"><spring:message code="student.title.student.payment.mthd"/></th>
			<td>
				<select name="paymMthdCd" id="paymMthdCd" class="form-control input-sm">
				<c:forEach var="code" items="${codeList}">
					<c:set var="codeName" value="${code.codeNm}"/>
					<c:forEach var="lang" items="${code.codeLangList }">
						<c:if test="${lang.langCd eq LOCALEKEY }"><c:set var="codeName" value="${lang.codeNm}"/></c:if>
					</c:forEach>
					<option value="${code.codeCd}">${codeName}</option>
				</c:forEach>
				</select>
			</td>
			<th scope="row"><spring:message code="student.title.student.payment.fee"/></th>
			<td>
				<c:if test="${sessionScope.MNTRYPOS eq 'PR'}">
				<span style="float:left;line-height:30px;padding-left:5px;">${sessionScope.MNTRYUNIT}</span>
				</c:if>
				<input type="text" name="paymPrice" style="width:114px;text-align:right;float:left;" value="${createCourseVO.eduPrice}" class="form-control input-sm" id="paymPrice" />
				<c:if test="${sessionScope.MNTRYPOS eq 'PO'}">
				<span style="float:left;line-height:30px;padding-left:5px;">${sessionScope.MNTRYUNIT}</span>
				</c:if>
				<input type="hidden" name="paymNo" value="${vo.paymNo }" />
			</td>
		</tr>
		<tr>
			<th scope="row"><spring:message code="student.title.student.payment.status"/></th>
			<td>
				<label style="font-weight: normal;"><input type="radio" style="border:0" name="paymStsCd" value="Y" id="paymStsCdY" onclick="chkPaymSts('Y');" <c:if test="${vo.paymStsCd eq 'Y'}">checked</c:if>/> <spring:message code="student.title.student.payment.complete"/></label>
				<label style="font-weight: normal; margin-left:10px;"><input type="radio" style="border:0" name="paymStsCd" value="N" id="paymStsCdN" onclick="chkPaymSts('N');" <c:if test="${vo.paymStsCd eq 'N'}">checked</c:if>/> <spring:message code="student.title.student.payment.nocomplete"/></label>
			</td>
			<th scope="row"><spring:message code="student.title.student.payment.date"/></th>
			<td>
				<div class="input-group" style="width:128px;">
					<input type="text" name="paymDttm" class="form-control input-sm" id="paymDttm" value="${vo.paymDttm }"/>
					<span class="input-group-addon btn-sm" onclick="_clickCalendar('paymDttm')" style="cursor:pointer">
						<i class="fa fa-calendar"></i>
					</span>
				</div>
				<meditag:datepicker name1="paymDttm"/>
			</td>
		</tr>
		</c:if> --%>
		<%-- <c:if test="${createCourseVO.eduPrice == 0}"> --%>
			<input type="hidden" name="paymMthdCd" value="FREE" /> 
			<input type="hidden" name="paymPrice" value="0" /> 
			<input type="hidden" name="paymStsCd" value="Y" /> 
		<%-- </c:if> --%>
		<c:if test="${createCourseVO.crsOperMthd eq 'ON'}">
		<input type="hidden" name="stayAplcYn" value="N" /> 
		</c:if>
	</table>
	<input type="submit" value="submit" style="display:none" />
	</form>
	<form id="Search" name="Search" onsubmit="return false">
	<div class="well" style="width:100%;padding:3px 15px 3px 15px;">
		<div class="row" style="padding:0px 15px 0px 15px;">
			<div class="input-group" style="float:left;">
				<select name="key" id="key" class="form-control input-sm">
					<option value="userNm"><spring:message code="user.title.userinfo.name"/></option>
					<option value="userId"><spring:message code="user.title.userinfo.userid"/></option>
				</select>
			</div>
			<div class="input-group" style="float:left;width:120px;">
				<input type="text" name="searchKey" class="_enterBind form-control input-sm" />
				<span class="input-group-addon" onclick="listStudent()" style="cursor:pointer">
					<i class="fa fa-search"></i>
				</span>
			</div>
			<div style="float:right;">
				<a href="javascript:parent.modalBoxClose()" class="btn btn-default btn-sm"><spring:message code="button.close"/> </a>
			</div>
		</div>
	</div>
	<div id="userList" style="overflow-y:auto;height:100%;width:100%;">
		<table summary="<spring:message code="student.title.student.manage"/>" class="table table-bordered wordbreak">
			<colgroup>
				<col style="width:50px;"/>
				<col style="width:auto;"/>
				<col style="width:auto;"/>
				<col style="width:auto;"/>
				<col style="width:auto"/>
				<col style="width:50px;"/>
			</colgroup>
			<thead>
				<tr>
					<th scope="col"><spring:message code="common.title.no"/></th>
					<th scope="col"><spring:message code="user.title.userinfo.name"/></th>
					<th scope="col"><spring:message code="user.title.userinfo.userid"/></th>
					<th scope="col"><spring:message code="user.title.userinfo.mobileno"/></th>
					<th scope="col"><spring:message code="user.title.userinfo.email"/></th>
					<th scope="col"><spring:message code="common.title.choice"/></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td colspan="6" align="center"><spring:message code="user.message.userinfo.search.user"/></td>
				</tr>
			</tbody>
		</table>
	</div>
	<input type="submit" value="submit" style="display:none"/>
	</form>
<script type="text/javascript">

	var ItemDTO =
		{
			crsCreCd : "${createCourseVO.crsCreCd}",
			stayYn : "${createCourseVO.stayYn}",
			stayAplcArr : ""
		};

	$(document).ready(function() {
		$("._enterBind").bind("keydown", eventForSearch);

		function eventForSearch(event) {
			if (event.keyCode == '13') {
				listStudent();
			}
		}
	});

	/**
	 * 학습자 목록 조회
	 *
	 */
	function listStudent() {
		var f = document.Search;
		var crsCreCd = ItemDTO.crsCreCd;
		var userNm = f.searchKey.value;
		var searchKey = $('#key option:selected').val();
		if(jsLength(userNm) < 2) {
			alert('<spring:message code="user.message.userinfo.alser.search.user"/>');
			return;
		}
		$("#userList").load(cUrl("/mng/std/student/listUser"), {"crsCreCd":crsCreCd,"userNm":userNm,"searchKey":searchKey});

	}

	/**
	 * 학습자 등록
	 */
	function addStudent(userNo, userNm) {
		$("#userNo").val(userNo);
		$("#receiptUserNm").val(userNm);
		process('addStudent');
	}

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		if(!validate(document.studentForm)) return;
		$('#studentForm').attr("action","/mng/std/student/" + cmd);
		$('#studentForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		alert(resultDTO.message);

		if(resultDTO.result >= 0) {
			// 정상 처리
			parent.subWorkFrame.listStuPayInfo('1');
			parent.modalBoxClose();

		} else {
			// 비정상 처리
		}
	}

	function chkPaymSts(str) {
		if(str == 'Y') $("#paymDttm").val("${curDate}");
		else $("#paymDttm").val("");
	}
</script>
</mhtml:frm_body>
</mhtml:mng_html>