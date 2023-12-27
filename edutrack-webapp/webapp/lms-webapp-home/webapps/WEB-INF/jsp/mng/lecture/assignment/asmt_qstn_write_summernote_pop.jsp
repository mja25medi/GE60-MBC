<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="gubun" value="${gubun}"/>
<c:set var="assignmentSubVO" value="${vo}" />
	<form id="assignmentForm" name="assignmentForm" onsubmit="return false">
	<input type="hidden" name="crsCreCd" value="${vo.crsCreCd }" />
	<input type="hidden" name="asmtSn" value="${vo.asmtSn }" />
	<input type="hidden" name="asmtSubSn" value="${vo.asmtSubSn }" />
	<input type="hidden" name="attachFileSns" value="${vo.attachFileSns }" />
	<input type="hidden" name="attachImageSns" value="${vo.attachImageSns }" />
	<input type="hidden" name="editorYn" value="Y"/>
	<table summary="<spring:message code="lecture.title.assignment.question.manage"/>" class="table table-bordered wordbreak">
		<colgroup>
			<col style="width:20%"/>
			<col style="width:80%"/>
		</colgroup>
		<tbody>
			<tr>
				<th scope="row"><spring:message code="lecture.title.assignment.question.title"/></th>
				<td>
					<input type="text" dispName="<spring:message code="lecture.title.assignment.question.title"/>" maxlength="100" isNull="N" lenCheck="100" name="asmtTitle" value="${vo.asmtTitle }" onfocus="this.select()" class="form-control input-sm"/>
				</td>
			</tr>
			<c:if test="${aVO.asmtSvcCd eq 'CODE'}">
			<tr>
			<th scope="row">난이도</th>
			<td>
				<c:if test="${gubun eq 'A'}">
				<select name="diffLvlCd" id="diffLvlCd" class="form-control input-sm">
					<c:forEach var="item" items="${asmtDiffLvlCdList}" varStatus="status">
					<option value="${item.codeCd}" <c:if test="${vo.diffLvlCd eq item.codeCd}">checked</c:if> >${item.codeNm}</option>
					</c:forEach>				
				</select>
				</c:if>
				<c:if test="${gubun eq 'E'}">
				<input type="hidden" name="diffLvlCd" id="diffLvlCd" value="${vo.diffLvlCd}"/>
				<meditag:codename code="${assignmentSubVO.diffLvlCd}" category="DIFF_LVL_CD"/>
				</c:if>
			</td>
		</tr>
		<tr>
			<th scope="row">테스트언어</th>
			<td>
				<c:if test="${gubun eq 'A'}">
				<select name="devLangCd" id="devLangCd" class="form-control input-sm">
					<c:forEach var="item" items="${asmtDevLangCdList}" varStatus="status">
					<option value="${item.codeCd}" <c:if test="${vo.devLangCd eq item.codeCd}">checked</c:if> >${item.codeNm}</option>
					</c:forEach>				
				</select>
				</c:if>
				<c:if test="${gubun eq 'E'}">
				<input type="hidden" name="devLangCd" id="devLangCd" value="${vo.devLangCd}"/>
				<meditag:codename code="${assignmentSubVO.devLangCd}" category="DEV_LANG_CD"/>
				</c:if>
			</td>
		</tr>
		</c:if>
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
					<div id="summernote" style="float:left; width:100%; margin: 0 auto;">${assignmentSubVO.asmtCts}</div>
					<textarea name="asmtCts" id="contentTextArea"  class="sr-only">${vo.asmtCts }</textarea>
				</td>
			</tr>
			<c:if test="${aVO.asmtSvcCd eq 'CODE'}">
				<c:if test="${gubun eq 'A'}">
					<tr>
						<th scope="row">제한조건 <button class="btnAdd">+</button></th>
						<td>
							<div class="input-group">
								 <div class='addInput'></div>	
							</div>
						</td>
					</tr>
				</c:if>
				<c:if test="${gubun eq 'E'}">
				<tr>
					<th scope="row">제한조건</th>
						<td>
							<div class="input-group">
								 <div class='addInput'>
								   	<c:forEach var="item" items="${constList}">
			                            <div class="inputArea" style="display: flex; margin-top: 5px;">
			                           		 <input type="text" style="width:380px;"name="constCts" id="constCts" class="form-control input-sm" value="${item.constCts}" disabled="disabled"/>
			                            </div>
			                        </c:forEach>
								 </div>	
							</div>
						</td>
					</tr>
				</c:if>
			</c:if>
		</tbody>
	</table>
	<div class="text-right">
		<c:if test="${gubun eq 'A'}">
		<a href="javascript:addQuestion()" class="btn btn-primary btn-sm"><spring:message code="button.add"/></a>
		</c:if>
		<c:if test="${gubun eq 'E'}">
		<a href="javascript:editQuestion()" class="btn btn-primary btn-sm"><spring:message code="button.add"/></a>
		<a href="javascript:delQuestion()" class="btn btn-warning btn-sm"><spring:message code="button.delete"/></a>
		</c:if>
		<a href="javascript:parent.modalBoxClose()" class="btn btn-default btn-sm"><spring:message code="button.close"/></a>
	</div>
	</form>

<script type="text/javascript">

	var atchFiles; // 첨부파일 목록
	var summernote;

	$(document).ready(function() {
		summernote = new $M.SummerNote({
			"editorId"			:	"summernote",
			"textareaId"		:	"contentTextArea",
			"repositoryCode"	:	"ASMT_SUB",
			'organization' 		: 	"${USER_ORGCD}",
			"editorHeight"		:	"300px",
			"attachments"		:	$.parseJSON('${assignmentSubVO.attachImagesJson}')
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

	function process(cmd) {
		$('#assignmentForm').attr("action","/mng/lecture/assignment/" + cmd);
		
		if(!validate(document.assignmentForm)) return;
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

		$('#assignmentForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		alert(resultDTO.message);
		if(resultDTO.result >= 0) {
			// 정상 처리
			parent.subWorkFrame.listQstn();
			parent.modalBoxClose();
		} else {
			// 비정상 처리
		}
	}

	<c:if test="${gubun eq 'A'}">
	/**
	 * 과제 문제 등록
	 */
	function addQuestion() {
		process("addQstn");
	}
	</c:if>

	<c:if test="${gubun eq 'E'}">
	function editQuestion() {
		process("editQstn");
	}

	/**
	 * 과제 문제 삭제
	 */
	function delQuestion() {
		if(!confirm('<spring:message code="lecture.message.assignment.question.confirm.delete"/>')) {
			return;
		}
		$('#assignmentForm').attr("action","/mng/lecture/assignment/removeQstn");
		$('#assignmentForm').ajaxSubmit(processCallback);
	}
	</c:if>
	
	$('.btnAdd').click (function () {                                        
        $('.addInput').append ( 
        	'<div class="inputArea"  style="display: flex;margin-top: 5px;"><input type="text" style="width:350px;"name="constCtsList" id="constCtsList" class="form-control input-sm"/>\
            <button type="button" class="btnRemove">삭제</button><br></div>'                    
        );    // input taf 추가                     
        $('.btnRemove').on('click', function () { 
            $(this).prev().remove();        
            $(this).next().remove();        
            $(this).remove();               
        });
    });   

</script>
