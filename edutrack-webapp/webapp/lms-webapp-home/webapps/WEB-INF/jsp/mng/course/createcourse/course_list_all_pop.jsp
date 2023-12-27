<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:url var="img_base" value="/img/home" />
<c:set var="createCourseVO" value="${createCourseVO}"/>
<mhtml:mng_html>
<mhtml:mng_head titleName="${MENUNAME}">

</mhtml:mng_head>
<mhtml:frm_body cssTag="background-color:#fff;">
	<div class="cal_contpop" style="padding-top:10px;">
		<table summary="<spring:message code="course.title.course.manage"/>" class="table table-bordered wordbreak">
			<colgroup>
				<col style="width:7%">
				<col style="width:7%;">
				<col style="width:16%;">
				<col style="width:auto;">
				<col style="width:19%;">
				<col style="width:7%">
				<col style="width:7%">
				<col style="width:9%">
			</colgroup>
			<thead>
				<tr>
					<th scope="col"><spring:message code="course.title.createcourse.creyear"/></th>
					<th scope="col">
						<c:choose>
						<c:when test="${fn:startsWith(createCourseVO.sortKey,'CRE_TERM') == true}">
							<c:if test="${createCourseVO.sortKey eq 'CRE_TERM_ASC'}">
						<a href="javascript:setSortKey2('CRE_TERM_DESC')"><spring:message code="course.title.createcourse.creterm"/> <i class="fa fa-sort-asc"></i></a>
							</c:if>
							<c:if test="${createCourseVO.sortKey eq 'CRE_TERM_DESC'}">
						<a href="javascript:setSortKey2('CRE_TERM_ASC')"><spring:message code="course.title.createcourse.creterm"/> <i class="fa fa-sort-desc"></i></a>
							</c:if>
						</c:when>
						<c:otherwise>
						<a href="javascript:setSortKey2('CRE_TERM_ASC')"><spring:message code="course.title.createcourse.creterm"/> <i class="fa fa-sort" style="color:#ddd"></i></a>
						</c:otherwise>
					</c:choose>
					</th>
					<th scope="col">
					<c:choose>
						<c:when test="${fn:startsWith(createCourseVO.sortKey,'CRS_NM') == true}">
							<c:if test="${createCourseVO.sortKey eq 'CRS_NM_ASC'}">
						<a href="javascript:setSortKey2('CRS_NM_DESC')"><spring:message code="course.title.course.name"/> <i class="fa fa-sort-asc"></i></a>
							</c:if>
							<c:if test="${createCourseVO.sortKey eq 'CRS_NM_DESC'}">
						<a href="javascript:setSortKey2('CRS_NM_ASC')"><spring:message code="course.title.course.name"/> <i class="fa fa-sort-desc"></i></a>
							</c:if>
						</c:when>
						<c:otherwise>
						<a href="javascript:setSortKey2('CRS_NM_ASC')"><spring:message code="course.title.course.name"/> <i class="fa fa-sort" style="color:#ddd"></i></a>
						</c:otherwise>
					</c:choose>
					</th>
					<th scope="col">
					<c:choose>
						<c:when test="${fn:startsWith(createCourseVO.sortKey,'CRS_CRE_NM') == true}">
							<c:if test="${createCourseVO.sortKey eq 'CRS_CRE_NM_ASC'}">
						<a href="javascript:setSortKey2('CRS_CRE_NM_DESC')"><spring:message code="course.title.createcourse.name"/> <i class="fa fa-sort-asc"></i></a>
							</c:if>
							<c:if test="${createCourseVO.sortKey eq 'CRS_CRE_NM_DESC'}">
						<a href="javascript:setSortKey2('CRS_CRE_NM_ASC')"><spring:message code="course.title.createcourse.name"/> <i class="fa fa-sort-desc"></i></a>
							</c:if>
						</c:when>
						<c:otherwise>
						<a href="javascript:setSortKey2('CRS_CRE_NM_ASC')"><spring:message code="course.title.createcourse.name"/> <i class="fa fa-sort" style="color:#ddd"></i></a>
						</c:otherwise>
					</c:choose>
					</th>
					<th scope="col"><spring:message code="course.title.createcourse.duration.edu"/></th>
					<th scope="col"><spring:message code="course.title.createcourse.enrollcnt"/></th>
					<th scope="col"><spring:message code="log.title.course.status.completecnt"/></th>
					<th scope="col"><spring:message code="log.title.course.status.noncompletecnt"/></th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${not empty createCourseList}">
				<c:set var="totalCnfmCnt" value="0"/>
				<c:set var="totalCpltCnt" value="0"/>
				<c:set var="totalFailCnt" value="0"/>
				<c:forEach items="${createCourseList}" var="item" varStatus="status">
				<tr>
					<td class="text-center">${item.creYear}</td>
					<td class="text-center wordbreak">${item.creTerm}</td>
					<td class="wordbreak">${item.crsNm}</td>
					<td class="wordbreak">${item.crsCreNm}</td>
					<td class="text-center">
						<c:if test="${item.enrlStartDttm ne ''}"><meditag:dateformat type="1" delimeter="." property="${item.enrlStartDttm}"/>~<meditag:dateformat type="1" delimeter="." property="${item.enrlEndDttm}"/></c:if>
						<c:if test="${item.enrlStartDttm eq ''}">${item.creYear}</c:if>
					</td>
					<td class="text-right">${item.cnfmCnt}<spring:message code="common.title.cnt.user"/></td>
					<td class="text-right">${item.cpltCnt}<spring:message code="common.title.cnt.user"/></td>
					<td class="text-right">${item.failCnt}<spring:message code="common.title.cnt.user"/></td>
					<c:set var="totalCnfmCnt" value="${totalCnfmCnt +  item.cnfmCnt}"/>
					<c:set var="totalCpltCnt" value="${totalCpltCnt +  item.cpltCnt}"/>
					<c:set var="totalFailCnt" value="${totalFailCnt +  item.failCnt}"/>
				</tr>
				</c:forEach>
				<tr>
					<td colspan="5" class="text-right"><spring:message code="log.title.course.status.totalcnt"/></td>
					<td class="text-right">${totalCnfmCnt }<spring:message code="common.title.cnt.user"/></td>
					<td class="text-right">${totalCpltCnt }<spring:message code="common.title.cnt.user"/></td>
					<td class="text-right">${totalFailCnt }<spring:message code="common.title.cnt.user"/></td>
				</tr>
				</c:if>
				<c:if test="${empty createCourseList}">
				<tr>
					<td colspan="8"><spring:message code="common.message.nodata"/></td>
				</tr>
				</c:if>
			</tbody>
		</table>
		<div class="text-right">
			<a href="#none" onclick="parent.modalBoxClose();" class="btn btn-sm btn-default"><spring:message code="button.close"/></a>
		</div>
	</div>
<script type="text/javascript">

var ItemDTO = {sortKey : "",sortKey2 : ""}
function setSortKey2(sortKey) {
	ItemDTO.sortKey2 = sortKey;
	location.href = "/mng/course/createCourse/listTotalCoursePop?createCourseVO.sortKey="+ItemDTO.sortKey2;
}
</script>
</mhtml:frm_body>
</mhtml:mng_html>
