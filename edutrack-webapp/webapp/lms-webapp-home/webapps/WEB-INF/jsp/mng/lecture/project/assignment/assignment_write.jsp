<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="prjAssignmentVO" value="${prjAssignmentVO}"/>
<mhtml:html>
<mhtml:head titleName="과제 등록">
	<meditag:js src="/js/calendar.js"/>
	<meditag:css href="libs/daumeditor/css/_orig/contents4view.css"/>
	<mhtml:head_module daumeditor="y" uploadify="y"/>
</mhtml:head>

<mhtml:frm_body cssTag="background-color:#ffffff;">
	 <div id="assignmentRead">
 		<div style="float: right;padding: 0px 0px 5px 0px">
			<div>
				<meditag:button value="수정" title="수정" func="editAssiBtn()" />
			</div>
			<div>
				<meditag:button value="삭제" title="삭제" func="delAssiBtn()" />
			</div>
 		</div>
	 	<table summary="팀 프로젝트 관리" style="width:100%" class="table_dtl" align="center">
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
 <form id="prjAssignmentForm" name="prjAssignmentForm" onsubmit="return false" action="/LecturePrjAssignmentAdmin.do">
	<input type="hidden" name="cmd" />
	<input type="hidden" name="crsCreCd" />
 	<input type="hidden" name="prjtSn" />
 	<input type="hidden" name="asmtSn" />
 	<input type="hidden" name="attachFileSns" />
 	<div id="assignmentWrite">
 		<br>
		<table summary="팀 프로젝트 관리 등록" style="width:100%;" class="table_dtl" align="center">
			<colgroup>
				<col style="width:20%"/>
				<col style="width:30%"/>
				<col style="width:20%"/>
				<col style="width:30%"/>
			</colgroup>
			<tr>
				<th class="top" scope="row">과제명</th>
				<td class="top" colspan="3">
					<input type="text" style="width:99%" dispName="과제 명" maxlength="100" isNull="N" lenCheck="100" name="asmtTitle" class="txt" id="asmtTitle"/>
				</td>
			</tr>
			<tr>
				<th scope="row">제출기간</th>
				<td colspan="3">
					<input type="text" style="width:100px;" maxlength="100" dispName="과제 시작일" isNull="N" lenCheck="100" name="asmtStartDttm" class="txt" readonly="true" />
					<img src="<c:url value="/img/framework/icon/icon_calendar.gif" />" onClick="Calendar('달력', 'prjAssignmentForm', 'prjAssignmentVO.asmtStartDttm', '.', 'position=right,datetype=00,clear=yes');">
					<input type="text" style="width:20px" dispName="과제 시작 시간" maxlength="2" isNull="N" lenCheck="2" name="asmtStartHour" class="txt inputSpecial inputNumber"/>시
					<input type="text" style="width:20px" dispName="과제 시작 분" maxlength="2" isNull="N" lenCheck="2" name="asmtStartMin" class="txt inputSpecial inputNumber"/>분부터
					~ <input type="text" style="width:100px;" maxlength="100" dispName="과제 종료일" isNull="N" lenCheck="100" name="asmtEndDttm" class="txt" readonly="true" />
					<img src="<c:url value="/img/framework/icon/icon_calendar.gif" />" onClick="Calendar('달력', 'prjAssignmentForm', 'prjAssignmentVO.asmtEndDttm', '.', 'position=right,datetype=00,clear=yes');">
					<input type="text" style="width:20px" dispName="과제 종료 시간" maxlength="2" isNull="N" lenCheck="2" name="asmtEndHour" class="txt inputSpecial inputNumber"/>시
					<input type="text" style="width:20px" dispName="과제 종료 분" maxlength="2" isNull="N" lenCheck="2" name="asmtEndMin" class="txt inputSpecial inputNumber"/>분까지
				</td>
			</tr>
			<tr>
				<td colspan="4" style="padding-left:0px;">
					<div id="daumeditor" style="float:left; width:100%; margin: 0 auto;"></div>
					<textarea name="asmtCts" id="contentTextArea" style="display:none" title="과제 내용"/>
				</td>
			</tr>
			<tr>
				<th scope="row"> 첨부파일</th>
				<td colspan="3">
					<div style="flot:left"><input type="file" title="첨부파일" name="uploadify" id="uploadify"/><%-- 첨부파일 버튼 --%></div>
					<div style="flot:left" id="fileQueue"></div>
					<div style="flot:left" id="fileListview"></div>
				</td>
			</tr>
		</table>
		<table style="width:98%">
			<tr>
				<td id="addBtn">
					<meditag:buttonwrapper>
						<meditag:button value="저장" title="저장" func="addAssi()" />
					</meditag:buttonwrapper>
				</td>
				<td id="editBtn">
					<meditag:buttonwrapper>
						<meditag:button value="저장" title="저장" func="editAssi()" />
						<meditag:button value="취소" title="취소" func="listRead()"/>
					</meditag:buttonwrapper>
				</td>
			</tr>
		</table>
	</div>
