<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>

<form name="fileForm" id="fileForm" onsubmit="return false" method="POST">
    <table summary="<spring:message code="system.title.file.manage.repository"/>" class="table table-bordered">
    	<colgroup>
    		<col style="width:30%"/>
    		<col style="width:70%"/>
    	</colgroup>
        <tr>
            <th scope="row"><label for="repoCd"><spring:message code="system.title.file.code.repository"/></label></th>
            <td >
            	<c:if test="${gubun eq 'A' }">
            		<input type="text" name="repoCd" id="repoCd" maxlength="20" required="required" title="<spring:message code="system.title.file.code.repository"/>" class="form-control input-sm"/>
            	</c:if>
            	<c:if test="${gubun eq 'E' }">
            		<input type="hidden" name="repoCd" id="repoCd" value="${vo.repoCd}"/>
            		${vo.repoCd}
            	</c:if>
            </td>
        </tr>
        <tr>
        	<c:set var="defRepoNm" value="${vo.repoNm}"/>
			<c:forEach var="fileRepoLang" items="${vo.fileRepoLangList}">
				<c:if test="${LANG_DEFAULT eq fileRepoLang.langCd}">
					<c:set var="defRepoNm" value="${fileRepoLang.repoNm}"/>
				</c:if>
			</c:forEach>
            <th scope="row"><label for="repoNm"><spring:message code="system.title.file.name.repository"/></label></th>
            <td>
            	<input type="text" name="repoNm" id="repoNm" maxlength="100" required="required" title="<spring:message code="system.title.file.name.repository"/>" value="${defRepoNm}" class="form-control input-sm"/>
            </td>
        </tr>
		<c:forEach var="lang" items="${langList}">
			<c:if test="${LANG_DEFAULT ne lang}">
				<c:set var="repoNm" value=""/>
				<c:forEach var="fileRepoLang" items="${vo.fileRepoLangList}">
					<c:if test="${fileRepoLang.langCd eq lang}">
						<c:set var="repoNm" value="${fileRepoLang.repoNm}"/>
					</c:if>
				</c:forEach>
        <tr>
            <th scope="row"><label for="repoNm_${lang}"><spring:message code="system.title.file.name.repository"/> (${lang})</label></th>
            <td>
            	<input type="text" name="repoNm_${lang}" id="repoNm_${lang}" value="${repoNm}" maxlength="100" required="required" title="<spring:message code="system.title.file.name.repository"/> ${lang}" class="form-control input-sm"/>
            </td>
        </tr>
        	</c:if>
		</c:forEach>
        <tr>
            <th scope="row"><label for="parTableNm"><spring:message code="system.title.file.tablenm.repository"/></label></th>
            <td>
            	<input type="text" name="parTableNm" id="parTableNm" maxlength="30" onkeyup="isChkCharacter(this)" title="<spring:message code="system.title.file.tablenm.repository"/>" value="${vo.parTableNm}" class="form-control input-sm"/>
            </td>
        </tr>
        <tr>
            <th scope="row"><label for="parFieldNm"><spring:message code="system.title.file.fieldnm.repository"/></label></th>
            <td>
            	<input type="text" name="parFieldNm" id="parFieldNm" maxlength="30" onkeyup="isChkCharacter(this)" title="<spring:message code="system.title.file.fieldnm.repository"/>" value="${vo.parFieldNm}" class="form-control input-sm"/>
            </td>
        </tr>
        <tr>
            <th scope="row"><label for="repoDfltPath"><spring:message code="system.title.file.path.repository"/></label></th>
            <td>
            	<input type="text" name="repoDfltPath" id="repoDfltPath" maxlength="100" title="<spring:message code="system.title.file.path.repository"/>" value="${vo.repoDfltPath}" class="form-control input-sm"/>
            </td>
        </tr>
    </table>
    <div class="text-right" style="margin-top:10px;">
    	<c:if test="${gubun eq 'A'}">
		<button class="btn btn-primary btn-sm" onclick="addRepo()" ><spring:message code="button.add"/></button>
		</c:if>
		<c:if test="${gubun eq 'E'}">
		<button class="btn btn-primary btn-sm" onclick="editRepo()" ><spring:message code="button.add"/></button>
		<button class="btn btn-warning btn-sm" onclick="delRepo()" ><spring:message code="button.delete"/></button>
		</c:if>
		<button class="btn btn-default btn-sm" onclick="parent.modalBoxClose();" ><spring:message code="button.close"/></button>
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
		if(!validate(document.fileForm)) return;
		$('#fileForm').attr("action",cmd);
		$('#fileForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultVO) {
		alert(resultVO.message);
		if(resultVO.result >= 0) {
			// 정상 처리
			parent.listPageing(1);
			parent.modalBoxClose();
		} else {
			// 비정상 처리
		}
	}

	/**
	 * 카테고리 추가
	 */
	function addRepo() {
		process("/adm/system/file/addRepo.do");	// cmd
	}

	/**
	 * 카테고리 수정
	 */
	function editRepo() {
		process("/adm/system/file/editRepo.do"); // cmd
	}

	/**
	 * 카테고리 삭제
	 */
	function delRepo() {
		if(confirm('<spring:message code="system.message.file.confirm.delete.repository"/>')) {
			process("/adm/system/file/removeRepo.do"); // cmd
		} else {
			return;
		}
	}
</script>
