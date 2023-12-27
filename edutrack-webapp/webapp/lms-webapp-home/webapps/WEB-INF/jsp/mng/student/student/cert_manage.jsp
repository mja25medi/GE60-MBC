<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<!-- <section class="content">
	<div class="row" id="content"> 
		<div class="box">
			<div class="box-body">
				<div class="rowLayer" id="listLayer" > -->
					<form name="Search" id="Search" onsubmit="return false" class="form-inline">
					<input type="hidden" id="crsCreCd" name="crsCreCd" value="${vo.crsCreCd}">
					<div class="col-md-12">
						<table class="table table-bordered wordbreak" id="certListTable" style="margin-top: 10px;">
							<colgroup>
								<col style="width:50%"/>
								<col style="width:auto;"/>
							</colgroup>
							<thead>
								<tr>
									<th>과정명</th>
									<th>자격증 신청기간</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td class="text-center">${vo.crsCreNm}</td>
									<td class="text-center"><meditag:dateformat type="8" delimeter="." property="${crsvo.scoreOpenStartDttm}"/> ~ <meditag:dateformat type="8" delimeter="." property="${crsvo.scoreOpenEndDttm}"/></td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="col-md-7 col-xs-12 mb10">
						<div class="input-group" style="float:left">
							<select name="certPassYn" id="certPassYn" onchange="listCertStudent(1)" class="form-control input-sm" style="float:left;">
								<option value="">합격/불합격 선택</option>
								<option value="Y">합격</option>
								<option value="N">불합격</option>
							</select>
						</div>
						<div class="input-group" style="float:left">
							<select name="certSts" id="certSts" onchange="listCertStudent(1)" class="form-control input-sm" style="float:left;">
								<option value="">신청 상태 선택</option>
								<c:forEach var="item" items="${certStsList}">
									<option value="${item.codeCd}">${item.codeNm}</option>
								</c:forEach>
							</select>
						</div>
						<div class="input-group" style="float:left">
							<input type="hidden" name="searchKey" id="searchKey" value="userNm">
							<input type="text" name="searchValue" id="searchValue" style="float:left;" class="_enterBind form-control input-sm" maxlength="20" title="<spring:message code="common.message.input.search"/>" placeholder="이름 검색"/>
							<span class="input-group-addon" onclick="listCertStudent(1)" style="cursor:pointer">
								<i class="fa fa-search"></i>
							</span>
						</div>
					</div>
					<div class="col-md-5 col-xs-12 mb5">
						<div class="pull-right">
							<div class="btn-group" style="float:left;">
								<button class="btn btn-default btn-sm" onclick="goPreviewList();">목록</button>
							</div>
						</div>
					</div>
					<div class="col-md-7 col-xs-12 mb5">
						<div class="btn-group" style="float:left;">
							<a href="javascript:viewAddCertStudentExcelPop();" class="btn btn-primary btn-sm"><i class="fa fa-file-excel-o fa-fw"></i> 응시생 일괄 등록 </a>
							<a href="javascript:checkEditConFirmStudentCertPass();" class="btn btn-success btn-sm" style="margin-left:5px">선택 합격 처리 </a>
							<a href="javascript:checkEditCancelStudentCertPass();" class="btn btn-danger btn-sm" style="margin-left:5px">선택 불합격 처리 </a>
						</div>
					</div>
					<div class="col-md-5 col-xs-12 mb5">
						<div class="pull-right">
							<div class="btn-group" style="float:left;">
								<a href="javascript:excelDownload();" class="btn btn-primary btn-sm"><i class="fa fa-file-excel-o fa-fw"></i> <spring:message code="button.download.excel"/> </a>
								<c:if test="${MSG_SMS eq 'Y' }">
								<button class="btn btn-default btn-sm" onclick="messageForm('SMS')"><i class="fa fa-envelope-o"></i> <spring:message code="button.sms"/></button>
								</c:if>
								<c:if test="${MSG_EMAIL eq 'Y' }">
								<button class="btn btn-default btn-sm" onclick="messageForm('EMAIL')"><i class="fa fa-envelope-o"></i> <spring:message code="button.email"/></button>
								</c:if>
								<c:if test="${MSG_NOTE eq 'Y' }">
								<button class="btn btn-default btn-sm" onclick="messageForm('MSG')"><i class="fa fa-envelope-o"></i> <spring:message code="button.note"/></button>
								</c:if>
							</div>
							<select name="listScale" id="listScale" onchange="listPageing(1);" class="form-control input-sm" style="width:60px;float:left">
								<option value="10">10</option>
								<option value="20" selected="selected">20</option>
								<option value="40">40</option>
								<option value="60">60</option>
								<option value="80">80</option>
								<option value="100">100</option>
								<option value="200">200</option>
							</select>
						</div>
						<div class="clearfix"></div>
					</div>
					<input type="submit" value="submit" style="display:none" />
					<div class="col-md-12">
						<div id="certStudentList" style="margin-top:5px;">
						</div>
					</div>
					</form>
					
