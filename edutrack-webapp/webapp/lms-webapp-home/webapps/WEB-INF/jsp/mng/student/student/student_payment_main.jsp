<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<style>
	#statusTable th {background-color: #eee;}
	#stuSearchTable th {background-color: #eee;}
	#stuPayTable th {background-color: #eee;}
	#stuPayTable td {vertical-align:middle;}
</style>

<section class="content">
	<div class="row" id="content">
		<div class="box">
			<div class="overlay">
       			<i class="fa fa-refresh fa-spin"></i>
       		</div>
		
			<div class="box-body">
				<form id="stuPayForm" name="stuPayForm" onsubmit="return false" >
				<!-- <form id="stuPayForm" name="stuPayForm"  > -->
					<input type="hidden" id="curPage" name="curPage" value="1"/>
					<input type="hidden" id="sortKey" name="sortKey"/>
					<table  class="table table-bordered wordbreak" style="margin-top: 5px;" id="stuSearchTable">
						<colgroup>
							<col style="width:25%"/>
							<col style="width:25%"/>
							<col style="width:25%"/>
							<col style="width:25%"/>
						</colgroup>
					
						<tr>
							<th scope="row">과정, 회차</th>
							<%-- <td>
								<select name="deptCd" id ="deptCd" class="form-control input-sm mr10" title="기업 선택" onchange="setSelectBox(1)">
									<option value="">기업 선택</option>
									<c:forEach var="item" items="${deptList }" varStatus="status">
										<option value="${item.deptCd }">${item.deptNm }</option>
									</c:forEach>
								</select>
							</td> --%>
							<td>
								<select name ="crsCd" id = "crsCd" class="form-control input-sm" title="과정 선택" onchange="setSelectBox(2)">
									<option value="">과정 선택</option>
									<c:forEach var="item" items="${crsList }" varStatus="status">
										<option value="${item.crsCd }">${item.crsNm }</option>
									</c:forEach>
								</select>
							</td>
							<td>
								<select name ="crsCreCd" id = "crsCreCd" class="form-control input-sm" title="회차 선택">
									<option value="">회차 선택</option>
									<c:forEach var="item" items="${crsCreList }">
										<option value="${item.crsCreCd}">${item.crsCreNm} ${item.creTerm}회차</option>
									</c:forEach>
								</select>
							</td>
							<%-- <td>
								<select name ="sbjCd" id = "sbjCd" class="form-control input-sm" title="과정 선택" onchange="setSelectBox(3)">
									<option value="">과목 선택</option>
									<c:forEach var="item" items="${sbjList }">
										<option value="${item.sbjCd}">${item.sbjNm}</option>
									</c:forEach>
								</select>
							</td> --%>
						</tr>
						<tr>
							<!-- <th scope="row">수강상태</th>
							<td>
								<select name ="enrlSts" id = "enrlSts" class="form-control input-sm mr10" title="수강상태 선택" >
									<option value="">수강상태 선택</option>
									<option value="E">수강대기</option>
									<option value="S">수강중</option>
									<option value="N">수강취소</option>
									<option value="F">수료취소</option>
									<option value="C">수료</option>
								</select>
							</td> -->
							<th scope="row">결제 상태</th>
							<td>
								<select name ="searchEnrlSts" id = "searchEnrlSts" class="form-control input-sm mr10" title="결제상태 선택" >
									<option value="">결제상태 선택</option>
									<option value="E">결제대기(미결제)</option>
									<option value="SCF">결제완료</option>
									<option value="N">결제취소</option>
								</select>
							</td>
							<th scope="row">결제수단</th>
							<td>	
								<select name ="paymMthdCd" id = "paymMthdCd" class="form-control input-sm mr10" title="결제수단 선택" >
									<option value="">결제수단 선택</option>
									<option value="PAYM001">카드 결제</option>
									<option value="PAYM002">계좌 이체</option>
									<option value="PAYM003">무통장입급(가상계좌)</option>
									<option value="PAYM004">관리자 입금</option>
								</select>
							</td>
						</tr>
						<!-- <tr>
							<th scope="row">승인,취소 가능 상태</th>
							<td style="vertical-align: middle;">
								<select name ="searchConfirmYn" id = "searchConfirmYn" class="form-control input-sm mr10" title="승인,취소 가능 조회" >
									<option value="">전체</option>
									<option value="Y">승인 가능</option>
									<option value="N">취소 가능</option>
								</select>
							</td>
							<td colspan="2"><span style="color:#00a65a;">승인</span>, <span style="color:#f39c12;">삭제</span> 가능 : 결제대기, 결제취소<br><span style="color:#dd4b39;">취소</span> 가능 : 결제완료(수강중),결제대기</td>
							<td></td>
						</tr> -->
						<tr>
							<th scope="row">환불상태</th>
							<td style="vertical-align: middle;">
								<select name ="repayStsCd" id = "repayStsCd" class="form-control input-sm mr10" title="환불상태선택" >
									<option value="">전체</option>
									<option value="REFUND001">환불요청</option>
									<option value="REFUND003">환불완료</option>
									<option value="REFUND004">환불취소</option>
								</select>
							</td>
							<td colspan="2"></td>
						</tr>
						<tr>
							<th>검색조건</th>
							<td colspan="3">
								<select name ="searchKey" id = "searchKey" class="form-control input-sm mr10" title="검색조건" style="width:200px;float:left;">
									<option value="userNm">회원명</option>
									<option value="userId">아이디</option>
								</select>
								<input type="text" name="searchValue" id="searchValue" class="_enterBind form-control input-sm mr10" maxlength="20" title="검색어" style="width:200px;float:left;" value="${vo.searchValue}" placeholder="검색어를 입력하세요" />
								<a href="javascript:listStuPayInfo(1);" class="btn btn-primary btn-sm" ><i class="fa fa-search"></i>검색</a>
							</td>
						</tr>
					</table>
					<div class="row">
						<div class="col-lg-12">
							<a href="javascript:excelUpload()" class="btn btn-primary btn-sm"><i class="fa fa-file-excel-o fa-fw"></i>엑셀 업로드</a>
							<%-- 
							<a href="javascript:checkEditCancelStudent()" class="btn btn-danger btn-sm">선택 결제 취소 처리</a>
							<a href="javascript:checkEditConFirmStudent()" class="btn btn-success btn-sm">선택 결제 완료 처리</a>
							--%>
							
							<select name="listScale" id="listScale" onchange="listStuPayInfo(1)" class="form-control input-sm" style="width:100px; float:right;">
								<option value="30" selected="selected">30</option>
								<option value="50">50</option>
								<option value="100">100</option>
							</select>
							<a href="javascript:excelDownload()" class="btn btn-primary btn-sm mr10" style="float:right;"><i class="fa fa-file-excel-o fa-fw"></i>엑셀다운로드</a>
							<%-- <a href="javascript:checkEditDeleteStudent()" class="btn btn-warning btn-sm mr10" style="float:right;">선택 삭제 처리</a> --%>
						</div>
					</div>
				</form>
				
      				
					<div id="stuPayListArea"></div>
			</div>
		</div>
	</div>
