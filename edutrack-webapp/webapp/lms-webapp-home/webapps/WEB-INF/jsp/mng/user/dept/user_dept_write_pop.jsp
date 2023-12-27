<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>

	<form onsubmit="return false" name="userDeptInfoForm" id="userDeptInfoForm">
	<input type="hidden" name="deptCd" id="deptCd" value="${vo.deptCd}"/>
	<div style="float:left;width:100%;line-height:30px;height:30px;"><span style="color:red;font-weight:bold;">* </span><spring:message code="common.message.input.required"/></div>
	<table summary="<spring:message code="user.title.userinfo.manage"/>" class="table table-bordered wordbreak">
		<colgroup>
			<col width="18%"/>
			<col width="32%"/>
			<col width="18%"/>
			<col width="32%"/>
		</colgroup>
		<tr>
			<th class="top" scope="row"><span style="color:red;">* </span><label for="deptNm"><spring:message code="user.title.user.dept.name"/></label></th>
			<td class="top" colspan="3">
				<input type="text" dispName="<spring:message code="user.title.user.dept.name"/>"  isNull="N" lenCheck="50" maxlength="50" name="deptNm" value="${vo.deptNm}" id="deptNm" class="form-control input-sm"/>
			</td>
		</tr>
		<tr>
			<th scope="row"><span style="color:red;">* </span><label for="areaCd"><spring:message code="user.title.area"/></label></th>
			<td colspan="3">
				<select name="areaCd" id="areaCd" class="form-control input-sm">
				<c:if test="${not empty areaCode }">
					<option value=""><spring:message code="board.title.editor.table.select.line"/></option>
				<c:forEach items="${areaCode}" var="item">
					<c:set var="codeName" value="${item.codeNm}"/>
					<c:forEach var="lang" items="${item.codeLangList}">
						<c:if test="${lang.langCd eq LOCALEKEY }"><c:set var="codeName" value="${lang.codeNm}"/></c:if>
					</c:forEach>
					<option value="${item.codeCd}" <c:if test="${item.codeCd eq vo.areaCd }"> selected</c:if>>${codeName}</option>
				</c:forEach>
				</c:if>
				<c:if test="${empty areaCode }">
					<option value=""><spring:message code="user.title.user.dept.areacd.empty"/></option>
				</c:if>
				</select>
			</td>
			<%--
			<th scope="row"><span style="color:red;">* </span><label for="typeCd"><spring:message code="user.title.user.dept.type"/></label></th>
			<td>
				<select name="typeCd" id="typeCd" class="form-control input-sm">
					<option value=""><spring:message code="board.title.editor.table.select.line"/></option>
				<c:forEach items="${deptTypeCode}" var="item">
					<c:set var="codeName" value="${item.codeNm}"/>
					<c:forEach var="lang" items="${item.codeLangList}">
						<c:if test="${lang.langCd eq LOCALEKEY }"><c:set var="codeName" value="${lang.codeNm}"/></c:if>
					</c:forEach>
					<option value="${item.codeCd}" <c:if test="${item.codeCd eq userDeptInfoDTO.typeCd }"> selected</c:if>>${codeName}</option>
				</c:forEach>
				</select>
			</td>
			 --%>
		</tr>
		<tr>
			<th scope="row"><span style="color:red;">* </span><label for="deptAddr"><spring:message code="user.title.address"/></label></th>
			<td colspan="3">
				<input type="text" dispName="<spring:message code="user.title.address"/>" isNull="N" lenCheck="500" maxlength="500" name="deptAddr" value="${vo.deptAddr}" id="deptAddr" class="form-control input-sm"/>
			</td>
		</tr>
		<tr>
			<th scope="row"><span style="color:red;">* </span><label for="phoneno"><spring:message code="user.title.userinfo.phoneno"/></label></th>
			<td>
				<input type="text" dispName="<spring:message code="user.title.userinfo.phoneno"/>" maxlength="14" isNull="N" lenCheck="14" name="phoneno" value="${vo.phoneno}" id="phoneno" class="form-control input-sm inputSpecial" onkeydown="return onlyTelNo(this,event)" />
			</td>
			<th scope="row"><label for="faxNo"><spring:message code="user.title.userinfo.fax"/></label></th>
			<td>
				<input type="text" dispName="<spring:message code="user.title.userinfo.fax"/>" maxlength="14" isNull="Y" lenCheck="14" name="faxNo" value="${vo.faxNo}" id="faxNo" class="form-control input-sm inputSpecial" onkeydown="return onlyTelNo(this,event)" />
			</td>
		</tr>
		<tr>
			<th scope="row"><span style="color:red;">* </span><spring:message code="course.open.title.category.useyn" />
			<td colspan="3">
				<label><input type="radio" name="useYn" value="Y" <c:if test="${vo.useYn eq 'Y'}">checked</c:if> /> <spring:message code="common.title.useyn_y"/></label>
 				<label style="margin-left:10px;"><input type="radio" name="useYn" value="N" <c:if test="${vo.useYn ne 'Y'}">checked</c:if> /> <spring:message code="common.title.useyn_n"/></label>
			</td>
		</tr>
	</table>
	<div class="text-right">
		<c:if test="${gubun eq 'A'}">
			<a href="javascript:addUserDept()" class="btn btn-primary btn-sm"><spring:message code="button.add"/> </a>
		</c:if>
		<c:if test="${gubun eq 'E'}">
			<a href="javascript:editUserDept()" class="btn btn-primary btn-sm"><spring:message code="button.edit"/> </a>
			<a href="javascript:delUserDept()" class="btn btn-warning btn-sm"><spring:message code="button.delete"/> </a>
		</c:if>

		<c:if test="${isPop eq 'Y' }"><a href="javascript:history.go(-1)" class="btn btn-default btn-sm"><spring:message code="button.close"/></a></c:if>
        <c:if test="${isPop ne 'Y' }"><a href="javascript:parent.modalBoxClose()" class="btn btn-default btn-sm"><spring:message code="button.close"/> </a></c:if>
	</div>
	</form>
