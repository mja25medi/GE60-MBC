<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="onlineSubjectVO" value="${vo}" />
<mhtml:mng_html>
<mhtml:mng_head titleName="${MENUNAME }">

</mhtml:mng_head>

<mhtml:frm_body cssTag="background-color:#ffffff;">
	<ul class="nav nav-tabs" id="tabMenu">
		<li id="crsSub" class="active"><a href="javascript: listCrsSubject()"><spring:message code="course.title.education.course"/></a></li>
		<li id="creCrsSub" ><a href="javascript: listCreCrsSubject()"><spring:message code="course.title.createcourse"/></a></li>
		<li id="openCrsSub" ><a href="javascript: listOpenCrsSubject()"><spring:message code="course.title.open"/></a></li>
	</ul><br/>
	<div class="col-md-12" style="margin-top:5px;">
		<div id="subjectList" >
		</div>
	</div>
<script type="text/javascript">

	$(document).ready(function() {
		listCrsSubject();
	});

	function listCrsSubject() {
		$('#subjectList')
			.load(
				cUrl("/mng/course/course/listSubInfo"),
				{ 
				  "sbjCd":"${onlineSubjectVO.sbjCd}"
				}
			);
		$("#crsSub").addClass("active");
		$("#creCrsSub").removeClass("active");
		$("#openCrsSub").removeClass("active");
	}

	function listCreCrsSubject() {
		$('#subjectList')
			.load(
				cUrl("/mng/course/createCourse/listSubInfo"),
				{ 
				  "sbjCd":"${onlineSubjectVO.sbjCd}"
				}
			);
		$("#crsSub").removeClass("active");
		$("#creCrsSub").addClass("active");
		$("#openCrsSub").removeClass("active");
	}


	function listOpenCrsSubject() {
		$('#subjectList')
			.load(
				cUrl("/mng/openCourse/listSubInfo"),
				{ 
				  "sbjCd":"${onlineSubjectVO.sbjCd}"
				}
			);
		$("#crsSub").removeClass("active");
		$("#creCrsSub").removeClass("active");
		$("#openCrsSub").addClass("active");
	}

</script>
</mhtml:frm_body>
</mhtml:mng_html>