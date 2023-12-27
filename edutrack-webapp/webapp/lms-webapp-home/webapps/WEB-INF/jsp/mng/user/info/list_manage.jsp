<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
										<table summary="<spring:message code="user.title.userinfo.manage"/>" class="table table-bordered wordbreak">
											<colgroup>
												<col style="width:30px"/>
												<col style="width:50px"/>
												<col style="width:100px"/>
												<col style="width:100px"/>
												<col style="width:100px"/>
												<col style="width:200px"/>
												<col style="width:100px"/>
												<col style="width:50px"/>
											</colgroup>
											<thead>
												<tr>
													<th scope="col"><input type="checkbox" name="chkAll" id="chkAll" value="N" style="border:0" title="check all" onclick="checkAll()"/></th>
													<th scope="col"><spring:message code="common.title.no"/> </th>
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
													<th scope="col"><spring:message code="user.title.userinfo.mobileno"/></th>
													<th scope="col"><spring:message code="user.title.userinfo.email"/></th>
													<th scope="col">
														<c:choose>
															<c:when test="${fn:startsWith(vo.sortKey,'REGDATE') == true}">
																<c:if test="${vo.sortKey eq 'REGDATE_ASC'}">
																	<a href="javascript:setSortKey('REGDATE_DESC')"><spring:message code="common.title.regdate"/> <i class="fa fa-sort-asc"></i></a>
																</c:if>
																<c:if test="${vo.sortKey eq 'REGDATE_DESC'}">
																	<a href="javascript:setSortKey('REGDATE_ASC')"><spring:message code="common.title.regdate"/> <i class="fa fa-sort-desc"></i></a>
																</c:if>
															</c:when>
															<c:otherwise>
																<a href="javascript:setSortKey('REGDATE_ASC')"><spring:message code="common.title.regdate"/> <i class="fa fa-sort" style="color:#ddd"></i></a>
															</c:otherwise>
														</c:choose>
													</th>
													<th scope="col">
														<c:choose>
															<c:when test="${fn:startsWith(vo.sortKey,'USER_STS') == true}">
																<c:if test="${vo.sortKey eq 'USER_STS_ASC'}">
																	<a href="javascript:setSortKey('USER_STS_DESC')"><spring:message code="user.title.userinfo.stats"/> <i class="fa fa-sort-asc"></i></a>
																</c:if>
																<c:if test="${vo.sortKey eq 'USER_STS_DESC'}">
																	<a href="javascript:setSortKey('RUSER_STS_ASC')"><spring:message code="user.title.userinfo.stats"/> <i class="fa fa-sort-desc"></i></a>
																</c:if>
															</c:when>
															<c:otherwise>
																<a href="javascript:setSortKey('USER_STS_ASC')"><spring:message code="user.title.userinfo.stats"/> <i class="fa fa-sort" style="color:#ddd"></i></a>
															</c:otherwise>
														</c:choose>
													</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${userInfoList}" var="item" varStatus="status">
												<tr>
													<td class="text-center"><input type='checkbox' name='sel' id='sel' value='${item.userNo}' style='border:0' title='<spring:message code="common.title.select"/>'></td>
													<td class="text-right">${pageInfo.totalRecordCount - ((pageInfo.currentPageNo- 1) * pageInfo.recordCountPerPage) - status.index }</td>
													<td>
														<c:choose>
															<c:when test="${not empty item.userId}">${item.userId }<a href="javascript:userEdit('${item.userNo}','${item.orgCd }');"><i class="fa fa-cog"></i></c:when>
															<c:otherwise>-</c:otherwise>
														</c:choose>
													</td>
													<td><a href="#_none" onclick="userInfo('${item.userNo}','${item.orgCd }')">${item.userNm}</a></td>
													<td>${item.mobileNo}</td>
													<td>
														<c:choose>
															<c:when test="${empty item.email}">-</c:when>
															<c:otherwise>${item.email}</c:otherwise>
														</c:choose>
													</td>
													<td class="text-center">
														<c:choose>
															<c:when test="${not empty item.regDttm}">${fn:substring(item.regDttm, 0, 4) }.${fn:substring(item.regDttm, 4, 6) }.${fn:substring(item.regDttm, 6, 8) }</c:when>
															<c:otherwise>-</c:otherwise>
														</c:choose>
													</td>
													<td class="text-center">
														<meditag:codename code="${item.userSts}" category="USER_STS"/>
													</td>
												</tr>
												</c:forEach>
											</tbody>
										</table>

										<meditag:paging pageInfo="${pageInfo}" funcName="listPageing"/>