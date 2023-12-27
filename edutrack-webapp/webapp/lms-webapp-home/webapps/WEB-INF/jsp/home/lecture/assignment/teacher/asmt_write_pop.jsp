<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="gubun" value="${gubun}"/>
<c:set var="assignmentVO" value="${vo}"/>
<c:set var="subjectList" value="${subjectList}"/>

	<form id="assignmentForm" name="assignmentForm" onsubmit="return false">
	<input type="hidden" name="crsCreCd" value="${assignmentVO.crsCreCd }" />
	<input type="hidden" name="asmtSn" value="${assignmentVO.asmtSn }" />
	<input type="hidden" name="attachFileSns" value="${assignmentVO.attachFileSns }" />
	<table summary="<spring:message code="lecture.title.assignment.manage"/>" class="table iframe-elem mt20">
		<colgroup>
			<col style="width:18%"/>
			<col style="width:34%"/>
			<col style="width:18%"/>
			<col style="width:30%"/>
		</colgroup>
		<tr >
			<th scope="row"><lable for="asmtTypeCd"><spring:message code="lecture.title.assignment.type"/></lable></th>
			<td>
				<c:if test="${gubun eq 'A'}">
					<select name="asmtTypeCd" id="asmtTypeCd" class="form-control input-sm" onclick="changeAsmtType()">
						<c:forEach var="item" items="${asmtTypeList}" varStatus="status">
							<option value="${item.codeCd}" <c:if test="${assignmentVO.asmtTypeCd eq item.codeCd}">checked</c:if> >${item.codeNm}</option>
						</c:forEach>				
					</select>
				</c:if>
				<c:if test="${gubun eq 'E'}">
				<input type="hidden" name="asmtTypeCd" id="asmtTypeCd" value="${assignmentVO.asmtTypeCd }"/><meditag:codename code="${assignmentVO.asmtTypeCd}" category="ASMT_TYPE_CD"/>
				</c:if>
			</td>
			<c:if test="${gubun eq 'A'}">
				<th scope="row"><spring:message code="lecture.title.assignment.seltype"/></th>
				<td>
					<select name="asmtSelectTypeCd" id="asmtSelectTypeCd" class="form-control input-sm">
						<c:forEach var="item" items="${asmtSelectTypeList}" varStatus="status">
						<option value="${item.codeCd}" <c:if test="${assignmentVO.asmtSelectTypeCd eq item.codeCd}">checked</c:if> >${item.codeNm}</option>
						</c:forEach>				
					</select>
				</td>
			</c:if>
			<c:if test="${gubun eq 'E'}">
				<c:if test="${assignmentVO.asmtTypeCd eq 'ON'}">
					<th scope="row"><spring:message code="lecture.title.assignment.seltype"/></th>
					<td>
						<input type="hidden" name="asmtSelectTypeCd" id="asmtSelectTypeCd" value="${assignmentVO.asmtSelectTypeCd }"/>
						<meditag:codename code="${assignmentVO.asmtSelectTypeCd}" category="ASMT_SELECT_TYPE_CD"/>
					</td>
				</c:if>
			</c:if>
		</tr>
		<tr >
			<th scope="row"><lable for="asmtTitle"><spring:message code="lecture.title.assignment.name"/></lable></th>
			<td colspan="3">
				<input type="text" dispName="<spring:message code="lecture.title.assignment.name"/>" maxlength="100" isNull="N" lenCheck="100" name="asmtTitle" value="${assignmentVO.asmtTitle }" onfocus="this.select()" id="asmtTitle" class="form-control input-sm"/>
			</td>
		</tr>
		<tr >
			<th scope="row"><spring:message code="lecture.title.assignment.duration"/></th>
			<td colspan="3">
				<div class="input-group" style="float:left;width:128px;">
					<input type="text" dispName="<spring:message code="lecture.title.assignment.startdate"/>" maxlength="50" isNull="N" lenCheck="50" name="asmtStartDttm" value="${assignmentVO.asmtStartDttm }" id="asmtStartDttm" class="inputDate form-control input-sm"/>
					<span class="input-group-addon btn-sm" onclick="_clickCalendar('asmtStartDttm')" style="cursor:pointer">
						<i class="fa fa-calendar"></i>
					</span>
				</div>
				<input type="text" style="float:left;width:40px;text-align:right" dispName="<spring:message code="lecture.title.assignment.starthour"/>" maxlength="2" isNull="N" lenCheck="2" name="asmtStartHour" value="${assignmentVO.asmtStartHour }" class="inputSpecial inputNumber form-control input-sm" onkeyup="isChkHours(this)"/>
				<span style="float:left;line-height:30px;"><spring:message code="common.title.hour"/></span>
				<input type="text" style="float:left;width:40px;text-align:right" dispName="<spring:message code="lecture.title.assignment.startmin"/>" maxlength="2" isNull="N" lenCheck="2" name="asmtStartMin" value="${assignmentVO.asmtStartMin }" class="inputSpecial inputNumber form-control input-sm" onkeyup="isChkMinute(this)"/>
				<span style="float:left;line-height:30px;"><spring:message code="common.title.min"/></span>
				<div class="input-group text-center" style="float:left;line-height:30px;padding-left:10px; padding-right:10px;"> ~ </div>
				<div class="input-group" style="float:left;width:128px;">
					<input type="text" dispName="<spring:message code="lecture.title.assignment.enddate"/>" maxlength="50" isNull="N" lenCheck="50" name="asmtEndDttm" value="${assignmentVO.asmtEndDttm }" id="asmtEndDttm" class="inputDate form-control input-sm" onblur="autoSet();"/>
					<span class="input-group-addon btn-sm" onclick="_clickCalendar('asmtEndDttm')" style="cursor:pointer">
						<i class="fa fa-calendar"></i>
					</span>
				</div>
				<input type="text" style="float:left;width:40px;text-align:right" dispName="<spring:message code="lecture.title.assignment.endhour"/>" maxlength="2" isNull="N" lenCheck="2" name="asmtEndHour" value="${assignmentVO.asmtEndHour }" class="inputSpecial inputNumber form-control input-sm" onkeyup="isChkHours(this)" onblur="autoSet();"/>
				<span style="float:left;line-height:30px;"><spring:message code="common.title.hour"/></span>
				<input type="text" style="float:left;width:40px;text-align:right" dispName="<spring:message code="lecture.title.assignment.endmin"/>" maxlength="2" isNull="N" lenCheck="2" name="asmtEndMin" value="${assignmentVO.asmtEndMin }" class="inputSpecial inputNumber form-control input-sm" onkeyup="isChkMinute(this)" onblur="autoSet();"/>
				<span style="float:left;line-height:30px;"><spring:message code="common.title.min"/></span>
				<meditag:datepicker name1="asmtStartDttm" name2="asmtEndDttm" />
			</td>
		</tr>
		<tr >
			<th scope="row"><spring:message code="lecture.title.assignment.delaydate"/></th>
			<td colspan="3">
				<div class="input-group" style="float:left;width:128px;">
					<input type="text" dispName="<spring:message code="lecture.title.assignment.delaydate"/>" maxlength="50" isNull="N" lenCheck="50" name="extSendDttm" value="${assignmentVO.extSendDttm }" id="extSendDttm" class="inputDate form-control input-sm"/>
					<span class="input-group-addon btn-sm" onclick="_clickCalendar('extSendDttm')" style="cursor:pointer">
						<i class="fa fa-calendar"></i>
					</span>
				</div>
				<input type="text" style="float:left;width:40px;text-align:right" dispName="<spring:message code="lecture.title.assignment.delayhour"/>" maxlength="2" isNull="N" lenCheck="2" name="extSendHour" value="${assignmentVO.extSendHour }" class="inputSpecial inputNumber form-control input-sm" onkeyup="isChkHours(this)"/>
				<span style="float:left;line-height:30px;"><spring:message code="common.title.hour"/></span>
				<input type="text" style="float:left;width:40px;text-align:right" dispName="<spring:message code="lecture.title.assignment.delaymin"/>" maxlength="2" isNull="N" lenCheck="2" name="extSendMin" value="${assignmentVO.extSendMin }" class="inputSpecial inputNumber form-control input-sm" onkeyup="isChkMinute(this)"/>
				<span style="float:left;line-height:30px;"><spring:message code="common.title.min"/></span>
				<meditag:datepicker name1="extSendDttm" />
			</td>
		</tr>
		<tr>
			<th scope="row"><lable for="asmtLimitCnt"><spring:message code="lecture.title.assignment.send.cnt"/></lable></th>
			<td >
				<input type="text" style="float:left;width:50px;text-align:right;" dispName="<spring:message code="lecture.title.assignment.send.cnt"/>" maxlength="9" isNull="N" lenCheck="9" name="asmtLimitCnt" value="${assignmentVO.asmtLimitCnt }" onfocus="this.select()" id="asmtLimitCnt" class="inputSpecial inputNumber form-control input-sm" onkeyup="isChkNumber(this)"/>
				<span style="float:left;line-height:30px;"><spring:message code="common.title.times.postfix"/></span>
			</td>
			<th scope="row"><lable for="asmtLimitCnt">제출가능 진도율</lable></th>
			<td >
				<input type="text" style="float:left;width:50px;text-align:right;" dispName="제출가능 진도율" maxlength="9" isNull="N" lenCheck="9" name="sendCritPrgrRatio" value="${assignmentVO.sendCritPrgrRatio }" onfocus="this.select()" id="sendCritPrgrRatio" class="inputSpecial inputNumber form-control input-sm" onkeyup="isChkNumber(this)"/>
				<span style="float:left;line-height:30px;">%</span>
			</td>
		</tr>
		<tr>
			<th scope="row"><spring:message code="common.title.atachfile"/></th>
			<td colspan="3">
				<div class="upload">
					<div class="upload_inbox">
						<a href="javascript:uploderclick('atchuploader');" class="btn3 solid fcBlue""><spring:message code="button.select.file"/></a>
						<input type="file" name="atchuploader" id="atchuploader" title="<spring:message code="common.title.atachfile"/>" multiple style="display:none" /><%-- 첨부파일 버튼 --%>
						<div id="atchprogress" class="progress">
		    				<div class="progress-bar progress-bar-success"></div>
						</div>
					</div>
					<div id="atchfiles" class="multi_inbox"></div>
				</div>
			</td>
		</tr>
		<tr >
			<th scope="row"><lable for="asmtCts"><spring:message code="common.title.cnts"/></lable></th>
			<td colspan="3">
				<textarea style="height:100px" dispName="<spring:message code="common.title.cnts"/>" isNull="N" name="asmtCts" id="asmtCts" class="form-control input-sm">${assignmentVO.asmtCts }</textarea>
			</td>
		</tr>
		<tr id="regYnArea" style="display: none;">
			<th scope="row"><spring:message code="lecture.title.assignment.regyn"/></th>
			<td colspan="3">
				<select name="regYn" id="regYn" class="form-control input-sm">
					<c:forEach var="item" items="${regTypeList}" varStatus="status">
					<option value="${item.codeCd}" <c:if test="${assignmentVO.regYn eq item.codeCd}">checked</c:if> >${item.codeNm}</option>
					</c:forEach>				
				</select>
			</td>
		</tr>
	</table>
	</form>
	<div class="text-right">
		<c:if test="${gubun eq 'A'}">
		<a href="javascript:addAssignment()" class="btn3 type1"><spring:message code="button.add"/></a>
		</c:if>
		<c:if test="${gubun eq 'E'}">
		<a href="javascript:editAssignment()" class="btn3 type1"><spring:message code="button.add"/></a>
		<a href="javascript:deleteAssignment()" class="btn3 solid fcOrange"><spring:message code="button.delete"/></a>
		</c:if>
		<a href="javascript:parent.modalBoxClose()" class="btn3"><spring:message code="button.close"/></a>
	</div>
