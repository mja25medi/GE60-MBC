<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/page_init.jsp" %>

		<table summary="<spring:message code="student.title.student.manage"/>" class="table table-bordered wordbreak">
			<colgroup>
				<col style="width:50px;"/>
				<col style="width:auto;"/>
				<col style="width:auto;"/>
				<col style="width:auto;"/>
				<col style="width:auto"/>
				<col style="width:auto"/>
				<col style="width:auto;"/>
				<col style="width:auto"/>
			</colgroup>
			<thead>
				<tr>
					<th scope="col"><spring:message code="null" text="no"/></th>
					<th scope="col"><spring:message code="null" text="장비명"/></th>
					<th scope="col"><spring:message code="null" text="신청상품수"/></th>
					<th scope="col"><spring:message code="null" text="신청자명"/></th>
					<th scope="col"><spring:message code="null" text="연락처"/></th>
					<th scope="col"><spring:message code="null" text="예약일"/></th>
					<th scope="col"><spring:message code="null" text="신청일"/></th>
					<th scope="col"><spring:message code="null" text="상태"/></th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="item" items="${rentInfoList}" varStatus="status">
				<tr>
					<td class="text-center">${pageInfo.totalRecordCount - status.index}</td>
					<td class="text-center">${item.equNm}</td>
					<td class="text-center">${item.rentCnt}대</td>
					<td class="text-center">${item.userNm}</td>
					<td class="text-center">${item.mobileNo} / ${item.email }</td>
					<td class="text-center"><a href="javaScript:viewRentInfoPop('${item.rentCd}')">
						<meditag:dateformat type="8" delimeter="." property="${item.rentStartDttm }" />
						~<meditag:dateformat type="8" delimeter="." property="${item.rentEndDttm }" />
					</a></td>
					<td class="text-center"><meditag:dateformat type="8" delimeter="." property="${item.regDttm }" /></td>
					<td class="text-center">${item.rentSts.codeNm}</td>
				</tr>
			</c:forEach>
			<c:if test="${empty rentInfoList}">
				<tr>
					<td colspan="10" align="center"><i class="fa fa-warning mr10"></i>검색된 예약 정보가 없습니다.</td>
				</tr>
			</c:if>
			</tbody>
		</table>
		<meditag:paging pageInfo="${pageInfo}" funcName="listRent"/>
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		