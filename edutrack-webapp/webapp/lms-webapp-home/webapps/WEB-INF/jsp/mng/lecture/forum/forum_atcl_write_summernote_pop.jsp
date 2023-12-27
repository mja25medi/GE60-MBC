<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="forumAtclVO" value="${vo}"/>
<c:set var="gubun" value="${gubun}"/>
	<form id="forumForm" name="forumForm" onsubmit="return false" >
	<input type="hidden" name="forumSn" value="${vo.forumSn }" />
	<input type="hidden" name="crsCreCd" value="${vo.crsCreCd }" />
	<input type="hidden" name="curPage" value="${vo.curPage }" />
	<input type="hidden" name="atclSn" value="${vo.atclSn }" />
	<input type="hidden" name="parAtclSn" value="${vo.parAtclSn }" />  <!--상위글 유무 파악 -->
	<input type="hidden" name="attachFilesSn" value="${vo.attachFilesSn }" />
	<input type="hidden" name="attachImageSns" value="${vo.attachImageSns }" />
	<input type="hidden" name="editorYn" value="Y"/>

	<table summary="<spring:message code="lecture.title.forum.atcl.manage"/>"  class="table table-bordered wordbreak">
		<colgroup>
			<col style="width:20%"/>
			<col style="width:80%"/>
		</colgroup>
		<tr>
			<th scope="row"><spring:message code="common.title.title"/></th>
			<td colspan="3">
				<input type="text" name="title" value="${vo.title }" dispName="<spring:message code="common.title.title"/>" isNull="N" lenCheck="100" maxlength="100" class="form-control input-sm"/>
			</td>
		</tr>
		<tr>
			<th scope="row" style="width:20%"><spring:message code="common.title.atachfile"/></th>
			<td colspan="3">
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
			<td colspan="4" style="padding:0px;">
				<div id="summernote" style="float:left; width:100%; margin: 0 auto;">${forumAtclVO.cts}</div>
				<textarea name="cts" id="contentTextArea"  class="sr-only">${vo.cts }</textarea>
			</td>
		</tr>
	</table>
	<div class="text-right">
		<c:if test="${gubun eq 'A'}">
		<a href="javascript:addArticleForum()" class="btn btn-primary btn-sm"><spring:message code="button.add"/></a>
		</c:if>
		<c:if test="${gubun eq 'E'}">
		<a href="javascript:editArticleForum()" class="btn btn-primary btn-sm"><spring:message code="button.add"/></a>
		<a href="javascript:deleteArticle()" class="btn btn-warning btn-sm"><spring:message code="button.delete"/></a>
		</c:if>
		<a href="javascript:parent.modalBoxClose()" class="btn btn-default btn-sm"><spring:message code="button.close"/></a>
	</div>
	</form>
<script type="text/javascript">

	var ItemDTO = new Object();
	var atchFiles;	// 첨부파일 변수
	var summernote;

	// 페이지 초기화
	$(document).ready(function() {
		//게시글 답변 수
		ItemDTO.replyCnt = ${forumAtclVO.replyCnt};

		summernote = new $M.SummerNote({
			"editorId"			:	"summernote",
			"textareaId"		:	"contentTextArea",
			"repositoryCode"	:	"FORUM_ATCL",
			'organization' 		: 	"${USER_ORGCD}",
			"editorHeight"		:	"300px",
			"attachments"		:	$.parseJSON('${forumAtclVO.attachImagesJson}')
		});

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
	});

	function uploderclick(str) {
		$("#"+str).click();
	}

	/**
	* 게시글 정보 저장
	*/
	function addArticleForum() {
		process("addAtcl");
	}

	/**
	* 게시글 정보 수정
	*/

	function editArticleForum() {
		process("editAtcl");
	}

	/**
	* 게시글 삭제
	*/
	function deleteArticle(){
		if(ItemDTO.replyCnt > 0){
			alert("<spring:message code="lecture.messgae.forum.atcl.alert.delete"/>");
			return;
		}
		if(confirm('<spring:message code="lecture.message.forum.atcl.confirm.delete"/>')) {
			$('#forumForm').attr("action","/mng/lecture/forum/removeAtcl");
			$('#forumForm').ajaxSubmit(processCallback);
		}
	}

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		$('#forumForm').attr("action","/mng/lecture/forum/" + cmd);
		if(!validate(document.forumForm)) return false;
		var _content = $("#summernote").summernote('code');
		if(_content == "<p><br></p>" || isEmpty(_content)){
			alert('<spring:message code="board.message.bbs.atcl.alert.input.cnts"/>');
			return false;
		}
		//-- 내용 form에 setting
		$("#contentTextArea").val(_content);
		var _paramImages = summernote.getFileSnAll();
		var _paramFiles = atchFiles.getFileSnAll();

		$(':input:hidden[name=attachImageSns]').val(_paramImages);
		$(':input:hidden[name=attachFilesSn]').val(_paramFiles);

		$('#forumForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		alert(resultDTO.message);
		if(resultDTO.result >= 0) {
			// 정상 처리
			parent.subWorkFrame.listAtcl(1);
			parent.modalBoxClose();
		} else {
			// 비정상 처리
		}
	}

	/**
	* 토론 게시글 등록 창 닫기
	*/
	function closePop(){
		parent.forumPopBox.close();
	}

</script>
