<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="olcCntsVO" value="${vo}"/>
<c:set var="gubun" value="${gubun}"/>
<mhtml:home_html>
<mhtml:home_head titleName="${MENUNAME}">
	<mhtml:head_module daumeditor="y" fileupload="y"/>
</mhtml:home_head>
<body style="padding-top: 0px;">
<form id="olcCartrgForm" name="olcCartrgForm" onsubmit="return false">
<input type="hidden" name="cartrgCd" value="${vo.cartrgCd}" />
<input type="hidden" name="cntsCd" value="${vo.cntsCd}" />
<input type="hidden" name="cntsDiv" value="${vo.cntsDiv}" />
<input type="hidden" name="cntsLoc" value="${vo.cntsLoc}" />
<input type="hidden" name="cntsOdr" value="${vo.cntsOdr}" />
<table class="table table-striped table-bordered" style="width:100%;">
	<caption class="sr-only"><spring:message code="board.title.bbs.atcl.cnts"/></caption>
	<colgroup>
		<col style="width: 15%"/>
		<col style="width: 85$"/>
	</colgroup>
	<tr>
		<th scope="row"><label for="cntsNm"><spring:message code="common.title.title"/></label></th>
		<td>
			<input type="text" name="cntsNm" value="${vo.cntsNm}" dispName="<spring:message code="common.title.title"/>" isNull="N" lenCheck="50" maxlength="50" class="form-control input-sm" id="cntsNm" />
		</td>
	</tr>
	<tr>
		<th scope="row"><spring:message code="common.title.atachfile"/></th>
		<td>
			<div class="upload">
				<div class="upload_inbox">
					<a href="javascript:uploderclick('atchuploader');" class="btn btn-primary btn-xs"><spring:message code="button.select.file"/></a>
					<input type="file" name="atchuploader" id="atchuploader" title="<spring:message code="common.title.atachfile"/>" multiple style="display:none" /><%-- 첨부파일 버튼 --%>
					<div id="atchprogress" class="progress">
	    				<div class="progress-bar progress-bar-success"></div>
					</div>
				</div>
				<div id="atchfiles" class="multi_inbox"></div>
			</div>
		</td>
	</tr>

	<tr>
		<td colspan="2" style="padding:0px;">
			<div id="daumeditor" style="width:820px; margin: 0 auto;"></div>
			<textarea name="cntsCts" id="contentTextArea" title="olc.title.contents..cnts" class="sr-only">${vo.cntsCts}</textarea>
		</td>
	</tr>
</table>
<div class="text-right">
	<c:if test="${gubun eq 'A'}">
	<a href="javascript:addCnts()" class="btn btn-primary btn-sm"><spring:message code="button.add"/></a>
	</c:if>
	<c:if test="${gubun eq 'E'}">
	<a href="javascript:editCnts()" class="btn btn-primary btn-sm"><spring:message code="button.add"/></a>
	<a href="javascript:delCnts()" class="btn btn-warning btn-sm"><spring:message code="button.delete"/></a>
	</c:if>
	<a href="javascript:closeWrite()" class="btn btn-default btn-sm"><spring:message code="button.cancel"/></a>
</div>
<input type="submit" value="submit" style="display:none" />
</form>

<script type="text/javascript">
	$(document).ready(function() {
		$M.CreateEditor({		// @see /js/daumeditor.js 참고
			"editorId"			:	"daumeditor",
			"formId"			:	"olcCartrgForm",
			"textareaId"		:	"contentTextArea",
			"repositoryCode"	:	"OLCCNTS",
			"attachments"		:	$.parseJSON('${vo.attachImagesJson}'),
			"editorHeight"		:	"380px",
			"editorWidth"		:	"800px"
		});

		// 파일 첨부 업로더 및 바인딩 및 초기화 @see /js/common_function.js 참조.
		atchFiles = new $M.JqueryFileUpload({
							"varName"			: "atchFiles",
							"files" 			: $.parseJSON('${vo.attachFilesJson}'),
							"uploaderId"		: "atchuploader",
							"fileListId"		: "atchfiles",
							"progressId"		: "atchprogress",
							"maxcount"			: 3,
							"uploadSetting"	: {
								'formData'		: { 'repository': 'OLCCNTS',
													'organization' : "${USER_ORGCD}",
													'type'		: 'file' }
							}
						});
	});

	function uploderclick(str) {
		$("#"+str).click();
	}

    /**
	 * 서브밋 처리
	 */
	function process(cmd) { //cmd : 해당메소드
		$('#olcCartrgForm').attr("action","/home/olc/cartrg/" + cmd);
		$('#olcCartrgForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		if(!isNull(resultDTO.message))
			alert(resultDTO.message);
		if(resultDTO.result >= 0) {
			// 정상 처리
			parent.listCnts();
			parent.closeWrite();
		} else {
			// 비정상 처리
		}
	}

	/* 글 저장 */
	function addCnts() {
		$('#olcCartrgForm').attr("action","/home/olc/cartrg/addCnts");
		Editor.save();	// 다음에디터 저장.
	}

	/* 글 수정 */
	function editCnts() {
		$('#olcCartrgForm').attr("action","/home/olc/cartrg/editCnts");
		Editor.save();
	}

	/* 글 삭제 */
	function delCnts() {
		if(confirm('<spring:message code="olc.message.contents.confirm.delete"/>')) {
			process("deleteCnts");
		} else {
			return;
		}
	}

	/**
	 * Editor.save()를 호출시 데이터 유효여부 검사 콜백함수. 유효할 경우 true 리턴.
	 * @function
	 * @param {Object} editor - 에디터에서 넘겨주는 editor 객체
	 * @returns {Boolean} 모든 데이터가 유효할 경우에 true
	 */
	function validForm(editor) {
		 if(!validate(document.olcCartrgForm)) return false;

		/* 본문 내용이 입력되었는지 검사하는 부분 */
		var _validator = new Trex.Validator();
		var _content = editor.getContent();
		if(!_validator.exists(_content)) {
			alert('<spring:message code="board.message.bbs.atcl.alert.input.cnts"/>');
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

		// 에디터 첨부파일 번호 목록 추출
		var _paramImages = Editor.getAttachmentIds(editor, 'image', true);
		// atchFiles에서 첨부파일 정보를 가져온다.
		var _paramFiles = atchFiles.getFileSnAll();

		$('#olcCartrgForm').ajaxSubmit(processCallback);
		return false;
	}

	 function closeWrite() {
		 parent.closeWrite();
	 }
</script>
</body>
</mhtml:home_html>