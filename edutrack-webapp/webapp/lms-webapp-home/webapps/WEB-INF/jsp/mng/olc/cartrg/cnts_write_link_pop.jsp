<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="olcCntsVO" value="${vo}"/>
<c:set var="gubun" value="${gubun}"/>
<mhtml:mng_html>
<mhtml:mng_head titleName="${MENUNAME}">
	<mhtml:head_module summernote="y" fileupload="y"/>
	<meditag:js src="/js/jquery/jquery.cookie.js"/>
	<meditag:js src="/js/jquery/jquery.hotkeys.js"/>
	<meditag:js src="/js/jquery/jquery.jstree.js"/>
</mhtml:mng_head>

<mhtml:frm_body cssTag="background-color:#ffffff;">
<form id="olcCartrgForm" name="olcCartrgForm" onsubmit="return false">
<input type="hidden" name="cartrgCd" value="${vo.cartrgCd }" />
<input type="hidden" name="cntsCd" value="${vo.cntsCd }" />
<input type="hidden" name="cntsDiv" value="${vo.cntsDiv }" />
<input type="hidden" name="cntsLoc" value="${vo.cntsLoc }" />
<input type="hidden" name="cntsOdr" value="${vo.cntsOdr }" />
<input type="hidden" name="attachImageSns" value="${vo.attachImageSns }" />
<input type="hidden" name="attachFileSns" value="${vo.attachFileSns }" />
<table summary="<spring:message code="board.title.bbs.atcl.cnts"/>" class="table table-striped table-bordered">
	<colgroup>
		<col style="width: 15%"/>
		<col style="width: 85$"/>
	</colgroup>

	<tr>
		<th scope="row"><label for="cntsNm"><spring:message code="common.title.title"/></label></th>
		<td>
			<input type="text" name="cntsNm" value="${vo.cntsNm }" dispName="<spring:message code="common.title.title"/>" isNull="N" lenCheck="50" maxlength="50" class="form-control input-sm" id="cntsNm" />
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
	<tr>
		<td colspan="2" style="padding:0px;">
			<div id="summernote" style="width:820px; margin: 0 auto;">${olcCntsVO.cntsCts}</div>
			<textarea name="cntsCts" id="contentTextArea" title="olc.title.contents..cnts" class="sr-only">${vo.cntsCts }</textarea>
		</td>
	</tr>
	<tr>
		<th scope="row"><spring:message code="olc.title.contents.select.file" /></th>
		<td>
			<input type="text" dispName="<spring:message code="olc.title.contents.cnts"/>" maxlength="100" isNull="N" name="cntsFilePath" value="${vo.cntsFilePath }" class="form-control input-sm" id="cntsFilePath"/>
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
	<a href="javascript:addCnts()" class="btn btn-primary btn-sm"><spring:message code="button.add"/></a>
	</c:if>
	<c:if test="${gubun eq 'E'}">
	<a href="javascript:editCnts()" class="btn btn-primary btn-sm"><spring:message code="button.add"/></a>
	<a href="javascript:delCnts()" class="btn btn-warning btn-sm"><spring:message code="button.delete"/></a>
	</c:if>
	<a href="javascript:closeWrite()" class="btn btn-default btn-sm"><spring:message code="button.cancel"/></a>
</div>
<input type="submit" value="submit" style="display:none" />
</form>
<script type="text/javascript">
	var dirTree = null;
	var ItemDTO = {"filePath":"","fileNm":""};
	$(document).ready(function() {
		summernote = new $M.SummerNote({
			"editorId"			:	"summernote",
			"textareaId"		:	"contentTextArea",
			"repositoryCode"	:	"OLCCNTS",
			'organization' 		: 	"${USER_ORGCD}",
			"editorHeight"		:	"300px",
			"attachments"		:	$.parseJSON('${olcCntsVO.attachImagesJson}')
		});
		// 파일 첨부 업로더 및 바인딩 및 초기화 @see /js/common_function.js 참조.
		atchFiles = new $M.JqueryFileUpload({
							"varName"			: "atchFiles",
							"files" 			: $.parseJSON('${olcCntsVO.attachFilesJson}'),
							"uploaderId"		: "atchuploader",
							"fileListId"		: "atchfiles",
							"progressId"		: "atchprogress",
							"maxcount"			: 3,
							"uploadSetting"	: {
								'formData'		: { 'repository': 'OLCCNTS',
													'organization' : "${USER_ORGCD}",
													'type'		: 'file' }
							}
						});

		dirTree = $('#folderDiv').jstree({
			"plugins" : [ "themes", "json_data", "types", "ui", "wholerow", "state", "hotkeys","contextmenu", "crrm"],
			"json_data" : {
				"ajax" : {
					"url" : cUrl("/mng/olc/userFile/listDir"),
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
					cUrl("/mng/olc/userFile/renameDir"),
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
					cUrl("/mng/olc/userFile/renameDir"),
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
				cUrl("/mng/olc/userFile/createDir"),
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
				cUrl("/mng/olc/userFile/removeDir"),
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
		$("#cntsNm").focus();
	});


	function uploderclick(str) {
		$("#"+str).click();
	}

	/**
	 * 선택한 폴더의 파일 목록을 가져온다.
	 */
	function listFile() {
		var url = cUrl("/mng/olc/userFile/listFileCnts")
		$("#fileDiv").load(url, { "filePath" : ItemDTO.filePath, "fileNm" : ItemDTO.fileNm});
	}

    /**
	 * 서브밋 처리
	 */
	function process(cmd) { //cmd : 해당메소드
		$('#olcCartrgForm').attr("action", "/mng/olc/cartrg/" + cmd );
    	if(!validate(document.olcCartrgForm)) return false;
    	var _content = $("#summernote").summernote('code');
		if(_content == "<p><br></p>" || isEmpty(_content)){
			alert('<spring:message code="board.message.bbs.atcl.alert.input.cnts"/>');
			return false;
		}
		//-- 내용 form에 setting
		$("#contentTextArea").val(_content);

		// 에디터 첨부파일 번호 목록 추출
		var _paramImages = summernote.getFileSnAll();
		// atchFiles에서 첨부파일 정보를 가져온다.
		var _paramFiles = atchFiles.getFileSnAll();
		$(':input:hidden[name=attachImageSns]').val(_paramImages);
		$(':input:hidden[name=attachFileSns]').val(_paramFiles);
		$('#olcCartrgForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		if(!isNull(resultDTO.message))
			alert(resultDTO.message);
		if(resultDTO.result >= 0) {
			// 정상 처리
			parent.listCnts();
			parent.closeWrite();
		} else {
			// 비정상 처리
		}
	}

	/* 글 저장 */
	function addCnts() {
		process("addCnts");
	}

	/* 글 수정 */
	function editCnts() {
		process("editCnts");
	}

	/* 글 삭제 */
	function delCnts() {
		if(confirm('<spring:message code="olc.message.contents.confirm.delete"/>')) {
			process("deleteCnts");
		} else {
			return;
		}
	}

	/* 등록/수정 창을 닫는다.*/
	function closeWrite() {
		parent.closeWrite();
	}
</script>
</mhtml:frm_body>
</mhtml:mng_html>