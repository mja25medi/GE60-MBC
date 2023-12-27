<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>

										<table summary="<spring:message code="system.title.code.manage"/>" class="table table-bordered">
											<colgroup>
												<col style="width:auto"/>
												<col style="width:auto"/>
												<col style="width:80px"/>
												<col style="width:50px"/>
											</colgroup>
											<thead>
											<tr>
												<th scope='col'><spring:message code="system.title.code.name"/></th>
												<th scope='col'><spring:message code="system.title.code.code"/></th>
												<th scope='col'><spring:message code="common.title.useyn"/></th>
												<th scope='col'><spring:message code="common.title.edit"/></th>
											</tr>
											</thead>
											<tbody id="tbodyCode">
												<c:forEach items="${codeList}" var="item" varStatus="status">
												<tr>
													<td class="wordbreak">${item.codeNm}</td>
													<td>${item.codeCd}</td>
													<td class="text-center"><meditag:codename code="${item.useYn}" category="USE_YN"/></td>
													<td class="text-center">
														<a class="btn btn-primary btn-sm" href="javascript:codeEdit('${item.codeCd}');" title="<spring:message code="button.edit"/>"><spring:message code="button.edit"/></a>
													</td>
												</tr>
												</c:forEach>
												<c:if test="${empty codeList}">
												<tr>
													<td colspan="4"><spring:message code="common.message.nodata"/></td>
												</tr>
												</c:if>
											</tbody>
										</table>