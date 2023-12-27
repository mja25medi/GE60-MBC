<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="paging" value="Y"/>

	<br/>
	<form name="Search" id="Search" onsubmit="return false;">
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
					<option value="S"><spring:message code="student.title.student.status.confirm"/></option>
					<option value="C"><spring:message code="student.title.student.status.complete"/></option>
					<option value="F"><spring:message code="student.title.student.status.failed"/></option>
				</select>
			</div>
			<div style="float:left;padding-right:5px">
				<span style="float:left;line-height:30px;"><spring:message code="student.title.student.completedate"/></span>
				<div class="input-group" style="width:128px;float:left;">
					<input type="text" name="startDate" id="startDate" class="form-control input-sm" />
					<span class="input-group-addon btn-sm" onclick="_clickCalendar('startDate')" style="cursor:pointer">
						<i class="fa fa-calendar"></i>
					</span>
				</div>
				<div class="input-group text-center" style="float:left;line-heigth:30px;padding-left:10px; padding-right:10px;"> ~ </div>
				<div class="input-group" style="width:128px;float:left;">
					<input type="text" name="endDate" id="endDate" class="form-control input-sm"/>
					<span class="input-group-addon btn-sm" onclick="_clickCalendar('endDate')" style="cursor:pointer">
						<i class="fa fa-calendar"></i>
					</span>
				</div>
				<meditag:datepicker name1="startDate" name2="endDate"/>
			</div>
			<div class="input-group" style="float:left;width:200px;">
				<span class="input-group-addon"><spring:message code="user.title.userinfo.name"/></span>
				<input type="text" name="searchKey" id="searchKey" style="ime-mode:active;" class="_enterBind form-control input-sm" maxlength="10"/>
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
				<%-- 
				<a href="javascript:autoCompleteStudent()" class="btn btn-primary btn-sm"><spring:message code="button.complete.auto"/> </a>
				<a href="javascript:checkCompleteStudent()" class="btn btn-primary btn-sm"><spring:message code="button.complete.select"/> </a>
				<a href="javascript:cancelStudent()" class="btn btn-primary btn-sm"><spring:message code="button.complete.cancel"/> </a>
				--%>
			</div>
			<div style="float:right">
				<a href="javascript:printCertificateMulti()" class="btn btn-default btn-sm"><i class="glyphicon glyphicon-print"></i> <spring:message code="button.print.certi"/></a>
				<a href="javascript:excelDownload()" class="btn btn-primary btn-sm"><i class="fa fa-file-excel-o fa-fw"></i> <spring:message code="student.title.student.list.complete"/> </a>
			</div><br/>
		</div>
	</div>
	<%-- <p><spring:message code="student.title.student.complete.info"/></p> --%>

	<div id="studentList" style="margin-top:5px;">
		<table summary="<spring:message code="student.title.student.manage"/>" class="table table-bordered wordbreak">
			<colgroup>
				<col style="width:20px"/>
				<col style="width:auto"/>
				<col style="width:auto"/>
				<col style="width:auto"/>
				<col style="width:auto"/>
				<col style="width:auto"/>
				<col style="width:auto"/>
				<col style="width:auto"/>
				<col style="width:auto"/>
				<col style="width:72px"/>
			</colgroup>
			<thead>
			<tr>
				<th scope="col"></th>
				<th scope="col"><spring:message code="common.title.no"/></th>
				<th scope="col"><spring:message code="student.title.student.decls"/></th>
				<th scope="col"><spring:message code="user.title.userinfo.name"/></th>
				<th scope="col"><spring:message code="user.title.userinfo.userid"/></th>
				<th scope="col"><spring:message code="student.title.student.getscore"/></th>
				<th scope="col"><spring:message code="student.title.student.scoretop.user"/></th>
				<th scope="col"><spring:message code="student.title.student.completeno"/></th>
				<th scope="col"><spring:message code="common.title.stats"/></th>
				<th scope="col"><spring:message code="common.title.manage"/></th>
			</tr>
			</thead>
			<tbody>
				<tr>
					<td colspan="10"><spring:message code="student.message.student.nodata"/></td>
				</tr>
			</tbody>
		</table>
	</div>

	</form>

	<form id="studentFrom" name="studentFrom" onsubmit="return false">
	<input type="hidden" name="crsCreCd" value="${studentVO.crsCreCd}"/>
	<input type="hidden" name="stdNo" id="stdNo"/>
	<input type="hidden" name="enrlSts"/>
	<input type="hidden" name="userNm"/>
	<input type="hidden" name="startDate"/>
	<input type="hidden" name="endDate"/>
	<input type="hidden" name="deptCd"/>
	<input type="hidden" name="reshAnsrYn"/>
	<input type="hidden" name="dateSearchType" value="CPLT"/>
	<input type="submit" value="submit" style="display:none" />
	</form>

