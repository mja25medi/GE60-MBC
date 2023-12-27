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
			<th scope="row"><spring:message code="common.title.cnts"/></th>
			<td colspan="3">
				<textarea style="height:100%;" rows="13" isNull="N" dispName="<spring:message code="common.title.cnts"/>" name="cts" class="form-control input-sm">${vo.cts }</textarea>
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

	// 페이지 초기화
	$(document).ready(function() {
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
			process("removeAtcl");
		}
	}

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		//if(!validate(document.forumForm)) return;

		$(':input:hidden[name=attachFilesSn]').val(atchFiles.getFileSnAll());
		$('#uploadify').attr("disabled", true); //-- 파일 객체 disable

		$('#forumForm').attr("action","/mng/lecture/forum/" + cmd);
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
