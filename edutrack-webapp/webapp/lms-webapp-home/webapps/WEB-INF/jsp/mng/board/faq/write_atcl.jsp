<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
				<section class="content">
					<div class="row" id="content">
						<div class="box">
							<div class="box-body">
								<div class="col-lg-12">
									<form name="faqAtclForm" id="faqAtclForm" method="POST">
									<input type="hidden" name="pageIndex" id="pageIndex" value="${vo.pageIndex}" />
									<input type="hidden" name="ctgrCd" value="${vo.ctgrCd}">
									<input type="hidden" name="ctgrNm" value="${vo.ctgrNm}">
									<input type="hidden" name="searchValue" value="${vo.searchValue}">
									<input type="hidden" name="atclSn" value="${vo.atclSn}">
									<input type="hidden" name="atclOdr" value="${vo.atclOdr}">
									<input type="hidden" name="attachImageSns" value="${vo.attachImageSns}">
									<input type="hidden" name="attachFileSns" value="${vo.attachFileSns}">
									<input type="hidden" name="editorYn" value="N">
									
									<div id="error" class="red"></div>
									<table summary="<spring:message code="board.title.faq.manage"/>" class="table table-bordered wordbreak">
										<colgroup>
											<col style="width:15%"/>
											<col style="width: 85%"/>
										</colgroup>
										<tr>
											<th scope="row"><label for="atclTitle"><spring:message code="common.title.title"/></label></th>
											<td>
												<input type="text" value="${vo.atclTitle }" dispName="<spring:message code="common.title.title"/>" maxlength="100" class="form-control input-sm" id="atclTitle" name="atclTitle"/>
											</td>
										</tr>
										<tr>
											<td colspan="2">
												<div id="summernote" style="float:left; width:100%; margin: 0 auto;">${vo.atclCts}</div>
												<textarea name="atclCts" id="contentTextArea" title="<spring:message code="board.title.bbs.atcl.cnts"/>" class="sr-only"></textarea>
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
									</table>
									<div class="text-right" style="margin-bottom:10px;">
										<c:if test="${gubun ne 'E'}">
										<a href="javascript:addAtcl()" class="btn btn-primary btn-sm"><spring:message code="button.add"/> </a>
										</c:if>
										<c:if test="${gubun eq 'E'}">
										<a href="javascript:editAtcl()" class="btn btn-primary btn-sm"><spring:message code="button.add"/> </a>
										<a href="javascript:removeAtcl()" class="btn btn-warning btn-sm"><spring:message code="button.delete"/> </a>
										</c:if>
										<a href="javascript:listAtcl()" class="btn btn-default btn-sm"><spring:message code="button.cancel"/> </a>
									</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</section>
<script type="text/javascript">
	var atchFiles;	// 첨부파일 변수
	var atchPhotos; // 첨부사진 변수
	var thumbFile;
	var summernote;
	var data = "";

	// 페이지 초기화
	$(document).ready(function() {
		summernote = new $M.SummerNote({
			"editorId"			:	"summernote",
			"textareaId"		:	"contentTextArea",
			"repositoryCode"	:	"FAQ_ATCL",
			'organization' 		: 	"${USER_ORGCD}",
			"editorHeight"		:	"400px",
			"attachments"		:	$.parseJSON('${vo.attachImagesJson}')
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
								'formData'		: { 'repository': 'FAQ_ATCL',
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
		$('#faqAtclForm').attr("action","/mng/brd/faq/" + cmd);
		
		var _content = $("#summernote").summernote('code');
		
		if($.trim($("#atclTitle").val()) == ""){
			alert("<spring:message code="board.message.bbs.atcl.alert.input.title"/>");
			return false;
		}
		
		if(_content == "<p><br></p>" || isEmpty(_content)){
			alert("<spring:message code="board.message.bbs.atcl.alert.input.cnts"/>");
			return false;
		}
		//-- 내용 form에 setting
		$("#contentTextArea").val(_content);

		// 에디터 첨부파일 번호 목록 추출
		var _paramImages = summernote.getFileSnAll();
		var _paramFiles = '';
		var _paramPhotos = '';
		var _thumbFile = '';
		// atchFiles에서 첨부파일 정보를 가져온다.
		_paramFiles = atchFiles.getFileSnAll();

		$(':input:hidden[name=attachFileSns]').val(_paramFiles);

		$('#faqAtclForm')[0].submit();
	}

	/* 글 저장 */
	function addAtcl() {
		process("addAtclMain");
	}

	/* 글 수정 */
	function editAtcl() {
		process("editAtcl");
	}

	/* 글목록 이동 */
	function listAtcl() {
		var url = generateUrl("/mng/brd/faq/main", {"searchValue": "${vo.searchValue}", "ctgrCd": "${vo.ctgrCd}", "ctgrNm": "${vo.ctgrNm}" });
		document.location.href = url;
	}

	/* 글 삭제 */
	function removeAtcl() {
		if(confirm("<spring:message code="board.message.bbs.atcl.confirm.delete"/>")) {
			$('#faqAtclForm').attr("action","/mng/brd/faq/removeAtcl");
			$('#faqAtclForm')[0].submit();
		} else {
			return;
		}
	}
</script>
