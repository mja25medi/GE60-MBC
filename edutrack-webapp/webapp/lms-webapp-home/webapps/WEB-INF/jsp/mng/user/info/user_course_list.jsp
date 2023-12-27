<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<style>
	#courseTable th {background-color: #eee;}
</style>

		<table class="table table-bordered wordbreak" id="courseTable" style="margin-top: 10px;">
			<colgroup>
				<col style="width:auto;"/>
				<col style="width:auto;"/>
				<col style="width:auto;"/>
			</colgroup>
			<thead>
				<tr>
					<!-- <th>신청기수</th> -->
					<th>신청과정</th>
					<th>상태</th>
					<th>신청일시</th>
					<th>수강기간</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${courseList}" var="item" varStatus="status">
					<tr>
						<%-- <td class="text-center">${item.crsYear}년도 ${item.crsTerm}기</td> --%>
						<td class="text-center">${item.crsCreNm} ${item.creTerm }회차</td>
						<td class="text-center">${item.enrlStsNm }</td>
						<td class="text-center"><meditag:dateformat type="1" delimeter="." property="${item.enrlAplcDttm}"/></td>
						<td class="text-center"><meditag:dateformat type="1" delimeter="." property="${item.enrlStartDttm}"/>~<meditag:dateformat type="1" delimeter="." property="${item.enrlEndDttm}"/></td>
					</tr>
				</c:forEach>
				<c:if test="${empty courseList}">
					<tr>
						<td colspan="4">수강 신청 내역이 없습니다. </td>
					</tr>
				</c:if>
			</tbody>
		</table>
		
		<meditag:paging pageInfo="${pageInfo}" funcName="listCourse"/> 
	
		
