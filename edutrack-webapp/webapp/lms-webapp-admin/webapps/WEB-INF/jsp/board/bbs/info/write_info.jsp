<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>

<form name="bbsInfoForm" id="bbsInfoForm" onsubmit="return false" method="POST" enctype="multipart/form-data">
	<input type="hidden" name="mainImgFileSn" id="mainImgFileSn" value="${vo.mainImgFileSn}"/>
	<input type="hidden" name="editorUseYn" id="editorUseYn" value="N"/>
	<input type="hidden" name="mobileUseYn" id="mobileUseYn" value="N"/>
	<input type="hidden" name="detlViewCd" id="detlViewCd" value="N"/>
	<table summary="<spring:message code="board.title.bbs.info.manage"/>" class="table table-bordered">
		<colgroup>
			<col style="width:20%"/>
			<col style="width:30%"/>
			<col style="width:20%"/>
			<col style="width:30%"/>
		</colgroup>
		<tr>
			<th scope="row" class="top"><label for="orgCd"><spring:message code="org.title.orginfo.orgname"/></label></th>
			<td class="top" colspan="3">
				<c:if test="${gubun eq 'A'}">
					<select name="orgCd" id="orgCd" class="form-control input-sm" onchange="javascript:uploadOrgChange()">
					<c:forEach var="item" items="${orgInfoList}">
						<option value="${item.orgCd}" <c:if test="${item.orgCd eq vo.orgCd}">selected</c:if>>${item.orgNm}</option>
					</c:forEach>
					</select>
	       		</c:if>
	       		<c:if test="${gubun eq 'E'}">
	       			${vo.orgNm}
		       		<input type="hidden" name="orgCd" id="orgCd" value="${vo.orgCd}"/>
	       		</c:if>
			</td>
		</tr>
		<tr>
			<th scope="row"><label for="bbsCd"><spring:message code="board.title.bbs.info.bbscd"/></label></th>
			<td colspan="3">
				<c:if test="${gubun eq 'A'}">
					<div class="input-group">
						<input type="text" name="bbsCd" id="bbsCd" maxlength="10" required="required" onkeyup="isChkCharacter(this)" title="<spring:message code="board.title.bbs.info.bbscd"/>" class="form-control input-sm" style="width:100px;"/>
	       				<span style="float:left;margin-left:10px;line-height:30px;">&nbsp;<spring:message code="board.message.bbs.info.bbscd"/></span>
	       			</div>
	       		</c:if>
	       		<c:if test="${gubun eq 'E'}">
		       		<input type="hidden" name="bbsCd" id="bbsCd" value="${vo.bbsCd}"/>
		       		${vo.bbsCd}
	       		</c:if>
			</td>
		</tr>
        <tr>
			<th scope="row"><label for="bbsNm"><spring:message code="board.title.bbs.info.bbsnm"/></label></th>
			<td colspan="3">
				<input type="text" name="bbsNm" id="bbsNm" maxlength="100" required="required" title="<spring:message code="board.title.bbs.info.bbsnm"/>" class="form-control input-sm" value="${vo.bbsNm}"/>
			</td>
		</tr>
		<tr>
			<th scope="row"><label for="bbsDesc"><spring:message code="board.title.bbs.info.desc"/></label></th>
			<td colspan="3">
				<textarea name="bbsDesc" id="bbsDesc" maxlength="2000" required="required" title="<spring:message code="board.title.bbs.info.desc"/>" class="form-control input-sm">${vo.bbsDesc}</textarea>
			</td>
		</tr>
		<tr >
			<th scope="row"><spring:message code="board.title.bbs.info.mainimg"/><br/>(727x150)</th>
			<td colspan="3">
				<!-- <input type="file" name="uploadifyMainFile" id="uploadifyMainFile" title="board.title.bbs.info.mainimg"/> --><!-- 이미지 파일 -->
				<!-- <div id="mainFileListview"></div><div id="uploadifyMainFileQueue"></div> -->
				<div class="upload">
					<div class="upload_inbox">
						<a href="javascript:uploderclick('mainuploader');" class="btn btn-primary btn-xs"><spring:message code="button.select.file"/></a>
						<input type="file" class="file" name="mainuploader" id="mainuploader" title="<spring:message code="org.title.orginfo.toplogoimg"/>" multiple style="display:none" accept="image/*" /><%-- 첨부파일 버튼 --%>
						<div id="homeprogress" class="progress">
		    				<div class="progress-bar progress-bar-success"></div>
						</div>
					</div>
					<div id="mainfiles" class="multi_inbox"></div>
				</div>	
			</td>
		</tr>
		<tr>
			<th scope="row"><spring:message code="board.title.bbs.info.listtype"/></th>
			<td colspan="3">
				<c:forEach var="item" items="${bbsTypeList}" varStatus="status">
				<label style="font-weight: normal; margin-right: 10px;">
					<input type="radio" name="bbsTypeCd" value="${item.codeCd}" id="bbsType_${item.codeCd}" onclick="changeBbsType()" <c:if test="${vo.bbsTypeCd eq item.codeCd}">checked</c:if>/> ${item.codeNm}
				</label>
				</c:forEach>
			</td>
		</tr>
		<tr>
			<th scope="row"><spring:message code="board.title.bbs.info.reply.useyn"/></th>
			<td>
				<label style="font-weight: normal;">
					<input type="radio" name="ansrUseYn" value="Y" <c:if test="${vo.ansrUseYn eq 'Y'}">checked</c:if>/> <spring:message code="common.title.useyn_y"/>
				</label>
				<label style="font-weight: normal; margin-left:10px;">
					<input type="radio" name="ansrUseYn" value="N" <c:if test="${vo.ansrUseYn eq 'N'}">checked</c:if>/> <spring:message code="common.title.useyn_n"/>
				</label>
			</td>
			<th scope="row"><spring:message code="board.title.bbs.info.comment.useyn"/></th>
			<td>
				<label style="font-weight:normal;">
					<input type="radio" name="cmntUseYn" value="Y" <c:if test="${vo.cmntUseYn eq 'Y'}">checked</c:if>/> <spring:message code="common.title.useyn_y"/>
				</label>
				<label style="font-weight:normal; margin-left:10px;">
					<input type="radio" name="cmntUseYn" value="N" <c:if test="${vo.cmntUseYn eq 'N'}">checked</c:if>/> <spring:message code="common.title.useyn_n"/>
				</label>
			</td>
		</tr>
		<tr>
			<th scope="row"><spring:message code="board.title.bbs.info.atache.useyn"/></th>
			<td>
				<label style="font-weight:normal;">
					<input type="radio" name="atchUseYn" value="Y" <c:if test="${vo.atchUseYn eq 'Y'}">checked</c:if>/> <spring:message code="common.title.useyn_y"/>
				</label>
				<label style="font-weight:normal; margin-left:10px;">
					<input type="radio" name="atchUseYn" value="N" <c:if test="${vo.atchUseYn eq 'N'}">checked</c:if>/> <spring:message code="common.title.useyn_n"/></label>
			</td>
			<th scope="row"><spring:message code="board.title.bbs.info.write.useyn"/></th>
			<td>
				<label style="font-weight:normal;">
					<input type="radio" name="writeUseYn" value="Y" <c:if test="${vo.writeUseYn eq 'Y'}">checked</c:if>/> <spring:message code="common.title.useyn_y"/>
				</label>
				<label style="font-weight:normal; margin-left:10px;">
					<input type="radio" name="writeUseYn" value="N" <c:if test="${vo.writeUseYn eq 'N'}">checked</c:if>/> <spring:message code="common.title.useyn_n"/>
				</label>
			</td>
		</tr>
		<tr>
			<th scope="row"><label for=""><spring:message code="board.title.bbs.info.list.size"/></label></th>
			<td colspan="3">
				<div class="input-group">
					<input type="text" name="listViewCnt" id="listViewCnt" maxlength="3"  required="required" title="<spring:message code="board.title.bbs.info.list.size"/>" class="form-control input-sm" style="width:100px;text-align:right" value="${vo.listViewCnt}"/>
					<span style="float:left;margin-left:10px;line-height:30px;">Line</span>
				</div>
			</td>
		</tr>
	</table>
	<div class="text-right">
		<c:if test="${gubun eq 'A' }">
		<button class="btn btn-primary btn-sm" onclick="addInfo()" ><spring:message code="button.add"/></button>
		</c:if>
		<c:if test="${gubun eq 'E' }">
		<button class="btn btn-primary btn-sm" onclick="editInfo()" ><spring:message code="button.add"/></button>
			<c:if test="${vo.dfltYn ne 'Y' }">
		<button class="btn btn-warning btn-sm" onclick="delInfo()" ><spring:message code="button.delete"/></button>
			</c:if>
		</c:if>
		<button class="btn btn-default btn-sm" onclick="parent.modalBoxClose()" ><spring:message code="button.close"/></button>
	</div>
