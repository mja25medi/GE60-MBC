<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>


	<table summary="유저 상담 내역" class="table table-bordered wordbreak custom-table" style="margin-top: 10px;">
		<colgroup>
			<col style="width:auto"/>
			<col style="width:auto"/>
			<col style="width:auto"/>
			<col style="width:auto"/>
			<col style="width:auto"/>
			<col style="width:auto"/>
		</colgroup>
		<thead>
			<tr>
				<th scope="col">과정명</th>
				<th scope="col">회차명</th>	
				<th scope="col">학습일시</th>	
				<th scope="col">접속기기</th>	
				<th scope="col">브라우저</th>	
				<th scope="col">접속IP</th>	
			</tr>
		</thead>
		<tbody>
			<c:if test="${not empty unitLogList }">
				<c:forEach var="item" items="${unitLogList }" varStatus="status">
					<tr class="text-center">
						<td>${item.crsCreNm }</td>
						<td>${item.unitNm }</td>
						<td>
							<fmt:parseDate var="logDate" value="${item.loginDttm}"  pattern="yyyyMMddHHmmss"/>
							<fmt:formatDate value="${logDate}" pattern="yyyy년 MM월 dd일 HH시 mm분 ss초"/>
						</td>
						<td>${item.deviceType }</td>
						<td>${item.browserType }</td>
						<td>${item.connIp }</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty unitLogList }">
				<tr>
					<td colspan="6">내역이 존재하지 않습니다.</td>
				</tr>
			</c:if>
		</tbody>
	</table>