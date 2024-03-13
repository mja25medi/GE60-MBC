<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="gubun" value="${gubun}"/>
<c:set var="examVO" value="${vo}"/>


	<form id="examForm" name="examForm" onsubmit="return false">
	<input type="hidden" name="crsCreCd" value="${vo.crsCreCd }" />
	<input type="hidden" name="examSn" value="${vo.examSn }" />
	<%-- <input type="hidden" name="semiExamYn" id="semiExamYn" value="${vo.semiExamYn }" /> --%>
	<input type="hidden" name="regYn" id="regYn" value="${vo.regYn }"/>
	<input type="hidden" name="examViewTypeCd" value="L" />
	<table summary="<spring:message code="lecture.title.exam.manage"/>" class="table table-bordered wordbreak">
		<colgroup>
			<col style="width:20%"/>
			<col style="width:auto;"/>
			<col style="width:20%"/>
			<col style="width:30%"/>
		</colgroup>
		<tr>
			<th scope="row"><spring:message code="lecture.title.exam.examtype"/></th>
			<td id="examTypeCdSel">
				<c:if test="${gubun eq 'A'}">
				<select name="examTypeCd" id="examTypeCd" class="form-control input-sm" onchange="changeExamType()">
					<c:forEach var="item" items="${examTypeCdList}" varStatus="status">
					<option value="${item.codeCd}" <c:if test="${vo.examTypeCd eq item.codeCd}">checked</c:if> >${item.codeNm}</option>
					</c:forEach>				
				</select>
				</c:if>
				<c:if test="${gubun eq 'E'}">
				<input type="hidden" name="examTypeCd" id="examTypeCd" value="${vo.examTypeCd }"/>
				<meditag:codename code="${examVO.examTypeCd}" category="EXAM_TYPE_CD"/>
				</c:if>
			</td>
			<th scope="row" class="online_exam">
				<label for="examStareTypeCd" ><spring:message code="lecture.title.exam.ansrtype"/></label>
			</th>
			<td class="online_exam">
				<c:if test="${gubun eq 'A'}">
				<select name="examStareTypeCd" id="examStareTypeCd" class="form-control input-sm" onchange="changeExamStareType()">
					<c:forEach var="item" items="${examStareTypeCdList}" varStatus="status">
					<option value="${item.codeCd}" <c:if test="${vo.examStareTypeCd eq item.codeCd}">checked</c:if> >${item.codeNm}</option>
					</c:forEach>				
				</select>
				</c:if>
				<c:if test="${gubun eq 'E'}">
					<input type="hidden" name="examStareTypeCd" id="examStareTypeCd" value="${vo.examStareTypeCd }"/>
					<c:if test="${examVO.examTypeCd eq 'ON' }">
						<meditag:codename code="${examVO.examStareTypeCd}" category="EXAM_STARE_TYPE_CD"/>
					</c:if>
				</c:if>
			</td>
		</tr>
		<tr class="online_exam" > 
			<th scope="row">평가구분</th>
			<td class="text-center" colspan="3">
				<label style="font-weight:normal; float: left;">
					<input type="radio" name="semiExamYn" id="semiExamYn_N" value="N" onclick="examChk()" <c:if test="${examVO.semiExamYn eq 'N'}">checked</c:if>/>일반시험
				</label>
				<label style="font-weight:normal;margin-left:10px; float: left;">
					<input type="radio" name="semiExamYn" id="semiExamYn_Y" value="Y" onclick="examChk()" <c:if test="${examVO.semiExamYn eq 'Y'}">checked</c:if>/>진행단계평가
				</label>
			</td>
		</tr>
		<tr class="online_exam">
				<th scope="row">평가용도</th>
				<td colspan="3">
					<table>
					<colgroup>
						<col style="width:100%"/>
					</colgroup>
					<tr class="examOptDiv">
						<td>
						<label style="font-weight:normal; float: left;">
							<input type="radio" name="examUseCd" id="examUseCd_A" value="CRECRS" onclick="examChk()" <c:if test="${empty examVO.examUseCd   or examVO.examUseCd eq 'CRECRS'}">checked</c:if>/>회차용
						</label>
						<label style="font-weight:normal;margin-left:10px; float: left;">
							<input type="radio" name="examUseCd" id="examUseCd_B" value="UNIT" onclick="examChk()" <c:if test="${examVO.examUseCd eq 'UNIT'}">checked</c:if>/>과목차시용
						</label>
						</td>
					</tr>

					<tr>
						<td>
							<div class="stareCritPrgrDiv" style="float:left;line-height:30px;display: none;">
								<span style="float:left;line-height:30px;"> <spring:message code="lecture.title.exam.answer.ratio"/> : </span>	
								<input type="text" style="width:50px;float:left;text-align:right;" dispName="강의 수" maxlength="3" isNull="N" lenCheck="3" name="stareCritPrgrRatio" id="stareCritPrgrRatio" value="${vo.stareCritPrgrRatio }" onfocus="this.select()" class="inputSpecial inputNumber examTypeControl examStareTypeControl form-control input-sm" onkeyup="isChkMaxNumber(this,100)"/>
								<span style="float:left;line-height:30px;"> % <spring:message code="common.title.over"/></span>
							</div>
							<div class="sbjDiv" style="float:left;line-height:30px;display: none;">
							<span style="float:left;line-height:30px;"> 강의 : </span>	
							<select name="sbjCd" id="sbjCd" class="form-control input-sm" style="float:left; width: auto;" onChange="listCnts()">
								<c:forEach var="item" items="${onlineSubjectList}" varStatus="status">
									<option value="${item.sbjCd }" <c:if test="${item.sbjCd eq examVO.sbjCd}">selected</c:if>>${item.sbjNm}</option>
								</c:forEach>				
							</select>
							</div>
							<div class="unitDiv" style="float:left;line-height:30px;display: none;">
								<span style="float:left;line-height:30px;margin-left:10px;"> 차시 : </span>
								<select name="unitCd" id="unitCd" class="form-control input-sm" style="float:left; width: auto;"></select>
							</div>
							<div class="lecCntDiv" style="float:left;line-height:30px;display: none;">
								<input type="text" style="width:50px;float:left;text-align:center;" dispName="<spring:message code="lecture.title.exam.answer.ratio"/>" maxlength="3" isNull="N" lenCheck="3" name="stareLecCount" id="stareLecCount" value="${vo.stareLecCount }" onfocus="this.select()" class="inputSpecial inputNumber examTypeControl examStareTypeControl form-control input-sm" onkeyup="isChkMaxNumber(this,100)"/>
								<span style="float:left;line-height:30px;"> 강 수강 후</span>
							</div>
						</td>
					</tr>	
					
					</table>					

				
			
	
				</td>
		</tr>		
		<tr>
			<th scope="row"><spring:message code="lecture.title.exam.name"/></th>
			<td colspan="3">
				<input type="text" dispName="<spring:message code="lecture.title.exam.name"/>" maxlength="100" isNull="N" lenCheck="100" name="examTitle" value="${vo.examTitle }" onfocus="this.select()" class="form-control input-sm"/>
			</td>
		</tr>
		<tr class="regular">
			<th scope="row"><spring:message code="lecture.title.exam.duration"/></th>
			<td colspan="3">
				<div class="input-group" style="float:left;width:128px;">
					<input type="text" dispName="<spring:message code="lecture.title.exam.start.date"/>" maxlength="50" isNull="N" lenCheck="50" name="examStartDttm" value="${vo.examStartDttm }" id="examStartDttm" class="inputDate examStareTypeControl form-control input-sm"/>
					<span class="input-group-addon btn-sm" onclick="_clickCalendar('examStartDttm')" style="cursor:pointer">
						<i class="fa fa-calendar"></i>
					</span>
				</div>
				<input type="text" style="width:40px;float:left;text-align:right;" dispName="<spring:message code="lecture.title.exam.start.hour"/>" maxlength="2" isNull="N" lenCheck="2" name="examStartHour" value="${vo.examStartHour }" id="examStartHour" class="examStareTypeControl form-control input-sm" onkeyup="isChkHours(this)" />
				<span style="float:left;line-height:30px;"><spring:message code="common.title.hour"/></span>
				<input type="text" style="width:40px;float:left;text-align:right;" dispName="<spring:message code="lecture.title.exam.start.min"/>" maxlength="2" isNull="N" lenCheck="2" name="examStartMin" value="${vo.examStartMin }" id="examStartMin" class="examStareTypeControl form-control input-sm" onkeyup="isChkMinute(this)" />
				<span style="float:left;line-height:30px;"><spring:message code="common.title.min"/></span>

				<div class="input-group text-center" style="float:left;line-height:30px;padding-left:10px; padding-right:10px;"> ~ </div>

				<div class="input-group" style="float:left;width:128px;">
					<input type="text" dispName="<spring:message code="lecture.title.exam.end.date"/>" maxlength="50" isNull="N" lenCheck="50" name="examEndDttm" id="examEndDttm" value="${vo.examEndDttm }" class="inputDate examStareTypeControl form-control input-sm"/>
					<span class="input-group-addon btn-sm" onclick="_clickCalendar('examEndDttm')" style="cursor:pointer">
						<i class="fa fa-calendar"></i>
					</span>
				</div>
				<input type="text" style="width:40px;float:left;text-align:right;" dispName="<spring:message code="lecture.title.exam.end.hour"/>" maxlength="2" isNull="N" lenCheck="2" name="examEndHour" id="examEndHour" value="${vo.examEndHour }" class="examStareTypeControl form-control input-sm" onkeyup="isChkHours(this)" />
				<span style="float:left;line-height:30px;"><spring:message code="common.title.hour"/></span>
				<input type="text" style="width:40px;float:left;text-align:right;" dispName="<spring:message code="lecture.title.exam.end.min"/>" maxlength="2" isNull="N" lenCheck="2" name="examEndMin" id="examEndMin" value="${vo.examEndMin }" class="examStareTypeControl form-control input-sm" onkeyup="isChkMinute(this)" />
				<span style="float:left;line-height:30px;"><spring:message code="common.title.min"/></span>
				<meditag:datepicker name1="examStartDttm" name2="examEndDttm"/>
			</td>
		</tr>
		<tr class="regular">
			<th scope="row"><spring:message code="lecture.title.exam.result.date"/></th>
			<td colspan="3">
				<div class="input-group" style="float:left;width:128px;">
					<input type="text" dispName="<spring:message code="lecture.title.exam.result.date"/>" maxlength="50" isNull="N" lenCheck="50" name="rsltCfrmDttm" id="rsltCfrmDttm" value="${vo.rsltCfrmDttm }" class="inputDate examStareTypeControl form-control input-sm"/>
					<span class="input-group-addon btn-sm" onclick="_clickCalendar('rsltCfrmDttm')" style="cursor:pointer">
						<i class="fa fa-calendar"></i>
					</span>
				</div>
				<input type="text" style="width:40px;float:left;text-align:right;" dispName="<spring:message code="lecture.title.exam.result.hour"/>" maxlength="2" isNull="N" lenCheck="2" name="rsltCfrmHour" id="rsltCfrmHour" value="${vo.rsltCfrmHour }" class="examStareTypeControl form-control input-sm" onkeyup="isChkHours(this)" />
				<span style="float:left;line-height:30px;"><spring:message code="common.title.hour"/></span>
				<input type="text" style="width:40px;float:left;text-align:right;" dispName="<spring:message code="lecture.title.exam.result.min"/>" maxlength="2" isNull="N" lenCheck="2" name="rsltCfrmMin" id="rsltCfrmMin" value="${vo.rsltCfrmMin }" class="examStareTypeControl form-control input-sm" onkeyup="isChkMinute(this)" />
				<span style="float:left;line-height:30px;"><spring:message code="common.title.min"/></span>
				<meditag:datepicker name1="rsltCfrmDttm"/>
			</td>
		</tr>
		<tr class="online_exam">
			<th scope="row"><spring:message code="lecture.title.exam.timelimit"/></th>
			<td>
				<select name="stareTmUseYn" id="stareTmUseYn" class="form-control input-sm">
					<c:forEach var="item" items="${stareTmUseYnList}" varStatus="status">
					<option value="${item.codeCd}" <c:if test="${vo.stareTmUseYn eq item.codeCd}">checked</c:if> >${item.codeNm}</option>
					</c:forEach>				
				</select>
			</td>
			<th scope="row"><spring:message code="common.title.time"/></th>
			<td>
				<input type="text" style="width:60px;float:left;text-align:right;" dispName="<spring:message code="common.title.time"/>" maxlength="4" isNull="N" lenCheck="4" name="examStareTm" id="examStareTm" value="${vo.examStareTm }" onfocus="this.select()" class="inputSpecial inputNumber examTypeControl form-control input-sm" onkeyup="isChkInteger(this)"/>
				<span style="float:left;line-height:30px;"><spring:message code="common.title.min"/></span>
			</td>
		</tr>

		<tr class="online_exam">
			<th scope="row"><spring:message code="lecture.title.exam.limitcnt"/></th>
			<td>
				<input type="text" style="width:50px;float:left;text-align:right;" dispName="<spring:message code="lecture.title.exam.limitcnt"/>" maxlength="3" isNull="N" lenCheck="3" name="stareLimitCnt" id="stareLimitCnt" value="${vo.stareLimitCnt }" onfocus="this.select()" class="inputSpecial inputNumber examTypeControl form-control input-sm" onkeyup="isChkInteger(this)"/>
				<span style="float:left;line-height:30px;"><spring:message code="common.title.times.postfix"/></span>
			</td>
		</tr>
		<tr id="input_regYn" style="display: none;">
			<th scope="row" style="width:20%"><spring:message code="lecture.title.exam.regyn"/></th>
			<td colspan="3">
				<select name="regYnSel" id="regYnSel" class="form-control input-sm">
					<c:forEach items="${regYnList}" var="item">
						<c:set var="codeName" value="${item.codeNm}"/>
						<c:forEach var="lang" items="${item.codeLangList}">
							<c:if test="${lang.langCd eq LOCALEKEY }"><c:set var="codeName" value="${lang.codeNm}"/></c:if>
						</c:forEach>
					<option value="${item.codeCd}" <c:if test="${examVO.regYn eq item.codeCd }">selected </c:if> >${codeName}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<%-- <tr>
			<th scope="row">시험 문제 갯수</th>
			<td>
				<input type="text" style="width:50px;float:left;text-align:right;" maxlength="3" isNull="N" lenCheck="3" name="examSetCnt" id="examSetCnt" value="${vo.examSetCnt }" onfocus="this.select()" class="inputSpecial inputNumber examTypeControl form-control input-sm" onkeyup="isChkInteger(this)"/>
				<span style="float:left;line-height:30px;">문제</span>
			</td>
		</tr> --%>
		<tr class="online_exam">
			<th scope="row">시험 배점<c:if test="${gubun eq 'E'}"><br>*비공개 상태에서만 수정가능</c:if></th>
			<td colspan="3">
				<table class="table table-bordered wordbreak">
					<colgroup>
						<col style="width:30%"/>
						<col style="width:20%"/>
						<col style="width:20%"/>
						<col style="width:30%"/>
					</colgroup>
					<tr>
						<th scope="row">종류</th>
						<th scope="row">문항 수</th>
						<th scope="row">배점</th>
						<th scope="row">합(문항수x배점)</th>
					</tr>
					<tr>
						<td>선택형</td>
						<td><input type="text" class="form-control form-mediumItem inputNumber" maxlength="2" id="selCnt" name="selCnt" value="${vo.selCnt }" onfocus="this.select()"  onkeyup="isChkInteger(this)" <c:if test="${vo.regYn eq 'Y'}">readonly="readonly"</c:if> /></td>
						<td><input type="text" class="form-control form-mediumItem inputNumber" maxlength="3" id="selPnt" name="selPnt" value="${vo.selPnt }" onfocus="this.select()"  onkeyup="isChkInteger(this)" <c:if test="${vo.regYn eq 'Y'}">readonly="readonly"</c:if>/></td>
						<td><input type="text" class="form-control form-mediumItem inputNumber" maxlength="3" id="selSum" name="selSum" value="${vo.selCnt * vo.selPnt }" readonly="readonly"/></td>
					</tr>
					<tr>
						<td>단답형</td>
						<td><input type="text" class="form-control form-mediumItem inputNumber"  maxlength="2" id="shortCnt" name="shortCnt" value="${vo.shortCnt }" onfocus="this.select()"  onkeyup="isChkInteger(this)" <c:if test="${vo.regYn eq 'Y'}">readonly="readonly"</c:if>/></td>
						<td><input type="text" class="form-control form-mediumItem inputNumber"  maxlength="3" id="shortPnt" name="shortPnt" value="${vo.shortPnt }" onfocus="this.select()"  onkeyup="isChkInteger(this)" <c:if test="${vo.regYn eq 'Y'}">readonly="readonly"</c:if>/></td>
						<td><input type="text" class="form-control form-mediumItem inputNumber"  maxlength="2" id="shortSum" name="shortSum" value="${vo.shortCnt * vo.shortPnt }" readonly="readonly"/></td>
					</tr>
					<tr>
						<td>서술형</td>
						<td><input type="text" class="form-control form-mediumItem inputNumber"  maxlength="2" id="desCnt" name="desCnt" value="${vo.desCnt }" onfocus="this.select()"  onkeyup="isChkInteger(this)" <c:if test="${vo.regYn eq 'Y'}">readonly="readonly"</c:if>/></td>
						<td><input type="text" class="form-control form-mediumItem inputNumber"  maxlength="3" id="desPnt" name="desPnt" value="${vo.desPnt }" onfocus="this.select()"  onkeyup="isChkInteger(this)" <c:if test="${vo.regYn eq 'Y'}">readonly="readonly"</c:if>/></td>
						<td><input type="text" class="form-control form-mediumItem inputNumber"  maxlength="2" id="desSum" name="desSum" value="${vo.desCnt * vo.desPnt }" readonly="readonly"/></td>
					</tr>
					<tr>
						<td>나머지 배점 <input type="checkbox" name="divYn" id="divYn" value="${vo.divYn}" <c:if test="${vo.divYn eq 'Y'}">checked</c:if> onclick="changeDivPnt()"/></td>
						<td></td>
						<td></td>
						<td><input type="text" class="form-control form-mediumItem inputNumber"  maxlength="2" id="divPnt" name="divPnt" value="${vo.divPnt}" readonly="readonly"/></td>
					</tr>
					<tr>
						<td colspan="3">총합</td>
						<td><input type="text" class="form-control form-mediumItem" id="examRateTotScore" value="${(vo.selCnt * vo.selPnt) + (vo.shortCnt * vo.shortPnt) + (vo.desCnt * vo.desPnt) + vo.divPnt }" readonly="readonly"/></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<th scope="row"><spring:message code="lecture.title.exam.desc"/></th>
			<td colspan="3">
				<textarea style="width:100%;height:160px;" dispName="<spring:message code="lecture.title.exam.desc"/>" name="examCts" class="form-control input-sm">${vo.examCts}</textarea>
			</td>
		</tr>
	</table>
	
	<div class="text-right" style="margin-top:10px;">
		<c:if test="${gubun eq 'A'}">
		<button class="btn btn-primary btn-sm" onclick="addExam()"><spring:message code="button.add"/></button>
		</c:if>
		<c:if test="${gubun eq 'E'}">
		<button class="btn btn-primary btn-sm" onclick="editExam()"><spring:message code="button.add"/></button>
		<button class="btn btn-warning btn-sm" onclick="delExam()"><spring:message code="button.delete"/></button>
		</c:if>
		<button class="btn btn-default btn-sm" onclick="parent.modalBoxClose();"><spring:message code="button.close"/></button>
	</div>
	</form>
