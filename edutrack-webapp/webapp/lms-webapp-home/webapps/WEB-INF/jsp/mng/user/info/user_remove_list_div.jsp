<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/page_init.jsp" %>
<c:set var="userInfoList" value="${userInfoList}"/>
<c:set var="pageInfo" value="${pageInfo}"/>

								<table summary="<spring:message code="user.title.userinfo.manage"/>" class="table table-bordered wordbreak">
									<colgroup>
										<col style="width:80px"/>
										<col style="width:auto"/>
										<col style="width:auto"/>
										<col style="width:auto"/>
										<col style="width:11%"/>
										<col style="width:11%"/>
										<col style="width:11%"/>
										<col style="width:80px"/>
										<col style="width:50px"/>
									</colgroup>
									<thead>
										<tr>
											<th scope="col"><spring:message code="common.title.no"/></th>
											<th scope="col">
											<c:choose>
												<c:when test="${fn:startsWith(vo.sortKey,'USER_ID') == true}">
													<c:if test="${vo.sortKey eq 'USER_ID_ASC'}">
												<a href="javascript:setSortKey('USER_ID_DESC')"><spring:message code="user.title.userinfo.userid"/> <i class="fa fa-sort-asc"></i></a>
													</c:if>
													<c:if test="${vo.sortKey eq 'USER_ID_DESC'}">
												<a href="javascript:setSortKey('USER_ID_ASC')"><spring:message code="user.title.userinfo.userid"/> <i class="fa fa-sort-desc"></i></a>
													</c:if>
												</c:when>
												<c:otherwise>
												<a href="javascript:setSortKey('USER_ID_ASC')"><spring:message code="user.title.userinfo.userid"/> <i class="fa fa-sort" style="color:#ddd"></i></a>
												</c:otherwise>
											</c:choose>
											</th>
											<th scope="col">
											<c:choose>
												<c:when test="${fn:startsWith(vo.sortKey,'USER_NM') == true}">
													<c:if test="${vo.sortKey eq 'USER_NM_ASC'}">
												<a href="javascript:setSortKey('USER_NM_DESC')"><spring:message code="user.title.userinfo.name"/> <i class="fa fa-sort-asc"></i></a>
													</c:if>
													<c:if test="${vo.sortKey eq 'USER_NM_DESC'}">
												<a href="javascript:setSortKey('USER_NM_ASC')"><spring:message code="user.title.userinfo.name"/> <i class="fa fa-sort-desc"></i></a>
													</c:if>
												</c:when>
												<c:otherwise>
												<a href="javascript:setSortKey('USER_NM_ASC')"><spring:message code="user.title.userinfo.name"/> <i class="fa fa-sort" style="color:#ddd"></i></a>
												</c:otherwise>
											</c:choose>
											</th>
											<th scope="col"><spring:message code="user.title.userinfo.userno"/></th>
											<th scope="col"><spring:message code="user.title.userinfo.date.reg"/></th>
											<th scope="col"><spring:message code="user.title.userinfo.date.confirm"/></th>
											<th scope="col"><spring:message code="user.title.userinfo.date.out"/></th>
											<th scope="col"><spring:message code="user.title.userinfo.stats"/></th>
											<th scope="col"><spring:message code="common.title.edit"/></th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${userInfoList}" var="item" varStatus="status">
										<tr>
											<td class="text-right">${pageInfo.totalRecordCount - ((pageInfo.currentPageNo - 1 ) * pageInfo.recordCountPerPage + status.index)}</td>
											<td>${item.userId}</td>
											<td><a href="#_none" onclick="userInfo('${item.userNo}')">${item.userNm}</a></td>
											<td>${item.userNo}</td>
											<td class="text-center"><meditag:dateformat type="8" delimeter="." property="${item.aplcDttm}"/></td>
											<td class="text-center"><meditag:dateformat type="8" delimeter="." property="${item.aprvDttm}"/></td>
											<td class="text-center"><meditag:dateformat type="8" delimeter="." property="${item.secedeDttm}"/></td>
											<td class="text-center"><meditag:codename code="${item.userSts}" category="USER_STS"/></td>
											<td class="text-center">
												<a href="javascript:userEdit('${item.userNo}')" class="btn btn-primary btn-sm"><spring:message code="button.edit"/></a>
											</td>
										</tr>
										</c:forEach>
										<c:if test="${empty userInfoList}">
										<tr>
											<td colspan="9"><spring:message code="user.message.userinfo.nodata"/></td>
										</tr>
										</c:if>
									</tbody>
								</table>
								<meditag:paging pageInfo="${pageInfo}" funcName="listPageing"/>
