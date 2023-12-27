<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
						<table summary='<spring:message code="course.title.course.manage"/>' class="table table-bordered wordbreak">
							<colgroup>
								<col style="width:auto"/>
								<col style="width:auto"/>
							</colgroup>
							<thead>
								<tr>
									<th scope="col"><spring:message code="etc.title.relatedsite.site.name"/></th>
									<th scope="col"><spring:message code="etc.title.relatedsite.site.url"/></th>
								</tr>
							</thead>
							<tbody id="sortable-site">
								<c:forEach var="item" items="${itemList}" varStatus="status">
								<tr id="${item.siteSn}">
									<td><i class="glyphicon glyphicon-move" style="cursor:pointer"></i> ${item.title} <a href="#none" onclick="siteEdit('${item.siteSn}')"><i class="fa fa-cog"></i></a></td>
									<td>${item.siteUrl}</td>
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
								$('#sortable-site').sortable({
									handle : 'i.glyphicon-move',
							    	stop : function(event, ui) {
							    		var sortString = "";
							    		$("#sortable-site").children('tr').each(function(){
							    			sortString += "|"+$(this).attr("id");
							    		});
							    		sortString = sortString.substring(1);
							    		sortSite(sortString);
							    	}
								});
								$( "#sortable-atcl").disableSelection();
							});
						</script>
