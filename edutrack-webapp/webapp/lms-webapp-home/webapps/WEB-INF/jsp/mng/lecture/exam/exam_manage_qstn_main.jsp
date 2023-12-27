<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<%pageContext.setAttribute("crlf", "\n");%>
<c:set var="examVO" value="${vo}" />

<br/>
<table summary="<spring:message code="lecture.title.exam.manage"/>" class="table table-bordered wordbreak">
	<tr height="35">
		<th class="top" scope="row" style="width:20%"><spring:message code="lecture.title.exam.examtype"/></th>
		<td class="top" style="width:30%">
			<meditag:codename code="${examVO.examTypeCd}" category="EXAM_TYPE_CD"/>
		</td>
		<th class="top" scope="row" style="width:20%"><spring:message code="lecture.title.exam.ansrtype"/></th>
		<td class="top" style="width:30%">
			<meditag:codename code="${examVO.examStareTypeCd}" category="EXAM_STARE_TYPE_CD"/>
		</td>
	</tr>
	<tr height="35">
		<th scope="row" style="width:20%"><spring:message code="lecture.title.exam.name"/></th>
		<td colspan="3">
			${examVO.examTitle}
		</td>
	</tr>
<c:if test="${examVO.examTypeCd eq 'ON'}">
	<c:if test="${examVO.examStareTypeCd eq 'S'}">
	<tr height="35">
		<th scope="row" style="width:20%"><spring:message code="lecture.title.exam.answer.ratio"/></th>
		<td style="width:30%">
			${examVO.stareCritPrgrRatio} % <spring:message code="common.title.over"/>
		</td>
		<th scope="row" style="width:20%"><spring:message code="lecture.title.exam.limitcnt"/></th>
		<td style="width:30%">
			${examVO.stareLimitCnt} <spring:message code="common.title.times.postfix"/>
		</td>
	</tr>
	</c:if>
	<c:if test="${examVO.examStareTypeCd eq 'R'}">
	<tr height="35">
		<th scope="row" style="width:20%"><spring:message code="lecture.title.exam.duration"/></th>
		<td colspan="3">
			<meditag:dateformat type="1" delimeter="." property="${examVO.examStartDttm}"/> <meditag:dateformat type="7" delimeter="." property="${examVO.examStartDttm}"/> ~ <meditag:dateformat type="1" delimeter="." property="${examVO.examEndDttm}"/> <meditag:dateformat type="7" delimeter="." property="${examVO.examEndDttm}"/>
		</td>
	</tr>
	<tr height="35">
		<th scope="row" style="width:20%"><spring:message code="lecture.title.exam.result.date"/></th>
		<td>
			<meditag:dateformat type="1" delimeter="." property="${examVO.rsltCfrmDttm}"/> <meditag:dateformat type="7" delimeter="." property="${examVO.rsltCfrmDttm}"/>
		</td>
		<th scope="row" style="width:20%"><spring:message code="lecture.title.exam.limitcnt"/></th>
		<td style="width:30%">
			${examVO.stareLimitCnt} <spring:message code="common.title.times.postfix"/>
		</td>
	</tr>
	</c:if>
	<tr height="35">
		<th scope="row" style="width:20%"><spring:message code="lecture.title.exam.timelimit"/></th>
		<td>
			<c:choose>
				<c:when test="${examVO.examStareTm > 0}">
					${examVO.examStareTm} <spring:message code="common.title.min"/>
				</c:when>
				<c:otherwise>
					<spring:message code="common.title.status.useyn_n"/>
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
	<tr height="35">
		<th scope="row" style="width:20%">시험 출제 문항수/배점</th>
		<td>
			<c:if test="${not empty examVO.selCnt && examVO.selCnt > 0 }">
				선택형  : ${examVO.selCnt }개 / ${examVO.selPnt } 점
			</c:if>
			<c:if test="${not empty examVO.shortCnt && examVO.shortCnt > 0 }">
				<br>단답형  : ${examVO.shortCnt }개 / ${examVO.shortPnt } 점
			</c:if>
			<c:if test="${not empty examVO.desCnt && examVO.desCnt > 0 }">
				<br>서술형 : ${examVO.desCnt }개 / ${examVO.desPnt } 점
			</c:if>
		</td>
	</tr>
