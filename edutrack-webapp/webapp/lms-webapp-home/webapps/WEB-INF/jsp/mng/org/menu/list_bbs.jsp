<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>

								<table summary="<spring:message code="org.title.orgmenu.manage"/>" class="table table-bordered">
									<colgroup>
										<col style="width:auto"/>
										<col style="width:80px"/>
										<col style="width:80px"/>
									</colgroup>
									<thead>
										<tr>
											<th scope="col"><spring:message code="board.title.bbs.info.bbsnm"/></th>
											<th scope="col"><spring:message code="common.title.useyn"/></th>
											<th scope="col"><spring:message code="common.title.select"/></th>
										</tr>
									</thead>
									<tbody>
									<c:forEach var="item" items="${bbsInfoList}" varStatus="status">
										<tr>
											<td>${item.bbsNm}</td>
											<td class="text-center"><meditag:codename code="${item.useYn}" category="USE_YN"/></td>
											<td class="text-center"><a href="javascript:selectBbs('${item.bbsNm}','${item.bbsCd}')" class="btn btn-primary btn-sm"><spring:message code="common.title.select" /></a></td>
										</tr>
									</c:forEach>
									<c:if test="${empty bbsInfoList}">
										<tr>
											<td colspan="3" class="text-center"><spring:message code="board.message.bbs.info.nodata"/></td>
										</tr>
									</c:if>
									</tbody>
								</table>
								<meditag:paging pageInfo="${pageInfo}" funcName="boardList"/>
