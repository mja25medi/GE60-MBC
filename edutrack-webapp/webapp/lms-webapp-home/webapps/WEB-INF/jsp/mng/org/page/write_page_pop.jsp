<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>

<form name="pageForm" id="pageForm" onsubmit="return false" method="POST" action="/mng/org/page">

<input type="hidden" name="orgCd" id="orgCd" value="${vo.orgCd}"/>
<input type="hidden" name="pageOdr" id="pageOdr" value="${vo.pageOdr}"/>
<input type="hidden" name="attachImageSns" id="attachImageSns" value="${vo.attachImageSns}"/>
<input type="hidden" name="editorYn" id="editorYn" value="Y"/>
	<table summary="<spring:message code="system.title.page.manage"/>" class="table table-bordered wordbreak" style="margin-bottom:0px;">
		<colgroup>
			<col style="width:12%;"/>
			<col style="width:56%;"/>
			<col style="width:12%;"/>
			<col style="width:20%;"/>
		</colgroup>
		<tr>
			<th scope="row"><spring:message code="system.title.page.code"/></th>
			<td>
				<c:if test="${gubun eq 'A'}">
				<div>
					<div class="input-group" style="float:left;width:100px;">
						<input type="text" name="pageCd" id="pageCd" maxlength="10" required="required" onkeyup="isChkCharacter(this)" title="<spring:message code="system.title.page.code"/>" class="form-control input-sm" style="width:120px;"/>
					</div>
					<div style="float:left;margin-top:5px;">
						&nbsp;<spring:message code="common.message.code.warning.info"/>
					</div>
				</div>
	       		</c:if>
	       		<c:if test="${gubun eq 'E'}">
		       		<input type="hidden" name="pageCd" id="pageCd" value="${vo.pageCd}"/>
		       		${vo.pageCd}
	       		</c:if>
			</td>
			<th scope="row"><spring:message code="common.title.useyn"/></th>
			<td>
				<label style="font-weight:normal;">
					<input type="radio" name="useYn" value="Y" <c:if test="${vo.useYn eq 'Y'}">checked</c:if> /> <spring:message code="common.title.useyn_y"/>
				</label>
				<label style="font-weight:normal;margin-left:10px;">
					<input type="radio" name="useYn" value="N" <c:if test="${vo.useYn eq 'N'}">checked</c:if> /> <spring:message code="common.title.useyn_n"/>
				</label>
			</td>
		</tr>
		<tr>
			<th scope="row"><label for="pageNm"><spring:message code="system.title.page.name"/></label></th>
			<td colspan="3">
				<input type="text" name="pageNm" id="pageNm" maxlength="100" required="required" title="<spring:message code="system.title.page.name"/>" class="form-control input-sm" value="${vo.pageNm}"/>
			</td>
		</tr>
	</table>
	<div id="summernote" style="float:left; width:100%; margin: 0 auto;">${vo.pageCts}</div>
	<textarea name="pageCts" id="contentTextArea" class="sr-only"></textarea>
	<div class="text-right" style="margin-top:10px;">
        <c:if test="${gubun eq 'A' }">
		<button class="btn btn-primary btn-sm" onclick="addPage()" ><spring:message code="button.add"/></button>
        </c:if>
        <c:if test="${gubun eq 'E' }">
		<button class="btn btn-primary btn-sm" onclick="editPage()" ><spring:message code="button.add"/></button>
		<c:if test="${vo.dfltYn ne 'Y'}">
		<button class="btn btn-warning btn-sm" onclick="delPage()" ><spring:message code="button.delete"/></button>
		</c:if>
		</c:if>
        <button class="btn btn-default btn-sm" onclick="parent.modalBoxClose()" ><spring:message code="button.close"/></button>
    </div>
</form>
	<script type="text/javascript">
		// 페이지 초기화
		var summernote;
		$(document).ready(function(){
			ItemVO.orgCd = "${USER_ORGCD}";
			ItemVO.attachImagesJson = '${vo.attachImagesJson}';
			MessageVO.alertCntsNull = "<spring:message code="board.message.bbs.atcl.alert.input.cnts"/>";
			MessageVO.confirmDelete = "<spring:message code="system.message.page.confirm.delete"/>";
			summernote = new $M.SummerNote({
				"editorId"			:	"summernote",
				"textareaId"		:	"contentTextArea",
				"repositoryCode"	:	"PAGE",
				'organization' 		: 	ItemVO.orgCd,
				"editorHeight"		:	"330px",
				"attachments"		:	$.parseJSON(ItemVO.attachImagesJson)
			});
		});


		/**
		 * 서브밋 처리
		 */
		function process(cmd) {
			if(!validate(document.pageForm)) return false;
			var _content = $("#summernote").summernote('code');
			if(_content == "<p><br></p>" || isEmpty(_content)) {
				alert(MessageVO.alertCntsNull);
				return false;
			}
			//-- 내용 form에 setting
			$("#contentTextArea").val(_content);
			// 에디터 첨부파일 번호 목록 추출
			var _paramImages = summernote.getFileSnAll();
			$(':input:hidden[name=attachImageSns]').val(_paramImages);
			$('#pageForm').attr("action", "/mng/org/page/" + cmd);
			$('#pageForm').ajaxSubmit(processCallback);
		}

		/**
		 * 처리 결과 표시 콜백
		 */
		function processCallback(resultVO) {
			alert(resultVO.message);
			if(resultVO.result >= 0) {
				// 정상 처리
				parent.listPage();
				parent.modalBoxClose();
			} else {
				// 비정상 처리
			}
		}

		/**
		 * 페이지 추가
		 */
		function addPage() {
			process("add"); 
		}

		/**
		 * 페이지 수정
		 */
		function editPage() {
			process("edit");
		}

		/**
		 * 페이지 삭제
		 */
		function delPage() {
			if(confirm(MessageVO.confirmDelete)) {
				$('#pageForm').attr("action", "/mng/org/page/remove");
				$('#pageForm').ajaxSubmit(processCallback);
			} else {
				return;
			}
		}		
	</script>
