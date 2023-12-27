<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="examQbankQstnVO" value="${vo}"/>
<c:set var="gubun" value="${gubun}"/>
<mhtml:mng_html>
<mhtml:mng_head titleName="${MENUNAME}">
	<mhtml:head_module uploadify="y"/>
</mhtml:mng_head>

<mhtml:frm_body cssTag="background-color:#ffffff;">
	<form id="examQbankForm" name="examQbankForm" onsubmit="return false" >
	<input type="hidden" name="sbjCd" value="${vo.sbjCd }"/>
	<input type="hidden" name="ctgrCd" value="${vo.ctgrCd }" />
	<input type="hidden" name="qstnSn" value="${vo.qstnSn }" />
	<input type="hidden" name="qstnOdr" value="${vo.qstnOdr }"/>
	<input type="hidden" name="rgtAnsr" value="${vo.rgtAnsr }" />
	<input type="hidden" name="attachImageSns" value="${vo.attachImageSns }"/>

	<table summary="<spring:message code="lecture.title.exam.question.manage"/>" class="table table-bordered wordbreak">
		<colgroup>
			<col style="width:10%"/>
			<col style="width:40%"/>
			<col style="width:20%"/>
			<col style="width:30%"/>
		</colgroup>
		<tr>
			<th scope="row"><label for="crsNm"><spring:message code="lecture.title.exam.question.type"/></label></th>
			<td colspan="3">
				<select name="qstnType" id="qstnType" class="form-control input-sm" onChange="changeType()">
					<c:forEach var="item" items="${qstnTypeList}" varStatus="status">
					<option value="${item.codeCd}" <c:if test="${courseVO.qstnType eq item.codeCd}">checked</c:if> >${item.codeNm}</option>
					</c:forEach>				
				</select>
			</td>
		</tr>
		<tr>
			<th scope="row"><spring:message code="course.title.exambank.title"/></th>
			<td colspan="3"><input type="text" name="qstnTitle" value="${vo.qstnTitle }" maxlength="200" lenCheck="200" class="form-control input-sm" /></td>
		</tr>
		<tr>
			<td colspan="4">
				<textarea name="qstnCts" id="qstnCts" style="height:200px;" class="form-control input-sm">${vo.qstnCts }</textarea>
			</td>
		</tr>
		<tr class="qstnTypeK qstnType">
			<th scope="row"><spring:message code="course.title.exambank.item1"/></th>
			<td colspan="3"><textarea name="view1" lenCheck="1000" class="form-control input-sm">${vo.view1 }</textarea></td>
		</tr>
		<tr class="qstnTypeK qstnType">
			<th scope="row"><spring:message code="course.title.exambank.item2"/></th>
			<td colspan="3"><textarea name="view2" lenCheck="1000" class="form-control input-sm">${vo.view2 }</textarea></td>
		</tr>
		<tr class="qstnTypeK qstnType">
			<th scope="row"><spring:message code="course.title.exambank.item3"/></th>
			<td colspan="3"><textarea name="view3" lenCheck="1000" class="form-control input-sm">${vo.view3 }</textarea></td>
		</tr>
		<tr class="qstnTypeK qstnType">
			<th scope="row"><spring:message code="course.title.exambank.item4"/></th>
			<td colspan="3"><textarea name="view4" lenCheck="1000" class="form-control input-sm">${vo.view4 }</textarea></td>
		</tr>
		<tr class="qstnTypeK qstnType">
			<th scope="row"><spring:message code="course.title.exambank.item5"/></th>
			<td colspan="3"><textarea name="view5" lenCheck="1000" class="form-control input-sm">${vo.view5 }</textarea></td>
		</tr>
		<tr class="qstnTypeK qstnType">
			<th scope="row"><spring:message code="course.title.exambank.rightansr"/></th>
			<td colspan="3">
				<ul style="list-style-type: none;padding-left:0px;">
					<li style="float:left;margin-right:10px;">
						1.<input type="radio" name="answerK" value="1" style="border:0" <c:if test="${vo.rgtAnsr eq '1'}">checked="checked"</c:if>/>
					</li>
					<li style="float:left;margin-right:10px;">
						2.<input type="radio" name="answerK" value="2" style="border:0" <c:if test="${vo.rgtAnsr eq '2'}">checked="checked"</c:if> />
					</li>
					<li style="float:left;margin-right:10px;">
						3.<input type="radio" name="answerK" value="3" style="border:0" <c:if test="${vo.rgtAnsr eq '3'}">checked="checked"</c:if>/>
					</li>
					<li style="float:left;margin-right:10px;">
						4.<input type="radio" name="answerK" value="4" style="border:0" <c:if test="${vo.rgtAnsr eq '4'}">checked="checked"</c:if>/>
					</li>
					<li style="float:left;margin-right:10px;">
						5.<input type="radio" name="answerK" value="5" style="border:0" <c:if test="${vo.rgtAnsr eq '5'}">checked="checked"</c:if>/>
					</li>
				</ul>
			</td>
		</tr>
		<tr class="qstnTypeD qstnType">
			<th scope="row"><spring:message code="course.title.exambank.rightansr"/></th>
			<td colspan="3">
				<textarea name="answerD" class="form-control input-sm">${vo.rgtAnsr}</textarea><br>
				<spring:message code="course.message.exambank.msg1"/> <br>
				&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="course.message.exambank.msg2"/>
			</td>
		</tr>
		<tr class="qstnTypeS qstnType">
			<th scope="row"><spring:message code="course.title.exambank.rightansr"/></th>
			<td colspan="3">
				<ul style="list-style-type: none;padding-left:0px;">
					<li style="float:left;margin-right:10px;">
						O.<input type="radio" name="answerS" value="O" style="border:0" <c:if test="${vo.rgtAnsr eq 'O'}">checked="checked"</c:if>/>
					</li>
					<li style="float:left;margin-right:10px;">
						X.<input type="radio" name="answerS" value="X" style="border:0" <c:if test="${vo.rgtAnsr eq 'X'}">checked="checked"</c:if>/>
					</li>
				</ul>
			</td>
		</tr>
		<tr class="qstnTypeJ qstnType">
			<th scope="row"><spring:message code="course.title.exambank.rightansr"/></th>
			<td colspan="3">
				<textarea name="answerJ" class="form-control input-sm">${vo.rgtAnsr}</textarea>
			</td>
		</tr>
		<tr height="33">
			<th scope="row"><spring:message code="course.title.exambank.comment"/></th>
			<td colspan="3">
				<textarea name="qstnExpl" class="form-control input-sm" />
			</td>
		</tr>
	</table>
	<div class="text-right">
		<c:if test="${gubun eq 'A'}">
		<a href="javascript:addQuestion()" class="btn btn-primary btn-sm"><spring:message code="button.add"/> </a>
		</c:if>
		<c:if test="${gubun eq 'E'}">
		<a href="javascript:editQuestion()" class="btn btn-primary btn-sm"><spring:message code="button.add"/> </a>
		<a href="javascript:deleteQuestion()" class="btn btn-warning btn-sm"><spring:message code="button.delete"/> </a>
		</c:if>
		<a href="javascript:parent.modalBoxClose()" class="btn btn-default btn-sm"><spring:message code="button.close"/> </a>
	</div>
	</form>

