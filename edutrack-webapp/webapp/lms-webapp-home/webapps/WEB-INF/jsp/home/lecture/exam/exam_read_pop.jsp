<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<%pageContext.setAttribute("crlf", "\n");%>
<c:set var="examVO" value="${examVO}" />
<mhtml:class_html>
<mhtml:class_head>
	<%-- 2015.11.04 김현욱 수정 css제거 --%>
	<%-- <meditag:css href="css/home/pop.css" /> --%>
</mhtml:class_head>
<mhtml:body>
	<table  class="table table-bordered wordbreak" style="word-break:break-all;word-wrap: break-word;">
		<colgroup>
			<col style="width:20%" />
			<col style="width:80%" />
		</colgroup>
		<tbody>
			<tr>
				<th scope="row"><spring:message code="lecture.title.exam.name"/></th>
				<td>${examVO.examTitle}</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="lecture.title.exam.duration"/></th>
				<td>
					<meditag:dateformat type="8" delimeter="." property="${examVO.examStartDttm}${examVO.examStartHour}${examVO.examStartMin}00"/> ~ <meditag:dateformat type="8" delimeter="." property="${examVO.examEndDttm}${examVO.examEndHour}${examVO.examEndMin}00"/>
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="lecture.title.exam.result.date"/></th>
				<td>
					<meditag:dateformat type="8" delimeter="." property="${examVO.rsltCfrmDttm}${examVO.rsltCfrmHour}${examVO.rsltCfrmMin}00"/>
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="lecture.title.exam.desc"/></th>
				<td style="padding:0px;">
					<div style="height:120px;overflow:auto;padding:5px;">
						${fn:replace(examVO.examCts,crlf,"<br/>")}
					</div>
				</td>
			</tr>
		</tbody>
	</table>
	<div class="text-right">
		<a href="javascript:parent.modalBoxClose()" class="btn btn-default btn-sm"><spring:message code="button.close"/></a>
	</div>

<script type="text/javascript">
	$(document).ready(function() {
		$("body").css("padding-top","0px").css("min-height","0px");
	});
</script>
</mhtml:body>
</mhtml:class_html>