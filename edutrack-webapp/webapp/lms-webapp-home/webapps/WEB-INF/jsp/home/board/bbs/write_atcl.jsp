<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/common/page_init.jsp"%>
<% // 서브 메뉴 체크 %>
<head>
	<script src="/tpl/002/js/jquery/jquery.star-rating-svg.js"></script>
 	<script src="/tpl/002/js/jquery/jquery-rating.js"></script>
</head>
	
                   
                    <div class="detail_cont write">
  <form id="frm" name="frm">
	
	<input type="hidden" id="pageIndex" name="pageIndex" value="${vo.pageIndex }">
	<input type="hidden" id="searchKey" name="searchKey" value="${vo.searchKey }">
	<input type="hidden" id="searchValue" name="searchValue" value="${vo.searchValue }">
	
	<input type="hidden" name="bbsCd" id="bbsCd" value="${vo.bbsCd}" />
	<input type="hidden" name="atclSn" id="atclSn" value="${vo.atclSn}" />
	<input type="hidden" name="parAtclSn" id="parAtclSn" value="${vo.parAtclSn}" />
	<input type="hidden" name="atclOdr" id="atclOdr" value="${vo.atclOdr}" />
	<input type="hidden" name="atclLvl" id="atclLvl" value="${vo.atclLvl}" />
	<input type="hidden" name="attachFileSns" id="attachFileSns" value="${vo.attachFileSns}" />
	<input type="hidden" name="attachImageSns" id="attachImageSns" value="${vo.attachImageSns}" />
	<input type="hidden" name="editorYn" id="editorYn" value="N" />
                       
                        <div class="tstyle">
                            <ul class="dbody">
                                <li>
                                    <div class="row">
                                        <label for="titleInput" class="form-label col-sm-2">제목</label>
                                        <div class="col-sm-10">
                                            <div class="form-row">
 												<input class="form-control" maxlength="100" name="atclTitle" type="text" id="atclTitle" title="<spring:message code="home.board.title"/>" value="<c:out value="${vo.atclTitle}"/>">
                                            </div>             
                                        </div>
                                    </div>
                                </li>
							<c:if test="${vo.bbsCd eq 'REVIEW'}">
								<li>
									<div class="row">
										<label for="starScoreInput" class="form-label col-sm-2">과정명</label>
										<div class="col-sm-10">
											<div class="form-row">
												<select id="crsCreCd" name="crsCreCd">
													<c:forEach var="item" items="${courseList}" varStatus="status">
														<option value="${item.crsCreCd}">${item.crsCreNm}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
								</li>
							</c:if>
							<c:if test="${vo.bbsCd eq 'REVIEW'}">
								<li>
									<div class="row">
										<label for="starScoreInput" class="form-label col-sm-2">만족도</label>
										<input type="hidden" name="starScore" id="starScore" value="${vo.starScore }"/>
										<div class="col-sm-10">
				                            <div class="jr-stars review">
				                                <div class="jr-ratenode jr-nomal"></div>
				                                <div class="jr-ratenode jr-nomal"></div>
				                                <div class="jr-ratenode jr-nomal"></div>
				                                <div class="jr-ratenode jr-nomal"></div>
				                                <div class="jr-ratenode jr-nomal"></div>
				                            </div>
				                            <span class="review_label">별을 클릭 또는 마우스오버로 강의평가를 할 수 있습니다.</span>
										</div>
									</div>
								</li>
							</c:if>				
							<c:if test="${gubun eq 'E'}">
								<li>
									<div class="row">
										<label for="writeInput" class="form-label col-sm-2">작성자</label>
										<div class="col-sm-10">
											<div class="form-inline">
												<input class="form-control" type="text" value="${vo.regNm}" readonly>
											</div>
										</div>
									</div>
								</li>
							</c:if>

                                <li>
                                    <div class="row">
                                        <label for="contTextarea" class="form-label col-sm-2">내용</label>
                                        <div class="col-sm-10">
                                            <div class="form-row">
												<div id="summernote" style="float:left; width:100%; margin: 0 auto;">${vo.atclCts}</div>
												<textarea name="atclCts" id="contentTextArea" title="<spring:message code="board.title.bbs.atcl.cnts"/>" class="sr-only"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                </li>
							<c:if test="${vo.bbsCd ne 'REVIEW'}">
								<li>
									<div class="row">
										<span class="form-label col-sm-2">첨부파일</span>
										<div class="upload col-sm-10 attach_area">
											<div class="upload_inbox">
												<a href="javascript:uploderclick('atchuploader');" class="btn btn-primary btn-xs" style="background: #4D4D4D; color: #fff; border-color: #4D4D4D;">
													<spring:message code="button.select.file"/>
												</a>
												<input type="file" name="atchuploader" id="atchuploader" title="<spring:message code="common.title.atachfile"/>" multiple style="display:none" /><%-- 첨부파일 버튼 --%>
												<div id="atchprogress" class="progress">
													<div class="progress-bar progress-bar-success"></div>
												</div>
											</div>
											<div id="atchfiles" class="multi_inbox attach_list"></div>
										</div>
									</div>
								</li>
							</c:if>
							<c:if test="${bbsInfoVO.lockUseYn eq 'Y' && not empty sessionScope.USERNO}">
								<li>
									<div class="row">
										<label for="hashInput" class="form-label col-sm-2">패스워드</label>
										<input type="hidden" id="encryptData" name="encryptData">
										<div class="col-sm-10">
											<div class="form-row">
												<input type="password" id="password" name="password" class="form-control">
											</div>
										</div>
									</div>
								</li>
							</c:if>
							<!-- 
                                <li>
                                    <div class="row">
                                        <label for="hashInput" class="form-label col-sm-2">해시태그</label>
                                        <div class="col-sm-10">     
                                            <small class="note">* 해시태그 입력후 엔터(Enter)나 우측 추가(+) 버튼을 눌러주세요</small>                  
                                            <div class="form-row">
                                                <input type="text" id="hashInput" class="form-control hash_input">
                                                <button type="button" class="hash_add"><i class="xi-plus"></i><span class="sr-only">해시태그 추가</span></button> 
                                            </div>                                            
                                            <div class="hash_area">
                                                <span class="sr-only">해시태그추가영역</span>
                                            </div>        
                                        </div>
                                    </div>
                                </li>  
                              -->                                 
                            </ul>
                        </div>
                        <div class="btns mt30">
							<c:if test="${gubun ne 'E'}">
								<button type="button" onclick="javascript:addAtcl();" class="btn gray2">등록</button>
								<button type="button" onclick="javascript:listAtcl()" class="btn type5">취소</button>
							</c:if>
							<c:if test="${gubun eq 'E'}">
								<button type="button" onclick="javascript:editAtcl();" class="btn gray2">등록</button>
								<button type="button" onclick="javascript:viewAtcl();" class="btn type5">취소</button>
							</c:if>
                        </div>
