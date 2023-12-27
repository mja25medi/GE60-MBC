<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="gubun" value="${gubun}"/>
<c:set var="clibMediaCntsVO" value="${vo}"/>

	<script type="text/JavaScript" src="/js/jquery/jquery-fileupload/vendor/jquery.ui.widget.js"></script>
	<script type="text/JavaScript" src="/js/jquery/jquery-fileupload/jquery.iframe-transport.js"></script>
	<script type="text/JavaScript" src="/js/jquery/jquery-fileupload/jquery.fileupload.js"></script>
	<script type="text/JavaScript" src="/js/common_fileupload.js"></script>

	<div class="row" id="content">
		<div class="box" style="margin-bottom:0px"> 
		<div class="box-body">
		<div id="displayArea">
	<form id="clibMediaCntsForm" name="clibMediaCntsForm" onsubmit="return false">
	<input type="hidden" name="cntsCd" value="${vo.cntsCd }" />
	<input type="hidden" name="cntsOdr" value="${vo.cntsOdr }" />
	<input type="hidden" name="cntsType" value="${vo.cntsType }" />
	<input type="hidden" name="thumbFileSn" value="${vo.thumbFileSn }"/>
	<input type="hidden" name="playerDiv" id="playerDiv" value="${vo.playerDiv }"/>
	<input type="hidden" name="fileNm" id="fileNm" value="${vo.fileNm }"/>
	<input type="hidden" name="uldStsCd" id="uldStsCd" value="${vo.uldStsCd }"/>
	<input type="hidden" name="uldFileKey" id="uldFileKey" value="${vo.uldFileKey }"/>
	<h4 style="margin-left:10px">
		<c:if test ="${vo.cntsType eq 'MLINK'}">
			영상 링크 등록
		</c:if>
		<c:if test ="${vo.cntsType ne 'MLINK'}">
			${vo.cntsType} 콘텐츠 등록
		</c:if>
	</h4>
	<table summary='<spring:message code="course.title.category.manage"/>' class="table table-striped table-bordered">
		<colgroup>
			<col style="width:15%"/>
			<col style="width:25%"/>
			<col style="width:15%"/>
			<col style="width:45%"/>
		</colgroup>
		<tr>
			<th scope="row" rowspan="5">
				<spring:message code="library.title.contents.thumb.image"/><br/>
				<a href="javascript:uploderclick('thumbuploader');" class="btn btn-primary btn-xs"><spring:message code="button.select.file"/></a>
				<input type="file" name="thumbuploader" id="thumbuploader" title="<spring:message code="board.title.bbs.atcl.thumb"/>" multiple style="display:none" accept="image/*"  "/><%-- 첨부파일 버튼 --%>
			</th>
			<td rowspan="5">
				<div class="upload">
					<div class="upload_inbox">
						<div id="thumbprogress" class="progress">
		    				<div class="progress-bar progress-bar-success"></div>
						</div>
					</div>
					<div id="thumbfiles" class="multi_inbox"></div>
				</div>
				<p id="sizeInfo"><spring:message code="org.message.imginfo.size.main"  arguments="166|134" argumentSeparator="|"/></p>
			</td>
			<th scope="row"><span style="color: red;">* </span><label for="ctgrNm"><spring:message code="course.title.exambank.category"/></label></th>
			<td>
				<div class="input-group">
					<div class="input-group-btn btn-group">
						<button type="button" class="btn btn-default btn-sm dropdown-toggle" data-toggle="dropdown">
							<span class="caret"></span>
						</button>
						<ul class="dropdown-menu" role="menu" id="ctgrDrop" style="max-height:300px; min-width:400px; overflow:auto;">
						<c:if test="${empty ctgrList}">
							<li style="padding-left:0px;">* <spring:message code="library.message.ctgr.nodata"/> </li>
						</c:if>
						<c:if test="${not empty ctgrList}">
						<c:forEach var="item" items="${ctgrList}">
							<c:url var="typeImgPath" value="/img/framework/icon/icon_contents.gif" />
							<c:set var="typeImgName" value="contents" />
							<c:if test="${fn:length(item.subList) > 0}">
								<c:url var="typeImgPath" value="/img/framework/icon/icon_category.gif" />
								<c:set var="typeImgName" value="category" />
							</c:if>
							<li style="padding-left:0px;"><a href="javascript:setCtgr('${item.ctgrCd}','${item.ctgrNm }')"><img src="${typeImgPath}" alt="${typeImgName}"> ${item.ctgrNm}</a></li>
							<c:forEach var="item1" items="${item.subList}">
								<c:url var="typeImgPath" value="/img/framework/icon/icon_contents.gif" />
								<c:set var="typeImgName" value="contents" />
								<c:if test="${fn:length(item1.subList) > 0}">
									<c:url var="typeImgPath" value="/img/framework/icon/icon_category.gif" />
									<c:set var="typeImgName" value="category" />
								</c:if>
							<li style="padding-left:15px;"><a href="javascript:setCtgr('${item1.ctgrCd}','${item1.ctgrNm }')"><img src="${typeImgPath}" alt="${typeImgName}"> ${item1.ctgrNm}</a></li>
								<c:forEach var="item2" items="${item1.subList}">
									<c:url var="typeImgPath" value="/img/framework/icon/icon_contents.gif" />
									<c:set var="typeImgName" value="contents" />
									<c:if test="${fn:length(item2.subList) > 0}">
										<c:url var="typeImgPath" value="/img/framework/icon/icon_category.gif" />
										<c:set var="typeImgName" value="category" />
									</c:if>
							<li style="padding-left:30px;"><a href="javascript:setCtgr('${item2.ctgrCd}','${item2.ctgrNm }')"><img src="${typeImgPath}" alt="${typeImgName}"> ${item2.ctgrNm}</a></li>
									<c:forEach var="item3" items="${item2.subList}">
										<c:url var="typeImgPath" value="/img/framework/icon/icon_contents.gif" />
										<c:set var="typeImgName" value="contents" />
										<c:if test="${fn:length(item3.subList) > 0}">
											<c:url var="typeImgPath" value="/img/framework/icon/icon_category.gif" />
											<c:set var="typeImgName" value="category" />
										</c:if>
							<li style="padding-left:45px;"><a href="javascript:setCtgr('${item3.ctgrCd}','${item3.ctgrNm }')"><img src="${typeImgPath}" alt="${typeImgName}"> ${item3.ctgrNm}</a></li>
										<c:forEach var="item4" items="${item3.subList}">
											<c:url var="typeImgPath" value="/img/framework/icon/icon_contents.gif" />
											<c:set var="typeImgName" value="contents" />
											<c:if test="${fn:length(item4.subList) > 0}">
												<c:url var="typeImgPath" value="/img/framework/icon/icon_category.gif" />
												<c:set var="typeImgName" value="category" />
											</c:if>
							<li style="padding-left:60px;"><a href="javascript:setCtgr('${item4.ctgrCd}','${item4.ctgrNm }')"><img src="${typeImgPath}" alt="${typeImgName}"> ${item4.ctgrNm}</a></li>
										</c:forEach>
									</c:forEach>
								</c:forEach>
							</c:forEach>
						</c:forEach>
						</c:if>
						</ul>
					</div>
					<input type="text" name="ctgrNm" id="ctgrNm" class="form-control input-sm" style="background-color:#ffffff;" readonly="readonly" value="${clibMediaCntsVO.ctgrNm}"/>
					<input type="hidden" name="ctgrCd" id="ctgrCd" value="${vo.ctgrCd }"/>
				</div>
			</td>
		</tr>
		<tr>
			<th scope="row"><span style="color: red;">* </span><label for="cntsNm"><spring:message code="library.title.contents.name"/></label></th>
			<td>
				<input type="text" dispName="<spring:message code="library.title.contents.name"/>" maxlength="50" isNull="N" lenCheck="50" name="cntsNm" value="${vo.cntsNm }" class="form-control input-sm" id="cntsNm"/>
			</td>
		</tr>
		
		<c:choose>
			<c:when test="${vo.cntsType eq 'VOD' }">
				<tr>
					<input type="hidden" name="filePath" id="filePath" value="${vo.filePath }"/>
					<th scope="row"><span style="color: red;">* </span><label for="cntsTag">영상파일경로</label></th>
					<td>
						<div style="display:flex;">
							<c:if test="${empty clibMediaCntsVO.fileNm}">
									<a href="javascript:mediaFileUpload()" class="btn btn-primary btn-xs" id="media-file-upload-button"><spring:message code="library.title.contents.media.file.select"/></a>
							</c:if>
							<c:if test="${not empty clibMediaCntsVO.fileNm}">
									<div id="media-file-name">${clibMediaCntsVO.fileNm}</div>
							</c:if>
						</div>
					</td>
				</tr>			
			</c:when>
			<c:when test="${vo.cntsType eq 'CDN' }">
				<tr>
					<th scope="row"><span style="color: red;">* </span><label for="">CDN 파일</label></th>
					<td>
						<input type="text" class="form-control input-sm" name="filePath" id="filePath" value="${vo.filePath}"  dispName="<spring:message code="library.title.contents.name"/>" isNull="N" />
					</td>
				</tr>	
			</c:when>
			<c:otherwise>	<!-- 영상 링크  -->
				<tr>
					<th scope="row"><span style="color: red;">* </span><label for="">링크 경로</label></th>
					<td>
						<input type="text" class="form-control input-sm" name="filePath" id="filePath" value="${vo.filePath}"  dispName="<spring:message code="library.title.contents.name"/>" isNull="N" />
					</td>
				</tr>			
			</c:otherwise>
		</c:choose>
		
		<tr>
			<th scope="row"><span style="color: red;">* </span><label for="cntsTag"><spring:message code="common.title.tag"/></label></th>
			<td>
				<input type="text" dispName="<spring:message code="common.title.tag"/>" maxlength="50" isNull="N" lenCheck="50" name="cntsTag" value="${vo.cntsTag }" class="form-control input-sm" id="cntsTag"/>
			</td>
		</tr>
		<tr>
			<th scope="row"><spring:message code="common.title.useyn"/></th>
			<td>
				<label style="font-weight: normal;" ><input type="radio" style="border:0" name="useYn" value="Y" <c:if test="${vo.useYn eq 'Y'}">checked</c:if>/><spring:message code="common.title.useyn_y"/></label>
				<label style="font-weight: normal; margin-left:10px;"><input type="radio" style="border:0" name="useYn" value="N" <c:if test="${vo.useYn ne 'Y'}">checked</c:if>/><spring:message code="common.title.useyn_n"/></label>
			</td>
		</tr>
		<tr>
			<th scope="row"><label for="cntsCts"><spring:message code="library.title.contents.description"/></label></th>
			<td colspan="3">
				<textarea name="cntsCts" lenCheck="1000" dispName="<spring:message code="library.title.contents.description"/>" style="height:80px" id="crsDesc" class="form-control input-sm">${vo.cntsCts}</textarea>
			</td>
		</tr>
	</table>

	<div class="text-right" style="margin-top:10px;margin-bottom:20px;">
		<c:if test="${gubun eq 'A'}">
			<button class="btn btn-primary btn-sm" onclick="addCnts()" ><spring:message code="button.add"/></button>
		</c:if>
		<c:if test="${gubun eq 'E'}">
			<button class="btn btn-primary btn-sm" onclick="editCnts()" ><spring:message code="button.add"/></button>
			<button class="btn btn-warning btn-sm" onclick="delCnts()" ><spring:message code="button.delete"/></button>
		</c:if>
		<button class="btn btn-default btn-sm" onclick="closeWriteArea()" ><spring:message code="button.close"/></button>
	</div>
	<input type="submit" value="submit" style="display:none" />
	</form>
	</div>
	<div id="workArea" style="dispaly:none" class="col-md-12">

	</div>
	</div>
	</div>
	</div>

