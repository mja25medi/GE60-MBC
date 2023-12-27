<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
	<br>
	<div style="width:100%;">
		<div style="float:left;">
			<h5><i class="glyphicon glyphicon-th-large"></i> 출결관리</h5>
		</div>
		<div style="float:right;">
			<select name="searchKey" id="searchKey" class="form-control input-sm" style="width:100px;float:left">
				<option value="userNm"><spring:message code="user.title.userinfo.name"/></option>
				<option value="userId"><spring:message code="user.title.userinfo.userid"/></option>
			</select>
			<div class="input-group mr10" style="width:210px;float:left">
				<input type="text" name="searchValue" id="searchValue" class="_enterBind form-control input-sm"/>
				<span class="input-group-addon" onclick="listStudent()" style="cursor:pointer">
					<i class="fa fa-search"></i>
				</span>
			</div>
			<div style="float:left">
				<c:if test="${MSG_SMS eq 'Y' }">
				<button class="btn btn-default btn-sm" onclick="messageFormTeacher('SMS')" ><spring:message code="button.sms"/></button>
				</c:if>
				<c:if test="${MSG_EMAIL eq 'Y' }">
				<button class="btn btn-default btn-sm" onclick="messageFormTeacher('EMAIL')" ><spring:message code="button.email"/></button>
				</c:if>
				<c:if test="${MSG_NOTE eq 'Y' }">
				<button class="btn btn-default btn-sm" onclick="messageFormTeacher('MSG')" ><spring:message code="button.note"/></button>
				</c:if>
			</div>	
		</div>

	</div>
	
	<div id="studentList" style="width:100%;float:left"></div>

<script type="text/javascript">
	var ItemDTO = new Object();

	$(document).ready(function() {
		$("._enterBind").bind("keydown", function(event){
			if($M.Check.Event.isEnter(event)) {
				listStudent();
			}
		});
		
		ItemDTO.crsCreCd = '${attendCreCourse.crsCreCd}';
		ItemDTO.sbjCd = '${attendCreCourse.sbjCd}';
		listStudent();
   	});

    //전체선택 /전체취소
	function checkAllStudent() {
	    var state=$('#studentList input[name=checkAllStudent]:checked').size();
	    if(state==1){
	   		$("#studentList input[name='selStudent']").prop({checked:true});
	    }else{
	    	$("#studentList input[name='selStudent']").prop({checked:false});
	    }
	}

	function listStudent() {
		var searchKey = $("#searchKey").val();
		var searchValue = $("#searchValue").val();
		$('#studentList').load(
			cUrl("/mng/std/student/stdAttendList"),
			{  "crsCreCd": ItemDTO.crsCreCd
				, "sbjCd" : ItemDTO.sbjCd
				, "searchKey" : searchKey
				, "searchValue" : searchValue
			},
			listStudentCallback
		);
	}
	
	function listStudentCallback() {
		var iframeObj = parent.document.getElementById("subWorkFrame");
		parent.resizeIframe3(iframeObj, $(document).height());
	}

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
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
			listStudent();
		} else {
			// 비정상 처리
		}
	}
	
	function updateStsAll(sts, unitCd) {
		if(confirm("해당 주차의 모든 수강생의 상태를 변경 하시겠습니까?")) {
			$.post( "/mng/lecture/bookmark/updateAllAttendSts", { 
				"crsCreCd" : ItemDTO.crsCreCd,
				"sbjCd" : ItemDTO.sbjCd,
				"unitCd" : unitCd, 
				"attendStsCd" : sts })
			  .done(function(data) {
				  if(data.result > 0) {
					  listStudent();
				  } else {
					  alert(data.message); 
				  }
			  });			
		} else {
			listStudent();
		}
	}
	
	function updateSts(sts, stdNo, unitCd) {
		$.post( "/mng/lecture/bookmark/updateAttendSts", { 
			"stdNo" : stdNo,
			"sbjCd" : ItemDTO.sbjCd,
			"crsCreCd" : ItemDTO.crsCreCd,
			"unitCd" : unitCd, 
			"attendStsCd" : sts })
		  .done(function(data) {
			  if(data.result > 0) {
				  rowUpdate(stdNo);
			  } else {
				  alert(data.message); 
			  }
		  });			
	}
	
	function rowUpdate(stdNo) {
		$("#R_" + stdNo).load(cUrl("/mng/std/student/stdRow"),{
			"stdNo" : stdNo
		});
	}
	
	function showStsBtn(target) {
		$("#" + target + " span").hide();
		$("#" + target + " div[name='stsBtns']").show();
		listStudentCallback();
	}
	
	function hideStsBtn(target) {
		$("#" + target + " span").show();
		$("#" + target + " div[name='stsBtns']").hide();
		listStudentCallback();
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
		$("input[name='selStudent']").each(function(i){
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
</script>
