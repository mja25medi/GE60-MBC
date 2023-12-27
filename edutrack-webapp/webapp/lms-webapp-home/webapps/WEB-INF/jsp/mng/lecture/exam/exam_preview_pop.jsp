<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>

	<form id="examForm" name="examForm" onsubmit="return false" action="/lec/exam">
		<input type="hidden" name="crsCreCd" value="${vo.crsCreCd }" />
		<input type="hidden" name="examSn" value="${vo.examSn }" />
		<input type="hidden" name="stdNo" value="${vo.stdNo }" />
		<input type="hidden" name="getScores" value="${vo.getScores }" />
		<input type="hidden" name="stareTm" value="${vo.stareTm }"/>
		<input type="hidden" name="totGetScore" value="${vo.totGetScore }" />
		<input type="hidden" name="stareAnss" value="${vo.stareAnss }" />
		<input type="hidden" name="stareCnt" value="${vo.stareCnt }" />
		<ul class="list-group">
		<c:forEach items="${questionList}" var="item" varStatus="status">
			<li class="list-group-item wordbreak">
				<p>${status.count}. ${item.title} (<meditag:codename code="${item.qstnType}" category="EXAM_QSTN_TYPE" /> : ${item.qstnScore}<spring:message code="common.title.score"/>)</p>
				<div class="well well-sm exam-contents" style="margin-top:10px;">
       							<span style="margin-bottom:10px;">${item.qstnCts}</span>
       							<c:if test="${item.qstnType eq 'K'}">
					<ul style="list-style-type:none;padding:10px ;line-height:25px;">
						<c:if test="${not empty item.empl1}">
						<li><label><input type="radio" name="ans_${item.qstnNo}" id="ans_${item.qstnNo}"  value="1"/> 1. ${item.empl1}</label></li>
						</c:if>
						<c:if test="${not empty item.empl2}">
						<li><label><input type="radio" name="ans_${item.qstnNo}" id="ans_${item.qstnNo}"  value="2"/> 2. ${item.empl2}</label></li>
						</c:if>
						<c:if test="${not empty item.empl3}">
						<li><label><input type="radio" name="ans_${item.qstnNo}" id="ans_${item.qstnNo}"  value="3"/> 3. ${item.empl3}</label></li>
						</c:if>
						<c:if test="${not empty item.empl4}">
						<li><label><input type="radio" name="ans_${item.qstnNo}" id="ans_${item.qstnNo}"  value="4"/> 4. ${item.empl4}</label></li>
						</c:if>
						<c:if test="${not empty item.empl5}">
						<li><label><input type="radio" name="ans_${item.qstnNo}" id="ans_${item.qstnNo}"  value="5"/> 5. ${item.empl5}</label></li>
						</c:if>
					</ul>
      							</c:if>
      							<c:if test="${item.qstnType eq 'S'}">
      							<p>
      							<label for="o"><input type="radio" name="ans_${item.qstnNo}" id="ans_${item.qstnNo}" value="O"/>&nbsp;O</label>
								<label for="x" style="margin-left:20px;"><input type="radio" name="ans_${item.qstnNo}" id="ans_${item.qstnNo}" value="X"/>&nbsp;X</label>
								</p>
      							</c:if>
      							<c:if test="${item.qstnType eq 'D'}">
      							<input type="text" name="ans_${item.qstnNo}" id="ans_${item.qstnNo}" class="form-control input-sm"/>
      							</c:if>
      							<c:if test="${item.qstnType eq 'J'}">
      							<textarea name="ans_${item.qstnNo}" id="ans_${item.qstnNo}" cols="1" rows="5"  class="form-control input-sm"></textarea>
      							</c:if>
      						</div>
			</li>
		</c:forEach>
		</ul>
		<div class="text-right">
		<a href="javascript:parent.modalBoxClose()" class="btn btn-default btn-sm"><spring:message code="button.close"/></a>
	</div>
	</form>
