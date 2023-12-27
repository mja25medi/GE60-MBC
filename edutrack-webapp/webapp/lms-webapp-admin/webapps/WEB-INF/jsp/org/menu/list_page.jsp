<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>


								<table summary="<spring:message code="org.title.orgmenu.manage"/>" class="table table-bordered">
									<colgroup>
										<col style="width:auto"/>
										<col style="width:80px"/>
										<col style="width:80px"/>
									</colgroup>
									<thead>
										<tr>
											<th scope="col"><spring:message code="system.title.page.name"/></th>
											<th scope="col"><spring:message code="common.title.useyn"/></th>
											<th scope="col"><spring:message code="common.title.select"/></th>
										</tr>
									</thead>
									<tbody>
									<c:forEach var="item" items="${pageList}" varStatus="status">
										<tr>
											<td>${item.pageNm}</td>
											<td class="text-center"><meditag:codename code="${item.useYn}" category="USE_YN"/></td>
											<td class="text-center"><a href="javascript:selectPage('${item.pageNm}','${item.pageCd}')" class="btn btn-primary btn-sm"><spring:message code="common.title.select" /></a></td>
										</tr>
									</c:forEach>
									<c:if test="${empty pageList}">
										<tr>
											<td colspan="3" class="text-center"><spring:message code="board.message.bbs.info.nodata"/></td>
										</tr>
									</c:if>
									</tbody>
								</table>