<script type="text/javascript">

	//---- 페이지 초기화 함수
	$(document).ready(function() {

		changeType();
	});

	function changeType() {
		var qstnType = $("#qstnType > option:selected").val();
		$(".qstnType").hide();
		$(".qstnType"+qstnType).show();
	}

	function submitCheck() {
		var qstnType = $("#qstnType > option:selected").val();
		var f = document.examQbankForm;
		var answerCnt = 0;

		if(isEmpty($("#qstnCts").val())) {
			alert("<spring:message code="common.message.alert.input.cnts"/>");
			return false;
		}
		if(qstnType == "J") {
			if(jsByteLength(f["answerJ"].value) <= 0) {
				alert("<spring:message code="lecture.message.exam.question.alert.input.rightanswer"/>");
				return false;
			}
			if(jsByteLength(f["answerJ"].value) > 1000) {
				alert("<spring:message code="lecture.messgae.exam.question.alert.over.rightanser"/>");
				return false;
			}
			f["rgtAnsr"].value = f["answerJ"].value;
		} else if(qstnType == "K") {
			if(jsByteLength(f["view1"].value) <= 0) {
				alert("<spring:message code="lecture.message.exam.question.alert.input.item4"/>");
				return false;
			}
			if(jsByteLength(f["view1"].value) > 1000) {
				alert("<spring:message code="lecture.message.exam.question.alert.over.item1"/>");
				return false;
			}
			if(jsByteLength(f["view2"].value) <= 0) {
				alert("<spring:message code="lecture.message.exam.question.alert.input.item4"/>");
				return false;
			}
			if(jsByteLength(f["view2"].value) > 1000) {
				alert("<spring:message code="lecture.message.exam.question.alert.over.item2"/>");
				return false;
			}
			if(jsByteLength(f["view3"].value) <= 0) {
				alert("<spring:message code="lecture.message.exam.question.alert.input.item4"/>");
				return false;
			}
			if(jsByteLength(f["view3"].value) > 1000) {
				alert("<spring:message code="lecture.message.exam.question.alert.over.item3"/>");
				return false;
			}
			if(jsByteLength(f["view4"].value) <= 0) {
				alert("<spring:message code="lecture.message.exam.question.alert.input.item4"/>");
				return false;
			}
			if(jsByteLength(f["view4"].value) > 1000) {
				alert("<spring:message code="lecture.message.exam.question.alert.over.item4"/>");
				return false;
			}
			if(jsByteLength(f["view5"].value) > 1000) {
				alert("<spring:message code="lecture.message.exam.question.alert.over.item5"/>");
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
				if(jsByteLength(f["view5"].value) <= 0) {
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
			if(jsByteLength(f["answerD"].value) <= 0) {
				alert("<spring:message code="lecture.message.exam.question.alert.input.rightanswer"/>");
				return false;
			}
			if(jsByteLength(f["answerD"].value) > 1000) {
				alert("<spring:message code="lecture.message.exam.question.alert.over.rightanswer"/>");
				return false;
			}
			f["rgtAnsr"].value = f["answerD"].value;
		}
		if(jsByteLength(f["qstnExpl"].value) <= 0) {
			alert("<spring:message code="lecture.message.exam.question.alert.input.desc"/>");
			return false;
		}
		if(jsByteLength(f["qstnExpl"].value) > 1000) {
			alert("<spring:message code="lecture.message.exam.question.alert.over.desc"/>");
			return false;
		}
		return true;
	}

	function addQuestion() {
		if(!submitCheck()) return;
		process("addQuestion");
	}

	function editQuestion() {
		if(!submitCheck()) return;
		process("editQuestion");
	}

	function deleteQuestion() {
		if(confirm("<spring:message code="lecture.message.exam.question.confirm.delete"/>"))
			process("deleteQuestion");
	}

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		$('#examQbankForm').attr("action","/mng/course/examQbank" + cmd);
		$('#examQbankForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		alert(resultDTO.message);
		if(resultDTO.result >= 0) {
			// 정상 처리
			parent.listQuestion('1');
			parent.modalBoxClose();
		} else {
			// 비정상 처리
		}
	}
</script>
</mhtml:frm_body>
</mhtml:mng_html>