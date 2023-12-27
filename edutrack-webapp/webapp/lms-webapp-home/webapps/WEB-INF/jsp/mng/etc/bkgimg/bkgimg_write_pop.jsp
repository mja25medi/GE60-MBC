<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="gubun" value="${gubun}"/>

<form onsubmit="return false" name="bkgImgForm" id="bkgImgForm">
    <input type="hidden" name="cmd" />
    <input type="hidden" name="bkgrImgSn" value="${vo.bkgrImgSn }"/>
    <input type="hidden" name="bkgrImgOdr" value="${vo.bkgrImgOdr }" />
    <input type="hidden" name="mainImgFileSn" value="${vo.mainImgFileSn }" />
    <table summary="<spring:message code="etc.title.banner.write"/>" class="table table-bordered wordbreak">
        <colgroup>
    		<col style="width:20%"/>
    		<col style="width:80%"/>
    	</colgroup>
		<tr>
			<th class="top" scope="row"><spring:message code="etc.title.bkgimg.name"/></th>
			<td class="top">
				<input type="text" maxlength="50" dispName="<spring:message code="etc.title.bkgimg.name"/>" isNull="N" name="bkgrImgNm" value="${vo.bkgrImgNm }" class="form-control input-sm"/>
			</td>
		</tr>
		<tr>
			<th scope="row"><spring:message code="etc.title.bkgimg.image"/></th>
			<td>
				<div class="upload">
					<div class="upload_inbox">
						<a href="javascript:uploderclick('uploader');" class="btn btn-primary btn-xs"><spring:message code="button.select.file"/></a>
						<input type="file" name="uploader" id="uploader"  style="display:none" accept="image/*" />
						<div id="progress" class="progress">
		    				<div class="progress-bar progress-bar-success"></div>
						</div>
					</div>
					<div id="files" class="multi_inbox"></div>
				</div>
			</td>
		</tr>
		<tr>
			<th scope="row"><spring:message code="common.title.useyn"/></th>
			<td>
				<label ><input type="radio" style="border:0" name="useYn" value="Y"  id="useY" <c:if test="${vo.useYn eq 'Y'}">checked</c:if>/><spring:message code="common.title.useyn_y"/></label>
				<label style="margin-left:10px;"><input type="radio" style="border:0" name="useYn" value="N" id="useN" <c:if test="${vo.useYn ne 'Y'}">checked</c:if>/><spring:message code="common.title.useyn_n"/></label>
			</td>
		</tr>
    </table>
    <div class="text-right">
    	<c:if test="${gubun eq 'A'}">
            <a href="javascript:add()" class="btn btn-primary btn-sm"><spring:message code="button.add"/></a>
        </c:if>
        <c:if test="${gubun eq 'E'}">

        	<a href="javascript:edit()" class="btn btn-primary btn-sm"><spring:message code="button.edit"/></a>
        	<a href="javascript:del()" class="btn btn-warning btn-sm"><spring:message code="button.delete"/></a>
        </c:if>
        <a href="javascript:parent.modalBoxClose()" class="btn btn-default btn-sm"><spring:message code="button.close"/></a>
    </div>

</form>
<script type="text/javascript">
	var mainImgFile;

	// 페이지 초기화
	$(document).ready(function() {
		// 이미지 파일 첨부 바인딩 및 초기화
		mainImgFile = new $M.JqueryFileUpload({
						"varName"			: "mainImgFile",
						"files" 			: $.parseJSON('${vo.mainImgFileJson}'),
						"uploaderId"		: "uploader",
						"fileListId"		: "files",
						"progressId"		: "progress",
						"maxcount"			: 1,
						"previewImage"		: true,
						"uploadSetting"	: {
							'formData'		: { 'repository': 'BKGIMG',
												'organization' : "${USER_ORGCD}",
												'type'		: 'main' }
						}
					});
		<c:if test="${gubun eq 'A'}">
		$("#useY").attr("checked",true);
		</c:if>
	});

	function uploderclick(str) {
		$("#"+str).click();
	}

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		if(!validate(document.bkgImgForm)) return;
		$(':input:hidden[name=mainImgFileSn]').val(mainImgFile.getFileSnAll());

		if(mainImgFile.getFileSnAll() == ""){
			alert("<spring:message code="etc.message.bkgimg.alert.imgfile"/>");
			return;
		}

		if(!$("#useY").is(":checked") && !$("#useN").is(":checked") ){
			alert("<spring:message code="common.message.alert.uesyn"/>");
			return;
		}

		$('#bkgImgForm').attr("action", "/mng/etc/bkgImg/" + cmd);
		$('#bkgImgForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		alert(resultDTO.message);

		if(resultDTO.result >= 0) {
			// 정상 처리
			parent.bkgImgList(1);
			parent.modalBoxClose();
		} else {
			// 비정상 처리
		}
	}

	/**
	 * 코드 추가
	 */
	function add() {
		process("add");	// cmd
	}

	/**
	 * 코드 수정
	 */
	function edit() {
		process("edit"); // cmd
	}

	/**
	 * 코드 삭제
	 */
	function del() {
		if(confirm('<spring:message code="etc.message.banner.confirm.delete"/>')) {
			process("delete"); // cmd
		} else {
			return;
		}
	}
</script>
