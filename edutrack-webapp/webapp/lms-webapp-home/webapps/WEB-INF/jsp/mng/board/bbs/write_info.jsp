<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<form name="bbsInfoForm" id="bbsInfoForm" method="POST" onsubmit="return false;">
	<input type="hidden" name="orgCd" id="orgCd" value="${vo.orgCd}"/>
	<input type="hidden" name="mainImgFileSn" id="mainImgFileSn" value="${vo.mainImgFileSn}"/>
	<input type="hidden" name="mobileUseYn" id="mobileUseYn" value="N"/>
	<input type="hidden" name="detlViewCd" id="detlViewCd" value="N"/>
	<input type="hidden" name="autoMakeYn" id="autoMakeYn" value="Y"/>
	<table summary="<spring:message code="board.title.bbs.info.manage"/>" class="table table-bordered wordbreak">
		<colgroup>
			<col style="width:20%"/>
			<col style="width:30%"/>
			<col style="width:20%"/>
			<col style="width:30%"/>
		</colgroup>
		<tr>
			<th scope="row"><label for="bbsCd"><spring:message code="board.title.bbs.info.bbscd"/></label></th>
			<td colspan="3">
				<c:if test="${gubun eq 'A'}">
					<div class="input-group">
	       				<div style="float:left">
	       					<input type="text" name="bbsCd" id="bbsCd" maxlength="10" required="required" onkeyup="isChkCharacter(this)" title="<spring:message code="board.title.bbs.info.bbscd"/>" class="form-control input-sm" style="width:120px;"/>
	       					<label style="font-weight:normal;line-height:30px;margin-left:5px;" ><input type="checkbox" id="chkAutoMakeYn"  name="chkAutoMakeYn" onclick="autoMakeCd()" style="border:0" id="autoMakeYn" checked/><spring:message code="common.title.automake"/></label>
						</div>
						<div style="float:left;margin-left:10px;">
							<span style="float:left;margin-left:10px;line-height:30px;">&nbsp;<spring:message code="board.message.bbs.info.bbscd"/></span>
						</div>
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
		<tr style="display:none;">
			<th scope="row"><spring:message code="board.title.bbs.info.mainimg"/><br/>(727x150)</th>
			<td colspan="3">
				<input type="file" name="uploadifyMainFile" id="uploadifyMainFile" title="<spring:message code="board.title.bbs.info.mainimg"/>" /><!-- 이미지 파일 -->
				<div id="mainFileListview"></div><div id="uploadifyMainFileQueue"></div>
			</td>
		</tr>
		<tr>
			<th scope="row"><spring:message code="board.title.bbs.info.listtype"/></th>
			<td colspan="3">
				<c:forEach var="item" items="${bbsTypeList}" varStatus="status">
				<label style="font-weight: normal; margin-right: 10px;">
					<input type="radio" name="bbsTypeCd" value="${item.codeCd}" onclick="changeBbsType()" <c:if test="${vo.bbsTypeCd eq item.codeCd}">checked</c:if> /> ${item.codeNm}
				</label>
				</c:forEach>
			</td>
		</tr>
		<tr style="display:none;">
			<th scope="row"><spring:message code="board.title.bbs.info.reply.useyn"/></th>
			<td>
				<label style="font-weight: normal;">
					<input type="radio" name="ansrUseYn" value="Y" <c:if test="${vo.ansrUseYn eq 'Y'}">checked</c:if>/> <spring:message code="common.title.useyn_y"/>
				</label>
				<label style="font-weight: normal; margin-left:10px;">
					<input type="radio" name="ansrUseYn" value="N" <c:if test="${vo.ansrUseYn eq 'N'}">checked</c:if>/> <spring:message code="common.title.useyn_n"/>
				</label>
			</td>
			<th scope="row"><spring:message code="board.title.bbs.info.atache.useyn"/></th>
			<td>
				<label style="font-weight:normal;">
					<input type="radio" name="atchUseYn" value="Y" <c:if test="${vo.atchUseYn eq 'Y'}">checked</c:if>/> <spring:message code="common.title.useyn_y"/>
				</label>
				<label style="font-weight:normal; margin-left:10px;">
					<input type="radio" name="atchUseYn" value="N" <c:if test="${vo.atchUseYn eq 'N'}">checked</c:if>/> <spring:message code="common.title.useyn_n"/>
				</label>
			</td>
		</tr>
		<tr>
			<th scope="row"><spring:message code="board.title.bbs.info.comment.useyn"/></th>
			<td>
				<label style="font-weight:normal;">
					<input type="radio" name="cmntUseYn" value="Y" <c:if test="${vo.cmntUseYn eq 'Y'}">checked</c:if>/> <spring:message code="common.title.useyn_y"/>
				</label>
				<label style="font-weight:normal; margin-left:10px;">
					<input type="radio" name="cmntUseYn" value="N" <c:if test="${vo.cmntUseYn eq 'N'}">checked</c:if>/> <spring:message code="common.title.useyn_n"/>
				</label>
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
			<th scope="row"><label for="listViewCnt"><spring:message code="board.title.bbs.info.list.size"/></label></th>
			<td>
				<div class="input-group">
					<input type="text" name="listViewCnt" id="listViewCnt" maxlength="3" required="required" title="<spring:message code="board.title.bbs.info.list.size"/>" class="form-control input-sm" value="${vo.listViewCnt}" style="text-align:rignt;width:80px;"/>
					<span style="float:left;margin-left:10px;line-height:30px;">Line</span>
				</div>
			</td>
			<th scope="row"><spring:message code="board.title.bbs.info.private.useyn"/></th>
			<td>
				<label style="font-weight:normal;">
					<input type="radio" name="lockUseYn" value="Y" <c:if test="${vo.lockUseYn eq 'Y'}">checked</c:if>/> <spring:message code="common.title.useyn_y"/>
				</label>
				<label style="font-weight:normal; margin-left:10px;">
					<input type="radio" name="lockUseYn" value="N" <c:if test="${vo.lockUseYn eq 'N'}">checked</c:if>/> <spring:message code="common.title.useyn_n"/>
				</label>
			</td>
		</tr>
		<tr style="display:none;">
			<th scope="row"><spring:message code="board.title.bbs.info.editor.useyn"/></th>
			<td>
				<label style="font-weight:normal;">
					<input type="radio" name="editorUseYn" value="Y" <c:if test="${vo.editorUseYn eq 'Y'}">checked</c:if>/> <spring:message code="common.title.useyn_y"/>
				</label>
				<label style="font-weight:normal; margin-left:10px;">
					<input type="radio" name="editorUseYn" value="N" <c:if test="${vo.editorUseYn eq 'N'}">checked</c:if>/> <spring:message code="common.title.useyn_n"/>
				</label>
			</td>
			<th scope="row"><spring:message code="common.title.useyn"/></th>
			<td>
				<label style="font-weight:normal;">
					<input type="radio" name="useYn" value="Y" <c:if test="${vo.useYn eq 'Y'}">checked</c:if>/> <spring:message code="common.title.useyn_y"/>
				</label>
				<label style="font-weight:normal; margin-left:10px;">
					<input type="radio" name="useYn" value="N" <c:if test="${vo.useYn eq 'N'}">checked</c:if>/> <spring:message code="common.title.useyn_n"/>
				</label>
			</td>
		</tr>
	</table>
	<div class="text-right">
		<c:if test="${gubun eq 'A'}">
		<button class="btn btn-primary btn-sm" onclick="addInfo()" ><spring:message code="button.add"/></button>
		</c:if>
		<c:if test="${gubun eq 'E'}">
		<button class="btn btn-primary btn-sm" onclick="editInfo()" ><spring:message code="button.add"/></button>
			<c:if test="${vo.dfltYn ne 'Y' }">
		<button class="btn btn-warning btn-sm" onclick="delInfo()" ><spring:message code="button.delete"/></button>
			</c:if>
		</c:if>
		<button class="btn btn-default btn-sm" onclick="parent.modalBoxClose()" ><spring:message code="button.close"/></button>
	</div>
