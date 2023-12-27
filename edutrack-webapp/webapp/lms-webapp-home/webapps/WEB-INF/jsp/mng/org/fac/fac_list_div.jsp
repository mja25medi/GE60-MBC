<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/page_init.jsp" %>

		<table summary="<spring:message code="student.title.student.manage"/>" class="table table-bordered wordbreak">
			<colgroup>
				<col style="width:50px;"/>
				<col style="width:auto;"/>
				<col style="width:auto;"/>
				<col style="width:auto;"/>
				<col style="width:auto;"/>
				<col style="width:auto"/>
				<col style="width:auto;"/>
				<col style="width:76px;"/>
			</colgroup>
			<thead>
				<tr>
					<th scope="col"><input type="checkbox" id="chkall" name="chkall"/></th>
					<th scope="col"><spring:message code="null" text="시설분류"/></th>
					<th scope="col"><spring:message code="null" text="시설명"/></th>
					<th scope="col"><spring:message code="null" text="비품"/></th>
					<th scope="col"><spring:message code="null" text="면적"/></th>
					<th scope="col"><spring:message code="null" text="수용인원"/></th>
					<th scope="col"><spring:message code="null" text="위치"/></th>
					<th scope="col"><spring:message code="null" text="사용여부"/></th>
				</tr>
			</thead>
			<tbody id="sortable-fac">
			<c:forEach var="item" items="${facList}" varStatus="status">
				<tr id="${item.facCd }">
					<td class="text-center">&nbsp;<input type="checkbox" name="chk_" value="${item.facCd}" /></td>
					<td>&nbsp;${item.facCtgrNm}</td>
					<td>
						<i class="glyphicon glyphicon-move" style="cursor:pointer"></i>&nbsp;
						<a href="javaScript:viewPopup('${item.facCd}','${item.facNm}')">&nbsp;${item.facNm}</a>
					</td>
					<td>&nbsp;${item.facEqu }</td>
					<td>&nbsp;${item.facArea}</td>
					<td>&nbsp;${item.facCap}</td>
					<td>&nbsp;${item.facLoc}</td>
					<td>&nbsp;${item.useYn}</td>
				</tr>
			</c:forEach>
			<c:if test="${empty facList}">
				<tr>
					<td colspan="10" align="center"><i class="fa fa-warning mr10"></i>검색된 시설 정보가 없습니다.</td>
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
				$('#sortable-fac').sortable({
					handle : 'i.glyphicon-move',
			    	stop : function(event, ui) {
			    		var facCd = ui.item.attr("id");
			    		var facOdr = ui.item.index() + 1;
			    		sortFac(facCd, facOdr);
			    	}
				});
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
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		