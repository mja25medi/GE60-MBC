<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>

		<table summary="<spring:message code="course.title.subject.manage"/>" style="width:100%;margin-bottom:0px;" class="table table-bordered wordbreak">
			<colgroup>
				<col style="width:auto;"/>
				<col style="width:auto;"/>
				<col style="width:120px"/>
				<col style="width:66px;"/>
			</colgroup>
			<thead>
				<tr>
					<th scope="col"><spring:message code="course.title.subject.category"/></th>
					<th scope="col"><spring:message code="course.title.subject.name"/></th>
					<th scope="col"><spring:message code="course.title.subject.type"/></th>
					<th scope="col"><spring:message code="common.title.select"/></th>
				</tr>
			</thead>
			<tbody id="tbodyList">
				<c:forEach items="${sbjList}" var="item" varStatus="status">
				<tr>
					<td>${item.sbjCtgrNm}</td>
					<td>${item.sbjNm}</td>
					<td class="text-center">
						<select name="lecKindCd_${item.sbjCd}" id="lecKindCd_${item.sbjCd}" class="form-control input-sm">
						<c:forEach var="code" items="${codeList}">
							<c:set var="codeName" value="${code.codeNm}"/>
							<c:forEach var="lang" items="${code.codeLangList }">
								<c:if test="${lang.langCd eq LOCALEKEY }"><c:set var="codeName" value="${lang.codeNm}"/></c:if>
							</c:forEach>
							<option value="${code.codeCd}">${codeName}</option>
						</c:forEach>
						</select>
					</td>
					<td class="text-center">
						<button class="btn btn-info btn-sm" onclick="javascript:addSubject('${item.sbjCd}');"><spring:message code="button.choice"/> </button>
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
