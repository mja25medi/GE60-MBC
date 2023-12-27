<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>

	<br/>
	<form name="Search" id="Search" onsubmit="return false">
	<div class="row">
		<div class="col-lg-12">
			<div class="input-group" style="float:left">
				<select name="declsNo" id="declsNo" onchange="listStudent(1)" class="form-control input-sm">
					<option value=""><spring:message code="course.title.decls.all"/></option>
					<c:forEach var="decls" items="${creCrsDeclsList}">
					<option value="${decls.declsNo}">${decls.declsNo}</option>
					</c:forEach>
				</select>
			</div>
			<div class="input-group" style="float:left;">
				<select name="enrlSts" id="enrlSts" onchange="listStudent(1)" class="form-control input-sm">
					<option value=""><spring:message code="student.title.student.status"/></option>
					<option value="E"><spring:message code="student.title.student.status.enroll"/></option>
					<option value="S"><spring:message code="student.title.student.status.confirm"/></option>
					<option value="N"><spring:message code="student.title.student.status.cancel"/></option>
				</select>
			</div>
			<div class="input-group" style="float:left;">
				<select name="repaySts" id="repaySts" onchange="listStudent(1)" class="form-control input-sm">
					<option value=""><spring:message code="student.title.student.repay.status"/></option>
					<c:forEach var="code" items="${repayStsList}">
						<c:set var="codeName" value="${code.codeNm}"/>
						<c:forEach var="lang" items="${code.codeLangList }">
							<c:if test="${lang.langCd eq LOCALEKEY }"><c:set var="codeName" value="${lang.codeNm}"/></c:if>
						</c:forEach>
					<option value="${code.codeCd}">${codeName}</option>
					</c:forEach>
				</select>
			</div>
			<div style="float:left;">
				<span style="float:left;line-height:30px;"><spring:message code="student.title.student.enrolldate"/></span>
				<div class="input-group" style="width:128px;float:left;">
					<input type="text" name="startDate" id="startDate" class="form-control input-sm"/>
					<span class="input-group-addon btn-sm" onclick="_clickCalendar('startDate')" style="cursor:pointer">
						<i class="fa fa-calendar"></i>
					</span>
				</div>
				<div class="input-group text-center" style="float:left;line-heigth:30px;padding-left:10px; padding-right:10px;"> ~ </div>
				<div class="input-group" style="width:128px;float:left;">
					<input type="text" name="endDate" id="endDate" class="form-control input-sm" />
					<span class="input-group-addon btn-sm" onclick="_clickCalendar('endDate')" style="cursor:pointer">
						<i class="fa fa-calendar"></i>
					</span>
				</div>
				<meditag:datepicker name1="startDate" name2="endDate"/>
			</div>
			<div class="input-group" style="float:left;width:200px;">
				<span class="input-group-addon"><spring:message code="student.title.student.name"/></span>
				<input type="text" name="searchKey" class="_enterBind form-control input-sm" title="<spring:message code="student.title.student.name"/>" />
				<span class="input-group-addon" style="cursor:pointer" onclick="listStudent(1)">
					<i class="fa fa-search"></i>
				</span>
			</div>
			<div style="float:right">
				<select name="listScale" id="listScale" onchange="listStudent(1)" class="form-control input-sm">
					<option value="10">10</option>
					<option value="20" selected="selected">20</option>
					<option value="40">40</option>
					<option value="60">60</option>
					<option value="80">80</option>
					<option value="100">100</option>
					<option value="200">200</option>
				</select>
			</div>
			<div style="float:right">
				<c:if test="${MSG_SMS eq 'Y' }">
				<a href="javascript:messageForm('SMS')" class="btn btn-info btn-sm"><spring:message code="button.sms"/> </a>
				</c:if>
				<c:if test="${MSG_EMAIL eq 'Y' }">
				<a href="javascript:messageForm('EMAIL')" class="btn btn-info btn-sm"><spring:message code="button.email"/> </a>
				</c:if>
				<c:if test="${MSG_NOTE eq 'Y' }">
				<a href="javascript:messageForm('MSG')" class="btn btn-info btn-sm"><spring:message code="button.note"/> </a>
				</c:if>
			</div>
		</div>
	</div>
	<div class="row" style="margin-top:5px;">
		<div class="col-lg-12">
			<div style="float:left">
				<a href="javascript:confirmStudent()" class="btn btn-primary btn-sm"><spring:message code="button.confirm"/> </a>
				<a href="javascript:cancelStudent()" class="btn btn-warning btn-sm"><spring:message code="button.cancel"/> </a>
				<a href="javascript:deleteStudent()" class="btn btn-warning btn-sm"><spring:message code="button.delete"/> </a>
				<a href="javascript:confirmDeposit()" class="btn btn-info btn-sm"><spring:message code="button.process.payment"/> </a>
				<a href="javascript:resetPass()" class="btn btn-default btn-sm"><spring:message code="button.reset.password"/> </a>
			</div>
			<div style="float:right">
				<a href="javascript:studentWrite()" class="btn btn-primary btn-sm"><spring:message code="button.write.each"/> </a>
				<a href="javascript:excelDownload('ENROLL')" class="btn btn-primary btn-sm"><i class="fa fa-file-excel-o fa-fw"></i> <spring:message code="student.title.student.list.enroll"/> </a>
				<a href="javascript:excelDownload('STUDENT')" class="btn btn-primary btn-sm"><i class="fa fa-file-excel-o fa-fw"></i> <spring:message code="student.title.student.list.student"/> </a>
			</div>
		</div>
	</div>
	<div id="studentList" style="margin-top:5px;">
		<table summary="<spring:message code="student.title.student.manage"/>" class="table table-bordered wordbreak">
			<colgroup>
				<col style="width:25px" />
				<col style="width:auto" />
				<col style="width:auto" />
				<col style="width:auto" />
				<col style="width:auto" />
				<col style="width:auto" />
				<col style="width:auto" />
				<col style="width:auto" />
				<col style="width:auto" />
				<col style="width:auto" />
				<col style="width:auto" />
				<col style="width:50px" />
				<col style="width:50px" />
			</colgroup>
			<thead>
				<tr>
					<th scope="col" ></th>
					<th scope="col" ><spring:message code="common.title.no"/></th>
					<th scope="col" ><spring:message code="student.title.student.decls"/></th>
					<th scope="col" ><spring:message code="user.title.userinfo.name"/></th>
					<th scope="col" ><spring:message code="user.title.userinfo.userid"/></th>
					<th scope="col" ><spring:message code="student.title.student.enrolldate"/></th>
					<th scope="col" ><spring:message code="student.title.student.payment.mthd"/></th>
					<th scope="col" ><spring:message code="student.title.student.payment.fee"/></th>
					<th scope="col" ><spring:message code="student.title.student.payment.status"/></th>
					<th scope="col" ><spring:message code="common.title.stats"/></th>
					<th scope="col" ><spring:message code="student.title.student.repay.status"/></th>
					<th scope="col" ><spring:message code="student.title.student.info"/></th>
					<th scope="col" ><spring:message code="student.title.student.repay"/></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td colspan="13"><spring:message code="student.message.student.enroll.nodata"/></td>
				</tr>
			</tbody>
		</table>
	</div>

	<input type="submit" value="submit" style="display:none" />
	</form>
	
	<form id="studentForm" name="studentForm" onsubmit="return false" >
		<input type="hidden" name="crsCreCd" value="${vo.crsCreCd }"/>
		<input type="hidden" name="stdNo"/>
		<input type="hidden" name="ssnYn"/>
		<input type="hidden" name="enrlSts"/>
		<input type="hidden" name="userNm"/>
		<input type="hidden" name="startDate"/>
		<input type="hidden" name="endDate"/>
		<input type="hidden" name="deptCd"/>
		<input type="hidden" name="dateSearchType" value="ENRL"/>
		<input type="submit" value="submit" style="display:none" />
	</form>
	
