<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>

	<form name="orgInfoForm" id="orgInfoForm" method="POST" onsubmit="return false;" class="form-inline">
	<input type="hidden" name="autoMakeYn" id="autoMakeYn" value="Y"/>
	<input type="hidden" name="topLogoFileSn" id="topLogoFileSn" value="${vo.topLogoFileSn}" />
	<input type="hidden" name="subLogo1FileSn" id="subLogo1FileSn" value="${vo.subLogo1FileSn}"/>
	<input type="hidden" name="subLogo2FileSn" id="subLogo2FileSn" value="${vo.subLogo2FileSn}"/>
	<input type="hidden" name="admLogoFileSn" id="admLogoFileSn" value="${vo.admLogoFileSn}"/>
	<table summary="<spring:message code="org.title.orginfo.manage"/>" class="table table-bordered">
		<colgroup>
			<col width="30%"/>
			<col width="70%"/>
		</colgroup>
		<tr height="35">
			<th class="top" scope="row"><label for="orgCd"><spring:message code="org.title.orginfo.orgcode"/></label></th>
			<td class="top">
				<c:if test="${gubun eq 'A'}">
				<div style="float:left">
					<input type="text" name="orgCd" id="orgCd" maxlength="10" required="required" title="<spring:message code="org.title.orginfo.orgcode"/>" onkeyup="isChkCharacter(this)" class="form-control input-sm"/>
				</div>
				<div style="float:left;margin-left:10px;">
					<label style="font-weight:normal;" ><input type="checkbox" id="checkAutoMakeYn" name="checkAutoMakeYn" onclick="autoMakeCd()" style="border:0" checked/><spring:message code="common.title.automake"/></label>
				</div>
				</c:if>
				<c:if test="${gubun eq 'E'}">
				<input type="hidden" name="orgCd" id="orgCd" value="${vo.orgCd}"/>
				${vo.orgCd}
				</c:if>
			</td>
		</tr>
		<tr>
			<th scope="row"><label for="orgNm"><spring:message code="org.title.orginfo.orgname"/></label></th>
			<td >
				<input type="text" name="orgNm" id="orgNm" maxlength="50" required="required" title="<spring:message code="org.title.orginfo.orgname"/>" class="form-control input-sm" value="${vo.orgNm}"/>
			</td>
		</tr>
		<tr>
			<th scope="row"><label for="domain"><spring:message code="org.title.orginfo.domain"/></label></th>
			<td>
				<c:if test="${gubun eq 'A' }">
				<div class="input-group" style="width:300px;">
					<span class="input-group-addon">http://</span>
					<input type="text" name="domainNm" id="domainNm" maxlength="50" required="required" title="<spring:message code="org.title.orginfo.domain"/>" class="form-control input-sm" value="${vo.domainNm}"/>
				</div>
				</c:if>
				<c:if test="${gubun eq 'E' }">
				http://${vo.rprstDomain}
				<input type="hidden" name="domainNm" id="domainNm" value="${vo.domainNm}"/>
				</c:if>
			</td>
		</tr>
		<c:if test="${LANG_MULTIUSE eq 'Y' }">
		<tr>
			<th scope="row"><label for="dfltLangCd"><spring:message code="org.title.orginfo.defaultlang"/></label></th>
			<td>
				<select name="dfltLangCd" id="dfltLangCd" class="form-control input-sm" style="width:300px;">
				<c:forEach var="lang" items="${langList}">
					<option value="${lang.codeCd}" <c:if test="${lang.codeCd eq vo.dfltLangCd}">selected</c:if>>${lang.codeNm}</option>
				</c:forEach>
				</select>
			</td>
		</tr>
		</c:if>
		<c:if test="${LANG_MULTIUSE ne 'Y' }">
		<input type="hidden" name="dfltLangCd" value="${dfltLangCd}" />
		</c:if>
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
		<%--
		<tr>
			<th scope="row"><label for="productCd"><spring:message code="org.title.orginfo.product"/></label></th>
			<td>
				<select name="productCd" id="productCd" class="form-control input-sm" style="width:300px;">
				<c:forEach var="product" items="${productList}">
					<option value="${product.codeCd}" <c:if test="${product.codeCd eq productCd}">selected</c:if>>${product.codeNm}</option>
				</c:forEach>
				</select>
			</td>
		</tr>
		--%>
		<tr>
			<th scope="row"><label for="startDate"><spring:message code="org.title.orginfo.servicedate"/></label></th>
			<td>
				<div class="input-group">
					<div class="input-group" style="float:left;width:140px;">
						<input type="text" name="startDttm" id="startDttm" maxlength="17" required="required" title="<spring:message code="org.title.orginfo.startdate"/>" class="form-control input-sm inputDate" value="${vo.startDttm }"/> 
						<span class="input-group-addon btn-sm" onclick="_clickCalendar('startDttm')" style="cursor:pointer">
							<i class="fa fa-calendar"></i>
						</span>
					</div>
					
					<div class="input-group text-center" style="float:left;padding-left:10px; padding-right:10px;"> ~ </div>
					<div class="input-group" style="float:left;width:140px">
						<input type="text" name="endDttm" id="endDttm" maxlength="17" required="required" title="<spring:message code="org.title.orginfo.enddate"/>" class="form-control input-sm inputDate" value="${vo.endDttm }"/>
						<span class="input-group-addon btn-sm" onclick="_clickCalendar('endDttm')" style="cursor:pointer">
							<i class="fa fa-calendar"></i>
						</span>
					</div>
				</div>
				<meditag:datepicker name1="startDttm" name2="endDttm" />
			</td>
		</tr>
		<tr>
			<th scope="row"><label for="limitNopCnt"><spring:message code="org.title.orginfo.limitcnt"/></label></th>
			<td>
				<div class="input-group" style="float:left;width:140px">
					<input type="text" name="limitNopCnt" id="limitNopCnt" maxlength="10" required="required" onkeyup="isChkNumber(this)" title="<spring:message code="org.title.orginfo.limitcnt"/>" class="inputNumber form-control input-sm" value="${vo.limitNopCnt}"/>
					<span class="input-group-addon"><spring:message code="common.title.cnt.user"/></span>
				</div>
			</td>
		</tr>
		<tr>
			<th scope="row"><spring:message code="org.title.orginfo.useyn.kollus"/></th>
			<td>
				<div class="form-group">
					<label style="font-weight: normal;" >
						<input type="radio" name="kollusUseYn" value="Y" onclick="checkKollus()" <c:if test="${vo.kollusUseYn eq 'Y' }">checked</c:if>/> <spring:message code="common.title.useyn_y"/>
					</label>
					<label style="font-weight: normal; margin-left:10px;">
						<input type="radio" name="kollusUseYn" value="N" onclick="checkKollus()" <c:if test="${vo.kollusUseYn eq 'N' }">checked</c:if>/> <spring:message code="common.title.useyn_n"/>
					</label>
				</div><br/>
				<div class="form-group" style="line-height:30px;">
					<label for="kollusKeyCd" style="min-width:130px;"><spring:message code="org.title.orginfo.userkey.kollus"/> </label>
					<input type="text" name="kollusKeyCd" id="kollusKeyCd" maxlength="20" title="<spring:message code="org.title.orginfo.userkey.kollus"/>" class="form-control input-sm" placeholder="<spring:message code="org.title.orginfo.userkey.kollus"/>" value="${vo.kollusKeyCd}" />
				</div><br/>
				<div class="form-group" style="line-height:30px;">
					<label for="kollusTokenCd" style="min-width:130px;"><spring:message code="org.title.orginfo.token.kollus"/> </label>
					<input type="text" name="kollusTokenCd" id="kollusTokenCd" maxlength="20" title="<spring:message code="org.title.orginfo.token.kollus"/>" class="form-control input-sm" placeholder="<spring:message code="org.title.orginfo.token.kollus"/>" value="${vo.kollusTokenCd}" />
				</div><br/>
				<div class="form-group" style="line-height:30px;">
					<label for="kollusCtgrCd" style="min-width:130px;"><spring:message code="org.title.orginfo.category.kollus"/> </label>
					<input type="text" name="kollusCtgrCd" id="kollusCtgrCd" maxlength="20" title="<spring:message code="org.title.orginfo.category.kollus"/>" class="form-control input-sm" placeholder="<spring:message code="org.title.orginfo.category.kollus"/>" value="${vo.kollusCtgrCd}" />
				</div>
			</td>
		</tr>
		<tr>
			<th scope="row"><spring:message code="common.title.useyn"/></th>
			<td>
				<label style="font-weight: normal;" >
					<input type="radio" name="useYn" value="Y" <c:if test="${vo.useYn eq 'Y' }">checked</c:if> /> <spring:message code="common.title.useyn_y"/>
				</label>
				<label style="font-weight: normal; margin-left:10px;">
					<input type="radio" name="useYn" value="N" <c:if test="${vo.useYn eq 'N' }">checked</c:if> /> <spring:message code="common.title.useyn_n"/>
				</label>
			</td>
		</tr>
		<tr>
			<th scope="row">산업인력공단(HRD) API 사용여부</th>
			<td>
				<label style="font-weight: normal;" >
					<input type="radio" name="hrdApiUseYn" value="Y" <c:if test="${vo.hrdApiUseYn eq 'Y' }">checked</c:if> /> <spring:message code="common.title.useyn_y"/>
				</label>
				<label style="font-weight: normal; margin-left:10px;">
					<input type="radio" name="hrdApiUseYn" value="N" <c:if test="${vo.hrdApiUseYn eq 'N' }">checked</c:if> /> <spring:message code="common.title.useyn_n"/>
				</label>
			</td>
		</tr>
	</table>
	<div class="text-right">
		<c:if test="${gubun eq 'A' }">
		<button class="btn btn-primary btn-sm" onclick="addOrg()"><spring:message code="button.add"/></button>
		</c:if>
		<c:if test="${gubun eq 'E' }">
		<button class="btn btn-primary btn-sm" onclick="editOrg()"><spring:message code="button.add"/></button>
		<button class="btn btn-warning btn-sm" onclick="delOrg()"><spring:message code="button.delete"/></button>
		</c:if>
		<button class="btn btn-default btn-sm" onclick="parent.modalBoxClose()"><spring:message code="button.close"/></button>
	</div>
	</form>

