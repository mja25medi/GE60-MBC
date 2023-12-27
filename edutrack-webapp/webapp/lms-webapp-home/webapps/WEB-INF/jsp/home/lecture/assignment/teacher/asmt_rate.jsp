<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<%pageContext.setAttribute("crlf", "\n");%>
<c:url var="img_base" value="/img/uniedu/home" />
<c:set var="assignmentSendVO" value="${vo}"/>
<c:set var="assignmentSubVO" value="${assignmentSubVO}"/>
		 <div class="modal_cont task_area">
		<div class="row">
             <div class="col-sm-5">
                <div class="board_top">
                    <h4>과제정보</h4>
                </div>
                <div class="table_list">
                    <ul class="list">
                        <li class="head"><label>과제제목</label></li>
                        <li>${assignmentVO.asmtTitle}</li>
                    </ul>
                    <ul class="list">
                        <li class="head"><label>첨부파일</label></li>
                        <li><c:forEach var="file" items="${assignmentVO.attachFiles}"><p>${file.downloadTag}</p></c:forEach></li>
                    </ul>
                    <ul class="list">
                          <li class="head"><label>내용</label></li>
                        <li>
                            <div class="form-row w100">
                              ${fn:replace(assignmentVO.asmtCts,crlf,"<br/>")}
                            </div>
                        </li>
                    </ul>
                </div>
                <div class="board_top">
                    <h4>과제문제</h4>
                </div>
                <div class="table_list">                      
                    <ul class="list">
                        <li class="head"><label>문제제목</label></li>
                        <li>${assignmentSubVO.asmtTitle}</li>
                    </ul>
                    <ul class="list">
                        <li class="head"><label>첨부파일</label></li>
                        <li>
                           <c:forEach var="file" items="${assignmentSubVO.attachFiles}"><p>${file.downloadTag}</p></c:forEach>
                        </li>
                    </ul>                    
                    <ul class="list">
                        <li class="head"><label>과제 내용</label></li>
                        <li>${fn:replace(assignmentVO.asmtCts,crlf,"<br/>")}</li>
                    </ul>
                </div>
            </div>
	            <div class="col-sm-7">
	                <div class="segment task_pop">
	                    <div class="test_field">
	                        <div class="right_content">
	                         <div class="board_top">
				                    <h4>과제제출정보</h4>
				               </div>
	                            <div class="table_list">
	                            <form id="assignmentForm" name="assignmentForm" onsubmit="return false">
								<input type="hidden" name="tab"/>
								<input type="hidden" name="crsCreCd" value="${vo.crsCreCd}" />
								<input type="hidden" name="asmtSn" value="${vo.asmtSn}" />
								<input type="hidden" name="asmtSubSn" value="${vo.asmtSubSn}" />
								<input type="hidden" name="stdNo" value="${vo.stdNo}" />
								<input type="hidden" name="rateYn" value="${vo.rateYn}" />                      
	                                <ul class="list">
	                                    <li class="head"><label>이름</label></li>
	                                    <li>${studentVO.userNm}</li>
	                                    <li class="head"><label>아이디</label></li>
	                                    <li>${studentVO.userId}</li>
	                                </ul>                                           
	                                <ul class="list">
	                                    <li class="head"><label>최종제출일자</label></li>
	                                    <li><meditag:dateformat type="1" delimeter="." property="${studentVO.modDttm}"/></li>
	                                    <li class="head"><label>제출횟수</label></li>
	                                    <li>${assignmentSendVO.sendCnt}</li>
	                                </ul>                                           
	                                <ul class="list">
	                                    <li class="head"><label>제출제목</label></li>
	                                    <li>
	                                          <div class="form-row w100">
	                                     	  ${assignmentSendVO.sendTitle}
	                                        </div>    
	                                    </li>
	                                </ul>                               
	                                <ul class="list">
	                                    <li class="head"><label>제출파일</label></li>
	                                    <li>
	                                          <div class="form-row w100">
	                                          <c:forEach var="file" items="${assignmentSendVO.attachFiles}"><p>${file.downloadTag}</p></c:forEach>
	                                        </div>    
	                                    </li>
	                                </ul>                               
	                                <ul class="list">
	                                    <li class="head"><label>제출내용</label></li>
	                                    <li>
	                                         <div class="form-row w100">
	                                         	${fn:replace(assignmentSendVO.sendCts,crlf,"<br/>")}
	                                        </div>    
	                                    </li>
	                                </ul>                               
	                               <ul class="list">
	                        <li class="head"><label for="commentlInput">첨언</label></li>
	                        <li>
	                            <div class="form-row w100">
	                                <textarea class="form-control" rows="5" name="atchCts" id="commentlInput">${vo.atchCts}</textarea>
	                            </div>
	                        </li>
	                    </ul>
	                    <ul class="list">
	                        <li class="head"><label>점수</label></li>
	                        <li>
	                            <div class="input_btn">
	                                <input class="form-control sm" id="timeInput" name="score" value="${vo.score}" type="text" maxlength="5" onfocus="this.select()" onkeyup="isChkMaxNumber(this,100)" onblur="isChkScore(this,100);"><label>점</label>
	                            </div>
	                        </li>
	                    </ul>   
	                    </form>
          <div class="txt-right mt20">
                    <button class="btn type4" onclick="javascript:addSendRate()">평가완료</button>
                    <button class="btn type2" onclick="javascript:parent.modalBoxClose()">닫기</button>
                </div>
                     </div>
                 </div>
             </div>
         </div>
     </div>
 </div>
</div>
<script type="text/javascript">

	$(document).ready(function() {
		$(".sub_title_2.ohddien").text("과제평가");
		$(".inputNumber").inputNumber();
	});


	function addSendRate() {
		//평가완료를 하면 과제제출을 할 수 없다.
		if(!confirm('<spring:message code="lecture.message.asmt.confirm.score.complete"/>')){
			return;
		}

		var fm = document.assignmentForm;
		var score = parseFloat(fm["score"].value);

		fm["rateYn"].value = "Y";

		if(score > 100) {
			alert('<spring:message code="lecture.message.common.alert.input.score100"/>');
			fm["score"].focus();
			return;
		}

		process("addSendRate");
	}

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		if(!validate(document.assignmentForm)) return;
		$('#assignmentForm').attr("action","/lec/assignment/"+cmd);
		$('#assignmentForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		alert(resultDTO.message);
		if(resultDTO.result >= 0) {
			// 정상 처리
			parent.listStudent(1);
			parent.modalBoxClose();
		} else {
			// 비정상 처리
		}
	}

	function chkNumber(param){
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
	}

</script>
