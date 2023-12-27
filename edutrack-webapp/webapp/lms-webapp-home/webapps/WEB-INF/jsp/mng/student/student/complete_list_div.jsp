<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="studentList" value="${studentList}"/>
<c:set var="pageInfo" value="${pageInfo}"/>
<c:set var="studentVO" value="${studentVO}"/>
<jsp:useBean id="toDay" class="java.util.Date" />
<fmt:formatDate value="${toDay}" pattern="yyyyMMdd" var="currentDate" />
<fmt:parseNumber value="${fn:replace(createCourseVO.enrlEndDttm,'.','') }" type="number"  var="intEnddate"/>
<fmt:parseNumber value="${currentDate}" type="number"  var="intCurrentDate"/>

		<table summary="<spring:message code="student.title.student.manage"/>" class="table table-bordered wordbreak">
			<colgroup>
				<col style="width:20px"/>
				<col style="width:auto"/>
				<col style="width:auto"/>
				<col style="width:auto"/>
				<col style="width:auto"/>
				<col style="width:auto"/>
				<col style="width:auto"/>
				<col style="width:auto"/>
				<col style="width:auto"/>
				<col style="width:72px"/>
			</colgroup>
			<thead>
			<tr>
				<th scope="col">
					<input type="checkbox" name="chkAll" id="chkAll" value="N" style="border:0" onclick="checkAll()"/>
				</th>
				<th scope="col"><spring:message code="common.title.no"/></th>
				<th scope="col" >
				<c:choose>
					<c:when test="${fn:startsWith(studentVO.sortKey,'DECLS_NO') == true}">
						<c:if test="${studentVO.sortKey eq 'DECLS_NO_ASC'}">
					<a href="javascript:setSortKey('DECLS_NO_DESC')"><spring:message code="student.title.student.decls"/> <i class="fa fa-sort-asc"></i></a>
						</c:if>
						<c:if test="${studentVO.sortKey eq 'DECLS_NO_DESC'}">
					<a href="javascript:setSortKey('DECLS_NO_ASC')"><spring:message code="student.title.student.decls"/> <i class="fa fa-sort-desc"></i></a>
						</c:if>
					</c:when>
					<c:otherwise>
					<a href="javascript:setSortKey('DECLS_NO_ASC')"><spring:message code="student.title.student.decls"/> <i class="fa fa-sort" style="color:#ddd"></i></a>
					</c:otherwise>
				</c:choose>
				</th>
				<th scope="col" >
				<c:choose>
					<c:when test="${fn:startsWith(studentVO.sortKey,'USER_NM') == true}">
						<c:if test="${studentVO.sortKey eq 'USER_NM_ASC'}">
					<a href="javascript:setSortKey('USER_NM_DESC')"><spring:message code="user.title.userinfo.name"/> <i class="fa fa-sort-asc"></i></a>
						</c:if>
						<c:if test="${studentVO.sortKey eq 'USER_NM_DESC'}">
					<a href="javascript:setSortKey('USER_NM_ASC')"><spring:message code="user.title.userinfo.name"/> <i class="fa fa-sort-desc"></i></a>
						</c:if>
					</c:when>
					<c:otherwise>
					<a href="javascript:setSortKey('USER_NM_ASC')"><spring:message code="user.title.userinfo.name"/> <i class="fa fa-sort" style="color:#ddd"></i></a>
					</c:otherwise>
				</c:choose>
				</th>
				<th scope="col" >
				<c:choose>
					<c:when test="${fn:startsWith(studentVO.sortKey,'USER_ID') == true}">
						<c:if test="${studentVO.sortKey eq 'USER_ID_ASC'}">
					<a href="javascript:setSortKey('USER_ID_DESC')"><spring:message code="user.title.userinfo.userid"/> <i class="fa fa-sort-asc"></i></a>
						</c:if>
						<c:if test="${studentVO.sortKey eq 'USER_ID_DESC'}">
					<a href="javascript:setSortKey('USER_ID_ASC')"><spring:message code="user.title.userinfo.userid"/> <i class="fa fa-sort-desc"></i></a>
						</c:if>
					</c:when>
					<c:otherwise>
					<a href="javascript:setSortKey('USER_ID_ASC')"><spring:message code="user.title.userinfo.userid"/> <i class="fa fa-sort" style="color:#ddd"></i></a>
					</c:otherwise>
				</c:choose>
				</th>
				<th scope="col"><spring:message code="student.title.student.getscore"/></th>
				<th scope="col"><spring:message code="student.title.student.scoretop.user"/></th>
				<th scope="col"><spring:message code="student.title.student.completeno"/></th>
				<th scope="col"><spring:message code="common.title.stats"/></th>
				<th scope="col"><spring:message code="common.title.manage"/></th>
			</tr>
			</thead>
			<tbody>
				<c:set var="isDisabled" value=""/>
				<c:forEach var="item" items="${studentList}" varStatus="status">
				<tr>
					<td>
						<c:if test="${currentDate <= intEnddate || createCourseVO.cpltScore <= item.totScore}"><c:set var="isDisabled" value="disabled"/></c:if>
						<c:if test="${currentDate > intEnddate  || createCourseVO.cpltScore <= item.totScore}"><c:set var="isDisabled" value=""/></c:if>
						<input type='checkbox' name='sel' id='sel' value='${item.stdNo}' style='border:0' ${isDisabled }>

					</td>
					<td class="text-right">${pageInfo.totalRecordCount -(pageInfo.recordCountPerPage * (pageInfo.currentPageNo -1)) - status.count + 1}</td>
					<td class="text-right">${item.declsNo}</td>
					<td><a href="javascript:userInfo('${item.userNo}')">${item.userNm}</a></td>
					<td>${item.userId}</td>
					<td class="text-right">${item.totScore}</td>
					<td class="text-center">
						<!-- 취득점수가 0보다 클때 -->
						<c:if test="${item.totScore > 0 }">
							<c:choose>
								<c:when test="${item.scoreEcltYn eq 'Y' }">
									<c:set var="check" value="checked='checked'"/>
								</c:when>
								<c:otherwise>
									<c:set var="check" value=""/>
								</c:otherwise>
							</c:choose>
						<input type='checkbox' name='scoreEcltYn' id='scoreEcltYn' value='${item.stdNo}'style='border:0' ${check} onclick="topStudent(this);">
						</c:if>
					</td>
					<td>${item.cpltNo}</td>
					<td class="text-center"><meditag:codename code="${item.enrlSts}" category="ENRL_STS"/> </td>
					<td class="text-center">
						<a href="javascript:viewStudent('${item.stdNo}');" class="btn btn-info btn-sm"><spring:message code="button.manage"/> </a>
					</td>
				</tr>
				</c:forEach>
				<c:if test="${empty studentList}">
				<tr>
					<td colspan="12"><spring:message code="student.message.student.nodata"/></td>
				</tr>
				</c:if>
			</tbody>
		</table>
		<meditag:paging pageInfo="${pageInfo}" funcName="listStudent"/>
