<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="examQuestionListVO" value="${examQuestionListVO}"/>

		<table summary="<spring:message code="lecture.title.exam.question.manage"/>" class="table table-bordered wordbreak">
			<colgroup>
				<col style="width:50px;">
				<col style="width:auto;">
				<col style="width:90px;">
				<col style="width:50px;">
			</colgroup>
			<thead>
				<tr>
					<th scope="col"><spring:message code="common.title.no"/></th>
					<th scope="col"><spring:message code="lecture.title.exam.question"/></th>
					<th scope="col"><spring:message code="lecture.title.exam.question.type"/></th>
					<th scope="col"><spring:message code="common.title.manage"/></th>
				</tr>
			</thead>
			<tbody>
				<c:set var="qstnCnt" value="0"/>
				<c:set var="lsitStart" value="N"/>
				<c:set var="lastQstnNo" value="0"/>
				<c:forEach var="item" items="${examQuestionListVO}" varStatus="status">
					<c:choose>
						<c:when test="${qstnCnt < item.qstnNo}">
							<c:set var="qstnCnt" value="${item.qstnNo}"/>
				<tr>
					<input type="hidden" name="examQstnSn" value="${item.examQstnSn}"/>
					<input type="hidden" name="qstnNo" value="${item.qstnNo}"/>
					<td rowspan="${item.qstnCnt}" class="text-right">${item.qstnNo}</td>
					<td class="wordbreak"><a href="javascript:viewQstn('${item.examQstnSn}');" >${item.title}</a></td>
					<td><meditag:codename code="${item.qstnType}" category="EXAM_QSTN_TYPE"/></td>
					<td class="text-center">
					<c:if test="${regYn eq 'Y' }">-</c:if>
					<c:if test="${regYn ne 'Y' }"><a href="javascript:questionEdit('${item.examQstnSn}');" class="btn btn-info btn-sm"><spring:message code="button.edit"/></a></c:if>
					</td>
				</tr>
						</c:when>
						<c:otherwise>
				<tr>
					<input type="hidden" name="examQstnSn" value="${item.examQstnSn}"/>
					<input type="hidden" name="qstnNo" value="${item.qstnNo}"/>
					<td rowspan="${item.qstnCnt}" class="text-right">${item.qstnNo}</td>
					<td class="wordbreak"><a href="javascript:viewQstn('${item.examQstnSn}');" >${item.title}</a></td>
					<td><meditag:codename code="${item.qstnType}" category="EXAM_QSTN_TYPE"/></td>
					<td class="text-center">
					<c:if test="${regYn eq 'Y' }">-</c:if>
					<c:if test="${regYn ne 'Y' }"><a href="javascript:questionEdit('${item.examQstnSn}');" class="btn btn-info btn-sm"><spring:message code="button.edit"/></a></c:if>
					</td>
				</tr>
						</c:otherwise>
					</c:choose>
				<c:if test="${status.last }"><c:set var="lastQstnNo" value="${item.qstnNo}"/></c:if>
				</c:forEach>
				<c:if test="${empty examQuestionListVO}">
				<tr>
					<td colspan="5"><spring:message code="lecture.message.exam.question.nodata"/></td>
				</tr>
				</c:if>
			</tbody>
		</table>
		<input type="hidden" id="lastQstnNo" name="lastQstnNo" value="${lastQstnNo }"/>