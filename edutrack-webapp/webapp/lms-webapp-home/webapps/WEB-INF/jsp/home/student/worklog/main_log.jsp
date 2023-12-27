<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="stdEduRsltWorkLogVO" value="${vo}"/>
<c:set var="stdEduRsltWorkLogList" value="${stdEduRsltWorkLogList}"/>
<c:set var="pageInfo" value="${pageInfo}"/>
<mhtml:home_html>
<mhtml:home_head titleName="${MENUNAME}">
	<mhtml:head_module paging="y"/>
</mhtml:home_head>
<mhtml:frm_body cssTag="background-color:#ffffff;">
<form name="Search" id="Search" onsubmit="return false;">
<div style="width:100%">
	<div class="input-group" style="float:left">
		<select name="regDivCd" id="regDivCd" class="form-control input-sm">
			<option value=""><spring:message code="common.title.select.all"/></option>
			<c:forEach var="item" items="${codeList}">
				<c:set var="codeName" value="${item.codeNm}"/>
				<c:forEach var="lang" items="${item.codeLangList}">
					<c:if test="${lang.langCd eq LOCALEKEY }"><c:set var="codeName" value="${lang.codeNm}"/></c:if>
				</c:forEach>
			<option value="${item.codeCd}" <c:if test="${item.codeCd eq vo.regDivCd}">selected="selected"</c:if>>${codeName}</option>
			</c:forEach>
		</select>
	</div>
	<div class="input-group" style="float:left;width:200px;">
		<input type="text" name="searchValue" id="searchValue" class="_enterBind form-control input-sm" placeholder="<spring:message code="user.title.userinfo.name"/>" value="${vo.searchValue}"/>
		<span class="input-group-addon" onclick="listPageing(1)" style="cursor:pointer">
			<i class="fa fa-search"></i>
		</span>
	</div>
	<div class="clearfix"></div>
</div>
</form>
<c:if test="${not empty stdEduRsltWorkLogList}">
<div style="width:100%;height:360px;overflow-y:auto;margin-top:5px;">
	<table class="table table-bordered">
		<tr>
			<th><spring:message code="common.title.regdate"/></th>
			<th><spring:message code="user.title.userinfo.name"/></th>
			<th><spring:message code="log.title.common.workdiv"/></th>
		</tr>
	<c:forEach var="item" items="${stdEduRsltWorkLogList}">
		<tr>
			<td><meditag:dateformat type="8" delimeter="." property="${item.regDttm}" /> <a href="javascript:viewLog('${item.regDttm}');" class="btn btn-xs"><i id="btn_${item.regDttm}" class="fa fa-chevron-down fabtn"></i></a></td>
			<td class="text-center">${item.regNm}</td>
			<td class="text-center"><meditag:codename category="SCORE_PRSS_MTHD" code="${item.regDivCd}"/></td>
		</tr>
		<tr id="cts_${item.regDttm}" class="cts" style="display:none">
			<td colspan="3" style="background-color:#FEDFDF;">
				${item.regCts}
			</td>
		</tr>
	</c:forEach>
	</table>
</div>
<meditag:paging pageInfo="${pageInfo}" funcName="listPageing"/>
</c:if>
<c:if test="${empty stdEduRsltWorkLogList}">
<div class="well well-sm text-center">
	<spring:message code="common.message.nodata"/>
</div>
</c:if>
<script type="text/javascript">
	$(document).ready(function() {

	});

	function viewLog(regdate) {
		var btnSts = "down";
		if($("#btn_"+regdate).hasClass("fa-chevron-up") === true) {
			btnSts = "up";
		}
		if(btnSts == "down") {
			$(".cts").hide();
			$("#cts_"+regdate).show();

			//
			$(".fabtn").removeClass("fa-chevron-up");
			$(".fabtn").addClass("fa-chevron-down");

			$("#btn_"+regdate).removeClass("fa-chevron-down");
			$("#btn_"+regdate).addClass("fa-chevron-up");

		} else {
			$(".cts").hide();
			$("#btn_"+regdate).removeClass("fa-chevron-up");
			$("#btn_"+regdate).addClass("fa-chevron-down");
		}
	}

	function listPageing(page) {
		var regDivCd = $("#regDivCd option:selected").val();
		var searchValue = $("#searchValue").val();
		var url = generateUrl("/lec/std/eduRsltWorkLog/main", { "crsCreCd":"${vo.crsCreCd}", "curPage":page, "regDivCd": regDivCd, "searchValue":searchValue});
		document.location.href = url;
	}
</script>
</mhtml:frm_body>
</mhtml:home_html>