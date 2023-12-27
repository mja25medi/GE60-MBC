<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<%pageContext.setAttribute("crlf", "\n");%>
<c:url var="img_base" value="/img/home" />
<c:set var="examVO" value="${vo}" />
	<div id="editExam" >
		<form id="examForm" name="examForm" onsubmit="return false" >
		<input type="hidden" name="crsCreCd" value="${vo.crsCreCd}" />
		<input type="hidden" name="examSn" value="${vo.examSn}" />
		<input type="hidden" name="qstnCnt" value="${vo.qstnCnt}" />
		<input type="hidden" name="stareCnt" value="${vo.stareCnt}" />
		<input type="hidden" name="regYn" id="regYn" value="${vo.regYn}"/>
		<input type="hidden" name="qstnScores" value="${vo.qstnScores}" />
		<input type="hidden" name="qstnNos" value="${vo.qstnNos}" />
		<input type="hidden" name="examTypeCd" value="${vo.examTypeCd}" />
		<input type="hidden" name="examStareTypeCd" value="${vo.examStareTypeCd}" />
		<input type="hidden" name="examQstnSn" value="${vo.examQstnSn}" />
		<input type="hidden" name="gubun" value="${vo.gubun}" />
		<input type="hidden" name="examViewTypeCd" value="${vo.examViewTypeCd}" />
		<div class="modal_cont">
                    <div class="table_list">
                        <ul class="list">
                            <li class="head"><label>시험유형</label></li>
                            <li>
	                            <c:choose>
									<c:when test="${examVO.examTypeCd eq 'ON'}"><label class="btn3 sm solid fcGreen">온라인</label></c:when>
									<c:when test="${examVO.examTypeCd eq 'OFF'}"><label class="btn3 sm solid fcViolet">오프라인</label></c:when>
								</c:choose>
                            </li>
                            <li class="head"><label>응시유형</label></li>
                            <li><meditag:codename code="${examVO.examStareTypeCd}" category="EXAM_STARE_TYPE_CD" /></li>
                           
                            
                        </ul>
                        <ul class="list">
                            <li class="head"><label>시험제목</label></li>
                            <li>${examVO.examTitle }</li>
                        </ul>
                        <ul class="list">
                            <li class="head"><label>시험기간</label></li>
                            <li class="examTypeR"><meditag:dateformat type="8" delimeter="." property="${examVO.examStartDttm}"/>~<meditag:dateformat type="8" delimeter="." property="${examVO.examEndDttm}"/></li>
                            <li class="examTypeS"><spring:message code="lecture.message.exam.msg.duration.sangsi"/></li>
                        </ul>
                        <ul class="list">
                           <li class="head"><label>결과확인일</label></li>
                           <li class="examTypeR"><meditag:dateformat type="8" delimeter="." property="${examVO.rsltCfrmDttm}"/></li>
						   <li class="examTypeS"><spring:message code="lecture.message.exam.msg.resultdate.sangsi"/></li>
                        </ul>
                        <c:if test="${examVO.examTypeCd eq 'ON'}">
							<c:if test="${examVO.stareTmUseYn eq 'Y'}">
		                        <ul class="list">
		                            <li class="head"><label>시간제한</label></li>
		                            <li><meditag:codename code="${examVO.stareTmUseYn}" category="STARE_TM_USE_YN" /></li>
		                            <li class="head"><label>시험시간</label></li>
		                            <li>${examVO.examStareTm }<spring:message code="common.title.min"/></li>
		                        </ul>
                      	  </c:if>
                        <ul class="list">
	                        <c:if test="${examVO.semiExamYn eq 'Y'}">
	                            <li class="head"><label>강의</label></li>
	                            <li>${examVO.stareLecCount} 강 수강 후</li>
	                        </c:if>    
                            <c:if test="${examVO.semiExamYn eq 'N'}">
	                            <li class="head"><label><spring:message code="lecture.title.exam.answer.ratio"/></label></li>
	                            <li>${examVO.stareCritPrgrRatio} % <spring:message code="common.title.over"/></li>
	                        </c:if>   
                            <li class="head"><label>응시제한횟수</label></li>
                            <li>${examVO.stareLimitCnt} <spring:message code="common.title.times.postfix"/></li>
                        </ul>
                        </c:if>
                        <ul class="list">
                            <li class="head"><label>시험설명</label></li>
                            <li>${fn:replace(examVO.examCts,crlf,"<br/>")}</li>
                        </ul>
                    </div>
                </div>
                <div class="modal_btns">
                    <button type="button" class="btn" onclick="parent.modalBoxClose()">닫기</button>
                </div>
		</form>
	</div>


<script type="text/javascript">
	$(document).ready(function() {

		changeExamType();
		changeExamStareType();
		changeExamStareTm();
	});

	function changeExamType() {
		var f = document.examForm;
		var examType = "";
		examType = f["examTypeCd"].value;
		if(examType == "OFF") {
			var examStareType = f["examStareTypeCd"].value;
			if(examStareType == "S") {
				alert("<spring:message code="lecture.message.exam.alert.change.examtype"/>");
				return;
			}
			$("#regYn").val("Y");
		} else {
			$('.examTypeControl').removeAttr("disabled").removeClass("disable").addClass("enable");
			$("#regYn").val("${examVO.regYn}");
			changeExamStareType();
		}
	}

	function changeExamStareType() {
		var f = document.examForm;
		var examStareType = f["examStareTypeCd"].value;
		if(examStareType == "S") {
			$('img.fn_calimg').hide();
			$('.examTypeR').remove();
			$('.examTypeS').show();
			$('.StareTypeTr').hide();
		} else {
			$('img.fn_calimg').show();
			$('.examTypeR').show();
			$('.examTypeS').remove();
			$('.StareTypeTr').show();

		}
	}

	function changeExamStareTm() {
		var f = document.examForm;
		var stareTmUseYn = f["stareTmUseYn"].value;
		if(stareTmUseYn == "Y") {
			f["examStareTm"].disabled = false;
		} else {
			f["examStareTm"].disabled = true;
		}
	}
</script>
