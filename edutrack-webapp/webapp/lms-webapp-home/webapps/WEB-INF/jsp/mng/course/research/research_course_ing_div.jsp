<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
	<table summary="<spring:message code="student.title.course.ing"/>" class="table table-bordered wordbreak">
		<colgroup>
			<col style="width:auto;"/>
			<col style="width:12%;"/>
			<col style="width:35%;"/>
		</colgroup>
		<thead>
			<tr>
				<th><spring:message code="course.title.course.name"/></th>
				<th><spring:message code="course.title.createcourse.creyear"/>/<spring:message code="course.title.createcourse.creterm"/></th>
				<th><spring:message code="course.title.createcourse.duration.edu"/></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${courseListIng}">
			<tr>
				<td class="left" style="margin-left:5px;">${item.crsCreNm}</td>
				<td>${item.creYear}<spring:message code="common.title.year"/>/${item.creTerm}</td>
				<td>
					<c:if test="${item.crsOperMthd eq 'ON' || item.crsOperMthd eq 'OF'}">
						<meditag:dateformat type="1" delimeter="." property="${item.startDttm}"/> ~ <meditag:dateformat type="1" delimeter="." property="${item.endDttm}"/>
					</c:if>
					<c:if test="${item.crsOperMthd eq 'BL'}">
						<spring:message code="course.title.createcourse.cyber"/> : <meditag:dateformat type="1" delimeter="." property="${item.startDttm}"/> ~ <meditag:dateformat type="1" delimeter="." property="${item.endDttm}"/> <br/>
						<spring:message code="course.title.createcourse.offline"/> : <meditag:dateformat type="1" delimeter="." property="${item.oflnStartDttm}"/> ~ <meditag:dateformat type="1" delimeter="." property="${item.oflnEndDttm}"/>
					</c:if>
				</td>
			</tr>
			</c:forEach>
			<c:if test="${empty courseListIng}">
			<tr>
				<td colspan="3"><spring:message code="student.message.course.ing.nodata"/></td>
			</tr>
			</c:if>
		</tbody>
	</table>