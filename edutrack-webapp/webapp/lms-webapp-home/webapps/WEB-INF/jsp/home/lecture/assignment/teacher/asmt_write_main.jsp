<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="img_base" value="/img/home" />
<c:set var="gubun" value="${gubun}"/>
<c:set var="assignmentVO" value="${vo}"/>
				<form id="assignmentForm" name="assignmentForm" onsubmit="return false">

				<input type="hidden" name="crsCreCd" value="${vo.crsCreCd }" />
				<input type="hidden" name="asmtSn" value="${vo.asmtSn }" />
				<input type="hidden" name="attachFileSns" value="${vo.attachFileSns }" />
                <div class="learn_top">
                    <div class="left_title">
                        <c:if test="${gubun eq 'A'}"><h3>과제등록</h3></c:if>
                        <c:if test="${gubun eq 'E'}"><h3>과제수정</h3></c:if>
                    </div>
                </div>

                <div class="segment">
                    <div class="tstyle">
                        <ul class="dbody">
                            <li>
                                <div class="row">
                                    <label for="asmtTypeCd" class="form-label col-sm-2"><spring:message code="lecture.title.assignment.type"/></label>
                                    <div class="col-sm-4">
                                        <div class="form-row">
						 				<c:if test="${gubun eq 'A'}">
											<select name="asmtTypeCd" id="asmtTypeCd" class="form-select" onclick="changeAsmtType()">
												<c:forEach var="item" items="${asmtTypeList}" varStatus="status">
													<option value="${item.codeCd}" <c:if test="${assignmentVO.asmtTypeCd eq item.codeCd}">checked</c:if> >${item.codeNm}</option>
												</c:forEach>				
											</select>
										</c:if>
										<c:if test="${gubun eq 'E'}">
										<input type="hidden" name="asmtTypeCd" id="asmtTypeCd" value="${assignmentVO.asmtTypeCd }"/><meditag:codename code="${assignmentVO.asmtTypeCd}" category="ASMT_TYPE_CD"/>
										</c:if>
                                        </div>             
                                    </div>
									                                
                                </div>
                            </li>
                            <li>
                                <div class="row">
                                    <label for="asmtSvcCd" class="form-label col-sm-2">서비스유형</label>
                                    <div class="col-sm-4">
                                        <div class="form-row">
						 				<c:if test="${gubun eq 'A'}">
											<select name="asmtSvcCd" id="asmtSvcCd" class="form-select" onclick="changeAsmtSvcType()">
												<c:forEach var="item" items="${asmtServiceCdList}" varStatus="status">
												<option value="${item.codeCd}" <c:if test="${vo.asmtSvcCd eq item.codeCd}">checked</c:if> >${item.codeNm}</option>
												</c:forEach>				
											</select>
										</c:if>
										<c:if test="${gubun eq 'E'}">
										<input type="hidden" name="asmtSvcCd" id="asmtSvcCd" value="${vo.asmtSvcCd}"/><meditag:codename code="${assignmentVO.asmtSvcCd}" category="ASMT_SVC_CD"/>
										</c:if>
                                        </div>             
                                    </div>
                                    
                                    <c:if test="${gubun eq 'A'}">
                                    <label for="asmtSelectTypeCd" class="form-label col-sm-2"><spring:message code="lecture.title.assignment.seltype"/></label>
                                    <div class="col-sm-4">
                                        <div class="form-row">
                                            <select name="asmtSelectTypeCd" id="asmtSelectTypeCd" class="form-select">
												<c:forEach var="item" items="${asmtSelectTypeList}" varStatus="status">
												<option value="${item.codeCd}" <c:if test="${assignmentVO.asmtSelectTypeCd eq item.codeCd}">checked</c:if> >${item.codeNm}</option>
												</c:forEach>				
											</select>
                                        </div>             
                                    </div>
									</c:if>
									<c:if test="${gubun eq 'E'}">
										<c:if test="${assignmentVO.asmtTypeCd eq 'ON'}">
                                    <label for="asmtSelectTypeCd" class="form-label col-sm-2"><spring:message code="lecture.title.assignment.seltype"/></label>
                                    <div class="col-sm-4">
                                        <div class="form-row">
                                            <input type="hidden" name="asmtSelectTypeCd" id="asmtSelectTypeCd" value="${assignmentVO.asmtSelectTypeCd }"/>
												<meditag:codename code="${assignmentVO.asmtSelectTypeCd}" category="ASMT_SELECT_TYPE_CD"/>
                                        </div>             
                                    </div>
										</c:if>
									</c:if>    
                                </div>
                            </li>
                            <li>
                                <div class="row">
                                    <label for="asmtTitle" class="form-label col-sm-2"><spring:message code="lecture.title.assignment.name"/></label>
                                    <div class="col-sm-10">
                                        <div class="form-row">
                                            <input type="text" dispName="<spring:message code="lecture.title.assignment.name"/>" maxlength="100" isNull="N" lenCheck="100" name="asmtTitle" value="${assignmentVO.asmtTitle }" onfocus="this.select()" id="asmtTitle" class="form-control"/>
                                        </div>             
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="row">
                                    <label for="asmtStartDttm" class="form-label col-sm-2"><span><spring:message code="lecture.title.assignment.duration"/></span></label>
                                    <div class="col-sm-10">
                                        <div class="form-inline">
                                        	<input type="date" dispName="<spring:message code="lecture.title.assignment.startdate"/>" maxlength="50" isNull="N" lenCheck="50" name="asmtStartDttm" value="" id="asmtStartDttm" class="form-control md"/>
                                            <div class="input_btn">
                                                <input type="text" dispName="<spring:message code="lecture.title.assignment.starthour"/>" maxlength="2" isNull="N" lenCheck="2" name="asmtStartHour" id="asmtStartHour" value="${assignmentVO.asmtStartHour }" class="inputSpecial inputNumber form-control sm" onkeyup="isChkHours(this)"/>
                                                <label><spring:message code="common.title.hour"/></label>
                                            </div>
                                            <div class="input_btn">
                                                <input type="text" dispName="<spring:message code="lecture.title.assignment.startmin"/>" maxlength="2" isNull="N" lenCheck="2" name="asmtStartMin" id="asmtStartMin" value="${assignmentVO.asmtStartMin }" class="inputSpecial inputNumber form-control sm" onkeyup="isChkMinute(this)"/>
                                                <label><spring:message code="common.title.min"/></label>
                                            </div><span class="ruffle_sign">~</span>
                                        </div>
                                        <div class="form-inline">
                                            <input type="date" dispName="<spring:message code="lecture.title.assignment.enddate"/>" maxlength="50" isNull="N" lenCheck="50" name="asmtEndDttm" value="" id="asmtEndDttm" class="form-control md" onblur="autoSet();"/>
                                            <div class="input_btn">
                                                <input type="text" dispName="<spring:message code="lecture.title.assignment.endhour"/>" maxlength="2" isNull="N" lenCheck="2" name="asmtEndHour" id="asmtEndHour" value="${assignmentVO.asmtEndHour }" class="inputSpecial inputNumber form-control sm" onkeyup="isChkHours(this)" onblur="autoSet();"/>
                                                <label><spring:message code="common.title.hour"/></label>
                                            </div>
                                            <div class="input_btn">
                                                <input type="text" dispName="<spring:message code="lecture.title.assignment.endmin"/>" maxlength="2" isNull="N" lenCheck="2" name="asmtEndMin" id="asmtEndMin" value="${assignmentVO.asmtEndMin }" class="inputSpecial inputNumber form-control sm" onkeyup="isChkMinute(this)" onblur="autoSet();"/>
                                                <label><spring:message code="common.title.min"/></label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="row">
                                    <label for="extSendDttm" class="form-label col-sm-2"><span><spring:message code="lecture.title.assignment.delaydate"/></span></label>
                                    <div class="col-sm-10">
                                        <div class="form-inline">
                                            <input type="date" dispName="<spring:message code="lecture.title.assignment.delaydate"/>" maxlength="50" isNull="N" lenCheck="50" name="extSendDttm" value="" id="extSendDttm" class="form-control md"/>
                                            <div class="input_btn">
                                                <input type="text" dispName="<spring:message code="lecture.title.assignment.delayhour"/>" maxlength="2" isNull="N" lenCheck="2" name="extSendHour" id="extSendHour" value="${assignmentVO.extSendHour }" class="inputSpecial inputNumber form-control sm" onkeyup="isChkHours(this)"/>
                                                <label><spring:message code="common.title.hour"/></label>
                                            </div>
                                            <div class="input_btn">
                                                 <input type="text" dispName="<spring:message code="lecture.title.assignment.delaymin"/>" maxlength="2" isNull="N" lenCheck="2" name="extSendMin" id="extSendMin" value="${assignmentVO.extSendMin }" class="inputSpecial inputNumber form-control sm" onkeyup="isChkMinute(this)"/>
                                                <label><spring:message code="common.title.min"/></label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="row">
                                    <label for="asmtLimitCnt" class="form-label col-sm-2"><spring:message code="lecture.title.assignment.send.cnt"/></label>
                                    <div class="col-sm-4">
                                        <div class="form-row">
                                            <div class="input_btn">
                                                <input type="text" dispName="<spring:message code="lecture.title.assignment.send.cnt"/>" maxlength="9" isNull="N" lenCheck="9" name="asmtLimitCnt" value="${assignmentVO.asmtLimitCnt }" onfocus="this.select()" id="asmtLimitCnt" class="inputSpecial inputNumber form-control sm" onkeyup="isChkNumber(this)"/>
                                                <label><spring:message code="common.title.times.postfix"/></label>
                                            </div>
                                        </div>             
                                    </div>
                                    <label for="sendCritPrgrRatio" class="form-label col-sm-2">제출가능 진도율</label>
                                    <div class="col-sm-4">
                                        <div class="form-row">
                                            <div class="input_btn">
                                                <input type="text" dispName="제출가능 진도율" maxlength="9" isNull="N" lenCheck="9" name="sendCritPrgrRatio" value="${assignmentVO.sendCritPrgrRatio }" onfocus="this.select()" id="sendCritPrgrRatio" class="form-control sm" onkeyup="isChkNumber(this)"/>
                                                <label>%</label>
                                            </div>
                                        </div>             
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="row">
                                    <span class="form-label col-sm-2"><spring:message code="common.title.atachfile"/></span>
                                    <div class="col-sm-10 attach_area">
                                        <div class="upload">
											<div class="upload_inbox">
												<button onClick="javascript:uploderclick('atchuploader');" class="btn gray2"><spring:message code="button.select.file"/></button>
												<input type="file" name="atchuploader" id="atchuploader" title="<spring:message code="common.title.atachfile"/>" multiple style="display:none" /><%-- 첨부파일 버튼 --%>
												<div id="atchprogress" class="progress">
								    				<div class="progress-bar progress-bar-success"></div>
												</div>
											</div>
											<div id="atchfiles" class="multi_inbox"></div>
										</div>                                        
                                    </div> 
                                </div>
                            </li>
                            <li>
                                <div class="row">
                                    <label for="asmtCts" class="form-label col-sm-2"><spring:message code="common.title.cnts"/></label>
                                    <div class="col-sm-10">
                                        <div class="form-row">
                                            <textarea rows="10" dispName="<spring:message code="common.title.cnts"/>" isNull="N" name="asmtCts" id="asmtCts" class="form-control">${assignmentVO.asmtCts }</textarea>
                                        </div>
                                    </div>
                                </div>
                            </li>                            
                            <li id="regYnArea" style="display: none;">
                                <div class="row">
                                    <label for="asmtCts" class="form-label col-sm-2"><spring:message code="lecture.title.assignment.regyn"/></label>
                                    <div class="col-sm-10">
                                        <div class="form-row">
                                            
											<select name="regYn" id="regYn" class="form-select">
												<c:forEach var="item" items="${regTypeList}" varStatus="status">
												<option value="${item.codeCd}" <c:if test="${assignmentVO.regYn eq item.codeCd}">checked</c:if> >${item.codeNm}</option>
												</c:forEach>				
											</select>
                                        </div>
                                    </div>
                                </div>
                            </li>                            
                        </ul>
                    </div>
                    <div class="btns mt30">
						<c:if test="${gubun eq 'A'}">
						<button type="button" class="btn gray2" onclick="addAssignment()"><spring:message code="button.add"/></button>
						</c:if>
						<c:if test="${gubun eq 'E'}">
						<button type="button" class="btn gray2" onclick="editAssignment()"><spring:message code="button.add"/></button>
						<button type="button" class="btn type9" onclick="deleteAssignment()"><spring:message code="button.delete"/></button>
						</c:if>
                        <button type="button" class="btn type5" onclick="goList()"><spring:message code="button.cancel"/></button>
                    </div>
                </div>
				</form>

