<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="gubun" value="${gubun}"/>
<c:set var="clibOlcCntsVO" value="${vo}"/>
<style>
.table {font-size: 14px;!impotant }
</style>
	<form id="clibOlcCntsForm" name="clibOlcCntsForm" onsubmit="return false" >
	<input type="hidden" name="cntsCd" value="${vo.cntsCd}" />
	<input type="hidden" name="shareDivCd" id="shareDivCd"/>
	<c:if test="${vo.useYn eq 'Y' }">
		<table summary='<spring:message code="library.title.contents.share"/>' class="table table-striped table-bordered wordbreak">
			<colgroup>
				<col style="width:30%"/>
				<col style="width:70%"/>
			</colgroup>
			<tr>
				<th scope="row"><label for="ctgrNm"><spring:message code="library.title.contents.name"/></label></th>
				<td>
					<input type="text" name="cntsNm" id="cntsNm" value="${vo.cntsNm }" class="form-control input-sm" id="cntsNm" maxlength="50" />

				</td>
			</tr>
			<tr>
				<th scope="row"><label for="ctgrNm"><spring:message code="library.title.contents.ccl"/></label></th>
				<td>
					<select name="cclCd" id="cclCd" class="form-control input-sm" >
						<c:if test="${not empty cclCode}">
							<option value=""><spring:message code="board.title.editor.table.select.line"/></option>
						<c:forEach items="${cclCode}" var="item">
							<c:set var="codeName" value="${item.codeNm}"/>
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
							<button type="button" class="btn btn-default btn-sm dropdown-toggle" data-toggle="dropdown" style="padding:11.5px 10px">
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
		<c:if test="${vo.useYn eq 'Y' }"><button class="btn btn-primary btn-sm" onclick="addCnts()" ><spring:message code="button.add.share"/></button></c:if>
		<c:if test="${vo.useYn eq 'N' }"><p style="float: left; padding-left: 10px;"><spring:message code="library.message.contents.olc.share.useyn"/></p></c:if>
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
			if(!validate(document.clibOlcCntsForm)) return;
			$('#clibOlcCntsForm').attr("action","/mng/library/clibOlcCnts/" + cmd);
			$('#clibOlcCntsForm').ajaxSubmit(processCallback);
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
			var url = cUrl("/mng/library/clibShareCnts/removeShareOlcCnts");
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
			$("#listCntsArea").load(cUrl("/mng/library/clibOlcCnts/listShare"), {"cntsCd":"${vo.cntsCd}"});
		}

		function preview(userNo, cntsCd, cntNm) {
			var url = cUrl("/mng/library/clibShareCnts/previewOlcMain")+"?clibShareOlcCntsVO.cntsCd="+cntsCd;
			var winOption = "left=0,top=0,toolbar=no,location=no,directories=0,status=0,menubar=no,scrollbars=no,resizable=no,width=1100,height=775";
			var contentsPage = window.open(url, "contentsWin", winOption);
			contentsPage.focus();
		}

		function changeCtgr(divCd){
			$("#ctgrCd").val("");
			$("#ctgrNm").val("");
			$("#ctgrDrop").load(cUrl("/mng/library/clibOlcCnts/listCtgr"),  {"divCd":divCd});
		}

	</script>
