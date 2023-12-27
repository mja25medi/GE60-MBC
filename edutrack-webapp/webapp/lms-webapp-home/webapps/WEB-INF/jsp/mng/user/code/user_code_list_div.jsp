<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>

				<table summary="<spring:message code="user.title.userinfo.manage"/>" class="table table-bordered wordbreak">
					<colgroup>
						<col style="width:60px"/>
						<col style="width:200px"/>
						<col style="width:auto"/>
						<col style="width:150px"/>
					</colgroup>
					<thead>
						<tr>
							<th scope="col"><spring:message code="common.title.no"/> </th>
							<th scope='col'>
							<c:choose>
								<c:when test="${codeCtgrCd eq 'USER_DIV_CD' }"><spring:message code="user.title.user.divcd.manage"/>&nbsp;<spring:message code="system.title.code.code"/></c:when>
								<c:when test="${codeCtgrCd eq 'AREA_CD' }"><spring:message code="user.title.user.areacd.manage"/>&nbsp;<spring:message code="system.title.code.code"/></c:when>
								<c:when test="${codeCtgrCd eq 'INTEREST_FIELD_CD' }"><spring:message code="user.title.user.interestcd.manage"/>&nbsp;<spring:message code="system.title.code.code"/></c:when>
								<c:when test="${codeCtgrCd eq 'CCL_CD' }"><spring:message code="library.title.contents.ccl"/>&nbsp;<spring:message code="system.title.code.code"/></c:when>
							</c:choose>
							</th>
							<th scope='col'>
							<c:choose>
								<c:when test="${codeCtgrCd eq 'USER_DIV_CD' }"><spring:message code="user.title.user.divcd.manage"/>&nbsp;<spring:message code="system.title.code.name"/></c:when>
								<c:when test="${codeCtgrCd eq 'AREA_CD' }"><spring:message code="user.title.user.areacd.manage"/>&nbsp;<spring:message code="system.title.code.name"/></c:when>
								<c:when test="${codeCtgrCd eq 'INTEREST_FIELD_CD' }"><spring:message code="user.title.user.interestcd.manage"/>&nbsp;<meditag:message messageKey="system.title.code.name"/></c:when>
								<c:when test="${codeCtgrCd eq 'CCL_CD' }"><spring:message code="library.title.contents.ccl"/>&nbsp;<spring:message code="system.title.code.name"/></c:when>
							</c:choose>
							</th>
							<th scope="col"><spring:message code="course.open.title.category.useyn"/></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${codeList}" var="item" varStatus="status">
						<tr>
							<td class="text-center">${pageInfo.totalRecordCount -(pageInfo.recordCountPerPage * (pageInfo.currentPageNo -1)) - status.count + 1}</td>
							<td class="text-center">${item.codeCd }</td>
							<td class="wordbreak">
								${item.codeNm}
								<a href="javascript:userCodeEdit('${item.codeCd}');" ><i class="fa fa-cog"></i></a>
							</td>
							<td class="text-center"><meditag:codename code="${item.useYn}" category="USE_YN"/></td>
						</tr>
						</c:forEach>
						<c:if test="${empty codeList}">
						<tr>
							<td colspan="4"><spring:message code="common.message.nodata"/></td>
						</tr>
						</c:if>
					</tbody>
				</table>
				<meditag:paging pageInfo="${pageInfo}" funcName="listPageing"/>