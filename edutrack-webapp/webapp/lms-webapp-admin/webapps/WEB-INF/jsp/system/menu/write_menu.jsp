<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>

	<form name="menuForm" id="menuForm" method="POST" onsubmit="return false">
	<input type="hidden" name="menuType" value="${vo.menuType}" />
	<input type="hidden" name="menuLvl" value="${vo.menuLvl}" />
	<input type="hidden" name="menuOdr" value="${vo.menuOdr}" />
	<input type="hidden" name="subCnt" value="${vo.subCnt}" />
	<input type="hidden" name="parMenuCd" value="${vo.parMenuCd}" />
	<input type="hidden" name="parMenuLvl" value="${vo.parMenuLvl}" />
	<input type="hidden" name="parMenuPath" value="${vo.parMenuPath}" />
	<input type="hidden" name="optnCtgrCd1" value="${vo.optnCtgrCd1}" />
	<input type="hidden" name="optnCtgrCd2" value="${vo.optnCtgrCd2}" />
	<input type="hidden" name="optnCtgrCd3" value="${vo.optnCtgrCd3}" />
	<input type="hidden" name="optnCtgrCd4" value="${vo.optnCtgrCd4}" />
	<c:if test="${vo.menuType ne 'LECT'}">
	<input type="hidden" name="optnCd1" value="${vo.optnCd1}" />
	<input type="hidden" name="optnCd2" value="${vo.optnCd2}" />
	<input type="hidden" name="optnCd3" value="${vo.optnCd3}" />
	<input type="hidden" name="optnCd4" value="${vo.optnCd4}" />
	</c:if>
	<table summary="<spring:message code="system.title.menu.manage" />" class="table table-bordered">
		<colgroup>
			<col width="25%">
			<col width="75%">
		</colgroup>
		<tr>
			<th scope="row"><spring:message code="system.title.menu.parent"/></th>
			<td>
				<c:if test="${paramform.parMenuNm eq 'ROOT' }"><spring:message code="system.title.menu.rootmenu"/></c:if>
				<c:if test="${paramform.parMenuNm ne 'ROOT' }">${vo.parMenuNm}</c:if>
			</td>
		</tr>
		<tr>
			<th scope="row"><label for="menuCd"><spring:message code="system.title.menu.code"/></label></th>
			<td>
				<c:if test="${gubun eq 'A'}">
				<div style="float:left">
					<input type="text" name="menuCd" id="menuCd" maxlength="10" required="required" title="<spring:message code="system.title.menu.code"/>"  onkeyup="isChkCharacter(this)" class="form-control input-sm"/>
				</div>
				<div style="float:left;margin-left:10px;">
					<label style="font-weight: normal;">
						<input type="checkbox" id="autoMakeYn" name="autoMakeYn" value="Y" onclick="autoMakeCd()" style="border:0" checked /><spring:message code="common.title.automake"/>
					</label>
				</div>
				</c:if>
				<c:if test="${gubun eq 'E'}">
				${vo.menuCd}
				<input type="hidden" name="menuCd" id="menuCd" value="${vo.menuCd}"/>
				</c:if>
			</td>
		</tr>
		<tr>
			<c:set var="defMenuName" value=""/>
			<c:forEach var="menuLang" items="${vo.menuLangList}">
				<c:if test="${LANG_DEFAULT eq menuLang.langCd}">
					<c:set var="defMenuName" value="${menuLang.menuNm}"/>
				</c:if>
			</c:forEach>
			<th scope="row"><label for="menuNm"><spring:message code="system.title.menu.name"/></label></th>
			<td>
				<input type="text" name="menuNm" id="menuNm" maxlength="50" required="required" title="<spring:message code="system.title.menu.name"/>" value="${defMenuName}" class="form-control input-sm" />
			</td>
		</tr>
		<c:forEach var="lang" items="${langList}">
			<c:if test="${LANG_DEFAULT ne lang}">
			<c:set var="menuName" value=""/>
			<c:forEach var="menuLang" items="${vo.menuLangList}">
				<c:if test="${menuLang.langCd eq lang}">
					<c:set var="menuName" value="${menuLang.menuNm}"/>
				</c:if>
			</c:forEach>
		<tr>
			<th scope="row"><label for="menuNm_${lang}"><spring:message code="system.title.menu.name"/> (${lang})</label></th>
			<td>
				<input type="text" name="menuNm_${lang}" id="menuNm_${lang}" maxlength="50" required="required" title="<spring:message code="system.title.menu.name"/> ${lang}" value="${menuName}" class="form-control input-sm" />
			</td>
		</tr>
			</c:if>
		</c:forEach>
		<c:if test="${vo.menuLvl == 1 || vo.parMenuLvl == 0}">
		<tr>
			<th scope="row"><label for="leftMenuImg"><spring:message code="system.title.menu.leftmenu.img"/></label></th>
			<td>
				<div class="input-group" style="width:200px;float:left;">
					<div class="input-group-btn btn-group">
  						<ul class="dropdown-menu" role="menu">
    						<li style="width:390px;padding-left:5px;overflow-x:hidden;overflow-y:auto;height:200px; ">
    						<c:forEach var="icon" items="${iconList}">
    							<div style="float:left;padding:5px;min-width:25px;">
    								<a href="javascript:setLeftMenuIcon('${icon.codeOptn}')"><i class="${icon.codeOptn}"></i></a>
    							</div>
    						</c:forEach>
    						</li>
  						</ul>
    					<button type="button" class="btn btn-default btn-sm dropdown-toggle" data-toggle="dropdown">
    						<span class="caret"></span>
  						</button>
  					</div>
  					<input type="text" name="leftMenuImg" id="leftMenuImg" maxlength="100" title="<spring:message code="system.title.menu.leftmenu.img"/>" value="${vo.leftMenuImg}" class="form-control input-sm"/>
				</div>
				<div style="float:left;margin-left:10px;line-height:30px;" id="leftMenuIcon">
				<c:if test="${not empty vo.leftMenuImg}">
					<i class="${vo.leftMenuImg}"></i>
				</c:if>
				</div>
			</td>
		</tr>
		</c:if>
		<tr>
			<th scope="row"><label for="menuUrl"><spring:message code="system.title.menu.url"/></label></th>
			<td>
				<input type="text" name="menuUrl" id="menuUrl" maxlength="150" title="<spring:message code="system.title.menu.url"/>" class="form-control input-sm" value="${vo.menuUrl}"/>
			</td>
		</tr>
		<tr>
			<th scope="row"><label for="menuDesc"><spring:message code="system.title.menu.info"/></label></th>
			<td>
				<textarea name="menuDesc" id="menuDesc" maxlength="2000" required="required" title="<spring:message code="system.title.menu.info"/>" class="form-control input-sm">${vo.menuDesc}</textarea>
			</td>
		</tr>
		<tr>
			<th scope="row"><spring:message code="common.title.useyn"/></th>
			<td>
				<label style="font-weight: normal;" ><input type="radio" name="useYn" value="Y" <c:if test="${vo.useYn eq 'Y'}">checked</c:if>/><spring:message code="common.title.useyn_y"/></label>
				<label style="font-weight: normal; margin-left:10px;"><input type="radio" name="useYn" value="N" <c:if test="${vo.useYn eq 'N'}">checked</c:if>/><spring:message code="common.title.useyn_n"/></label>
			</td>
		</tr>
		<tr>
			<th scope="row"><spring:message code="system.title.menu.sslyn"/></th>
			<td>
				<label style="font-weight: normal;" ><input type="radio" name="sslYn" value="Y" <c:if test="${vo.sslYn eq 'Y'}">checked</c:if>/><spring:message code="common.title.useyn_y"/></label>
				<label style="font-weight: normal; margin-left:10px;"><input type="radio" name="sslYn" value="N" <c:if test="${vo.sslYn eq 'N'}">checked</c:if>/><spring:message code="common.title.useyn_n"/></label>			
			</td>
		</tr>
		<c:if test="${menuForm.menuType eq 'HOME' && menuForm.parMenuLvl eq 0}">
		<tr>
			<th scope="row"><spring:message code="system.title.menu.topuseyn"/></th>
			<td>
				<label style="font-weight: normal;" ><input type="radio" name="topMenuYn" value="Y" <c:if test="${vo.topMenuYn eq 'Y'}">checked</c:if>/><spring:message code="system.title.menu.topuseyn_y"/></label>
				<label style="font-weight: normal; margin-left:10px;"><input type="radio" name="topMenuYn" value="N" <c:if test="${vo.topMenuYn eq 'N'}">checked</c:if>/><spring:message code="system.title.menu.topuseyn_n"/></label>
			</td>
		</tr>
		</c:if>
		<c:if test="${menuForm.menuType eq 'HOME' && not empty menuForm.menuUrl}">
		<tr>
			<th scope="row"><spring:message code="system.title.menu.footer.useyn"/></th>
			<td>
				<label style="font-weight: normal;" ><input type="radio" name="divLineUseYn" value="Y" <c:if test="${vo.divLineUseYn eq 'Y'}">checked</c:if>/><spring:message code="common.title.useyn_y"/></label>
				<label style="font-weight: normal; margin-left:10px;"><input type="radio" name="divLineUseYn" value="N" <c:if test="${vo.divLineUseYn eq 'N'}">checked</c:if>/><spring:message code="common.title.useyn_n"/></label>
			</td>
		</tr>
		</c:if>
		<c:if test="${menuForm.menuType eq 'LECT'}">
		<tr>
			<th scope="row"><spring:message code="system.title.menu.course.type"/></th>
			<td>
				<label style="font-weight: normal;" ><input type="radio" name="optnCd1" value="Y" <c:if test="${vo.optnCd1 eq 'Y'}">checked</c:if>/><spring:message code="system.title.menu.course.type.online"/></label>
				<label style="font-weight: normal; margin-left:10px;"><input type="radio" name="optnCd2" value="Y" <c:if test="${vo.optnCd2 eq 'Y'}">checked</c:if>/><spring:message code="system.title.menu.course.type.offline"/></label>
				<label style="font-weight: normal; margin-left:10px;"><input type="radio" name="optnCd3" value="Y" <c:if test="${vo.optnCd3 eq 'Y'}">checked</c:if>/><spring:message code="system.title.menu.course.type.blended"/></label>
				<label style="font-weight: normal; margin-left:10px;"><input type="radio" name="optnCd4" value="Y" <c:if test="${vo.optnCd4 eq 'Y'}">checked</c:if>/><spring:message code="system.title.menu.course.type.sangsi"/></label>
			</td>
		</tr>
		</c:if>
	</table>
	<div class="text-right">
		<c:if test="${gubun eq 'A' }">
		<a href="javascript:addMenu()" class="btn btn-primary btn-sm"><spring:message code="button.add"/></a>
		</c:if>
		<c:if test="${gubun eq 'E' }">
		<a href="javascript:editMenu()" class="btn btn-primary btn-sm"><spring:message code="button.add"/></a>
		<a href="javascript:delMenu()" class="btn btn-warning btn-sm"><spring:message code="button.delete"/></a>
		</c:if>
		<a href="javascript:parent.modalBoxClose();" class="btn btn-default btn-sm"><spring:message code="button.close"/></a>
	</div>
	</form>

