<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<section class="content">
	<div class="row" id="content">
		<div class="box">
			<div class="box-body">
						<div class="col-lg-12">
							<form name="orgMenuForm" id="orgMenuForm" onsubmit="return false" method="POST" >
							<input type="hidden" name="menuType" id="menuType" value="${vo.menuType}"/>
							<input type="hidden" name="orgCd" id="orgCd" value="${vo.orgCd}" />
							<input type="hidden" name="viewAuthArray" id="viewAuthArray"/>
							<input type="hidden" name="creAuthArray" id="creAuthArray"/>
							<div style="width:100%;height:30px;margin-top:10px;">
								<div style="width:50%;float:left">
									<div class="input-group">
										<div class="input-group-btn btn-group">
											<button type="button" class="btn btn-default btn-sm dropdown-toggle" data-toggle="dropdown">
												<span class="caret"></span>
											</button>
											<ul class="dropdown-menu" role="menu">
											<c:forEach var="item" items="${authGrpList}" >
												<c:set var="authGrpNm" value="${item.authGrpNm}"/>
												<c:forEach var="lang" items="${item.authGrpLangList}">
													<c:if test="${lang.langCd eq LOCALEKEY}">
														<c:set var="authGrpNm" value="${lang.authGrpNm}"/>
													</c:if>
												</c:forEach>
						   						<li><a href="javascript:setAuthGrpCd('${item.authGrpCd}','${authGrpNm}')"> ${authGrpNm}</a></li>
											</c:forEach>
											</ul>
										</div>
										<input type="text" name="authGrpNm" id="authGrpNm" class="form-control input-sm" style="width:140px;background-color:#ffffff;" readonly="readonly"/>
										<input type="hidden" name="authGrpCd" id="authGrpCd"/>
									</div>
								</div>
								<div style="width:50%;float:right;" class="text-right">
									<a class="btn btn-primary btn-sm" href="javascript:menuWrite()"><spring:message code="button.write.menu" /></a>
									<a class="btn btn-primary btn-sm" href="javascript:authMenuSave()"><spring:message code="button.write.authmenu" /></a>
									<a class="btn btn-info btn-sm" href="javascript:initMenu()"><spring:message code="button.init.menu" /></a>
								</div>
								<div style="clear:both;"></div>
							</div>
							<div id="menuList" style="margin-top:5px;">
								<table summary="<spring:message code="org.title.orgmenu.manage"/>" class="table table-bordered">
									<colgroup>
										<col style="width:100px"/>
										<col style="width:auto"/>
										<col style="width:80px"/>
										<col style="width:80px"/>
										<col style="width:80px"/>
									</colgroup>
									<thead>
										<tr>
											<th scope="col"><spring:message code="common.title.manage"/></th>
											<th scope="col"><spring:message code="system.title.menu.name"/></th>
											<th scope="col"><spring:message code="system.title.menu.write"/></th>
											<th scope="col"><spring:message code="system.title.menu.auth.use"/></th>
											<th scope="col"><spring:message code="system.title.menu.auth.write"/></th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td colspan="5"><spring:message code="system.message.menu.nodata"/></td>
										</tr>
									</tbody>
								</table>
							</div>
							</form>
						</div>
			</div>
		</div>
	</div>
