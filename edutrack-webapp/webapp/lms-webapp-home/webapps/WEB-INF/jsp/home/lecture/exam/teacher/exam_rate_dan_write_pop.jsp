<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<%pageContext.setAttribute("crlf", "\n");%>
	<form id="examForm" name="examForm" onsubmit="return false" method="post">
	<input type="hidden" name="crsCreCd" value="${examQuestionVO.crsCreCd}"/>
	<input type="hidden" name="examSn" value="${examQuestionVO.examSn}"/>
	<input type="hidden" name="stdNo" id="stdNos"/>
	<input type="hidden" name="getScores" id="getScores"/>
	<div class="modal_cont">
        <div class="table_list">
            <ul class="list">
                <li class="head"><label><spring:message code="lecture.title.exam.question.select"/></label></li>
                <li>
                    <div class="form-inline w100">
                        <select class="form-select" name="examQstnSn" id="examQstnSn" onchange="changeQstn();" >
                           <c:forEach var="item" items="${questionList}">
								<c:if test="${item.qstnType eq 'D' or item.qstnType eq 'J' }">
									<option value="${item.examQstnSn}" <c:if test="${item.examQstnSn eq examQstnSn}">selected</c:if>>(${item.qstnTypeNm })(응시 : ${item.stareCnt }명) ${item.qstnNo}. ${fn:substring(item.title,0,50)}<c:if test="${fn:length(item.title) > 50 }">..</c:if></option>
								</c:if>
							</c:forEach>
                        </select>
                    </div>
                </li>
            </ul>           
            <ul class="list">
                <li class="head"><label><spring:message code="common.title.cnts"/></label></li>
                <li>${examQuestionVO.qstnCts}</li>
            </ul>
            <ul class="list">
                <li class="head"><label><spring:message code="lecture.title.exam.question.rightanswer"/></label></li>
                <li>${fn:replace(examQuestionVO.rgtAnsr,crlf,"<br/>")}</li>
            </ul>
            <ul class="list">
                <li class="head"><label><spring:message code="lecture.title.exam.question.desc"/></label></li>
                <li>${fn:replace(examQuestionVO.qstnExpl,crlf,"<br/>")}</li>
            </ul>
        </div>
        <div class="btns mt20">
            <button class="btn type4" onclick="javascript:addStareScore()"><spring:message code="button.socre.save"/></button>
            <button class="btn type2" onclick="javascript:parent.document.location.reload()"><spring:message code="button.close"/></button>
        </div>

        <div class="res_tbl_wrap mt30">
            <table>
                <caption>평가관리 목록</caption>
                <thead>
                    <tr>
                        <th scope="col" width="8%"><spring:message code="common.title.no"/></th>
                        <th scope="col" width="10%"><spring:message code="user.title.userinfo.name"/></th>
                        <th scope="col" width="14%"><spring:message code="user.title.userinfo.userid"/></th>
                        <th scope="col"><spring:message code="lecture.title.exam.question.useranswer"/></th>
                        <c:if test="${examQuestionVO.qstnType eq 'J' }">
                        <th scope="col" width="10%">모사율</th>
                        </c:if>
                        <th scope="col" width="10%"><spring:message code="lecture.title.exam.score"/></th>
                    </tr>
                </thead>
                <tbody>
					<c:forEach var="item" items="${stuStareList}" varStatus="status">
						<c:if test="${item.examSnChk eq 'Y' }">
							<tr>
								<td scope="row" data-label="번호">${status.count}</td>
								<td data-label="이름">${item.userNm}</td>
								<td data-label="아이디">${item.userId}</td>
								<td class="title" data-label="학습자답">${fn:replace(item.rgtAnsr,crlf,"<br/>")}</td>
								<c:if test="${examQuestionVO.qstnType eq 'J' }">
									<td data-label="모사율">
										<c:if test="${item.completeStatus eq 'Y'}">
											<a href="http://183.111.234.121:8082/ckplus/copykiller.jsp?uri=${item.copyRatioUri}&property_id=40" target="_blank">${item.dispTotalCopyRatio }</a>
										</c:if>
									</td>
								</c:if>
								<td data-label="점수">
									<input type="hidden" name="stdNoUsr" value="${item.stdNo}"/>
									<%--수강생 점수, data-value는 수강생이 응시했을 때의 문항 점수 --%>
									<input type="text" name="score" style="width:60px;text-align:right;float:left;" value="${item.getScore}" data-value="${item.qstnScore}" class="inputNumber form-control input-sm" maxlength="5" onfocus="this.select()" onkeyup="isChkMaxNumber(this,${item.qstnScore})" onblur="chkNumber(this,${item.qstnScore});"/>
									<span style="float:left;line-height:30px;"><spring:message code="common.title.score"/> / ${item.qstnScore} <spring:message code="common.title.score"/></span>
								</td>
							</tr>
						</c:if>
					</c:forEach>
					<c:if test="${empty stuStareList}">
					<tr>
						<td colspan="5"><spring:message code="lecture.message.exam.stare.nostudent"/></td>
					</tr>
				</c:if>
				</tbody>
            </table>
        </div>
    </div>
	</form>

<script type="text/javascript">
	$(document).ready(function() {
		$("body").css("padding-top","0px").css("min-height","0px");
		$(".inputNumber").inputNumber();
	});

	function changeQstn() {
		var examQstnSn = $("#examQstnSn option:selected").val();
		document.location.href = cUrl("/lec/exam/editRateDanPop")+"?crsCreCd=${examQuestionVO.crsCreCd}${AMPERSAND}examSn=${examQuestionVO.examSn}${AMPERSAND}examQstnSn="+examQstnSn;
	}

	function addStareScore() {
		var connYn = '${vo.connYn}';
		if(connYn == 'Y'){
			//alert('<spring:message code="lecture.message.evaluation.complete.warning"/>');
			//return;
		}
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
		$('#examForm').attr("action","/lec/exam/"+cmd);
		$('#examForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		alert(resultDTO.message);
		if(resultDTO.result >= 0) {
			// 정상 처리
			parent.document.location.reload();
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
				alert('<spring:message code="lecture.message.exam.rate.overscore2"/>');
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