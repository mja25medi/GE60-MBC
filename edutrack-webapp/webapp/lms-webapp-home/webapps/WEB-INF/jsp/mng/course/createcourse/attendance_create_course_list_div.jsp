<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="createCourseList" value="${createCourseList}"/>
<c:set var="vo" value="${vo}"/>
<c:set var="pageInfo" value="${pageInfo}"/>
	<table summary="<spring:message code="course.title.createcourse.manage"/>"  class="table table-bordered wordbreak">
		<colgroup>
			<col style="width:auto;min-width:90px;"/>
			<col style="width:auto"/>
			<col style="width:80px"/>
			<col style="width:80px"/>
			<col style="width:auto"/>
			<col style="width:auto"/>
		</colgroup>
		<thead>
			<tr>
				<th scope="col"><spring:message code="course.title.course.code"/></th>
				<th scope="col">
					<c:choose>
						<c:when test="${fn:startsWith(vo.sortKey,'CRS_CRE_NM') == true}">
							<c:if test="${vo.sortKey eq 'CRS_CRE_NM_ASC'}">
						<a href="javascript:setSortKey2('CRS_CRE_NM_DESC')"><spring:message code="course.title.course.name"/> <i class="fa fa-sort-asc"></i></a>
							</c:if>
							<c:if test="${vo.sortKey eq 'CRS_CRE_NM_DESC'}">
						<a href="javascript:setSortKey2('CRS_CRE_NM_ASC')"><spring:message code="course.title.course.name"/> <i class="fa fa-sort-desc"></i></a>
							</c:if>
						</c:when>
						<c:otherwise>
						<a href="javascript:setSortKey2('CRS_CRE_NM_ASC')"><spring:message code="course.title.course.name"/> <i class="fa fa-sort" style="color:#ddd"></i></a>
						</c:otherwise>
					</c:choose>
				</th>
				<th scope="col">
					<c:choose>
						<c:when test="${fn:startsWith(vo.sortKey,'CRE_TERM') == true}">
							<c:if test="${vo.sortKey eq 'CRE_TERM_ASC'}">
						<a href="javascript:setSortKey2('CRE_TERM_DESC')"><spring:message code="course.title.createcourse.creterm"/> <i class="fa fa-sort-asc"></i></a>
							</c:if>
							<c:if test="${vo.sortKey eq 'CRE_TERM_DESC'}">
						<a href="javascript:setSortKey2('CRE_TERM_ASC')"><spring:message code="course.title.createcourse.creterm"/> <i class="fa fa-sort-desc"></i></a>
							</c:if>
						</c:when>
						<c:otherwise>
						<a href="javascript:setSortKey2('CRE_TERM_ASC')"><spring:message code="course.title.createcourse.creterm"/> <i class="fa fa-sort" style="color:#ddd"></i></a>
						</c:otherwise>
					</c:choose>
				</th>
				<th scope="col"><spring:message code="course.title.createcourse.enrollcnt"/></th>
				<th scope="col"><spring:message code="course.title.createcourse.duration.edu"/></th>
				<th scope="col"><spring:message code="common.title.manage"/></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${createCourseList}" var="item" varStatus="status">
			<c:if test="${item.creTypeCd ne 'ON' }">
				<tr>
					<td class="text-center">${item.crsCd}</td>
					<td>${item.crsCreNm}</td>
					<td class="text-center">${item.creTerm}</td>
					<td class="text-center">${item.cnfmCnt}</td>
					<td>
						<c:if test="${item.enrlStartDttm ne ''}"><meditag:dateformat type="1" delimeter="." property="${item.enrlStartDttm}"/>~<meditag:dateformat type="1" delimeter="." property="${item.enrlEndDttm}"/></c:if>
					</td>
					<td class="text-center">
						<button class="btn btn-primary btn-sm" onclick="listAttend('${item.crsCreCd}')">출석부관리</button>
						<button class="btn btn-info btn-sm" onclick="excelDownload('${item.crsCreCd}')">엑셀다운로드</button>
					</td>
				</tr>
			</c:if>
			</c:forEach>
			<c:if test="${empty createCourseList}">
			<tr>
				<td colspan="11"><spring:message code="course.message.createcourse.nodata"/></td>
			</tr>
			</c:if>
		</tbody>
	</table>
	<meditag:paging pageInfo="${pageInfo}" funcName="listCreateCourse"/>

	<script type="text/javascript">
	$("._enterBind2").bind("keydown", eventForSearch2);
	function eventForSearch2(event) {
		if (event.keyCode == '13') {
			event.preventDefault();
			listCreateCourse('${createCourseVO.crsCd}','${createCourseVO.crsOperType }');
		}
	}
	
	</script>