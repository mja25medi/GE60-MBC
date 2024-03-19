<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<%pageContext.setAttribute("crlf", "\n");%>
<c:url var="img_base" value="/img/home" />
<c:set var="gubun" value="${gubun}"/>
<c:set var="assignmentVO" value="${vo}" />
<c:set var="assignmentSendVO" value="${vo.assignmentSendVO}" />
<c:set var="assignmentSubListVO" value="${assignmentSubListVO}" />
<c:set var="pageInfo" value="${pageInfo}"/>
                <div class="learn_top m_column_row">
                    <div class="left_title">
                        <h3><spring:message code="lecture.title.assignment.manage"/></h3>
                        <button type="button" class="list" onclick="goList()"><spring:message code="button.list"/></button>
                    </div>
                </div>
                <div class="segment">
                
                    <div class="board_top">
                        <h4>과제정보</h4>
                    </div>
                    <div class="table_list">
							<form id="assignmentForm" name="assignmentForm" onsubmit="return false">
								<input type="hidden" name="curPage" id="curPage" value="${vo.curPage}"/>
								<input type="hidden" name="crsCreCd" id="crsCreCd" value="${vo.crsCreCd}"/>
								<input type="hidden" name="asmtSn" id="asmtSn" value="${vo.asmtSn}"/>
								<input type="hidden" name="sendCnt" value="${vo.sendCnt}" />
								<input type="hidden" name="attachFileSns" value="${vo.attachFileSns}" />
								<input type="hidden" name="score" value="${assignmentSendVO.score}" />
								<input type="hidden" name="stdNo" value="${assignmentSendVO.stdNo}" />
								<input type="hidden" name="userNm" value="${vo.userNm}" />
								<input type="hidden" name="declsNo" value="${vo.declsNo}" />
								<input type="hidden" name="rateYn" id="rateYn" value="N" />
								<input type="hidden" name="userNoObj" id="userNoObj"/>
								<input type="hidden" name="strStdNo" />
								<input type="hidden" name="strScore" />
								<input type="hidden" name="listScale" value="${vo.listScale}"/>
								<input type="hidden" name="tab" />
								<input type="hidden" name="asmtSelectTypeCd" value="${vo.asmtSelectTypeCd}" />
								<input type="hidden" name="asmtTypeCd" value="${vo.asmtTypeCd}" />
								<input type="hidden" name="asmtTitle" value="${vo.asmtTitle}" id="asmtTitle"/>
								<input type="hidden" name="asmtStartDttm" value="${vo.asmtStartDttm}" id="asmtStartDttm" />
								<input type="hidden" name="asmtStartHour" value="${vo.asmtStartHour}" />
								<input type="hidden" name="asmtStartMin" value="${vo.asmtStartMin}" />
								<input type="hidden" name="asmtEndDttm" value="${vo.asmtEndDttm}" id="asmtEndDttm"/>
								<input type="hidden" name="asmtEndHour" value="${vo.asmtEndHour}" />
								<input type="hidden" name="asmtEndMin" value="${vo.asmtEndMin}" />
								<input type="hidden" name="extSendDttm" value="${vo.extSendDttm}" id="extSendDttm"/>
								<input type="hidden" name="extSendHour" value="${vo.extSendHour}" />
								<input type="hidden" name="extSendMin" value="${vo.extSendMin}" />
								<input type="hidden" name="asmtLimitCnt" value="${vo.asmtLimitCnt}" id="asmtLimitCnt"/>
								<input type="hidden" name="asmtCts" value="${vo.asmtCts}" id="asmtCts"/>
								<input type="hidden" name="regYn" value="${vo.regYn}" id="regYn"/>
								<input type="hidden" name="asmtSvcCd" value="${vo.asmtSvcCd}" id="asmtSvcCd"/>
                    
                        <ul class="list">
                            <li class="head"><label><spring:message code="lecture.title.assignment.name"/></label></li>
                            <li>${assignmentVO.asmtTitle}</li>
                        </ul>
                        <ul class="list">
                            <li class="head"><label><spring:message code="lecture.title.assignment.type"/></label></li>
                            <li>
                            <c:if test="${assignmentVO.asmtTypeCd eq 'ON'}"><label class="btn3 sm solid fcGreen">온라인</label></c:if>
							<c:if test="${assignmentVO.asmtTypeCd eq 'OFF'}"><label class="btn3 sm solid fcViolet">오프라인</label></c:if>
							</li>
							<c:if test="${assignmentVO.asmtTypeCd eq 'ON'}">
							<li class="head"><label><spring:message code="lecture.title.assignment.seltype"/></label></li>
                            <li><meditag:codename code="${assignmentVO.asmtSelectTypeCd}" category="ASMT_SELECT_TYPE_CD" /></li>
							</c:if>
                        </ul>
                        <c:if test="${assignmentVO.asmtStareTypeCd eq 'R' }">
                        <ul class="list">
                            <li class="head"><label><spring:message code="lecture.title.assignment.duration"/></label></li>
                            <li>${assignmentVO.asmtStartDttm} ${assignmentVO.asmtStartHour}:${assignmentVO.asmtStartMin} ~ ${assignmentVO.asmtEndDttm} ${assignmentVO.asmtEndHour}:${assignmentVO.asmtEndMin}</li>
                        </ul>
                        </c:if>
                        <ul class="list">
                            <c:if test="${assignmentVO.asmtStareTypeCd eq 'R' }">
                           	 	<li class="head"><label><spring:message code="lecture.title.assignment.delaydate"/></label></li>
                            	<li>${assignmentVO.extSendDttm} ${assignmentVO.extSendHour}:${assignmentVO.extSendMin}</li>
                            </c:if>
                            <c:if test="${assignmentVO.asmtTypeCd eq 'ON'}">
                            <li class="head"><label><spring:message code="lecture.title.assignment.send.cnt"/></label></li>
                            <li>${assignmentVO.asmtLimitCnt} <spring:message code="common.title.times.postfix"/></li>
							</c:if>
                            
                        </ul>
                        <ul class="list">
                            <li class="head"><label><spring:message code="common.title.atachfile"/></label></li>
                            <li>
                                <ul class="add_file">
                                	<c:forEach var="file" items="${assignmentVO.attachFiles}">
                                		<li>${file.lecDownloadTag}</li>
                                	</c:forEach>


                                </ul>
                            </li>
                        </ul>
                        <ul class="list">
                            <li class="head"><label><spring:message code="common.title.cnts"/></label></li>
                            <li>${fn:replace(assignmentVO.asmtCts,crlf,"<br/>")}</li>
                        </ul>
                        <ul class="list">
                            <li class="head"><label><spring:message code="lecture.title.assignment.regyn"/></label></li>
                            <li>
                            	<c:set var="regYn" value="${assignmentVO.regYn}"/>
								<c:if test="${empty assignmentVO.regYn}"><c:set var="regYn" value="N"/></c:if>
								<meditag:codename code="${regYn}" category="REG_YN" />
                            </li>
                        </ul>
                        </form>
                    </div>

                    
                </div>
                    <ul class="class_tab">
                        <li class="active"><a href="#"><spring:message code="lecture.title.assignment.manage.question.tab"/></a></li>
                        <li><a href="<c:url value="/lec/assignment/editFormAssignmentRateMain?crsCreCd=${assignmentVO.crsCreCd}&amp;asmtSn=${assignmentVO.asmtSn}"/>"><spring:message code="lecture.title.assignment.manage.rate.tab"/></a></li>
                        <%-- <li><a href="<c:url value="/lec/assignment/editFormAssignmentRsltMain?crsCreCd=${assignmentVO.crsCreCd}&amp;asmtSn=${assignmentVO.asmtSn}"/>"><spring:message code="lecture.title.assignment.manage.rslt.tab"/></a></li> --%>
                    </ul>
                <div class="segment">
                	<form name="SearchSub" id="SearchSub" action="javascript:listSub()">
                    <div class="board_top">
                        <div class="page_btn">
							<c:if test="${assignmentVO.regYn ne 'Y'}">
								<c:if test="${vo.asmtSvcCd eq 'CODE'}">
									<button type="button" class="btn type3" onclick="writeAIQstn();">AI<spring:message code="button.write.question"/></button>
								</c:if>
                            <button type="button" class="btn type4" onclick="subWrite()"><spring:message code="button.write.assignment.question"/></button>
                            <button type="button" class="btn type1" onclick="assignmentRegist()"><spring:message code="button.ok.regist"/></button>
							</c:if>
							<c:if test="${assignmentVO.regYn eq 'Y'}">
							<button type="button" class="btn type1" onclick="assignmentRegistCancel()"><spring:message code="button.cancel.regist"/></button>
							</c:if>                            
                        </div>
                    </div>
                    <div class="res_tbl_wrap">
                        <table>
                            <caption>문제관리 목록</caption>
                            <thead>
                                <tr>
                                    <th scope="col" width="8%"><spring:message code="common.title.no"/></th>
                                    <th scope="col"><spring:message code="lecture.title.assignment.question"/></th>
                                    <th scope="col" width="15%"><spring:message code="common.title.edit"/></th>
                                </tr>
                            </thead>
                            <tbody>
							<c:forEach items="${assignmentSubListVO}" var="item" varStatus="status">
								<tr>
									<td scope="row" data-label="<spring:message code="common.title.no"/>">${status.count}</td>
									<td class="title" data-label="<spring:message code="lecture.title.assignment.question"/>"><a href="javascript:subView('${item.asmtSubSn}')" style="color: #337ab7;">${item.asmtTitle}</a></td>
									<td data-label="<spring:message code="button.manage"/>">
										<c:if test="${assignmentVO.regYn eq 'Y' }">-</c:if>
										<c:if test="${assignmentVO.regYn ne 'Y' }">
											<button class="btn type3" onclick="subEdit('${item.asmtSubSn}')"><spring:message code="button.manage"/></button>
										</c:if>
									</td>
								</tr>
							</c:forEach>
							<c:if test="${empty assignmentSubListVO}">
								<tr>
									<td colspan='3' data-label=""><spring:message code="lecture.message.assignment.question.nodata"/></td>
								</tr>
							</c:if>

                            </tbody>
                        </table>
                    </div>
                    </form>
                </div>


