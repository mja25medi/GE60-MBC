<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>
				<div class="well">
					<ul style="list-style-type: none;padding-left:0px;">
					<c:if test="${errCode eq '000' }">
						<li><spring:message code="user.message.login.search.id.result1"/></li>
						<li>- <spring:message code="user.title.userinfo.userid"/> : <b>${userId}</b></li>
					</c:if>
					<c:if test="${errCode eq '100' }">
						<li><spring:message code="user.message.login.search.id.result2"/></li>
						<li><spring:message code="user.message.login.search.id.result3"/></li>
					</c:if>
					<c:if test="${errCode eq '110' }">
						<li><spring:message code="user.message.login.search.alert.input.name"/></li>
					</c:if>
					<c:if test="${errCode eq '120' }">
						<li><spring:message code="user.message.login.search.alert.input.email"/></li>
					</c:if>
					</ul>
				</div>


