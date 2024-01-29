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
				<input type="hidden" name="originRegNo" value="${vo.originRegNo}"/>
				
                <div class="learn_top">
                    <div class="left_title">
                        <h3>
                        	<c:if test="${param.bbsCd eq 'NOTICE' }">과목 공지사항</c:if>
                        	<c:if test="${param.bbsCd eq 'PDS' }">과목 자료실</c:if>
                        </h3>
                    </div>
                </div>
                 <div class="segment">
                <div class="tstyle">
                        <ul class="dbody">
                            <li>
                                <div class="row">
                                    <label for="titleInput" class="form-label col-sm-2"><spring:message code="common.title.title"/></label>
                                    <div class="col-sm-10">
                                        <div class="form-row">
                                            <input class="form-control" type="text" name="title" value="${lecBbsAtclVO.title}" id="atclTitle"  isNull="N" lenCheck="100" maxlength="100" placeholder="제목을 입력하세요">
                                        </div>             
                                    </div>
                                </div>
                            </li>
                            <c:if test="${lecBbsAtclVO.bbsCd eq 'QNA' }">
                            	<li>
								  <div class="row">
                                    <label for="titleInput" class="form-label col-sm-2"><spring:message code="board.title.qna.private"/></label>
                                    <div class="col-sm-10">
                                        <div class="form-row">
                                        	<input type="checkbox" name="lockYn" id="lockYn" value="Y" <c:if test="${lecBbsAtclVO.lockYn eq 'Y'}">checked</c:if>/>&nbsp;&nbsp;<spring:message code="board.title.qna.private.info"/>
                                        </div>
                                   </div>
                                  </div>
								</li>
							</c:if>
                            <li>
                                <div class="row">
                                    <label for="contTextarea" class="form-label col-sm-2">내용</label>
                                    <div class="col-sm-10">
                                        <div class="editor">
											<div id="summernote" style="float:left; width:100%; margin: 0 auto;">${lecBbsAtclVO.cts}</div>
										<textarea name="cts" id="contentTextArea" style="display:none" title="common.title.cnts">${lecBbsAtclVO.cts}</textarea>
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
                    </div>
				 <div class="btns mt30">
                     <c:if test="${gubun ne 'E'}">
						 <button type="button" class="btn gray2" onclick="addAtcl()"><spring:message code="button.add"/></button>
					</c:if>
					<c:if test="${gubun eq 'E'}">
						 <button type="button" class="btn gray2" onclick="editAtcl()"><spring:message code="button.add"/></button>
						 <button type="button" class="btn type3" onclick="removeAtcl()"><spring:message code="button.delete"/></button>
					</c:if>
					 <button type="button" class="btn type5" onclick="cancelAtcl()"><spring:message code="button.cancel"/></button>
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
		"repositoryCode"	:	"LEC_BBS_ATCL",
		'organization' 		: 	"${USER_ORGCD}",
		"editorHeight"		:	"400px",
		"attachments"		:	$.parseJSON('${lecBbsAtclVO.attachImagesJson}')
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
	 
	if(submitChk) {
		alert("처리 중 입니다.");
		return;
	}
	
	$('#lecBbsForm').attr("action","/lec/bbs/"+cmd);
	if(!validate(document.lecBbsForm)) return false;
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

	$(':input:hidden[name=attachImagesSn]').val(_paramImages);
	$(':input:hidden[name=attachFilesSn]').val(_paramFiles);
	
	$('#lecBbsForm')[0].submit();
	submitChk = true;
}
 /* 글 저장 */
function addAtcl() {
	process("addAtcl");
}

/* 글 수정 */
function editAtcl(){
	process("editAtcl");
}

/* 글 삭제 */
function removeAtcl() {
	if(confirm('<spring:message code="board.message.bbs.atcl.confirm.delete"/>')) {
		$('#lecBbsForm').attr("action","/lec/bbs/removeAtcl");
		$('#lecBbsForm')[0].submit();
	} else {
		return;
	}
}
function cancelAtcl(){
	var url = generateUrl("/lec/bbs/main")+"?bbsCd=${lecBbsAtclVO.bbsCd}&curPage=${lecBbsAtclVO.curPage}"
	document.location.href = url;
}

</script>
