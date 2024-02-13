<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>

	<div class="box-body">
		<form id="stuPayForm" name="stuPayForm" onsubmit="return false" class="form-inline">
			<input type="hidden" id="curPage" name="curPage" value="1"/>
			<input type="hidden" id="sortKey" name="sortKey"/>
			<input type="hidden" id="stdNo" name="stdNo"/>
			<input type="hidden" id="crsCreCd" name="crsCreCd" value="${crsCreCd}"/>
			<div style="width:100%;">
				<div class="col-md-6 col-sm-6 text-left">
					<h5><i class="glyphicon glyphicon-th-large"></i> <spring:message code="course.title.std.manage"/></h5>
				</div>
				<div class="col-sm-6 text-right" style="padding: 0">
					<div class="form-group">
						<c:if test="${MSG_SMS eq 'Y' }">
						<a href="javascript:messageForm('SMS')" class="btn btn-info btn-sm"><spring:message code="button.sms"/> </a>
						</c:if>
						<c:if test="${MSG_EMAIL eq 'Y' }">
						<a href="javascript:messageForm('EMAIL')" class="btn btn-info btn-sm"><spring:message code="button.email"/> </a>
						</c:if>
						<c:if test="${MSG_NOTE eq 'Y' }">
						<a href="javascript:messageForm('MSG')" class="btn btn-info btn-sm"><spring:message code="button.note"/> </a>
						</c:if>
					</div>
								
					<select name="listScale" id="listScale" onchange="listStuPayInfo(1)" class="form-control input-sm">
						<option value="10">10</option>
						<option value="20" selected="selected">20</option>
						<option value="40">40</option>
						<option value="60">60</option>
						<option value="80">80</option>
						<option value="100">100</option>
						<option value="200">200</option>
					</select>
				</div>

				<div class="row" style="margin-top:5px;">
					<div class="col-lg-12">
						<div style="float:right">
							<a href="javascript:excelDownload()" class="btn btn-primary btn-sm"><i class="fa fa-file-excel-o fa-fw"></i>엑셀다운로드</a>
							<a href="javascript:excelUpload()" class="btn btn-primary btn-sm"><i class="fa fa-file-excel-o fa-fw"></i>IDE 엑셀업로드</a>
							<!--  <a href="javascript:uptIde()" class="btn btn-default btn-sm">IDE부여</a> -->
							<button class="btn btn-success btn-sm" onclick="studentWrite()" ><spring:message code="button.write.student"/></button>
						</div><br/>
					</div>
				</div>
	
			</div>
			
			<div class="col-md-12" style="margin-top:5px; padding: 0">
				<div id="stuPayListArea"></div>
			</div>
		</form>
	</div>

<script>
var modalBox = null;
var curPage = 1;
var ItemDTO = { "curPage" : "1",
				"sortKey" : "",
				"crsCreCd" : "${crsCreCd}",
				"listScale" : ""
			 };	 

$(document).ready(function(){
	$('.inputDate').inputDate();	// 날짜 형식만 입력되도록 설정.
	$("._enterBind").bind("keydown", eventForSearch);

	modalBox = new $M.ModalDialog({
		"modalid" : "modal1"
	});

	function eventForSearch(event) {
		if (event.keyCode == '13') {
			listStuPayInfo(1);
		}
	}
	listStuPayInfo(1);
});

/**
 *  페이징처리
 */
function listStuPayInfo(page) {

	$(".overlay").show();
	
	if(page != undefined){
		ItemDTO.curPage = page;
		$("#curPage").val(page);
	}
	
	$("#sortKey").val(ItemDTO.sortKey);
	
	$('#stuPayListArea').load(cUrl("/mng/course/std/listCourseStdPay"), 
			$("#stuPayForm").serialize()
		 , function() {
			$(".overlay").hide();	
		}
	);
}

function modalBoxClose() {
	modalBox.clear();
	modalBox.close();
}


function viewStuPayPop(stdNo){
	var addContent  = "<iframe id='viewStuPayFrame' name='viewStuPayFrame' width='100%' height='100%' "
		+ "frameborder='0' scrolling='auto' src='/mng/std/student/viewStdPayPop?stdNo=" + stdNo +"'"
		+ "/>";
	modalBox.clear();
	modalBox.addContents(addContent);
	modalBox.resize(1000, 600);
	//modalBox.size('lg');
	modalBox.setTitle("수강생 관리");
	modalBox.show();
}


function setStudent() {
	var strs = "";
	$('input[name=sel]:checked').each(function() {
		
		var arr = $(this).val().split("||");//0 : 수강생번호, 1 : 수강상태
		
		/* if(arr[1] == 'S'){
			alert('test');
			$(this).prop('checked', false);
			$(this).attr("disabled", "disabled");
			return false;
		} */
		
		strs = strs + "|" + arr[0];
	});
	strs = strs.substring(1);
	//console.log(strs);
	if(strs == "") {
		alert("<spring:message code="student.message.student.alert.select.user"/>");
		return false;
	}
	$("#studentSearchForm input[name=stdNo]").val(strs);
	return true;
}


