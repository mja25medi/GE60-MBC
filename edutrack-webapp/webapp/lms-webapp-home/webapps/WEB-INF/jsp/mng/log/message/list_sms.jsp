<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="messageList" value="${messageForm.messageList}"/>
<c:set var="pageInfo" value="${messageForm.pageInfo}"/>
<mhtml:html>
<mhtml:head titleName="${MENUNAME}">

</mhtml:head>

<mhtml:frm_body>

	<table class="table_list" summary="메시지 발송리스트" style="width: 100%">
		<colgroup>
			<col style="width:60px;"/>
			<col style="width:auto;"/>
			<col style="width:100px;"/>
			<col style="width:120px;"/>
			<col style="width:100px;"/>
			<col style="width:80px;"/>
			<col style="width:120px;"/>
		</colgroup>
		<thead>
			<tr>
				<th scope='col' >번호</th>
				<th scope='col' >내 용</th>
				<th scope='col' >발신상태<br>[완료/전체]</th>
				<th scope='col' >발신 메뉴</th>
				<th scope='col' >발신자번호</th>
				<th scope='col' >등록자</th>
				<th scope='col' >등록일시</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${messageList}" varStatus="status">
			<tr>
				<td>${item.msgSn}</td>
				<td><a href="/MessageLogAdmin.do?cmd=viewMessage&amp;messageDTO.msgSn=${item.msgSn}">${item.cts}</a></td>
				<c:if test="${item.transFailCount > 0}">
				<td align='center'>[<span class='accent'>${item.transSuccessCount}</span>(<span class='accent'>${item.transDenialCount}</span>)/<span class='accent'>${item.transCount}</span>]</td>
				</c:if>
				<c:if test="${item.transFailCount <= 0}">
				<td align='center' class='b_td02'>[<span class='wblue'>${item.transSuccessCount}</span>(<span class='wgray'>${item.transDenialCount}</span>)/${item.transCount}]</td>
				</c:if>
				<td>${item.sendMenuCdNm}</td>
				<td>${item.sendAddr}</td>
				<td>${item.regNm}</td>
				<td><meditag:dateformat type="1" delimeter="." property="${item.regDttm}"/></td>
			</tr>
			</c:forEach>
			<c:if test="${empty messageList}">
			<tr>
				<td colspan="7">* 등록된 메시지 로그가 없습니다.</td>
			</tr>
			</c:if>
		</tbody>
	</table>
	<meditag:paging pageInfo="${pageInfo}" funcName="listPageing"/>
</mhtml:frm_body>
</mhtml:html>

