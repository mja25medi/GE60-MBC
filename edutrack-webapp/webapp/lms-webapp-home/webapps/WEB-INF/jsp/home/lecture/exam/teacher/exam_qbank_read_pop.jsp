<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="examQbankQstnVO" value="${vo}" />
<c:url var="img_base" value="/img/home" />
<%pageContext.setAttribute("crlf", "\n");%>
<mhtml:class_html>
<mhtml:class_head>

</mhtml:class_head>

<mhtml:body>
	<%-- 2015.11.04 김현욱 수정 css추가 --%>
	<table class="table table-bordered">
		<colgroup>
			<col style="width:20%"/>
			<col/>
		</colgroup>
		<tbody>
			<tr>
				<th scope="row"><spring:message code="lecture.title.exam.question.type"/></th>
				<td >
					${examQbankQstnVO.qstnTypeNm }
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="lecture.title.exam.question.title"/></th>
				<td >
					${examQbankQstnVO.title }
				</td>
			</tr>
			<tr>
				<th scope="row" colspan="2"><spring:message code="lecture.title.exam.question"/></th>
			</tr>
			<tr>
				<td colspan="2">${fn:replace(examQbankQstnVO.qstnCts,crlf,"<br/>")}</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="lecture.title.exam.question.rightanswer"/></th>
				<td >
					${examQbankQstnVO.rgtAnsr}
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="lecture.title.exam.question.desc"/></th>
				<td >
					${examQbankQstnVO.qstnExpl}
				</td>
			</tr>
			</tbody>
		</table>
		<div class="text-right">
			<a href="javascript:listQstn()" class="btn btn-default"><spring:message code="button.list"/></a>
		</div>
		<form id="examForm" name="examForm" onsubmit="return false" >
		<input type="hidden" name="examSn" value="${examSn}"/>
		<input type="hidden" name="sbjCd" value="${examQbankQstnVO.sbjCd}"/>
		<input type="hidden" name="ctgrCd" value="${examQbankQstnVO.ctgrCd}"/>
		</form>

<script type="text/javascript">
	/**
	* 문제 목록 이동
	*/
	function listQstn(){
		$('#examForm').attr("action","/lec/exam/editQbankPop");
		document.examForm.submit();
	}

</script>
</mhtml:body>
</mhtml:class_html>