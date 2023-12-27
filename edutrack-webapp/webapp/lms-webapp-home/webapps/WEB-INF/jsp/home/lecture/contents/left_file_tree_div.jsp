<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>

							<div id="fileBox" style="width:100%;float:left;">
								<div class="text-right" style="margin-top:10px;">
									<a href="javascript:addFile()" class="btn btn-primary btn-sm"><spring:message code="button.write.file"/> </a>
								</div>
								<div id="localFileTree" class="tree" style="float:none;width:100%;height:642px;overflow:auto; border:2px solid #4FADBC;margin-top:6px;font-size:14px;"></div>
								<div sylte="clear:both"></div>
							</div>

							<script type="text/javascript">
								$(document).ready(function() {
									$("#localFileTree").jstree({
										"plugins" : [ "themes", "json_data", "ui", "crrm", "dnd", "types", "hotkeys", "contextmenu"],
										"json_data" : {
											"ajax" : {
												"url" : "/lec/cnts/listFile",
												"data" : function (n) {
													return {
														"sbjCd" : ItemDTO.sbjCd,
														"filePath" : n.attr ? n.attr("fileDir") : "",
														"id" : n.attr ? n.attr("id") : "",
														"orderCd" :ItemDTO.orderCd
													};
												}
											}
										},
										"types" : {
											"max_depth" : -2,
											"max_children" : -2,
											"valid_children" : [ "drive" ],
											"types" : {
												"default" : {
													"valid_children" : "none",
													"icon" : { "image" : "/img/framework/icon/filetype/file.png" }
												},
												"file" : {
													"valid_children" : "none",
													"icon" : { "image" : "/img/framework/icon/filetype/file.png" },
													"start_drag" : false,
													"move_node" : false
												},
												"html" : {
													"valid_children" : "none",
													"icon" : { "image" : "/img/framework/icon/filetype/html.gif" }
												},
												"media" : {
													"valid_children" : "none",
													"icon" : { "image" : "/img/framework/icon/filetype/movie.gif" }
												},
												"zip" : {
													"valid_children" : "none",
													"icon" : { "image" : "/img/framework/icon/filetype/zip.gif" },
													"start_drag" : false,
													"move_node" : false
												},
												"folder" : {
													"valid_children" : [ "default", "folder" ],
													"icon" : { "image" : "/img/framework/icon/filetype/folder.png" },
													"start_drag" : false,
													"move_node" : false
												},
												"drive" : {
													"valid_children" : [ "default", "folder" ],
													"icon" : { "image" : "/img/framework/icon/filetype/root.png" },
													"start_drag" : false,
													"move_node" : false,
													"delete_node" : false,
													"remove" : false
												}
											}
										},
										"dnd" : {"drag_start" : function (data) { alert("DRAG OK"); }},
										"core" : { "initially_open" : [ "00" ] },
										"themes" : { "theme" : "default", "dots" : true },
										"rules" : { "multitree" : true },
										"contextmenu" : {
											"items" : function (obj) {
												var fileType = obj.attr("fileType");
												var fileExt = (fileType == "F") ? obj.attr("fileName").split(".")[1]:"";
												var fileName = obj.attr("fileName");
												var fileDir = obj.attr("fileDir");
												setFile(obj);
												return {
													"select_file" : {
														"label" : "<spring:message code="course.title.contents.link"/>",
														"icon" : "/img/framework/icon/icon_import.gif",
														"_disabled" : fileExt.toLowerCase() != "html" &&  fileExt.toLowerCase() != "htm" && fileExt.toLowerCase() != "mp4",//-- &&  fileExt.toLowerCase() != "mp3" && fileExt.toLowerCase() != "wmv" && fileExt.toLowerCase() != "wma" , 2016.03.19 미디어 콘텐츠 개발로 연결 삭제함.
														"action" : function () { selectFile(); }
													},
													"mobile_select_file" : {
														"label" : "<spring:message code="course.title.contents.mobile.link"/>",
														"icon" : "/img/framework/icon/icon_import.gif",
														"_disabled" : fileExt.toLowerCase() != "html" &&  fileExt.toLowerCase() != "htm" && fileExt.toLowerCase() != "mp4",//-- &&  fileExt.toLowerCase() != "mp3" && fileExt.toLowerCase() != "wmv" && fileExt.toLowerCase() != "wma" , 2016.03.19 미디어 콘텐츠 개발로 연결 삭제함.
														"action" : function () { mobileSelectFile(); }
													},
													"select_atach" : {
														"label" : "<spring:message code="course.title.contents.link.attach"/>",
														"icon" : "/img/framework/icon/icon_import.gif",
														"separator_after"	: true,
														"_disabled" : fileExt.toLowerCase() != "zip" &&  fileExt.toLowerCase() != "hwp"  &&  fileExt.toLowerCase() != "pdf"  &&  fileExt.toLowerCase() != "ppt"  &&  fileExt.toLowerCase() != "pptx" &&  fileExt.toLowerCase() != "doc"  &&  fileExt.toLowerCase() != "xls" &&  fileExt.toLowerCase() != "xlsx" && fileExt.toLowerCase() != "jpg" && fileExt.toLowerCase() != "png" && fileExt.toLowerCase() != "gif",
														"action" : function () { selectAtch(); }
													},
													"add_folder" : {
														"label" : "<spring:message code="course.title.contents.write.folder"/>",
														"icon" : "/img/framework/icon/icon_folder_add.gif",
														"_disabled" : fileType == "F",
														"action" : function () { addDir(); }
													},
													"add_file" : {
														"label" : "<spring:message code="course.title.contents.add.file"/>",
														"icon" : "/img/framework/icon/icon_file_upload.gif",
														"_disabled" : fileType == "F",
														"action" : function () { addFile();	}
													},
													"down_file" : {
														"label" : "<spring:message code="course.title.contents.download"/>",
														"icon" : "/img/framework/icon/icon_file_download.gif",
														"_disabled" : fileExt.toLowerCase() != "zip",
														"action" : function () { fileDownload(); }
													},
													"rename_file" : {
														"label" : "<spring:message code="course.title.contents.rename"/>",
														"icon" : "/img/framework/icon/icon_function_edit.gif",
														"separator_after"	: true,
														"_disabled" : fileType == "R",
														"action" : function () { fileRename(); }
													},
													"unzip_file" : {
														"label" : "<spring:message code="course.title.contents.unzip"/>",
														"icon" : "/img/framework/icon/icon_unzip.gif",
														"_disabled" : fileExt.toLowerCase() != "zip",
														"action" : function () { fileUnzip(); }
													},
													"paste_file" : {
														"label" : "<spring:message code="course.title.contents.paste"/>",
														"icon" : "/img/framework/icon/icon_paste.gif",
														"_disabled" : fileType == "F" || (ItemDTO.cutFileDir == "" || ItemDTO.cutFileDir == undefined),
														"action" : function () { filePaste(); }
													},
													"cut_file" : {
														"label" : "<spring:message code="course.title.contents.cut"/>",
														"icon" : "/img/framework/icon/icon_cut.gif",
														"_disabled" : fileType != "F",
														"action" : function () { fileCut('cut'); }
													},
													"copy_file" : {
														"label" : "<spring:message code="course.title.contents.copy"/>",
														"icon" : "/img/framework/icon/icon_copy.gif",
														"_disabled" : fileType != "F",
														"action" : function () { fileCut('copy'); }
													},
													"delete_file" : {
														"label" : "<spring:message code="course.title.contents.delfile"/>",
														"icon" : "/img/framework/icon/icon_delete.gif",
														"_disabled" : fileType == "R",
														"action" : function() {	fileDelete(); }
													}
												}
											}
										}
									})
									.bind("move_node.jstree", function (e, data) {
										$.jstree.rollback(data.rlbk);
									})
									.bind("select_node.jstree",function(e,data) {
										setFile(data.rslt.obj);
									})
									.bind("dnd_start.jstree",function(e,data) {
									})
									.bind("loaded.jstree", function(e, data){
										$(this).jstree("open_node","#00");
									});

									initFile();
								});

								function initFile() {
									ItemDTO.fileType = "";
									ItemDTO.fileName = "";
									ItemDTO.fileDir = "";
								}

								function openNodeFileTree(nodeId) {
									$("#localFileTree").jstree("open_node","#"+nodeId);
									$("#localFileTree").jstree("refresh","#"+nodeId);
								}

								function listDir(){
									$('#localFileTree').jstree('refresh',-1);
									//$("#localFileTree").jstree("refresh","#"+ItemDTO.parentFileId);
								}


								/**
								 * 선택한 파일/폴더 셋팅
								 */
								function setFile(obj) {
									var parentFileId = obj.parents("li").attr("id") == undefined ? "" : obj.parents("li").attr("id");
									var fileId = obj.attr("fileId");
									var fileType = obj.attr("fileType");
									var fileExt = (fileType == "F") ? obj.attr("fileName").split(".")[1]:"";
									var fileName = obj.attr("fileName");
									var fileDir = obj.attr("fileDir");

									ItemDTO.parentFileId = parentFileId;
									ItemDTO.fileId = fileId;
									ItemDTO.fileType = fileType;
									if(fileType == 'R') {
										ItemDTO.fileName = '';
										ItemDTO.fileDir = fileDir;
									}else if (fileType == 'D'){
										ItemDTO.fileName = "";
										ItemDTO.fileDir = fileDir;
									}else {
										ItemDTO.fileName = fileName;
										ItemDTO.fileDir = fileDir.substr(0, fileDir.length - fileName.length -1);
									}
								}

								/**
								 * 파일 업로드 폼
								 */
								function addFile() {
									var filePath = "";
									filePath = (ItemDTO.fileDir != '') ? filePath += "/"+ItemDTO.fileDir:filePath;
									filePath = (ItemDTO.fileName != '') ? filePath += "/"+ItemDTO.fileName:filePath;
									if(ItemDTO.fileType == 'F' || filePath == "") {
										alert('<spring:message code="course.message.contents.select.folder"/>');
										return;
									}
									//-- 최상단 파일 업로드일 경우
									var addContent  = "<iframe id='fileFrame' name='fileFrame' width='100%' height='100%' "
										+ "frameborder='0' scrolling='no' src='<c:url value="/lec/cnts/addFormFilePop"/>"
										+ "?sbjCd="+ItemDTO.sbjCd+"&amp;filePath="+filePath+"&amp;fileId="+ItemDTO.fileId+"'/>";
									modalBoxChild.clear();
									modalBoxChild.addContents(addContent);
									modalBoxChild.resize(500, 370);
									modalBoxChild.setTitle("<spring:message code="course.title.contents.add.file"/>");
									modalBoxChild.show();
								}

								/**
								 * 폴더 생성 폼
								 */
								function addDir() {
									var addContent  = "<iframe id='fileFrame' name='fileFrame' width='100%' height='100%' "
										+ "frameborder='0' scrolling='no' src='<c:url value="/lec/cnts/addFormDirPop"/>"
										+ "?fileDir=/"+ItemDTO.fileDir+"'/>";
									modalBoxChild.clear();
									modalBoxChild.addContents(addContent);
									modalBoxChild.resize(500, 370);
									modalBoxChild.setTitle("<spring:message code="course.title.contents.write.folder"/>");
									modalBoxChild.show();
								}

								/**
								 * 파일, 폴더 명 변경
								 */
								function fileRename() {
									var addContent  = "<iframe id='fileFrame' name='fileFrame' width='100%' height='100%' "
										+ "frameborder='0' scrolling='no' src='<c:url value="/lec/cnts/renameFormFilePop"/>"
										+ "?fileName="+ItemDTO.fileName+"&amp;fileDir=/"+ItemDTO.fileDir+"&amp;linkDir="+ItemDTO.fileName+"'/>";
									modalBoxChild.clear();
									modalBoxChild.addContents(addContent);
									modalBoxChild.resize(500, 370);
									modalBoxChild.setTitle("<spring:message code="course.title.contents.rename"/>");
									modalBoxChild.show();
								}

								/**
								 * 파일 및 폴더 삭제
								 */
								function fileDelete() {
									if (ItemDTO.fileType == "R") {
										alert("<spring:message code="course.title.contents.delfile"/>");
										return;
									}
									var fileName = (ItemDTO.fileType =='F') ? "/"+ItemDTO.fileDir+"/"+ItemDTO.fileName : "/"+ItemDTO.fileDir;
									$.getJSON( cUrl("/lec/cnts/deleteFile"),	{
											sbjCd : ItemDTO.sbjCd,
											fileDir : fileName
										},  function	(resultDTO) {
											//alert(resultDTO.message);
											if(resultDTO.result > 0) {
												$("#localFileTree").jstree("refresh","#"+ItemDTO.parentFileId);
											}
										}
									);
								}

								/**
								 * 파일 압축 풀기
								 */
								function fileUnzip() {
									showLoadingImage("localFileTree");
									if(ItemDTO.sbjCd != "" && ItemDTO.fileDir != "" && ItemDTO.fileName != "") {
										$.getJSON( cUrl("/lec/cnts/unzipFile"),	{
												sbjCd : ItemDTO.sbjCd,
												fileDir : "/"+ItemDTO.fileDir,
												fileName : ItemDTO.fileName
											},  function(resultDTO) {
												hideLoadingImage("localFileTree");
												alert(resultDTO.message);
												if(resultDTO.result > 0) {
													$('#localFileTree').jstree('refresh',-1);
												}
											}
										);
									} else {
										alert("<spring:message code="course.message.contents.unzip.failed"/>");
									}
								}

								/**
								 * 파일 자르기, 복사
								 */
							 	function fileCut(cutType) {
								 	ItemDTO.cutFileName = ItemDTO.fileName;
								 	ItemDTO.cutFileDir = "/"+ItemDTO.fileDir;
									ItemDTO.cutType = cutType;
								}

								/**
								 * 파일 붙여넣기
								 */
								function filePaste() {
									$.getJSON( cUrl("/lec/cnts/pasteFile"),	{
											sbjCd : ItemDTO.sbjCd,
										  	tarFileDir : "/"+ItemDTO.fileDir,
										  	cutFileDir : ItemDTO.cutFileDir,
										  	cutFileName : ItemDTO.cutFileName,
										  	cutType : ItemDTO.cutType
										}, function (resultDTO) {
											alert(resultDTO.message);
											if(resultDTO.result > 0) {
												$('#localFileTree').jstree('refresh',-1);
											}
										}
									);
								}

								/**
								 * 교안 파일 연결
								 */
								function selectFile() {
									var unitCd = $("#contentsForm").find('input[name="unitCd"]').val();
									var unitType = $("#contentsForm").find('input[name="unitType"]').val();
									if(unitCd == "" || unitCd == undefined) {
										alert("<spring:message code="course.message.contents.alert.msg1"/>");
										return;
									}
									if(unitType != "L") {
										alert("<spring:message code="course.message.contents.alert.msg2"/>");
										return;
									}

									if($('#contentsWriteForm').attr('style').indexOf("none") > 0 ){
										alert("<spring:message code="course.message.contents.alert.msg3"/>");
										return;
									}

									$("#contentsForm").find('input[name="unitNm"]').val(ItemDTO.fileName);
									$("#contentsForm").find('input[name="unitFilePath"]').val("/contents/${ORGCD}/"+ItemDTO.fileDir+"/"+ItemDTO.fileName);
									//$("#contentsForm").find('input[name="cntsTypeCd"]').val("LOCAL");
									$("#contentsForm").find('input[name="olcYn"]').val("N");
								}

								/**
								 * 교안 파일 연결
								 */
								function mobileSelectFile() {
									var unitCd = $("#contentsForm").find('input[name="unitCd"]').val();
									var unitType = $("#contentsForm").find('input[name="unitType"]').val();
									if(unitCd == "" || unitCd == undefined) {
										alert("<spring:message code="course.message.contents.alert.msg1"/>");
										return;
									}
									if(unitType != "L") {
										alert("<spring:message code="course.message.contents.alert.msg2"/>");
										return;
									}

									if($('#contentsWriteForm').attr('style').indexOf("none") > 0 ){
										alert("<spring:message code="course.message.contents.alert.msg3"/>");
										return;
									}

									$("#contentsForm").find('input[name="unitNm"]').val(ItemDTO.fileName);
									$("#contentsForm").find('input[name="mobileFilePath"]').val("/contents/${ORGCD}/"+ItemDTO.fileDir+"/"+ItemDTO.fileName);
									//$("#contentsForm").find('input[name="cntsTypeCd"]').val("LOCAL");
									$("#contentsForm").find('input[name="olcYn"]').val("N");
								}

								/**
								 * 첨부 파일 연결
								 */
								function selectAtch() {
									var unitCd = $("#contentsForm").find('input[name="unitCd"]').val();
									var unitType = $("#contentsForm").find('input[name="unitType"]').val();
									if(unitCd == "" || unitCd == undefined) {
										alert("<spring:message code="course.message.contents.alert.msg1"/>");
										return;
									}
									if(unitType != "L") {
										alert("<spring:message code="course.message.contents.alert.msg2"/>");
										return;
									}
									var f = document.contentsForm;
									f["atchFilePath"].value = "/contents/${ORGCD}/"+ItemDTO.fileDir+"/"+ItemDTO.fileName;
								}

								function fileDownload() {
									// download용 iframe이 없으면 만든다.
									if ( $("#_m_download_iframe").length == 0 ) {
										iframeHtml =
											'<iframe id="_m_download_iframe" name="_m_download_iframe" style="visibility: none; display: none;"></iframe>';
										$("body").append(iframeHtml);
									}
									_m_download_iframe.document.location.href = "/contents/${ORGCD}/"+ItemDTO.fileDir+"/"+ItemDTO.fileName;
								}
							</script>