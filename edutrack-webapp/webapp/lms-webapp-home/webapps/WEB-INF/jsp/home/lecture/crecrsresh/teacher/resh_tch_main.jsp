<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="creCrsReshList" value="${creCrsReshList}"/>
<c:url var="img_base" value="/img/home" />
	<form name="Search" id="Search" action="/lec/research">
		<div class="learn_top">
			<div class="left_title">
				<h3>강의평가관리</h3>
			</div>
		</div>
		<c:forEach var="item" items="${creCrsReshList}" varStatus="status">
			<div class="segment">
				<div class="learn_info">
					<div class="header">
						<!-- <label class="mixing">온라인</label> -->
						<h4 title="${item.reshTitle}">
                        	${fn:substring(item.reshTitle,0, 30)}<c:if test="${fn:length(item.reshTitle) > 30 }">...</c:if>
                    	</h4>
                	</div>
				</div>
				<div class="course_list test_custom">
					<div class="item">
							<ul class="info_disc">
								<c:if test="${item.creOperTypeCd ne 'S' }">
								<li><strong><spring:message code="lecture.title.research.duration"/></strong><meditag:dateformat type="8" delimeter="." property="${item.startDttm}"/>~<meditag:dateformat type="8" delimeter="." property="${item.endDttm}"/></li>
								</c:if>
								<li><strong><spring:message code="lecture.title.research.regyn"/></strong><meditag:codename code="${item.regYn}" category="OPEN_YN" /></li>
						</ul>
						
						<div class="button_group">
							<button type="button" class="primary" onclick="location.href='<c:url value="/lec/creCrsResh/rsltMain"/>?crsCreCd=${item.crsCreCd}&amp;reshSn=${item.reshSn}'"><spring:message code="button.view.result"/></button>
						</div>
					</div>
				</div>
			</div>
		</c:forEach>
		<c:if test="${empty creCrsReshList}">
			<li class="list-group-item"><spring:message code="lecture.message.research.nodata"/></li>
		</c:if>
	</form>

