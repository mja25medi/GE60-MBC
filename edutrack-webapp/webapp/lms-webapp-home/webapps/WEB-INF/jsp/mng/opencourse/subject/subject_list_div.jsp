<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="openCrsSbjList" value="${openCrsSbjList}"/>
							<table summary='<spring:message code="course.title.course.manage.subject"/>' class="table table-bordered wordbreak">
								<colgroup>
									<col style="width:auto"/>
									<col style="width:120px"/>
									<col style="width:80px"/>
									<col style="width:70px"/>
								</colgroup>
								<thead>
									<tr>
										<th scope="col"><spring:message code="course.open.title.course.name"/></th>
										<th scope="col"><spring:message code="course.title.subject.code"/></th>
										<th scope="col"><spring:message code="course.title.subject.contents.cnt"/></th>
										<th scope="col"><spring:message code="common.title.delete"/></th>
									</tr>
								</thead>
								<tbody id="sortable-subject">
									<c:forEach var="item" items="${openCrsSbjList}" varStatus="status">
									<tr id="${item.sbjCd}">
										<td ><i class="glyphicon glyphicon-move" style="cursor:pointer"></i> ${item.sbjNm}</td>
										<td class="text-center">${item.sbjCd}</td>
										<td class="text-center">${item.contentsCnt}</td>
										<td class="text-center"><a class="btn btn-sm btn-primary" href="#none" onclick="sbjDelete('${item.crsCd}','${item.sbjCd}')"><spring:message code="button.delete"/></a></td>
									</tr>
									</c:forEach>
									<c:if test="${empty openCrsSbjList}">
									<tr>
										<td colspan="5"><spring:message code="common.message.nodata"/></td>
									</tr>
									</c:if>
								</tbody>
							</table>
							<script type="text/javascript">
								$(document).ready(function() {
									$('#sortable-subject').sortable({
										handle : 'i.glyphicon-move',
								    	stop : function(event, ui) {
								    		var sortString = "";
								    		$("#sortable-subject").children('tr').each(function(){
								    			sortString += "|"+$(this).attr("id");
								    		});
								    		sortString = sortString.substring(1);
								    		sbjSort(sortString);
								    	}
									});
									$( "#sortable-subject" ).disableSelection();
								});
							</script>
