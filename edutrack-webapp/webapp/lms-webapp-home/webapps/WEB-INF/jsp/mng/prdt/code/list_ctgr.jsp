<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>

							<ul class="list-group" id="sortable-ctgr">
								<c:forEach var="item" items="${codeCtgrList}">
								<li class="list-group-item" id="${item.codeCtgrCd}">
									<i class="glyphicon glyphicon-move" style="cursor:pointer"></i>  ${item.codeCtgrNm} <a href="javascript:editCtgrForm('${item.codeCtgrCd}')"><i class="fa fa-cog"></i></a>
									<span class="pull-right"><meditag:codename code="${item.useYn}" category="USE_YN"/></span>
								</li>
								</c:forEach>
								<c:if test="${empty codeCtgrList}">
								<li class="list-group-item"><spring:message code="common.message.nodata"/></li>
								</c:if>
							</ul>
							<script type="text/javascript">
								$(document).ready(function() {
									$('#sortable-ctgr').sortable({
										handle : 'i.glyphicon-move',
								    	stop : function(event, ui) {
								    		var sortString = "";
								    		$("#sortable-ctgr").children('li').each(function(){
								    			sortString += "|"+$(this).attr("id");
								    		});
								    		sortString = sortString.substring(1);
								    		sortCtgr(sortString);
								    	}
									});
									$( "#sortable-ctgr" ).disableSelection();
								});
							</script>