</section>

<form id="studentSearchForm" name="studentSearchForm" onsubmit="return false" method="post">
	<input type="hidden" name="stdNo" value="" />
</form>

<script>
var modalBox = null;
var curPage = 1;
var ItemDTO = { "curPage" : "1",
				"sortKey" : ""
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
	$('#stuPayListArea').load(cUrl("/mng/std/student/listStdPay"), 
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

function excelUpload(){
	var addContent  = "<iframe id='addStuPayFrame' name='addStuPayFrame' width='100%' height='100%' "
		+ "frameborder='0' scrolling='auto' src='/mng/std/student/addStdPayExcelPop' "
		+ "/>";
	modalBox.clear();
	modalBox.addContents(addContent);
	modalBox.resize(400, 300);
	modalBox.setTitle("수강신청관리 엑셀업로드");
	modalBox.show();
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

<%--
function checkEditCancelStudent(){
	if(setStudent()){
		if(!confirm('선택한 수강생을 취소(결제취소) 처리하시겠습니까?')){
			return;
		}
		process('editCancelStdPay');
	}
}

function checkEditConFirmStudent(){
	if(setStudent()){
		if(!confirm('선택한 수강생을 승인(결제완료)하시겠습니까?')){
			return;
		}
		process('editConfirmStdPay');
	}
}
--%>

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

function process(cmd) {
	$('#studentSearchForm').attr("action" , "/mng/std/student/" + cmd);
	$('#studentSearchForm').ajaxSubmit(searchProcessCallback);
}

function searchProcessCallback(resultDTO) {
	alert(resultDTO.message);
	if(resultDTO.result >= 0) {// 정상 처리
		listStuPayInfo();
	} else {// 비정상 처리
		listStuPayInfo();
	}
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
	var url = cUrl("/mng/std/student/excelDownloadListStdPay?" + $("#stuPayForm").serialize());
	
	$("#_m_download_iframe").attr("src", url);
}

<%--
function checkEditDeleteStudent(){
	if(setStudent()){
		if(confirm('선택한 수강생을 삭제하시겠습니까?\n결제대기,결제취소 상태인 수강생만 삭제 가능합니다.') && confirm('정말로 삭제하시겠습니까?\n삭제할 경우 더이상 보이지 않습니다.')){
			process('editDeleteStdPay');
		}
	}
}
--%>

//기업 선택
function selectDept(){
	var beforeDeptCd = $("#deptCd").val();
	
	$.ajax({
		url : '/mng/std/student/listDeptStdPay'
		,data : {
			'crsCd' : $("#crsCd").val(),
			'sbjCd' : $("#sbjCd").val()
		}
		, method: "GET"
		,success : function(resultVO) {
			var selectHtml = "";
			selectHtml += "<option value=''>기업 선택</option>";
			for(var i = 0; i < resultVO.returnList.length; i++){
				var selectedFlag = false;
				if(beforeDeptCd == resultVO.returnList[i].deptCd){
					selectedFlag = true;
				}
				
				selectHtml += "<option value="+ resultVO.returnList[i].deptCd +
							((selectedFlag) ? " selected " : "") + 
							">" +resultVO.returnList[i].deptNm+"</option>";
			}
			$("#deptCd").empty();
			$("#deptCd").html(selectHtml);
		}
		,error : function(request,status,error) {
			alert("오류가 발생하였습니다. 새로고침 후 다시 이용바랍니다.");
		}
	});	
}

//과목 선택
function selectSbj(){
	var beforeSbjCd = $("#sbjCd").val();
	$.ajax({
		url : '/mng/std/student/listSbjStdPay'
		,data : {
			'crsCd' : $("#crsCd").val(),
			'deptCd' : $("#deptCd").val()
		}
		, method: "GET"
		,success : function(resultVO) {
			var selectHtml = "";
			selectHtml += "<option value=''>과목 선택</option>";
			for(var i = 0; i < resultVO.returnList.length; i++){
				var selectedFlag = false;
				if(beforeSbjCd == resultVO.returnList[i].sbjCd){
					selectedFlag = true;
				}
				
				selectHtml += "<option value="+ resultVO.returnList[i].sbjCd +
							((selectedFlag) ? " selected " : "") + 
							">" +resultVO.returnList[i].sbjNm+"</option>";
			}
			$("#sbjCd").empty();
			$("#sbjCd").html(selectHtml);
		}
		,error : function(request,status,error) {
			alert("오류가 발생하였습니다. 새로고침 후 다시 이용바랍니다.");
			//parent.listStuPayInfo();
		}
	});
}

//과정 선택
function selectCrs(){
	var beforeCrsCd = $("#crsCd").val();
	
	$.ajax({
		url : '/mng/std/student/listCrsStdPay'
		,data : {
			'sbjCd' : $("#sbjCd").val(),
			'deptCd' : $("#deptCd").val()
		}
		, method: "GET"
		,success : function(resultVO) {
			var selectHtml = "";
			selectHtml += "<option value=''>기수 선택</option>";
			
			for(var i = 0; i < resultVO.returnList.length; i++){
				var selectedFlag = false;
				if(beforeCrsCd == resultVO.returnList[i].crsCd){
					selectedFlag = true;
				}
				
				selectHtml += "<option value="+ resultVO.returnList[i].crsCd +
							((selectedFlag) ? " selected " : "") + 
							">" +resultVO.returnList[i].crsYear + "년도 " + resultVO.returnList[i].crsTerm + " 기수" +"</option>";
			}	
			
			$("#crsCd").empty();
			$("#crsCd").html(selectHtml);
		}
		,error : function(request,status,error) {
			alert("오류가 발생하였습니다. 새로고침 후 다시 이용바랍니다.");
			//parent.listStuPayInfo();
		}
	});	
}

//회차 선택
function selectCre(){
	var beforeCrsCd = $("#crsCd").val();
	
	$.ajax({
		url : '/mng/std/student/listCreStdPay'
		,data : {
			'crsCd' : $("#crsCd").val(),
		}
		, method: "GET"
		,success : function(resultVO) {
			var selectHtml = "";
			selectHtml += "<option value=''> 전체 </option>";
			
			for(var i = 0; i < resultVO.returnList.length; i++){
				var selectedFlag = false;
				if(beforeCrsCd == resultVO.returnList[i].crsCd){
					selectedFlag = true;
				}
				
				selectHtml += "<option value="+ resultVO.returnList[i].crsCreCd +
							((selectedFlag) ? " selected " : "") + 
							">" +resultVO.returnList[i].crsCreNm +" "+ resultVO.returnList[i].creTerm + "회차" +"</option>";
			}	
			
			$("#crsCreCd").html(selectHtml);
		}
		,error : function(request,status,error) {
			alert("오류가 발생하였습니다. 새로고침 후 다시 이용바랍니다.");
			//parent.listStuPayInfo();
		}
	});	
}

function setSelectBox(data){
	if(data == '1'){//1 : 기업 선택 / 기수, 과목 재검색
		selectSbj();
		selectCrs();
	}else if(data == '2'){// 과정 선택 > 회차 재검색
		selectCre();
	}else if(data == '3'){//3 : 과목 선택 / 기업, 기수 재검색
		selectDept();
		selectCrs();	
	}
}
</script>