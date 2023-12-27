<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<mhtml:home_html>
<mhtml:home_head titleName="${MENUNAME}">
	<mhtml:head_module fileupload="y"/>
	<meditag:js src="/js/jquery/jquery.cookie.js"/>
	<meditag:js src="/js/jquery/jquery.hotkeys.js"/>
	<meditag:js src="/js/jquery/jquery.jstree.js"/>
</mhtml:home_head>

<body style="padding-top: 0px;">
	<div>
		<div id="folderDiv" style="border:1px solid #dedede;width:38%;height: 550px;float:left;padding-top:5px;font-size:12px;overflow-y: auto;"></div>
		<div  id="fileDiv" style="border:1px solid #dedede;width:60%;min-height:550px;float:right;font-size:12px;"></div>
	</div>

<script type="text/javascript">
	var dirTree = null;
	var ItemDTO = {"filePath":"","fileNm":""};
	$(document).ready(function() {
		dirTree = $('#folderDiv').jstree({
			"plugins" : [ "themes", "json_data", "types", "ui", "wholerow", "state", "hotkeys","contextmenu", "crrm"],
			"json_data" : {
				"ajax" : {
					"url" : cUrl("/home/olc/userFile/listDir"),
					"data" : function (n) {
						var str = n.attr ? n.attr("filePath") : "";
						return {
							"filePath" : n.attr ? n.attr("filePath") : "",
							"fileNm" : n.attr ? n.attr("fileNm") : "",
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
						"valid_children" : "none",
						"icon" : { "image" : "/img/framework/icon/filetype/file.png" }
					},
					"root" : {
						"valid_children" : [ "default", "folder" ],
						"icon" : { "image" : "/img/framework/icon/filetype/root.png" },
						"move_node" : false,
						"rename" : false,
						"remove" : false
					},
					"folder" : {
						"valid_children" :  [ "default", "folder" ],
						"icon" : { "image" : "/img/framework/icon/filetype/folder.png" }
					}
				}
			},
			"themes" : { "theme" : "default", "dots" : true },
			"core" : {check_callback : true},
			"contextmenu" : {
				"items" : function (obj) {
					return {
						"create" : {
							"label" : "<spring:message code="course.title.contents.write.folder"/>",
							"icon" : "/img/framework/icon/icon_folder_add.gif",
							"_disabled" : false,
							"action" : function () {
								createNode(obj);
							}
						},
						"rename" : {
							"label" : "<spring:message code="course.title.contents.rename"/>",
							"icon" : "/img/framework/icon/icon_function_edit.gif",
							"_disabled" : obj.attr("rel") == "root",
							"action" : function () {
								renameNode(obj);
							}
						},
						"remove" : {
							"label" : "<spring:message code="course.title.contents.delfile"/>",
							"icon" : "/img/framework/icon/icon_delete.gif",
							"_disabled" : obj.attr("rel") == "root",
							"action" : function () {
								removeNode(obj);
							}
						}
					}
				}
			}
		})
		.bind("rename.jstree", function(e, data) {
			var filePath = data.rslt.obj.attr("filePath");
			var fileName = data.rslt.obj.attr("fileNm");
			var newFileName = data.rslt.new_name;
			if(validateFileName(newFileName)) {
				$.ajaxSetup({async: false});
	            $.post(
					cUrl("/home/olc/userFile/renameDir"),
					{
						"filePath" : filePath,
						"fileNm" : fileName,
						"newFileNm" : newFileName
					},
					function(resultDTO) {
						if(resultDTO.result >= 0) {
							var returnDTO = resultDTO.returnDto;
							data.rslt.obj.attr("filePath", returnDTO.attr.filePath);
							data.rslt.obj.attr("fileNm", returnDTO.attr.fileNm);
							$(this).jstree("refresh");
                		} else {
                			// 비정상 처리
	                    	$.jstree.rollback(data.rlbk);
                		}
                    });
	            $.ajaxSetup({async: true});
			} else {
				$.jstree.rollback(data.rlbk);
			}
		})
		.bind("create.jstree", function(e, data) {
			var filePath = data.rslt.obj.attr("filePath");
			var fileName = data.rslt.obj.attr("fileNm");
			var newFileName = data.rslt.name;

			if(validateFileName(newFileName)) {
				$.ajaxSetup({async: false});
				$.post(
					cUrl("/home/olc/userFile/renameDir"),
					{
						"filePath" : filePath,
						"fileNm" : fileName,
						"newFileNm" : newFileName
					},
					function(resultDTO) {
						if(resultDTO.result >= 0) {
							// 정상 처리
							var returnDTO = resultDTO.returnDto;
							data.rslt.obj.attr("filePath", returnDTO.attr.filePath);
							data.rslt.obj.attr("fileNm", returnDTO.attr.fileNm);
						} else {
							// 비정상 처리
							$.jstree.rollback(data.rlbk);
						}
					});
				$.ajaxSetup({async: true});
			} else {
				$.jstree.rollback(data.rlbk);
			}
		})
		.bind("select_node.jstree", function(e, data) {
			ItemDTO.filePath = data.rslt.obj.attr("filePath");
			ItemDTO.fileNm = data.rslt.obj.attr("fileNm");
			if(data.rslt.obj.attr("rel") != "root") {
				listFile();
			} else {
				$("#fileDiv").html("");
			}
		})
		.bind("loaded.jstree", function(e, data){
			//$(this).jstree("open_all"); // 모든 node open
			$(this).jstree("open_node", "#${USER_NO}"); // 1단계 노드 OPEN
		});

		function createNode(obj) {
			$.ajaxSetup({async: false});
			$.post(
				cUrl("/home/olc/userFile/createDir"),
				{
					"filePath" : obj.attr("filePath")+"/"+obj.attr("fileNm")
				},
				function(result) {
					$("#folderDiv").jstree("refresh");
					$("#folderDiv").jstree("open_node","#"+obj.attr("id"));
					$("#folderDiv").jstree("rename","#"+result.attr.id);
					//$("#folderDiv").jstree("create","#"+obj.attr("id"),"last",result);
				});
			$.ajaxSetup({async: true});
		}
		function renameNode(obj) {
			$("#folderDiv").jstree("rename","#"+obj.attr("id"));
		}
		function removeNode(obj) {
			$.ajaxSetup({async: false});
            $.post(
				cUrl("/home/olc/userFile/removeDir"),
				{
					"filePath" : obj.attr("filePath")+"/"+obj.attr("fileNm")
				},
				function(result) {
					$("#folderDiv").jstree("remove","#"+obj.attr("id"));
				});
            $.ajaxSetup({async: true});
		}
		function validateFileName(str) {
			var pattern = new RegExp(/[A-Za-z0=9_]+/);
			if(str.match(pattern)) {
				return true;
			} else {
				alert(getCommonMessage('msg039'));
				return false;
			}
		}
	});

	function uploderclick(str) {
		$("#"+str).click();
	}

	/**
	 * 선택한 폴더의 파일 목록을 가져온다.
	 */
	function listFile() {
		var url = cUrl("/home/olc/userFile/listFileManage")
		$("#fileDiv").load(url, { "filePath" : ItemDTO.filePath, "fileNm" : ItemDTO.fileNm});
	}

</script>
</body>
</mhtml:home_html>