</c:if>
<c:if test="${examVO.examTypeCd ne 'ON'}">
	<tr height="35">
		<th scope="row" style="width:20%"><spring:message code="lecture.title.exam.duration"/></th>
		<td colspan="3">
			<meditag:dateformat type="1" delimeter="." property="${examVO.examStartDttm}"/> <meditag:dateformat type="7" delimeter="." property="${examVO.examStartDttm}"/> ~ <meditag:dateformat type="1" delimeter="." property="${examVO.examEndDttm}"/> <meditag:dateformat type="7" delimeter="." property="${examVO.examEndDttm}"/>
		</td>
	</tr>
	<tr height="35">
		<th scope="row" style="width:20%"><spring:message code="lecture.title.exam.result.date"/></th>
		<td colspan="3">
			<meditag:dateformat type="1" delimeter="." property="${examVO.rsltCfrmDttm}"/> <meditag:dateformat type="7" delimeter="." property="${examVO.rsltCfrmDttm}"/>
		</td>
	</tr>
</c:if>
	<tr height="35">
		<th scope="row" style="width:20%"><spring:message code="lecture.title.exam.regyn"/></th>
		<td colspan="3">
			<c:set var="regYn" value="${examVO.regYn}"/>
			<c:if test="${empty examVO.regYn}"><c:set var="regYn" value="N"/></c:if>
			<meditag:codename code="${regYn}" category="REG_YN"/>
		</td>
	</tr>
	<tr>
		<th scope="row"><spring:message code="lecture.title.exam.desc"/></th>
		<td colspan="3" class="wordbreak">
			${fn:replace(examVO.examCts,crlf,"<br/>")}
		</td>
	</tr>
</table>
<div class="text-right" style="margin-top:10px;">
	<c:if test="${examVO.regYn ne 'Y'}">
	<button class="btn btn-info btn-sm" onclick="examRegist();" ><spring:message code="button.ok.regist"/></button>
	</c:if>
	<c:if test="${examVO.regYn eq 'Y'}">
	<button class="btn btn-warning btn-sm" onclick="examRegistCancel();" ><spring:message code="button.cancel.regist"/></button>
	</c:if>
	<button class="btn btn-default btn-sm" onclick="goList();" ><spring:message code="button.list"/></button>
</div>
<ul class="nav nav-tabs" id="tabMenuExam">
	<li class="active"><a href="#"><spring:message code="lecture.title.exam.manage.question.tab"/></a></li>
	<li><a href="javascript:examRateForm()"><spring:message code="lecture.title.exam.manage.result.tab"/></a></li>
	<li><a href="javascript:examRsltForm()"><spring:message code="lecture.title.exam.manage.status.tab"/></a></li>
</ul>
<div style=";border-top:0px;border-left:1px solid #e3e3e3;border-right:1px solid #e3e3e3;border-bottom:1px solid #e3e3e3;margin-top:-1px;padding:15px;">
	<form name="SearchQstn" id="SearchQstn" onsubmit="return false">
	<div class="row">
		<div class="col-lg-12">
			<div style="float:left">
				<h5><spring:message code="lecture.title.exam.total.scoreing"/>  <b>[100<spring:message code="common.title.score"/>]</b> </h5>
			</div>
			<div style="float:right">
				<c:if test="${examVO.regYn ne 'Y'}">
				<%-- <button class="btn btn-info btn-sm" onclick="setSortQstn();" ><spring:message code="button.sort"/></button> --%>
