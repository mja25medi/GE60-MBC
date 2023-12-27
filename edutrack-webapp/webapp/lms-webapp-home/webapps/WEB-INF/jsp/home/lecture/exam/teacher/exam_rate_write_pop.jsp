<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<%pageContext.setAttribute("crlf", "\n");%>
<c:set var="examStareVO" value="${vo}"/>
	<form id="examForm" name="examForm" onsubmit="return false" method="post">
		<input type="hidden" name="crsCreCd" value="${vo.crsCreCd}" />
		<input type="hidden" name="examSn" value="${vo.examSn}" />
		<input type="hidden" name="stdNo" value="${vo.stdNo}" />
		<input type="hidden" name="rateYn" value="Y"/>
		<input type="hidden" name="getScores" value="${vo.getScores}" />
		<input type="hidden" name="totGetScore" value="${vo.totGetScore}" />
        <div class="table_list"> 
            <ul class="list">
                <li class="head"><label>이름</label></li>
                <li>${studentVO.userNm}</li>
                <li class="head"><label>아이디</label></li>
                <li>${studentVO.userId}</li>
            </ul>
            <ul class="list">
                <li class="head"><label><spring:message code="lecture.title.exam.laststaredate"/></label></li>
                <li><meditag:dateformat type="1" delimeter="." property="${examStareVO.startDttm}"/> <meditag:dateformat type="7" delimeter="." property="${examStareVO.startDttm}"/></li>
                <li class="head"><label><spring:message code="lecture.title.exam.answer.cnt"/></label></li>
                <li>${examStareVO.stareCnt}</li>
            </ul>
            <ul class="list">
                <li class="head"><label><spring:message code="lecture.title.exam.answer.commnet"/></label></li>
                <li>
                    <div class="form-row w100">
                        <textarea class="form-control" rows="5" name="atchCts" id="contTextarea">${examStareVO.atchCts}</textarea>
                    </div>
                </li>
            </ul>
            <ul class="list">
                <li class="head"><label><spring:message code="student.title.student.getscore"/></label></li>
                <li>
                    <div class="input_btn">
                        <input class="form-control sm" name="getTotScore" id="getTotScore" value="" type="text" disabled><label>점</label>
                    </div>
                </li>
            </ul>
        </div>
		<c:forEach items="${questionList}" var="item" varStatus="status">
		<c:url var="mark" value="mark_right"/>
		<c:set var="correctText" value="correct"/>
		<c:if test="${stareInfo[item.examQstnSn]['score'] eq '0'}">
			<c:url var="mark" value="mark_wrong"/>
			<c:set var="correctText" value="incorrect"/>
		</c:if>
        <div class="list_view_box">
            <div class="list_title ${mark}">
                <div class="type"><span class="num">${status.count}.</span><meditag:codename code="${item.qstnType}" category="EXAM_QSTN_TYPE" /></div>
            </div>
            <div class="list_item">
                <div class="quest">${item.title}</div>
                <c:if test="${item.qstnType eq 'K'}">
				<c:if test="${not empty item.qstnCts}"><div class="exam_area">${item.qstnCts}</div>	</c:if>
					<ol class="field">
						<c:if test="${not empty item.empl1}">
							<li class="ui checkbox"><input type="radio" name="ans_${item.qstnNo}"	id="ans_${item.qstnNo}" value="1" <c:if test="${fn:replace(stareInfo[item.examQstnSn]['answer'],crlf,'<br/>') eq '1'}">checked</c:if> /><label for="ans_${status.count}_01">${item.empl1}</label></li>
						</c:if>
						<c:if test="${not empty item.empl2}">
							<li class="ui checkbox"><input type="radio"	name="ans_${item.qstnNo}"	id="ans_${item.qstnNo}" value="2" <c:if test="${fn:replace(stareInfo[item.examQstnSn]['answer'],crlf,'<br/>') eq '2'}">checked</c:if> /><label for="ans_${status.count}_02">${item.empl2}</label></li>
						</c:if>
						<c:if test="${not empty item.empl3}">
							<li class="ui checkbox"><input type="radio"	name="ans_${item.qstnNo}"	id="ans_${item.qstnNo}" value="3" <c:if test="${fn:replace(stareInfo[item.examQstnSn]['answer'],crlf,'<br/>') eq '3'}">checked</c:if> /><label for="ans_${status.count}_03">${item.empl3}</label></li>
						</c:if>
						<c:if test="${not empty item.empl4}">
							<li class="ui checkbox"><input type="radio"	name="ans_${item.qstnNo}"	id="ans_${item.qstnNo}" value="4" <c:if test="${fn:replace(stareInfo[item.examQstnSn]['answer'],crlf,'<br/>') eq '4'}">checked</c:if> /><label for="ans_${status.count}_04">${item.empl4}</label></li>
						</c:if>
						<c:if test="${not empty item.empl5}">
							<li class="ui checkbox"><input type="radio"	name="ans_${item.qstnNo}"	id="ans_${item.qstnNo}" value="5" <c:if test="${fn:replace(stareInfo[item.examQstnSn]['answer'],crlf,'<br/>') eq '5'}">checked</c:if>/><label for="ans_${status.count}_05">${item.empl5}</label></li>
						</c:if>
					</ol>
                </c:if>
                <c:if test="${item.qstnType eq 'S'}">
                <c:if test="${not empty item.qstnCts}"><div class="exam_area">${item.qstnCts}</div>	</c:if>
					 <div class="checkImg">
                  	  	 <c:if test="${stareInfo[item.examQstnSn]['answer'] eq 'O'}">
                 	  		 <input id="imgChk_true2" type="radio" name="imgChk2" checked disabled><label class="imgChk true" for="imgChk_true"></label>
                   			 <input id="imgChk_false2" type="radio" name="imgChk2"  disabled><label class="imgChk false" for="imgChk_false"></label>
                    	</c:if>
                  	  	 <c:if test="${stareInfo[item.examQstnSn]['answer'] eq 'X'}">
          	                 <input id="imgChk_true2" type="radio" name="imgChk2"  disabled><label class="imgChk true" for="imgChk_true"></label>
                   			 <input id="imgChk_false2" type="radio" name="imgChk2" checked disabled> <label class="imgChk false" for="imgChk_false"></label>
                    	</c:if>
                	</div>
				</c:if>
				 <c:if test="${item.qstnType eq 'J'}"><!-- 서술 -->
				 <c:if test="${not empty item.qstnCts}">
					 <div class="form-row">
                    <textarea rows="5" class="form-control" placeholder="내용을 작성하세요">${item.qstnCts}</textarea>
                </div>	
				</c:if>
					<div class="form-row">
                    <textarea rows="5" class="form-control">${stareInfo[item.examQstnSn]['answer']}</textarea>
                </div>
				</c:if>
                <c:if test="${item.qstnType eq 'D'}"><!-- 단답 -->
                <c:if test="${not empty item.qstnCts}">
					 <div class="form-row">
                    <textarea rows="5" class="form-control" placeholder="내용을 작성하세요">${item.qstnCts}</textarea>
                </div>	
				</c:if>
					  <div class="form-inline">
                    <input class="form-control" type="text"value="${stareInfo[item.examQstnSn]['answer']}">
                </div>
				</c:if>
                
                <ul class="feedback">
                    <li><strong>정답</strong>
                 		<c:if test="${item.qstnType eq 'D'}">${fn:replace(item.rgtAnsr, '|', ',')}</c:if>
						<c:if test="${item.qstnType ne 'D'}">${fn:replace(item.rgtAnsr,crlf,"<br/>")}</c:if>
					</li>
                    <li><strong>학습자답</strong>
                  	  ${fn:replace(stareInfo[item.examQstnSn]['answer'],crlf,"<br/>")}
                    </li>
                    <li><strong>해설</strong>${item.qstnExpl}</li>
                </ul>
            </div>
        </div>
           <div class="segment total_score">
		            <strong>점수</strong> 
		            <input type="text" class="form-control sm" name="getScore" onfocus="this.select()" maxlength="5" onkeyup="isChkMaxNumber(this,${item.qstnScore});sumScore();"  onblur="chkNumber(this,${item.qstnScore});" value="${stareInfo[item.examQstnSn]['score']}"/>
		            /  ${ item.qstnScore}
		            <input type="hidden" name="baseScore" value="${item.qstnScore}"/>
		        </div>
        </c:forEach>
    
    <div class="modal_btns">
        <button class="btn type4" onclick="javascript:addStareScore()"><spring:message code="button.ok.rate"/></button>
        <button class="btn type2" onclick="javascript:parent.modalBoxClose()"><spring:message code="button.close"/></button>
    </div>    
	</form>
	
		

<script type="text/javascript">
	$(document).ready(function() {
		$("body").css("padding-top","0px").css("min-height","0px");
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
				alert((i+1)+'<spring:message code="lecture.message.exam.rate.overscore"/>');
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
			parent.location.reload();
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
