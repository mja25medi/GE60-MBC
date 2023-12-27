<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>

	<form name="orgMenuForm" id="orgMenuForm" onsubmit="return false" method="POST">
	<input type="hidden" name="orgCd" id="orgCd" value="${vo.orgCd}"/>
	<input type="hidden" name="autoMakeYn" id="autoMakeYn" value="Y"/>
	<input type="hidden" name="menuType" id="menuType" value="${vo.menuType}"/>
	<input type="hidden" name="menuCd" id="menuCd" value="${vo.menuCd}"/>
	<input type="hidden" name="menuLvl" id="menuLvl" value="${vo.menuLvl}"/>
	<input type="hidden" name="menuOdr" id="menuOdr" value="${vo.menuOdr}"/>
	<input type="hidden" name="parMenuCd" id="parMenuCd" value="${vo.parMenuCd}"/>
	<input type="hidden" name="sslYn" id="sslYn" value="N"/>
	<input type="hidden" name="subCnt" id="subCnt" />
	<input type="hidden" name="optnCtgrCd1" id="optnCtgrCd1" value="${vo.optnCtgrCd1}"/>
	<input type="hidden" name="optnCtgrCd2" id="optnCtgrCd2" value="${vo.optnCtgrCd2}"/>
	<input type="hidden" name="optnCtgrCd3" id="optnCtgrCd3" value="${vo.optnCtgrCd3}"/>
	<input type="hidden" name="optnCtgrCd4" id="optnCtgrCd4" value="${vo.optnCtgrCd4}"/>
	<c:if test="${vo.menuType ne 'LECT'}">
	<input type="hidden" name="optnCd1" id="optnCd1" value="${vo.optnCd1}"/>
	<input type="hidden" name="optnCd2" id="optnCd2" value="${vo.optnCd2}"/>
	<input type="hidden" name="optnCd3" id="optnCd3" value="${vo.optnCd3}"/>
	<input type="hidden" name="optnCd4" id="optnCd4" value="${vo.optnCd4}"/>
	</c:if>
	<div style="width:100%">
		<div style="width:50%;float:left;padding:5px;">
			<table summary="<spring:message code="system.title.menu.manage" />" class="table table-bordered">
				<colgroup>
					<col width="32%">
					<col width="68%">
				</colgroup>
				<tr>
					<th scope="row"><spring:message code="system.title.menu.parent"/></th>
					<td>
						<c:if test="${vo.parMenuNm eq 'ROOT' }"><spring:message code="system.title.menu.rootmenu"/></c:if>
						<c:if test="${vo.parMenuNm ne 'ROOT' }">${vo.parMenuNm}</c:if>
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
						<input type="text" name="menuNm" id="menuNm" maxlength="50" required="required" title="<spring:message code="system.title.menu.name"/>" class="form-control input-sm" value="${defMenuName}"/>
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
						<input type="text" name="menuNm_${lang}" id="menuNm_${lang}" maxlength="50" required="required" title="<spring:message code="system.title.menu.name"/> ${lang}" class="form-control input-sm" value="${menuName}"/>
					</td>
				</tr>
					</c:if>
				</c:forEach>
				<c:if test="${vo.parMenuLvl == 0}">
				<tr>
					<th scope="row"><label for="leftMenuImg"><spring:message code="system.title.menu.leftmenu.img"/></label></th>
					<td>
						<div class="input-group" style="width:200px;float:left;">
							<div class="input-group-btn btn-group">
		  						<ul class="dropdown-menu" role="menu">
		    						<li style="width:600px;padding-left:5px;height:200px;overflow-y:auto;">
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
		  					<input type="text" name="leftMenuImg" id="leftMenuImg" maxlength="100" title="<spring:message code="system.title.menu.leftmenu.img"/>" class="form-control input-sm" value="${vo.leftMenuImg}"/>
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
						<div class="input-group">
							<input type="text" name="menuUrl" id="menuUrl" maxlength="150" readonly="readonly" title="<spring:message code="system.title.menu.url"/>" class="form-control input-sm" value="${vo.menuUrl}"/>
							<span class="input-group-addon" style="cursor: pointer;" onclick="javascript:delUrl()"><i class="fa fa-times "></i></span>
						</div>
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
						<label style="font-weight: normal;">
							<input type="radio" name="useYn" value="Y" <c:if test="${vo.useYn eq 'Y' }">checked</c:if>/> <spring:message code="common.title.useyn_y"/>
						</label>
						<label style="font-weight: normal; margin-left:10px;">
							<input type="radio" name="useYn" value="N" <c:if test="${vo.useYn eq 'N' }">checked</c:if>/> <spring:message code="common.title.useyn_n"/>
						</label>
					</td>
				</tr>
				<c:if test="${orgMenuForm.menuType eq 'HOME' && orgMenuForm.parMenuLvl eq 0}">
				<tr>
					<th scope="row"><spring:message code="system.title.menu.topuseyn"/></th>
					<td>
						<label style="font-weight: normal;">
							<input type="radio" name="topMenuYn" value="Y" <c:if test="${vo.topMenuYn eq 'Y' }">checked</c:if>/> <spring:message code="system.title.menu.topuseyn_y"/>
						</label>
						<label style="font-weight: normal; margin-left:10px;">
							<input type="radio" name="topMenuYn" value="N" <c:if test="${vo.topMenuYn eq 'N' }">checked</c:if>/> <spring:message code="system.title.menu.topuseyn_n"/>
						</label>
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
		</div>
		<div style="width:50%;float:left;padding:5px;">
			<ul class="nav nav-tabs" id="tabMenu">
  				<li class="active"><a href="javascript:onclickTab(0)"><spring:message code="system.title.menu.tab.board"/></a></li>
  				<li><a href="javascript:onclickTab(1)"><spring:message code="system.title.menu.tab.page"/></a></li>
			</ul>
			<div id="prgmListArea" style="width:100%;"></div>
		</div>
	</div>

	</form>

