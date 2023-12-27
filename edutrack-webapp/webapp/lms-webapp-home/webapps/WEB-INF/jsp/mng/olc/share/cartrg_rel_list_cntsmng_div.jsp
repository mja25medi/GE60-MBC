<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="olcShareRelList" value="${olcShareRelList}"/>

						<ul class="list-group">
							<c:forEach var="item" items="${olcShareRelList}" varStatus="status">
							<li class="list-group-item" style="padding:5px 10px 5px 10px;">
								${item.cartrgNm} / ${item.cntsCnt}
								<div class="pull-right">
									<a href="javascript:selectShareCnts('${item.cartrgCd}','${item.cartrgNm}')" ><i class="glyphicon glyphicon-ok"></i></a>
									<a href="javascript:previewShareCnts('${item.cartrgCd}')" ><i class="glyphicon glyphicon-eye-open"></i></a>
								</div>
							</li>
							</c:forEach>
							<c:if test="${empty olcShareRelList}">
							<li class="list-group-item" style="padding:5px 10px 5px 10px;"><spring:message code="common.message.nodata"/></li>
							</c:if>
						</ul>