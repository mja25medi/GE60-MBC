<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<form onsubmit="return false" id="orgConnIpForm" name="orgConnIpForm" method="post">
	<table summary="<spring:message code="org.title.conn.ip.manage"/>" class="table table-bordered wordbreak">
		<colgroup>
			<col style="width:20%;"/>
			<col style="width:80%;"/>
		</colgroup>
		<tr>
			<th scope="row"><spring:message code="org.title.conn.ip"/></th>
			<td>
				<input type="text" dispName="<spring:message code="org.title.conn.ip"/>" maxlength="15" isNull="N" lenCheck="15" id="connIp" name="connIp" onkeyup="isChkIpAddress(this)" class="form-control input-sm" />
			</td>
		</tr>
		<tr>
			<th scope="row"><spring:message code="common.title.useyn"/></th>
			<td>
				<label ><input type="radio" style="border:0" name="useYn" id="useYnY" value="Y" checked="checked"/><spring:message code="common.title.useyn_y"/></label>
				<label style="margin-left:10px;"><input type="radio" style="border:0" name="useYn" id="useYnN" value="N"/><spring:message code="common.title.useyn_n"/></label>
			</td>
		</tr>
	</table>
	<div class="text-right" style="margin-top:10px;">
		<button class="btn btn-primary btn-sm" onclick="addIp()" ><spring:message code="button.add"/></button>
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
		if(!validate(document.orgConnIpForm)) return;
		$('#orgConnIpForm').attr("action", "/mng/org/connIp/" + cmd);
		$('#orgConnIpForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(result) {
		alert(result.message);
		if(result.result == 1) {
			// 정상 처리
			parent.listPageing(1);
			parent.modalBoxClose();
		} else {
			// 비정상 처리
		}
	}

	/**
	 * 페이지 추가
	 */
	function addIp() {
		var val = $("#connIp").val();
    	val = common_replaceAll(val, "," , "");
    	val = common_replaceAll(val, "." , "");
    	val = common_replaceAll(val, "*" , "");
    	if(!isNumber(val)) {
    		alert("<spring:message code="common.message.alert.input.dataformat"/>");
    		return;
    	}
		process("add");
	}
</script>
