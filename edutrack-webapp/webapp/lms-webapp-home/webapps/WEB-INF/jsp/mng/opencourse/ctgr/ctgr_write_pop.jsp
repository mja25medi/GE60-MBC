<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="openCrsCtgrVO" value="${vo}"/>
<c:set var="gubun" value="${gubun}"/>
	<form id="openCrsCtgrForm" name="openCrsCtgrForm" onsubmit="return false" >
	<input type="hidden" name="ctgrCd" value="${vo.ctgrCd }" />
	<input type="hidden" name="ctgrOdr" value="${vo.ctgrOdr }" />
    <table summary="<spring:message code="etc.title.relatedsite.ctgr.manage"/>" class="table table-bordered wordbreak">
        <colgroup>
    		<col style="width:30%"/>
    		<col style="width:70%"/>
    	</colgroup>
		<tr>
			<th scope="row"><spring:message code="course.open.title.category.name"/></th>
			<td>
				<input type="text" dispName="<spring:message code="course.open.title.category.name"/>" maxlength="50" isNull="N" lenCheck="50" name="ctgrNm" value="${vo.ctgrNm }" class="form-control input-sm" />
			</td>
		</tr>
		<tr>
			<th scope="row"><spring:message code="course.open.title.category.info"/></th>
			<td>
				<textarea style="height:50px" dispName="<spring:message code="course.open.title.category.info"/>" isNull="N" lenCheck="2000" name="ctgrDesc" class="form-control input-sm" id="ctgrDesc">${vo.ctgrDesc }</textarea>
			</td>
		</tr>
		<tr>
			<th scope="row"><spring:message code="course.open.title.category.useyn" />
			<td>
				<label><input type="radio" name="useYn" value="Y" <c:if test="${vo.useYn eq 'Y'}">checked</c:if> /> <spring:message code="common.title.useyn_y"/></label>
 				<label style="margin-left:10px;"><input type="radio" name="useYn" value="N" <c:if test="${vo.useYn eq 'N'}">checked</c:if> /> <spring:message code="common.title.useyn_n"/></label>
			</td>
		</tr>
	</table>
    <div class="text-right">
    	<c:if test="${gubun eq 'A'}">
		<a href="#none" onclick="addCtgr()" class="btn btn-primary btn-sm"><spring:message code="button.write"/></a>
        </c:if>
        <c:if test="${gubun eq 'E'}">
        <a href="#none" onclick="editCtgr()" class="btn btn-primary btn-sm"><spring:message code="button.edit"/></a>
        <a href="#none" onclick="delCtgr()" class="btn btn-warning btn-sm"><spring:message code="button.delete"/></a>
        </c:if>
        <a href="#none" onclick="parent.modalBoxClose()" class="btn btn-default btn-sm"><spring:message code="button.close"/></a>
    </div>
	</form>

<script type="text/javascript">
	// 관련사이트 초기화
	$(document).ready(function() {

	});

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		if(!validate(openCrsCtgrForm)) return;
		$('#openCrsCtgrForm').attr("action" ,"/mng/openCourse/" + cmd);
		$('#openCrsCtgrForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		alert(resultDTO.message);
		if(resultDTO.result >= 0) {
			// 정상 처리
			parent.listCtgr();
			parent.modalBoxClose();
		} else {
			// 비정상 처리
		}
	}

	/**
	 * 카테고리 추가
	 */
	function addCtgr() {
		process("addCtgr");	// cmd
	}

	/**
	 * 카테고리 수정
	 */
	function editCtgr() {
		process("editCtgr"); // cmd
	}

	/**
	 * 카테고리 삭제
	 */
	function delCtgr() {
		if(confirm('<spring:message code="course.open.message.category.confirm.delete"/>')) {
			process("removeCtgr"); // cmd
		} else {
			return;
		}
	}

</script>
