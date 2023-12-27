<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<html>
<head>

</head>

<body>
	<form id="examForm" name="examForm" onsubmit="return false">
		<input type="hidden" name="crsCreCd" value="${vo.crsCreCd}" />
		<input type="hidden" name="examSn" value="${vo.examSn}" />
		<input type="hidden" name="stdNo" value="${vo.stdNo}" />
		<input type="hidden" name="getScores" value="${vo.getScores}" />
		<input type="hidden" name="stareTm" value="${vo.stareTm}"/>
		<input type="hidden" name="totGetScore" value="${vo.totGetScore}" />
		<input type="hidden" name="stareAnss" value="${vo.stareAnss}" />
		<input type="hidden" name="stareCnt" value="${vo.stareCnt}" />
		<div class="modal_cont">
			<c:forEach items="${questionList}" var="item" varStatus="status">
   
                    <div class="list_view_box">
                        <div class="list_title">
                            <div class="type"><span class="num">${status.count}.</span><meditag:codename code="${item.qstnType}" category="EXAM_QSTN_TYPE" /> 문항</div>
                            <div class="score"><meditag:codename code="${item.qstnType}" category="EXAM_QSTN_TYPE" /> : ${item.qstnScore}<spring:message code="common.title.score"/></div>
                        </div>
                        <div class="list_item">
                            <div class="quest">${item.title}</div>
                          	  <c:if test="${not empty item.qstnCts}">
	                       		 <div class="exam_area">${item.qstnCts}</div>
                        	</c:if>
                            
						<c:if test="${item.qstnType eq 'J'}">	<!-- 서술형 -->
							<div class="form-row">
       							<textarea name="ans_${status.count}" id="ans_${status.count}" rows="5"  class="form-control"  placeholder="답을 입력하세요"  onpaste="return false;" oncopy="return false;"></textarea>
       						</div>
        				</c:if>
        				<c:if test="${item.qstnType eq 'D'}">	<!--  단답형 -->
	                        <div class="form-inline">
	                                <input class="form-control" type="text" name="ans_${status.count}" id="ans_${status.count}" placeholder="답을 입력하세요" onpaste="return false;" oncopy="return false;">
<!-- 	                                <input class="form-control" type="text" name="name" id="nameInput" placeholder="2번 답">
	                                <input class="form-control" type="text" name="name" id="nameInput" placeholder="3번 답">
 -->	                                
	                        </div>
         				</c:if>	
						<c:if test="${item.qstnType eq 'K'}">	<!-- 선택형 -->
                            <ol class="field">
							<c:if test="${not empty item.empl1}">
                               <li class="ui checkbox"><input type="radio" id="ans_${status.count}_01" name="ans_${status.count}" class="hidden" value="1"><label for="ans_${status.count}_01">${item.empl1}</label></li>
							</c:if>
							<c:if test="${not empty item.empl2}">
                               <li class="ui checkbox"><input type="radio" id="ans_${status.count}_02" name="ans_${status.count}" class="hidden" value="2"><label for="ans_${status.count}_02">${item.empl2}</label></li>
							</c:if>
							<c:if test="${not empty item.empl3}">
                               <li class="ui checkbox"><input type="radio" id="ans_${status.count}_03" name="ans_${status.count}" class="hidden" value="3"><label for="ans_${status.count}_03">${item.empl3}</label></li>
							</c:if>
							<c:if test="${not empty item.empl4}">
                               <li class="ui checkbox"><input type="radio" id="ans_${status.count}_04" name="ans_${status.count}" class="hidden" value="4"><label for="ans_${status.count}_04">${item.empl4}</label></li>
							</c:if>
							<c:if test="${not empty item.empl5}">
                               <li class="ui checkbox"><input type="radio" id="ans_${status.count}_05" name="ans_${status.count}" class="hidden" value="5"><label for="ans_${status.count}_05">${item.empl5}</label></li>
							</c:if>
                            </ol>
						</c:if>
          				<c:if test="${item.qstnType eq 'S'}">	<!-- 진위형 -->
                            <div class="checkImg">
                                <input id="ans_${status.count}_01" type="radio" name="ans_${status.count}" value="O">
                                <label class="imgChk true" for="ans_${status.count}_01"></label>
                                <input id="ans_${status.count}_02" type="radio" name="ans_${status.count}" value="X">
                                <label class="imgChk false" for="ans_${status.count}_02"></label>
                            </div>

        				</c:if>                            

                        </div>
                    </div>
            </c:forEach>
            </div>     
             <div class="modal_btns">
                    <button type="button" class="btn" onclick="parent.modalBoxClose()"><spring:message code="button.close"/></button>
                </div>
	</form>

<script type="text/javascript">
	$(document).ready(function() {
		$("body").css("padding-top","0px").css("min-height","0px");
	});
</script>
</body>
</html>