<script type="text/javascript">
	var atchFiles; // 첨부파일 목록

	$(document).ready(function() {

		$('.inputNumber').inputNumber();	// 숫자만 입력되도록 설정.
		$('.inputDate').inputDate();	// 숫자만 입력되도록 설정.
		$('.inputSpecial').inputSpecial();  // 특수키가 입력이 들어오묜 false return;
		
		$("#asmtStartDttm").val(formatDate("${assignmentVO.asmtStartDttm}"));
		$("#asmtEndDttm").val(formatDate("${assignmentVO.asmtEndDttm}"));
		$("#extSendDttm").val(formatDate("${assignmentVO.extSendDttm }"));
		
		$("#regYn").val("N");
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
		changeAsmtSvcType();
		$("#regYn").val("${vo.regYn}");
	});

	function uploderclick(str) {
		$("#"+str).click();
	}

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		$('#uploadify').attr("disabled","disabled");
		//if(!validate(document.assignmentForm)) return;
		$('#assignmentForm').attr("action","/lec/assignment/" + cmd);
		document.assignmentForm.submit();
	}

	/**
	 * 과제 기본 정보 등록
	 */
	function addAssignment() {
		$(':input:hidden[name=attachFileSns]').val(atchFiles.getFileSnAll());
		$('#uploadify').attr("disabled", true); //-- 파일 객체 disable
		var f = document.assignmentForm;
		if(isEmpty(f["asmtTitle"].value)) {
			alert("<spring:message code="lecture.message.assignment.alert.input.name"/>");
			f["asmtTitle"].focus();
			return;
		}
		var svcType = $("#asmtSvcCd > option:selected").val();
		if(svcType != "CODE"){
		if(validate(document.assignmentForm) ==false) return ;

		var asmtStartDttm = chgDate(f["asmtStartDttm"].value);
		var asmtEndDttm = chgDate(f["asmtEndDttm"].value);
		var extSendDttm = chgDate(f["extSendDttm"].value);
		var asmtSubmit = f["asmtLimitCnt"].value;
		
			if(!dateCheck(asmtEndDttm, extSendDttm)) {
				alert("<spring:message code="lecture.message.assignment.alert.delaydate"/>");
				return;
			}
	
			var asmtType = $("#asmtTypeCd > option:selected").val();
			if(asmtSubmit == 0 && asmtType == 'ON'){
				alert("<spring:message code="lecture.message.assignment.alert.input.sendcnt"/>");
				f["asmtLimitCnt"].focus();
				return;
			}
	
			var extSend = $('#extSendDttm').val().replace(/[.]/g, "")+f["extSendHour"].value+f["extSendMin"].value;
			var asmtEnd = $('#asmtEndDttm').val().replace(/[.]/g, "")+f["asmtEndHour"].value+f["asmtEndMin"].value;
	
			if(asmtEnd > extSend){
				alert("<spring:message code="lecture.message.assignment.alert.delaydate"/>");
				return;
			}
	
			if(validateTime()==false) return;
		}
		process("addAssignment");	// cmd
	}
	
	/**
	 * 과제 기본 정보 수정
	 */
	function editAssignment() {
		var f = document.assignmentForm;
		$(':input:hidden[name=attachFileSns]').val(atchFiles.getFileSnAll());
		$('#uploadify').attr("disabled", true); //-- 파일 객체 disable
		var asmtSubmit = f["asmtLimitCnt"].value;
		var svcType = "${assignmentVO.asmtSvcCd}"
		if(isEmpty(f["asmtTitle"].value)) {
			alert("<spring:message code="lecture.message.assignment.alert.input.name"/>");
			f["asmtTitle"].focus();
			return;
		}
		if(svcType != "CODE"){
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
		}
		process("editAssignment");	// cmd
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
			alert("<spring:message code="lecture.message.assignment.alert.input.startmin"/>" );
			f["asmtStartMin"].focus();
			return false;
		}
		else if(asmtEndHour==""){
			alert("<spring:message code="lecture.message.assignment.alert.input.endhour"/>" );
			f["asmtEndHour"].focus();
			return false;
		}
		else if(asmtEndMin==""){
			alert("<spring:message code="lecture.message.assignment.alert.input.endmin"/>" );
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

	function goList() {
		document.location.href = cUrl("/lec/assignment/tchAssignmentMain")+"?crsCreCd=${assignmentVO.crsCreCd}";
	}

	function changeAsmtType() {
		var asmtType = $("#asmtTypeCd").val();
		if(asmtType == "OFF") {
			$("#asmtSvcCd").attr("disabled",true);
			$("#asmtSelectTypeCd").attr("disabled",true);
			$("#asmtLimitCnt").attr("disabled",true);
			$("#sendCritPrgrRatio").attr("disabled",true);
			$("#regYnArea").show();
		} else {
			$("#asmtSvcCd").attr("disabled",false);
			$("#asmtSelectTypeCd").attr("disabled",false);
			$("#asmtLimitCnt").attr("disabled",false);
			$("#sendCritPrgrRatio").attr("disabled",false);
			$("#regYnArea").hide();
			$("#regYn").val("N");
		}
	}
	
	function changeAsmtSvcType() {
		var svcType = $("#asmtSvcCd").val();
		if(svcType == "CODE") {
			$("#asmtSelectTypeCd").attr("disabled",true);
			$("#asmtLimitCnt").attr("disabled",true);
			$("#sendCritPrgrRatio").attr("disabled",true);
			$("#asmtStartDttm").attr("disabled",true);
			$("#asmtStartHour").attr("disabled",true);
			$("#asmtStartMin").attr("disabled",true);
			$("#asmtEndDttm").attr("disabled",true);
			$("#asmtEndHour").attr("disabled",true);
			$("#asmtEndMin").attr("disabled",true);
			$("#extSendDttm").attr("disabled",true);
			$("#extSendHour").attr("disabled",true);
			$("#extSendMin").attr("disabled",true);
		} else {
			$("#asmtSelectTypeCd").attr("disabled",false);
			$("#asmtLimitCnt").attr("disabled",false);
			$("#sendCritPrgrRatio").attr("disabled",false);
			$("#asmtStartDttm").attr("disabled",false);
			$("#asmtStartHour").attr("disabled",false);
			$("#asmtStartMin").attr("disabled",false);
			$("#asmtEndDttm").attr("disabled",false);
			$("#asmtEndHour").attr("disabled",false);
			$("#asmtEndMin").attr("disabled",false);
			$("#extSendDttm").attr("disabled",false);
			$("#extSendHour").attr("disabled",false);
			$("#extSendMin").attr("disabled",false);
		}
	}

	function autoSet(){
		var f = document.assignmentForm;
		$('#extSendDttm').val($('#asmtEndDttm').val());
		f["extSendHour"].value = f["asmtEndHour"].value;
		f["extSendMin"].value = f["asmtEndMin"].value;
	}
	
	function formatDate(date) {
		var dt = date.replaceAll('.','-');
		return dt;
	}
	
	/**
	 * 과제 기본 정보 삭제
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
</script>
