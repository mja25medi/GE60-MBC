<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>

<c:set var="gubun" value="${gubun}"/>
<c:set var="onlineSubjectVO" value="${vo}" />


	<form id="subjectForm" name="subjectForm" onsubmit="return false" >
	<input type="hidden" name="autoMakeYn" value="Y"/>
	<input type="hidden" name="sbjCd" value="${vo.sbjCd }"/>
	<input type="hidden" name="thumbFileSn" value="${vo.thumbFileSn }"/>
	<input type="hidden" name="attachFileSns" id="attachFileSns" value="${vo.attachFileSns}" />
	<table summary="<spring:message code="course.title.subject.manage.online"/>" class="table table-bordered wordbreak">
		<colgroup>
			<col style="width:20%"/>
			<col style="width:30%"/>
			<col style="width:20%"/>
			<col style="width:30%"/>
		</colgroup>
		<tr>
			<th class="top" scope="row"><spring:message code="course.title.subject.category"/></th>
			<td class="top" colspan="3">

				<div class="input-group">
					<input type="hidden" name="sbjCtgrCd" id="sbjCtgrCd" value="${vo.sbjCtgrCd }"/>
					<div class="input-group-btn btn-group">
    					<button type="button" class="btn btn-default btn-sm dropdown-toggle" data-toggle="dropdown">
    						<span class="caret"></span>
  						</button>
  						<ul class="dropdown-menu" style="max-height:220px;overflow:auto;">
  						<c:set var="defLvl" value="0" />
						<c:set var="lineNo" value="0" />
						<c:set var="sbjCtgrCd_01" value="0" />
						<c:set var="sbjCtgrCd_02" value="0" />
						<c:set var="sbjCtgrCd_03" value="0" />

						<c:forEach items="${categoryList}" var="item">
							<c:set var="lineNo" value="${lineNo + 1}" />
    						<c:if test="${lineNo == 1}" >
								<c:set var="defLvl" value="1" />
							</c:if>
    						<c:url var="typeImgPath" value="/img/framework/icon/icon_contents.gif" />
							<c:if test="${item.subCnt > 0}">
								<c:url var="typeImgPath" value="/img/framework/icon/icon_category.gif" />
							</c:if>
							<c:url var="blankImgPath" value="/img/framework/common/blank.gif" />

							<c:if test="${item.ctgrLvl == 1}">
								<c:set var="sbjCtgrCd_01" value="${item.sbjCtgrCd}" />
								<li><a href="javascript:setSbjCtgr('${item.sbjCtgrCd}','${item.sbjCtgrNm}')"> <img src='${blankImgPath}' height='10' width='${(item.ctgrLvl - defLvl)*20}'><img src='${typeImgPath}' align='absmiddle'>${item.sbjCtgrNm} </a></li>
							</c:if>

							<c:if test="${item.ctgrLvl == 2}">
								<c:if test="${sbjCtgrCd_01 eq item.parSbjCtgrCd}">
								<c:set var="sbjCtgrCd_02" value="${item.sbjCtgrCd}" />
								<li><a href="javascript:setSbjCtgr('${item.sbjCtgrCd}','${item.sbjCtgrNm}')"> <img src='${blankImgPath}' height='10' width='${(item.ctgrLvl - defLvl)*20}'><img src='${typeImgPath}' align='absmiddle'>${item.sbjCtgrNm} </a></li>
								</c:if>
							</c:if>

							<c:if test="${item.ctgrLvl == 3}">
								<c:if test="${sbjCtgrCd_02 eq item.parSbjCtgrCd}">
								<c:set var="sbjCtgrCd_03" value="${item.sbjCtgrCd}" />
								<li><a href="javascript:setSbjCtgr('${item.sbjCtgrCd}','${item.sbjCtgrNm}')"> <img src='${blankImgPath}' height='10' width='${(item.ctgrLvl - defLvl)*20}'><img src='${typeImgPath}' align='absmiddle'>${item.sbjCtgrNm} </a></li>
								</c:if>
							</c:if>

    						</c:forEach>
    						<c:if test="${empty categoryList}">
								<li style="padding-left:0px;">* <spring:message code="library.message.ctgr.nodata"/> </li>
							</c:if>

  						</ul>
  					</div>
  					<input type="text" dispName="<spring:message code="course.title.course.category"/>" maxlength="100" isNull="N" name="sbjCtgrNm" value="${vo.sbjCtgrNm }" class="form-control input-sm" id="sbjCtgrNm" readonly="true" style="background-color:#ffffff;"/>
				</div>
			</td>
		</tr>
		<tr>
			<th scope="row"><label for="sbjNm"><spring:message code="course.title.subject.name"/></label></th>
			<td colspan="3">
				<input type="text" dispName="<spring:message code="course.title.subject.name"/>" maxlength="50" isNull="N" lenCheck="50"  name="sbjNm" value="${vo.sbjNm }" class="form-control input-sm" id="sbjNm"/>
			</td>
		</tr>
		<tr>
			<th scope="row"><label for="sbjNm"><spring:message code="course.title.subject.type"/></label></th>
			<td>
				<select name="sbjType" id="sbjType" class="form-control input-sm">
					<option value=""><spring:message code="common.title.select"/></option>
					<option value="ON" <c:if test="${vo.sbjType eq 'ON'}">selected</c:if>>온라인</option>
					<option value="OFF" <c:if test="${vo.sbjType eq 'OFF'}">selected</c:if>>오프라인</option>
				</select>
			</td>
		</tr>
		<%-- 
		<tr>
			<th scope="row"><label for="refundYn">환급구분</label></th>
			<td colspan="3">
				<select name="refundYn" id="refundYn" class="form-control input-sm" style="width:300px;">
					<option value=""><spring:message code="common.title.select"/></option>
					<option value="Y" <c:if test="${vo.refundYn eq 'Y'}">selected</c:if>>환급</option>
					<option value="N" <c:if test="${vo.refundYn eq 'N'}">selected</c:if>>비환급</option>
				</select>
			</td>
		</tr>
		
		<tr>
			<th scope="row"><label for="eduPrice">정가</label></th>
			<td>
				<div class="input-group">
					<input type="text" style="text-align:right" dataType="number" isNull="N" name="eduPrice" value="${vo.eduPrice }" class="form-control input-sm" id="eduPrice" onkeyup="isChkNumber(this)"/>
					<span class="input-group-addon">원</span>
				</div>
			</td>
			<th scope="row"><label for="refundPrice">환급액</label></th>
			<td>
				<div class="input-group">
					<input type="text" style="text-align:right" dataType="number" isNull="N" name="refundPrice" value="${vo.refundPrice }" class="form-control input-sm" id="refundPrice"  onkeyup="isChkNumber(this)"/>
					<span class="input-group-addon">원</span>
				</div>
			</td>
		</tr>
		 --%>
		<%-- <tr>
			<th scope="row"><label for="simsaCode">심사코드</label></th>
			<td colspan="3">
				<input type="text" dispName="심사코드" maxlength="50" isNull="N" lenCheck="50"  name="simsaCode" value="${vo.simsaCode }" class="form-control input-sm" id="simsaCode"/>
			</td>
		</tr>
		<tr>
			<th scope="row"><label for="tracseId">훈련과정 ID</label></th>
			<td colspan="3">
				<input type="text" dispName="훈련과정 ID" maxlength="50" isNull="N" lenCheck="50"  name="tracseId" value="${vo.tracseId }" class="form-control input-sm" id="tracseId"/>
			</td>
		</tr>
		<tr>
			<th scope="row"><label for="eduGrade">과정등급</label></th>
			<td colspan="3">
				<div class="input-group">
					<input type="text" style="text-align:right" dataType="number" isNull="N" name="eduGrade" value="${vo.eduGrade }" class="form-control input-sm" id="eduGrade"/>
				</div>
			</td>
			
			<th scope="row"><label for="eduNop">정원</label></th>
			<td>
				<div class="input-group">
					<input type="text" style="text-align:right" dataType="number" isNull="N" name="eduNop" value="${vo.eduNop }" class="form-control input-sm" id="eduNop"  onkeyup="isChkNumber(this)"/>
					<span class="input-group-addon">명</span>
				</div>
			</td>
			
		</tr> --%>
		<%-- 
		<tr>
			<th scope="row"><label for="winWidth"><spring:message code="course.title.subject.width"/></label></th>
			<td>
				<div class="input-group">
					<input type="text" style="text-align:right" dispName="<spring:message code="course.title.subject.width"/>"  dataType="number" maxlength="4" isNull="N" lenCheck="4" name="winWidth" value="${vo.winWidth }" class="form-control input-sm" id="winWidth" onkeyup="isChkNumber(this)"/>
					<span class="input-group-addon">px</span>
				</div>
			</td>
			<th scope="row"><label for="winHeight"><spring:message code="course.title.subject.height"/></label></th>
			<td>
				<div class="input-group">
					<input type="text" style="text-align:right" dispName="<spring:message code="course.title.subject.height"/>"  dataType="number" maxlength="4" isNull="N" lenCheck="4" name="winHeight" value="${vo.winHeight }" class="form-control input-sm" id="winHeight"  onkeyup="isChkNumber(this)"/>
					<span class="input-group-addon">px</span>
				</div>
			</td>
		</tr>
		 --%>
		<%-- <tr>
			<th scope="row"><label for="eduPrps">학습목표</label></th>
			<td colspan="3">
				<textarea style="height:50px" isNull="N" name="eduPrps" id="eduPrps" class="form-control input-sm">${vo.eduPrps }</textarea>
			</td>
		</tr>
		<tr>
			<th scope="row"><label for="eduTarget">학습대상</label></th>
			<td colspan="3">
				<textarea style="height:50px" isNull="N" name="eduTarget" id="eduTarget" class="form-control input-sm">${vo.eduTarget }</textarea>
			</td>
		</tr> --%>
		<tr>
			<th scope="row"><label for="eduTm">총학습시간</label></th>
			<td>
				<div class="input-group">
					<input type="text" style="text-align:right" dataType="number" maxlength="4" isNull="N" name="eduTm" value="${vo.eduTm }" class="form-control input-sm" id="eduTm" onkeyup="isChkNumber(this)"/>
					<span class="input-group-addon">시간</span>
				</div>
			</td>
			<th scope="row"><label for="eduDaycnt">총학습일</label></th>
			<td>
				<div class="input-group">
					<input type="text" style="text-align:right" dataType="number" maxlength="4" isNull="N" name="eduDaycnt" value="${vo.eduDaycnt }" class="form-control input-sm" id="eduDaycnt"  onkeyup="isChkNumber(this)"/>
					<span class="input-group-addon">일</span>
				</div>
			</td>
		</tr>
		<%-- <tr>
			<th scope="row"><label for="evalDesc">평가형식</label></th>
			<td colspan="3">
				<textarea style="height:50px" isNull="N" name="evalDesc" id="evalDesc" class="form-control input-sm">${vo.evalDesc }</textarea>
			</td>
		</tr>
		<tr>
			<th scope="row"><label for="tchDesc">교강사소개</label></th>
			<td colspan="3">
				<textarea style="height:50px" isNull="N" name="tchDesc" id="tchDesc" class="form-control input-sm">${vo.tchDesc }</textarea>
			</td>
		</tr>
		<tr>
			<th scope="row"><label for="tchDesc">썸네일 이미지</label></th>
			<td colspan="3">
				<a href="javascript:uploderclick('thumbuploader');" class="btn btn-primary btn-xs"><spring:message code="button.select.file"/></a>
				<input type="file" name="thumbuploader" id="thumbuploader" title="<spring:message code="board.title.bbs.atcl.thumb"/>" multiple style="display:none" accept="image/*"  "/>첨부파일 버튼
				<div class="upload">
					<div class="upload_inbox">
						<div id="thumbprogress" class="progress">
		    				<div class="progress-bar progress-bar-success"></div>
						</div>
					</div>
					<div id="thumbfiles" class="multi_inbox"></div>
				</div>
			</td>
		</tr> --%>
		<tr>
			<th scope="row"><label for="dayStudyLimit">진도제한</label></th>
			<td colspan="3">
				<%-- <input type="hidden" maxlength="50" isNull="N" lenCheck="50" name="dayStudyLimit" value="8" class="form-control input-sm" id="dayStudyLimit" />
				<p style="margin: 5px 5px;float: left;">하루 최대 8차시까지 수강 가능</p>  ${vo.dayStudyLimit } --%>
				<p style="margin: 5px 5px;float: left;">하루 최대</p>
				<input type="text" maxlength="50" isNull="N" lenCheck="50"  name="dayStudyLimit" value="${vo.dayStudyLimit }" class="form-control input-sm" id="dayStudyLimit" style="float: left;width: 20%"  onkeyup="isChkNumber(this)"/>
				<p style="margin: 5px 5px;float: left;">차시까지만 수강 가능</p>
			</td>
		</tr>
		
		<tr>
			<th scope="row"><label for="tchDesc">강의계획서</label></th>
			<td colspan="3">
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
		<%-- <tr>
			<th scope="row">평가비율</th>
			<td colspan="3">
				<div class="input-group" style="float: left;width: 23%">
					<span class="input-group-addon">시험</span>
					<input type="text" maxlength="50" isNull="N" lenCheck="50"  name="examRatio" value="${vo.examRatio }" class="form-control input-sm" id="examRatio"  onkeyup="isChkNumber(this)"/>
					<span class="input-group-addon">%</span>
				</div>
				<div class="input-group" style="float: left;width: 23%">
					<span class="input-group-addon">출석</span>
					<input type="text" maxlength="50" isNull="N" lenCheck="50"  name="prgrRatio" value="${vo.prgrRatio }" class="form-control input-sm" id="prgrRatio"  onkeyup="isChkNumber(this)"/>
					<span class="input-group-addon">%</span>
				</div>
				<div class="input-group" style="float: left;width: 23%">
					<span class="input-group-addon">과제</span>
					<input type="text" maxlength="50" isNull="N" lenCheck="50"  name="asmtRatio" value="${vo.asmtRatio }" class="form-control input-sm" id="asmtRatio"  onkeyup="isChkNumber(this)"/>
					<span class="input-group-addon">%</span>
				</div>
				<div class="input-group" style="float: left;width: 28%">
					<span class="input-group-addon">진행단계</span>
					<input type="text" maxlength="50" isNull="N" lenCheck="50"  name="etcRatio" value="${vo.etcRatio }" class="form-control input-sm" id="etcRatio"  onkeyup="isChkNumber(this)"/>
					<span class="input-group-addon">%</span>
				</div>
			</td>
		</tr>
		
		<tr>
			<th scope="row">메인노출여부</th>
			<td colspan="3">
				<label style="font-weight: normal;"><input type="radio" style="border:0" name="mainYn" value="Y" <c:if test="${vo.mainYn eq 'Y'}">checked</c:if>/>노출</label>
				<label style="font-weight: normal;margin-left:10px;"><input type="radio" style="border:0" name="mainYn" value="N" <c:if test="${vo.mainYn ne 'Y'}">checked</c:if>/>미노출</label>
			</td>
		</tr> --%>
		<tr>
			<th scope="row"><spring:message code="common.title.useyn"/></th>
			<td colspan="3">
				<label style="font-weight: normal;"><input type="radio" style="border:0" name="useYn" value="Y" <c:if test="${vo.useYn eq 'Y'}">checked</c:if>/><spring:message code="common.title.useyn_y"/></label>
				<label style="font-weight: normal;margin-left:10px;"><input type="radio" style="border:0" name="useYn" value="N" <c:if test="${vo.useYn ne 'Y'}">checked</c:if>/><spring:message code="common.title.useyn_n"/></label>
			</td>
		</tr>
		<tr>
			<th scope="row"><label for="sbjDesc"><spring:message code="course.title.subject.desc"/></label></th>
			<td colspan="3">
				<textarea style="height:50px" dispName="<spring:message code="course.title.subject.desc"/>" isNull="N" name="sbjDesc" id="sbjDesc" class="form-control input-sm">${vo.sbjDesc }</textarea>
			</td>
		</tr>
	</table>
	<div class="text-right">
		<c:if test="${gubun eq 'A'}">
		<a href="javascript:addSubject()" class="btn btn-primary btn-sm"><spring:message code="button.add"/></a>
		</c:if>
		<c:if test="${gubun eq 'E'}">
		<a href="javascript:editSubject()" class="btn btn-primary btn-sm"><spring:message code="button.add"/> </a>
		<a href="javascript:delSubject()" class="btn btn-warning btn-sm"><spring:message code="button.delete"/> </a>
		</c:if>
		<a href="javascript:parent.modalBoxClose()" class="btn btn-default btn-sm"><spring:message code="button.close"/> </a>
	</div>
	<input type="submit" value="submit" style="display:none" />
	</form>

