<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="gubun" value="${gubun}"/>
<c:set var="assignmentSubVO" value="${assignmentSubVO}" />
<c:url var="img_base" value="/img/uniedu/home" />
	<form id="assignmentForm" name="assignmentForm" onsubmit="return false" >
	<input type="hidden" name="crsCreCd" value="${vo.crsCreCd}" />
	<input type="hidden" name="asmtSn" value="${vo.asmtSn}" />
	<input type="hidden" name="asmtSubSn" value="${vo.asmtSubSn}" />
	<input type="hidden" name="attachFileSns" value="${vo.attachFileSns}" />
	<input type="hidden" name="attachImageSns" value="${vo.attachImageSns}" />
                <div class="modal_cont">
                    <div class="tstyle">
                        <ul class="dbody">
                            <li>
                                <div class="row">
                                    <label for="asmtTitle" class="form-label col-sm-2"><spring:message code="lecture.title.assignment.question.title"/><i class="icon_star" aria-hidden="true"></i></label>
                                    <div class="col-sm-10">
                                        <div class="form-row">
                                            <input type="text" dispName="<spring:message code="lecture.title.assignment.question.title"/>" maxlength="100" isNull="N" lenCheck="100" name="asmtTitle" value="${vo.asmtTitle}" class="form-control" onfocus="this.select()"/>
                                        </div>             
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="row">
                                    <label for="numInput" class="form-label col-sm-2"><spring:message code="common.title.atachfile"/></label>
                                    <div class="col-sm-10 attach_area">
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
<!--                                     
                                        <input type="file" class="input_attach sr-only" id="attchFile" multiple=""><label for="attchFile" class="btn gray2">파일 찾기</label> 
                                        <div class="attach_list">
                                            <div class="attach_name">첨부파일첨부파일첨부파일.png<button type="button" class="attach_close"><i class="xi-close" aria-hidden="true"></i><span class="sr-only">파일삭제</span></button></div>
                                            <div class="attach_name">동물관련종사자_입력폼.jpg<button type="button" class="attach_close"><i class="xi-close" aria-hidden="true"></i><span class="sr-only">파일삭제</span></button></div>
                                            <div class="attach_name">KakaoTalk_20210729_131327900.png<button type="button" class="attach_close"><i class="xi-close" aria-hidden="true"></i><span class="sr-only">파일삭제</span></button></div>
                                        </div>  
 -->                                        
                                                                                  
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class="form-row">
                                            <textarea name="asmtCts" style="height:250px;" title="common.title.cnts" class="form-control">${vo.asmtCts}</textarea>
                                        </div>
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
	</form>
				<div class="modal_btns">
					<c:if test="${gubun eq 'A'}">
					<button type="button" class="btn type4" onclick="addQuestion()"><spring:message code="button.add"/></button>
					</c:if>
					<c:if test="${gubun eq 'E'}">
					<button type="button" class="btn type4" onclick="addQuestion()"><spring:message code="button.add"/></button>
                    <button type="button" class="btn type9" onclick="delQuestion()"><spring:message code="button.delete"/></button>
					</c:if>
					<button type="button" class="btn type2" onclick="parent.modalBoxClose()"><spring:message code="button.close"/></button>
				</div>


<script type="text/javascript">
	var atchFiles; // 첨부파일 목록
	$(document).ready(function() {

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
			return false;
		}
		if(isEmpty(f["asmtCts"].value)) {
			alert('<spring:message code="lecture.message.assignment.alert.input.cnts"/>');
			return false;
		}
		$(':input:hidden[name=attachFileSns]').val(atchFiles.getFileSnAll());
		$('#uploadify').attr("disabled", true);
		if(document.assignmentForm["asmtSubSn"].value != "0") {
			$('#assignmentForm').attr("action","/lec/assignment/editSub");
			$('#assignmentForm').ajaxSubmit(processCallback);
		} else {
			$('#assignmentForm').attr("action","/lec/assignment/addSub");
			$('#assignmentForm').ajaxSubmit(processCallback);
		}
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

</script>
