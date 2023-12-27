<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<section class="content">
	<div class="row" id="content">
		<div class="box">
			<div class="box-body">

				<p style="border: 2px solid #B7CED4; border-radius: 10px; padding: 10px; font-size: 12px;">
				<spring:message code="olc.title.main.info.message"/>
				</p>
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
								<form name="Search">
								<div style="width:100%;margin-bottom:5px;" class="text-right">
									<a href="javascript:fileManage()" class="btn btn-primary btn-sm"><spring:message code="button.manage.file"/></a>
									<a href="javascript:olcWrite()" class="btn btn-primary btn-sm">OLC <spring:message code="button.write"/></a>
								</div>
								</form>
								<div id="cartrgList">
									<table summary='<spring:message code="olc.title.main.manage"/>' class="table table-striped table-bordered">
										<colgroup>
											<col style="width:50px"/>
											<col style="width:auto"/>
											<col style="width:auto"/>
											<col style="width:75px"/>
											<col style="width:80px"/>
											<col style="width:100px"/>
											<col style="width:75px"/>
											<col style="width:75px"/>
										</colgroup>
										<thead>
											<tr>
												<th scope="col"><spring:message code="common.title.no"/></th>
												<th scope="col"><spring:message code="olc.title.category.name"/></th>
												<th scope="col"><spring:message code="olc.title.cartridge.name"/></th>
												<th scope="col"><spring:message code="common.title.manage"/></th>
												<th scope="col"><spring:message code="olc.title.cartridge.contents.cnt"/></th>
												<th scope="col"><spring:message code="common.title.regdate"/></th>
												<th scope="col"><spring:message code="common.title.edit"/></th>
												<th scope="col"><spring:message code="org.title.orginfo.design"/></th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td colspan="8"><spring:message code="olc.message.category.select"/></td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>				
