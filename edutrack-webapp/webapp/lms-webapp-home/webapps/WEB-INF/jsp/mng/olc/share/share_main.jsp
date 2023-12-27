<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<mhtml:mng_html>
<mhtml:mng_head titleName="${MENUNAME}">
	<mhtml:head_module paging="Y"/>
	<meditag:js src="/js/modaldialog.js"/>
	<meditag:js src="/js/jquery/jquery.cookie.js"/>
	<meditag:js src="/js/jquery/jquery.hotkeys.js"/>
	<meditag:js src="/js/jquery/jquery.jstree.js"/>
</mhtml:mng_head>

<mhtml:mng_body>
			<mhtml:mng_title title="${MENUNAME}" location="${MENUPATH}"/>
			<div class="row" id="content">
				<div class="col-md-3">
					<div class="panel panel-default">
						<div class="panel-heading">
							<spring:message code="olc.title.category.manage"/>
						</div>
						<div class="panel-body" style="padding:0px;">
							<div id="ctgrTreeArea" style="width:100%;height:600px;overflow:auto;background-color:white;padding:5px;line-height:20px;"></div>
						</div>
					</div>
				</div>
				<div class="col-md-9">
					<div class="panel panel-default">
						<div class="panel-heading">
							<span id="cartrgTitle"><spring:message code="olc.title.main.manage"/> : </span>
						</div>
						<div class="panel-body">
							<form name="Search" id="Search" onsubmit="return false;" class="form-inline">
							<div class="text-right">
								<select name="listScale" id="listScale" onchange="listPageing(1);" class="form-control input-sm">
									<option value="10">10</option>
									<option value="20" selected="selected">20</option>
									<option value="40">40</option>
									<option value="60">60</option>
									<option value="80">80</option>
									<option value="100">100</option>
									<option value="200">200</option>
								</select>
							</div>
							</form>
							<div id="cartrgRelList">

							</div>
						</div>
					</div>
				</div>
			</div>

