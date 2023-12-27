<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<mhtml:mng_html>
<mhtml:mng_head titleName="${MENUNAME}">
	<mhtml:head_module colorpicker="y" fileupload="y" />
</mhtml:mng_head>

<mhtml:frm_body cssTag="background-color:#ffffff;">
<form name="orgImgInfoForm" id="orgImgInfoForm" onsubmit="return false" method="POST" >

	<input type="hidden" name="orgCd" id="orgCd" value="${vo.orgCd}"/>
	<input type="hidden" name="imgSn" id="imgSn" value="${vo.imgSn}"/>
	<input type="hidden" name="imgTypeCd" id="imgTypeCd" value="${vo.imgTypeCd}"/>
	<input type="hidden" name="imgOdr" id="imgOdr" value="${vo.imgOdr}"/>
	<input type="hidden" name="bkgrFileSn" id="bkgrFileSn" value="${vo.bkgrFileSn}"/>
	<input type="hidden" name="descFileSn" id="descFileSn" value="${vo.descFileSn}"/>
	<input type="hidden" name="connFileSn" id="connFileSn" value="${vo.connFileSn}" />
	<table summary="" class="table table-bordered">
		<colgroup>
			<col style="width:25%"/>
			<col style="width:75%"/>
		</colgroup>
		<tr>
			<th scope="row"><spring:message code="org.title.imginfo.img.name"/></th>
			<td>
				<input type="text" name="imgTitle" id="imgTitle" maxlength="50" required="required" title="<spring:message code="org.title.imginfo.img.name"/>" class="form-control input-sm" value="${vo.imgTitle}"/>
			</td>
		</tr>
		<tr>
			<th scope="row"><spring:message code="org.title.imginfo.imgfile"/></th>
			<td>
				<c:if test="${vo.imgTypeCd eq 'MAINIMG'}">
					<c:choose>
						<c:when test="${colorTplCd eq '001'}"><p><spring:message code="org.message.imginfo.size.main"  arguments="1920|400"  argumentSeparator="|"/></p></c:when>
						<c:when test="${colorTplCd eq '002'}"><p><spring:message code="org.message.imginfo.size.main"  arguments="1170|597"  argumentSeparator="|"/></p></c:when>
						<c:when test="${colorTplCd eq '003'}"><p><spring:message code="org.message.imginfo.size.main"  arguments="1950|414"  argumentSeparator="|"/></p></c:when>
						<c:when test="${colorTplCd eq '004'}"><p><spring:message code="org.message.imginfo.size.main"  arguments="1903|420"  argumentSeparator="|"/></p></c:when>
						<c:when test="${colorTplCd eq '005'}"><p><spring:message code="org.message.imginfo.size.main"  arguments="1170|200"  argumentSeparator="|"/></p></c:when>
						<c:when test="${colorTplCd eq '006'}"><p><spring:message code="org.message.imginfo.size.main"  arguments="720|260"  argumentSeparator="|"/></p></c:when>
						<c:otherwise><p><spring:message code="org.message.imginfo.size.main"  arguments="1170|400"  argumentSeparator="|"/></p></c:otherwise>
					</c:choose>
				</c:if>
				<c:if test="${vo.imgTypeCd eq 'SUBIMG'}">
					<c:choose>
						<c:when test="${colorTplCd eq '001'}"><p><spring:message code="org.message.imginfo.size.sub" arguments="1170|120"  argumentSeparator="|" /></p></c:when>
						<c:when test="${colorTplCd eq '002'}"><p><spring:message code="org.message.imginfo.size.sub"  arguments="1170|120"  argumentSeparator="|"/></p></c:when>
						<c:when test="${colorTplCd eq '003'}"><p><spring:message code="org.message.imginfo.size.sub"  arguments="1170|120"  argumentSeparator="|"/></p></c:when>
						<c:when test="${colorTplCd eq '004'}"><p><spring:message code="org.message.imginfo.size.sub"  arguments="825|225"  argumentSeparator="|"/></p></c:when>
						<c:when test="${colorTplCd eq '005'}"><p><spring:message code="org.message.imginfo.size.sub"  arguments="1170|200"  argumentSeparator="|"/></p></c:when>
						<c:when test="${colorTplCd eq '006'}"><p><spring:message code="org.message.imginfo.size.sub"  arguments="780|150"  argumentSeparator="|"/></p></c:when>
						<c:otherwise><p><spring:message code="org.message.imginfo.size.sub" arguments="1170|120"  argumentSeparator="|" /></p></c:otherwise>
					</c:choose>
				</c:if>
				<c:if test="${vo.imgTypeCd eq 'LECIMG'}">
					<c:choose>
						<c:when test="${colorTplCd eq '001'}"><p><spring:message code="org.message.imginfo.size.lec" arguments="1170|120"  argumentSeparator="|" /></p></c:when>
						<c:when test="${colorTplCd eq '002'}"><p><spring:message code="org.message.imginfo.size.lec"  arguments="1170|120"  argumentSeparator="|"/></p></c:when>
						<c:when test="${colorTplCd eq '003'}"><p><spring:message code="org.message.imginfo.size.lec"  arguments="1170|120"  argumentSeparator="|"/></p></c:when>
						<c:when test="${colorTplCd eq '004'}"><p><spring:message code="org.message.imginfo.size.lec"  arguments="825|225"  argumentSeparator="|"/></p></c:when>
						<c:when test="${colorTplCd eq '005'}"><p><spring:message code="org.message.imginfo.size.lec"  arguments="1170|200"  argumentSeparator="|"/></p></c:when>
						<c:when test="${colorTplCd eq '006'}"><p><spring:message code="org.message.imginfo.size.lec"  arguments="780|150"  argumentSeparator="|"/></p></c:when>
						<c:otherwise><p><spring:message code="org.message.imginfo.size.lec" arguments="1170|120"  argumentSeparator="|" /></p></c:otherwise>
					</c:choose>
				</c:if>
				<div class="upload">
					<div class="upload_inbox">
						<a href="javascript:uploderclick('uploader');" class="btn btn-primary btn-xs"><spring:message code="button.select.file"/></a>
						<input type="file" class="file" name="uploader" id="uploader" title="<spring:message code="org.message.imginfo.size.sublogoimg"/>" multiple style="display:none" accept="image/*" /><%-- 첨부파일 버튼 --%>
						<div id="progress" class="progress">
						<div class="progress-bar progress-bar-success"></div>
						</div>
					</div>
					<div id="files" class="multi_inbox"></div>
				</div>
			</td>
		</tr>
		<tr>
			<th scope="row"><label for="descImgNm"><spring:message code="org.title.imginfo.imgtitle"/></label></th>
			<td>
				<span style="float:left;line-height:30px;margin-right:10px;width: 70px">Main Title :</span>
				<input type="text" name="bkgrImgNm" id="bkgrImgNm" maxlength="200" title="<spring:message code="org.title.imginfo.imgtitle"/>" class="form-control input-sm" style="width:80%" value="${vo.bkgrImgNm}"/>
				<div class="input-group" style="margin-top:5px;">
					<span style="float:left;line-height:30px;margin-right:10px;width: 70px">
						<spring:message code="common.title.fontcolor"/> :
					</span>
					<input type="text" name="bkgrFileUrl" id="bkgrFileUrl" maxlength="20" title="Text Color" class="form-control input-sm" style="width:100px;" value="${vo.bkgrFileUrl}"/>
				</div>
				<c:if test="${vo.imgTypeCd ne 'SUBIMG'}">
				<span style="float:left;line-height:30px;margin-right:10px;width: 70px">Sub Title : </span>
				<input type="text" name="descImgNm" id="descImgNm" maxlength="400" title="<spring:message code="org.title.imginfo.imgtext"/>" class="form-control input-sm" style="width:80%;" value="${vo.descImgNm}"/>
				<div class="input-group" style="margin-top:5px;">
					<span style="float:left;line-height:30px;margin-right:10px;width: 70px">
						<spring:message code="common.title.fontcolor"/> :
					</span>
					<input type="text" name="descFileUrl" id="descFileUrl" maxlength="20" title="Text Color" class="form-control input-sm" style="width:100px;" value="${vo.descFileUrl}"/>
				</div>
				</c:if>
			</td>
		</tr>
		<c:if test="${vo.imgTypeCd ne 'SUBIMG'}">
		<tr>
			<th scope="row"><label for="connUrl"><spring:message code="org.title.imginfo.linkurl"/></label></th>
			<td >
				<input type="text" name="connUrl" id="connUrl" maxlength="50" title="<spring:message code="org.title.imginfo.linkurl"/>" class="form-control input-sm" value="${vo.connUrl}"/>
			</td>
		</tr>
		<tr>
			<th scope="row"><label for="connTrgt"><spring:message code="org.title.imginfo.linktarget"/></label></th>
            <td>
  				<select name="connTrgt" id="connTrgt" class="form-control input-sm">
					<c:forEach var="item" items="${connTrgtList}" varStatus="status">
					<option value="${item.codeCd}" <c:if test="${vo.connTrgt eq item.codeCd}">selected</c:if> >${item.codeNm}</option>
					</c:forEach>				
				</select>
             </td>
		</tr>
		</c:if>
	</table>
	<div class="text-right">
		<c:if test="${gubun eq 'A'}">
		<a href="javascript:addImg()" class="btn btn-primary btn-sm"><spring:message code="button.add"/> </a>
		</c:if>
		<c:if test="${gubun eq 'E'}">
		<a href="javascript:editImg()" class="btn btn-primary btn-sm"><spring:message code="button.add"/> </a>
		<a href="javascript:delImg()" class="btn btn-warning btn-sm"><spring:message code="button.delete"/> </a>
		</c:if>
		<a href="javascript:parent.modalBoxClose()" class="btn btn-default btn-sm"><spring:message code="button.close"/> </a>
	</div>
