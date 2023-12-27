<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>

			<ul class="list-group">
				<c:forEach var="item" items="${sbjList}" varStatus="status">
				<li class="list-group-item">
					<a href="javascript:setSbj('${item.sbjCd}','${fn:replace(item.sbjNm,'\'','')}');">${item.sbjNm}</a>
					<span class="pull-right"><meditag:codename code="${item.eduMthdNm}" category="LEC_KIND_CD"/> </span>
				</li>
				</c:forEach>
				<c:if test="${empty sbjList}">
				<li class="list-group-item"><spring:message code="common.message.nodata" /></li>
				</c:if>
			</ul>
