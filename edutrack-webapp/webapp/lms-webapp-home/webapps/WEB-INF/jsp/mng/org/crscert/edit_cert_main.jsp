<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<section class="content">
	<div class="row" id="content">
		<div class="box">
			<div class="box-body">
						<div class="col-lg-12">
							<ul class="nav nav-tabs" id="tabMenu">
								<li><a href="/mng/org/orgInfo/editInfoMain"><spring:message code="org.title.orginfo.defaultinfo.manage"/></a></li>
					<%-- 		<li><a href="//mng/org/userInfoCfg/editCfgMain"><spring:message code="org.title.orginfo.orgdata.manage"/></a></li>
						  		<li><a href="/mng/org/orgInfo/editTemplateMain"><spring:message code="org.title.orginfo.template.manage"/></a></li> --%>
						  		<li><a href="/mng/org/orgInfo/editDesignMain"><spring:message code="org.title.orginfo.design.manage"/></a></li>
						  		<%-- <li class="active"><a href="/mng/org/crsCert/editCertMain"><spring:message code="org.title.crscert.manage"/></a></li> --%>
							</ul>
						</div>
						<div class="col-lg-12" style="margin-top:10px;">
							<form name="orgCrsCertForm" id="orgCrsCertForm" onsubmit="return false" method="POST">
							<input type="hidden" name="orgCd" id="orgCd" value="${vo.orgCd}" />
							<input type="hidden" name="imgFileSn" id="imgFileSn" value="${vo.imgFileSn}"/>
							<input type="hidden" name="fakeFileSn" id="fakeFileSn" value="|${vo.imgFileSn}"/>
							<div id="writeArea">
								<table class="table table-bordered wordbreak">
									<colgroup>
										<col style="width:20%;"/>
										<col style="width:30%;"/>
										<col style="width:20%;"/>
										<col style="width:30%;"/>
									</colgroup>
									<tr>
										<th scope="row"><spring:message code="org.title.crscert.prn.direction"/></th>
										<td>
											<label>
												<input type="radio" name="prnDirec" value="HOR" <c:if test="${vo.prnDirec eq 'HOR' }">checked</c:if> /> <spring:message code="org.title.crscert.prn.direction.horizontal"/>
											</label>
											<label style="margin-left:20px;">
												<input type="radio" name="prnDirec" value="VER" <c:if test="${vo.prnDirec eq 'VER' }">checked</c:if> /> <spring:message code="org.title.crscert.prn.direction.vertical"/>
											</label>
										</td>
										<th scope="row"><spring:message code="org.title.crscert.cpltno.position"/></th>
										<td>
											<span class="spanCm"><spring:message code="course.title.reshbank.item.view.w"/> : </span>&nbsp;
											<input type="text" name="cpltnoX" id="cpltnoX" maxlength="4" required="required" title="<spring:message code="org.title.crscert.cpltno.position.x"/>" class="form-control input-sm" style="width:60px;text-align:right;float:left;margin-left:5px;" value="${vo.cpltnoX}" onkeyup="isChkNumber(this)"/>
											<span class="spanCm">&nbsp;px</span>
					
											<span class="spanCm" style="margin-left:20px;"><spring:message code="course.title.reshbank.item.view.h"/> : </span>&nbsp;
											<input type="text" name="cpltnoY" id="cpltnoY" maxlength="4" required="required" title="<spring:message code="org.title.crscert.cpltno.position.y"/>" class="form-control input-sm" style="width:60px;text-align:right;float:left;margin-left:5px;" value="${vo.cpltnoY}" onkeyup="isChkNumber(this)"/>
											<span class="spanCm">&nbsp;px</span>
										</td>
									</tr>
									<tr>
										<th scope="row"><spring:message code="org.title.crscert.crsnm.position"/></th>
										<td>
											<span class="spanCm"><spring:message code="course.title.reshbank.item.view.w"/> : </span>&nbsp;
											<input type="text" name="crsNmX" id="crsNmX" maxlength="4" required="required" title="<spring:message code="org.title.crscert.crsnm.position.x"/>" class="form-control input-sm" style="width:60px;text-align:right;float:left;margin-left:5px;" value="${vo.crsNmX}" onkeyup="isChkNumber(this)"/>
											<span class="spanCm">&nbsp;px</span>
					
											<span class="spanCm" style="margin-left:20px;"><spring:message code="course.title.reshbank.item.view.h"/> : </span>&nbsp;
											<input type="text" name="crsNmY" id="crsNmY" maxlength="4" required="required" title="<spring:message code="org.title.crscert.crsnm.position.y"/>" class="form-control input-sm" style="width:60px;text-align:right;float:left;margin-left:5px;" value="${vo.crsNmY}" onkeyup="isChkNumber(this)"/>
											<span class="spanCm">&nbsp;px</span>
										</td>
										<th scope="row"><spring:message code="org.title.crscert.prnday.position"/></th>
										<td>
											<span class="spanCm"><spring:message code="course.title.reshbank.item.view.w"/> : </span>&nbsp;
											<input type="text" name="prndayX" id="prndayX" maxlength="4" required="required" title="<spring:message code="org.title.crscert.prnday.position.x"/>" class="form-control input-sm" style="width:60px;text-align:right;float:left;margin-left:5px;" value="${vo.prndayX}" onkeyup="isChkNumber(this)"/>
											<span class="spanCm">&nbsp;px</span>
					
											<span class="spanCm" style="margin-left:20px;"><spring:message code="course.title.reshbank.item.view.h"/> : </span>&nbsp;
											<input type="text" name="prndayY" id="prndayY" maxlength="4" required="required" title="<spring:message code="org.title.crscert.prnday.position.y"/>" class="form-control input-sm" style="width:60px;text-align:right;float:left;margin-left:5px;" value="${vo.prndayY}" onkeyup="isChkNumber(this)"/>
											<span class="spanCm">&nbsp;px</span>
										</td>
									</tr>
									<tr>
										<th scope="row"><spring:message code="org.title.crscert.image"/></th>
										<td colspan="3">
											<div class="col-md-6 col-sm-6" style="margin-top: 10px;">
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
											</div>
											<div class="col-md-6 col-sm-6" style="margin-top:10px;" >
												<p><spring:message code="org.title.crscert.position.info"/></p>
												<div class="text-center"><img alt="sample" src="/img/Certificates.png" ></div>
											</div>
										</td>
									</tr>
								</table>
							</div>
							</form>
						</div>
			
						<div class="text-center" style="margin-top:10px;">
						<c:if test="${gubun eq 'A' }">
							<button class="btn btn-primary btn-sm" onclick="addCert()" ><spring:message code="button.add"/></button>
						</c:if>
						<c:if test="${gubun eq 'E' }">
							<button class="btn btn-primary btn-sm" onclick="editCert()" ><spring:message code="button.add"/></button>
						</c:if>
							<button class="btn btn-info btn-sm" onclick="previewCert()" ><spring:message code="common.title.preview"/></button>
						</div>
			</div>
		</div>
	</div>