</form>
</mhtml:frm_body>
<script type="text/javascript">
	var bkgrFile;
	var descFile;

	// 페이지 초기화
	$(document).ready(function() {
		// 이미지 파일 첨부 바인딩 및 초기화
		bkgrFile = new $M.JqueryFileUpload({
						"varName"			: "bkgrFile",
						"files" 			: $.parseJSON('${vo.bkgrFileJson}'),
						"uploaderId"		: "uploader",
						"fileListId"		: "files",
						"progressId"		: "progress",
						"maxcount"			: 1,
						"previewImage"		: true,
						"previewImageMaxWidth" : "600",
						"uploadSetting"	: {
							'formData'		: { 'repository': 'ORGIMG',
												'organization' : "${vo.orgCd}",
												'type'		: 'bkgr' }
						}
					});
		
		descFile = new $M.JqueryFileUpload({
			"varName"			: "descFile",
			"files" 			: $.parseJSON('${vo.descFileJson}'),
			"uploaderId"		: "uploaderDesc",
			"fileListId"		: "filesDesc",
			"progressId"		: "progressDesc",
			"maxcount"			: 1,
			"previewImage"		: true,
			"uploadSetting"	: {
				'formData'		: { 'repository': 'ORGIMG',
									'organization' : "${vo.orgCd}",
									'type'		: 'desc' }
			}
		});
		
		$('#bkgrFileUrl,#descFileUrl').ColorPicker({
			onSubmit: function(hsb, hex, rgb, el) {
				$(el).val(hex);
				$(el).ColorPickerHide();
			},
			onBeforeShow: function () {
				$(this).ColorPickerSetColor(this.value);
			}
		})
		.bind('keyup', function(){
			$(this).ColorPickerSetColor(this.value);
		});
	});

	function uploderclick(str) {
		$("#"+str).click();
	}

	function clickDropdown(str) {
		$("#"+str+"Drop").toggle();
	}

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		if(!validate(document.orgImgInfoForm)) return;
		if(cmd != "remove") {
			if(bkgrFile.getFileSnAll() == "") {
				alert("<spring:message code="org.message.imginfo.alert.input.main"/>");
				return;
			}
		}
		$(':input:hidden[name=bkgrFileSn]').val(bkgrFile.getFileSnAll());
		$(':input:hidden[name=descFileSn]').val(descFile.getFileSnAll());
		$('#orgImgInfoForm').attr("action", "/mng/org/imgInfo/" + cmd);
		$('#orgImgInfoForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultVO) {
		alert(resultVO.message);
		if(resultVO.result >= 0) {
			parent.callImgList()
			parent.modalBoxClose();
		} else {
			// 비정상 처리
		}
	}

	/**
	 * 이미지 추가
	 */
	function addImg() {
		process("add");	// cmd
	}

	/**
	 * 이미지 수정
	 */
	function editImg() {
		process("edit"); // cmd
	}

	/**
	 * 이미지 삭제
	 */
	function delImg() {
		if(confirm('<spring:message code="org.message.imginfo.confirm.delete"/>')) {
			process("remove"); // cmd
		} else {
			return;
		}
	}
</script>
</mhtml:mng_html>