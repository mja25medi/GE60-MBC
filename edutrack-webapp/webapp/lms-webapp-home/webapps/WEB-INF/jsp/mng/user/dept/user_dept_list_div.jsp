<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="userDeptInfoList" value="${userDeptInfoList}"/>
<c:set var="pageInfo" value="${pageInfo}"/>

				<table summary="<spring:message code="user.title.userinfo.manage"/>" class="table table-bordered wordbreak">
					<colgroup>
						<col style="width:60px"/>
						<col style="width:150px"/>
						<col style="width:150px"/>
						<col style="width:130px"/>
						<col style="width:130px"/>
					</colgroup>
					<thead>
						<tr>
							<th scope="col">순서</th>
							<th scope="col">법인명</th>
							<th scope="col">코드명</th>
							<th scope="col">등록일</th>
							<th scope="col">사용여부</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${userDeptInfoList}" var="item" varStatus="status">
						<tr>
							<td class="text-center">${pageInfo.totalRecordCount -(pageInfo.recordCountPerPage * (pageInfo.currentPageNo -1)) - status.count + 1}</td>
							<td>${item.deptNm} <a href="javascript:userDeptEditForm('${item.deptCd}');" ><i class="fa fa-cog"></i></a></td>
							<td><a href="javascript:userDeptEditForm('${item.deptCd}');">${item.deptCd}</a></td>
							<td class="text-center"><meditag:dateformat type="1" delimeter="." property="${item.regDttm}"/></td>
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