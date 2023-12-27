<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>
				<div class="well">
					<ul style="list-style-type: none;padding-left:0px;">
					<c:if test="${errCode eq '000' }">
						<li><spring:message code="user.message.login.reset.pass.email.send.success"/></li>
						<li>- <spring:message code="user.message.login.reset.pass.email.send.success.msg" arguments="${email}"/></li>
					</c:if>
					<c:if test="${errCode eq '310' }">
						<li><spring:message code="user.message.login.search.alert.input.userid"/></li>
					</c:if>
					<c:if test="${errCode eq '320' }">
						<li><spring:message code="user.message.login.search.alert.input.name"/></li>
					</c:if>
					<c:if test="${errCode eq '330' }">
						<li><spring:message code="user.message.login.search.alert.input.email"/></li>
					</c:if>
					<c:if test="${errCode eq '340' }">
						<li><spring:message code="user.message.login.reset.pass.failed"/></li>
					</c:if>
					<c:if test="${errCode eq '350' }">
						<li><spring:message code="user.message.login.reset.pass.email.send.failed"/></li>
						<li><spring:message code="user.message.login.reset.pass.email.send.failed.msg"/></li>
					</c:if>
					<c:if test="${errCode eq '360' }">
						<li><spring:message code="user.message.login.search.id.result2"/></li>
						<li><spring:message code="user.message.login.search.id.result3"/></li>
					</c:if>
					</ul>
				</div>