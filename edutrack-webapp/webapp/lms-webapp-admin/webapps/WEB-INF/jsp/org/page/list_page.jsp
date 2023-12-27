<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>

						<table summary="<spring:message code="system.title.page.manage"/>" class="table table-bordered">
							<colgroup>
								<col style="width:auto"/>
								<col style="width:110px"/>
								<col style="width:auto"/>
								<col style="width:70px"/>
								<col style="width:50px"/>
							</colgroup>
							<thead>
								<tr>
									<th scope='col'><spring:message code="org.title.orginfo.orgname"/></th>
									<th scope='col'><spring:message code="system.title.page.code"/></th>
									<th scope='col'><spring:message code="system.title.page.name"/></th>
									<th scope='col'><spring:message code="common.title.useyn"/></th>
									<th scope='col'><spring:message code="common.title.edit"/></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${pageList}" var="item" varStatus="status">
								<tr>
									<td class="wordbreak">${item.orgNm}</td>
									<td class="text-center">${item.pageCd}</td>
									<td class="wordbreak">${item.pageNm}</td>
									<td class="text-center"><meditag:codename code="${item.useYn}" category="USE_YN"/></td>
									<td class="text-center">
										<button class="btn btn-primary btn-sm" onclick="javascript:pageEdit('${item.orgCd}','${item.pageCd}');"><spring:message code="button.edit"/></button>
									</td>
								</tr>
								</c:forEach>
								<c:if test="${empty pageList}">
								<tr>
									<td colspan="5"><spring:message code="common.message.nodata"/></td>
								</tr>
								</c:if>
							</tbody>
						</table>
						<meditag:paging pageInfo="${pageInfo}" funcName="listPageing"/>