<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="gubun" value="${gubun}"/>
<c:set var="olcCartrgVO" value="${vo}"/>
<mhtml:mng_html>
<mhtml:mng_head titleName="${MENUNAME}">
	<mhtml:head_module colorpicker="y" fileupload="y" />
</mhtml:mng_head>

<mhtml:frm_body>
	<form id="olcCartrgForm" name="olcCartrgForm" onsubmit="return false" action="/mng/olc/cartrg">
	<input type="hidden" name="bkgImgVal" id="bkgImgVal"/>
	<input type="hidden" name="cartrgCd" value="${vo.cartrgCd }" />
	<input type="hidden" name="cartrgOdr" value="${vo.cartrgOdr }" />
	<input type="hidden" name="topLogoImgSn" value="${vo.topLogoImgSn }"/>
	<input type="hidden" name="bkgImgSn" value="${vo.bkgImgSn }"/>
	<input type="hidden" name="bkgImgSelType" value="${vo.bkgImgSelType }"/>
	<div id="cartrgDiv">
	<table summary='<spring:message code="course.title.category.manage"/>' class="table table-striped table-bordered">
		<colgroup>
			<col style="width:20%"/>
			<col style="width:30%"/>
			<col style="width:20%"/>
			<col style="width:30%"/>
		</colgroup>
		<tr>
			<th scope="row"><spring:message code="olc.title.category"/></th>
			<td colspan="3">
				${olcCartrgVO.ctgrNm}
			</td>
		</tr>
		<tr>
			<th scope="row"><spring:message code="olc.title.cartridge.name"/></th>
			<td colspan="3">
				${olcCartrgVO.cartrgNm }
			</td>
		</tr>

		<tr>
			<th scope="row"><label for="olcNameColor"><spring:message code="olc.title.olcname.color"/></label></th>
			<td >
				<input type="text" maxlength="20" style="width:100px;" dispName="<spring:message code="olc.title.olcname.color"/>" name="olcNameColor" value="${vo.olcNameColor }" isNull="Y" lenCheck="20" class="form-control input-sm" id="olcNameColor"  />
			</td>
			<th scope="row"><label for="listBgColor"><spring:message code="olc.title.cartridge.list.bgcolor"/></label></th>
			<td>
				<input type="text" maxlength="20" style="width:100px;" dispName="<spring:message code="olc.title.cartridge.list.bgcolor"/>" name="listBgColor" value="${vo.listBgColor }" isNull="Y" lenCheck="20" class="form-control input-sm" id="listBgColor"/>
			</td>
		</tr>
		<tr>
			<th scope="row"><label for="listFontColor"><spring:message code="olc.title.cartridge.list.fontcolor"/></label></th>
			<td>
				<input type="text" maxlength="20" style="width:100px;" dispName="<spring:message code="olc.title.cartridge.list.fontcolor"/>" name="listFontColor" value="${vo.listFontColor }" isNull="Y" lenCheck="20" class="form-control input-sm" id="listFontColor"/>
			</td>
			<th scope="row"><label for="contentBgColor"><spring:message code="olc.title.cartridge.content.bgcolor"/></label></th>
			<td>
				<input type="text" maxlength="20" style="width:100px;" dispName="<spring:message code="olc.title.cartridge.content.bgcolor"/>" name="contentBgColor" value="${vo.contentBgColor }" isNull="Y" lenCheck="20" class="form-control input-sm" id="contentBgColor"/>
			</td>
		</tr>
		<tr>
			<th scope="row"><spring:message code="olc.title.cartridge.list.location"/></th>
			<td>
				<label><input type="radio" name="listLocation" value="LEFT" <c:if test="${vo.listLocation eq 'LEFT'}">checked</c:if>/> <spring:message code="olc.title.cartridge.location.left"/></label>
				<label style="margin-left:20px;"><input type="radio" name="listLocation" value="RIGHT" <c:if test="${vo.listLocation eq 'RIGHT'}">checked</c:if>/> <spring:message code="olc.title.cartridge.location.right"/></label>
			</td>
			<th scope="row"><spring:message code="olc.title.cartridge.pageno.location"/></th>
			<td>
				<label><input type="radio" name="pageNoLocation" value="LEFT" <c:if test="${vo.pageNoLocation eq 'LEFT'}">checked</c:if>/> <spring:message code="olc.title.cartridge.location.left"/></label>
				<label style="margin-left:20px;"><input type="radio" name="pageNoLocation" value="CENTER" <c:if test="${vo.pageNoLocation eq 'CENTER'}">checked</c:if>/> <spring:message code="olc.title.cartridge.location.center"/></label>
				<label style="margin-left:20px;"><input type="radio" name="pageNoLocation" value="RIGHT" <c:if test="${vo.pageNoLocation eq 'RIGHT'}">checked</c:if>/> <spring:message code="olc.title.cartridge.location.right"/></label>
			</td>
		</tr>
		<tr>
			<th scope="row"><spring:message code="olc.title.cartridge.toplogoimg"/></th>
			<td colspan="3">
				<p><spring:message code="org.message.imginfo.size.main"  arguments="255|60" argumentSeparator="|"/></p>
				<div class="upload">
					<div class="upload_inbox">
						<a href="javascript:uploderclick('toplogouploader');" class="btn btn-primary btn-xs"><spring:message code="button.select.file"/></a>
						<input type="file" name="toplogouploader" id="toplogouploader"  style="display:none" accept="image/*" />
						<div id="toplogoprogress" class="progress">
		    				<div class="progress-bar progress-bar-success"></div>
						</div>
					</div>
					<div id="toplogofiles" class="multi_inbox"></div>
				</div>
			</td>
		</tr>
		<tr>
			<th scope="row"><spring:message code="olc.title.cartridge.bkgimg.image"/></th>
			<td colspan="3">
				<div id="bkgimgArea" class="files">
					<a href="javascript:listBkgImg();" class="btn btn-primary btn-xs">
						<spring:message code="button.select.file"/>
					</a>
				</div>
			</td>
		</tr>
		<tr>
			<th scope="row"><label for="bkgImgType"><spring:message code="olc.title.cartridge.bkgimg.imagetype"/></label></th>
			<td colspan="3">
				<label style="font-weight: normal;"><input type="radio" style="border:0" name="bkgImgType" value="RESIZE" id="imgTypeR" <c:if test="${vo.bkgImgType eq 'RESIZE'}">checked</c:if>/> <spring:message code="olc.title.cartridge.bkgimg.imagetype.resize"/></label>
				<label style="font-weight: normal; margin-left:10px;"><input type="radio" style="border:0" name="bkgImgType" value="TILE" id="imgTypeT" <c:if test="${vo.bkgImgType eq 'TILE'}">checked</c:if>/> <spring:message code="olc.title.cartridge.bkgimg.imagetype.tile"/></label>
			</td>
		</tr>
	</table>

	<div class="text-right" style="margin-top:10px;">
		<c:if test="${gubun eq 'A'}">
			<button class="btn btn-primary btn-sm" onclick="addCartrg()" ><spring:message code="button.add"/></button>
		</c:if>
		<c:if test="${gubun eq 'E'}">
			<button class="btn btn-primary btn-sm" onclick="editCartrgDesign()" ><spring:message code="button.add"/></button>
		</c:if>
		<button class="btn btn-default btn-sm" onclick="parent.modalBoxClose()" ><spring:message code="button.close"/></button>
	</div>
	<input type="submit" value="submit" style="display:none" />
	</div>

	<div id="bkgImgfDiv">
	</div>

	</form>