</section>
<script type="text/javascript">
	var modalBox = null;
	var ItemDTO = {menuCount : 0};

	// 페이지 초기화
	$(document).ready(function() {
		ItemDTO.menuCount = 0;
		modalBox = new $M.ModalDialog({
			"modalid" : "modal1"
		});

		menuList();
	});

	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}

	/**
	 *  메뉴 목록 호출
	 */
	function menuList() {
		var authGrpCd = $("#authGrpCd").val();
		$('#menuList').load(
				cUrl("/mng/org/menu/list"),
				{ "orgCd" : "${vo.orgCd}",
				  "authGrpCd" :  authGrpCd
				},
				function () {}
			);
	}

	function setAuthGrpCd(authGrpCd, authGrpNm) {
		$("#authGrpCd").val(authGrpCd);
		$("#authGrpNm").val(authGrpNm);
		menuList();
	}

	/**
	 * 메뉴 등록 폼
	 */
	function menuWrite() {
		var addContent  = "<iframe id='addMenuFrame' name='addMenuFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='/mng/org/menu/addMenuPop"
			+ "?orgCd=${vo.orgCd}'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(870, 560);
		modalBox.setTitle("<spring:message code="system.title.menu.write"/>");
		modalBox.show();
	}

	/**
	 * 서브 메뉴 등록 폼
	 */
	function subMenuWrite(parMenuCd) {
		var addContent  = "<iframe id='addMenuFrame' name='addMenuFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='/mng/org/menu/addSubMenuPop"
			+ "?orgCd=${vo.orgCd}&parMenuCd="+parMenuCd+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(870, 560);
		modalBox.setTitle("<spring:message code="system.title.menu.write"/>");
		modalBox.show();
	}

	/**
	 * 메뉴 수정 폼
	 */
	function menuEdit(menuCd) {
		var addContent = "<iframe id='addMenuFrame' name='addMenuFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='/mng/org/menu/editMenuPop"
			+ "?orgCd=${vo.orgCd}&menuCd="+menuCd+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(870, 560);
		modalBox.setTitle("<spring:message code="system.title.menu.edit"/>");
		modalBox.show();
	}

// 	function moveMenu(type, menuCd) {
// 		var cmdStr = "moveUpMenu";
// 		if(type == "down" ) cmdStr = "moveDownMenu";
// 		$.getJSON(cUrl("/mng/org/menu/"+cmdStr+".do"), 	// url
// 				{ "orgCd" : "${vo.orgCd}",
// 				  "menuCd" : menuCd
// 				}, function(data) { menuList() }					// params
// 			);
// 	}
	
	/**
	 * 메뉴 순서 변경
	 */
	function menuSort(parMenuCd, sortString) {
		var menuType = $("#menuType option:selected").val();
		$.getJSON(cUrl("/mng/org/menu/sortMenu"), { "orgCd" : "${vo.orgCd}", "menuType": menuType, "parMenuCd":parMenuCd, "menuCd":sortString}, function(resultVO){
			alert(resultVO.message);
			if(resultVO.result >= 0) {
			} else {
				menuList();
			}
		});
	}

	/**
	 * 권한 메뉴 등록
	 */
	function authMenuSave() {
		if(ItemDTO.menuCount == 0) {
			alert('<spring:message code="org.message.orgmenu.alert.nodata.initplz"/>');
			return;
		}
		if($("#authGrpCd").val() == "") {
			alert("<spring:message code="system.message.menu.select.auth"/>");
			return;
		} else {
			var viewAuthArray = $("#orgMenuForm input[name='viewAuth']:checked").stringValues('|');
			var creAuthArray = $("#orgMenuForm input[name='creAuth']:checked").stringValues('|');
			$("#viewAuthArray").val(viewAuthArray);
			$("#creAuthArray").val(creAuthArray);
			$("#orgAuthGrpCd").val($("#authGrpCd").val());
			process("saveAuthMenu");	// cmd
		}
	}

	/**
	 * 기관 메뉴 초기화
	 */
	function initMenu() {
		if(confirm('<spring:message code="system.message.menu.confirm.init"/>'))
			process("initMenu");	// cmd
	}

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		$('#orgMenuForm').attr("action", "/mng/org/menu/" + cmd);
		$('#orgMenuForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultVO) {
		alert(resultVO.message);
		if(resultVO.result >= 0) {
			// 정상 처리
			menuList();
		} else {
			// 비정상 처리
		}
	}

	function closeWrite() {
		var url = cUrl("/mng/org/menu/main")+"?pageIndexe=${vo.pageIndex}&searchKey=${vo.searchKey}&searchValue=${vo.searchValue}&listScale=${vo.listScale}";
		document.location.href = url;
	}
</script>
