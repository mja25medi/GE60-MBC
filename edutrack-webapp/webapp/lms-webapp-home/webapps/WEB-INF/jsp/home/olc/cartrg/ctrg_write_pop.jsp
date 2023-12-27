<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="gubun" value="${gubun}"/>
<mhtml:home_html>
<mhtml:home_head titleName="${MENUNAME}">

</mhtml:home_head>

<body style="padding-top: 0px;">
	<form id="olcCartrgForm" name="olcCartrgForm" onsubmit="return false">
	<input type="hidden"  name="ctgrCd"  id="ctgrCd" value="${vo.ctgrCd}" />
	<input type="hidden"  name="parCtgrCd" value="${vo.parCtgrCd}" />
	<input type="hidden"  name="ctgrLvl" value="${vo.ctgrLvl}" />
	<input type="hidden"  name="ctgrOdr" value="${vo.ctgrOdr}" />
	<input type="hidden"  name="ctgrPath" value="${vo.ctgrPath}" />
	<input type="hidden"  name="ctgrPathNm" value="${vo.ctgrPathNm}" />
	<input type="hidden"  name="useYn"  id="useYn" value="${vo.useYn}" />
	<table class="table table-bordered wordbreak">
		<caption class="sr-only"><spring:message code="user.title.userinfo.change.password"/></caption>
		<colgroup>
			<col style="width:30%;" />
			<col />
		</colgroup>
		<tbody>
			<tr>
				<th scope="row"><label for="ctgrNm"><spring:message code="olc.title.category.name"/></label></th>
				<td>
					<input type="text" dispName="<spring:message code="olc.title.category.name"/>" isNull="N" name="ctgrNm" id="ctgrNm" value="${vo.ctgrNm}" class="form-control input-sm"/>
				</td>
			</tr>
			<tr>
				<th scope="row"><spring:message code="olc.title.category.desc"/></th>
				<td>
					<textarea dispName="<spring:message code="olc.title.category.desc"/>" name="ctgrDesc" id="ctgrDesc" class="form-control input-sm">value="${vo.ctgrDesc}"</textarea>
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
		$('#olcCartrgForm').attr("action","/home/olc/cartrg/" + cmd);
		$('#olcCartrgForm').ajaxSubmit(processCallback);
	}
	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		alert(resultDTO.message);

		if(resultDTO.result >= 0) {
			document.location = "/home/olc/cartrg/manageCtrg";
		} else {
			// 비정상 처리
		}
	}
	function addCtrg(){
		if(!validate(document.olcCartrgForm)) return;
		<c:if test="${gubun eq 'A'}">
		$("#useYn").val("Y");
		</c:if>

		process("addCtrg");
	}

	function editCtrg(){
		if(!validate(document.olcCartrgForm)) return;

		process("editCtrg");
	}
</script>
</body>
</mhtml:home_html>
