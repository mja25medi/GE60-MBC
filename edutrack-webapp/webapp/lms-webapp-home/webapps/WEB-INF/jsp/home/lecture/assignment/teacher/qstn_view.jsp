<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="gubun" value="${gubun}"/>
<c:set var="assignmentSubVO" value="${vo}" />
<c:url var="img_base" value="/img/uniedu/home" />
	<form id="assignmentForm" name="assignmentForm" onsubmit="return false" action ="/lec/assignment" >
	<input type="hidden" name="crsCreCd" value="${vo.crsCreCd}" />
	<input type="hidden" name="asmtSn" value="${vo.asmtSn}" />
	<input type="hidden" name="asmtSubSn" value="${vo.asmtSubSn}" />
	<input type="hidden" name="attachFileSns" value="${vo.attachFileSns}" />
	<input type="hidden" name="attachImageSns" value="${vo.attachImageSns}" />
	<input type="hidden" name="editorYn" value="Y"/>
	
	   <div class="tstyle">
            <ul class="dbody">
                <li>
                    <div class="row">
                        <label for="titleInput" class="form-label col-sm-2">문제제목</label>
                        <div class="col-sm-10">
                            <div class="form-row">
                                ${assignmentSubVO.asmtTitle}
                            </div>             
                        </div>
                    </div>
                </li>
                <li>
                    <div class="row">
                        <label for="numInput" class="form-label col-sm-2">첨부파일</label>
                        <div class="col-sm-10 attach_area">
                           <c:if test="${not empty assignmentSubVO.attachFiles}">
						<c:forEach var="fileItem" items="${assignmentSubVO.attachFiles}" varStatus="status">
							<div style="margin-left: 5px; float: left">${fileItem.downloadTag}</div>
						</c:forEach>
					</c:if>                                       
                        </div>
                    </div>
                </li>
                <li>
                    <div class="row">
                      <label for="titleInput" class="form-label col-sm-2">문제내용</label>
                        <div class="col-sm-10">
                            <div class="form-row">
                                ${assignmentSubVO.asmtCts}
                            </div>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
	</form>
	 <div class="btns mt30">
         <button type="button" class="btn type5" onclick="javascript:parent.modalBoxClose()"><spring:message code="button.close"/></button>
     </div>

<script type="text/javascript">
	var atchFiles; // 첨부파일 목록
	$(document).ready(function() {
		$("body").css("padding-top","0px").css("min-height","0px");
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
</script>
