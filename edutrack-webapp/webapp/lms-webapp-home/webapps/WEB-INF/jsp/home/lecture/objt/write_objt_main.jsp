<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="img_base" value="/img/home" />
		<div class="learn_top">
           <div class="left_title">
               <h3>이의제기</h3>
           </div>
       </div>
	<form id="objtWriteForm" name="objtWriteForm">
		<input type="hidden" name="crsCreCd" value="${sessionScope.CRSCRECD }">
		<input type="hidden" name="attachFileSns" value="${vo.attachFileSns}" />
		<fieldset>
			<div class="segment">
                    <div class="tstyle">
                        <ul class="dbody">
                            <li>
                                <div class="row">
                                    <label for="titleInput" class="form-label col-sm-2">제목</label>
                                    <div class="col-sm-10">
                                        <div class="form-row">
                                            <input class="form-control" type="text" name="title" id="objtTitle" value="" maxlength="100" placeholder="제목을 입력하세요"> 
                                        </div>             
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="row">
                                    <label for="objtCts" class="form-label col-sm-2">내용</label>
                                    <div class="col-sm-10">
                                        <div class="form-row">
                                            <textarea class="form-control" rows="10" name="cts" id="objtCts" ></textarea>
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="row">
                                    <span class="form-label col-sm-2">첨부파일</span>
                                    <div class="col-sm-10 attach_area">
                                        <div class="upload">
											<div class="upload_inbox">
												<a href="javascript:uploderclick('atchuploader');" class="btn gray2">파일 찾기</a>
												<input type="file" name="atchuploader" id="atchuploader" title="<spring:message code="common.title.atachfile"/>" multiple style="display:none" /><%-- 첨부파일 버튼 --%>
											</div>
										<div id="atchfiles" class="attach_list"></div>
									</div>
                                   </div> 
                                </div>
                            </li>    
                        </ul>
                    </div>
                    <div class="btns mt30">
                        <button type="button" class="btn gray2" onclick="addObjt()">저장</button>
                        <button type="button" class="btn type5" onclick="listObjt()">취소</button>
                    </div>
                </div>
		</fieldset>
	</form>

<script type="text/javascript"> 
	var atchFiles; // 첨부파일 목록
	
	$(document).ready(function() {
		atchFiles = new $M.JqueryFileUpload({
			"varName"			: "atchFiles",
			"files" 			: $.parseJSON('${lecObjtVO.attachFilesJson}'),
			"uploaderId"		: "atchuploader",
			"fileListId"		: "atchfiles",
			"progressId"		: "atchprogress",
			//"maxcount"			: 3, 첨부파일 갯수 확인
			"uploadSetting"		: {
				'formData'		: { 'repository': 'LEC_OBJT',
									'type'		: 'file' }
			}
		});
	
	});
	
	function addObjt() {
		if(checkValid()) {
			$(':input:hidden[name=attachFileSns]').val(atchFiles.getFileSnAll());
			$('#uploadify').attr("disabled", true); //-- 파일 객체 disable
			$("#objtWriteForm").attr("action", "/lec/objt/writeObjt");
			$("#objtWriteForm").ajaxSubmit(
				function(data) {
					if(data.result == 1){
	 					alert(data.message);
	 					parent.listObjt(1);
	 					parent.modalBoxClose();
					}
					else {
						alert(data.message);
					}
				}
			);
		}
		else {
			alert("이의제기의 제목과 내용을 입력해주세요.")
		}
	}
	
	function checkValid() {
		if(isNull($("#objtTitle").val())) return false;
		if(isNull($("#objtCts").val())) return false;
		return true;
	}
	
	function uploderclick(str) {
		$("#"+str).click();
	}
	
	/** 목록 화면으로 이동 */
	function listObjt(){
		var url = "/lec/objt/objtMain";
		document.location.href = url;
	}

</script>
