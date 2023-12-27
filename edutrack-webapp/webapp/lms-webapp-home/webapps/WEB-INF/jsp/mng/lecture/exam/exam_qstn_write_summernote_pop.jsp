<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="gubun" value="${gubun}"/>
<c:set var="examQuestionVO" value="${vo}" />

	<form id="examForm" name="examForm" onsubmit="return false" >
	<input type="hidden" name="crsCreCd" value="${vo.crsCreCd}" />
	<input type="hidden" name="examSn" value="${vo.examSn}" />
	<input type="hidden" name="examQstnSn" value="${vo.examQstnSn}" />
	<input type="hidden" name="rgtAnsr" value="${vo.rgtAnsr}" />
	<input type="hidden" name="qstnScore" value="${vo.qstnScore}" />
	<input type="hidden" name="attachImageSns" value="${vo.attachImageSns}" />
	<input type="hidden" name="editorYn" value="Y"/>
	<input type="hidden" name="qstnType" id="qstnType" value="${vo.qstnType}"/>
	<table summary="<spring:message code="lecture.title.exam.question.manage"/>" class="table table-bordered wordbreak">
		<colgroup>
			<col style="width:20%" />
			<col style="width:80%" />
		</colgroup>
		<tbody>
		<tr>
			<th><span style="color:red;">* </span><spring:message code="lecture.title.exam.question.title"/></th>
			<td>
				<input type="text" class="form-control input-sm" maxlength="200" lenMCheck="200" dispName="<spring:message code="lecture.title.exam.question.title"/>" isNull="N" name="title" value="${vo.title}"/>
			</td>
		</tr>
		<tr>
			<th><spring:message code="lecture.title.exam.question.no"/></th>
			<td>
				<input type="text" class="inputSpecial inputNumber form-control input-sm" id="qstnNo" style="width:50px;text-align:right;" dispName="<spring:message code="lecture.title.exam.question.no"/>" maxlength="9" isNull="N" lenCheck="9" name="qstnNo" value="${vo.qstnNo}" onfocus="this.select()"/>
			</td>
		</tr>
		<tr>
			<td colspan="2" style="padding:0px;">
				<div id="summernote" style="float:left; width:100%; margin: 0 auto;">${examQuestionVO.qstnCts}</div>
				<textarea name="qstnCts" id="contentTextArea"  class="sr-only">${examQuestionVO.qstnCts}</textarea>
			</td>
		</tr>
		<c:set var="checked1" value=""/><c:set var="checked2" value=""/><c:set var="checked3" value=""/><c:set var="checked4" value=""/><c:set var="checked5" value=""/>
		<c:if test="${examQuestionVO.rgtAnsr eq '1'}"><c:set var="checked1" value="checked"/></c:if>
		<c:if test="${examQuestionVO.rgtAnsr eq '2'}"><c:set var="checked2" value="checked"/></c:if>
		<c:if test="${examQuestionVO.rgtAnsr eq '3'}"><c:set var="checked3" value="checked"/></c:if>
		<c:if test="${examQuestionVO.rgtAnsr eq '4'}"><c:set var="checked4" value="checked"/></c:if>
		<c:if test="${examQuestionVO.rgtAnsr eq '5'}"><c:set var="checked5" value="checked"/></c:if>

		<tr class="qstnTypeK qstnItem" style="display:none">
			<th scope="row"><spring:message code="lecture.title.exam.question.item1"/></th>
			<td>
				<textarea name="empl1" style="height:30px;" class="form-control input-sm">${examQuestionVO.empl1}</textarea>
			</td>
		</tr>
		<tr class="qstnTypeK qstnItem" style="display:none">
			<th scope="row"><spring:message code="lecture.title.exam.question.item2"/></th>
			<td>
				<textarea name="empl2" style="height:30px;" class="form-control input-sm">${examQuestionVO.empl2}</textarea>
			</td>
		</tr>
		<tr class="qstnTypeK qstnItem" style="display:none">
			<th scope="row"><spring:message code="lecture.title.exam.question.item3"/></th>
			<td>
				<textarea name="empl3" style="height:30px;" class="form-control input-sm">${examQuestionVO.empl3}</textarea>
			</td>
		</tr>
		<tr class="qstnTypeK qstnItem" style="display:none">
			<th scope="row"><spring:message code="lecture.title.exam.question.item4"/></th>
			<td>
				<textarea name="empl4" style="height:30px;" class="form-control input-sm">${examQuestionVO.empl4}</textarea>
			</td>
		</tr>
		<tr class="qstnTypeK qstnItem" style="display:none">
			<th scope="row"><spring:message code="lecture.title.exam.question.item5"/></th>
			<td>
				<textarea name="empl5" style="height:30px;" class="form-control input-sm">${examQuestionVO.empl5}</textarea>
			</td>
		</tr>
		<tr class="qstnTypeK qstnItem" style="display:none">
			<th scope="row"><span style="color:red;">* </span><spring:message code="lecture.title.exam.question.rightanswer"/></th>
			<td>
				1.<input type="radio" name="answerK" value="1" style="border:0" ${checked1}	/>&nbsp;
				2.<input type="radio" name="answerK" value="2" style="border:0" ${checked2} />&nbsp;
				3.<input type="radio" name="answerK" value="3" style="border:0" ${checked3} />&nbsp;
				4.<input type="radio" name="answerK" value="4" style="border:0" ${checked4}	/>&nbsp;
				5.<input type="radio" name="answerK" value="5" style="border:0" ${checked5} />
			</td>
		</tr>


		<tr class="qstnTypeD qstnItem" style="display:none">
			<th scope="row"><span style="color:red;">* </span><spring:message code="lecture.title.exam.question.rightanswer"/></th>
			<td>
				<%-- <textarea name="answerD" style="height:30;" class="form-control input-sm">${examQuestionVO.rgtAnsr}</textarea> --%>
				<input type="text" name="answerD" class="form-control input-sm" value="${examQuestionVO.rgtAnsr}" /><br>
				<spring:message code="lecture.message.exam.question.msg1"/>&nbsp;<spring:message code="lecture.message.exam.question.msg2"/>
			</td>
		</tr>

		<c:set var="checkedO" value=""/><c:set var="checkedX" value=""/>
		<c:if test="${examQuestionVO.rgtAnsr eq 'O'}"><c:set var="checkedO" value="checked"/></c:if>
		<c:if test="${examQuestionVO.rgtAnsr eq 'X'}"><c:set var="checkedX" value="checked"/></c:if>
		<tr class="qstnTypeS qstnItem" style="display:none">
			<th scope="row"><span style="color:red;">* </span><spring:message code="lecture.title.exam.question.rightanswer"/></th>
			<td>
				<input type="radio" name="answerS" value="O" style="border:0" ${checkedO}/>&nbsp;O&nbsp;
				<input type="radio" name="answerS" value="X" style="border:0" ${checkedX}/>&nbsp;X
			</td>
		</tr>

		<tr class="qstnTypeJ qstnItem" style="display:none">
			<th scope="row"><span style="color:red;">* </span><spring:message code="lecture.title.exam.question.rightanswer"/></th>
			<td>
				<textarea name="answerJ" style="height:50px;" class="form-control input-sm">${examQuestionVO.rgtAnsr}</textarea>
			</td>
		</tr>
		<tr>
			<th scope="row"><spring:message code="lecture.title.exam.question.desc"/></th>
			<td>
				<textarea name="qstnExpl" style="height:40;" class="form-control input-sm">${examQuestionVO.qstnExpl}</textarea>
			</td>
		</tr>
		</tbody>
	</table>

	<div class="text-right">
		<c:if test="${gubun eq 'A'}">
		<a href="javascript:addQuestion()" class="btn btn-primary btn-sm"><spring:message code="button.add"/></a>
		</c:if>
		<c:if test="${gubun eq 'E'}">
		<a href="javascript:editQuestion()" class="btn btn-primary btn-sm"><spring:message code="button.add"/></a>
		<a href="javascript:delQuestion()" class="btn btn-warning btn-sm"><spring:message code="button.delete"/></a>
		</c:if>
		<a href="javascript:parent.modalBoxClose()" class="btn btn-default btn-sm"><spring:message code="button.close"/></a>
	</div>
	</form>
