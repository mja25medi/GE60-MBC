<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>


			<table summary="<spring:message code="log.title.course.result"/>" style="width:100%" class="table table-bordered">
			<colgroup>
				<col style="width:100px"/>
				<col style="width:auto"/>
				<col style="width:100px"/>
				<col style="width:100px"/>
				<col style="width:60px"/>
				<col style="width:60px"/>
				<col style="width:60px"/>
				<col style="width:60px"/>
				<col style="width:60px"/>
				<col style="width:60px"/>
				<col style="width:80px"/>
				<col style="width:120px"/>
			</colgroup>
			<thead>
				<tr>
					<th scope="col" rowspan="2" style="border-right:1px solid #dadada">
					<c:choose>
						<c:when test="${fn:startsWith(eduCourseStatusVO.sortKey,'CRE_YEAR') == true}">
							<c:if test="${eduCourseStatusVO.sortKey eq 'CRE_YEAR_ASC'}">
						<a href="javascript:setSortKey('CRE_YEAR_DESC')"><spring:message code="course.title.createcourse.creyear"/> <i class="fa fa-sort-asc"></i></a>
							</c:if>
							<c:if test="${eduCourseStatusVO.sortKey eq 'CRE_YEAR_DESC'}">
						<a href="javascript:setSortKey('CRE_YEAR_ASC')"><spring:message code="course.title.createcourse.creyear"/> <i class="fa fa-sort-desc"></i></a>
							</c:if>
						</c:when>
						<c:otherwise>
						<a href="javascript:setSortKey('CRE_YEAR_ASC')"><spring:message code="course.title.createcourse.creyear"/> <i class="fa fa-sort" style="color:#ddd"></i></a>
						</c:otherwise>
					</c:choose>
					</th>
					<th scope="col" rowspan="2" style="border-right:1px solid #dadada">
					<c:choose>
						<c:when test="${fn:startsWith(eduCourseStatusVO.sortKey,'CRS_NM') == true}">
							<c:if test="${eduCourseStatusVO.sortKey eq 'CRS_NM_ASC'}">
						<a href="javascript:setSortKey('CRS_NM_DESC')"><spring:message code="course.title.course.name"/> <i class="fa fa-sort-asc"></i></a>
							</c:if>
							<c:if test="${eduCourseStatusVO.sortKey eq 'CRS_NM_DESC'}">
						<a href="javascript:setSortKey('CRS_NM_ASC')"><spring:message code="course.title.course.name"/> <i class="fa fa-sort-desc"></i></a>
							</c:if>
						</c:when>
						<c:otherwise>
						<a href="javascript:setSortKey('CRS_NM_ASC')"><spring:message code="course.title.course.name"/> <i class="fa fa-sort" style="color:#ddd"></i></a>
						</c:otherwise>
					</c:choose>
					</th>
					<th scope="col" rowspan="2" style="border-right:1px solid #dadada">
					<c:choose>
						<c:when test="${fn:startsWith(eduCourseStatusVO.sortKey,'CRS_OPER_MTHD') == true}">
							<c:if test="${eduCourseStatusVO.sortKey eq 'CRS_OPER_MTHD_ASC'}">
						<a href="javascript:setSortKey('CRS_OPER_MTHD_DESC')"><spring:message code="course.title.course.edumthd"/> <i class="fa fa-sort-asc"></i></a>
							</c:if>
							<c:if test="${eduCourseStatusVO.sortKey eq 'CRS_OPER_MTHD_DESC'}">
						<a href="javascript:setSortKey('CRS_OPER_MTHD_ASC')"><spring:message code="course.title.course.edumthd"/> <i class="fa fa-sort-desc"></i></a>
							</c:if>
						</c:when>
						<c:otherwise>
						<a href="javascript:setSortKey('CRS_OPER_MTHD_ASC')"><spring:message code="course.title.course.edumthd"/><i class="fa fa-sort" style="color:#ddd"></i></a>
						</c:otherwise>
					</c:choose>
					</th>
					<th scope="col" rowspan="2" style="border-right:1px solid #dadada">
					<c:choose>
						<c:when test="${fn:startsWith(eduCourseStatusVO.sortKey,'CRS_OPER_TYPE') == true}">
							<c:if test="${eduCourseStatusVO.sortKey eq 'CRS_OPER_TYPE_ASC'}">
						<a href="javascript:setSortKey('CRS_OPER_TYPE_DESC')"><spring:message code="course.title.course.crstype"/> <i class="fa fa-sort-asc"></i></a>
							</c:if>
							<c:if test="${eduCourseStatusVO.sortKey eq 'CRS_OPER_TYPE_DESC'}">
						<a href="javascript:setSortKey('CRS_OPER_TYPE_ASC')"><spring:message code="course.title.course.crstype"/> <i class="fa fa-sort-desc"></i></a>
							</c:if>
						</c:when>
						<c:otherwise>
						<a href="javascript:setSortKey('CRS_OPER_TYPE_ASC')"><spring:message code="course.title.course.crstype"/><i class="fa fa-sort" style="color:#ddd"></i></a>
						</c:otherwise>
					</c:choose>
					</th>
					<th scope="col" colspan="2" style="border-right:1px solid #dadada"><spring:message code="log.title.course.result.plan"/></th>
					<th scope="col" colspan="2" style="border-right:1px solid #dadada"><spring:message code="log.title.course.result.performance"/></th>
					<th scope="col" colspan="2" style="border-right:1px solid #dadada"><spring:message code="log.title.course.result.ratio"/></th>
					<th scope="col" rowspan="2" style="border-right:1px solid #dadada"><spring:message code="log.title.course.result.yearcnt"/></th>
					<th scope="col" rowspan="2"><spring:message code="log.title.course.result.proceeds"/></th>
				</tr>
				<tr>
					<th scope="col" style="border-right:1px solid #dadada;border-top:1px solid #dadada;"><spring:message code="log.title.course.result.cnt"/></th>
					<th scope="col" style="border-right:1px solid #dadada;border-top:1px solid #dadada;"><spring:message code="log.title.course.result.user"/></th>
					<th scope="col" style="border-right:1px solid #dadada;border-top:1px solid #dadada;"><spring:message code="log.title.course.result.cnt"/></th>
					<th scope="col" style="border-right:1px solid #dadada;border-top:1px solid #dadada;"><spring:message code="log.title.course.result.user"/></th>
					<th scope="col" style="border-right:1px solid #dadada;border-top:1px solid #dadada;"><spring:message code="log.title.course.result.cnt"/></th>
					<th scope="col" style="border-right:1px solid #dadada;border-top:1px solid #dadada;"><spring:message code="log.title.course.result.user"/></th>
				</tr>
			</thead>
			<tbody>
			<c:set var="totPlanCreCnt" value="0"/>
			<c:set var="totPlanEnrlCnt" value="0"/>
			<c:set var="totCreCnt" value="0"/>
			<c:set var="totEnrlCnt" value="0"/>
			<c:set var="totCreRatio" value="0"/>
			<c:set var="totEduRatio" value="0"/>
			<c:set var="totEnrlCnt" value="0"/>
			<c:set var="totEduPrice" value="0"/>

			<c:if test="${not empty eduCourseStatusList}">
			<c:forEach items="${eduCourseStatusList}" var="item" varStatus="status">
				<c:set var="totPlanCreCnt" value="${totPlanCreCnt + item.planCreCnt}"/>
				<c:set var="totPlanEnrlCnt" value="${totPlanEnrlCnt + item.planEnrlCnt}"/>
				<c:set var="totCreCnt" value="${totCreCnt + item.creCnt}"/>
				<c:set var="totEnrlCnt" value="${totEnrlCnt + item.enrlCnt}"/>
				<c:set var="totCreRatio" value="${totCreRatio + item.creRatio}"/>
				<c:set var="totEduRatio" value="${totEduRatio + item.eduRatio}"/>
				<c:set var="totEnrlCnt" value="${totEnrlCnt + item.enrlCnt}"/>
				<c:set var="totEduPrice" value="${totEduPrice + item.eduPrice}"/>

				<tr>
					<td>${item.creYear}<spring:message code="common.title.year"/></td>
					<td class="wordbreak" style="border-right:1px solid #dadada">${item.crsCreNm}</td>
					<td><spring:message code="${item.crsOperMthd}" /></td>
					<td><spring:message code="${item.crsOperType}" /></td>
					<td class="text-right" style="border-right:1px solid #dadada"><fmt:formatNumber value="${item.planCreCnt}"/></td>
					<td class="text-right" style="border-right:1px solid #dadada"><fmt:formatNumber value="${item.planEnrlCnt}"/></td>
					<td class="text-right" style="border-right:1px solid #dadada"><fmt:formatNumber value="${item.creCnt}"/></td>
					<td class="text-right" style="border-right:1px solid #dadada"><fmt:formatNumber value="${item.enrlCnt}"/></td>
					<td class="text-right" style="border-right:1px solid #dadada"><fmt:formatNumber value="${item.creRatio}" pattern="#"/></td>
					<td class="text-right" style="border-right:1px solid #dadada"><fmt:formatNumber value="${item.eduRatio}" pattern="#.##"/></td>
					<td class="text-right" style="border-right:1px solid #dadada"><fmt:formatNumber value="${item.enrlCnt}"/></td>
					<td class="text-right"><fmt:formatNumber value="${item.eduPrice}"/></td>
				</tr>
			</c:forEach>
				<tr style="background-color:#fefefe">
					<c:choose>
						<c:when test="${totPlanCreCnt > 0 }">
							<c:set var="totCreRatio" value="${totCreCnt / totPlanCreCnt * 100}"/>
						</c:when>
						<c:otherwise>
							<c:set var="totCreRatio" value="0"/>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${totPlanEnrlCnt > 0 }">
							<c:set var="totEduRatio" value="${totEnrlCnt / totPlanEnrlCnt * 100}"/>
						</c:when>
						<c:otherwise>
							<c:set var="totEduRatio" value="0"/>
						</c:otherwise>
					</c:choose>

					<td colspan="4" style="border-right:1px solid #dadada"><spring:message code="log.title.course.status.totalcnt"/></td>
					<td class="text-right" style="border-right:1px solid #dadada"><fmt:formatNumber value="${totPlanCreCnt}"/></td>
					<td class="text-right" style="border-right:1px solid #dadada"><fmt:formatNumber value="${totPlanEnrlCnt}"/></td>
					<td class="text-right" style="border-right:1px solid #dadada"><fmt:formatNumber value="${totCreCnt}"/></td>
					<td class="text-right" style="border-right:1px solid #dadada"><fmt:formatNumber value="${totEnrlCnt}"/></td>
					<td class="text-right" style="border-right:1px solid #dadada"><fmt:formatNumber value="${totCreRatio}" pattern="#"/></td>
					<td class="text-right" style="border-right:1px solid #dadada"><fmt:formatNumber value="${totEduRatio}" pattern="#.##"/></td>
					<td class="text-right" style="border-right:1px solid #dadada"><fmt:formatNumber value="${totEnrlCnt}"/></td>
					<td class="text-right"><fmt:formatNumber value="${totEduPrice}"/></td>
				</tr>
			</c:if>
			<c:if test="${empty eduCourseStatusList}">
				<tr>
					<td colspan="12"><spring:message code="course.message.course.nodata"/></td>
				</tr>
			</c:if>
			</tbody>
		</table>
