<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<%pageContext.setAttribute("crlf", "\n");%>
<c:set var="assignmentSendVO" value="${assignmentSendVO}"/>
<c:set var="assignmentSubVO" value="${assignmentSubVO}"/>
	<form id="assignmentForm" name="assignmentForm" onsubmit="return false" >
	<input type="hidden" name="crsCreCd" value="${assignmentSendVO.crsCreCd }" />
	<input type="hidden" name="asmtSn" value="${assignmentSendVO.asmtSn }" />
	<input type="hidden" name="asmtSubSn" value="${assignmentSendVO.asmtSubSn }" />
	<input type="hidden" name="stdNo" value="${assignmentSendVO.stdNo }" />
	<table summary="<spring:message code="lecture.title.assignment.send.info"/>" class="table table-bordered wordbreak">
		<colgroup>
			<col style="width:20%"/>
			<col style="width:30%"/>
			<col style="width:20%"/>
			<col style="width:30%"/>
		</colgroup>
		<tbody>
			<tr>
				<th scope="row"><spring:message code="user.title.userinfo.name"/></th>
				<td>
					${studentVO.userNm}
				</td>
				<th scope="row"><spring:message code="user.title.userinfo.userid"/></th>
				<td>
					${studentVO.userId}
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="lecture.title.assignment.send.lastdate"/></th>
				<td>
					<meditag:dateformat type="8" delimeter="." property="${assignmentSendVO.modDttm}"/>
				</td>
				<th scope="row"><spring:message code="lecture.title.assignment.send.cnt"/></th>
				<td>
					 ${assignmentSendVO.sendCnt} <spring:message code="common.title.times.postfix"/>
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="lecture.title.assignment.send.comment"/></th>
				<td colspan="3">
					<textarea name="atchCts" style="height:40" class="form-control input-sm">${assignmentSendVO.atchCts}</textarea>
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="lecture.title.assignment.score"/></th>
				<td colspan="3">
					<input type="text" name="score" maxlength="5" lenCheck="5" isNull='N' style="float:left;width:60px;text-align:right;" value="${assignmentSendVO.score}" class="inputNumber form-control input-sm" onfocus="this.select()" onkeyup="isChkMaxNumber(this,100)" onblur="isChkScore(this,100)"/>
					<span style="float:left;line-height:30px;"><spring:message code="common.title.score"/></span>
				</td>
			</tr>
		</tbody>
	</table>
	<div class="text-right" style="margin-bottom:5px;">
		<a href="javascript:addSendRate()" class="btn btn-primary btn-sm"><spring:message code="button.ok.rate"/></a>
		<a href="javascript:parent.modalBoxClose()" class="btn btn-default btn-sm"><spring:message code="button.close"/></a>
	</div>

	<table summary="<spring:message code="lecture.title.assignment.desc"/>" class="table table-bordered wordbreak">
		<colgroup>
			<col style="width:20%"/>
			<col style="width:80%"/>
		</colgroup>
		<tr>
			<th scope="row"><spring:message code="lecture.title.assignment.name"/></th>
			<td class="wordbreak">
				${vo.asmtTitle}
			</td>
		</tr>
		<tr>
			<th scope="row"><spring:message code="common.title.atachfile"/></th>
			<td class="wordbreak">
				<c:forEach var="file" items="${vo.attachFiles}"><p>${file.downloadTag}</p></c:forEach>
			</td>
		</tr>
		<tr>
			<th scope="row"><spring:message code="lecture.title.assignment.question.desc"/></th>
			<td class="wordbreak">
				${vo.asmtCts}
			</td>
		</tr>
	</table>
	<table summary="<spring:message code="lecture.title.assignment.question"/>" class="table table-bordered wordbreak">
		<colgroup>
			<col style="width:20%"/>
			<col style="width:80%"/>
		</colgroup>
		<tr>
			<th scope="row"><spring:message code="lecture.title.assignment.question.title"/></th>
			<td class="wordbreak">
				${assignmentSubVO.asmtTitle}
			</td>
		</tr>
		<tr>
			<th scope="row"><spring:message code="common.title.atachfile"/></th>
			<td class="wordbreak">
				<c:forEach var="file" items="${assignmentSubVO.attachFiles}"><p>${file.downloadTag}</p></c:forEach>
			</td>
		</tr>
		<tr>
			<th scope="row"><spring:message code="common.title.cnts"/></th>
			<td class="wordbreak">
				${assignmentSubVO.asmtCts}
			</td>
		</tr>
	</table>
	<table summary="<spring:message code="lecture.title.assignment.desc"/>" class="table table-bordered wordbreak">
		<colgroup>
			<col style="width:20%"/>
			<col style="width:80%"/>
		</colgroup>
		<tr>
			<th scope="row"><spring:message code="lecture.title.assignment.send.name"/></th>
			<td  class="wordbreak">
				${assignmentSendVO.sendTitle}
			</td>
		</tr>
		<tr>
			<th scope="row"><spring:message code="lecture.title.assignment.send.file"/></th>
			<td class="wordbreak">
				<c:forEach var="file" items="${assignmentSendVO.attachFiles}"><p>${file.downloadTag}</p></c:forEach>
			</td>
		</tr>
		<tr>
			<th scope="row"><spring:message code="lecture.title.assignment.send.cnts"/></th>
			<td class="wordbreak">
				${fn:replace(assignmentSendVO.sendCts,crlf,"<br/>")}
			</td>
		</tr>
	</table>
	<br>
	</form>

<script type="text/javascript">

	//첨부파일 목록
	var atchFiles;
	var atchFilesSend;

	$(document).ready(function() {
		$(".inputNumber").inputNumber();
	});


	function addSendRate() {
		var fm = document.assignmentForm;
		//평가완료를 하면 과제제출을 할 수 없다.
		if(!confirm('<spring:message code="lecture.message.asmt.confirm.score.complete"/>')){
			return;
		}

		var score = parseInt(fm["score"].value,10);
		if(score > 100) {
			alert('<spring:message code="lecture.message.common.alert.input.score100"/>');
			fm["score"].focus();
			return;
		}
		process("rateAsmt");
	}

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		if(!validate(document.assignmentForm)) return;
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
			parent.subWorkFrame.listStudent();
			parent.modalBoxClose();
		} else {
			// 비정상 처리
		}
	}



</script>
