<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="forumAtclList" value="${forumAtclList}"/>
<c:set var="pageInfo" value="${pageInfo}"/>


			<table summary="<spring:message code="lecture.title.forum.atcl.manage"/> " class="table table-bordered wordbreak">
				<colgroup>
					<col style="width:70px"/>
					<col style="width:auto"/>
					<col style="width:120px"/>
					<col style="width:120px"/>
					<col style="width:80px"/>
				</colgroup>
				<thead>
					<tr>
						<th scope="col" width="50"><spring:message code="common.title.no"/></th>
						<th scope="col"><spring:message code="common.title.title"/></th>
						<th scope="col"><spring:message code="common.title.reguser"/></th>
						<th scope="col"><spring:message code="common.title.regdate"/></th>
						<th scope="col"><spring:message code="common.title.hits"/></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${forumAtclList}" varStatus="status">
					<tr>
						<td class="text-right">${pageInfo.totalRecordCount -(pageInfo.recordCountPerPage * (pageInfo.currentPageNo -1)) - status.count + 1}</td>
						<td class="wordbreak">
							<meditag:reply level="${item.atclLvl}" />
							<span>
								<a href="javascript:readforumAtcl('${item.atclSn}')">${item.title} <c:if test="${item.commentCount > 0}"><span class="badge">${item.commentCount}</span></c:if></a>
							</span>
						</td>
						<td>${item.regNm}</td>
						<td class="text-center"><meditag:dateformat type="1" delimeter="." property="${item.regDttm}"/></td>
						<td class="text-right">${item.hits}</td>
					</tr>
					</c:forEach>
					<c:if test="${empty forumAtclList}">
					<tr>
						<td colspan="5"><spring:message code="lecture.message.forum.atcl.nodata"/></td>
					</tr>
					</c:if>
				</tbody>
			</table>
			<meditag:paging pageInfo="${pageInfo}" funcName="listAtcl"/>
