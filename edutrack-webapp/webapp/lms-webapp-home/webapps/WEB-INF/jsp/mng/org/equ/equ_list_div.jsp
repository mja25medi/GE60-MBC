<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/page_init.jsp" %>

		<table summary="<spring:message code="student.title.student.manage"/>" class="table table-bordered wordbreak">
			<colgroup>
				<col style="width:50px;"/>
				<col style="width:auto;"/>
				<col style="width:150px;"/>
				<col style="width:150px;"/>
				<col style="width:150px;"/>
			</colgroup>
			<thead>
				<tr>
					<th scope="col"><input type="checkbox" id="chkall" name="chkall"/></th>
					<th scope="col"><spring:message code="null" text="장비명"/></th>
					<th scope="col"><spring:message code="null" text="상품갯수"/></th>
					<th scope="col"><spring:message code="null" text="사용여부"/></th>
					<th scope="col"><spring:message code="null" text="관리"/></th>
				</tr>
			</thead>
			<tbody id="sortable-equ">
			<c:forEach var="item" items="${equList}" varStatus="status">
				<tr id="${item.equCd}">
					<td class="text-center">&nbsp;<input type="checkbox" name="chk_" value="${item.equCd}" /></td>
					<td><i class="glyphicon glyphicon-move" style="cursor:pointer"></i>&nbsp;${item.equNm} <a href="javascript:viewEquEditForm('${item.equCd}');" ><i class="fa fa-cog"></i></a></td>
					<td class="text-center">&nbsp;${item.itemCnt}</a></td>
					<td class="text-center">&nbsp;
						<c:if test="${item.useYn eq 'Y' }">사용</c:if>
						<c:if test="${item.useYn eq 'N' }">사용안함</c:if>
					</td>
					<td class="text-center"><button onclick="listItemMain('${item.equCd }')" class="btn btn-sm">상품관리</button></td>
				</tr>
			</c:forEach>
			<c:if test="${empty equList}">
				<tr>
					<td colspan="10" align="center"><i class="fa fa-warning mr10"></i>등록된 장비 정보가 없습니다.</td>
				</tr>
			</c:if>
			</tbody>
		</table>
		<meditag:paging pageInfo="${pageInfo}" funcName="listEqu"/>
		<script type="text/javascript">
			$(document).ready(function() {
				$('#sortable-equ').sortable({
					handle : 'i.glyphicon-move',
			    	stop : function(event, ui) {
			    		var equCd = ui.item.attr("id");
			    		var equOdr = ui.item.index() + 1;
			    		sortEqu(equCd, equOdr);
			    	}
				});
				var cnt = ${pageInfo.totalRecordCount};
				$("#equCntInfo").text("장비목록(" + cnt + ")");
			});
		
			$("#chkall").click(function() {
				var tmp = $('input:checkbox[id="chkall"]').is(":checked");
				if(tmp) {
					$("input[name=chk_]:checkbox").prop("checked", "checked");
				} else {
					$("input[name=chk_]:checkbox").removeProp("checked");
				}
			});
		</script>
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		