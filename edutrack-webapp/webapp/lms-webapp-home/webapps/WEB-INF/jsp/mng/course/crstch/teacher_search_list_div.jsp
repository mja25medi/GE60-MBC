<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
			<table summary="<spring:message code="course.title.teacher.manage"/>" style="width:100%;margin-bottom:0px;" class="table table-bordered wordbreak">
				<colgroup>
					<col style="width:auto"/>
					<col style="width:auto"/>
					<col style="width:auto"/>
					<col style="width:72px"/>
				</colgroup>
				<thead>
					<tr>
						<th scope="col"><spring:message code="user.title.userinfo.userid"/></th>
						<th scope="col"><spring:message code="user.title.userinfo.name"/></th>
						<th scope="col"><spring:message code="user.title.userinfo.email"/></th>
						<th scope="col"><spring:message code="common.title.select"/></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${userInfoList}" var="item" varStatus="status">
					<tr>
						<td>${item.userId}</td>
						<td>${item.userNm}</td>
						<td>${item.email}</td>
						<td class="text-center">
							<button class="btn btn-info btn-sm" onclick="javascript:addTeacher('${item.userNo}');" ><spring:message code="button.choice"/></button>
						</td>
					</tr>
					</c:forEach>
					<c:if test="${empty userInfoList}">
					<tr>
						<td colspan="4"><spring:message code="course.messgae.teacher.search.nodata"/></td>
					</tr>
					</c:if>
				</tbody>
			</table>
