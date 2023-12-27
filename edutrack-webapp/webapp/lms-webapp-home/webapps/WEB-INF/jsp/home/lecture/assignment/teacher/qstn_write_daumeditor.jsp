<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="gubun" value="${gubun}"/>
<c:set var="assignmentSubVO" value="${vo}" />
<c:url var="img_base" value="/img/uniedu/home" />
<mhtml:class_html>
<mhtml:class_head>
	<mhtml:head_module daumeditor="y" fileupload="y"/>
</mhtml:class_head>

<mhtml:body>
	<form id="assignmentForm" name="assignmentForm" onsubmit="return false" >
	<input type="hidden" name="crsCreCd" value="${vo.crsCreCd}" />
	<input type="hidden" name="asmtSn" value="${vo.asmtSn}" />
	<input type="hidden" name="asmtSubSn" value="${vo.asmtSubSn}" />
	<input type="hidden" name="attachFileSns" value="${vo.attachFileSns}" />
	<input type="hidden" name="attachImageSns" value="${vo.attachImageSns}" />
	<input type="hidden" name="editorYn" value="Y"/>
	<table class="table table-bordered wordbreak">
		<caption class="sr-only"><spring:message code="lecture.title.assignment.question.manage"/></caption>
		<colgroup>
			<col style="width:20%"/>
			<col style="width:80%"/>
		</colgroup>
		<tbody>
			<tr >
				<th scope="row"><spring:message code="lecture.title.assignment.question.title"/></th>
				<td>
					<input type="text" dispName="<spring:message code="lecture.title.assignment.question.title"/>" maxlength="100" isNull="N" lenCheck="100" name="asmtTitle" value="${vo.asmtTitle}" class="form-control input-sm" onfocus="this.select()"/>
				</td>
			</tr>
			<tr >
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
			<tr >
				<td colspan="2" style="padding:0px;">
					<div id="daumeditor" style="float:left; width:100%; margin: 0 auto;"></div>
					<textarea name="asmtCts" id="contentTextArea"  class="sr-only">${vo.asmtCts}</textarea>
				</td>
			</tr>
		</tbody>
	</table>
	</form>
	<div class="text-right">
		<c:if test="${gubun eq 'A'}">
		<a href="javascript:addQuestion()" class="btn btn-primary btn-sm"><spring:message code="button.add"/></a>
		</c:if>
		<c:if test="${gubun eq 'E'}">
		<a href="javascript:addQuestion()" class="btn btn-primary btn-sm"><spring:message code="button.add"/></a>
		<a href="javascript:delQuestion()" class="btn btn-warning btn-sm"><spring:message code="button.delete"/></a>
		</c:if>
		<a href="javascript:parent.modalBoxClose()" class="btn btn-default btn-sm"><spring:message code="button.close"/></a>
	</div>

<script type="text/javascript">

	var atchFiles; // 첨부파일 목록

	$(document).ready(function() {
		$("body").css("padding-top","0px").css("min-height","0px");

		$M.CreateEditor({		// @see /js/daumeditor.js 참고
			"editorId"			:	"daumeditor",
			"formId"			:	"assignmentForm",
			"textareaId"		:	"contentTextArea",
			"repositoryCode"	:	"ASMT_SEND",
			"attachments"		:	$.parseJSON('${assignmentSubVO.attachImagesJson}'),
			"editorHeight"		:	"300px"
		});

		// 파일 첨부 업로더 및 바인딩 및 초기화 @see /js/common_function.js 참조.
		atchFiles = new $M.JqueryFileUpload({
						"varName"			: "atchFiles",
						"files" 			: $.parseJSON('${assignmentSubVO.attachFilesJson}'),
						"uploaderId"		: "atchuploader",
						"fileListId"		: "atchfiles",
						"progressId"		: "atchprogress",
						"maxcount"			: 3,
						"uploadSetting"		: {
							'formData'		: { 'repository': 'ASMT_SUB',
												'organization' : "${USER_ORGCD}",
												'type'		: 'file' }
						}
					});
	});

	function uploderclick(str) {
		$("#"+str).click();
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

	/**
	 * 과제 문제 등록
	 */
	function addQuestion() {
		var f = document.assignmentForm;
		if(isEmpty(f["asmtTitle"].value)) {
			alert('<spring:message code="lecture.message.assignment.alert.input.name"/>');
			return;
		}
		$(':input:hidden[name=attachFileSns]').val(atchFiles.getFileSnAll());
		$('#uploadify').attr("disabled", true);
		if(document.assignmentForm["asmtSubSn"].value != "0") {
			$('#assignmentForm').attr("action","/lec/assignment/editSub");
		} else {
			$('#assignmentForm').attr("action","/lec/assignment/addSub");
		}
		Editor.save();
	}

	/**
	 * 과제 문제 삭제
	 */
	function delQuestion() {
		if(!confirm('<spring:message code="lecture.message.assignment.question.confirm.delete"/>')) {
			return;
		}
		$('#assignmentForm').attr("action","/lec/assignment/deleteSub");
		$('#uploadify').attr("disabled", true); //-- 파일 객체 disable
		$('#assignmentForm').ajaxSubmit(processCallback);
	}

	function goList() {
		document.location.href = cUrl("/lec/assignment/tchAssignmentMain")+"?crsCreCd=${assignmentVO.crsCreCd}";
	}

	/**
	 * Editor.save()를 호출시 데이터 유효여부 검사 콜백함수. 유효할 경우 true 리턴.
	 * @function
	 * @param {Object} editor - 에디터에서 넘겨주는 editor 객체
	 * @returns {Boolean} 모든 데이터가 유효할 경우에 true
	 */
	function validForm(editor) {
		 if(!validate(document.assignmentForm)) return;

		/* 본문 내용이 입력되었는지 검사하는 부분 */
		var _validator = new Trex.Validator();
		var _content = editor.getContent();
		if(!_validator.exists(_content)) {
			alert('<spring:message code="board.message.bbs.atcl.alert.input.cnts"/>');
			return false;
		}
		return true;
	}

	/**
	 * Editor.save() -> validForm(editor)를 통해 데이터가 유효하면 Form Summit을 위해 필드를
	 * 생성, 변경하기 위해 부르는 콜백함수로 컨텐츠 값과 첨부파일을 설정.
	 * 정상적인 경우에 true를 리턴하면 submit();
	 * @function
	 * @param {Object} editor - 에디터에서 넘겨주는 editor 객체
	 * @returns {Boolean} 정상적인 경우에 true
	 */
	function setForm(editor) {
		// 본문 전송을 위해 컨텐츠를 textarea로..
		$("#contentTextArea").val(editor.getContent());

		// 에디터 첨부파일 번호 목록 추출
		var _paramImages = Editor.getAttachmentIds(editor, 'image', true);

		$(':input:hidden[name=attachImageSns]').val(_paramImages);

		$('#assignmentForm').ajaxSubmit(processCallback);
		return false;
	}
</script>
</mhtml:body>
</mhtml:class_html>