<script type="text/javascript">

	var originOrgCd = '${vo.orgCd}';
	// 페이지 초기화
	$(document).ready(function() {
		$('.inputNumber').inputNumber();	// 숫자만 입력되도록 설정.
		$('.inputSpecial').inputSpecial();  // 특수키가 입력이 들어오묜 false return;
	});

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		$('#userDeptInfoForm').attr("action","/mng/user/deptInfo/"+cmd);
		$('#userDeptInfoForm').ajaxSubmit(processCallback);
	}

	function editValidation(){
		if($('#areaCd').val() == ''){
			alert("<spring:message code="user.message.alert.area"/>");
			$('#areaCd').focus();
			return false;
		}
		/* if($('#typeCd').val() == ''){
			alert("<spring:message code="user.message.alert.dept.type"/>");
			$('#typeCd').focus();
			return false;
		} */
		return true;
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		alert(resultDTO.message);

		if(resultDTO.result >= 0) {
			<c:if test="${isPop eq 'Y' }">
			history.go(-1);
			</c:if>
	        <c:if test="${isPop ne 'Y' }">
	        parent.listPageing(1);
			parent.modalBoxClose();
	        </c:if>

		} else {
			// 비정상 처리
		}
	}

	/**
	 * 소속 추가
	 */
	function addUserDept() {
		if(!validate(document.userDeptInfoForm)) return;
		if(!editValidation()) return;

		process("addUserDept");	// cmd
	}

	/**
	 * 소속 수정
	 */
	function editUserDept() {
		if(!validate(document.userDeptInfoForm)) return;
		if(!editValidation()) return;
		process("editUserDept");	// cmd
	}

	/**
	 * 소속 삭제
	 */
	function delUserDept() {
		process("removeUserDept");	// cmd
	}
</script>
