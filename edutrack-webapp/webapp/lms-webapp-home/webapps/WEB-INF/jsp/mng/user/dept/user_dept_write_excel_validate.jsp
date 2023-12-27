<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../common/page_init.jsp" %>
<c:set var="gubun" value="${userDeptInfoForm.gubun}" />
<c:set var="userDeptInfoDTO" value="${userDeptInfoForm.userDeptInfoDTO}"/>
<mhtml:mng_html>
<mhtml:mng_head titleName="${MENUNAME}">
	<mhtml:head_module fileupload="y"/>
	<style type="text/css">
		.error {
			color:red;
			font-weight:bold;
		}
	</style>
</mhtml:mng_head>

<mhtml:frm_body cssTag="background-color:#ffffff;">
	<form name="Validate" id="Validate" onsubmit="return false;" method="post" action="<c:url value="/UserDeptInfoManage.do"/>">
	<input type="hidden" name="cmd" id="listCmd" />
	<input type="hidden" name="codeCtgrCd" value="${orgCodeDTO.codeCtgrCd }" id="codeCtgrCd" />
	<div class="text-right" style="width:100%">
		<a href="javascript:saveUser()" class="btn btn-primary btn-sm"><meditag:message messageKey="button.add"/> </a>
		<a href="javascript:parent.modalBoxClose()" class="btn btn-default btn-sm"><meditag:message messageKey="button.close"/> </a>
	</div>
	<div style="width:100%;height:520px;overflow-x: hidden;overflow-y: auto;margin-top:10px; ">
		<table summary="<meditag:message messageKey="user.title.userinfo.manage"/>" class="table table-bordered wordbreak" style="margin-bottom:0px;">
			<colgroup>
				<col style="width:30px"/>
				<col style="width:50px;"/>
				<col style="width:100px"/>
				<col style="width:100px"/>
				<col style="width:100px"/>
				<col style="width:100px"/>
				<col style="width:100px"/>
				<col style="width:60px;"/>
			</colgroup>
			<thead>
				<tr>
					<th scope="col"><input type="checkbox" name="chkAll" id="chkAll" value="N" style="border:0" title="check all" onclick="checkAll()"/></th>
					<th scope="col"><meditag:message messageKey="common.title.no"/> </th>
					<th scope='col'><meditag:message messageKey="user.title.user.dept.name"/></th>
					<th scope='col'><meditag:message messageKey="user.title.address"/></th>
					<th scope='col'><meditag:message messageKey="user.title.area"/></th>
					<th scope='col'><meditag:message messageKey="user.title.userinfo.phoneno"/></th>
					<th scope='col'><meditag:message messageKey="user.title.userinfo.fax"/></th>
					<th scope="col"><meditag:message messageKey="common.title.edit"/></th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="item" items="${deptList}">
				<tr>
					<td class="text-center">
						<input type="checkbox" name="chk" id="chk_${item.lineNo}" value="${item.lineNo}" <c:if test="${not empty item.errorCode }">disabled</c:if>>
					</td>
					<td class="text-center">
						${item.lineNo}
						<input type="hidden" name="lineNo" id="lineNo_${item.lineNo}" value="${item.lineNo}" />
						<input type="hidden" name="errorCode" id="errorCode_${item.lineNo}" value="${item.errorCode}" />
					</td>
					<td>
						<c:set var="errorCss" value=""/><c:if test="${fn:contains(item.errorCode, 'DEPTNM')}"><c:set var="errorCss" value="error"/></c:if>
						<span class="value-view-${item.lineNo} ${errorCss}" id="deptNm-view-${item.lineNo}">
						<c:if test="${fn:contains(item.errorCode, 'EMPTYDEPTNM')}">
							<meditag:message messageKey="common.message.required"/>
						</c:if>
							${item.deptNm}
						</span>
						<input type="text" name="deptNm" id="deptNm-edit-${item.lineNo}" value="${item.deptNm}" class="value-edit-${item.lineNo} form-control input-sm" maxlength="50" style="display:none"/>
					</td>
					<td>
						<c:set var="errorCss" value=""/><c:if test="${fn:contains(item.errorCode, 'DEPTADDR')}"><c:set var="errorCss" value="error"/></c:if>
						<span class="value-view-${item.lineNo} ${errorCss}" id="deptAddr-view-${item.lineNo}">
							<c:choose>
								<c:when test="${fn:contains(item.errorCode, 'EMPTYDEPTADDR')}">
									<meditag:message messageKey="common.message.required"/>
								</c:when>
								<c:otherwise>
									${item.deptAddr}
								</c:otherwise>
							</c:choose>
						</span>
						<input type="text" name="deptAddr" id="deptAddr-edit-${item.lineNo}" value="${item.deptAddr}" class="value-edit-${item.lineNo} form-control input-sm"  maxlength="500"  style="display:none"/>
					</td>
					<td>
						<c:set var="errorCss" value=""/><c:if test="${fn:contains(item.errorCode, 'AREACD')}"><c:set var="errorCss" value="error"/></c:if>
						<span class="value-view-${item.lineNo} ${errorCss}" id="areaCd-view-${item.lineNo}">
						<c:choose>
							<c:when test="${fn:contains(item.errorCode, 'AREACD')}">
								<meditag:message messageKey="common.message.required"/>
							</c:when>
							<c:otherwise>
								<meditag:orgcodename code="${item.areaCd}" category="AREA_CD" orgCd="${USER_ORGCD }"/>
							</c:otherwise>
						</c:choose>
						</span>
						<%-- <input type="text" name="areaCd" id="areaCd-edit-${item.lineNo}" value="${item.areaCd}" class="value-edit-${item.lineNo} form-control input-sm" style="display:none"/> --%>
						<select name="areaCd" id="areaCd-edit-${item.lineNo}" class="value-edit-${item.lineNo} form-control input-sm" style="display:none;width:100%">
							<option value=""><meditag:message messageKey="board.title.editor.table.select.line"/></option>
							<c:forEach items="${areaCode}" var="codeItem">
							<c:set var="codeName" value="${codeItem.codeNm}"/>
							<c:forEach var="lang" items="${codeItem.codeLangList}">
								<c:if test="${lang.langCd eq LOCALEKEY }"><c:set var="codeName" value="${lang.codeNm}"/></c:if>
							</c:forEach>
							<option value="${codeItem.codeCd}" <c:if test="${codeItem.codeCd eq item.areaCd }"> selected</c:if>>${codeName}</option>
							</c:forEach>
						</select>
					</td>
					<td>
						<c:set var="errorCss" value=""/><c:if test="${fn:contains(item.errorCode, 'PHONENO')}"><c:set var="errorCss" value="error"/></c:if>
						<span class="value-view-${item.lineNo} ${errorCss}" id="phoneno-view-${item.lineNo}">
							<c:choose>
								<c:when test="${fn:contains(item.errorCode, 'EMPTYPHONENO')}">
									<meditag:message messageKey="common.message.required"/>
								</c:when>
								<c:otherwise>
									${item.phoneno}
								</c:otherwise>
							</c:choose>
						</span>
						<input type="text" name="phoneno" id="phoneno-edit-${item.lineNo}" value="${item.phoneno}" class="value-edit-${item.lineNo} form-control input-sm"  maxlength="14"  style="display:none"/>
					</td>
					<td>
						<c:set var="errorCss" value=""/><c:if test="${fn:contains(item.errorCode, 'FAXNO')}"><c:set var="errorCss" value="error"/></c:if>
						<span class="value-view-${item.lineNo} ${errorCss}" id="faxNo-view-${item.lineNo}">${item.faxNo}</span>
						<input type="text" name="faxNo" id="faxNo-edit-${item.lineNo}" value="${item.faxNo}" class="value-edit-${item.lineNo} form-control input-sm" maxlength="14"  style="display:none"/>
					</td>
					<td class="text-center">
						<a id="editBtn_${item.lineNo}" href="javascript:editLine('${item.lineNo}')" class="btn btn-primary btn-sm"><meditag:message messageKey="button.edit"/> </a>
						<a id="checkBtn_${item.lineNo}" href="javascript:checkLine('${item.lineNo}')" class="btn btn-warning btn-sm" style="display:none"><meditag:message messageKey="button.process"/> </a>
					</td>
				</tr>
			</c:forEach>
			<c:if test="${empty deptList }">
				<tr>
					<td colspan="5"><meditag:message messageKey="user.message.userinfo.nodata"/></td>
				</tr>
			</c:if>
			</tbody>
		</table>
	</div>
	</form>
	<nested:form action="/UserDeptInfoManage.do" onsubmit="return false" styleId="userDeptInfoForm">
	<input type="hidden" name="cmd" id="cmd"/>
	<input type="hidden" name="userDeptInfoDTO.lineNo" value="" id="lineNo" />
	<input type="hidden" name="userDeptInfoDTO.deptNm" value="" id="deptNm" />
	<input type="hidden" name="userDeptInfoDTO.deptAddr" value="" id="deptAddr" />
	<input type="hidden" name="userDeptInfoDTO.areaCd" value="" id="areaCd" />
	<input type="hidden" name="userDeptInfoDTO.phoneno" value="" id="phoneno" />
	<input type="hidden" name="userDeptInfoDTO.faxNo" value="" id="faxNo" />
	<nested:submit value="submit" style="display:none" />
	</nested:form>
