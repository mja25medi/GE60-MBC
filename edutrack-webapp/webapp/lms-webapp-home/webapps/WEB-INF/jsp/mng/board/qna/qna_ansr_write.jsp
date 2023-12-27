<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
				<section class="content">
					<div class="row" id="content">
						<div class="box">
							<div class="box-body">
								<div class="col-lg-12">
									<form name="qnaAsnrForm" id="qnaAsnrForm" method="POST">
										<input type="hidden" name="qnaSn" id="qnaSn" value="${vo.qnaSn }"/>
										<input type="hidden" name="attachImageSns" id="attachImageSns" value="${vo.attachImageSns}" />
										<input type="hidden" name="attachFileSns" id="attachFileSns" value="${vo.attachFileSns}" />
										<input type="hidden" name="editorYn" id="editorYn" value="Y"/>
										<table summary="<spring:message code="board.title.bbs.atcl.cnts"/>" class="table table-bordered wordbreak">
											<colgroup>
												<col style="width:15%" />
												<col style="width:75%" />
											</colgroup>
											<tr>
												<th scope="row"><label for="atclTitle"><spring:message code="common.title.title"/></label></th>
												<td>
													<input type="text" name="ansrTitle" id="ansrTitle" maxlength="100" required="required" title="<spring:message code="common.title.title"/>" class="form-control input-sm" value="<c:out value="[답변] 질문번호 : ${vo.qnaSn }"/>" readonly />
												</td>
											</tr>
											<tr>
												<c:set var="fontFamily" value="Helvetica"/>
												<c:if test="${LOCALEKEY eq 'jp' }"><c:set var="fontFamily" value="Meiryo"/></c:if>
												<c:if test="${LOCALEKEY eq 'ko' }"><c:set var="fontFamily" value="맑은 고딕"/></c:if>
												<td colspan="2" style="padding:0px;font-family:${fontFamily}">
													<div id="summernote" style="float:left; width:100%; margin: 0 auto;">${vo.qnaCts}</div>
													<textarea name="qnaCts" id="contentTextArea" title="<spring:message code="board.title.bbs.atcl.cnts"/>" class="sr-only"></textarea>
												</td>
											</tr>
											<tr>
												<th scope="row"><spring:message code="common.title.atachfile"/></th>
												<td>
													<a href="javascript:uploderclick('atchuploader');" class="btn btn-primary btn-xs"><spring:message code="button.select.file"/></a>
													<input type="file" name="atchuploader" id="atchuploader" title="<spring:message code="common.title.atachfile"/>" multiple style="display:none"/><%-- 첨부파일 버튼 --%>
													<!-- The global progress bar -->
													<div id="atchprogress" class="progress">
											    		<div class="progress-bar progress-bar-success"></div>
													</div>
													<!-- The container for the uploaded files -->
													<div id="atchfiles" class="files multi_inbox"></div>
												</td>
											</tr>
										</table>
										<div class="text-right" style="margin-bottom:20px;">
											<c:if test="${gubun ne 'E'}">
											<a href="javascript:writeAnsr()" class="btn btn-primary btn-sm"><spring:message code="button.add"/></a>
											</c:if>
											<c:if test="${gubun eq 'E'}">
											<a href="javascript:editAnsr()" class="btn btn-primary btn-sm"><spring:message code="button.add"/></a>
											</c:if>
											<a href="javascript:listQstn()" class="btn btn-default btn-sm"><spring:message code="button.cancel"/></a>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</section>
<script type="text/javascript">
	var atchFiles;	// 첨부파일 변수
	var summernote;
	var data = "";

	// 페이지 초기화
	$(document).ready(function() {
		ItemVO.orgCd = "${USER_ORGCD}";
		ItemVO.attachImagesJson = '${vo.attachImagesJson}';
		ItemVO.attachFilesJson = '${vo.attachFilesJson}';
		
		summernote = new $M.SummerNote({
							"editorId"			:	"summernote",
							"textareaId"		:	"contentTextArea",
							"repositoryCode"	:	"QNA_ANSR",
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
								'formData'		: { 'repository': 'QNA_ANSR',
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
		$('#qnaAsnrForm').attr("action","/mng/brd/qna/" + cmd);
		if(!validate(document.qnaAsnrForm)) return false;
		var _content = $("#summernote").summernote('code');
		if(_content == "<p><br></p>" || isEmpty(_content)){
			alert("<spring:message code="board.message.bbs.atcl.alert.input.cnts"/>");
			return;
		}
		//-- 내용 form에 setting
		$("#contentTextArea").val(_content);

		// 에디터 첨부파일 번호 목록 추출
		var _paramImages = summernote.getFileSnAll();
		var _paramFiles = '';
		// atchFiles에서 첨부파일 정보를 가져온다.
		_paramFiles = atchFiles.getFileSnAll();

		$(':input:hidden[name=attachImageSns]').val(_paramImages);
		$(':input:hidden[name=attachFileSns]').val(_paramFiles);

		$('#qnaAsnrForm').ajaxSubmit(processCallback);
	}
	
	function processCallback(resultDTO) {
		alert(resultDTO.message);
		if(resultDTO.result == 1) {
			document.location.href = "/mng/brd/qna/main";
		} else {
			
		}
	}


	/* 글 저장 */
	function writeAnsr() {
		process("writeAnsr");
	}

	/* 글 수정 */
	function editAnsr() {
		process("editAnsr");
	}

	/* 글목록 이동 */
	function listQstn() {
		var url = generateUrl("/mng/brd/qna/main");
		document.location.href = url;
	}

</script>
