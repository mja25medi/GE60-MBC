<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>

<section class="content">
<div class="box">
<div class="box-body">
	<div class="row" id="content">
		<div class="col-lg-12">
			<ul class="nav nav-tabs" id="tabMenu">
				<li class="active"><a href="/adm/org/orginfo/editInfoFormMain?orgCd=${vo.orgCd }"><spring:message code="org.title.orginfo.defaultinfo.manage"/></a></li>
		  		<li><a href="/adm/org/config/editCfgFormMain?orgCd=${vo.orgCd }"><spring:message code="org.title.orginfo.orgdata.manage"/></a></li>
		  		<li><a href="/adm/org/orginfo/editTemplateFormMain?orgCd=${vo.orgCd }"><spring:message code="org.title.orginfo.template.manage"/></a></li>
		  		<li><a href="/adm/org/orginfo/editDesignFormMain?orgCd=${vo.orgCd }"><spring:message code="org.title.orginfo.design.manage"/></a></li>
		  		<li><a href="/adm/org/crscert/editCertFormMain?orgCd=${vo.orgCd}"><spring:message code="org.title.crscert.manage"/></a></li>
		  		<li><a href="/adm/org/menu/main?orgCd=${vo.orgCd}"><spring:message code="org.title.orginfo.menu.manage"/></a></li>
			</ul>
		  	<div class="pull-right" style="margin-top:-35px;">
				<a class="btn btn-default btn-sm" href="javascript:closeWrite()"><spring:message code="button.list"/></a>
			</div>			
		</div>
		<form name="orgInfoForm" id="orgInfoForm" onsubmit="return false" method="POST">			
		<input type="hidden" name="orgCd" id="orgCd" value="${vo.orgCd}"/>
		<div class="col-lg-12" style="margin-top:10px;">
			<table summary="<spring:message code="org.title.orginfo.defaultinfo"/>" class="table table-bordered wordbreak">
				<colgroup>
					<col width="20%"/>
					<col width="80%"/>
				</colgroup>
				<c:if test="${LANG_MULTIUSE eq 'Y'}">
				<tr>
					<th scope="row"><label for="dfltLangCd"><spring:message code="org.title.orginfo.defaultlang"/></label></th>
					<td>
						<select name="dfltLangCd" id="dfltLangCd">
						<c:forEach var="lang" items="${langList}">
							<option value="${lang.codeCd}" <c:if test="${lang.codeCd eq vo.dfltLangCd}">selected="selected"</c:if>>${lang.codeNm}</option>
						</c:forEach>
						</select>
					</td>
				</tr>
				</c:if>
				<c:if test="${LANG_MULTIUSE ne 'Y'}">
				<input type="hidden" name="dfltLangCd" id="dfltLangCd" value="${vo.dfltLangCd}" />
				</c:if>
				<tr>
					<th scope="row"><spring:message code="org.title.orginfo.memberidtype"/></th>
					<td>
						<label>
							<input type="radio" name="mbrIdType" value="CHAR" <c:if test="${vo.mbrIdType eq 'CHAR'}">checked</c:if>/> <spring:message code="org.title.orginfo.memberidtype.char"/>
						</label>
						<label style="margin-left:20px;">
							<input type="radio" name="mbrIdType" value="EMAIL" <c:if test="${vo.mbrIdType eq 'EMAIL'}">checked</c:if>/> <spring:message code="org.title.orginfo.memberidtype.email"/>
						</label>
					</td>
				</tr>

				<tr>
					<th scope="row"><spring:message code="org.title.orginfo.usejoin"/></th>
					<td>
						<label>
							<input type="radio" name="mbrAplcUseYn" value="Y" <c:if test="${vo.mbrAplcUseYn eq 'Y'}">checked</c:if>/> <spring:message code="common.title.status.useyn_y"/>
						</label>
						<label style="margin-left:20px;">
							<input type="radio" name="mbrAplcUseYn" value="N" <c:if test="${vo.mbrAplcUseYn eq 'N'}">checked</c:if>/> <spring:message code="common.title.status.useyn_n"/>
						</label>
					</td>
				</tr>

				<tr>
		            <th scope="row"><label for="bankNm"><spring:message code="org.title.orginfo.bankinfo"/></label></th>
		            <td>
		            	<table>
		            		<tr>
		            			<td><spring:message code="org.title.orginfo.bankname"/></td>
		            			<td style="padding-left:10px;">
		            				<input type="text" name="bankNm" id="bankNm" maxlength="100" title="<spring:message code="org.title.orginfo.bankname"/>" class="form-control input-sm" style="width:200px;" value="${vo.bankNm}"/> 
		            			</td>
		            		</tr>
		            		<tr>
		            			<td><spring:message code="org.title.orginfo.acntnum"/></td>
		            			<td style="padding-left:10px;">
		            				<input type="text" name="acntNo" id="acntNo" maxlength="100" title="<spring:message code="org.title.orginfo.acntnum"/>" class="form-control input-sm" style="width:200px;" value="${vo.acntNo}"/>
		            			</td>
		            		</tr>
		            		<tr>
		            			<td><spring:message code="org.title.orginfo.acntname"/></td>
		            			<td style="padding-left:10px;">
		            				<input type="text" name="acntNm" id="acntNm" maxlength="100" title="<spring:message code="org.title.orginfo.acntname"/>" class="form-control input-sm" style="width:200px;" value="${vo.acntNm}"/>
		            			</td>
		            		</tr>
		            	</table>
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
		            <th scope="row"><label for="emailAddr"><spring:message code="org.title.orginfo.connectinfo"/></label></th>
		            <td>
		            	<input type="text" name="chrgPrsnInfo" id="chrgPrsnInfo" maxlength="100" title="<spring:message code="org.title.orginfo.emailname"/>" class="form-control input-sm" style="width:200px;" value="${vo.chrgPrsnInfo}"/>
					</td>
		        </tr>
			</table>
			<div class="text-right">
				<a href="javascript:reflectOrgInfo()" class="btn btn-primary btn-sm"><spring:message code="button.reflect"/> </a>
			</div>
	</form>
		</div>
	</div>
</div>
</div>
</section>

<script type="text/javascript">
	var modalBox = null;
	// 페이지 초기화
	$(document).ready(function() {
		modalBox = new $M.ModalDialog({
			"modalid" : "modal1"
		});
	});

	function reflectOrgInfo() {
		$('#orgInfoForm').attr("action","/adm/org/orginfo/editInfo.do");
		$('#orgInfoForm').ajaxSubmit(processCallback);
	}
	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultVO) {
		alert(resultVO.message);
	}

	function closeWrite() {
		document.location.href = cUrl("/adm/org/orginfo/main");
	}
</script>