<%--			<button class="btn btn-info btn-sm" onclick="setScoreChk();" ><spring:message code="button.write.setscore"/></button>  --%>
				<button class="btn btn-info btn-sm" onclick="qstnBankWrite();" ><spring:message code="button.view.exambank"/></button>
				<button class="btn btn-primary btn-sm" onclick="questionWrite('J');" ><meditag:codename code="J" category="EXAM_QSTN_TYPE"/> <spring:message code="button.write.question"/></button>
				<button class="btn btn-primary btn-sm" onclick="questionWrite('K');" ><meditag:codename code="K" category="EXAM_QSTN_TYPE"/> <spring:message code="button.write.question"/></button>
				<button class="btn btn-primary btn-sm" onclick="questionWrite('S');" ><meditag:codename code="S" category="EXAM_QSTN_TYPE"/> <spring:message code="button.write.question"/></button>
				<button class="btn btn-primary btn-sm" onclick="questionWrite('D');" ><meditag:codename code="D" category="EXAM_QSTN_TYPE"/> <spring:message code="button.write.question"/></button>
				<%-- <a href="javascript:writeQuestionExcel()" class="btn btn-info btn-sm"><i class="fa fa-file-excel-o fa-fw"></i> <spring:message code="button.upload.excel"/> </a> --%>
				</c:if>
				<c:if test="${examVO.regYn eq 'Y'}">
				<button class="btn btn-info btn-sm" onclick="previewQstn();" ><spring:message code="lecture.title.exam.question"/> <spring:message code="button.question.preview"/></button>
				</c:if>
			</div>
		</div>
	</div>
	<div id="examQstnList" style="margin-top:5px;">
		<table summary="<spring:message code="lecture.title.exam.question.manage"/>" class="table table-bordered wordbreak">
			<colgroup>
				<col style="width:50px;">
				<col style="width:auto;">
				<col style="width:90px;">
				<col style="width:50px;">
			</colgroup>
			<thead>
				<tr>
					<th scope="col"><spring:message code="common.title.no"/></th>
					<th scope="col"><spring:message code="lecture.title.exam.question"/></th>
					<th scope="col"><spring:message code="lecture.title.exam.question.type"/></th>
					<th scope="col"><spring:message code="common.title.manage"/></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td colspan="5"><spring:message code="lecture.message.exam.question.nodata"/></td>
				</tr>
			</tbody>
		</table>
	</div>
	<input type="submit" value="submit" style="display:none" />
	</form>
</div>
<form id="examForm" name="examForm" onsubmit="return false" >
	<input type="hidden" name="crsCreCd" value="${vo.crsCreCd }" />
	<input type="hidden" name="examSn" value="${vo.examSn }" />
	<input type="hidden" name="qstnScores" value="${vo.qstnScores }" />
	<input type="hidden" name="examQstnSn" value="${vo.examQstnSn }" />
	<input type="hidden" name="qstnNos" value="${vo.qstnNos }" />
	<input type="hidden" name="stareCnt" value="${vo.stareCnt }"/>