</section>
		


<script type="text/javascript">
	//페이지 초기화
	var imgFile;
	var modalBox = null;

	$(document).ready(function() {

		modalBox = new $M.ModalDialog({
			"modalid" : "modal1"
		});

		// 이미지 파일 첨부 바인딩 및 초기화
		imgFile = new $M.JqueryFileUpload({
						"varName"			: "imgFile",
						"files" 			: $.parseJSON('${vo.imgFileJson}'),
						"uploaderId"		: "uploader",
						"fileListId"		: "files",
						"progressId"		: "progress",
						"maxcount"			: 1,
						"previewImage"		: true,
						"uploadSetting"	: {
							'formData'		: { 'repository': 'CERTIMG',
												'organization' : "${vo.orgCd}",
												'type'		: 'image' }
						}
					});
		$(window).bind("load resize", function() {
	    	resizeSubContents("writeArea", 60);
	    });
	});

	function uploderclick(str) {
		$("#"+str).click();
	}
	
	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		if(!validate(document.orgCrsCertForm)) return;
		$(':input:hidden[name=imgFileSn]').val(imgFile.getFileSnAll());
		$('#orgCrsCertForm').attr("action", "/mng/org/crsCert/" + cmd);
		$('#orgCrsCertForm').attr("target","");
		$('#orgCrsCertForm').ajaxSubmit(processCallback);
	}

	function addCert() {
		process("add");
	}

	function editCert() {
		process("edit");
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultVO) {
		alert(resultVO.message);
		if(resultVO.result >= 0) { //success
		} else { //failed
		}
	}

	function previewCert() {
		if(!validate(document.orgCrsCertForm)) return;
		var prnType = $(':input:radio[name=prnDirec]:checked').val();
		$(':input:hidden[name=imgFileSn]').val(imgFile.getFileSnAll());
		if(isEmpty($(':input:hidden[name=imgFileSn]').val())) {
			alert("<spring:message code="common.message.alert.input.image"/>");
		} else {
			if(browserType == 'NE') {
				var addContent  = "<iframe id='certificateFrame' name='certificateFrame' width='100%' height='100%' "
					+ "frameborder='0' scrolling='auto' src='about:blank'/>"
				modalBox.clear();
				modalBox.addContents(addContent);
				modalBox.resize(1100, 700);
				modalBox.setTitle("<spring:message code="org.title.crscert.manage"/>");
				modalBox.show();
			} else {
				var addContent  = "<iframe id='certificateFrame' name='certificateFrame' style='visibility:none; display: none;' src='about:blank'/>";
				$("body").append(addContent);
			}
			$('#orgCrsCertForm').attr("target","certificateFrame");
			$('#orgCrsCertForm').attr("action", "/mng/org/crsCert/preview");
			$('#orgCrsCertForm')[0].submit();
			
		}
	}
	
	function closePreview() {
		$("#writeArea").show();
		$("#previewArea").hide();
	}
	
</script>
