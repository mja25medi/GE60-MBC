<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
	<form name="Search" onsubmit="return false">
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
				<div id="userList" style="height:350px; overflow-x: hidden; overflow-y: scroll;">
					<table summary="<spring:message code="course.title.teacher.manage"/>" class="table table-bordered wordbreak">
						<colgroup>
							<col style="width:auto"/>
							<col style="width:auto"/>
							<col style="width:auto"/>
							<col style="width:auto"/>
							<col style="width:72px"/>
						</colgroup>
						<thead>
							<tr>
								<th scope="col"><spring:message code="user.title.userinfo.userid"/></th>
								<th scope="col"><spring:message code="user.title.userinfo.name"/></th>
								<th scope="col"><spring:message code="user.title.userinfo.mobileno"/></th>
								<th scope="col"><spring:message code="user.title.userinfo.email"/></th>
								<th scope="col"><spring:message code="common.title.choice"/></th>
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

	var ItemDTO = new Object();

	$(document).ready(function() {
		$("._enterBind").bind("keydown", eventForSearch);
		function eventForSearch(event) {
			if (event.keyCode == '13') {
				event.preventDefault();
				listUser();
			}
		}
		ItemDTO.crsCreCd = '${vo.crsCreCd}';
		ItemDTO.tchType = '${vo.tchType}';

		//listUser();
	});


	function process(cmd) {
		if(!validate(document.createCourseTeacherForm)) return;

		$('#createCourseTeacherForm').find('input[name=cmd]').val(cmd);
		$('#createCourseTeacherForm').ajaxSubmit(processCallback);
	}


	function processCallback(resultDTO) {
		alert(resultDTO.message);
		if(resultDTO.result >= 0) {
			listUser();
			parent.subWorkFrame.listTeacher();         //부모 조회 메소드 호출
		} else {
			// 비정상 처리
		}
	}

	function listUser() {
		var userNm = $("#searchValue").val();
		$('#userList')
			.load(
				cUrl("/mng/course/createCourse/teacher/listSearch"),
				{
					"crsCreCd": ItemDTO.crsCreCd,
					"tchType": ItemDTO.tchType,
					"userNm":userNm
				}
			);
	}

	function addTeacher(userNo) {
		var successMessage = "<spring:message code="course.message.tutor.add.success"/>";
		var failedMessage = "<spring:message code="course.message.tutor.add.failed"/>";
		if(ItemDTO.tchType == "TCHMGR") {
			successMessage = "<spring:message code="course.message.teacher.add.success"/>";
			failedMessage = "<spring:message code="course.message.tutor.add.failed"/>";
		}
		$.getJSON( cUrl( "/mng/course/createCourse/teacher/addTeacher"),
				   {  "crsCreCd" : ItemDTO.crsCreCd, "tchType": ItemDTO.tchType,"userNo" : userNo },			// params
				   function(data) {
						if(data.result >= 0) {
							//-- 정상 처리
							alert(successMessage);
							listUser();
							if(ItemDTO.tchType == 'TCHMGR')
								parent.subWorkFrame.listTeacher();
							else
								parent.subWorkFrame.listTutor();
			 	  		} else {
			 	  			//-- 비정상 처리
			 	  			alert(failedMessage);
			 	  		}
					}
				);
	}
</script>
