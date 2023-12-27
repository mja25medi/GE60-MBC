<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="clibMediaCntsVO" value="${vo}"/>

	<form id="clibMediaCntsForm" name="clibMediaCntsForm" onsubmit="return false" >
	<input type="hidden" name="cntsCd" value="${vo.cntsCd }" />
	<input type="hidden" name="shareDivCd"  id="shareDivCd" value="${vo.shareDivCd }"/>
	<input type="hidden" name="cntsType"  id="cntsType" value="${vo.cntsType }"/>
	<c:if test="${clibMediaCntsVO.useYn eq 'Y' }">
		<table summary='<spring:message code="library.title.contents.share"/>' class="table table-striped table-bordered wordbreak">
			<colgroup>
				<col style="width:30%"/>
				<col style="width:70%"/>
			</colgroup>
			<tr>
				<th scope="row"><label for="ctgrNm"><spring:message code="library.title.contents.name"/></label></th>
				<td>
					<input type="text" name="cntsNm" id="cntsNm" value="${clibMediaCntsVO.cntsNm }" class="form-control input-sm" id="cntsNm" maxlength="50" />
				</td>
			</tr>
			<tr>
				<th scope="row"><label for="ctgrNm"><spring:message code="library.title.contents.ccl"/></label></th>
				<td>
					<%-- <meditag:orgcodeselectbox category="CCL_CD" orgCd="${USER_ORGCD }" fieldName="clibMediaCntsVO.cclCd" fieldId="cclCd" value="clibMediaCntsDTO.cclCd" class="form-control input-sm" /> --%>
					<select name="cclCd" id="cclCd" class="form-control input-sm" >
						<c:if test="${not empty cclCode}">
							<option value=""><spring:message code="board.title.editor.table.select.line"/></option>
						<c:forEach items="${cclCode}" var="item">
							<c:set var="codeName" value="${item.codeNm}"/>
							<%-- <c:forEach var="lang" items="${item.codeLangList}">
								<c:if test="${lang.langCd eq LOCALEKEY }"><c:set var="codeName" value="${lang.codeNm}"/></c:if>
							</c:forEach> --%>
							<option value="${item.codeCd}" <c:if test="${item.codeCd eq clibMediaCntsVO.cclCd }"> selected</c:if>>${codeName}</option>
						</c:forEach>
						</c:if>
						<c:if test="${empty cclCode}">
								<option value=""><spring:message code="user.message.userinfo.alert.infocode"/></option>
						</c:if>
					</select>
				</td>
			</tr>
			<tr>
				<th scope="row"><label for="ctgrNm"><spring:message code="library.title.contents.share.div"/></label></th>
				<td>
					<label style="font-weight: normal;" ><input type="radio" name="shareCd" value="CNTS" id="shareCd_C" onchange="changeCtgr('CNTS')" checked> <spring:message code="olc.title.contents.share"/></label>
					<label style="font-weight: normal; margin-left:10px;"><input type="radio" name="shareCd" value="KNOW" id="shareCd_K" onchange="changeCtgr('KNOW')"><spring:message code="olc.title.knowledge.share"/></label>
				</td>
			</tr>
			<tr>
				<th scope="row"><label for="ctgrNm"><spring:message code="course.title.exambank.category"/></label></th>
				<td>
					<div class="input-group">
						<div class="input-group-btn btn-group">
							<button type="button" class="btn btn-default btn-sm dropdown-toggle" data-toggle="dropdown" style="padding:5.0px 10px">
								<span class="caret"></span>
							</button>
							<ul class="dropdown-menu" role="menu" id="ctgrDrop" style="max-height:300px; min-width:300px; overflow:auto;">

							</ul>
						</div>
						<input type="text" name="ctgrNm" id="ctgrNm" class="form-control input-sm" style="background-color:#ffffff;" readonly="readonly" value=""/>
						<input type="hidden" name="ctgrCd" id="ctgrCd">
					</div>
				</td>
			</tr>

		</table>
	</c:if>
	<div class="text-right" style="margin-bottom:10px;">
		<c:if test="${clibMediaCntsVO.useYn eq 'Y' }"><button class="btn btn-primary btn-sm" onclick="addCnts()" ><spring:message code="button.add.share"/></button></c:if>
		<c:if test="${clibMediaCntsVO.useYn eq 'N' }"><p style="float: left; padding-left: 10px;"><spring:message code="library.message.contents.media.share.useyn"/></p></c:if>

		<button class="btn btn-default btn-sm" onclick="parent.modalBoxClose()" ><spring:message code="button.close"/></button>
	</div>
	<div id="listCntsArea">

	</div>
	<input type="submit" value="submit" style="display:none" />
	</form>
<script type="text/javascript">
	$(document).ready(function() {
		listCnts();
		changeCtgr("CNTS");
	});

	function setCtgr(ctgrCd, ctgrNm) {
		$("#ctgrCd").val(ctgrCd);
		$("#ctgrNm").val(ctgrNm);
	}

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		if(!validate(document.clibMediaCntsForm)) return;
		$('#clibMediaCntsForm').attr("action", "/mng/library/clibMediaCnts/" + cmd);
		$('#clibMediaCntsForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		alert(resultDTO.message);
		if(resultDTO.result >= 0) {
			// 정상 처리
			location.reload();
		} else {
			// 비정상 처리
		}
	}

	function addCnts() {
		if($("#cntsNm").val() == ""){
			alert("<spring:message code="library.message.contents.alert.input.contents.name"/>");
			return;
		}
		if($("#cclCd option:selected").val() == "") {
			alert("<spring:message code="library.message.contents.alert.select.cclcd"/>");
			return;
		}
		if($("#ctgrCd").val() == "") {
			alert("<spring:message code="library.message.contents.category.select.category"/>");
			return;
		}
		if(!$("#shareCd_C").is(":checked") && !$("#shareCd_K").is(":checked") ){
			alert("<spring:message code="library.title.sharecd.select.type"/>");
			return;
		}
		var shareType =  $(':radio[name="shareCd"]:checked').val();
		$("#shareDivCd").val(shareType);
		process("addShare");
	}

	function delCnts(cntsCd) {
		if(!confirm("<spring:message code="library.message.contents.share.confirm.delete"/>")) {
			return;
		}
		var url = cUrl("/mng/library/clibShareCnts/removeShareMediaCnts");
		var data = {"cntsCd":cntsCd};
		$.getJSON(url, data, function(resultDTO){
			alert(resultDTO.message);
			if(resultDTO.result >= 0) {
				listCnts();
			} else {
				// 비정상 처리
			}
		});
	}

	function listCnts() {
		$("#listCntsArea").load(cUrl("/mng/library/clibMediaCnts/listShare"), {"cntsCd":"${clibMediaCntsVO.cntsCd}"});
	}

	function preview(cntsCd) {
		var url = cUrl("/mng/library/clibShareCnts/previewMediaPop")+"?cntsCd="+cntsCd;
		var winOption = "left=0,top=0,toolbar=no,location=no,directories=0,status=0,menubar=no,scrollbars=yes,resizable=no,width=800,height=480";
		var contentsWin = window.open(url, "contentsWin", winOption);
		contentsWin.focus();
	}

	function changeCtgr(divCd){
		$("#ctgrCd").val("");
		$("#ctgrNm").val("");
		$("#ctgrDrop").load(cUrl("/mng/library/clibMediaCnts/listCtgr"), {"divCd":divCd});
	}

</script>
