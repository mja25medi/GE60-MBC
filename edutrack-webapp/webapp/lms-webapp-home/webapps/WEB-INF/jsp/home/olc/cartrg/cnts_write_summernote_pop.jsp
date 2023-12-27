<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="olcCntsVO" value="${vo}"/>
<c:set var="gubun" value="${gubun}"/>
<mhtml:home_html>
<mhtml:home_head titleName="${MENUNAME}">
	<mhtml:head_module summernote="y" fileupload="y"/>
</mhtml:home_head>
<body style="padding-top: 0px;">
<form id="olcCartrgForm" name="olcCartrgForm" onsubmit="return false">
<input type="hidden" name="cartrgCd" value="${vo.cartrgCd}" />
<input type="hidden" name="cntsCd" value="${vo.cntsCd}" />
<input type="hidden" name="cntsDiv" value="${vo.cntsDiv}" />
<input type="hidden" name="cntsLoc" value="${vo.cntsLoc}" />
<input type="hidden" name="cntsOdr" value="${vo.cntsOdr}" />
<input type="hidden" name="attachImageSns" value="${vo.attachImageSns}" />
<input type="hidden" name="attachFileSns" value="${vo.attachFileSns}" />
<table class="table table-striped table-bordered" style="width:100%;">
	<caption class="sr-only"><spring:message code="board.title.bbs.atcl.cnts"/></caption>
	<colgroup>
		<col style="width: 15%"/>
		<col style="width: 85$"/>
	</colgroup>
	<tr>
		<th scope="row"><label for="cntsNm"><spring:message code="common.title.title"/></label></th>
		<td>
			<input type="text" name="cntsNm" value="${vo.cntsNm}" dispName="<spring:message code="common.title.title"/>" isNull="N" lenCheck="50" maxlength="50" class="form-control input-sm" id="cntsNm" />
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

	<tr>
		<td colspan="2" style="padding:0px;">
			<div id="summernote" style="width:820px; margin: 0 auto;">${vo.cntsCts}</div>
			<textarea name="cntsCts" id="contentTextArea" title="olc.title.contents..cnts" class="sr-only">${vo.cntsCts}</textarea>
		</td>
	</tr>
</table>
<div class="text-right">
	<c:if test="${gubun eq 'A'}">
	<a href="javascript:addCnts()" class="btn btn-primary btn-sm"><spring:message code="button.add"/></a>
	</c:if>
	<c:if test="${gubun eq 'E'}">
	<a href="javascript:editCnts()" class="btn btn-primary btn-sm"><spring:message code="button.add"/></a>
	<a href="javascript:delCnts()" class="btn btn-warning btn-sm"><spring:message code="button.delete"/></a>
	</c:if>
	<a href="javascript:closeWrite()" class="btn btn-default btn-sm"><spring:message code="button.cancel"/></a>
</div>
<input type="submit" value="submit" style="display:none" />
</form>

<script type="text/javascript">
	var summernote;
	$(document).ready(function() {
		summernote = new $M.SummerNote({
			"editorId"			:	"summernote",
			"textareaId"		:	"contentTextArea",
			"repositoryCode"	:	"OLCCNTS",
			'organization' 		: 	"${USER_ORGCD}",
			"editorHeight"		:	"300px",
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
								'formData'		: { 'repository': 'OLCCNTS',
													'organization' : "${USER_ORGCD}",
													'type'		: 'file' }
							}
						});
	});

	function uploderclick(str) {
		$("#"+str).click();
	}

    /**
	 * 서브밋 처리
	 */
	function process(cmd) { //cmd : 해당메소드
		$('#olcCartrgForm').attr("action","/home/olc/cartrg/" + cmd);
    	if(!validate(document.olcCartrgForm)) return false;
		var _content = $("#summernote").summernote('code');
		if(_content == "<p><br></p>" || isEmpty(_content)){
			alert('<spring:message code="board.message.bbs.atcl.alert.input.cnts"/>');
			return false;
		}
		//-- 내용 form에 setting
		$("#contentTextArea").val(_content);

		// 에디터 첨부파일 번호 목록 추출
		var _paramImages = summernote.getFileSnAll();
		// atchFiles에서 첨부파일 정보를 가져온다.
		var _paramFiles = atchFiles.getFileSnAll();
		$(':input:hidden[name=attachImageSns]').val(_paramImages);
		$(':input:hidden[name=attachFileSns]').val(_paramFiles);

		$('#olcCartrgForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		if(!isNull(resultDTO.message))
			alert(resultDTO.message);
		if(resultDTO.result >= 0) {
			// 정상 처리
			parent.listCnts();
			parent.closeWrite();
		} else {
			// 비정상 처리
		}
	}

	/* 글 저장 */
	function addCnts() {
		process('addCnts');
	}

	/* 글 수정 */
	function editCnts() {
		process('editCnts');
	}

	/* 글 삭제 */
	function delCnts() {
		if(confirm('<spring:message code="olc.message.contents.confirm.delete"/>')) {
			$('#olcCartrgForm').attr("action","/home/olc/cartrg/" + deleteCnts);
			$('#olcCartrgForm').ajaxSubmit(processCallback);
		} else {
			return;
		}
	}

	function closeWrite() {
		parent.closeWrite();
	}
</script>
</body>
</mhtml:home_html>