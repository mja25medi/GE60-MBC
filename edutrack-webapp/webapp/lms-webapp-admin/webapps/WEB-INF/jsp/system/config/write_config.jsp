<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>

<form name="configForm" id="configForm" method="POST" onsubmit="return false">
<input type="hidden" name="cfgCtgrCd" value="${vo.cfgCtgrCd}"/>
	<table summary="<spring:message code="system.title.config.manage"/>" class="table table-bordered">
		<tr>
			<th scope="row" width="35%"><spring:message code="system.title.config.name.category"/></th>
			<td>${ctgrVo.ctgrNm}</td>
		</tr>
		<tr>
			<th scope="row"><label for="cfgCd"><spring:message code="system.title.config.code"/></label></th>
			<td>
				<c:if test="${gubun eq 'A' }">
					<input type="text" name="cfgCd" id="cfgCd" maxlength="10" required="required" title="<spring:message code="system.title.config.code"/>" onkeyup="isChkCharacter(this)" value="${vo.cfgCd}" class="form-control input-sm"/>
				</c:if>
				<c:if test="${gubun eq 'E' }">
					<input type="hidden" name="cfgCd" id="cfgCd" value="${vo.cfgCd}"/>${vo.cfgCd}
				</c:if>
			</td>
		</tr>
		<tr>
			<th scope="row"><label for="cfgNm"><spring:message code="system.title.config.name"/></label></th>
			<td>
				<input type="text" name="cfgNm" id="cfgNm" maxlength="50" required="required" title="<spring:message code="system.title.config.name"/>" value="${vo.cfgNm}" class="form-control input-sm"/>
			</td>
		</tr>
		<tr>
			<th scope="row"><label for="cfgVal"><spring:message code="system.title.config.value"/></label></th>
			<td>
				<input type="text" name="cfgVal" id="cfgVal" maxlength="100" required="required" title="<spring:message code="system.title.config.value"/>" value="${vo.cfgVal}" class="form-control input-sm"/>
			</td>
		</tr>
		<tr>
			<th scope="row"><label for="cfgDesc"><spring:message code="system.title.config.info"/></label></th>
			<td>
				<textarea name="cfgDesc" id="cfgDesc" maxlength="2000" required="required" title="<spring:message code="system.title.config.info"/>" class="form-control input-sm">${vo.cfgDesc}</textarea> 
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
	<div class="text-right" style="margin-top:10px">
		<c:if test="${gubun eq 'A' }">
		<button class="btn btn-primary btn-sm" onclick="addConfig()"><spring:message code="button.add"/></button>
        </c:if>
        <c:if test="${gubun eq 'E' }">
		<button class="btn btn-primary btn-sm" onclick="editConfig()"><spring:message code="button.add"/></button>
		<button class="btn btn-warning btn-sm" onclick="delConfig()"><spring:message code="button.delete"/></button>
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
		$('#configForm').attr("action", cmd);
		$('#configForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultVO) {
		alert(resultVO.message);
		if(resultVO.result >= 0) {
			// 정상 처리
			parent.listConfig();
			parent.modalBoxClose();
		} else {
			// 비정상 처리
		}
	}

	/**
	 * 설정 추가
	 */
	function addConfig() {
		process("/adm/system/config/addCfg");	// cmd
	}

	/**
	 * 설정 수정
	 */
	function editConfig() {
		process("/adm/system/config/editCfg"); // cmd
	}

	/**
	 * 설정 삭제
	 */
	function delConfig() {
		if(confirm('<spring:message code="system.message.config.confirm.delete"/>')) {
			process("/adm/system/config/removeCfg"); // cmd
		} else {
			return;
		}
	}
</script>
