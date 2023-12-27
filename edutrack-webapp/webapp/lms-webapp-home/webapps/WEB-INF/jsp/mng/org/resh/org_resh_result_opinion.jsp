<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<%pageContext.setAttribute("crlf", "\n");%>

	<div class="well" style="word-break:break-all;word-wrap: break-word;">
		${vo.reshItemCts}
	</div>
	<table class="table table-bordered wordbreak">
		<thead>
			<tr>
				<c:if test="${vo.reshAnsrTypeCd eq 'K'}">
				<th scope="col" width="10%"><spring:message code="course.title.exambank.item.no"/></th>
			    </c:if>
			    <th scope="col" width="15%">
			    	<c:choose>
						<c:when test="${fn:startsWith(vo.sortKey,'USER_ID') == true}">
							<c:if test="${vo.sortKey eq 'USER_ID_ASC'}">
						<a href="javascript:setSortKey('USER_ID_DESC')"><spring:message code="user.title.userinfo.userid"/> <i class="fa fa-sort-asc"></i></a>
							</c:if>
							<c:if test="${vo.sortKey eq 'USER_ID_DESC'}">
						<a href="javascript:setSortKey('USER_ID_ASC')"><spring:message code="user.title.userinfo.userid"/> <i class="fa fa-sort-desc"></i></a>
							</c:if>
						</c:when>
						<c:otherwise>
						<a href="javascript:setSortKey('USER_ID_ASC')"><spring:message code="user.title.userinfo.userid"/> <i class="fa fa-sort" style="color:#ddd"></i></a>
						</c:otherwise>
					</c:choose>
			    </th>
			    <th scope="col" width="15%">
			    	<c:choose>
						<c:when test="${fn:startsWith(vo.sortKey,'USER_NM') == true}">
							<c:if test="${vo.sortKey eq 'USER_NM_ASC'}">
						<a href="javascript:setSortKey('USER_NM_DESC')"><spring:message code="user.title.userinfo.name"/> <i class="fa fa-sort-asc"></i></a>
							</c:if>
							<c:if test="${vo.sortKey eq 'USER_NM_DESC'}">
						<a href="javascript:setSortKey('USER_NM_ASC')"><spring:message code="user.title.userinfo.name"/> <i class="fa fa-sort-desc"></i></a>
							</c:if>
						</c:when>
						<c:otherwise>
						<a href="javascript:setSortKey('USER_NM_ASC')"><spring:message code="user.title.userinfo.name"/> <i class="fa fa-sort" style="color:#ddd"></i></a>
						</c:otherwise>
					</c:choose>
			    </th>
			    <c:if test="${vo.reshAnsrTypeCd eq 'D'}">
			    <th scope="col" width="auto"><spring:message code="lecture.title.research.opinion"/></th>
			    </c:if>
			    <th scope="col" width="20%"><spring:message code="common.title.regtime"/></th>
			</tr>
		</thead>
		<tbody>
		<c:choose>
			<c:when test="${empty crsReshAnsrList}">
				<c:set var="colspanCnt" value="3" />
				<c:if test="${vo.reshAnsrTypeCd eq 'D'}">
					<c:set var="colspanCnt" value="4" />
				</c:if>
				<tr>
					<td colspan="${colspanCnt }" class="text-center">
						 <font color="red"><spring:message code="lecture.message.research.opinion.nodata"/></font>
					</td>
				</tr>
			</c:when>
			<c:otherwise>
				<c:forEach items="${crsReshAnsrList}" var="item" varStatus="status">
					<tr>
						<c:if test="${vo.reshAnsrTypeCd eq 'K'}">
						<td class="text-right">${vo.reshAnsr}</td>
						</c:if>
						<td class="text-center">
							${item.userId}
						</td>
						<td class="text-center">${item.userNm}</td>
						<c:if test="${vo.reshAnsrTypeCd eq 'D'}">
						<td class="wordbreak">${fn:replace(item.reshAnsr,crlf,"<br/>")}</td>
						</c:if>
						<td><meditag:dateformat type="8" delimeter="." property="${item.regDttm}"/></td>
				  </tr>
			   </c:forEach>
			</c:otherwise>
		</c:choose>
		</tbody>
	</table>

	<div class="text-center">
		<a href="#none" onclick="resultResh()" class="btn btn-default btn-sm"><spring:message code="button.close"/></a>
	</div>

<script type="text/javascript">

	var ItemDTO = {sortKey : "",sortKey2 : ""}

	$(document).ready(function() {

	});

	function resultResh(){
		location.href="/mng/org/research/rsltPop"
			+"?reshTitle=${vo.reshTitle}"
			+"${AMPERSAND}reshSn=${vo.reshSn}"
			+"${AMPERSAND}regYn=${vo.regYn}"
			+"${AMPERSAND}searchValue=${vo.searchValue}";
	}

	function setSortKey(sortKey) {
		ItemDTO.sortKey = sortKey;
		listOpinion(1);
	}

	function listOpinion(page) {
		location.href="/mng/org/research/mainOpinionPop"
				+"?reshItemCts=${vo.reshItemCts}"
				+"${AMPERSAND}reshItemSn=${vo.reshItemSn}"
				+"${AMPERSAND}reshSn=${vo.reshSn}"
				+"${AMPERSAND}reshAnsr=${vo.reshAnsr}"
				+"${AMPERSAND}reshAnsrTypeCd=${vo.reshAnsrTypeCd}"
				+"${AMPERSAND}reshTitle=${vo.reshTitle}"
				+"${AMPERSAND}regYn=${vo.regYn}"
				+"${AMPERSAND}searchValue=${vo.searchValue}"
				+"${AMPERSAND}sortKey="+ItemDTO.sortKey;
	}

</script>
