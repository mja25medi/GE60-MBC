<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="openCrsList" value="${openCrsList}"/>
						<table summary='<spring:message code="course.title.course.manage"/>' class="table table-bordered wordbreak">
							<colgroup>
								<col style="width:auto"/>
								<col style="width:auto"/>
								<col style="width:80px"/>
							</colgroup>
							<thead>
								<tr>
									<th scope="col"><spring:message code="course.open.title.category.name"/></th>
									<th scope="col"><spring:message code="course.title.course.name"/></th>
									<th scope="col"><spring:message code="course.open.title.subject.count"/></th>

								</tr>
							</thead>
							<tbody>
								<c:forEach var="item" items="${openCrsList}" varStatus="status">
								<tr>
									<td class="text-left wordbreak">${item.ctgrNm}</td>
									<td class="text-left wordbreak">${item.crsNm}</td>
									<td class="text-center">${item.sbjCnt}</td>
								</tr>
								</c:forEach>
								<c:if test="${empty openCrsList}">
								<tr>
									<td colspan="6"><spring:message code="common.message.nodata"/></td>
								</tr>
								</c:if>
							</tbody>
						</table>