</form>

<script type="text/javascript">
	var mainFile;
	// 페이지 초기화
	$(document).ready(function() {
		mainFile = new $M.JqueryFileUpload({
			"varName"			: "mainFile",
			"files" 			: $.parseJSON('${vo.mainImgFileJson}'),
			"uploaderId"		: "mainuploader",
			"fileListId"		: "mainfiles",
			"progressId"		: "mainprogress",
			"maxcount"			: 1,
			"previewImage"		: true,
			"uploadSetting"	: {
				'formData'		: { 'repository': 'BBS_INFO',
									'organization' : $("#orgCd").val(),
									'type'		: 'thumb' }
			}
		});
	});


	function changeBbsType() {

	}

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		if(!validate(document.bbsInfoForm)) return;
		$('#uploadifyMainFile').attr('disabled',true);
		$('#bbsInfoForm').attr("action",cmd);
		$('#bbsInfoForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultVO) {
		alert(resultVO.message);
		if(resultVO.result >= 0) {
			// 정상 처리
			parent.listPageing();
			parent.modalBoxClose();
		} else {
			// 비정상 처리
		}
	}

	/**
	 * 카테고리 추가
	 */
	function addInfo() {
		var _mainFile = mainFile.getFileSnAll();
		$("#bbsInfoForm").find('input[name=mainImgFileSn]').val(_mainFile);
		process("/adm/brd/bbs/add");	// cmd
	}

	/**
	 * 카테고리 수정
	 */
	function editInfo() {
		var _mainFile = mainFile.getFileSnAll();
		$("#bbsInfoForm").find('input[name=mainImgFileSn]').val(_mainFile);
		process("/adm/brd/bbs/edit"); // cmd
	}

	/**
	 * 카테고리 삭제
	 */
	function delInfo() {
		if(confirm('<spring:message code="board.message.bbs.info.confirm.delete"/>')) {
			process("/adm/brd/bbs/remove"); // cmd
		} else {
			return;
		}
	}
	
	function uploderclick(str) {
		$("#"+str).click();
	}
	
	//선택할 때마다 초기화 후 선택한 orgCd로 세팅
	function uploadOrgChange(){
		//등록된 파일이 있다면 삭제(굳이 필요없으면 삭제)
		/* if(mainFile.data.files[0] != undefined){
			if(confirm('등록한 이미지는 삭제됩니다.')){
				mainFile.removeNotConfirm(mainFile.data.files[0].fileSn);
			}else{
				return;
			}
		} */
		
		//사이트 변경할 때 이미지 삭제
		mainFile.removeAll();//removeAll은 삭제 후 callback 할 때 이벤트 발생하지 않음.
		mainFile.data.onAppend();//따라서 이벤트 발생
		
		//orgCd가 바뀌기 때문에 재설정
		mainFile = new $M.JqueryFileUpload({
			"varName"			: "mainFile",
			"files" 			: $.parseJSON('${vo.mainImgFileJson}'),
			"uploaderId"		: "mainuploader",
			"fileListId"		: "mainfiles",
			"progressId"		: "mainprogress",
			"maxcount"			: 1,
			"previewImage"		: true,
			"uploadSetting"	: {
				'formData'		: { 'repository': 'BBS_INFO',
									'organization' : $("#orgCd").val(),
									'type'		: 'thumb' }
			}
		});
	}
</script>