</form>
                    </div>
                    <!-- //detail_cont -->
	
	

<script type="text/javascript">
	var atchFiles;	// 첨부파일 변수
	var atchPhotos; // 첨부사진 변수
	var thumbFile;
	var summernote;
	var data = "";
	
	var ItemVO = {};
	
	// 페이지 초기화
	$(document).ready(function() {
		ItemVO.orgCd = "${USER_ORGCD}";
		ItemVO.bbsCd = "${bbsInfoVO.bbsCd}";
		ItemVO.bbsTypeCd = "${bbsInfoVO.bbsTypeCd}";
		
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
		
		summernote = new $M.SummerNote({
			"editorId"			:	"summernote",
			"textareaId"		:	"contentTextArea",
			"repositoryCode"	:	"BBS_ATCL",
			'organization' 		: 	"${USER_ORGCD}",
			"editorHeight"		:	"400px",
			"attachments"		:	$.parseJSON('${vo.attachImagesJson}')
		});
	});
	
	$(function(){
		var score = Number('${vo.starScore}');
        $('.jr-stars').start(score,function(cur){
            var grade;
            switch (cur) {
                case 1:
                    grade = "불만";
                    break;
                case 2:
                    grade = "조금불만";
                    break;
                case 3:
                    grade = "보통";
                    break;
                case 4:
                    grade = "만족";
                    break;
                case 5:
                    grade = "매우만족";
                    break;
                case 0:
                    grade = "별을 클릭 또는 마우스오버로 강의평가를 할 수 있습니다.";
                    break;
            }
            $('#starScore').val(cur);
            $('.review_label').text(grade);
        });
    });

	function uploderclick(str) {
		$("#"+str).click();
	}


	/* 서브밋 처리 */
	function process(cmd) {
		$('#frm').attr("action","/home/brd/bbs/"+cmd);
 		
		if(!validate(document.frm)) return false;
		
		var _content = $("#summernote").summernote('code');
		if(_content == "<p><br></p>" || isEmpty(_content)){
			alert("<spring:message code="home.board.inserttitle"/>");
			return false;
		}
		
		<c:if test="${bbsInfoVO.lockUseYn eq 'Y' && not empty sessionScope.USERNO}">
			var password = $('#password').val(); 
			
			if(password==""){
				alert("비밀번호를 입력하세요.");
				return;
			}
			var encrypt = makeSendInfo(password);
		
			$('#encryptData').val(encrypt);
		</c:if>
		
		//-- 내용 form에 setting
		$("#contentTextArea").val(_content);
		var _paramImages = summernote.getFileSnAll();
		var _paramFiles = atchFiles.getFileSnAll();
		
		$(':input:hidden[name=attachImageSns]').val(_paramImages);
		$(':input:hidden[name=attachFileSns]').val(_paramFiles);
		
		$('#frm')[0].submit();
	}

	/* 글 저장 */
	function addAtcl() {
		process("addAtcl");
	}

	/* 글 수정 */
	function editAtcl() {
		process("editAtcl");
	}
	
	/** 글 상세 화면으로 이동 */
	function viewAtcl(){
		$("#frm").attr("action","/home/brd/bbs/viewAtclMain");
		document.frm.submit();
	}
	
	/** 글 목록 화면으로 이동 */
	function listAtcl(){
		$("#frm").attr("action","/home/brd/bbs/listAtclMain");
		document.frm.submit();
	}

	/* 글 삭제 */
	function delAtcl() {
		if(confirm("<spring:message code="board.message.bbs.atcl.confirm.delete"/>")) {
			$('#frm').find("[name=cmd]").val("removeAtcl");
			$('#frm')[0].submit();
		} else {
			return;
		}
	}
	</script>