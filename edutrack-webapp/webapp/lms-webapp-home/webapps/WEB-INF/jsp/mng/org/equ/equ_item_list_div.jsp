<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/page_init.jsp" %>

		<table summary="<spring:message code="student.title.student.manage"/>" class="table table-bordered wordbreak">
			<colgroup>
				<col style="width:50px;"/>
				<col style="width:auto;"/>
				<col style="width:250px;"/>
				<col style="width:150px;"/>
			</colgroup>
			<thead>
				<tr>
					<th scope="col"><input type="checkbox" id="chkall" name="chkall"/></th>
					<th scope="col"><spring:message code="null" text="상품명"/></th>
					<th scope="col"><spring:message code="null" text="상품코드"/></th>
					<th scope="col"><spring:message code="null" text="사용여부"/></th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="item" items="${itemList}" varStatus="status">
				<tr>
					<td class="text-center">&nbsp;<input type="checkbox" name="chk_" value="${item.itemCd}" /></td>
					<td>&nbsp;${item.itemNm} <a href="javascript:viewItemEditForm('${item.itemCd}');" ><i class="fa fa-cog"></i></a></td>
					<td class="text-center">&nbsp;${item.itemCd}</a></td>
					<td class="text-center">&nbsp;
						<c:if test="${item.useYn eq 'Y' }">사용</c:if>
						<c:if test="${item.useYn eq 'N' }">사용안함</c:if>
					</td>
				</tr>
			</c:forEach>
			<c:if test="${empty itemList}">
				<tr>
					<td colspan="10" align="center"><i class="fa fa-warning mr10"></i>등록된 상품 정보가 없습니다.</td>
				</tr>
			</c:if>
			</tbody>
		</table>
		<meditag:paging pageInfo="${pageInfo}" funcName="listItem"/>
		<script type="text/javascript">
			
			$("#chkall").click(function() {
				var tmp = $('input:checkbox[id="chkall"]').is(":checked");
				if(tmp) {
					$("input[name=chk_]:checkbox").prop("checked", "checked");
				} else {
					$("input[name=chk_]:checkbox").removeProp("checked");
				}
			});
		</script>
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		