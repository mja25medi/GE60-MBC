<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="assignmentListVO" value="${assignmentListVO}"/>

							<ul>
							<c:forEach var="item" items="${assignmentListVO}">
								<li class="list-group-item">
									${fn:substring(item.asmtTitle,0, 45)}(문제 : ${item.subCnt})<c:if test="${fn:length(item.asmtTitle) > 45 }">...</c:if>
									<span class="btns">
                               		     <button class="btn3 sm basic fcBlue" onclick="javascript:selectShareCnts('${item.asmtSn}')"><spring:message code="button.choice"/></button>
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
