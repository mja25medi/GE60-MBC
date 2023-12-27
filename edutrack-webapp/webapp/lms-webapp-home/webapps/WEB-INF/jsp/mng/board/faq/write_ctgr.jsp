<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>


	<form onsubmit="return false" id="faqForm" name="faqForm">
    <input type="hidden" name="orgCd" value="${vo.orgCd }">
    <input type="hidden" name="ctgrOdr" value="${vo.ctgrOdr }">
    <input type="hidden" name="autoMakeYn" value="Y">
    
    <table summary="<spring:message code="board.title.faq.category.manage"/>" class="table table-bordered wordbreak">
    	<colgroup>
    		<col style="width:30%"/>
    		<col style="width:70%"/>
    	</colgroup>
        <tr>
            <th class="top" scope="row"><label for="ctgrCd"><spring:message code="board.title.faq.category.code"/></label></th>
            <td class="top">
                <c:if test="${gubun eq 'A'}">
                    <div class="input-group">
	       				<div style="float:left">
							<input type="text" style="width:230px" maxlength="10" onkeyup="isChkCharacter(this)" class="form-control input-sm inputSpecial" id="ctgrCd" name="ctgrCd"/>
							<p id="codeInfo" style="font-size: 12px;display: none;"><spring:message code="common.message.code.warning.info"/></p>
						</div>
						<div style="float:left;margin-left:10px;">
							<label style="font-weight:normal;" ><input type="checkbox" id="autoMakeYn" onclick="autoMakeCd()" checked="checked"/> <spring:message code="common.title.automake"/></label>
						</div>
	       			</div>
                </c:if>
                <c:if test="${gubun eq 'E'}">
                    <input type="hidden" name="ctgrCd" value="${vo.ctgrCd }">
                    ${vo.ctgrCd}
                </c:if>
            </td>
        </tr>
        <tr>
            <th scope="row"><label for="ctgrNm"><spring:message code="board.title.faq.category.name"/></label></th>
            <td><input type="text" style="width:230px" maxlength="50" value="${vo.ctgrNm }" class="form-control input-sm" id="ctgrNm" name="ctgrNm"/></td>
        </tr>
        <tr>
            <th scope="row"><label for="codeCtgrDesc"><spring:message code="board.title.faq.category.desc"/></label></th>
            <td><input type="text"area style="width:230px;height:50px" value="${vo.ctgrDesc}" class="form-control input-sm" id="ctgrDesc" name="ctgrDesc"/></td>
        </tr>
        <tr>
            <th scope="row"><spring:message code="common.title.useyn"/></th>
            <td>
                <label style="font-weight: normal;"><input type="radio" name="useYn" value="Y" <c:if test="${vo.useYn eq 'Y' || empty vo.useYn }">checked="checked"</c:if>/><spring:message code="common.title.useyn_y"/></label>
                <label style="font-weight: normal;margin-left:10px;"><input type="radio" name="useYn" value="N" <c:if test="${vo.useYn eq 'N'}">checked="checked"</c:if>/><spring:message code="common.title.useyn_n"/></label>
            </td>
        </tr>
    </table>
    <div class="text-right">
    	<c:if test="${gubun eq 'A'}">
    	<a href="javascript:addCtgr()" class="btn btn-primary btn-sm"><spring:message code="button.add"/> </a>
    	</c:if>
    	<c:if test="${gubun eq 'E'}">
    	<a href="javascript:editCtgr()" class="btn btn-primary btn-sm"><spring:message code="button.add"/> </a>
    	<a href="javascript:delCtgr()" class="btn btn-warning btn-sm"><spring:message code="button.delete"/> </a>
    	</c:if>
		<a href="javascript:parent.modalBoxClose()" class="btn btn-default btn-sm"><spring:message code="button.close"/> </a>
    </div>
    </form>


<script type="text/javascript">
	
	// 페이지 초기화
	$(document).ready(function() {
		$('.inputSpecial').inputSpecial();  // 특수키가 입력이 들어오면 false return;
		autoMakeCd();
	});
	
	function autoMakeCd() {
		<c:if test="${gubun eq 'A'}">
		var textObj = $("#faqForm").find("[name='orgCd']");
	
		if($("#autoMakeYn").is(":checked")) {
			$("#faqForm").find("[name='ctgrCd']").val("<spring:message code="common.title.automake"/>");
			$("#faqForm").find("[name='ctgrCd']").css("background-color","#f3f3f3");
			$("#faqForm").find("[name='ctgrCd']").attr("readonly",true);
			$("#faqForm").find("[name='autoMakeYn']").val("Y");
			$("#codeInfo").hide();
		} else {
			$("#faqForm").find("[name='ctgrCd']").val("");
			$("#faqForm").find("[name='ctgrCd']").css("background-color","#ffffff");
			$("#faqForm").find("[name='ctgrCd']").attr("readonly",false);
			$("#faqForm").find("[name='autoMakeYn']").val("N");
			$("#codeInfo").show();
		}
		</c:if>
	}
	
	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		if(cmd == 'addCtgr' || cmd == 'editCtgr'){
			
			if($('#faqForm').find('input[name=ctgrNm]').val() == ''){
				alert('<spring:message code="board.messgae.faq.category.alert.noctgr"/>');
				return;
			}
	
			if($('#faqForm').find('input[name=ctgrDesc]').val() == ''){
				alert('<spring:message code="board.messgae.faq.category.alert.nodesc"/>');
				return;
			}
		}
		
		
		$('#faqForm').attr("action","/mng/brd/faq/" + cmd);
		$('#faqForm').ajaxSubmit(processCallback);
	}
	
	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(result) {
		alert(result.message);
		if(result.result == 1) {
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
		if(confirm('<spring:message code="board.message.faq.category.confirm.delete"/>')) {
			process("removeCtgr"); // cmd
		} else {
			return;
		}
	}

</script>
