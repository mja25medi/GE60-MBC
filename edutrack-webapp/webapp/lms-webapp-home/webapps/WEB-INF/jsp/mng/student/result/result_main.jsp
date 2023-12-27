<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="eduResultVO" value="${vo}"/>
	<br/>
	<form name="Search" id="Search" onsubmit="return false">
	<div class="row">
		<div class="col-lg-12">
			<div class="input-group" style="display: none; float:left">
				<select name="declsNo" id="declsNo" onchange="listStudent(1)" class="form-control input-sm">
					<option value=""><spring:message code="course.title.decls.all"/></option>
					<c:forEach var="decls" items="${creCrsDeclsList}">
					<option value="${decls.declsNo}">${decls.declsNo}</option>
					</c:forEach>
				</select>
			</div>
			
			<div class="input-group" style="float:left; width: 7%; margin-right: 5px;">
				<select name="enrlSts" id="enrlSts" onchange="listStudent(1)" class="form-control input-sm">
					<option value="">수강상태</option>
					<option value="S">수강승인</option>
					<option value="C">수료</option>
					<option value="F">미수료</option>
				</select>
			</div>
			
			<div class="input-group" style="float:left; width: 10%">
				<input type="text" name="searchKey" id="searchKey" class="_enterBind form-control input-sm" placeholder="<spring:message code="user.title.userinfo.name"/>"/>
				<span class="input-group-addon" onclick="listStudent()" style="cursor:pointer">
					<i class="fa fa-search"></i>
				</span>
			</div>
			<div style="float:right; margin-left: 3px;">
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
				<a href="javascript:messageForm('SMS')" class="btn btn-info btn-sm"><spring:message code="button.sms"/></a>
				</c:if>
				<c:if test="${MSG_EMAIL eq 'Y' }">
				<a href="javascript:messageForm('EMAIL')" class="btn btn-info btn-sm"><spring:message code="button.email"/></a>
				</c:if>
				<c:if test="${MSG_NOTE eq 'Y' }">
				<a href="javascript:messageForm('MSG')" class="btn btn-info btn-sm"><spring:message code="button.note"/></a>
				</c:if>
				<%-- <a href="javascript:viewWorkLog()" class="btn btn-default btn-sm"><spring:message code="log.title.common.view.log"/></a> --%>
				<%-- <a href="javascript:addAutoResult()" class="btn btn-primary btn-sm"><spring:message code="button.save.score.auto"/></a>
				<a href="javascript:addResultAll()" class="btn btn-primary btn-sm"><spring:message code="button.add.all"/></a> --%>
				<%-- <a href="javascript:scoreEclt()" class="btn btn-primary btn-sm"><spring:message code="button.score.excellent"/></a> --%>
			</div>
		</div>
	</div>
	<div class="row" style="margin-top:5px;">
		<div class="col-lg-12">
			<div style="float:right">
				<!-- <a href="javascript:printCertificateMulti()" class="btn btn-default btn-sm"><i class="glyphicon glyphicon-print"></i> 수료증출력</a> -->
				<a href="javascript:excelDownload();" class="btn btn-primary btn-sm"><i class="fa fa-file-excel-o fa-fw"></i>엑셀 다운로드</a>
			</div><br/>
		</div>
	</div>
	<div id="studentList" style="margin-top:5px;">
		<table summary="<spring:message code="student.title.result.manage"/>" class="table table-bordered wordbreak">
			<c:set var="colspan" value="5"/>
			<colgroup>
				<col style="width:30px;"/>
				<!-- <col style="width:50px;"/> -->
				<col style="width:auto;"/>
				<col style="width:auto;"/>
				<!-- <col style="width:50px;"/> -->
				<c:if test="${createCourseVO.prgrRatio > 0}">
				<col style="width:80px;"/>
				<c:set var="colspan" value="${colspan +1}"/>
				</c:if>
				<c:if test="${createCourseVO.attdRatio > 0}">
				<col style="width:80px;"/>
				<c:set var="colspan" value="${colspan +1}"/>
				</c:if>
				<c:if test="${createCourseVO.examRatio > 0}">
				<col style="width:80px;"/>
				<c:set var="colspan" value="${colspan +1}"/>
				</c:if>
				<c:if test="${createCourseVO.semiExamRatio > 0}">
				<col style="width:80px;"/>
				<c:set var="colspan" value="${colspan +1}"/>
				</c:if>
				<c:if test="${createCourseVO.asmtRatio > 0}">
				<col style="width:80px;"/>
				<c:set var="colspan" value="${colspan +1}"/>
				</c:if>
				<c:if test="${createCourseVO.forumRatio > 0}">
				<col style="width:80px;"/>
				<c:set var="colspan" value="${colspan +1}"/>
				</c:if>
				<c:if test="${createCourseVO.joinRatio > 0}">
				<col style="width:80px;"/>
				<c:set var="colspan" value="${colspan +1}"/>
				</c:if>
				<c:if test="${createCourseVO.etcRatio > 0}">
				<col style="width:80px;"/>
				<c:set var="colspan" value="${colspan +1}"/>
				</c:if>
				<c:if test="${createCourseVO.etcRatio2 > 0}">
				<col style="width:80px;"/>
				<c:set var="colspan" value="${colspan +1}"/>
				</c:if>
				<c:if test="${createCourseVO.etcRatio3 > 0}">
				<col style="width:80px;"/>
				<c:set var="colspan" value="${colspan +1}"/>
				</c:if>
				<c:if test="${createCourseVO.etcRatio4 > 0}">
				<col style="width:80px;"/>
				<c:set var="colspan" value="${colspan +1}"/>
				</c:if>
				<c:if test="${createCourseVO.etcRatio5 > 0}">
				<col style="width:80px;"/>
				<c:set var="colspan" value="${colspan +1}"/>
				</c:if>
				<col style="width: auto"/>
				<!-- <col style="width:50px;"/> -->
			</colgroup>
			<thead>
				<tr>
					<th scope="col"></th>
					<%-- <th scope="col"><spring:message code="student.title.student.decls"/></th> --%>
					<th scope="col"><spring:message code="user.title.userinfo.name"/></th>
					<th scope="col"><spring:message code="user.title.userinfo.userid"/></th>
					<%-- <th scope="col"><spring:message code="student.title.result.score.excellent"/></th> --%>
					<c:if test="${createCourseVO.prgrRatio > 0}">
					<th scope="col"><spring:message code="course.title.course.ratio.progress"/><br>(${createCourseVO.prgrRatio}%)</th>
					</c:if>
					<c:if test="${createCourseVO.attdRatio > 0}">
					<th scope="col"><spring:message code="course.title.course.ratio.attend"/><br>(${createCourseVO.attdRatio}%)</th>
					</c:if>
					<c:if test="${createCourseVO.examRatio > 0}">
					<th scope="col"><spring:message code="course.title.course.ratio.exam"/><br>(${createCourseVO.examRatio}%)</th>
					</c:if>
					<c:if test="${createCourseVO.semiExamRatio > 0}">
					<th scope="col"><spring:message code="course.title.course.ratio.semiExam"/><br>(${createCourseVO.semiExamRatio}%)</th>
					</c:if>
					<c:if test="${createCourseVO.asmtRatio > 0}">
					<th scope="col"><spring:message code="course.title.course.ratio.asmt"/><br>(${createCourseVO.asmtRatio}%)</th>
					</c:if>
					<c:if test="${createCourseVO.forumRatio > 0}">
					<th scope="col"><spring:message code="course.title.course.ratio.forum"/><br>(${createCourseVO.forumRatio}%)</th>
					</c:if>
					<c:if test="${createCourseVO.joinRatio > 0}">
					<th scope="col"><spring:message code="course.title.course.ratio.join"/><br>(${createCourseVO.joinRatio}%)</th>
					</c:if>
					<c:if test="${createCourseVO.etcRatio > 0}">
					<th scope="col">${createCourseVO.etcNm}<br>(${createCourseVO.etcRatio}%)</th>
					</c:if>
					<c:if test="${createCourseVO.etcRatio2 > 0}">
					<th scope="col">${createCourseVO.etcNm2}<br>(${createCourseVO.etcRatio2}%)</th>
					</c:if>
					<c:if test="${createCourseVO.etcRatio3 > 0}">
					<th scope="col">${createCourseVO.etcNm3}<br>(${createCourseVO.etcRatio3}%)</th>
					</c:if>
					<c:if test="${createCourseVO.etcRatio4 > 0}">
					<th scope="col">${createCourseVO.etcNm4}<br>(${createCourseVO.etcRatio4}%)</th>
					</c:if>
					<c:if test="${createCourseVO.etcRatio5 > 0}">
					<th scope="col">${createCourseVO.etcNm5}<br>(${createCourseVO.etcRatio5}%)</th>
					</c:if>
					<th scope="col"><spring:message code="student.title.result.totalscore"/><br>(100%)</th>
					<%-- <th scope="col"><spring:message code="common.title.add"/></th> --%>
					<th scope="col">상태</th>
					<th scope="col">수료번호</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td colspan="${colspan+2}"><spring:message code="student.message.student.nodata"/></td>
				</tr>
			</tbody>
		</table>
	</div>
	<input type="submit" value="submit" style="display:none" />
	</form>


	<form id="eduResultForm" name="eduResultForm">
		<input type="hidden" name="crsCreCd" value="${eduResultVO.crsCreCd}" />
		<input type="submit" value="submit" style="display:none" />
		<input type="hidden" name="stdNo" id="stdNo" value="">
	</form>
	
