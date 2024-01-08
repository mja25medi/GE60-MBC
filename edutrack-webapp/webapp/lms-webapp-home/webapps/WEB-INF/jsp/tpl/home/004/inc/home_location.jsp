<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="USER_ORGNM" value="${sessionScope.ORGNM}"/>
<c:set var="SUB_IMGSN" value="${sessionScope.SUBIMGSN}"/>

                    <div class="h1_area">
                        <div class="location_bar">
                            <nav class="location">
                                <ul>
                                    <li><a href="/"><i class="xi-home-o" aria-hidden="true"></i><span class="sr-only">Home</span></a></li>
                                    <c:set var="LOCATIONARR" value="${fn:split(LOCATION,'>')}" />
                                    <c:forEach items="${LOCATIONARR }" var="item" varStatus="status">
                                    	<li><span class="current">${item }</span></li>
                                    </c:forEach>
                                </ul>
                            </nav>                                                   
                        </div>
                        <h4 class="title_h1">${sessionScope.ROOTMENUNAME}</h4>
                    </div>

	