</form>
<script type="text/javascript">

	var ItemDTO = new Object();

	$(document).ready(function() {
		$('#tabMenuExam a').click(function (e) {
			  $(this).tab('show');
			})
		listQuestion();
	});

	/**
	* resize
	*/
	function parentResize() {
		var iframeObj = parent.document.getElementById("subWorkFrame");
		parent.resizeIframe3(iframeObj, $(document).height());
	}

	/**
	*목록 이동
	*/
	function goList() {
		document.location.href = cUrl("/mng/lecture/exam/examMain")+"?crsCreCd=${examVO.crsCreCd}${AMPERSAND}semiExamYn=${examVO.semiExamYn}";
	}

	//-- 평가 관리
	function examRateForm() {
		document.location.href = cUrl("/mng/lecture/exam/manageFormExamRateMain")+"?crsCreCd=${examVO.crsCreCd}${AMPERSAND}examSn=${examVO.examSn}";
	}

	//-- 결과 통계
	function examRsltForm() {
		document.location.href = cUrl("/mng/lecture/exam/manageFormExamRsltMain")+"?crsCreCd=${examVO.crsCreCd}${AMPERSAND}examSn=${examVO.examSn}";
	}

	/**
	 * 시험 등록 완료
	 */
	function examRegist() {
		var Obj = document.getElementsByName("score");
		var ObjExamQstnSn = document.getElementsByName("examQstnSn");
		var ObjQstnNo = document.getElementsByName("qstnNo");
		var AvgScore = Math.round(100 / Obj.length);
		var totalScore = 0.0;
		var Scores = "";
		var examQstnSn = "";
		var qstnNos = "";
		for(var i=0; i < ObjQstnNo.length; i++) {
			totalScore = totalScore + parseFloat(0);
			Scores = Scores +"|"+ 0;
			examQstnSn = examQstnSn +"|"+ 0;
			qstnNos = qstnNos + "|"+0;
		}
		Scores = Scores.substring(1);
		examQstnSn = examQstnSn.substring(1);
		qstnNos = qstnNos.substring(1);
		document.examForm["qstnScores"].value = Scores;
		document.examForm["examQstnSn"].value = examQstnSn;
		document.examForm["qstnNos"].value = qstnNos;
		process("addRegistExam");	// cmd
	}

	/**
	 * 시험 등록 완료 취소
	 */
	function examRegistCancel() {
		var stareCnt = '${examVO.stareCnt}';
		if(stareCnt > 0) {
			alert("<spring:message code="lecture.message.exam.question.alert.nocancel"/>");
			return;
		}
		process("cancelRegistExam");	// cmd
	}

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
			document.location.href = cUrl("/mng/lecture/exam/manageFormExam")+"?crsCreCd=${examVO.crsCreCd}${AMPERSAND}examSn=${examVO.examSn}";
		} else {
			// 비정상 처리
		}
	}

	/**
	 * 시험 문제 목록
	**/
	function listQuestion() {
		
		$("#examQstnList")
			.load(
				cUrl("/mng/lecture/exam/listQuestion"),
				{
					"crsCreCd" : '${examVO.crsCreCd}',
					"examSn" : '${examVO.examSn}',
					"regYn" : '${examVO.regYn}'
				},
				function(){
					$(".inputNumber").inputNumber();
					parentResize();
					setScore();
				<c:if test="${examVO.regYn eq 'Y'}">
					setReadonly();
				</c:if>
				}
		);
	}

	function setReadonly(){
		$("#SearchQstn input[name='score']").prop("readonly",true);
	}

	function setScoreChk(){
		var Obj = document.getElementsByName("score");
		if(Obj.length ==0){
			alert("<spring:message code="lecture.message.exam.alert.setscore.nodata"/>");
			return;
		}
		setScore();
	}

	function setScore() {
		var Obj = document.getElementsByName("score");
		var AvgScore = Math.round(100 / Obj.length);
		var Tot_AvgScore = 0;
		//-- 스코어 입력란이 있을 경우
		if(Obj.length > 0) {
			for(var i=0; i < Obj.length; i++) {
				Obj[i].value = AvgScore;
				Tot_AvgScore += AvgScore;
			}
			Obj[Obj.length-1].value = Number(Obj[Obj.length-1].value) + Number(100-Tot_AvgScore);

			//-- 첫번째 배점 칸에 값이 없을 경우에만 자동 배점 처리함.
			if(Obj[0].value == "0.0") {
			}
		}
	}

	/**
	 * 문제 은행 보기 폼
	 */
	function qstnBankWrite() {
		if('${examVO.regYn}' == "Y") {
			alert("<spring:message code="lecture.message.exam.question.alert.write"/>");
			return;
		}
		var addContent  = "<iframe id='examWriteFrame' name='examWriteFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/mng/lecture/exam/editQbankPop"/>"
			+ "?crsCreCd=${examVO.crsCreCd}&examSn=${examVO.examSn}&semiExamYn=${examVO.semiExamYn}'/>"; 
		parent.modalBox.clear();
		parent.modalBox.addContents(addContent);
		parent.modalBox.resize(800, 700);
		parent.modalBox.setTitle("<spring:message code="lecture.title.exam.qstnbank"/>");
		parent.modalBox.show();
	}

	/**
	 * 시험 문제 등록 폼 호출
	 */
	function questionWrite(qstnType) {
		if('${examVO.regYn}' == "Y") {
			alert("<spring:message code="lecture.message.exam.question.alert.write"/>");
			return;
		}
		var addContent  = "<iframe id='examWriteFrame' name='examWriteFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/mng/lecture/exam/addFormQuestionPop"/>"
			+ "?crsCreCd=${examVO.crsCreCd}&amp;examSn=${examVO.examSn}&amp;qstnType="+qstnType+"'/>";
		parent.modalBox.clear();
		parent.modalBox.addContents(addContent);
		parent.modalBox.resize(1100, 770);
		parent.modalBox.setTitle("<spring:message code="lecture.title.exam.question.manage"/>");
		parent.modalBox.show();
	}

	/**
	 * 시험 문제 수정 폼 호출
	 */
	function questionEdit(examQstnSn) {
		if('${examVO.regYn}' == "Y") {
			alert("<spring:message code="lecture.message.exam.question.alert.edit"/>");
			return;
		}
		var addContent  = "<iframe id='examWriteFrame' name='examWriteFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/mng/lecture/exam/editFormQuestionPop"/>"
			+ "?crsCreCd=${examVO.crsCreCd}&amp;examSn=${examVO.examSn}&amp;examQstnSn="+examQstnSn+"'/>";
		parent.modalBox.clear();
		parent.modalBox.addContents(addContent);
		parent.modalBox.resize(1100, 770);
		parent.modalBox.setTitle("<spring:message code="lecture.title.exam.question.manage"/>");
		parent.modalBox.show();
	}

	function viewQstn(examQstnSn){
		var addContent  = "<iframe id='examWriteFrame' name='examWriteFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/mng/lecture/exam/viewQstnPop"/>"
			+ "?crsCreCd=${examVO.crsCreCd}&amp;examSn=${examVO.examSn}&amp;examQstnSn="+examQstnSn+"'/>";
		parent.modalBox.clear();
		parent.modalBox.addContents(addContent);
		parent.modalBox.resize(1000, 600);
		parent.modalBox.setTitle("<spring:message code="lecture.title.exam.question"/> <spring:message code="button.view"/>");
		parent.modalBox.show();
	}

	function previewQstn(){
		var addContent  = "<iframe id='examPaperFrame' name='examPaperFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/mng/lecture/exam/previewPaperPop"/>"
			+ "?crsCreCd=${examVO.crsCreCd}&amp;examSn=${examVO.examSn}&amp;examSetCnt=${examVO.examSetCnt}'/>";
		parent.modalBox.clear();
		parent.modalBox.addContents(addContent);
		parent.modalBox.resize(1100, 600);
		parent.modalBox.setTitle("<spring:message code="lecture.title.exam.question"/> <spring:message code="button.question.preview"/>");
		parent.modalBox.show();
	}

	function setSortQstn(){
		var addContent  = "<iframe id='examPaperFrame' name='examPaperFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/mng/lecture/exam/sortQuestionPop"/>"
			+ "?crsCreCd=${examVO.crsCreCd}&amp;examSn=${examVO.examSn}'/>";
		parent.modalBox.clear();
		parent.modalBox.addContents(addContent);
		parent.modalBox.resize(1100, 600);
		parent.modalBox.setTitle("<spring:message code="lecture.title.exam.question"/> <spring:message code="button.sort"/>");
		parent.modalBox.show();
	}


	function writeQuestionExcel(){
		if('${examVO.regYn}' == "Y") {
			alert("<spring:message code="lecture.message.exam.question.alert.edit"/>");
			return;
		}
		var addContent  = "<iframe id='addUserFrame' name='addUserFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/mng/lecture/exam/addQuestionExcelPop"/>"
			+ "?crsCreCd=${examVO.crsCreCd}&amp;examSn=${examVO.examSn}'/>";
		parent.modalBox.clear();
		parent.modalBox.addContents(addContent);
		parent.modalBox.resize(400, 300);
		parent.modalBox.setTitle("<spring:message code="lecture.title.exam.question.manage"/>");
		parent.modalBox.show();
	}

</script>
