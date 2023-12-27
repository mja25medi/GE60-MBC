<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="studentList" value="${studentList}"/>
<c:set var="pageInfo" value="${pageInfo}"/> 
<mhtml:html>
<mhtml:head titleName="수강생 관리">
</mhtml:head>

<mhtml:frm_body>
	<table summary="수강생 목룍 표" style="width:100%" class="table_list">
		<colgroup>
			<col style="width:30px" />
			<col style="width:50px" />
			<col style="width:70px" />
			<col style="width:70px" />
			<col style="width:100px" />
			<col style="width:100px" />
			<col style="width:100px" />
			<col style="width:100px" />
			<col style="width:auto" />
			<col style="width:100px" />
			<col style="width:100px" />
			<col style="width:50px" />
		</colgroup>
		<thead>
			<tr>
				<th scope="col" ><input type="checkbox" name="chkAll" id="chkAll" value="N" style="border:0" title="목록 전체 선택" onclick="checkAll()"/></th>
				<th scope="col" >번호</th>
				<th scope="col" >이 름</th>
				<th scope="col" >아이디</th>
				<th scope="col" >회원사</th>
				<th scope="col" >수강신청일</th>
				<th scope="col" >결제금액</th>
				<th scope="col" >결제상태</th>
				<th scope="col" >환불요청일</th>
				<th scope="col" >환불일</th>
				<th scope="col" >환불상태</th>
				<th scope="col" >관리</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${studentList}" varStatus="status">
			<tr>
				<td><input type='checkbox' name='sel' id='sel' value='${item.stdNo}' style='border:0' title='선택'></td>
				<td>${pageInfo.startPageNum - (status.count-1)}</td>
				<td><a href="javascript:userInfo('${item.userNo}')" title='회원정보 상세보기'>${item.userNm}</a></td>
				<td>${item.userId}</td>
				<td>${item.orgNm}</td>
				<td><meditag:dateformat type="1" delimeter="." property="${item.enrlAplcDttm}"/></td>
				<td>
					<c:if test="${item.paymMthdCd eq 'FREE'}"> - </c:if>
					<c:if test="${item.paymMthdCd ne 'FREE'}">
						<fmt:formatNumber value="${item.paymPrice}" type="number"/> 원
					</c:if>
				</td>
				<td>
					<c:if test="${item.paymMthdCd eq 'FREE'}"> - </c:if>
					<c:if test="${item.paymMthdCd ne 'FREE'}">
						<c:if test="${item.paymStsCd eq 'Y'}">결제완료</c:if>
						<c:if test="${item.paymStsCd ne 'Y'}">미결제</c:if>
					</c:if>
				</td>
				<td>
					<meditag:dateformat type="1" delimeter="." property="${item.repayReqDttm}"/>
				</td>
				<td>
					<meditag:dateformat type="1" delimeter="." property="${item.repayDttm}"/>
				</td>
				<c:choose>
					<c:when test="${item.repayStsCd eq 'REFUND003' || item.repayStsCd eq 'REFUND005'} ">
						<td><a href="javascript:resultRepay('${item.stdNo}')" title='환불 결과 상세보기'>${item.repayStsNm}</a></td>
					</c:when>
					<c:otherwise>
						<td>${item.repayStsNm}</td>
					</c:otherwise>
				</c:choose>
				<td><meditag:button func="javascript:editRepay('${item.stdNo}');" title="관리" value="관리"/></td>
			</tr>
			</c:forEach>
			<c:if test="${empty studentList}">
			<tr>
				<td colspan="13">* 검색된 환불관련 정보가 없습니다.</td>
			</tr>
			</c:if>
		</tbody>
	</table>
	<meditag:paging pageInfo="${pageInfo}" funcName="listStudent"/>
</mhtml:frm_body>
<script type="text/javascript">

</script>
</mhtml:html>