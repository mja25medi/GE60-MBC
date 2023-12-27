<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="PrjBbsAtclVO" value="${vo}"/>
<c:set var="prjBbsVO" value="${prjBbsVO}"/>
<mhtml:class_html>
<mhtml:class_head>
	<meditag:js src="/js/selectbox.js"/>
	<mhtml:head_module daumeditor="y" uploadify="y"/>
</mhtml:class_head>
<mhtml:body>
	<form id="prjBbsAtclForm" name="prjBbsAtclForm" >
		<input type="hidden" name="crsCreCd" value="${vo.crsCreCd}" />
		<input type="hidden" name="prjtSn" value="${vo.prjtSn}" />
		<input type="hidden" name="bbsCd" value="${vo.bbsCd}" />
		<input type="hidden" name="atclSn" value="${vo.atclSn}" />
		<input type="hidden" name="parAtclSn" value="${vo.parAtclSn}" />
		<input type="hidden" name="atclOdr" value="${vo.atclOdr}" />
		<input type="hidden" name="atclLvl" value="${vo.atclLvl}" />
		<input type="hidden" name="attachFileSns" value="${vo.attachFileSns}" />
		<div style="float:left; width:100%;">
			<div style="float:left"><p style="font-weight:bold;margin-top:10px;">${prjBbsVO.bbsNm}</p></div>
		</div>
		<div id="error" class="red"></div>
		<table class="board_b">
			<colgroup>
				<col style="width:20%"/>
				<col style="width:30%"/>
				<col style="width:20%"/>
				<col style="width:30%"/>
			</colgroup>
			<tr>
				<th scope="row" class="top">제 목</th>
				<td class="top" colspan="3">
					<input type="text" name="atclTitle" value="${vo.atclTitle}" style="width:97%; " dispName="제 목" isNull="N" lenCheck="100" maxlength="100" class="text" id="atclTitle"/>
				</td>
			</tr>
			<tr>
				<td colspan="4" style="padding: 0px;">
					<div id="daumeditor" style="float:left; width:100%; margin: 0 auto;"></div>
					<textarea name="atclCts" id="contentTextArea" style="display:none" title="내용">${vo.atclCts}</textarea>
				</td>
			</tr>

			<c:if test="${prjBbsVO.atchUseYn eq 'Y'}">
			<tr>
				<th scope="row"> 첨부파일</th>
				<td colspan="3">
					<div style="flot:left"><input type="file" title="첨부파일" name="uploadify" id="uploadify"/><%-- 첨부파일 버튼 --%></div>
					<div style="flot:left" id="fileQueue"></div>
					<div style="flot:left" id="fileListview"></div>
				</td>
			</tr>
			</c:if>
		</table>
		<div class="btn_right">
			<c:if test="${gubun eq 'A'}">
			<a href="javascript:addArticle()" class="btn02">저장</a>
			</c:if>
			<c:if test="${gubun eq 'E'}">
			<a href="javascript:editArticle()" class="btn02">수정</a>
			</c:if>
			<a href="javascript:listArticle()" class="btn01">취소</a>
		</div>
		<input type="submit" value="submit" style="display:none" />
	</form>

<script type="text/javascript">
	var atchFiles;	// 첨부파일 변수
	// 페이지 초기화
	$(document).ready(function() {
		callResize();
		// 파일 첨부 업로더 및 바인딩 및 초기화 @see /js/common_function.js 참조.
		atchFiles = new $M.AttachFiles(
				{	"varName"			: "atchFiles",		// 변수명과 동일한 명칭 설정
					"maxcount"			: 5,
					"files"				: $.parseJSON('${PrjBbsAtclVO.attachFilesJson}'),
					"uploadifySetting"	: {
							"queueID"		: "fileQueue",
							"fileDesc"		:	"문서 파일 (*.hwp, *.doc, *.xls, *.docx, *.xlsx, *.zip, *.ppt, *.pptx)",
							"fileExt"		:	"*.hwp; *.doc; *.xls; *.docx; *.xlsx; *.zip; *.ppt; *.pptx;",
							"scriptData"	:	{
									"repository"	:	"PRJT_ATCL",
									"type"			:	"file"		}
				}});

		$M.CreateEditor({		// @see /js/daumeditor.js 참고
			"editorId"			:	"daumeditor",
			"formId"			:	"prjBbsAtclForm",
			"textareaId"		:	"contentTextArea",
			"repositoryCode"	:	"PRJ_BBS_ATCL",
			//"attachments"		:	$.parseJSON('${bbsAtclDTO.attachImagesJson}'),
			"editorHeight"		:	"200px"
		});


	});

	/* 서브밋 처리 */
	function process(cmd) {
		$('#prjBbsAtclForm').submit();
	}

	/* 글 저장 */
	function addArticle() {
		$(':input:hidden[name=attachFileSns]').val(atchFiles.getFileSnAll());
		$('#uploadify').attr("disabled", true); //-- 파일 객체 disable

		$('#prjBbsAtclForm').attr("action","/lec/prj/bbsAtcl/addPrjBbsAtclStu");
		Editor.save();	// 다음에디터 저장.
	}

	/* 글 수정 */
	function editArticle() {
		$('#prjBbsAtclForm').attr("action","/lec/prj/bbsAtcl/editPrjBbsAtclStu");
		Editor.save();
	}

	/* 글목록 이동 */
	function listArticle() {
		$('#prjBbsAtclForm')
			.attr("action","/lec/prj/bbsAtcl/listPrjBbsAtcl")
			.submit();
	}

	function callResize() {
		var height = document.body.scrollHeight+300;
        parent.resizeTopIframe(height);
	}

	/**
	 * Editor.save()를 호출시 데이터 유효여부 검사 콜백함수. 유효할 경우 true 리턴.
	 * @function
	 * @param {Object} editor - 에디터에서 넘겨주는 editor 객체
	 * @returns {Boolean} 모든 데이터가 유효할 경우에 true
	 */
	function validForm(editor) {

		var f = document.prjBbsAtclForm;
		if(isEmpty(f["atclTitle"].value)) {
			alert('게시글 제목을 입력하세요.');
			return false;
		}

		var _content = editor.getContent();

		if(isEmpty(_content)) {
			alert('게시글 내용을 입력하세요.');
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
		//$(':input:hidden[name=PrjBbsAtclVO\\.attachImageSns]').val(Editor.getAttachmentIds(editor, 'image', true));

		// ajax호출시 multipart 오류로.. Input file disable 로
		$('#uploadify').attr("disabled", true);

		$('#prjBbsAtclForm').submit();

		return false;
	}
</script>
</mhtml:body>
</mhtml:class_html>