<script type="text/javascript">

	var ItemDTO = new Object();
	var nextFunc ="";

	$(document).ready(function() {
		$("._enterBind").bind("keydown", eventForSearch);

		function eventForSearch(event) {
			if (event.keyCode == '13') {
				alert('enter..');
				listStudent(1);
			}
		}

		ItemDTO.crsCreCd = '${studentVO.crsCreCd}';
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
	 * 수강생 목록 조회
	 */
	function listStudent(page) {
		ItemDTO.curPage = page;
		var f = document.Search;
		var crsCreCd = ItemDTO.crsCreCd;
		var enrlSts = $("#enrlSts option:selected").val();
		var userNm = $("#searchKey").val();
		var startDate = $("#startDate").val();
		var endDate = $("#endDate").val();
		var listScale = $("#listScale option:selected").val();
		var declsNo = $("#declsNo option:selected").val();

		$("#studentList")
		.load( cUrl("/mng/std/student/listCompleteStudent"), {
			"crsCreCd" : ItemDTO.crsCreCd,
			"enrlSts" : enrlSts,
			"userNm" : userNm,
			"startDate" : startDate,
			"endDate" : endDate,
			"dateSearchType" : 'CPLT',
			"declsNo" : declsNo,
			"sortKey" : ItemDTO.sortKey,
			"curPage" : ItemDTO.curPage,
			"listScale" : listScale },
			listStudentCallback
		);
	}


	/**
	 * 수강관리 수강생 리스트 콜백
	 */
	function listStudentCallback() {
		var iframeObj = parent.document.getElementById("subWorkFrame");
		parent.resizeIframe3(iframeObj, $(document).height());
	}


	function cancelStudent() {
		if(setStudent()) {
			var enrlSts = $("#enrlSts option:selected").val();
			if( enrlSts == 'S') {
				alert("<spring:message code="student.message.student.alert.cancelcomplete"/>");
				return;
			}  else if (enrlSts == 'C') {
				if(!confirm("<spring:message code="student.message.student.confirm.cancelcomplete1"/>"))
				return;
			} else if (enrlSts == 'F') {
				if(!confirm("<spring:message code="student.message.student.confirm.cancelcomplete2"/>"))
				return;
			}
			process("editCancelComplete");
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
		$("#stdNo").val(strs);
		return true;
	}


	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		//if(!validate(document.studentFrom)) return;
		$('#studentFrom').attr("action", "/mng/std/student/" + cmd);
		$('#studentFrom').ajaxSubmit(processCallback);
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

	function checkCompleteStudent() {
		if(setStudent()){
			if(confirm("<spring:message code="student.message.student.confirm.docomplete"/>")) {
				process("edutCheckComplete");
			}
		}
		return;
	}

	/**
	* 성적 우수자 선정
	*/
	function topStudent(checkbox) {
		var crsCreCd = ItemDTO.crsCreCd;
		if (checkbox.checked == false){
			$.getJSON( cUrl("/mng/std/student/clearTopStudent"),		// url
					{ 
					  "stdNo" : checkbox.value
					},			// params
					topStudentCallback				// callback function
				);
			displayWorkProgress();
		}else{
			$.getJSON( cUrl("/mng/std/student/chooseTopStudent"),		// url
					{
					  "stdNo" : checkbox.value
					},			// params
					topStudentCallback				// callback function
				);
			displayWorkProgress();
		}
	}

	/**
	* 성적 우수자 선정 callback
	*/
	function topStudentCallback(resultDTO) {
		alert(resultDTO.message);
		if(resultDTO.result >= 0) {
			// 정상 처리
			listStudent(ItemDTO.curPage);
		} else {
			// 비정상 처리
		}
		closeWorkProgress();
	}

	function printCertification(crsCreCd, stdNo) {
		var url = cUrl("/mng/std/student/viewPrintCerti?studentVO.crsCreCd="+crsCreCd+"${AMPERSAND}studentVO.stdNo="+stdNo);
		var certiPrint = window.open(url, 'certiPrint','width=800, height=830, top=10, left=10, scrollbars=1');
	}

	function autoCompleteStudent() {
		if(confirm("<spring:message code="student.message.student.confirm.autocomplete"/>")) {
			var crsCreCd = ItemDTO.crsCreCd;
			$.getJSON( cUrl("/mng/std/student/editAutoComplete"),		// url
					{ 
					  "crsCreCd" : crsCreCd
					},			// params
					autoCompleteStudentCallback				// callback function
				);
			displayWorkProgress();
		}
		return;
	}

	function autoCompleteStudentCallback(resultDTO) {
		alert(resultDTO.message);
		if(resultDTO.result >= 0) {
			// 정상 처리
			listStudent(ItemDTO.curPage);
		} else {
			// 비정상 처리
		}
		closeWorkProgress();
	}

	function excelDownloadComplete() {
		// download용 iframe이 없으면 만든다.


		if ( $("#_m_download_iframe").length == 0 ) {
			iframeHtml =
				'<iframe id="_m_download_iframe" name="_m_download_iframe" style="visibility: none; display: none;"></iframe>';
			$("body").append(iframeHtml);
		}

		var f = document.Search;
		f.chkAll.checked = false;
		var crsCreCd = ItemDTO.crsCreCd;
		var enrlSts = $("#enrlSts option:selected").val();
		var userNm = $("#searchKey").val();
		var startDate = $("#startDate").val();
		var endDate = $("#endDate").val();

		var deptCd = "";

		if(SearchdeptCd.getSelectedValue() == ""){
			deptCd = SearchparDeptCd.getSelectedValue();
		}else{
			deptCd = SearchdeptCd.getSelectedValue();
		}

		// 폼에 action을 설정하고 submit시킨다.
		$("#studentFrom").attr('target', '_m_download_iframe');
		$("#studentFrom").attr("action", "/mng/std/student/listExcelDownloadComplete");

		$("#studentForm").find('input[name$=enrlSts]').val(enrlSts);
		$("#studentForm").find('input[name$=crsCreCd]').val(crsCreCd);
		$("#studentForm").find('input[name$=userNm]').val(userNm);
		$("#studentForm").find('input[name$=startDate]').val(startDate);
		$("#studentForm").find('input[name$=endDate]').val(endDate);
		$("#studentForm").find('input[name$=reshAnsrYn]').val(reshAnsrYn);
		$("#studentForm").find('input[name$=deptCd]').val(deptCd);

		$("#studentFrom").submit();
		$("#studentFrom").removeAttr('target');
	}

	function viewStudent(stdNo){
		var addContent  = "<iframe id='addStudentFrame' name='addStudentFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/mng/std/student/viewStudentPop"/>"
			+ "?stdNo="+stdNo+"'/>";
		parent.modalBox.clear();
		parent.modalBox.addContents(addContent);
		parent.modalBox.resize(800, 450);
		parent.modalBox.setTitle("<spring:message code="student.title.student.detailinfo"/>");
		parent.modalBox.show();
	}

	// 검색 창에 상태 값 selectBox 변경 처리 시 호출
	function changeStatus() {
		//-- 버튼처리

		listStudent(1);
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
			+ "?msgDivCd="+msgDivCd+"&amp;>logMsgTransLogVO.stdNoList="+userList+"'/>";
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

	function viewReport(str) {
		var url = "${reportUrl}?rex_rptname="+str+"${AMPERSAND}crsCreCd=${studentVO.crsCreCd}";
		//var url = "/mng/std/student/?cmd=viewReport&crsCreCd=${studentVO.crsCreCd}&reportType="+str;
		var option = "width=830, height=640, toolbar=no, menubar=no, scrollbars=no, resizable=no, copyhistory=no";
		var reportWin = window.open(url,'reportWin', option);
		reportWin.focus();
	}

	function printCertificate(stdNo) {
		var url = "/mng/std/student/printCertification?studentVO.stdNo="+stdNo;
		var option = "width=830, height=640, toolbar=no, menubar=no, scrollbars=no, resizable=no, copyhistory=no";
		var reportWin = window.open(url,'reportWin', option);
		reportWin.focus();
	}

	function printCertificateMulti() {
		if(setStudent()) {
			var stdNo = $(':input:hidden[name=studentVO\\.stdNo]').val();
			var url = "/mng/std/student/printCertificationMulti?studentVO.crsCreCd=${studentVO.crsCreCd}&studentVO.stdNo="+stdNo;

			if ( $("#_m_pdf_iframe").length == 0 ) {
				iframeHtml =
					'<iframe id="_m_pdf_iframe" name="_m_pdf_iframe" style="visibility: none; display: none;"></iframe>';
				$("body").append(iframeHtml);
			}
			$("#_m_pdf_iframe").attr("src",url);
		}
	}

	function setSortKey(sortKey) {
		ItemDTO.sortKey = sortKey;
		listStudent(1);
	}

	// 수강생 명단
	function excelDownload(){
		var addContent  = "<iframe id='studentInfoFrame' name='studentInfoFrame' width='100%' height='100%' "
					+ "frameborder='0' scrolling='auto' src='<c:url value="/mng/std/student/excelDownloadPop"/>"
					+ "?downloadType=COMPLETE&amp;crsCreCd="+ItemDTO.crsCreCd+"'/>";
		parent.modalBox.clear();
		parent.modalBox.addContents(addContent);
		parent.modalBox.resize(500, 340);
		parent.modalBox.setTitle("<spring:message code="student.title.student.list.complete"/>");
		parent.modalBox.show();
	}
</script>
