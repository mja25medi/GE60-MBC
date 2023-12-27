<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>

										<table summary="<spring:message code="system.title.menu.manage.auth"/>" class="table table-bordered">
											<colgroup>
												<col style="width:auto;"/>
												<col style="width:100px"/>
												<col style="width:80px"/>
												<col style="width:50px"/>
											</colgroup>
											<thead>
												<tr>
													<th scope="col"><spring:message code="system.title.menu.name.auth"/></th>
													<th scope="col"><spring:message code="system.title.menu.code.auth"/></th>
													<th scope="col"><spring:message code="common.title.useyn"/></th>
													<th scope="col"><spring:message code="common.title.edit"/></th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${authGrpList}" var="item" varStatus="status">
												<tr>
													<c:set var="authGrpNm" value="${item.authGrpNm}"/>
													<c:forEach var="lang" items="${item.authGrpLangList}">
														<c:if test="${lang.langCd eq LOCALEKEY}">
															<c:set var="authGrpNm" value="${lang.authGrpNm}"/>
														</c:if>
													</c:forEach>
													<td class="wordbreak"><a href="javascript:setAuthGroupCode('${item.authGrpCd}','${authGrpNm}')" title='${authGrpNm}'>${authGrpNm}</a></td>
													<td class="text-center">${item.authGrpCd}</td>
													<td class="text-center"><meditag:codename code="${item.useYn}" category="USE_YN"/></td>
													<td class="text-center">
														<a class="btn btn-primary btn-sm" href="javascript:authGroupEdit('${item.authGrpCd}')" title="<spring:message code="button.edit"/>"><spring:message code="button.edit"/></a>
													</td>
												</tr>
												</c:forEach>
												<c:if test="${empty authGrpList}">
												<tr>
													<td colspan="4"><spring:message code="common.message.nodata"/></td>
												</tr>
												</c:if>
											</tbody>
										</table>