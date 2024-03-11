<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
										<table summary="<spring:message code="user.title.userinfo.manage"/>" class="table table-bordered wordbreak">
											<colgroup>
												<col style="width:30px"/>
												<col style="width:70px"/>
											<c:set var="colspan" value="5" />
											<c:forEach items="${userInfoCfgList}" var="item" varStatus="status">
											<c:if test="${item.viewYn eq 'Y' }">
												<col style="width:auto"/>
												<c:set var="colspan" value="${colspan + 1}" />
											</c:if>
											</c:forEach>
												<col style="width:100px"/>
												<col style="width:50px"/>
												<col style="width:70px"/>
											</colgroup>
											<thead>
												<tr>
													<th scope="col"><input type="checkbox" name="chkAll" id="chkAll" value="N" style="border:0" title="check all" onclick="checkAll()"/></th>
													<th scope="col"><spring:message code="common.title.no"/> </th>
													<c:forEach items="${userInfoCfgList}" var="cfgItem" varStatus="status">
														<c:if test="${cfgItem.viewYn eq 'Y' }">
															<c:choose>
																<c:when test="${cfgItem.fieldNm eq 'USERID' }">
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
																</c:when>
																<c:when test="${cfgItem.fieldNm eq 'USERNM' }">
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
																</c:when>
																<c:otherwise>
																	<th scope="col"><spring:message code="user.title.userinfo.manage.${fn:toLowerCase(cfgItem.fieldNm)}"/></th>
																</c:otherwise>
															</c:choose>
														</c:if>
													</c:forEach>
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
													<th scope="col">예외<br>아이디</th>													
													<th scope="col">관리</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${userInfoList}" var="item" varStatus="status">
													<tr>
														<td><input type='checkbox' name='sel' id='sel' value='${item.userNo}' style='border:0' title='<spring:message code="common.title.select"/>'></td>
														<td class="text-right">${pageInfo.totalRecordCount - ((pageInfo.currentPageNo- 1) * pageInfo.recordCountPerPage) - status.index }</td>
														<c:forEach items="${userInfoCfgList}" var="cfgItem" varStatus="status">
														<c:if test="${cfgItem.viewYn eq 'Y' }">
															<c:if test="${cfgItem.fieldNm eq 'USERNM' }"><td  class="text-center"><a href="#_none" onclick="crmOpen('${item.userNo}','${item.orgCd }')">${item.userNm}</a></td></c:if>
															<c:if test="${cfgItem.fieldNm eq 'USERID' }">
																<td class="text-center">${item.userId}</td>
															</c:if>
															<c:if test="${cfgItem.fieldNm eq 'SEX' }"><td class="text-center"><c:if test="${item.sexCd eq 'M'}"><spring:message code="user.title.userinfo.sex_m"/></c:if><c:if test="${item.sexCd eq 'F'}"><spring:message code="user.title.userinfo.sex_f"/></c:if></td></c:if>
															<c:if test="${cfgItem.fieldNm eq 'BIRTH' }"><td class="text-center"><meditag:dateformat type="1" delimeter="-" property="${item.birth}"/></td></c:if>
															<c:if test="${cfgItem.fieldNm eq 'EMAIL' }"><td class="text-center">${item.email}</td></c:if>
															<c:if test="${cfgItem.fieldNm eq 'MOBILENO' }"><td class="text-center">${item.mobileNo}</td></c:if>
															<c:if test="${cfgItem.fieldNm eq 'PHONENO' }"><td class="text-center">${item.homePhoneno}</td></c:if>
															<c:if test="${cfgItem.fieldNm eq 'MESSAGE' }">
																<td class="text-center"><c:if test="${item.emailRecv eq 'Y' }"><spring:message code="user.title.userinfo.receive.email"/></c:if>
																<c:if test="${item.emailRecv eq 'Y' && item.smsRecv eq 'Y' }">/</c:if>
																<c:if test="${item.smsRecv eq 'Y' }"><spring:message code="user.title.userinfo.receive.sms"/></c:if></td>
															</c:if>
															<c:if test="${cfgItem.fieldNm eq 'DEPT' }"><td class="text-center">${item.deptNm}</td></c:if>
															<c:if test="${cfgItem.fieldNm eq 'AREA' }"><td class="text-center">${item.areaNm}</td></c:if>
															<c:if test="${cfgItem.fieldNm eq 'USERDIV' }"><td class="text-center">${item.userDivNm}</td></c:if>
															<c:if test="${cfgItem.fieldNm eq 'DISABLILITY' }"><td class="text-center">${item.disablilityYn}</td></c:if>
															<c:if test="${cfgItem.fieldNm eq 'JOB' }"><td class="text-center">${item.jobNm}</td></c:if>
														</c:if>
														</c:forEach>
														<td class="text-center"><meditag:dateformat type="1" delimeter="." property="${item.regDttm}"/></td>
														<td class="text-center"><meditag:codename code="${item.userSts}" category="USER_STS"/></td>
														<td class="text-center"><input type='checkbox' name='exceptChk' style='border:0' title='심사용 예외 아이디' <c:if test="${item.exceptYn eq 'Y'}">checked</c:if> onclick="editExceptYn('${item.userNo}',this)"></td>
														<td class="text-center">
															<div class="btn-group">
																<button class="btn btn-primary btn-sm" onclick="crmOpen('${item.userNo}')">CRM</button>
																<c:if test="${mngType eq '|MANAGER'}">
																<button class="btn btn-primary btn-sm" onclick="userEdit('${item.userNo}')">정보수정</button>
																</c:if>
															</div>
														</td>
													</tr>
												</c:forEach>
												<c:if test="${empty userInfoList}">
												<tr>
													<td colspan="${colspan}"><spring:message code="user.message.userinfo.nodata"/></td>
												</tr>
												</c:if>
											</tbody>
										</table>

										<meditag:paging pageInfo="${pageInfo}" funcName="listPageing"/>