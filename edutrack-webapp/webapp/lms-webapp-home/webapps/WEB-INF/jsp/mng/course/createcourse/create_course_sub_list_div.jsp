<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="createCourseVO" value="${createCourseVO}"/>


	<table summary="<spring:message code="course.title.createcourse.manage"/>"  class="table table-bordered wordbreak">
		<colgroup>
			<col style="width:auto"/>
			<col style="width:60px"/>
			<col style="width:60px"/>
			<col style="width:200px"/>
		</colgroup>
		<thead>
			<tr>
				<th scope="col"><spring:message code="course.title.course.name"/></th>
				<th scope="col"><spring:message code="common.title.year"/></th>
				<th scope="col"><spring:message code="course.title.createcourse.creterm"/> </th>
				<th scope="col"><spring:message code="course.title.createcourse.duration.edu"/></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${createCourseList}" var="item" varStatus="status">
			<tr>
				<td>${item.crsCreNm}</td>
				<td class="text-center">${item.creYear}</td>
				<td class="text-center">${item.creTerm}</td>
				<td>
					<c:if test="${item.crsOperType eq 'R'}">
						<c:if test="${item.enrlStartDttm ne ''}"><meditag:dateformat type="1" delimeter="." property="${item.enrlStartDttm}"/>~<meditag:dateformat type="1" delimeter="." property="${item.enrlEndDttm}"/></c:if>
						<c:if test="${item.enrlStartDttm eq ''}">${item.creYear}</c:if>
					</c:if>
					<c:if test="${item.crsOperType eq 'S'}">
						${item.enrlDaycnt }<spring:message code="common.title.day"/>
					</c:if>
				</td>
			</tr>
			</c:forEach>
			<c:if test="${empty createCourseList}">
			<tr>
				<td colspan="4"><spring:message code="course.message.createcourse.nodata"/></td>
			</tr>
			</c:if>
		</tbody>
	</table>