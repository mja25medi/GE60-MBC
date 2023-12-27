<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/common/page_init.jsp"%>
<% // 서브 메뉴 체크 %>

<form id="frm" name="frm" method="post">
	<input type="hidden" name="qnaSn" id="qnaSn" value="${vo.qnaSn}" />
	<input type="hidden" name="attachFileSns" id="attachFileSns" value="${vo.attachFileSns}" />
	<input type="hidden" name="attachImageSns" id="attachImageSns" value="${vo.attachImageSns}" />
	<input type="hidden" name="editorYn" id="editorYn" value="N" />
	<div class="detail_cont write">
		<div class="tstyle">
			<ul class="dbody">
				<li>
					<div class="row">
						<label for="titleInput" class="form-label col-sm-2">제목</label>
						<div class="col-sm-10">
							<div class="form-row">
								<input class="form-control" maxlength="100" name="qnaTitle" type="text" id="qnaTitle" title="<spring:message code="home.board.title"/>" value="<c:out value="${vo.qnaTitle}"/>">
							</div>
						</div>
					</div>
				</li>
				<li>
					<div class="row">
						<label for="starScoreInput" class="form-label col-sm-2">분류</label>
						<div class="col-sm-10">
							<div class="form-inline">
								<select id="qnaCtgrCd" name="qnaCtgrCd">
									<c:forEach var="item" items="${qnaCtgrList}" varStatus="status">
										<option value="${item.codeCd}" <c:if test="${item.codeCd eq vo.qnaCtgrCd }">selected</c:if>>${item.codeNm}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</div>
				</li>
				<c:if test="${gubun eq 'E'}">
					<li>
						<div class="row">
							<label for="writeInput" class="form-label col-sm-2">작성자</label>
							<div class="col-sm-10">
								<div class="form-inline">
									<input class="form-control" type="text" value="${vo.regNm}" readonly>
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
								<div id="summernote">${vo.qnaCts}</div>
								<textarea name="qnaCts" id="contentTextArea" title="<spring:message code="board.title.bbs.atcl.cnts"/>" class="sr-only"></textarea>
							</div>
						</div>
					</div>
				</li>
 				<li>
					<div class="row">
						<span class="form-label col-sm-2">첨부파일</span>
						<div class="upload col-sm-10 attach_area">
							<div class="upload_inbox">
								<a href="javascript:uploderclick('atchuploader');" class="btn btn-primary btn-xs" style="background: #4D4D4D; color: #fff; border-color: #4D4D4D;">
									<spring:message code="button.select.file"/>
								</a>
								<input type="file" name="atchuploader" id="atchuploader" title="<spring:message code="common.title.atachfile"/>" multiple style="display:none" />
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
		<div class="btns mt30">
			<c:if test="${gubun ne 'E'}">
				<button type="button" onclick="javascript:writeQstn();" class="btn gray2">등록</button>
				<button type="button" onclick="javascript:listQstn()" class="btn type5">취소</button>
			</c:if>
			<c:if test="${gubun eq 'E'}">
				<button type="button" onclick="javascript:editQstn();" class="btn gray2">수정</button>
				<button type="button" onclick="javascript:viewQna();" class="btn type5">취소</button>
			</c:if>
		</div>
	</div>
</form>

<script type="text/javascript">
	var atchFiles;	// 첨부파일 변수
	var summernote;
	var data = "";
	
	var ItemVO = {};
	
	// 페이지 초기화
	$(document).ready(function() {
		ItemVO.orgCd = "${USER_ORGCD}";
		
		atchFiles = new $M.JqueryFileUpload({
							"varName"			: "atchFiles",
							"files" 			: $.parseJSON('${vo.attachFilesJson}'),
							"uploaderId"		: "atchuploader",
							"fileListId"		: "atchfiles",
							"progressId"		: "atchprogress",
							"maxcount"			: 3,
							"uploadSetting"	: {
								'formData'		: { 'repository': 'QNA_QSTN',
													'organization' : "${USER_ORGCD}",
													'type'		: 'file' }
							}
						});
		
		 summernote = new $M.SummerNote({
			"editorId"			:	"summernote",
			"textareaId"		:	"contentTextArea",
			"repositoryCode"	:	"QNA_QSTN",
			'organization' 		: 	"${USER_ORGCD}",
			"editorHeight"		:	"400px",
			"attachments"		:	$.parseJSON('${vo.attachImagesJson}')
		}); 
		
	});

	function uploderclick(str) {
		$("#"+str).click();
	}


	/* 서브밋 처리 */
	function process(cmd) {
		$('#frm').attr("action","/home/brd/qna/"+cmd);
 		
		if(!validate(document.frm)) return false;
		
	 	var _content = $("#summernote").summernote('code');
		if(_content == "<p><br></p>" || isEmpty(_content)){
			alert("<spring:message code="home.board.inserttitle"/>");
			return false;
		} 
		
		//-- 내용 form에 setting
	 	$("#contentTextArea").val(_content);
		var _paramImages = summernote.getFileSnAll();
		
		var _paramFiles = atchFiles.getFileSnAll();
		
		$(':input:hidden[name=attachImageSns]').val(_paramImages);
		$(':input:hidden[name=attachFileSns]').val(_paramFiles); 
		
		$('#frm').ajaxSubmit(processCallback);
	}
	
	function processCallback(resultDTO) {
		alert(resultDTO.message);
		if(resultDTO.result == 1) {
			document.location.href = "/home/brd/qna/main";
		} else {
			
		}
	}

	/* 글 저장 */
	function writeQstn() {
		process("writeQstn");
	}

	/* 글 수정 */
	function editQstn() {
		process("editQstn");
	}
	
	/** 글 상세 화면으로 이동 */
	function viewQna(){
		var qnaSn = '${vo.qnaSn}';
		var url = generateUrl("/home/brd/qna/viewQnaMain", {"qnaSn" : qnaSn});
		document.location.href = url;
	}
	
	/** 글 목록 화면으로 이동 */
	function listQstn(){
		var url = generateUrl("/home/brd/qna/main");
		document.location.href = url;
	}

	</script>