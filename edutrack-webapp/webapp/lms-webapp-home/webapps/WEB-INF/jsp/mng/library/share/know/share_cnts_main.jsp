<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<mhtml:mng_html>
<mhtml:mng_head titleName="${MENUNAME}">
	<mhtml:head_module paging="Y" fileupload="y"/>
	<meditag:js src="/js/modaldialog.js"/>
	<meditag:js src="/js/jquery/jquery.cookie.js"/>
	<meditag:js src="/js/jquery/jquery.hotkeys.js"/>
	<meditag:js src="/js/jquery/jquery.jstree.js"/>
</mhtml:mng_head>

<mhtml:mng_body>
			<mhtml:mng_title title="${MENUNAME}" location="${MENUPATH}"/>
			<div class="row" id="content">
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
						<ul class="nav nav-tabs" id="tabMenuOper" style="margin-bottom: 5px;">
				  			<li class="active" id="mediaTab" ><a href="javascript:onclickTab(0)"><spring:message code="library.title.contents.media"/></a></li>
				  			<li id="olcTab" ><a href="javascript:onclickTab(1)"><spring:message code="course.title.contents.olccontents"/></a></li>
						</ul>
						<form name="Search" onsubmit="return false" class="form-inline">
						<div class="panel panel-default">
							<div class="panel-heading">
								<span id="contentsTitle"><spring:message code="library.title.contents.category"/> : </span>
								<div class="pull-right" style="margin-top:-4px;">
									<select name="listScale" id="listScale" onchange="listPageing('1')" class="form-control input-sm" style="width: 100px;float: right;">
										<option value="10">10</option>
										<option value="20" selected="selected">20</option>
										<option value="40">40</option>
										<option value="60">60</option>
										<option value="80">80</option>
										<option value="100">100</option>
										<option value="200">200</option>
									</select>
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
<script type="text/javascript">
	var ctgrTree = null;
	var modalBox = null;
	var ItemDTO = { };

	/** 메인 페이지 초기화 */
	$(document).ready(function() {
		modalBox = new $M.ModalDialog({
			"modalid" : "modal1"
		});
		ItemDTO.nodeId ="";
		ItemDTO.tab = "media";

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

		ctgrTree = $('#ctgrTreeArea').jstree({
			"plugins" : [ "themes", "json_data", "types", "ui", "wholerow", "state", "hotkeys","contextmenu", "crrm"],
			"json_data" : {
				"ajax" : {
					"url" : cUrl("/mng/library/clibShareCntsCtgr/listJsTree"),
					"data" : function (n) {
						return {
							"divCd" : "KNOW",
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
				cUrl("/mng/library/clibShareCntsCtgr/editCtgr"),
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
				cUrl("/mng/library/clibShareCntsCtgr/editCtgr"),
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
				alert("<spring:message code="library.message.manage.know.ctgrdepth"/>");
				return;
			}
            $.post(
				cUrl("/mng/library/clibShareCntsCtgr/addCtgr"),
				{
					"ctgrCd" : parCtgrCd,
					"ctgrNm" : ctgrNm,
					"divCd" : "KNOW"
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
				alert("<spring:message code="library.message.contents.category.delete.alert.subcate"/>");
				return;
			}
			if(obj.attr("olcCnt") > 0) {
				alert("<spring:message code="library.message.contents.category.delete.alert.contents"/>");
				return;
			}
			$.ajaxSetup({async: false});
            $.post(
				cUrl("/mng/library/clibShareCntsCtgr/deleteCtgr"),
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
			var url = cUrl("/mng/library/clibShareCntsCtgr/");
			var cmd ="moveDownCtgr";
			if(str == 'up') cmd = "moveUpCtgr";
            $.post(
				cUrl("/mng/library/clibCntsCtgr" + cmd),
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

		var listScale = $("#listScale option:selected").val();
		var url = "";
		var searchKey = $("#searchKey option:selected").val();
		var searchValue = $('#searchValue').val();
		var searchCd = $("#searchCd option:selected").val();

		if(ItemDTO.tab == "media"){
			url = cUrl("/mng/library/clibShareCnts/listShareMediaKnow");
			$("#contentsList").load(url,
					{	"ctgrCd":ItemDTO.nodeId
						,"useYn":"Y"
						,"searchKey":searchKey
						,"searchValue":searchValue
						,"searchCd":searchCd
						,"curPage" : page
						,"listScale":listScale
					});
		} else if(ItemDTO.tab == "olc"){
			url = cUrl("/mng/library/clibShareCnts/listShareOlcKnow");
			$("#contentsList").load(url,
					{	"ctgrCd":ItemDTO.nodeId
						,"useYn":"Y"
						,"searchKey":searchKey
						,"searchValue":searchValue
						,"searchCd":searchCd
						,"curPage" : page
						,"listScale":listScale
					});
		}
		/*
		if(ItemDTO.nodeId != 'root' && ItemDTO.nodeId != '') {
		}
		 */
	}

	function onclickTab(tab){
		$("#mediaTab").removeClass("active");
		$("#olcTab").removeClass("active");
		if(tab==0){
			$("#mediaTab").addClass("active");
			ItemDTO.tab = "media";
		} else if(tab==1){
			$("#olcTab").addClass("active");
			ItemDTO.tab = "olc";
		}
		listPageing(1);
	}


	function previewMedia(cntsCd) {
		var url = cUrl("/mng/library/clibShareCnts/previewMediaPop")+"?cntsCd="+cntsCd;
		var winOption = "left=0,top=0,toolbar=no,location=no,directories=0,status=0,menubar=no,scrollbars=yes,resizable=no,width=800,height=480";
		var contentsWin = window.open(url, "contentsWin", winOption);
		contentsWin.focus();
	}

	function previewOlc(userNo, cntsCd, cntNm) {
		var url = cUrl("/mng/library/clibShareCnts/previewOlcMain")+"?clibShareOlcCntsDTO.cntsCd="+cntsCd;
		var winOption = "left=0,top=0,toolbar=no,location=no,directories=0,status=0,menubar=no,scrollbars=no,resizable=no,width=1100,height=775";
		var contentsPage = window.open(url, "contentsWin", winOption);
		contentsPage.focus();
	}
	//공유거절
	function delMediaShare(cntsCd) {
		if(!confirm("<spring:message code="library.message.contents.share.confirm.delete"/>")) {
			return;
		}
		var url = cUrl("/mng/library/clibShareCnts/rejectShareMediaCnts");
		var data = {"clibShareMediaCntsVO.cntsCd":cntsCd};
		$.getJSON(url, data, function(resultDTO){
			alert(resultDTO.message);
			if(resultDTO.result >= 0) {
				listPageing(1);
			} else {
				// 비정상 처리
			}
		});
	}
	//공유거절
	function delOlcShare(cntsCd) {
		if(!confirm("<spring:message code="library.message.contents.share.confirm.delete"/>")) {
			return;
		}
		var url = cUrl("/mng/library/clibShareCnts/rejectShareOlcCnts");
		var data = {"cntsCd":cntsCd};
		$.getJSON(url, data, function(resultDTO){
			alert(resultDTO.message);
			if(resultDTO.result >= 0) {
				listPageing(1);
			} else {
				// 비정상 처리
			}
		});
	}


	function userInfo(userNo) {
		var addContent  = "<iframe id='userInfoFrame' name='userInfoFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/mng/user/userInfo/viewUserPop"/>"
			+ "?usrUserInfoVO.userNo="+userNo+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(1000, 600);
		modalBox.setTitle("<spring:message code="user.title.userinfo.user.info"/>");
		modalBox.show();
	}
</script>
</mhtml:mng_body>

</mhtml:mng_html>
