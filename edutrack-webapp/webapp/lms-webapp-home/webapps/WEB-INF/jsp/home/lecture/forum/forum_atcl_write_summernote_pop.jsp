<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="img_base" value="/img/home" />
<c:set var="gubun" value="${gubun}"/>
<c:set var="forumAtclVO" value="${vo}"/>

	<form id="forumForm" name="forumForm" onsubmit="return false" action="/lec/forum">
	<input type="hidden" name="forumSn" value="${vo.forumSn}" />
	<input type="hidden" name="crsCreCd" value="${vo.crsCreCd}" />
	<input type="hidden" name="curPage" value="${vo.curPage}" />
	<input type="hidden" name="atclSn" value="${vo.atclSn}" />
	<input type="hidden" name="parAtclSn" value="${vo.parAtclSn}" />  <!--상위글 유무 파악 -->
	<input type="hidden" name="attachImageSns" value="${vo.attachImageSns}" />
	<input type="hidden" name="attachFilesSn" value="${vo.attachFilesSn}" />
	<input type="hidden" name="editorYn" value="Y" />
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
				<td colspan="2" style="padding:0px;">
					<div id="summernote" style="float:left; width:100%; margin: 0 auto;">${forumAtclVO.cts}</div>
					<textarea  name="cts" id="contentTextArea" style="display:none" dispName="<spring:message code="lecture.title.forum.atcl.cnts"/>">${vo.cts}</textarea>
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
	var summernote;

	// 페이지 초기화
	$(document).ready(function() {
		//팝업창 스타일조정
		$("body").css("padding-top","0px").css("min-height","0px");
		//부모창 새로고침
		if("${refreshYn}" == "D"){		//삭제일 경우
			parent.location.reload();
			parent.modalBoxClose();
		}

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
		process("addForumArticle");
	}

	/**
	* 게시글 수정
	*/
	function editAtcl(){
		process("editForumArticle");
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
			$('#forumForm').attr("action","/lec/forum/removeForumArticle");
			document.forumForm.submit();
		}
	}

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		$('#forumForm').attr("action","/lec/forum/"+cmd);
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
			parent.location.reload();
		} else {
			// 비정상 처리
		}
	}
</script>
