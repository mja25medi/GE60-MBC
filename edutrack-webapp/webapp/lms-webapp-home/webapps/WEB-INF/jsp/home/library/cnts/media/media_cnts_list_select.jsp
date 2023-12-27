<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="itemList" value="${clibMediaCntsList}"/>
<c:if test="${not empty itemList}">
								<option><spring:message code="library.message.olc.page.media"/></option>
							<c:forEach var="item" items="${itemList}">
								<option value="/home/library/clibMediaCnts/preview?clibMediaCntsDTO.cntsCd=${item.cntsCd}">${item.cntsNm}</option>
							</c:forEach>
</c:if>
<c:if test="${empty itemList}">
								<option><spring:message code="common.message.nodata"/></option>
</c:if>