<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="img_base" value="/img/home" />
<c:set var="gubun" value="${gubun}"/>
<c:set var="examVO" value="${vo}"/>
				<form id="examForm" name="examForm" onsubmit="return false">
				<input type="hidden" name="crsCreCd" value="${vo.crsCreCd}" />
				<input type="hidden" name="examSn" value="${vo.examSn}" />
				<input type="hidden" name="regYn" value="${vo.regYn}"/>
				<input type="hidden" name="examViewTypeCd" value="L"/>
				<div class="row">
					<div class="col-lg-12">
						<table class="table table-bordered">
							<caption class="sr-only"><spring:message code="lecture.title.exam.manage"/></caption>
							<colgroup>
								<col style="width:20%;" />
								<col />
								<col style="width:20%;" />
								<col />
							</colgroup>
							<tbody>
								<tr>
									<th scope="row"><spring:message code="lecture.title.exam.examtype"/></th>
									<td>
										<html:select name="examTypeCd" id="selectboxExCd" onchange="changeExamType()" class="form-control input-sm">
											<c:forEach items="${examTypeList}" var="item">
												<c:set var="codeName" value="${item.codeNm}"/>
												<c:forEach var="lang" items="${item.codeLangList}">
													<c:if test="${lang.langCd eq LOCALEKEY }"><c:set var="codeName" value="${lang.codeNm}"/></c:if>
												</c:forEach>
												<html:option value="${item.codeCd}">${codeName}</html:option>
											</c:forEach>
										</html:select>
									</td>
									<th scope="row"><spring:message code="lecture.title.exam.ansrtype"/></th>
									<td>
										<html:select name="examStareTypeCd" id="selectboxTypeCd" onchange="changeExamStareType()" class="form-control input-sm">
											<c:forEach items="${examStareTypeList}" var="item">
												<c:set var="codeName" value="${item.codeNm}"/>
												<c:forEach var="lang" items="${item.codeLangList}">
													<c:if test="${lang.langCd eq LOCALEKEY }"><c:set var="codeName" value="${lang.codeNm}"/></c:if>
												</c:forEach>
												<html:option value="${item.codeCd}">${codeName}</html:option>
											</c:forEach>
										</html:select>
									</td>
								</tr>
								<tr>
									<th scope="row"><spring:message code="lecture.title.exam.name"/></th>
									<td colspan="3"><input type="text" dispName="<spring:message code="lecture.title.exam.name"/>" maxlength="100" isNull="N" lenCheck="100" name="examTitle" value="${vo.examTitle}" onfocus="this.select()" class="form-control input-sm"/></td>
								</tr>
								<tr>
									<th scope="row"><spring:message code="lecture.title.exam.duration"/></th>
									<td colspan="3">
										<div class="examTypeR">
											<div class="input-group" style="float:left;width:128px;">
												<input type="text" dispName="<spring:message code="lecture.title.exam.start.date"/>" maxlength="50" isNull="Y" lenCheck="50" name="examStartDttm" value="${vo.examStartDttm}" id="examStartDttm" class="inputDate examStareTypeControl form-control input-sm"/>
												<span class="input-group-addon btn-sm" onclick="_clickCalendar('examStartDttm')" style="cursor:pointer">
													<i class="fa fa-calendar"></i>
												</span>
											</div>
											<input type="text" style="float:left;width:40px;text-align:right" dispName="<spring:message code="lecture.title.exam.start.hour"/>" maxlength="2" isNull="Y" lenCheck="2" name="examStartHour" value="${vo.examStartHour}" id="examStartHour" class="examStareTypeControl inputSpecial inputNumber form-control input-sm" onkeyup="isChkHours(this)"/>
											<span style="float:left;line-height:30px;"><spring:message code="common.title.hour"/></span>
											<input type="text" style="float:left;width:40px;text-align:right" dispName="<spring:message code="lecture.title.exam.start.min"/>" maxlength="2" isNull="Y" lenCheck="2" name="examStartMin" value="${vo.examStartMin}" id="examStartMin" class="examStareTypeControl inputSpecial inputNumber form-control input-sm" onkeyup="isChkMinute(this)"/>
											<span style="float:left;line-height:30px;"><spring:message code="common.title.min"/></span>

											<div class="input-group text-center" style="float:left;padding-left:10px; padding-right:10px;"> ~ </div>

											<div class="input-group" style="float:left;width:128px;">
												<input type="text" dispName="<spring:message code="lecture.title.exam.end.date"/>" maxlength="50" isNull="Y" lenCheck="50" name="examEndDttm" value="${vo.examEndDttm}" id="examEndDttm" class="inputDate examStareTypeControl form-control input-sm"/>
												<span class="input-group-addon btn-sm" onclick="_clickCalendar('examEndDttm')" style="cursor:pointer">
													<i class="fa fa-calendar"></i>
												</span>
											</div>
											<input type="text" style="float:left;width:40px;text-align:right" dispName="<spring:message code="lecture.title.exam.end.hour"/>" maxlength="2" isNull="Y" lenCheck="2" name="examEndHour" value="${vo.examEndHour}" id="examEndHour" class="examStareTypeControl inputSpecial inputNumber form-control input-sm" onkeyup="isChkHours(this)"/>
											<span style="float:left;line-height:30px;"><spring:message code="common.title.hour"/></span>
											<input type="text" style="float:left;width:40px;text-align:right" dispName="<spring:message code="lecture.title.exam.end.min"/>" maxlength="2" isNull="Y" lenCheck="2" name="examEndMin" value="${vo.examEndMin}" id="examEndMin" class="examStareTypeControl inputSpecial inputNumber form-control input-sm" onkeyup="isChkMinute(this)"/>
											<span style="float:left;line-height:30px;"><spring:message code="common.title.min"/></span>
											<meditag:datepicker name1="examStartDttm" name2="examEndDttm" />
										</div>
										<div class="examTypeS">
											<spring:message code="lecture.message.exam.msg.duration.sangsi"/>
										</div>
									</td>
								</tr>
								<tr>
									<th scope="row"><spring:message code="lecture.title.exam.result.date"/></th>
									<td colspan="3">
										<div class="examTypeR">
											<div class="input-group" style="float:left;width:128px;">
												<input type="text" dispName="<spring:message code="lecture.title.exam.result.date"/>" maxlength="50" isNull="Y" lenCheck="50" name="rsltCfrmDttm" value="${vo.rsltCfrmDttm}" id="rsltCfrmDttm" class="inputDate examStareTypeControl form-control input-sm"/>
												<span class="input-group-addon btn-sm" onclick="_clickCalendar('rsltCfrmDttm')" style="cursor:pointer">
													<i class="fa fa-calendar"></i>
												</span>
											</div>
											<input type="text" style="float:left;width:40px;text-align:right;" dispName="<spring:message code="lecture.title.exam.result.hour"/>" maxlength="2" isNull="Y" lenCheck="2" name="rsltCfrmHour" value="${vo.rsltCfrmHour}" id="rsltCfrmHour" class="examStareTypeControl inputSpecial inputNumber form-control input-sm" onkeyup="isChkHours(this)"/>
											<span style="float:left;line-height:30px;"><spring:message code="common.title.hour"/></span>
											<input type="text" style="float:left;width:40px;text-align:right;" dispName="<spring:message code="lecture.title.exam.result.min"/>" maxlength="2" isNull="Y" lenCheck="2" name="rsltCfrmMin" value="${vo.rsltCfrmMin}" id="rsltCfrmMin" class="examStareTypeControl inputSpecial inputNumber form-control input-sm" onkeyup="isChkMinute(this)"/>
											<span style="float:left;line-height:30px;"><spring:message code="common.title.min"/></span>
											<meditag:datepicker name1="rsltCfrmDttm" />
										</div>
										<div class="examTypeS">
											<spring:message code="lecture.message.exam.msg.resultdate.sangsi"/>
										</div>
									</td>
								</tr>
								<tr>
									<th scope="row"><spring:message code="lecture.title.exam.timelimit"/></th>
									<td>
										<html:select name="stareTmUseYn" id="selectboxTimeUseCd" class="form-control input-sm" onchange="changeExamStareTm()">
											<c:forEach items="${stareTmUseList}" var="item">
												<c:set var="codeName" value="${item.codeNm}"/>
												<c:forEach var="lang" items="${item.codeLangList}">
													<c:if test="${lang.langCd eq LOCALEKEY }"><c:set var="codeName" value="${lang.codeNm}"/></c:if>
												</c:forEach>
												<html:option value="${item.codeCd}">${codeName}</html:option>
											</c:forEach>
										</html:select>
									</td>
									<th scope="row"><spring:message code="lecture.title.exam.time"/></th>
									<td>
										<input type="text" style="float:left;text-align:right;width:60px;" dispName="<spring:message code="lecture.title.exam.time"/>" maxlength="4" isNull="N" lenCheck="4" name="examStareTm" value="${vo.examStareTm}" onfocus="this.select()" class="inputNumber examTypeControl form-control input-sm"  onkeyup="isChkNumber(this)"/>
										<span style="float:left;line-height:30px;"><spring:message code="common.title.min"/></span>
									</td>
								</tr>
								<tr>
									<th scope="row"><spring:message code="lecture.title.exam.answer.ratio"/></th>
									<td>
										<div class="examTypeS">
											<input type="text" style="float:left;text-align:right;width:50px;" dispName="<spring:message code="lecture.title.exam.answer.ratio"/>" maxlength="3" isNull="N" lenCheck="3" name="stareCritPrgrRatio" value="${vo.stareCritPrgrRatio}" onfocus="this.select()" class="inputSpecial inputNumber examTypeControl examStareTypeControl form-control input-sm" onkeyup="isChkMaxNumber(this,100)"/>
											<span style="float:left;line-height:30px;"> % <spring:message code="common.title.over"/></span>
										</div>
										<div class="examTypeR">
											<spring:message code="lecture.message.exam.msg.answer.ratio.sangsi"/>
										</div>
									</td>
									<th scope="row"><spring:message code="lecture.title.exam.limitcnt"/></th>
									<td>
										<input type="text" style="float:left;text-align:right;width:50px;" dispName="<spring:message code="lecture.title.exam.limitcnt"/>" maxlength="3" isNull="N" lenCheck="3" name="stareLimitCnt" value="${vo.stareLimitCnt}" onfocus="this.select()" class="examTypeControl inputSpecial inputNumber form-control input-sm" onkeyup="isChkNumber(this)"/>
									</td>
								</tr>
								<tr>
									<th scope="row"><spring:message code="lecture.title.exam.desc"/></th>
									<td colspan="3">
										<textarea style="width:100%;height:120px;" rows="13" dispName="<spring:message code="lecture.title.exam.desc"/>" name="examCts" class="form-control input-sm"/>
									</td>
								</tr>
								<tr id="input_regYn" style="display: none;">
									<th scope="row" style="width:20%"><spring:message code="lecture.title.exam.regyn"/></th>
									<td colspan="3">
										<select name="regYn" id="regYn" class="form-control input-sm">
											<c:forEach items="${regYnList}" var="item">
												<c:set var="codeName" value="${item.codeNm}"/>
												<c:forEach var="lang" items="${item.codeLangList}">
													<c:if test="${lang.langCd eq LOCALEKEY }"><c:set var="codeName" value="${lang.codeNm}"/></c:if>
												</c:forEach>
											<option value="${item.codeCd}">${codeName}</option>
											</c:forEach>
										</select>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
				<div class="row" style="margin-bottom:20px;">
					<div class="col-lg-12">
						<div class="text-right">
							<a href="javascript:editExam()" class="btn btn-primary btn-sm"><spring:message code="button.add"/></a>
							<a href="javascript:goView()" class="btn btn-default btn-sm"><spring:message code="button.cancel"/></a>
						</div>
					</div>
				</div>
				</form>

