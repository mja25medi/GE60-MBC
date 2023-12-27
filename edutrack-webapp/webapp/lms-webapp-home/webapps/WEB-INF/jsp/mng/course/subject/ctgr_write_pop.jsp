<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="subjectCategoryVO" value="${vo}" />

	<form id="subjectForm" name="subjectForm" onsubmit="return false" >
	<input type="hidden" name="sbjCtgrTypeCd" value="${vo.sbjCtgrTypeCd }" />
	<input type="hidden" name="sbjCtgrCd" value="${vo.sbjCtgrCd }" />
	<input type="hidden" name="ctgrLvl" value="${vo.ctgrLvl }" />
	<input type="hidden" name="ctgrOdr" value="${vo.ctgrOdr }" />
	<input type="hidden" name="subCnt" value="${vo.subCnt }" />
	<input type="hidden" name="parSbjCtgrCd" value="${vo.parSbjCtgrCd }" />
	<input type="hidden" name="parCtgrLvl" value="${vo.parCtgrLvl}" />
	<input type="hidden" name="sbjCnt" value="${vo.sbjCnt }" />
	<table summary="<spring:message code="course.title.subject.category.manage"/>" class="table table-bordered wordbreak">
		<colgroup>
			<col style="width:30%"/>
			<col style="width:70%"/>
		</colgroup>
		<tr height="35">
			<th class="top" scope="row"><spring:message code="course.title.subject.category.parent"/></th>
			<td class="top">
				${vo.parSbjCtgrNm}
			</td>
		</tr>
		<tr height="35">
			<th scope="row"><label for="sbjCtgrNm"><spring:message code="course.title.subject.category.name"/></label></th>
			<td>
				<input type="text" dispName="<spring:message code="course.title.subject.category.name"/>" maxlength="50" isNull="N" lenCheck="50" name="sbjCtgrNm" value="${vo.sbjCtgrNm }" class="form-control input-sm" id="sbjCtgrNm"/>
			</td>
		</tr>
		<tr height="33">
			<th scope="row"><spring:message code="common.title.useyn"/></th>
			<td>
				<label style="font-weight: normal;"><input type="radio" style="border:0" name="useYn" value="Y" <c:if test="${vo.useYn eq 'Y'}">checked</c:if>/><spring:message code="common.title.useyn_y"/></label>
				<label style="font-weight: normal;margin-left:10px;"><input type="radio" style="border:0" name="useYn" value="N" <c:if test="${vo.useYn ne 'Y'}">checked</c:if>/><spring:message code="common.title.useyn_n"/></label>
			</td>
		</tr>
	</table>
	<div class="text-right">
		<c:if test="${gubun eq 'A'}">
		<a href="javascript:addCategory()" class="btn btn-primary btn-sm"><spring:message code="button.add"/> </a>
		</c:if>
		<c:if test="${gubun eq 'E'}">
		<a href="javascript:editCategory()" class="btn btn-primary btn-sm"><spring:message code="button.add"/> </a>
		<a href="javascript:delCategory()" class="btn btn-warning btn-sm"><spring:message code="button.delete"/> </a>
		</c:if>
		<a href="javascript:parent.modalBoxClose()" class="btn btn-default btn-sm"><spring:message code="button.close"/> </a>
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
		if(!validate(document.subjectForm)) return;
		$('#subjectForm').attr("action","/mng/course/subject/"+cmd);
		$('#subjectForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		alert(resultDTO.message);
		if(resultDTO.result >= 0) {
			// 정상 처리
			parent.refreshCategoryList();
			parent.modalBoxClose();
		} else {
			// 비정상 처리
		}
	}

	/**
	 * 과목 분류 등록
	 */
	function addCategory() {
		process("addCtgr");	// cmd
	}

	/**
	 * 과목 분류 수정
	 */
	function editCategory() {
		process("editCtgr");	// cmd
	}

	/**
	 * 과목 분류 삭제
	 */
	function delCategory() {
		var f = document.subjectForm;
		if(f["sbjCnt"].value > 0) {
			alert("<spring:message code="course.message.subject.category.alert.delete1"/>");
			return;
		}
		if(f["subCnt"].value > 0) {
			alert("<spring:message code="course.message.subject.category.alert.delete2"/>");
			return;
		}
		if(confirm('<spring:message code="course.message.subject.category.confirm.delete"/>')) {
			process("deleteCtgr");	// cmd
		} else {
			return;
		}
	}
</script>
