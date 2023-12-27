<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="olcCntsList" value="${olcCntsList}"/>


						<ul class="list-group">
							<c:forEach var="item" items="${olcCntsList}" varStatus="status">
							<li class="list-group-item cnts-item wordbreak" id="item-${item.cntsCd}">
								${status.count}. ${item.cntsNm}
								<div class="text-right">
									<a href="javascript:viewCnts('${item.cntsCd }')" title="<spring:message code="button.view"/>"><i class="glyphicon glyphicon-eye-open" ></i></a>
									<a href="javascript:editCnts('${item.cntsCd }')" title="<spring:message code="button.edit"/>"><i class="glyphicon glyphicon-wrench" ></i></a>
									<c:choose>
										<c:when test="${status.first }">
									<font style="color:#dedede"><i class="glyphicon glyphicon-chevron-up"></i></font>
										</c:when>
										<c:otherwise>
									<a href="javascript:moveCnts('up','${item.cntsCd }')" title="<spring:message code="button.up"/>"><i class="glyphicon glyphicon-chevron-up" ></i></a>
										</c:otherwise>
									</c:choose>
									<c:choose>
										<c:when test="${status.last }">
									<font style="color:#dedede"><i class="glyphicon glyphicon-chevron-down"></i></font>
										</c:when>
										<c:otherwise>
									<a href="javascript:moveCnts('down','${item.cntsCd }')"  title="<spring:message code="button.down"/>"><i class="glyphicon glyphicon-chevron-down" ></i></a>
										</c:otherwise>
									</c:choose>

								</div>
							</li>
							</c:forEach>
						</ul>