<script type="text/javascript">
	var thumbFile;
	var uploadFileInfo;
	$(document).ready(function() {
		thumbFile = new $M.JqueryFileUpload({
			"varName"			: "thumbFile",
			"files" 			: $.parseJSON('${vo.thumbFileJson}'),
			"uploaderId"		: "thumbuploader",
			"fileListId"		: "thumbfiles",
			"progressId"		: "thumbprogress",
			"maxcount"			: 1,
			"previewImage"		: true,
			"infoUse"		: true,
			"uploadSetting"		: {
				'formData'		: { 'repository': 'MEDIA_CNTS',
	                                'organization' : "${USER_ORGCD}",
									'type'		: 'thumb' }
			}
		});
	});

	function uploderclick(str) {
		$("#"+str).click();
	}

	function setCtgr(ctgrCd, ctgrNm) {
		$("#ctgrCd").val(ctgrCd);
		$("#ctgrNm").val(ctgrNm);
	}

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		if("" == $("#ctgrCd").val()){
			alert("<spring:message code="library.message.contents.category.select.category"/>");
			return;
		}
		if(!validate(document.clibMediaCntsForm)) return;

		if( '${vo.cntsType}' == 'VOD'){
			if($("#media-file-name").html() == ""){
				alert("<spring:message code="library.title.contents.alert.media.file"/>");
				return;
			}
		}
		var _thumbFile = thumbFile.getFileSnAll();
		$(':input:hidden[name=thumbFileSn]').val(_thumbFile);
		

		// 이부분을 처리 하지 않으면 multiPartFile 호출 됨.
		$('#uploadifyThumb').attr('disabled',true);
		$('#clibMediaCntsForm').attr("action","/mng/library/clibMediaCnts/" + cmd);
		$('#clibMediaCntsForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		alert(resultDTO.message);
		if(resultDTO.result >= 0) {
			// 정상 처리
			closeWriteArea();
			listPageing(1,'${vo.cntsType}');
		} else {
			// 비정상 처리
		}
	}

	function addCnts() {
		process("add");
	}

	function editCnts() {
		process("edit");
	}

	function delCnts() {
		if(confirm("<spring:message code="library.message.contents.confirm.delete"/>")){
			process("remove");
		}
	}

	function mediaFileUpload() {
		var cntsNm = $("#cntsNm").val();
		if(isEmpty(cntsNm)) {
			alert("<spring:message code="library.message.contents.alert.input.contents.name"/>");
			return;
		}
		var addContent  = "<iframe id='addFileUpload' name='addFileUpload' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/mng/library/clibMediaCnts/addUploadPop"/>"
			+ "'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(500, 300);
		modalBox.setTitle("<spring:message code="library.title.contents.media.file.upload"/>");
		modalBox.show();
	}

	/*
	function onComplateUpload(uploadFileKey, uploadFileName, ) {
		$("#uldFileKey").val(uploadFileKey);
		$("#fileNm").val(getFileName(uploadFileName))
		$("#media-file-name").html(getFileName(uploadFileName));
		$("#media-file-upload-button").hide();
		modalBoxClose();
	}
	*/

</script>
