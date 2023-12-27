<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
	<!-- <hr> -->
	<div class="row">
		<div class="col-lg-6" style="margin-top:5px;">
			<h5><i class="fa fa-check" aria-hidden="true"></i>신청현황</h5>
			<table summary="<spring:message code="board.title.bbs.atcl.list"/>" style="margin-top:5px;" class="table table-bordered" id="statusTable">
				<tr>
					<th>결제대기</th><!-- 수강생의 상태가 수강대기  -->
					<th>결제완료</th><!-- 수강생의 상태가 수강중,수료,수료취소  -->
					<th>결제취소</th><!-- 수강생의 상태가 수강취소  -->
					<th>총</th>
				</tr>
				<tr>
					<td class="text-center">${stdPayStatusVO.stuECnt }</td>
					<td class="text-center">${stdPayStatusVO.stuSCnt + stdPayStatusVO.stuCCnt + stdPayStatusVO.stuFCnt }</td>
					<td class="text-center">${stdPayStatusVO.stuNCnt }</td>
					<td class="text-center">${stdPayStatusVO.stuCnt }</td>
				</tr>
			</table>
		</div>
		
		<div class="col-lg-6" style="margin-top:5px;">
			<h5><i class="fa fa-check" aria-hidden="true"></i>결제현황</h5>
			<table summary="<spring:message code="board.title.bbs.atcl.list"/>" style="margin-top:5px;" class="table table-bordered" id="statusTable">
				<tr>
					<th>신용카드</th>
					<th>무통장입금(가상계좌)</th>
					<th>실시간계좌이체</th>
					<th>관리자 입금</th>
					<th>총</th>
				</tr>
				<tr>
					<td class="text-center">${stdPayStatusVO.cardCnt}</td>
					<td class="text-center">${stdPayStatusVO.vactCnt}</td>
					<td class="text-center">${stdPayStatusVO.acctCnt}</td>
					<td class="text-center">${stdPayStatusVO.siteCnt}</td>
					<td class="text-center">${stdPayStatusVO.payCnt}</td>
				</tr>
			</table>
		</div>
		
		<div class="col-lg-12" style="margin-top:5px;">
			<h5><i class="fa fa-check" aria-hidden="true"/>수강신청 내역</h5>
			<div class="table-responsive">
				<table summary="<spring:message code="board.title.bbs.atcl.list"/>" style="margin-top:5px;" class="table table-bordered" id="stuPayTable">
					<colgroup>
						<col style="width:25px;"/>
						<col style="width:50px;"/>
						<col style="width:70px"/>
						<col style="width:90px"/>
						<col style="width:60px;"/>
						<!-- <col style="width:100px"/>
						<col style="width:130px"/> -->
						<col style="width:120px"/>
						<col style="width:60px;"/>
						<col style="width:60px;"/>
						<col style="width:60px;"/>
						<col style="width:90px"/>
						<col style="width:80px;"/>
						<col style="width:50px;"/>
					</colgroup>
					<thead>
					<tr>
						<!-- <th scope="col">선택</th> -->
						<th scope="col"><input type="checkbox" name="chkAll" id="chkAll" value="N" style="border:0" onclick="checkAll()"/></th>
						<th scope="col"><spring:message code="common.title.no"/></th>
						<th scope="col">이름</th>
						<th scope="col">아이디</th>
						<th scope="col">생년월일</th>
						<!-- <th scope="col">소속기업</th> -->
						<!-- <th scope="col">과정명</th> -->
						<th scope="col">운영과정</th>
						<th scope="col">총 금액</th>
						<th scope="col">과정금액</th>
						<th scope="col">환불금액</th>
						<th scope="col">승인여부</th>
						<th scope="col">신청일</th>
						<th scope="col">관리</th>
					</tr>
					</thead>
					<tbody id="tbodyList">
					<c:forEach items="${stdPayList}" var="item" varStatus="status">
					<tr>
						<td class="text-center"><input type="checkbox" name="sel" value="${item.stdNo }||${item.enrlSts}" /> </td>
						<td>${pageInfo.totalRecordCount - ((pageInfo.currentPageNo - 1 ) * pageInfo.recordCountPerPage + status.index)}</td>
						<td>${item.userNm}</td>
						<td>${item.userId }</td>
						<td>${item.birth }</td>
						<%-- <td>${item.deptNm }</td> --%>
						<%-- <td>${item.crsNm }</td> --%>
						<td>${item.crsCreNm } ${item.creTerm }회차</td>
						<td>${item.paymPrice }</td>
						<td>${item.stdPrice }</td>
						<td>${item.repayPrice }</td>
						<td class="text-center">
							<c:choose>
								<c:when test="${item.repayStsCd eq 'REFUND001' }">환불 요청</c:when>
								<c:when test="${item.repayStsCd eq 'REFUND003' }">환불 완료</c:when>
								<c:when test="${item.repayStsCd eq 'REFUND004' }">환불 취소</c:when>
								<c:when test="${item.enrlSts eq 'E' }">결제 대기</c:when>
								<c:when test="${item.enrlSts eq 'N' }">결제 취소</c:when>
								<c:when test="${item.enrlSts eq 'S' || item.enrlSts eq 'C' || item.enrlSts eq 'F' }">결제 완료</c:when>
								<c:otherwise>-</c:otherwise>
							</c:choose>
						</td>
						<td class="text-center"><meditag:dateformat type="0" delimeter="." property="${item.paymDttm}" /></td>
						<td class="text-center">
							<a href="javascript:viewStuPayPop('${item.stdNo }');" class="btn btn-info btn-sm">결제 관리</a>
						</td>
					</tr>
					</c:forEach>
					<c:if test="${empty stdPayList}">
						<tr>
							<td colspan="10"><spring:message code="common.message.nodata"/></td>
						</tr>
					</c:if>
					</tbody>
				</table>
			</div>
			<meditag:paging pageInfo="${pageInfo}" funcName="listStuPayInfo"/>
		</div>
	</div>