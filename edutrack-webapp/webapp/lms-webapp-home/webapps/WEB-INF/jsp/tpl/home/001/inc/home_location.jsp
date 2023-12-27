<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="USER_ORGNM" value="${sessionScope.ORGNM}"/>
<c:set var="SUB_IMGSN" value="${sessionScope.SUBIMGSN}"/>

				<div class="row" style="margin-bottom:20px;">
					<div class="col-lg-12">
						<div id="main-title">
							<h3>${sessionScope.MENUNAME}</h3>
						</div>
						<div id="main-location" class="hidden-xs">
							<a href="/" target="_top"><i class="fa fa-home fa-fw"></i>HOME</a> &gt; ${sessionScope.LOCATION}
						</div>
					</div>
				</div>