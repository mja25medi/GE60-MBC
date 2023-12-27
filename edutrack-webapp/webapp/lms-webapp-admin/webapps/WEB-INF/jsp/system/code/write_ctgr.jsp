<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>

	<form id="codeForm" name="codeForm" method="POST" onsubmit="return false;">
    <input type="hidden" name="codeCtgrOdr" value="${vo.codeCtgrOdr}"/>
    <table summary="<spring:message code="system.title.code.manage.category"/>" class="table table-bordered">
    	<colgroup>
    		<col style="width:30%"/>
    		<col style="width:70%"/>
    	</colgroup>
        <tr>
            <th scope="row"><label for="codeCtgrCd"><spring:message code="system.title.code.code.category"/></label></th>
            <td>
				<c:if test="${gubun eq 'A' }">
					<input type="text" name="codeCtgrCd" id="codeCtgrCd" title="<spring:message code="system.title.code.code.category"/>" maxlength="20" required="required" onkeyup="isChkCharacter(this)" class="form-control input-sm"/>
				</c:if>
				<c:if test="${gubun eq 'E' }">
					<input type="hidden" name="codeCtgrCd" id="codeCtgrCd" value="${vo.codeCtgrCd}"/>
                    ${vo.codeCtgrCd}				
				</c:if>
            </td>
        </tr>
        <tr>
            <th scope="row"><label for="codeCtgrNm"><spring:message code="system.title.code.name.category"/></label></th>
            <td>
            	<input type="text" name="codeCtgrNm" id="codeCtgrNm" title="<spring:message code="system.title.code.name.category"/>" value="${vo.codeCtgrNm}" maxlength="50" required="required" class="form-control input-sm" />
            </td>
        </tr>
        <tr>
            <th scope="row"><label for="codeCtgrDesc"><spring:message code="system.title.code.info.category"/></label></th>
            <td>
            	<textarea name="codeCtgrDesc" id="codeCtgrDesc" title="<spring:message code="system.title.code.info.category"/>" style="height:50px;" required="required" class="form-control input-sm">${vo.codeCtgrDesc}</textarea>
            </td>
        </tr>
        <tr>
            <th scope="row"><spring:message code="common.title.useyn"/></th>
            <td>
               	<label style="font-weight: normal;">
               		<input type="radio" name="useYn" value="Y" <c:if test="${vo.useYn eq 'Y'}">checked</c:if>><spring:message code="common.title.useyn_y"/>
               	</label>
               	<label style="font-weight: normal; margin-left: 10px;">
               		<input type="radio" name="useYn" value="N" <c:if test="${vo.useYn eq 'N'}">checked</c:if>><spring:message code="common.title.useyn_n"/>
               	</label>
            </td>
        </tr>
    </table>
    <div style="width:100%;margin-top:10px;" class="text-right">
    	<c:if test="${gubun eq 'A' }">
		<button class="btn btn-primary btn-sm" onclick="addCategory()"><spring:message code="button.add"/></button>
		</c:if>
        <c:if test="${gubun eq 'E' }">
		<button class="btn btn-primary btn-sm" onclick="editCategory()"><spring:message code="button.edit"/></button>
		<button class="btn btn-warning btn-sm" onclick="delCategory()"><spring:message code="button.delete"/></button>
		</c:if>
		<button class="btn btn-default btn-sm" onclick="parent.modalBoxClose();"><spring:message code="button.close"/></button>
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
		if(!validate(document.codeForm)) return;
		$('#codeForm').attr("action", cmd);
		$('#codeForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultVO) {
		alert(resultVO.message);
		if(resultVO.result >= 0) {
			// 정상 처리
			parent.listCategory(parent.ItemDTO.page);
			parent.modalBoxClose();
		} else {
			// 비정상 처리
		}
	}

	/**
	 * 카테고리 추가
	 */
	function addCategory() {
		process("/adm/system/code/addCtgr");	// cmd
	}

	/**
	 * 카테고리 수정
	 */
	function editCategory() {
		process("/adm/system/code/editCtgr"); // cmd
	}

	/**
	 * 카테고리 삭제
	 */
	function delCategory() {
		if(confirm('<spring:message code="system.message.code.confirm.delete.category"/>')) {
			process("/adm/system/code/removeCtgr"); // cmd
		} else {
			return;
		}
	}
</script>
