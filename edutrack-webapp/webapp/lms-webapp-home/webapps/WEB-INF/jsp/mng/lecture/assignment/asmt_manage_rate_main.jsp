<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<%pageContext.setAttribute("crlf", "\n");%>
<c:set var="gubun" value="${gubun}"/>
<c:set var="assignmentVO" value="${vo}" />
<c:set var="assignmentSendVO" value="${vo.assignmentSendVO}" />
	<br/>
	<form id="assignmentForm" name="assignmentForm" onsubmit="return false" action="/mng/lecture/assignment/">
	<input type="hidden" name="crsCreCd" value="${vo.crsCreCd }" />
	<input type="hidden" name="asmtSn" value="${vo.asmtSn }" />
	<input type="hidden" name="sendCnt" value="${vo.sendCnt }" />
	<input type="hidden" name="userNoObj" id="userNoObj" />
	</form>
	<table summary="<spring:message code="lecture.title.assignment.manage"/>" class="table table-bordered wordbreak">
		<colgroup>
			<col style="width:20%"/>
			<col style="width:30%"/>
			<col style="width:20%"/>
			<col style="width:30%"/>
		</colgroup>
		<tbody>
			<tr>
				<th scope="row"><spring:message code="lecture.title.assignment.name"/></th>
				<td colspan="3" class="wordbreak">
					${assignmentVO.asmtTitle}
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="lecture.title.assignment.type"/></th>
				<td><meditag:codename code="${assignmentVO.asmtTypeCd}" category="ASMT_TYPE_CD"/></td>
				<th scope="row"><spring:message code="lecture.title.assignment.seltype"/></th>
				<td><meditag:codename code="${assignmentVO.asmtSelectTypeCd}" category="ASMT_SELECT_TYPE_CD" /></td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="lecture.title.assignment.duration"/></th>
				<td colspan="3">${assignmentVO.asmtStartDttm} ${assignmentVO.asmtStartHour}:${assignmentVO.asmtStartMin} ~ ${assignmentVO.asmtEndDttm} ${assignmentVO.asmtEndHour}:${assignmentVO.asmtEndMin}</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="lecture.title.assignment.delaydate"/></th>
				<td>${assignmentVO.extSendDttm} ${assignmentVO.extSendHour}:${assignmentVO.extSendMin}</td>
				<th scope="row"><spring:message code="lecture.title.assignment.send.cnt"/></th>
				<td>${assignmentVO.asmtLimitCnt} <spring:message code="common.title.times.postfix"/></td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="common.title.atachfile"/></th>
				<td colspan="3" class="wordbreak">
					<c:forEach var="file" items="${assignmentVO.attachFiles}"><p>${file.downloadTag}</p></c:forEach>
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="common.title.cnts"/></th>
				<td colspan="3" class="wordbreak">${fn:replace(assignmentVO.asmtCts,crlf,"<br/>")}</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="lecture.title.assignment.regyn"/></th>
				<td colspan="3" class="wordbreak"><meditag:codename code="${assignmentVO.regYn}" category="REG_YN"/></td>
			</tr>
		</tbody>
	</table>
	<div class="text-right">
