<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="gubun" value="${createCourseForm.gubun}"/>
<c:set var="createCourseVO" value="${createCourseForm.createCourseVO}"/>
<c:set var="courseVO" value="${createCourseForm.courseVO}"/>


		<form id="createCourseFrom" name="createCourseFrom" onsubmit="return false">
		<input type="hidden" name="crsCreCd" />
		<div id="viewCreateCourse" style="display:block">
			<table summary="<spring:message code="course.title.createcourse.manage"/>" class="table_dtl" style="width:100%" align="center">
				<colgroup>
					<col style="width:20%"/>
					<col style="width:30%"/>
					<col style="width:20%"/>
					<col style="width:30%"/>
				</colgroup>
				<tr height="35">
					<th class="top" scope="row"><spring:message code="course.title.course.name"/></th>
					<td class="top">
						${createCourseVO.crsCreNm}
					</td>
					<th class="top" scope="row"><spring:message code="course.title.createcourse.creyear"/> / <spring:message code="course.title.createcourse.creterm"/></th>
					<td class="top">
						${createCourseVO.creYear}<spring:message code="common.title.year"/> / ${createCourseVO.creTerm}
					</td>
				</tr>
				<tr height="35">
					<th scope="row"><spring:message code="course.title.createcourse.duration.aplc"/></th>
					<td>
						${createCourseVO.enrlAplcStartDttm} ~ ${createCourseVO.enrlAplcEndDttm}
					</td>
					<th scope="row"><spring:message code="course.title.createcourse.duration.edu"/></th>
					<td>
						<c:if test="${courseVO.crsOperType eq 'R'}">
						${createCourseVO.enrlStartDttm} ~ ${createCourseVO.enrlEndDttm}
						</c:if>
						<c:if test="${courseVO.crsOperType eq 'S'}">
							<span class='red'><spring:message code="course.message.createcourse.auto.audit"/></span>
						</c:if>
					</td>
				</tr>
			</table>
			<div style="width:100%; height:25px; float:left">
				<div style="float:right;margin-top:3px;">
					<meditag:button value="button.edit" title="button.edit" func="editFormShow()" />
					<meditag:button value="button.list" title="button.list" func="goList();" />
				</div>
			</div>
		</div>
		<div id="editCreateCourse" style="display:none">
			<input type="hidden" name="stuCnt" />
			<input type="hidden" name="sbjCnt" />
			<input type="hidden" name="crsCd" />
			<input type="hidden" name="crsCreCd" />
			<table summary="<spring:message code="course.title.createcourse.manage"/>" class="table_dtl" style="width:100%" align="center">
				<colgroup>
					<col style="width:17%"/>
					<col style="width:33%"/>
					<col style="width:17%"/>
					<col style="width:33%"/>
				</colgroup>
				<tr height="35">
					<th class="top" scope="row"><label for="crsCreNm_${creId}"><spring:message code="course.title.course.name"/></label></th>
					<td class="top">
						<input type="text" style="width:96%" dispName="<spring:message code="course.title.course.name"/>" maxlength="50" isNull="N"  name="crsCreNm" onfocus="this.select()" class="txt" id="crsCreNm"/>&nbsp;
					</td>
					<th class="top" scope="row"><label for="creYear"><spring:message code="course.title.createcourse.creyear"/></label>/<label for="creTerm"><spring:message code="course.title.createcourse.creterm"/></label></th>
					<td class="top">
						<input type="text" style="width:30px;" dispName="<spring:message code="course.title.createcourse.creyear"/>" maxlength="4" isNull="N" lenCheck="4" dataType="number" name="creYear" onfocus="this.select()" class="txt" id="creYear" readonly="true"/> 년
						<input type="text" style="width:80px;" dispName="<spring:message code="course.title.createcourse.creterm"/>" maxlength="50" isNull="N" lenCheck="50" name="creTerm" onfocus="this.select()" class="txt" id="creTerm"/>
					</td>
				</tr>
				<tr height="33">
					<th scope="row"><spring:message code="course.title.createcourse.duration.aplc"/></th>
					<td>
						<input type="text" style="width:70px;" dispName="<spring:message code="course.title.createcourse.aplc.startdate"/>" maxlength="50" isNull="N" lenCheck="50" name="enrlAplcStartDttm" id="enrlAplcStartDttm" readonly="true" class="txt"/>
						~
						<input type="text" style="width:70px;" dispName="<spring:message code="course.title.createcourse.aplc.enddate"/>" maxlength="50" isNull="N" lenCheck="50" name="enrlAplcEndDttm" id="enrlAplcEndDttm" readonly="true" class="txt"/>
						<meditag:datepicker name1="enrlAplcStartDttm" name2="enrlAplcEndDttm" />
					</td>
					<c:if test="${courseVO.crsOperType eq 'R'}">
					<th scope="row"><spring:message code="course.title.createcourse.duration.edu"/></th>
					<td>
						<input type="text" style="width:70px;" dispName="<spring:message code="course.title.createcourse.edu.startdate"/>" maxlength="50" isNull="N" lenCheck="50" name="enrlStartDttm" id="enrlStartDttm" readonly="true" class="txt"/>
						~
						<input type="text" style="width:70px;" dispName="<spring:message code="course.title.createcourse.edu.enddate"/>" maxlength="50" isNull="N" lenCheck="50" name="enrlEndDttm" id="enrlEndDttm" readonly="true" class="txt" />
						<meditag:datepicker name1="enrlStartDttm" name2="enrlEndDttm" />
					</td>
					</c:if>
					<c:if test="${courseVO.crsOperType eq 'S'}">
					<th scope="row"><spring:message code="course.title.createcourse.eduday"/></th>
					<td>
						<input type="hidden" name="enrlEndDttm" />
						<input type="hidden" name="enrlStartDttm" />
						<span class='red'><spring:message code="course.message.createcourse.auto.audit"/></span>
					</td>
					</c:if>
				</tr>
				<c:if test="${courseVO.crsOperMthd eq 'BL'}">
				<tr height="33">
					<th scope="row"><spring:message code="course.title.createcourse.duration.offedu"/></th>
					<td colspan="3">
						<input type="text" style="width:70px;" dispName="<spring:message code="course.title.createcourse.offedu.startdate"/>" maxlength="50" isNull="N" lenCheck="50" name="oflnStartDttm" id="oflnStartDttm" readonly="true" class="txt" />
						~
						<input type="text" style="width:70px;" dispName="<spring:message code="course.title.createcourse.offedu.enddate"/>" maxlength="50" isNull="N" lenCheck="50" name="oflnEndDttm" readonly="true" id="oflnEndDttm"  class="txt" />
						<meditag:datepicker name1="oflnStartDttm" name2="oflnEndDttm" />
					</td>
				</tr>
				</c:if>
				<c:if test="${courseVO.crsOperMthd eq 'ON' || courseVO.crsOperMthd eq 'BL'}">
				<tr height="33">
					<th scope="row"><spring:message code="course.title.createcourse.endday.audit"/></th>
					<td colspan="3">
						<c:if test="${courseVO.crsOperType eq 'R'}">
						<input type="text" style="width:70px;" dispName="<spring:message code="course.title.createcourse.endday.audit"/>" maxlength="50" isNull="N" lenCheck="50" name="auditEndDttm" id="auditEndDttm" readonly="true" class="txt" />
						<meditag:datepicker name1="auditEndDttm" />
						</c:if>
						<c:if test="${courseVO.crsOperType eq 'S'}">
							 <input type="hidden" name="auditEndDttm" />
							<span class='red'><spring:message code="course.message.createcourse.auto.audit" /></span>
						</c:if>
					</td>
				</tr>
				</c:if>
				<tr height="33">
					<th scope="row"><label for="cpltScore"><spring:message code="course.title.course.completescore"/></label></th>
					<td>
						<input type="text" style="width:30px;" dispName="<spring:message code="course.title.course.completescore"/>" maxlength="3" isNull="N" lenCheck="3" maxValue="100" dataType="number" name="cpltScore" onfocus="this.select()" class="inputNumber txt" id="cpltScore"/> <spring:message code="common.title.score"/>
					</td>
					<th scope="row"><spring:message code="course.title.course.stdcnt" /></th>
					<td>
						<div style="float:left">
						<select name="nopLimitYn" id="nopLimitYn" style="width:120px;" onChange="chgNopLimitYn();">
						<c:forEach var="item" items="${nopLimitList}">
							<option value="${item.codeCd}">${item.codeNm}</option>
						</c:forEach>
						</select>
						</div>
						<div id="enrlNopArea" style="float:left:display:hidden;">
						<input type="text" style="width:30px;" dispName="<spring:message code="course.title.course.stdcnt"/>" maxlength="9" dataType="number" isNull="N" lenCheck="9" name="enrlNop" onfocus="this.select()" class="inputNumber txt" id="enrlNop"/> <spring:message code="common.title.cnt.user"/>
						</div>
					</td>
				</tr>
				<tr height="33">
					<th scope="row"><spring:message code="course.title.createcourse.decls.cnt"/></th>
					<td>
						${createCourseVO.declsCnt}
						<input type="hidden" name="declsCnt" id="declsCnt"/>
					</td>
					<th scope="row"><label for="eduPrice"><spring:message code="course.title.course.edufee"/></label></th>
					<td>
						<input type="text" style="width:120px;" dispName="<spring:message code="course.title.course.edufee"/>" maxlength="10" dataType="number" isNull="N" lenCheck="10" name="eduPrice" onfocus="this.select()" class="inputNumber txt" id="eduPrice"/>
					</td>
				</tr>
				<tr height="33">
					<th scope="row"><label for="eduPrps"><spring:message code="course.title.course.scoreratio"/></label></th>
					<td colspan="3" style="padding:0px 0px 5px 5px;">
						<ul>
							<li style="padding-top:5px;width:200px;float:left"><span style="width:70px;display:inline-block"><label for="prgrRatio"><spring:message code="course.title.course.ratio.progress"/></label></span>
								<input type="text" style="width:30px;" dispName="<spring:message code="course.title.course.ratio.progress"/>" maxlength="3" dataType="number" isNull="N" lenCheck="3" name="prgrRatio" onfocus="this.select()" class="inputNumber txt" id="prgrRatio"/>%</li>
							<li style="padding-top:5px;width:200px;float:left"><span style="width:70px;display:inline-block"><label for="attdRatio"><spring:message code="course.title.course.ratio.attend"/></label></span>
								<input type="text" style="width:30px;" dispName="<spring:message code="course.title.course.ratio.attend"/>" maxlength="3" dataType="number" isNull="N" lenCheck="3" name="attdRatio" onfocus="this.select()" class="inputNumber txt" id="attdRatio"/>%</li>
							<li style="padding-top:5px;width:200px;float:left"><span style="width:70px;display:inline-block"><label for="examRatio"><spring:message code="course.title.course.ratio.exam"/></label></span>
								<input type="text" style="width:30px;" dispName="<spring:message code="course.title.course.ratio.exam"/>" maxlength="3" dataType="number" isNull="N" lenCheck="3" name="examRatio" onfocus="this.select()" class="inputNumber txt" id="examRatio"/>%</li>
							<li style="padding-top:5px;width:200px;float:left"><span style="width:70px;display:inline-block"><label for="forumRatio"><spring:message code="course.title.course.ratio.forum"/></label></span>
								<input type="text" style="width:30px;" dispName="<spring:message code="course.title.course.ratio.forum"/>" maxlength="3" dataType="number" isNull="N" lenCheck="3" name="forumRatio" onfocus="this.select()" class="inputNumber txt" id="forumRatio"/>%</li>
							<li style="padding-top:5px;width:200px;float:left"><span style="width:70px;display:inline-block"><label for="asmtRatio"><spring:message code="course.title.course.ratio.asmt"/></label></span>
								<input type="text" style="width:30px;" dispName="<spring:message code="course.title.course.ratio.asmt"/>" maxlength="3" dataType="number" isNull="N" lenCheck="3" name="asmtRatio" onfocus="this.select()" class="inputNumber txt" id="asmtRatio"/>%</li>
							<li style="padding-top:5px;width:200px;float:left"><span style="width:70px;display:inline-block"><label for="prjtRatio"><spring:message code="course.title.course.ratio.project"/></label></span>
								<input type="text" style="width:30px;" dispName="<spring:message code="course.title.course.ratio.project"/>" maxlength="3" dataType="number" isNull="N" lenCheck="3" name="prjtRatio" onfocus="this.select()" class="inputNumber txt" id="prjtRatio"/>%</li>
							<li style="padding-top:5px;width:200px;float:left"><span style="width:70px;display:inline-block"><label for="joinRatio"><spring:message code="course.title.course.ratio.join"/></label></span>
								<input type="text" style="width:30px;" dispName="<spring:message code="course.title.course.ratio.join"/>" maxlength="3" dataType="number" isNull="N" lenCheck="3" name="joinRatio" onfocus="this.select()" class="inputNumber txt" id="joinRatio"/>%</li>
							<li style="padding-top:5px;width:200px;float:left"><span style="width:70px;display:inline-block"><label for="etcRatio"><spring:message code="course.title.course.ratio.etc"/></label></span>
								<input type="text" style="width:30px;" dispName="<spring:message code="course.title.course.ratio.etc"/>" maxlength="3" dataType="number" isNull="N" lenCheck="3" name="etcRatio" onfocus="this.select()" class="inputNumber txt" id="etcRatio"/>%</li>
						</ul>
					</td>
				</tr>
			</table>

			<div style="width:100%; height:25px; float:left">
				<div style="float:right;margin-top:3px;">
					<c:if test="${gubun eq 'A'}">
					<meditag:button value="button.add" title="button.add" func="addCreateCourse()" />
					</c:if>
					<c:if test="${gubun eq 'E'}">
					<meditag:button value="button.edit" title="button.edit" func="editCreateCourse()" />
					<meditag:button value="button.delete" title="button.delete" func="delCreateCourse()" />
					</c:if>
					<meditag:button value="button.close" title="button.close" func="editFormHide()" />
				</div>
			</div>
		</div>
		<input type="submit" value="submit" style="display:none" />
		</form>
		<div class="tabbox" style="width:100%;float:left">
			<ul id="tab" class="tab">
				<li id="tab0"><span onMouseDown="onclickTab('0')"><spring:message code="course.title.createcourse.manage.enroll"/></span></li>
				<c:if test="${courseVO.crsOperMthd eq 'OF' || courseVO.crsOperMthd eq 'BL'}">
				<!--
					<li id="tab10"><span onMouseDown="onclickTab('10')"><spring:message code="course.title.createcourse.manage.eduno"/></span></li>
				 -->
				</c:if>
				<li id="tab1"><span onMouseDown="onclickTab('1')"><spring:message code="course.title.createcourse.manage.complete"/></span></li>
				<c:if test="${courseVO.crsOperMthd eq 'ON' || courseVO.crsOperMthd eq 'BL'}">
					<li id="tab2"><span onMouseDown="onclickTab('2')"><spring:message code="course.title.createcourse.manage.subject"/>(ON)</span></li>
				</c:if>
				<c:if test="${courseVO.crsOperMthd eq 'OF' || courseVO.crsOperMthd eq 'BL'}">
					<li id="tab3"><span onMouseDown="onclickTab('3')"><spring:message code="course.title.createcourse.manage.subject"/>(OF)</span></li>
				</c:if>
				<li id="tab4"><span onMouseDown="onclickTab('4')"><spring:message code="course.title.createcourse.manage.teacher"/></span></li>
				<c:if test="${courseVO.crsOperMthd eq 'OF' || courseVO.crsOperMthd eq 'BL'}">
				<!-- li id="tab5"><span onMouseDown="onclickTab('5')"><spring:message code="course.title.createcourse.manage.timetab"/></span></li -->
				</c:if>
				<c:if test="${courseVO.crsOperType eq 'R'}">
				<c:if test="${courseVO.crsOperMthd eq 'ON' || courseVO.crsOperMthd eq 'BL'}">
				<!--
				<li id="tab6"><span onMouseDown="onclickTab('6')"><spring:message code="course.title.createcourse.manage.exam"/></span></li>
				<li id="tab7"><span onMouseDown="onclickTab('7')"><spring:message code="course.title.createcourse.manage.asmt"/></span></li>
				<li id="tab9"><span onMouseDown="onclickTab('9')"><spring:message code="course.title.createcourse.manage.forum"/></span></li>
				-->
				</c:if>
				<li id="tab8"><span onMouseDown="onclickTab('8')"><spring:message code="course.title.createcourse.manage.score"/></span></li>
				</c:if>
			</ul>
		</div>
		<iframe name="subWorkFrame" id="subWorkFrame" frameborder="0" src="about:blank" scrolling="no" title="Sub Work Frame" style="width:100%;min-height:300px;"></iframe>

