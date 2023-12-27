<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="clibOlcPageList" value="${clibOlcPageList}"/>
<c:set var="pageInfo" value="${pageInfo}"/>
<c:set var="clibOlcPageVO" value="${clibOlcPageVO}"/>

							<c:forEach var="item" items="${clibOlcPageList}" varStatus="status">
							<div class="btn-group divPage" role="group" id="${item.pageCd }">
								<div class="input-group-addon"><i class="glyphicon glyphicon-sort"></i></div>
								<button type="button" class="btn btn-default btn-sm ct_txt listPage <c:if test="${clibOlcPageVO.pageCd eq item.pageCd }">active</c:if>" onclick="viewPage('${item.pageCd }')" title="${item.pageNm}"  id="list_${item.pageCd }">
									${status.count}. ${item.pageNm}
								</button>
								<div class="btn-group" role="group">
									<button id="btnGroupDrop1" type="button" class="btn btn-default btn-sm" onclick="editPage('${item.pageCd }')"><i class="glyphicon glyphicon-cog"></i></button>
								</div>
							</div>
							</c:forEach>
							<c:if test="${empty clibOlcPageList}">
								<spring:message code="common.message.nodata"/>
							</c:if>

							<%--
							<ul class="list-group" style="margin-bottom: 0px;">
							<c:forEach var="item" items="${clibOlcPageList}">
								<li class="list-group-item">
									${item.pageNm}
									<div class="text-right">
										<a href="javascript:viewPage('${item.pageCd }')" title="<spring:message code="button.view"/>"><i class="glyphicon glyphicon-eye-open" ></i></a>
										<a href="javascript:editPage('${item.pageCd }')" title="<spring:message code="button.edit"/>"><i class="glyphicon glyphicon-wrench" ></i></a>
										<c:choose>
											<c:when test="${status.first }">
										<font style="color:#dedede"><i class="glyphicon glyphicon-chevron-up"></i></font>
											</c:when>
											<c:otherwise>
										<a href="javascript:moveCnts('up','${item.pageCd }')" title="<spring:message code="button.up"/>"><i class="glyphicon glyphicon-chevron-up" ></i></a>
											</c:otherwise>
										</c:choose>
										<c:choose>
											<c:when test="${status.last }">
										<font style="color:#dedede"><i class="glyphicon glyphicon-chevron-down"></i></font>
											</c:when>
											<c:otherwise>
										<a href="javascript:moveCnts('down','${item.pageCd }')"  title="<spring:message code="button.down"/>"><i class="glyphicon glyphicon-chevron-down" ></i></a>
											</c:otherwise>
										</c:choose>
									</div>
								</li>
							</c:forEach>
							<c:if test="${empty clibOlcPageList}">
								<li class="list-group-item"><spring:message code="common.message.nodata"/></li>
							</c:if>
							</ul> --%>

