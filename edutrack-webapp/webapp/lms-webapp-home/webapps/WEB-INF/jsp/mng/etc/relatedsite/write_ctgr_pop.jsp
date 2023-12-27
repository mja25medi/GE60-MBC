<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>

	<form onsubmit="return false" id="relatedSiteForm" name="relatedSiteForm">
    <input type="hidden" name="orgCd" value="${vo.orgCd }">
    <input type="hidden" name="ctgrOdr" value="${vo.ctgrOdr }">
    <input type="hidden" name="autoMakeYn" value="Y">
    
    <table summary="<spring:message code="board.title.faq.category.manage"/>" class="table table-bordered wordbreak">
    	<colgroup>
    		<col style="width:30%"/>
    		<col style="width:70%"/>
    	</colgroup>
        <tr>
			<th scope="row"><spring:message code="etc.title.relatedsite.ctgr.code"/></th>
			<td>
				<c:if test="${gubun eq 'A' }">
					<div class="input-group">
						<input type="text" dispName="<spring:message code="etc.title.relatedsite.ctgr.code"/>" maxlength="10" isNull="N" onkeyup="isChkCharacter(this)" lenCheck="10" name="ctgrCd" value="${vo.ctgrCd }" class="form-control input-sm" style="width:120px;"/>
						<span style="float:left;margin-left:10px;">
							<label style="font-weight:normal;" ><input type="checkbox" id="autoMakeYn" onclick="autoMakeCd()" style="border:0" id="autoMakeYn" checked="checked"/> <spring:message code="common.title.automake"/></label>
						</span>
	       			</div>
				</c:if>
				<c:if test="${gubun ne 'A' }">
				${vo.ctgrCd}
				<input type="hidden" name="ctgrCd" value="${vo.ctgrCd }">
				</c:if>
			</td>
		</tr>
		<tr>
			<th scope="row"><spring:message code="etc.title.relatedsite.ctgr.name"/></th>
			<td>
				<input type="text" maxlength="50" dispName="<spring:message code="etc.title.relatedsite.ctgr.name"/>" isNull="N" name="title" value="${vo.title }" class="form-control input-sm"/>
			</td>
		</tr>
		<tr>
			<th scope="row"><spring:message code="etc.title.relatedsite.ctgr.desc"/></th>
			<td>
				<input type="text" area style="height:50px" dispName="<spring:message code="etc.title.relatedsite.ctgr.desc"/>" isNull="N" lenCheck="1000" class="form-control input-sm" name="ctgrDesc" value="${vo.ctgrDesc }"/>
			</td>
		</tr>
    </table>
    <div class="text-right">
    	<c:if test="${gubun eq 'A'}">
		<a href="#none" onclick="addCtgr()" class="btn btn-primary btn-sm"><spring:message code="button.add"/></a>
        </c:if>
        <c:if test="${gubun eq 'E'}">
        <a href="#none" onclick="editCtgr()" class="btn btn-primary btn-sm"><spring:message code="button.edit"/></a>
<%--         <a href="#none" onclick="delCtgr()" class="btn btn-warning btn-sm"><spring:message code="button.delete"/></a> --%>
        </c:if>
        <a href="#none" onclick="parent.modalBoxClose()" class="btn btn-default btn-sm"><spring:message code="button.close"/></a>
    </div>
    </form>


<script type="text/javascript">
	
	//관련사이트 초기화
	$(document).ready(function() {
		autoMakeCd();
	});
	
	function autoMakeCd() {
		<c:if test="${gubun eq 'A'}">
		var textObj = $("#relatedSiteForm").find("[name='orgCd']");
	
		if($("#autoMakeYn").is(":checked")) {
			$("#relatedSiteForm").find("[name='ctgrCd']").val("<spring:message code="common.title.automake"/>");
			$("#relatedSiteForm").find("[name='ctgrCd']").css("background-color","#f3f3f3");
			$("#relatedSiteForm").find("[name='ctgrCd']").attr("readonly",true);
			$("#relatedSiteForm").find("[name='autoMakeYn']").val("Y");
			$("#codeInfo").hide();
		} else {
			$("#relatedSiteForm").find("[name='ctgrCd']").val("");
			$("#relatedSiteForm").find("[name='ctgrCd']").css("background-color","#ffffff");
			$("#relatedSiteForm").find("[name='ctgrCd']").attr("readonly",false);
			$("#relatedSiteForm").find("[name='autoMakeYn']").val("N");
			$("#codeInfo").show();
		}
		</c:if>
	}
	
	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		if(!validate(document.relatedSiteForm)) return;
		$('#relatedSiteForm').attr("action","/mng/etc/relatedSite/" + cmd);
		$('#relatedSiteForm').ajaxSubmit(processCallback);
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
		if(confirm('<spring:message code="etc.message.relatedsite.ctgr.confirm.delete"/>')) {
			process("deleteCtgr"); // cmd
		} else {
			return;
		}
	}

</script>