<form id="studentSearchForm" name="studentSearchForm" onsubmit="return false" method="post">
	<input type="hidden" name="stdNo" value="" />
</form>
<script type="text/javascript">
	var modalBox = null;
	var ItemDTO = {
			sortKey : ""
		};

	/** 메인 페이지 초기화 */
	$(document).ready(function() {
		ItemDTO.sortKey = "USER_NM_ASC";
		modalBox = new $M.ModalDialog({
			"modalid" : "modal1"
		});

		$("._enterBind").bind("keydown", eventForSearch);
		function eventForSearch(event) {
			if (event.keyCode == '13') {
				event.preventDefault();
				(1);
			}
		}
		listCertStudent(1);
	});

	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}
	
	function setSortKey(key) {
		ItemDTO.sortKey = key;
		listCertStudent(1);
	}

	function checkAll() {
	    var state=$('input[name=chkAll]:checked').size();
	    if(state==1){
	   		$("#Search input[name='sel']").prop({checked:true});
	    }else{
	    	$("#Search input[name='sel']").prop({checked:false});
	    }
	}
	
	/**
	 * 응시자 목록 조회
	 */
	function listCertStudent(page) {
		$("#loadingBar").show();
		var searchKey = $('#searchKey').val();
		var searchValue = $('#searchValue').val();
		var listScale = $("#listScale option:selected").val();
		var certPassYn = $("#certPassYn option:selected").val();
		var certSts = $("#certSts option:selected").val();

		$('#certStudentList')
			.load(cUrl("/mng/std/student/listCertStudent"),
				{ 
				  "sortKey":ItemDTO.sortKey,
				  "searchKey":searchKey,
				  "searchValue":searchValue,
				  "curPage":page,
				  "crsCreCd":"${vo.crsCreCd}",
				  "certPassYn":certPassYn,
				  "certSts":certSts,
				  "listScale":listScale
				 },function () {$("#loadingBar").hide(); }
			);

	}

	/**
	 * 응시자 일괄 등록 폼
	 */
	function viewAddCertStudentExcelPop() {
		var addContent  = "<iframe id='certInfoFrame' name='certInfoFrame' width='100%' height='100%' "
				+ "frameborder='0' scrolling='auto' src='<c:url value="/mng/std/student/addCertStudentExcelPop"/>"
				+ "?crsCreCd=${vo.crsCreCd}'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(500, 300);
		modalBox.setTitle("응시자 일괄 등록");
		modalBox.show();
	}
	
	/**
	 * 응시자 일괄 합격/불합격 처리 폼
	 */
	function certScorePassWriteForm() {
		var addContent  = "<iframe id='certInfoFrame' name='certInfoFrame' width='100%' height='100%' "
				+ "frameborder='0' scrolling='auto' src='<c:url value="/mng/std/student/certScorePassWriteForm"/>'>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(650, 400);
		modalBox.setTitle("응시자 일괄 합격/불합격 처리");
		modalBox.show();
	}
	
	/**
	 * 자격증 발급 상태 일괄 변경 폼
	 */
	function totalCertReqStatusEditForm() {
		var addContent  = "<iframe id='certInfoFrame' name='certInfoFrame' width='100%' height='100%' "
				+ "frameborder='0' scrolling='auto' src='<c:url value="/mng/std/student/totalCertReqStatusEditForm"/>'>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(650, 400);
		modalBox.setTitle("자격증 발급 상태 일괄 변경 폼");
		modalBox.show();
	}

	
	function userInfo(userNo) {
		var url = generateUrl("/mng/user/userInfo/viewUserPop",{"userNo":userNo});
		var addContent  = "<iframe id='userInfoFrame' name='userInfoFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='"+url+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(1000, 600);
		modalBox.setTitle("<spring:message code="user.title.userinfo.user.info"/>");
		modalBox.show();
	}
	
	function crmOpen(userNo) {
		var url = generateUrl("/mng/user/userInfo/crmOpenPop",{"userNo":userNo});
		var contentsWin = window.open(url);
	}
	
	//-- 메시지 입력 폼 호출
	function messageForm(msgDivCd) {
		var userList = "";
		$("#Search input[name='sel']").each(function(i){
			if(this.checked) {
				if(i > 0) userList += ",";
				userList += $(this).val();
			}
		});
		if(userList == "") {
			alert("<spring:message code="common.message.nouser.message"/>");
			return;
		}
		
		var url = generateUrl("/mng/log/message/addMessagePop",{ "msgDivCd":msgDivCd, "logMsgTransLogVO.userNoList":userList});
		var addContent  = "<iframe id='messageFrame' name='messageFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='"+url+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(720, 520);
		modalBox.setTitle(getMessageTitle(msgDivCd));
		modalBox.show();
	}
	
	function goPreviewList() {
		listCreateCourse();
	}
	
	//자격증 발급 상태 수정
	function changeCertSts(stdNo){
		var url = generateUrl("/mng/std/student/editCertStsPop",{"stdNo":stdNo});
		var addContent  = "<iframe id='certStsFrame' name='certStsFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='"+url+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(600, 300);
		modalBox.setTitle("자격증 발급 상태 수정");
		modalBox.show();
	}
	
	
	//선택 합격 처리
	function checkEditConFirmStudentCertPass(){
		if(!confirm('선택한 수강생을 합격처리 하시겠습니까?')){
			return;
		}
		if(setStudent()){
			process('editConfirmStudentCertPass');
		}
	}
	
	//선택 블합격 처리
	function checkEditCancelStudentCertPass(){
		if(!confirm('선택한 수강생을 불합격 처리하시겠습니까?')){
			return;
		}
		if(setStudent()){
			process('editCancelStudentCertPass');
		}
	}
	
	function setStudent() {
		var strs = "";
		$('input[name=sel]:checked').each(function() {
			var arr = $(this).val();
			strs = strs + "|" + arr;
		});
		strs = strs.substring(1);
		if(strs == "") {
			alert("<spring:message code="student.message.student.alert.select.user"/>");
			return false;
		}
		$("#studentSearchForm input[name=stdNo]").val(strs);
		return true;
	}
	
	function process(cmd) {
		$('#studentSearchForm').attr("action" , "/mng/std/student/" + cmd);
		$('#studentSearchForm').ajaxSubmit(searchProcessCallback);
	}

	function searchProcessCallback(resultDTO) {
		alert(resultDTO.message);
		if(resultDTO.result >= 0) {// 정상 처리
			listCertStudent(1);
		} else {// 비정상 처리
			listCertStudent(1);
		}
	}
	
	//엑셀 다운로드
	function excelDownload(){
		if ( $("#_m_download_iframe").length == 0 ) {	
			iframeHtml = '<iframe id="_m_download_iframe" name="_m_download_iframe" style="visibility: none; display: none;"></iframe>';
			$("body").append(iframeHtml);
		}
		var url = cUrl("/mng/std/student/excelDownloadCertStdManage?" + $("#Search").serialize());
		$("#_m_download_iframe").attr("src", url);
	}
	
</script>
