<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="courseList" value="${courseList}"/>
<c:set var="courseVO" value="${vo}"/>

						<table summary='<spring:message code="course.title.course.manage"/>' class="table table-bordered wordbreak">
							<colgroup>
								<col style="width:auto"/>
								<col style="width:auto"/>
								<col style="width:auto;min-width:90px;"/>
								<col style="width:auto;min-width:110px;"/>
							</colgroup>
							<thead>
								<tr>
									<th scope="col"><spring:message code="course.title.course.category"/>	</th>
									<th scope="col"><spring:message code="course.title.course.name"/></th>
									<th scope="col"><spring:message code="course.title.course.edumthd"/></th>
									<th scope="col"><spring:message code="course.title.course.crstype"/></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${courseList}" var="item" varStatus="status">
								<tr>
									<td class="wordbreak">${item.crsCtgrNm}</td>
									<td class="wordbreak">${item.crsNm}</td>
									<td class="text-center"><meditag:codename code="${item.crsOperMthd}" category="CRS_OPER_MTHD" /></td>
									<td class="text-center"><meditag:codename code="${item.crsOperType}" category="CRS_OPER_TYPE" /></td>
								</tr>
								</c:forEach>
								<c:if test="${empty courseList}">
								<tr>
									<td colspan="8"><spring:message code="course.message.course.nodata"/></td>
								</tr>
								</c:if>
							</tbody>
						</table>
