<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="prjAssignmentVO" value="${vo}"/>
<mhtml:class_html>
<mhtml:class_head>
	<meditag:css href="css/simpletab.css"/>
	<meditag:js src="/js/simpletab.js"/>
	<meditag:js src="/js/calendar.js"/>
	<mhtml:head_module daumeditor="y" uploadify="y"/>
</mhtml:class_head>
<mhtml:body>
	<div id="assignmentRead">
 		<div class="btn_right">
			<a href="javascript:editAssiBtn()" class="btn02">수정</a>
			<a href="javascript:delAssiBtn()" class="btn02">삭제</a>
		</div>
		<br/>
	 	<table class="board_b">
			<colgroup>
				<col style="width:20%"/>
				<col style="width:30%"/>
				<col style="width:20%"/>
				<col style="width:30%"/>
			</colgroup>
			<tr>
				<th class="top" scope="row">과제명</th>
				<td class="top" colspan="3">${prjAssignmentVO.asmtTitle}</td>
			</tr>
			<tr>
				<th scope="row">제출기간</th>
				<td colspan="3">
 					${prjAssignmentVO.asmtStartDate} ~ ${prjAssignmentVO.asmtEndDate}
				</td>
			</tr>
			<tr>
				<td colspan="4" valign="top" style="padding:10px 5px 10px 5px">
				<div style="float:left;width:100%;min-height:160px;" class="tx-content-container">${prjAssignmentVO.asmtCts}</div>
				</td>
			</tr>
			<tr>
				<th scope="row"> 첨부파일</th>
				<td colspan="3">
 					<c:forEach var="fileItem" items="${prjAssignmentVO.attachFiles}" varStatus="status">
						<div style="margin-left: 5px; float: left">${fileItem.downloadTag}</div>
					</c:forEach>
				</td>
			</tr>
		</table>
 	</div>
 <form id="prjAssignmentForm" name="prjAssignmentForm" onsubmit="return false" >
	<input type="hidden" name="crsCreCd" value="${vo.crsCreCd}" />
 	<input type="hidden" name="prjtSn" value="${vo.prjtSn}" />
 	<input type="hidden" name="asmtSn" value="${vo.asmtSn}" />
 	<input type="hidden" name="attachFileSns" value="${vo.attachFileSns}" />
 	<div id="assignmentWrite">
 		<br/>
		<table class="board_b">
			<colgroup>
				<col style="width:20%"/>
				<col style="width:30%"/>
				<col style="width:20%"/>
				<col style="width:30%"/>
			</colgroup>
			<tr>
				<th class="top" scope="row">과제명</th>
				<td class="top" colspan="3">
					<input type="text" style="width:500px;" dispName="과제 명" maxlength="100" isNull="N" lenCheck="100" name="asmtTitle" class="text" id="asmtTitle"/>
				</td>
			</tr>
			<tr>
				<th scope="row">제출기간</th>
				<td colspan="3">
					<input type="text":text style="width:100px;" maxlength="100" dispName="과제 시작일" isNull="N" lenCheck="100" name="asmtStartDttm" class="text" readonly="true" />
					<img class="fn_calimg" src="<c:url value="/img/framework/icon/icon_calendar.gif" />" onClick="Calendar('달력', 'prjAssignmentForm', 'asmtStartDttm', '.', 'position=right,datetype=00,clear=yes');"/>
					<input type="text" style="width:20px" dispName="과제 시작 시간" maxlength="2" isNull="N" lenCheck="2" name="asmtStartHour" class="text inputSpecial inputNumber"/>시
					<input type="text" style="width:20px" dispName="과제 시작 분" maxlength="2" isNull="N" lenCheck="2" name="asmtStartMin" class="text inputSpecial inputNumber"/>분부터
					~ <input type="text" style="width:100px;" maxlength="100" dispName="과제 종료일" isNull="N" lenCheck="100" name="asmtEndDttm" class="text" readonly="true" />
					<img class="fn_calimg" src="<c:url value="/img/framework/icon/icon_calendar.gif" />" onClick="Calendar('달력', 'prjAssignmentForm', 'asmtEndDttm', '.', 'position=right,datetype=00,clear=yes');"/>
					<input type="text" style="width:20px" dispName="과제 종료 시간" maxlength="2" isNull="N" lenCheck="2" name="asmtEndHour" class="text inputSpecial inputNumber"/>시
					<input type="text" style="width:20px" dispName="과제 종료 분" maxlength="2" isNull="N" lenCheck="2" name="asmtEndMin" class="text inputSpecial inputNumber"/>분까지
				</td>
			</tr>
			<tr>
				<td colspan="4" style="padding:0px">
					<div id="daumeditor" style="float:left; width:100%; margin: 0 auto;"></div>
					<textarea name="asmtCts" id="contentTextArea" style="display:none" title="과제 내용"/>
				</td>
			</tr>
			<tr>
				<th scope="row"> 첨부파일</th>
				<td colspan="3">
					<input type="file" name="uploadify" id="uploadify" title="첨부파일"/>
					<div id="fileQueue"></div>
					<div id="fileListview"></div>
				</td>
			</tr>
		</table>
		<table style="width:98%">
			<tr>
				<td id="addBtn">
					<div class="btn_right">
						<a href="javascript:addAssi()" class="btn02">저장</a>
					</div>
				</td>
				<td id="editBtn">
					<div class="btn_right">
						<a href="javascript:editAssi()" class="btn02">저장</a>
						<a href="javascript:listRead()" class="btn01">취소</a>
					</div>
				</td>
			</tr>
		</table>
	</div>
