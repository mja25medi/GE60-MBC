<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="eduResultVO" value="${vo}"/>
<c:set var="gubun" value="${gubun}"/>
<mhtml:class_html>
<mhtml:class_head>

</mhtml:class_head>

<mhtml:body>
	<p><spring:message code="student.message.result.message.batchscore.check"/></p>
	<table summary="<spring:message code="student.title.result.manage"/>" class="table table-bordered wordbreak">
		<colgroup>
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
			<col style="width:80px;"/>
			<col style="width:56px;"/>
		</colgroup>
		<thead>
			<tr>
				<c:if test="${createCourseVO.prgrRatio > 0}">
				<th scope="col"><input type="checkbox" name="prgrChk" id="prgrChk" checked></th>
				</c:if>
				<c:if test="${createCourseVO.attdRatio > 0}">
				<th scope="col"><input type="checkbox" name="attdChk" id="attdChk" checked></th>
				</c:if>
				<c:if test="${createCourseVO.examRatio > 0}">
				<th scope="col"><input type="checkbox" name="examChk" id="examChk" checked></th>
				</c:if>
				<c:if test="${createCourseVO.asmtRatio > 0}">
				<th scope="col"><input type="checkbox" name="asmtChk" id="asmtChk" checked></th>
				</c:if>
				<c:if test="${createCourseVO.forumRatio > 0}">
				<th scope="col"><input type="checkbox" name="forumChk" id="forumChk" checked></th>
				</c:if>
				<c:if test="${createCourseVO.joinRatio > 0}">
				<th scope="col"><input type="checkbox" name="joinChk" id="joinChk" checked></th>
				</c:if>
				<c:if test="${createCourseVO.etcRatio > 0}">
				<th scope="col"><input type="checkbox" name="etcChk" id="etcChk" checked></th>
				</c:if>
				<th scope="col" rowspan="2"><spring:message code="student.title.result.totalscore"/><br>(100%)</th>
				<th scope="col" rowspan="2"><spring:message code="common.title.add"/></th>
			</tr>
			<tr>
				<c:if test="${createCourseVO.prgrRatio > 0}">
				<th scope="col"><spring:message code="course.title.course.ratio.progress"/><br>(${createCourseVO.prgrRatio}%)</th>
				</c:if>
				<c:if test="${createCourseVO.attdRatio > 0}">
				<th scope="col"><spring:message code="course.title.course.ratio.attend"/><br>(${createCourseVO.attdRatio}%)</th>
				</c:if>
				<c:if test="${createCourseVO.examRatio > 0}">
				<th scope="col"><spring:message code="course.title.course.ratio.exam"/><br>(${createCourseVO.examRatio}%)</th>
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
				<th scope="col"><spring:message code="course.title.course.ratio.etc"/><br>(${createCourseVO.etcRatio}%)</th>
				</c:if>
			</tr>
		</thead>
		<tbody>
			<c:set var="fontColor" value="#DFF6FE"/>
			<c:if test="${item.resultYn eq 'Y'}"><c:set var="fontColor" value="#DFF6FE"/></c:if>
			<tr>
				<c:if test="${createCourseVO.prgrRatio > 0}">
				<td class="text-center"><input type="text" class="inputNumber form-control input-sm" onfocus="this.select()" id="prgrScore" style="width:50px;background-color:${fontColor};text-align:right;" value='0.0' onBlur="checkTotalScore()" onkeyup="isChkMaxNumber(this,${createCourseVO.prgrRatio})"/></td>
				</c:if>
				<c:if test="${createCourseVO.attdRatio > 0}">
				<td class="text-center"><input type="text" class="inputNumber form-control input-sm" onfocus="this.select()" id="attdScore" style="width:50px;background-color:${fontColor};text-align:right;" value='0.0' onBlur="checkTotalScore()" onkeyup="isChkMaxNumber(this,${createCourseVO.attdRatio})"/></td>
				</c:if>
				<c:if test="${createCourseVO.examRatio > 0}">
				<td class="text-center"><input type="text" class="inputNumber form-control input-sm" onfocus="this.select()" id="examScore" style="width:50px;background-color:${fontColor};text-align:right;" value='0.0' onBlur="checkTotalScore()" onkeyup="isChkMaxNumber(this,${createCourseVO.examRatio})"/></td>
				</c:if>
				<c:if test="${createCourseVO.asmtRatio > 0}">
				<td class="text-center"><input type="text" class="inputNumber form-control input-sm" onfocus="this.select()" id="asmtScore" style="width:50px;background-color:${fontColor};text-align:right;" value='0.0' onBlur="checkTotalScore()" onkeyup="isChkMaxNumber(this,${createCourseVO.asmtRatio})"/></td>
				</c:if>
				<c:if test="${createCourseVO.forumRatio > 0}">
				<td class="text-center"><input type="text" class="inputNumber form-control input-sm" onfocus="this.select()" id="forumScore" style="width:50px;background-color:${fontColor};text-align:right;" value='0.0' onBlur="checkTotalScore()" onkeyup="isChkMaxNumber(this,${createCourseVO.forumRatio})"/></td>
				</c:if>
				<c:if test="${createCourseVO.joinRatio > 0}">
				<td class="text-center"><input type="text" class="inputNumber form-control input-sm" onfocus="this.select()" onfocus="this.select()" id="joinScore" style="width:50px;background-color:${fontColor};text-align:right;" value='0.0' onBlur="checkTotalScore()" onkeyup="isChkMaxNumber(this,${createCourseVO.joinRatio})"/></td>
				</c:if>
				<c:if test="${createCourseVO.etcRatio > 0}">
				<td class="text-center"><input type="text" class="InputNumber form-control input-sm" onfocus="this.select()" id="etcScore" style="width:50px;background-color:${fontColor};text-align:right;" value='0.0' onBlur="checkTotalScore()" onkeyup="isChkMaxNumber(this,${createCourseVO.etcRatio})"/></td>
				</c:if>
				<td><input type="text" class="inputNumber form-control input-sm" onfocus="this.select()" id="totScore" style="width:50px;background-color:${fontColor};text-align:right;" value='' readonly="readonly" onBlur="checkTotalScore()"/></td>
				<td class="text-center">
					<a href="javascript:setResult();" class="btn btn-primary btn-sm" ><spring:message code="button.add"/></a>
					<c:if test="${createCourseVO.prgrRatio eq 0}"><input type="hidden" name="prgrScore" value="0"/></c:if>
					<c:if test="${createCourseVO.attdRatio eq 0}"><input type="hidden" name="attdScore" value="0"/></c:if>
					<c:if test="${createCourseVO.examRatio eq 0}"><input type="hidden" name="examScore" value="0"/></c:if>
					<c:if test="${createCourseVO.asmtRatio eq 0}"><input type="hidden" name="asmtScore" value="0"/></c:if>
					<c:if test="${createCourseVO.forumRatio eq 0}"><input type="hidden" name="forumScore" value="0"/></c:if>
					<c:if test="${createCourseVO.joinRatio eq 0}"><input type="hidden" name="joinScore" value="0"/></c:if>
					<c:if test="${createCourseVO.etcRatio eq 0}"><input type="hidden" name="etcScore" value="0"/></c:if>
				</td>
			</tr>
		</tbody>
	</table>
	<div class="text-right">
        <a href="javascript:parent.modalBoxClose()" class="btn btn-default btn-sm"><spring:message code="button.close"/></a>
    </div>