<script type="text/javascript">

	// 페이지 초기화
	$(document).ready(function() {
		parent.modalBox.resize(1100,600);
	});

	function editLine(lineNo) {
		// unchecked and disabled checkbox
		$("#chk_"+lineNo).attr("checked", false);
		$("#chk_"+lineNo).attr("disabled", true);

		$("#editBtn_"+lineNo).hide();
		$("#checkBtn_"+lineNo).show();

		$(".value-view-"+lineNo).hide();
		$(".value-edit-"+lineNo).show();
	}

	function checkAll() {
	    var state=$('input[name=chkAll]:checked').size();
	    if(state==1){
	    	$("#Validate input[name='chk']:enabled").prop({checked:true});
	    }else{
	    	$("#Validate input[name='chk']:enabled").prop({checked:false});
	    }
	}


	function checkLine(lineNo) {
		var deptNm = $("#deptNm-edit-"+lineNo).val();
		var deptAddr = $("#deptAddr-edit-"+lineNo).val();
		var areaCd = $("#areaCd-edit-"+lineNo).val();
		var phoneno = $("#phoneno-edit-"+lineNo).val();
		var faxNo = $("#faxNo-edit-"+lineNo).val();
		var check = /^[0-9,-]+$/;

		if(isEmpty(deptNm)) {
			alert('<meditag:message messageKey="user.message.login.search.alert.input.deptnm"/>');
			$("#deptNm-edit-"+lineNo).focus();
			$("#deptNm-edit-"+lineNo).addClass("validerr");
			return;
		} else {
			$("#deptNm-edit-"+lineNo).removeClass("validerr");
		}
		if(deptNm.length > 50) {
			alert('<meditag:message messageKey="common.message.textcnt" args1="50"/>');
			$("#deptNm-edit-"+lineNo).focus();
			$("#deptNm-edit-"+lineNo).addClass("validerr");
			return;
		}
		if(isEmpty(deptAddr)) {
			alert('<meditag:message messageKey="user.message.login.search.alert.input.deptaddr"/>');
			$("#deptAddr-edit-"+lineNo).focus();
			$("#deptAddr-edit-"+lineNo).addClass("validerr");
			return;
		} else {
			$("#deptAddr-edit-"+lineNo).removeClass("validerr");
		}
		if(deptAddr.length > 500) {
			alert('<meditag:message messageKey="common.message.textcnt" args1="500"/>');
			$("#deptAddr-edit-"+lineNo).focus();
			$("#deptAddr-edit-"+lineNo).addClass("validerr");
			return;
		}
		if(isEmpty(areaCd)) {
			alert('<meditag:message messageKey="user.message.login.search.alert.input.areacd"/>');
			$("#areaCd-edit-"+lineNo).focus();
			$("#areaCd-edit-"+lineNo).addClass("validerr");
			return;
		} else {
			$("#areaCd-edit-"+lineNo).removeClass("validerr");
		}
		if(isEmpty(phoneno)) {
			alert('<meditag:message messageKey="user.message.login.search.alert.input.phoneno"/>');
			$("#phoneno-edit-"+lineNo).focus();
			$("#phoneno-edit-"+lineNo).addClass("validerr");
			return;
		} else {
			$("#phoneno-edit-"+lineNo).removeClass("validerr");
		}
		/* if(isEmpty(faxNo)) {
			alert('<meditag:message messageKey="user.message.login.search.alert.input.faxno"/>');
			$("#faxNo-edit-"+lineNo).focus();
			$("#faxNo-edit-"+lineNo).addClass("validerr");
			return;
		} else {
			$("#faxNo-edit-"+lineNo).removeClass("validerr");
		} */
		if(!check.test(phoneno)){
			alert('<meditag:message messageKey="user.message.dept.alert.isnumber.phone"/>');
			$("#phoneno-edit-"+lineNo).focus();
			$("#phoneno-edit-"+lineNo).addClass("validerr");
			return;
		} else {
			$("#phoneno-edit-"+lineNo).removeClass("validerr");
		}
		if(phoneno.length > 14) {
			alert('<meditag:message messageKey="common.message.textcnt" args1="14"/>');
			$("#phoneno-edit-"+lineNo).focus();
			$("#phoneno-edit-"+lineNo).addClass("validerr");
			return;
		}
		if(!isEmpty(faxNo)) {
			if(!check.test(faxNo)){
				alert('<meditag:message messageKey="user.message.dept.alert.isnumber.fax"/>');
				$("#faxNo-edit-"+lineNo).focus();
				$("#faxNo-edit-"+lineNo).addClass("validerr");
				return;
			} else {
				$("#faxNo-edit-"+lineNo).removeClass("validerr");
			}
			if(phoneno.length > 14) {
				alert('<meditag:message messageKey="common.message.textcnt" args1="14"/>');
				$("#faxNo-edit-"+lineNo).focus();
				$("#faxNo-edit-"+lineNo).addClass("validerr");
				return;
			}
		}
		$("#lineNo").val(lineNo);
		$("#deptNm").val(deptNm);
		$("#deptAddr").val(deptAddr);
		$("#areaCd").val(areaCd);
		$("#phoneno").val(phoneno);
		$("#faxNo").val(faxNo);

		$("#cmd").val("deptUploadCheck");
		$('#userDeptInfoForm').ajaxSubmit(checkLineCallback);
	}

	function checkLineCallback(resultDTO) {
		//var result = resultDTO.returnDto;
		//alert(showAttribute(resultDTO));
		var lineNo = resultDTO.lineNo;
		if(isEmpty(resultDTO.errorCode)) {
			//-- error class 삭제
			var areaSelectBox = document.getElementById("areaCd-edit-"+lineNo);
			$(".value-view-"+lineNo).removeClass("error");
			//-- check box 활성화
			$("#chk_"+lineNo).attr("disabled", false);

			$("#deptNm-view-"+lineNo).html(resultDTO.deptNm);
			$("#deptAddr-view-"+lineNo).html(resultDTO.deptAddr);
			//$("#areaCd-view-"+lineNo).html(resultDTO.areaCd);
			for(var i=0; i<areaSelectBox.length; i++){
				if(areaSelectBox.options[i].value == resultDTO.areaCd){
					$("#areaCd-view-"+lineNo).html(areaSelectBox.options[i].text);
				}
			}

			$("#phoneno-view-"+lineNo).html(resultDTO.phoneno);
			$("#faxNo-view-"+lineNo).html(resultDTO.faxNo);
			$("#errorCode_"+lineNo).val("");
			$("#chk_"+lineNo).val(resultDTO.lineNo);
			$("#checkBtn_"+lineNo).hide();
			$("#editBtn_"+lineNo).show();
			$(".value-edit-"+lineNo).hide();
			$(".value-view-"+lineNo).show();
		} else {
			//-- 오류가 있는 경우
			$("#errorCode_"+lineNo).val(resultDTO.errorCode);
			//-- check box 비활성화
			$("#chk_"+lineNo).attr("disabled", true);
		}
	}

	function saveUser() {
		var userList = $("#Validate input[name='chk']:checked").stringValues();
		if(isEmpty(userList)) {
			alert("<meditag:message messageKey="user.message.userdept.select.dept"/>");
			return;
		}
		else {
			$("#listCmd").val("deptUpload");
			$('#Validate').ajaxSubmit(saveUserCallback);
		}
	}

	function saveUserCallback(resultDTO) {
		alert(resultDTO.returnMessage);
		if(resultDTO.returnResult >= 0) {
			// 정상 처리
			parent.listPageing(1);
			parent.modalBoxClose();
		} else {
			// 비정상 처리
		}
	}
</script>
</mhtml:frm_body>
</mhtml:mng_html>