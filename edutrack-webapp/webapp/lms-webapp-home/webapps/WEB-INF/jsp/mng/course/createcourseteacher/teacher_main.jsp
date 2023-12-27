<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
	<br/>
	<form name="Search" id="Search" onsubmit="return false">
	<div style="width:100%;">
		<div style="float:left;">
			<h5><i class="glyphicon glyphicon-th-large"></i>담임 관리</h5>
		</div>
		<div style="float:right">
			<%-- <c:if test="${MSG_SMS eq 'Y' }">
			<button class="btn btn-default btn-sm" onclick="messageFormTeacher('SMS')" ><spring:message code="button.sms"/></button>
			</c:if>
			<c:if test="${MSG_EMAIL eq 'Y' }">
			<button class="btn btn-default btn-sm" onclick="messageFormTeacher('EMAIL')" ><spring:message code="button.email"/></button>
			</c:if>
			<c:if test="${MSG_NOTE eq 'Y' }">
			<button class="btn btn-default btn-sm" onclick="messageFormTeacher('MSG')" ><spring:message code="button.note"/></button>
			</c:if> --%>
			<button class="btn btn-primary btn-sm" onclick="creCrsTchSearch('TCHMGR')" >담임 등록</button>
		</div>
	</div>
	<div id="teacherList" style="width:100%;float:left">
		<table summary="<spring:message code="course.title.teacher.manage"/>" class="table table-bordered wordbreak">
			<colgroup>
				<col style="width:50px"/>
				<col style="width:auto"/>
				<col style="width:auto"/>
				<col style="width:auto"/>
				<col style="width:auto"/>
				<col style="width:75px"/>
			</colgroup>
			<thead>
				<tr>
					<th scope="col"></th>
					<th scope="col"><spring:message code="user.title.userinfo.name"/></th>
					<th scope="col"><spring:message code="user.title.userinfo.userid"/></th>
					<th scope="col"><spring:message code="user.title.userinfo.phoneno"/></th>
					<th scope="col"><spring:message code="user.title.userinfo.email"/></th>
					<th scope="col"><spring:message code="common.title.delete"/></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td colspan="6"><spring:message code="course.message.teacher.nodata"/></td>
				</tr>
			</tbody>
		</table>
	</div>

	<%-- <div style="width:100%;">
		<div style="float:left;">
			<h5><i class="glyphicon glyphicon-th-large"></i>부담임관리</h5>
		</div>
		<div style="float:right">
			<c:if test="${MSG_SMS eq 'Y' }">
			<button class="btn btn-default btn-sm" onclick="messageFormTutor('SMS')" ><spring:message code="button.sms"/></button>
			</c:if>
			<c:if test="${MSG_EMAIL eq 'Y' }">
			<button class="btn btn-default btn-sm" onclick="messageFormTutor('EMAIL')" ><spring:message code="button.email"/></button>
			</c:if>
			<c:if test="${MSG_NOTE eq 'Y' }">
			<button class="btn btn-default btn-sm" onclick="messageFormTutor('MSG')" ><spring:message code="button.note"/></button>
			</c:if>
			<button class="btn btn-primary btn-sm" onclick="creCrsTchSearch('ASSTCHMGR')" >부담임 등록</button>
		</div>
	</div> --%>
<%-- 	<div id="tutorList" style="width:100%;float:left;">
		<table summary="<spring:message code="course.title.tutor.manage"/>" class="table table-bordered wordbreak">
			<colgroup>
				<col style="width:50px"/>
				<col style="width:auto"/>
				<col style="width:auto"/>
				<col style="width:auto"/>
				<col style="width:auto"/>
				<col style="width:75px"/>
			</colgroup>
			<thead>
				<tr>
					<th scope="col"></th>
					<th scope="col"><spring:message code="user.title.userinfo.name"/></th>
					<th scope="col"><spring:message code="user.title.userinfo.userid"/></th>
					<th scope="col"><spring:message code="user.title.userinfo.phoneno"/></th>
					<th scope="col"><spring:message code="user.title.userinfo.email"/></th>
					<th scope="col"><spring:message code="common.title.delete"/></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td colspan="6"><spring:message code="course.message.tutor.nodata"/></td>
				</tr>
			</tbody>
		</table>
	</div> --%>
	<input type="submit" value="submit" style="display:none" />
	</form>

	<form id="createCourseTeacherFrom" name="createCourseTeacherFrom" onsubmit="return false" >
	<input type="hidden" name="crsCreCd" value="${vo.crsCreCd}">
	<input type="hidden" name="userNo">
	<input type="submit" value="submit" style="display:none" />
	</form>

