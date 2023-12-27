<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="img_base" value="/img/uniedu/home" />

	<script src="/app/js/Context.js"></script>

	<script src="/js/common.js"></script>
	<script src="/js/common_conf.js"></script>
	<script src="/js/common_util.js"></script>
	<script src="/js/common_function.js"></script>

	<meditag:js src="/js/jquery/jquery-fileupload/vendor/jquery.ui.widget.js"/>
	<meditag:js src="/js/jquery/jquery-fileupload/jquery.iframe-transport.js"/>
	<meditag:js src="/js/jquery/jquery-fileupload/jquery.fileupload.js"/>
	<meditag:js src="/js/common_fileupload.js"/>
	<link rel="stylesheet" href="/tpl/002/css/video.css"> 

<c:set var="gubun" value="${gubun}"/>

				<form id="lecQnaForm" name="lecQnaForm" onsubmit="return false" method="post">
				<input type="hidden" name="crsCreCd" id="crsCreCd" value="${CRSCRECD }" />
			    <input type="hidden" name="unitCd" id="unitCd" value="${vo.unitCd }" />
				<input type="hidden" name="attachFileSns" value="${vo.attachFileSns}" />
				<input type="hidden" name="qnaCtgrCd" value="LEC" />
				<input type="hidden" name="editorYn" value="N" />
				<input type="hidden" name="lecYn" value="Y" />
						
					<div class="tstyle">
		                <ul class="dbody">
		                    <li>
		                        <div class="row">
		                            <label for="qnaTitle" class="form-label col-sm-3">제목</label>
		                            <div class="col-sm-9">
		                                <div class="form-row">
		                                    <input class="form-control" type="text" name="qnaTitle" id="qnaTitle" value="" maxlength="100" placeholder="제목을 입력하세요"> 
		                                </div>             
		                            </div>
		                        </div>
		                    </li>
		                    <li class="testTextarea">
		                        <div class="row">
		                            <label for="contTextarea" class="form-label col-sm-3">내용</label>
		                            <div class="col-sm-9">
		                                <div class="form-row">
											<textarea name="qnaCts" class="form-control" id="qnaCts" rows="10" title="<spring:message code="board.title.bbs.atcl.cnts"/>"></textarea>
		                                </div>
		                            </div>
		                        </div>
		                    </li>
		                    <li class="testFileUpload">
								<div class="row">
									<span class="form-label col-sm-3">첨부파일</span>
									<div class="upload col-sm-9 attach_area">
										<div class="upload_inbox">
											<a href="javascript:uploderclick('atchuploader');" class="btn gray2" style="background: #4D4D4D; color: #fff; border-color: #4D4D4D;">
												<spring:message code="button.select.file"/>
											</a>
											<input type="file" name="atchuploader" id="atchuploader" title="<spring:message code="common.title.atachfile"/>" multiple style="display:none" /><%-- 첨부파일 버튼 --%>
											<div id="atchprogress" class="progress">
												<div class="progress-bar progress-bar-success"></div>
											</div>
										</div>
										<div id="atchfiles" class="multi_inbox attach_list"></div>
									</div>
								</div>
		                    </li>
		                </ul>
		            </div>
		            <div class="txt-center mt30">
		                <button type="button" class="btn gray2" onclick="addQstn()">등록</button>
		                <button type="button" class="btn type5" onclick="unloadForm()">취소</button>
		            </div>

				</form>

<script type="text/javascript">
	var atchFiles; // 첨부파일 목록
	var summernote;
	var submitChk = false;

	$(document).ready(function() {
		$("#qnaTitle").focus();
		$("html, body").animate({ scrollTop: $(document).height() }, 300);

		// 파일 첨부 업로더 및 바인딩 및 초기화 @see /js/common_function.js 참조.
		atchFiles = new $M.JqueryFileUpload({
						"varName"			: "atchFiles",
						"files" 			: $.parseJSON('${vo.attachFilesJson}'),
						"uploaderId"		: "atchuploader",
						"fileListId"		: "atchfiles",
						"progressId"		: "atchprogress",
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
		
		var qnaSn = $("#qnaSn").val();
		 
		if(submitChk) {
			alert("처리 중 입니다.");
			return;
		}
		
		$('#lecQnaForm').attr("action","/lec/qna/"+cmd);
		if(!validate(document.lecQnaForm)) return false;

		var _paramFiles = atchFiles.getFileSnAll();
		$(':input:hidden[name=attachFileSns]').val(_paramFiles);
		
		$('#lecQnaForm').ajaxSubmit(
			function(data) {
				if(data.result > 0 ) {
					alert(data.message);
					unloadForm();
				} else {
					alert(data.message);
					submitChk = false;
				}
			}
		);
		submitChk = true;
	}
	 /* 글 저장 */
	function addQstn() {
		process("writeQstn");
	}


</script>
