<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<%pageContext.setAttribute("crlf", "\n");%>
<c:url var="img_base" value="/img/home" />
<c:set var="forumVO" value="${vo}"/>

	<div id="editExam" >
		<table class="table table-bordered wordbreak">
			<caption class="sr-only"><spring:message code="lecture.title.forum.manage"/></caption>
			<tr>
				<th scope="row" style="width:20%"><spring:message code="lecture.title.forum.title"/></th>
				<td>${forumVO.forumTitle }</td>
			</tr>
			<tr>
				<th scope="row" style="width:20%"><spring:message code="lecture.title.forum.duration"/></th>
				<td>
					<meditag:dateformat type="8" delimeter="." property="${forumVO.forumStartDttm}"/>~<meditag:dateformat type="8" delimeter="." property="${forumVO.forumEndDttm}"/>
				</td>
			</tr>
			<tr>
				<th scope="row" style="width:15%"><spring:message code="lecture.title.forum.period.after.write"/></th>
				<td>
					<c:if test="${forumVO.periodAfterWriteYn eq 'Y' }"><spring:message code="lecture.title.forum.writeY"/></c:if>
					<c:if test="${forumVO.periodAfterWriteYn eq 'N' }"><spring:message code="lecture.title.forum.writeN"/></c:if>
				</td>
			</tr>
			<tr>
				<th scope="row" style="width:20%"><spring:message code="lecture.title.forum.desc"/></th>
				<td style="min-height:120px;valing:top">
					${fn:replace(forumVO.forumCts,crlf,"<br/>")}
				</td>
			</tr>
			<tr>
				<th scope="row" style="width:15%"><spring:message code="lecture.title.forum.regyn"/></th>
				<td>
					<c:forEach var="item" items="${regYnList}" varStatus="status">
						<c:set var="codeName" value="${item.codeNm}"/>
						<c:forEach var="lang" items="${item.codeLangList}">
							<c:if test="${lang.langCd eq LOCALEKEY }"><c:set var="codeName" value="${lang.codeNm}"/></c:if>
						</c:forEach>
						<c:if test="${forumVO.forumRegYn eq item.codeCd }">${codeName}</c:if>
						<c:choose>
							<c:when test="${status.last}"></c:when>
							<c:otherwise>&nbsp;&nbsp;</c:otherwise>
						</c:choose>
					</c:forEach>
				</td>
			</tr>
		</table>
	</div>
	<div class="text-right">
		<a href="javascript:parent.modalBoxClose()" class="btn btn-default btn-sm"><spring:message code="button.close"/></a>
	</div>
