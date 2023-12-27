<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
	<section class="content">
		<div class="row">
			<div class="col-md-12">
				<div class="box">
					<div class="box-body">
						<form onsubmit="return false" id="orgCodeForm" name="orgCodeForm">
					    <input type="hidden" name="codeCtgrCd" value="${vo.codeCtgrCd }" />
					    <input type="hidden" name="codeOdr" value="${vo.codeOdr }" />
					    <input type="hidden" name="orgCd" value="${vo.orgCd}" />
						<input type="hidden" name="autoMakeYn" value="Y"/>
						<table summary="<spring:message code="system.title.code.manage"/>" class="table table-bordered">
					        <colgroup>
					    		<col style="width:30%"/>
					    		<col style="width:70%"/>
					    	</colgroup>
							<tr>
								<th scope="row">
									<label for="codeCd">
										<spring:message code="system.title.code.code"/>
									</label>
								</th>
								<td>
									<c:if test="${gubun eq 'A'}">
									<div style="float:left">
										<input type="text" dispName="<spring:message code="system.title.code.code"/>" maxlength="10" isNull="N" onkeyup="isChkCharacter(this)" lenCheck="10" name="codeCd" value="${vo.codeCd }" class="form-control input-sm" id="codeCd"/>
										<p id="codeInfo" style="font-size: 12px;display: none;"><spring:message code="common.message.code.warning.info"/></p>
									</div>
									<div style="float:left;margin-left:10px;">
										<label style="font-weight:normal;" ><input type="checkbox" id="autoMakeYn" onclick="autoMakeCd()" style="border:0" checked="checked"/> <spring:message code="common.title.automake"/></label>
									</div>
						       		</c:if>
						       		<c:if test="${gubun eq 'E'}">
							       		<input type="hidden" name="codeCd" value="${vo.codeCd }" id="codeCd"/>
							       		${vo.codeCd}
						       		</c:if>
								</td>
							</tr>
							<tr>
					            <th scope="row">
						            <label for="codeNm">
						            	<spring:message code="system.title.code.name"/>
						            </label>
					            </th>
					            <td><input type="text" maxlength="50" dispName="<spring:message code="system.title.code.name"/>" isNull="N" lenCheck="100" name="codeNm" value="${vo.codeNm }" class="form-control input-sm" id="codeNm"/></td>
					        </tr>
							<tr>
								<th scope="row">
									<label for="codeDesc">
										<spring:message code="system.title.code.info"/>
									</label>
								</th>
								<td><textarea style="height:50px" dispName="<spring:message code="system.title.code.info"/>" isNull="N" lenCheck="1000" name="codeDesc" id="codeDesc" class="form-control input-sm">${vo.codeDesc }</textarea></td>
							</tr>
							<tr>
								<th scope="row">
									<spring:message code="common.title.useyn"/>
								</th>
								<td>
									<label style="font-weight: normal;" ><input type="radio" name="useYn" value="Y" style="border:0" <c:if test="${vo.useYn eq 'Y' }">checked="checked"</c:if>><spring:message code="common.title.useyn_y"/></label>
									<label style="font-weight: normal; margin-left:10px;"><input type="radio" name="useYn" value="N" style="border:0" <c:if test="${vo.useYn eq 'N' }">checked="checked"</c:if>><spring:message code="common.title.useyn_n"/></label>
								</td>
							</tr>
					    </table>
				    	<div class="text-right">
				    		<c:if test="${gubun eq 'A' }">
								<button class="btn btn-primary btn-sm" onclick="addCode()"><spring:message code="button.add"/></button>
				    		</c:if>
				    		<c:if test="${gubun eq 'E' }">
							<button class="btn btn-primary btn-sm" onclick="editCode()"><spring:message code="button.add"/></button>
							<button class="btn btn-warning btn-sm" onclick="delCode()"><spring:message code="button.delete"/></button>
							</c:if>
					        <c:if test="${isPop eq 'Y' }"><button class="btn btn-default btn-sm" onclick="javascript:history.go(-1)"><spring:message code="button.close"/></button></c:if>
					        <c:if test="${isPop ne 'Y' }"><button class="btn btn-default btn-sm" onclick="parent.modalBoxClose()"><spring:message code="button.close"/></button></c:if>
						</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</section>

<script type="text/javascript">

	//페이지 초기화
	$(document).ready(function() {
		autoMakeCd();
	});
	
	/**
	*사용자 정보 코드 자동 입력 관련 처리
	*/
	function autoMakeCd() {
		<c:if test="${gubun eq 'A'}">
	
		if($("#autoMakeYn").is(":checked")) {
			$("#orgCodeForm").find("[name='codeCd']").val("<spring:message code="common.title.automake"/>");
			$("#orgCodeForm").find("[name='codeCd']").css("background-color","#f3f3f3");
			$("#orgCodeForm").find("[name='codeCd']").attr("readonly",true);
			$("#orgCodeForm").find("[name='autoMakeYn']").val("Y");
			$("#codeInfo").hide();
		} else {
			$("#orgCodeForm").find("[name='codeCd']").val("");
			$("#orgCodeForm").find("[name='codeCd']").css("background-color","#ffffff");
			$("#orgCodeForm").find("[name='codeCd']").attr("readonly",false);
			$("#orgCodeForm").find("[name='autoMakeYn']").val("N");
			$("#codeInfo").show();
		}
		</c:if>
	}
	
	
	/**
	* 서브밋 처리
	*/
	function process(cmd) {
		if(cmd != "deleteCode"){
			if(!validate(document.orgCodeForm)) return;
		}
		$('#orgCodeForm').attr("action" , "/mng/user/codeInfo/" + cmd);
		$('#orgCodeForm').ajaxSubmit(processCallback);
	}
	
	/**
	* 처리 결과 표시 콜백
	*/
	function processCallback(result) {
		alert(result.message);
		if(result.result == 1) {
			// 정상 처리
			<c:if test="${isPop eq 'Y' }">
				history.go(-1);
			</c:if>
	        <c:if test="${isPop ne 'Y' }">
		        parent.listPageing();
				parent.modalBoxClose();
	        </c:if>
		} else {
			// 비정상 처리
		}
	}
	
	/**
	* 코드 추가
	*/
	function addCode() {
		process("addCode");	// cmd
	}
	
	/**
	* 코드 수정
	*/
	function editCode() {
		process("editCode"); // cmd
	}
	
	/**
	* 코드 삭제
	*/
	function delCode() {
		if(confirm('<spring:message code="user.message.code.confirm.delete"/>')) {
			process("deleteCode"); // cmd
		} else {
			return;
		}
	}
</script>
