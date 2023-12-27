<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<%pageContext.setAttribute("crlf", "\n");%>
<c:set var="crsReshAnsrVO" value="${crsReshAnsrVO}" />
<c:set var="crsReshAnsrList" value="${crsReshResultList}" />
<c:url var="img_base" value="/img/home" />
<script src="../js/iframeResizer.contentWindow.min.js"></script>
<body style="background-color:#fff;" class="scroll_custom">
	<div class="modal_cont">
		<div class="board_top">
			<h4>${reshBankItemVO.reshItemCts}</h4>
	    </div>
		<div class="res_tbl_wrap">
			<table>
				<caption>설문결과 목록</caption>
	               <thead>
	                   <tr>
	                       <th scope="col" width="8%"><spring:message code="common.title.no"/></th>
	                       <th scope="col">
	                       	<c:choose>
								<c:when test="${fn:startsWith(crsReshAnsrVO.sortKey,'USER_ID') == true}">
									<c:if test="${crsReshAnsrVO.sortKey eq 'USER_ID_ASC'}">
										<a href="javascript:setSortKey('USER_ID_DESC')"><spring:message code="user.title.userinfo.userid"/> <i class="fa fa-sort-asc"></i></a>
									</c:if>
									<c:if test="${crsReshAnsrVO.sortKey eq 'USER_ID_DESC'}">
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
								<c:when test="${fn:startsWith(crsReshAnsrVO.sortKey,'USER_NM') == true}">
									<c:if test="${crsReshAnsrVO.sortKey eq 'USER_NM_ASC'}">
										<a href="javascript:setSortKey('USER_NM_DESC')"><spring:message code="user.title.userinfo.name"/> <i class="fa fa-sort-asc"></i></a>
									</c:if>
									<c:if test="${crsReshAnsrVO.sortKey eq 'USER_NM_DESC'}">
										<a href="javascript:setSortKey('USER_NM_ASC')"><spring:message code="user.title.userinfo.name"/> <i class="fa fa-sort-desc"></i></a>
									</c:if>
								</c:when>
								<c:otherwise>
									<a href="javascript:setSortKey('USER_NM_ASC')"><spring:message code="user.title.userinfo.name"/> <i class="fa fa-sort" style="color:#ddd"></i></a>
								</c:otherwise>
							</c:choose>
	                       </th>
	                       <th scope="col" width="50%">
	                       	<c:if test="${crsReshAnsrVO.reshAnsrTypeCd eq 'D'}">
	                       		<spring:message code="lecture.title.research.opinion"/>
	                       	</c:if>
	                       </th>
	                       <th scope="col" width="15%"><spring:message code="common.title.regtime"/></th>
	                   </tr>
	               </thead>
	               
	               <tbody>
					<c:choose>
						<c:when test="${empty crsReshAnsrList}">
							<tr>
								<td colspan="5" class="no-list">
									<spring:message code="lecture.message.research.opinion.nodata"/>
								</td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach items="${crsReshAnsrList}" var="item" varStatus="status">
								<tr>
		                        	<td scope="row" data-label="번호">${status.count}</td>
			                        <td style="text-align: center;" class="title" data-label="아이디">${item.userId}</td>
			                        <td style="text-align: center;" class="title" data-label="이름">${item.userNm}</td>
		                        	<c:if test="${crsReshAnsrVO.reshAnsrTypeCd eq 'D'}">
				                        <td class="title" data-label="답변">
											${fn:replace(item.reshAnsr,crlf,"<br/>")}
				                        </td>
									</c:if>
			                        <td data-label="등록일시"><meditag:dateformat type="8" delimeter="." property="${item.regDttm}"/></td>
			                    </tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>

		<br>
		 <div class="modal_btns">
	        <button type="button" onclick="parent.modalBoxClose();" class="btn btn-default btn-sm""><spring:message code="button.close"/></button>
	    </div>
	</div>

<script type="text/javascript">
	var ItemDTO = {sortKey : "",sortKey2 : ""}

	$(document).ready(function() {
		$("body").css("padding-top","0px").css("min-height","0px");
	});

	function setSortKey(sortKey) {
		ItemDTO.sortKey = sortKey;
		listOpinion(1);
	}

	function listOpinion(page) {
		location.href="/lec/creCrsResh/mainOpinionPop"
				+"?crsCreCd=${crsReshAnsrVO.crsCreCd}"
				+"${AMPERSAND}reshItemSn=${crsReshAnsrVO.reshItemSn}"
				+"${AMPERSAND}reshSn=${crsReshAnsrVO.reshSn}"
				+"${AMPERSAND}reshAnsr=${crsReshAnsrVO.reshAnsr}"
				+"${AMPERSAND}reshAnsrTypeCd=${crsReshAnsrVO.reshAnsrTypeCd}"
				+"${AMPERSAND}sortKey="+ItemDTO.sortKey;
	}
</script>
</body>
