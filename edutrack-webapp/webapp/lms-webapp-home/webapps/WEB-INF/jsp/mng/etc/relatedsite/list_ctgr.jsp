<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
						<table summary="<spring:message code="board.title.faq.category.manage"/>" class="table table-bordered wordbreak">
							<colgroup>
								<col style="width:auto"/>
								<col style="width:auto"/>
							</colgroup>
							<thead>
								<tr>
									<th scope="col"><spring:message code="etc.title.relatedsite.ctgr.name"/></th>
									<th scope="col"><spring:message code="etc.title.relatedsite.ctgr.desc"/></th>
								</tr>
							</thead>
							<tbody id="sortable-ctgr">
								<c:forEach var="item" items="${itemList}" varStatus="status">
								<c:if test="${status.index eq 0 }">
									<c:set var="mainCtgrCd" value="${item.ctgrCd }"/>
									<c:set var="mainTitle" value="${item.title }"/>
								</c:if>
								<tr id="${item.ctgrCd}">
									<td>
										<i class="glyphicon glyphicon-move" style="cursor:pointer"></i> <a href="#none" onclick="setCtgr('${item.ctgrCd}','${item.title}')">${item.title}</a>
										<a href="#none" onclick="ctgrEdit('${item.ctgrCd}')"><i class="fa fa-cog"></i></a>
									</td>
									<td>${item.ctgrDesc}</td>
								</tr>
								</c:forEach>
								<c:if test="${empty itemList}">
								<tr>
									<td colspan="2"><spring:message code="common.message.nodata"/></td>
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
							    		sortCtgr(sortString);
							    	}
								});
								$( "#sortable-ctgr" ).disableSelection();
								setCtgr('${mainCtgrCd}', '${mainTitle}');
							});
						</script>
