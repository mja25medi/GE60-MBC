<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>

<form name="configForm" id="configForm" method="POST" onsubmit="return false">
<table summary="<spring:message code="system.title.config.manage.category"/>" class="table table-bordered">
	<tr>
		<th scope="row" width="35%"><label for="cfgCtgrCd"><spring:message code="system.title.config.code.category"/></label></th>
		<td >
			<c:if test="${gubun eq 'A'}">
				<input type="text" name="cfgCtgrCd" id="cfgCtgrCd" maxlength="10" required="required" title="<spring:message code="system.title.config.code.category"/>" value="${vo.cfgCtgrCd}" class="form-control input-sm"/>
			</c:if>
			<c:if test="${gubun eq 'E'}">
				<input type="hidden" name="cfgCtgrCd" id="cfgCtgrCd" value="${vo.cfgCtgrCd}">${vo.cfgCtgrCd}
			</c:if>
		</td>
	</tr>
	<tr>
		<th scope="row"><label for="ctgrNm"><spring:message code="system.title.config.name.category"/></label></th>
		<td>
			<input type="text" name="ctgrNm" id="ctgrNm" maxlength="50" required="required" title="<spring:message code="system.title.config.name.category"/>" value="${vo.ctgrNm}" class="form-control input-sm"/>
		</td>
	</tr>
	<tr>
		<th scope="row"><label for="ctgrDesc"><spring:message code="system.title.config.info.category"/></label></th>
		<td>
			<textarea name="ctgrDesc" id="ctgrDesc" maxlength="2000" required="required" title="<spring:message code="system.title.config.info.category"/>" class="form-control input-sm">${vo.ctgrDesc}</textarea>
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
<div class="text-right" style="margin-top:10px;">
	<c:if test="${gubun eq 'A'}">
	<button class="btn btn-primary btn-sm" onclick="addCategory()"><spring:message code="button.add"/></button>
	</c:if>
	<c:if test="${gubun eq 'E'}">
	<button class="btn btn-primary btn-sm" onclick="editCategory()"><spring:message code="button.add"/></button>
    <button class="btn btn-warning btn-sm" onclick="delCategory()"><spring:message code="button.delete"/></button>
	</c:if>
    <button class="btn btn-default btn-sm" onclick="parent.modalBoxClose()"><spring:message code="button.close"/></button>
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
		if(!validate(document.configForm)) return;
		$('#configForm').attr("action", cmd)
		$('#configForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultVO) {
		alert(resultVO.message);
		if(resultVO.result >= 0) {
			// 정상 처리
			parent.listCategory();
			parent.modalBoxClose()
		} else {
			// 비정상 처리
		}
	}

	/**
	 * 카테고리 추가
	 */
	function addCategory() {
		process("/adm/system/config/addCtgr");	// cmd
	}

	/**
	 * 카테고리 수정
	 */
	function editCategory() {
		process("/adm/system/config/editCtgr"); // cmd
	}

	/**
	 * 카테고리 삭제
	 */
	function delCategory() {
		if(confirm('<spring:message code="system.message.config.confirm.delete.category"/>')) {
			process("/adm/system/config/removeCtgr"); // cmd
		} else {
			return;
		}
	} 

</script>
