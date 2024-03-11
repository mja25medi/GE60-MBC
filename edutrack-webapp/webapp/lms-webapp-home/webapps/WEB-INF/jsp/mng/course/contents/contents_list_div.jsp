<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="contentsList" value="${contentsList}"/>

								<table summary="<spring:message code="org.title.orgmenu.manage"/>" class="table table-bordered wordbreak">
									<colgroup>
										<col style="width:auto"/>
										<col style="width:200px"/>
									</colgroup>
									<thead>
										<tr>
											<th scope="col">차시명</th>
											<th scope="col">관리</th>
										</tr>
									</thead>
									<tbody>
									<c:forEach var="item" items="${contentsList}" varStatus="status">
										<c:set var="marginleft" value="0" />
										<c:if test="${item.unitLvl == 1}"><c:set var="marginleft" value="15" /></c:if>
										<c:if test="${item.unitLvl == 2}"><c:set var="marginleft" value="30" /></c:if>
										<c:set var="ctgr_icon" value="fa fa-file fa-fw" />
										<c:if test="${item.unitLvl == 1}"><c:set var="ctgr_icon" value="fa fa-folder-open fa-fw" /></c:if>
										<tr>
											<td style="padding-left:${marginleft}px;">
												<i class="${ctgr_icon}"></i> ${item.unitNm}
												<a href="javascript:cntsEditForm('${item.unitCd}')" class=""><i class="fa fa-cog"></i></a>
											</td>
											<td class="text-center">
												<c:if test="${item.unitLvl == 1 && item.prgrChkType == 'PAGE'}">
												<a href="javascript:cntsWrite('2' ,'${item.unitCd}')" class="btn btn-primary btn-sm">페이지 추가</a>
												</c:if>
												<c:if test="${item.unitLvl == 2}">
												-
												</c:if>
											</td>
										</tr>
									</c:forEach>
									<c:if test="${empty contentsList}">
										<tr>
											<td colspan="2">등록된 차시가 없습니다.</td>
										</tr>
									</c:if>
									</tbody>
								</table>
