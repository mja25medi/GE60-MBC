<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
						<table summary="<spring:message code="board.title.faq.category.manage"/>" class="table table-bordered wordbreak">
							<colgroup>
								<col style="width:100px"/>
								<col style="width:auto"/>
								<col style="width:80px"/>
							</colgroup>
							<thead>
								<tr>
									<th scope="col"><spring:message code="board.title.faq.category.code"/></th>
									<th scope="col"><spring:message code="board.title.faq.category.name"/></th>
									<th scope="col"><spring:message code="common.title.useyn"/></th>
								</tr>
							</thead>
							<tbody id="sortable-ctgr">
								<c:forEach items="${brdFaqCtgrList}" var="item" varStatus="status">
								<tr id="${item.ctgrCd}">
									<td>${item.ctgrCd}</td>
									<td>
										<i class="glyphicon glyphicon-move" style="cursor:pointer"></i> <a href="javascript:setCtgr('${item.ctgrCd}','${item.ctgrNm}');">${item.ctgrNm}</a>
										<a href="javascript:ctgrEdit('${item.ctgrCd}');" ><i class="fa fa-cog"></i></a>
									</td>
									<td class="text-center"><meditag:codename code="${item.useYn}" category="USE_YN"/></td>
								</tr>
								</c:forEach>
								<c:if test="${empty brdFaqCtgrList}">
								<tr>
									<td colspan="3"><spring:message code="board.message.faq.category.nodata"/></td>
								</tr>
								</c:if>
							</tbody>
						</table>
						<script type="text/javascript">
							$(document).ready(function() {
								$('#sortable-ctgr').sortable({
									handle : 'i.glyphicon-move',
							    	stop : function(event, ui) {
							    		var sortString = "";
							    		$("#sortable-ctgr").children('tr').each(function(){
							    			sortString += "|"+$(this).attr("id");
							    		});
							    		sortString = sortString.substring(1);
							    		ctgrSort(sortString);
							    	}
								});
								$( "#sortable-ctgr" ).disableSelection();
							});
						</script>