<script type="text/javascript">
	$(document).ready(function() {
		$('.inputNumber').inputNumber();	// 숫자만 입력되도록 설정.
		$('.inputDate').inputDate();  // 날짜 형식만 입력되도록 설정.
		$('.inputSpecial').inputSpecial();  // 특수키가 입력이 들어오묜 false return;

		changeExamType();
		changeExamStareType();
		document.examForm["examTitle"].focus();

		var width = (parent.window.innerWidth > 0) ? parent.window.innerWidth : parent.screen.width;
		width = width - 20;
		if(width > 1000) width = 1000;
		parent.modalBox.resize(width,600);

	});

	function goView(){
		parent.modalBox.resize(800,400);
		history.go(-1);
	}

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		$('#examForm').attr("action","/lec/exam/"+cmd);
		$('#examForm').ajaxSubmit(processCallback);
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

	function changeExamType() {

		var f = document.examForm;
		var examType = "";
		examType = f["examTypeCd"].value;
		if(examType == "OFF") {
			var examStareType = f["examStareTypeCd"].value;
			if(examStareType == "S") {
				alert("<spring:message code="lecture.message.exam.alert.change.examtype"/>");
				return;
			}
			f["examStareTypeCd"].disabled = true;
			//f["examViewTypeCd"].disabled = true;
			f["stareTmUseYn"].disabled = true;
			$('.examTypeControl').attr("disabled",true).removeClass("enable").addClass("disable");
			$('#input_regYn').show();
		} else {
			f["examStareTypeCd"].disabled = false;
			//f["examViewTypeCd"].disabled = false;
			f["stareTmUseYn"].disabled = false;
			$('.examTypeControl').removeAttr("disabled").removeClass("disable").addClass("enable");
			$('#input_regYn').hide();
			changeExamStareType();
		}
	}

	function changeExamStareType() {
		var f = document.examForm;
		var examStareType = f["examStareTypeCd"].value;
		if(examStareType == "S") {
			f["examStartDttm"].disabled = true;
			f["examStartHour"].disabled = true;
			f["examStartMin"].disabled = true;
			f["examEndDttm"].disabled = true;
			f["examEndHour"].disabled = true;
			f["examEndMin"].disabled = true;
			f["rsltCfrmDttm"].disabled = true;
			f["rsltCfrmHour"].disabled = true;
			f["rsltCfrmMin"].disabled = true;
			f["stareCritPrgrRatio"].disabled = false;
			$('img.fn_calimg').hide();
			$('.examTypeR').hide();
			$('.examTypeS').show();
		} else {
			f["examStartDttm"].disabled = false;
			f["examStartHour"].disabled = false;
			f["examStartMin"].disabled = false;
			f["examEndDttm"].disabled = false;
			f["examEndHour"].disabled = false;
			f["examEndMin"].disabled = false;
			f["rsltCfrmDttm"].disabled = false;
			f["rsltCfrmHour"].disabled = false;
			f["rsltCfrmMin"].disabled = false;
			f["stareCritPrgrRatio"].disabled = true;
			$('img.fn_calimg').show();
			$('.examTypeR').show();
			$('.examTypeS').hide();
		}
	}

	function changeExamStareTm() {
		var f = document.examForm;
		var stareTmUseYn = f["stareTmUseYn"].value;
		if(stareTmUseYn == "Y") {
			f["examStareTm"].disabled = false;
		} else {
			f["examStareTm"].disabled = true;
		}
	}

	function examList(){
		document.location.href=cUrl("/lec/exam/examMain");
	}

	/*입력한 시간의 유효성을 체크한다.
	*  폼 벨리데이션 체크를 하지 않아서 빈값까지 여기서 검증함
	*/
	function validateTime(){

		var f = document.examForm;

		if(f["examStartDttm"].value == ""){
			alert("<spring:message code="lecture.message.exam.alert.input.startdate"/>");
			return false;
		}

		if(f["examEndDttm"].value == ""){
			alert("<spring:message code="lecture.message.exam.alert.input.enddate"/>");
			return false;
		}

		if(f["rsltCfrmDttm"].value == ""){
			alert("<spring:message code="lecture.message.exam.alert.input.resultdate"/>");
			return false;
		}

		var asmtStartHour = chgDate(f["examStartHour"].value);  //과제 시작일 시''
		var asmtStartMin = chgDate(f["examStartMin"].value);   //과제 시작일 분''

		var asmtEndHour = chgDate(f["examEndHour"].value);  //과제 종료일 시''
		var asmtEndMin = chgDate(f["examEndMin"].value);   //과제 종료일 분''

		var extSendHour = chgDate(f["rsltCfrmHour"].value); //결과확인일 시
		var extSendMin = chgDate(f["rsltCfrmMin"].value);   //결과확인일 분

		if(asmtStartHour==""){
			alert("<spring:message code="lecture.message.exam.alert.input.starthour"/>" );
			f["examStartHour"].focus();
			return false;
		}
		if(asmtStartMin==""){
			alert("<spring:message code="lecture.message.exam.alert.input.startmin"/>" );
			f["examStartMin"].focus();
			return false;
		}
		if(asmtEndHour==""){
			alert("<spring:message code="lecture.message.exam.alert.input.endhour"/>" );
			f["examEndHour"].focus();
			return false;
		}
		if(asmtEndMin==""){
			alert("<spring:message code="lecture.message.exam.alert.input.endmin"/>" );
			f["examEndMin"].focus();
			return false;
		}
		if(extSendHour==""){
			alert("<spring:message code="lecture.message.exam.alert.input.resulthour"/>" );
			f["rsltCfrmHour"].focus();
			return false;
		}
		if(extSendMin==""){
			alert("<spring:message code="lecture.message.exam.alert.input.resultmin"/>" );
			f["rsltCfrmMin"].focus();
			return false;
		}

		if(asmtStartHour>24 || asmtStartHour>24  || extSendHour>24){
			alert("<spring:message code="lecture.message.exam.alert.validate.hour"/>");
			return false;
		}

		if(asmtStartMin>59 || asmtEndMin>59  || extSendMin>59){
			alert("<spring:message code="lecture.message.exam.alert.validate.min"/>");
			return false;
		}

		return true;
	}

	/**
	 * 과정 개설 등록
	 */
	function editExam() {
		var examStareType = '${examVO.examStareTypeCd}';
		var examType = '${examVO.examTypeCd}';

		var f = document.examForm;

		var limitCnt = f["stareLimitCnt"].value;

		if(isEmpty(f["examTitle"].value)) {
			alert("<spring:message code="lecture.message.exam.alert.input.name"/>");
			f["examTitle"].focus();
			return;
		}
		if(limitCnt == 0 && examType == 'ON'){
			alert("<spring:message code="lecture.message.exam.alert.input.answercnt"/>");
			f["stareLimitCnt"].focus();
			return;
		}

		if(validate(document.examForm) ==false) return ; //1차 null 체크

		if(examStareType == "R") {
			if(validateTime()==false) return;
			var examStartDttm = chgDate(f["examStartDttm"].value);
			var examEndDttm = chgDate(f["examEndDttm"].value);
			var rsltCfrmDttm = chgDate(f["rsltCfrmDttm"].value);

			//if(!dateCheck(examEndDttm, rsltCfrmDttm)) {
			//	alert("결과확인일을 종료일 보다 늦은 날짜를 선택하셔야 합니다.");
			//	return;
			//}
			if(examType == "ON") {
				var examStareTm = parseInt(f["examStareTm"].value,10);
				var stareTmUseYn = f["stareTmUseYn"].value;

				if(stareTmUseYn == "Y") {
					if(examStareTm <= 0) {
						alert("<spring:message code="lecture.message.exam.alert.answer.time"/>");
						f["examStareTm"].focus();
						return;
					}
				}
			}
		} else {
			var stareCritPrgrRatio = parseInt(f["stareCritPrgrRatio"].value,10);
			if(stareCritPrgrRatio <=0 ) {
				alert("<spring:message code="lecture.message.exam.alert.answer.ratio"/>");
				f["stareCritPrgrRatio"].focus();
				return;
			}
		}

		var StartDttm = $("#examStartDttm").val();
		var EndDttm = $("#examEndDttm").val();
		var StartDttmArray = StartDttm.split(".");
		var EndDttmArray = EndDttm.split(".");
		var StartDttmObj = new Date(StartDttmArray[0], Number(StartDttmArray[1])-1, StartDttmArray[2]);
		var EndDttmObj = new Date(EndDttmArray[0], Number(EndDttmArray[1])-1, EndDttmArray[2]);

		if(StartDttmObj >= EndDttmObj){
			alert("<spring:message code="lecture.message.exam.alert.compare.date"/>");
			return;
		}

		process("editExamJson");	// cmd
	}
</script>