<script type="text/javascript">
	var ItemDTO = new Object();
	var nextFunc ="";

	$(document).ready(function() {
		$("._enterBind").bind("keydown", eventForSearch);

		function eventForSearch(event) {
			if (event.keyCode == '13') {
				listStudent(1);
			}
		}

		ItemDTO.crsCreCd = '${vo.crsCreCd}';
		ItemDTO.sortKey = "USER_NM_ASC";
		listStudent(1);
	});

	function checkAll() {
	    var state=$('input[name=chkAll]:checked').size();
	    if(state==1){
	   		$(document).find("#Search input[name='sel']").prop({checked:true});
	    }else{
	    	$(document).find("#Search input[name='sel']").prop({checked:false});
	    }
	}

	/**
	 * 수강신청자 목록을 조회한다.
	 */
	function listStudent(page) {
		ItemDTO.curPage = page;
		var f = document.Search;
		//f.chkAll.checked = false;
		var declsNo = $("#declsNo option:selected").val();
		var enrlSts = $("#enrlSts option:selected").val();
		var repayStsCd = $("#repaySts option:selected").val();
		var listScale = $("#listScale option:selected").val();

		var userNm = f.searchKey.value;
		var startDate = f.startDate.value;
		var endDate = f.endDate.value;

		$("#studentList")
			.load( cUrl("/mng/std/student/listEnrollStudent"), {
				"crsCreCd" : ItemDTO.crsCreCd,
				"enrlSts" : enrlSts,
				"repayStsCd" : repayStsCd,
				"userNm" : userNm,
				"startDate" : startDate,
				"endDate" : endDate,
				"dateSearchType" : 'ENRL',
				"declsNo" : declsNo,
				"sortKey" : ItemDTO.sortKey,
				"curPage" : ItemDTO.curPage,
				"listScale" : listScale },
				listStudentCallback
			);
	}

	function listStudentCallback() {
		var iframeObj = parent.document.getElementById("subWorkFrame");
		parent.resizeIframe3(iframeObj, $(document).height());
	}


	function confirmStudent() {
		var enrlSts = $("#enrlSts option:selected").val();
		if(setStudent()) {
			process("editConfirmStudent");
		}
	}

	function cancelStudent() {
		if(setStudent()) {
			if(confirm("<spring:message code="student.message.student.confirm.enroll.cancel"/>")){
				process("editCancelStudent");
			}
		}
	}

	function deleteStudent() {
		if(setStudent()) {
			if(confirm("<spring:message code="student.message.student.confirm.enroll.delete"/>")){
				process("deleteStudent");
			}
		}
	}

	function setStudent() {
		var strs = "";
		$('input[name=sel]:checked').each(function() {
				strs = strs + "|" + $(this).val();
			}
		);
		strs = strs.substring(1);
		if(strs == "") {
			alert("<spring:message code="student.message.student.alert.select.user"/>");
			return false;
		}
		document.studentForm["stdNo"].value = strs;
		return true;
	}


	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		if(!validate(document.studentForm)) return;
		$('#studentForm').attr("action", "/mng/std/student/" +cmd);
		$('#studentForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		alert(resultDTO.message);
		if(resultDTO.result >= 0) {
			// 정상 처리
			listStudent(ItemDTO.curPage);
		} else {
			// 비정상 처리
		}
	}



	/**
	 * 수강생 등록 폼
	 */
	function studentWrite() {
		var addContent  = "<iframe id='addStudentFrame' name='addStudentFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='yes' src='<c:url value="/mng/std/student/addFormStudentPop"/>"
			+ "?crsCreCd="+ItemDTO.crsCreCd+"'/>";
		parent.modalBox.clear();
		parent.modalBox.addContents(addContent);
		parent.modalBox.resize(800, 700);
		parent.modalBox.setTitle("<spring:message code="student.title.student.manage"/>");
		parent.modalBox.show();
	}

	//교육생 상세 정보
	function viewStudent(stdNo){
		var addContent  = "<iframe id='studentInfoFrame' name='studentInfoFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/mng/std/student/viewStudentPop"/>"
			+ "?stdNo="+stdNo+"&amp;stayYn=${courseVO.stayYn}'/>";
		parent.modalBox.clear();
		parent.modalBox.addContents(addContent);
		parent.modalBox.resize(800, 430);
		parent.modalBox.setTitle("<spring:message code="student.title.student.detailinfo"/>");
		parent.modalBox.show();
	}

	/**
	* 수강생 등록 후 자동으로 부모창 리프레쉬
	*/
	function popRefresh(){
		//$("#enrlSts").val("S");
		listStudent(1);
	}

	/**
	 * 교육생 카드 출력
	 */
	function printStdInfo() {
		if(setStudent()) {
			var url = cUrl("/mng/std/student/viewPrintStdCardPop?stdNo="+document.studentForm["stdNo"].value+"${AMPERSAND}reportCnt="+$('input[name=sel]:checked').length);
			var printWin = window.open(url, 'stdCardPrint','width=760, height=700, top=10, left=10, scrollbars=0');
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

	//-- 메시지 입력 폼 호출
	function messageForm(msgDivCd) {
		var userList = $("#Search input[name='sel']:checked").stringValues();
		if(userList == "") {
			alert("<spring:message code="common.message.nouser.message"/>");
			return;
		}
		var addContent  = "<iframe id='messageFrame' name='messageFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/mng/log/message/addMessagePop"/>"
			+ "?logMsgLogVO.msgDivCd="+msgDivCd+"&amp;logMsgTransLogVO.userNoList="+userList+"'/>";
		parent.modalBox.clear();
		parent.modalBox.addContents(addContent);
		parent.modalBox.resize(720, 520);
		parent.modalBox.setTitle(getMessageTitle(msgDivCd));
		parent.modalBox.show();
	}

	// 사용자 상세 정보 조회
	function userInfo(userNo) {
		var addContent  = "<iframe id='userInfoFrame' name='userInfoFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/mng/user/userInfo/viewUserPop"/>"
			+ "?userNo="+userNo+"'/>";
		parent.modalBox.clear();
		parent.modalBox.addContents(addContent);
		parent.modalBox.resize(800, 400);
		parent.modalBox.setTitle("<spring:message code="user.title.userinfo.user.info"/>");
		parent.modalBox.show();
	}

	// 교육생 환불 관리
	function editRepay(stdNo){
		var addContent  = "<iframe id='studentInfoFrame' name='studentInfoFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/mng/std/student/editFormRepayPop"/>"
			+ "?stdNo="+stdNo+"'/>";
		parent.modalBox.clear();
		parent.modalBox.addContents(addContent);
		parent.modalBox.resize(800, 400);
		parent.modalBox.setTitle("<spring:message code="student.title.student.detailinfo"/>");
		parent.modalBox.show();
	}

	// 교육생 입금 확인 일괄처리
	function confirmDeposit() {
		var enrlSts = $("#enrlSts option:selected").val();
		if(confirm("<spring:message code="student.message.student.confirm.payment"/>")) {
			if(setStudent()) {
				process("confirmDepositStudent");
			}
		}
	}


	function resetPass() {
		if(setStudent()) {
			if(confirm("<spring:message code="student.message.student.confirm.reset.password"/>")) {
				process("resetPassStudent");
			}
		}
	}

	function printReceiptReport(stdNo) {
		var url = "${reportUrl}?rex_rptname=receipt${AMPERSAND}stdNo="+stdNo;
		var option = "width=830, height=640, toolbar=no, menubar=no, scrollbars=no, resizable=no, copyhistory=no";
		var reportWin = window.open(url,'reportWin', option);
		reportWin.focus();
	}

	function setSortKey(sortKey) {
		ItemDTO.sortKey = sortKey;
		listStudent(1);
	}

	// 수강생 명단
	function excelDownload(type){
		var mTitle = "<spring:message code="student.title.student.list.enroll"/>";
		if(type == 'STUDENT') mTitle = "<spring:message code="student.title.student.list.student"/>";
		var addContent  = "<iframe id='studentInfoFrame' name='studentInfoFrame' width='100%' height='100%' "
					+ "frameborder='0' scrolling='auto' src='<c:url value="/mng/std/student/excelDownloadPop"/>"
					+ "?downloadType="+type+"&amp;crsCreCd="+ItemDTO.crsCreCd+"'/>";
		parent.modalBox.clear();
		parent.modalBox.addContents(addContent);
		parent.modalBox.resize(500, 340);
		parent.modalBox.setTitle(mTitle);
		parent.modalBox.show();
	}
</script>
