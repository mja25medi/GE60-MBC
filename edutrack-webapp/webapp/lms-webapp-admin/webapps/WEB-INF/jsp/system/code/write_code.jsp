<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>

	<form name="codeForm" id="codeForm" method="POST" onsubmit="return false">
    <input type="hidden" name="codeCtgrCd" value="${vo.codeCtgrCd}"/>
    <input type="hidden" name="codeOdr" value="${vo.codeOdr}"/>
    <table summary="<spring:message code="system.title.code.manage"/>" class="table table-bordered">
        <colgroup>
    		<col style="width:30%"/>
    		<col style="width:70%"/>
    	</colgroup>
		<tr>
			<th scope="row"><spring:message code="system.title.code.name"/></th>
			<td >${ctgrVo.codeCtgrNm}</td>
		</tr>
		<tr>
			<th scope="row"><label for="codeCd"><spring:message code="system.title.code.code"/></label></th>
			<td>
				<c:if test="${gubun eq 'A'}">
					<input type="text" name="codeCd" id="codeCd" maxlength="10" required="required" title="<spring:message code="system.title.code.code"/>" onkeyup="isChkCharacter(this)" class="form-control input-sm"/>
				</c:if>
	       		<c:if test="${gubun eq 'E'}">
		       		<input type="hidden" name="codeCd" id="codeCd" value="${vo.codeCd}"/>
		       		${vo.codeCd}
	       		</c:if>
			</td>
		</tr>
		<c:set var="codeName" value="${vo.codeNm}"/>
		<c:forEach var="codeLang" items="${vo.codeLangList}">
			<c:if test="${codeLang.langCd eq LANG_DEFAULT}">
				<c:set var="codeName" value="${codeLang.codeNm}"/>
			</c:if>
		</c:forEach>
		<tr>
            <th scope="row"><label for="codeNm"><spring:message code="system.title.code.name"/></label></th>
            <td>
            	<input type="text" name="codeNm" id="codeNm" maxlength="50" required="required" title="<spring:message code="system.title.code.name"/>" value="${vo.codeNm}" class="form-control input-sm"/>
            </td>
        </tr>
		<c:forEach var="lang" items="${langList}">
			<c:if test="${LANG_DEFAULT ne lang}">
			<c:set var="codeNm" value=""/>
			<c:set var="codeDesc" value=""/>
			<c:forEach var="codeLang" items="${vo.codeLangList}">
				<c:if test="${codeLang.langCd eq lang}">
					<c:set var="codeNm" value="${codeLang.codeNm}"/>
					<c:set var="codeDesc" value="${codeLang.codeDesc}"/>
				</c:if>
			</c:forEach>
		<tr>
            <th scope="row"><label for="codeNm_${lang}"><spring:message code="system.title.code.name"/> (${lang})</label></th>
            <td>
            	<input type="text" name="codeNm_${lang}" id="codeNm_${lang}" maxlength="50" required="required" title="<spring:message code="system.title.code.name"/> ${lang}"  value="${codeNm}" class="form-control input-sm" />
            </td>
        </tr>
        	</c:if>
		</c:forEach>
		<tr>
			<th scope="row"><label for="codeDesc"><spring:message code="system.title.code.info"/></label></th>
			<td>
				<textarea name="codeDesc" id="codeDesc" maxlength="2000" required="required" title="<spring:message code="system.title.code.info"/>" style="height:50px" class="form-control input-sm">${vo.codeDesc}</textarea>
			</td>
		</tr>
		<tr>
            <th scope="row"><label for="codeOptn"><spring:message code="system.title.code.option"/></label></th>
            <td>
            	<input type="text" name="codeOptn" id="codeOptn" title="<spring:message code="system.title.code.option"/>" value="${vo.codeOptn}" class="form-control input-sm" />
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
		<button class="btn btn-primary btn-sm" onclick="addCode()"><spring:message code="button.add"/></button>
        </c:if>
		<c:if test="${gubun eq 'E'}">
		<button class="btn btn-primary btn-sm" onclick="editCode()"><spring:message code="button.add"/></button>
		<button class="btn btn-warning btn-sm" onclick="delCode()"><spring:message code="button.delete"/></button>
        </c:if>
		<button class="btn btn-default btn-sm" onclick="parent.modalBoxClose()"><spring:message code="button.close"/></button>
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
		$('#codeForm').attr("action",cmd);
		$('#codeForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultVO) {
		alert(resultVO.message);
		if(resultVO.result >= 0) {
			// 정상 처리
			parent.listCode();
			parent.modalBoxClose();
		} else {
			// 비정상 처리
		}
	}

	/**
	 * 코드 추가
	 */
	function addCode() {
		process("/adm/system/code/addCode");	// cmd
	}

	/**
	 * 코드 수정
	 */
	function editCode() {
		process("/adm/system/code/editCode"); // cmd
	}

	/**
	 * 코드 삭제
	 */
	function delCode() {
		if(confirm('<spring:message code="system.message.code.confirm.delete"/>')) {
			process("/adm/system/code/removeCode"); // cmd
		} else {
			return;
		}
	}
</script>
