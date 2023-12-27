<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>

<section class="content">
<div class="box">
<div class="box-body">
	<div class="row" id="content">
		<div class="col-lg-12">
			<ul class="nav nav-tabs" id="tabMenu">
				<li><a href="/adm/org/orginfo/editInfoFormMain?orgCd=${vo.orgCd }"><spring:message code="org.title.orginfo.defaultinfo.manage"/></a></li>
		  		<li><a href="/adm/org/config/editCfgFormMain?orgCd=${vo.orgCd }"><spring:message code="org.title.orginfo.orgdata.manage"/></a></li>
		  		<li><a href="/adm/org/orginfo/editTemplateFormMain?orgCd=${vo.orgCd }"><spring:message code="org.title.orginfo.template.manage"/></a></li>
		  		<li class="active"><a href="/adm/org/orginfo/editDesignFormMain?orgCd=${vo.orgCd }"><spring:message code="org.title.orginfo.design.manage"/></a></li>
		  		<li><a href="/adm/org/crscert/editCertFormMain?orgCd=${vo.orgCd}"><spring:message code="org.title.crscert.manage"/></a></li>
		  		<li><a href="/adm/org/menu/main?orgCd=${vo.orgCd}"><spring:message code="org.title.orginfo.menu.manage"/></a></li>
			</ul>
		  	<div class="pull-right" style="margin-top:-35px;">
				<a class="btn btn-default btn-sm" href="javascript:closeWrite()"><spring:message code="button.list"/></a>
			</div>			
		</div>	
		<div class="col-lg-12" style="margin-top:10px;">
			<form name="orgInfoForm" id="orgInfoForm" onsubmit="return false" method="POST">
			<input type="hidden" name="orgCd" id="orgCd" value="${vo.orgCd}"/>
			<input type="hidden" name="topLogoFileSn" id="topLogoFileSn" value="${vo.topLogoFileSn}"/>
			<input type="hidden" name="subLogo1FileSn" id="subLogo1FileSn" value="${vo.subLogo1FileSn}"/>
			<input type="hidden" name="subLogo2FileSn" id="subLogo2FileSn" value="${vo.subLogo2FileSn}"/>
			<input type="hidden" name="admLogoFileSn" id="admLogoFileSn" value="${vo.admLogoFileSn}"/>
			<input type="hidden" name="colorTplCd"  id="colorTplCd" value="${vo.colorTplCd}"/>
			<input type="hidden" name="layoutTplCd" id="layoutTplCd" value="${vo.layoutTplCd}"/>
			<table summary="<spring:message code="org.title.orginfo.defaultinfo"/>" class="table table-bordered wordbreak">
				<colgroup>
					<col width="20%"/>
					<col width="80%"/>
				</colgroup>
				<tr>
					<th scope="row">
						<spring:message code="org.title.orginfo.toplogoimg"/><br/>
					</th>
					<td>
						<c:if test="${vo.colorTplCd eq '001'}"><p><spring:message code="org.message.imginfo.size.main"  arguments="158|46"  argumentSeparator="|"/></p></c:if>
						<c:if test="${vo.colorTplCd eq '002'}"><p><spring:message code="org.message.imginfo.size.main"  arguments="144|42"  argumentSeparator="|"/></p></c:if>
						<c:if test="${vo.colorTplCd eq '003'}"><p><spring:message code="org.message.imginfo.size.main"  arguments="175|51"  argumentSeparator="|"/></p></c:if>
						<c:if test="${vo.colorTplCd eq '004'}"><p><spring:message code="org.message.imginfo.size.main"  arguments="220|51"  argumentSeparator="|"/></p></c:if>
						<c:if test="${vo.colorTplCd eq '005'}"><p><spring:message code="org.message.imginfo.size.main"  arguments="220|51"  argumentSeparator="|"/></p></c:if>
						<c:if test="${vo.colorTplCd eq '006'}"><p><spring:message code="org.message.imginfo.size.main"  arguments="140|40"  argumentSeparator="|"/></p></c:if>						
						<div class="upload">
							<div class="upload_inbox">
								<a href="javascript:uploderclick('homeuploader');" class="btn btn-primary btn-xs"><spring:message code="button.select.file"/></a>
								<input type="file" class="file" name="homeuploader" id="homeuploader" title="<spring:message code="org.title.orginfo.toplogoimg"/>" multiple style="display:none" accept="image/*" /><%-- 첨부파일 버튼 --%>
								<div id="homeprogress" class="progress">
				    				<div class="progress-bar progress-bar-success"></div>
								</div>
							</div>
							<div id="homefiles" class="multi_inbox"></div>
						</div>					
					</td>
				</tr>
				<tr class="sel_tpl">
					<th scope="row">
						<spring:message code="org.title.orginfo.sublogoimg"/><br/>
					</th>
					<td>
						<p><spring:message code="org.message.imginfo.size.sublogoimg"/></p>
						<div class="upload">
							<div class="upload_inbox">
								<a href="javascript:uploderclick('sub1uploader');" class="btn btn-primary btn-xs"><spring:message code="button.select.file"/></a>
								<input type="file" class="file" name="sub1uploader" id="sub1uploader" title="<spring:message code="org.message.imginfo.size.sublogoimg"/>" multiple style="display:none" accept="image/*" /><%-- 첨부파일 버튼 --%>
								<div id="sub1progress" class="progress">
				    				<div class="progress-bar progress-bar-success"></div>
								</div>
							</div>
							<div id="sub1files" class="multi_inbox"></div>
						</div>
						<div class="upload" style="margin-top:10px;">
							<div class="upload_inbox">
								<a href="javascript:uploderclick('sub2uploader');" class="btn btn-primary btn-xs"><spring:message code="button.select.file"/></a>
								<input type="file" class="file" name="sub2uploader" id="sub2uploader" title="<spring:message code="org.message.imginfo.size.sublogoimg"/>" multiple style="display:none" accept="image/*" /><%-- 첨부파일 버튼 --%>
								<div id="sub2progress" class="progress">
				    				<div class="progress-bar progress-bar-success"></div>
								</div>
							</div>
							<div id="sub2files" class="multi_inbox"></div>
						</div>
					</td>
				</tr>
				<tr>
		            <th scope="row"><label for="wwwFooter"><spring:message code="org.title.orginfo.wwwfooter"/></label></th>
		            <td>
		            	<textarea name="wwwFooter" id="wwwFooter" maxlength="2000" title="<spring:message code="org.title.orginfo.wwwfooter"/>" class="form-control input-sm">${vo.wwwFooter}</textarea>
		            </td>
		        </tr>

				<tr>
					<th scope="row">
						<spring:message code="org.title.orginfo.admlogoimg"/><br/>
					</th>
					<td>
						<p><spring:message code="org.message.imginfo.size.main"  arguments="220|64"  argumentSeparator="|"/></p>
						<div class="upload">
							<div class="upload_inbox">
								<a href="javascript:uploderclick('adminuploader');" class="btn btn-primary btn-xs"><spring:message code="button.select.file"/></a>
								<input type="file" class="file" name="adminuploader" id="adminuploader" title="<spring:message code="org.message.imginfo.size.sublogoimg"/>" multiple style="display:none" accept="image/*" /><%-- 첨부파일 버튼 --%>
								<div id="adminprogress" class="progress">
				    				<div class="progress-bar progress-bar-success"></div>
								</div>
							</div>
							<div id="adminfiles" class="multi_inbox"></div>
						</div>					
					</td>
				</tr>
			</table>
			<div class="text-right">
				<a href="javascript:reflectOrgInfo()" class="btn btn-primary btn-sm"><spring:message code="button.reflect"/> </a>
			</div>
			</form>
		</div>
	
		<div class="col-lg-12" style="margin-top:20px;">
			<h4>
				<i class="fa fa-th-large"></i>
				<spring:message code="org.title.orginfo.mainimg"/>
			</h4>
			<div class="row">
				<div class="col-lg-12">
					<div style="float:left;line-height:35px;">
						<spring:message code="org.message.imginfo.alert.mainimg.cnt" />
					</div>
					<div style="float:right">
						<a href="javascript:imgWrite('MAINIMG')" class="btn btn-primary btn-sm"><spring:message code="button.write"/> </a>
					</div>
				</div>
			</div>
			<div id="mainImgList" style="margin-top:5px;"></div>
		</div>

		<div class="col-lg-12" style="margin-top:20px;">
			<h4 >
				<i class="fa fa-th-large"></i>
				<spring:message code="org.title.orginfo.subimg"/>
			</h4>
			<div class="row">
				<div class="col-lg-12">
					<div style="float:left;line-height:35px;">
						<spring:message code="org.message.imginfo.alert.subimg.cnt" />
					</div>
					<div style="float:right">
						<a href="javascript:imgWrite('SUBIMG')" class="btn btn-primary btn-sm"><spring:message code="button.write"/> </a>
						<%-- <a href="javascript:imgSort('SUBIMG')"  class="btn btn-info btn-sm"><spring:message code="button.sort" /></a> --%>
					</div>
				</div>
			</div>
			<div id="subImgList" style="margin-top:5px;"></div>
		</div>

		<div class="col-lg-12" style="margin-top:20px;">
			<h4>
				<i class="fa fa-th-large"></i>
				<spring:message code="org.title.orginfo.lecimg"/>
			</h4>
			<div class="row">
				<div class="col-lg-12">
					<div style="float:left;line-height:35px;">
						<spring:message code="org.message.imginfo.alert.lecimg.cnt" />
					</div>
					<div style="float:right">
						<a href="javascript:imgWrite('LECIMG')" class="btn btn-primary btn-sm"><spring:message code="button.write"/> </a>
						<%-- <a href="javascript:imgSort('LECIMG')"  class="btn btn-info btn-sm"><spring:message code="button.sort" /></a> --%>
					</div>
				</div>
			</div>
			<div id="lecImgList" style="margin-top:5px;"></div>
		</div>
	</div>
