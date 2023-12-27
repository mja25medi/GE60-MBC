<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="examQuestionListVO" value="${examQuestionListVO}"/>
	<form id="examForm" name="examForm" onsubmit="return false">
	<input type="hidden" name="crsCreCd" value="${vo.crsCreCd }" />
	<input type="hidden" name="examSn" value="${vo.examSn }" />
	<input type="hidden" name="qstnNoSort"  id="qstnNoSort" value="${vo.qstnNoSort }"/>
	<input type="hidden" name="examQstnSnSort"  id="examQstnSnSort" value="${vo.examQstnSnSort }"/>
	<table summary="<spring:message code="lecture.title.exam.question.manage"/>" class="table table-bordered wordbreak">
		<colgroup>
			<col style="width:50px;">
			<col style="width:auto;">
			<col style="width:150px;">
		</colgroup>
		<thead>
			<tr>
				<th scope="col"><spring:message code="common.title.no"/></th>
				<th scope="col"><spring:message code="lecture.title.exam.question"/></th>
				<th scope="col"><spring:message code="lecture.title.exam.question.type"/></th>
			</tr>
		</thead>
		<tbody>
			<c:set var="qstnCnt" value="0"/>
			<c:set var="lsitStart" value="N"/>
			<c:set var="lastQstnNo" value="0"/>
			<c:forEach var="item" items="${examQuestionListVO}" varStatus="status">

			<tr>
				<input type="hidden" name="examQstnSn" value="${item.examQstnSn}"/>
				<td class="text-right"><input type="text" style="width:50px;text-align:right;" name="qstnNo" value="${item.qstnNo}" class="inputNumber form-control input-sm" /></td>
				<td class="wordbreak">${item.title}</td>
				<td><meditag:codename code="${item.qstnType}" category="EXAM_QSTN_TYPE"/></td>
			</tr>
			<c:if test="${status.last }"><c:set var="lastQstnNo" value="${item.qstnNo}"/></c:if>
			</c:forEach>
			<c:if test="${empty examQuestionListVO}">
			<tr>
				<td colspan="5"><spring:message code="lecture.message.exam.question.nodata"/></td>
			</tr>
			</c:if>
		</tbody>
	</table>
	<input type="hidden" id="lastQstnNo" name="lastQstnNo" value="${fn:length(examQuestionListVO) }"/>
	<div class="text-right" style="margin-top:10px;">
		<button class="btn btn-primary btn-sm" onclick="sortQstn()"><spring:message code="button.add"/></button>
		<button class="btn btn-default btn-sm" onclick="parent.modalBoxClose();"><spring:message code="button.close"/></button>
	</div>
	</form>
<script type="text/javascript">

$(document).ready(function() {

	$('.inputNumber').inputNumber();	// 숫자만 입력되도록 설정.
	$('.inputSpecial').inputSpecial();  // 특수키가 입력이 들어오묜 false return;

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
		parent.subWorkFrame.listQuestion();
		parent.modalBoxClose();
	} else {
		// 비정상 처리
	}
}

function sortQstn(){
	var qstnSn = $('#examForm').find('input[name=examQstnSn]');
	var qstnNo = $('#examForm').find('input[name=qstnNo]');
	var enableQstnNo = $("#lastQstnNo").val();
	var qstnNoArray = new Array();
	var qstnSnList = "";
	var qstnNoList = "";
	
	for(var i=0; i<qstnSn.length; i++){
		if(qstnNo[i].value == ""){
			alert("<spring:message code="lecture.message.exam.question.alert.input.no"/>");
			qstnNo[i].focus();
			return;
		}
		if(Number(qstnNo[i].value) > enableQstnNo ){
			alert('<spring:message code="lecture.message.exam.question.alert.input.enableqstnno" arguments="'+enableQstnNo+'"/>');
			return;
		}
		if(i==0) {
			qstnSnList = qstnSn[i].value;
			qstnNoList = qstnNo[i].value;
		} else {
			qstnSnList = qstnSnList+'|'+qstnSn[i].value;
			qstnNoList = qstnNoList+'|'+qstnNo[i].value;
		}
		qstnNoArray[i] = qstnNo[i].value;
	}
	//qstnNoArray.sort();

	qstnNoArray.sort(function(left,right){
        return left - right;
    });

	for(var chk=0; chk<qstnNoArray.length;chk++){
		if(chk == 0){
			if("1" != qstnNoArray[chk]) {
				alert("<spring:message code="lecture.message.exam.question.alert.input.sortno"/>");
				return;
			}
		} else {
			if(qstnNoArray[chk-1] != qstnNoArray[chk]){
				if( (Number(qstnNoArray[chk]) - Number(qstnNoArray[chk-1]) ) != 1 ){
					alert("<spring:message code="lecture.message.exam.question.alert.input.sortno"/>");
					return;
				}

			}
		}

	}
	$("#examQstnSnSort").val(qstnSnList);
	$("#qstnNoSort").val(qstnNoList);

	process("sortQuestion");
}

</script>
