<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="itemList" value="${researchForm.researchAnswerListDTO}"/>
<c:set var="researchDTO" value="${researchForm.researchDTO}"/>
<c:set var="pageInfo" value="${researchForm.pageInfo}"/>
<c:url var="img_base" value="/img/home" />
<mhtml:home_html>
<mhtml:home_head titleName="${MENUNAME}">
	<meditag:css href="css/home/pop.css"/>

</mhtml:home_head>
<body>
	<div class="wrap" style="width:754px;">
		<h1>설문답변</h1>
		<div class="contents">

			<nested:form action="/ResearchLecture.do" onsubmit="return false" id="forumForm">
			<input type="hidden" name="cmd"/>
			<input type="hidden" property="curPage" />

			<p class="d_note"><strong>설문명 :</strong> ${researchDTO.reshTitle}</p>
			<p class="d_note"><strong>설문기간 :</strong>${researchDTO.reshStartDttm} ~ ${researchDTO.reshEndDttm}</p>

			<div class="btn_right" style="margin-top:10px;margin-bottom:10px;">
				<a href="#" onclick="self.close();" class="btn02">닫기</a>
			</div>
			<table  class="board_a" width="90%">
				<caption>학습자 목록</caption>
				<thead>
					<tr>
						<th scope="col" width="5%">번호</th>
					    <th scope="col" width="10%">아이디</th>
					    <th scope="col" width="10%">성명</th>
					    <th scope="col" width="auto">등록된 의견</th>
					</tr>
				</thead>
				<tbody id="tbodyList">
				<c:choose>
					<c:when test="${empty itemList}">
						<tr>
							<td colspan="6" class="rnone">
								 <font color="red"> 등록된 토론이 없습니다.</font>
							</td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach items="${itemList}" var="item" varStatus="status">
							<tr>
								<td>${pageInfo.totalRecordCount - ((pageInfo.currentPageNo - 1 ) * pageInfo.recordCountPerPage + status.index)}</td>
								<td class="subject">
									${item.userId}
								</td>
								<td>${item.userNm}</td>
								<td height="70px">${item.etcOpinion}</td>
						  </tr>
					   </c:forEach>
					</c:otherwise>
				</c:choose>
				</tbody>
			</table>
			</nested:form>
		</div>
	</div>
</body>
</mhtml:home_html>