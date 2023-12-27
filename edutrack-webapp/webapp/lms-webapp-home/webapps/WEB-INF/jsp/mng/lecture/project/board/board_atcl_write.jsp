<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="prjBbsAtclVO" value="${prjBbsAtclVO}"/>
<c:set var="prjBbsVO" value="${prjBbsVO}"/>
<mhtml:html>
<mhtml:head titleName="게시글 등록">
	<meditag:js src="/js/selectbox.js"/>
	<meditag:css href="libs/daumeditor/css/_orig/contents4view.css"/>
	<mhtml:head_module daumeditor="y" uploadify="y"/>
</mhtml:head>
<mhtml:frm_body>
	<div class="dvcontents">
		<form id="prjBbsAtclForm" name="prjBbsAtclForm" onsubmit="return false" action="/LecturePrjBbsAtclAdmin.do">
			<input type="hidden" name="cmd" />
			<input type="hidden" name="crsCreCd" />
			<input type="hidden" name="prjtSn" />
			<input type="hidden" name="bbsCd" />
			<input type="hidden" name="atclSn" />
			<input type="hidden" name="parAtclSn" />
			<input type="hidden" name="atclOdr" />
			<input type="hidden" name="atclLvl" />
			<input type="hidden" name="attachFileSns" />
			<div style="float:left; width:100%; height:30px;">
				<div style="float:left"><p style="font-weight:bold;margin-top:10px;">${prjBbsVO.bbsNm}</p></div>
			</div>
			<div id="error" class="red"></div>
			<table summary="게시글  등록 폼" style="width:100%" class="table_dtl" align="center">
				<tr height="35">
					<th scope="row" width="15%" class="top"><label for="title">제 목</label></th>
					<td class="top" colspan="3">
						<input type="text" name="atclTitle" style="width:75%; " dispName="제 목" isNull="N" lenCheck="100" maxlength="100" class="txt" id="atclTitle"/>
					</td>
				</tr>
				<tr>
					<td colspan="4" style="padding-left:0px;">
						<div id="daumeditor" style="float:left; width:100%; margin: 0 auto;"></div>
						<textarea name="atclCts" id="contentTextArea" style="display:none" title="내용"/>
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
			<meditag:buttonwrapper style="width: 98%; float:left">
				<c:if test="${gubun eq 'A'}">
					<meditag:button value="저장" title="저장" func="addArticle()" />
				</c:if>
				<c:if test="${gubun eq 'E'}">
					<meditag:button value="수정" title="수정" func="editArticle()" />
				</c:if>
				<meditag:button value="취소" title="취소" func="listArticle()" />
			</meditag:buttonwrapper>
		<input type="submit" value="submit" style="display:none" />
	</form>
	</div>
<script type="text/javascript">


	var atchFiles;	// 첨부파일 변수
	setFrame();

	// 페이지 초기화
	$(document).ready(function() {

		// 파일 첨부 업로더 및 바인딩 및 초기화 @see /js/common_function.js 참조.
		atchFiles = new $M.AttachFiles(
				{	"varName"			: "atchFiles",		// 변수명과 동일한 명칭 설정
					"maxcount"			: 5,
					"files"				: $.parseJSON('${prjBbsAtclVO.attachFilesJson}'),
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
			"editorHeight"		:	"400px"
		});


	});

	/* 서브밋 처리 */
	function process(cmd) {
		$('#prjBbsAtclForm').submit();
	}

	/* 글 저장 */
	function addArticle() {
		$(':input:hidden[name=prjBbsAtclVO\\.attachFileSns]').val(atchFiles.getFileSnAll());
		$('#uploadify').attr("disabled", true); //-- 파일 객체 disable

		$('#prjBbsAtclForm').find('input[name=cmd]').val('add');
		Editor.save();	// 다음에디터 저장.
	}

	/* 글 수정 */
	function editArticle() {
		$('#prjBbsAtclForm').find('input[name=cmd]').val('edit');
		Editor.save();
	}

	/* 글목록 이동 */
	function listArticle() {
		$('#prjBbsAtclForm')
			.find('input[name=cmd]').val("main").end()
			.submit();
	}

	/* 글 삭제 */
	function delArticle() {
		if(confirm('게시글을 삭제하려고 합니다.\n\n삭제 하시겠습니까?')) {
			$('#prjBbsAtclForm')
				.find('input[name=cmd]').val("remove").end()
				.submit();
		} else {
			return;
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
		 if(!validate(document.prjBbsAtclForm)) return false;

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