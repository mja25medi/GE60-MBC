<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="gubun" value="${gubun}"/>
<c:set var="clibShareOlcCntsVO" value="${vo}"/>
<mhtml:mng_html>
<mhtml:mng_head titleName="${MENUNAME}">

</mhtml:mng_head>

<mhtml:frm_body cssTag="background-color:#fff;">
	<form id="clibShareCntsForm" name="clibShareCntsForm" onsubmit="return false" >
	<input type="hidden" name="reqSn" value="${vo.reqSn }" />
	<input type="hidden" name="cntsCd" value="${vo.cntsCd }" />
	<table summary='<spring:message code="library.title.contents.share"/>' class="table table-striped table-bordered wordbreak">
		<colgroup>
			<col style="width:30%"/>
			<col style="width:70%"/>
		</colgroup>
		<tr>
			<th scope="row"><spring:message code="library.title.contents.name"/></th>
			<td>
				${clibShareOlcCntsVO.cntsNm }
			</td>
		</tr>
		<tr>
			<th scope="row"><spring:message code="library.title.contents.ccl"/></th>
			<td>
				<meditag:orgcodename code="${clibShareOlcCntsVO.cclCd}" category="CCL_CD" orgCd="${USER_ORGCD }"/>
			</td>
		</tr>
		<tr>
			<th scope="row"><label for="ctgrNm"><spring:message code="library.title.contents.category"/></label></th>
			<td>
				<div class="input-group">
					<div class="input-group-btn btn-group">
						<button type="button" class="btn btn-default btn-sm dropdown-toggle" data-toggle="dropdown" style="padding:11.5px 10px">
							<span class="caret"></span>
						</button>
						<ul class="dropdown-menu" role="menu" id="ctgrDrop" style="max-height:300px; min-width:300px; overflow:auto;">
						<c:forEach var="item" items="${ctgrList}">
							<c:url var="typeImgPath" value="/img/framework/icon/icon_contents.gif" />
							<c:set var="typeImgName" value="contents" />
							<c:if test="${fn:length(item.subList) > 0}">
								<c:url var="typeImgPath" value="/img/framework/icon/icon_category.gif" />
								<c:set var="typeImgName" value="category" />
							</c:if>
							<li style="padding-left:0px;"><a href="javascript:setCtgr('${item.ctgrCd}','${item.ctgrNm }')"><img src="${typeImgPath}" alt="${typeImgName}"> ${item.ctgrNm}</a></li>
							<c:forEach var="item1" items="${item.subList}">
								<c:url var="typeImgPath" value="/img/framework/icon/icon_contents.gif" />
								<c:set var="typeImgName" value="contents" />
								<c:if test="${fn:length(item1.subList) > 0}">
									<c:url var="typeImgPath" value="/img/framework/icon/icon_category.gif" />
									<c:set var="typeImgName" value="category" />
								</c:if>
							<li style="padding-left:15px;"><a href="javascript:setCtgr('${item1.ctgrCd}','${item1.ctgrNm }')"><img src="${typeImgPath}" alt="${typeImgName}"> ${item1.ctgrNm}</a></li>
								<c:forEach var="item2" items="${item1.subList}">
									<c:url var="typeImgPath" value="/img/framework/icon/icon_contents.gif" />
									<c:set var="typeImgName" value="contents" />
									<c:if test="${fn:length(item2.subList) > 0}">
										<c:url var="typeImgPath" value="/img/framework/icon/icon_category.gif" />
										<c:set var="typeImgName" value="category" />
									</c:if>
							<li style="padding-left:30px;"><a href="javascript:setCtgr('${item2.ctgrCd}','${item2.ctgrNm }')"><img src="${typeImgPath}" alt="${typeImgName}"> ${item2.ctgrNm}</a></li>
									<c:forEach var="item3" items="${item2.subList}">
										<c:url var="typeImgPath" value="/img/framework/icon/icon_contents.gif" />
										<c:set var="typeImgName" value="contents" />
										<c:if test="${fn:length(item3.subList) > 0}">
											<c:url var="typeImgPath" value="/img/framework/icon/icon_category.gif" />
											<c:set var="typeImgName" value="category" />
										</c:if>
							<li style="padding-left:45px;"><a href="javascript:setCtgr('${item3.ctgrCd}','${item3.ctgrNm }')"><img src="${typeImgPath}" alt="${typeImgName}"> ${item3.ctgrNm}</a></li>
										<c:forEach var="item4" items="${item3.subList}">
											<c:url var="typeImgPath" value="/img/framework/icon/icon_contents.gif" />
											<c:set var="typeImgName" value="contents" />
											<c:if test="${fn:length(item4.subList) > 0}">
												<c:url var="typeImgPath" value="/img/framework/icon/icon_category.gif" />
												<c:set var="typeImgName" value="category" />
											</c:if>
							<li style="padding-left:60px;"><a href="javascript:setCtgr('${item4.ctgrCd}','${item4.ctgrNm }')"><img src="${typeImgPath}" alt="${typeImgName}"> ${item4.ctgrNm}</a></li>
										</c:forEach>
									</c:forEach>
								</c:forEach>
							</c:forEach>
						</c:forEach>
						</ul>
					</div>
					<input type="text" name="ctgrNm" id="ctgrNm" class="form-control input-sm" style="background-color:#ffffff;" readonly="readonly" value="${clibShareOlcCntsVO.ctgrNm }"/>
					<input type="hidden" name="clibShareOlcCntsVO.ctgrCd" id="ctgrCd" value="${clibShareOlcCntsVO.ctgrCd }">
				</div>
			</td>
		</tr>
		<tr>
			<th scope="row"><label for="ctgrNm"><spring:message code="library.title.contents.olc.page.media.share"/></label></th>
			<td>
				<select name="clibShareOlcCntsVO.shareReqStsCd" id="shareReqStsCd" class="form-control input-sm">
					<option value="01" <c:if test="${clibShareOlcCntsVO.shareReqStsCd eq '01' }">selected</c:if>><spring:message code="library.title.sharecd.01"/></option>
					<option value="03" <c:if test="${clibShareOlcCntsVO.shareReqStsCd eq '03' }">selected</c:if>><spring:message code="library.title.sharecd.03"/></option>
					<option value="04" <c:if test="${clibShareOlcCntsVO.shareReqStsCd eq '04' }">selected</c:if>><spring:message code="library.title.sharecd.04"/></option>
				</select>
			</td>
		</tr>
	</table>
	<div class="text-right" style="margin-bottom:10px;">
		<button class="btn btn-primary btn-sm" onclick="updateShareCd()" ><spring:message code="button.add"/></button>
		<button class="btn btn-default btn-sm" onclick="parent.modalBoxClose()" ><spring:message code="button.close"/></button>
	</div>
	<input type="submit" value="submit" style="display:none" />
	</form>
	<script type="text/javascript">
		$(document).ready(function() {
		});

		function setCtgr(ctgrCd, ctgrNm) {
			$("#ctgrCd").val(ctgrCd);
			$("#ctgrNm").val(ctgrNm);
		}

		/**
		 * 서브밋 처리
		 */
		function process(cmd) {
			if(!validate(document.clibShareCntsForm)) return;
			$('#clibShareCntsForm').attr("action", "/mng/library/clibShareCnts/" + cmd);
			$('#clibShareCntsForm').ajaxSubmit(processCallback);
		}

		/**
		 * 처리 결과 표시 콜백
		 */
		function processCallback(resultDTO) {
			alert(resultDTO.message);
			if(resultDTO.result >= 0) {
				// 정상 처리
				parent.listPageing();
				parent.modalBoxClose()
			} else {
				// 비정상 처리
			}
		}

		function updateShareCd(){
			if($("#cntsNm").val() == ""){
				alert("<spring:message code="library.message.contents.alert.input.contents.name"/>");
				return;
			}
			if($("#shareReqStsCd option:selected").val() != "04") {
				if($("#ctgrCd").val() == "") {
					alert("<spring:message code="library.message.contents.category.select.category"/>");
					return;
				}
			}
			process("manageShareOlc");
		}


	</script>
</mhtml:frm_body>
</mhtml:mng_html>