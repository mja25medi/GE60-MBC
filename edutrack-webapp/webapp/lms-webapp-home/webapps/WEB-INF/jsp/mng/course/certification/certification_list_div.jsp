<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="courseList" value="${courseList}"/>
<c:set var="pageInfo" value="${pageInfo}"/>
<c:set var="courseVO" value="${courseVO}"/>

						<table summary='<spring:message code="course.title.course.manage"/>' class="table table-bordered wordbreak">
							<colgroup>
								<col style="width:60px"/>
								<col style="width:auto"/>
								<col style="width:auto;min-width:110px;"/>
								<col style="width:auto;min-width:110px;"/>
								<col style="width:auto;min-width:90px;"/>
							</colgroup>
							<thead>
								<tr>
									<th scope="col"><spring:message code="common.title.no"/></th>
									<th scope="col">
										<c:choose>
											<c:when test="${fn:startsWith(courseVO.sortKey,'CRS_NM') == true}">
												<c:if test="${courseVO.sortKey eq 'CRS_NM_ASC'}">
													<a href="javascript:setSortKey('CRS_NM_DESC')">회차명<i class="fa fa-sort-asc"></i></a>
												</c:if>
												<c:if test="${courseVO.sortKey eq 'CRS_NM_DESC'}">
													<a href="javascript:setSortKey('CRS_NM_ASC')">회차명<i class="fa fa-sort-desc"></i></a>
												</c:if>
											</c:when>
											<c:otherwise>
												<a href="javascript:setSortKey('CRS_NM_ASC')">회차명<i class="fa fa-sort" style="color:#ddd"></i></a>
											</c:otherwise>
										</c:choose>
									</th>
									<th scope="col">운영기간</th>
									<th scope="col">자격증 신청 기간</th>
									<th scope="col">개설 과정 수</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${courseList}" var="item" varStatus="status">
									<tr>
										<td class="text-center">${status.count}</td>
										<td class="text-center">
											${item.crsNm}<a href="javascript:crsEditForm('${item.crsCd}');"><i class="fa fa-cog"></i></a>
										</td>
										<td class="text-center">
											<c:if test="${item.enrlStartDttm ne ''}">
												<meditag:dateformat type="1" delimeter="." property="${item.enrlStartDttm}"/>~<meditag:dateformat type="1" delimeter="." property="${item.enrlEndDttm}"/>
											</c:if>
										</td>
										<td class="text-center">
											<c:if test="${item.scoreOpenStartDttm ne ''}">
												<meditag:dateformat type="1" delimeter="." property="${item.scoreOpenStartDttm}"/>~<meditag:dateformat type="1" delimeter="." property="${item.scoreOpenEndDttm}"/>
											</c:if>
										</td>
										<td class="text-center">
											${item.creCount }
										</td>
									</tr>
								</c:forEach>
								<c:if test="${empty courseList}">
								<tr>
									<td colspan="8" class="text-center">현재 검색한 검색조건으로 조회된 검색결과가 없습니다.</td>
								</tr>
								</c:if>
							</tbody>
						</table>
						<meditag:paging pageInfo="${pageInfo}" funcName="listCourse"/>
