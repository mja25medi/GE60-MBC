<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
	<script type="text/javascript">
		$(document).ready(function(){
			ItemVO.orgCd = "${USER_ORGCD}";
			ItemVO.bbsCd = "${bbsInfoVO.bbsCd}";
			ItemVO.atchUseYn = "${bbsInfoVO.atchUseYn}";
			ItemVO.bbsTypeCd = "${bbsInfoVO.bbsTypeCd}";
			ItemVO.attachImagesJson = '${vo.attachImagesJson}';
			ItemVO.attachFilesJson = '${vo.attachFilesJson}';
			ItemVO.attachPhotosJson = '${vo.attachPhotosJson}';
			ItemVO.thumbFileJson = '${vo.thumbFileJson}';
			MessageVO.alertCntsNull = "<spring:message code="board.message.bbs.atcl.alert.input.cnts"/>";
			MessageVO.alertTagOver = "<spring:message code="board.message.bbs.atcl.msg.tag"/>";
			MessageVO.confirmDelete = "<spring:message code="board.message.bbs.atcl.confirm.delete"/>";
		});
	</script>
				<section class="content">
					<div class="row" id="content">
						<div class="box">
							<div class="box-body">
								<div class="col-lg-12">
									<form name="bbsAtclForm" id="bbsAtclForm" method="POST">
									<input type="hidden" name="pageIndex" id="pageIndex" value="${vo.pageIndex}" />
									<input type="hidden" name="bbsCd" id="bbsCd" value="${vo.bbsCd}" />
									<input type="hidden" name="atclSn" id="atclSn" value="${vo.atclSn}" />
									<input type="hidden" name="parAtclSn" id="parAtclSn" value="${vo.parAtclSn}" />
									<input type="hidden" name="atclOdr" id="atclOdr" value="${vo.atclOdr}" />
									<input type="hidden" name="atclLvl" id="atclLvl" value="${vo.atclLvl}" />
									<input type="hidden" name="attachImageSns" id="attachImageSns" value="${vo.attachImageSns}" />
									<input type="hidden" name="attachFileSns" id="attachFileSns" value="${vo.attachFileSns}" />
									<input type="hidden" name="attachPhotoSns" id="attachPhotoSns" value="${vo.attachPhotoSns}" />
									<input type="hidden" name="thumbFileSn" id="thumbFileSn" value="${vo.thumbFileSn}" />
									<input type="hidden" name="editorYn" id="editorYn" value="Y"/>
									<input type="hidden" name="password" id="editPassword"/>
									<table summary="<spring:message code="board.title.bbs.atcl.cnts"/>" class="table table-bordered wordbreak">
										<colgroup>
											<col style="width:15%" />
											<col style="width:75%" />
										</colgroup>
										<c:if test="${not empty bbsHeadList}">
										<tr>
											<th scope="row"><label for="headCd"><spring:message code="board.title.bbs.head.select"/></label></th>
											<td>
												<select name="headCd" id="headCd" title="<spring:message code="board.title.bbs.head.select"/>" class="_enterBind form-control input-sm" >
													<option value=""><spring:message code="board.title.bbs.head.select"/></option>
													<c:forEach var="item" items="${bbsHeadList}">
													<option value="${item.headCd}">${item.headNm}</option>
													</c:forEach>
												</select> 
											</td>
										</tr>
										</c:if>
										<tr>
											<th scope="row"><label for="atclTitle"><spring:message code="common.title.title"/></label></th>
											<td>
												<input type="text" name="atclTitle" id="atclTitle" maxlength="100" required="required" title="<spring:message code="common.title.title"/>" class="form-control input-sm" value="<c:out value="${vo.atclTitle}"/>"/>
											</td>
										</tr>
										<c:if test="${not empty categoryList}">
										<tr>
											<th scope="row"><label for="ctgrCd">카테고리</label></th>
											<td>
												<select name="ctgrCd" id="ctgrCd" title="카테고리" class="_enterBind form-control input-sm" >
													<c:forEach var="item" items="${categoryList}">
														<option <c:if test="${vo.ctgrCd eq item.codeCd }">selected="selected"</c:if> value="${item.codeCd}">${item.codeNm}</option>
													</c:forEach>
												</select> 
											</td>
										</tr>
										</c:if>
										<c:if test="${bbsInfoVO.lockUseYn eq 'Y' }">
											<tr>
												<th scope="row"><spring:message code="board.title.qna.private"/></th>
												<td>
													<label>
														<input type="checkbox" name="lockYn" id="lockYn" value="Y" <c:if test="${vo.lockYn eq 'Y'}">checked</c:if> />&nbsp;<spring:message code="board.title.qna.private.info"/>
														<div class="Row" id="pwBox">
															<label for="hashInput" class="form-label col-sm-2">패스워드</label>
															<input type="hidden" id="encryptData" name="encryptData">
															<div class="col-sm-10">
																<div class="form-row">
																	<input type="password" id="password" value="${vo.password}" class="form-control" <c:if test="${vo.lockYn eq 'Y'}"> disabled</c:if>>
																	<c:if test="${vo.lockYn eq 'Y'}">
																		<button type="button" id="editPw"><spring:message code="button.edit.password"/></button>
																	</c:if>
																</div>
															</div>
														</div>
													</label>
												</td>
											</tr>
										</c:if>
										<tr>
											<c:set var="fontFamily" value="Helvetica"/>
											<c:if test="${LOCALEKEY eq 'jp' }"><c:set var="fontFamily" value="Meiryo"/></c:if>
											<c:if test="${LOCALEKEY eq 'ko' }"><c:set var="fontFamily" value="맑은 고딕"/></c:if>
											<td colspan="2" style="padding:0px;font-family:${fontFamily}">
												<div id="summernote" style="float:left; width:100%; margin: 0 auto;">${vo.atclCts}</div>
												<textarea name="atclCts" id="contentTextArea" title="<spring:message code="board.title.bbs.atcl.cnts"/>" class="sr-only"></textarea>
											</td>
										</tr>
										<c:if test="${bbsInfoVO.atchUseYn eq 'Y'}">
										<tr>
											<th scope="row"><spring:message code="common.title.atachfile"/></th>
											<td>
												<a href="javascript:uploderclick('atchuploader');" class="btn btn-primary btn-xs"><spring:message code="button.select.file"/></a>
												<input type="file" name="atchuploader" id="atchuploader" title="<spring:message code="common.title.atachfile"/>" multiple style="display:none"/><%-- 첨부파일 버튼 --%>
												<!-- The global progress bar -->
												<div id="atchprogress" class="progress">
										    		<div class="progress-bar progress-bar-success"></div>
												</div>
												<!-- The container for the uploaded files -->
												<div id="atchfiles" class="files multi_inbox"></div>
											</td>
										</tr>
										</c:if>
										<c:if test="${bbsInfoVO.bbsTypeCd eq 'GALLERY'}">
										<tr>
											<th scope="row"><spring:message code="common.title.photofile"/></th>
											<td>
												<a href="javascript:uploderclick('photouploader');" class="btn btn-primary btn-xs"><spring:message code="button.select.file"/></a>
												<input type="file" name="photouploader" id="photouploader" title="<spring:message code="common.title.photofile"/>" multiple style="display:none" accept="image/*"/><%-- 첨부파일 버튼 --%>
												<!-- The global progress bar -->
												<div id="photoprogress" class="progress">
										    		<div class="progress-bar progress-bar-success"></div>
												</div>
												<!-- The container for the uploaded files -->
												<div id="photofiles" class="files multi_inbox"></div>
											</td>
										</tr>
										</c:if>
										<tr>
											<th scope="row"><spring:message code="common.title.tag"/></th>
											<td>
												<input type="text" name="atclTag" id="atclTag" maxlength="100" required="required" title="<spring:message code="common.title.tag"/>" class="form-control input-sm" value="<c:out value="${vo.atclTag}"/>"/>
												<spring:message code="board.message.bbs.atcl.msg.tag"/>
											</td>
										</tr>
									</table>
									<div class="text-right" style="margin-bottom:20px;">
										<c:if test="${gubun ne 'E'}">
										<a href="javascript:addAtcl()" class="btn btn-primary btn-sm"><spring:message code="button.add"/></a>
										</c:if>
										<c:if test="${gubun eq 'E'}">
										<a href="javascript:editAtcl()" class="btn btn-primary btn-sm"><spring:message code="button.add"/></a>
										<a href="javascript:delAtcl()" class="btn btn-warning btn-sm"><spring:message code="button.delete"/></a>
										</c:if>
										<a href="javascript:listAtcl()" class="btn btn-default btn-sm"><spring:message code="button.cancel"/></a>
									</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</section>
