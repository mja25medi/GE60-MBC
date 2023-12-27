<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="gubun" value="${gubun}"/>
<c:set var="clibOlcCntsVO" value="${clibOlcCntsVO}"/>
<c:set var="curPage" value="${clibOlcCntsVO.curPage}"/>

	<form id="clibOlcCntsForm" name="clibOlcCntsForm" onsubmit="return false">
	<input type="hidden" name="cntsCd" />
	<input type="hidden" name="logoImgSn"/>
	<input type="hidden" name="bkgrImgSn" id="bkgrImgSn"/>
	<input type="hidden" name="bkgrImgUldDiv" id="bkgrImgUldDiv"/>
	<ul class="nav nav-tabs" style="width:100%">
		<li><a href="javascript:cntsEdit('${clibOlcCntsVO.cntsCd}');"><spring:message code="library.title.contents.olc.tab.info"/></a></li>
		<li class="active"><a href="#"><spring:message code="library.title.contents.olc.tab.design"/></a></li>
	</ul>
	<table summary='<spring:message code="library.title.contents.manage"/>' class="table table-striped table-bordered">
		<colgroup>
			<col style="width:15%"/>
			<col style="width:35%"/>
			<col style="width:15%"/>
			<col style="width:35%"/>
		</colgroup>
		<tr>
			<th scope="row"><spring:message code="library.title.contents.category"/></th>
			<td colspan="3">${clibOlcCntsVO.ctgrNm}</td>
		</tr>
		<tr>
			<th scope="row"><spring:message code="library.title.contents.name"/></th>
			<td colspan="3">${clibOlcCntsVO.cntsNm}</td>
		</tr>
		<tr>
			<th scope="row"><label for="cntsTitleColor"><spring:message code="library.title.contents.olc.name.color.font"/></label></th>
			<td>
				<input type="text" maxlength="20" style="width:100px;" dispName="<spring:message code="library.title.contents.olc.name.color.font"/>" name="cntsTitleColor" isNull="Y" lenCheck="20" class="form-control input-sm" id="cntsTitleColor"  />
			</td>
			<th scope="row"><label for="cntsTableBkgrColor"><spring:message code="library.title.contents.olc.order.color.background"/></label></th>
			<td>
				<input type="text" maxlength="20" style="width:100px;" dispName="<spring:message code="library.title.contents.olc.order.color.background"/>" name="cntsTableBkgrColor" isNull="Y" lenCheck="20" class="form-control input-sm" id="cntsTableBkgrColor"/>
			</td>
		</tr>
		<tr>
			<th scope="row"><label for="cntsTableFontColor"><spring:message code="library.title.contents.olc.order.color.font"/></label></th>
			<td colspan="3">
				<input type="text" maxlength="20" style="width:100px;" dispName="<spring:message code="olc.title.cartridge.list.fontcolor"/>" name="cntsTableFontColor" isNull="Y" lenCheck="20" class="form-control input-sm" id="cntsTableFontColor"/>
			</td>
		</tr>
		<tr>
			<th scope="row"><spring:message code="library.title.contents.olc.order.location"/></th>
			<td>
				<label><input type="radio" name="cntsTablePosCd" value="LEFT"/> <spring:message code="common.title.left"/></label>
				<label style="margin-left:20px;"><input type="radio" name="cntsTablePosCd" value="RIGHT"/> <spring:message code="common.title.right"/></label>
			</td>
			<th scope="row"><spring:message code="library.title.contents.olc.navi.locaton"/></th>
			<td>
				<label><input type="radio" name="pageNoPosCd" value="LEFT"/> <spring:message code="common.title.left"/></label>
				<label style="margin-left:20px;"><input type="radio" name="pageNoPosCd" value="CENTER"/> <spring:message code="common.title.center"/></label>
				<label style="margin-left:20px;"><input type="radio" name="pageNoPosCd" value="RIGHT"/> <spring:message code="common.title.right"/></label>
			</td>
		</tr>
		<tr>
			<th scope="row"><spring:message code="library.title.contents.olc.image.logo.top"/></th>
			<td colspan="3">
				<p><spring:message code="org.message.imginfo.size.main"  arguments="255|60" argumentSeparator="|"/></p>
				<div class="upload">
					<div class="upload_inbox">
						<a href="javascript:uploderclick('logouploader');" class="btn btn-primary btn-xs"><spring:message code="button.select.file"/></a>
						<input type="file" name="logouploader" id="logouploader"  style="display:none" accept="image/*" />
						<div id="logoprogress" class="progress">
		    				<div class="progress-bar progress-bar-success"></div>
						</div>
					</div>
					<div id="logofiles" class="multi_inbox"></div>
				</div>
			</td>
		</tr>
		<tr>
			<th scope="row"><spring:message code="library.title.contents.olc.image.background"/></th>
			<td colspan="3">
				<div id="bkgimgArea" class="files">
					<a href="javascript:listBkgImg();" class="btn btn-primary btn-xs">
						<spring:message code="button.select.file"/>
					</a>
				</div>
			</td>
		</tr>
		<tr>
			<th scope="row"><spring:message code="library.title.contents.olc.image.background.type"/></th>
			<td colspan="3">
				<label style="font-weight: normal;"><input type="radio" style="border:0" name="bkgrImgTypeCd" value="RESIZE" id="imgTypeR"/> <spring:message code="library.title.contents.olc.image.background.type.resize"/></label>
				<label style="font-weight: normal; margin-left:10px;"><input type="radio" style="border:0" name="bkgrImgTypeCd" value="TILE" id="imgTypeT"/> <spring:message code="library.title.contents.olc.image.background.type.tile"/></label>
			</td>
		</tr>
	</table>
	<div class="text-right" style="margin-top:10px;margin-bottom:20px;">
		<button class="btn btn-primary btn-sm" onclick="editCnts()" ><spring:message code="button.add"/></button>
		<button class="btn btn-warning btn-sm" onclick="delCnts()" ><spring:message code="button.delete"/></button>
		<button class="btn btn-default btn-sm" onclick="closeWriteArea()" ><spring:message code="button.close"/></button>
	</div>
	<input type="submit" value="submit" style="display:none" />
	</form>


	<script type="text/javascript">
		var logoFile;
		var colorObj = {"cntsTitleColor":"${clibOlcCntsVO.cntsTitleColor}","cntsTableBkgrColor":"${clibOlcCntsVO.cntsTableBkgrColor}","cntsTableFontColor":"${clibOlcCntsVO.cntsTableFontColor}"};

		$(document).ready(function() {
			// 이미지 파일 첨부 바인딩 및 초기화
			logoFile = new $M.JqueryFileUpload({
					"varName"			: "logoFile",
					"files" 			: $.parseJSON('${clibOlcCntsVO.logoImgJson}'),
					"uploaderId"		: "logouploader",
					"fileListId"		: "logofiles",
					"progressId"		: "logoprogress",
					"maxcount"			: 1,
					"previewImage"		: true,
					"uploadSetting"		: {
						'formData'		: { 'repository': 'OLC_CNTS',
			                                'organization' : "${USER_ORGCD}",
											'type'		: 'logo' }
					}
				});

			bkgrImgSet('${clibOlcCntsVO.bkgrImgSn}','${clibOlcCntsVO.bkgrImgUldDiv}');

			$('#cntsTitleColor,#cntsTableBkgrColor,#cntsTableFontColor').ColorPicker({
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

			if(colorObj.cntsTitleColor != "")
				$('#cntsTitleColor').css("background-color","#"+colorObj.cntsTitleColor);
			if(colorObj.cntsTableBkgrColor !="")
				$('#cntsTableBkgrColor').css("background-color","#"+colorObj.cntsTableBkgrColor);
			if(colorObj.cntsTableFontColor !="")
				$('#cntsTableFontColor').css("background-color","#"+colorObj.cntsTableFontColor);
<c:if test="${empty  clibOlcCntsVO.cntsTablePosCd}">
			$("input:radio[name='clibOlcCntsVO\\.cntsTablePosCd']:radio[value='LEFT']").attr("checked",true);
</c:if>
<c:if test="${empty  clibOlcCntsVO.pageNoPosCd}">
			$("input:radio[name='clibOlcCntsVO\\.pageNoPosCd']:radio[value='RIGHT']").attr("checked",true);
</c:if>
<c:if test="${empty  clibOlcCntsVO.bkgrImgTypeCd}">
			$("input:radio[name='clibOlcCntsVO\\.bkgrImgTypeCd']:radio[value='RESIZE']").attr("checked",true);
</c:if>
		});

		function uploderclick(str) {
			$("#"+str).click();
		}

		function bkgrImgSet(imgSn, uldType) {
			if(imgSn == 0){
				return;
			}
			if(imgSn != '' || imgSn > 0) {
				$("#bkgrImgSn").val(imgSn);
				$("#bkgrImgUldDiv").val(uldType);
				var innerHtml  = '<div id="">';
				    innerHtml += '<a href="javascript:fileDown('+imgSn+');" style="cursor:pointer;" title="Download: image">';
				    innerHtml += '<img src="/app/file/thumb/'+imgSn+'">';
				    innerHtml += '</a>';
				    innerHtml += '<a class="btnRemoveFile" id="btnRemoveFile_'+imgSn+'" onclick="delBkgImg();" href="#_none">';
				    innerHtml += '<img src="/img/framework/icon/icon_delete.gif" alt="Delete File">';
				    innerHtml += '</a>';
				    innerHtml += '</div>';
				$("#bkgimgArea").html(innerHtml);
			}
		}

		/**
		 * 백그라운드 이미지 선택
		 */
		function listBkgImg() {
	        var addContent = "<iframe id='backgroundImageFrame' name='backgroundImageFrame' width='100%' height='100%' "
	            + "frameborder='0' scrolling='auto' src='<c:url value="/mng/library/clibOlcCnts/searchBkgrImg"/>"
	            + "?clibOlcCntsVO.cntsCd=${clibOlcCntsVO.cntsCd}'/>";
			modalBox.clear();
			modalBox.addContents(addContent);
			modalBox.resize(820, 820);
			modalBox.setTitle("<spring:message code="library.title.contents.olc.image.background"/>");
			modalBox.show();
		}

		/**
		 * 서브밋 처리
		 */
		function process(cmd) {
			if(!validate(document.clibOlcCntsForm)) return;
			var _logoFile = logoFile.getFileSnAll();
			$(':input:hidden[name=clibOlcCntsVO\\.logoImgSn]').val(_logoFile);

			// 이부분을 처리 하지 않으면 multiPartFile 호출 됨.
			$('#logouploader').attr('disabled',true);

			$('#clibOlcCntsForm').attr("action", "/mng/library/clibOlcCnts/" + cmd);
			$('#clibOlcCntsForm').ajaxSubmit(processCallback);
		}

		/**
		 * 처리 결과 표시 콜백
		 */
		function processCallback(resultDTO) {
			alert(resultDTO.message);
			if(resultDTO.result >= 0) {
				// 정상 처리
				closeWriteArea();
				listPageing('${curPage}');
			} else {
				// 비정상 처리
			}
		}

		function editCnts() {
			process("editDesign");
		}

		function delCnts() {
			process("remove");
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
