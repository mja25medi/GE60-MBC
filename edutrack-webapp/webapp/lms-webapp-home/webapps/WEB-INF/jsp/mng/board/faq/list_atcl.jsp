<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
						<table summary="<spring:message code="board.title.faq.manage"/>" class="table table-bordered wordbreak">
							<colgroup>
								<col style="width:50px"/>
								<col style="width:auto"/>
								<col style="width:100px"/>
							</colgroup>
							<thead>
								<tr>
									<th scope="col"><spring:message code="common.title.no"/></th>
									<th scope="col"><spring:message code="common.title.title"/></th>
									<th scope="col"><spring:message code="common.title.regdate"/></th>
								</tr>
							</thead>
							<tbody id="sortable-atcl">
								<c:forEach items="${brdFaqAtclList}" var="item" varStatus="status">
								<tr id="${item.atclSn}">
									<td class="text-right">${status.count}</td>
									<td><i class="glyphicon glyphicon-move" style="cursor:pointer"></i> ${item.atclTitle} <a href="javascript:atclEdit('${item.atclSn}');"><i class="fa fa-cog"></i></a></td>
									<td class="text-center"><meditag:dateformat type="1" delimeter="." property="${item.regDttm}"/></td>
								</tr>
								</c:forEach>
								<c:if test="${empty brdFaqAtclList}">
								<tr>
									<td colspan="3"><spring:message code="board.message.faq.nodata"/></td>
								</tr>
								</c:if>
							</tbody>
						</table>
						<script type="text/javascript">
							$(document).ready(function() {
								$('#sortable-atcl').sortable({
									handle : 'i.glyphicon-move',
							    	stop : function(event, ui) {
							    		var sortString = "";
							    		$("#sortable-atcl").children('tr').each(function(){
							    			sortString += "|"+$(this).attr("id");
							    		});
							    		sortString = sortString.substring(1);
							    		atclSort(sortString);
							    	}
								});
								$( "#sortable-atcl").disableSelection();
							});
						</script>
