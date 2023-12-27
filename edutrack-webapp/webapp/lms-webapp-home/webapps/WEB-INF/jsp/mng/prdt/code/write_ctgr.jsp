<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
	<form id="prdtCodeForm" name="prdtCodeForm" method="POST" onsubmit="return false;" action="/PrdtCodeManage.do">
	<input type="hidden" name="cmd" id="cmd" />
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
        <c:set var="codeCtgrName" value="${vo.codeCtgrNm}"/>
		<c:forEach var="codeLang" items="${vo.codeCtgrLangList}">
			<c:if test="${codeLang.langCd eq LANG_DEFAULT}">
				<c:set var="codeCtgrName" value="${codeLang.codeCtgrNm}"/>
			</c:if>
		</c:forEach>
        <tr>
            <th scope="row"><label for="codeCtgrNm"><spring:message code="system.title.code.name.category"/></label></th>
            <td>
            	<input type="text" name="codeCtgrNm" id="codeCtgrNm" title="<spring:message code="system.title.code.name.category"/>" value="${codeCtgrName}" maxlength="50" required="required" class="form-control input-sm" />
            </td>
        </tr>
        
		<c:forEach var="lang" items="${langList}">
			<c:if test="${LANG_DEFAULT ne lang}">
			<c:set var="codeCtgrNm" value=""/>
			<c:set var="codeCtgrDesc" value=""/>
			<c:forEach var="codeLang" items="${vo.codeCtgrLangList}">
				<c:if test="${codeLang.langCd eq lang}">
					<c:set var="codeCtgrNm" value="${codeLang.codeCtgrNm}"/>
					<c:set var="codeCtgrDesc" value="${codeLang.codeCtgrDesc}"/>
				</c:if>
			</c:forEach>
		<tr>
            <th scope="row"><label for="codeCtgrNm_${lang}"><spring:message code="system.title.code.name.category"/> (${lang})</label></th>
            <td>
            	<input type="text" name="codeCtgrNm_${lang}" id="codeCtgrNm_${lang}" title="<spring:message code="system.title.code.name.category"/>" value="${codeCtgrNm}" maxlength="50" required="required" class="form-control input-sm" />
            </td>
        </tr>
        	</c:if>
		</c:forEach>
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
		<button class="btn btn-primary btn-sm" onclick="addCtgr()"><i class="fa fa-check"></i> <spring:message code="button.add"/></button>
		</c:if>
        <c:if test="${gubun eq 'E' }">
		<button class="btn btn-primary btn-sm" onclick="editCtgr()"><i class="fa fa-check"></i> <spring:message code="button.edit"/></button>
		<button class="btn btn-warning btn-sm" onclick="delCtgr()"><i class="fa fa-trash-o"></i> <spring:message code="button.delete"/></button>
		</c:if>
	</div>
</form>
<script type="text/javascript">

	// 페이지 초기화
	$(document).ready(function() {

	});

	/**
	 * 카테고리 추가
	 */
	function addCtgr() {
		if(!validate(document.prdtCodeForm)) return;
		$('#cmd').val("addCtgr");
		$('#prdtCodeForm').ajaxSubmit(function(resultVO){
			alert(resultVO.message);
			if(resultVO.result >= 0) {
				vo = resultVO.returnVO;
				listPageing();
				editCtgrForm(vo.codeCtgrCd);
			}
		});
	}

	/**
	 * 카테고리 수정
	 */
	function editCtgr() {
		if(!validate(document.prdtCodeForm)) return;
		$('#cmd').val("editCtgr");
		$('#prdtCodeForm').ajaxSubmit(function(resultVO){
			alert(resultVO.message);
			if(resultVO.result >= 0) {
				listPageing();
			}
		});
	}

	/**
	 * 카테고리 삭제
	 */
	function delCtgr() {
		if(confirm('<spring:message code="system.message.code.confirm.delete.category"/>')) {
			$('#cmd').val("removeCtgr");
			$('#prdtCodeForm').ajaxSubmit(function(resultVO){
				alert(resultVO.message);
				if(resultVO.result >= 0) {
					listPageing();
					writeCtgrForm();
				}
			});			
		} else {
			return;
		}
	}
</script>