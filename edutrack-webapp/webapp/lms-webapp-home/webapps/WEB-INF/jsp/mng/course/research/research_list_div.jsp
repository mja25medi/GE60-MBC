<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="researchBankVO" value="${vo}"/>
<c:set var="researchBankListVO" value="${researchBankListVO}"/>
<c:set var="pageInfo" value="${pageInfo}"/>
							<table summary="<spring:message code="course.title.reshbank.manage"/>" class="table table-bordered wordbreak">
								<colgroup>
									<col style="width:60px"/>
									<col style="width:auto"/>
									<col style="width:80px"/>
									<col style="width:80px"/>
									<col style="width:120px"/>
									<col style="width:72px"/>
								</colgroup>
								<thead>
									<tr>
										<th scope="col"><spring:message code="common.title.no"/></th>
										<th scope="col">
										<c:choose>
											<c:when test="${fn:startsWith(researchBankVO.sortKey,'TITLE') == true}">
												<c:if test="${researchBankVO.sortKey eq 'TITLE_ASC'}">
											<a href="javascript:setSortKey('TITLE_DESC')"><spring:message code="course.title.reshbank.title"/> <i class="fa fa-sort-asc"></i></a>
												</c:if>
												<c:if test="${researchBankVO.sortKey eq 'TITLE_DESC'}">
											<a href="javascript:setSortKey('TITLE_ASC')"><spring:message code="course.title.reshbank.title"/> <i class="fa fa-sort-desc"></i></a>
												</c:if>
											</c:when>
											<c:otherwise>
											<a href="javascript:setSortKey('TITLE_ASC')"><spring:message code="course.title.reshbank.title"/> <i class="fa fa-sort" style="color:#ddd"></i></a>
											</c:otherwise>
										</c:choose>
										</th>
										<th scope="col">
										<c:choose>
											<c:when test="${fn:startsWith(researchBankVO.sortKey,'CNT') == true}">
												<c:if test="${researchBankVO.sortKey eq 'CNT_ASC'}">
											<a href="javascript:setSortKey('CNT_DESC')"><spring:message code="course.title.reshbank.qstn.cnt"/> <i class="fa fa-sort-asc"></i></a>
												</c:if>
												<c:if test="${researchBankVO.sortKey eq 'CNT_DESC'}">
											<a href="javascript:setSortKey('CNT_ASC')"><spring:message code="course.title.reshbank.qstn.cnt"/> <i class="fa fa-sort-desc"></i></a>
												</c:if>
											</c:when>
											<c:otherwise>
											<a href="javascript:setSortKey('CNT_ASC')"><spring:message code="course.title.reshbank.qstn.cnt"/> <i class="fa fa-sort" style="color:#ddd"></i></a>
											</c:otherwise>
										</c:choose>
										</th>
										<th scope="col"><spring:message code="common.title.useyn"/></th>
										<th scope="col">
										<c:choose>
											<c:when test="${fn:startsWith(researchBankVO.sortKey,'REGDATE') == true}">
												<c:if test="${researchBankVO.sortKey eq 'REGDATE_ASC'}">
											<a href="javascript:setSortKey('REGDATE_DESC')"><spring:message code="common.title.regdate"/> <i class="fa fa-sort-asc"></i></a>
												</c:if>
												<c:if test="${researchBankVO.sortKey eq 'REGDATE_DESC'}">
											<a href="javascript:setSortKey('REGDATE_ASC')"><spring:message code="common.title.regdate"/> <i class="fa fa-sort-desc"></i></a>
												</c:if>
											</c:when>
											<c:otherwise>
											<a href="javascript:setSortKey('REGDATE_ASC')"><spring:message code="common.title.regdate"/> <i class="fa fa-sort" style="color:#ddd"></i></a>
											</c:otherwise>
										</c:choose>
										</th>
										<th scope="col"><spring:message code="common.title.manage"/></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="item" items="${researchBankListVO}" varStatus="status">
									<tr>
										<td class="text-right">${status.count}</td>
										<td class="wordbreak">
											<a href="javascript:reshInfo('${item.reshSn}');">${item.reshTitle}</a>
											<a href="javascript:editForm('${item.reshSn}');" class=""><i class="fa fa-cog"></i></a>
										</td>
										<td class="text-right">${item.itemCnt}</td>
										<td class="text-center"><meditag:codename code="${item.regYn}" category="USE_YN"/> </td>
										<td class="text-center"><meditag:dateformat type="1" delimeter="." property="${item.regDttm}"/></td>
										<td class="text-center">
											<a href="javascript:manageForm('${item.reshSn}');" class="btn btn-info btn-sm"><spring:message code="button.manage.exambank"/> </a>
										</td>
									</tr>
									</c:forEach>
									<c:if test="${empty researchBankListVO}">
									<tr>
										<td colspan="6"><spring:message code="course.message.reshbank.nodata"/></td>
									</tr>
									</c:if>
								</tbody>
							</table>
							<meditag:paging pageInfo="${pageInfo}" funcName="listResearch"/>
