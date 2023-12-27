<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
						<table summary="<spring:message code="system.title.page.manage"/>" class="table table-bordered wordbreak">
							<colgroup>
								<col style="width:80px"/>
								<col style="width:110px"/>
								<col style="width:auto"/>
								<col style="width:80px"/>
								<col style="width:50px"/>
							</colgroup>
							<thead>
								<tr>
									<th scope='col'><spring:message code="common.title.no"/></th>
									<th scope='col'>
										<c:choose>
											<c:when test="${fn:startsWith(vo.sortKey,'PAGE_CD') == true}">
												<c:if test="${vo.sortKey eq 'PAGE_CD_ASC'}">
											<a href="javascript:setSortKey('PAGE_CD_DESC')"><spring:message code="system.title.page.code"/> <i class="fa fa-sort-asc"></i></a>
												</c:if>
												<c:if test="${vo.sortKey eq 'PAGE_CD_DESC'}">
											<a href="javascript:setSortKey('PAGE_CD_ASC')"><spring:message code="system.title.page.code"/> <i class="fa fa-sort-desc"></i></a>
												</c:if>
											</c:when>
											<c:otherwise>
											<a href="javascript:setSortKey('PAGE_CD_ASC')"><spring:message code="system.title.page.code"/> <i class="fa fa-sort" style="color:#ddd"></i></a>
											</c:otherwise>
										</c:choose>
									</th>
									<th scope='col'>
										<c:choose>
											<c:when test="${fn:startsWith(vo.sortKey,'PAGE_NM') == true}">
												<c:if test="${vo.sortKey eq 'PAGE_NM_ASC'}">
											<a href="javascript:setSortKey('PAGE_NM_DESC')"><spring:message code="system.title.page.name"/> <i class="fa fa-sort-asc"></i></a>
												</c:if>
												<c:if test="${vo.sortKey eq 'PAGE_NM_DESC'}">
											<a href="javascript:setSortKey('PAGE_NM_ASC')"><spring:message code="system.title.page.name"/> <i class="fa fa-sort-desc"></i></a>
												</c:if>
											</c:when>
											<c:otherwise>
											<a href="javascript:setSortKey('PAGE_NM_ASC')"><spring:message code="system.title.page.name"/> <i class="fa fa-sort" style="color:#ddd"></i></a>
											</c:otherwise>
										</c:choose>
									</th>
									<th scope='col'>
										<c:choose>
											<c:when test="${fn:startsWith(vo.sortKey,'USE_YN') == true}">
												<c:if test="${vo.sortKey eq 'USE_YN_ASC'}">
											<a href="javascript:setSortKey('USE_YN_DESC')"><spring:message code="common.title.useyn"/> <i class="fa fa-sort-asc"></i></a>
												</c:if>
												<c:if test="${vo.sortKey eq 'USE_YN_DESC'}">
											<a href="javascript:setSortKey('USE_YN_ASC')"><spring:message code="common.title.useyn"/> <i class="fa fa-sort-desc"></i></a>
												</c:if>
											</c:when>
											<c:otherwise>
											<a href="javascript:setSortKey('USE_YN_ASC')"><spring:message code="common.title.useyn"/> <i class="fa fa-sort" style="color:#ddd"></i></a>
											</c:otherwise>
										</c:choose>
									</th>
									<th scope='col'><spring:message code="common.title.edit"/></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${pageList}" var="item" varStatus="status">
								<tr>
									<td class="text-right">${status.count}</td>
									<td>${item.pageCd}</td>
									<td>${item.pageNm}</td>
									<td class="text-center"><meditag:codename code="${item.useYn}" category="USE_YN" /></td>
									<td class="text-center">
										<a href="javascript:pageEdit('${item.orgCd}','${item.pageCd}');" class="btn btn-primary btn-sm"><spring:message code="button.edit" /> </a>
									</td>
								</tr>
								</c:forEach>
								<c:if test="${empty pageList}">
								<tr>
									<td colspan="5"><spring:message code="common.message.nodata"/></td>
								</tr>
								</c:if>
							</tbody>
						</table>
						<meditag:paging pageInfo="${pageInfo}" funcName="listPage"/>