<script type="text/javascript">
	// 페이지 초기화
	$(document).ready(function() {
		$(".inputDate").inputDate();
		$(".inputNumber").inputNumber();
		autoMakeCd();
		checkKollus();
	});

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		$('#orgInfoForm').attr("action",cmd);
		$('#orgInfoForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultVO) {
		alert(resultVO.message);

		if(resultVO.result >= 0) {
			// 정상 처리
			parent.listPageing(1);
			parent.modalBoxClose();
		} else {
			// 비정상 처리
		}
	}

	/**
	 * 교육기관 추가
	 */
	function addOrg() {
		if(!validate(document.orgInfoForm)) return;
		process("/adm/org/orginfo/add");	// cmd
	}

	/**
	 * 교육기관 수정
	 */
	function editOrg() {
		if(!validate(document.orgInfoForm)) return;
		process("/adm/org/orginfo/edit");	// cmd
	}

	/**
	 * 교육기관 삭제
	 */
	function delOrg() {
		if(confirm("<spring:message code="org.message.orginfo.delete.confirm"/>")) 
			process("/adm/org/orginfo/remove");	// cmd
	}

	/**
	 * 교육기관 코드 자동 입력 관련 처리
	 */
	function autoMakeCd() {
		<c:if test="${gubun eq 'A'}">
		if($("#checkAutoMakeYn").is(":checked")) {
			$("#orgInfoForm").find("[name='orgCd']").val("<spring:message code="common.title.automake"/>");
			$("#orgInfoForm").find("[name='orgCd']").css("background-color","#f3f3f3");
			$("#orgInfoForm").find("[name='orgCd']").attr("readonly",true);
			$("#orgInfoForm").find("[name='autoMakeYn']").val("Y");
		} else {
			$("#orgInfoForm").find("[name='orgCd']").val("");
			$("#orgInfoForm").find("[name='orgCd']").css("background-color","#ffffff");
			$("#orgInfoForm").find("[name='orgCd']").attr("readonly",false);
			$("#orgInfoForm").find("[name='autoMakeYn']").val("N");
		}
		</c:if>
	}

	function checkKollus() {
		var kollusUse = $("#orgInfoForm").find("[name='kollusUseYn']:checked").val();
		if(kollusUse == "Y") {
			$("#kollusKeyCd").attr("readonly",false);
			$("#kollusCtgrCd").attr("readonly",false);
			$("#kollusTokenCd").attr("readonly",false);
		} else {
			$("#kollusKeyCd").attr("readonly",true);
			$("#kollusCtgrCd").attr("readonly",true);
			$("#kollusTokenCd").attr("readonly",true);
		}
	}

	function setZipCodeComp(zipCode, addr) {
		$("#compPostNo").val(zipCode);
		$("#compAddr1").val(addr);
	}
</script>
