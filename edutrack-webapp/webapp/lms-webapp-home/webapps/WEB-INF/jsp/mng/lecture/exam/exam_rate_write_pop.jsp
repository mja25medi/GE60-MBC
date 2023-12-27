<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="examStareVO" value="${vo}"/>

	<form id="examForm" name="examForm" onsubmit="return false" method="post">
	<input type="hidden" name="crsCreCd" value="${vo.crsCreCd }" />
	<input type="hidden" name="examSn" value="${vo.examSn }" />
	<input type="hidden" name="stdNo" value="${vo.stdNo }" />
	<input type="hidden" name="rateYn" value="Y"/>
	<input type="hidden" name="getScores" value="${vo.getScores }" />
	<input type="hidden" name="totGetScore" value="${vo.totGetScore }" />
	<table summary="<spring:message code="lecture.title.exam.rate"/>" class="table table-bordered wordbreak">
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
				<th scope="row"><spring:message code="lecture.title.exam.laststaredate"/></th>
				<td>
					<meditag:dateformat type="8" delimeter="." property="${studentVO.enrlEndDttm}"/>
					<%-- <meditag:dateformat name="startDttm" name="examForm" type="1" delimeter="."/> ~ <meditag:dateformat name="endDttm" name="examForm" type="1" delimeter="."/> --%>
					<%-- <meditag:dateformat name="examStareTm" name="examForm" type="1" delimeter="."/> --%>
				</td>
				<th scope="row"><spring:message code="lecture.title.exam.answer.cnt"/></th>
				<td>
					 ${examStareVO.stareCnt}
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="lecture.title.exam.answer.commnet"/></th>
				<td colspan="3">
					<textarea name="atchCts" style="height:40" class="form-control input-sm">${examStareVO.atchCts }</textarea>
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="student.title.student.getscore"/></th>
				<td colspan="3">
					<input type="text" name="getTotScore" id="getTotScore" value="" readonly="readonly" style="width:60px;text-align:right" class="form-control input-sm"/>
				</td>
			</tr>
		</tbody>
	</table>
	<div class="text-right">
		<a href="javascript:addStareScore()" class="btn btn-primary btn-sm"><spring:message code="button.ok.rate"/></a>
		<a href="javascript:parent.modalBoxClose();" class="btn btn-default btn-sm"><spring:message code="button.close"/></a>
	</div>

	<div style="overflow-y:auto;height:350px;margin-top:10px;">
	<ul class="list-group">
	<c:forEach items="${questionList}" var="item" varStatus="status">
		<li class="list-group-item">
			<p style="word-break:break-all;word-wrap: break-word;"">${status.count}. ${item.title} [<meditag:codename code="${item.qstnType}" category="EXAM_QSTN_TYPE" />] </p>
			<div class="well well-sm" style="margin-top:10px;">
				<span style="margin-bottom:10px;word-break:break-all;word-wrap: break-word;">${item.qstnCts}</span>
      							<c:if test="${item.qstnType eq 'K'}">
				<ul style="list-style-type:none;padding-left:10px ;line-height:25px;">
					<c:if test="${not empty item.empl1}"><li class="wordbreak">1. ${item.empl1}</li></c:if>
					<c:if test="${not empty item.empl2}"><li class="wordbreak">2. ${item.empl2}</li></c:if>
					<c:if test="${not empty item.empl3}"><li class="wordbreak">3. ${item.empl3}</li></c:if>
					<c:if test="${not empty item.empl4}"><li class="wordbreak">4. ${item.empl4}</li></c:if>
					<c:if test="${not empty item.empl5}"><li class="wordbreak">5. ${item.empl5}</li></c:if>
				</ul>
				</c:if>
				<table style="width:100%;border-top:1px solid gray;background-color:#fff;margin-top:10px;word-break:break-all;word-wrap: break-word;">
					<colgroup>
						<col style="width:15%;"/>
						<col />
					</colgroup>
					<tr>
						<td style="border:1px solid gray;padding:5px;"><spring:message code="lecture.title.exam.question.rightanswer"/></td>
						<td style="border:1px solid gray;padding:5px;">
							<c:if test="${item.qstnType eq 'D'}">${fn:replace(item.rgtAnsr, '|', ',')}</c:if>
							<c:if test="${item.qstnType ne 'D'}">${item.rgtAnsr }</c:if>
						</td>
					</tr>
					<tr>
						<td style="border:1px solid gray;padding:5px;"><spring:message code="lecture.title.exam.question.useranswer"/></td>
						<td style="border:1px solid gray;padding:5px;">${stareInfo[item.examQstnSn]['answer']}</td>
					</tr>
					<tr>
						<td style="border:1px solid gray;padding:5px;"><spring:message code="lecture.title.exam.score"/></td>
						<td style="border:1px solid gray;padding:5px;">
							<input type="text" class="inputNumber form-control input-sm" name="getScore" onfocus="this.select()" onkeyup="isChkMaxNumber(this,${item.qstnScore});sumScore();"  onblur="chkNumber(this,${item.qstnScore});"  maxlength="5" style="width:60px;text-align:right;float:left;" value="${stareInfo[item.examQstnSn]['score']}"/>
							<span style="margin-left:10px;line-height:30px;">  / ${item.qstnScore}</span>
							<input type="hidden" name="baseScore" value="${item.qstnScore}"/>
						</td>
					</tr>
					<tr>
						<td style="border:1px solid gray;padding:5px;"><spring:message code="lecture.title.exam.question.desc"/></td>
						<td style="border:1px solid gray;padding:5px;">${item.qstnExpl}</td>
					</tr>
				</table>
			</div>
		</li>
	</c:forEach>
	</ul>
	</form>

<script type="text/javascript">

	$(document).ready(function() {
		$(".inputNumber").inputNumber();
		sumScore();
	});

	function sumScore() {
		var totScore = 0;
		var getScoreObj = document.getElementsByName("getScore");
		for(var i=0; i < getScoreObj.length; i++) {
			var getScore = getScoreObj[i].value;
			if(getScore == "") getScore = "0";
			totScore += parseFloat(getScore);
		}
		var returnScore = totScore.toString();
		$("#getTotScore").val(parseFloat(returnScore).toFixed(1));
	}

	function addStareScore() {
		var connYn = '${examVO.connYn}';
		if(connYn == 'Y'){
			//alert('<spring:message code="lecture.message.evaluation.complete.warning"/>');
			//return;
		}

		var getScoreObj = document.getElementsByName("getScore");
		var baseScoreObj = document.getElementsByName("baseScore");

		var strGetScore = "";
		var totalScore = 0;

		for(var i=0; i < getScoreObj.length; i++) {
			if(isEmpty(getScoreObj[i].value)) {
				alert(getCommonMessage("lecture.message.exam.rate.input.score", (i+1)));
				getScoreObj[i].focus();
				return;
			}
			if(parseFloat(getScoreObj[i].value) > parseFloat(baseScoreObj[i].value)) {
				alert(getCommonMessage("lecture.message.exam.rate.overscore", (i+1)));
				getScoreObj[i].focus();
				return;
			}
			strGetScore = strGetScore+"@#"+getScoreObj[i].value;
			totalScore = totalScore + parseFloat(getScoreObj[i].value,10);
		}
		strGetScore = strGetScore+"@#";
		document.examForm["getScores"].value = strGetScore;
		document.examForm["totGetScore"].value = totalScore;
		process("addStareRate");
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
			parent.modalBoxClose();
		} else {
			// 비정상 처리
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
</script>
