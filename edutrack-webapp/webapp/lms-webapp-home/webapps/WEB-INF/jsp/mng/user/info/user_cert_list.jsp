<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<style>
	#certListTable th {background-color: #eee;}
</style>
		<table class="table table-bordered wordbreak" id="certListTable" style="margin-top: 10px;">
			<colgroup>
				<col style="width:5%"/>
				<col style="width:10%;"/>
				<col style="width:10%;"/>
				<col style="width:auto;"/>
				<col style="width:auto;"/>
				<col style="width:10%;"/>
				<col style="width:auto;"/>
				<col style="width:auto;"/>
			</colgroup>
			<thead>
				<tr>
					<th>번호</th>
					<th>아이디</th>
					<th>인증방법</th>
					<th>과정</th>
					<th>회차</th>
					<th>유형</th>
					<th>IP주소</th>
					<th>인증일시</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${userCertList}" var="item" varStatus="status">
					<tr>
						<td class="text-center">${pageInfo.totalRecordCount - ((pageInfo.currentPageNo- 1) * pageInfo.recordCountPerPage) - status.index }</td>
						<td class="text-center">${item.userId}</td>
						<td class="text-center">${item.certMthdNm}</td>
						<td class="text-center">${item.crsCreNm}</td>
						<td class="text-center">${item.creTerm}회차</td>
						<td class="text-center">${item.certEvalNm}</td>
						<td class="text-center">${item.connIp}</td>
						<td class="text-center"><meditag:dateformat type="8" delimeter="." property="${item.certDttm}"/></td>
					</tr>
				</c:forEach>
				<c:if test="${empty userCertList}">
					<tr>
						<td colspan="4">인증내역이 없습니다. </td>
					</tr>
				</c:if>
			</tbody>
		</table>
		
		<meditag:paging pageInfo="${pageInfo}" funcName="listCert"/>
	
	
