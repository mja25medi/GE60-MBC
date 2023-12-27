<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<%pageContext.setAttribute("crlf", "\n");%>
<c:set var="assignmentSubList" value="${assignmentSubList}"/>
<c:set var="assignmentSendVO" value="${assignmentSendVO}"/>
<c:set var="vo" value="${vo}"/>
<c:set var="studentVO" value="${studentVO}"/>

<!-- 클래스 추가 -->
<style> 
.test_field {position: relative; display: flex; overflow-x: hidden; overflow-y: auto; padding: 1rem; background-color: #f5f5f5;}
.test_field ul.left_number {width: 100px; min-width: 100px; margin-top: 2rem; padding: 0;}
.test_field ul.left_number li {position: relative; margin: .5rem 0 .5rem .5rem; border: 1px solid #E0E0E0; border-top-left-radius: .5rem; border-bottom-left-radius: .5rem; background: linear-gradient(90deg, rgba(255,255,255,1) 90%, rgba(230,230,230,.1) 100%);}
.test_field ul.left_number li a {display: block; padding: .75rem .75rem .75rem 1.4rem;}
.test_field ul.left_number li.active:after {position: absolute; top: 50%; right: 2rem; transform: translate(0, -50%); display: inline-block; font-family: xeicon; content: "\e928"; cursor: pointer; z-index: 1;}
.test_field ul.left_number li.on {border-right: 1px solid #fff; background: #fff;}
.test_field .right_content {display: flex; flex-direction: column; min-height: calc(100vh - 4.75rem); padding: 3rem 3rem 2rem 3rem; flex: 1; background-color: #fff;}
.task_question { overflow: hidden; text-overflow: ellipsis; display: -webkit-box; -webkit-line-clamp: 1; -webkit-box-orient: vertical; word-wrap: break-word; }
.task_question.on {width: auto;display: block; }
.btn_more_que { display: flex; justify-content: center; align-items: center; width: 25px; height: 25px; margin-left: 1rem; border-radius: 50%; box-sizing: border-box; padding: 1rem; border: 1px solid #ddd; background-color: #fff; }
.modal_cont{overflow-y: unset;}
</style>
<div style="display: flex;">
	<div class="col-md-5 col-xs-18">
            <div class="panel panel-default">
                
                <div class="panel-body" id="leftWorkArea" style="border-top:0px;">                   
                    <div style="width:100%;margin-top:10px;">
                        <form id="assignmentForm" name="assignmentForm" onsubmit="return false" >
						<input type="hidden" name="crsCreCd" value="${vo.crsCreCd }" />
						<input type="hidden" name="asmtSn" value="${vo.asmtSn }" />
						<input type="hidden" name="stdNo" value="${vo.stdNo }" />
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
										<input type="number" id="totalScore" name="score" maxlength="5" lenCheck="5" isNull='N' style="float:left;width:60px;text-align:right;" value="${assignmentSendVO.score}" class="inputNumber form-control input-sm" onfocus="this.select()" onkeyup="isChkMaxNumber(this,100)" onblur="isChkScore(this,100)"/>
										<span style="float:left;line-height:30px;"><spring:message code="common.title.score"/></span>
									</td>
								</tr>
							</tbody>
						</table>
						<div class="text-right" style="margin-top:5px; text-align: right;">
							<a href="javascript:addSendRate()" class="btn3 type1 btn-sm"><spring:message code="button.ok.rate"/></a>
							<a href="javascript:parent.modalBoxClose()" class="btn3 type2 btn-sm"><spring:message code="button.close"/></a>
						</div>

                        <div class="mb10">                       
                            <span class="text-left f16" style="float:left;"><b>과제정보</b></span>
                        </div>
                        <div style="clear:both"></div>
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
                    </div>
                </div>
            </div>
        </div>
        </form>
        
        <div class="col-md-7 col-xs-18">
            <div class="panel panel-default">
                
                <div class="panel-body">
                    <div id="contentsList" style="width:100%;font-size:14px;">

                        <div class="test_field">
                            <ul class="left_number" style="list-style-type: none;">
		                    	<c:forEach items="${assignmentSubList}" var="asList" varStatus="status">
		                        	<li <c:if test="${asList.sendCnt > 0}"> class="active" </c:if>><a href="javascript:selectAsmtSubSend(${asList.asmtSubSn })">${status.count}번 문제</a></li>
		                        </c:forEach>
		                    </ul>
                            <script>
                                $("ul.left_number li").unbind('click').bind('click', function(e) {
                                    $("ul.left_number li").removeClass('on');
                                    $(this).addClass("on");
                                });
                            </script>
                            <div class="right_content">
                                <table summary="문제" class="table table-bordered wordbreak">
                                    <colgroup>
                                        <col style="width:20%">
                                        <col style="width:30%">
                                        <col style="width:20%">
                                        <col style="width:30%">
                                    </colgroup>
                                    <tbody>
                                        <tr height="35">
                                            <th class="top">문제제목</th>
                                            <td class="top" colspan="3" id="asmtTitle">
                                                
                                            </td>                                           
                                        </tr>
                                        <tr height="35">
                                            <th class="top">언어</th>
                                            <td class="top" id="devLangCd">
                                                
                                            </td>
                                            <th class="top">난이도</th>
                                            <td class="top" id="diffLvlCd">
                                                하
                                            </td>
                                        </tr>
                                        <form id="asmtSubForm" name="asmtSubForm" onsubmit="return false"> 
                                        	<input type="hidden" name="asmtSn" value="${vo.asmtSn }">
                                        	<input type="hidden" name="crsCreCd" value="${vo.crsCreCd }">
                                        	<input type="hidden" name="stdNo" value="${studentVO.stdNo}">
                                        	<input type="hidden" id="asmtSubSn" name="asmtSubSn" value="">                               
	                                        <tr height="35">
	                                            <th class="top">점수</th>
	                                            <td class="top" colspan="3">
	                                                <input type="number" style="width:60px;float:left;" name="score" id="asmtSubScore" class="form-control input-sm" onkeyup="isChkMaxNumber(this,100)" onblur="isChkScore(this,100)">
	                                                <span style="float:left;margin-left:5px; margin-right: 10px;line-height:30px;">점</span>
	                                                <button id="saveBtn" class="btn btn-primary btn-sm">저장</button>
	                                            </td>
	                                        </tr>
                                        </form>  
                                        <tr height="35">
                                            <th class="top">문제 내용</th>
                                            <td class="top" colspan="3">
                                                <div id="asmtSubDetail" class="task_question" style="width: calc(100% - 50px); float: left;">
                                                   
                                                </div>    
                                                <a href="#0" class="btn btn_more_que"> <i class="fa fa-arrow-left"></i></a> 
                                            </td>
                                        </tr>                
                                    </tbody>
                                </table> 
                                <script>
                                    // 문제내용
                                    $(".btn_more_que").unbind('click').bind('click', function(e) {
                                        $(".task_question").toggleClass('on');
                                        $(this).addClass("on");
                                    });
                                </script>
                                <div class="mb10">                       
                                    <span class="text-left f16" style="float:left;"><b>채점결과</b></span>
                                </div>
                                <h5 class="text-left"><i class="fa fa-check" aria-hidden="true"></i><b>문제 설명</b></h5>
                                <p id="asmtCts"></p>

                                <h5 class="text-left"><i class="fa fa-check" aria-hidden="true"></i><b>제출한 코드</b></h5>
                                <pre id="submitCode" style="white-space: break-spaces;">

                                </pre>
                                <h5 class="text-left"><i class="fa fa-check" aria-hidden="true"></i><b>채점 결과</b></h5>   
                                <pre id="gptResult" style="border: none; background: none; white-space: break-spaces;">

                                </pre>      
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

<script type="text/javascript">

	//첨부파일 목록
	var atchFiles;
	var atchFilesSend;

	$(document).ready(function() {
		$(".inputNumber").inputNumber();
		selectAsmtSubSend('${assignmentSubList[0].asmtSubSn}')
		$("ul.left_number li:first").addClass('on');
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
	
	//문제 불러오기
	 function selectAsmtSubSend(asmtSubSn) {
    	 $.getJSON(cUrl("/mng/lecture/assignment/getAsmtSub"), 	// url
    				{ 
    				  "asmtSubSn" : asmtSubSn,
    				  "asmtSn" : '${vo.asmtSn}',
    				  "crsCreCd" : '${vo.crsCreCd}',
    				  "stdNo" : '${studentVO.stdNo}'
    				}, function(data) { 
    					var asmtSubVO = data.returnVO;
    					var ascVOList = asmtSubVO.asmtSubConstVOList;
    					var constList = "";
    					$('#asmtTitle').html(asmtSubVO.asmtTitle);
    					$('#devLangCd').html(asmtSubVO.devLangCd);
    					if(asmtSubVO.diffLvlCd == 'L'){
    						$('#diffLvlCd').html('하')
    					} else if (asmtSubVO.diffLvlCd == 'M') {
    						$('#diffLvlCd').html('중')
    					} else if (asmtSubVO.diffLvlCd == 'H') {
    						$('#diffLvlCd').html('상')
    					}
    					$("#asmtSubScore").val(asmtSubVO.score);
    					$("#asmtSubDetail").html(asmtSubVO.asmtCts);
    					$("#asmtCts").html(asmtSubVO.asmtCts);
    					if(asmtSubVO.sendCts != '') {
    						$("#submitCode").html(asmtSubVO.sendCts);
    					} else{
    						$("#submitCode").html('<p>제출 내역이 없습니다.');
    					}
    					if(asmtSubVO.gptResult != ''){
    						$("#gptResult").html(asmtSubVO.gptResult);
    					} else {
    						$("#gptResult").html('<p>제출 내역이 없습니다.');
    					}
    					$("#saveBtn").attr("onclick", "saveScore("+asmtSubVO.asmtSubSn+")");
    				}
    			);
     }
	 
	 //문제별 점수저장
	 function saveScore(asmtSubSn) {
		 $("#asmtSubSn").val(asmtSubSn);
		 if($("#asmtSubScore").val() < 0) {
			 alert("점수는 0보다 커야합니다.");
			 return false;
		 } else if ($("#asmtSubScore").val() > 100){
			 alert("점수는 100보다 작아야합니다.");
			 return false;
		 }
		 $("#asmtSubForm").attr("action","/mng/lecture/assignment/saveAsmtSubScore");
		 $("#asmtSubForm").ajaxSubmit(function(resultVO) {
				if(resultVO.result >= 0) {
					// 정상 처리
					alert(resultVO.message);
					sumAsmtScore(resultVO.returnVO.score)
				} else {
					// 비정상 처리
					alert(resultVO.message);
				}			
			});
	 }
	 
	 function sumAsmtScore(subScore) {
		 var listSize = ${fn:length(assignmentSubList)};
		 var totalScore = $("#totalScore").val();
		 if(totalScore == "") {
			 totalScore = 0;
		 } else {
			 totalScore = Number(totalScore)
		 }
		 totalScore += subScore/listSize;
		 $("#totalScore").val(Math.floor(totalScore));
		 
	 }


</script>
