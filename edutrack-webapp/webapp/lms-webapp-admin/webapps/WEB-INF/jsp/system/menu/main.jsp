<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>

			<form name="menuForm" id="menuForm" onsubmit="return false">
			<input type="hidden" name="chkAllList" value="N">
			<input type="hidden" name="chkAllWrite" value="N">
			<input type="hidden" name="menuType" />
			<input type="hidden" name="authGrpCd" />
			<input type="hidden" name="viewAuthArray" />
			<input type="hidden" name="creAuthArray" />
			</form>
			<mhtml:title title="${MENUNAME}" location="${MENUPATH}"/>
			<section class="content">
				<div class="row" id="content">
					<div class="box">
						<div class="box-body">
							<div class="col-md-4">
								<form name="Input" class="form-inline">
								<div class="input-group" style="width:100%;">
									<span class="input-group-addon"><spring:message code="system.title.menu.auth"/></span>
									<select class="form-control input-sm" name="menuType" id="menuType" onChange="listAuthGroup()">
										<c:forEach var="code" items="${codeList}">
											<c:set var="codeName" value="${code.codeNm}"/>
											<c:forEach var="codeLang" items="${code.codeLangList}">
												<c:if test="${codeLang.langCd eq LOCALEKEY }"><c:set var="codeName" value="${codeLang.codeNm}"/></c:if>
											</c:forEach>
										<option value="${code.codeCd}">${codeName}</option>
										</c:forEach>
									</select>
									<div class="input-group-btn text-right">
										<a class="btn btn-primary btn-sm" href="javascript:authGroupWrite()"><spring:message code="button.write.auth" /></a>
									</div>
								</div>
								<input type="submit" value="submit" style="display:none" />
								</form>
								<div id="authList" style="margin-top:5px;"></div>
							</div>
							<div class="col-md-8">
								<div class="input-group">
									<div style="float:left;line-height:30px;">
										<span id="authGroupTitle"></span>
									</div>
									<div class="input-group-btn text-right;">
										<a class="btn btn-default btn-sm" href="javascript:openAll()"><i class="fa fa-plus-square-o "></i></a>
										<a class="btn btn-default btn-sm" href="javascript:closeAll()"><i class="fa fa-minus-square-o "></i></a>
										<a class="btn btn-primary btn-sm" href="javascript:menuWrite()"><spring:message code="button.write.menu" /></a>
										<a class="btn btn-info btn-sm" href="javascript:menuSort()"><spring:message code="button.sort" /></a>
										<a class="btn btn-primary btn-sm" href="javascript:authMenuSave()"><spring:message code="button.write.authmenu" /></a>
									</div>
								</div>
								<div id="menuList" style="margin-top:5px;"></div>
							</div>
						</div>
					</div>
				</div>
			</section>

