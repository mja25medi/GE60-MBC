<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="clibShareMediaCntsList" value="${clibShareMediaCntsList}"/>

							<ul class="list-group">
							<c:forEach var="item" items="${clibShareMediaCntsList}">
								<li class="list-group-item">
									${fn:substring(item.cntsNm,0, 45)}<c:if test="${fn:length(item.cntsNm) > 45 }">...</c:if>
									<span class="pull-right" style="text-align: center;">
										<a href="javascript:previewShareCnts('${item.cntsCd}')" class="btn btn-xs btn-default"><spring:message code="button.preview"/></a>
										<a href="javascript:selectShareCnts('${item.cntsCd}','${item.cntsNm}')" class="btn btn-xs btn-default"><spring:message code="button.choice"/></a>
										<c:if test="${sbjType eq 'ON'}">
										<a href="javascript:selectMobileShareCnts('${item.cntsCd}','${item.cntsNm}')" class="btn btn-xs btn-default">모바일<spring:message code="button.choice"/></a>
										</c:if>
									</span>
									<div style="clear:both;"></div>
								</li>
							</c:forEach>
							<c:if test="${empty clibShareMediaCntsList}">
								<li class="list-group-item"><spring:message code="common.message.nodata"/></li>
							</c:if>
							</ul>

							<script type="text/javascript">
								$(document).ready(function() {


								});
							</script>

