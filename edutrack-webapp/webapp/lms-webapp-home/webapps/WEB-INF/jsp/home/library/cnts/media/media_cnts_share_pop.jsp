<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="clibMediaCntsVO" value="${clibMediaCntsVO}"/>
<mhtml:home_html>
<mhtml:home_head titleName="${MENUNAME}">

</mhtml:home_head>
<style>
.table {font-size: 14px;!impotant }
</style>
<body cssTag="background-color:#fff;">
	<form id="clibMediaCntsForm" name="clibMediaCntsForm" onsubmit="return false"">
	<input type="hidden" name="cntsCd" />
	<input type="hidden" name="shareDivCd"  id="shareDivCd"/>
	<c:if test="${clibMediaCntsVO.useYn eq 'Y' }">
		<table summary='<spring:message code="library.title.contents.share"/>' class="table table-striped table-bordered wordbreak">
			<colgroup>
				<col style="width:30%"/>
				<col style="width:70%"/>
			</colgroup>
			<tr>
				<th scope="row"><label for="ctgrNm"><spring:message code="library.title.contents.name"/></label></th>
				<td>
					<input type="text" name="clibMediaCntsVO.cntsNm" id="cntsNm" value="${clibMediaCntsVO.cntsNm }" class="form-control input-sm" id="cntsNm" maxlength="50" />
				</td>
			</tr>
			<tr>
				<th scope="row"><label for="ctgrNm"><spring:message code="library.title.contents.ccl"/></label></th>
				<td>
					<%-- <meditag:codeselectbox category="CCL_CD" fieldName="clibMediaCntsVO.cclCd" fieldId="cclCd" value="clibMediaCntsVO.cclCd" class="form-control input-sm" /> --%>
					<meditag:orgcodeselectbox category="CCL_CD" orgCd="${USER_ORGCD }" fieldName="clibMediaCntsVO.cclCd" fieldId="cclCd" value="clibMediaCntsVO.cclCd" class="form-control input-sm" />
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
			$('#clibMediaCntsForm').attr("action","/home/library/clibMediaCnts/"+cmd);
			$('#clibMediaCntsForm').ajaxSubmit(processCallback);
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
			var url = cUrl("/home/library/clibShareCnts/removeShareMediaCnts");
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
			$("#listCntsArea").load(cUrl("/home/library/clibMediaCnts/listShare"), {"cntsCd":"${clibMediaCntsVO.cntsCd}"});
		}

		function preview(cntsCd) {
			var url = cUrl("/home/library/clibShareCnts/previewMediaPop")+"?cntsCd="+cntsCd;
			var winOption = "left=0,top=0,toolbar=no,location=no,directories=0,status=0,menubar=no,scrollbars=yes,resizable=no,width=800,height=480";
			var contentsWin = window.open(url, "contentsWin", winOption);
			contentsWin.focus();
		}
	</script>
</body>
</mhtml:home_html>