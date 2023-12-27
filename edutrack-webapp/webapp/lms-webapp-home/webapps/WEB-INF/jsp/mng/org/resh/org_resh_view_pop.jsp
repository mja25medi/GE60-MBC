<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="orgReshVO" value="${vo}"/>


	<form id="researchBankForm" name="researchBankForm" onsubmit="return false" action="/mng/org/research">
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
				${orgReshVO.reshTitle}
			</td>
		</tr>
		<tr height="35">
			<th scope="row"><label for="reshCts"><spring:message code="course.title.reshbank.desc"/></label></th>
			<td>
				${orgReshVO.reshCts }
			</td>
		</tr>
		<tr height="33">
			<th scope="row"><spring:message code="common.title.useyn"/></th>
			<td>
				<c:if test="${orgReshVO.useYn eq 'Y'}">
				<spring:message code="common.title.useyn_y"/>
				</c:if>
				<c:if test="${orgReshVO.useYn eq 'N'}">
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
<script type="text/javascript">

$(document).ready(function() {

	var width = (parent.window.innerWidth > 0) ? parent.window.innerWidth : parent.screen.width;
	width = width - 20;
	if(width > 600) width = 600;
	parent.modalBox.resize(width,300);
	parent.modalBox.setTitle("<spring:message code="course.title.reshbank.info"/>");

	onclickTab(0);
});


</script>
