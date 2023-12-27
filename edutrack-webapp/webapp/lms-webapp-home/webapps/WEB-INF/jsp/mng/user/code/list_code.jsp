<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
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
													<th scope='col'><spring:message code="user.title.userinfo.code"/></th>
													<th scope='col'><spring:message code="user.title.userinfo.codename"/></th>
													<th scope="col"><spring:message code="course.open.title.category.useyn"/></th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${codeList}" var="item" varStatus="status">
												<tr>
													<td class="text-center">${pageInfo.totalRecordCount - ((pageInfo.currentPageNo- 1) * pageInfo.recordCountPerPage) - status.index }</td>
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