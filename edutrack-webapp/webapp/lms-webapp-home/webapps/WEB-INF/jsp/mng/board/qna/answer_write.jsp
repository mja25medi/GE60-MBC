<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../common/page_init.jsp" %>
<c:set var="qnaQstnDTO" value="${qnaForm.qnaQstnDTO}"/>
<c:set var="qnaAnsrDTO" value="${qnaForm.qnaAnsrDTO}"/>
<c:set var="gubun" value="${qnaForm.gubun}"/>
<mhtml:mng_html>
<mhtml:mng_head titleName="${MENUNAME}">
	<mhtml:head_module summernote="Y" fileupload="y"/>
</mhtml:mng_head>

<mhtml:mng_body>
			<mhtml:mng_title title="${MENUNAME}" location="${MENUPATH}"/>
			<div class="row" id="content">
				<div class="col-lg-12">
					<nested:form action="/QnaManage.do" onsubmit="return false" styleId="qnaForm">
					<input type="hidden" name="cmd"/>
					<nested:hidden property="curPage" />
					<nested:hidden property="qnaQstnDTO.searchKey" />
					<nested:hidden property="qnaQstnDTO.searchValue" />
					<nested:hidden property="qnaQstnDTO.qnaSn" />
					<nested:hidden property="qnaQstnDTO.attachImageSns" />
					<nested:hidden property="qnaQstnDTO.attachFileSns"/>

					<nested:hidden property="qnaAnsrDTO.qnaSn" />
					<nested:hidden property="qnaAnsrDTO.chrgPrsnNm"  value="${USER_NAME}"/>
					<nested:hidden property="qnaAnsrDTO.attachFileSns"/>
					<nested:hidden property="qnaAnsrDTO.editorYn" value="Y"/>


					<table summary="<meditag:message messageKey="user.title.userinfo.manage"/>" class="table table-bordered wordbreak">
						<colgroup>
							<col width="18%"/>
							<col width="32%"/>
							<col width="18%"/>
							<col width="32%"/>
						</colgroup>

						<tr height="33">
							<th scope="row"><label for="qnaTitle"><meditag:message messageKey="common.title.title"/></label></th>
							<td colspan="3">
								${qnaQstnDTO.qnaTitle}
							</td>
						</tr>
						<tr height="35">
							<th class="top" scope="row"><label for="userId"><meditag:message messageKey="common.title.reguser"/></label></th>
							<td class="top">
								${qnaQstnDTO.regNm}
							</td>
							<th class="top" scope="row"><meditag:message messageKey="common.title.regdate"/></th>
							<td class="top">
								<meditag:dateformat type="1" delimeter="." property="${qnaQstnDTO.regDttm}" />
								<meditag:dateformat type="7" delimeter="." property="${qnaQstnDTO.regDttm}" />
							</td>
						</tr>
						<tr height="70">
							<th scope="row"><label for="userNm"><meditag:message messageKey="common.title.cnts"/></label></th>
							<td colspan="3">
								${qnaQstnDTO.qnaCts}
								<table style="width:100%;">
									<tr>
										<td colspan="2" style="padding-top:5px;">
											<div id="QS_atchfiles" class="files"></div>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>

					<table summary="<meditag:message messageKey="user.title.userinfo.manage"/>" class="well table table-bordered wordbreak">
						<colgroup>
							<col width="18%"/>
							<col width="82%"/>
						</colgroup>
						<tr>
							<th colspan="2"><label for="userNm"><meditag:message messageKey="common.title.answer"/></label></th>
						</tr>
						<tr>
							<td colspan="2" style="padding:0px;">
								<div id="summernote" style="float:left; width:100%; margin: 0 auto;">${qnaAnsrDTO.qnaCts}</div>
								<nested:textarea property="qnaAnsrDTO.qnaCts" styleId="contentTextArea" style="display:none" title="common.title.cnts"/>
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="uploadify"><meditag:message messageKey="common.title.atachfile"/></label></th>
							<td>
								<div class="upload">
									<div class="upload_inbox">
										<a href="javascript:uploderclick('atchuploader');" class="btn btn-primary btn-xs"><meditag:message messageKey="button.select.file"/></a>
										<input type="file" name="atchuploader" id="atchuploader" title="<meditag:message messageKey="common.title.atachfile"/>" multiple style="display:none" /><%-- 첨부파일 버튼 --%>
										<div id="atchprogress" class="progress">
						    				<div class="progress-bar progress-bar-success"></div>
										</div>
									</div>
									<div id="atchfiles" class="multi_inbox"></div>
								</div>
							</td>
						</tr>
					</table>

					<div class="text-right" style="margin-bottom:20px;">
						<c:if test="${gubun eq 'A'}">
						<a href="javascript: addAnswer()" class="btn btn-primary btn-sm"><meditag:message messageKey="button.add"/></a>
						</c:if>
						<c:if test="${gubun eq 'E'}">
						<a href="javascript: editAnswer()" class="btn btn-primary btn-sm"><meditag:message messageKey="button.add"/></a>
						</c:if>
						<a href="<c:url value="/QnaManage.do"/>?cmd=main&amp;curPage=${qnaForm.curPage}" class="btn btn-default btn-sm"><meditag:message messageKey="button.close"/> </a>
					</div>
					<nested:submit value="submit" style="display:none" />
					</nested:form>
				</div>
			</div>

