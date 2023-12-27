<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="forumList" value="${forumList}"/>

		<table summary="<spring:message code="lecture.title.forum.manage"/>" class="table table-bordered wordbreak">
			<colgroup>
				<col style="width:50px"/>
				<col style="width:auto"/>
				<col style="width:300px;"/>
				<col style="width:70px"/>
				<col style="width:80px"/>
				<col style="width:80px"/>
				<col style="width:72px"/>
			</colgroup>
			<thead>
				<tr>
					<th scope="col"><spring:message code="common.title.no"/></th>
					<th scope="col"><spring:message code="lecture.title.forum.title"/></th>
					<th scope="col"><spring:message code="lecture.title.forum.duration"/></th>
					<th scope="col"><spring:message code="lecture.title.forum.atclcnt"/></th>
					<th scope="col"><spring:message code="lecture.title.forum.period.after.write"/></th>
					<th scope="col"><spring:message code="lecture.title.forum.regyn"/></th>
					<th scope="col"><spring:message code="common.title.manage"/></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${forumList}" varStatus="status">
				<tr>
					<td class="text-right">${status.count}</td>
					<td class="wordbreak">
						${item.forumTitle}
						<a href="javascript:forumEdit('${item.forumSn}');" ><i class="fa fa-cog"></i></a>
					</td>
					<td class="text-center"><meditag:dateformat type="8" delimeter="." property="${item.forumStartDttm}"/> ~ <meditag:dateformat type="8" delimeter="." property="${item.forumEndDttm}"/></td>
					<td class="text-right">${item.atclCnt}</td>
					<td class="text-center">
						<c:if test="${item.periodAfterWriteYn eq 'Y'}">
							<spring:message code="lecture.title.forum.writeY"/>
						</c:if>
						<c:if test="${item.periodAfterWriteYn ne 'Y'}">
							<spring:message code="lecture.title.forum.writeN"/>
						</c:if>
					</td>
					<td class="text-center"><meditag:codename code="${item.forumRegYn}" category="REG_YN"/></td>
					<td class="text-center">
						<a href="<c:url value="/mng/lecture/forum/manageForumAtclMain"/>?crsCreCd=${item.crsCreCd}&amp;forumSn=${item.forumSn}" class="btn btn-info btn-sm"><spring:message code="common.title.manage"/></a>
					</td>
				</tr>
				</c:forEach>
				<c:if test="${empty forumList}">
				<tr>
					<td colspan="7"><spring:message code="lecture.message.forum.nodata"/></td>
				</tr>
				</c:if>
			</tbody>
		</table>
