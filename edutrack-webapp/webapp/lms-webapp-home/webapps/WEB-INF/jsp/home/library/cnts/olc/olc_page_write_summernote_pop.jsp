<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="clibOlcPageVO" value="${vo}"/>
<c:set var="gubun" value="${gubun}"/>
<mhtml:home_html>
<mhtml:home_head titleName="${MENUNAME}">
	<mhtml:head_module summernote="y" fileupload="y"/>
	<meditag:js src="/js/jquery/jquery.cookie.js"/>
	<meditag:js src="/js/jquery/jquery.hotkeys.js"/>
	<meditag:js src="/js/jquery/jquery.jstree.js"/>
</mhtml:home_head>

<body style="padding-top: 0px;overflow-x:hidden; ">
<form id="clibOlcCntsForm" name="clibOlcCntsForm" onsubmit="return false" >
<input type="hidden" name="cntsCd" value="${vo.cntsCd}" />
<input type="hidden" name="pageCd" value="${vo.pageCd}" />
<input type="hidden" name="pageOdr" value="${vo.pageOdr}" />
<input type="hidden" name="attachImageSns" value="${vo.attachImageSns}" />
<input type="hidden" name="attachFileSns" value="${vo.attachFileSns}" />

<table summary="<spring:message code="library.title.contents.olc.page.manage"/>" class="table table-striped table-bordered wordbreak" style="width:100%;">
	<colgroup>
		<col style="width: 15%"/>
		<col style="width: 85%"/>
	</colgroup>
	<tr>
		<th scope="row"><label for="pageNm"><spring:message code="common.title.title"/></label></th>
		<td>
			<input type="text" name="pageNm" value="${vo.pageNm}" dispName="<spring:message code="common.title.title"/>" isNull="N" lenCheck="50" maxlength="50" class="form-control input-sm" id="pageNm"  />
		</td>
	</tr>
	<tr>
		<th scope="row"><spring:message code="common.title.atachfile"/></th>
		<td>
			<div class="upload">
				<div class="upload_inbox">
					<a href="javascript:uploderclick('atchuploader');" class="btn btn-primary btn-xs"><spring:message code="button.select.file"/></a>
					<input type="file" name="atchuploader" id="atchuploader" title="<spring:message code="common.title.atachfile"/>" multiple style="display:none" /><%-- 첨부파일 버튼 --%>
					<div id="atchprogress" class="progress">
	    				<div class="progress-bar progress-bar-success"></div>
					</div>
				</div>
				<div id="atchfiles" class="multi_inbox"></div>
			</div>
		</td>
	</tr>
	<%--
	<tr>
		<th scope="row"><spring:message code="library.title.contents.olc.page.type"/></th>
		<td>
			<label><input type="radio" name="pageDiv" value="create" class="pageDiv"/> <spring:message code="library.title.contents.olc.page.write.create"/></label>
			<label style="margin-left:20px;"><input type="radio" name="pageDiv" value="link" class="pageDiv"/> <spring:message code="library.title.contents.olc.page.write.link"/></label>
		</td>
	</tr>
	 --%>
	<tr id="page-create">
		<th scope="row"><spring:message code="course.title.contents.mediacontents"/></th>
		<td>
			<input type="radio" value="contents" name="mediaType" onchange="mediaCtgr()" checked><spring:message code="library.title.contents.olc.page.media.content"/>
			<%-- <input type="radio" value="share" name="mediaType" onchange="mediaCtgr()"><spring:message code="library.title.contents.olc.page.media.share"/> --%>
			<br/>
			<div class="input-group">
				<div class="input-group-btn btn-group">
					<button onclick="clickDropdown()" type="button" class="btn btn-default btn-sm dropdown-toggle" >
						<span class="caret" style="margin-top: 6px; margin-bottom: 5px;"></span>
					</button>
					<ul class="dropdown-menu" role="menu" id="ctgrDrop">
						<li style="width:380px;">
							<div id="ctgrTreeArea" style="font-size:14px;line-height:18px;overflow:auto;width:100%;min-height:200px"></div>
						</li>
					</ul>
				</div>
				<input type="text" name="ctgrNmList" id="ctgrNmList" class="form-control input-sm" style="width:30%;background-color:#ffffff;" readonly="readonly" value=""/>
				<select id="mediaContents" name="mediaContents" class="form-control input-sm" style="width: 70%" onchange="setSummernote()">
					<option><spring:message code="library.message.contents.category.select.category"/></option>
				</select>
			</div>
		</td>
	</tr>
	<tr id="page-create">
		<td colspan="2" style="padding:0px;">
			<div id="summernote" style="width:820px; margin: 0 auto;">${vo.pageCts}</div>
			<textarea name="pageCts" id="contentTextArea" title="library.title.contents.olc.page.contents" class="sr-only">${vo.pageCts}</textarea>
		</td>
	</tr>
	<tr id="page-link" style="display:none">
		<th scope="row"><spring:message code="library.title.contents.olc.page.file.select" /></th>
		<td>
			<input type="text" dispName="<spring:message code="library.title.contents.olc.page.file.select"/>" maxlength="100" isNull="Y" name="filePath" value="${vo.filePath}" class="form-control input-sm" id="filePath" readonly="true" />
			<div style="margin-top:15px;">
				<div id="folderDiv" style="border:1px solid #dedede;width:40%;min-height:250px;float:left;padding-top:5px;font-size:12px;">

				</div>
				<div  id="fileDiv" style="border:1px solid #dedede;width:56%;min-height:250px;float:right;font-size:12px;">
				</div>
			</div>
		</td>
	</tr>
