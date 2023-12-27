<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>

		<table style="width:100%" class="table table-bordered wordbreak">
			<colgroup>
				<col style="width:auto"/>
				<col style="width:20%"/>
				<col style="width:15%"/>
				<col style="width:15%"/>
			</colgroup>
			<thead>
				<tr>
					<th scope="col"><spring:message code="course.title.subject.name"/></th>
					<th scope="col"><spring:message code="course.title.subject.category"/></th>
					<th scope="col"><spring:message code="course.title.subject.contents.cnt"/></th>
					<th scope="col"><spring:message code="common.title.select"/></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${subjectList}">
				<tr>
					<td class="wordbreak">${item.sbjNm}</td>
					<td class="wordbreak">${item.sbjCtgrNm}</td>
					<td class="text-center">${item.contentsCnt}</td>
					<td class="text-center"><a href="javascript:selectSubject('${item.sbjCd}', '${item.sbjNm}');" title="<spring:message code="common.title.select"/>" class="btn btn-primary btn-sm"><spring:message code="common.title.select"/></a></td>
				</tr>
				</c:forEach>
				<c:if test="${empty subjectList}">
				<tr>
					<td colspan="4"><spring:message code="course.message.contents.search.course.nodata"/></td>
				</tr>
				</c:if>
			</tbody>
		</table>