</mhtml:body>

<script type="text/javascript">
function checkTotalScore() {
	var ObjPrgrScore = document.getElementById("prgrScore");
	var ObjAttdScore = document.getElementById("attdScore");
	var ObjExamScore = document.getElementById("examScore");
	var ObjAsmtScore = document.getElementById("asmtScore");
	var ObjForumScore = document.getElementById("forumScore");
	var ObjJoinScore = document.getElementById("joinScore");
	var ObjEtcScore = document.getElementById("etcScore");
	var ObjTotScore = document.getElementById("totScore");

	chkNumber(ObjPrgrScore,${createCourseVO.prgrRatio});
	chkNumber(ObjAttdScore,${createCourseVO.attdRatio});
	chkNumber(ObjExamScore,${createCourseVO.examRatio});
	chkNumber(ObjAsmtScore,${createCourseVO.asmtRatio});
	chkNumber(ObjForumScore,${createCourseVO.forumRatio});
	chkNumber(ObjJoinScore,${createCourseVO.joinRatio});
	chkNumber(ObjEtcScore,${createCourseVO.etcRatio});

	if(ObjPrgrScore.value == "")	ObjPrgrScore.value = "0.0";
	if(ObjAttdScore.value == "")	ObjAttdScore.value = "0.0";
	if(ObjExamScore.value == "")	ObjExamScore.value = "0.0";
	if(ObjAsmtScore.value == "")	ObjAsmtScore.value = "0.0";
	if(ObjForumScore.value == "")	ObjForumScore.value = "0.0";
	if(ObjJoinScore.value == "")	ObjJoinScore.value = "0.0";
	if(ObjEtcScore.value == "")	ObjEtcScore.value = "0.0";

	var oldTotalScore = parseFloat(parseFloat(ObjTotScore.value).toFixed(2));
	var newTotalScore =parseFloat(parseFloat(ObjPrgrScore.value).toFixed(2)) +
						parseFloat(parseFloat(ObjAttdScore.value).toFixed(2)) +
						parseFloat(parseFloat(ObjExamScore.value).toFixed(2)) +
						parseFloat(parseFloat(ObjAsmtScore.value).toFixed(2)) +
						parseFloat(parseFloat(ObjForumScore.value).toFixed(2)) +
						parseFloat(parseFloat(ObjJoinScore.value).toFixed(2)) +
						parseFloat(parseFloat(ObjEtcScore.value).toFixed(2)) ;

    if(oldTotalScore != newTotalScore) {
		ObjTotScore.value = newTotalScore;
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

function setResult(){
	var prgrScore = document.getElementById("prgrScore").value;
	var attdScore = document.getElementById("attdScore").value;
	var examScore = document.getElementById("examScore").value;
	var asmtScore = document.getElementById("asmtScore").value;
	var forumScore = document.getElementById("forumScore").value;
	var joinScore = document.getElementById("joinScore").value;
	var etcScore = document.getElementById("etcScore").value;
	var totScore = document.getElementById("totScore").value;

	if(!$("#prgrChk").is(":checked")){
		prgrScore = "";
	}
	if(!$("#attdChk").is(":checked")){
		attdScore = "";
	}
	if(!$("#examChk").is(":checked")){
		examScore = "";
	}
	if(!$("#asmtChk").is(":checked")){
		asmtScore = "";
	}
	if(!$("#forumChk").is(":checked")){
		forumScore = "";
	}
	if(!$("#joinChk").is(":checked")){
		joinScore = "";
	}
	if(!$("#etcChk").is(":checked")){
		etcScore = "";
	}
	totScore = Number(prgrScore)+Number(attdScore)+Number(examScore)+Number(asmtScore)+Number(forumScore)+Number(joinScore)+Number(etcScore);

	if(!confirm('<spring:message code="student.message.result.confirm.addall"/>')){
		return;
	}
	if(parseFloat(totScore) > 100){
		alert("성적비율의 합은 100점이 넘을수 없습니다.");
		return;
	}
	parent.scoreAllSet(prgrScore, attdScore, examScore, asmtScore, forumScore, joinScore, etcScore, totScore);

}

</script>

</mhtml:class_html>