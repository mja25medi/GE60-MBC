<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="tchSchsVO" value="${vo}"/>
<c:set var="tchSchsList" value="${tchSchsList}"/>
<%-- <c:set var="pageInfo" value="${tchSchsForm.pageInfo}"/> --%>

				<table class="table table-bordered wordbreak">
					<caption class="sr-only"><spring:message code="user.title.userinfo.manage"/></caption>
					<colgroup>
						<col style="width:50px"/>
						<col style="width:100px"/>
						<col style="width:auto"/>
						<col style="width:auto"/>
						<col style="width:auto"/>
						<col style="width:100px"/>
						<col style="width:100px"/>
						<c:if test="${vo.isView ne 'Y'}">
						<col style="width:50px"/>
						</c:if>
					</colgroup>
					<thead>
						<tr>
							<th scope="col"><spring:message code="common.title.no"/> </th>
							<th scope="col"><spring:message code="teacher.title.teacherinfo.school.graddiv"/></th>
							<th scope="col"><spring:message code="teacher.title.teacherinfo.school.schlnm"/></th>
							<th scope="col"><spring:message code="teacher.title.teacherinfo.school.schlsubj"/></th>
							<th scope="col"><spring:message code="teacher.title.teacherinfo.school.locatnm"/></th>
							<th scope="col"><spring:message code="teacher.title.teacherinfo.school.regdttm"/></th>
							<th scope="col"><spring:message code="teacher.title.teacherinfo.school.entrgraddt"/></th>
							<c:if test="${vo.isView ne 'Y'}">
							<th scope="col"><spring:message code="common.title.edit"/></th>
							</c:if>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${tchSchsList}" var="item" varStatus="status">
						<tr>
							<td class="text-right">${status.count}</td>
							<td class="text-center"><meditag:codename code="${item.gradDiv}" category="GRAD_DIV" /></td>
							<td class="text-center wordbreak">${item.schlNm}</td>
							<td class="text-center wordbreak">${item.schlSubj}</td>
							<td class="text-center wordbreak">${item.locatNm}</td>
							<td class="text-center">${item.entrDt }</td>
							<td class="text-center">${item.gradDt }</td>
							<c:if test="${vo.isView ne 'Y'}">
							<td class="text-center">
								<!--
								 /UserInfoManage.do?cmd=mngTeacher&amp;usrUserInfoVO.userNo=
								javascript:userTeacher('${item.userNo}');
								 -->
								<a href="javascript:editSchool('${item.schsSn}');" class="btn btn-primary btn-sm"><spring:message code="button.edit"/> </a>
							</td>
							</c:if>
						</tr>
						</c:forEach>
						<c:if test="${empty tchSchsList}">
						<tr>
							<td colspan="9"><spring:message code="user.message.userinfo.nodata"/></td>
						</tr>
						</c:if>
					</tbody>
				</table>
				<%-- <meditag:paging pageInfo="${pageInfo}" funcName="listSchool"/> --%>