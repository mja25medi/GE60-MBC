<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="researchBankVO" value="${vo}"/>


	<form id="researchBankForm" name="researchBankForm" onsubmit="return false" action="/mng/course/researchBank">
	<input type="hidden" name="reshSn" value="${vo.reshSn }" />
	<input type="hidden" name="itemCnt" value="${vo.itemCnt }" />
	<input type="hidden" name="useCnt" value="${vo.useCnt }" />
	<table summary="<spring:message code="course.title.reshbank.manage"/>" class="table table-bordered wordbreak">
		<colgroup>
			<col style="width:25%"/>
			<col style="width:75%"/>
		</colgroup>
		<tbody>
		<tr height="35">
			<th scope="row" class="top"><label for="reshTitle"><spring:message code="course.title.reshbank.title"/></label></th>
			<td class="top">
				${researchBankVO.reshTitle}
			</td>
		</tr>
		<tr height="35">
			<th scope="row"><label for="reshCts"><spring:message code="course.title.reshbank.desc"/></label></th>
			<td>
				${researchBankVO.reshCts }
			</td>
		</tr>
		<tr height="33">
			<th scope="row"><spring:message code="common.title.useyn"/></th>
			<td>
				<c:if test="${researchBankVO.regYn eq 'Y'}">
				<spring:message code="common.title.useyn_y"/>
				</c:if>
				<c:if test="${researchBankVO.regYn eq 'N'}">
				<spring:message code="common.title.useyn_n"/>
				</c:if>
			</td>
		</tr>
		</tbody>
	</table>
	<div class="text-right">
		<a href="javascript:parent.modalBoxClose()" class="btn btn-default btn-sm"><spring:message code="button.close"/></a>
	</div>
	<input type="submit" value="submit" style="display:none" />
	</form>
	<ul class="nav nav-tabs" id="tabMenu">
		<li class="active"><a href="javascript:onclickTab(0)"><spring:message code="course.title.reshbank.ing"/></a></li>
	</ul>
	<div id="listCourse">

	</div>
<script type="text/javascript">

$(document).ready(function() {

	var width = (parent.window.innerWidth > 0) ? parent.window.innerWidth : parent.screen.width;
	width = width - 20;
	if(width > 1000) width = 1000;
	parent.modalBox.resize(width,600);
	parent.modalBox.setTitle("<spring:message code="course.title.reshbank.info"/>");

	onclickTab(0);
});

function onclickTab(tab) {
	var cmd = "viewCourse";
	$("#listCourse").load(cUrl("/mng/course/researchBank/" + cmd),{ "reshSn":"${researchBankVO.reshSn}"});
}

</script>
