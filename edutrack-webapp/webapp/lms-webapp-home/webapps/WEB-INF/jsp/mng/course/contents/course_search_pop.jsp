<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<mhtml:mng_html>
<mhtml:mng_head titleName="${MENUNAME}">
</mhtml:mng_head>

<mhtml:frm_body cssTag="background-color:#ffffff;">

<div id="dvwrap" style="position:relative; height:100%;">
	<div class="well well-sm">
		<form name="Search" onsubmit="return false">
			<div class="input-group">
				<input type="text" name="searchKey" value="${sbjNm}" class="_enterBind form-control input-sm" title="<spring:message code="common.title.input.searchvalue"/>"/>
				<span class="input-group-addon" onclick="listSubject()" style="cursor:pointer">
					<i class="fa fa-search"></i>
				</span>
			</div>
		</form>
	</div>
	<div id="searchList" style="overflow:auto; height:254px; width:100%;">
		<table style="width:100%" class="table table-bordered wordbreak">
			<colgroup>
				<col style="width:auto"/>
				<col style="width:20%"/>
				<col style="width:15%"/>
				<col style="width:15%"/>
			</colgroup>
			<thead>
				<tr>
					<th scope="col"><spring:message code="course.title.subject.name"/></th>
					<th scope="col"><spring:message code="course.title.subject.category"/></th>
					<th scope="col"><spring:message code="course.title.subject.contents.cnt"/></th>
					<th scope="col"><spring:message code="common.title.select"/></th>
				</tr>
			</thead>
			<tbody></tbody>
		</table>
	</div>
</div>
<form id="createCourseSubjectFrom" name="createCourseSubjectFrom" onsubmit="return false" action="/mng/course/createCourse/subject">
<input type="hidden" property="crsCreCd" value="${vo.crsCreCd }"/>
<input type="hidden" property="sbjCd" value="${vo.sbjCd }" />
<input type="hidden" property="studyMthd" value="${vo.studyMthd }" />
<input type="submit" value="submit" style="display:none" />
</form>

<script type="text/javascript">
	$(document).ready(function() {
		$("._enterBind").bind("keydown", eventForSearch);

		function eventForSearch(event) {
			if (event.keyCode == '13') {
				listSubject();
			}
		}
		listSubject();
	});

	function listSubject() {
		var f = document.Search;
		var sbjNm = f.searchKey.value;
		$("#searchList")
			.load(
				cUrl("/mng/course/contents/listSearchSubject"),
				{
					"sbjNm" : sbjNm
				}
			);
	}

	function selectSubject(sbjCd, sbjNm) {
		parent.setOtherSubject(sbjCd, sbjNm);
	}
</script>
</mhtml:frm_body>
</mhtml:mng_html>