<script type="text/javascript">

	var modalBox = null;
	var ItemDTO = new Object();
	var MenuType = "";

	$(document).ready(function() {
		modalBox = new $M.ModalDialog({
			"modalid" : "modal1"
		});

		listAuthGroup();
	});

	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}

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
			listMenu();
		} else {
			// 비정상 처리
		}
	}

	/**
	 * 시스템 권한 그룹 목록 조회
	 */
	function listAuthGroup() {
		var menuType = $("#menuType option:selected").val();
		ItemDTO.authGrpCd = "";
		ItemDTO.authGrpNm = "";
		//displayWorkProgress();
		$('#authList')
			.load(
				cUrl("/adm/system/menu/listAuthGrp"),
				{"menuType":menuType},
				function() {}
			);
		listMenu();
	}

	/**
	 * 시스템 분류 코드 셋팅
	 */
	function setAuthGroupCode(authGrpCd, authGrpNm) {
		ItemDTO.authGrpCd = authGrpCd;
		ItemDTO.authGrpNm = authGrpNm;
		listMenu();
	}

	/**
	 * 시스템 메뉴 목록 조회
	 */
	function listMenu() {
		var menuType = $("#menuType option:selected").val();
		ItemDTO.menuCd = "";
		ItemDTO.menuNm = "";
		//displayWorkProgress();
		var title = "&nbsp;:&nbsp;&nbsp;<b>"+ItemDTO.authGrpNm+"</b>";
	 	$("#authGroupTitle").show().html(title);
		$('#menuList')
			.load(
				cUrl("/adm/system/menu/listMenu"),
				{"menuType":menuType, "authGrpCd":ItemDTO.authGrpCd},
				function() {}
			);
	}


	/**
	 * 권한 그룹  등록 폼
	 */
	function authGroupWrite() {
		var menuType = $("#menuType option:selected").val();
		var addContent  = "<iframe id='addAuthGroupFrame' name='addAuthGroupFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='no' src='<c:url value="/adm/system/menu/addFormAuthGrpPop"/>"
			+ "?menuType="+menuType+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(500, 400);
		modalBox.setTitle("<spring:message code="system.title.menu.write.auth"/>");
		modalBox.show();
	}

	/**
	 * 권한 그룹 수정 폼
	 */
	function authGroupEdit(authGrpCd) {
		var menuType = $("#menuType option:selected").val();
		var addContent = "<iframe id='addAuthGroupFrame' name='addAuthGroupFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='no' src='<c:url value="/adm/system/menu/editFormAuthGrpPop"/>"
			+ "?menuType="+menuType+"&authGrpCd="+authGrpCd+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(500, 400);
		modalBox.setTitle("<spring:message code="system.title.menu.edit.auth"/>");
		modalBox.show();
	}

	/**
	 * 메뉴 등록 폼
	 */
	function menuWrite() {
		var menuType = $("#menuType option:selected").val();
		var addContent  = "<iframe id='addMenuFrame' name='addMenuFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/adm/system/menu/addFormMenuPop"/>"
			+ "?menuType="+menuType+"&parMenuCd='/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(600, 620);
		modalBox.setTitle("<spring:message code="system.title.menu.write"/>");
		modalBox.show();
	}

	/**
	 * 서브 메뉴 등록 폼
	 */
	function subMenuWrite(parMenuCd) {
		var menuType = $("#menuType option:selected").val();
		var addContent  = "<iframe id='addMenuFrame' name='addMenuFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/adm/system/menu/addFormSubMenuPop"/>"
			+ "?menuType="+menuType+"&parMenuCd="+parMenuCd+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(600, 620);
		modalBox.setTitle("<spring:message code="system.title.menu.write"/>");
		modalBox.show();
	}

	/**
	 * 메뉴 수정 폼
	 */
	function menuEdit(menuCd) {
		var menuType = $("#menuType option:selected").val();
		var addContent = "<iframe id='addMenuFrame' name='addMenuFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/adm/system/menu/editFormMenuPop"/>"
			+ "?menuType="+menuType+"&menuCd="+menuCd+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(600, 620);
		modalBox.setTitle("<spring:message code="system.title.menu.edit"/>");
		modalBox.show();
	}

	/**
	 * 메뉴 순서 변경 폼
	 */
	function menuSort() {
		var menuType = $("#menuType option:selected").val();
		var addContent = "<iframe id='sortMenuFrame' name='sortMenuFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='no' src='<c:url value="/adm/system/menu/sortFormMenuPop"/>"
			+ "?menuType="+menuType+"&menuCd="+ItemDTO.menuCd+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(310, 310);
		modalBox.setTitle("<spring:message code="system.title.menu.sort"/>");
		modalBox.show();
	}

	/**
	 * 권한 메뉴 등록
	 */
	function authMenuSave() {
		var menuType = $("#menuType option:selected").val();
		if(ItemDTO.authGrpCd == "") {
			alert("<spring:message code="system.message.menu.select.auth"/>");
			return;
		} else {
			var viewList = document.List.viewAuth;
			var creList = document.List.creAuth;
			var viewAuthArray = "";
			var creAuthArray = "";
			for(var i=0; i<viewList.length;i++) {
				if(viewList[i].checked == true) viewAuthArray = viewAuthArray+"|"+viewList[i].value;
			}
			for(var i=0; i<creList.length;i++) {
				if(creList[i].checked == true) creAuthArray = creAuthArray+"|"+creList[i].value;
			}
			var fm = document.menuForm;
			fm["menuType"].value = menuType;
			fm["authGrpCd"].value = ItemDTO.authGrpCd;
			fm["viewAuthArray"].value = viewAuthArray;
			fm["creAuthArray"].value = creAuthArray;
			process("/adm/system/menu/saveAuthMenu.do");	// cmd
		}
	}

	/**
	 * 읽기 권한 체크 All
	 */
	function checkAllList() {
		var f = document.menuForm;
		var listObj = document.getElementsByName("viewAuth");
		var checkYn = f.chkAllList.value;
		for(var i=0; i < listObj.length; i++) {
			if(checkYn == "Y") listObj[i].checked = false;
			else listObj[i].checked = true;
		}
		if(checkYn == "Y") f.chkAllList.value = "N";
		else f.chkAllList.value = "Y";
	}

	function checkAllWrite() {
		var f = document.menuForm;
		var listObj = document.getElementsByName("creAuth");
		var checkYn = f.chkAllWrite.value;
		//alert(listObj.length);
		for(var i=0; i < listObj.length; i++) {
			if(checkYn == "Y") listObj[i].checked = false;
			else listObj[i].checked = true;
		}
		if(checkYn == "Y") f.chkAllWrite.value = "N";
		else f.chkAllWrite.value = "Y";
	}

	function openAll() {
		$(".menu_Lvl2").show();
		$(".menu_Lvl3").show();
	}

	function closeAll() {
		$(".menu_Lvl3").hide();
		$(".menu_Lvl2").hide();
	}

	function toggleMenu(str) {
		$(".menu_"+str).toggle();
	}
</script>
