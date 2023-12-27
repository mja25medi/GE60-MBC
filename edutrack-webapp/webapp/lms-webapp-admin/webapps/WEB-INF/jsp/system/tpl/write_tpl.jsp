<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>

<form name="sysTplForm" id="sysTplForm" onsubmit="return false;" method="POST" enctype="multipart/form-data">
    <input type="hidden" name="tplOdr" id="tplOdr" value="${vo.tplOdr}"/>
    <input type="hidden" name="mainImgFileSn" id="mainImgFileSn" value="${vo.mainImgFileSn}"/>
    <input type="hidden" name="subImgFileSn" id="subImgFileSn" value="${vo.subImgFileSn}"/>
    <table summary="<spring:message code="etc.title.banner.write"/>" class="table table-bordered">
        <colgroup>
    		<col style="width:20%"/>
    		<col style="width:80%"/>
    	</colgroup>
		<tr>
			<th class="top" scope="row"><spring:message code="system.title.template.code"/></th>
			<td class="top">
				<c:if test="${gubun eq 'A' }">
				<input type="text" name="tplCd" id="tplCd" maxlength="10" required="required" onkeyup="isChkCharacter(this)" title="<spring:message code="system.title.template.code"/>" class="form-control input-sm" value="${vo.tplCd}"/>
				</c:if>
				<c:if test="${gubun ne 'A' }">
				<input type="hidden" name="tplCd" id="tplCd" value="${vo.tplCd}"/>${vo.tplCd}
				</c:if>
			</td>
		</tr>
		<tr>
			<th class="top" scope="row"><spring:message code="system.title.template.name"/></th>
			<td class="top">
				<input type="text" name="tplNm" id="tplNm" maxlength="50" required="required" title="<spring:message code="system.title.template.name"/>" class="form-control input-sm" value="${vo.tplNm}"/>
			</td>
		</tr>
		<tr>
			<th scope="row"><spring:message code="system.title.template.mainimage"/></th>
			<td>
				<div class="upload">
					<div class="upload_inbox">
						<a href="javascript:uploderclick('mainuploader');" class="btn btn-primary btn-xs"><spring:message code="button.select.file"/></a>
						<input type="file" name="mainuploader" id="mainuploader"  style="display:none" accept="image/*" />
						<div id="mainprogress" class="progress">
		    				<div class="progress-bar progress-bar-success"></div>
						</div>
					</div>
					<div id="mainfiles" class="multi_inbox"></div>
				</div>			
			</td>
		</tr>
		<tr>
			<th scope="row"><spring:message code="system.title.template.subimage"/></th>
			<td>
				<div class="upload">
					<div class="upload_inbox">
						<a href="javascript:uploderclick('subuploader');" class="btn btn-primary btn-xs"><spring:message code="button.select.file"/></a>
						<input type="file" name="subuploader" id="subuploader"  style="display:none" accept="image/*" />
						<div id="subprogress" class="progress">
		    				<div class="progress-bar progress-bar-success"></div>
						</div>
					</div>
					<div id="subfiles" class="multi_inbox"></div>
				</div>			
			</td>
		</tr>
		<tr>
            <th scope="row"><spring:message code="system.title.template.desc"/></th>
            <td>
            	<textarea name="tplDesc" id="tplDesc" maxlength="2000" required="required" title="<spring:message code="system.title.template.desc"/>" class="form-control input-sm">${vo.tplDesc}</textarea> 
            </td>
        </tr>
		<tr>
			<th scope="row"><spring:message code="common.title.useyn"/></th>
			<td>
				<label >
					<input type="radio" name="useYn" value="Y" <c:if test="${vo.useYn eq 'Y' }">checked</c:if>/> <spring:message code="common.title.useyn_y"/>
				</label>
				<label style="margin-left:10px;">
					<input type="radio" name="useYn" value="N" <c:if test="${vo.useYn eq 'N' }">checked</c:if>/> <spring:message code="common.title.useyn_n"/>
				</label>
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
	var subImgFile;

	// 페이지 초기화
	$(document).ready(function() {
		// 이미지 파일 첨부 바인딩 및 초기화
		mainImgFile = new $M.JqueryFileUpload({
						"varName"			: "mainImgFile",
						"files" 			: $.parseJSON('${vo.mainImgFileJson}'),
						"uploaderId"		: "mainuploader",
						"fileListId"		: "mainfiles",
						"progressId"		: "mainprogress",
						"maxcount"			: 1,
						"previewImage"		: true,
						"uploadSetting"	: {
							'formData'		: { 'repository': 'SYSTPL',
												'type'		: 'main' }
						}
					});

		subImgFile = new $M.JqueryFileUpload({
			"varName"			: "subImgFile",
			"files" 			: $.parseJSON('${vo.subImgFileJson}'),
			"uploaderId"		: "subuploader",
			"fileListId"		: "subfiles",
			"progressId"		: "subprogress",
			"maxcount"			: 1,
			"previewImage"		: true,
			"uploadSetting"	: {
				'formData'		: { 'repository': 'SYSTPL',
									'type'		: 'sub' }

			}
		});

	});

	function uploderclick(str) {
		$("#"+str).click();
	}

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		if(!validate(document.sysTplForm)) return;
		$(':input:hidden[name=mainImgFileSn]').val(mainImgFile.getFileSnAll());
		$(':input:hidden[name=subImgFileSn]').val(subImgFile.getFileSnAll());
		$('#sysTplForm').attr("action", cmd);
		$('#sysTplForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultVO) {
		alert(resultVO.message);
		if(resultVO.result >= 0) {
			// 정상 처리
			parent.listPageing(1);
			parent.modalBoxClose();
		} else {
			// 비정상 처리
		}
	}

	/**
	 * 코드 추가
	 */
	function add() {
		process("/adm/system/tpl/add");	// cmd
	}

	/**
	 * 코드 수정
	 */
	function edit() {
		process("/adm/system/tpl/edit"); // cmd
	}

	/**
	 * 코드 삭제
	 */
	function del() {
		if(confirm('<spring:message code="system.message.template.confirm.delete"/>')) {
			process("/adm/system/tpl/remove"); // cmd
		} else {
			return;
		}
	}
</script>
