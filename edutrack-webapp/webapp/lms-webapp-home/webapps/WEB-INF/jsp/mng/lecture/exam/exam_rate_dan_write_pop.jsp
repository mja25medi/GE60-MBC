<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>

	<form id="examForm" name="examForm" onsubmit="return false" method="post">
	<input type="hidden" name="crsCreCd" value="${examQuestionVO.crsCreCd}"/>
	<input type="hidden" name="examSn" value="${examQuestionVO.examSn}"/>
	<input type="hidden" name="stdNo" id="stdNos"/>
	<input type="hidden" name="getScores" id="getScores"/>
	<table summary="<spring:message code="lecture.title.exam.rate"/>" class="table table-bordered wordbreak">
		<colgroup>
			<col style="width:20%"/>
			<col style="width:80%"/>
		</colgroup>
		<tr>
			<th scope="row"><spring:message code="lecture.title.exam.question.select"/></th>
			<td>
				<select name="examQstnSn" id="examQstnSn" onchange="changeQstn();" class="form-control input-sm">
				<c:forEach var="item" items="${questionList}">
				<c:if test="${item.qstnType eq 'D' or item.qstnType eq 'J' }">
					<option value="${item.examQstnSn}" <c:if test="${item.examQstnSn eq examQstnSn}">selected</c:if>>(${item.qstnTypeNm })(응시 : ${item.stareCnt }명) ${item.qstnNo}. ${fn:substring(item.title,0,50)}<c:if test="${fn:length(item.title) > 50 }">..</c:if></option>
				</c:if>
				</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<th scope="row"><spring:message code="common.title.cnts"/></th>
			<td>
				${examQuestionVO.qstnCts}
			</td>
		</tr>
		<tr height="35">
			<th scope="row"><spring:message code="lecture.title.exam.question.rightanswer"/></th>
			<td>
				${examQuestionVO.rgtAnsr}
			</td>
		</tr><tr height="35">
			<th scope="row"><spring:message code="lecture.title.exam.question.desc"/></th>
			<td>
				${examQuestionVO.qstnExpl }
			</td>
		</tr>
	</table>
	<div class="text-right">
		<c:if test="${not empty stuStareList}">
		<a href="javascript:addStareScore()" class="btn btn-primary btn-sm"><spring:message code="button.socre.save"/></a>
		</c:if>
		<a href="javascript:parent.modalBoxClose()" class="btn btn-default btn-sm"><spring:message code="button.close"/></a>
	</div>
	<div style="overflow-y:auto;height:390px;margin-top:10px;">
		<table summary="" class="table table-bordered wordbreak">
			<colgroup>
				<col style="width:50px;"/>
				<col style="width:100px;"/>
				<col style="width:100px;"/>
				<col style="width:auto"/>
				<c:if test="${examQuestionVO.qstnType eq 'J' }">
				<col style="width:60px;"/>
				</c:if>
				<col style="width:160px;"/>
			</colgroup>
			<thead>
				<tr>
					<th scope="col"><spring:message code="common.title.no"/></th>
					<th scope="col"><spring:message code="user.title.userinfo.name"/></th>
					<th scope="col"><spring:message code="user.title.userinfo.userid"/></th>
					<th scope="col"><spring:message code="lecture.title.exam.question.useranswer"/></th>
					<c:if test="${examQuestionVO.qstnType eq 'J' }">
					<th scope="col">모사율</th>
					</c:if>
					<th scope="col"><spring:message code="lecture.title.exam.score"/></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${stuStareList}" varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${item.userNm}</td>
					<td>${item.userId}</td>
					<td>${item.rgtAnsr}</td>
					<%--completeStatus 로 완료 여부 확인, 완료 시 링크 삽입/표시 --%>
					<c:if test="${examQuestionVO.qstnType eq 'J' }">
					<td>
						<c:if test="${item.completeStatus eq 'Y'}">
							<a href="http://183.111.234.121:8082/ckplus/copykiller.jsp?uri=${item.copyRatioUri}&property_id=40" target="_blank">${item.dispTotalCopyRatio}</a>
						</c:if>
					</td>
					</c:if>
					<td>
						<input type="hidden" name="stdNoUsr" value="${item.stdNo}"/>
						<%--수강생 점수, data-value는 수강생이 응시했을 때의 문항 점수 --%>
						<input type="text" name="score" style="float:left;width:60px;text-align:right" value="${item.getScore}" data-value="${item.qstnScore}" class="inputNumber form-control input-sm" maxlength="5"  onfocus="this.select()" onkeyup="isChkMaxNumber(this,${item.qstnScore})" onblur="chkNumber(this,${item.qstnScore});"/>
						<span style="float:left;line-height:30px;"><spring:message code="common.title.score"/> / ${item.qstnScore} <spring:message code="common.title.score"/></span>
					</td>
				</tr>
				</c:forEach>
				<c:if test="${empty stuStareList}">
				<tr>
					<td colspan="5"><spring:message code="lecture.message.exam.stare.nostudent"/></td>
				</tr>
				</c:if>
			</tbody>
		</table>
	</div>
	</form>

<script type="text/javascript">

	$(document).ready(function() {
		$(".inputNumber").inputNumber();
	});

	function changeQstn() {
		var examQstnSn = $("#examQstnSn option:selected").val();
		document.location.href = cUrl("/mng/lecture/exam/editRateDanPop")+"?crsCreCd=${examQuestionVO.crsCreCd}${AMPERSAND}examSn=${examQuestionVO.examSn}${AMPERSAND}examQstnSn="+examQstnSn;
	}

	function addStareScore() {
		if(validationScore('score')) {
			$("#stdNos").val(arrayToString('stdNoUsr'));
			$("#getScores").val(arrayToString('score'));
			process("editRateDan");

		}
	}


	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		if(!validate(document.examForm)) return;
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
			parent.subWorkFrame.listStudent();
			//parent.modalBoxClose();
		} else {
			// 비정상 처리
		}
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

	function validationScore(objName) {
		obj = document.getElementsByName(objName);
		for(var i=0; i < obj.length; i++) {
			var stdQstnScore =  obj[i].getAttribute('data-value');
			if(isNull(stdQstnScore)){
				alert((i+1) + '번째 수강생 문항 점수에 문제가 있습니다. 반복되는 경우 재시험 설정바랍니다.');
				obj[i].focus();
				return;
			}
			if(parseInt(obj[i].value,10) >  parseInt(stdQstnScore,10)) {
				alert('배정된 점수 보다 높은 점수를 입력할 수 없습니다.');
				obj[i].focus();
				return false;
			}
		}
		return true;
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

</script>
