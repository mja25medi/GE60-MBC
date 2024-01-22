<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="examListVO" value="${examListVO}"/>
<c:set var="pageInfo" value="${pageInfo}"/>

	<table summary="<spring:message code="lecture.title.exam.manage"/>" class="table table-bordered wordbreak">
		<colgroup>
				<col style="width:50px"/>
				<col style="width:auto"/>
				<col style="width:250px"/>
				<col style="width:250px"/>
				<col style="width:80px"/>
				<col style="width:80px"/>
				<col style="width:80px"/>
				<col style="width:80px"/>
				<col style="width:72px"/>
		</colgroup>
		<thead>
			<tr>
				<th scope="col"><spring:message code="common.title.no"/></th>
				<th scope="col"><spring:message code="lecture.title.exam.name"/></th>
				<th scope="col"><spring:message code="lecture.title.exam.duration"/></th>
				<th scope="col"><spring:message code="lecture.title.exam.answer.ratio"/></th>
				<th scope="col"><spring:message code="lecture.title.exam.examtype"/></th>
				<th scope="col"><spring:message code="lecture.title.exam.ansrtype"/></th>
				<th scope="col"><spring:message code="lecture.title.exam.timelimit"/></th>
				<th scope="col"><spring:message code="lecture.title.exam.regyn"/></th>
				<th scope="col"><spring:message code="common.title.manage"/></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${examListVO}" varStatus="status">
			<tr>
				<td class="text-right">${status.count}</td>
				<td class="wordbreak">
					${item.examTitle}
					<a onclick="javascript:examEdit('${item.examSn}');" style="cursor: pointer;"><i class="fa fa-cog"></i></a>
				</td>
				<td style="text-align: center;">
					<c:choose>
						<c:when test="${item.examStareTypeCd eq 'R'}">
							<meditag:dateformat type="1" delimeter="." property="${item.examStartDttm}"/> <meditag:dateformat type="7" delimeter="." property="${item.examStartDttm}"/> ~ <meditag:dateformat type="1" delimeter="." property="${item.examEndDttm}"/> <meditag:dateformat type="7" delimeter="." property="${item.examEndDttm}"/>
						</c:when>
						<c:otherwise>
						<span>-</span>
						</c:otherwise>
					</c:choose>
				</td>
				<td style="text-align: center;">
					<c:choose>
						<c:when test="${item.examStareTypeCd eq 'R'}">
								<span>-</span>
						</c:when>
						<c:otherwise>
							<spring:message code="lecture.message.exam.progress.ratio"/> ${item.stareCritPrgrRatio}% <spring:message code="lecture.message.exam.over"/>
						</c:otherwise>
					</c:choose>
				</td>
				<td class="text-center"><meditag:codename category="EXAM_TYPE_CD" code="${item.examTypeCd}"/></td>
				<td class="text-center">
					<c:choose>
						<c:when test="${item.examTypeCd eq 'ON' }">
							<meditag:codename category="EXAM_STARE_TYPE_CD" code="${item.examStareTypeCd}"/>
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>
				</td>
				<td class="text-center">
					<c:choose>
						<c:when test="${item.examTypeCd eq 'ON' }">
							<c:choose>
								<c:when test="${item.stareTmUseYn eq 'Y' }">
									${item.examStareTm} <spring:message code="common.title.min"/>
								</c:when>
								<c:otherwise>
									<spring:message code="lecture.title.exam.Unlimited"/>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>
				</td>
				<td class="text-center">
					<c:set var="regYn" value="${item.regYn}"/>
					<c:if test="${empty item.regYn}"><c:set var="regYn" value="N"/></c:if>
					<meditag:codename code="${regYn}" category="REG_YN"/>
				</td>
				<td class="text-center">
					<a class="btn btn-info btn-sm" href="<c:url value="/mng/lecture/exam/manageFormExam"/>?crsCreCd=${item.crsCreCd}&amp;examSn=${item.examSn}"><spring:message code="button.manage"/></button>
				</td>
			</tr>
			</c:forEach>
			<c:if test="${empty examListVO}">
			<tr>
				<td colspan="9"><spring:message code="lecture.message.exam.nodata"/></td>
			</tr>
			</c:if>
		</tbody>
	</table>
