<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="OrgReshVO" value="${OrgReshVO}" />
<c:set var="OrgReshItemList" value="${OrgReshItemList}" />
						<div class="text-right">
						<c:if test= "${CREAUTH eq 'Y'}">
						<c:if test="${orgReshVO.useCnt == 0 }">
							<a href="javascript:questionWrite()" class="btn btn-primary btn-sm" ><spring:message code="button.write.question"/> </a>
							<a href="javascript:questionExcelWrite()" class="btn btn-primary btn-sm"><i class="fa fa-file-excel-o fa-fw"></i> <spring:message code="button.upload.excel"/> </a>
						</c:if>
						</c:if>
						</div>
			<table summary="<spring:message code="course.title.reshbank.item.manage"/>" class="table table-bordered wordbreak">
				<colgroup>
					<col style="width:auto"/>
					<col style="width:50px"/>
				</colgroup>
				<thead>
					<tr>
						<th scope="col"><spring:message code="course.title.reshbank.item"/></th>
						<th scope="col"><spring:message code="common.title.edit"/></th>
					</tr>
				</thead>
				<tbody id="sortable-item">
					<c:forEach var="item" items="${OrgReshItemList}" varStatus="status">
						<c:set var="styleStr" value="float:left;margin-left:20px;"/>
						<c:if test="${item.emplViewType eq 'HEIGHT' }"><c:set var="styleStr" value="margin-left:20px;"/></c:if>
					<tr id="${item.reshItemSn}">
						<td class="wordbreak">
							<i class="glyphicon glyphicon-move" style="cursor:pointer"></i> ${status.count}. ${item.reshItemCts}
