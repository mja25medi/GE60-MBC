<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="img_base" value="/img/home" />
<c:set var="gubun" value="${gubun}"/>
<c:set var="examQuestionVO" value="${vo}" />
<mhtml:class_html>
<mhtml:class_head>
	<mhtml:head_module daumeditor="y" />
</mhtml:class_head>

<mhtml:body>
	<form id="examForm" name="examForm" onsubmit="return false">
	<input type="hidden" name="crsCreCd" value="${vo.crsCreCd}" />
	<input type="hidden" name="examSn" value="${vo.examSn}" />
	<input type="hidden" name="examQstnSn" value="${vo.examQstnSn}" />
	<input type="hidden" name="rgtAnsr" value="${vo.rgtAnsr}" />
	<input type="hidden" name="subNo" value="${vo.subNo}" />
	<input type="hidden" name="attachImageSns" value="${vo.attachImageSns}" />
	<input type="hidden" name="editorYn" value="Y"/>
	<table class="table table-bordered">
		<colgroup>
			<col style="width:20%" />
			<col style="width:30%" />
			<col style="width:20%" />
			<col style="width:30%" />
		</colgroup>
		<tbody>
		<tr>
			<th scope="row"><spring:message code="lecture.title.exam.question.type"/></th>
			<td>
				<select name="examQuestionVO.qstnType" id="qstnType" onChange="changeType()" class="form-control input-sm">
				<c:forEach items="${qstnTypeList}" var="qstnType">
					<c:set var="qstnTypeNm" value="${qstnType.codeNm}"/>
					<c:forEach var="lang" items="${qstnType.codeLangList}">
						<c:if test="${lang.langCd eq LOCALEKEY }"><c:set var="qstnTypeNm" value="${lang.codeNm}"/></c:if>
					</c:forEach>
					<option value="${qstnType.codeCd}" <c:if test="${qstnType.codeCd eq examQuestionVO.qstnType}">selected</c:if> >${qstnTypeNm}</option>
				</c:forEach>
				</select>
			</td>
			<th scope="row"><spring:message code="lecture.title.exam.question.no"/></th>
			<td>
				<input type="text" class="inputSpecial inputNumber form-control input-sm" style="width:50px;text-align:right;" dispName="<spring:message code="lecture.title.exam.question.no"/>" maxlength="9" isNull="N" lenCheck="9" name="qstnNo" value="${vo.qstnNo}" onfocus="this.select()"/>
			</td>
		</tr>
		<tr>
			<th scope="row"><spring:message code="lecture.title.exam.question.title"/></th>
			<td colspan="3">
				<input type="text" class="form-control input-sm" dispName="<spring:message code="lecture.title.exam.question.title"/>" name="title" value="${vo.title}"/>
			</td>
		</tr>
		<tr>
			<td colspan="4" style="padding:0px;">
				<div id="daumeditor" style="float:left; width:100%; margin: 0 auto;"></div>
				<textarea name="qstnCts" id="contentTextArea"  class="sr-only">${vo.qstnCts}</textarea>
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
			<td colspan="3">
				<textarea name="empl1" style="height:30px;" class="form-control input-sm">${vo.empl1}</textarea>
			</td>
		</tr>
		<tr class="qstnTypeK qstnItem" style="display:none">
			<th scope="row"><spring:message code="lecture.title.exam.question.item2"/></th>
			<td colspan="3">
				<textarea name="empl2" style="height:30px;" class="form-control input-sm">${vo.empl2}</textarea>
			</td>
		</tr>
		<tr class="qstnTypeK qstnItem" style="display:none">
			<th scope="row"><spring:message code="lecture.title.exam.question.item3"/></th>
			<td colspan="3">
				<textarea name="empl3" style="height:30px;" class="form-control input-sm">${vo.empl3}</textarea>
			</td>
		</tr>
		<tr class="qstnTypeK qstnItem" style="display:none">
			<th scope="row"><spring:message code="lecture.title.exam.question.item4"/></th>
			<td colspan="3">
				<textarea name="empl4" style="height:30px;" class="form-control input-sm">${vo.empl4}</textarea>
			</td>
		</tr>
		<tr class="qstnTypeK qstnItem" style="display:none">
			<th scope="row"><spring:message code="lecture.title.exam.question.item5"/></th>
			<td colspan="3">
				<textarea name="empl5" style="height:30px;" class="form-control input-sm">${vo.empl5}</textarea>
			</td>
		</tr>
		<tr class="qstnTypeK qstnItem" style="display:none">
			<th scope="row"><spring:message code="lecture.title.exam.question.rightanswer"/></th>
			<td colspan="3">
				1.<input type="radio" name="answerK" value="1" style="border:0" ${checked1}	/>&nbsp;
				2.<input type="radio" name="answerK" value="2" style="border:0" ${checked2} />&nbsp;
				3.<input type="radio" name="answerK" value="3" style="border:0" ${checked3} />&nbsp;
				4.<input type="radio" name="answerK" value="4" style="border:0" ${checked4}	/>&nbsp;
				5.<input type="radio" name="answerK" value="5" style="border:0" ${checked5} />
			</td>
		</tr>


		<tr class="qstnTypeD qstnItem" style="display:none">
			<th scope="row"><spring:message code="lecture.title.exam.question.rightanswer"/></th>
			<td colspan="3">
				<textarea name="answerD" style="height:30;" class="form-control input-sm">${examQuestionVO.rgtAnsr}</textarea><br>
				<spring:message code="lecture.message.exam.question.msg1"/><br>
				&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="lecture.message.exam.question.msg2"/>
			</td>
		</tr>

		<c:set var="checkedO" value=""/><c:set var="checkedX" value=""/>
		<c:if test="${examQuestionVO.rgtAnsr eq 'O'}"><c:set var="checkedO" value="checked"/></c:if>
		<c:if test="${examQuestionVO.rgtAnsr eq 'X'}"><c:set var="checkedX" value="checked"/></c:if>
		<tr class="qstnTypeS qstnItem" style="display:none">
			<th scope="row"><spring:message code="lecture.title.exam.question.rightanswer"/></th>
			<td colspan="3">
				O.<input type="radio" name="answerS" value="O" style="border:0" ${checkedO}/>&nbsp;
				X.<input type="radio" name="answerS" value="X" style="border:0" ${checkedX}/>
			</td>
		</tr>

		<tr class="qstnTypeJ qstnItem" style="display:none">
			<th scope="row"><spring:message code="lecture.title.exam.question.rightanswer"/></th>
			<td colspan="3">
				<textarea name="answerJ" style="height:50px;" class="form-control input-sm">${examQuestionVO.rgtAnsr}</textarea>
			</td>
		</tr>
		<tr>
			<th scope="row"><spring:message code="lecture.title.exam.question.desc"/></th>
			<td colspan="3">
				<textarea name="qstnExpl" style="height:40;" class="form-control input-sm">${vo.qstnExpl}</textarea>
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
	// 페이지 초기화
	$(document).ready(function() {
		$M.CreateEditor({		// @see /js/daumeditor.js 참고
			"editorId"			:	"daumeditor",
			"formId"			:	"examForm",
			"textareaId"		:	"contentTextArea",
			"repositoryCode"	:	"EXAM_QSTN",
			"attachments"		:	$.parseJSON('${examQuestionVO.attachImagesJson}'),
			"editorHeight"		:	"250px"
		});

		//부모창 새로고침
		if("${refreshYn}" == "Y"){		//등록일 경우
			parent.location.reload();
			parent.modalBoxClose();
		}else if("${refreshYn}" == "D"){		//삭제일 경우
			parent.location.reload();
			parent.modalBoxClose();
		}
		$('.inputNumber').inputNumber();	// 숫자만 입력되도록 설정.
		$('.inputSpecial').inputSpecial();  // 특수키가 입력이 들어오묜 false return;
		changeType();
	});

	function changeType() {
		var qstnType = $("#qstnType > option:selected").val();
		$(".qstnItem").hide();
		$(".qstnType"+qstnType).show();
	}

	function submitCheck() {
		var qstnType = $("#qstnType > option:selected").val();
		var f = document.examForm;
		var answerCnt = 0;

		if(parseInt(f["qstnNo"].value,10) <= 0) {
			alert('<spring:message code="lecture.message.exam.question.alert.input.no"/>');
			return false;
		}

		//--  콘텐츠 벨리데이션 체크는 다음에디터에서...
		//var _content = $("#qstnCts").val();
		//if(isEmpty(_content)) {
		//	alert('<spring:message code="lecture.message.exam.question.alert.input.cnts"/>');
		//	return false;
		//}

		//-- 문제 유형에 따른 벨리데이션
		if(qstnType == "J") {
			if(isEmpty(f["answerJ"].value)) {
				alert('<spring:message code="lecture.message.exam.question.alert.input.rightanswer"/>');
				return false;
			}
			f["rgtAnsr"].value = f["answerJ"].value;
		} else if(qstnType == "K") {
			if(isEmpty(f["empl1"].value)) {
				alert('<spring:message code="lecture.message.exam.question.alert.input.item4"/>');
				return false;
			}
			if(isEmpty(f["empl2"].value)) {
				alert('<spring:message code="lecture.message.exam.question.alert.input.item4"/>');
				return false;
			}
			if(isEmpty(f["empl3"].value)) {
				alert('<spring:message code="lecture.message.exam.question.alert.input.item4"/>');
				return false;
			}
			if(isEmpty(f["empl4"].value)) {
				alert('<spring:message code="lecture.message.exam.question.alert.input.item4"/>');
				return false;
			}
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
				answerCnt++;
				f["rgtAnsr"].value = '4';
			}
			if(f.answerK[4].checked == true) {
				if(isEmpty(f["empl5"].value)) {
					alert("<spring:message code="lecture.message.exam.question.alert.input.answer4"/>");
					return false;
				}
				answerCnt++;
				f["rgtAnsr"].value = '5';
			}
			if(answerCnt <= 0) {
				alert("<spring:message code="lecture.message.exam.question.alert.check.rightanser"/>");
				return false;
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
				return false;
			}
		} else if(qstnType == "D") {
			if(isEmpty(f["answerD"].value)) {
				alert("<spring:message code="lecture.message.exam.question.alert.input.rightanswer"/>");
				return false;
			}
			f["rgtAnsr"].value = f["answerD"].value;
		}
		if(isEmpty(f["qstnExpl"].value)) {
			alert("<spring:message code="lecture.message.exam.question.alert.input.desc"/>");
			return false;
		}
		return true;
	}


	/**
	 * 시험 문제 등록
	 */
	function addQuestion() {
		if(submitCheck()) {
			$('#examForm').actio("/lec/exam/addQuestion");
			Editor.save();
		}
		return;
	}

	/**
	* 시험 문제 수정
	*/
	function editQuestion(){
		if(submitCheck()) {
			$('#examForm').actio("/lec/exam/editQuestion");
			Editor.save();
		}
		return;
	}


	/**
	 * 시험 문제 삭제
	 */
	function delQuestion() {
		if(confirm('<spring:message code="lecture.message.exam.question.confirm.delete"/>')) {
			$('#examForm').actio("/lec/exam/deleteQuestion");
			document.examForm.submit();
		} else {
			return;
		}
	}

	/**
	 * Editor.save()를 호출시 데이터 유효여부 검사 콜백함수. 유효할 경우 true 리턴.
	 * @function
	 * @param {Object} editor - 에디터에서 넘겨주는 editor 객체
	 * @returns {Boolean} 모든 데이터가 유효할 경우에 true
	 */
	function validForm(editor) {
		if(!validate(document.examForm)) return false;

		/* 본문 내용이 입력되었는지 검사하는 부분 */
		var _validator = new Trex.Validator();
		var _content = editor.getContent();
		if(!_validator.exists(_content)) {
			alert('<spring:message code="board.message.bbs.atcl.alert.input.cnts"/>');
			return false;
		}
		return true;
	}

	/**
	 * Editor.save() -> validForm(editor)를 통해 데이터가 유효하면 Form Summit을 위해 필드를
	 * 생성, 변경하기 위해 부르는 콜백함수로 컨텐츠 값과 첨부파일을 설정.
	 * 정상적인 경우에 true를 리턴하면 submit();
	 * @function
	 * @param {Object} editor - 에디터에서 넘겨주는 editor 객체
	 * @returns {Boolean} 정상적인 경우에 true
	 */
	function setForm(editor) {
		// 본문 전송을 위해 컨텐츠를 textarea로..
		$("#contentTextArea").val(editor.getContent());

		// 에디터 첨부파일 번호 목록 추출
		var _paramImages = Editor.getAttachmentIds(editor, 'image', true);

		$(':input:hidden[name=attachImageSns]').val(_paramImages);

		return true;
	}

</script>
</mhtml:body>
</mhtml:class_html>