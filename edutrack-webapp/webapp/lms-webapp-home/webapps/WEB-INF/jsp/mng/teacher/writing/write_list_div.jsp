<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="tchWritingsVO" value="${tchWritingsVO}"/>
<c:set var="tchWritingsList" value="${writeList}"/>
<%-- <c:set var="pageInfo" value="${pageInfo}"/> --%>
				<table summary="<spring:message code="user.title.userinfo.manage"/>" class="table table-bordered wordbreak">
					<colgroup>
						<col style="width:70px">
						<col style="width:100px;">
						<col style="width:auto">
						<col style="width:100px;">
						<col style="width:130px;">
						<col style="width:100px">
						<col style="width:50px">
					</colgroup>
					<thead>
						<tr>
							<th scope="col"><spring:message code="common.title.no"/> </th>
							<th scope="col"><spring:message code="teacher.title.teacherinfo.writing.pbls.dt"/></th>
							<th scope="col"><spring:message code="teacher.title.teacherinfo.writing.booknm"/></th>
							<th scope="col"><spring:message code="teacher.title.teacherinfo.writing.pbls.cnt"/></th>
							<th scope="col"><spring:message code="teacher.title.teacherinfo.writing.pbls.phoneno"/></th>
							<th scope="col"><spring:message code="teacher.title.teacherinfo.writing.pbls.deptnm"/></th>
							<c:if test="${tchWritingsVO.isView ne 'Y'}">
							<th scope="col"><spring:message code="common.title.edit"/></th>
							</c:if>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${tchWritingsList}" var="item" varStatus="status">
						<tr>
							<td class="text-right">${status.count}</td>
							<td class="text-center"><meditag:dateformat type="1" delimeter="." property="${item.pblsDt}"/></td>
							<td class="text-center wordbreak">${item.bookNm}</td>
							<td class="text-center">${item.pblsCnt}</td>
							<td class="text-center wordbreak">${item.pblsrPhoneno}</td>
							<td class="text-center wordbreak">${item.pblsDeptNm}</td>
							<c:if test="${tchWritingsVO.isView ne 'Y'}">
							<td class="text-center">
								<!--
								 /UserInfoManage.do?cmd=mngTeacher&usrUserInfoVO.userNo=
								javascript:userTeacher('${item.userNo}');
								 -->
								<a href="javascript:editWriter('${item.lecWritingsSn}');" class="btn btn-primary btn-sm"><spring:message code="button.edit"/> </a>
							</td>
							</c:if>
						</tr>
						</c:forEach>
						<c:if test="${empty tchWritingsList}">
						<tr>
							<td colspan="9"><spring:message code="teacher.message.writing.nodata"/></td>
						</tr>
						</c:if>
					</tbody>
				</table>
				<%-- <meditag:paging pageInfo="${pageInfo}" funcName="listWriting"/> --%>