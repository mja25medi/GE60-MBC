<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
						<table summary="data" class="table table-bordered wordbreak">
							<colgroup>
								<col style="width:100px"/>
								<col style="width:auto"/>
								<col style="width:150px"/>
								<col style="width:100px"/>
							</colgroup>
							<thead>
								<tr>
									<th scope='col'><spring:message code="common.title.no"/></th>
									<th scope='col'><spring:message code="org.title.emailtpl.name"/></th>
									<th scope='col'><spring:message code="org.title.emailtpl.code"/></th>
									<th scope='col'><spring:message code="common.title.useyn"/></th>
								</tr>
							</thead>
							<tbody>
							<c:forEach items="${emailTplList}" var="item" varStatus="status">
								<tr>
									<td class="text-right">${status.count}</td>
									<td>${item.tplNm} <a href="javascript:emailTplEdit('${item.orgCd}','${item.tplCd}');"><i class="fa fa-cog"></i></a></td>
									<td class="text-center">${item.tplCd}</td>
									<td class="text-center"><meditag:codename code="${item.useYn}" category="USE_YN" /></td>
								</tr>
							</c:forEach>
							<c:if test="${empty emailTplList}">
								<tr>
									<td colspan="4"><spring:message code="common.message.nodata"/></td>
								</tr>
							</c:if>
							</tbody>
						</table>