</form>
<script type="text/javascript">
	// 페이지 초기화
	$(document).ready(function() {
		autoMakeCd();
	});
	
	
	function changeBbsType() {
	
	}
	
	function autoMakeCd() {
		if('${gubun}' == 'A') {
			if($("#chkAutoMakeYn").is(":checked")) {
				$("#bbsInfoForm").find("[name='bbsCd']").val("<spring:message code="common.title.automake"/>");
				$("#bbsInfoForm").find("[name='bbsCd']").css("background-color","#f3f3f3");
				$("#bbsInfoForm").find("[name='bbsCd']").attr("readonly",true);
				$("#bbsInfoForm").find("[name='autoMakeYn']").val("Y");
			} else {
				$("#bbsInfoForm").find("[name='bbsCd']").val("");
				$("#bbsInfoForm").find("[name='bbsCd']").css("background-color","#ffffff");
				$("#bbsInfoForm").find("[name='bbsCd']").attr("readonly",false);
				$("#bbsInfoForm").find("[name='autoMakeYn']").val("N");
			}
		}
	}
	
	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		if(!validate(document.bbsInfoForm)) return;
		$('#uploadifyMainFile').attr('disabled',true);
		$('#bbsInfoForm').attr("action","/mng/brd/bbs/" + cmd);
		$('#bbsInfoForm').ajaxSubmit(processCallback);
	}
	
	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultVO) {
		alert(resultVO.message);
		if(resultVO.result >= 0) {
			// 정상 처리
			parent.listInfo();
			parent.modalBoxClose();
		} else {
			// 비정상 처리
		}
	}
	
	/**
	 * 카테고리 추가
	 */
	function addInfo() {
		process("addInfo");	// cmd
	}
	
	/**
	 * 카테고리 수정
	 */
	function editInfo() {
		process("editInfo"); // cmd
	}
	
	/**
	 * 카테고리 삭제
	 */
	function delInfo() {
		if(confirm('<spring:message code="board.message.bbs.info.confirm.delete"/>')) {
			process("removeInfo"); // cmd
		} else {
			return;
		}
	}
</script>
