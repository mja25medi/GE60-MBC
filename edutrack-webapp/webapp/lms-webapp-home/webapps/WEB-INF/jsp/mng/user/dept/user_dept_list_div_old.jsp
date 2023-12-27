<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="userDeptInfoList" value="${userDeptInfoList}"/>
<c:set var="pageInfo" value="${pageInfo}"/>

				<table summary="<spring:message code="user.title.userinfo.manage"/>" class="table table-bordered wordbreak">
					<colgroup>
						<col style="width:60px"/>
						<col style="width:110px"/>
						<col style="width:130px"/>
						<col style="width:auto"/>
						<col style="width:100px"/>
						<col style="width:130px"/>
						<col style="width:130px"/>
						<col style="width:130px"/>
					</colgroup>
					<thead>
						<tr>
							<th scope="col"><spring:message code="common.title.no"/> </th>
							<th scope="col"><spring:message code="user.title.user.dept.code"/></th>
							<th scope="col"><spring:message code="user.title.user.dept.name"/></th>
							<th scope="col"><spring:message code="user.title.address"/></th>
							<th scope="col"><spring:message code="user.title.area"/></th>
							<th scope="col"><spring:message code="user.title.userinfo.phoneno"/></th>
							<th scope="col"><spring:message code="user.title.userinfo.fax"/></th>
							<th scope="col"><spring:message code="course.open.title.category.useyn"/></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${userDeptInfoList}" var="item" varStatus="status">
						<tr>
							<td class="text-center">${pageInfo.totalRecordCount -(pageInfo.recordCountPerPage * (pageInfo.currentPageNo -1)) - status.count + 1}</td>
							<%-- <td><meditag:codename code="${item.typeCd}" category="DEPT_TYPE_CD"/></td> --%>
							<td>${item.deptCd}</td>
							<td>${item.deptNm} <a href="javascript:userDeptEdit('${item.deptCd}');" ><i class="fa fa-cog"></i></a></td>
							<td>${item.deptAddr}</td>
							<td><meditag:orgcodename code="${item.areaCd}" category="AREA_CD" orgCd="${USER_ORGCD }"/></td>
							<td>${item.phoneno}</td>
							<td>${item.faxNo}</td>
							<td class="text-center"><meditag:codename code="${item.useYn}" category="USE_YN"/></td>
						</tr>
						</c:forEach>
						<c:if test="${empty userDeptInfoList}">
						<tr>
							<td colspan="9"><spring:message code="common.message.nodata"/></td>
						</tr>
						</c:if>
					</tbody>
				</table>
				<meditag:paging pageInfo="${pageInfo}" funcName="listPageing"/>