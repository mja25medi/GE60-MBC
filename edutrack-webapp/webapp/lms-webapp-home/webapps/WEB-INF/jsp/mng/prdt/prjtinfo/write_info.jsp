<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<mhtml:mng_html>
<mhtml:mng_head titleName="${MENUNAME}">
	
</mhtml:mng_head>

<mhtml:frm_body cssTag="background-color:#ffffff;">
<form name="prdtPrjtInfoForm" id="prdtPrjtInfoForm" onsubmit="return false" method="POST" action="/PrdtPrjtInfoManage.do">
	<input type="hidden" name="cmd" />
	<input type="hidden" name="autoMakeYn" id="autoMakeYn" value="Y"/>
	<input type="hidden" name="prjtOdr" id="prjtOdr" value="${vo.prjtOdr}" />
	<table summary="<spring:message code="prdt.title.prjt.info.prjt.manage"/>" class="table table-bordered">
		<colgroup>
			<col style="width:25%"/>
			<col style="width:75%"/>
		</colgroup>
		<tr>
			<th scope="row"><spring:message code="prdt.title.prjt.info.prjt.code"/></th>
			<td>
				<c:if test="${gubun eq 'A'}">
				<div class="input-group">
					<input type="text" name="prjtCd" id="prjtCd" maxlength="10" required="required" title="<spring:message code="prdt.title.prjt.info.prjt.code"/>" class="form-control input-sm" value="${vo.prjtCd}" onkeyup="isChkCharacter(this)" style="width:70%"/>
					<label class="ml10" ><input type="checkbox" id="checkAutoMakeYn" name="checkAutoMakeYn" onclick="autoMakeCd()" style="border:0" checked/><spring:message code="common.title.automake"/></label>
				</div>
				</c:if>
	       		<c:if test="${gubun eq 'E'}">
		       		<input type="hidden" name="prjtCd" id="prjtCd" value="${vo.prjtCd}"/>
		       		${vo.prjtCd}
	       		</c:if>			
				
			</td>
		</tr>		
		<tr>
			<th scope="row"><spring:message code="prdt.title.prjt.info.prjt.name"/></th>
			<td>
				<input type="text" name="prjtNm" id="prjtNm" maxlength="50" required="required" title="<spring:message code="prdt.title.prjt.info.prjt.name"/>" class="form-control input-sm" value="${vo.prjtNm}"/>
			</td>
		</tr>
		
		<tr>
			<th scope="row"><label for="codeDesc"><spring:message code="prdt.title.prjt.info.prjt.cts"/></label></th>
			<td>
				<textarea name="prjtCts" id="prjtCts" required="required" title="<spring:message code="prdt.title.prjt.info.prjt.cts"/>" style="height:100px" class="form-control input-sm">${vo.prjtCts}</textarea>
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
		<c:if test="${gubun eq 'A'}">
		<a href="javascript:addPrjt()" class="btn btn-primary btn-sm"><i class="fa fa-check"></i> <spring:message code="button.add"/> </a>
		</c:if>
		<c:if test="${gubun eq 'E'}">
		<a href="javascript:editPrjt()" class="btn btn-primary btn-sm"><i class="fa fa-check"></i> <spring:message code="button.add"/> </a>
		<a href="javascript:delPrjt()" class="btn btn-warning btn-sm"><i class="fa fa-trash-o"></i> <spring:message code="button.delete"/> </a>
		</c:if>
		<a href="javascript:parent.modalClose('modalBox')" class="btn btn-default btn-sm"><i class="fa fa-times"></i> <spring:message code="button.close"/> </a>
	</div>
</form>
</mhtml:frm_body>
<script type="text/javascript">

	// 페이지 초기화
	$(document).ready(function() {
		autoMakeCd();
	});

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		if(!validate(document.prdtPrjtInfoForm)) return;
		$('#prdtPrjtInfoForm').find("[name=cmd]").val(cmd);
		$('#prdtPrjtInfoForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultVO) {
		alert(resultVO.message);
		if(resultVO.result >= 0) {
			parent.listPageing(1)
			parent.modalClose('modalBox');
		} else {
			// 비정상 처리
		}
	}

	/**
	 * 프로젝트 추가
	 */
	function addPrjt() {
		process("add");	// cmd
	}

	/**
	 * 프로젝트 수정
	 */
	function editPrjt() {
		process("edit"); // cmd
	}

	/**
	 * 프로젝트 삭제
	 */
	function delPrjt() {
		if(confirm('<spring:message code="prdt.message.prjt.info.confirm.delete"/>')) {
			process("remove"); // cmd
		} else {
			return;
		}
	}
	
	function autoMakeCd() {
		<c:if test="${gubun eq 'A'}">
		if($("#checkAutoMakeYn").is(":checked")) {
			$("#prdtPrjtInfoForm").find("[name='prjtCd']").val("<spring:message code="common.title.automake"/>");
			$("#prdtPrjtInfoForm").find("[name='prjtCd']").css("background-color","#f3f3f3");
			$("#prdtPrjtInfoForm").find("[name='prjtCd']").attr("readonly",true);
			$("#prdtPrjtInfoForm").find("[name='autoMakeYn']").val("Y");
		} else {
			$("#prdtPrjtInfoForm").find("[name='prjtCd']").val("");
			$("#prdtPrjtInfoForm").find("[name='prjtCd']").css("background-color","#ffffff");
			$("#prdtPrjtInfoForm").find("[name='prjtCd']").attr("readonly",false);
			$("#prdtPrjtInfoForm").find("[name='autoMakeYn']").val("N");
		}
		</c:if>
	}	
</script>
</mhtml:mng_html>