<script type="text/javascript">
	var ItemDTO = new Object();
	$(document).ready(function() {
		$("._enterBind").bind("keydown", eventForSearch);
		function eventForSearch(event) {
			if (event.keyCode == '13') {
				listStudent(1);
			}
		}
		ItemDTO.crsCreCd = '${eduResultVO.crsCreCd}';
		ItemDTO.sortKey = "";
		listStudent(1);
		initResultBatch();
	});

	function checkAll() {
	    var state=$('input[name=chkAll]:checked').size();
	    if(state==1){
	   		$("#Search input[name='sel']").prop("checked",true);
	    }else{
	    	$("#Search input[name='sel']").prop("checked",false);
	    }
	}

	/**
	* resize
	*/
	function parentResize() {
		var iframeObj = parent.document.getElementById("subWorkFrame");
		parent.resizeIframe3(iframeObj, $(document).height());
	}

	/**
	 * 개설 과정 목록 조회
	 * 개설 년도별 개설 과정 목록
	 */
	function listStudent(page) {
		ItemDTO.curPage = page;
		var f = document.Search;
		var crsCreCd = ItemDTO.crsCreCd;
		var userNm = f.searchKey.value;
		var enrlSts = f.enrlSts.value;
		var listScale = f.listScale[f.listScale.selectedIndex].value;
		var declsNo = f.declsNo[f.declsNo.selectedIndex].value;

		$("#studentList").load( cUrl("/mng/std/eduResult/listResult"),		// url
				{ 
				  "crsCreCd" : crsCreCd,
				  "userNm" : userNm,
				  "declsNo" : declsNo,
				  "sortKey" : ItemDTO.sortKey,
				  "curPage" : ItemDTO.curPage,
				  "listScale" : listScale,
				  "enrlSts" : enrlSts
				},			// params
				listStudentCallback				// callback function
			);
	}


	/**
	 * 수강관리 수강생 리스트 콜백
	 */
	function listStudentCallback() {
		parentResize();
		$('.inputNumber').inputNumber();	// 숫자만 입력되도록 설정.
	}

	function chkNumber(param,maxVlaue){
		var score =  param.value;
		var scoreAr;
		if(score != ""){
			if(score.indexOf(".") != -1){
				scoreAr = score.split(".");
				if(scoreAr[1].length > 1){
					param.value = parseFloat(score).toFixed(1);
				} else {
					param.value = parseFloat(score);
				}
			} else {
				param.value = Number(score);
			}
		}
		if(param.value > maxVlaue){
			param.value = maxVlaue;
		}
	}


	function checkTotalScore(no) {
		var ObjPrgrScore = document.getElementsByName("prgrScore");
		var ObjAttdScore = document.getElementsByName("attdScore");
		var ObjExamScore = document.getElementsByName("examScore");
		var ObjSemiExamScore = document.getElementsByName("semiExamScore");
		var ObjAsmtScore = document.getElementsByName("asmtScore");
		var ObjForumScore = document.getElementsByName("forumScore");
		var ObjJoinScore = document.getElementsByName("joinScore");
		var ObjEtcScore = document.getElementsByName("etcScore");
		var ObjEtcScore2 = document.getElementsByName("etcScore2");
		var ObjEtcScore3 = document.getElementsByName("etcScore3");
		var ObjEtcScore4 = document.getElementsByName("etcScore4");
		var ObjEtcScore5 = document.getElementsByName("etcScore5");
		var ObjTotScore = document.getElementsByName("totScore");

		chkNumber(ObjPrgrScore[no],${createCourseVO.prgrRatio});
		chkNumber(ObjAttdScore[no],${createCourseVO.attdRatio});
		chkNumber(ObjExamScore[no],${createCourseVO.examRatio});
		chkNumber(ObjExamScore[no],${createCourseVO.semiExamRatio});
		chkNumber(ObjAsmtScore[no],${createCourseVO.asmtRatio});
		chkNumber(ObjForumScore[no],${createCourseVO.forumRatio});
		chkNumber(ObjJoinScore[no],${createCourseVO.joinRatio});
		chkNumber(ObjEtcScore[no],${createCourseVO.etcRatio});
		chkNumber(ObjEtcScore2[no],${createCourseVO.etcRatio2});
		chkNumber(ObjEtcScore3[no],${createCourseVO.etcRatio3});
		chkNumber(ObjEtcScore4[no],${createCourseVO.etcRatio4});
		chkNumber(ObjEtcScore5[no],${createCourseVO.etcRatio5});
/*
		var oldTotalScore = parseInt(ObjTotScore[no].value,10);
		var newTotalScore = parseInt(ObjPrgrScore[no].value,10) +
							parseInt(ObjAttdScore[no].value,10) +
		                    parseInt(ObjExamScore[no].value,10) +
		                    parseInt(ObjAsmtScore[no].value,10) +
		                    parseInt(ObjForumScore[no].value,10) +
		                    parseInt(ObjJoinScore[no].value,10) +
		                    parseInt(ObjEtcScore[no].value,10) ;
 */
	if(ObjPrgrScore[no].value == "")	ObjPrgrScore[no].value = "0.0";
	if(ObjAttdScore[no].value == "")	ObjAttdScore[no].value = "0.0";
	if(ObjExamScore[no].value == "")	ObjExamScore[no].value = "0.0";
	if(ObjSemiExamScore[no].value == "")	ObjSemiExamScore[no].value = "0.0";
	if(ObjAsmtScore[no].value == "")	ObjAsmtScore[no].value = "0.0";
	if(ObjForumScore[no].value == "")	ObjForumScore[no].value = "0.0";
	if(ObjJoinScore[no].value == "")	ObjJoinScore[no].value = "0.0";
	if(ObjEtcScore[no].value == "")	ObjEtcScore[no].value = "0.0";
	if(ObjEtcScore2[no].value == "")	ObjEtcScore[no].value = "0.0";
	if(ObjEtcScore3[no].value == "")	ObjEtcScore[no].value = "0.0";
	if(ObjEtcScore4[no].value == "")	ObjEtcScore[no].value = "0.0";
	if(ObjEtcScore5[no].value == "")	ObjEtcScore[no].value = "0.0";




	var oldTotalScore = parseFloat(parseFloat(ObjTotScore[no].value).toFixed(1));
	/*
	var newTotalScore =parseFloat(parseFloat(ObjPrgrScore[no].value).toFixed(1)) +
						parseFloat(parseFloat(ObjAttdScore[no].value).toFixed(1)) +
						parseFloat(parseFloat(ObjExamScore[no].value).toFixed(1)) +
						parseFloat(parseFloat(ObjAsmtScore[no].value).toFixed(1)) +
						parseFloat(parseFloat(ObjForumScore[no].value).toFixed(1)) +
						parseFloat(parseFloat(ObjJoinScore[no].value).toFixed(1)) +
						parseFloat(parseFloat(ObjEtcScore[no].value).toFixed(1)) ;
	*/
	var newTotalScore = parseFloat(ObjPrgrScore[no].value) +
									parseFloat(ObjAttdScore[no].value) +
									parseFloat(ObjExamScore[no].value) +
									parseFloat(ObjSemiExamScore[no].value) +
									parseFloat(ObjAsmtScore[no].value) +
									parseFloat(ObjForumScore[no].value) +
									parseFloat(ObjJoinScore[no].value) +
									parseFloat(ObjEtcScore[no].value) +
									parseFloat(ObjEtcScore2[no].value) +
									parseFloat(ObjEtcScore3[no].value) +
									parseFloat(ObjEtcScore4[no].value) +
									parseFloat(ObjEtcScore5[no].value);

		//-- 성적 변화가 있으면 스타일 색상 변경
        if(oldTotalScore != newTotalScore) {
			ObjPrgrScore[no].style.background = "#FEDFDF";
			ObjAttdScore[no].style.background = "#FEDFDF";
			ObjExamScore[no].style.background = "#FEDFDF";
			ObjSemiExamScore[no].style.background = "#FEDFDF";
			ObjAsmtScore[no].style.background = "#FEDFDF";
			ObjForumScore[no].style.background = "#FEDFDF";
			ObjJoinScore[no].style.background = "#FEDFDF";
			ObjEtcScore[no].style.background = "#FEDFDF";
			ObjEtcScore2[no].style.background = "#FEDFDF";
			ObjEtcScore3[no].style.background = "#FEDFDF";
			ObjEtcScore4[no].style.background = "#FEDFDF";
			ObjEtcScore5[no].style.background = "#FEDFDF";
			ObjTotScore[no].style.background = "#FEDFDF";
			ObjTotScore[no].value = newTotalScore.toFixed(1);
        }
	}

	function chkNumber(param,maxVlaue){
		var score =  param.value;
		var scoreAr;
		if(score != ""){
			if(score.indexOf(".") != -1){
				scoreAr = score.split(".");
				if(scoreAr[1].length > 1){
					param.value = parseFloat(score).toFixed(1);
				} else {
					param.value = parseFloat(score);
				}
			} else {
				param.value = Number(score);
			}
		}

		if(param.value > maxVlaue){
			param.value = maxVlaue;
		}
	}


	function addAutoResult() {
		if(arrayToString('sel') == '') {
			alert("<spring:message code="student.message.result.alert.nouser"/>");
			return;
		}
		if(!confirm('<spring:message code="student.message.result.alert.scoreauto"/>')) {
			return;
		}
		$.getJSON( cUrl("/mng/std/eduResult/addAutoResult"),		// url
			{ 
			  "crsCreCd" : ItemDTO.crsCreCd
			},			// params
			processCallback				// callback function
		);
	}

	function addResult(no) {
		var ObjStdNo = document.getElementsByName("sel");
		var ObjAttdScore = document.getElementsByName("attdScore");
		var ObjPrgrScore = document.getElementsByName("prgrScore");
		var ObjExamScore = document.getElementsByName("examScore");
		var ObjSemiExamScore = document.getElementsByName("semiExamScore");
		var ObjAsmtScore = document.getElementsByName("asmtScore");
		var ObjForumScore = document.getElementsByName("forumScore");
		var ObjJoinScore = document.getElementsByName("joinScore");
		var ObjEtcScore = document.getElementsByName("etcScore");
		var ObjEtcScore2 = document.getElementsByName("etcScore2");
		var ObjEtcScore3 = document.getElementsByName("etcScore3");
		var ObjEtcScore4 = document.getElementsByName("etcScore4");
		var ObjEtcScore5 = document.getElementsByName("etcScore5");
		var ObjTotScore = document.getElementsByName("totScore");

		$.getJSON( cUrl("/mng/std/eduResult/addResult"),		// url
				{ 
				  "crsCreCd" 	: ItemDTO.crsCreCd,
				  "stdNo" 		: ObjStdNo[no].value,
				  "prgrScore" 	: ObjPrgrScore[no].value,
				  "attdScore" 	: ObjAttdScore[no].value,
				  "examScore" 	: ObjExamScore[no].value,
				  "semiExamScore" 	: ObjSemiExamScore[no].value,
				  "asmtScore" 	: ObjAsmtScore[no].value,
				  "forumScore" : ObjForumScore[no].value,
				  "joinScore" 	: ObjJoinScore[no].value,
				  "etcScore" 	: ObjEtcScore[no].value,
				  "etcScore2" 	: ObjEtcScore2[no].value,
				  "etcScore3" 	: ObjEtcScore3[no].value,
				  "etcScore4" 	: ObjEtcScore4[no].value,
				  "etcScore5" 	: ObjEtcScore5[no].value,
				  "totScore" 	: ObjTotScore[no].value
				},			// params
				processCallback				// callback function
			);
	}

	function addResultAll() {

		if(arrayToString('sel') == '') {
			alert("<spring:message code="student.message.result.alert.nouser"/>");
			return;
		}
		var addContent  = "<iframe id='messageFrame' name='messageFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/mng/std/eduResult/scoreAllPop"/>"
			+ "?crsCreCd="+ItemDTO.crsCreCd+"'/>";
		parent.modalBox.clear();
		parent.modalBox.addContents(addContent);
		parent.modalBox.resize(720, 260);
		parent.modalBox.setTitle("<spring:message code="button.add.all"/>");
		parent.modalBox.show();
		/*
		if(!confirm('<spring:message code="student.message.result.confirm.addall"/>')){
			return;
		}

		$.getJSON( cUrl("/mng/std/eduResult/"),		// url
				{ "cmd" : "addResultAll",
				  "eduResultDTO.crsCreCd" : ItemDTO.crsCreCd,
				  "strStdNo" : arrayToString('sel'),
				  "strPrgrScore" : arrayToString('prgrScore'),
				  "strAttdScore" : arrayToString('attdScore'),
				  "strExamScore" : arrayToString('examScore'),
				  "strAsmtScore" : arrayToString('asmtScore'),
				  "strForumScore" : arrayToString('forumScore'),
				  "strJoinScore" : arrayToString('joinScore'),
				  "strEtcScore" : arrayToString('etcScore'),
				  "strTotScore" : arrayToString('totScore')
				},			// params
				processCallback				// callback function
			);
		 */
	}

	function addResultAllSubmit(){
		$.getJSON( cUrl("/mng/std/eduResult/addResultAll"),		// url
				{ 
				  "crsCreCd" : ItemDTO.crsCreCd,
				  "strStdNo" : arrayToString('sel'),
				  "strPrgrScore" : arrayToString('prgrScore'),
				  "strAttdScore" : arrayToString('attdScore'),
				  "strExamScore" : arrayToString('examScore'),
				  "strSemiExamScore" : arrayToString('semiExamScore'),
				  "strAsmtScore" : arrayToString('asmtScore'),
				  "strForumScore" : arrayToString('forumScore'),
				  "strJoinScore" : arrayToString('joinScore'),
				  "strEtcScore" : arrayToString('etcScore'),
				  "strEtcScore2" : arrayToString('etcScore2'),
				  "strEtcScore3" : arrayToString('etcScore3'),
				  "strEtcScore4" : arrayToString('etcScore4'),
				  "strEtcScore5" : arrayToString('etcScore5'),
				  "strTotScore" : arrayToString('totScore')
				},			// params
				processCallback				// callback function
			);
	}

	function scoreAllSet(prgrScore, attdScore, examScore, semiExamScore, asmtScore, forumScore, joinScore, etcScore,totScore){
		var Objsel = document.getElementsByName("sel");
		var ObjPrgrScore = document.getElementsByName("prgrScore");
		var ObjAttdScore = document.getElementsByName("attdScore");
		var ObjExamScore = document.getElementsByName("examScore");
		var ObjSemiExamScore = document.getElementsByName("semiExamScore");
		var ObjAsmtScore = document.getElementsByName("asmtScore");
		var ObjForumScore = document.getElementsByName("forumScore");
		var ObjJoinScore = document.getElementsByName("joinScore");
		var ObjEtcScore = document.getElementsByName("etcScore");
		var ObjEtcScore2 = document.getElementsByName("etcScore2");
		var ObjEtcScore3 = document.getElementsByName("etcScore3");
		var ObjEtcScore4 = document.getElementsByName("etcScore4");
		var ObjEtcScore5 = document.getElementsByName("etcScore5");
		var ObjTotScore = document.getElementsByName("totScore");

		for(var i=0; i < Objsel.length; i++) {
			if(prgrScore != "")	ObjPrgrScore[i].value = prgrScore;
			if(attdScore != "")	ObjAttdScore[i].value = attdScore;
			if(examScore != "")	ObjExamScore[i].value = examScore;
			if(semiExamScore != "")	ObjSemiExamScore[i].value = semiExamScore;
			if(asmtScore != "")	ObjAsmtScore[i].value = asmtScore;
			if(forumScore != "")	ObjForumScore[i].value = forumScore;
			if(joinScore != "")	ObjJoinScore[i].value = joinScore;
			if(etcScore != "")	ObjEtcScore[i].value = etcScore;
			if(etcScore2 != "")	ObjEtcScore[i].value = etcScore2;
			if(etcScore3 != "")	ObjEtcScore[i].value = etcScore3;
			if(etcScore4 != "")	ObjEtcScore[i].value = etcScore4;
			if(etcScore5 != "")	ObjEtcScore[i].value = etcScore5;
			totScore = parseFloat(ObjPrgrScore[i].value) +
							parseFloat(ObjAttdScore[i].value) +
							parseFloat(ObjExamScore[i].value) +
							parseFloat(ObjSemiExamScore[i].value) +
							parseFloat(ObjAsmtScore[i].value) +
							parseFloat(ObjForumScore[i].value) +
							parseFloat(ObjJoinScore[i].value) +
							parseFloat(ObjEtcScore[i].value) +
							parseFloat(ObjEtcScore2[i].value) +
							parseFloat(ObjEtcScore3[i].value) +
							parseFloat(ObjEtcScore4[i].value) +
							parseFloat(ObjEtcScore5[i].value);

			ObjTotScore[i].value =totScore.toFixed(1);
		}
		addResultAllSubmit();
		parent.modalBox.close();
	}

	function addResultCheck() {
		var stdList = $("#Search input[name='sel']:checked").stringValues("|");
		if(isEmpty(stdList)) {
			alert("<spring:message code="student.message.result.alert.chkuser"/>");
			return;
		}
		if(!confirm('<spring:message code="student.message.result.confirm.addsel"/>')){
			return;
		}

		$.getJSON( cUrl("/mng/std/eduResult/addResultAll"),		// url
				{ 
				  "crsCreCd" : ItemDTO.crsCreCd,
				  "strStdNo" : arrayToStringCheck('sel'),
				  "strPrgrScore" : arrayToStringCheck('prgrScore'),
				  "strAttdScore" : arrayToStringCheck('attdScore'),
				  "strExamScore" : arrayToStringCheck('examScore'),
				  "strSemiExamScore" : arrayToStringCheck('semiExamScore'),
				  "strAsmtScore" : arrayToStringCheck('asmtScore'),
				  "strForumScore" : arrayToStringCheck('forumScore'),
				  "strJoinScore" : arrayToStringCheck('joinScore'),
				  "strEtcScore" : arrayToStringCheck('etcScore'),
				  "strEtcScore2" : arrayToStringCheck('etcScore2'),
				  "strEtcScore3" : arrayToStringCheck('etcScore3'),
				  "strEtcScore4" : arrayToStringCheck('etcScore4'),
				  "strEtcScore5" : arrayToStringCheck('etcScore5'),
				  "strTotScore" : arrayToStringCheck('totScore')
				},			// params
				processCallback				// callback function
			);
	}

	function arrayToString(objName) {
		obj = document.getElementsByName(objName);
		if(obj == null) {
			return "";
		}
		var retStr = "";
		for(var i=0; i < obj.length; i++) {
			retStr = retStr + "|" + obj[i].value;
		}
		retStr = retStr.substring(1);
		//alert('objName : '+objName + "==>" +retStr);
		return retStr;
	}

	function arrayToStringCheck(objName) {
		obj = document.getElementsByName(objName);
		if(obj == null) {
			return "";
		}
		var retStr = "";
		for(var i=0; i < obj.length; i++) {
			if($("#sel_"+i).is(":checked")) {
				retStr = retStr + "|" + obj[i].value;
			}
		}
		retStr = retStr.substring(1);
		//alert('objName : '+objName + "==>" +retStr);
		return retStr;
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

	function excelDownload() {
		// download용 iframe이 없으면 만든다.
		if ( $("#_m_download_iframe").length == 0 ) {
			iframeHtml =
				'<iframe id="_m_download_iframe" name="_m_download_iframe" style="visibility: none; display: none;"></iframe>';
			$("body").append(iframeHtml);
		}
		// 폼에 action을 설정하고 submit시킨다.
		$("#eduResultForm").attr('target', '_m_download_iframe');
		$("#eduResultForm").attr("action", "/mng/std/eduResult/listExcelDownload");
		$("#eduResultForm").submit();
		$("#eduResultForm").removeAttr('target');
	}

	/**
	 * 엑셀파일 업로드
	 */
	function excelUpload() {
		var addContent  = "<iframe id='addResultFrame' name='addResultFrame' width='100%' height='100%' "
						+ "frameborder='0' scrolling='no' src='<c:url value="/mng/std/eduResult/addFormExcelPop"/>"
						+ "?crsCreCd="+ItemDTO.crsCreCd+"'/>";
		parent.stdPopBox.clear();
		parent.stdPopBox.addContents(addContent);
		parent.stdPopBox.resize(400, 220);
		parent.stdPopBox.setTitle("<spring:message code="student.title.result.write.excel"/>");
		parent.stdPopBox.show();
	}

	function initResultBatch() {
		var addContent  =
			"	<div style='width:100%;height:100%;background-color:#ffffff;'>"+
			"		<form name='Input'>"+
			"		<table summary='<spring:message code="student.title.result.manage"/>' style='width:100%' class='table_list'>"+
			"			<colgroup>"+
			"				<col style='width:auto;'/>"+
			"				<col style='width:auto;'/>"+
			"				<col style='width:auto;'/>"+
			"				<col style='width:auto;'/>"+
			"				<col style='width:auto;'/>"+
			"				<col style='width:auto;'/>"+
			"				<col style='width:auto;'/>"+
			"				<col style='width:auto;'/>"+
			"				<col style='width:auto;'/>"+
			"			</colgroup>"+
			"			<thead>"+
			"				<tr>"+
			"					<th scope='col'><spring:message code="course.title.course.ratio.progress"/><br>(${createCourseVO.prgrRatio}%)</th>"+
			"					<th scope='col'><spring:message code="course.title.course.ratio.attend"/><br>(${createCourseVO.attdRatio}%)</th>"+
			"					<th scope='col'><spring:message code="course.title.course.ratio.exam"/><br>(${createCourseVO.examRatio}%)</th>"+
			"					<th scope='col'><spring:message code="course.title.course.ratio.semiExam"/><br>(${createCourseVO.semiExamRatio}%)</th>"+			
			"					<th scope='col'><spring:message code="course.title.course.ratio.asmt"/><br>(${createCourseVO.asmtRatio}%)</th>"+
			"					<th scope='col'><spring:message code="course.title.course.ratio.forum"/><br>(${createCourseVO.forumRatio}%)</th>"+
			"					<th scope='col'><spring:message code="course.title.course.ratio.join"/><br>(${createCourseVO.joinRatio}%)</th>"+
			"					<th scope='col'><spring:message code="course.title.course.ratio.etc"/><br>(${createCourseVO.etcRatio}%)</th>"+
			"					<th scope='col'><spring:message code="student.title.result.totalscore"/><br>(100%)</th>"+
			"				</tr>"+
			"			</thead>"+
			"			<tbody>"+
			"				<tr>"+
			"					<td><input type='text' style='width:28px;' id='prgrScore' value='0' <c:if test="${createCourseVO.prgrRatio == 0}">readonly</c:if>/></td>"+
			"					<td><input type='text' style='width:28px;' id='attdScore' value='0' <c:if test="${createCourseVO.attdRatio == 0}">readonly</c:if>/></td>"+
			"					<td><input type='text' style='width:28px;' id='examScore' value='0' <c:if test="${createCourseVO.examRatio == 0}">readonly</c:if>/></td>"+
			"					<td><input type='text' style='width:28px;' id='semiExamScore' value='0' <c:if test="${createCourseVO.semiExamRatio == 0}">readonly</c:if>/></td>"+
			"					<td><input type='text' style='width:28px;' id='asmtScore' value='0' <c:if test="${createCourseVO.asmtRatio == 0}">readonly</c:if>/></td>"+
			"					<td><input type='text' style='width:28px;' id='forumScore' value='0' <c:if test="${createCourseVO.forumRatio == 0}">readonly</c:if>/></td>"+
			"					<td><input type='text' style='width:28px;' id='joinScore' value='0' <c:if test="${createCourseVO.joinRatio == 0}">readonly</c:if>/></td>"+
			"					<td><input type='text' style='width:28px;' id='etcScore' value='0' <c:if test="${createCourseVO.etcRatio == 0}">readonly</c:if>/></td>"+
			"					<td><input type='text' style='width:28px;' id='totalScore' value='0' readonly/></td>"+
			"				</tr>"+
			"			</tbody>"+
			"		</table>"+
			"		<div style='margin-top:10px; width:600px; text-align:center'>"+
			"			<a class='squarebutton' href='#none' onclick='javascript:setResultBatch();' ><span><spring:message code="button.add.all"/></span></a>"+
			"		</div>"+
			"		</form>"+
			"	</div>";
			parent.modalBox.clear();
			parent.modalBox.addContents(addContent);
			parent.modalBox.resize(600, 150);
			parent.modalBox.setTitle("일괄점수입력");
	}

	function inputResultBatch() {
		popBox.show();

	}

	function setResultBatch() {
		var prgrScore = parseInt($("#prgrScore").val(),10);
		var attdScore = parseInt($("#attdScore").val(),10);
		var examScore = parseInt($("#examScore").val(),10);
		var semiExamScore = parseInt($("#semiExamScore").val(),10);
		var asmtScore = parseInt($("#asmtScore").val(),10);
		var forumScore = parseInt($("#forumScore").val(),10);
		var joinScore = parseInt($("#joinScore").val(),10);
		var etcScore = parseInt($("#etcScore").val(),10);
		var etcScore2 = parseInt($("#etcScore2").val(),10);
		var etcScore3 = parseInt($("#etcScore3").val(),10);
		var etcScore4 = parseInt($("#etcScore4").val(),10);
		var etcScore5 = parseInt($("#etcScore5").val(),10);
		var totalScore = prgrScore + attdScore + examScore + semiExamScore + asmtScore + forumScore + prjtScore + joinScore + etcScore+ etcScore2+ etcScore3+ etcScore4+ etcScore5;
		$("#totalScore").val(totalScore);

		var stdList = $("#Search input[name='sel']:checked").stringValues("|");
		if(isEmpty(stdList)) {
			alert("<spring:message code="student.message.result.alert.chkuser"/>");
			popBox.close();
			return;
		}
		if(confirm("<spring:message code="student.message.result.alert.addmsg1"/> "+totalScore+"<spring:message code="student.message.result.alert.addmsg2"/>")) {
			setValueToStringCheck('prgrScore', prgrScore);
			setValueToStringCheck('attdScore', attdScore);
			setValueToStringCheck('examScore', examScore);
			setValueToStringCheck('semiExamScore', semiExamScore);
			setValueToStringCheck('asmtScore', asmtScore);
			setValueToStringCheck('forumScore', forumScore);
			setValueToStringCheck('joinScore', joinScore);
			setValueToStringCheck('etcScore', etcScore);
			setValueToStringCheck('etcScore2', etcScore2);
			setValueToStringCheck('etcScore3', etcScore3);
			setValueToStringCheck('etcScore4', etcScore4);
			setValueToStringCheck('etcScore5', etcScore5);
			setValueToStringCheck('totScore', totalScore);

			addResultCheck();
		}
		popBox.close();
	}

	function setValueToStringCheck(objName, value) {
		obj = document.getElementsByName(objName);
		if(obj == null) {
			return "";
		}
		for(var i=0; i < obj.length; i++) {
			if($("#sel_"+i).is(":checked")) {
				obj[i].value = value;
			}
		}
	}

	function setSortKey(sortKey) {
		ItemDTO.sortKey = sortKey;
		listStudent(1);
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
			+ "?msgDivCd="+msgDivCd+"&amp;logMsgTransLogVO.stdNoList="+userList+"'/>";
		parent.modalBox.clear();
		parent.modalBox.addContents(addContent);
		parent.modalBox.resize(720, 520);
		parent.modalBox.setTitle(getMessageTitle(msgDivCd));
		parent.modalBox.show();
	}

	function scoreEclt() {
		var targetList = $("#Search input[name='scoreEcltYn']:checked").stringValues();
		var studentList = $("#Search input[name='scoreEcltYn']").stringValues();

		if(targetList.length == 0){
			alert("<spring:message code="lecture.message.score.excellent.add.noselect"/>");
			return;
		}

		$.getJSON( cUrl("/mng/std/eduResult/addEclt"),		// url
				{ 
				  "studentList" : studentList,
				  "targetList" : targetList
				},			// params
				function (resultDTO) {
					alert(resultDTO.message);
					if(resultDTO.result >= 0) {
						// 정상 처리
						document.location.reload();
					} else {
						// 비정상 처리
					}
				}
			);
	}

	//-- 로그
	function viewWorkLog() {
		var addContent  = "<iframe id='workLogFrame' name='workLogFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/mng/std/eduRsltWorkLog/mainPop"/>"
			+ "?crsCreCd="+ItemDTO.crsCreCd+"'/>";
		parent.modalBox.clear();
		parent.modalBox.addContents(addContent);
		parent.modalBox.resize(720, 520);
		parent.modalBox.setTitle('view work log');
		parent.modalBox.show();
	}
	
	/* 수료증출력 */	
	function printCerti(crsCreCd,stdNo) {
		var url = "/mng/std/student/viewPrintCerti?crsCreCd="+crsCreCd+"&stdNo="+stdNo;
		var option = "width=640, toolbar=no, menubar=no, scrollbars=no, resizable=no, copyhistory=no";
		var reportWin = window.open(url,'reportWin', option);
		reportWin.focus();
	}
	
	
	function setStudent() {
		var strs = "";
		$('input[name=sel]:checked').each(function() {
				strs = strs + "|" + $(this).val();
			}
		);
		strs = strs.substring(1);
		if(strs == "") {
			alert("학습자를 선택해 주세요.");
			return false;
		}
		$("#stdNo").val(strs);
		return true;
	}
	
	
	var crsCreCd = '${createCourseVO.crsCreCd}';

	function printCertificateMulti() {
		if(setStudent()) {
			var stdNo = $(':input:hidden[name=stdNo]').val();
			var url = "/mng/std/student/viewPrintCerti?crsCreCd="+crsCreCd+"&stdNo="+stdNo;

			if ( $("#_m_pdf_iframe").length == 0 ) {
				iframeHtml =
					'<iframe id="_m_pdf_iframe" name="_m_pdf_iframe" style="visibility: none; display: none;"></iframe>';
				$("body").append(iframeHtml);
			}
			$("#_m_pdf_iframe").attr("src",url);
		}
	}


</script>
