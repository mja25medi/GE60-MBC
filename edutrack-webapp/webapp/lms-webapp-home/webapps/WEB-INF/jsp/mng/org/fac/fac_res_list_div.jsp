<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/page_init.jsp" %>

		<span id="totCnt">예약접수목록(${pageInfo.totalRecordCount})</span>

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
					<th scope="col"><spring:message code="null" text="시설분류"/></th>
					<th scope="col"><spring:message code="null" text="시설명"/></th>
					<th scope="col"><spring:message code="null" text="참석자"/></th>
					<th scope="col"><spring:message code="null" text="예약일자"/></th>
					<th scope="col"><spring:message code="null" text="예약시간"/></th>
					<th scope="col"><spring:message code="null" text="신청일"/></th>
					<th scope="col"><spring:message code="null" text="상태"/></th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="item" items="${facList}" varStatus="status">
				<tr>
					<td class="text-center">&nbsp;${pageInfo.totalRecordCount - status.index}</td>
					<td>&nbsp;${item.facCtgrNm}</td>
					<td>&nbsp;${item.facNm}</td>
					<td>&nbsp;${item.attCnt}</td>
					<td>&nbsp;${item.resDt}</td>
					<td><a href="javaScript:viewPopup('${item.resCd}')">&nbsp;${item.resStm}~${item.resEtm}</a></td>
					<td>&nbsp;<meditag:dateformat type="8" delimeter="-" property="${item.regDttm }" /></td>
					<td>&nbsp;${item.resSts.codeNm}</td>
				</tr>
			</c:forEach>
			<c:if test="${empty facList}">
				<tr>
					<td colspan="10" align="center"><i class="fa fa-warning mr10"></i>검색된 예약 정보가 없습니다.</td>
				</tr>
			</c:if>
			</tbody>
		</table>
		<input type="hidden" name="tmpCnt" id="tmpCnt" value="${vo.cnt}" />
		<meditag:paging pageInfo="${pageInfo}" funcName="listPageing"/>
		<script type="text/javascript">
			function numberWithCommas(x) {
			    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
			}

			$(document).ready(function() {

			});
		</script>
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		