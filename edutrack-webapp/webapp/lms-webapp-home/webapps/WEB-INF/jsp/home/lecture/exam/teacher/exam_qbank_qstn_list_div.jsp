<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
		<table>
           <caption>시험문제 목록</caption>
           <thead>
               <tr>
                   <th scope="col" width="8%"><spring:message code="common.title.no"/></th>
                   <th scope="col"><spring:message code="lecture.title.exam.question"/></th>
                   <th scope="col" width="15%"><spring:message code="lecture.title.exam.question.type"/></th>
                   <th scope="col" width="10%"><span class="custom-input onlychk"><input type="checkbox" name="chkAll" id="chkall"  onclick="checkAll()"><label for="chkall"></label></span></th>
               </tr>
           </thead>
           <tbody>
          	 <c:forEach var="item" items="${examQuestionList}" varStatus="status">
	               <tr>
	                   <td scope="row" data-label="번호">${status.count}</td>
	                   <td class="title" data-label="시험문제">${item.title}</td>
	                   <td data-label="문제유형"><meditag:codename code="${item.qstnType}" category="EXAM_QSTN_TYPE"/></td>
	                   <td data-label="선택">
		                   	<span class="custom-input onlychk">
		                   		<input type='checkbox' id="sel${status.count}" name='sel' value='${item.qstnSn}'><label for="sel${status.count}"></label>
								<input type="hidden" name="selCtgrCd" id="selCtgrCd${item.qstnSn}" value="${item.ctgrCd}">
	                   		</span>
                   		</td>
	               </tr>
               </c:forEach>
               <c:if test="${empty examQuestionList}">
					<tr>
					<td colspan="4"><spring:message code="lecture.message.exam.question.nodata"/></td>
				</tr>
				</c:if>
           </tbody>
       </table>
		<%--
		<meditag:paging pageInfo="${pageInfo}" funcName="listQuestion"/>
		--%>