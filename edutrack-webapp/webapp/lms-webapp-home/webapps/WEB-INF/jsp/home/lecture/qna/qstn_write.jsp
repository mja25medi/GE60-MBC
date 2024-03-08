<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="img_base" value="/img/uniedu/home" />
<c:set var="gubun" value="${gubun}"/>
<div class="learn_top">
           <div class="left_title">
               <h3>학습문의</h3>
           </div>
       </div>
				<form id="lecQnaForm" name="lecQnaForm" onsubmit="return false" method="post">
				<input type="hidden" name="qnaSn" id="qnaSn" value="${vo.qnaSn}" />
			    <input type="hidden" name="crsCreCd" value="${vo.crsCreCd}" />
			    <input type="hidden" name="unitCd" id="unitCd" value="${vo.unitCd }" />
				<input type="hidden" name="attachFileSns" value="${vo.attachFileSns}" />
				<input type="hidden" name="attachImageSns" value="${vo.attachImageSns}" />
				<input type="hidden" name="qnaCtgrCd" value="LEC" />
				<input type="hidden" name="editorYn" value="Y" />
				<input type="hidden" name="lecYn" value="Y" />
				<div class="segment">
                    <div class="tstyle">
                        <ul class="dbody" style="word-break : break-all">
                            <li>
                                <div class="row">
                                    <label for="titleInput" class="form-label col-sm-2">제목</label>
                                    <div class="col-sm-10">
                                        <div class="form-row">
                                            <input class="form-control" type="text" name="qnaTitle" id="qnaTitle"  value="${vo.qnaTitle}" maxlength="100" placeholder="제목을 입력하세요">
                                        </div>             
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="row">
                                    <label for="objtCts" class="form-label col-sm-2">내용</label>
                                    <div class="col-sm-10">
                                        <div class="editor">
                                            <div id="summernote">${vo.qnaCts}</div>
											<textarea name="qnaCts" id="contentTextArea" style="display:none" title="common.title.cnts">${vo.qnaCts}</textarea>
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="row">
                                    <label for="objtCts" class="form-label col-sm-2">강의 회차</label>
                                    <div class="col-sm-10">
                              			  <select class="form-control input" id="parUnitCd" name="parUnitCd" onchange="changeUnitCd()">
                                      		 <option value="">선택</option>
											<c:forEach var="item" items="${parCntsCdList}" varStatus="status">
												<option value="${item.unitCd}" <c:if test="${item.unitCd eq vo.unitCd}">selected</c:if>>${item.unitNm}</option>
											</c:forEach>
											</select>
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
							<c:if test="${gubun ne 'E'}">
								 <button type="button"onclick="addQstn()" class="btn gray2"><spring:message code="button.add"/></button>
							</c:if>
							<c:if test="${gubun eq 'E'}">
								 <button type="button" onclick="editQstn()" class="btn gray2"><spring:message code="button.add"/></button>
								 <button type="button"onclick="removeQstn()" class="btn type9"><spring:message code="button.delete"/></button>
							</c:if>
						  	   <button type="button" class="btn type5" onclick="listQstn()">취소</button>
						</div>
					
                </div>
				</form>

<script type="text/javascript">
	var atchFiles; // 첨부파일 목록
	var summernote;
	var submitChk = false;

	$(document).ready(function() {
		summernote = new $M.SummerNote({
			"editorId"			:	"summernote",
			"textareaId"		:	"contentTextArea",
			"repositoryCode"	:	"QNA_QSTN",
			'organization' 		: 	"${USER_ORGCD}",
			"editorHeight"		:	"400px",
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
		
		if($('#parUnitCd').val() == "") {
			alert("강의 회차를 선택해주세요.");
			return;
		}
		
		$('#lecQnaForm').attr("action","/lec/qna/"+cmd);
		if(!validate(document.lecQnaForm)) return false;
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
		$('#lecQnaForm').ajaxSubmit(
			function(data) {
				if(data.result > 0 ) {
					alert(data.message);
					document.location.href = "/lec/qna/main";
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

	/* 글 수정 */
	function editQstn(){
		process("editQstn");
	}

	/* 글 삭제 */
	function removeQstn() {
		var qnaSn = $("#qnaSn").val();
		
		if(submitChk) {
			alert("처리 중 입니다.");
			return;
		}
		
		if(confirm('문의 내용을 정말 삭제 하시겠습니까?')) {
			$('#lecQnaForm').attr("action","/lec/qna/removeQstn");
			$('#lecQnaForm').ajaxSubmit(
				function (data) {
					if(data.result > 0) {
						alert(data.message);
						document.location.href = "/lec/qna/main";
					} else {
						alert(data.message);
						submitChk = false;
					}
				}
			);
			submitChk = true;
		} else {
			return;
		}
	}
	
	function changeUnitCd() {
		var parUnitCd = $("#parUnitCd").val();
		$("#unitCd").val(parUnitCd);
	}
	
	/** 목록 화면으로 이동 */
	function listQstn(){
		var url = "/lec/qna/main";
		document.location.href = url;
	}

</script>
