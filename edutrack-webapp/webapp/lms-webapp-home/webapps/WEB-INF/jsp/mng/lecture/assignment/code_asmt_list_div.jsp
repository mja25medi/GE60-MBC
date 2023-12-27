<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="assignmentListVO" value="${assignmentListVO}"/>

							<ul class="list-group">
							<c:forEach var="item" items="${assignmentListVO}">
								<li class="list-group-item">
									${fn:substring(item.asmtTitle,0, 45)}(문제 : ${item.subCnt})<c:if test="${fn:length(item.asmtTitle) > 45 }">...</c:if>
									<span class="pull-right" style="text-align: center;">
										<a href="javascript:selectShareCnts('${item.asmtSn}')" class="btn btn-xs btn-default"><spring:message code="button.choice"/></a>
										
									</span>
									<div style="clear:both;"></div>
								</li>
							</c:forEach>
							<c:if test="${empty assignmentListVO}">
								<li class="list-group-item"><spring:message code="common.message.nodata"/></li>
							</c:if>
							</ul>

							<script type="text/javascript">
								$(document).ready(function() {


								});
							</script>

