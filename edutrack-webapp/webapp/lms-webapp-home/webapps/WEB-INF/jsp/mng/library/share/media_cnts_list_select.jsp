<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="clibShareMediaCntsList" value="${clibShareMediaCntsList}"/>
<c:if test="${not empty clibShareMediaCntsList}">
								<option><spring:message code="library.message.olc.page.media"/></option>
							<c:forEach var="item" items="${clibShareMediaCntsList}">
								<option value="/mng/library/clibMediaCnts/preview?clibMediaCntsDTO.cntsCd=${item.originCntsCd}">${item.cntsNm}</option>
							</c:forEach>
</c:if>
<c:if test="${empty clibShareMediaCntsList}">
								<option><spring:message code="common.message.nodata"/></option>
</c:if>