function checkAll() {
    var state=$('#stuPayTable input[name=chkAll]:checked').size();
    if(state==1){
   		$(document).find("#stuPayTable input[name='sel']").prop({checked:true});
    }else{
    	$(document).find("#stuPayTable input[name='sel']").prop({checked:false});
    }
}

function excelDownload(){
	if ( $("#_m_download_iframe").length == 0 ) {
		iframeHtml = '<iframe id="_m_download_iframe" name="_m_download_iframe" style="visibility: none; display: none;"></iframe>';
		$("body").append(iframeHtml);
	}
	var url = cUrl("/mng/course/std/excelDownloadListStd?" + $("#stuPayForm").serialize());
	
	$("#_m_download_iframe").attr("src", url);
}

function uptIde() {	
	var addContent  = "<iframe id='addStudentFrame' name='addStudentFrame' width='100%' height='100%' "
		+ "frameborder='0' scrolling='yes' src='<c:url value="/mng/std/student/uptIdePop"/>"
		+ "?crsCreCd="+ItemDTO.crsCreCd+"'/>";
	
	parent.modalBox.clear();
	parent.modalBox.addContents(addContent);
	parent.modalBox.resize(800, 700);
	parent.modalBox.setTitle("IDE 부여");
	parent.modalBox.show();
}

/* 메시지 입력 폼 호출 */
function messageForm(msgDivCd) {
	var userList = $("#stuPayForm input[name='sel']:checked").stringValues();

	if(userList == "") {
		alert("<spring:message code="common.message.nouser.message"/>");
		return;
	}
	
	var addContent  = "<iframe id='messageFrame' name='messageFrame' width='100%' height='100%' "
		+ "frameborder='0' scrolling='auto' src='<c:url value="/mng/log/message/addMessagePop"/>"
		+ "?msgDivCd="+msgDivCd+"&amp;logMsgTransLogVO.stdNoList="+userList+"'/>";
		
	parent.modalBox.clear();
	parent.modalBox.addContents(addContent);
	parent.modalBox.resize(720, 520);
	parent.modalBox.setTitle(getMessageTitle(msgDivCd));
	parent.modalBox.show();
}

/* 수강생 승인 */
function editConfirmStudent(stdNo) {
	
	$('#stdNo').val(stdNo);
	
	if(confirm("선택하신 수강생을 승인 하시겠습니까?")){
		processStd("editConfirmStudent");
	}
}

/* 수강생 승인취소 */
function editCancelStudent(stdNo) {
	
	$('#stdNo').val(stdNo);
	
	if(confirm("선택하신 수강생을 승인 취소 하시겠습니까?")){
		processStd("editCancelStudent");
	}
}

/* 수강생 삭제 */
function deleteStudent(stdNo) {
	
	$('#stdNo').val(stdNo);
	
	if(confirm("<spring:message code="student.message.student.confirm.enroll.delete"/>")){
		process("deleteCourseStudent");
	}
}

/**
 * 수강생 등록 폼
 */
function studentWrite() {
	var addContent  = "<iframe id='addStudentFrame' name='addStudentFrame' width='100%' height='100%' "
		+ "frameborder='0' scrolling='yes' src='<c:url value="/mng/std/student/addFormStudentPop"/>"
		+ "?crsCreCd="+ItemDTO.crsCreCd+"'/>";
	parent.modalBox.clear();
	parent.modalBox.addContents(addContent);
	parent.modalBox.resize(800, 700);
	parent.modalBox.setTitle("<spring:message code="student.title.student.manage"/>");
	parent.modalBox.show();
}

/* IDE 엑셀 업로드 */
function excelUpload(){
	var addContent  = "<iframe id='addStuPayFrame' name='addStuPayFrame' width='100%' height='100%' "
		+ "frameborder='0' scrolling='auto' src='/mng/course/std/addStdIdeExcelPop"
		+ "?crsCreCd="+ItemDTO.crsCreCd+"'/>";
		parent.modalBox.clear();
		parent.modalBox.addContents(addContent);
		parent.modalBox.resize(400, 300);
		parent.modalBox.setTitle("수강생 IDE 엑셀업로드");
		parent.modalBox.show();
}

/**
 * 서브밋 처리
 */
function process(cmd) {
	/* if(!validate(document.studentForm)) return; */

	$('#stuPayForm').attr("action", "/mng/course/std/" +cmd);
	$('#stuPayForm').ajaxSubmit(processCallback);
	
}

/**
 * 서브밋 처리
 */
function processStd(cmd) {

	$('#stuPayForm').attr("action", "/mng/std/student/" +cmd);
	$('#stuPayForm').ajaxSubmit(processCallback);
	
}

/**
 * 처리 결과 표시 콜백
 */
function processCallback(resultDTO) {
	alert(resultDTO.message);
	if(resultDTO.result >= 0) {
		// 정상 처리
		listStuPayInfo(ItemDTO.curPage);
	} else {
		// 비정상 처리
	}
}

</script>