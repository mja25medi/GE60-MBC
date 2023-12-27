<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<mhtml:home_html>
<mhtml:home_head titleName="${MENUNAME}">

</mhtml:home_head>

<body style="padding-top: 0px;">

	<form id="clibCntsCtgrForm" name="clibCntsCtgrForm" onsubmit="return false" action="/home/library/clibCntsCtgr">
	<div style="width:100%;margin-bottom:5px;" class="text-right">
		<a href="javascript:addCtgr()" class="btn btn-primary btn-sm"><spring:message code="button.write"/></a>
		<a href="javascript:parent.modalBoxClose()" class="btn btn-default btn-sm"><spring:message code="button.close"/></a>
	</div>
	<table class="table table-bordered wordbreak">
		<caption class="sr-only"><spring:message code="user.title.userinfo.change.password"/></caption>
		<colgroup>
			<col />
			<col style="width:75px;" />
		</colgroup>
		<thead>
			<tr>
				<th scope="col"><spring:message code="library.title.contents.category.name"/></th>
				<th scope="col"><spring:message code="common.title.edit"/></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${ctgrSubList}" var="item" varStatus="status">
			<tr>
				<td>${item.ctgrNm}</td>
				<td class="text-center"><a href="javascript:editCtgr('${item.ctgrCd}')" class="btn btn-info btn-sm"><spring:message code="button.edit"/></a></td>
			</tr>
			</c:forEach>
			<c:if test="${empty ctgrSubList}">
			<tr>
				<td colspan="3"><spring:message code="common.message.nodata"/></td>
			</tr>
			</c:if>
		</tbody>
	</table>
	</form>

<script type="text/javascript">
	// 페이지 초기화
	$(document).ready(function() {

	});

	function addCtgr(){
		document.location = "/home/library/clibCntsCtgr/addCtrgForm";
	}

	function editCtgr(ctgrCd){
		document.location = "/home/library/clibCntsCtgr/editCtrgForm?ctgrCd="+ctgrCd;
	}
</script>
</body>
</mhtml:home_html>
