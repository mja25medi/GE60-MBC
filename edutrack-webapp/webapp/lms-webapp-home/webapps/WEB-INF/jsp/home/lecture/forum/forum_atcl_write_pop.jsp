<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="img_base" value="/img/home" />
<c:set var="gubun" value="${gubun}"/>
<c:set var="forumAtclVO" value="${vo}"/>

	<form id="forumForm" name="forumForm" onsubmit="return false" >
	<input type="hidden" name="forumSn" value="${vo.forumSn}" />
	<input type="hidden" name="crsCreCd" value="${vo.crsCreCd}" />
	<input type="hidden" name="curPage" value="${vo.curPage}" />
	<input type="hidden" name="atclSn" value="${vo.atclSn}" />
	<input type="hidden" name="parAtclSn" value="${vo.parAtclSn}" />  <!--상위글 유무 파악 -->
	<input type="hidden" name="attachFilesSn" value="${vo.attachFilesSn}" />
	<input type="hidden" name="editorYn" value="N" />
	<input type="hidden" name="gubun" value="${vo.gubun}" />
	<table class="table table-bordered">
		<colgroup>
			<col style="width:20%" />
			<col/>
		</colgroup>
		<tbody>
			<tr>
				<th scope="row"><spring:message code="common.title.title"/></th>
				<td>
					<input type="text" name="title" value="${vo.title}" dispName="<spring:message code="common.title.title"/>" isNull="N" lenCheck="100" maxlength="100" class="form-control input-sm"/>
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
					<textarea name="cts" id="contentTextArea"  class="sr-only">${vo.cts}</textarea>
				</td>
			</tr>
		</tbody>
	</table>
	<div class="text-right">
		<c:if test="${gubun eq 'A' || gubun eq 'AR' }">
		<a href="javascript:addAtcl()" class="btn btn-primary btn-sm"><spring:message code="button.add"/></a>
		</c:if>
		<c:if test="${gubun eq 'E'}">
		<a href="javascript:editAtcl()" class="btn btn-primary btn-sm"><spring:message code="button.add"/></a>
		<a href="javascript:delAtcl()" class="btn btn-warning btn-sm"><spring:message code="button.delete"/></a>
		</c:if>
		<a href="javascript:parent.modalBoxClose()" class="btn btn-default btn-sm"><spring:message code="button.close"/></a>
	</div>
	</form>

<script type="text/javascript">
	var ItemDTO = new Object();
	var atchFiles;	// 첨부파일 변수

	// 페이지 초기화
	$(document).ready(function() {

		$M.CreateEditor({		// @see /js/daumeditor.js 참고
			"editorId"			:	"daumeditor",
			"formId"			:	"forumForm",
			"textareaId"		:	"contentTextArea",
			"repositoryCode"	:	"POPUP",
			"attachments"		:	$.parseJSON('${forumAtclVO.attachImagesJson}'),
			"editorHeight"		:	"300px"
		});

		//부모창 새로고침
		if("${refreshYn}" == "D"){		//삭제일 경우
			parent.location.reload();
			parent.modalBoxClose();
		}

		//게시글 답변 수
		ItemDTO.replyCnt = ${forumAtclVO.replyCnt};

		atchFiles = new $M.JqueryFileUpload({
						"varName"			: "atchFiles",
						"files" 			: $.parseJSON('${forumAtclVO.attachFilesJson}'),
						"uploaderId"		: "atchuploader",
						"fileListId"		: "atchfiles",
						"progressId"		: "atchprogress",
						"maxcount"			: 3,
						"uploadSetting"		: {
							'formData'		: { 'repository': 'FORUM_ATCL',
												'organization' : "${USER_ORGCD}",
												'type'		: 'file' }
						}
					});

		$('.inputNumber').inputNumber();	// 숫자만 입력되도록 설정.
		$('.inputSpecial').inputSpecial();  // 특수키가 입력이 들어오묜 false return;

	});

	function uploderclick(str) {
		$("#"+str).click();
	}

	/**
	 * 게시글 등록
	 */
	function addAtcl() {
		$('#forumForm').attr("action","/lec/forum/addForumArticle");
		Editor.save();
	}

	/**
	* 게시글 수정
	*/
	function editAtcl(){
		$('#forumForm').attr("action","/lec/forum/editForumArticle");
		Editor.save();
	}

	/**
	 * 게시글 삭제
	 */
	function delAtcl() {
		if(ItemDTO.replyCnt > 0){
			alert("<spring:message code="lecture.messgae.forum.atcl.alert.delete"/>");
			return;
		}
		if(confirm('<spring:message code="lecture.message.forum.atcl.confirm.delete"/>')) {
			process("removeForumArticle");
		}
	}

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		$(':input:hidden[name=attachFilesSn]').val(atchFiles.getFileSnAll());
		$('#uploadify').attr("disabled", true); //-- 파일 객체 disable

		$('#forumForm').attr("action","/lec/forum/"+cmd);
		document.forumForm.submit();
	}

	/**
	 * Editor.save()를 호출시 데이터 유효여부 검사 콜백함수. 유효할 경우 true 리턴.
	 * @function
	 * @param {Object} editor - 에디터에서 넘겨주는 editor 객체
	 * @returns {Boolean} 모든 데이터가 유효할 경우에 true
	 */
	function validForm(editor) {
		if(!validate(document.forumForm)) return false;

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

		$(':input:hidden[name=attachImageSns]').val(_paramImages);
		return true;
	}
</script>