<script type="text/javascript">
	var olcCtgrTree = null;
	var modalBox = null;
	var ItemDTO = { "nodeId" : "", "subCnt" : 0, "olcCnt" : 0, "nodeType" : "root"};

	/** 메인 페이지 초기화 */
	$(document).ready(function() {
		modalBox = new $M.ModalDialog({
			"modalid" : "modal1"
		});

		$("._enterBind").bind("keydown", eventForSearch);
		function eventForSearch(event) {
			if (event.keyCode == '13') {
				event.preventDefault();
				//listCourse(1);
			}
		}

		olcCtgrTree = $('#ctgrTreeArea').jstree({
			"plugins" : [ "themes", "json_data", "types", "ui", "wholerow", "state", "hotkeys","contextmenu", "crrm"],
			"json_data" : {
				"ajax" : {
					"url" : cUrl("/mng/olc/cartrg/listCtgrJsTree"),
					"data" : function (n) {
						return {
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
			$.post(
				cUrl("/mng/olc/cartrg/editCtgr"),
				{
                    "ctgrCd" : data.rslt.obj.attr("ctgrCd"),
                    "parCtgrCd" : data.rslt.obj.attr("parCtgrCd"),
                    "ctgrNm" : data.rslt.new_name
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
		})
		.bind("create.jstree", function(e, data) {
			$.post(
				cUrl("/mng/olc/cartrg/editCtgr"),
				{
                    "ctgrCd" : data.rslt.obj.attr("ctgrCd"),
                    "parCtgrCd" : data.rslt.obj.attr("parCtgrCd"),
                    "ctgrNm" : data.rslt.name
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
				cUrl("/mng/olc/cartrg/addCtgr"),
				{
					"ctgrCd" : parCtgrCd,
					"ctgrNm" : ctgrNm
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
			var subCnt = $("#"+obj.attr("id")+" li").size();
			if(subCnt > 0) {
				alert("<spring:message code="olc.message.category.alert.subcate"/>");
				return;
			}
			if(obj.attr("olcCnt") > 0) {
				alert("<spring:message code="olc.message.category.alert.olc"/>");
				return;
			}
			$.ajaxSetup({async: false});
            $.post(
				cUrl("/mng/olc/cartrg/deleteCtgr"),
				{
					"ctgrCd" : obj.attr("ctgrCd"),
					"parCtgrCd" : obj.attr("parCtgrCd"),
					"ctgrNm" : obj.attr("ctgrNm")
				},
				function(result) {
					$("#ctgrTreeArea").jstree("remove","#"+obj.attr("id"));
				});
            $.ajaxSetup({async: true});
		}
		function moveNode(obj, str) {
			$.ajaxSetup({async: false});
			var url = cUrl("/mng/olc/cartrg");
			var cmd ="moveDownCtgr";
			if(str == 'up') cmd = "moveUpCtgr";
            $.post(
				cUrl("/mng/olc/cartrg/" + cmd),
				{
					"ctgrCd" : obj.attr("ctgrCd"),
					"parCtgrCd" : obj.attr("parCtgrCd")
				},
				function(result) {
					$("#ctgrTreeArea").jstree("refresh","#"+obj.attr("parCtgrCd"));
				});
            $.ajaxSetup({async: true});
		}
	});

	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}

	function setSelectNode(data) {
		var id = data.rslt.obj.attr("id");
		var name = data.rslt.obj.attr("ctgrNm");
		var rel = data.rslt.obj.attr("rel");
		var subCnt = data.rslt.obj.attr("subCnt");
		var olcCnt = data.rslt.obj.attr("olcCnt");

		ItemDTO.nodeId = id;
		ItemDTO.nodeType = rel;
		ItemDTO.subCnt = subCnt;
		ItemDTO.olcCnt = olcCnt;

		if(rel == 'root') {
			$("#cartrgTitle").html("<spring:message code="olc.title.main.manage"/> : ");
		} else {
			$("#cartrgTitle").html("<spring:message code="olc.title.main.manage"/> : "+name);
			listPageing(1);
		}
	}

	/**
	 * 카트리지 목록을 조회한다.
	 */
	function listPageing(page) {
		if(ItemDTO.nodeId != 'root' && ItemDTO.nodeId != '') {
			var listScale = $("#listScale option:selected").val();
			var url = cUrl("/mng/olc/cartrg/listCartrg");
			$("#cartrgList").load(url, {"ctgrCd":ItemDTO.nodeId,"listScale":listScale});
		}
	}

	/**
	 * OLC 등록 폼
	 */
	function olcWrite() {
		var ctgrCd = ItemDTO.nodeId;
		var addContent  = "<iframe id='addCCtgrFrame' name='addCtgrFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/mng/olc/cartrg/addCartrgPop"/>"
			+ "?ctgrCd="+ctgrCd+"'/>";
		$(".modal-title").css("color","#000000");
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(600, 400);
		modalBox.setTitle("<spring:message code="olc.title.cartridge.write"/>");
		modalBox.show();
	}

	/**
	 * OLC 수정 폼
	 */
	function olcEdit(cartrgCd) {
		var addContent  = "<iframe id='addCCtgrFrame' name='addCtgrFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/mng/olc/cartrg/editCartrgPop"/>"
			+ "?cartrgCd="+cartrgCd+"'/>";
		$(".modal-title").css("color","#000000");
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(600, 600);
		modalBox.setTitle("<spring:message code="olc.title.cartridge.edit"/>");
		modalBox.show();
	}

	function olcEditDesign(cartrgCd) {
		var addContent  = "<iframe id='addCCtgrFrame' name='addCtgrFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/mng/olc/cartrg/editCartrgDesignPop"/>"
			+ "?cartrgCd="+cartrgCd+"'/>";
		$(".modal-title").css("color","#000000");
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(800, 660);
		modalBox.setTitle("<spring:message code="olc.title.cartridge.design.edit"/>");
		modalBox.show();

	}

	function changeColor(color){
		$(".modal-title").css("color",color);
	}

	function moveOLC(type, cartrgCd) {
		var cmdStr = "moveUpOLC";
		if(type == "down" ) cmdStr = "moveDownOLC";
		$.getJSON(cUrl("/mng/olc/cartrg/" + cmdStr), 	// url
				  "ctgrCd" : ItemDTO.nodeId,
				  "cartrgCd" : cartrgCd
				}, function(data) { listPageing(1) }					// params
			);
	}

	/**
	 * Contents 관리 폼
	 */
	function cntsManage(cartrgCd) {
		var addContent  = "<iframe id='addCntsFrame' name='addCntsFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='no' src='<c:url value="/mng/olc/cartrg/cntsPop"/>"
			+ "?cartrgCd="+cartrgCd+"'/>";
		$(".modal-title").css("color","#000000");
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(1100, 700);
		modalBox.setTitle("<spring:message code="olc.title.contents.manage"/>");
		modalBox.show();
	}

	/**
	 * Contents 관리 폼
	 */
	function fileManage() {
		var addContent  = "<iframe id='manageFileFrame' name='manageFileFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='no' src='<c:url value="/mng/olc/userFile/mainPop"/>"
			+ "'/>";
		$(".modal-title").css("color","#000000");
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(800, 600);
		modalBox.setTitle("<spring:message code="button.manage.file"/>");
		modalBox.show();
	}

	function preview(userNo, cartrgCd, cartrgNm) {
		var url = cUrl("/mng/olc/cartrg/previewPop")+"?userNo="+userNo+"${AMPERSAND}cartrgCd="+cartrgCd;
		/*
		var addContent  = "<iframe id='addCCtgrFrame' name='addCtgrFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/mng/olc/cartrg/previewMain"/>"
			+ "?olcCntsVO.userNo="+userNo+"&amp;olcCntsVO.cartrgCd="+cartrgCd+"'/>";
		$(".modal-title").css("color","#000000");
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(1100, 650);
		modalBox.setTitle(cartrgNm);
		modalBox.show();
		 */
		var winOption = "left=0,top=0,toolbar=no,location=no,directories=0,status=0,menubar=no,scrollbars=yes,resizable=no,width=1100,height=670";
		var contentsWin = window.open(url, "contentsWin", winOption);
		contentsWin.focus();
	}
</script>

