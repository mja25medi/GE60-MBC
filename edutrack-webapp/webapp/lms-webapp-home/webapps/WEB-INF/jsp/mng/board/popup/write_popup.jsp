<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
				<section class="content">
					<div class="row" id="content">
						<div class="box">
							<div class="box-body">
								<div class="col-lg-12">
									<form name="popupNoticeForm" id="popupNoticeForm" method="POST" >
									<input type="hidden" name="popupSn" id="popupSn" value="${vo.popupSn }">
									<input type="hidden" name="gubun" id="gubun" value="${gubun }">
									<input type="hidden" name="attachImageSns" id="attachImageSns" value="${vo.attachImageSns }">
									<input type="hidden" name="editorYn" id="editorYn" value="${vo.editorYn }">
									
<%-- 									<input type="hidden" name="pageIndex" id="pageIndex" value="${vo.pageIndex}" /> --%>
<%-- 									<input type="hidden" name="searchValue" id="searchValue" value="${vo.searchValue}" /> --%>
									
									<table summary="<spring:message code="board.title.popup.manage"/>" class="table table-bordered wordbreak">
										<tr>
											<th scope="row" width="15%" ><spring:message code="common.title.title"/></th>
											<td colspan="3">
												<input type="text" maxlength="100" isNull="N" name="title" dispName="<spring:message code="common.title.title"/>" value="${vo.title }" class="form-control input-sm"/>
										    </td>
										</tr>
									    <tr >
											<th scope="row"><spring:message code="board.title.popup.opentype"/></th>
											<td>
												<select name="popupTypeCd" id="popupTypeCd" class="form-control input-sm">
													<c:forEach items="${popupTypeList}" var="item">
														<c:set var="codeName" value="${item.codeNm}"/>
														<c:forEach var="lang" items="${item.codeLangList}">
															<c:if test="${lang.langCd eq LOCALEKEY }"><c:set var="codeName" value="${lang.codeNm}"/></c:if>
														</c:forEach>
														<option value="${item.codeCd}" <c:if test="${item.codeCd eq vo.popupTypeCd }"> selected</c:if>>${codeName}</option>
													</c:forEach>
												</select>
											</td>
											<th scope="row"><spring:message code="common.title.useyn"/></th>
											<td>
												<label style="font-weight: normal;"><input type="radio" name="useYnWww" value="Y" <c:if test="${vo.useYnWww eq 'Y' or empty vo.useYnWww }">checked="chekced"</c:if>><spring:message code="common.title.useyn_y"/></label>
												<label style="font-weight: normal; margin-left:10px;"><input type="radio" name="useYnWww" value="N" <c:if test="${vo.useYnWww eq 'N' }">checked="chekced"</c:if>><spring:message code="common.title.useyn_n"/></label>
											</td>
									    </tr>
				
										<tr >
											<th scope="row" width="15%"><spring:message code="board.title.popup.width"/></th>
											<td >
												<input type="text" style="width:50px;text-align:right;float:left" maxlength="4" dispName="<spring:message code="board.title.popup.width"/>" isNull="N" lenCheck="100" dataType="number" name="xSize" value="${vo.xSize}" class="form-control input-sm inputSpecial inputNumber" onkeyup="isChkNumber(this)"/>
												<span style="float:left;line-height:30px;padding:0px 10px 0px 10px;">X</span>
												<input type="text" style="width:50px;text-align:right;float:left" maxlength="4" dispName="<spring:message code="board.title.popup.height"/>" isNull="N" lenCheck="100" dataType="number" name="ySize" value="${vo.ySize}" class="form-control input-sm inputSpecial inputNumber" onkeyup="isChkNumber(this)"/>
												<span style="float:left;line-height:30px;padding-left:10px;">px</span>
											</td>
											<th scope="row"><spring:message code="board.title.popup.postop"/></th>
											<td>
												<input type="text" style="width:50px;text-align:right;float:left" maxlength="4" dispName="<spring:message code="board.title.popup.postop"/>" isNull="N" lenCheck="100" dataType="number" value="${vo.xPos }" name="xPos" class="form-control input-sm inputSpecial inputNumber" onkeyup="isChkNumber(this)"/>
												<span style="float:left;line-height:30px;padding:0px 10px 0px 10px;">X</span>
												<input type="text" style="width:50px;text-align:right;float:left" maxlength="4" dispName="<spring:message code="board.title.popup.posleft"/>" isNull="N" lenCheck="100" dataType="number" value="${vo.yPos }" name="yPos" class="form-control input-sm inputSpecial inputNumber" onkeyup="isChkNumber(this)"/>
												<span style="float:left;line-height:30px;padding-left:10px;">px</span>
											</td>
										</tr>
										<tr >
											<th scope="row"><spring:message code="board.title.popup.startdate"/></th>
											<td>
												<div class="input-group" style="float:left;width:128px;">
													<input type="text" maxlength="100" dispName="<spring:message code="board.title.popup.startdate"/>" isNull="N" lenCheck="100" name="startDttm" value="${vo.startDttm }" id="startDttm" class="inputDate form-control input-sm" />
													<span class="input-group-addon btn-sm" onclick="_clickCalendar('startDttm')" style="cursor:pointer">
														<i class="fa fa-calendar"></i>
													</span>
												</div>
				
												<input type="text" style="width:40px;float:left;text-align:right;" dispName="<spring:message code="common.title.hour"/>" maxlength="2" isNull="N" lenCheck="2" name="startHour" value="${vo.startHour }" id="startHour" class="form-control input-sm" onkeyup="isChkHours(this)" />
												<span style="float:left;line-height:30px;"><spring:message code="common.title.hour"/></span>
												<input type="text" style="width:40px;float:left;text-align:right;" dispName="<spring:message code="common.title.min"/>" maxlength="2" isNull="N" lenCheck="2" name="startMin" value="${vo.startMin }" id="startMin" class="form-control input-sm" onkeyup="isChkMinute(this)" />
												<span style="float:left;line-height:30px;"><spring:message code="common.title.min"/></span>
				
											</td>
											<th scope="row"><spring:message code="board.title.popup.enddate"/></th>
											<td>
												<div class="input-group" style="float:left;width:128px;">
													<input type="text" maxlength="100" dispName="<spring:message code="board.title.popup.enddate"/>" isNull="N" lenCheck="100" name="endDttm" value="${vo.endDttm }" id="endDttm" class="inputDate form-control input-sm"/>
													<span class="input-group-addon btn-sm" onclick="_clickCalendar('endDttm')" style="cursor:pointer">
														<i class="fa fa-calendar"></i>
													</span>
												</div>
				
												<input type="text" style="width:40px;float:left;text-align:right;" dispName="<spring:message code="common.title.hour"/>" maxlength="2" isNull="N" lenCheck="2" name="endHour" value="${vo.endHour }" id="endHour" class="form-control input-sm" onkeyup="isChkHours(this)" />
												<span style="float:left;line-height:30px;"><spring:message code="common.title.hour"/></span>
												<input type="text" style="width:40px;float:left;text-align:right;" dispName="<spring:message code="common.title.min"/>" maxlength="2" isNull="N" lenCheck="2" name="endMin" value="${vo.endMin }" id="endMin" class="form-control input-sm" onkeyup="isChkMinute(this)" />
												<span style="float:left;line-height:30px;"><spring:message code="common.title.min"/></span>
				
												<meditag:datepicker name1="startDttm" name2="endDttm" />
											</td>
										</tr>
										<tr>
											<c:set var="fontFamily" value="Helvetica"/>
											<c:if test="${LOCALEKEY eq 'jp' }"><c:set var="fontFamily" value="Meiryo"/></c:if>
											<c:if test="${LOCALEKEY eq 'ko' }"><c:set var="fontFamily" value="맑은 고딕"/></c:if>
											<td colspan="4" style="padding:0px;font-family:${fontFamily}">
												<div id="summernote" style="float:left; width:100%; margin: 0 auto;">${vo.cts }</div>
												<textarea name="cts" id="contentTextArea"  class="sr-only">${vo.cts }</textarea>
											</td>
										</tr>
									</table>
									<div class="text-right" style="margin-bottom:20px;">
										<c:if test="${gubun eq 'A'}">
										<a href="javascript:addPopup()" class="btn btn-primary"><spring:message code="button.add"/> </a>
										</c:if>
										<c:if test="${gubun eq 'E'}">
										<a href="javascript:editPopup()" class="btn btn-primary"><spring:message code="button.add"/> </a>
										<a href="javascript:deletePopup()" class="btn btn-warning"><spring:message code="button.delete"/> </a>
										</c:if>
										<a href="javascript:goList()" class="btn btn-default"><spring:message code="button.list"/> </a>
									</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</section>
