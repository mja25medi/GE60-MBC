<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>

							<ul class="list-group wordbreak">
							<c:forEach var="item" items="${cntsList}">
								<li class="list-group-item">
									<h5 class="list-group-item-heading">
										<c:if test="${item.shareDivCd eq 'KNOW' }">[<spring:message code="olc.title.knowledge.share"/>]</c:if>
										<c:if test="${item.shareDivCd eq 'CNTS' }">[<spring:message code="olc.title.contents.share"/>]</c:if>
										${item.cntsNm}
										<span class="pull-right" style="text-align: center;">
											<a href="javascript:preview('${item.cntsCd}')" class="btn btn-xs btn-default"><spring:message code="button.preview"/></a>
											<a href="javascript:delCnts('${item.cntsCd}')" class="btn btn-xs btn-default"><spring:message code="button.delete"/></a>
										</span>
									</h5>

									<spring:message code="common.title.regdate"/> : <meditag:dateformat type="8" delimeter="." property="${item.regDttm}"/>
									<br/><spring:message code="library.title.contents.ccl"/> : <meditag:orgcodename code="${item.cclCd}" category="CCL_CD" orgCd="${USER_ORGCD }"/> |
									<spring:message code="common.title.stats"/> :
									<c:if test="${item.shareDivCd eq 'CNTS' }">
										<c:choose>
											<c:when test="${item.shareReqStsCd eq '01' }"><font color="#FF0000"><meditag:codename code="${item.shareReqStsCd}" category="CNTS_SHARE_CD" /></font></c:when>
											<c:when test="${item.shareReqStsCd eq '03' }"><font color="#0000FF"><meditag:codename code="${item.shareReqStsCd}" category="CNTS_SHARE_CD" /></font></c:when>
											<c:when test="${item.shareReqStsCd eq '04' }"><font color="#000000"><meditag:codename code="${item.shareReqStsCd}" category="CNTS_SHARE_CD" /></font></c:when>
										</c:choose>
									</c:if>
									<c:if test="${item.shareDivCd eq 'KNOW' }">
										<c:choose>
											<c:when test="${item.shareReqStsCd eq '01' }"><font color="#FF0000"><meditag:codename code="${item.shareReqStsCd}" category="KNOW_SHARE_CD" /></font></c:when>
											<c:when test="${item.shareReqStsCd eq '03' }"><font color="#0000FF"><meditag:codename code="${item.shareReqStsCd}" category="KNOW_SHARE_CD" /></font></c:when>
											<c:when test="${item.shareReqStsCd eq '04' }"><font color="#000000"><meditag:codename code="${item.shareReqStsCd}" category="KNOW_SHARE_CD" /></font></c:when>
										</c:choose>
									</c:if>
								</li>
							</c:forEach>
							<c:if test="${empty cntsList}">
								<li class="list-group-item"><spring:message code="common.message.nodata"/></li>
							</c:if>
							</ul>

