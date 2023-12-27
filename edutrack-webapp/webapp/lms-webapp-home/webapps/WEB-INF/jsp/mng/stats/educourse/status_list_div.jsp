<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>

		<table summary="총합" style="width:100%;margin-top:1%" class="table table-bordered wordbreak" >
			<colgroup>
				<col style="width:15%">
				<col style="width:auto">
				<col style="width:15%">
				<col style="width:auto">
				<col style="width:15%">
				<col style="width:auto">
			</colgroup>
			<tbody>
				<c:set var="totEnrlCnt" value="0"/>
				<c:set var="totCpltCnt" value="0"/>
				<c:set var="totFailCnt" value="0"/>
				<c:forEach items="${eduCourseStatusList}" var="item" varStatus="status">
					<c:set var="totEnrlCnt" value="${totEnrlCnt + item.enrlCnt}"/>
					<c:set var="totCpltCnt" value="${totCpltCnt + item.cpltCnt}"/>
					<c:set var="totFailCnt" value="${totFailCnt + item.failCnt}"/>
				</c:forEach>
				<c:set var="totCpltPercent" value="${totCpltCnt / totEnrlCnt * 100}"/>
				<tr>
					<th>기업 수</th>
					<td class="text-right">${deptList.size()}건</td>
					<th>과정 수</th>
					<td class="text-right">${eduCourseStatusList.size()}건</td>
					<th>수료율</th>
					<td class="text-right">${meditag:round(totCpltPercent,1)}%</td>
				</tr>
				<tr>
					<th>전체 학습자 수 </th>
					<td class="text-right">${totEnrlCnt}<spring:message code="common.title.cnt.user"/></td>
					<th>수료자 수  </th>
					<td class="text-right">${totCpltCnt}<spring:message code="common.title.cnt.user"/></td>
					<th>미수료자 수 </th>
					<td class="text-right">${totFailCnt}<spring:message code="common.title.cnt.user"/></td>
				</tr>
			</tbody>
		</table>
		<table summary="<spring:message code="log.title.course.status"/>" style="width:100%" class="table table-bordered wordbreak">
			<colgroup>
				<col style="width:auto">
				<col style="width:auto">
				<col style="width:auto">
				<col style="width:200px;">
				<col style="width:90px">
				<col style="width:90px">
				<col style="width:90px">
				<col style="width:90px">
				<col style="width:90px">
			</colgroup>
			<thead>
				<tr>
					<th scope="col">
					<c:choose>
						<c:when test="${fn:startsWith(eduCourseStatusVO.sortKey,'CRS_NM') == true}">
							<c:if test="${eduCourseStatusVO.sortKey eq 'CRS_NM_ASC'}">
						<a href="javascript:setSortKey('CRS_NM_DESC')"><spring:message code="course.title.createcourse.creterm"/> <i class="fa fa-sort-asc"></i></a>
							</c:if>
							<c:if test="${eduCourseStatusVO.sortKey eq 'CRS_NM_DESC'}">
						<a href="javascript:setSortKey('CRS_NM_ASC')"><spring:message code="course.title.createcourse.creterm"/> <i class="fa fa-sort-desc"></i></a>
							</c:if>
						</c:when>
						<c:otherwise>
						<a href="javascript:setSortKey('CRS_NM_ASC')"><spring:message code="course.title.createcourse.creterm"/> <i class="fa fa-sort" style="color:#ddd"></i></a>
						</c:otherwise>
					</c:choose>
					</th>
					<th scope="col">기업명</th>
					<th scope="col">
					<c:choose>
						<c:when test="${fn:startsWith(eduCourseStatusVO.sortKey,'CRS_CRE_NM') == true}">
							<c:if test="${eduCourseStatusVO.sortKey eq 'CRS_CRE_NM_ASC'}">
						<a href="javascript:setSortKey('CRS_CRE_NM_DESC')"><spring:message code="course.title.course.name"/> <i class="fa fa-sort-asc"></i></a>
							</c:if>
							<c:if test="${eduCourseStatusVO.sortKey eq 'CRS_CRE_NM_DESC'}">
						<a href="javascript:setSortKey('CRS_CRE_NM_ASC')"><spring:message code="course.title.course.name"/> <i class="fa fa-sort-desc"></i></a>
							</c:if>
						</c:when>
						<c:otherwise>
						<a href="javascript:setSortKey('CRS_CRE_NM_ASC')"><spring:message code="course.title.course.name"/> <i class="fa fa-sort" style="color:#ddd"></i></a>
						</c:otherwise>
					</c:choose>
					</th>
					<th scope="col"><spring:message code="course.title.createcourse.duration.edu"/></th>
					<th scope="col"><spring:message code="log.title.course.status.enrollcnt"/></th>
					<th scope="col"><spring:message code="log.title.course.status.completecnt"/></th>
					<th scope="col"><spring:message code="log.title.course.status.noncompletecnt"/></th>
					<th scope="col"><spring:message code="log.title.course.status.completepercent"/></th>
					<th scope="col"><spring:message code="button.view.detail"/></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${eduCourseStatusList}" var="item" varStatus="status">
					<c:if test="${item.enrlCnt eq 0 }">
						<c:set var="cpltPercent" value="0"/>
					</c:if>
					<c:if test="${item.enrlCnt ne 0 }">
						<c:set var="cpltPercent" value="${item.cpltCnt / item.enrlCnt * 100}"/>
					</c:if>
				<tr>
					<td class="text-center">${item.crsNm}</td>
					<td class="text-center">${item.deptNm}</td>
					<td class="wordbreak">${item.crsCreNm}</td>
					<td class="text-center"><meditag:dateformat type="1" delimeter="." property="${item.enrlStartDttm}"/> ~ <meditag:dateformat type="1" delimeter="." property="${item.enrlEndDttm}"/></td>
					<td class="text-right">${item.enrlCnt}<spring:message code="common.title.cnt.user"/></td>
					<td class="text-right">${item.cpltCnt}<spring:message code="common.title.cnt.user"/></td>
					<td class="text-right">${item.failCnt}<spring:message code="common.title.cnt.user"/></td>
					<td class="text-right">${meditag:round(cpltPercent,1)}%</td>
					<td class="text-center"> <a href="javascript:stdEduResltView('${item.crsCreCd}')" class="btn btn-primary btn-sm">보기</a></td>
				</tr>
				
				</c:forEach>
				<%-- <tr style="background-color:#f3f3f3">
					<td colspan="3"  class="text-right"><spring:message code="log.title.course.status.totalcnt"/></td>
					<td class="text-right">${totEnrlCnt}<spring:message code="common.title.cnt.user"/></td>
					<td class="text-right">${totCpltCnt}<spring:message code="common.title.cnt.user"/></td>
					<td class="text-right">${totFailCnt}<spring:message code="common.title.cnt.user"/></td>
				</tr> --%>
				<c:if test="${empty eduCourseStatusList}">
				<tr>
					<td colspan="8"><spring:message code="course.message.createcourse.nodata"/></td>
				</tr>
				</c:if>
			</tbody>
		</table>
		
		<%-- <meditag:paging pageInfo="${pageInfo}" funcName="listStatus"/> --%>
