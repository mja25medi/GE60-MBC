<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="img_base" value="/img/home" />
<c:set var="itemList" value="${researchForm.researchListDTO}"/>
<mhtml:class_html>
<mhtml:class_head>

</mhtml:class_head>
<mhtml:class_body>
	<div id="content">
		<mhtml:class_location />

		<form name="Search" id="Search" action="/ResearchLecture.do">
		<table class="board_a">
			<caption>설문 목록</caption>
               <colgroup>
                <col width="5%" />
                <col width="auto" />
                <col width="22%" />
                <col width="10%" />
               </colgroup>
			<thead>
				<tr>
					<th scope="col">번호</th>
					<th scope="col" >설문제목</th>
					<th scope="col">설문기간</th>
					<th scope="col" class="rnone">참여</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${itemList}" varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td class="subject">${item.reshTitle}</td>
					<td><meditag:dateformat type="1" delimeter="." property="${item.regDttm}"/></td>
					<td>
						<c:if test="${item.joinYn eq 'N' }">
						<a href="<c:url value="/ResearchLecture.do"/>?cmd=joinFormResearch&amp;researchDTO.reshSn=${item.reshSn}" class="btn_org"><span>참여</span></a>
						</c:if>
						<c:if test="${item.joinYn ne 'N' }">
						<a href='#' onclick='val();' class="btn_gr"><span>참여</span></a>
						</c:if>
					</td>
				</tr>
				</c:forEach>
				<c:if test="${empty itemList}">
				<tr>
					<td colspan="4">* 등록된 설문이 없습니다.</td>
				</tr>
				</c:if>
			</tbody>
		</table>
		</form>

	</div>

<script type="text/javascript">
	var ItemDTO = new Object();
	$(document).ready(function() {

	});

	function val(){
		alert("이미 참여 중입니다");
		return false;
	}

</script>
</mhtml:class_body>
</mhtml:class_html>