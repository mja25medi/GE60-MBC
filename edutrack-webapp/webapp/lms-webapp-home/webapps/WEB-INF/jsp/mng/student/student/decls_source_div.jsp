<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="studentList" value="${studentList}"/>

	<table summary="<spring:message code="student.title.student.list"/>" class="table table-bordered wordbreak">
		<colgroup>
			<col style="width:30px" />
			<col style="width:50px" />
			<col style="width:auto;" />
			<col style="width:auto;" />
		</colgroup>
		<thead>
			<tr>
				<th scope="col" ><input type="checkbox" name="chkAllSource" id="chkAllSource" value="N" style="border:0" title="select all" onclick="checkAllSource()"/></th>
				<th scope="col" ><spring:message code="common.title.no"/></th>
				<th scope="col" ><spring:message code="user.title.userinfo.name"/></th>
				<th scope="col" ><spring:message code="user.title.userinfo.userid"/></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${studentList}" varStatus="status">
			<tr>
				<td><input type='checkbox' name='selSource' id='selSource' value='${item.stdNo}' style='border:0' title='common.title.select'></td>
				<td>${status.count}</td>
				<td><a href="javascript:userInfo('${item.userNo}')" >${item.userNm}</a></td>
				<td>${item.userId}</td>
			</tr>
			</c:forEach>
			<c:if test="${empty studentList}">
			<tr>
				<td colspan="4"><spring:message code="student.message.student.nodata"/></td>
			</tr>
			</c:if>
		</tbody>
	</table>
