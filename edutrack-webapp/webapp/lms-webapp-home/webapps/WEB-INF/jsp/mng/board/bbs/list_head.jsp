<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>

<div style="width:96%;margin:0 auto;">
	<div style="float:left; width:100%; height:40px; line-height:30px;">
		<div style="float:left">
			<h4>${bbsInfoVO.bbsNm}</h4>
		</div>
		<div style="float:right;margin-top:4px;">
			<button class="btn btn-primary btn-sm" onclick="addHead();"><spring:message code="button.write"/></button>
			<button class="btn btn-default btn-sm" onclick="parent.modalBoxClose();" ><spring:message code="button.close"/></button>
		</div>
	</div>
	<table summary="<spring:message code="board.title.bbs.head.manage"/>" class="table table-bordered wordbreak">
		<colgroup>
			<col style="width:80px"/>
			<col style="width:auto"/>
			<col style="width:80px"/>
			<col style="width:50px"/>
		</colgroup>
		<thead>
		<tr>
			<th scope="col"><spring:message code="board.title.bbs.head.code"/></th>
			<th scope="col"><spring:message code="board.title.bbs.head.name"/></th>
			<th scope="col"><spring:message code="common.title.useyn"/></th>
			<th scope="col"><spring:message code="common.title.edit"/></th>
		</tr>
		</thead>
		<tbody id="tbodyList">
		<c:forEach var="item" items="${bbsHeadList}" varStatus="status">
		<tr>
			<td>${item.headCd}</td>
			<td>${item.headNm}</td>
			<td><meditag:codename code="${item.useYn}" category="USE_YN"/></td>
			<td><a href="javascript:editHead('${item.headCd}');" class="btn btn-primary btn-sm"><spring:message code="button.edit"/></a></td>
		</tr>
		</c:forEach>
		<c:if test="${empty bbsHeadList}">
		<tr>
			<td colspan="4"><spring:message code="board.message.bbs.head.nodata"/></td>
		</tr>
		</c:if>
		</tbody>
	</table>
</div>
<script type="text/javascript">

	function addHead() {
		document.location.href = generateUrl("/mng/brd/bbs/addFormHeadPop",{ "orgCd":"${USER_ORGCD}", "bbsCd":"${vo.bbsCd}"});
	}
	
	function editHead(headCd) {
		document.location.href = generateUrl("/mng/brd/bbs/editFormHeadPop",{ "orgCd":"${USER_ORGCD}", "bbsCd":"${vo.bbsCd}", "headCd":headCd});
	}
</script>
