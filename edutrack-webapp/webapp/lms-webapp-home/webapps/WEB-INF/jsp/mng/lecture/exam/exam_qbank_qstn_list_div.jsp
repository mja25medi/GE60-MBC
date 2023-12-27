<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>

		<table summary="<spring:message code="lecture.title.exam.question.manage"/>" class="table table-bordered wordbreak">
			<colgroup>
				<col style="width:50px;"/>
				<col style="width:auto;"/>
				<col style="width:100px;"/>
				<col style="width:50px;"/>
			</colgroup>
			<thead>
				<tr>
					<th scope="col"><spring:message code="common.title.no"/></th>
					<th scope="col"><spring:message code="lecture.title.exam.question"/></th>
					<th scope="col"><spring:message code="lecture.title.exam.question.type"/></th>
					<th scope="col" class="text-center"><input type="checkbox" name="chkAll" id="chkAll" style="border:0" onclick="checkAll()"/></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${examQuestionList}" varStatus="status">
				<tr>
					<td class="text-right">${status.count}</td>
					<td class="wordbreak">${item.title}</td>
					<td class="text-center"><meditag:codename code="${item.qstnType}" category="EXAM_QSTN_TYPE"/> </td>
					<td class="text-center">
						<input type='checkbox' name='sel' value='${item.qstnSn}' style='border:0'>
						<input type="hidden" name="selCtgrCd" id="selCtgrCd${item.qstnSn}" value="${item.ctgrCd}">
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