</table>
<div class="text-right">
	<c:if test="${gubun eq 'A'}">
	<a href="javascript:addPage()" class="btn btn-primary btn-sm"><spring:message code="button.add"/></a>
	</c:if>
	<c:if test="${gubun eq 'E'}">
	<a href="javascript:editPage()" class="btn btn-primary btn-sm"><spring:message code="button.add"/></a>
	<a href="javascript:delPage()" class="btn btn-warning btn-sm"><spring:message code="button.delete"/></a>
	</c:if>
	<a href="javascript:closeWrite()" class="btn btn-default btn-sm"><spring:message code="button.cancel"/></a>
</div>
<input type="submit" value="submit" style="display:none" />
</form>
<script type="text/javascript">
	var summernote;
	var atchFiles;
	var dirTree;
	var ItemDTO = {"filePath":"","fileNm":""};
	$(document).ready(function() {
		summernote = new $M.SummerNote({
			"editorId"			:	"summernote",
			"textareaId"		:	"contentTextArea",
			"repositoryCode"	:	"OLC_PAGE",
			'organization' 		: 	"${USER_ORGCD}",
			"editorHeight"		:	"300px",
			"attachments"		:	$.parseJSON('${vo.attachImagesJson}')
		});

		// 파일 첨부 업로더 및 바인딩 및 초기화 @see /js/common_function.js 참조.
		atchFiles = new $M.JqueryFileUpload({
							"varName"			: "atchFiles",
							"files" 			: $.parseJSON('${vo.attachFilesJson}'),
							"uploaderId"		: "atchuploader",
							"fileListId"		: "atchfiles",
							"progressId"		: "atchprogress",
							"maxcount"			: 3,
							"uploadSetting"	: {
								'formData'		: { 'repository': 'OLC_PAGE',
													'organization' : "${USER_ORGCD}",
													'type'		: 'file' }
							}
						});

		$(".pageDiv").bind("click", function(event) {
			pageDivCheck();
		});

		dirTree = $('#folderDiv').jstree({
			"plugins" : [ "themes", "json_data", "types", "ui", "wholerow", "state", "hotkeys","contextmenu", "crrm"],
			"json_data" : {
				"ajax" : {
					"url" : cUrl("/home/library/clibOlcFile/listDir"),
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
					cUrl("/home/library/clibOlcFile/renameDir"),
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
					cUrl("/home/library/clibOlcFile/renameDir"),
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
				cUrl("/home/library/clibOlcFile/createDir"),
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
				cUrl("/home/library/clibOlcFile/removeDir"),
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
		mediaCtgr();
		pageDivCheck();
	});

	function mediaCtgr(){
		var ctgrType = $(':radio[name="mediaType"]:checked').val();
		var cmd = "";
		if(ctgrType=="share"){
			cmd = "listShareCtgrJsTree";
		} else  {
			cmd = "listCtgrJsTree";
		}
		$("#ctgrNmList").val("");
		$("#mediaContents").html("<option><spring:message code="library.message.contents.category.select.category"/></option>");

		olcCtgrTree = $('#ctgrTreeArea').jstree({
			"plugins" : [ "themes", "json_data", "types", "ui", "wholerow", "state"],
			"json_data" : {
				"ajax" : {
					"url" : cUrl("/home/library/clibCntsCtgr/"+ cmd),
					"data" : function (n) {
						return {
							"id" : n.attr ? n.attr("id") : "#"
						};
					}
				}
			},
			"types" : {
				"valid_children" : [ "root" ],
				"types" : {
					"default" : {
						"valid_children" : "none",
						"icon" : { "image" : "/img/framework/icon/filetype/file.png" }
					},
					"root" : {
						"valid_children" : "all",
						"icon" : { "image" : "/img/framework/icon/icon_administrator.gif" }
					},
					"category" : {
						"valid_children" :  "all",
						"icon" : { "image" : "/img/framework/icon/icon_contents.gif" }
					},
					"contents" : {
						"valid_children" : "none",
						"icon" : { "image" : "/img/framework/icon/icon_lesson.gif" }
					}
				}
			},
			"themes" : { "theme" : "default", "dots" : true }
		}).bind("select_node.jstree", function(e, data) {
			setSelectNode(data);
		}).bind("loaded.jstree", function(e, data){
			$(this).jstree("open_all");
		});

	}

	function clickDropdown() {
		$("#ctgrDrop").toggle();
	}

	function uploderclick(str) {
		$("#"+str).click();
	}

	function setSelectNode(data) {
		var id = data.rslt.obj.attr("id");
		var name = data.rslt.obj.attr("ctgrNm");
		var rel = data.rslt.obj.attr("rel");
		var subCnt = data.rslt.obj.attr("subCnt");
		var cntsCnt = data.rslt.obj.attr("cntsCnt");

		ItemDTO.nodeId = id;
		ItemDTO.nodeType = rel;
		ItemDTO.nodeName = name;
		ItemDTO.subCnt = subCnt;
		ItemDTO.cntsCnt = cntsCnt;

		if("root" == id){
			name = "";
		}
		//-- form에 적용
		//$("#ctgrCd").val(id);
		$("#ctgrNmList").val(name);
		$("#ctgrDrop").hide();
		$("#mediaContents").html("<option><spring:message code="library.message.contents.category.select.category"/></option>");

		var ctgrType = $(':radio[name="mediaType"]:checked').val();
		var cmd = "";
		var url = "";
		if(ctgrType=="share"){
			cmd = "listMediaCntsForOlc";
			url = cUrl("/home/library/clibShareCnts/" +cmd );
			if(ItemDTO.nodeId != 'root' && ItemDTO.nodeId != '') {
				$("#mediaContents").load(
						url, {
								"ctgrCd":ItemDTO.nodeId,
							}
				);
			}
		} else  {
			cmd = "listOlc";
			url = cUrl("/home/library/clibMediaCnts/" + cmd);
			if(ItemDTO.nodeId != 'root' && ItemDTO.nodeId != '') {
				$("#mediaContents").load(
						url, {
								"ctgrCd":ItemDTO.nodeId,
							}
				);
			}

		}

	}

	function pageDivCheck() {
		var divCd = $(':input:radio[name=pageDiv]:checked').val();
		if(divCd == 'link') {
			$("#page-link").show();
			$("#page-create").hide();
			//에디터 입력값 초기화
			$('.note-editable').html("");
		} else {
			$("#page-link").hide();
			$("#page-create").show();
		}
	}

	/**
	 * 선택한 폴더의 파일 목록을 가져온다.
	 */
	function listFile() {
		var url = cUrl("/home/library/clibOlcFile/listFileCnts")
		$("#fileDiv").load(url, { "filePath" : ItemDTO.filePath, "fileNm" : ItemDTO.fileNm});
	}

    /**
	 * 서브밋 처리
	 */
	function process(cmd) { //cmd : 해당메소드
		$('#clibOlcCntsForm').attr("action","/home/library/clibOlcCnts/"+cmd);
    	if(!validate(document.clibOlcCntsForm)) return false;
		var _content = $("#summernote").summernote('code');
		/*
		var pageDiv = $(":input:radio[name=clibOlcPageVO\\.pageDiv]:checked").val();
		if(pageDiv == "create"){
			if(_content == "<p><br></p>" || isEmpty(_content)){
				alert('<spring:message code="board.message.bbs.atcl.alert.input.cnts"/>');
				return;
			}
		} else if(pageDiv == "link"){
			if($("#filePath").val() == ""){
				alert('<spring:message code="library.message.contents.olc.page.select.file"/>');
				return;
			}
		} else {
			alert('<spring:message code="library.message.contents.olc.page.select.pagediv"/>');
			return;
		}
 		*/
		//-- 내용 form에 setting
		$("#contentTextArea").val(_content);

		// 에디터 첨부파일 번호 목록 추출
		var _paramImages = summernote.getFileSnAll();
		// atchFiles에서 첨부파일 정보를 가져온다.
		var _paramFiles = atchFiles.getFileSnAll();
		$(':input:hidden[name=attachImageSns]').val(_paramImages);
		$(':input:hidden[name=attachFileSns]').val(_paramFiles);

		$('#clibOlcCntsForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		if(!isNull(resultDTO.message))
			alert(resultDTO.message);
		if(resultDTO.result >= 0) {
			// 정상 처리
			if(resultDTO.pageCd != ""){
				parent.document.getElementById("pageCd").value = resultDTO.pageCd;
				parent.listPage();
				parent.closeWrite(resultDTO.pageCd);
			} else {
				parent.listPage();
				parent.closeWriteArea();
			}
		} else {
			// 비정상 처리
		}
	}

	/* 글 저장 */
	function addPage() {
		process('addPage');
	}

	/* 글 수정 */
	function editPage() {
		process('editPage');
	}

	/* 글 삭제 */
	function delPage() {
		if(confirm('<spring:message code="olc.message.contents.confirm.delete"/>')) {
			$('#cmd').val("deletePage");
			$('#clibOlcCntsForm').ajaxSubmit(processCallback);
		} else {
			return;
		}
	}

	function closeWrite() {
		parent.closeWriteArea();
	}


	function setSummernote(){
		var mediaSrc = $("#mediaContents").val()
		var editorHtml = $('.note-editable').html();
		var videohtml = "<iframe frameborder='0' src='"+mediaSrc+"' width='100%' height='360' class='note-video-clip'></iframe>";
		var setHtml = editorHtml+"<br>"+videohtml;
		$('.note-editable').html(setHtml);
	}
</script>
</body>
</mhtml:home_html>