<script type="text/javascript">
	var thumbFile;
	var olcCtgrTree = null;
	var shareCtgrTreeC = null;
	var shareCtgrTreeK = null;
	var ItemDTO = {"curPage":1};

	var topLogoFile;

	$(document).ready(function() {

		// 이미지 파일 첨부 바인딩 및 초기화
		topLogoFile = new $M.JqueryFileUpload({
							"varName"			: "topLogoFile",
							"files" 			: $.parseJSON('${olcCartrgVO.topLogoImgJson}'),
							"uploaderId"		: "toplogouploader",
							"fileListId"		: "toplogofiles",
							"progressId"		: "toplogoprogress",
							"maxcount"			: 1,
							"previewImage"		: true,
							"uploadSetting"	: {
								'formData'		: { 'repository': 'OLC_LESN_CARTRG',
													'organization' : "${USER_ORGCD}",
													'type'		: 'toplogo' }
							}
						});
		<c:if test="${olcCartrgVO.bkgImgSn ne '' || olcCartrgVO.bkgImgSn ne '0'}">
		$("#bkgimgArea").html(
				'<div id="">'
				+'<a href="javascript:fileDown(\'${olcCartrgVO.bkgImgSn }\');" style="cursor:pointer;" title="Download: image">'
				+'<img src="/app/file/thumb/${olcCartrgVO.bkgImgSn }">'
				+'</a>'
				+'<a class="btnRemoveFile" id="btnRemoveFile_${olcCartrgVO.bkgImgSn }" onclick="delBkgImg();" href="#_none">'
				+'<img src="/img/framework/icon/icon_delete.gif" alt="Delete File"></a></div>'
		);
		</c:if>

		$('#olcNameColor,#listBgColor,#contentBgColor,#listFontColor').ColorPicker({
			onSubmit: function(hsb, hex, rgb, el) {
				$(el).val(hex);
				$(el).ColorPickerHide();
				$(el).css("background-color","#"+$(el).val());
			},
			onBeforeShow: function () {
				$(this).ColorPickerSetColor(this.value);
			}
		})
		.bind('keyup', function(){
			$(this).ColorPickerSetColor(this.value);
		});
		<c:if test="${olcCartrgVO.olcNameColor ne null && olcCartrgVO.olcNameColor ne '' }">
		$('#olcNameColor').css("background-color","#${olcCartrgVO.olcNameColor}");
		</c:if>
		<c:if test="${olcCartrgVO.listBgColor ne null && olcCartrgVO.listBgColor ne '' }">
		$('#listBgColor').css("background-color","#${olcCartrgVO.listBgColor}");
		</c:if>
		<c:if test="${olcCartrgVO.contentBgColor ne null && olcCartrgVO.contentBgColor ne '' }">
		$('#contentBgColor').css("background-color","#${olcCartrgVO.contentBgColor}");
		</c:if>
		<c:if test="${olcCartrgVO.listFontColor ne null && olcCartrgVO.listFontColor ne '' }">
		$('#listFontColor').css("background-color","#${olcCartrgVO.listFontColor}");
		</c:if>
	});

	function uploderclick(str) {
		$("#"+str).click();
	}
	function clickDropdown() {
		$("#ctgrDrop").toggle();
	}

	function setSelectNode(data) {
		var id = data.rslt.obj.attr("id");
		var name = data.rslt.obj.attr("title");
		//-- form에 적용
		$("#ctgrCd").val(id);
		$("#ctgrNm").val(name);
		$("#ctgrDrop").hide();
	}

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		if(!validate(document.olcCartrgForm)) return;
		var _topLogoImg = topLogoFile.getFileSnAll();
		//var _bkgImg = bkgImgFile.getFileSnAll();
		var bkgImg = $("#bkgImgVal").val();
		if(bkgImg != ""){
			if(!$("#imgTypeR").is(":checked") && !$("#imgTypeT").is(":checked") ){
				alert('<spring:message code="olc.message.cartredge.alert.bkgimg.imagetype"/>');
				return;
			}
		}
		$(':input:hidden[name=topLogoImgSn]').val(_topLogoImg);
		$(':input:hidden[name=bkgImgSn]').val(bkgImg);

		$('#olcCartrgForm').attr("action" , "/mng/olc/cartrg/" + cmd);
		$('#olcCartrgForm').ajaxSubmit(processCallback);

	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		alert(resultDTO.message);

		if(resultDTO.result >= 0) {
			// 정상 처리
			parent.listPageing(1);
			parent.modalBoxClose();
		} else {
			// 비정상 처리
		}
	}

	/**
	 * 과정 분류 수정
	 */
	function editCartrgDesign() {

		process("editCartrgDesign");	// cmd
	}

	function listBkgImg() {
		$('#cartrgDiv').hide();
		$('#bkgImgfDiv').load(
			cUrl("/mng/olc/cartrg/editCartrgDesignBkgImgForm")
		);
		$('#bkgImgfDiv').show();
	}

	function changeType(){
		var selType = $(':radio[name="selType"]:checked').val();
		if(selType=="selImg"){
			$(':radio[name="bkgImg"]').attr("disabled",false);
			$("#uploadFile").attr("disabled",true);
		} else if(selType=="uploadImg"){
			$(':radio[name="bkgImg"]').attr("disabled",true);
			$("#uploadFile").attr("disabled",false);
		}
	}

	function addBkgImg(){
		var _bkgImg = bkgImgFile.getFileSnAll();
		var _bkgImgName =  bkgImgFile.getFileName();
		var _bkgImgSize =  bkgImgFile.getFileSize();
		var selType = $(':radio[name="selType"]:checked').val();
		var selImg = "";
		if(selType=="selImg"){
			selImg = $(':radio[name="bkgImg"]:checked').val();
			$("#bkgImgVal").val(selImg);
			var imgTitle = $(':radio[name="bkgImg"]:checked').next('label:first').html()
			$("#bkgimgArea").html(
					'<div id="">'
					+'<a href="javascript:fileDown(\''+$("#bkgImgVal").val()+'\');" style="cursor:pointer;" title="Download: '+imgTitle+'.jpg">'
					+'<img src="/app/file/thumb/'+$("#bkgImgVal").val()+'">'
					+'</a>'
					+'<a class="btnRemoveFile" id="btnRemoveFile_'+$("#bkgImgVal").val()+'" onclick="delBkgImg();" href="#_none">'
					+'<img src="/img/framework/icon/icon_delete.gif" alt="Delete File"></a></div>'
			);
			$(':input:hidden[name=bkgImgSelType]').val("S");

			if(!$('input:radio[name=bkgImg]').is(':checked')){
				alert("<spring:message code="olc.message.design.alert.selectfile"/>");
				return;
			}

		} else if(selType=="uploadImg"){
			$("#bkgImgVal").val(_bkgImg);
			$("#bkgimgArea").html(
					'<div id="">'
					+'<a href="javascript:fileDown(\''+$("#bkgImgVal").val()+'\');" style="cursor:pointer;" title="Download: '+_bkgImgName+'.jpg">'
					+'<img src="/app/file/thumb/'+$("#bkgImgVal").val()+'">'
					+'</a>'
					+'<a class="btnRemoveFile" id="btnRemoveFile_'+$("#bkgImgVal").val()+'" onclick="bkgImgFile.remove(\''+$("#bkgImgVal").val()+'\');delBkgImg();" onkeydown="if($M.Check.Event.isEnter(event)){bkgImgFile.remove(\''+$("#bkgImgVal").val()+'\');}" href="#_none">'
					+'<img src="/img/framework/icon/icon_delete.gif" alt="Delete File"></a></div>'
			);
			$(':input:hidden[name=bkgImgSelType]').val("U");
			if(bkgImgFile.getFileSnAll() == ""){
				alert("<spring:message code="olc.message.design.alert.uploadfile"/>");
				return;
			}
		}
		$('#cartrgDiv').show();
		$('#bkgImgfDiv').hide();
	}


	function closeBkgImg(){
		$('#cartrgDiv').show();
		$('#bkgImgfDiv').hide();
	}
	function delBkgImg(){
		$("#bkgimgArea").html(
				'<a href="javascript:listBkgImg();" class="btn btn-primary btn-xs">'
				+'	<spring:message code="button.select.file"/>'
				+'</a>'
		);
		$("#bkgImgVal").val("");
	}

</script>
</mhtml:frm_body>
</mhtml:mng_html>