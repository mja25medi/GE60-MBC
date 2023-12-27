<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="itemList" value="${researchForm.researchListDTO}"/>
<c:url var="img_base" value="/img/home" />
<mhtml:class_html>
<mhtml:class_head>

</mhtml:class_head>
<mhtml:class_body>
	<div id="content">
		<mhtml:class_location />

		<div class="btn_right" style="margin-top:10px;margin-bottom:10px;">
			<a href="<c:url value="/ResearchLecture.do"/>?cmd=addFormResearch" class="btn02"><spring:message code="button.write.research"/></a>
		</div>
		<form name="Search" id="Search" action="/ResearchLecture.do">
		<table class="board_a">
			<caption><spring:message code="lecture.title.research.manage"/></caption>
			<colgroup>
				<col style="width:6%"/>
				<col style="width:auto;"/>
				<col style="width:90px"/>
				<col style="width:90px;"/>
				<col style="width:8%"/>
				<col style="width:8%"/>
			</colgroup>
			<thead>
				<tr>
					<th scope="col"><spring:message code="common.title.no"/></th>
					<th scope="col"><spring:message code="lecture.title.research.name"/></th>
					<th scope="col"><spring:message code="lecture.title.research.startdate"/></th>
					<th scope="col"><spring:message code="lecture.title.research.enddate"/></th>
					<th scope="col"><spring:message code="lecture.title.research.regyn"/></th>
					<th scope="col"><spring:message code="common.title.manage"/></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${itemList}" varStatus="status">
				<tr>
					<td class="text-right">${status.count}</td>
					<td class="wordbreak">${item.reshTitle}</td>
					<td class="text-center">${item.reshStartDttm}</td>
					<td class="text-center">${item.reshEndDttm}</td>
					<td class="text-center">${item.regYn}</td>
					<td class="text-center"><a href="<c:url value="/ResearchLecture.do"/>?cmd=listItem&amp;researchDTO.crsCreCd=${item.crsCreCd}&amp;researchDTO.reshSn=${item.reshSn}" class="btn_org"><span><spring:message code="button.manage"/></span></a></td>
				</tr>
				</c:forEach>
				<c:if test="${empty itemList}">
				<tr>
					<td colspan="6"><spring:message code="lecture.message.research.nodata"/></td>
				</tr>
				</c:if>
			</tbody>
		</table>
		</form>
	</div>
</mhtml:class_body>
</mhtml:class_html>