<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="img_base" value="/img/uniedu/home" />
<c:set var="lecBbsAtclVO" value="${vo}"/>
<c:set var="gubun" value="${gubun}"/>

				<form id="lecBbsForm" name="lecBbsForm" onsubmit="return false">
			    <input type="hidden" name="crsCreCd" value="${vo.crsCreCd}" />
				<input type="hidden" name="bbsCd" value="${vo.bbsCd}" />
				<input type="hidden" name="searchKey" value="${vo.searchKey}" />
				<input type="hidden" name="searchValue" value="${vo.searchValue}" />
				<input type="hidden" name="atclSn" value="${vo.atclSn}"/>
				<input type="hidden" name="parAtclSn" value="${vo.parAtclSn}" />
				<input type="hidden" name="atclOdr" value="${vo.atclOdr}" />
				<input type="hidden" name="atclLvl" value="${vo.atclLvl}" />
				<input type="hidden" name="curPage" value="${vo.curPage}" />
				<input type="hidden" name="attachFilesSn" value="${vo.attachFilesSn}" />
				<input type="hidden" name="attachImagesSn" value="${vo.attachImagesSn}" />
				<input type="hidden" name="editorYn" value="Y" />
				<div class="row">
					<div class="col-lg-12">
						<table class="table table-bordered wordbreak">
							<caption class="sr-only"><spring:message code="lecture.title.bbs"/></caption>
							<colgroup>
								<col style="width:20%" />
								<col />
							</colgroup>
							<tbody>
								<tr>
									<th scope="row" ><label for="atclTitle"><spring:message code="common.title.title"/></label></th>
									<td scope="row">
										<ninput type="text" name="title" value="${vo.title}" dispName="<spring:message code="common.title.title"/>" isNull="N" lenCheck="100" maxlength="100" class="form-control input-sm" id="atclTitle"/>
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
									<th scope="row" colspan="2"><spring:message code="common.title.cnts"/></th>
								</tr>
								<tr>
									<td colspan="2">
										<textarea name="cts" style="height:350px;" title="common.title.cnts" class="form-control input-sm">${vo.cts}</textarea>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
				<div class="row" style="margin-bottom:20px;">
					<div class="col-lg-12">
						<div class="text-right">
							<c:if test="${gubun ne 'E'}">
								<a href="#" onclick="addAtcl()" class="btn btn-primary btn-sm"><spring:message code="button.add"/></a>
							</c:if>
							<c:if test="${gubun eq 'E'}">
								<a href="#" onclick="editAtcl()" class="btn btn-primary btn-sm"><spring:message code="button.add"/></a>
								<a href="#" onclick="delAtcl()" class="btn btn-warning btn-sm"><spring:message code="button.delete"/></a>
							</c:if>
							<a href="<c:url value="/lec/bbs/main?bbsCd=${lecBbsAtclVO.bbsCd}&amp;curPage=${lecBbsAtclVO.curPage}"/>" class="btn btn-default btn-sm"><spring:message code="button.cancel"/></a>
						</div>
					</div>
				</div>
				</form>

<script type="text/javascript">
	var atchFiles; // 첨부파일 목록

	$(document).ready(function() {

		// 파일 첨부 업로더 및 바인딩 및 초기화 @see /js/common_function.js 참조.
		atchFiles = new $M.JqueryFileUpload({
						"varName"			: "atchFiles",
						"files" 			: $.parseJSON('${lecBbsAtclVO.attachFilesJson}'),
						"uploaderId"		: "atchuploader",
						"fileListId"		: "atchfiles",
						"progressId"		: "atchprogress",
						"maxcount"			: 3,
						"uploadSetting"		: {
							'formData'		: { 'repository': 'LEC_BBS_ATCL',
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
		$('#lecBbsForm')[0].submit();
	}
	 /* 글 저장 */
	function addAtcl() {
		if(!validate(document.lecBbsForm)) return false;
		$('#lecBbsForm').attr("action","/lec/bbs/addAtcl");

		$(':input:hidden[name=attachFilesSn]').val(atchFiles.getFileSnAll());
		$('#uploadify').attr("disabled", true); //-- 파일 객체 disable

		document.lecBbsForm.submit();
	}

	/* 글 수정 */
	function editAtcl(){
		if(!validate(document.lecBbsForm)) return false;
		$('#lecBbsForm').attr("action","/lec/bbs/editAtcl");
		$(':input:hidden[name=attachFilesSn]').val(atchFiles.getFileSnAll());
		$('#uploadify').attr("disabled", true); //-- 파일 객체 disable

		document.lecBbsForm.submit();
	}

	/* 글 삭제 */
	function delAtcl() {
		if(confirm('<spring:message code="board.message.faq.confirm.delete"/>')) {
			$('#lecBbsForm').attr("action","/lec/bbs/removeAtcl");
			document.lecBbsForm.submit();
		} else {
			return;
		}
	}

</script>
