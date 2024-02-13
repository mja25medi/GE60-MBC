<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<div class="table-responsive">
			<table summary="<spring:message code="board.title.bbs.atcl.list"/>" class="table table-bordered wordbreak">
			<colgroup>
						<col style="width:25px;"/>
						<col style="width:50px;"/>
						<col style="width:70px"/>
						<col style="width:90px"/>
						<col style="width:200px;"/>
						<col style="width:100px"/>
						<col style="width:100px"/>
						<col style="width:150px"/>
						<col style="width:200px"/>
						<col style="width:200px"/>

					</colgroup>
					<thead>
					<tr>
						<th scope="col"><input type="checkbox" name="chkAll" id="chkAll" value="N" style="border:0" onclick="checkAll()"/></th>
						<th scope="col"><spring:message code="common.title.no"/></th>
						<th scope="col">이름</th>
						<th scope="col">아이디</th>
						<th scope="col">수강신청일</th>
						<th scope="col">수강상태</th>
						<th scope="col">결제방법</th>
						<th scope="col">결제금액</th>
						<th scope="col">IDE</th>
						<th scope="col">관리</th>
					</tr>
					</thead>
			<tbody id="tbodyList">
					<c:forEach items="${stdPayList}" var="item" varStatus="status">
					<tr>
						<td class="text-center"><input type="checkbox" name="sel" value="${item.stdNo }" /> </td>
						<td>${pageInfo.totalRecordCount - ((pageInfo.currentPageNo - 1 ) * pageInfo.recordCountPerPage + status.index)}</td>
						<td>${item.userNm}</td>
						<td>${item.userId }</td>
						<td class="text-center"><meditag:dateformat type="0" delimeter="." property="${item.enrlAplcDttm}" /></td>	
						<td class="text-center">
							<c:choose>
								<c:when test="${item.enrlSts eq 'E'}">수강대기</c:when>
								<c:when test="${item.enrlSts eq 'N'}">수강취소</c:when>
								<c:otherwise>수강승인</c:otherwise>
							</c:choose>
						</td>
						<td class="text-center">
							<c:if test="${not empty item.paymMthdNm}">${item.paymMthdNm}</c:if>
							<c:if test="${empty item.paymMthdNm}">무료수강</c:if>
						</td>
						<c:if test="${empty item.paymMthdNm}"><td class="text-center">-</td></c:if>
						<c:if test="${not empty item.paymMthdNm}"><td class="text-right"><fmt:formatNumber value="${item.paymPrice}" pattern="#,###" />원</td></c:if>	
						<td class="text-center">${item.ideUrl }</td>					
						<td class="text-center">
							<c:if test="${item.paymPrice eq '0' || empty item.paymPrice}">
								<a href="javascript:editConfirmStudent('${item.stdNo }')" class="btn btn-info btn-sm">승인</a>
								<a href="javascript:editCancelStudent('${item.stdNo }')" class="btn btn-warning btn-sm">취소</a>
							</c:if>
							<a href="javascript:deleteStudent('${item.stdNo }')" class="btn btn-default btn-sm">삭제</a>
						</td>
					</tr>
					</c:forEach>
					<c:if test="${empty stdPayList}">
						<tr>
							<td colspan="8"><spring:message code="common.message.nodata"/></td>
						</tr>
					</c:if>
					</tbody>
		</table>
		</div>
		<meditag:paging pageInfo="${pageInfo}" funcName="listStuPayInfo"/>