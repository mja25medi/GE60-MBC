<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="olcCartrgVO" value="${vo}"/>
<mhtml:mng_html>
<mhtml:mng_head titleName="${MENUNAME}">
	<mhtml:head_module colorpicker="y" fileupload="y" />
</mhtml:mng_head>

<mhtml:frm_body>
	<table summary='<spring:message code="course.title.category.manage"/>' class="table table-bordered">
		<tr>
			<td style="border-right-color: #fff;">
				<spring:message code="olc.title.contents.select.file"/>
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
							<img src="/app/file/thumb/${item.mainImgFileSn}" style="max-width:100%;max-height:220px;"/>
						</c:if>
						</div>
						<div class="caption">
							<input type="radio" value="${item.mainImgFileSn}" name="bkgImg" id="bkgImg_${item.mainImgFileSn}">
							<label for="bkgImg_${item.mainImgFileSn}">${fn:substring(item.bkgrImgNm,0, 10)}<c:if test="${fn:length(item.bkgrImgNm) > 10 }">...</c:if></label>
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
			</td>
		</tr>
		<tr>
			<td style="border-right-color: #fff;">
				<spring:message code="olc.title.contents.select.file"/>
			</td>
			<td>
				<div style="text-align: right">
				<input type="radio" name="selType" value="uploadImg" onchange="changeType()">
				</div>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<div class="upload">
					<div class="upload_inbox">
						<a href="javascript:uploderclick('bkgimguploader');" class="btn btn-primary btn-xs"><spring:message code="button.select.file"/></a>
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
		<button class="btn btn-default btn-sm" onclick="closeBkgImg()" ><spring:message code="button.close"/></button>
	</div>
<script type="text/javascript">

	var bkgImgFile;

	$(document).ready(function() {
		bkgImgFile = new $M.JqueryFileUpload({
							"varName"			: "bkgImgFile",
							"files" 			: $.parseJSON('${vo.bkgImgJson}'),
							"uploaderId"		: "bkgimguploader",
							"fileListId"		: "bkgimgfiles",
							"progressId"		: "bkgimgprogress",
							"maxcount"			: 1,
							"previewImage"		: true,
							"uploadSetting"	: {
								'formData'		: { 'repository': 'OLC_LESN_CARTRG',
													'organization' : "${USER_ORGCD}",
													'type'		: 'bkgimg' }
							}
						});

	});


</script>
</mhtml:frm_body>
</mhtml:mng_html>