<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="img_base" value="/img/home" scope="request"/>
<c:set var="crsBbsAtclVO" value="${vo}"/>
<c:set var="crsBbsInfoVO" value="${crsBbsInfoVO}"/>
<c:set var="gubun" value="${gubun}"/>
<mhtml:class_html>
<mhtml:class_head>
	<mhtml:head_module daumeditor="Y" uploadify="y"/>
</mhtml:class_head>

<mhtml:class_body>
	<div id="content">
		<mhtml:class_location />

		<form id="crsBbsAtclForm" name="crsBbsAtclForm" onsubmit="return false">
		<input type="hidden" name="curPage" value="${vo.curPage}" />
		<input type="hidden" name="searchKey" value="${vo.searchKey}" />
		<input type="hidden" name="searchValue" value="${vo.searchValue}" />
		<input type="hidden" name="bbsCd" value="${vo.bbsCd}" />
		<input type="hidden" name="atclSn" value="${vo.atclSn}" />
		<input type="hidden" name="parAtclSn" value="${vo.parAtclSn}" />
		<input type="hidden" name="atclOdr" value="${vo.atclOdr}" />
		<input type="hidden" name="atclLvl" value="${vo.atclLvl}" />
		<input type="hidden" name="attachImagesSn" value="${vo.attachImagesSn}" />
		<input type="hidden" name="attachFilesSn" value="${vo.attachFilesSn}"/>
		<input type="hidden" name="editorYn" value="Y"/>

		<table class="board_b">
			<caption>${MENUNAME}</caption>
			<colgroup>
				<col style="width:15%" />
				<col style="width:75%" />
			</colgroup>
			<tbody>
				<tr>
					<th scope="row"><label for="atclTitle">제목</label></th>
					<td>
						<input type="text" name="title" value="${vo.title}" style="width:95%; " dispName="제 목" isNull="N" lenCheck="100" maxlength="100" class="text" id="atclTitle"/>
					</td>
				</tr>
				<tr>
					<td colspan="2" style="padding:0px;">
						<div id="daumeditor" style="float:left; width:100%; margin: 0 auto;"></div>
						<textarea name="cts" id="contentTextArea" style="display:none" title="내용">${vo.cts}</textarea>
					</td>
				</tr>
				<tr>
					<th scope="row"><label for="uploadify">첨부파일</label></th>
					<td>
						<input type="file" name="uploadify" id="uploadify" title="첨부 파일"/><%-- 첨부파일 버튼 --%>
						<div id="fileQueue"></div>
						<div id="fileListview"></div>
					</td>
				</tr>
			</tbody>
		</table>
		<div class="btn_center">
			<c:if test="${gubun eq 'A' || gubun eq 'AR'}">
			<a href="<c:url value="javascript:addAtcl()"/>"><img src="${img_base}/common/btn/btn_cofirm.gif" alt="확인" /></a>
			</c:if>
			<c:if test="${gubun eq 'E'}">
			<a href="<c:url value="javascript:editAtcl()"/>"><img src="${img_base}/common/btn/btn_cofirm.gif" alt="확인" /></a>
			<a href="<c:url value="javascript:delAtcl()"/>"><img src="${img_base}/common/btn/btn_canceltxt.gif" alt="삭제" /></a>
			</c:if>
			<a href="<c:url value="javascript:listAtcl()"/>"><img src="${img_base}/common/btn/btn_cancel.gif" alt="취소" /></a>
		</div>
		</form>
	</div>

<script type="text/javascript">
	var atchFiles;	// 첨부파일 변수
	// 페이지 초기화
	$(document).ready(function() {
		$M.CreateEditor({		// @see /js/daumeditor.js 참고
			"editorId"			:	"daumeditor",
			"formId"			:	"crsBbsAtclForm",
			"textareaId"		:	"contentTextArea",
			"repositoryCode"	:	"CRS_BBS_ATCL",
			"attachments"		:	$.parseJSON('${crsBbsAtclVO.attachImagesJson}'),
			"editorHeight"		:	"400px"
		});

		// 파일 첨부 업로더 및 바인딩 및 초기화 @see /js/common_function.js 참조.
		atchFiles = new $M.AttachFiles(
				{	"varName"			: "atchFiles",		// 변수명과 동일한 명칭 설정
					"files"				: $.parseJSON('${crsBbsAtclVO.attachFilesJson}'),
					"uploadifySetting"	: {
							"queueID"		: "fileQueue",
							"scriptData"	:	{
							"repository"	:	"CRS_BBS_ATCL",
							"type"			:	"file"		}
		}});
	});

	/* 서브밋 처리 */
	function process(cmd) {
		$('#crsBbsAtclForm').attr("action","/lec/crsBbs/"+cmd);
		$('#crsBbsAtclForm').submit();
	}

	/* 글 저장 */
	function addAtcl() {
		$('#crsBbsAtclForm').attr("action","/lec/crsBbs/add");
		Editor.save();	// 다음에디터 저장.
	}

	/* 글 수정 */
	function editAtcl() {
		$('#crsBbsAtclForm').attr("action","/lec/crsBbs/edit");
		Editor.save();
	}

	/* 글목록 이동 */
	function listAtcl() {
		$('#crsBbsAtclForm')
			.attr("action","/lec/crsBbs/main")
			.submit();
	}

	/* 글 삭제 */
	function delAtcl() {
		if(confirm('게시글을 삭제하려고 합니다.\n\n삭제 하시겠습니까?')) {
			$('#crsBbsAtclForm')
				.attr("action","/lec/crsBbs/remove")
				.submit();
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
		 if(!validate(document.crsBbsAtclForm)) return false;

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

		// 에디터 첨부파일 번호 목록 추출
		var _paramImages = Editor.getAttachmentIds(editor, 'image', true);

		// atchFiles에서 첨부파일 정보를 가져온다.
		var _paramFiles = atchFiles.getFileSnAll();

		$(':input:hidden[name=attachImagesSn]').val(_paramImages);
		$(':input:hidden[name=attachFilesSn]').val(_paramFiles);
		return true;
	}
</script>
</mhtml:class_body>
</mhtml:class_html>