<script type="text/javascript">
	var shareCtgrTree = null;
	var modalBox = null;
	var ItemDTO = { "nodeId" : "", "subCnt" : 0, "olcCnt" : 0, "nodeType" : "root", "ctgrCd" : "", "ctgrDivCd" : ""};

	/** 메인 페이지 초기화 */
	$(document).ready(function() {
		modalBox = new $M.ModalDialog({
			"modalid" : "modal1"
		});
		ItemDTO.ctgrDivCd = '${olcShareForm.olcShareCtgrDTO.ctgrDivCd}';

		$("._enterBind").bind("keydown", eventForSearch);
		function eventForSearch(event) {
			if (event.keyCode == '13') {
				event.preventDefault();
				//listCourse(1);
			}
		}

		shareCtgrTree = $('#ctgrTreeArea').jstree({
			"plugins" : [ "themes", "json_data", "types", "ui", "wholerow", "state", "hotkeys","contextmenu", "crrm"],
			"json_data" : {
				"ajax" : {
					"url" : cUrl("/mng/olc/share/listCtgrJsTree"),
					"data" : function (n) {
						return {
							"olcShareCtgrDTO.ctgrDivCd" : ItemDTO.ctgrDivCd,
							"id" : n.attr ? n.attr("id") : "#"
						};
					}
				}
			},
			"types" : {
				"max_depth" : -2,
				"max_children" : -2,
				"valid_children" : [ "root" ],
				"types" : {
					"default" : {
						"valid_children" : [],
						"icon" : { "image" : "/img/framework/icon/filetype/file.png" }
					},
					"root" : {
						"valid_children" : ["category", "contents", "default"],
						"icon" : { "image" : "/img/framework/icon/icon_administrator.gif" },
						"start_drag" : false
					},
					"category" : {
						"valid_children" :  ["contents", "default"],
						"icon" : { "image" : "/img/framework/icon/icon_contents.gif" }
					},
					"contents" : {
						"valid_children" : [],
						"icon" : { "image" : "/img/framework/icon/icon_lesson.gif" }
					}
				}
			},
			"themes" : { "theme" : "default", "dots" : true },
			"core" : {check_callback : true},
			"contextmenu" : {
				"items" : function (obj) {
					return {
						"create" : {
							"label" : "<spring:message code="olc.title.category.write"/>",
							"icon" : "/img/framework/icon/icon_folder_add.gif",
							"_disabled" : false,
							"action" : function () {
								createNode(obj);
							}
						},
						"rename" : {
							"label" : "<spring:message code="olc.title.category.edit"/>",
							"icon" : "/img/framework/icon/icon_function_edit.gif",
							"_disabled" : obj.attr("rel") == "root",
							"action" : function () {
								renameNode(obj);
							}
						},
						"remove" : {
							"label" : "<spring:message code="olc.title.category.delete"/>",
							"icon" : "/img/framework/icon/icon_delete.gif",
							"_disabled" : obj.attr("rel") == "root",
							"action" : function () {
								removeNode(obj);
							}
						},
						"moveup" : {
							"label" : "<spring:message code="button.up"/>",
							"icon" : "/img/framework/icon/icon_function_up.gif",
							"_disabled" : (obj.attr("rel") == "root" || obj.attr("id") == obj.attr("firstCd")),
							"action" : function () {
								moveNode(obj, 'up');
							}
						},
						"movedown" : {
							"label" : "<spring:message code="button.down"/>",
							"icon" : "/img/framework/icon/icon_function_down.gif",
							"_disabled" : (obj.attr("rel") == "root" || obj.attr("id") == obj.attr("lastCd")),
							"action" : function () {
								moveNode(obj, 'down');
							}
						}
					}
				}
			}
		})
		.bind("rename.jstree", function(e, data) {
			if(data.rslt.new_name.length > 50) {
				alert("<spring:message code="olc.message.category.alert.rename"/>");
				$.jstree.rollback(data.rlbk);
				return;
			}
			$.ajaxSetup({async: false});
			$.post(
				cUrl("/mng/olc/share/editCtgr"),
				{
					"olcShareCtgrDTO.ctgrDivCd" : ItemDTO.ctgrDivCd,
                    "olcShareCtgrDTO.ctgrCd" : data.rslt.obj.attr("ctgrCd"),
                    "olcShareCtgrDTO.parCtgrCd" : data.rslt.obj.attr("parCtgrCd"),
                    "olcShareCtgrDTO.ctgrNm" : data.rslt.new_name
				},
				function(resultDTO) {
					if(resultDTO.result >= 0) {
						var returnDTO = resultDTO.returnDto;
						data.rslt.obj.attr("ctgrNm", returnDTO.attr.ctgrNm);
						data.rslt.obj.attr("data", returnDTO.attr.ctgrNm);
					} else {
            			// 비정상 처리
						$.jstree.rollback(data.rlbk);
					}
				});
			$.ajaxSetup({async: true});
		})
		.bind("create.jstree", function(e, data) {
			$.ajaxSetup({async: false});
			$.post(
				cUrl("/mng/olc/share/editCtgr"),
				{
					"olcShareCtgrDTO.ctgrDivCd" : ItemDTO.ctgrDivCd,
                    "olcShareCtgrDTO.ctgrCd" : data.rslt.obj.attr("ctgrCd"),
                    "olcShareCtgrDTO.parCtgrCd" : data.rslt.obj.attr("parCtgrCd"),
                    "olcShareCtgrDTO.ctgrNm" : data.rslt.name
				},
				function(resultDTO) {
					if(resultDTO.result >= 0) {
						var returnDTO = resultDTO.returnDto;
						data.rslt.obj.attr("ctgrNm", returnDTO.attr.ctgrNm);
					} else {
            			// 비정상 처리
						$.jstree.rollback(data.rlbk);
					}
				});
			$.ajaxSetup({async: true});
		})
		.bind("select_node.jstree", function(e, data) {
			setSelectNode(data);
		})
		.bind("loaded.jstree", function(e, data){
			$(this).jstree("open_all");
		});

		function createNode(obj) {
			$.ajaxSetup({async: false});
			var parCtgrCd = "";
			var ctgrNm = "New Node";
			if(obj.attr("rel") != 'root') {
				parCtgrCd = obj.attr("ctgrCd");
			}
            $.post(
				cUrl("/mng/olc/share/addCtgr"),
				{
					"olcShareCtgrDTO.ctgrDivCd" : ItemDTO.ctgrDivCd,
					"olcShareCtgrDTO.ctgrCd" : parCtgrCd,
					"olcShareCtgrDTO.ctgrNm" : ctgrNm
				},
				function(result) {
					$("#ctgrTreeArea").jstree("refresh");
					$("#ctgrTreeArea").jstree("open_node","#"+obj.attr("id"));
					$("#ctgrTreeArea").jstree("rename","#"+result.attr.id);
					//$("#ctgrTreeArea").jstree("create","#"+obj.attr("id"),"last" ,result);
				});
            $.ajaxSetup({async: true});
		}
		function renameNode(obj) {
			$("#ctgrTreeArea").jstree("rename","#"+obj.attr("id"));
		}
		function removeNode(obj) {
			$.ajaxSetup({async: false});
			var subCnt = $("#"+obj.attr("id")+" li").size();
			if(subCnt > 0) {
				alert("<spring:message code="olc.message.category.alert.subcate"/>");
				return;
			}
			if(obj.attr("olcCnt") > 0) {
				alert("<spring:message code="olc.message.category.alert.olc"/>");
				return;
			}
            $.post(
				cUrl("/mng/olc/share/deleteCtgr"),
				{
					"olcShareCtgrDTO.ctgrDivCd" : ItemDTO.ctgrDivCd,
					"olcShareCtgrDTO.ctgrCd" : obj.attr("ctgrCd"),
					"olcShareCtgrDTO.parCtgrCd" : obj.attr("parCtgrCd"),
					"olcShareCtgrDTO.ctgrNm" : obj.attr("ctgrNm")
				},
				function(result) {
					$("#ctgrTreeArea").jstree("remove","#"+obj.attr("id"));

				});
            $.ajaxSetup({async: true});
		}
		function moveNode(obj, str) {
			var url = cUrl("/mng/olc/share/");
			var cmd ="moveDownCtgr";
			if(str == 'up') cmd = "moveUpCtgr";
            $.post(
				cUrl("/mng/olc/share/" + cmd),
				{
					"olcShareCtgrDTO.ctgrDivCd" : ItemDTO.ctgrDivCd,
					"olcShareCtgrDTO.ctgrCd" : obj.attr("ctgrCd"),
					"olcShareCtgrDTO.parCtgrCd" : obj.attr("parCtgrCd")
				},
				function(result) {
					$("#ctgrTreeArea").jstree("refresh","#"+obj.attr("parCtgrCd"));
				});
		}
	});

	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}

	function setSelectNode(data) {
		ItemDTO.ctgrCd = data.rslt.obj.attr("ctgrCd");
		if(data.rslt.obj.attr("rel") == 'root') {
			$("#cartrgTitle").html("<spring:message code="olc.title.main.manage"/> : ");
		} else {
			$("#cartrgTitle").html("<spring:message code="olc.title.main.manage"/> : "+data.rslt.obj.attr("ctgrNm"));
			listPageing(1);
		}
	}

	function listPageing(page) {
		if(page != null && page > 0)
			ItemDTO.curPage = page;
		var listScale = $("#listScale option:selected").val();
		var url = cUrl("/mng/olc/share/listCartrgRel")
		$("#cartrgRelList").load(url, { "olcShareRelDTO.ctgrDivCd" : ItemDTO.ctgrDivCd, "curPage" : ItemDTO.curPage, "olcShareRelDTO.ctgrCd" : ItemDTO.ctgrCd});
	}

	function preview(cartrgCd, cartrgNm) {
		var addContent  = "<iframe id='addCCtgrFrame' name='addCtgrFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/mng/olc/cartrg/previewMain"/>"
			+ "?olcCntsDTO.cartrgCd="+cartrgCd+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(1100, 650);
		modalBox.setTitle(cartrgNm);
		modalBox.show();
	}


	/**
	 * OLC 수정 폼
	 */
	function olcCtgrEdit(cartrgCd) {
		var addContent  = "<iframe id='addCCtgrFrame' name='addCtgrFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/mng/olc/cartrg/editCartrgCtgrForm"/>"
			+ "?olcCartrgDTO.ctgrDivCd="+ItemDTO.ctgrDivCd+"&amp;olcCartrgDTO.cartrgCd="+cartrgCd+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(600, 400);
		modalBox.setTitle("<spring:message code="olc.title.cartridge.edit"/>");
		modalBox.show();
	}
</script>

</mhtml:mng_body>
</mhtml:mng_html>
