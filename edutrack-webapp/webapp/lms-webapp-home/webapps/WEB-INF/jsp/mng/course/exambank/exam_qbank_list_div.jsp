<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="vo" value="${vo}"/>
						<table summary="<spring:message code="course.open.title.category.manage"/>" class="table table-bordered wordbreak">
							<colgroup>
								<col style="width:auto"/>
								<col style="width:100px"/>
							</colgroup>
							<thead>
								<tr>
									<th scope="col"><spring:message code="course.open.title.category.name"/></th>
									<th scope="col"><spring:message code="course.open.title.category.useyn"/></th>
								</tr>
							</thead>
							<tbody id="sortable-ctgr">
								<c:forEach items="${vo}" var="item" varStatus="status">
								<tr id="${item.ctgrCd}">
									<td>
										${item.ctgrNm} <a href="javascript:subEditFormCategory('${item.ctgrCd}');" class=""><i class="fa fa-cog"></i> </a>
									</td>
									<td class="text-center"><meditag:codename code="${item.useYn}" category="USE_YN"/></td>
								</tr>
								</c:forEach>
								<c:if test="${empty vo}">
								<tr>
									<td colspan="3"><spring:message code="course.open.message.category.nodata"/></td>
								</tr>
								</c:if>								
							</tbody>
						</table>