<script type="text/javascript">
	// 페이지 초기화
	$(document).ready(function() {
		autoMakeCd();
	});

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		if(!validate(document.menuForm)) return;
		$('#menuForm').attr("action",cmd);
		$('#menuForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultVO) {
		alert(resultVO.message);
		if(resultVO.result >= 0) {
			// 정상 처리
			parent.listMenu();
			parent.modalBoxClose();
		} else {
			// 비정상 처리
		}
	}

	/**
	 * 메뉴 추가
	 */
	function addMenu() {
		process("/adm/system/menu/addMenu");	// cmd
	}


	/**
	 * 메뉴 수정
	 */
	function editMenu() {
		process("/adm/system/menu/editMenu");	// cmd
	}

	/**
	 * 메뉴 삭제
	 */
	function delMenu() {
		var f = document.menuForm;
		if(f["subCnt"].value > 0) {
			alert('<spring:message code="system.message.menu.alert.delete.sub"/>');
		} else {
			if(confirm('<spring:message code="system.message.menu.confirm.delete"/>')) {
				process("/adm/system/menu/removeMenu.do");	// cmd
			} else {
				return;
			}
		}
	}

	/**
	 * 과정 코드 자동 입력 관련 처리
	 */
	function autoMakeCd() {
		<c:if test="${gubun eq 'A'}">
		if($("#autoMakeYn").is(":checked")) {
			$("#menuCd").val("<spring:message code="common.title.automake"/>");
			$("#menuCd").css("background-color", "#f3f3f3");
			$("#menuCd").attr("readonly", true);
		} else {
			$("#menuCd").val("");
			$("#menuCd").css("background-color", "#ffffff");
			$("#menuCd").attr("readonly", false);
		}
		</c:if>
	}

	function setLeftMenuIcon(icon) {
		$("#leftMenuImg").val(icon);
		var iconStr = "<i class='"+icon+"'></i>";
		$("#leftMenuIcon").html(iconStr);
	}
</script>