<script type="text/JavaScript" src="/js/jquery/jquery-fileupload/jquery.fileupload.js"></script>
<script type="text/JavaScript" src="/js/common_fileupload.js"></script>	
<script type="text/javascript">
	var thumbFile;
	var atchFiles;	// 첨부파일 변수
	$(document).ready(function() {
		thumbFile = new $M.JqueryFileUpload({
			"varName"			: "thumbFile",
			"files" 			: $.parseJSON('${vo.thumbFileJson}'),
			"uploaderId"		: "thumbuploader",
			"fileListId"		: "thumbfiles",
			"progressId"		: "thumbprogress",
			"maxcount"			: 1,
			"previewImage"		: true,
			"infoUse"		: true,
			"uploadSetting"		: {
				'formData'		: { 'repository': 'SBJ_THUMB',
	                                'organization' : "${USER_ORGCD}",
									'type'		: 'thumb' }
			}
		});
		
		atchFiles = new $M.JqueryFileUpload({
			"varName"			: "atchFiles",
			"files" 			: $.parseJSON('${vo.attachFilesJson}'),
			"uploaderId"		: "atchuploader",
			"fileListId"		: "atchfiles",
			"progressId"		: "atchprogress",
			"maxcount"			: 1,
			"uploadSetting"	: {
				'formData'		: { 'repository': 'SBJ_PLAN',
									'organization' : "${USER_ORGCD}",
									'type'		: 'file' }
			}
		});
	});

	function setSbjCtgr(ctgrCd, ctgrNm) {
		$('#sbjCtgrCd').val(ctgrCd);
		$('#sbjCtgrNm').val(ctgrNm);
	}

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		if(!validate(document.subjectForm)) return;
		
		var _thumbFile = thumbFile.getFileSnAll();
		$(':input:hidden[name=thumbFileSn]').val(_thumbFile);
		
		var _paramFiles = atchFiles.getFileSnAll();
		$(':input:hidden[name=attachFileSns]').val(_paramFiles);
		
		$('#subjectForm').attr("action","/mng/course/subject/" + cmd);
		$('#subjectForm').attr("method","post");
		$('#subjectForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		alert(resultDTO.message);
		if(resultDTO.result >= 0) {
			// 정상 처리
			parent.listSubject(1);
			parent.modalBoxClose();
		} else {
			// 비정상 처리
			parent.modalBoxClose();
		}
	}

	/**
	 * 과목 등록
	 */
	function addSubject() {
		process("addOnline");	// cmd
	}

	/**
	 * 과목 수정
	 */
	function editSubject() {
		process("editOnline");	// cmd
	}

	/**
	 * 과목 삭제
	 */
	function delSubject() {
		var f = document.subjectForm;

		//과정 cnt
		if('${vo.crsCreCnt}' > 0){
			alert("<spring:message code="course.message.subject.alert.delete1"/>");
			return;
		}

		//교재 cnt
		if('${vo.contentsCnt}' > 0){
			alert("<spring:message code="course.message.subject.alert.delete2"/>");
			return;
		}

		if(confirm('<spring:message code="course.message.subject.confirm.delete"/>')) {
			process("deleteOnline");	// cmd
		} else {
			return;
		}
	}

	function uploderclick(str) {
		$("#"+str).click();
	}
	
	// 최대 수강 8차시 제한
	$("#dayStudyLimit").change(function(){
		if($("#dayStudyLimit").val() > 8){
			alert("하루 최대 8차시까지 가능합니다.");
			$("#dayStudyLimit").val("");
			$("#dayStudyLimit").focus();
			return;
		}
	});
</script>
