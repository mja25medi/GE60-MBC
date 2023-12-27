<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="gubun" value="${gubun}"/>
<c:set var="examVO" value="${vo}"/>

	<form id="examForm" name="examForm" onsubmit="return false">
	<input type="hidden" name="crsCreCd" value="${vo.crsCreCd }" />
	<input type="hidden" name="examSn" value="${vo.examSn }" />
	<%-- <input type="hidden" name="semiExamYn" id="semiExamYn" value="${vo.semiExamYn }" /> --%>
	<input type="hidden" name="regYn" id="regYn" value="${vo.regYn }"/>
	<input type="hidden" name="examViewTypeCd" value="L" />
	<%-- <input type="hidden" name="sbjCd" value="${vo.sbjCd }" /> --%>
	   <div class="learn_top">
                    <div class="left_title">
                        <c:if test="${gubun eq 'A'}">
                        <h3>시험등록</h3>
                        </c:if>
                        <c:if test="${gubun eq 'E'}">
                        <h3>시험수정</h3>
                        </c:if>
                    </div>
                </div>
			<div class="segment">
                    <div class="tstyle">
                        <ul class="dbody">
                            <li>
                                <div class="row">
                                    <label for="typeSelect" class="form-label col-sm-2"><spring:message code="lecture.title.exam.examtype"/></label>
                                    <div class="col-sm-4">
                                        <div class="form-row">
                                            <c:if test="${gubun eq 'A'}">
												<select name="examTypeCd" id="examTypeCd" class="form-select" onchange="changeExamType()">
													<c:forEach var="item" items="${examTypeCdList}" varStatus="status">
													<option value="${item.codeCd}" <c:if test="${vo.examTypeCd eq item.codeCd}">checked</c:if> >${item.codeNm}</option>
													</c:forEach>				
												</select>
												</c:if>
												<c:if test="${gubun eq 'E'}">
												<input type="hidden" name="examTypeCd" id="examTypeCd" value="${vo.examTypeCd }"/>
												<meditag:codename code="${examVO.examTypeCd}" category="EXAM_TYPE_CD"/>
												</c:if>
                                        </div>             
                                    </div>
                                    <label for="typeSelect2" class="form-label col-sm-2 online_exam"><spring:message code="lecture.title.exam.ansrtype"/></label>
                                    <div class="col-sm-4 online_exam">
                                        <div class="form-row">
                                            <c:if test="${gubun eq 'A'}">
											<select name="examStareTypeCd" id="examStareTypeCd" class="form-select" onchange="changeExamStareType()">
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
                                        </div>             
                                    </div>
                                </div>
                            </li>
                           <li class="cntsTypeSel">
                                <div class="row" >
                                    <label for="conType" class="form-label col-sm-2">평가구분</label>
                                    <div class="col-sm-10">
                                        <div class="form-row">
                                            <div class="ra_inline">
                                                <span class="custom-input">
                                                    <input type="radio" name="semiExamYn" id="semiExamYn_N" value="N" onclick="examChk()"<c:if test="${examVO.semiExamYn eq 'N'}">checked</c:if>/>
                                                    <label for="semiExamYn_N">일반시험</label>
                                                </span>
                                                <span class="custom-input ml5">
                                                    <input type="radio"  name="semiExamYn" id="semiExamYn_Y" value="Y" onclick="examChk()"<c:if test="${examVO.semiExamYn eq 'Y'}">checked</c:if>/>
                                                    <label for="semiExamYn_Y">진행단계평가</label>
                                                </span>
                                            </div>
                                        </div>             
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="row">
                                    <label for="titleInput" class="form-label col-sm-2"><spring:message code="lecture.title.exam.name"/></label>
                                    <div class="col-sm-10">
                                        <div class="form-row">
                                            <input type="text" class="form-control" dispName="<spring:message code="lecture.title.exam.name"/>" placeholder="제목을 입력하세요" maxlength="100" isNull="N" lenCheck="100" name="examTitle" value="${vo.examTitle }" onfocus="this.select()"/>
                                        </div>             
                                    </div>
                                </div>
                            </li>
                            <li class="regular">
                                <div class="row">
                                    <label for="testCalender" class="form-label col-sm-2"><span><spring:message code="lecture.title.exam.duration"/></span></label>
                                    <div class="col-sm-10">
                                        
                                        <div class="form-inline">
                                            <input type="date" dispName="<spring:message code="lecture.title.exam.start.date"/>" name="examStartDttm" value="" id="examStartDttm" class="form-control md" />
                                            <div class="input_btn">
                                                <input type="text" dispName="<spring:message code="lecture.title.exam.start.hour"/>" maxlength="2" isNull="N" lenCheck="2" name="examStartHour" value="${vo.examStartHour }" id="examStartHour" class="form-control sm"  onkeyup="isChkHours(this)" />
                                                <label>시</label>
                                            </div>
                                            <div class="input_btn">
                                                <input type="text" dispName="<spring:message code="lecture.title.exam.start.min"/>" maxlength="2" isNull="N" lenCheck="2" name="examStartMin" value="${vo.examStartMin }" id="examStartMin" class="form-control sm"  onkeyup="isChkMinute(this)" />
                                                <label>분</label>
                                            </div><span class="ruffle_sign">~</span>
                                        </div>
                                        <div class="form-inline">
                                            <input type="date" dispName="<spring:message code="lecture.title.exam.end.date"/>" name="examEndDttm" id="examEndDttm" value="" class="form-control md" />
                                            <div class="input_btn">
                                                <input type="text"  dispName="<spring:message code="lecture.title.exam.end.hour"/>" maxlength="2" isNull="N" lenCheck="2" name="examEndHour" id="examEndHour" value="${vo.examEndHour }" class="form-control sm" onkeyup="isChkHours(this)" />
                                                <label>시</label>
                                            </div>
                                            <div class="input_btn">
                                  				<input type="text"  dispName="<spring:message code="lecture.title.exam.end.min"/>" maxlength="2" isNull="N" lenCheck="2" name="examEndMin" id="examEndMin" value="${vo.examEndMin }" class="form-control sm"  onkeyup="isChkMinute(this)" />
                                                <label>분</label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </li>
                            
                            <li class="regular">
                                <div class="row">
                                    <label for="resultCalender" class="form-label col-sm-2"><span>결과확인일</span></label>
                                    <div class="col-sm-10">
                                        <div class="form-inline">
                                            <input type="date" dispName="<spring:message code="lecture.title.exam.result.date"/>"name="rsltCfrmDttm" id="rsltCfrmDttm" value="" class="class="form-control md"/>
                                            <div class="input_btn">
                                                <input type="text"  dispName="<spring:message code="lecture.title.exam.result.hour"/>" maxlength="2" isNull="N" lenCheck="2" name="rsltCfrmHour" id="rsltCfrmHour" value="${vo.rsltCfrmHour }" class="form-control sm"  onkeyup="isChkHours(this)" />
                                                <label>시</label>
                                            </div>
                                            <div class="input_btn">
                                                <input type="text"  dispName="<spring:message code="lecture.title.exam.result.min"/>" maxlength="2" isNull="N" lenCheck="2" name="rsltCfrmMin" id="rsltCfrmMin" value="${vo.rsltCfrmMin }" class="form-control sm"  onkeyup="isChkMinute(this)" />
                                                <label>분</label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </li>
                            
                            <li class="online_exam">
                                <div class="row">
                                    <label for="timeSelect" class="form-label col-sm-2">시간제한</label>
                                    <div class="col-sm-4">
                                        <div class="form-row">
                                            <select name="stareTmUseYn" id="stareTmUseYn" class="form-select" onchange="changeExamStareTm()">
										<c:forEach var="item" items="${stareTmUseYnList}" varStatus="status">
										<option value="${item.codeCd}" <c:if test="${item.codeCd eq examVO.stareTmUseYn}">selected</c:if>>${item.codeNm}</option>
										</c:forEach>				
											</select>
                                        </div>             
                                    </div>
                                    <label for="timeInput" class="form-label col-sm-2">시간</label>
                                    <div class="col-sm-4">
                                        <div class="form-row">
                                            <div class="input_btn">
                                                <input type="text"  dispName="<spring:message code="common.title.time"/>" maxlength="4" isNull="N" lenCheck="4" name="examStareTm" id="examStareTm" value="${vo.examStareTm }" onfocus="this.select()" class="form-control sm" onkeyup="isChkInteger(this)"/>
                                                <label>분</label>
                                            </div>
                                        </div>             
                                    </div>
                                </div>
                            </li>
                            <li class="online_exam semiExamYn_Y">
                                <div class="row">
										 <label for="rateSelect" class="form-label col-sm-2">강의</label>
									   <div class="col-sm-4">
                                        <div class="form-row">
                                        <select name="sbjCd" id="sbjCd" class="form-select">
										<c:forEach var="item" items="${onlineSubjectList}" varStatus="status">
										<option value="${item.sbjCd }" <c:if test="${item.sbjCd eq examVO.sbjCd}">selected</c:if>>${item.sbjNm}</option>
										</c:forEach>				
											</select>
                                            <div class="input_btn">
											<input type="text"  dispName="<spring:message code="lecture.title.exam.answer.ratio"/>" maxlength="3" isNull="N" lenCheck="3" name="stareLecCount" id="stareLecCount" value="${vo.stareLecCount }" onfocus="this.select()" class="form-control md" onkeyup="isChkMaxNumber(this,100)"/>
											<span style="float:left;line-height:30px;"> 강 수강 후</span>
											</div>
										</div>
									</div>
                                </div>
                            </li>
                            <li class="online_exam semiExamYn_N" style="display: none;">
                                <div class="row">
									 <label for="rateSelect" class="form-label col-sm-2">응시가능진도율</label>
									 <div class="col-sm-4">
                                        <div class="form-row">
                                            <div class="input_btn">
											<input type="text"  dispName="강의 수" maxlength="3" isNull="N" lenCheck="3" name="stareCritPrgrRatio" id="stareCritPrgrRatio" value="${vo.stareCritPrgrRatio }" onfocus="this.select()" class="form-control md" onkeyup="isChkMaxNumber(this,100)"/>
											<label>%이상</label>
											</div>
										</div>
									</div>
                                </div>
                            </li>
                            <li class="online_exam">
                                <div class="row">
                                    <label for="limitInput" class="form-label col-sm-2">응시제한횟수</label>
                                    <div class="col-sm-4">
                                        <div class="form-row">
                                            <div class="input_btn">
                                                <input type="text"  dispName="<spring:message code="lecture.title.exam.limitcnt"/>" maxlength="2" isNull="N" lenCheck="3" name="stareLimitCnt" id="stareLimitCnt" value="${vo.stareLimitCnt }" onfocus="this.select()" class="form-control sm"  onkeyup="isChkInteger(this)"/>
                                                <label>회</label>
                                            </div>
                                        </div>             
                                    </div>
                                </div>
                            </li>
							<li id="input_regYn" style="display: none;">
								<div class="row">
									<label for="regYnSel" class="form-label col-sm-2"><spring:message code="lecture.title.exam.regyn" /></label>
									<div class="col-sm-4">
										<div class="form-row">
											<select name="regYnSel" id="regYnSel"
												class="form-control input-sm">
												<c:forEach items="${regYnList}" var="item">
													<c:set var="codeName" value="${item.codeNm}" />
													<c:forEach var="lang" items="${item.codeLangList}">
														<c:if test="${lang.langCd eq LOCALEKEY }">
															<c:set var="codeName" value="${lang.codeNm}" />
														</c:if>
													</c:forEach>
													<option value="${item.codeCd}"
														<c:if test="${examVO.regYn eq item.codeCd }">selected </c:if>>${codeName}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>
							</li>
							<li class="online_exam">
                                <div class="row">
                                    <label for="pointInput" class="form-label col-sm-2">시험배점<c:if test="${gubun eq 'E'}"><br>*비공개 상태에서만 수정가능</c:if></label>
                                    <div class="col-sm-10">
                                        <div class="form-row">
                                            <div class="res_tbl_wrap">
                                                <table>
                                                    <caption>시험배점 목록</caption>
                                                    <thead>
                                                        <tr>
                                                            <th scope="col">종류</th>
                                                            <th scope="col">문항수</th>
                                                            <th scope="col">배점</th>
                                                            <th scope="col">합(문항수X배점)</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <tr>
                                                            <td scope="row" data-label="종류">선택형</td>
                                                            <td data-label="문항수"><input type="text" class="form-control w50" maxlength="3" id="selCnt" name="selCnt" value="${vo.selCnt }" onfocus="this.select()"  onkeyup="isChkInteger(this)" <c:if test="${vo.regYn eq 'Y'}">readonly="readonly"</c:if> /></td>
															<td data-label="배점"><input type="text" class="form-control w50" maxlength="3" id="selPnt" name="selPnt" value="${vo.selPnt }" onfocus="this.select()"  onkeyup="isChkInteger(this)" <c:if test="${vo.regYn eq 'Y'}">readonly="readonly"</c:if>/></td>
														 	<td data-label="합(문항수X배점)"><input type="text" class="form-control w50" maxlength="3" id="selSum" name="selSum" value="${vo.selCnt * vo.selPnt }" disabled/></td>
                                                        </tr>
                                                        <tr>
                                                            <td scope="row" data-label="종류">단답형</td>
                                                            <td data-label="문항수"><input type="text" class="form-control w50"   maxlength="3" id="shortCnt" name="shortCnt" value="${vo.shortCnt }" onfocus="this.select()"  onkeyup="isChkInteger(this)" <c:if test="${vo.regYn eq 'Y'}">readonly="readonly"</c:if>/></td>
															<td data-label="배점"><input type="text" class="form-control w50"   maxlength="3" id="shortPnt" name="shortPnt" value="${vo.shortPnt }" onfocus="this.select()"  onkeyup="isChkInteger(this)" <c:if test="${vo.regYn eq 'Y'}">readonly="readonly"</c:if>/></td>
															<td data-label="합(문항수X배점)"><input type="text" class="form-control w50"   maxlength="3" id="shortSum" name="shortSum" value="${vo.shortCnt * vo.shortPnt }" disabled/></td>
                                                            
                                                        </tr>
                                                        <tr>
                                                            <td scope="row" data-label="종류">서술형</td>
                                                           	<td data-label="문항수"><input type="text" class="form-control w50"  maxlength="3" id="desCnt" name="desCnt" value="${vo.desCnt }" onfocus="this.select()"  onkeyup="isChkInteger(this)" <c:if test="${vo.regYn eq 'Y'}">readonly="readonly"</c:if>/></td>
															<td data-label="배점"><input type="text" class="form-control w50"  maxlength="3" id="desPnt" name="desPnt" value="${vo.desPnt }" onfocus="this.select()"  onkeyup="isChkInteger(this)" <c:if test="${vo.regYn eq 'Y'}">readonly="readonly"</c:if>/></td>
															<td data-label="합(문항수X배점)"><input type="text" class="form-control w50"  maxlength="3" id="desSum" name="desSum" value="${vo.desCnt * vo.desPnt }" disabled/></td>
                                                            
                                                        </tr>
                                                        <tr>
                                                            <td scope="row" data-label="종류">총합</td>
                                                            <td></td>
                                                            <td></td>
                                                            <td data-label="합(문항수X배점)"><input class="form-control w50" type="text" maxlength="3" disabled id="examRateTotScore" value="${(vo.selCnt * vo.selPnt) + (vo.shortCnt * vo.shortPnt) + (vo.desCnt * vo.desPnt) }"/></td>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                            </div>             
                                        </div>             
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="row">
                                    <label for="contTextarea" class="form-label col-sm-2"><spring:message code="lecture.title.exam.desc"/></label>
                                    <div class="col-sm-10">
                                        <div class="form-row">
                                            <textarea class="form-control" rows="10" id="contTextarea" name="examCts" >${vo.examCts}</textarea>
                                        </div>
                                    </div>
                                </div>
                            </li>                            
                        </ul>
                    </div>
                    <div class="btns mt30">
                        <c:if test="${gubun eq 'A'}">
							<button type="button" class="btn gray2" onclick="addExam()"><spring:message code="button.add"/></button>
						</c:if>
						<c:if test="${gubun eq 'E'}">
							<button type="button" class="btn gray2" onclick="editExam()"><spring:message code="button.add"/></button>
							<button type="button" class="btn type5" onclick="delExam()"><spring:message code="button.delete"/></button>
						</c:if>
							<button type="button" class="btn type5" onclick="listExam();"><spring:message code="button.close"/></button>
                    </div>
                </div>
	</form>