<script type="text/javascript">

	var ItemDTO = new Object();
	var nextFunc ="";

	$(document).ready(function() {
		ItemDTO.crsCreCd = '${vo.crsCreCd}';
		listTeacher();
		listTutor();
/* 		listTutor(); */
		ItemDTO.readCnt = 0;
   	});

    //전체선택 /전체취소
	function checkAllTeacher() {
	    var state=$('input[name=chkAllTeacher]:checked').size();
	    if(state==1){
	   		$("#Search input[name='selTeacher']").prop({checked:true});
	    }else{
	    	$("#Search input[name='selTeacher']").prop({checked:false});
	    }
	}

    function checkAllTutor() {
	    var state=$('input[name=chkAllTutor]:checked').size();
	    if(state==1){
	   		$("#Search input[name='selTutor']").prop({checked:true});
	    }else{
	    	$("#Search input[name='selTutor']").prop({checked:false});
	    }
	}

	function listTeacher() {
		$('#teacherList').load(
			cUrl("/mng/course/createCourse/teacher/listTeacher"),
			{  "crsCreCd": "${vo.crsCreCd}" },
			resizeForm
		);
	}

	function listTutor() {
		$('#tutorList').load(
			cUrl("/mng/course/createCourse/teacher/listTutor"),
			{ "crsCreCd": "${vo.crsCreCd}"},
			resizeForm
		);
	}

	function resizeForm() {
		ItemDTO.readCnt++;
		if(ItemDTO.readCnt > 1) {
			var iframeObj = parent.document.getElementById("subWorkFrame");
			parent.resizeIframe3(iframeObj, $(document).height());
		}
	}

	function teacherSort(sortString) {
		$.getJSON( cUrl( "/mng/course/createCourse/teacher/sortTeacher"),
				{"crsCreCd": ItemDTO.crsCreCd, "userNo" : sortString },			// params
				function(data) {
					alert(data.message);
					if(data.result >= 0) {
						//-- 정상 처리
		 	  		} else {
		 	  			//-- 비정상 처리
						listTeacher();
		 	  		}
				}
			);
	}

	function tutorSort(sortString) {
		$.getJSON( cUrl( "/mng/course/createCourse/teacher/sortTutor"),
				{ "crsCreCd": ItemDTO.crsCreCd, "userNo" : sortString },			// params
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
	 * 개설 과정 강사 삭제
	 */
	function creCrsTchDelete(userNo, tchType) {
		var confirmMessage = "<spring:message code="course.message.tutor.confirm.delete"/>";
		var successMessage = "<spring:message code="course.message.tutor.delete.success"/>";
		var failedMessage = "<spring:message code="course.message.tutor.delete.failed"/>";
		if(tchType == 'TEACHER') {
			confirmMessage = "<spring:message code="course.message.teacher.confirm.delete"/>";
			successMessage = "<spring:message code="course.message.teacher.delete.success"/>";
			failedMessage = "<spring:message code="course.message.teacher.delete.failed"/>";
		}
		if(confirm(confirmMessage)) {
			$.getJSON( cUrl( "/mng/course/createCourse/teacher/deleteTeacher"),
					   {  "crsCreCd": ItemDTO.crsCreCd, "userNo" : userNo },			// params
					   function(data) {
							if(data.result >= 0) {
								//-- 정상 처리
								alert(successMessage);
								if(tchType == 'TEACHER')
									listTeacher();
								else
									listTutor();
				 	  		} else {
				 	  			//-- 비정상 처리
				 	  			alert(failedMessage);
				 	  		}
						}
					);
		} else return;
	}

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		if(!validate(document.createCourseTeacherFrom)) return;

		$('#createCourseTeacherFrom').attr("action","/mng/course/createCourse/teacher/"+cmd);
		$('#createCourseTeacherFrom').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		alert(resultDTO.message);
		if(resultDTO.result >= 0) {
			// 정상 처리
			listTeacher();
		} else {
			// 비정상 처리
		}
	}

	/**
	 * 담임/부담임 선택 폼 호출
	 */
	function creCrsTchSearch(tchType) {
		if (tchType == "TCHMGR") {
			var boxTitle = "<spring:message code="course.title.teacher.write"/>";
		} else {
			var boxTitle = "<spring:message code="course.title.tutor.write"/>";
		}
		
		var addContent  = "<iframe id='addTeacherFrame' name='addTeacherFrame' width='100%' height='100%' "
						+ "frameborder='0' scrolling='no' src='<c:url value="/mng/course/createCourse/teacher/addFormTeacherPop"/>"
						+ "?crsCreCd="+ItemDTO.crsCreCd+"&amp;tchType="+tchType+"'/>";

		parent.modalBox.clear();
		parent.modalBox.addContents(addContent);
		parent.modalBox.resize(600, 500);
		parent.modalBox.setTitle(boxTitle);
		parent.modalBox.show();
	}

	/**
	 * 강사평가폼
	 */
	function creCrsTchScoreForm(userNo) {
		var addContent  = "<iframe id='addTeacherFrame' name='addTeacherFrame' width='100%' height='100%' "
						+ "frameborder='0' scrolling='no' src='<c:url value="/mng/course/createCourse/teacher/addFormTeacherScore"/>"
						+ "?crsCreCd="+ItemDTO.crsCreCd+"&amp;userNo="+userNo+"'/>";

		parent.modalBox.clear();
		parent.modalBox.addContents(addContent);
		parent.modalBox.resize(300, 200);
		parent.modalBox.setTitle("강사/튜터 평가");
		parent.modalBox.show();
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

	//-- 메시지 입력 폼 호출
	function messageFormTeacher(msgDivCd) {
		var userList = "";
		$("#Search input[name='selTeacher']").each(function(i){
			if(this.checked) {
				if(i > 0) userList += ",";
				userList += $(this).val();
			}
		});
		if(userList == "") {
			alert("<spring:message code="common.message.nouser.message"/>");
			return;
		}
		var addContent  = "<iframe id='messageFrame' name='messageFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/mng/log/message/addMessagePop"/>"
			+ "?logMsgLogVO.msgDivCd="+msgDivCd+"&amp;logMsgTransLogVO.userNoList="+userList+"'/>";
		parent.modalBox.clear();
		parent.modalBox.addContents(addContent);
		parent.modalBox.resize(720, 520);
		parent.modalBox.setTitle(getMessageTitle(msgDivCd));
		parent.modalBox.show();
	}

	//-- 메시지 입력 폼 호출
	function messageFormTutor(msgDivCd) {
		var userList = "";
		$("#Search input[name='selTutor']").each(function(i){
			if(this.checked) {
				if(i > 0) userList += ",";
				userList += $(this).val();
			}
		});
		if(userList == "") {
			alert("<spring:message code="common.message.nouser.message"/>");
			return;
		}

		var url = generateUrl("/mng/log/message/addMessagePop",{"logMsgLogVO.msgDivCd":msgDivCd, "logMsgTransLogVO.userNoList":userList});
		var addContent  = "<iframe id='messageFrame' name='messageFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='"+url+"'/>";
		parent.modalBox.clear();
		parent.modalBox.addContents(addContent);
		parent.modalBox.resize(720, 520);
		parent.modalBox.setTitle(getMessageTitle(msgDivCd));
		parent.modalBox.show();
	}
</script>
