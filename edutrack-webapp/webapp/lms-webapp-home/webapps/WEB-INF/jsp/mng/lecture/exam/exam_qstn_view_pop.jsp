<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="gubun" value="${gubun}"/>
<c:set var="examQuestionVO" value="${vo}" />

	<form id="examForm" name="examForm" onsubmit="return false" action="/mng/lecture/exam">
	<input type="hidden" name="crsCreCd" value="${vo.crsCreCd }" />
	<input type="hidden" name="examSn" value="${vo.examSn }" />
	<input type="hidden" name="examQstnSn" value="${vo.examQstnSn }" />
	<input type="hidden" name="rgtAnsr" value="${vo.rgtAnsr }" />
	<input type="hidden" name="qstnScore" value="${vo.qstnScore }" />
	<input type="hidden" name="attachImageSns" value="${vo.attachImageSns }" />
	<input type="hidden" name="editorYn" value="Y"/>
	<input type="hidden" name="qstnType" id="qstnType" value="${vo.qstnType }"/>
	<ul class="list-group">
		<li class="list-group-item wordbreak">
			<p>${examQuestionVO.qstnNo}. ${examQuestionVO.title} <%--(<meditag:codename code="${examQuestionVO.qstnType}" category="EXAM_QSTN_TYPE" /> : ${examQuestionVO.qstnScore}<spring:message code="common.title.score"/>) --%></p>
			<div class="well well-sm exam-contents" style="margin-top:10px;">
      							<span style="margin-bottom:10px;">${examQuestionVO.qstnCts}</span>
      							<c:if test="${examQuestionVO.qstnType eq 'K'}">
				<ul style="list-style-type:none;padding:10px ;line-height:25px;">
					<c:if test="${not empty examQuestionVO.empl1}">
					<li><label><input type="radio" name="ans_${examQuestionVO.qstnNo}" id="ans_${examQuestionVO.qstnNo}"  value="1"/> 1. ${examQuestionVO.empl1}</label></li>
					</c:if>
					<c:if test="${not empty examQuestionVO.empl2}">
					<li><label><input type="radio" name="ans_${examQuestionVO.qstnNo}" id="ans_${examQuestionVO.qstnNo}"  value="2"/> 2. ${examQuestionVO.empl2}</label></li>
					</c:if>
					<c:if test="${not empty examQuestionVO.empl3}">
					<li><label><input type="radio" name="ans_${examQuestionVO.qstnNo}" id="ans_${examQuestionVO.qstnNo}"  value="3"/> 3. ${examQuestionVO.empl3}</label></li>
					</c:if>
					<c:if test="${not empty examQuestionVO.empl4}">
					<li><label><input type="radio" name="ans_${examQuestionVO.qstnNo}" id="ans_${examQuestionVO.qstnNo}"  value="4"/> 4. ${examQuestionVO.empl4}</label></li>
					</c:if>
					<c:if test="${not empty examQuestionVO.empl5}">
					<li><label><input type="radio" name="ans_${examQuestionVO.qstnNo}" id="ans_${examQuestionVO.qstnNo}"  value="5"/> 5. ${examQuestionVO.empl5}</label></li>
					</c:if>
				</ul>
     							</c:if>
     							<c:if test="${examQuestionVO.qstnType eq 'S'}">
     							<p>
      							<label for="o"><input type="radio" name="ans_${item.qstnNo}" id="ans_${item.qstnNo}" value="O"/>&nbsp;O</label>
								<label for="x" style="margin-left:20px;"><input type="radio" name="ans_${item.qstnNo}" id="ans_${item.qstnNo}" value="X"/>&nbsp;X</label>
								</p>
     							</c:if>
     							<c:if test="${examQuestionVO.qstnType eq 'D'}">
     							<input type="text" name="ans_${examQuestionVO.qstnNo}" id="ans_${examQuestionVO.qstnNo}" class="form-control input-sm"/>
     							</c:if>
     							<c:if test="${examQuestionVO.qstnType eq 'J'}">
     							<textarea name="ans_${examQuestionVO.qstnNo}" id="ans_${examQuestionVO.qstnNo}" cols="1" rows="5"  class="form-control input-sm"></textarea>
     							</c:if>
     						</div>
		</li>
	</ul>


	<table summary="<spring:message code="lecture.title.exam.question.manage"/>" class="table table-bordered wordbreak">
		<colgroup>
			<col style="width:20%" />
			<col style="width:80%" />
		</colgroup>
		<tbody>

		<tr>
			<th scope="row"><spring:message code="lecture.title.exam.question.rightanswer"/></th>
			<td>
				<c:if test="${examQuestionVO.qstnType eq 'D'}">${fn:replace(examQuestionVO.rgtAnsr, '|', ',')}</c:if>
				<c:if test="${examQuestionVO.qstnType ne 'D'}">${examQuestionVO.rgtAnsr }</c:if>
			</td>
		</tr>
		<tr>
			<th scope="row"><spring:message code="lecture.title.exam.question.desc"/></th>
			<td>
				${examQuestionVO.qstnExpl }
			</td>
		</tr>
		</tbody>
	</table>

	<div class="text-right">
		<a href="javascript:parent.modalBoxClose()" class="btn btn-default btn-sm"><spring:message code="button.close"/></a>
	</div>
	</form>
<script type="text/javascript">
	var summernote;
	var enableQstnNo;
	// 페이지 초기화
	$(document).ready(function() {
		enableQstnNo = Number($("#lastQstnNo", parent.subWorkFrame.document ).val()) +1;
		$('.inputNumber').inputNumber();	// 숫자만 입력되도록 설정.
		$('.inputSpecial').inputSpecial();  // 특수키가 입력이 들어오묜 false return;
	});

</script>