</form>
<script type="text/javascript">

	var atchFiles; // 첨부파일 목록

	setFrame();

	asmtView();

	var ItemDTO = new Object();
	$(document).ready(function() {

		ItemDTO.crsCreCd = '${prjAssignmentVO.crsCreCd}';
		ItemDTO.prjtSn = '${prjAssignmentVO.prjtSn}';
		ItemDTO.asmtSn = '${prjAssignmentVO.asmtSn}';

		$('.inputNumber').inputNumber();	// 숫자만 입력되도록 설정.
		$('.inputSpecial').inputSpecial();  // 특수키가 입력이 들어오면 false return;


		// 파일 첨부 업로더 및 바인딩 및 초기화 @see /js/common_function.js 참조.
		atchFiles = new $M.AttachFiles(
				{	"varName"			: "atchFiles",		// 변수명과 동일한 명칭 설정
					"maxcount"			: 5,
					"files"				: $.parseJSON('${prjAssignmentVO.attachFilesJson}'),
					"uploadifySetting"	: {
							"queueID"		: "fileQueue",
							"fileDesc"		:	"문서 파일 (*.hwp, *.doc, *.xls, *.docx, *.xlsx, *.zip, *.ppt, *.pptx)",
							"fileExt"		:	"*.hwp; *.doc; *.xls; *.docx; *.xlsx; *.zip; *.ppt; *.pptx;",
							"scriptData"	:	{
									"repository"	:	"PRJT_ASMT",
									"type"			:	"file"		}
				}});

		$M.CreateEditor({		// @see /js/daumeditor.js 참고
			"editorId"			:	"daumeditor",
			"formId"			:	"prjAssignmentForm",
			"textareaId"		:	"contentTextArea",
			"repositoryCode"	:	"PRJ_ASSIGNMENT",
			//"attachments"		:	$.parseJSON('${bbsAtclDTO.attachImagesJson}'),
			"editorHeight"		:	"400px"
		});


	});

	function asmtView(){
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

	/* 서브밋 처리 */
	function process(cmd) {
		$('#prjAssignmentForm').submit();
	}

	/* 과제 저장 */
	function addAssi() {
		$(':input:hidden[name=prjAssignmentVO\\.attachFileSns]').val(atchFiles.getFileSnAll());
		$('#uploadify').attr("disabled", true); //-- 파일 객체 disable

		var f = document.prjAssignmentForm;

		if(isEmpty(f["prjAssignmentVO.asmtTitle"].value)) {
			alert("과제 제목을 입력해 주세요.");
			f["prjAssignmentVO.asmtTitle"].focus();
			return;
		}

		if(validate(document.prjAssignmentForm) ==false) return ;

		var asmtStartDttm = chgDate(f["prjAssignmentVO.asmtStartDttm"].value);
		var asmtEndDttm = chgDate(f["prjAssignmentVO.asmtEndDttm"].value);

		if(!dateCheck(asmtStartDttm, asmtEndDttm)) {
			alert("종료일을 시작일 보다 늦은 날짜를 선택하셔야 합니다.");
			return;
		}

		if(validateTime()==false) return;

		$('#prjAssignmentForm').find('input[name=cmd]').val('add');
		Editor.save();	// 다음에디터 저장.
	}

	/* 과제 수정 */
	function editAssi() {
		$(':input:hidden[name=prjAssignmentVO\\.attachFileSns]').val(atchFiles.getFileSnAll());
		$('#uploadify').attr("disabled", true); //-- 파일 객체 disable

		var f = document.prjAssignmentForm;

		if(isEmpty(f["prjAssignmentVO.asmtTitle"].value)) {
			alert("과제 제목을 입력해 주세요.");
			f["prjAssignmentVO.asmtTitle"].focus();
			return;
		}

		if(validate(document.prjAssignmentForm) ==false) return ;

		var asmtStartDttm = chgDate(f["prjAssignmentVO.asmtStartDttm"].value);
		var asmtEndDttm = chgDate(f["prjAssignmentVO.asmtEndDttm"].value);

		if(!dateCheck(asmtStartDttm, asmtEndDttm)) {
			alert("종료일을 시작일 보다 늦은 날짜를 선택하셔야 합니다.");
			return;
		}

		if(validateTime()==false) return;

		$('#prjAssignmentForm').find('input[name=cmd]').val('edit');
		Editor.save();
	}

	/* 글목록 이동 */
	function listRead() {
		$('#prjAssignmentForm')
			.find('input[name=cmd]').val("main").end()
			.submit();
	}

	/* 과제 삭제 */
	function delAssiBtn() {
		if(confirm('과제를 삭제하려고 합니다.\n\n삭제 하시겠습니까?')) {
			$('#prjAssignmentForm')
				.find('input[name=cmd]').val("remove").end()
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

		var asmtStartHour = chgDate(f["prjAssignmentVO.asmtStartHour"].value);  //과제 시작일 시''
		var asmtStartMin = chgDate(f["prjAssignmentVO.asmtStartMin"].value);   //과제 시작일 분''

		var asmtEndHour = chgDate(f["prjAssignmentVO.asmtEndHour"].value);  //과제 종료일 시''
		var asmtEndMin = chgDate(f["prjAssignmentVO.asmtEndMin"].value);   //과제 종료일 분''

		if(asmtStartHour==""){
			alert("과제시작 시간을 등록하여 주시기 바랍니다." );
			f["prjAssignmentVO.asmtStartHour"].focus();
			return false;
		}
		else if(asmtStartMin==""){
			alert("과제시작 시간(분)을 등록하여 주시기 바랍니다." );
			f["prjAssignmentVO.asmtStartMin"].focus();
			return false;
		}
		else if(asmtEndHour==""){
			alert("과제종료 시간을 등록하여 주시기 바랍니다." );
			f["prjAssignmentVO.asmtEndHour"].focus();
			return false;
		}
		else if(asmtEndMin==""){
			alert("과제종료 시간(분)을 등록하여 주시기 바랍니다." );
			f["prjAssignmentVO.asmtEndMin"].focus();
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

	function setFrame(){
		var iframeObj = parent.document.getElementById("subWorkFrame2");
		parent.resizeIframe3(iframeObj, $(document).height());
	}

	/**
	 * Editor.save()를 호출시 데이터 유효여부 검사 콜백함수. 유효할 경우 true 리턴.
	 * @function
	 * @param {Object} editor - 에디터에서 넘겨주는 editor 객체
	 * @returns {Boolean} 모든 데이터가 유효할 경우에 true
	 */
	function validForm(editor) {
		 if(!validate(document.prjAssignmentForm)) return false;

		/* 본문 내용이 입력되었는지 검사하는 부분 */
		var _validator = new Trex.Validator();
		var _content = editor.getContent();
		if(!_validator.exists(_content)) {
			alert('내용을 입력하세요');
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
		/*
		// 에디터 첨부파일 번호 목록 추출
		var _paramImages = Editor.getAttachmentIds(editor, 'image', true);

		// atchFiles에서 첨부파일 정보를 가져온다.
		var _paramFiles = atchFiles.getFileSnAll();
		var _paramPhotos = atchPhotos.getFileSnAll();
		var _thumbFile = thumbFile.getFileSnAll();


		// 썸네일파일이 등록되어 있지 않은 경우 첨부이미지 첫번째 이미지를 썸네일 파일로 사용한다.
		if(_thumbFile == '' && _paramPhotos != '') {
			try {
				var imgArr = _paramPhotos.split('!@!');
				_thumbFile = imgArr[(imgArr.length-1)];
			} catch(er) {
				_thumbFile = _paramGallerys;
			}
		}
		// 썸네일파일이 등록되어 있지 않은 경우 내용에 첨부된 첫번째 이미지를 썸네일 파일로 사용한다.
		if(_thumbFile == '' && _paramImages != '') {
			try {
				var imgArr = _paramImages.split('!@!');
				_thumbFile = imgArr[(imgArr.length-1)];
			} catch(er) {
				_thumbFile = _paramImages;
			}
		}

		$(':input:hidden[name=bbsAtclDTO\\.attachImageSns]').val(_paramImages);
		$(':input:hidden[name=bbsAtclDTO\\.attachFileSns]').val(_paramFiles);
		$(':input:hidden[name=bbsAtclDTO\\.attachPhotoSns]').val(_paramPhotos);
		$(':input:hidden[name=bbsAtclDTO\\.thumbFileSn]').val(_thumbFile);
		 */
		return true;
	}

	</script>
</mhtml:frm_body>
</mhtml:html>