<script type="text/javascript">

	$(document).ready(function() {
		$('.inputNumber').inputNumber();	// 숫자만 입력되도록 설정.
		$('.inputDate').inputDate();	// 숫자만 입력되도록 설정.
		$('.inputSpecial').inputSpecial();  // 특수키가 입력이 들어오묜 false return;

		changeExamType();
		changeExamStareType();
		listCnts();
		<c:if test="${gubun eq 'E'}">
			var semiExamYn = $('input[name="semiExamYn"]:checked').val();
			if(semiExamYn == 'Y'){
				$('#semiExamYn_N').attr("disabled",true);
			} else{ 
				$('#semiExamYn_Y').attr("disabled",true);
			}
		</c:if>
		//문항 수, 배점 변화에 따른 총점 확인
		$('#selCnt, #selPnt, #shortCnt, #shortPnt, #desCnt, #desPnt').on('change keyup' ,function() {
			getExamQstnTotScore(this);
		});
		
	});

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		$('#examForm').attr("action","/mng/lecture/exam/" + cmd);
		$('#examForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		alert(resultDTO.message);

		if(resultDTO.result >= 0) {
			// 정상 처리
			parent.subWorkFrame.listExam();
			parent.modalBoxClose();
		} else {
			// 비정상 처리
		}
	}

	<c:if test="${gubun eq 'A'}">
	/**
	 * 시험 등록.
	 */
	function addExam() {
		var f = document.examForm;
		
		$("#examTypeCd").attr("disabled", false);
		$("#examStareTypeCd").attr("disabled", false);
		
		var examStareType = f["examStareTypeCd"].value;
		var examType = f["examTypeCd"].value;

		if(isEmpty(f["examTitle"].value)) {
			alert("<spring:message code="lecture.message.exam.alert.input.name"/>");
			f["examTitle"].focus();
			return;
		}
			var startDttm = $("#examStartDttm").val();
			var endDttm = $("#examEndDttm").val();
			var resultDttm = $("#rsltCfrmDttm").val();

			var startDttmArray = startDttm.split(".");
			var endDttmArray = endDttm.split(".");
			var resultDttmArray = resultDttm.split(".");

			var startHour = $("#examStartHour").val();
			var startMin = $("#examStartMin").val();
			var endHour = $("#examEndHour").val();
			var endMin = $("#examEndMin").val();
			var resultHour = $("#rsltCfrmHour").val();
			var resultMin = $("#rsltCfrmMin").val();

			var startDttmObj = new Date(startDttmArray[0], Number(startDttmArray[1])-1, startDttmArray[2], startHour, startMin, 01);
			var endDttmObj = new Date(endDttmArray[0], Number(endDttmArray[1])-1, endDttmArray[2], endHour, endMin, 01);
			var resultDttmObj = new Date(resultDttmArray[0], Number(resultDttmArray[1])-1, resultDttmArray[2], resultHour, resultMin, 01);

			if(startDttmObj >= endDttmObj){
				alert("<spring:message code="lecture.message.exam.alert.compare.date"/>");
				return;
			}

			if(endDttmObj >= resultDttmObj) {
				alert("<spring:message code="lecture.message.exam.alert.result.date"/>");
				return;
			}

			if(examType == "ON") {
				var examStareTm = parseInt(f["examStareTm"].value,10);
				var stareTmUseYn = $("#stareTmUseYn option:selected").val();
				if(stareTmUseYn == "Y") {
					if(examStareTm <= 0) {
						alert("<spring:message code="lecture.message.exam.alert.answer.time"/>");
						$("#examStareTm").focus();
						return;
					}
				}
				
				//시험 배점 입력 확인
				if(!getExamQstnTotScore()) return;
				
				if(parseInt($("#examRateTotScore").val(),10) != 100) {
					alert("시험 배점 총합이 100점 이어야 합니다.");
					return;
				}
			}

		var semiExamYn = $('input[name="semiExamYn"]:checked').val();
		if(semiExamYn == 'Y'){	//진행단계평가일 경우
			var examStareTm = parseInt(f["stareLecCount"].value,10);
			if(examType == 'ON') {
				if(examStareTm <=0 ) {
					alert("<spring:message code="lecture.message.exam.alert.answer.leccount"/>");
					$("#stareLecCount").focus();
					return;
				}
				
				/* if(!(parseInt($("#examSetCnt").val()) > 0)){
					alert('시험문제갯수는 0보다 커야합니다.');
					$("#examSetCnt").focus();
					return;
				} */
				
			}
		}else{		//시험일 경우
			var examUseCd = $('input[name="examUseCd"]:checked').val();	
			if(examUseCd == 'UNIT'){	//일반시험 & 과목 차시 시험일 경우
				if(f["unitCd"].value == '') {
					alert("차시를 선택해 주세요.");
					$("#unitCd").focus();
					return;
				}
			} else { // 일반시험 & 차시 용 시험일 경우
				var examStareTm = parseInt(f["stareCritPrgrRatio"].value,10);
				if(examType == 'ON') {
					if(examStareTm <=0 ) {
						alert("<spring:message code="lecture.message.exam.alert.answer.ratio"/>");
						$("#stareCritPrgrRatio").focus();
						return;
					}
					
					/* if(!(parseInt($("#examSetCnt").val()) > 0)){
						alert('시험문제갯수는 0보다 커야합니다.');
						$("#examSetCnt").focus();
						return;
					} */
				}
			}

		}
		if(examType == 'ON') {
			if(parseInt($("#stareLimitCnt").val(),10) <= 0) {
				alert("<spring:message code="lecture.message.exam.alert.input.answercnt"/>");
				$("#stareLimitCnt").focus();
				return;
			}
		}
		$("#regYn").val($("#regYnSel").val());

		if(!validate(document.getElementById("examForm")) ) return;
		
		process("addExam");	// cmd
	}
	</c:if>

	<c:if test="${gubun eq 'E'}">
	/**
	* 시험 정보 삭제
	*/
	function delExam() {
		var f = document.examForm;
		var stareCnt = ${examVO.stareCnt};

		if(stareCnt > 0){
			if(confirm(stareCnt+"<spring:message code="lecture.message.exam.confirm.delete1"/>")){
				process("deleteExam");
			}
		}else{
			if(confirm("<spring:message code="lecture.message.exam.confirm.delete2"/>")){
				process("deleteExam");
			}
		}
	}


	/**
	 * 시험 정보 수정
	 */
	 function editExam() {
		var f = document.examForm;
		
		$("#examTypeCd").attr("disabled", false);
		$("#examStareTypeCd").attr("disabled", false);
		
		var examStareType = f["examStareTypeCd"].value;
		var examType = f["examTypeCd"].value;

		if(isEmpty(f["examTitle"].value)) {
			alert("<spring:message code="lecture.message.exam.alert.input.name"/>");
			f["examTitle"].focus();
			return;
		}
			//-- 정규 시험일 경우
			var startDttm = $("#examStartDttm").val();
			var endDttm = $("#examEndDttm").val();
			var resultDttm = $("#rsltCfrmDttm").val();

			var startDttmArray = startDttm.split(".");
			var endDttmArray = endDttm.split(".");
			var resultDttmArray = resultDttm.split(".");

			var startHour = $("#examStartHour").val();
			var startMin = $("#examStartMin").val();
			var endHour = $("#examEndHour").val();
			var endMin = $("#examEndMin").val();
			var resultHour = $("#rsltCfrmHour").val();
			var resultMin = $("#rsltCfrmMin").val();

			var startDttmObj = new Date(startDttmArray[0], Number(startDttmArray[1])-1, startDttmArray[2], startHour, startMin, 01);
			var endDttmObj = new Date(endDttmArray[0], Number(endDttmArray[1])-1, endDttmArray[2], endHour, endMin, 01);
			var resultDttmObj = new Date(resultDttmArray[0], Number(resultDttmArray[1])-1, resultDttmArray[2], resultHour, resultMin, 01);

			if(startDttmObj >= endDttmObj){
				alert("<spring:message code="lecture.message.exam.alert.compare.date"/>");
				return;
			}

			if(endDttmObj >= resultDttmObj) {
				alert("<spring:message code="lecture.message.exam.alert.result.date"/>");
				return;
			}
			
			var semiExamYn = $('input[name="semiExamYn"]:checked').val();
			if(semiExamYn == 'Y'){	//진행단계평가일 경우
				var examStareTm = parseInt(f["stareLecCount"].value,10);
				if(examType == 'ON') {
					if(examStareTm <=0 ) {
						alert("<spring:message code="lecture.message.exam.alert.answer.leccount"/>");
						$("#stareLecCount").focus();
						return;
					}
					
					/* if(!(parseInt($("#examSetCnt").val()) > 0)){
						alert('시험문제갯수는 0보다 커야합니다.');
						$("#examSetCnt").focus();
						return;
					} */
				}
			}else{		//일반시험일 경우
				var examUseCd = $('input[name="examUseCd"]:checked').val();	
				if(examUseCd == 'UNIT'){	//일반시험 & 과목 차시 시험일 경우
					if(f["unitCd"].value == '') {
						alert("차시를 선택해 주세요.");
						$("#unitCd").focus();
						return;
					}
				} else { // 일반시험 & 차시 용 시험일 경우
					var examStareTm = parseInt(f["stareCritPrgrRatio"].value,10);
					if(examType == 'ON') {
						if(examStareTm <=0 ) {
							alert("<spring:message code="lecture.message.exam.alert.answer.ratio"/>");
							$("#stareCritPrgrRatio").focus();
							return;
						}
						
						/* if(!(parseInt($("#examSetCnt").val()) > 0)){
							alert('시험문제갯수는 0보다 커야합니다.');
							$("#examSetCnt").focus();
							return;
						} */
					}
				}				
				
				

			}
		if(examType == 'ON') {
			var examStareTm = parseInt(f["examStareTm"].value,10);
			var stareTmUseYn = $("#stareTmUseYn option:selected").val();

			if(stareTmUseYn == "Y") {
				if(examStareTm <= 0) {
					alert("<spring:message code="lecture.message.exam.alert.answer.time"/>");
					f["examStareTm"].focus();
					return;
				}
			}

			if(parseInt($("#stareLimitCnt").val(),10) <= 0) {
				alert("<spring:message code="lecture.message.exam.alert.input.answercnt"/>");
				return;
			}
			
			//시험 배점 입력 확인
			if(!getExamQstnTotScore()) return;
			
			if(parseInt($("#examRateTotScore").val(),10) != 100) {
				alert("시험 배점 총합이 100점 이어야 합니다.");
				return;
			}
		}
		if(!validate(document.getElementById("examForm"))) return;
		
		$("#regYn").val($("#regYnSel").val());
		process("editExam");	// cmd
	}
	</c:if>

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

	function changeExamType() {
		var examType = $("#examTypeCd").val();
		var creOperTypeCd = "${onlineSubjectList[0].creOperTypeCd}"
		if (creOperTypeCd == 'S') {
			$(".online_exam").show();
			$("#regYn").val("${examVO.regYn}");
			$("#regYnSel").val("${examVO.regYn}");
			$('#input_regYn').hide();
			$("#examStareTypeCd option:eq(1)").prop("selected", "selected");
			$("#examTypeCd").attr("disabled", true);
			$("#examStareTypeCd").attr("disabled", true);
		}else{
			if(examType == "ON") {
				$(".online_exam").show();
				$("#regYn").val("${examVO.regYn}");
				$("#regYnSel").val("${examVO.regYn}");
				$('#input_regYn').hide();
			} else {
				//-- 정규 시험으로 선택
				$("#examStareTypeCd option:eq(0)").prop("selected", "selected");
				$(".online_exam").hide();
				$('#input_regYn').show();
			}
			changeExamStareType();
		}
	}

	function changeExamStareType() {
		var examStareType = $("#examStareTypeCd").val();
		var f = document.examForm;
		var stareTmUseYn = f["stareTmUseYn"].value;
		var semiExamYn = $('input[name="semiExamYn"]:checked').val();

		if(examStareType == "R") {
			$(".regular").show();
			document.getElementById("examStartDttm").setAttribute("isNull" ,"N");
			document.getElementById("examStartHour").setAttribute("isNull" ,"N");
			document.getElementById("examStartMin").setAttribute("isNull" ,"N");
			document.getElementById("examEndDttm").setAttribute("isNull" ,"N");
			document.getElementById("examEndHour").setAttribute("isNull" ,"N");
			document.getElementById("examEndMin").setAttribute("isNull" ,"N");
			document.getElementById("rsltCfrmDttm").setAttribute("isNull" ,"N");
			document.getElementById("rsltCfrmHour").setAttribute("isNull" ,"N");
			document.getElementById("rsltCfrmMin").setAttribute("isNull" ,"N");
			if(semiExamYn == "N"){
				document.getElementById("stareCritPrgrRatio").setAttribute("isNull" ,"N");
			}else{
				document.getElementById("stareLecCount").setAttribute("isNull" ,"N");
			}
		} else {
			$(".regular").hide();
			document.getElementById("examStartDttm").setAttribute("isNull" ,"Y");
			document.getElementById("examStartHour").setAttribute("isNull" ,"Y");
			document.getElementById("examStartMin").setAttribute("isNull" ,"Y");
			document.getElementById("examEndDttm").setAttribute("isNull" ,"Y");
			document.getElementById("examEndHour").setAttribute("isNull" ,"Y");
			document.getElementById("examEndMin").setAttribute("isNull" ,"Y");
			document.getElementById("rsltCfrmDttm").setAttribute("isNull" ,"Y");
			document.getElementById("rsltCfrmHour").setAttribute("isNull" ,"Y");
			document.getElementById("rsltCfrmMin").setAttribute("isNull" ,"Y");
			if(semiExamYn == "N"){
				document.getElementById("stareCritPrgrRatio").setAttribute("isNull" ,"Y");
			}else{
				document.getElementById("stareLecCount").setAttribute("isNull" ,"Y");
			}
		}
		if(stareTmUseYn == "Y"){
			$("#examStareTm").removeAttr("disabled");
		}else{
			$("#examStareTm").attr("disabled",true);
		}
		examChk();
	}

	function changeExamStareTm(){
		var f = document.examForm;
		var stareTmUseYn = f["stareTmUseYn"].value;
		if(stareTmUseYn == "Y"){
			$("#examStareTm").val('${examVO.examStareTm}');
			$("#examStareTm").removeAttr("disabled");
		}else{
			$("#examStareTm").val('0');
			$("#examStareTm").attr("disabled",true);
		}
	}
	
	//문항수,배점 입력 시 
	function getExamQstnTotScore(obj, checkYn){//this, 재귀여부
		var selCnt 	 = parseInt(isNull($("#selCnt").val()) ? 0 : $("#selCnt").val());
		var selPnt 	 = parseInt(isNull($("#selPnt").val()) ? 0 : $("#selPnt").val());
		var shortCnt = parseInt(isNull($("#shortCnt").val()) ? 0 : $("#shortCnt").val());
		var shortPnt = parseInt(isNull($("#shortPnt").val()) ? 0 : $("#shortPnt").val());
		var desCnt   = parseInt(isNull($("#desCnt").val()) ? 0 : $("#desCnt").val()); 
		var desPnt   = parseInt(isNull($("#desPnt").val()) ? 0 : $("#desPnt").val());
		var divPnt   = parseInt(isNull($("#divPnt").val()) ? 0 : $("#divPnt").val());
		
		
		var selSum = parseInt(isNull($("#selSum").val()) ? 0 : $("#selSum").val());
		var shortSum = parseInt(isNull($("#shortSum").val()) ? 0 : $("#shortSum").val());
		var desSum = parseInt(isNull($("#desSum").val()) ? 0 : $("#desSum").val());
		var examRateTotScore = parseInt(isNull($("#examRateTotScore").val()) ? 0 : $("#examRateTotScore").val());
		
		var returnFlag = true;
		
		//if((selCnt * selPnt) + (shortCnt * shortPnt) + (desCnt * desPnt) > 100 || (selSum + shortSum + desSum) > 100  || examRateTotScore > 100){
		if((selCnt * selPnt) + (shortCnt * shortPnt) + (desCnt * desPnt) + divPnt > 100){
			if(isNotNull(obj)){//jquery 이벤트 this 여부
				obj.value = 0;//초기화
			}else{//전송 전 체크
				$("#selCnt").val(0);
				$("#selPnt").val(0);
				$("#shortCnt").val(0);
				$("#shortPnt").val(0);
				$("#desCnt").val(0);
				$("#desPnt").val(0);
				$("#divPnt").val(0);
			}
			alert("총합이 100을 초과해서는 안됩니다.");
			returnFlag = false;
			/* if(isNull(checkYn)){//처음 호출 시에는 반복
				getExamQstnTotScore(obj, "Y");
			}else{//재귀 호출 시에는 모든 값 초기화
				$("#selCnt").val(0);
				$("#selPnt").val(0);
				$("#shortCnt").val(0);
				$("#shortPnt").val(0);
				$("#desCnt").val(0);
				$("#desPnt").val(0);
			} */
		}
		
		//초기화 후 다시 세팅
		selCnt 	 = parseInt(isNull($("#selCnt").val()) ? 0 : $("#selCnt").val());           
		selPnt 	 = parseInt(isNull($("#selPnt").val()) ? 0 : $("#selPnt").val());           
		shortCnt = parseInt(isNull($("#shortCnt").val()) ? 0 : $("#shortCnt").val());       
		shortPnt = parseInt(isNull($("#shortPnt").val()) ? 0 : $("#shortPnt").val());       
		desCnt   = parseInt(isNull($("#desCnt").val()) ? 0 : $("#desCnt").val());           
		desPnt   = parseInt(isNull($("#desPnt").val()) ? 0 : $("#desPnt").val()); 
		divPnt   = parseInt(isNull($("#divPnt").val()) ? 0 : $("#divPnt").val()); 
		
		$("#selSum").val(selCnt * selPnt);
		$("#shortSum").val(shortCnt * shortPnt);
		$("#desSum").val(desCnt * desPnt);
		
		$("#examRateTotScore").val( (selCnt * selPnt) + (shortCnt * shortPnt) + (desCnt * desPnt) + divPnt );
		return returnFlag;	
	}
		
	function examChk(){
		var examChkVal = $('input[name="semiExamYn"]:checked').val();
		var examUseCd = $('input[name="examUseCd"]:checked').val();

		examCtl(examChkVal,examUseCd);
	}
	
	// 평가 유형 설정
	function examCtl(examChkVal,examUseCd){
		// 강좌유형 상태값 
		switch(examChkVal){
    	case "Y" : 	
			$(".examOptDiv").css("display", "none");
			$(".sbjDiv").css("display", "");
	   		$(".lecCntDiv").css("display", "");
	   		$(".stareCritPrgrDiv").css("display", "none");
	   		$(".unitDiv").css("display", "none");
    		break;
	   	case "N" :
	   		$(".examOptDiv").css("display", "");
			switch(examUseCd){
	    	case "UNIT" : 	
		   		$(".stareCritPrgrDiv").css("display", "none");
				$(".sbjDiv").css("display", "");
		   		$(".unitDiv").css("display", "");
		   		$(".lecCntDiv").css("display", "none");
	    		break;
		   	case "CRECRS" :
		   		$(".stareCritPrgrDiv").css("display", "");
				$(".sbjDiv").css("display", "none");
		   		$(".unitDiv").css("display", "none");
		   		$(".lecCntDiv").css("display", "none");
	    		break;
			}					
   		break;
		}

	}	
	/**
	 * 컨텐츠 목록 조회
	 */
	function listCnts() {
		var f = document.examForm;
		var crsCreCd = '${vo.crsCreCd}';
		var sbjCd = f["sbjCd"].value;
		var selectBox = document.getElementById("unitCd");
		var len = selectBox.length;
		for(var i=len ; i >= 0; i--) {
			selectBox.options[0] = null;
		}
		new_option = new Option("차시를 선택하세요.", "");
		selectBox.options.add(new_option);
		$.getJSON( cUrl("/mng/course/createCourse/subject/listCreCnts"),		// url
				{ 
				  "sbjCd"	  : sbjCd,
				  "crsCreCd"  : crsCreCd
				},			// params
				listCntsCallback				// callback function
			);
	}
	/**
	 * 컨텐츠 목록 조회 Callback
	 */
	function listCntsCallback(ProcessResultListDTO) {
		var selectBox = document.getElementById("unitCd");
		var len = selectBox.length;
		var unitCd = '${vo.unitCd}';
		var contentsList = ProcessResultListDTO.returnList;
		for (var i=0; i<contentsList.length; i++) {
			var item = contentsList[i];
			new_option = new Option(item.unitNm, item.unitCd);
			selectBox.options.add(new_option);
			if(item.unitCd == unitCd) {
				selectBox.options[i+1].selected = "selected";
			}
		}
		
	}
	
	function changeDivPnt() {
	    var divYnCheckbox = document.getElementById('divYn');
	    var is_checked = divYnCheckbox.checked;
	    var selSum = parseFloat(document.getElementById("examRateTotScore").value);
	    var divYnValue = is_checked ? "Y" : "N";
	    var divPntValue = is_checked ? (100 - selSum) : 0;

	    if (is_checked) {
	        if (selSum === 0) {
	            alert("문항과 배점을 지정 후 선택가능합니다.");
	            divYnCheckbox.checked = false;
	            return;
	        } else if (selSum === 100) {
	            alert("총합이 100일 경우 선택 할 수 없습니다.");
	            divYnCheckbox.checked = false;
	            return;
	        }
	    }

	    $("#divYn").val(divYnValue);
	    $("#divPnt").val(divPntValue);
	    getExamQstnTotScore(); 
	}



</script>
