<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>

	<form name="menuForm" id="menuForm" method="POST" onsubmit="return false">
	<input type="hidden" name="menuType" value="${vo.menuType}"/>
	<input type="hidden" name="authGrpOdr" value="${vo.authGrpOdr}" />
	<table summary="<spring:message code="system.title.menu.manage.auth"/>" class="table table-bordered">
		<colgroup>
			<col style="width:30%"/>
			<col style="width:70%"/>
		</colgroup>
		<tr>
			<th class="top" scope="row"><label for="authGrpCd"><spring:message code="system.title.menu.code.auth"/></label></th>
			<td class="top">
				<c:if test="${gubun eq 'A' }">
					<input type="text" name="authGrpCd" id="authGrpCd" maxlength="10" required="required"  title="<spring:message code="system.title.menu.code.auth"/>" onkeyup="isChkCharacter(this)" class="form-control input-sm"/> 
				</c:if>
				<c:if test="${gubun eq 'E' }">
					<input type="hidden" name="authGrpCd" id="authGrpCd" value="${vo.authGrpCd}"/>${vo.authGrpCd}
				</c:if>
			</td>
		</tr>
		<tr>
			<c:set var="defAuthGrpName" value=""/>
			<c:forEach var="authGrpLang" items="${vo.authGrpLangList}">
				<c:if test="${LANG_DEFAULT eq authGrpLang.langCd}">
					<c:set var="defAuthGrpName" value="${authGrpLang.authGrpNm}"/>
				</c:if>
			</c:forEach>
			<th scope="row"><label for="authGrpNm"><spring:message code="system.title.menu.name.auth"/></label></th>
			<td>
				<input type="text" name="authGrpNm" id="authGrpNm" maxlength="50" required="required" title="<spring:message code="system.title.menu.name.auth"/>" value="${defAuthGrpName}" class="form-control input-sm"/> 
			</td>
		</tr>
		<c:forEach var="lang" items="${langList}">
			<c:if test="${LANG_DEFAULT ne lang}">
				<c:set var="authGrpName" value=""/>
				<c:forEach var="authGrpLang" items="${vo.authGrpLangList}">
					<c:if test="${authGrpLang.langCd eq lang}">
						<c:set var="authGrpName" value="${authGrpLang.authGrpNm}"/>
					</c:if>
				</c:forEach>
		<tr>
			<th scope="row"><label for="authGrpNm_${lang}"><spring:message code="system.title.menu.name.auth"/> (${lang})</label></th>
			<td>
				<input type="text" name="authGrpNm_${lang}" id="authGrpNm_${lang}" maxlength="50" required="required" title="<spring:message code="system.title.menu.name.auth"/> ${lang}" value="${authGrpName}" class="form-control input-sm" />
			</td>
		</tr>
			</c:if>
		</c:forEach>
		<tr>
			<th scope="row"><label for="authGrpDesc"><spring:message code="system.title.menu.info.auth"/></label></th>
			<td>
				<textarea name="authGrpDesc" id="authGrpDesc" maxlength="2000" required="required" title="<spring:message code="system.title.menu.info.auth"/>" class="form-control input-sm">${vo.authGrpDesc}</textarea>
			</td>
		</tr>
		<tr>
			<th scope="row"><spring:message code="common.title.useyn"/></th>
			<td>
				<label style="font-weight: normal;" ><input type="radio" name="useYn" value="Y" <c:if test="${vo.useYn eq 'Y'}">checked</c:if>/><spring:message code="common.title.useyn_y"/></label>
				<label style="font-weight: normal; margin-left:10px;"><input type="radio" name="useYn" value="N" <c:if test="${vo.useYn eq 'N'}">checked</c:if>/><spring:message code="common.title.useyn_n"/></label>
			</td>
		</tr>
	</table>
	<div class="text-right">
		<c:if test="${gubun eq 'A' }">
		<a href="javascript:addAuthGroup()" class="btn btn-primary btn-sm"><spring:message code="button.add"/></a>
		</c:if>
		<c:if test="${gubun eq 'E' }">
		<a href="javascript:editAuthGroup()" class="btn btn-primary btn-sm"><spring:message code="button.edit"/></a>
		<a href="javascript:delAuthGroup()" class="btn btn-warning btn-sm"><spring:message code="button.delete"/></a>
		</c:if>
		<a href="javascript:parent.modalBoxClose()" class="btn btn-default btn-sm"><spring:message code="button.close"/></a>
	</div>
	</form>

<script type="text/javascript">

	// 페이지 초기화
	$(document).ready(function() {

	});

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		if(!validate(document.menuForm)) return;
		$('#menuForm').attr("action",cmd);
		$('#menuForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultVO) {
		alert(resultVO.message);
		if(resultVO.result >= 0) {
			// 정상 처리
			parent.listAuthGroup();
			parent.modalBoxClose();
		} else {
			// 비정상 처리
		}
	}

	/**
	 * 권한 그룹 추가
	 */
	function addAuthGroup() {
		process("/adm/system/menu/addAuthGrp");	// cmd
	}


	/**
	 * 권한 그룹 수정
	 */
	function editAuthGroup() {
		process("/adm/system/menu/editAuthGrp");	// cmd
	}

	/**
	 * 권한 그룹 삭제
	 */
	function delAuthGroup() {
		if(confirm('<spring:message code="system.message.menu.confirm.delete.auth"/>')) {
			process("/adm/system/menu/removeAuthGrp");	// cmd
		} else {
			return;
		}
	}
</script>