</form>

<script type="text/javascript">
	var atchFiles; // 첨부파일 목록
	//iframe 리사이즈
	callResize();
	//읽기화면 / 등록화면
	viewAssignment();

	var ItemDTO = new Object();

	$(document).ready(function() {

		ItemDTO.crsCreCd = '${prjAssignmentVO.crsCreCd}';
		ItemDTO.prjtSn = '${prjAssignmentVO.prjtSn}';
		ItemDTO.asmtSn = '${prjAssignmentVO.asmtSn}';

		$('.inputNumber').inputNumber();	// 숫자만 입력되도록 설정.
		$('.inputSpecial').inputSpecial();  // 특수키가 입력이 들어오면 false return;



		$M.CreateEditor({		// @see /js/daumeditor.js 참고
			"editorId"			:	"daumeditor",
			"formId"			:	"prjAssignmentForm",
			"textareaId"		:	"contentTextArea",
			"repositoryCode"	:	"PRJ_ASSIGNMENT",
			"attachments"		:	$.parseJSON('${prjAssignmentVO.attachImagesJson}'),
			"editorHeight"		:	"200px"
		});

		// 파일 첨부 업로더 및 바인딩 및 초기화 @see /js/common_function.js 참조.
		atchFiles = new $M.AttachFiles(
				{	"varName"			: "atchFiles",		// 변수명과 동일한 명칭 설정
					"files"				: $.parseJSON('${prjAssignmentVO.attachFilesJson}'),
					"uploadifySetting"	: {
							"queueID"		: "fileQueue",
							"scriptData"	:	{
									"repository"	:	"PRJT_ASMT",
									"type"			:	"file"		}
		}});


	});

	function viewAssignment(){
		if('${prjAssignmentVO.asmtSn}' == 0){
			$('#assignmentWrite').show();
			$('#assignmentRead').hide();
			$('#addBtn').show();
			$('#editBtn').hide();
		}else{
			$('#assignmentWrite').hide();
			$('#assignmentRead').show();
			$('#addBtn').hide();
			$('#editBtn').show();
		}
	}

	function editAssiBtn(){
		$('#assignmentWrite').show();
		$('#assignmentRead').hide();
	}

	function goRead(){
		$('#assignmentWrite').hide();
		$('#assignmentRead').show();
	}

	/* 과제 등록 */
	function addAssi() {
		$(':input:hidden[name=attachFileSns]').val(atchFiles.getFileSnAll());
		$('#uploadify').attr("disabled", true); //-- 파일 객체 disable

		var f = document.prjAssignmentForm;

		if(isEmpty(f["asmtTitle"].value)) {
			alert("과제 제목을 입력해 주세요.");
			f["prjAssignmentVO.asmtTitle"].focus();
			return;
		}

		if(validate(document.prjAssignmentForm) ==false) return ;

		var asmtStartDttm = chgDate(f["asmtStartDttm"].value);
		var asmtEndDttm = chgDate(f["asmtEndDttm"].value);

		if(!dateCheck(asmtStartDttm, asmtEndDttm)) {
			alert("종료일을 시작일 보다 늦은 날짜를 선택하셔야 합니다.");
			return;
		}

		if(validateTime()==false) return;

		$('#prjAssignmentForm').attr("action","/lec/prj/assignment/addPrjAssignment");
		Editor.save();	// 다음에디터 저장.
	}

	/* 과제 수정 */
	function editAssi() {
		$(':input:hidden[name=attachFileSns]').val(atchFiles.getFileSnAll());
		$('#uploadify').attr("disabled", true); //-- 파일 객체 disable

		var f = document.prjAssignmentForm;

		if(isEmpty(f["asmtTitle"].value)) {
			alert("과제 제목을 입력해 주세요.");
			f["asmtTitle"].focus();
			return;
		}

		if(validate(document.prjAssignmentForm) ==false) return ;

		var asmtStartDttm = chgDate(f["asmtStartDttm"].value);
		var asmtEndDttm = chgDate(f["asmtEndDttm"].value);

		if(!dateCheck(asmtStartDttm, asmtEndDttm)) {
			alert("종료일을 시작일 보다 늦은 날짜를 선택하셔야 합니다.");
			return;
		}

		if(validateTime()==false) return;

		$('#prjAssignmentForm').attr("action","/lec/prj/assignment/editPrjAssignment");
		Editor.save();
	}

	/* 읽기화면으로 이동 */
	function listRead() {
		$('#prjAssignmentForm')
			.attr("action","/lec/prj/assignment/editFormPrjAssignment")
			.submit();
	}

	/* 과제 삭제 */
	function delAssiBtn() {
		if(confirm('과제를 삭제하려고 합니다.\n\n삭제 하시겠습니까?')) {
			$('#prjAssignmentForm')
				.attr("action","/lec/prj/assignment/removePrjAssignment")
				.submit();
		} else {
			return;
		}
	}

	/*입력한 시간의 유효성을 체크한다.
	*  폼 벨리데이션 체크를 하지 않아서 빈값까지 여기서 검증함
	*/
	function validateTime(){

		var f = document.prjAssignmentForm;

		var asmtStartHour = chgDate(f["asmtStartHour"].value);  //과제 시작일 시''
		var asmtStartMin = chgDate(f["asmtStartMin"].value);   //과제 시작일 분''

		var asmtEndHour = chgDate(f["asmtEndHour"].value);  //과제 종료일 시''
		var asmtEndMin = chgDate(f["asmtEndMin"].value);   //과제 종료일 분''

		if(asmtStartHour==""){
			alert("과제시작 시간을 등록하여 주시기 바랍니다." );
			f["asmtStartHour"].focus();
			return false;
		}
		else if(asmtStartMin==""){
			alert("과제시작 시간(분)을 등록하여 주시기 바랍니다." );
			f["asmtStartMin"].focus();
			return false;
		}
		else if(asmtEndHour==""){
			alert("과제종료 시간을 등록하여 주시기 바랍니다." );
			f["asmtEndHour"].focus();
			return false;
		}
		else if(asmtEndMin==""){
			alert("과제종료 시간(분)을 등록하여 주시기 바랍니다." );
			f["asmtEndMin"].focus();
			return false;
		}

		if(asmtStartHour>24 || asmtStartHour>24 ){
			alert("시간의(시) 의 설정값은 00이상 23 이하로 등록하여 주시기 바랍니다");
			return false;
		}

		if(asmtStartMin>59 || asmtEndMin>59){
			alert("시간의(분) 의 설정값은 00이상 59 이하로 등록하여 주시기 바랍니다");
			return false;
		}

		return true;
	}

	//Date Type YYYYMMDD
	function chgDate(date){
		var chgDate = date.replace(/\./g,"");
		return chgDate;
	}

	//날짜 체크하기.
	function dateCheck(startDate , endDate){
		if(startDate > endDate) {
			return false;
		}else{
			return true;
		}
	}

	/**
	 * Editor.save()를 호출시 데이터 유효여부 검사 콜백함수. 유효할 경우 true 리턴.
	 * @function
	 * @param {Object} editor - 에디터에서 넘겨주는 editor 객체
	 * @returns {Boolean} 모든 데이터가 유효할 경우에 true
	 */
	function validForm(editor) {

		var f = document.prjAssignmentForm;
		if(isEmpty(f["prjAssignmentVO.asmtTitle"].value)) {
			alert('과제 제목을 입력하세요.');
			return false;
		}

		var _content = editor.getContent();

		if(isEmpty(_content)) {
			alert('과제 내용을 입력하세요.');
			return false;
		}
		return true;
	}

	/**
	 * Editor.save() -> validForm(editor)를 통해 데이터가 유효하면 Form Summit을 위해 필드를
	 * 생성, 변경하기 위해 부르는 콜백함수로 컨텐츠 값과 첨부파일을 설정.
	 * 정상적인 경우에 true를 리턴하면 submit();
	 * @function
	 * @param {Object} editor - 에디터에서 넘겨주는 editor 객체
	 * @returns {Boolean} 정상적인 경우에 true
	 */
	function setForm(editor) {

		// 본문 전송을 위해 컨텐츠를 textarea로..
		$("#contentTextArea").val(editor.getContent());

		// 서버에서 파일에 대한 정보를 처리할 파라매터 항목을 추가.
		$(':input:hidden[name=attachFileSns]').val(atchFiles.getFileSnAll());
		$(':input:hidden[name=attachImageSns]').val(Editor.getAttachmentIds(editor, 'image', true));

		// ajax호출시 multipart 오류로.. Input file disable 로
		$('#uploadify').attr("disabled", true);

		$('#prjAssignmentForm').submit();

		return false;
	}

	function callResize() {
        var height = document.body.scrollHeight;
        parent.resizeTopIframe(height);
	}

</script>
</mhtml:body>
</mhtml:class_html>