</div>
</div>
</section>

<script type="text/javascript">

	var modalBox = null;
	var topLogoFile;
	var subLogo1File;
	var subLogo2File;
	var admLogoFile;

	var mainImgCnt = 0;
	var subImgCnt = 0;
	var lecImgCnt = 0;
	var mainBbsCnt = 0;

	// 페이지 초기화
	$(document).ready(function() {
		modalBox = new $M.ModalDialog({
			"modalid" : "modal1"
		});

		// 이미지 파일 첨부 바인딩 및 초기화
		topLogoFile = new $M.JqueryFileUpload({
							"varName"			: "topLogoFile",
							"files" 			: $.parseJSON('${vo.topLogoFileJson}'),
							"uploaderId"		: "homeuploader",
							"fileListId"		: "homefiles",
							"progressId"		: "homeprogress",
							"maxcount"			: 1,
							"previewImage"		: true,
							"uploadSetting"	: {
								'formData'		: { 'repository': 'ORGINFO',
													'organization' : "${vo.orgCd}",
													'type'		: 'toplogo' }
							}
						});

		subLogo1File = new $M.JqueryFileUpload({
							"varName"			: "subLogo1File",
							"files" 			: $.parseJSON('${vo.subLogo1FileJson}'),
							"uploaderId"		: "sub1uploader",
							"fileListId"		: "sub1files",
							"progressId"		: "sub1progress",
							"maxcount"			: 1,
							"previewImage"		: true,
							"uploadSetting"	: {
								'formData'		: { 'repository': 'ORGINFO',
													'organization' : "${vo.orgCd}",
													'type'		: 'sublogo1' }
							}
						});

		subLogo2File = new $M.JqueryFileUpload({
							"varName"			: "subLogo2File",
							"files" 			: $.parseJSON('${vo.subLogo2FileJson}'),
							"uploaderId"		: "sub2uploader",
							"fileListId"		: "sub2files",
							"progressId"		: "sub2progress",
							"maxcount"			: 1,
							"previewImage"		: true,
							"uploadSetting"	: {
								'formData'		: { 'repository': 'ORGINFO',
													'organization' : "${vo.orgCd}",
													'type'		: 'sublogo2' }
							}
						});

		admLogoFile = new $M.JqueryFileUpload({
						"varName"			: "admLogoFile",
						"files" 			: $.parseJSON('${vo.admLogoFileJson}'),
						"uploaderId"		: "adminuploader",
						"fileListId"		: "adminfiles",
						"progressId"		: "adminprogress",
						"maxcount"			: 1,
						"previewImage"		: true,
						"uploadSetting"	: {
							'formData'		: { 'repository': 'ORGINFO',
												'organization' : "${vo.orgCd}",
												'type'		: 'admlogo' }
						}
					});

		 mainImgList();
		 subImgList();
		 lecImgList();

		 checkTemplate();
	});

	function uploderclick(str) {
		$("#"+str).click();
	}

	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}

	function checkTemplate() {
		var template = $('#colorTplCd').val();
		if(template == '006') $(".sel_tpl").show();
		else $(".sel_tpl").hide();
	}

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		if(!validate(document.orgInfoForm)) return;
		var template = $(':input:radio[name=colorTplCd]:checked').val();

		$(':input:hidden[name=topLogoFileSn]').val(topLogoFile.getFileSnAll());
		if(template == '006') {
			$(':input:hidden[name=subLogo1FileSn]').val(subLogo1File.getFileSnAll());
			$(':input:hidden[name=subLogo2FileSn]').val(subLogo2File.getFileSnAll());

		} else {
			$(':input:hidden[name=subLogo1FileSn]').val("");
			$(':input:hidden[name=subLogo2FileSn]').val("");
		}
		$(':input:hidden[name=admLogoFileSn]').val(admLogoFile.getFileSnAll());

		$('#orgInfoForm').attr("action",cmd);
		$('#orgInfoForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultVO) {
		alert(resultVO.message);
	}

	/**
	 * 사이트 정보 수정
	 */
	function reflectOrgInfo() {
		process("/adm/org/orginfo/editDesign");	// cmd
	}

	/**
	 *  메인 이미지 목록 호출
	 */
	function mainImgList() {
		$('#mainImgList').load(
				cUrl("/adm/org/imginfo/list"),
				{ "orgCd":"${vo.orgCd}", "imgTypeCd":"MAINIMG" }
			);
	}

	/**
	 *  서브 이미지 목록 호출
	 */
	function subImgList() {
		$('#subImgList').load(
				cUrl("/adm/org/imginfo/list"),
				{ "orgCd":"${vo.orgCd}", "imgTypeCd":"SUBIMG" }
			);
	}

	/**
	 *  강의실 이미지 목록 호출
	 */
	function lecImgList() {
		$('#lecImgList').load(
				cUrl("/adm/org/imginfo/list"),
				{ "orgCd":"${vo.orgCd}", "imgTypeCd":"LECIMG" }
			);
	}

	/**
	 * 이미지 등록 폼
	 */
	function imgWrite(imgTypeCd) {
		ItemVO.workType = imgTypeCd;
		if(imgTypeCd == 'MAINIMG') {
			$("#mainImgList").children("ul").children("li").each(function() {
				if($(this).attr("class") == "list-item") mainImgCnt++;
			});
			if(mainImgCnt >= 4) {
				alert('<spring:message code="org.message.imginfo.alert.mainimg.cnt"/>');
				return;
			}
		} else if(imgTypeCd == 'SUBIMG') {
			$("#subImgList").children("ul").children("li").each(function() {
				if($(this).attr("class") == "list-item") subImgCnt++;
			});
			if(subImgCnt >= 1) {
				alert('<spring:message code="org.message.imginfo.alert.subimg.cnt"/>');
				return;
			}
		} else if(imgTypeCd == 'LECIMG') {
			$("#lecImgList").children("ul").children("li").each(function() {
				if($(this).attr("class") == "list-item") lecImgCnt++;
			});			
			if(lecImgCnt >= 1) {
				alert('<spring:message code="org.message.imginfo.alert.lecimg.cnt"/>');
				return;
			}
		}
		var colorTplCd = $(':input:radio[name=colorTplCd]:checked').val();
		var addContent  = "<iframe id='addImgInfoFrame' name='addImgInfoFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/adm/org/imginfo/addForm"/>"
			+ "?orgCd=${vo.orgCd}&amp;imgTypeCd="+imgTypeCd+"&amp;colorTplCd="+colorTplCd+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(1100, 500);
		modalBox.setTitle("<spring:message code="org.title.imginfo.write"/>");
		modalBox.show();
	}

	/**
	 * 이미지 등록 폼
	 */
	function imgEdit(imgTypeCd, imgSn) {
		ItemVO.workType = imgTypeCd;
		var colorTplCd = $(':input:radio[name=colorTplCd]:checked').val();
		var addContent  = "<iframe id='addImgInfoFrame' name='addImgInfoFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/adm/org/imginfo/editForm"/>"
			+ "?orgCd=${vo.orgCd}&amp;imgTypeCd="+imgTypeCd+"&amp;imgSn="+imgSn+"&amp;colorTplCd="+colorTplCd+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(1100, 500);
		modalBox.setTitle("<spring:message code="org.title.imginfo.edit"/>");
		modalBox.show();
	}

	/**
	 * 이미지 미리보기
	 */
	function preview(fileSn) {
		if(fileSn != "0"){
			var addContent  = "<div style='width:100%;height:100%;overflow-y:auto;'><img src='/app/file/view2/"+fileSn+"' style='width:1050px;'></div>";
			modalBox.clear();
			modalBox.addContents(addContent);
			modalBox.resize(1100, 700);
			modalBox.setTitle("Preview Image");
			modalBox.show();
		}
	}

	function closeWrite() {
		document.location.href = cUrl("/adm/org/orginfo/main")
	}
	
	function callImgList() {
		if(ItemVO.workType == 'MAINIMG') mainImgList();
		if(ItemVO.workType == 'SUBIMG') subImgList();
		if(ItemVO.workType == 'LECIMG') lecImgList();
	}
</script>
