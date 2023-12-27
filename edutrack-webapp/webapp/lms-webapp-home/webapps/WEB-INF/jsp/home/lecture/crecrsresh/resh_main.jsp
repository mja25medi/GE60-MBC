<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="creCrsReshList" value="${creCrsReshList}"/>
<c:url var="img_base" value="/img/home" />
				<div class="row">
					<div class="col-lg-12">
						<div class="text-right">
							<c:choose>
								<c:when test="${not empty researchBankList}">
									<a href="<c:url value="/lec/creCrsResh/addFormMain"/>" class="btn btn-primary btn-sm"><spring:message code="button.write.research"/></a>
								</c:when>
								<c:otherwise>
									<a href="javascript: alert('<spring:message code="lecture.message.research.noreg"/>')" class="btn btn-primary btn-sm"><spring:message code="button.write.research"/></a>
								</c:otherwise>
							</c:choose>

						</div>

						<form name="Search" id="Search" action="/lec/research">
						<ul class="list-group" style="padding-top: 10px;">
							<c:forEach var="item" items="${creCrsReshList}" varStatus="status">
							<li class="list-group-item" style="clear: both;">
								<div class="media wordbreak" style="min-height:55px;">
									<div class="media-body" style="float: left;">
										<h4 class="media-heading" title="${item.reshTitle }">
											${fn:substring(item.reshTitle,0, 30)}<c:if test="${fn:length(item.reshTitle) > 30 }">...</c:if>
										</h4>
										<p style="font-size:14px;color:gray;margin-top:10px;">
											<strong><spring:message code="lecture.title.research.duration"/></strong> : <meditag:dateformat type="8" delimeter="." property="${item.startDttm}"/>~<meditag:dateformat type="8" delimeter="." property="${item.endDttm}"/>&nbsp;|&nbsp;
											<strong><spring:message code="lecture.title.research.regyn"/></strong> : <meditag:codename code="${item.regYn}" category="REG_YN" />
										</p>
									</div>
									<div style="padding-left: 10px;display: table-cell;vertical-align: middle;float: right;">
										<%-- <a href="<c:url value="/CreCrsReshLecture.do"/>?cmd=editForm&amp;creCrsReshDTO.crsCreCd=${item.crsCreCd}&amp;creCrsReshDTO.reshSn=${item.reshSn}" class="btn btn-warning btn-sm"><spring:message code="button.edit"/></a> --%>
										<a href="<c:url value="/lec/creCrsResh/rsltMain"/>?crsCreCd=${item.crsCreCd}&amp;reshSn=${item.reshSn}" class="btn btn-primary btn-sm"><spring:message code="button.view.result"/></a>
									</div>
								</div>
								<div style="clear: both;"></div>
								</li>
							</c:forEach>
							<c:if test="${empty creCrsReshList}">
							<li class="list-group-item"><spring:message code="lecture.message.research.nodata"/></li>
							</c:if>
						</ul>
						</form>
					</div>
				</div>

