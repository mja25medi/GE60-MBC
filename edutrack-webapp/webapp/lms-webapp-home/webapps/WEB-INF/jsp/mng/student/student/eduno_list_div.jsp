<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="studentList" value="${studentList}"/>
<c:set var="pageInfo" value="${pageInfo}"/> 
	
		<table summary="<spring:message code="student.title.student.manage"/>" style="width:100%" class="table_list">
			<colgroup>
				<col style="width:30px" />
				<col style="width:50px" />
				<col style="width:50px" />
				<col style="width:50px" />
				<col style="width:auto" />
				<col style="width:auto" />
				<col style="width:auto" />
				<col style="width:auto" />
				<col style="width:50px" />
				<col style="width:50px" />
			</colgroup>
			<thead>
				<tr>
					<th scope="col" ></th>
					<th scope="col" ><spring:message code="common.title.no"/></th>
					<th scope="col" ><spring:message code="student.title.student.eduno"/></th>
					<th scope="col" ><spring:message code="student.title.student.decls"/></th>
					<th scope="col" ><spring:message code="user.title.userinfo.name"/></th>
					<th scope="col" ><spring:message code="user.title.userinfo.userid"/></th>
					<th scope="col" ><spring:message code="user.title.userinfo.mobileno"/></th>
					<th scope="col" ><spring:message code="user.title.userinfo.email"/></th>
					<th scope="col" ><spring:message code="common.title.stats"/></th>
					<th scope="col" ><spring:message code="common.title.manage"/></th>
				</tr>
			</thead>
		<tbody>
			<c:forEach var="item" items="${studentList}" varStatus="status">
			<tr>
				<td><input type='checkbox' name='sel' id='sel' value='${item.stdNo}' style='border:0'></td>
				<td>${pageInfo.startPageNum - (status.count-1)}</td>
				<td><input type='text' name='eduNo' style='width:30px' value='${item.eduNo}'></td>
				<td>${item.declsNo}</td>
				<td><a href="javascript:userInfo('${item.userNo}')">${item.userNm}</a></td>
				<td>${item.userId}</td>
				<td>${item.mobileNo}</td>
				<td>${item.email}</td>
				<td>${item.enrlStsNm}</td>
				<td><meditag:button func="javascript:viewStudent('${item.stdNo}');" title="button.manage" value="button.manage"/></td>
			</tr>
			</c:forEach>
			<c:if test="${empty studentList}">
			<tr>
				<td colspan="10"><spring:message code="student.message.student.nodata"/> 11</td>
			</tr>
			</c:if>
		</tbody>
	</table>
	<meditag:paging pageInfo="${pageInfo}" funcName="listStudent"/>