<%-- 							<c:if test="${item.reshItemTypeCd eq 'K' }"> --%>
<!-- 							<ul style="list-style-type: none; padding-left:0px;"> -->
<%-- 								<c:if test="${not empty item.empl1}"> --%>
<%-- 								<li style="${styleStr}"> --%>
<%-- 									<b><spring:message code="course.title.reshbank.item.empl"/>1</b> : ${item.empl1} --%>
<!-- 								</li> -->
<%-- 								</c:if> --%>
<%-- 								<c:if test="${not empty item.empl2}"> --%>
<%-- 								<li style="${styleStr}"> --%>
<%-- 									<b><spring:message code="course.title.reshbank.item.empl"/>2</b> : ${item.empl2} --%>
<!-- 								</li> -->
<%-- 								</c:if> --%>
<%-- 								<c:if test="${not empty item.empl3}"> --%>
<%-- 								<li style="${styleStr}"> --%>
<%-- 									<b><spring:message code="course.title.reshbank.item.empl"/>3</b> : ${item.empl3} --%>
<!-- 								</li> -->
<%-- 								</c:if> --%>
<%-- 								<c:if test="${not empty item.empl4}"> --%>
<%-- 								<li style="${styleStr}"> --%>
<%-- 									<b><spring:message code="course.title.reshbank.item.empl"/>4</b> : ${item.empl4} --%>
<!-- 								</li> -->
<%-- 								</c:if> --%>
<%-- 								<c:if test="${not empty item.empl5}"> --%>
<%-- 								<li style="${styleStr}"> --%>
<%-- 									<b><spring:message code="course.title.reshbank.item.empl"/>5</b> : ${item.empl5} --%>
<!-- 								</li> -->
<%-- 								</c:if> --%>
<%-- 								<c:if test="${not empty item.empl6}"> --%>
<%-- 								<li style="${styleStr}"> --%>
<%-- 									<b><spring:message code="course.title.reshbank.item.empl"/>6</b> : ${item.empl6} --%>
<!-- 								</li> -->
<%-- 								</c:if> --%>
<%-- 								<c:if test="${not empty item.empl7}"> --%>
<%-- 								<li style="${styleStr}"> --%>
<%-- 									<b><spring:message code="course.title.reshbank.item.empl"/>7</b> : ${item.empl7} --%>
<!-- 								</li> -->
<%-- 								</c:if> --%>
<%-- 								<c:if test="${not empty item.empl8}"> --%>
<%-- 								<li style="${styleStr}"> --%>
<%-- 									<b><spring:message code="course.title.reshbank.item.empl"/>8</b> : ${item.empl8} --%>
<!-- 								</li> -->
<%-- 								</c:if> --%>
<%-- 								<c:if test="${not empty item.empl9}"> --%>
<%-- 								<li style="${styleStr}"> --%>
<%-- 									<b><spring:message code="course.title.reshbank.item.empl"/>9</b> : ${item.empl9} --%>
<!-- 								</li> -->
<%-- 								</c:if> --%>
<%-- 								<c:if test="${not empty item.empl10}"> --%>
<%-- 								<li style="${styleStr}"> --%>
<%-- 									<b><spring:message code="course.title.reshbank.item.empl"/>10</b> : ${item.empl10} --%>
<!-- 								</li> -->
<%-- 								</c:if> --%>
<%-- 								<c:if test="${not empty item.empl11}"> --%>
<%-- 								<li style="${styleStr}"> --%>
<%-- 									<b><spring:message code="course.title.reshbank.item.empl"/>11</b> : ${item.empl11} --%>
<!-- 								</li> -->
<%-- 								</c:if> --%>
<%-- 								<c:if test="${not empty item.empl12}"> --%>
<%-- 								<li style="${styleStr}"> --%>
<%-- 									<b><spring:message code="course.title.reshbank.item.empl"/>12</b> : ${item.empl12} --%>
<!-- 								</li> -->
<%-- 								</c:if> --%>
<%-- 								<c:if test="${not empty item.empl13}"> --%>
<%-- 								<li style="${styleStr}"> --%>
<%-- 									<b><spring:message code="course.title.reshbank.item.empl"/>13</b> : ${item.empl13} --%>
<!-- 								</li> -->
<%-- 								</c:if> --%>
<%-- 								<c:if test="${not empty item.empl14}"> --%>
<%-- 								<li style="${styleStr}"> --%>
<%-- 									<b><spring:message code="course.title.reshbank.item.empl"/>14</b> : ${item.empl14} --%>
<!-- 								</li> -->
<%-- 								</c:if> --%>
<%-- 								<c:if test="${not empty item.empl15}"> --%>
<%-- 								<li style="${styleStr}"> --%>
<%-- 									<b><spring:message code="course.title.reshbank.item.empl"/>15</b> : ${item.empl15} --%>
<!-- 								</li> -->
<%-- 								</c:if> --%>
<%-- 								<c:if test="${not empty item.empl16}"> --%>
<%-- 								<li style="${styleStr}"> --%>
<%-- 									<b><spring:message code="course.title.reshbank.item.empl"/>16</b> : ${item.empl16} --%>
<!-- 								</li> -->
<%-- 								</c:if> --%>
<%-- 								<c:if test="${not empty item.empl17}"> --%>
<%-- 								<li style="${styleStr}"> --%>
<%-- 									<b><spring:message code="course.title.reshbank.item.empl"/>17</b> : ${item.empl17} --%>
<!-- 								</li> -->
<%-- 								</c:if> --%>
<%-- 								<c:if test="${not empty item.empl18}"> --%>
<%-- 								<li style="${styleStr}"> --%>
<%-- 									<b><spring:message code="course.title.reshbank.item.empl"/>18</b> : ${item.empl18} --%>
<!-- 								</li> -->
<%-- 								</c:if> --%>
<%-- 								<c:if test="${not empty item.empl19}"> --%>
<%-- 								<li style="${styleStr}"> --%>
<%-- 									<b><spring:message code="course.title.reshbank.item.empl"/>19</b> : ${item.empl19} --%>
<!-- 								</li> -->
<%-- 								</c:if> --%>
<%-- 								<c:if test="${not empty item.empl20}"> --%>
<%-- 								<li style="${styleStr}"> --%>
<%-- 									<b><spring:message code="course.title.reshbank.item.empl"/>20</b> : ${item.empl20} --%>
<!-- 								</li> -->
<%-- 								</c:if> --%>
<!-- 							</ul> -->
<%-- 							</c:if> --%>
						</td>
						<td class="text-center">
							<c:if test= "${CREAUTH eq 'Y'}">
							<c:if test="${OrgReshVO.useCnt == 0 }">
							<a href="javascript:questionEdit('${item.reshItemSn}')" class="btn btn-primary btn-sm"><spring:message code="button.edit"/> </a>
							</c:if>
							<c:if test="${OrgReshVO.useCnt > 0 }">
							-
							</c:if>
							</c:if>
						</td>
					</tr>
					</c:forEach>
					<c:if test="${empty OrgReshItemList }">
					<tr>
						<td colspan="2"><spring:message code="course.message.reshbank.item.nodata"/> </td>
					</tr>
					</c:if>
				</tbody>
			</table>
			<script type="text/javascript">
				$(document).ready(function() {
					$('#sortable-item').sortable({
						handle : 'i.glyphicon-move',
				    	stop : function(event, ui) {
				    		var sortString = "";
				    		$("#sortable-item").children('tr').each(function(){
				    			sortString += "|"+$(this).attr("id");
				    		});
				    		sortString = sortString.substring(1);
				    		researchSort(sortString);
				    	}
					});
					$( "#sortable-item" ).disableSelection();
				});
			</script>

