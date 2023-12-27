<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>


							<table summary="<spring:message code="org.title.orginfo.manage"/>" style="width:100%" class="table table-bordered">
								<colgroup>
									<col style="width:50px;"/>
									<col style="width:auto"/>
									<col style="width:auto"/>
									<col style="width:80px;"/>
									<col style="width:80px;"/>
									<col style="width:75px"/>
									<col style="width:80px"/>
									<col style="width:80px"/>
								</colgroup>
								<thead>
									<tr>
										<th scope="col">No</th>
										<th scope="col">
										<c:choose>
											<c:when test="${fn:startsWith(vo.sortKey,'ORG_NM') == true}">
												<c:if test="${vo.sortKey eq 'ORG_NM_ASC'}">
											<a href="javascript:setSortKey('ORG_NM_DESC')"><spring:message code="org.title.orginfo.orgname"/> <i class="fa fa-sort-asc"></i></a>
												</c:if>
												<c:if test="${vo.sortKey eq 'ORG_NM_DESC'}">
											<a href="javascript:setSortKey('ORG_NM_ASC')"><spring:message code="org.title.orginfo.orgname"/> <i class="fa fa-sort-desc"></i></a>
												</c:if>
											</c:when>
											<c:otherwise>
											<a href="javascript:setSortKey('ORG_NM_ASC')"><spring:message code="org.title.orginfo.orgname"/> <i class="fa fa-sort" style="color:#ddd"></i></a>
											</c:otherwise>
										</c:choose>
										</th>
										<th scope="col"><spring:message code="org.title.orginfo.domain"/></th>
										<th scope="col">
										<c:choose>
											<c:when test="${fn:startsWith(vo.sortKey,'START_DTTM') == true}">
												<c:if test="${vo.sortKey eq 'START_DTTM_ASC'}">
											<a href="javascript:setSortKey('START_DTTM_DESC')"><spring:message code="org.title.orginfo.startdate"/> <i class="fa fa-sort-asc"></i></a>
												</c:if>
												<c:if test="${vo.sortKey eq 'START_DTTM_DESC'}">
											<a href="javascript:setSortKey('START_DTTM_ASC')"><spring:message code="org.title.orginfo.startdate"/> <i class="fa fa-sort-desc"></i></a>
												</c:if>
											</c:when>
											<c:otherwise>
											<a href="javascript:setSortKey('START_DTTM_ASC')"><spring:message code="org.title.orginfo.startdate"/> <i class="fa fa-sort" style="color:#ddd"></i></a>
											</c:otherwise>
										</c:choose>
										</th>
										<th scope="col">
										<c:choose>
											<c:when test="${fn:startsWith(vo.sortKey,'END_DTTM') == true}">
												<c:if test="${vo.sortKey eq 'END_DTTM_ASC'}">
											<a href="javascript:setSortKey('END_DTTM_DESC')"><spring:message code="org.title.orginfo.enddate"/> <i class="fa fa-sort-asc"></i></a>
												</c:if>
												<c:if test="${vo.sortKey eq 'END_DTTM_DESC'}">
											<a href="javascript:setSortKey('END_DTTM_ASC')"><spring:message code="org.title.orginfo.enddate"/> <i class="fa fa-sort-desc"></i></a>
												</c:if>
											</c:when>
											<c:otherwise>
											<a href="javascript:setSortKey('END_DTTM_ASC')"><spring:message code="org.title.orginfo.enddate"/> <i class="fa fa-sort" style="color:#ddd"></i></a>
											</c:otherwise>
										</c:choose>
										</th>
										<th scope="col"><spring:message code="common.title.useyn"/></th>
										<th scope="col"><spring:message code="common.title.edit"/></th>
										<th scope="col"><spring:message code="org.title.orginfo.homepage.manage"/></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="item" items="${orgList}" varStatus="status">
									<tr>
										<td class="text-right">${pageInfo.firstRecordIndex + status.count}</td>
										<td class="wordbreak">${item.orgNm}</td>
										<td>http://${item.rprstDomain} <a href="javascript:orgDomain('${item.orgCd}')"><i class="fa fa-cog"></i></a> <a href="http://${item.rprstDomain}" target="_blank"><i class="fa fa-eye"></i></a></td>
										<td class="text-center"><meditag:dateformat type="1" delimeter="." property="${item.startDttm}"/></td>
										<td class="text-center"><meditag:dateformat type="1" delimeter="." property="${item.endDttm}"/></td>
										<td class="text-center"><meditag:codename code="${item.useYn}" category="USE_YN"/></td>
										<td class="text-center"><a href="javascript:orgEdit('${item.orgCd}');" class="btn btn-primary btn-sm"><spring:message code="button.edit"/></a></td>
										<td class="text-center"><a href="javascript:orgManage('${item.orgCd}');" class="btn btn-info btn-sm"><spring:message code="button.manage"/></a></td>
									</tr>
									</c:forEach>
									<c:if test="${empty orgList}">
									<tr>
										<td colspan="8"><spring:message code="org.message.orginfo.nodata"/></td>
									</tr>
									</c:if>
								</tbody>
							</table>
							<meditag:paging pageInfo="${pageInfo}" funcName="listPageing"/>