<%-- 		<c:if test="${assignmentVO.regYn ne 'Y'}">
		<a href="javascript:registerAsmt()" class="btn btn-info btn-sm"><spring:message code="button.ok.regist"/> </a>
		</c:if>
		<c:if test="${assignmentVO.regYn eq 'Y'}">
		<a href="javascript:registerCancelAsmt()" class="btn btn-warning btn-sm"><spring:message code="button.cancel.regist"/> </a>
		</c:if>
 --%>		<a href="javascript:goList()" class="btn btn-default btn-sm"><spring:message code="button.list"/> </a>
	</div>
	<ul class="nav nav-tabs" id="tabMenuExam">
		<c:if test="${assignmentVO.asmtTypeCd eq 'ON'}" >
		<li><a href="javascript:qstnAsmtForm()"><spring:message code="lecture.title.assignment.manage.question.tab"/></a></li>
		</c:if>
		<li class="active"><a href="#"><spring:message code="lecture.title.assignment.manage.rate.tab"/></a></li>
		<li><a href="javascript:rsltAsmtForm()"><spring:message code="lecture.title.assignment.manage.rslt.tab"/></a></li>
	</ul>
	<div style="border-top:0px;border-left:1px solid #e3e3e3;border-right:1px solid #e3e3e3;border-bottom:1px solid #e3e3e3;margin-top:-1px;padding:15px;">

		<form name="StdSearch" id="StdSearch" onsubmit="return false">
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
				<div class="input-group" style="float:left;width:200px;">
					<input type="text" name="searchKey" id="searchKey" class="form-control input-sm _enterBind" placeholder="<spring:message code="user.title.userinfo.name"/>"/>
					<span class="input-group-addon" onclick="listStudent(1)" style="cursor:pointer">
						<i class="fa fa-search"></i>
					</span>
				</div>
				<div style="float:right; margin-left: 5px;">
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
					<c:if test="${assignmentVO.regYn eq 'Y' && assignmentVO.asmtTypeCd eq 'ON'}">
					<a href="javascript:resetSend()" class="btn btn-warning btn-sm"><spring:message code="button.reset.send"/></a>
					</c:if>
					<c:if test="${assignmentVO.asmtTypeCd eq 'OFF'}">
					<a href="javascript:addScoreAll()" class="btn btn-primary btn-sm"><spring:message code="button.add.score"/></a>
					</c:if>
				</div>
			</div>
		</div>

		<div id="studentList" style="margin-top:5px;">
			<table summary="<spring:message code="lecture.title.assignment.manage"/>" class="table table-bordered wordbreak">
				<colgroup>
					<col style="width:30px">
					<col style="width:50px">
					<col style="width:auto">
					<col style="width:auto">
					<col style="width:auto">
					<col style="width:60px">
					<col style="width:auto">
					<col style="width:auto">
					<col style="width:auto">
					<col style="width:auto">
				</colgroup>
				<thead>
					<tr>
						<th scope="col"></th>
						<th scope="col"><spring:message code="common.title.no"/></th>
						<th scope="col"><spring:message code="user.title.userinfo.name"/></th>
						<th scope="col"><spring:message code="user.title.userinfo.userid"/></th>
						<th scope="col"><spring:message code="lecture.title.assignment.sendyn"/></th>
						<th scope="col"><spring:message code="lecture.title.assignment.send.lastdate"/></th>
						<th scope="col">모사율</th>
						<th scope="col"><spring:message code="lecture.title.assignment.score"/></th>
						<th scope="col"><spring:message code="lecture.title.assignment.rateyn"/></th>
						<th scope="col"><spring:message code="lecture.title.assignment.rate"/>/<spring:message code="lecture.title.assignment.result"/></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td colspan="8"><spring:message code="student.message.student.nodata"/></td>
					</tr>
				</tbody>
			</table>
		</div>
		</form>

	</div>

