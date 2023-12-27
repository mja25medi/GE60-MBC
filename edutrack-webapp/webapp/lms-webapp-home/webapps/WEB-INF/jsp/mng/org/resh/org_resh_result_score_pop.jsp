<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
		
	<table class="table table-bordered wordbreak" style="margin-top: 5px;">
     		<caption class="sr-only"><spring:message code="lecture.title.research.info"/></caption>
     		<colgroup>
     			<col style="width:20%;"/>
     			<col style="width:80%;"/>
     		</colgroup>
     		<tbody>
		<tr>
			<th>응시회원수</th>
			<td>
				${vo.reshCnt}
			</td>
		</tr>
		<tr>
			<th>종합점수</th>
			<td id="sumScoreTd">0</td>
		</tr>
		</tbody>
	</table>

	<ul class="list-gruop" style="padding-left:0px;margin-top: 20px;">
		<li class="list-group-item">
			<div>문제별점수</div>
			<c:set var="sumScore" value="0"/>
			<table style="width:100%;" class="table table-bordered">
				<colgroup>
					<col style="width:40%"/>
					<col style="width:20%"/>
					<col style="width:40%"/>
				</colgroup>
				<thead>
					<tr>
						<th scope="col" style="background-color:#f5f5f5">문제 번호</th>
						<th scope="col" style="background-color:#f5f5f5">문제 유형</th>
						<th scope="col" style="background-color:#f5f5f5">점수</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach var="item" items="${ansrList}" varStatus="status">
				<c:if test="${item.reshType eq 'I' and item.reshItemTypeCd eq 'K'}">	<!-- 설문 타입 점수형 이고 선택형 일 경우 -->
				<tr>
					<td class="text-center">${item.itemOdr}</td>
					<td class="text-center">선택형</td>
					<td class="text-center">${item.avgReshAnsrScore}점</td>
					<c:set var="sumScore" value="${sumScore + item.avgReshAnsrScore}"/>	<!-- 종합점수 구하기  -->
				</tr>
				</c:if>
				<c:if test="${item.reshType ne 'I' or item.reshItemTypeCd eq 'D'}">		<!-- 설문 타입 일반형 이거나 점수형 선택형 일 경우 -->
				<tr>
					<td class="text-center">${item.itemOdr}</td>
					<td class="text-center">${item.reshItemTypeCd eq 'K' ? '선택형' : '서술형' }</td>
					<td class="text-center">-</td>
				</tr>
				</c:if>
				</c:forEach>
				<c:if test="${empty ansrList}">
					<tr>
						<td colspan="3">* 등록된 설문 정보가 없습니다.</td>
					</tr>
				</c:if>
				</tbody>
			</table>
		</li>
	</ul>

	<div class="text-center">
		<a href="#none" onclick="parent.modalBoxClose();" class="btn btn-default btn-sm"><spring:message code="button.close"/></a>
	</div>

<script type="text/javascript">
	$(document).ready(function() {
		$("#sumScoreTd").text('${sumScore}');
	});
</script>
