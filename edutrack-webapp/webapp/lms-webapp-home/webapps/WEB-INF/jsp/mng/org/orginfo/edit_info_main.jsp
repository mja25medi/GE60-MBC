<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<section class="content">
	<div class="row" id="content">
		<div class="box">
			<div class="box-body">
						<div class="col-lg-12">
							<ul class="nav nav-tabs" id="tabMenu">
								<li class="active"><a href="/mng/org/orgInfo/editInfoMain"><spring:message code="org.title.orginfo.defaultinfo.manage"/></a></li>
					<%-- 		<li><a href="//mng/org/userInfoCfg/editCfgMain"><spring:message code="org.title.orginfo.orgdata.manage"/></a></li>
						  		<li><a href="/mng/org/orgInfo/editTemplateMain"><spring:message code="org.title.orginfo.template.manage"/></a></li> --%>								
						  		<li><a href="/mng/org/orgInfo/editDesignMain"><spring:message code="org.title.orginfo.design.manage"/></a></li>
						  		<%-- <li><a href="/mng/org/crsCert/editCertMain"><spring:message code="org.title.crscert.manage"/></a></li> --%>
							</ul>
						</div>
						<form name="orgInfoForm" id="orgInfoForm" onsubmit="return false" method="POST">
						<input type="hidden" name="orgCd" id="orgCd" value="${vo.orgCd}"/>
						<input type="hidden" name="contractFileSn" id="contractFileSn" value="${vo.contractFileSn}"/>
						<div class="col-lg-12" style="margin-top:10px;">
							<table summary="<spring:message code="org.title.orginfo.defaultinfo"/>" class="table table-bordered wordbreak">
								<colgroup>
									<col width="20%"/>
									<col width="80%"/>
								</colgroup>
								<input type="hidden" name="dfltLangCd" id="dfltLangCd" value="${vo.dfltLangCd}" />
								<tr style="display: none;">
									<th scope="row"><spring:message code="org.title.orginfo.memberidtype"/></th>
									<td>
										<label>
											<input type="radio" name="mbrIdType" value="CHAR" <c:if test="${vo.mbrIdType eq 'CHAR' or empty vo.mbrIdType }">checked</c:if>/> <spring:message code="org.title.orginfo.memberidtype.char"/>
										</label>
										<label style="margin-left:20px;">
											<input type="radio" name="mbrIdType" value="EMAIL" <c:if test="${vo.mbrIdType eq 'EMAIL'}">checked</c:if>/> <spring:message code="org.title.orginfo.memberidtype.email"/>
										</label>
									</td>
								</tr>
				
								<tr style="display: none;">
									<th scope="row"><spring:message code="org.title.orginfo.usejoin"/></th>
									<td>
										<label>
											<input type="radio" name="mbrAplcUseYn" value="Y" <c:if test="${vo.mbrAplcUseYn eq 'Y' or empty vo.mbrAplcUseYn }">checked</c:if>/> <spring:message code="common.title.status.useyn_y"/>
										</label>
										<label style="margin-left:20px;">
											<input type="radio" name="mbrAplcUseYn" value="N" <c:if test="${vo.mbrAplcUseYn eq 'N'}">checked</c:if>/> <spring:message code="common.title.status.useyn_n"/>
										</label>
									</td>
								</tr>
								<tr>
						            <th scope="row"><label for="emailAddr"><spring:message code="org.title.orginfo.emailinfo"/></label></th>
						            <td>
						            	<table>
						            		<tr>
						            			<td><spring:message code="org.title.orginfo.emailaddr"/></td>
						            			<td style="padding-left:10px;">
						            				<input type="text" name="emailAddr" id="emailAddr" maxlength="100" title="<spring:message code="org.title.orginfo.emailaddr"/>" class="form-control input-sm" style="width:200px;" value="${vo.emailAddr}"/>
						            			</td>
						            		</tr>
						            		<tr>
						            			<td><spring:message code="org.title.orginfo.phoneno"/></td>
						            			<td style="padding-left:10px;">
						            				<input type="text" name="rprstPhoneNo" id="rprstPhoneNo" maxlength="100" title="<spring:message code="org.title.orginfo.phoneno"/>" class="form-control input-sm" style="width:200px;" value="${vo.rprstPhoneNo}"/>
						            			</td>
						            		</tr>
						            		<tr>
						            			<td><spring:message code="org.title.orginfo.emailname"/></td>
						            			<td style="padding-left:10px;">
						            				<input type="text" name="emailNm" id="emailNm" maxlength="100" title="<spring:message code="org.title.orginfo.emailname"/>" class="form-control input-sm" style="width:200px;" value="${vo.emailNm}"/>
						            			</td>
						            		</tr>
						            	</table>
									</td>
						        </tr>
								<tr>
						            <th scope="row"><label for="callCenter"><spring:message code="org.title.orginfo.connectinfo"/></label></th>
						            <td>
						            	<input type="text" name="chrgPrsnInfo" id="chrgPrsnInfo" maxlength="100" title="<spring:message code="org.title.orginfo.connectinfo"/>" class="form-control input-sm" style="width:200px;" value="${vo.chrgPrsnInfo}"/>
									</td>
						        </tr>
<%-- 						        <tr>
				        			<th scope="row">위탁계약서</th>
									<td>
										<div class="upload">
											<div class="upload_inbox">
												<a href="javascript:uploderclick('uploader');" class="btn btn-primary btn-xs"><spring:message code="button.select.file"/></a>
												<input type="file" name="uploader" id="uploader"  style="display:none"/>
												<div id="progress" class="progress">
								    				<div class="progress-bar progress-bar-success"></div>
												</div>
											</div>
											<div id="files" class="multi_inbox"></div>
										</div>
									</td>		
						        </tr> --%>
				        
							</table>
							<div class="text-right">
								<a href="javascript:reflectOrgInfo()" class="btn btn-primary btn-sm"><spring:message code="button.reflect"/> </a>
							</div>
						</div>
					</form>

			</div>
		</div>
	</div>
</section>		

<script type="text/javascript">
	var modalBox = null;
	var contractFile;
	// 페이지 초기화
	$(document).ready(function() {
		modalBox = new $M.ModalDialog({
			"modalid" : "modal1"
		});
		
		contractFile = new $M.JqueryFileUpload({
			"varName"			: "contractFile",
			"files" 			: $.parseJSON('${vo.contractFileJson}'),
			"uploaderId"		: "uploader",
			"fileListId"		: "files",
			"progressId"		: "progress",
			"maxcount"			: 1,
			"previewImage"		: true,
			"uploadSetting"	: {
				'formData'		: { 'repository': 'ORGINFO',
									'organization' : "${vo.orgCd}",
									'type' : 'contract'
									}
			}
		});
	});
	
	function uploderclick(str) {
		$("#"+str).click();
	}

	function reflectOrgInfo() {
		$(':input:hidden[name=contractFileSn]').val(contractFile.getFileSnAll());
		
		$('#orgInfoForm').attr("action" , "/mng/org/orgInfo/editInfo");
		$('#orgInfoForm').ajaxSubmit(processCallback);
	}
	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultVO) {
		alert(resultVO.message);
	}

	function closeWrite() {
		document.location.href = cUrl("/mng/org/orginfo/main");
	}
</script>
