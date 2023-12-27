<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="courseVO" value="${vo}"/>

	<table summary="<spring:message code="course.title.course.manage"/>" style="width:100%" class="table table-bordered wordbreak">
		<tr>
			<th class="top" scope="row"><spring:message code="course.title.course.name"/></th>
			<td class="top" colspan="3">${courseVO.crsNm}</td>
			<th scope="row"><spring:message code="course.title.course.category"/></th>
			<td>${courseVO.crsCtgrPathNm}</td>
		</tr>
	</table>
	<%-- <div class="text-right" style="width:100%;">
		<button class="btn btn-primary btn-sm" onclick="javascript:crsEditForm('${courseVO.crsCd}')"><spring:message code="button.edit"/></button>
		<button class="btn btn-default btn-sm" onclick="javascript:closeWriteArea()"><spring:message code="button.close"/></button>
	</div> --%>
	<%-- <ul class="nav nav-tabs" id="tabMenu">
  		<li class="active"><a href="javascript:onclickTab(0)"><spring:message code="course.title.course.manage.teacher.tab"/></a></li>
  		<li><a href="javascript:onclickTab(1)"><spring:message code="course.title.course.manage.subject.tab"/></a></li>
	</ul> --%>
	<iframe name="subWorkFrame" id="subWorkFrame" frameborder="0" src="about:blank" scrolling="no" title="sub work frame" style="width:100%;min-height:300px;"></iframe>


<script type="text/javascript">

	$(document).ready(function() {
		$('#tabMenu a').click(function (e) {
			  $(this).tab('show');
			})
		onclickTab(1);
	});

	function onclickTab(tab) {
		resetIframe3(document.getElementById("subWorkFrame"));
		var url = {};
		url['0'] = "/mng/course/teacher/main?crsCd=${courseVO.crsCd}";
		url['1'] = "/mng/course/courseSubject/main?crsCd=${courseVO.crsCd}";

		document.getElementById('subWorkFrame').contentWindow.location.href=cUrl(url[tab]);
		//document.subWorkFrame.location.href = cUrl(url[tab]);
	}

	function editCourse() {
		document.location.href = '<c:url value="/mng/course/course/editFormCourse"/>?crsCd=${courseVO.crsCd}';
	}
</script>