<script type="text/javascript">
	var atchFiles;	// 첨부파일 변수
	var QS_atchfiles;
	var summernote;

	// 페이지 초기화
	$(document).ready(function() {

		summernote = new $M.SummerNote({
							"editorId"			:	"summernote",
							"textareaId"		:	"contentTextArea",
							"repositoryCode"	:	"QNA_ANSR",
							'organization' 		: 	"${USER_ORGCD}",
							"editorHeight"		:	"400px",
							"attachments"		:	$.parseJSON('${qnaAnsrDTO.attachImagesJson}')
						});

		// 파일 첨부 업로더 및 바인딩 및 초기화 @see /js/common_function.js 참조.
		atchFiles = new $M.JqueryFileUpload({
						"varName"			: "atchFiles",
						"files" 			: $.parseJSON('${qnaAnsrDTO.attachFilesJson}'),
						"uploaderId"		: "atchuploader",
						"fileListId"		: "atchfiles",
						"progressId"		: "atchprogress",
						"maxcount"			: 3,
						"uploadSetting"		: {
							'formData'		: { 'repository': 'QNA_ANSR',
												'organization' : "${USER_ORGCD}",
												'type'		: 'file' }
						}
					});
		QS_atchfiles = new $M.JqueryFileUpload({
						"varName"			: "QS_atchfiles",
						"files" 			: $.parseJSON('${qnaQstnDTO.attachFilesJson}'),
						"fileListId"		: "QS_atchfiles",
						"maxcount"			: 3,
						"uploadSetting"		: {
							'formData'		: { 'repository': 'QNA_QSTN',
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
		$('#qnaForm').find('input[name=cmd]').val(cmd);
		if(!validate(document.qnaForm)) return false;
		var _content = $("#summernote").summernote('code');
		if(_content == "<p><br></p>" || isEmpty(_content)){
			alert('<meditag:message messageKey="board.message.bbs.atcl.alert.input.cnts"/>');
			return false;
		}
		//-- 내용 form에 setting
		$("#contentTextArea").val(_content);

		// 에디터 첨부파일 번호 목록 추출
		// 에디터 첨부파일 번호 목록 추출
		var _paramImages = summernote.getFileSnAll();
		var _paramFiles = '';
		var _paramPhotos = '';
		var _thumbFile = '';

		_paramFiles = atchFiles.getFileSnAll();
		$(':input:hidden[name=qnaAnsrDTO\\.attachFileSns]').val(_paramFiles);

		$('#qnaForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		alert(resultDTO.returnMessage);

		if(resultDTO.returnResult >= 0) {
			// 정상 처리
			<c:if test="${gubun eq 'A'}">
			parent.listQna(1);
			parent.modalBoxClose();
			</c:if>
			<c:if test="${gubun ne 'A'}">
			parent.listQna();
			</c:if>
		} else {
			// 비정상 처리
		}
	}

	/* 글 저장 */
	function addAnswer() {
		process("addAnswer");
	}

	/* 글 수정 */
	function editAnswer() {
		process("editAnswer");
	}


</script>
</mhtml:mng_body>
</mhtml:mng_html>