<script type="text/javascript">

	var attachFiles;	// 첨부 파일

	// 페이지 초기화
	$(document).ready(function() {
		$('.inputNumber').inputNumber();	// 숫자만 입력되도록 설정.
		$('.inputSpecial').inputSpecial();  // 특수키가 입력이 들어오묜 false return;
		$('.inputDate').inputDate();
		
		summernote = new $M.SummerNote({
			"editorId"			:	"summernote",
			"textareaId"		:	"contentTextArea",
			"repositoryCode"	:	"POPUP",
			'organization' 		: 	"${USER_ORGCD}",
			"editorHeight"		:	"400px",
			"attachments"		:	$.parseJSON('${vo.attachImagesJson}')
		});

		// 파일 첨부 업로더 및 바인딩 및 초기화 @see /js/common_function.js 참조.
		attachFiles = new $M.JqueryFileUpload({
							"varName"			: "attachFiles",
							"files" 			:$.parseJSON('[]'),
							"uploaderId"		: "atchuploader",
							"fileListId"		: "atchfiles",
							"progressId"		: "atchprogress",
							"maxcount"			: 3,
							"uploadSetting"	: {
								'formData'		: { 'repository': 'MESSAGE',
													'organization' : "${USER_ORGCD}",
													'type'		: 'file' }
							}
						});
	});
	
	function goList() {
		document.location.href = cUrl("/mng/brd/popupNotice/main?pageIndex=${vo.pageIndex}&searchValue=${vo.searchValue}");
	}
	
	<c:if test="${gubun eq 'A'}">
	function addPopup(){
		process("addPop");
	}
    </c:if>
    
    <c:if test="${gubun eq 'E'}">
    /**
	 * 팝업 수정
	 */
	function editPopup() {
		process("editPopupMain");
	}

	/**
	 * 팝업 삭제
	 */
	function deletePopup() {
		if(confirm('<spring:message code="board.message.popup.confirm.delete"/>')) {
			$('#popupNoticeForm').attr("action","/mng/brd/popupNotice/deletePopup");
			$('#popupNoticeForm')[0].submit();
		}
		return false;
	}
	</c:if>
    
	function process(cmd) {
		$('#popupNoticeForm').attr("action","/mng/brd/popupNotice/" + cmd);
		if(!validate(document.popupNoticeForm)) return false;
		var _content = $("#summernote").summernote('code');
		if(_content == "<p><br></p>" || isEmpty(_content)){
			alert('<spring:message code="board.message.bbs.atcl.alert.input.cnts"/>');
			return false;
		}
		
		$("#contentTextArea").val(_content);
		// 에디터 첨부파일 번호 목록 추출
		var _paramImages = summernote.getFileSnAll();
		$(':input:hidden[name=attachImageSns]').val(_paramImages);
		$('#popupNoticeForm')[0].submit();
	}

	function uploderclick(str) {
		$("#"+str).click();
	}

</script>
