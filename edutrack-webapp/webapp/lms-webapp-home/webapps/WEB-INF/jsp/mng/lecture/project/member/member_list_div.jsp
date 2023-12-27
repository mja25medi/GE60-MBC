<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="prjTeamListVO" value="${prjTeamListVO}"/>

			<table summary="<spring:message code="lecture.title.project.team.manage"/>" style="width:100%" class="table_list">
				<colgroup>
					<col style="width:5%"/>
					<col style="width:5%"/> 
					<col style="width:60%"/> 
					<col style="width:10%"/> 
					<col style="width:10%"/> 
					<col style="width:5%"/> 
					<col style="width:5%"/> 
				</colgroup>
				<thead>
					<tr>
						<th scope="col"><input type="checkbox" name="chkAll" id="chkAll" value="N" style="border:0" title="전체 선택" onclick="checkAll()"/></th>
						<th scope="col"><spring:message code="common.title.no"/></th>
						<th scope="col"><spring:message code="lecture.title.project.team.name"/></th>
						<th scope="col"><spring:message code="lecture.title.project.team.boss"/></th>
						<th scope="col"><spring:message code="lecture.title.project.team.membercnt"/></th>
						<th scope="col"><spring:message code="common.title.edit"/></th>
						<th scope="col"><spring:message code="common.title.manage"/></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${prjTeamListVO}" varStatus="status">
					<tr>
						<td><input type="checkbox" name="sel" id="sel" value="${item.prjtTeamSn}" style="border:0" /></td>
						<td>${status.count}</td>
						<td class="left">${item.prjtTeamNm}</td>
						<td>${item.teamLeader}</td>
						<td>${item.mbrCnt}</td>
						<td><meditag:button value="button.edit" title="button.edit" func="editTeam('${item.prjtTeamSn}')" /></td>
						<td><meditag:button value="button.manage" title="button.manage" func="adminTeam('${item.prjtTeamSn}')" /></td>
					</tr>					
					</c:forEach>
					<c:if test="${empty prjTeamListVO}">
					<tr>
						<td colspan="7"><spring:message code="lecture.message.project.team.nodata"/></td>
					</tr>
					</c:if>
				</tbody>
			</table>
