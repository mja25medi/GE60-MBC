<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="gubun" value="${gubun}"/>
<c:set var="clibOlcCntsVO" value="${vo}"/>
<mhtml:home_html>
<mhtml:home_head titleName="${MENUNAME}">

</mhtml:home_head>
<style>
.table {font-size: 14px;!impotant }
</style>
<body cssTag="background-color:#fff;">
	<form id="clibOlcCntsForm" name="clibOlcCntsForm" onsubmit="return false" >
	<input type="hidden" name="cntsCd" value="${vo.cntsCd}" />
	<input type="hidden" name="shareDivCd" id="shareDivCd" value="${vo.shareDivCd}" />
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
					<%-- <meditag:codeselectbox category="CCL_CD" fieldName="clibMediaCntsVO.cclCd" fieldId="cclCd" value="clibMediaCntsVO.cclCd" class="form-control input-sm" /> --%>
					<meditag:orgcodeselectbox category="CCL_CD" orgCd="${USER_ORGCD }" fieldName="cclCd" fieldId="cclCd" value="clibMediaCntsVO.cclCd" class="form-control input-sm" />
				</td>
			</tr>
			<tr>
				<th scope="row"><label for="ctgrNm"><spring:message code="library.title.contents.share.div"/></label></th>
				<td>
					<label style="font-weight: normal;" ><input type="checkbox" name="shareCd" value="CNTS" id="shareCd_C"> <spring:message code="olc.title.contents.share"/></label>
					<label style="font-weight: normal; margin-left:10px;"><input type="checkbox" name="shareCd" value="KNOW" id="shareCd_K"><spring:message code="olc.title.knowledge.share"/></label>
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
			$('#clibOlcCntsForm').attr("action","/home/library/clibOlcCnts/" + cmd);
			$('#clibOlcCntsForm').ajaxSubmit(processCallback);
		}

		/**
		 * 처리 결과 표시 콜백
		 */
		function processCallback(resultDTO) {
			alert(resultDTO.message);
			if(resultDTO.result >= 0) {
				// 정상 처리
				listCnts();
				$("#shareCd_C").attr('checked', false) ;
				$("#shareCd_K").attr('checked', false) ;
			} else {
				// 비정상 처리
			}
		}

		function addCnts() {
			if($("#cntsNm").val() == ""){
				alert("<spring:message code="library.message.contents.alert.input.contents.name"/>");
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
			var url = cUrl("/home/library/clibShareCnts/removeShareOlcCnts");
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
			$("#listCntsArea").load(cUrl("/home/library/clibOlcCnts/listShare"), {"cntsCd":"${vo.cntsCd}"});
		}

		function preview(userNo, cntsCd, cntNm) {
			var url = cUrl("/home/library/clibShareCnts/previewOlcMain")+"?cntsCd="+cntsCd;
			var winOption = "left=0,top=0,toolbar=no,location=no,directories=0,status=0,menubar=no,scrollbars=no,resizable=no,width=1100,height=775";
			var contentsPage = window.open(url, "contentsWin", winOption);
			contentsPage.focus();
		}

	</script>
</body>
</mhtml:home_html>