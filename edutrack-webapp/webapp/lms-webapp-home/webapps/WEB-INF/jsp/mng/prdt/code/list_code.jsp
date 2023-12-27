<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
							<ul class="list-group" id="sortable-code">
								<c:forEach var="item" items="${codeList}">
								<li class="list-group-item" id="${item.codeCd}">
									<i class="glyphicon glyphicon-move" style="cursor:pointer"></i> ${item.codeNm} <a href="javascript:editCodeForm('${item.codeCd}')"><i class="fa fa-cog"></i></a>
									<span class="pull-right"><meditag:codename code="${item.useYn}" category="USE_YN"/></span>
								</li>
								</c:forEach>
								<c:if test="${empty codeList}">
								<li class="list-group-item"><spring:message code="common.message.nodata"/></li>
								</c:if>
							</ul>
							<script type="text/javascript">
								$(document).ready(function() {
									$('#sortable-code').sortable({
										handle : 'i.glyphicon-move',
								    	stop : function(event, ui) {
								    		var sortString = "";
								    		$("#sortable-code").children('li').each(function(){
								    			sortString += "|"+$(this).attr("id");
								    		});
								    		sortString = sortString.substring(1);
								    		sortCode(sortString);
								    	}
									});
									$( "#sortable-code" ).disableSelection();
								});
							</script>