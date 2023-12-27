<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="forumJoinUserVO" value="${forumJoinUserVO}"/>
<c:set var="forumJoinUserList" value="${forumJoinUserList}"/>
<c:set var="pageInfo" value="${pageInfo}"/>


			<table summary="<spring:message code="lecture.title.forum.rate.manage"/>" class="table table-bordered wordbreak">
				<colgroup>
					<col style="width:50px"/>
					<col style="width:auto"/>
					<col style="width:auto"/>
					<col style="width:auto"/>
					<col style="width:auto"/>
					<col style="width:auto"/>
					<col style="width:auto"/>
					<col style="width:120px;"/>
				</colgroup>
				<thead>
					<tr>
						<th scope="col"><spring:message code="common.title.no"/></th>
						<th scope="col"><spring:message code="student.title.student.decls"/></th>
						<th scope="col">
							<c:choose>
								<c:when test="${fn:startsWith(forumJoinUserVO.sortKey,'USER_NAME') == true}">
									<c:if test="${forumJoinUserVO.sortKey eq 'USER_NAME_ASC'}">
								<a href="javascript:setSortKey('USER_NAME_DESC')"><spring:message code="user.title.userinfo.name"/> <i class="fa fa-sort-asc"></i></a>
									</c:if>
									<c:if test="${forumJoinUserVO.sortKey eq 'USER_NAME_DESC'}">
								<a href="javascript:setSortKey('USER_NAME_ASC')"><spring:message code="user.title.userinfo.name"/> <i class="fa fa-sort-desc"></i></a>
									</c:if>
								</c:when>
								<c:otherwise>
								<a href="javascript:setSortKey('USER_NAME_ASC')"><spring:message code="user.title.userinfo.name"/> <i class="fa fa-sort" style="color:#ddd"></i></a>
								</c:otherwise>
							</c:choose>
						</th>
						<th scope="col">
							<c:choose>
								<c:when test="${fn:startsWith(forumJoinUserVO.sortKey,'USER_ID') == true}">
									<c:if test="${forumJoinUserVO.sortKey eq 'USER_ID_ASC'}">
								<a href="javascript:setSortKey('USER_ID_DESC')"><spring:message code="user.title.userinfo.userid"/> <i class="fa fa-sort-asc"></i></a>
									</c:if>
									<c:if test="${forumJoinUserVO.sortKey eq 'USER_ID_DESC'}">
								<a href="javascript:setSortKey('USER_ID_ASC')"><spring:message code="user.title.userinfo.userid"/> <i class="fa fa-sort-desc"></i></a>
									</c:if>
								</c:when>
								<c:otherwise>
								<a href="javascript:setSortKey('USER_ID_ASC')"><spring:message code="user.title.userinfo.userid"/> <i class="fa fa-sort" style="color:#ddd"></i></a>
								</c:otherwise>
							</c:choose>
						</th>
						<th scope="col">
							<c:choose>
								<c:when test="${fn:startsWith(forumJoinUserVO.sortKey,'ATCL_CNT') == true}">
									<c:if test="${forumJoinUserVO.sortKey eq 'ATCL_CNT_ASC'}">
								<a href="javascript:setSortKey('ATCL_CNT_DESC')"><spring:message code="lecture.title.forum.atcl.cnt"/> <i class="fa fa-sort-asc"></i></a>
									</c:if>
									<c:if test="${forumJoinUserVO.sortKey eq 'ATCL_CNT_DESC'}">
								<a href="javascript:setSortKey('ATCL_CNT_ASC')"><spring:message code="lecture.title.forum.atcl.cnt"/> <i class="fa fa-sort-desc"></i></a>
									</c:if>
								</c:when>
								<c:otherwise>
								<a href="javascript:setSortKey('ATCL_CNT_ASC')"><spring:message code="lecture.title.forum.atcl.cnt"/> <i class="fa fa-sort" style="color:#ddd"></i></a>
								</c:otherwise>
							</c:choose>
						</th>
						<th scope="col">
							<c:choose>
								<c:when test="${fn:startsWith(forumJoinUserVO.sortKey,'CMNT_CNT') == true}">
									<c:if test="${forumJoinUserVO.sortKey eq 'CMNT_CNT_ASC'}">
								<a href="javascript:setSortKey('CMNT_CNT_DESC')"><spring:message code="lecture.title.forum.cmnt.cnt"/> <i class="fa fa-sort-asc"></i></a>
									</c:if>
									<c:if test="${forumJoinUserVO.sortKey eq 'CMNT_CNT_DESC'}">
								<a href="javascript:setSortKey('CMNT_CNT_ASC')"><spring:message code="lecture.title.forum.cmnt.cnt"/> <i class="fa fa-sort-desc"></i></a>
									</c:if>
								</c:when>
								<c:otherwise>
								<a href="javascript:setSortKey('CMNT_CNT_ASC')"><spring:message code="lecture.title.forum.cmnt.cnt"/> <i class="fa fa-sort" style="color:#ddd"></i></a>
								</c:otherwise>
							</c:choose>
						</th>
						<th scope="col">
							<c:choose>
								<c:when test="${fn:startsWith(forumJoinUserVO.sortKey,'SCORE') == true}">
									<c:if test="${forumJoinUserVO.sortKey eq 'SCORE_ASC'}">
								<a href="javascript:setSortKey('SCORE_DESC')"><spring:message code="lecture.title.forum.rate.score"/> <i class="fa fa-sort-asc"></i></a>
									</c:if>
									<c:if test="${forumJoinUserVO.sortKey eq 'SCORE_DESC'}">
								<a href="javascript:setSortKey('SCORE_ASC')"><spring:message code="lecture.title.forum.rate.score"/> <i class="fa fa-sort-desc"></i></a>
									</c:if>
								</c:when>
								<c:otherwise>
								<a href="javascript:setSortKey('SCORE_ASC')"><spring:message code="lecture.title.forum.rate.score"/> <i class="fa fa-sort" style="color:#ddd"></i></a>
								</c:otherwise>
							</c:choose>
						</th>
						<th scope="col"><spring:message code="lecture.title.forum.rate"/></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${forumJoinUserList}" varStatus="status">
					<tr>
						<td class="text-right">${pageInfo.totalRecordCount -(pageInfo.recordCountPerPage * (pageInfo.currentPageNo -1)) - status.count + 1}<input type="hidden" name="selStd" value="${item.stdNo}"></td>
						<td class="text-right">${item.declsNo}</td>
						<td>${item.userNm}</td>
						<td>${item.userId}</td>
						<td class="text-right">${item.atclCnt}</td>
						<td class="text-right">${item.cmntCnt}</td>
						<td class="text-right">${item.score}</td>
						<td class="text-center">
							<div class="input-group" style="widtH:100px;">
								<input type="text" name="score" style="text-align:right;" class="form-control input-sm inputNumber" onfocus="this.select()">
								<span class="input-group-addon" onClick="addScore(${status.count -1})" style="cursor:pointer">
									<spring:message code="button.add"/>
								</span>
							</div>
						</td>
					</tr>
					</c:forEach>
					<c:if test="${empty forumJoinUserList}">
					<tr>
						<td colspan="8"><spring:message code="student.message.student.nodata"/></td>
					</tr>
					</c:if>
				</tbody>
			</table>
			<meditag:paging pageInfo="${pageInfo}" funcName="listStd"/>