<script type="text/javascript">

	$(document).ready(function() {
		$('.inputNumber').inputNumber();	// 숫자만 입력되도록 설정.
		$('.inputDate').inputDate();	// 숫자만 입력되도록 설정.
		$('.inputSpecial').inputSpecial();  // 특수키가 입력이 들어오묜 false return;
		
		changeExamType();
		changeExamStareType();
	
		
		$("#examStartDttm").val(formatDate("${vo.examStartDttm}"));
		$("#examEndDttm").val(formatDate("${vo.examEndDttm}"));
		$("#rsltCfrmDttm").val(formatDate("${vo.rsltCfrmDttm }"));
		
		<c:if test="${gubun eq 'E'}">
		if("ON" == "${examVO.examTypeCd}"){
			$(".online_exam").show();
		
			
		}
		</c:if>
		examChk();
		
		//문항 수, 배점 변화에 따른 총점 확인
		$('#selCnt, #selPnt, #shortCnt, #shortPnt, #desCnt, #desPnt').on('change keyup' ,function() {
			getExamQstnTotScore(this);
		});
		
	});

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		$('#examForm').attr("action","/lec/exam/" + cmd);
		$('#examForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		alert(resultDTO.message);

		if(resultDTO.result >= 0) {
			// 정상 처리
			listExam();
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

			var startDttmArray = startDttm.split("-");
			var endDttmArray = endDttm.split("-");
			var resultDttmArray = resultDttm.split("-");

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
				
			}
		}else{		//시험일 경우
			var examStareTm = parseInt(f["stareCritPrgrRatio"].value,10);
			if(examType == 'ON') {
				if(examStareTm <=0 ) {
					alert("<spring:message code="lecture.message.exam.alert.answer.ratio"/>");
					$("#stareCritPrgrRatio").focus();
					return;
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

			var startDttmArray = startDttm.split("-");
			var endDttmArray = endDttm.split("-");
			var resultDttmArray = resultDttm.split("-");

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
				}
			}else{		//시험일 경우
				var examStareTm = parseInt(f["stareCritPrgrRatio"].value,10);
				if(examType == 'ON') {
					if(examStareTm <=0 ) {
						alert("<spring:message code="lecture.message.exam.alert.answer.ratio"/>");
						$("#stareCritPrgrRatio").focus();
						return;
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
		if(examType == "ON") {
			$(".online_exam").show();
			$("#regYn").val("${examVO.regYn}");
			$("#regYnSel").val("${examVO.regYn}");
			$('#input_regYn').hide();
			$('.cntsTypeSel').show();

		} else {
			//-- 정규 시험으로 선택
			$("#examStareTypeCd option:eq(0)").prop("selected", "selected");
			$(".online_exam").hide();
			$(".cntsTypeSel").hide();
			$('#input_regYn').show();
		}
		changeExamStareType();
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
		
		var selSum = parseInt(isNull($("#selSum").val()) ? 0 : $("#selSum").val());
		var shortSum = parseInt(isNull($("#shortSum").val()) ? 0 : $("#shortSum").val());
		var desSum = parseInt(isNull($("#desSum").val()) ? 0 : $("#desSum").val());
		var examRateTotScore = parseInt(isNull($("#examRateTotScore").val()) ? 0 : $("#examRateTotScore").val());
		
		var returnFlag = true;
		
		if((selCnt * selPnt) + (shortCnt * shortPnt) + (desCnt * desPnt) > 100){
			if(isNotNull(obj)){//jquery 이벤트 this 여부
				obj.value = 0;//초기화
			}else{//전송 전 체크
				$("#selCnt").val(0);
				$("#selPnt").val(0);
				$("#shortCnt").val(0);
				$("#shortPnt").val(0);
				$("#desCnt").val(0);
				$("#desPnt").val(0);
			}
			alert("총합이 100을 초과해서는 안됩니다.");
			returnFlag = false;
		}
		
		//초기화 후 다시 세팅
		selCnt 	 = parseInt(isNull($("#selCnt").val()) ? 0 : $("#selCnt").val());           
		selPnt 	 = parseInt(isNull($("#selPnt").val()) ? 0 : $("#selPnt").val());           
		shortCnt = parseInt(isNull($("#shortCnt").val()) ? 0 : $("#shortCnt").val());       
		shortPnt = parseInt(isNull($("#shortPnt").val()) ? 0 : $("#shortPnt").val());       
		desCnt   = parseInt(isNull($("#desCnt").val()) ? 0 : $("#desCnt").val());           
		desPnt   = parseInt(isNull($("#desPnt").val()) ? 0 : $("#desPnt").val()); 
		
		$("#selSum").val(selCnt * selPnt);
		$("#shortSum").val(shortCnt * shortPnt);
		$("#desSum").val(desCnt * desPnt);
		
		$("#examRateTotScore").val( (selCnt * selPnt) + (shortCnt * shortPnt) + (desCnt * desPnt));
		return returnFlag;	
	}
	
	function listExam() {
		var url = "/lec/exam/examMain";
		document.location.href = url;
	}
	
	function formatDate(date) {
		var dt = date.substring(0,4)+"-"+date.substring(4,6)+"-"+date.substring(6,8);
		return dt;
	}
	function examChk(){
		var radioVal = $('input[name="semiExamYn"]:checked').val();
		if(radioVal == undefined){
			radioVal = 'N'
			$(":radio[name='semiExamYn'][value='N']").attr('checked', true);
		}
		examCtl(radioVal);
	}
	
	// 평가 유형 설정
	function examCtl(radioVal){
		
		// 강좌유형 상태값 
		switch(radioVal){
    	case "Y" : 	
			$(".semiExamYn_Y").css("display", "");
	   		$(".semiExamYn_N").css("display", "none");
    		break;
	   	case "N" :
	   		$(".semiExamYn_Y").css("display", "none");
	   		$(".semiExamYn_N").css("display", "");
    		break;
		}
	}	
</script>

