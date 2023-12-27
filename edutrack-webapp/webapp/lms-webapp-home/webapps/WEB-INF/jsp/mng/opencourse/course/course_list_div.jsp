<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="openCrsList" value="${openCrsList}"/>
						<table summary='<spring:message code="course.title.course.manage"/>' class="table table-bordered wordbreak">
							<colgroup>
								<col style="width:auto"/>
								<col style="width:80px"/>
								<col style="width:90px"/>
								<col style="width:100px"/>
								<col style="width:70px"/>
							</colgroup>
							<thead>
								<tr>
									<th scope="col"><spring:message code="course.title.course.name"/></th>
									<th scope="col"><spring:message code="course.open.title.subject.count"/></th>
									<th scope="col"><spring:message code="course.open.title.category.useyn"/></th>
									<th scope="col"><spring:message code="course.open.title.course.main.exposure"/></th>
									<th scope="col"><spring:message code="button.manage"/></th>
								</tr>
							</thead>
							<tbody id="sortable-course">
								<c:forEach var="item" items="${openCrsList}" varStatus="status">
								<tr id="${item.crsCd}">
									<td class="text-left">
										<i class="glyphicon glyphicon-move" style="cursor:pointer"></i> ${item.crsNm}
										<a href="#none" onclick="crsEdit('${item.crsCd}')"><i class="fa fa-cog"></i></a>
									</td>
									<td class="text-center">${item.sbjCnt}</td>
									<td class="text-center"><meditag:codename code="${item.useYn}" category="USE_YN"/></td>
									<td class="text-center">
										<c:if test="${not empty item.sampleCntsPath}">
										<input type="checkbox" value="${item.crsCd}" name="exposure" <c:if test="${item.exposureYn eq 'Y'}">checked</c:if> onclick="changeExposure(this)"/>
										</c:if>
									</td>
									<td class="text-center"><a class="btn btn-sm btn-primary" href="#none" onclick="crsManage('${item.crsCd}','${item.ctgrCd}')"><spring:message code="button.manage"/></a></td>
								</tr>
								</c:forEach>
								<c:if test="${empty openCrsList}">
								<tr>
									<td colspan="5"><spring:message code="common.message.nodata"/></td>
								</tr>
								</c:if>
							</tbody>
						</table>
						<script type="text/javascript">
							$(document).ready(function(){
								$('#sortable-course').sortable({
									handle : 'i.glyphicon-move',
							    	stop : function(event, ui) {
							    		var sortString = "";
							    		$("#sortable-course").children('tr').each(function(){
							    			sortString += "|"+$(this).attr("id");
							    		});
							    		sortString = sortString.substring(1);
							    		crsSort(sortString);
							    	}
								});
							});
						</script>
