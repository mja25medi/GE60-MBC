<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="createCourseVO" value="${vo}"/>

	<table summary="<spring:message code="course.title.createcourse.manage"/>"  class="table table-bordered wordbreak">
		<colgroup>
			<col style="width:60px"/>
			<col style="width:180px"/>
			<col style="width:auto"/>
			<col style="width:180px"/>
			<col style="width:180px"/>
			<col style="width:80px"/>
		</colgroup>
		<thead>
			<tr>
				<th scope="col"><spring:message code="common.title.no"/></th>
				<th scope="col">
					<spring:message code="course.title.createcourse.creterm"/> 
				</th>
				<th scope="col">
					<c:choose>
						<c:when test="${fn:startsWith(createCourseVO.sortKey,'CRS_CRE_NM') == true}">
							<c:if test="${createCourseVO.sortKey eq 'CRS_CRE_NM_ASC'}">
						<a href="javascript:setSortKey2('CRS_CRE_NM_DESC')"><spring:message code="course.title.course.name"/> <i class="fa fa-sort-asc"></i></a>
							</c:if>
							<c:if test="${createCourseVO.sortKey eq 'CRS_CRE_NM_DESC'}">
						<a href="javascript:setSortKey2('CRS_CRE_NM_ASC')"><spring:message code="course.title.course.name"/> <i class="fa fa-sort-desc"></i></a>
							</c:if>
						</c:when>
						<c:otherwise>
						<a href="javascript:setSortKey2('CRS_CRE_NM_ASC')"><spring:message code="course.title.course.name"/> <i class="fa fa-sort" style="color:#ddd"></i></a>
						</c:otherwise>
					</c:choose>
				</th>
				<th scope="col">자격증 신청자 수</th>
				<th scope="col">승인 대기자 수</th>
				<th scope="col"><spring:message code="common.title.manage"/></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${createCourseList}" var="item" varStatus="status">
			<tr>
				<td class="text-center">${status.count}</td>
				<td class="text-center">${item.crsNm}</td>
				<td>${item.crsCreNm}<a href="javascript:editCreCrsPop('${item.crsCreCd}');"><i class="fa fa-cog"></i></a></td>
				<td class="text-right">${item.applyCertCnt}</td>
				<td class="text-right">${item.waitCertCnt}</td>
				<td class="text-center">
					<button type="button" class="btn btn-info btn-sm" onclick="javascript:viewCertStudentMngForm('${item.crsCreCd}');"><spring:message code="button.manage"/></button>
				</td>
			</tr>
			</c:forEach>
			<c:if test="${empty createCourseList}">
			<tr>
				<td colspan="11">현재 검색한 검색조건으로 조회된 검색결과가 없습니다.</td>
			</tr>
			</c:if>
		</tbody>
	</table>

	<script type="text/javascript">
	$("._enterBind2").bind("keydown", eventForSearch2);
	function eventForSearch2(event) {
		if (event.keyCode == '13') {
			event.preventDefault();
			listCreateCourse('${createCourseVO.crsCd}','${createCourseVO.crsOperType }');
		}
	}
	</script>