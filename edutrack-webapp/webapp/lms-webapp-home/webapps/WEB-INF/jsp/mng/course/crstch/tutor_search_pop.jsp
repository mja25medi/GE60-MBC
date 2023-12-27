<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="crsTchVO" value="${crsTchVO}"/>
<mhtml:mng_html>
<mhtml:mng_head titleName="${MENUNAME}">

</mhtml:mng_head>

<mhtml:frm_body cssTag="background-color:#ffffff;">
	<form name="Search" onsubmit="return false">
	<input type="hidden" name="cmd" />

	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<spring:message code="course.title.teacher.search.title"/>
				</div>
				<div class="panel-body">
					<div class="input-group" style="float:left;width:140px;">
						<input type="text" name="searchValue" id="searchValue" class="_enterBind form-control input-sm" style="float:left" placeholder="<spring:message code="common.title.all"/>"/>
						<span class="input-group-addon btn-sm" onclick="listUser()" style="flot:left;cursor:pointor;">
							<i class="fa fa-search"></i>
						</span>
					</div>
					<div style="float:right">
						<button class="btn btn-default btn-sm" onclick="parent.modalBoxClose()"><spring:message code="button.close" /></button>
					</div>
				</div>
				<div id="userList" style="overflow-y:auto;height:320px;">
					<table summary="<spring:message code="course.title.teacher.manage"/>" style="width:100%;margin-bottom:0px;" class="table table-bordered wordbreak">
						<colgroup>
							<col style="width:auto"/>
							<col style="width:auto"/>
							<col style="width:auto"/>
							<col style="width:50px"/>
						</colgroup>
						<thead>
							<tr>
								<th scope="col"><spring:message code="user.title.userinfo.userid"/></th>
								<th scope="col"><spring:message code="user.title.userinfo.name"/></th>
								<th scope="col"><spring:message code="user.title.userinfo.email"/></th>
								<th scope="col"><spring:message code="common.title.select"/></th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td colspan="5"><spring:message code="course.message.teacher.search.user"/></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<input type="submit" value="submit" style="display:none" />
	</form>

<script type="text/javascript">

	// 페이지 초기화
	$(document).ready(function() {
		$("._enterBind").bind("keydown", eventForSearch);
		function eventForSearch(event) {
			if (event.keyCode == '13') {
				event.preventDefault();
				listUser();
			}
		}
	});

	function listUser() {
		var userNm = $("#searchValue").val();
		$('#userList').load(cUrl("/mng/course/teacher/listSearchTutor"), { "crsCd":"${crsTchVO.crsCd}", "userNm":userNm});
	}

	function viewUser(userNo) {
		$('#userView').load(cUrl("/mng/course/teacher/viewSearchTutor"), { "crsCd":"${crsTchVO.crsCd}", "userNo":userNo});
	}

	function closeView() {
		$('#userView').html("");
	}

	function addTutor(userNo) {
		$.getJSON( cUrl( "/mng/course/teacher/addTutor"),
				   { "crsCd" : "${crsTchVO.crsCd}","userNo" : userNo },			// params
				   function(data) {
						if(data.result >= 0) {
							//-- 정상 처리
							alert("<spring:message code="course.message.tutor.add.success"/>");
							listUser();
							//closeView();
							parent.subWorkFrame.listTutor();
			 	  		} else {
			 	  			//-- 비정상 처리
			 	  			alert("<spring:message code="course.message.tutor.add.failed"/>");
			 	  		}
					}
				);
	}
</script>
</mhtml:frm_body>
</mhtml:mng_html>