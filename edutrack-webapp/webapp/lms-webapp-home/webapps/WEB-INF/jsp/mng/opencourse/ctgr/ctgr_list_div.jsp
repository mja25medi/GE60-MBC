<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="openCrsCtgrVO" value="${vo}"/>
<c:set var="openCrsCtgrList" value="${openCrsCtgrList}"/>
						<table summary="<spring:message code="course.open.title.category.manage"/>" class="table table-bordered wordbreak">
							<colgroup>
								<col style="width:auto"/>
								<col style="width:100px"/>
							</colgroup>
							<thead>
								<tr>
									<th scope="col"><spring:message code="course.open.title.category.name"/></th>
									<th scope="col"><spring:message code="course.open.title.category.useyn"/></th>
								</tr>
							</thead>
							<tbody id="sortable-ctgr">
								<c:set var="ctgrCd" value=""/>
								<c:set var="ctgrNm" value=""/>
								<c:forEach items="${openCrsCtgrList}" var="item" varStatus="status">
								<c:if test="${item.ctgrCd eq openCrsCtgrVO.ctgrCd }">
									<c:set var="ctgrCd" value="${item.ctgrCd}"/>
									<c:set var="ctgrNm" value="${item.ctgrNm}"/>
								</c:if>
								<tr id="${item.ctgrCd}">
									<td>
										<i class="glyphicon glyphicon-move" style="cursor:pointer"></i>  <a href="javascript:setCtgr('${item.ctgrCd}','${item.ctgrNm}');">${item.ctgrNm}</a>
										<a href="javascript:ctgrEdit('${item.ctgrCd}');" class=""><i class="fa fa-cog"></i> </a>
									</td>
									<td class="text-center"><meditag:codename code="${item.useYn}" category="USE_YN"/></td>
								</tr>
								</c:forEach>
								<c:if test="${empty openCrsCtgrList}">
								<tr>
									<td colspan="3"><spring:message code="course.open.message.category.nodata"/></td>
								</tr>
								</c:if>
							</tbody>
						</table>
						<script type="text/javascript">
							$(document).ready(function(){
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
								setCtgr("${ctgrCd}", "${ctgrNm}");
							});
						</script>
