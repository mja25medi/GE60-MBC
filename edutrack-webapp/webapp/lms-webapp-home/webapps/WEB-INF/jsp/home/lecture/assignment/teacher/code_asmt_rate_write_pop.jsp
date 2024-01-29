<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<%pageContext.setAttribute("crlf", "\n");%>
<c:set var="assignmentSubList" value="${assignmentSubList}"/>
<c:set var="assignmentSendVO" value="${assignmentSendVO}"/>
<c:set var="vo" value="${vo}"/>
<c:set var="studentVO" value="${studentVO}"/>

 <div class="modal_cont task_area">
        <div class="row">
            <div class="col-sm-5">
            	 <form id="assignmentForm" name="assignmentForm" onsubmit="return false" >
						<input type="hidden" name="crsCreCd" value="${vo.crsCreCd }" />
						<input type="hidden" name="asmtSn" value="${vo.asmtSn }" />
						<input type="hidden" name="stdNo" value="${vo.stdNo }" />
                <div class="table_list">
                    <ul class="list">
                        <li class="head"><label>이름</label></li>
                        <li>${studentVO.userNm}</li>
                        <li class="head"><label>아이디</label></li>
                        <li>${studentVO.userId}</li>
                    </ul>
                    <ul class="list">
                        <li class="head"><label>제출일자</label></li>
                        <li><meditag:dateformat type="8" delimeter="." property="${assignmentSendVO.modDttm}"/></li>
                        <li class="head"><label>제출횟수</label></li>
                        <li>${assignmentSendVO.sendCnt} <spring:message code="common.title.times.postfix"/></li>
                    </ul>
                    <ul class="list">
                        <li class="head"><label for="commentlInput">첨언</label></li>
                        <li>
                            <div class="form-row w100">
                                <textarea class="form-control" rows="5" name="atchCts" id="commentlInput">${assignmentSendVO.atchCts}</textarea>
                            </div>
                        </li>
                    </ul>
                    <ul class="list">
                        <li class="head"><label>점수</label></li>
                        <li>
                            <div class="input_btn">
                                <input type="number" id="totalScore" name="score" maxlength="3" lenCheck="3" isNull='N'  value="${assignmentSendVO.score}"  class="form-control sm" onfocus="this.select()" onkeyup="isChkMaxNumber(this,100)" onblur="isChkScore(this,100)"/>
                                <label>점</label>
                            </div>
                        </li>
                    </ul>                        
                </div>
                <div class="txt-right mt20">
               		<a href="javascript:addSendRate()" class="btn type4"><spring:message code="button.ok.rate"/></a>
                    <a href="javascript:parent.modalBoxClose()" class="btn type2"><spring:message code="button.close"/></a>
                </div>

                <div class="board_top">
                    <h4>과제정보</h4>
                </div>
                <div class="table_list">                      
                    <ul class="list">
                        <li class="head"><label>과제제목</label></li>
                        <li>${vo.asmtTitle}</li>
                    </ul>
                    <ul class="list">
                        <li class="head"><label>첨부파일</label></li>
                        <li>
                            <ul class="add_file">
                                <li>                    
                                    <c:forEach var="file" items="${vo.attachFiles}"><p>${file.downloadTag}</p></c:forEach>
                                </li>
                            </ul>
                        </li>
                    </ul>                    
                    <ul class="list">
                        <li class="head"><label>과제 내용</label></li>
                        <li>${vo.asmtCts}</li>
                    </ul>
                </div>

            </div>
            <div class="col-sm-7">
                <div class="segment task_pop">
                    <div class="test_field">
                        <ul class="left_number">
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
                        <div class="right_content" style="width: 50%;">
                            <div class="table_list">                      
                                <ul class="list">
                                    <li class="head"><label>문제제목</label></li>
                                    <li id="asmtTitle"></li>
                                </ul>  
                                <ul class="list">
                                    <li class="head"><label>언어</label></li>
                                    <li id="devLangCd"></li>
                                    <li class="head"><label>난이도</label></li>
                                    <li id="diffLvlCd"></li>
                                </ul>
                                <ul class="list">
                                    <li class="head"><label>점수</label></li>
                                    <li>
                                    	<div class="input_btn">
		                               		<input type="number" style="width:7rem" name="score" id="asmtSubScore" class="form-control sm"  maxlength="3" lenCheck="3" onkeyup="isChkMaxNumber(this,100)" onblur="isChkScore(this,100)">
	                                   		<label>점</label>
	                                   		<button id="saveBtn" class="btn">저장</button>
                            			</div>
                                    </li>
                                </ul>                                                      
                                <ul class="list">
                                    <li class="head"><label>문제 내용</label></li>
                                    <li>
                                        <div id="asmtSubDetail" class="task_question"> </div>    
                                        <a href="#0" class="btn btn_more_que"> <i class="xi-angle-left-min"></i></a> 
                                    </li>
                                </ul>                               
                            </div>
                            <div class="board_top">
                                <h4>채점결과</h4>
                            </div>
                            <div class="cont">
                                <label class="h_title fcBlue">문제 설명</label>
                                <div class="task_txt" id="asmtCts"></div>                                
                            </div>
                            <div class="answer">
                                <label for="contTextarea" class="h_title fcBlue">제출한 코드</label>
                                <div class="form-row task_txt">
                                    <pre id="submitCode">

                                    </pre>
                                </div>                               
                            </div>
                            <div class="cont">
                                <label class="h_title fcBlue">채점 결과</label>
                                <div class="task_txt" id="gptResult">
                                    
                                </div>                                
                            </div>
                        </div>
                    </div>
                </div>

                <div class="scroll_img_w">
                    <div class="scroll_img">
                        <p class="txt">가로로 스와이프하여 <br>내용을 확인해주세요</p>
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
    					var detail = asmtSubVO.asmtCts.replace(/(<([^>]+)>)/gi, '');
    					$("#asmtSubDetail").html(detail);
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
