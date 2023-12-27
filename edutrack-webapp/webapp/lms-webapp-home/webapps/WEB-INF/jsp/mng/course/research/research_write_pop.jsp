<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="researchBankVO" value="${vo}"/>
<c:set var="gubun" value="${gubun}"/>

	<form id="researchBankForm" name="researchBankForm" onsubmit="return false" >
	<input type="hidden" name="reshSn" value="${vo.reshSn }" />
	<input type="hidden" name="itemCnt" value="${vo.itemCnt }" />
	<input type="hidden" name="useCnt" value="${vo.useCnt }" />
	<table summary="<spring:message code="course.title.reshbank.manage"/>" class="table table-bordered wordbreak">
		<colgroup>
			<col style="width:25%"/>
			<col style="width:75%"/>
		</colgroup>
		<tbody>
		<tr height="35">
			<th scope="row" class="top"><label for="reshTitle"><spring:message code="course.title.reshbank.title"/></label></th>
			<td class="top">
				<input type="text" dispName="<spring:message code="course.title.reshbank.title"/>" maxlength="100" isNull="N" lenCheck="100" name="reshTitle" value="${vo.reshTitle }" class="form-control input-sm" id="reshTitle"/>
			</td>
		</tr>
		<tr height="35">
			<th scope="row"><label for="reshCts"><spring:message code="course.title.reshbank.desc"/></label></th>
			<td>
				<textarea name="reshCts" style="height:100px" dispName="<spring:message code="course.title.reshbank.desc"/>" isNull="N" id="reshCts" class="form-control input-sm">${vo.reshCts }</textarea>
			</td>
		</tr>
		<tr height="33">
			<th scope="row"><spring:message code="common.title.useyn"/></th>
			<td>
				<label style="font-weight: normal;"><input type="radio" style="border:0" name="regYn" value="Y" <c:if test="${vo.regYn eq 'Y'}">checked</c:if> /><spring:message code="common.title.useyn_y"/></label>
				<label style="font-weight: normal;margin-left:10px;"><input type="radio" style="border:0" name="regYn" value="N" <c:if test="${vo.regYn eq 'N'}">checked</c:if> /><spring:message code="common.title.useyn_n"/></label>
			</td>
		</tr>
		</tbody>
	</table>
	<div class="text-right">
		<c:if test="${gubun eq 'A'}">
		<a href="javascript:addResh()" class="btn btn-primary btn-sm"><spring:message code="button.add"/></a>
		</c:if>
		<c:if test="${gubun eq 'E'}">
		<a href="javascript:editResh()" class="btn btn-primary btn-sm"><spring:message code="button.add"/></a>
		<a href="javascript:delResh()" class="btn btn-warning btn-sm"><spring:message code="button.delete"/></a>
		</c:if>
		<a href="javascript:parent.modalBoxClose()" class="btn btn-default btn-sm"><spring:message code="button.close"/></a>
	</div>
	<input type="submit" value="submit" style="display:none" />
	</form>
<script type="text/javascript">

	$(document).ready(function() {
		//
	});

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		if(!validate(document.researchBankForm)) return;

		$('#researchBankForm').attr("action","/mng/course/researchBank/" + cmd);
		$('#researchBankForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		alert(resultDTO.message);

		if(resultDTO.result >= 0) {
			var curPage = ${vo.curPage};
			if(curPage <= 0 ) curPage =1 ;
			// 정상 처리
			parent.listResearch(curPage);
			parent.modalBoxClose();
		} else {
			// 비정상 처리
		}
	}

	<c:if test="${gubun eq 'A'}">
	/**
	 * 과목 분류 등록
	 */
	function addResh() {
		process("add");	// cmd
	}
	</c:if>

	<c:if test="${gubun eq 'E'}">
	/**
	 * 과목 분류 수정
	 */
	function editResh() {
		process("edit");	// cmd
	}

	/**
	 * 과목 분류 삭제
	 */
	function delResh() {
		if(parseInt(document.researchBankForm["itemCnt"].value,10) > 0) {
			alert('<spring:message code="course.message.reshbank.alert.delete1"/>')
			return
		} else if(parseInt(document.researchBankForm["useCnt"].value,10) > 0) {
			alert('<spring:message code="course.message.reshbank.alert.delete2"/>')
			return
		} else{
			 if(confirm('<spring:message code="course.message.reshbank.confirm.delete"/>')) {
				process("delete");
			 }
		}
	}
	</c:if>
</script>
