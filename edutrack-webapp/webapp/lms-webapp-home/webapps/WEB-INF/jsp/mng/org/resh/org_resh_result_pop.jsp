<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="examStareVO" value="${vo}"/>

	<form id="reshResultForm" name="reshResultForm" style="overflow-y:auto;height: 500px">
	<div style=" height:520px;margin-top:10px;">
	<ul class="list-group">
	<c:forEach items="${ansrList}" var="item" varStatus="status">
		<li class="list-group-item">
			<p style="word-break:break-all;word-wrap: break-word;"">${status.count}. ${item.reshItemCts}</p>
			<div class="well well-sm" style="margin-top:10px;">
				<span style="margin-bottom:10px;word-break:break-all;word-wrap: break-word;">${item.reshItemCts}</span>
				<table style="width:100%;border-top:1px solid gray;background-color:#fff;margin-top:10px;word-break:break-all;word-wrap: break-word;">
					<colgroup>
						<col style="width:15%;"/>
						<col />
					</colgroup>
					<tr>
						<td style="border:1px solid gray;padding:5px;"><spring:message code="lecture.title.exam.question.rightanswer"/></td>
						<c:if test="${item.reshItemTypeCd ne 'K'}">
							<td style="border:1px solid gray;padding:5px;">${item.reshAnsr}</td>
						</c:if>
						<c:if test="${item.reshItemTypeCd eq 'K'}">
							<td style="border:1px solid gray;padding:5px;">${item.reshItemCtsK}</td>
						</c:if>
					</tr>
				</table>
			</div>
		</li>
	</c:forEach>
	</ul>
	</form>