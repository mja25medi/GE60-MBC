<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>

<div style="width:96%;margin:0 auto">
	<form name="bbsHeadForm" id="bbsHeadForm" onsubmit="return false;" method="POST">
	<input type="hidden" name="orgCd" id="orgCd" value="${vo.orgCd}"/>
	<input type="hidden" name="bbsCd" id="bbsCd" value="${vo.bbsCd}"/>
	<input type="hidden" name="headOdr" id="headOdr" value="${vo.headOdr}"/>
	<table class="table table-bordered">
		<colgroup>
			<col style="width:30%"/>
			<col style="width:70%"/>
		</colgroup>
		<tr>
			<th scope="row"><spring:message code="board.title.bbs.info.bbsnm"/></th>
			<td>${infovo.bbsNm}</td>
		</tr>
        <tr>
			<th scope="row"><label for="headCd"><spring:message code="board.title.bbs.head.code"/></label></th>
			<td>
				<c:if test="${gubun eq 'A'}">
				<input type="text" name="headCd" id="headCd" maxlength="10" required="required" onkeyup="isChkCharacter(this)" title="<spring:message code="board.title.bbs.head.code"/>" class="form-control input-sm" style="width:100px;" value="${vo.headCd}"/>
				<spring:message code="common.message.code.warning.info"/>
				</c:if>
				<c:if test="${gubun eq 'E'}">
				<input type="hidden" name="headCd" id="headCd" value="${vo.headCd}"/>
				${vo.headCd}
				</c:if>
			</td>
		</tr>
        <tr>
			<th scope="row"><label for="headNm"><spring:message code="board.title.bbs.head.name"/></label></th>
			<td>
				<input type="text" name="headNm" id="headNm" maxlength="100" required="required" title="<spring:message code="board.title.bbs.head.name"/>" class="form-control input-sm" value="${vo.headNm}"/>
			</td>
		</tr>
		<tr>
			<th scope="row"><spring:message code="common.title.useyn"/></th>
			<td>
				<label style="font-weight: normal;">
					<input type="radio" name="useYn" value="Y" <c:if test="${vo.useYn eq 'Y'}">checked</c:if> /> <spring:message code="common.title.useyn_y"/>
				</label>
				<label style="font-weight: normal; margin-left:10px;">
					<input type="radio" name="useYn" value="N" <c:if test="${vo.useYn eq 'N'}">checked</c:if> /> <spring:message code="common.title.useyn_n"/>
				</label>
			</td>
		</tr>
	</table>
	<div style="width:100%;margin-top:10px;" class="text-right">
		<c:if test="${gubun eq 'A' }">
		<a class="btn btn-primary btn-sm" href="javascript:addHead()" ><spring:message code="button.add" /></a>
		</c:if>
		<c:if test="${gubun eq 'E' }">
		<a class="btn btn-primary btn-sm" href="javascript:editHead()" ><spring:message code="button.add" /></a>
		<a class="btn btn-warning btn-sm" href="javascript:delHead()" ><spring:message code="button.delete" /></a>
		</c:if>
		<a class="btn btn-default btn-sm" href="javascript:goList()" ><spring:message code="button.close" /></a>
	</div>
	</form>
</div>

<script type="text/javascript">
	function goList() {
		document.location.href = "/adm/brd/bbs/listHeadPop?orgCd=${vo.orgCd}&bbsCd=${vo.bbsCd}"
	}

	function addHead() {
		if(!validate(document.bbsHeadForm)) return;
		$('#bbsHeadForm').attr("action","/adm/brd/bbs/addHead");
		$("#bbsHeadForm")[0].submit();
	}

	function editHead() {
		if(!validate(document.bbsHeadForm)) return;
		$('#bbsHeadForm').attr("action","/adm/brd/bbs/editHead");
		$("#bbsHeadForm")[0].submit();
	}

	function delHead() {
		if(!validate(document.bbsHeadForm)) return;
		$('#bbsHeadForm').attr("action","/adm/brd/bbs/removeHead");
		$("#bbsHeadForm")[0].submit();
	}
</script>
