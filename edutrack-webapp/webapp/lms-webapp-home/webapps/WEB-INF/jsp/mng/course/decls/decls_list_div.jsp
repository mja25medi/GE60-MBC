<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="creCrsDeclsList" value="${creCrsDeclsList}"/>

		<table summary="<spring:message code="course.title.decls.manage"/>" class="table table-bordered wordbreak">
			<colgroup>
				<col style="width:auto"/>
				<col style="width:auto"/>
				<col style="width:auto"/>
				<col style="width:auto"/>
				<col style="width:auto"/>
				<col style="width:auto"/>
			</colgroup>
			<thead>
				<tr>
					<th scope="col"><spring:message code="course.title.decls.name"/></th>
					<th scope="col"><spring:message code="course.title.decls.student"/></th>
					<th scope="col"><spring:message code="course.title.decls.enrlstd"/></th>
					<th scope="col"><spring:message code="course.title.decls.confstd"/></th>
					<th scope="col"><spring:message code="common.title.delete"/></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${creCrsDeclsList}" var="item" varStatus="status">
				<tr>
					<td>${item.declsNo}</td>
					<td class="text-right">${item.stuCnt} <spring:message code="common.title.cnt.user"/></td>
					<td class="text-right">${item.enrlCnt} <spring:message code="common.title.cnt.user"/></td>
					<td class="text-right">${item.cnfmCnt} <spring:message code="common.title.cnt.user"/></td>
					<td class="text-center">
						<c:if test="${item.declsNo != 1 && status.last}">
						<button class="btn btn-warning btn-sm" onclick="javascript:delDecls('${item.declsNo}','${item.stuCnt}');"><spring:message code="button.delete"/></button>
						</c:if>
					</td>
				</tr>
				</c:forEach>
				<c:if test="${empty creCrsDeclsList}">
				<tr>
					<td colspan="5"><spring:message code="course.message.decls.nodata"/></td>
				</tr>
				</c:if>
			</tbody>
		</table>

<script type="text/javascript">
	declsCnt = ${declsCnt};
</script>