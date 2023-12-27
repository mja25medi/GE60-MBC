<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="crsTchVO" value="${vo}"/>
	<br/>
	<div style="width:100%;">
		<div style="float:left;">
			<h5><i class="glyphicon glyphicon-th-large"></i> <spring:message code="course.title.teacher.manage"/></h5>
		</div>
		<div style="float:right">
			<button class="btn btn-primary btn-sm" onclick="teacherSearch()" ><spring:message code="button.write.teacher"/></button>
		</div>
	</div>
	<div id="teacherList">
		<table summary="<spring:message code="course.title.teacher.manage"/>" style="width:100%" class="table table-bordered wordbreak">
			<colgroup>
				<col style="width:auto"/>
				<col style="width:auto"/>
				<col style="width:auto"/>
				<col style="width:auto"/>
				<col style="width:70px"/>
			</colgroup>
			<thead>
				<tr>
					<th scope="col"><spring:message code="user.title.userinfo.userid"/></th>
					<th scope="col"><spring:message code="user.title.userinfo.name"/></th>
					<th scope="col"><spring:message code="user.title.userinfo.phoneno"/></th>
					<th scope="col"><spring:message code="user.title.userinfo.email"/></th>
					<th scope="col"><spring:message code="common.title.delete"/></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td colspan="5"><spring:message code="course.message.teacher.nodata"/></td>
				</tr>
			</tbody>
		</table>
	</div>

	<div style="width:100%;">
		<div style="float:left;">
			<h5><i class="glyphicon glyphicon-th-large"></i> <spring:message code="course.title.tutor.manage"/></h5>
		</div>
		<div style="float:right;">
			<button class="btn btn-primary btn-sm" onclick="tutorSearch()" ><spring:message code="button.write.tutor"/></button>
		</div>
	</div>
	<div id="tutorList">
		<table summary="<spring:message code="course.title.tutor.manage"/>" style="width:100%" class="table table-bordered wordbreak">
			<colgroup>
				<col style="width:auto"/>
				<col style="width:auto"/>
				<col style="width:auto"/>
				<col style="width:auto"/>
				<col style="width:70px"/>
			</colgroup>
			<thead>
				<tr>
					<th scope="col"><spring:message code="user.title.userinfo.userid"/></th>
					<th scope="col"><spring:message code="user.title.userinfo.name"/></th>
					<th scope="col"><spring:message code="user.title.userinfo.phoneno"/></th>
					<th scope="col"><spring:message code="user.title.userinfo.email"/></th>
					<th scope="col"><spring:message code="common.title.delete"/></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td colspan="5"><spring:message code="course.message.tutor.nodata"/></td>
				</tr>
			</tbody>
		</table>
	</div>

<script type="text/javascript">
	var ItemDTO = new Object();
	$(document).ready(function() {
		listTeacher();
		listTutor();
		ItemDTO.loadCnt = 0;
	});

	function listTeacher() {
		$('#teacherList').load(cUrl("/mng/course/teacher/listTeacher"), { "crsCd": "${crsTchVO.crsCd}"});
	}

	function listTutor() {
		$('#tutorList').load(cUrl("/mng/course/teacher/listTutor"), {"crsCd": "${crsTchVO.crsCd}"});
	}

	function resizeForm() {
		ItemDTO.loadCnt++;
		if(ItemDTO.loadCnt >= 2) {
			var iframeObj = parent.document.getElementById("subWorkFrame");
			parent.resizeIframe3(iframeObj, $(document).height());
		}
	}

	function teacherSearch() {
		var addContent  = "<iframe id='addTeacherFrame' name='addTeacherFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='no' src='<c:url value="/mng/course/teacher/searchTeacherPop"/>"
			+ "?crsCd=${crsTchVO.crsCd}'/>";
		parent.modalBox.clear();
		parent.modalBox.resize(620, 480);
		parent.modalBox.addContents(addContent);
		parent.modalBox.setTitle("<spring:message code="course.title.teacher.write"/>");
		parent.modalBox.show();
	}

	function tutorSearch() {
		var addContent  = "<iframe id='addTeacherFrame' name='addTeacherFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='no' src='<c:url value="/mng/course/teacher/searchTutorPop"/>"
			+ "?crsCd=${crsTchVO.crsCd}'/>";
		parent.modalBox.clear();
		parent.modalBox.resize(620, 480);
		parent.modalBox.addContents(addContent);
		parent.modalBox.setTitle("<spring:message code="course.title.tutor.write"/>");
		parent.modalBox.show();
	}

	function teacherSort(sortString) {
		$.getJSON( cUrl( "/mng/course/teacher/sortTeacher"),
				{  "crsCd": "${crsTchVO.crsCd}", "userNo" : sortString },			// params
				function(data) {
					alert(data.message);
					if(data.result >= 0) {
						//-- 정상 처리
		 	  		} else {
		 	  			//-- 비정상 처리
						listTutor();
		 	  		}
				}
			);
	}

	function tutorSort(sortString) {
		$.getJSON( cUrl( "/mng/course/teacher/sortTutor"),
				{ "crsCd": "${crsTchVO.crsCd}", "userNo" : sortString },			// params
				function(data) {
					alert(data.message);
					if(data.result >= 0) {
						//-- 정상 처리
		 	  		} else {
		 	  			//-- 비정상 처리
						listTutor();
		 	  		}
				}
			);
	}

	/**
	 * 과정 강사 삭제
	 */
	function delTeacher(userNo) {
		if(confirm("<spring:message code="course.message.teacher.confirm.delete"/>")) {
			$.getJSON( cUrl( "/mng/course/teacher/delTeacher"),
					   {  "crsCd": "${crsTchVO.crsCd}", "userNo" : userNo },			// params
					   function(data) {
							if(data.result >= 0) {
								//-- 정상 처리
								alert("<spring:message code="course.message.teacher.delete.success"/>");
								listTeacher();
				 	  		} else {
				 	  			//-- 비정상 처리
				 	  			alert("<spring:message code="course.message.teacher.delete.failed"/>");
				 	  		}
						}
					);
		} else return;
	}

	/**
	 * 과정 튜터 삭제
	 */
	function delTutor(userNo) {
		if(confirm("<spring:message code="course.message.tutor.confirm.delete"/>")) {
			$.getJSON( cUrl( "/mng/course/teacher/delTutor"),
					   { "crsCd": "${crsTchVO.crsCd}", "userNo" : userNo },			// params
					   function(data) {
							if(data.result >= 0) {
								//-- 정상 처리
								alert("<spring:message code="course.message.tutor.delete.success"/>");
								listTutor();
				 	  		} else {
				 	  			//-- 비정상 처리
				 	  			alert("<spring:message code="course.message.tutor.delete.failed"/>");
				 	  		}
						}
					);
		} else return;
	}

	function userInfo(userNo) {
		var addContent  = "<iframe id='userInfoFrame' name='userInfoFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/mng/user/userInfo/viewUserPop"/>"
			+ "?userNo="+userNo+"'/>";
		parent.modalBox.clear();
		parent.modalBox.addContents(addContent);
		parent.modalBox.resize(800, 400);
		parent.modalBox.setTitle("<spring:message code="user.title.userinfo.user.info"/>");
		parent.modalBox.show();
	}


</script>