<script type="text/javascript">
	var atchFiles; // 첨부파일 목록

	$(document).ready(function() {
		$('.inputNumber').inputNumber();	// 숫자만 입력되도록 설정.
		$('.inputDate').inputDate();  // 날짜 형식만 입력되도록 설정.
		$('.inputSpecial').inputSpecial();  // 특수키가 입력이 들어오묜 false return;

		// 파일 첨부 업로더 및 바인딩 및 초기화 @see /js/common_function.js 참조.
		atchFiles = new $M.JqueryFileUpload({
							"varName"			: "atchFiles",
							"files" 			: $.parseJSON('${assignmentVO.attachFilesJson}'),
							"uploaderId"		: "atchuploader",
							"fileListId"		: "atchfiles",
							"progressId"		: "atchprogress",
							"maxcount"			: 3,
							"uploadSetting"		: {
								'formData'		: { 'repository': 'ASMT',
													'organization' : "${USER_ORGCD}",
													'type'		: 'file' }
							}
						});

		changeAsmtType();
		$("#regYn").val("${assignmentVO.regYn}");
	});

	function uploderclick(str) {
		$("#"+str).click();
	}

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		if(!validate(document.assignmentForm)) return;

		$('#assignmentForm').attr("action","/lec/assignment/" + cmd);
		$('#assignmentForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		alert(resultDTO.message);

		if(resultDTO.result >= 0) {
			// 정상 처리
			parent.location.reload();
			parent.modalBoxClose();
		} else {
			// 비정상 처리
		}
	}

	/**
	 * 과제 기본 정보 등록
	 */
	function addAssignment() {
		var f = document.assignmentForm;

		$(':input:hidden[name=attachFileSns]').val(atchFiles.getFileSnAll());
		$('#uploadify').attr("disabled", true); //-- 파일 객체 disable

		var asmtSubmit = f["asmtLimitCnt"].value;
		var asmtTypeCd = $("#asmtTypeCd > option:selected").val();

		if(isEmpty(f["asmtTitle"].value)) {
			alert("<spring:message code="lecture.message.assignment.alert.input.name"/>");
			f["asmtTitle"].focus();
			return;
		}

		var asmtStartDttm = chgDate(f["asmtStartDttm"].value);
		var asmtEndDttm = chgDate(f["asmtEndDttm"].value);
		var extSendDttm = chgDate(f["extSendDttm"].value);

		if(!dateCheck(asmtEndDttm, extSendDttm)) {
			alert("<spring:message code="lecture.message.assignment.alert.delaydate"/>");
			return;
		}

		if(!asmtStartDttm) {
			alert("<spring:message code="lecture.message.assignment.alert.input.startdate"/>");
			return;
		}

		if(!asmtEndDttm) {
			alert("<spring:message code="lecture.message.assignment.alert.input.enddate"/>");
			return;
		}

		if(!extSendDttm) {
			alert("<spring:message code="lecture.message.assignment.alert.input.delaydate"/>");
			return;
		}

		//시간,분 기본값 입력해주기
		if(isEmpty(f["asmtStartHour"].value)) {
			f["asmtStartHour"].value="0";
		}
		if(isEmpty(f["asmtStartMin"].value)) {
			f["asmtStartMin"].value="0";
		}
		if(isEmpty(f["asmtEndHour"].value)) {
			f["asmtEndHour"].value="0";
		}
		if(isEmpty(f["asmtEndMin"].value)) {
			f["asmtEndMin"].value="0";
		}
		if(isEmpty(f["extSendHour"].value)) {
			f["extSendHour"].value="0";
		}
		if(isEmpty(f["extSendMin"].value)) {
			f["extSendMin"].value="0";
		}

		if(asmtSubmit == 0 && asmtTypeCd == 'ON'){
			alert("<spring:message code="lecture.message.assignment.alert.input.sendcnt"/>");
			f["asmtLimitCnt"].focus();
			return;
		}

		/* if(asmtTypeCd == 'OFF') {
			$("#regYn").val("Y");
		} else {
			$("#regYn").val("N");
		}
 */
		if(validateTime()==false) return;

		process("addAssignment");	// cmd
	}

	/**
	 * 과제 기본 정보 등록
	 */
	function editAssignment() {
		var f = document.assignmentForm;
		$(':input:hidden[name=attachFileSns]').val(atchFiles.getFileSnAll());
		$('#uploadify').attr("disabled", true); //-- 파일 객체 disable
		var asmtSubmit = f["asmtLimitCnt"].value;
		if(isEmpty(f["asmtTitle"].value)) {
			alert("<spring:message code="lecture.message.assignment.alert.input.name"/>");
			f["asmtTitle"].focus();
			return;
		}
		var asmtStartDttm = chgDate(f["asmtStartDttm"].value);
		var asmtEndDttm = chgDate(f["asmtEndDttm"].value);
		var extSendDttm = chgDate(f["extSendDttm"].value);

		if(!dateCheck(asmtEndDttm, extSendDttm)) {
			alert("<spring:message code="lecture.message.assignment.alert.delaydate"/>");
			return;
		}

		if(!asmtStartDttm) {
			alert("<spring:message code="lecture.message.assignment.alert.input.startdate"/>");
			return;
		}

		if(!asmtEndDttm) {
			alert("<spring:message code="lecture.message.assignment.alert.input.enddate"/>");
			return;
		}

		if(!extSendDttm) {
			alert("<spring:message code="lecture.message.assignment.alert.input.delaydate"/>");
			return;
		}

		//시간,분 기본값 입력해주기
		if(isEmpty(f["asmtStartHour"].value)) {
			f["asmtStartHour"].value="0";
		}
		if(isEmpty(f["asmtStartMin"].value)) {
			f["asmtStartMin"].value="0";
		}
		if(isEmpty(f["asmtEndHour"].value)) {
			f["asmtEndHour"].value="0";
		}
		if(isEmpty(f["asmtEndMin"].value)) {
			f["asmtEndMin"].value="0";
		}
		if(isEmpty(f["extSendHour"].value)) {
			f["extSendHour"].value="0";
		}
		if(isEmpty(f["extSendMin"].value)) {
			f["extSendMin"].value="0";
		}

		if(asmtSubmit == 0 && asmtTypeCd == 'ON'){
			alert("<spring:message code="lecture.message.assignment.alert.input.sendcnt"/>");
			f["asmtLimitCnt"].focus();
			return;
		}

		if(validateTime()==false) return;

		process("editAssignment");	// cmd
	}

	/**
	 * 과제 기본 정보 등록
	 */
	function deleteAssignment() {
		var sendCnt = ${assignmentVO.sendCnt};
		$(':input:hidden[name=attachFileSns]').val(atchFiles.getFileSnAll());
		$('#uploadify').attr("disabled", true); //-- 파일 객체 disable
		
		if(sendCnt > 0){
			if(confirm("<spring:message code="lecture.message.assignment.confirm.delete2"/>")){
				process("deleteAssignment");
			}
		}else{
			if(confirm("<spring:message code="lecture.message.assignment.confirm.delete1"/>")){
				process("deleteAssignment");
			}
		}
	}

	/*입력한 시간의 유효성을 체크한다.
	*  폼 벨리데이션 체크를 하지 않아서 빈값까지 여기서 검증함
	*/
	function validateTime(){

		var f = document.assignmentForm;

		var asmtStartHour = chgDate(f["asmtStartHour"].value);  //과제 시작일 시''
		var asmtStartMin = chgDate(f["asmtStartMin"].value);   //과제 시작일 분''

		var asmtEndHour = chgDate(f["asmtEndHour"].value);  //과제 종료일 시''
		var asmtEndMin = chgDate(f["asmtEndMin"].value);   //과제 종료일 분''

		var extSendHour = chgDate(f["extSendHour"].value);  //과제 제출일 시''
		var extSendMin = chgDate(f["extSendMin"].value);   //과제 제출일 분''

		if(asmtStartHour==""){
			alert("<spring:message code="lecture.message.assignment.alert.input.starthour"/>" );
			f["asmtStartHour"].focus();
			return false;
		}
		else if(asmtStartMin==""){
			alert("<spring:message code="lecture.message.assignment.alert.input.starthour"/>" );
			f["asmtStartMin"].focus();
			return false;
		}
		else if(asmtEndHour==""){
			alert("<spring:message code="lecture.message.assignment.alert.input.startmin"/>" );
			f["asmtEndHour"].focus();
			return false;
		}
		else if(asmtEndMin==""){
			alert("<spring:message code="lecture.message.assignment.alert.input.endhour"/>" );
			f["asmtEndMin"].focus();
			return false;
		}
		else if(extSendHour==""){
			alert("<spring:message code="lecture.message.assignment.alert.input.delayhour"/>" );
			f["extSendHour"].focus();
			return false;
		}
		else if(extSendMin==""){
			alert("<spring:message code="lecture.message.assignment.alert.input.delaymin"/>" );
			f["extSendMin"].focus();
			return false;
		}
		if(asmtStartHour>24 || asmtStartHour>24  || extSendHour>24){
			alert("<spring:message code="lecture.message.assignment.alert.validate.hour"/>");
			return false;
		}

		if(asmtStartMin>59 || asmtEndMin>59  || extSendMin>59){
			alert("<spring:message code="lecture.message.assignment.alert.validate.min"/>");
			return false;
		}

		return true;
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

	//날짜 사이의 간격 구하기
	function getGapDate() {
		var f = document.f;
		var from = f.serviceStart.value;
		var to = f.serviceEnd.value;
		if(from != "" && to != "") {
			var days = jsGetDays(chgDate(from),chgDate(to));

			f.serviceDay.value = days;

		}
	}

	function changeAsmtType() {
		var asmtType = $("#asmtTypeCd").val();
		if(asmtType == "OFF") {
			$("#asmtSelectTypeCd").attr("disabled",true);
			$("#asmtLimitCnt").attr("disabled",true);
			$("#sendCritPrgrRatio").attr("disabled",true);
			$("#regYn").val("Y");
			$("#regYnArea").show();
		} else {
			$("#asmtSelectTypeCd").attr("disabled",false);
			$("#asmtLimitCnt").attr("disabled",false);
			$("#sendCritPrgrRatio").attr("disabled",false);
			$("#regYnArea").hide();
			$("#regYn").val("N");
		}
	}

	function autoSet(){
		var f = document.assignmentForm;
		$('#extSendDttm').val($('#asmtEndDttm').val());
		f["extSendHour"].value = f["asmtEndHour"].value;
		f["extSendMin"].value = f["asmtEndMin"].value;
	}
</script>
