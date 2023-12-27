<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>

	<form id="courseCategoryForm" name="courseCategoryForm" onsubmit="return false">
	<input type="hidden" name="crsCtgrCd" value="${vo.crsCtgrCd }"/>
	<input type="hidden" name="crsCtgrLvl" value="${vo.crsCtgrLvl }"/>
	<input type="hidden" name="crsCtgrOdr" value="${vo.crsCtgrOdr }"/>
	<input type="hidden" name="subCnt" value="${vo.subCnt }"/>
	<input type="hidden" name="parCrsCtgrCd" value="${vo.parCrsCtgrCd }"/>
	<input type="hidden" name="parCrsCtgrLvl" value="${vo.parCrsCtgrLvl }"/>
	<input type="hidden" name="parCrsCtgrPath" value="${vo.parCrsCtgrPath }"/>
	<input type="hidden" name="crsCnt" value="${vo.crsCnt }"/>

	<table summary='<spring:message code="course.title.category.manage"/>' class="table table-bordered wordbreak">
		<colgroup>
			<col style="width:30%"/>
			<col style="width:70%"/>
		</colgroup>
		<tr height="35">
			<th class="top" scope="row"><spring:message code="course.title.category.parent"/></th>
			<td class="top">${vo.parCrsCtgrNm }</td>
		</tr>
		<tr height="35">
			<th scope="row"><label for="crsCtgrNm"><spring:message code="course.title.category.name"/></label></th>
			<td>
				<input type="text" style="width:230px" dispName="<spring:message code="course.title.category.name"/>" value="${vo.crsCtgrNm }" maxlength="50" isNull="N" lenCheck="50" name="crsCtgrNm" class="form-control input-sm" id="crsCtgrNm"/>
			</td>
		</tr>
		<tr height="70">
			<th scope="row"><label for="crsCtgrDesc"><spring:message code="course.title.category.desc"/></label></th>
			<td><textarea style="width:230px;height:50px" dispName="<spring:message code="course.title.category.desc"/>" isNull="Y" name="crsCtgrDesc" id="crsCtgrDesc"  class="form-control input-sm"/> ${vo.crsCtgrDesc }</textarea></td>
		</tr>
		<tr height="33">
			<th scope="row"><spring:message code="common.title.useyn"/></th>
			<td>
				<label style="font-weight: normal;"><input type="radio" style="border:0" name="useYn" value="Y" <c:if test="${vo.useYn eq 'Y' or empty vo.useYn }">checked="chekced"</c:if> /> <spring:message code="common.title.useyn_y"/></label>
				<label style="font-weight: normal; margin-left:10px;"><input type="radio" style="border:0" name="useYn" value="N" <c:if test="${vo.useYn eq 'N' }">checked="chekced"</c:if>/> <spring:message code="common.title.useyn_n"/></label>
			</td>
		</tr>
	</table>
	<div class="text-right" style="margin-top:10px;">
		<c:if test="${gubun eq 'A'}">
			<button class="btn btn-primary btn-sm" onclick="addCategory()" ><spring:message code="button.add"/></button>
		</c:if>
		<c:if test="${gubun eq 'E'}">
			<button class="btn btn-primary btn-sm" onclick="editCategory()" ><spring:message code="button.add"/></button>
			<button class="btn btn-warning btn-sm" onclick="delCategory()" ><spring:message code="button.delete"/></button>
		</c:if>
		<c:if test="${isPop eq 'Y' }"><button class="btn btn-default btn-sm" onclick="javascript:history.go(-1)"><spring:message code="button.close"/></button></c:if>
		<c:if test="${isPop ne 'Y' }"><button class="btn btn-default btn-sm" onclick="parent.modalBoxClose()" ><spring:message code="button.close"/></button></button></c:if>


	</div>
	<input type="submit" value="submit" style="display:none" />
	</form>

<script type="text/javascript">

	$(document).ready(function() {
		<c:if test="${isPop eq 'Y' }">
		parent.modalBox.resize(500, 290);
		</c:if>
	});

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		if(!validate(document.courseCategoryForm)) return;
		
		
		$('#courseCategoryForm').attr("action","/mng/course/category/" + cmd);
		$('#courseCategoryForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		if(resultDTO.result >= 0) {
			// 정상 처리
			<c:if test="${isPop eq 'Y' }">
			history.go(-1);
			</c:if>
	        <c:if test="${isPop ne 'Y' }">
			parent.refreshCategoryList();
			parent.modalBoxClose();
	        </c:if>
		} else {
			// 비정상 처리
		}
	}

	/**
	 * 과정 분류 등록
	 */
	function addCategory() {
		process("addCategory");	// cmd
	}

	/**
	 * 과정 분류 수정
	 */
	function editCategory() {
		process("editCategory");	// cmd
	}

	/**
	 * 과정 분류 삭제
	 */
	function delCategory() {
		var f = document.courseCategoryForm;
		if(f["crsCnt"].value > 0) {
			alert("<spring:message code="course.message.category.alert.course"/>");
			return;
		}
		if(f["subCnt"].value > 0) {
			alert("<spring:message code="course.message.category.alert.subcate"/>");
			return;
		}
		if(confirm('<spring:message code="course.message.category.confirm.delete"/>')) {
			process("deleteCategory");	// cmd
		} else {
			return;
		}
	}
</script>