<script type="text/javascript">
	var summernote;
	var enableQstnNo;
	// 페이지 초기화
	$(document).ready(function() {
		enableQstnNo = Number($("#lastQstnNo", parent.subWorkFrame.document ).val()) +1;
		$('.inputNumber').inputNumber();	// 숫자만 입력되도록 설정.
		$('.inputSpecial').inputSpecial();  // 특수키가 입력이 들어오묜 false return;

		summernote = new $M.SummerNote({
			"editorId"			:	"summernote",
			"textareaId"		:	"contentTextArea",
			"repositoryCode"	:	"EXAM_QSTN",
			'organization' 		: 	"${USER_ORGCD}",
			"editorHeight"		:	"330px",
			"attachments"		:	$.parseJSON('${examQuestionVO.attachImagesJson}')
		});
		<c:if test="${gubun eq 'A'}">
		$("#qstnNo").val(enableQstnNo);
		</c:if>
		changeType();
	});

	function changeType() {
		var qstnType = $("#qstnType").val();
		$(".qstnItem").hide();
		$(".qstnType"+qstnType).show();
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


	function submitCheck(cmd) {
		$('#examForm').attr("action","/mng/lecture/exam/"+ cmd);
		if(!validate(document.examForm)) return false;
		if(Number($("#qstnNo").val()) > enableQstnNo ){
			alert('<spring:message code="lecture.message.exam.question.alert.input.enableqstnno" arguments="'+enableQstnNo+'"/>');
			return;
		}
		var _content = $("#summernote").summernote('code');
		if(_content == "<p><br></p>" || isEmpty(_content)){
			alert("<spring:message code="common.message.alert.input.cnts"/>");
			return;
		}
		//-- 내용 form에 setting
		$("#contentTextArea").val(_content);

		var qstnType = $("#qstnType").val();
		var f = document.examForm;
		var answerCnt = 0;

		if(parseInt(f["qstnNo"].value,10) <= 0) {
			alert('<spring:message code="lecture.message.exam.question.alert.input.no"/>');
			return;
		}

		//-- 문제 유형에 따른 벨리데이션
		if(qstnType == "J") {
			if(isEmpty(f["answerJ"].value)) {
				alert('<spring:message code="lecture.message.exam.question.alert.input.rightanswer"/>');
				return;
			}
			f["rgtAnsr"].value = f["answerJ"].value;
		} else if(qstnType == "K") {
			if(isEmpty(f["empl1"].value)) {
				alert('<spring:message code="lecture.message.exam.question.alert.input.item3"/>');
				return;
			}
			if(isEmpty(f["empl2"].value)) {
				alert('<spring:message code="lecture.message.exam.question.alert.input.item3"/>');
				return;
			}
			if(isEmpty(f["empl3"].value)) {
				alert('<spring:message code="lecture.message.exam.question.alert.input.item3"/>');
				return;
			}
/*
			if(isEmpty(f["empl4"].value)) {
				alert('<spring:message code="lecture.message.exam.question.alert.input.item4"/>');
				return;
			}
 */
			answerCnt = 0;
			if(f.answerK[0].checked == true) {
				answerCnt++;
				f["rgtAnsr"].value = '1';
			}
			if(f.answerK[1].checked == true) {
				answerCnt++;
				f["rgtAnsr"].value = '2';
			}
			if(f.answerK[2].checked == true) {
				answerCnt++;
				f["rgtAnsr"].value = '3';
			}
			if(f.answerK[3].checked == true) {
				if(isEmpty(f["empl4"].value)) {
					alert("<spring:message code="lecture.message.exam.question.alert.input.answer3"/>");
					return;
				}
				answerCnt++;
				f["rgtAnsr"].value = '4';
			}
			if(f.answerK[4].checked == true) {
				if(isEmpty(f["empl4"].value)) {
					alert("<spring:message code="lecture.message.exam.question.alert.input.answer3"/>");
					return;
				}
				if(isEmpty(f["empl5"].value)) {
					alert("<spring:message code="lecture.message.exam.question.alert.input.answer4"/>");
					return;
				}
				answerCnt++;
				f["rgtAnsr"].value = '5';
			}
			if(answerCnt <= 0) {
				alert("<spring:message code="lecture.message.exam.question.alert.check.rightanser"/>");
				return;
			}
		} else if(qstnType == "S") {
			answerCnt = 0;
			if(f.answerS[0].checked == true) {
				answerCnt++;
				f["rgtAnsr"].value = 'O';
			}
			if(f.answerS[1].checked == true) {
				answerCnt++;
				f["rgtAnsr"].value = 'X';
			}
			if(answerCnt <= 0) {
				alert("<spring:message code="lecture.message.exam.question.alert.check.rightanser"/>");
				return;
			}
		} else if(qstnType == "D") {
			if(isEmpty(f["answerD"].value)) {
				alert("<spring:message code="lecture.message.exam.question.alert.input.rightanswer"/>");
				return;
			}
			f["rgtAnsr"].value = f["answerD"].value;
		}
		/*
		if(isEmpty(f["examQuestionVO.qstnExpl"].value)) {
			alert("<spring:message code="lecture.message.exam.question.alert.input.desc"/>");
			return;
		}
		 */
		// 에디터 첨부파일 번호 목록 추출
		var _paramImages = summernote.getFileSnAll();
		$(':input:hidden[name=attachImageSns]').val(_paramImages);
		$('#examForm').ajaxSubmit(processCallback);
	}

	/**
	 * 시험 문제 등록
	 */
	function addQuestion() {
		submitCheck("addQuestion");
	}

	/**
	* 시험 문제 수정
	*/
	function editQuestion(){
		submitCheck("editQuestion");
	}


	/**
	 * 시험 문제 삭제
	 */
	function delQuestion() {
		if(confirm('<spring:message code="lecture.message.exam.question.confirm.delete"/>')) {
			$('#examForm').attr("action","/mng/lecture/exam/deleteQuestion");
			$('#examForm').ajaxSubmit(processCallback);
		} else {
			return;
		}
	}

</script>
