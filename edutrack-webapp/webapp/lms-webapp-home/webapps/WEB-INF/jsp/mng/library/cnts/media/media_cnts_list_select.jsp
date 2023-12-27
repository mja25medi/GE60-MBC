<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="clibMediaCntsList" value="${clibMediaCntsList}"/>
<c:if test="${not empty clibMediaCntsList}">
								<option><spring:message code="library.message.olc.page.media"/></option>
							<c:forEach var="item" items="${clibMediaCntsList}">
								<option value="/mng/library/clibMediaCnts/preview?clibMediaCntsVO.cntsCd=${item.cntsCd}">${item.cntsNm}</option>
							</c:forEach>
</c:if>
<c:if test="${empty clibMediaCntsList}">
								<option><spring:message code="common.message.nodata"/></option>
</c:if>