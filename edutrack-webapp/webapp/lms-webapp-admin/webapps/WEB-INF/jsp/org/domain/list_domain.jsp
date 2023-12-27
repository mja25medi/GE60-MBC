<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>


		<form name="List">
		<table summary="<spring:message code="org.title.orgmenu.manage"/>" style="width:100%"  class="table table-bordered">
			<colgroup>
				<col style="width:120px"/>
				<col style="width:auto"/>
				<col style="width:auto"/>
				<col style="width:auto"/>
				<col style="width:auto"/>
				<col style="width:60px"/>
			</colgroup>
			<thead>
				<tr>
					<th scope="col"><spring:message code="org.title.domain.representative"/></th>
					<th scope="col"><spring:message code="org.title.orginfo.domain"/></th>
					<th scope="col"><spring:message code="common.title.type"/></th>
					<th scope="col">도메인 타입</th>
					<th scope="col">서비스 타입</th>
					<th scope="col"><spring:message code="common.title.delete"/></th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="item" items="${domainList}" varStatus="status">
				<tr>
					<td class="text-center">
						<input type="radio" name="rprstYn" onclick="javascript:changeRprst('${item.orgDomain}')" <c:if test="${item.rprstYn eq 'Y' }">checked</c:if>/>
					</td>
					<td class="left">http://${item.orgDomain}</td>
					<td>
						<c:if test="${item.dfltYn eq 'Y'}">
							<spring:message code="org.title.domain.dfltYn_Y"/>
						</c:if>
						<c:if test="${item.dfltYn eq 'N'}">
							<spring:message code="org.title.domain.dfltYn_N"/>
						</c:if>
					</td>
					<td>${item.domainTypeCd}</td>
					<td>${item.siteServiceTypeCd}</td>
					<td class="text-center">
						<c:if test="${item.dfltYn ne 'Y'}">
						<a class="btn btn-warning btn-sm" href="javascript:deleteDomain('${item.orgDomain}')" ><spring:message code="button.delete"/></a>
						</c:if>
					</td>
				</tr>
			</c:forEach>
			<c:if test="${empty domainList}">
				<tr>
					<td colspan="4"><spring:message code="org.message.domain.nodata"/> 111</td>
				</tr>
			</c:if>
			</tbody>
		</table>
		</form>
		<br/>
