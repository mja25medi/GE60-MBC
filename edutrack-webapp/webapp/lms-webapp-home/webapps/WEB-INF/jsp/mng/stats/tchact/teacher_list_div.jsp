<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="itemList" value="${tchActStatusList}"/>
<c:set var="pageInfo" value="${pageInfo}"/>
		<table style="width:100%" class="table table-bordered wordbreak">
			<colgroup>
				<col style="width:30px"/>
				<col style="width:50px"/>
				<col style="width:auto"/>
				<col style="width:auto"/>
				<col style="width:auto"/>
				<col style="width:auto"/>
				<col style="width:80px"/>
				<col style="width:80px"/>
				<col style="width:80px"/>
			</colgroup>
			<thead>
				<tr>
					<th scope="col"><input type="checkbox" name="chkAll" id="chkAll" value="N" style="border:0" title="check all" onclick="checkAll()"/></th>
					<th><spring:message code="common.title.no"/></th>
					<th>
					<c:choose>
						<c:when test="${fn:startsWith(vo.sortKey,'USER_NM') == true}">
							<c:if test="${vo.sortKey eq 'USER_NM_ASC'}">
						<a href="javascript:setSortKey('USER_NM_DESC')"><spring:message code="user.title.userinfo.name"/> <i class="fa fa-sort-asc"></i></a>
							</c:if>
							<c:if test="${vo.sortKey eq 'USER_NM_DESC'}">
						<a href="javascript:setSortKey('USER_NM_ASC')"><spring:message code="user.title.userinfo.name"/> <i class="fa fa-sort-desc"></i></a>
							</c:if>
						</c:when>
						<c:otherwise>
						<a href="javascript:setSortKey('USER_NM_ASC')"><spring:message code="user.title.userinfo.name"/> <i class="fa fa-sort" style="color:#ddd"></i></a>
						</c:otherwise>
					</c:choose>
					</th>
					<th>
					<c:choose>
						<c:when test="${fn:startsWith(vo.sortKey,'USER_ID') == true}">
							<c:if test="${vo.sortKey eq 'USER_ID_ASC'}">
						<a href="javascript:setSortKey('USER_ID_DESC')"><spring:message code="user.title.userinfo.userid"/> <i class="fa fa-sort-asc"></i></a>
							</c:if>
							<c:if test="${vo.sortKey eq 'USER_ID_DESC'}">
						<a href="javascript:setSortKey('USER_ID_ASC')"><spring:message code="user.title.userinfo.userid"/> <i class="fa fa-sort-desc"></i></a>
							</c:if>
						</c:when>
						<c:otherwise>
						<a href="javascript:setSortKey('USER_ID_ASC')"><spring:message code="user.title.userinfo.userid"/> <i class="fa fa-sort" style="color:#ddd"></i></a>
						</c:otherwise>
					</c:choose>
					</th>
					<th><spring:message code="user.title.userinfo.email"/></th>
					<th><spring:message code="user.title.userinfo.mobileno"/></th>
					<th><spring:message code="student.title.course.his"/></th>
					<th><spring:message code="student.title.course.ing"/></th>
					<th><spring:message code="student.title.course.bef"/></th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${itemList}" var="item" varStatus="status">
				<tr>
					<td class="text-center"><input type='checkbox' name='sel' id='sel' value='${item.userNo}' style='border:0' title='<spring:message code="common.title.select"/>'></td>
					<td class="text-right">${pageInfo.totalRecordCount -(pageInfo.recordCountPerPage * (pageInfo.currentPageNo -1)) - status.count + 1}</td>
					<td><a href="#_none" onclick="tchInfo('${item.userNo}')">${item.userNm}</a></td>
					<td>${item.userId}</td>
					<td>${item.email}</td>
					<td>${item.mobileNo}</td>
					<td class="text-right"><a href="<c:url value="/mng/stats/tchAct/courseMain"/>?searchDuration=end&amp;userNo=${item.userNo}">${item.endCrsCnt}</a></td>
					<td class="text-right"><a href="<c:url value="/mng/stats/tchAct/courseMain"/>?searchDuration=ing&amp;userNo=${item.userNo}">${item.ingCrsCnt}</a></td>
					<td class="text-right"><a href="<c:url value="/mng/stats/tchAct/courseMain"/>?searchDuration=bef&amp;userNo=${item.userNo}">${item.befCrsCnt}</a></td>
				</tr>
			</c:forEach>
			<c:if test="${empty itemList}">
				<tr>
					<td colspan="8"><spring:message code="common.message.nodata"/> </td>
				</tr>
			</c:if>
			</tbody>
		</table>
		<meditag:paging pageInfo="${pageInfo}" funcName="listPageing"/>
