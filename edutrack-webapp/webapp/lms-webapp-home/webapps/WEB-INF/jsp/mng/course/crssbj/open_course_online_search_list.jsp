<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>

		<table summary="<spring:message code="course.title.subject.manage"/>" style="width:100%;margin-bottom:0px;" class="table table-bordered wordbreak">
			<colgroup>
				<col style="width:auto"/>
				<col style="width:auto;"/>
				<col style="width:auto"/>
				<col style="width:66px;"/>
			</colgroup>
			<thead>
				<tr>
					<th scope="col"><spring:message code="course.title.subject.category"/></th>
					<th scope="col"><spring:message code="course.title.subject.name"/></th>
					<th scope="col"><spring:message code="course.title.subject.contents.cnt"/></th>
					<th scope="col"><spring:message code="common.title.select"/></th>
				</tr>
			</thead>
			<tbody id="tbodyList">
				<c:forEach items="${sbjList}" var="item" varStatus="status">
				<tr>
					<td class="wordbreak text-left">${item.sbjCtgrNm}</td>
					<td class="wordbreak text-left">${item.sbjNm}</td>
					<td>${item.contentsCnt}</td>
					<td class="text-center">
						<c:if test="${item.contentsCnt > 0}">
						<button class="btn btn-info btn-sm" onclick="javascript:addSubject('${item.sbjCd}');"><spring:message code="button.choice"/></button>
						</c:if>
						<c:if test="${item.contentsCnt == 0}">
						-
						</c:if>
					</td>
				</tr>
				</c:forEach>
				<c:if test="${empty sbjList}">
				<tr>
					<td colspan="4" align="center"><spring:message code="course.message.subject.search.nodata"/></td>
				</tr>
				</c:if>
			</tbody>
		</table>
