<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:if test="${not empty eduResultVO }">
 	<table class="table table-bordered wordbreak">
 		<tr class="info">
 			<th><spring:message code="student.title.result.totalscore"/></strong>(100%)</th>
 		<c:if test="${createCourseVO.prgrRatio > 0}">
			<th><spring:message code="student.title.result.ratio.prgr"/></strong>(${createCourseVO.prgrRatio}%)</th>
		</c:if>
		<c:if test="${createCourseVO.attdRatio > 0}">
			<th><spring:message code="student.title.result.ratio.attd"/></strong>(${createCourseVO.attdRatio}%)</th>
		</c:if>
		<c:if test="${createCourseVO.examRatio > 0}">
			<th><spring:message code="student.title.result.ratio.exam"/></strong>(${createCourseVO.examRatio}%)</th>
		</c:if>
		<c:if test="${createCourseVO.asmtRatio > 0}">
			<th><spring:message code="student.title.result.ratio.asmt"/></strong>(${createCourseVO.asmtRatio}%)</th>
		</c:if>
		<c:if test="${createCourseVO.forumRatio > 0}">
			<th><spring:message code="student.title.result.ratio.forum"/></strong>(${createCourseVO.forumRatio}%)</th>
		</c:if>
		<c:if test="${createCourseVO.prjtRatio > 0}">
			<th><spring:message code="student.title.result.ratio.project"/></strong>(${createCourseVO.prjtRatio}%)</th>
		</c:if>
		<c:if test="${createCourseVO.joinRatio > 0}">
			<th><spring:message code="student.title.result.ratio.join"/></strong>(${createCourseVO.joinRatio}%)</th>
		</c:if>
		<c:if test="${createCourseVO.etcRatio > 0}">
			<th><spring:message code="student.title.result.ratio.etc"/></strong>(${createCourseVO.etcRatio}%)</th>
		</c:if>
 		</tr>
 		<tr>
			<td class="text-center">${eduResultVO.totScore} <spring:message code="common.title.score"/></td>
 		<c:if test="${createCourseVO.prgrRatio > 0}">
			<td class="text-center">${eduResultVO.prgrScore} <spring:message code="common.title.score"/></td>
		</c:if>
		<c:if test="${createCourseVO.attdRatio > 0}">
			<td class="text-center">${eduResultVO.attdScore} <spring:message code="common.title.score"/></td>
		</c:if>
		<c:if test="${createCourseVO.examRatio > 0}">
			<td class="text-center">${eduResultVO.examScore} <spring:message code="common.title.score"/></td>
		</c:if>
		<c:if test="${createCourseVO.asmtRatio > 0}">
			<td class="text-center">${eduResultVO.asmtScore} <spring:message code="common.title.score"/></td>
		</c:if>
		<c:if test="${createCourseVO.forumRatio > 0}">
			<td class="text-center">${eduResultVO.forumScore} <spring:message code="common.title.score"/></td>
		</c:if>
		<c:if test="${createCourseVO.prjtRatio > 0}">
			<td class="text-center">${eduResultVO.prjtScore} <spring:message code="common.title.score"/></td>
		</c:if>
		<c:if test="${createCourseVO.joinRatio > 0}">
			<td class="text-center">${eduResultVO.joinScore} <spring:message code="common.title.score"/></td>
		</c:if>
		<c:if test="${createCourseVO.etcRatio > 0}">
			<td class="text-center">${eduResultVO.etcScore} <spring:message code="common.title.score"/></td>
		</c:if>
 		</tr>
 	</table>

</c:if>
