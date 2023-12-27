<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<form name="ideInfoForm" id="ideInfoForm" method="POST" onsubmit="return false;">
	<input type="hidden" name="orgCd" id="orgCd" value="${vo.orgCd}"/>
	<table class="table table-bordered wordbreak">
		<colgroup>
			<col style="width:20%"/>
			<col style="width:30%"/>
			<col style="width:20%"/>
			<col style="width:30%"/>
		</colgroup>
        <tr>
			<th scope="row"><label for="bbsNm">IDE URL</label></th>
			<td colspan="4">
				<input type="text" name="ideUrl" id="ideUrl" maxlength="100" required="required" class="form-control input-sm" value="${vo.ideUrl}"/>
			</td>
		</tr>
		<tr>
			<th scope="row">사용여부</th>
			<td colspan="4">
				<label style="font-weight:normal;">
					<input type="radio" name="useYn" value="Y" <c:if test="${vo.useYn eq 'Y'}">checked</c:if>/> <spring:message code="common.title.useyn_y"/>
				</label>
				<label style="font-weight:normal; margin-left:10px;">
					<input type="radio" name="useYn" value="N" <c:if test="${vo.useYn eq 'N'}">checked</c:if>/> <spring:message code="common.title.useyn_n"/>
				</label>
			</td>
		</tr>
	</table>
	<div class="text-right">
		<c:if test="${gubun eq 'A'}">
		<button class="btn btn-primary btn-sm" onclick="addInfo()" ><spring:message code="button.add"/></button>
		</c:if>
		<button class="btn btn-default btn-sm" onclick="parent.modalBoxClose()" ><spring:message code="button.close"/></button>
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
		if(!validate(document.ideInfoForm)) return;
		$('#ideInfoForm').attr("action","/mng/org/ideInfo/" + cmd);
		$('#ideInfoForm').ajaxSubmit(processCallback);
	}
	
	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultVO) {
		alert(resultVO.message);
		if(resultVO.result >= 0) {
			// 정상 처리
			parent.ideList();
			parent.modalBoxClose();
		} else {
			// 비정상 처리
		}
	}
	
	/**
	 * IDE 추가
	 */
	function addInfo() {
		process("addIde");	// cmd
	}
	
</script>
