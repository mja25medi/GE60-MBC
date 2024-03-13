<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="assignmentListVO" value="${assignmentListVO}"/>

		<table summary="<spring:message code="lecture.title.assignment.manage"/>" class="table table-bordered wordbreak">
			<colgroup>
				<col style="width:50px;"/>
				<col style="width:auto"/>
				<col style="width:165px;"/>
				<col style="width:80px"/>
				<col style="width:80px"/>
				<col style="width:80px"/>
				<col style="width:80px"/>
				<col style="width:70px;"/>
			</colgroup>
				<tr>
					<th scope="col" ><spring:message code="common.title.no"/></th>
					<th scope="col" ><spring:message code="lecture.title.assignment.name"/></th>
					<th scope="col" ><spring:message code="lecture.title.assignment.duration"/></th>
					<th scope="col" ><spring:message code="lecture.title.assignment.type"/></th>
					<th scope="col" >서비스유형</th>
					<th scope="col" ><spring:message code="lecture.title.assignment.seltype"/></th>
					<th scope="col" ><spring:message code="lecture.title.assignment.regyn"/></th>
					<th scope="col" ><spring:message code="common.title.manage"/></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${assignmentListVO }" varStatus="status">
				<tr>
					<td class="text-right">${status.count}</td>
					<td class="wordbreak">
						${item.asmtTitle}
						<a href="javascript:editAsmt('${item.asmtSn}');" ><i class="fa fa-cog"></i></a>
					</td>
					<td class="text-center">
					<c:choose>
						<c:when test="${item.asmtStareTypeCd eq 'S' }">-</c:when>
						<c:when test="${item.asmtSvcCd eq 'CODE' }">-</c:when>
						<c:otherwise>${item.asmtDuration}</c:otherwise>
					</c:choose>		
					</td>
					<td class="text-center"><meditag:codename code="${item.asmtTypeCd}" category="ASMT_TYPE_CD"/></td>
					<td class="text-center"><meditag:codename code="${item.asmtSvcCd}" category="ASMT_SVC_CD"/></td>
					<td class="text-center">
						<c:choose>
							<c:when test="${item.asmtSvcCd eq 'CODE' || item.asmtTypeCd eq 'OFF'  }">-</c:when>
							<c:otherwise><meditag:codename code="${item.asmtSelectTypeCd}" category="ASMT_SELECT_TYPE_CD" /></c:otherwise>
						</c:choose>					
					</td>
					<td class="text-center">
						<c:set var="regYn" value="${item.regYn}"/>
						<c:if test="${empty item.regYn}"><c:set var="regYn" value="N"/></c:if>
						<meditag:codename code="${regYn}" category="REG_YN"/>
					</td>
					<td class="text-center"><a href="<c:url value="/mng/lecture/assignment/manageAsmtMain"/>?crsCreCd=${item.crsCreCd}&amp;asmtSn=${item.asmtSn}" class="btn btn-info btn-sm"><spring:message code="common.title.manage"/></a></td>
				</tr>
				</c:forEach>
				<c:if test="${empty assignmentListVO}">
				<tr>
					<td colspan="7"><spring:message code="lecture.message.assignment.nodata"/></td>
				</tr>
				</c:if>
			</tbody>
		</table>

