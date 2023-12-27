<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="gubun" value="${gubun}"/>
<mhtml:home_html>
<mhtml:home_head titleName="${MENUNAME}">

</mhtml:home_head>

<body style="padding-top: 0px;">
	<form id="clibCntsCtgrForm" name="clibCntsCtgrForm" onsubmit="return false">
	<input type="hidden"  name="ctgrCd"  id="ctgrCd" value="${vo.ctgrCd}" />
	<input type="hidden"  name="parCtgrCd" value="${vo.parCtgrCd}" />
	<input type="hidden"  name="ctgrLvl" value="${vo.ctgrLvl}" />
	<input type="hidden"  name="ctgrOdr" value="${vo.ctgrOdr}" />
	<input type="hidden"  name="ctgrPath" value="${vo.ctgrPath}" />
	<input type="hidden"  name="ctgrPathNm" value="${vo.ctgrPathNm}" />
	<input type="hidden"  name="useYn"  id="useYn" value="${vo.useYn}" />
	<input type="hidden"  name="subCnt"  id="subCnt" value="${vo.subCnt}" />
	<input type="hidden"  name="cntsCnt"  id="cntsCnt" value="${vo.cntsCnt}" />
	<table class="table table-bordered wordbreak">
		<caption class="sr-only"><spring:message code="user.title.userinfo.change.password"/></caption>
		<colgroup>
			<col style="width:30%;" />
			<col />
		</colgroup>
		<tbody>
			<tr>
				<th scope="row"><label for="ctgrNm"><spring:message code="library.title.contents.category.name"/></label></th>
				<td>
					<input type="text" dispName="<spring:message code="library.title.contents.category.name"/>" isNull="N" name="ctgrNm" value="${vo.ctgrNm}" maxlength="50" lenCheck="50" id="ctgrNm" class="form-control input-sm"/>
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="library.title.contents.category.desc"/></th>
				<td>
					<textarea dispName="<spring:message code="library.title.contents.category.desc"/>" name="ctgrDesc" lenCheck="2000" lenMCheck="2000" id="ctgrDesc" class="form-control input-sm">${vo.ctgrDesc}</textarea>
				</td>
			</tr>
		</tbody>
	</table>
	<div class="text-center" style="margin-top:10px;">
		<c:if test="${gubun eq 'A'}">
		<a href="#" onclick ="addCtrg()" class="btn btn-primary btn-sm"><spring:message code="button.add"/></a>
		</c:if>
		<c:if test="${gubun eq 'E'}">
		<a href="#" onclick ="editCtrg()" class="btn btn-primary btn-sm"><spring:message code="button.add"/></a>
		<a href="#" onclick ="delCtrg()" class="btn btn-warning btn-sm"><spring:message code="button.delete"/></a>
		</c:if>
		<a href="javascript:history.go(-1);" class="btn btn-default btn-sm"><spring:message code="button.close"/></a>
	</div>
	</form>

<script type="text/javascript">
	// 페이지 초기화
	$(document).ready(function() {

	});
//?cmd=manageCtrg
	function process(cmd) {
		$('#clibCntsCtgrForm').attr("action","/home/library/clibCntsCtgr/"+ cmd);
		$('#clibCntsCtgrForm').ajaxSubmit(processCallback);
	}
	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		alert(resultDTO.message);

		if(resultDTO.result >= 0) {
			document.location = "/home/library/clibCntsCtgr/manageCtrg";
		} else {
			// 비정상 처리
		}
	}
	function addCtrg(){
		if(!validate(document.clibCntsCtgrForm)) return;
		<c:if test="${gubun eq 'A'}">
		$("#useYn").val("Y");
		</c:if>

		process("addCtrg");
	}

	function editCtrg(){
		if(!validate(document.clibCntsCtgrForm)) return;

		process("editCtrg");
	}

	function delCtrg(){
		if(!validate(document.clibCntsCtgrForm)) return;
		var cntsCnt = $("#cntsCnt").val();
		var subCnt = $("#subCnt").val();
		if(Number(subCnt) > 0) {
			alert("<spring:message code="library.message.contents.category.delete.alert.subcate"/>");
			return;
		}
		if(Number(cntsCnt) > 0){
			alert("<spring:message code="library.message.contents.category.delete.alert.contents"/>");
			return;
		}

		process("deleteCtgr");

	}
</script>
</body>
</mhtml:home_html>
