<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="examQbankQstnVO" value="${vo}" />
	<ul class="list-group">
		<li class="list-group-examQbankQstnVO wordbreak">
			<p>${examQbankQstnVO.title} (<meditag:codename code="${examQbankQstnVO.qstnType}" category="EXAM_QSTN_TYPE" />)</p>
			<div class="well well-sm exam-contents" style="margin-top:10px;">
      			<span style="margin-bottom:10px;">${examQbankQstnVO.qstnCts}</span>
      			<!-- 선택형 -->
      			<c:if test="${examQbankQstnVO.qstnType eq 'K'}">
					<ul style="list-style-type:none;padding:10px ;line-height:25px;">
					<c:if test="${not empty examQbankQstnVO.view1}">
					<li><label><input type="radio" name="ans_${examQbankQstnVO.qstnSn}" id="ans_${examQbankQstnVO.qstnSn}"  value="1"/> 1. ${examQbankQstnVO.view1}</label></li>
					</c:if>
					<c:if test="${not empty examQbankQstnVO.view2}">
					<li><label><input type="radio" name="ans_${examQbankQstnVO.qstnSn}" id="ans_${examQbankQstnVO.qstnSn}"  value="2"/> 2. ${examQbankQstnVO.view2}</label></li>
					</c:if>
					<c:if test="${not empty examQbankQstnVO.view3}">
					<li><label><input type="radio" name="ans_${examQbankQstnVO.qstnSn}" id="ans_${examQbankQstnVO.qstnSn}"  value="3"/> 3. ${examQbankQstnVO.view3}</label></li>
					</c:if>
					<c:if test="${not empty examQbankQstnVO.view4}">
					<li><label><input type="radio" name="ans_${examQbankQstnVO.qstnSn}" id="ans_${examQbankQstnVO.qstnSn}"  value="4"/> 4. ${examQbankQstnVO.view4}</label></li>
					</c:if>
					<c:if test="${not empty examQbankQstnVO.view5}">
					<li><label><input type="radio" name="ans_${examQbankQstnVO.qstnSn}" id="ans_${examQbankQstnVO.qstnSn}"  value="5"/> 5. ${examQbankQstnVO.view5}</label></li>
					</c:if>
					</ul>
     			</c:if>
     			<!-- 진위형 -->
     			<c:if test="${examQbankQstnVO.qstnType eq 'S'}">
      				<label for="o"><input type="radio" name="ans_${examQbankQstnVO.qstnSn}" id="ans_${examQbankQstnVO.qstnSn}" value="O"/>&nbsp;O</label>
					<label for="x" style="margin-left:20px;"><input type="radio" name="ans_${examQbankQstnVO.qstnSn}" id="ans_${examQbankQstnVO.qstnSn}" value="X"/>&nbsp;X</label>
     			</c:if>
     			<!-- 단단형 -->
     			<c:if test="${examQbankQstnVO.qstnType eq 'D'}">
     					<input type="text" name="ans_${examQbankQstnVO.qstnSn}" id="ans_${examQbankQstnVO.qstnSn}" class="form-control input-sm"/>
     			</c:if>
     			<!-- 서술형 -->
     			<c:if test="${examQbankQstnVO.qstnType eq 'J'}">
     					<textarea name="ans_${examQbankQstnVO.qstnSn}" id="ans_${examQbankQstnVO.qstnSn}" cols="1" rows="5"  class="form-control input-sm"></textarea>
     			</c:if>
     		</div>
		</li>
	</ul>
	<div class="text-right">
		<a href="javascript:parent.modalBoxClose()" class="btn btn-default btn-sm"><spring:message code="button.close"/></a>
	</div>
