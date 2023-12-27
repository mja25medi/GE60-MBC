<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="projectListVO" value="${projectListVO}"/>
<c:set var="pageInfo" value="${pageInfo}"/>

			<table summary="<spring:message code="lecture.title.project.manage"/>" style="width:100%" class="table_list">
				<colgroup>
					<col style="width:50px;"/>
					<col style="width:auto;"/>
					<col style="width:auto;"/>
					<col style="width:5%"/>
					<col style="width:10%"/>
					<col style="width:50px;"/>
					<col style="width:50px;"/>
				</colgroup>
				<thead>
					<tr>
						<th scope="col"><spring:message code="common.title.no"/></th>
						<th scope="col"><spring:message code="lecture.title.project.name"/></th>
						<th scope="col"><spring:message code="lecture.title.project.duration"/></th>
						<th scope="col"><spring:message code="lecture.title.project.teamcnt"/></th>
						<th scope="col"><spring:message code="lecture.title.project.scoreopenyn"/></th>
						<th scope="col"><spring:message code="common.title.edit"/></th>
						<th scope="col"><spring:message code="common.title.manage"/></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${projectListVO}" varStatus="status">
					<tr>
						<td>${status.count}</td>
						<td class="left">${item.prjtTitle}</td>
						<td>${item.prjtStartDttm} ~ ${item.prjtEndDttm}</td>
						<td>${item.teamCnt}</td>
						<td>${item.scoreOpenYn}</td>
						<td><meditag:button value="button.edit" title="button.edit" func="prjtEdit('${item.crsCreCd}','${item.prjtSn}')" /></td>
						<td><a class="squarebutton" href="<c:url value="/mng/lecture/project/manageForm"/>?projectVO.crsCreCd=${item.crsCreCd}&amp;projectVO.prjtSn=${item.prjtSn}"><span><spring:message code="common.title.manage"/></span></a></td>
					</tr>
					</c:forEach>
					<c:if test="${empty projectListVO}">
					<tr>
						<td colspan="7"><spring:message code="lecture.message.project.nodata"/></td>
					</tr>
					</c:if>
				</tbody>
			</table>


