<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="courseList" value="${courseList}"/>
<c:set var="pageInfo" value="${pageInfo}"/>
<c:set var="courseVO" value="${courseVO}"/>

						<table summary="<spring:message code="course.title.course.manage"/>" class="table table-bordered wordbreak">
							<colgroup>
								<col style="width:80px"/>
								<col style="width:auto;min-width:90px;"/>
								<col style="width:auto"/>
								<col style="width:auto;min-width:90px;"/>
								<col style="width:auto;min-width:110px;"/>
								<col style="width:80px"/>
								<col style="width:80px"/>
								<col style="width:80px"/>
								<col style="width:75px"/>
							</colgroup>
							<thead>
								<tr>
									<th scope="col"><spring:message code="course.title.createcourse.creyear"/></th>
									<th scope="col">
									<c:choose>
										<c:when test="${fn:startsWith(courseVO.sortKey,'CRS_CTGR_NM') == true}">
											<c:if test="${courseVO.sortKey eq 'CRS_CTGR_NM_ASC'}">
										<a href="javascript:setSortKey('CRS_CTGR_NM_DESC')"><spring:message code="course.title.course.category"/> <i class="fa fa-sort-asc"></i></a>
											</c:if>
											<c:if test="${courseVO.sortKey eq 'CRS_CTGR_NM_DESC'}">
										<a href="javascript:setSortKey('CRS_CTGR_NM_ASC')"><spring:message code="course.title.course.category"/> <i class="fa fa-sort-desc"></i></a>
											</c:if>
										</c:when>
										<c:otherwise>
										<a href="javascript:setSortKey('CRS_CTGR_NM_ASC')"><spring:message code="course.title.course.category"/> <i class="fa fa-sort" style="color:#ddd"></i></a>
										</c:otherwise>
									</c:choose>
									</th>
									<th scope="col">
									<c:choose>
										<c:when test="${fn:startsWith(courseVO.sortKey,'CRS_NM') == true}">
											<c:if test="${courseVO.sortKey eq 'CRS_NM_ASC'}">
										<a href="javascript:setSortKey('CRS_NM_DESC')"><spring:message code="course.title.course.name"/> <i class="fa fa-sort-asc"></i></a>
											</c:if>
											<c:if test="${courseVO.sortKey eq 'CRS_NM_DESC'}">
										<a href="javascript:setSortKey('CRS_NM_ASC')"><spring:message code="course.title.course.name"/> <i class="fa fa-sort-desc"></i></a>
											</c:if>
										</c:when>
										<c:otherwise>
										<a href="javascript:setSortKey('CRS_NM_ASC')"><spring:message code="course.title.course.name"/> <i class="fa fa-sort" style="color:#ddd"></i></a>
										</c:otherwise>
									</c:choose>
									</th>
									<th scope="col">
									<c:choose>
										<c:when test="${fn:startsWith(courseVO.sortKey,'CRS_OPER_MTHD') == true}">
											<c:if test="${courseVO.sortKey eq 'CRS_OPER_MTHD_ASC'}">
										<a href="javascript:setSortKey('CRS_OPER_MTHD_DESC')"><spring:message code="course.title.course.edumthd"/> <i class="fa fa-sort-asc"></i></a>
											</c:if>
											<c:if test="${courseVO.sortKey eq 'CRS_OPER_MTHD_DESC'}">
										<a href="javascript:setSortKey('CRS_OPER_MTHD_ASC')"><spring:message code="course.title.course.edumthd"/> <i class="fa fa-sort-desc"></i></a>
											</c:if>
										</c:when>
										<c:otherwise>
										<a href="javascript:setSortKey('CRS_OPER_MTHD_ASC')"><spring:message code="course.title.course.edumthd"/><i class="fa fa-sort" style="color:#ddd"></i></a>
										</c:otherwise>
									</c:choose>
									</th>
									<th scope="col">
									<c:choose>
										<c:when test="${fn:startsWith(courseVO.sortKey,'CRS_OPER_TYPE') == true}">
											<c:if test="${courseVO.sortKey eq 'CRS_OPER_TYPE_ASC'}">
										<a href="javascript:setSortKey('CRS_OPER_TYPE_DESC')"><spring:message code="course.title.course.crstype"/> <i class="fa fa-sort-asc"></i></a>
											</c:if>
											<c:if test="${courseVO.sortKey eq 'CRS_OPER_TYPE_DESC'}">
										<a href="javascript:setSortKey('CRS_OPER_TYPE_ASC')"><spring:message code="course.title.course.crstype"/> <i class="fa fa-sort-desc"></i></a>
											</c:if>
										</c:when>
										<c:otherwise>
										<a href="javascript:setSortKey('CRS_OPER_TYPE_ASC')"><spring:message code="course.title.course.crstype"/><i class="fa fa-sort" style="color:#ddd"></i></a>
										</c:otherwise>
									</c:choose>
									</th>
									<th scope="col"><spring:message code="common.title.useyn"/></th>
									<th scope="col"><spring:message code="course.title.createcourse.create.cnt"/></th>
									<th scope="col"><spring:message code="course.title.createcourse.creterm"/></th>
									<th scope="col"><spring:message code="common.title.manage"/></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${courseList}" var="item" varStatus="status">
								<tr>
									<td class="text-center">${courseVO.creYear}</td>
									<td class="wordbreak">${item.crsCtgrNm}</td>
									<td class="wordbreak">${item.crsNm}</td>
									<td class="text-center"><meditag:codename code="${item.crsOperMthd}" category="CRS_OPER_MTHD" /></td>
									<td class="text-center"><meditag:codename code="${item.crsOperType}" category="CRS_OPER_TYPE" /></td>
									<td class="text-center"><meditag:codename code="${item.useYn}" category="USE_YN"/></td>
									<td class="text-right">${item.creCrsCnt}</td>
									<td class="text-center">
										<c:if test="${item.useYn eq 'Y'}">
										<div class="input-group" style="width:60px;">
											<input type="text" name="creCnt_${item.crsCd}" id="creCnt_${item.crsCd}" maxlength="2" style="text-align:right;width:40px;" class="inputNumber form-control input-sm" value="1" onfocus="this.select()"/>
											<span class="input-group-btn">
												<button class="btn btn-default btn-sm" onclick="javascript:crsCreWriteForm('${item.crsCd}', '${item.crsOperType }');"><spring:message code="button.plus"/></button>
											</span>
										</div>
										</c:if>
										<c:if test="${item.useYn ne 'Y'}">
										-
										</c:if>
									</td>
									<td class="text-center">
									<c:if test="${item.useYn eq 'Y'}">
										<button class="btn btn-info btn-sm" onclick="javascript:listCreateCourse('${item.crsCd}','${item.crsOperType }');"><spring:message code="button.manage"/></button>
									</c:if>
									<c:if test="${item.useYn ne 'Y'}">
										-
									</c:if>
									</td>
								</tr>
								</c:forEach>
								<c:if test="${empty courseList}">
								<tr>
									<td colspan="8"><spring:message code="common.message.nodata"/></td>
								</tr>
								</c:if>
							</tbody>
						</table>
						<meditag:paging pageInfo="${pageInfo}" funcName="listCourse"/>