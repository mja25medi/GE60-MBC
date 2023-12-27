<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>

<section class="content">
	<div class="row" id="content">
		<div class="box">
			<div class="box-body">
				<div id="displayArea">
					<div class="col-md-3 col-xs-18">
						<div class="panel panel-default">
							<div class="panel-heading">
								<spring:message code="course.title.exambank.category"/>
							</div>
							<div class="panel-body" style="padding:0px;">
								<div id="ctgrTreeArea" style="width:100%;height:600px;overflow:auto;background-color:white;padding:5px;line-height:20px;"></div>
							</div>
						</div>
					</div>
					<div class="col-md-9 col-xs-18">
						<form name="Search" onsubmit="return false" class="form-inline">
						<div class="panel panel-default">
							<div class="panel-heading">
								<span id="contentsTitle" ><spring:message code="library.title.contents.category"/> : </span>
								<div class="pull-right" >
									<select name="listScale" id="listScale" onchange="listPageing('1')" class="form-control input-sm" style="width: 100px;float: right;margin-right: 5px;">
										<option value="10" <c:if test="${vo.listScale eq '10' }">selected</c:if>>10</option>
										<option value="20" <c:if test="${vo.listScale eq '20' }">selected</c:if>>20</option>
										<option value="40" <c:if test="${vo.listScale eq '40' }">selected</c:if>>40</option>
										<option value="60" <c:if test="${vo.listScale eq '60' }">selected</c:if>>60</option>
										<option value="80" <c:if test="${vo.listScale eq '80' }">selected</c:if>>80</option>
										<option value="100" <c:if test="${vo.listScale eq '100' }">selected</c:if>>100</option>
										<option value="200" <c:if test="${vo.listScale eq '200' }">selected</c:if>>200</option>
									</select>
									<button class="btn btn-primary btn-sm" id="writeBtn" style="float: right;margin-right: 5px;"><spring:message code="button.write.contents"/></button>
									<div class="input-group" style="width:180px;float: right;margin-right: 5px;">
										<input type="text" name="searchValue" id="searchValue" class="_enterBind form-control input-sm" placeholder="<spring:message code="common.title.all"/>"/>
										<span class="input-group-addon" onclick="listPageing(1)" style="cursor:pointer">
											<i class="fa fa-search"></i>
										</span>
									</div>
									<select name="searchKey" id="searchKey" class="form-control input-sm" style="width: 100px;float: right;margin-right: 5px;">
										<option value="ALL"><spring:message code="common.title.all"/></option>
										<option value="cntsNm"><spring:message code="library.title.contents.name"/></option>
										<option value="cntsTag"><spring:message code="common.title.tag"/></option>
										<option value="regUser"><spring:message code="common.title.writer.name"/></option>
									</select>
									<%-- <button class="btn btn-primary btn-sm" id="fileManageBtn"><spring:message code="button.manage.file"/></button> --%>

								</div>
								<div style="clear: both;"></div>
							</div>
							<div class="panel-body" id="contentsList">
								<ul class="list-group">
									<li class="list-group-item"><spring:message code="library.message.contents.category.select.category"/></li>
								</ul>
							</div>
						</div>
						</form>
					</div>
				</div>
				<div id="workArea" style="dispaly:none" class="col-md-12">

				</div>
			</div>
		</div>
	</div>
