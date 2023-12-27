<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="img_base" value="/img/uniedu/home" />
<c:set var="lecBbsAtclVO" value="${vo}"/>
<c:set var="gubun" value="${gubun}"/>
				<form id="lecBbsForm" name="lecBbsForm" onsubmit="return false">
			    <input type="hidden" name="crsCreCd" value="${vo.crsCreCd}" />
				<input type="hidden" name="bbsCd" value="${vo.bbsCd}" />
				<input type="hidden" name="searchKey" value="${vo.searchKey}" />
				<input type="hidden" name="searchValue" value="${vo.searchValue}" />
				<input type="hidden" name="atclSn" value="${vo.atclSn}"/>
				<input type="hidden" name="parAtclSn" value="${vo.parAtclSn}" />
				<input type="hidden" name="atclOdr" value="${vo.atclOdr}" />
				<input type="hidden" name="atclLvl" value="${vo.atclLvl}" />
				<input type="hidden" name="curPage" value="${vo.curPage}" />
				<input type="hidden" name="attachFilesSn" value="${vo.attachFilesSn}" />
				<input type="hidden" name="attachImagesSn" value="${vo.attachImagesSn}" />
				<input type="hidden" name="editorYn" value="Y" />
				<div class="row">
					<div class="col-lg-12">
						<table class="table table-bordered wordbreak">
							<caption class="sr-only"><spring:message code="lecture.title.bbs"/></caption>
							<colgroup>
								<col style="width:20%" />
								<col />
							</colgroup>
							<tbody>
								<tr>
									<th scope="row" ><label for="atclTitle"><spring:message code="common.title.title"/></label></th>
									<td scope="row">
										<input type="text" name="title" value="${vo.title}" dispName="<spring:message code="common.title.title"/>" isNull="N" lenCheck="100" maxlength="100" class="form-control input-sm" id="atclTitle"/>
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
										<div id="daumeditor" style="float:left; width:100%; margin: 0 auto;"></div>
										<textarea name="cts" id="contentTextArea" style="display:none" title="common.title.cnts">${vo.cts}</textarea>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
				<div class="row" style="margin-bottom:20px;">
					<div class="col-lg-12">
						<div class="text-right">
							<c:if test="${gubun ne 'E'}">
								<a href="#" onclick="addAtcl()" class="btn btn-primary btn-sm"><spring:message code="button.add"/></a>
							</c:if>
							<c:if test="${gubun eq 'E'}">
								<a href="#" onclick="editAtcl()" class="btn btn-primary btn-sm"><spring:message code="button.add"/></a>
								<a href="#" onclick="delNotice()" class="btn btn-warning btn-sm"><spring:message code="button.delete"/></a>
							</c:if>
							<a href="<c:url value="/lec/bbs/main?bbsCd=${lecBbsAtclVO.bbsCd}&amp;curPage=${lecBbsAtclVO.curPage}"/>" class="btn btn-default btn-sm"><spring:message code="button.cancel"/></a>
						</div>
					</div>
				</div>
				</form>

<script type="text/javascript">
	var atchFiles; // 첨부파일 목록
	$(document).ready(function() {

		$M.CreateEditor({		// @see /js/daumeditor.js 참고
			"editorId"			:	"daumeditor",
			"formId"			:	"lecBbsForm",
			"textareaId"		:	"contentTextArea",
			"repositoryCode"	:	"LEC_BBS_ATCL",
			"attachments"		:	$.parseJSON('${lecBbsAtclVO.attachImagesJson}'),
			"editorHeight"		:	"400px"
		});

		// 파일 첨부 업로더 및 바인딩 및 초기화 @see /js/common_function.js 참조.
		atchFiles = new $M.JqueryFileUpload({
						"varName"			: "atchFiles",
						"files" 			: $.parseJSON('${lecBbsAtclVO.attachFilesJson}'),
						"uploaderId"		: "atchuploader",
						"fileListId"		: "atchfiles",
						"progressId"		: "atchprogress",
						"maxcount"			: 3,
						"uploadSetting"		: {
							'formData'		: { 'repository': 'LEC_BBS_ATCL',
												'organization' : "${USER_ORGCD}",
												'type'		: 'file' }
						}
					});
	});

	function uploderclick(str) {
		$("#"+str).click();
	}

	 /* 서브밋 처리 */
	function process(cmd) {
		$('#lecBbsForm').submit();
	}
	 /* 글 저장 */
	function addAtcl() {
		$('#lecBbsForm').attr("action","/lec/bbs/addAtcl");
		Editor.save();	// 다음에디터 저장.

		$(':input:hidden[name=attachFilesSn]').val(atchFiles.getFileSnAll());
		$('#uploadify').attr("disabled", true); //-- 파일 객체 disable
	}

	/* 글 수정 */
	function editAtcl(){
		$('#lecBbsForm').attr("action","/lec/bbs/editAtcl");
		Editor.save();	// 다음에디터 저장.

		$(':input:hidden[name=attachFilesSn]').val(atchFiles.getFileSnAll());
		$('#uploadify').attr("disabled", true); //-- 파일 객체 disable
	}

	/* 글 삭제 */
	function delNotice() {
		if(confirm('게시글을 삭제하려고 합니다.\n\n삭제 하시겠습니까?')) {
			$('#lecBbsForm')
				.attr("action","/lec/bbs/removeAtcl")
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
		if(!validate(document.lecBbsForm)) return false;
		/* 본문 내용이 입력되었는지 검사하는 부분 */
		var _validator = new Trex.Validator();
		var _content = editor.getContent();
		if(!_validator.exists(_content)) {
			alert('<spring:message code="common.message.alert.input.cnts"/>');
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

		$(':input:hidden[name=attachImagesSn]').val(_paramImages);

		return true;
	}

</script>
