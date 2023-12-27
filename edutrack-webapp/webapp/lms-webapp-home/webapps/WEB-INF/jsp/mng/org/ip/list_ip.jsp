<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
						<table summary="<spring:message code="org.title.conn.ip.manage"/>" class="table table-bordered wordbreak">
							<colgroup>
								<col style="width:100px"/>
								<col style="width:auto"/>
								<col style="width:12%"/>
								<col style="width:100px"/>
								<col style="width:80px"/>
							</colgroup>
							<thead>
								<tr>
									<th scope='col'><spring:message code="common.title.no"/></th>
									<th scope='col'><spring:message code="org.title.conn.ip"/></th>
									<th scope='col'><spring:message code="org.title.conn.ip.type"/></th>
									<th scope='col'><spring:message code="common.title.useyn"/></th>
									<th scope='col'><spring:message code="common.title.delete"/></th>
								</tr>
							</thead>
							<tbody>
							<c:set var="num" value="${vo.listScale * (vo.pageIndex-1)}"/>
							<c:forEach items="${orgConnIpList}" var="item" varStatus="status">
								<tr>
									<td class="text-right">${num + status.count}</td>
									<td class="text-center">${item.connIp}</td>
									<td class="text-center">${item.divCd}</td>
									<td class="text-center"><meditag:codename code="${item.useYn}" category="USE_YN" /></td>
									<td class="text-center">
										<a href="javascript:delIp('${item.connIp}');" class="btn btn-warning btn-xs"><spring:message code="button.delete" /> </a>
									</td>
								</tr>
							</c:forEach>
							<c:if test="${empty orgConnIpList}">
								<tr>
									<td colspan="5"><spring:message code="common.message.nodata"/></td>
								</tr>
							</c:if>
							</tbody>
						</table>
						<meditag:paging pageInfo="${pageInfo}" funcName="listPageing"/>
