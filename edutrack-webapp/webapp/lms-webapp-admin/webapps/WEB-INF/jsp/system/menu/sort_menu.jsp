<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>

	<form name="menuForm" id="menuForm" onsubmit="return false" method="POST">
	<input type="hidden" name="menuCd" value="${vo.menuCd}"/>
	<input type="hidden" name="menuType" value="${vo.menuType}"/>
	<table summary="" style="width:100%;">
		<tr>
			<td style="padding:5px" colspan="2">
				<select name="parMenuCd" id="parMenuCd" onchange="listMenuSub()" class="form-control input-sm">
					<option value=''><spring:message code="system.title.menu.rootmenu"/></option>
				<c:if test="${not empty menuList}">
					<c:forEach var="item" items="${menuList}">
					<option value='${item.menuCd}'>${item.menuNm}</option>
					</c:forEach>
				</c:if>
				</select>
			</td>
		</tr>
		<tr>
			<td style="width:60%;padding:5px;" valign="top">
				<select name="selMenuCd" id="selMenuCd" multiple style="width:180px;height:220px" class="form-control input-sm">
				</select>
			</td>
			<td style="width:40%;padding:5px;" valign="top">
				<a class="btn btn-default btn-sm" href="javascript:moveUp()" style="display:block;"><spring:message code="button.up"/></a>
				<a class="btn btn-default btn-sm" href="javascript:moveDown()" style="display:block;"><spring:message code="button.down"/></a>
				<a class="btn btn-default btn-sm" href="javascript:sortMenu()" style="display:block;"><spring:message code="button.add"/></a>
			</td>
		</tr>
	</table>
	</form>

<script type="text/javascript">
	var menuList;
	// 페이지 초기화
	$(document).ready(function() {
		$.getJSON(CONTEXT_PATH + "/adm/system/menu/listSubMenu.do", 	// url
				{ menuType : "${vo.menuType}",
				  parMenuCd : ""
				},					// params
				function(resultList) {
					menuList = resultList;
					listMneuTop();
				}
			);
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
	
	function listMneuTop() {
		for(i=0; i < menuList.length; i++) {
			var item = menuList[i];
			if(item.subCnt > 0) {
				$("#parMenuCd").append("<option value='"+item.menuCd+"'>"+item.menuNm+"</option>");
			}
			var subList = item.subList;
			for(j = 0 ; j < subList.length; j++) {
				var subItem = subList[j];
				if(subItem.subCnt > 0) {
					$("#parMenuCd").append("<option value='"+subItem.menuCd+"'>"+subItem.menuNm+"</option>");
				}
			}
		}
		listMenuSub();
	}

	/**
	 * 시스템 메뉴 목록 조회
	 */
	function listMenuSub() {
		var parMenuCd = $("#parMenuCd option:selected").val();
		$("#selMenuCd option").remove();
		for(var i = 0; i < menuList.length; i++) {
			item1 = menuList[i];
			if(parMenuCd == item1.parMenuCd) {
				$("#selMenuCd").append("<option value='"+item1.menuCd+"'>"+item1.menuNm+"</option>");
			}
			subList1 = item1.subList;
			for(var j = 0; j < subList1.length; j++) {
				item2 = subList1[j];
				if(parMenuCd == item2.parMenuCd) {
					$("#selMenuCd").append("<option value='"+item2.menuCd+"'>"+item2.menuNm+"</option>");
				}
				subList2 = item2.subList;
				for(var k = 0; k < subList2.length; k++) {
					item3 = subList2[i];
					if(parMenuCd == item3.parMenuCd) {
						$("#selMenuCd").append("<option value='"+item3.menuCd+"'>"+item3.menuNm+"</option>");
					}
				}
			}
		}
	}

	/**
	 * 과정 분류 순서 변경
	 */
	function sortMenu() {
		$("input[name=menuCd]").val("");
		var len = $("#selMenuCd option").size();
		if(len == 0) {
			alert('<spring:message code="system.message.menu.impossible.sort"/>');
		} else {
			var menuListStr = "";
			for(var i=0; i < len; i++) {
				if(i==0) {
					menuListStr = $("#selMenuCd option:eq("+i+")").val();
				} else {
					menuListStr = menuListStr+'|'+$("#selMenuCd option:eq("+i+")").val();
				}
			}
			$("input[name=menuCd]").val(menuListStr);
			process("/adm/system/menu/sortMenu.do");	// cmd
		}
	}


	function moveUp() {
		moveOption('up');
	}

	function moveDown() {
		moveOption('down');
	}

	function moveOption(moveType) {
		var si =  $("#selMenuCd option").index($("#selMenuCd option:selected"));
		var len = $("#selMenuCd option").size();
		if(si >= 0) {
			var siValue = $("#selMenuCd option:eq("+si+")").val();
			var siText = $("#selMenuCd option:eq("+si+")").text();
			var moveYn = "Y";
			var ti = null;
			if(moveType == 'up' && si != 0) {
				ti = si-1;
			} else if(moveType == 'down' && len-1 > si) {
				ti = si+1;
			} else {
				moveYn = "N";
			}
			if(moveYn == "Y") {
				var tiValue = $("#selMenuCd option:eq("+ti+")").val();
				var tiText = $("#selMenuCd option:eq("+ti+")").text();
				//---- 위치변경
				$("#selMenuCd option:eq("+si+")").val(tiValue);
				$("#selMenuCd option:eq("+si+")").text(tiText);
				$("#selMenuCd option:eq("+ti+")").val(siValue);
				$("#selMenuCd option:eq("+ti+")").text(siText);
				$("#selMenuCd option:eq("+ti+")").prop("selected", "selected");
				$("#selMenuCd option:eq("+si+")").prop("selected", "");
			}
		}
	}
</script>
