<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="examQbankQstnVO" value="${vo}"/>
<c:set var="gubun" value="${gubun}"/>
<mhtml:mng_html>
<mhtml:mng_head titleName="${MENUNAME}">
	<mhtml:head_module uploadify="y"/>
</mhtml:mng_head>

<mhtml:frm_body cssTag="background-color:#ffffff;">
	<table summary="<spring:message code="lecture.title.exam.question.manage"/>" class="table table-bordered wordbreak">
		<colgroup>
			<col style="width:10%"/>
			<col style="width:40%"/>
			<col style="width:20%"/>
			<col style="width:30%"/>
		</colgroup>
		<tr>
			<th scope="row"><spring:message code="course.title.exambank.title"/></th>
			<td colspan="3"><input type="text" name="qstnTitle" value="${vo.qstnTitle }" maxlength="200" lenCheck="200" class="form-control input-sm" /></td>
		</tr>
		<tr>
			<td colspan="4">
				${vo.qstnCts}
			</td>
		</tr>

	</table>
	<div class="text-right">
		<c:if test="${gubun eq 'A'}">
		<a href="javascript:addQuestion()" class="btn btn-primary btn-sm"><spring:message code="button.add"/> </a>
		</c:if>
		<c:if test="${gubun eq 'E'}">
		<a href="javascript:editQuestion()" class="btn btn-primary btn-sm"><spring:message code="button.add"/> </a>
		<a href="javascript:deleteQuestion()" class="btn btn-warning btn-sm"><spring:message code="button.delete"/> </a>
		</c:if>
		<a href="javascript:parent.modalBoxClose()" class="btn btn-default btn-sm"><spring:message code="button.close"/> </a>
	</div>

<script type="text/javascript">

	//---- 페이지 초기화 함수
	$(document).ready(function() {

	});


</script>
</mhtml:frm_body>
</mhtml:mng_html>