</section>
<script type="text/javascript">
	var ctgrTree = null;
	var modalBox = null;
	var ItemDTO = { "nodeId" : "", "subCnt" : 0, "cntsCnt" : 0, "nodeType" : "root"};

	/** 메인 페이지 초기화 */
	$(document).ready(function() {
		modalBox = new $M.ModalDialog({
			"modalid" : "modal1"
		});

		$("._enterBind").bind("keydown", eventForSearch);
		function eventForSearch(event) {
			if (event.keyCode == '13') {
				event.preventDefault();
				listPageing(1);
			}
		}

		$("#writeBtn").bind("click", function(event) {
			cntsWrite();
		});

		$("#fileManageBtn").bind("click",function(event){
			fileManage();
		});

		ctgrTree = $('#ctgrTreeArea').jstree({
			"plugins" : [ "themes", "json_data", "types", "ui", "wholerow", "state", "hotkeys","contextmenu", "crrm"],
			"json_data" : {
				"ajax" : {
					"url" : cUrl("/mng/library/clibCntsCtgr/listJsTree"),
					"data" : function (n) {
						return {
							"id" : n.attr ? n.attr("id") : "#",
							"listType" : "OLC"
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
							"_disabled" : obj.attr("ctgrLvl") > 4,
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
				alert("<spring:message code="library.message.contents.category.rename.alert.length50"/>");
				$.jstree.rollback(data.rlbk);
				return;
			}
			$.post(
				cUrl("/mng/library/clibCntsCtgr/editCtgr"),
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
						$("#ctgrTreeArea").jstree("rename_node","#"+returnDTO.attr.ctgrCd, returnDTO.attr.ctgrNm+"("+returnDTO.attr.olcCnt+")"); //-- 뒤의 (갯수)를 삭제함.
					} else {
            			// 비정상 처리
						$.jstree.rollback(data.rlbk);
					}
				});
		})
		.bind("create.jstree", function(e, data) {
			$.post(
				cUrl("/mng/library/clibCntsCtgr/editCtgr"),
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
				cUrl("/mng/library/clibCntsCtgr/addCtgr"),
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
			var oldname = obj.attr("title").replace("("+obj.attr("olcCnt")+")","");
			$("#ctgrTreeArea").jstree("rename_node","#"+obj.attr("id"), oldname); //-- 뒤의 (갯수)를 삭제함.
			$("#ctgrTreeArea").jstree("rename","#"+obj.attr("id"));
		}
		function removeNode(obj) {
			var subCnt = $("#"+obj.attr("id")+" li").size();
			if(subCnt > 0) {
				alert("<spring:message code="library.message.contents.category.delete.alert.subcate"/>");
				return;
			}
			if(obj.attr("olcCnt") > 0) {
				alert("<spring:message code="library.message.contents.category.delete.alert.contents"/>");
				return;
			}
			$.ajaxSetup({async: false});
            $.post(
				cUrl("/mng/library/clibCntsCtgr/deleteCtgr"),
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
			var url = cUrl("/mng/library/clibCntsCtgr/");
			var cmd ="moveDownCtgr";
			if(str == 'up') cmd = "moveUpCtgr";
            $.post(
				cUrl("/mng/library/clibCntsCtgr/" + cmd),
				{
					"ctgrCd" : obj.attr("ctgrCd"),
					"parCtgrCd" : obj.attr("parCtgrCd")
				},
				function(result) {
					$("#ctgrTreeArea").jstree("refresh","#"+obj.attr("parCtgrCd"));
				});
            $.ajaxSetup({async: true});
		}

		$("#contentsTitle").html("<spring:message code="library.title.contents.category"/> : "+"<spring:message code="common.title.all"/>");
		listPageing(1);
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
		var cntsCnt = data.rslt.obj.attr("cntsCnt");

		ItemDTO.nodeId = id;
		ItemDTO.nodeType = rel;
		ItemDTO.subCnt = subCnt;
		ItemDTO.cntsCnt = cntsCnt;

		if(rel == 'root') {
			ItemDTO.nodeId = "";
			ItemDTO.nodeType = "";
			ItemDTO.subCnt = "";
			ItemDTO.cntsCnt = "";
			$("#contentsTitle").html("<spring:message code="library.title.contents.category"/> : "+"<spring:message code="common.title.all"/>");
			listPageing(1);
		} else {
			$("#contentsTitle").html("<spring:message code="library.title.contents.category"/> : "+name);
			listPageing(1);
		}
	}

	/**
	 * 콘텐츠 목록을 조회한다.
	 */
	function listPageing(page) {
		ItemDTO.page = page;
		var searchKey = $("#searchKey option:selected").val();
		var searchValue = $('#searchValue').val();

			var listScale = $("#listScale option:selected").val();
			var url = cUrl("/mng/library/clibOlcCnts/list");
			$("#contentsList").load(
					url, {
							"ctgrCd":ItemDTO.nodeId,
							"searchKey":searchKey,
							"searchValue":searchValue,
							"curPage" : page ,
							"listScale":listScale
						}
			);
		/*
		if(ItemDTO.nodeId != 'root' && ItemDTO.nodeId != '') {
		}
		 */
	}

	/**
	 * 콘텐츠 등록 폼
	 */
	function cntsWrite() {
		$("#workArea").empty();
		$("#workArea").load(cUrl("/mng/library/clibOlcCnts/addForm"),{ "ctgrCd":ItemDTO.nodeId,"curPage":ItemDTO.page});
		$("#displayArea").slideUp('slow');
		$("#workArea").show();
	}

	/**
	 * 콘텐츠 수정 폼
	 */
	function cntsEdit(cntsCd) {
		$("#workArea").empty();
		$("#workArea").load(cUrl("/mng/library/clibOlcCnts/editForm"),{"cntsCd":cntsCd,"curPage":ItemDTO.page});
		$("#displayArea").slideUp('slow');
		$("#workArea").show();
	}

	/**
	 * 콘텐츠 디자인 수정 폼
	 */
	function cntsDesignEdit(cntsCd) {
		$("#workArea").empty();
		$("#workArea").load(cUrl("/mng/library/clibOlcCnts/editDesignForm"),{"cntsCd":cntsCd,"curPage":ItemDTO.page});
		$("#displayArea").slideUp('slow');
		$("#workArea").show();
	}

	function closeWriteArea() {
		$("#displayArea").slideDown('slow');
		$("#workArea").empty();
		$("#workArea").slideUp('slow');
	}

	/**
	 * File 관리 폼
	 */
	function fileManage() {
		var url = generateUrl("/mng/library/clibOlcFile/main");
		var addContent  = "<iframe id='manageFileFrame' name='manageFileFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='no' src='"+url+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(800, 600);
		modalBox.setTitle("<spring:message code="button.manage.file"/>");
		modalBox.show();
	}

	/**
	 * Page 관리 폼
	 */
	function pageManage(cntsCd, cntsNm) {
		var url = generateUrl("/mng/library/clibOlcCnts/mainPagePop",{"cntsCd":cntsCd});
		var addContent  = "<iframe id='managePageFrame' name='managePageFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='no' src='"+url+"'/>";6
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(1135, 750);
		modalBox.setTitle(cntsNm);
		modalBox.show();
	}

	function preview(userNo, cntsCd, cntNm) {
		var url = generateUrl("/mng/library/clibOlcCnts/previewPop",{ "userNo":userNo, "cntsCd": cntsCd});
		var winOption = "left=0,top=0,toolbar=no,location=no,directories=0,status=0,menubar=no,scrollbars=no,resizable=no,width=1100,height=775";
		var contentsPage = window.open(url, "contentsWin", winOption);
		contentsPage.focus();
	}

	function contentsShare(cntsCd) {
		var url = generateUrl("/mng/library/clibOlcCnts/addShareFormPop",{ "cntsCd":cntsCd});
        var addContent = "<iframe id='contentsShareFrame' name='contentsShareFrame' width='100%' height='100%' "
            + "frameborder='0' scrolling='auto' src='"+url+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(600, 500);
		modalBox.setTitle("<spring:message code="library.title.contents.share"/>");
		modalBox.show();
	}
</script>
