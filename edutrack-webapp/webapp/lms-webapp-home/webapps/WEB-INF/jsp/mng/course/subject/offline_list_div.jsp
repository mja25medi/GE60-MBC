<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="offlineSubjectList" value="${offlineSubjectList}"/>
<c:set var="pageInfo" value="${pageInfo}"/>
<c:set var="offlineSubjectVO" value="${offlineSubjectVO}" />

						<table summary="<spring:message code="course.title.subject.category.manage"/>" class="table table-bordered wordbreak">
							<colgroup>
								<col style="width:110px"/>
								<col style="width:250px"/>
								<col style="width:auto"/>
								<col style="width:auto"/>
								<col style="width:80px"/>
							</colgroup>
							<thead>
								<tr>
									<th scope="col">
									<c:choose>
										<c:when test="${fn:startsWith(offlineSubjectVO.sortKey,'SBJ_CD') == true}">
											<c:if test="${offlineSubjectVO.sortKey eq 'SBJ_CD_ASC'}">
										<a href="javascript:setSortKey('SBJ_CD_DESC')"><spring:message code="course.title.subject.code"/> <i class="fa fa-sort-asc"></i></a>
											</c:if>
											<c:if test="${offlineSubjectVO.sortKey eq 'SBJ_CD_DESC'}">
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
										<c:when test="${fn:startsWith(offlineSubjectVO.sortKey,'SBJ_CTGR_CD') == true}">
											<c:if test="${offlineSubjectVO.sortKey eq 'SBJ_CTGR_CD_ASC'}">
										<a href="javascript:setSortKey('SBJ_CTGR_CD_DESC')"><spring:message code="course.title.subject.category"/> <i class="fa fa-sort-asc"></i></a>
											</c:if>
											<c:if test="${offlineSubjectVO.sortKey eq 'SBJ_CTGR_CD_DESC'}">
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
										<c:when test="${fn:startsWith(offlineSubjectVO.sortKey,'SBJ_NM') == true}">
											<c:if test="${offlineSubjectVO.sortKey eq 'SBJ_NM_ASC'}">
										<a href="javascript:setSortKey('SBJ_NM_DESC')"><spring:message code="course.title.subject.name"/> <i class="fa fa-sort-asc"></i></a>
											</c:if>
											<c:if test="${offlineSubjectVO.sortKey eq 'SBJ_NM_DESC'}">
										<a href="javascript:setSortKey('SBJ_NM_ASC')"><spring:message code="course.title.subject.name"/> <i class="fa fa-sort-desc"></i></a>
											</c:if>
										</c:when>
										<c:otherwise>
										<a href="javascript:setSortKey('SBJ_NM_ASC')"><spring:message code="course.title.subject.name"/> <i class="fa fa-sort" style="color:#ddd"></i></a>
										</c:otherwise>
									</c:choose>
									</th>
									<th scope="col"><spring:message code="course.title.subject.contents.cnt"/></th>
									<th scope="col"><spring:message code="common.title.stats"/></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${offlineSubjectList}" var="item" varStatus="status">
								<tr>
									<td>${item.sbjCd}</td>
									<td class="wordbreak">${item.sbjCtgrNm}</td>
									<td class="wordbreak">
										${item.sbjNm}
										<a href="javascript:sbjEdit('${item.sbjCd}');" class=""><i class="fa fa-cog"></i></a>
									</td>
									<td class="text-right"><a href="#_none" onclick="subCntInfo('${item.sbjCd}')">${item.crsSubCnt + item.creCrsSubCnt + item.openCrsSubCnt}</a></td>
									<td class="text-center"><meditag:codename code="${item.useYn}" category="USE_YN"/></td>
								</tr>
								</c:forEach>
								<c:if test="${empty offlineSubjectList}">
								<tr>
									<td colspan="6"><spring:message code="course.message.subject.nodata"/></td>
								</tr>
								</c:if>
							</tbody>
						</table>
						<meditag:paging pageInfo="${pageInfo}" funcName="listSubject"/>