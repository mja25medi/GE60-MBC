<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="img_base" value="/img/uniedu/home" />
<c:set var="gubun" value="${gubun}"/>

				<form id="lecQnaForm" name="lecQnaForm" method="post">
				<input type="hidden" name="qnaSn" id="qnaSn" value="${ansr.qnaSn}" />
				<input type="hidden" name="attachFileSns" value="${ansr.attachFileSns}" />
				<input type="hidden" name="attachImageSns" value="${ansr.attachImageSns}" />
				<input type="hidden" name="editorYn" value="Y" />
				
				
				<div class="segment">
                    <div class="tstyle">
                        <ul class="dbody">
                            <li>
                                <div class="row">
                                    <label for="titleInput" class="form-label col-sm-2"><spring:message code="common.title.title"/></label>
                                    <div class="col-sm-10">
                                        <div class="form-row">
                                            <input type="text" name="" value="[답변] 질문번호 : ${ansr.qnaSn }번" isNull="N" class="form-control"" readonly />
                                        </div>             
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="row">
                                    <label for="contTextarea" class="form-label col-sm-2">내용</label>
                                    <div class="col-sm-10">
                                        <div class="editor">
                                 		<div id="summernote" >${ansr.qnaCts}</div>
										<textarea name="qnaCts" id="contentTextArea" style="display:none" title="common.title.cnts">${ansr.qnaCts}</textarea>
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="row">
                                    <span class="form-label col-sm-2"><spring:message code="common.title.atachfile"/></span>
                                    <div class="col-sm-10 attach_area">
                                        <a href="javascript:uploderclick('atchuploader');"><label for="attchFile" class="btn gray2"><spring:message code="button.select.file"/></label></a>
                                        <input type="file" class="input_attach sr-only" name="atchuploader" id="atchuploader" multiple style="display:none">
                                        <div id="atchprogress" class="progress">
						    				<div class="progress-bar progress-bar-success"></div>
										</div> 
                                        <div class="attach_list" id="atchfiles"></div>                                            
                                    </div> 
                                </div>
                            </li>     
                        </ul>
                    </div>
                   <div class="btns mt30">
                     <c:if test="${gubun ne 'E'}">
						 <button type="button" class="btn gray2" onclick="addAsnr()"><spring:message code="button.add"/></button>
					</c:if>
					<c:if test="${gubun eq 'E'}">
						 <button type="button" class="btn gray2" onclick="editAnsr()"><spring:message code="button.add"/></button>
						 <button type="button" class="btn type9" onclick="removeAnsr()"><spring:message code="button.delete"/></button>
					</c:if>
						<button type="button" class="btn type5" onclick="cancelAnsr()"><spring:message code="button.cancel"/></button>
                </div>
                </div>
				</form>

<script type="text/javascript">
var atchFiles; // 첨부파일 목록
var summernote;
var submitChk = false;

$(document).ready(function() {
	summernote = new $M.SummerNote({
		"editorId"			:	"summernote",
		"textareaId"		:	"contentTextArea",
		"repositoryCode"	:	"QNA_ANSR",
		'organization' 		: 	"${USER_ORGCD}",
		"editorHeight"		:	"400px",
		"attachments"		:	$.parseJSON('${ansr.attachImagesJson}')
	});
	
	// 파일 첨부 업로더 및 바인딩 및 초기화 @see /js/common_function.js 참조.
	atchFiles = new $M.JqueryFileUpload({
					"varName"			: "atchFiles",
					"files" 			: $.parseJSON('${ansr.attachFilesJson}'),
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
});

function uploderclick(str) {
	$("#"+str).click();
}

 /* 서브밋 처리 */
function process(cmd) {
	var qnaSn = $("#qnaSn").val();
	 
	if(submitChk) {
		alert("처리 중 입니다.");
		return;
	}
	
	$('#lecQnaForm').attr("action","/lec/qna/"+cmd);
	var _content = $("#summernote").summernote('code');
	if(_content == "<p><br></p>" || isEmpty(_content)){
		alert('<spring:message code="board.message.bbs.atcl.alert.input.cnts"/>');
		return false;
	}
	//-- 내용 form에 setting
	$("#contentTextArea").val(_content);
	// 에디터 첨부파일 번호 목록 추출
	var _paramImages = summernote.getFileSnAll();
	var _paramFiles = atchFiles.getFileSnAll();

	$(':input:hidden[name=attachImageSns]').val(_paramImages);
	$(':input:hidden[name=attachFileSns]').val(_paramFiles);
	
	$('#lecQnaForm').ajaxSubmit(
		function(data) {
			if(data.result > 0 ) {
				alert(data.message);
				document.location.href = "/lec/qna/viewQnaMain?qnaSn=" + qnaSn;
			} else {
				alert(data.message);
				submitChk = false;
			}
		}
	);
	submitChk = true;
}
 /* 글 저장 */
function addAsnr() {
	process("writeAnsr");
}

/* 글 수정 */
function editAnsr(){
	process("editAnsr");
}

/* 글 삭제 */
function removeAnsr() {
	var qnaSn = $("#qnaSn").val();
	
	if(submitChk) {
		alert("처리 중 입니다.");
		return;
	}
	
	if(confirm('답변 내용을 정말 삭제 하시겠습니까?')) {
		$('#lecQnaForm').attr("action","/lec/qna/removeAnsr");
		$('#lecQnaForm').ajaxSubmit(
			function (data) {
				if(data.result > 0) {
					alert(data.message);
					document.location.href = "/lec/qna/viewQnaMain?qnaSn=" + qnaSn;
				} else {
					alert(data.message);
					submitChk = false;
				}
			}
		);
		submitChk = true;
	} else {
		return;
	}
}
function cancelAnsr(){
	var url = "";
	document.location.href = url;
}
</script>