<script type="text/javascript">
	var atchFiles;	// 첨부파일 변수
	var atchPhotos; // 첨부사진 변수
	var thumbFile;
	var summernote;
	var data = "";
	var flag = false;

	// 페이지 초기화
	$(document).ready(function() {
		summernote = new $M.SummerNote({
							"editorId"			:	"summernote",
							"textareaId"		:	"contentTextArea",
							"repositoryCode"	:	"BBS_ATCL",
							'organization' 		: 	"${USER_ORGCD}",
							"editorHeight"		:	"400px",
							"attachments"		:	$.parseJSON('${vo.attachImagesJson}')
						});
		<c:if test="${bbsInfoVO.atchUseYn eq 'Y'}" >
		// 파일 첨부 업로더 및 바인딩 및 초기화 @see /js/common_function.js 참조.
		atchFiles = new $M.JqueryFileUpload({
							"varName"			: "atchFiles",
							"files" 			: $.parseJSON('${vo.attachFilesJson}'),
							"uploaderId"		: "atchuploader",
							"fileListId"		: "atchfiles",
							"progressId"		: "atchprogress",
							"maxcount"			: 3,
							"uploadSetting"	: {
								'formData'		: { 'repository': 'BBS_ATCL',
													'organization' : "${USER_ORGCD}",
													'type'		: 'file' }
							}
						});
		</c:if>
		<c:if test="${bbsInfoVO.bbsTypeCd eq 'GALLERY'}">
		atchPhotos = new $M.JqueryFileUpload({
						"varName"			: "atchPhotos",
						"files" 			: $.parseJSON('${vo.attachPhotosJson}'),
						"uploaderId"		: "photouploader",
						"fileListId"		: "photofiles",
						"progressId"		: "photoprogress",
						"maxcount"			: 10,
						"previewImage"		: true,
						"previewImageMaxWidth" : "600",
						"uploadSetting"		: {
							'formData'		: { 'repository': 'BBS_ATCL',
				                                'organization' : "${USER_ORGCD}",
												'type'		: 'photo' }
						}
					});
		</c:if>
		<c:if test="${bbsInfoVO.bbsTypeCd eq 'GALLERY' || bbsInfoVO.bbsTypeCd eq 'NEWS'}">
		thumbFile = new $M.JqueryFileUpload({
						"varName"			: "thumbFile",
						"files" 			: $.parseJSON('${vo.thumbFileJson}'),
						"uploaderId"		: "thumbuploader",
						"fileListId"		: "thumbfiles",
						"progressId"		: "thumbprogress",
						"maxcount"			: 1,
						"previewImage"		: true,
						"previewImageMaxWidth" : "600",
						"uploadSetting"		: {
							'formData'		: { 'repository': 'BBS_ATCL',
				                                'organization' : "${USER_ORGCD}",
												'type'		: 'thumb' }
						}
					});
		</c:if>

	});

	function uploderclick(str) {
		$("#"+str).click();

	}

	/* 패스워드 입력 Input */
	$("#pwBox").css("display","none");
	if($('#lockYn').is(':checked')){
		$("#pwBox").css("display","");
	}
	
	$('#lockYn').click(function() {
		if ($("input[name=lockYn]").is(":checked")) {
            $("#pwBox").show();
        } else {
            $("#pwBox").hide();
            $("#password").val("");
            $("#password").attr("disabled",false);
        }
	});
	
	// 비밀번호 수정버튼 처리
	$('#editPw').click(function(){
		$('#password').attr("disabled",false);
		$('#password').val('');
		flag = true;
	});

	/* 서브밋 처리 */
	function process(cmd) {
		$('#bbsAtclForm').attr("action","/mng/brd/bbs/" + cmd);
		
		if(!validate(document.bbsAtclForm)) return false;
		
		var _content = $("#summernote").summernote('code');
		if(_content == "<p><br></p>" || isEmpty(_content)){
			alert("<spring:message code="board.message.bbs.atcl.alert.input.cnts"/>");
			return;
		}
		
		if($('#lockYn').is(':checked')){
			var password = $('#password').val(); 
			
			if(password==""){
				alert("비밀번호를 입력하세요.");
				return;
			} else {
				if(flag == false && "${vo.password}" != "" ){
					$("#editPassword").val("${vo.password}");
					$('#encryptData').val("");
				} else {
					var encrypt = makeSendInfo(password);
					$('#encryptData').val(encrypt);
				}
			}
			
		} else {
			//패스워드 널값
			$('#password').val() == null;
		};
		
		//-- 내용 form에 setting
		$("#contentTextArea").val(_content);

		var atclTag = $("#atclTag").val();
		var atclTagAr = atclTag.split(",");
		if(atclTagAr.length > 10){
			alert("<spring:message code="board.message.bbs.atcl.msg.tag"/>");
			$("#atclTag").focus();
			return;
		}

		// 에디터 첨부파일 번호 목록 추출
		var _paramImages = summernote.getFileSnAll();
		var _paramFiles = '';
		var _paramPhotos = '';
		var _thumbFile = '';
		// atchFiles에서 첨부파일 정보를 가져온다.
		<c:if test="${bbsInfoVO.atchUseYn eq 'Y'}">
		_paramFiles = atchFiles.getFileSnAll();
		</c:if>
		if(ItemVO.bbsTypeCd == 'GALLERY') {
		_paramPhotos = atchPhotos.getFileSnAll();
		}
		if(ItemVO.bbsTypeCd == 'GALLERY' || ItemVO.bbsTypeCd == 'NEWS') {
		_thumbFile = thumbFile.getFileSnAll();
		}


		// 썸네일파일이 등록되어 있지 않은 경우 첨부이미지 첫번째 이미지를 썸네일 파일로 사용한다.
		if(_thumbFile == '' && _paramPhotos != '') {
			try {
				var imgArr = _paramPhotos.split('!@!');
				_thumbFile = imgArr[(imgArr.length-1)];
			} catch(er) {
				_thumbFile = _paramGallerys;
			}
		}
		// 썸네일파일이 등록되어 있지 않은 경우 내용에 첨부된 첫번째 이미지를 썸네일 파일로 사용한다.
		if(_thumbFile == '' && _paramImages != '') {
			try {
				var imgArr = _paramImages.split('!@!');
				_thumbFile = imgArr[(imgArr.length-1)];
			} catch(er) {
				_thumbFile = _paramImages;
			}
		}

		$(':input:hidden[name=attachImageSns]').val(_paramImages);
		$(':input:hidden[name=attachFileSns]').val(_paramFiles);
		$(':input:hidden[name=attachPhotoSns]').val(_paramPhotos);
		$(':input:hidden[name=thumbFileSn]').val(_thumbFile);

		$('#bbsAtclForm')[0].submit();
	}

	/* 글 저장 */
	function addAtcl() {
		process("addAtclMain");
	}

	/* 글 수정 */
	function editAtcl() {
		process("editAtcl");
	}

	/* 글목록 이동 */
	function listAtcl() {
		var url = generateUrl("/mng/brd/bbs/listAtclMain", {"bbsCd": "${bbsInfoVO.bbsCd}"});
		document.location.href = url;
	}

	/* 글 삭제 */
	function delAtcl() {
		if(confirm("<spring:message code="board.message.bbs.atcl.confirm.delete"/>")) {
			$('#bbsAtclForm').attr("action","/mng/brd/bbs/removeAtcl");
			$('#bbsAtclForm')[0].submit();
		} else {
			return;
		}
	}
</script>
