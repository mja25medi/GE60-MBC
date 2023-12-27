<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>

		<table summary="<spring:message code="student.title.student.manage"/>" class="table table-bordered wordbreak">
			<colgroup>
				<col style="width:50px;"/>
				<col style="width:auto;"/>
				<col style="width:auto;"/>
				<col style="width:auto;"/>
				<col style="width:auto"/>
				<col style="width:66px;"/>
			</colgroup>
			<thead>
				<tr>
					<th scope="col"><spring:message code="common.title.no"/></th>
					<th scope="col"><spring:message code="user.title.userinfo.name"/></th>
					<th scope="col"><spring:message code="user.title.userinfo.userid"/></th>
					<th scope="col"><spring:message code="user.title.userinfo.mobileno"/></th>
					<th scope="col"><spring:message code="user.title.userinfo.email"/></th>
					<th scope="col"><spring:message code="common.title.choice"/></th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="item" items="${userList}" varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${item.userNm}</td>
					<td>${item.userId}</td>
					<td>${item.mobileNo}</td>
					<td>${item.email}</td>
					<td>
						<a href="javascript:addStudent('${item.userNo}','${item.userNm}')" class="btn btn-primary btn-sm"><spring:message code="button.choice"/></a>
					</td>
				</tr>
			</c:forEach>
			<c:if test="${empty userList}">
				<tr>
					<td colspan="6" align="center"><spring:message code="user.message.userinfo.search.nodata"/></td>
				</tr>
			</c:if>
			</tbody>
		</table>