<script type="text/javascript">
	var ItemDTO = new Object();
	var atchFiles; // 첨부파일 목록

	$(document).ready(function() {
		ItemDTO.curPage = 1;
		$("._enterBind").bind("keydown", eventForSearch);
		function eventForSearch(event) {
			if (event.keyCode == '13') {
				event.preventDefault();
				listStudent(1);
			}
		}
		listStudent(1);
	});

	function checkAll() {
    	//전체선택 /전체취소
	    var state=$('input[name=stdChkAll]:checked').size();
	    if(state==1){
	    	$("#StdSearch input[name='selStd']").prop("checked",true);
	    }else{
	    	$("#StdSearch input[name='selStd']").prop("checked",false);
	    }
	}

	//-- 과제 목록
	function goList() {
		document.location.href = cUrl("/mng/lecture/assignment/asmtMain")+"?crsCreCd=${assignmentVO.crsCreCd}";
	}

	//-- 과제 평가
	function qstnAsmtForm() {
		document.location.href = cUrl("/mng/lecture/assignment/manageQstnMain")+"?crsCreCd=${assignmentVO.crsCreCd}${AMPERSAND}asmtSn=${assignmentVO.asmtSn}";
	}

	//-- Result Status
	function rsltAsmtForm() {
		document.location.href = cUrl("/mng/lecture/assignment/manageRsltMain")+"?crsCreCd=${assignmentVO.crsCreCd}${AMPERSAND}asmtSn=${assignmentVO.asmtSn}";
	}

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		$('#assignmentForm').attr("action","/mng/lecture/assignment/" + cmd);
		$('#assignmentForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		alert(resultDTO.message);
		if(resultDTO.result >= 0) {
			// 정상 처리
			document.location.reload();
			//listStudent(ItemDTO.curPage);
		} else {
			// 비정상 처리
		}
	}

	/**
	 * 과제 등록 완료
	 */
	function registerAsmt() {
		<c:if test="${empty assignmentSibListVO}">
		alert("<spring:message code="lecture.message.assignment.question.nodata"/>");
		return;
		</c:if>
		process("registerAsmt");	// cmd
	}

	/**
	 * 과제 등록 완료 취소
	 */
	function registerCancelAsmt() {
		if(parseInt(document.assignmentForm["sendCnt"].value,10) > 0) {
			alert("<spring:message code="lecture.message.assignment.alert.cancelregist"/>");
			return;
		}
		process("registerCancelAsmt");	// cmd
	}

    /**
	 * resize
	 */
	function parentResize() {
		var iframeObj = parent.document.getElementById("subWorkFrame"); //-- $('#subWorkFrame', parent);
		parent.resizeIframe3(iframeObj, $(document).height());
	}

	/**
	 * 학습자 목록 조회
	 */
	function listStudent(page) {
		$("#studentListArea").show();
		ItemDTO.curPage = page;
		var userNm = $("#searchKey").val();
		var listScale = $("#listScale option:selected").val();
		var declsNo = $("#declsNo option:selected").val();

		$("#studentList")
			.load(
				cUrl("/mng/lecture/assignment/listStd"),		// url
				{
				  	"crsCreCd" : '${assignmentVO.crsCreCd}',
				  	"asmtSn" : '${assignmentVO.asmtSn}',
				  	"userNm" : userNm,
				  	"declsNo" : declsNo,
				  	"curPage" : page,
		  		  	"listScale" : listScale
				},			// params
				function() {
					parentResize();
				}
			);
	}

	/**
	 * 제제출 설정
	 */
	function resetSend() {
		var userList = $("#StdSearch input[name='selStd']:checked").stringValues();
		var chkLength = $("#StdSearch input[name='selStd']:checked").length;

		if(userList == ""){
			alert("<spring:message code="lecture.message.assignment.rate.alert.resend.selectuser"/>");
		}else{
			if(confirm('<spring:message code="lecture.message.assignment.rate.alert.resend.confirm" arguments="'+chkLength+'"/>')){
				$('#userNoObj').val(userList);
				process("removeSend");	// cmd
			}
		}
	}

	/**
	 * 학습자 평가
	 */
	function editRate(stdNo) {
		var addContent  = "<iframe id='addRateFrame' name='addRateFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/mng/lecture/assignment/rateAsmtForm"/>"
			+ "?crsCreCd=${assignmentVO.crsCreCd}&amp;asmtSn=${assignmentVO.asmtSn}&amp;stdNo="+stdNo+"'/>";
		parent.modalBox.clear();
		parent.modalBox.addContents(addContent);
		parent.modalBox.resize(1000, 600);
		parent.modalBox.setTitle("<spring:message code="lecture.title.assignment.rate"/>");
		parent.modalBox.show();
	}

	/**
	 * 개별 성적 저장
	 */
	function addScore(no) {
		var ScoreObj = document.getElementsByName("score");
		var StdNoObj = document.getElementsByName("selStd");

		if(ScoreObj[no].value>100){
			alert('<spring:message code="lecture.message.common.alert.input.score100"/>');
			ScoreObj[no].value = "0";
			ScoreObj[no].focus();
			return;
		}
		
		if(ScoreObj[no].value ==""){
			alert('점수를 입력하세요');
			ScoreObj[no].focus();
			return;
		}

		$.getJSON( cUrl("/mng/lecture/assignment/addOffScore"),		// url
				{ 
				  "crsCreCd" : '${assignmentVO.crsCreCd}',
				  "asmtSn" : '${assignmentVO.asmtSn}',
				  "stdNo" : StdNoObj[no].value,
				  "score" : ScoreObj[no].value,
				  "sendCnt" : 1,
				  "rateYn" : "Y"
				},			// params
				processAddScoreCallback				// callback function
			);
	}

	//-- 전체 성적 저장
	function addScoreAll() {
		var ScoreObj = document.getElementsByName("score");
		var StdNoObj = document.getElementsByName("selStd");

		var strStdNo = "";
		var strScore = "";

		for(var i=0; i < ScoreObj.length; i++) {
			//-- 값이 들어온것만 잡는다.
			if(!isEmpty(ScoreObj[i].value)) {
				strStdNo = strStdNo+"|"+StdNoObj[i].value;
				strScore = strScore+"|"+ScoreObj[i].value;
			}
		}
		strStdNo = strStdNo.substring(1);
		strScore = strScore.substring(1);
		
		if(strStdNo == ""){
			alert('점수를 입력하세요');
			return;
		}

		$.getJSON( cUrl("/mng/lecture/assignment/addOffScoreAll"),		// url
				{ 
			  	  "crsCreCd" : '${assignmentVO.crsCreCd}',
			      "asmtSn" : '${assignmentVO.asmtSn}',
				  "sendCnt" : 1,
				  "strStdNo" : strStdNo,
				  "strScore" : strScore,
				  "rateYn" : "Y"
				},			// params
				processAddScoreCallback				// callback function
			);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processAddScoreCallback(resultDTO) {
		alert(resultDTO.message);

		if(resultDTO.result >= 0) {
			// 정상 처리
			listStudent(ItemDTO.curPage);
		} else {
			// 비정상 처리
		}
	}

	//-- 메시지 입력 폼 호출
	function messageForm(msgDivCd) {
		var userList = $("#StdSearch input[name='selStd']:checked").stringValues();
		if(userList == "") {
			alert("<spring:message code="common.message.nouser.message"/>");
			return;
		}
		var addContent  = "<iframe id='messageFrame' name='messageFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/mng/log/message/addMessagePop"/>"
			+ "?logMsgLogVO.msgDivCd="+msgDivCd+"&amp;logMsgTransLogVO.stdNoList="+userList+"'/>";
		parent.modalBox.clear();
		parent.modalBox.addContents(addContent);
		parent.modalBox.resize(720, 520);
		parent.modalBox.setTitle(getMessageTitle(msgDivCd));
		parent.modalBox.show();
	}
	
	/**
	 * 학습자 평가(코딩학습의 경우)
	 */
	function editCodeRate(stdNo) {

		var addContent  = "<iframe id='addRateFrame' name='addRateFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/mng/lecture/assignment/rateCodeAsmtForm"/>"
			+ "?crsCreCd=${assignmentVO.crsCreCd}&asmtSn=${assignmentVO.asmtSn}&stdNo="+stdNo+"'/>";
		parent.modalBox.clear();
		parent.modalBox.addContents(addContent);
		parent.modalBox.resize(1200, 800);
		parent.modalBox.setTitle("<spring:message code="lecture.title.assignment.rate"/>");
		parent.modalBox.show();
	}
</script>
