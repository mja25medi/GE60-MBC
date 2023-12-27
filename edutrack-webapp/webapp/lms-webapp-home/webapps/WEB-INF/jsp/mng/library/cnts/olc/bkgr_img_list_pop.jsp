<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="gubun" value="${gubun}"/>
<c:set var="clibOlcCntsVO" value="${clibOlcCntsVO}"/>
<mhtml:mng_html>
<mhtml:mng_head titleName="${MENUNAME}">
	<mhtml:head_module fileupload="y" paging="y"/>
</mhtml:mng_head>

<mhtml:frm_body>
	<table summary='<spring:message code="library.title.contents.olc.image.background"/>' class="table table-bordered">
		<tr>
			<td style="border-right-color: #fff;">
				<spring:message code="library.title.contents.olc.image.background.select"/>
			</td>
			<td>
				<div style="text-align: right">
				<input type="radio" name="selType" value="selImg" onchange="changeType()" checked>
				</div>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<c:forEach items="${bkgImgList}" var="item">
				<div style="float: left;">
					<div class="thumbnail" style="margin: 6px; width: 175px;">
						<div>
						<c:if test="${item.mainImgFileSn > 0}">
							<img src="/app/file/thumb/${item.mainImgFileSn}" style="max-width:100%;height:120px;"/>
						</c:if>
						</div>
						<div class="caption">
							<input type="radio" value="${item.mainImgFileSn}" name="bkgImg" id="bkgImg_${item.mainImgFileSn}">
							<label for="bkgImg_${item.mainImgFileSn}">${fn:substring(item.bkgrImgNm,0, 8)}<c:if test="${fn:length(item.bkgrImgNm) > 8 }">...</c:if></label>
						</div>
					</div>
				</div>
				</c:forEach>
				<c:if test="${empty bkgImgList}">
				<div class="col-md-12">
					<div class="well">
						<spring:message code="common.message.nodata"/>
					</div>
				</div>
				</c:if>
				<div style="clear:both"></div>
				<meditag:paging pageInfo="${pageInfo}" funcName="listBkimg"/>
			</td>
		</tr>
		<tr>
			<td style="border-right-color: #fff;">
				<spring:message code="library.title.contents.olc.image.background.add"/>
			</td>
			<td>
				<div style="text-align: right">
				<input type="radio" name="selType" value="uploadImg" onchange="changeType()">
				</div>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<p><spring:message code="org.message.imginfo.size.main"  arguments="1088|675" argumentSeparator="|"/></p>
				<div class="upload">
					<div class="upload_inbox">
						<a href="javascript:uploderclick('bkgimguploader');" class="btn btn-primary btn-xs" id="uploadFile"><spring:message code="button.select.file"/></a>
						<input type="file" name="bkgimguploader" id="bkgimguploader"  style="display:none" accept="image/*" />
						<div id="bkgimgprogress" class="progress">
		    				<div class="progress-bar progress-bar-success"></div>
						</div>
					</div>
					<div id="bkgimgfiles" class="multi_inbox"></div>
				</div>
			</td>
		</tr>
	</table>

	<div class="text-right" style="margin-top:10px;">
		<button class="btn btn-primary btn-sm" onclick="addBkgImg()" ><spring:message code="button.add"/></button>
		<button class="btn btn-default btn-sm" onclick="parent.modalBoxClose();" ><spring:message code="button.close"/></button>
	</div>
<script type="text/javascript">

	var bkgImgFile;

	$(document).ready(function() {
		bkgImgFile = new $M.JqueryFileUpload({
					"varName"			: "bkgImgFile",
					"files" 			: $.parseJSON('${clibOlcCntsVO.bkgrImgJson}'),
					"uploaderId"		: "bkgimguploader",
					"fileListId"		: "bkgimgfiles",
					"progressId"		: "bkgimgprogress",
					"maxcount"			: 1,
					"previewImage"		: true,
					"uploadSetting"	: {
						'formData'		: { 'repository': 'OLC_CNTS',
											'organization' : "${USER_ORGCD}",
											'type'		: 'bkgr' }
					}
				});
		changeType();
	});

	function uploderclick(str) {
		$("#"+str).click();
	}

	function listBkimg (page) {
		var url = cUrl("/mng/library/clibOlcCnts/searchBkgrImg")+"?clibOlcCntsVO.cntsCd=${clibOlcCntsVO.cntsCd}&curPage="+page;
		$(location).attr('href', url);
	}

	/**
	 * 이미지 선택 변경시
	 */
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

	function addBkgImg() {
		var selType = $(':radio[name="selType"]:checked').val();
		if(selType=="uploadImg") {
			var _bkgImg = bkgImgFile.getFileSnAll();
			var _bkgImgName =  bkgImgFile.getFileName();
			var _bkgImgSize =  bkgImgFile.getFileSize();
			if(bkgImgFile.getFileSnAll() == "") {
				alert("<spring:message code="olc.message.design.alert.uploadfile"/>");
				return;
			}
			parent.bkgrImgSet(_bkgImg, 'U');
			parent.modalBoxClose();
		} else { //--selImg
			var selImg = $(':radio[name="bkgImg"]:checked').val();
			if(selImg == "") {
				alert("<spring:message code="olc.message.design.alert.selectfile"/>");
				return;
			}
			//$(this).parent().find("#bkgrImgSn").val(selImg, 'S');
			parent.bkgrImgSet(selImg, 'S');

			parent.modalBoxClose();
		}
	}

</script>
</mhtml:frm_body>
</mhtml:mng_html>