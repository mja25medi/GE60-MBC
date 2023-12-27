<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>


		<table summary="<spring:message code="log.title.course.status"/>" style="width:100%" class="table table-bordered wordbreak">
			<colgroup>
				<col style="width:95px">
				<col style="width:70px">
				<col style="width:auto">
				<col style="width:auto">
				<col style="width:210px;">
				<col style="width:80px">
				<col style="width:80px">
				<col style="width:90px">
			</colgroup>
			<thead>
				<tr>
					<th scope="col">
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
					<th scope="col">
					<c:choose>
						<c:when test="${fn:startsWith(eduCourseStatusVO.sortKey,'CRE_TERM') == true}">
							<c:if test="${eduCourseStatusVO.sortKey eq 'CRE_TERM_ASC'}">
						<a href="javascript:setSortKey('CRE_TERM_DESC')"><spring:message code="course.title.createcourse.creterm"/> <i class="fa fa-sort-asc"></i></a>
							</c:if>
							<c:if test="${eduCourseStatusVO.sortKey eq 'CRE_TERM_DESC'}">
						<a href="javascript:setSortKey('CRE_TERM_ASC')"><spring:message code="course.title.createcourse.creterm"/> <i class="fa fa-sort-desc"></i></a>
							</c:if>
						</c:when>
						<c:otherwise>
						<a href="javascript:setSortKey('CRE_TERM_ASC')"><spring:message code="course.title.createcourse.creterm"/> <i class="fa fa-sort" style="color:#ddd"></i></a>
						</c:otherwise>
					</c:choose>
					</th>
					<th scope="col">
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
					<th scope="col">
					<c:choose>
						<c:when test="${fn:startsWith(eduCourseStatusVO.sortKey,'CRS_CRE_NM') == true}">
							<c:if test="${eduCourseStatusVO.sortKey eq 'CRS_CRE_NM_ASC'}">
						<a href="javascript:setSortKey('CRS_CRE_NM_DESC')"><spring:message code="course.title.createcourse.name"/> <i class="fa fa-sort-asc"></i></a>
							</c:if>
							<c:if test="${eduCourseStatusVO.sortKey eq 'CRS_CRE_NM_DESC'}">
						<a href="javascript:setSortKey('CRS_CRE_NM_ASC')"><spring:message code="course.title.createcourse.name"/> <i class="fa fa-sort-desc"></i></a>
							</c:if>
						</c:when>
						<c:otherwise>
						<a href="javascript:setSortKey('CRS_CRE_NM_ASC')"><spring:message code="course.title.createcourse.name"/> <i class="fa fa-sort" style="color:#ddd"></i></a>
						</c:otherwise>
					</c:choose>
					</th>
					<th scope="col"><spring:message code="course.title.createcourse.duration.edu"/></th>
					<th scope="col"><spring:message code="log.title.course.status.enrollcnt"/></th>
					<th scope="col"><spring:message code="log.title.course.status.completecnt"/></th>
					<th scope="col"><spring:message code="log.title.course.status.noncompletecnt"/></th>
				</tr>
			</thead>
			<tbody>
				<c:set var="totEnrlCnt" value="0"/>
				<c:set var="totCpltCnt" value="0"/>
				<c:set var="totFailCnt" value="0"/>
				<c:forEach items="${eduCourseStatusList}" var="item" varStatus="status">
					<c:set var="totEnrlCnt" value="${totEnrlCnt + item.enrlCnt}"/>
					<c:set var="totCpltCnt" value="${totCpltCnt + item.cpltCnt}"/>
					<c:set var="totFailCnt" value="${totFailCnt + item.failCnt}"/>
				<tr>
					<td class="text-center">${item.creYear}<spring:message code="common.title.year"/></td>
					<td class="text-center">${item.creTerm}</td>
					<td class="wordbreak">${item.crsNm}</td>
					<td class="wordbreak">${item.crsCreNm}</td>
					<td class="text-center"><meditag:dateformat type="1" delimeter="." property="${item.enrlStartDttm}"/> ~ <meditag:dateformat type="1" delimeter="." property="${item.enrlEndDttm}"/></td>
					<td class="text-right">${item.enrlCnt}<spring:message code="common.title.cnt.user"/></td>
					<td class="text-right">${item.cpltCnt}<spring:message code="common.title.cnt.user"/></td>
					<td class="text-right">${item.failCnt}<spring:message code="common.title.cnt.user"/></td>
				</tr>
				</c:forEach>
				<tr style="background-color:#f3f3f3">
					<td colspan="5"  class="text-right"><spring:message code="log.title.course.status.totalcnt"/></td>
					<td class="text-right">${totEnrlCnt}<spring:message code="common.title.cnt.user"/></td>
					<td class="text-right">${totCpltCnt}<spring:message code="common.title.cnt.user"/></td>
					<td class="text-right">${totFailCnt}<spring:message code="common.title.cnt.user"/></td>
				</tr>
				<c:if test="${empty eduCourseStatusList}">
				<tr>
					<td colspan="8"><spring:message code="course.message.createcourse.nodata"/></td>
				</tr>
				</c:if>
			</tbody>
		</table>
