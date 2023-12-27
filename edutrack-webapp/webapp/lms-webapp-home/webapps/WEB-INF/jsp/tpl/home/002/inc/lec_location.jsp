<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>
				<div class="row" style="padding:0 15px;margin-bottom:20px;">
					<div id="main-title">
						<h3>${sessionScope.MENUNAME}</h3>
					</div>
					<div id="main-location" class="hidden-xs">
						<a href="/" target="_top"><i class="fa fa-home fa-fw"></i>HOME</a> &gt; <spring:message code="lecture.title.classroom"/> &gt; ${sessionScope.LOCATION}
					</div>
				</div>