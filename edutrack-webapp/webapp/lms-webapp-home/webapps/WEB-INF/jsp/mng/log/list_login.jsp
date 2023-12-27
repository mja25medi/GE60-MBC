<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
						<table summary="data" class="table table-bordered wordbreak">
							<colgroup>
								<col style="width:100px"/>
								<col style="width:20%"/>
								<col style="width:20%"/>
								<col style="width:20%"/>
								<col style="width:auto"/>
							</colgroup>
							<thead>
								<tr>
									<th scope='col'><spring:message code="common.title.no"/></th>
									<th scope='col'><spring:message code="user.title.userinfo.name"/></th>
									<th scope='col'><spring:message code="user.title.userinfo.userid"/></th>
									<th scope='col'><spring:message code="log.title.mgr.conn.datetime"/></th>
									<th scope='col'><spring:message code="log.title.mgr.conn.ip"/></th>
								</tr>
							</thead>
							<tbody>
							<c:set var="num" value="${vo.listScale * (vo.pageIndex-1)}"/>
							<c:forEach items="${orgMgrConnList}" var="item" varStatus="status">
								<tr>
									<td class="text-right">${pageInfo.totalRecordCount - (num + status.index)}</td>
									<td class="text-center">${item.userNm}</td>
									<td class="text-center">${item.userId}</td>
									<td class="text-center"><meditag:dateformat type="0" delimeter="." property="${item.loginDttm}"/></td>
									<td class="text-center">${item.loginIp}</td>
								</tr>
							</c:forEach>
							<c:if test="${empty orgMgrConnList}">
								<tr>
									<td colspan="5"><spring:message code="common.message.nodata"/></td>
								</tr>
							</c:if>
							</tbody>
						</table>
						<meditag:paging pageInfo="${pageInfo}" funcName="listPageing"/>
