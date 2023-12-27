<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
	<div class="row">
		<div class="col-lg-12" style="margin-top:5px;">
			<h5><i class="fa fa-check" aria-hidden="true"/>환불 내역</h5>
			<div class="table-responsive">
				<table summary="<spring:message code="board.title.bbs.atcl.list"/>" style="margin-top:5px;" class="table table-bordered" id="stuPayTable">
					<colgroup>
						<col style="width:25px;"/>
						<col style="width:50px;"/>
						<col style="width:70px"/>
						<col style="width:110px"/>
						<col style="width:60px;"/>
						<col style="width:60px;"/>
					</colgroup>
					<thead>
					<tr>
						<th scope="col">과정</th>
						<th scope="col">종류</th>
						<th scope="col">결제금액(원)</th>
						<th scope="col">환불금액(원)</th>
						<th scope="col">환불요청일시</th>
						<th scope="col">환불일시</th>
					</tr>
					</thead>
					<tbody id="tbodyList">
					<c:forEach items="${refundList}" var="item" varStatus="status">
					<tr>
						<td>${item.crsCreNm }</td>
						<td>${item.refundTypeCd }</td>
						<td>${item.stdPrice }</td>
						<td>${item.repayPrice}</td>
						<td class="text-center"><meditag:dateformat type="0" delimeter="." property="${item.repayReqDttm}" /></td>
						<td class="text-center"><meditag:dateformat type="0" delimeter="." property="${item.repayDttm}" /></td>
					</tr>
					</c:forEach>
					<c:if test="${empty refundList}">
						<tr>
							<td colspan="6"><spring:message code="common.message.nodata"/></td>
						</tr>
					</c:if>
					</tbody>
				</table>
			</div>
		</div>
	</div>