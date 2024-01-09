<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="onlineSubjectList" value="${onlineSubjectList}"/>
<c:set var="pageInfo" value="${pageInfo}"/>
<c:set var="onlineSubjectVO" value="${vo}" />

										<table summary="<spring:message code="course.title.subject.category.manage"/>" class="table table-bordered wordbreak">
											<colgroup>
												<col style="width:110px"/>
												<col style="width:250px"/>
												<col style="width:auto"/>
												<col style="width:100px"/>
												<col style="width:70px"/>
												<col style="width:80px"/>
												<col style="width:100px"/>
											</colgroup>
											<thead>
											<tr>
												<th scope="col">
												<c:choose>
													<c:when test="${fn:startsWith(onlineSubjectVO.sortKey,'SBJ_CD') == true}">
														<c:if test="${onlineSubjectVO.sortKey eq 'SBJ_CD_ASC'}">
													<a href="javascript:setSortKey('SBJ_CD_DESC')"><spring:message code="course.title.subject.code"/> <i class="fa fa-sort-asc"></i></a>
														</c:if>
														<c:if test="${onlineSubjectVO.sortKey eq 'SBJ_CD_DESC'}">
													<a href="javascript:setSortKey('SBJ_CD_ASC')"><spring:message code="course.title.subject.code"/> <i class="fa fa-sort-desc"></i></a>
														</c:if>
													</c:when>
													<c:otherwise>
													<a href="javascript:setSortKey('SBJ_CD_ASC')"><spring:message code="course.title.subject.code"/> <i class="fa fa-sort" style="color:#ddd"></i></a>
													</c:otherwise>
												</c:choose>
												</th>
												<th scope="col">
												<c:choose>
													<c:when test="${fn:startsWith(onlineSubjectVO.sortKey,'SBJ_CTGR_CD') == true}">
														<c:if test="${onlineSubjectVO.sortKey eq 'SBJ_CTGR_CD_ASC'}">
													<a href="javascript:setSortKey('SBJ_CTGR_CD_DESC')"><spring:message code="course.title.subject.category"/> <i class="fa fa-sort-asc"></i></a>
														</c:if>
														<c:if test="${onlineSubjectVO.sortKey eq 'SBJ_CTGR_CD_DESC'}">
													<a href="javascript:setSortKey('SBJ_CTGR_CD_ASC')"><spring:message code="course.title.subject.category"/> <i class="fa fa-sort-desc"></i></a>
														</c:if>
													</c:when>
													<c:otherwise>
													<a href="javascript:setSortKey('SBJ_CTGR_CD_ASC')"><spring:message code="course.title.subject.category"/> <i class="fa fa-sort" style="color:#ddd"></i></a>
													</c:otherwise>
												</c:choose>
												</th>
												<th scope="col">
												<c:choose>
													<c:when test="${fn:startsWith(onlineSubjectVO.sortKey,'SBJ_NM') == true}">
														<c:if test="${onlineSubjectVO.sortKey eq 'SBJ_NM_ASC'}">
													<a href="javascript:setSortKey('SBJ_NM_DESC')"><spring:message code="course.title.subject.name"/> <i class="fa fa-sort-asc"></i></a>
														</c:if>
														<c:if test="${onlineSubjectVO.sortKey eq 'SBJ_NM_DESC'}">
													<a href="javascript:setSortKey('SBJ_NM_ASC')"><spring:message code="course.title.subject.name"/> <i class="fa fa-sort-desc"></i></a>
														</c:if>
													</c:when>
													<c:otherwise>
													<a href="javascript:setSortKey('SBJ_NM_ASC')"><spring:message code="course.title.subject.name"/> <i class="fa fa-sort" style="color:#ddd"></i></a>
													</c:otherwise>
												</c:choose>
												</th>
												<th scope="col">과목구분</th>
												<th scope="col">사용</th>
												<th scope="col"><spring:message code="course.title.subject.contents.cnt"/></th>
												<th scope="col"><spring:message code="course.title.contents.manage"/></th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${onlineSubjectList}" var="item" varStatus="status">
											<tr>
												<td class="text-right">${item.sbjCd}</td>
												<td class="wordbreak">${item.sbjCtgrNm}</td>
												<td class="wordbreak">
													${item.sbjNm}
													<a href="javascript:sbjEdit('${item.sbjCd}');" ><i class="fa fa-cog"></i></a>
												</td>
												<td class="text-center">${item.sbjType}</td>
												<td class="text-center">${item.useYn}</td>
												<td class="text-center">${item.contentsCnt}</td>
												<td class="text-center">
													<a href="javascript:contentsMgr('${item.sbjCd}');" class="btn btn-info btn-sm"><spring:message code="button.manage.contents"/> </a>
												</td>
											</tr>
											</c:forEach>
											<c:if test="${empty onlineSubjectList}">
											<tr>
												<td colspan="8"><spring:message code="course.message.subject.nodata"/></td>
											</tr>
											</c:if>
										</tbody>
									</table>
									<meditag:paging pageInfo="${pageInfo}" funcName="listSubject"/>