<script type="text/javascript">
	// 페이지 초기화
	$(document).ready(function() {
		$('#tabMenu a').click(function (e) {
			  $(this).tab('show');
			})
		onclickTab(0);
	});

	function onclickTab(tab) {
		if(tab == 0) boardList();
		else pageList();
	}

	function boardList(page) {
		if(page == undefined) page = 1;
		$("#prgmListArea").load(cUrl("/adm/org/menu/listBbs"),{"pageIndex":page});
	}

	function pageList(page) {
		if(page == undefined) page = 1;
		$("#prgmListArea").load(cUrl("/adm/org/menu/listPage"),{"pageIndex":page});
	}

	function selectBbs(bbsNm, bbsCd) {
		$("#menuNm").val(bbsNm);
		$("#menuUrl").val("/home/board/bbs/atcl/list?bbsCd="+bbsCd);
		$("#optnCtgrCd1").val("BBS");
		$("#optnCtgrCd2").val(bbsCd);
	}

	function selectPage(pageNm, pageCd) {
		$("#menuNm").val(pageNm);
		$("#menuUrl").val("/home/org/page/read?pageCd="+pageCd);
		$("#optnCtgrCd1").val("PAGE");
		$("#optnCtgrCd2").val(pageCd);
	}

	function delUrl() {
		$("#optnCtgrCd1").val("");
		$("#optnCtgrCd2").val("");
		$("#menuUrl").val("");
	}

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		$('#orgMenuForm').attr("action",cmd);
		$('#orgMenuForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultVO) {
		alert(resultVO.message);
		if(resultVO.result >= 0) {
			// 정상 처리
			parent.menuList();
			parent.modalBoxClose();
		} else {
			// 비정상 처리
		}
	}

	/**
	 * 메뉴 추가
	 */
	function addMenu() {
		if(!validate(document.orgMenuForm)) return;
		process("/adm/org/menu/addMenu");	// cmd
	}


	/**
	 * 메뉴 수정
	 */
	function editMenu() {
		if(!validate(document.orgMenuForm)) return;
		process("/adm/org/menu/editMenu");	// cmd
	}

	/**
	 * 메뉴 삭제
	 */
	function delMenu() {
		var subCnt = $("#subCnt").val();
		if(subCnt > 0) {
			alert('<spring:message code="system.message.menu.alert.delete.sub"/>');
		} else {
			if(confirm('<spring:message code="system.message.menu.confirm.delete"/>')) {
				process("/adm/org/menu/removeMenu");	// cmd
			} else {
				return;
			}
		}
	}

	function setLeftMenuIcon(icon) {
		$("#leftMenuImg").val(icon);
		var iconStr = "<i class='"+icon+"'></i>";
		$("#leftMenuIcon").html(iconStr);
	}
</script>
