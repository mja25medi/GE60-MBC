<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<style>
	#connTable th {background-color: #eee;}
</style>

		<table class="table table-bordered wordbreak" id="connTable" style="margin-top: 10px;">
			<colgroup>
				<col style="width:auto;"/>
				<col style="width:auto;"/>
				<col style="width:auto;"/>
				<col style="width:auto;"/>
				<col style="width:auto;"/>
			</colgroup>
			<thead>
				<tr>
					<th>번호</th>
					<th>아이디</th>
					<th>모드</th>
					<th>IP주소</th>
					<th>접속시간</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${userConnList}" var="item" varStatus="status">
					<tr>
						<td class="text-center">${pageInfo.totalRecordCount - ((pageInfo.currentPageNo- 1) * pageInfo.recordCountPerPage) - status.index }</td>
						<td class="text-center">${item.userId}</td>
						<c:choose>
							<c:when test="${empty item.logoutDttm }">
								<td class="text-center">로그인</td>
							</c:when>
							<c:otherwise>
								<td class="text-center">로그아웃</td>
							</c:otherwise>
						</c:choose>
						<td class="text-center">${item.connIp}</td>
						<td class="text-center">
							<c:choose>
								<c:when test="${empty item.logoutDttm }">
									<meditag:dateformat type="8" delimeter="." property="${item.loginDttm}"/>
								</c:when>
								<c:otherwise>
									<meditag:dateformat type="8" delimeter="." property="${item.logoutDttm}"/>
								</c:otherwise>
							</c:choose>
						</td>
													
					</tr>
				</c:forEach>
				<c:if test="${empty userConnList}">
					<tr>
						<td colspan="5">접속이력이 없습니다. </td>
					</tr>
				</c:if>
			</tbody>
		</table>
			
		<meditag:paging pageInfo="${pageInfo}" funcName="listConn"/>
	
