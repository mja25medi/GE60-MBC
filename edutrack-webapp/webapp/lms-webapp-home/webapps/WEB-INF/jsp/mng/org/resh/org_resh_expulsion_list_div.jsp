<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="vo" value="${vo}" />
<c:set var="listExpulsion" value="${listExpulsion}" />

			<table summary="<spring:message code="course.title.reshbank.item.manage"/>" class="table table-bordered wordbreak">
				<colgroup>
					<col style="width:50px"/>
					<col style="width:300px"/>
					<col style="width:300px"/>
					<col style="width:300px"/>
					<col style="width:300px"/>
				</colgroup>
				<thead>
					<tr>
						<th scope="col">번호</th>
						<th scope="col">성명</th>
						<th scope="col">아이디</th>
						<th scope="col">설문작성일시</th>
						<th scope="col">설문결과</th>
					</tr>
				</thead>
				<tbody id="sortable-item">
					<c:forEach var="item" items="${listExpulsion}" varStatus="status">
					<tr>
						<td class="text-center">${pageInfo.totalRecordCount - ((pageInfo.currentPageNo- 1) * pageInfo.recordCountPerPage) - status.index }</td>
						<td>${item.userNm }</td>
						<td>${item.userId }</td>
						<td class="text-center"><meditag:dateformat type="1" delimeter="." property="${item.regDttm}"/></td>
						<td class="text-center">
							<a href="javascript:reshResult('${item.userNo}')" class="btn btn-info btn-sm"><spring:message code="button.result"/></a>
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
			<meditag:paging pageInfo="${pageInfo}" funcName="listExpulsionStd"/>