<script type="text/javascript">

	var ItemDTO = new Object();

	$(document).ready(function() {
		$('.inputNumber').inputNumber();	// 숫자만 입력되도록 설정.
		//-- 텝 초기 페이지 호출
		ItemDTO.crsCreCd = '${createCourseVO.crsCreCd}';
		ItemDTO.crsCreNm = '${createCourseVO.crsCreNm}';

//		onclickTab('0');

	});

	function onclickTab(tab) {
		resetIframe3(document.getElementById("subWorkFrame"));
		selectTab(tab);
		var url = {};
		url['0'] = "/mng/std/student/listFormEnrollStudent?studentVO.crsCreCd="+ItemDTO.crsCreCd;
		url['1'] = "/mng/std/student/listFormConfirmStudent?studentVO.crsCreCd="+ItemDTO.crsCreCd;
		url['2'] = "/mng/course/createCourse/subject/mainOnlineSubject?onlineSubjectVO.crsCreCd="+ItemDTO.crsCreCd;
		url['3'] = "/mng/course/createCourse/subject/mainOfflineSubject?offlineSubjectVO.crsCreCd="+ItemDTO.crsCreCd;
		url['4'] = "/mng/course/createCourse/teacher/teacherMain?teacherVO.crsCreCd="+ItemDTO.crsCreCd;
		url['5'] = "/CourseCreateCourseTimetableManage.do?cmd=mainTimetable${AMPERSAND}timetableVO.crsCreCd="+ItemDTO.crsCreCd;
		url['6'] = "/mng/lecture/exam/examMain?examVO.crsCreCd="+ItemDTO.crsCreCd;
		url['7'] = "/mng/lecture/assignment/asmtMain?assignmentVO.crsCreCd="+ItemDTO.crsCreCd;
		url['8'] = "/StudentSubjectEduResultManage.do?cmd=main${AMPERSAND}subjectEduResultVO.crsCreCd="+ItemDTO.crsCreCd;
		url['9'] = "/LectureResearchManage.do?cmd=mainResearch${AMPERSAND}researchVO.crsCreCd="+ItemDTO.crsCreCd;
		url['10'] = "/mng/std/student/listFormEduNoStudent?studentVO.crsCreCd="+ItemDTO.crsCreCd;
		url['11'] = "/StudentCarApplicationManage.do?cmd=mainCarApplication${AMPERSAND}carApplicationVO.crsCreCd="+ItemDTO.crsCreCd;
		url['12'] = "/StudentMealApplicationManage.do?cmd=listMealApplication${AMPERSAND}studentVO.crsCreCd="+ItemDTO.crsCreCd;

		document.getElementById('subWorkFrame').contentWindow.location.href=cUrl(url[tab]);
		//document.subWorkFrame.location.href = cUrl(url[tab]);
		//if(isNotNull(url[tab])) $('#subWorkFrame').load(cUrl(url[tab]), callbackOnClickTab(tab));
	}

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		if(!validate(document.createCourseFrom)) return;

		$('#createCourseFrom').attr("action","/mng/course/createCourse/"+cmd);
		if(cmd == 'editCreateCourse') {
			$('#createCourseFrom').ajaxSubmit(processEditCallback);
		} else if(cmd == 'deleteCreateCourse') {
			$('#createCourseFrom').ajaxSubmit(processDeleteCallback);
		}
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processEditCallback(resultDTO) {
		alert(resultDTO.message);
		if(resultDTO.result >= 0) {
			// 정상 처리
			document.location.reload();
		} else {
			// 비정상 처리
		}
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processDeleteCallback(resultDTO) {
		alert(resultDTO.message);
		if(resultDTO.result >= 0) {
			// 정상 처리
			goList();
		} else {
			// 비정상 처리
		}
	}


	function goList() {
		document.location.href = cUrl('/mng/course/createCourse/listCreateCourse')+'?createCourseVO.creYear=${createCourseVO.creYear}${AMPERSAND}createCourseVO.crsCd=${createCourseVO.crsCd}';
	}

	/**
	 * 과정 개설 등록 폼 보이기
	 */
	function editFormShow() {
		$("#viewCreateCourse").hide();
		$("#editCreateCourse").show();
		var iframeObj = parent.document.getElementById("addCreateFrame");
		parent.resizeIframe3(iframeObj, $(document).height());
	}

	/**
	 * 과정 개설 등록
	 */
	function editFormHide() {
		$("#viewCreateCourse").show();
		$("#editCreateCourse").hide();
		var iframeObj = parent.document.getElementById("addCreateFrame");
		parent.resizeIframe3(iframeObj, $(document).height());
	}

	/**
	 * 과정 개설 수정
	 */
	function editCreateCourse() {

		process("editCreateCourse");
	}

	/**
	 * 과정 개설 삭제
	 */
	function delCreateCourse() {
		var f = document.createCourseForm;
		if(f["createCourseVO.stuCnt"].value > 0) {
			alert("<spring:message code="course.message.createcourse.alert.delete1"/>");
			return;
		}
		if(f["createCourseVO.sbjCnt"].value > 0) {
			alert("<spring:message code="course.message.createcourse.alert.delete2"/>");
			return;
		}
		if(confirm("<spring:message code="course.message.createcourse.confirm.delete"/>")) {
			process("deleteCreateCourse");	// cmd
		} else {
			return;
		}
	}

	//인원제한 able <-> disable
	function chgNopLimitYn(){
		var nopLimitYn = createCourseFormcreateCourseVO_nopLimitYn.getSelectedValue();
		if(nopLimitYn == "Y"){
			$('#enrlNop').attr("disabled", false);
		}else{
			$('#enrlNop').attr("disabled", true);
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

</script>