<script type="text/javascript">

	var ItemDTO = new Object();
	var atchFiles; // 첨부파일 목록

	var modalBox = null;

	$(document).ready(function() {
		modalBox = new $M.ModalDialog({
			"modalid" : "modal1"
		});

		$('.inputNumber').inputNumber();	// 숫자만 입력되도록 설정.
		$('.inputDate').inputDate();	// 숫자만 입력되도록 설정.
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

	});

	function uploderclick(str) {
		$("#"+str).click();
	}

	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}

	/**
	 * 수정 화면 토글
	 */
	function viewEditForm() {
		$("#viewAsmt").hide();
		$("#editAsmt").show();
	}

	/**
	 * 수정 화면 토글
	 */
	function hideEditForm() {
		$("#viewAsmt").show();
		$("#editAsmt").hide();
	}

	function goList() {
		document.location.href = cUrl("/lec/assignment/tchAssignmentMain")+"?crsCreCd=${assignmentVO.crsCreCd}";
	}

	function delAssignment() {
		$('#uploadify').attr("disabled", true); //-- 파일 객체 disable
		var f = document.assignmentForm;
		var sendCnt = parseInt(f["sendCnt"].value,10);
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

	/**
	 * 과제 정보 수정
	 */
/* 	function editAssignment() {
		$(':input:hidden[name=attachFileSns]').val(atchFiles.getFileSnAll());
		$('#uploadify').attr("disabled", true); //-- 파일 객체 disable

		var asmtType = '${assignmentVO.asmtTypeCd}';
		var f = document.assignmentForm;

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

		if(validate(document.assignmentForm) ==false) return ;
		if(validateTime()==false) return;

		var extSend = $('#extSendDttm').val().replace(/[.]/g, "")+f["extSendHour"].value+f["extSendMin"].value;
		var asmtEnd = $('#asmtEndDttm').val().replace(/[.]/g, "")+f["asmtEndHour"].value+f["asmtEndMin"].value;

		if(asmtEnd > extSend){
			alert("<spring:message code="lecture.message.assignment.alert.delaydate"/>");
			return;
		}
		if(!validate(document.assignmentForm)) return;
		process("editAssignment");	// cmd
	}
 */

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

	/**
	 * 과제 등록 완료
	 */
	function assignmentRegist() {
		<c:if test="${empty assignmentSubListVO}">
		alert("<spring:message code="lecture.message.assignment.question.nodata"/>");
		return;
		</c:if>
		document.assignmentForm["regYn"].value = "Y";
		$('#uploadify').attr("disabled", true); //-- 파일 객체 disable
		process("addRegistAssignment");	// cmd
	}

	/**
	 * 과제 등록  취소
	 */
	function assignmentRegistCancel() {
		if(parseInt(document.assignmentForm["sendCnt"].value,10) > 0) {
			alert("<spring:message code="lecture.message.assignment.alert.cancelregist"/>");
			return;
		}
		document.assignmentForm["regYn"].value = "N";
		$('#uploadify').attr("disabled", true); //-- 파일 객체 disable
		process("editRegistAssignment");	// cmd
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

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		$('#assignmentForm').attr("action","/lec/assignment/"+cmd);
		document.assignmentForm.submit();
	}

	/**
	 * 문제 은행 보기 폼
	 */
	function qstnBankWrite() {
		if('${assignmentVO.regYn}' == "Y") {
			alert("<spring:message code="lecture.message.assignment.question.alert.write"/>");
			return;
		}
		var url = cUrl("/lec/assignment/editQbankPop")+"?crsCreCd=${assignmentVO.crsCreCd}"+"&amp;asmtSn=${assignmentVO.asmtSn}";
		var winOption = "left=0,top=0,toolbar=0,location=0,directories=0,status=yes,menubar=0,scrollbars=yes,resizable=yes,width=760,height=600";
		var qstnBankWin = window.open(url, "qstnBankWin", winOption);
		qstnBankWin.focus();
	}

	/**
	 * 과제 문제 등록 폼 호출
	 */
	function subWrite() {
		if('${assignmentVO.regYn}' == "Y") {
			alert("<spring:message code="lecture.message.assignment.question.alert.write"/>");
			return;
		}

		var addContent  = "<iframe id='asmtWriteFrame' name='asmtWriteFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/lec/assignment/addFormSubPop"/>"
			+ "?crsCreCd=${assignmentVO.crsCreCd}&amp;asmtSn=${assignmentVO.asmtSn}'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(1400, 800);
		modalBox.setTitle("<spring:message code="lecture.title.assignment.question.manage"/>");
		modalBox.show();
	}

	/**
	 * 과제 문제 수정 폼 호출
	 */
	function subEdit(asmtSubSn) {
		if('${assignmentVO.regYn}' == "Y") {
			alert("<spring:message code="lecture.message.assignment.question.alert.edit"/>");
			return;
		}

		var addContent  = "<iframe id='asmtWriteFrame' name='asmtWriteFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/lec/assignment/editFormSubPop"/>"
			+ "?crsCreCd=${assignmentVO.crsCreCd}&amp;asmtSn=${assignmentVO.asmtSn}&amp;asmtSubSn="+asmtSubSn+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(1400, 800);
		modalBox.setTitle("<spring:message code="lecture.title.assignment.question.manage"/>");
		modalBox.show();
	}

	function subView(asmtSubSn){
		var addContent  = "<iframe id='asmtWriteFrame' name='asmtWriteFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/lec/assignment/viewSubPop"/>"
			+ "?crsCreCd=${assignmentVO.crsCreCd}&amp;asmtSn=${assignmentVO.asmtSn}&amp;asmtSubSn="+asmtSubSn+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(1400, 600);
		modalBox.setTitle("<spring:message code="lecture.title.assignment.question.manage"/>");
		modalBox.show();
	}
	
	/**
	 * AI 과제 문제 등록 폼 호출
	 */
	function writeAIQstn() {

		if('${assignmentVO.regYn}' == "Y") {
			alert("<spring:message code="lecture.message.assignment.question.alert.write"/>");
			return;
		}
		var addContent  = "<iframe id='addSubFrame' name='addSubFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/lec/assignment/addAIQstnPop"/>"
			+ "?crsCreCd=${assignmentVO.crsCreCd}&amp;asmtSn=${assignmentVO.asmtSn}'/>";
		parent.modalBox.clear();
		parent.modalBox.addContents(addContent);
		parent.modalBox.resize(1200, 700);
		parent.modalBox.setTitle("AI<spring:message code="lecture.title.assignment.question.manage"/>");
		parent.modalBox.show();
	}
</script>
