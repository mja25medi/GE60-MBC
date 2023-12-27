<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>

<section class="content">
	<div class="row" id="content">
		<div class="box">
			<div class="box-body">
				<div class="col-md-12 col-xs-18">
					<div class="well well-sm" style="margin-bottom:5px;">
						<spring:message code="course.title.subject.contents.manage"/> : ${osVO.sbjNm }
						<div class="pull-right">
							<a href="<c:url value="/mng/main/goMenuPage"/>?mcd=${MENUCODE}" class="btn btn-default btn-xs"><spring:message code="button.list"/></a>
						</div>
						<div style="clear:both"></div>
					</div>
				</div>
				<div class="col-md-5 col-xs-18">
					<div class="panel panel-default">
						<div class="panel-heading" style="padding:0px;">
							<ul class="nav nav-tabs" id="tabMenuCnts" style="width:100%">
								<li class="active"><a href="javascript:tabBox('3')"><spring:message code="course.title.contents.olccontents"/></a></li>
								<li><a href="javascript:tabBox('4')"><spring:message code="course.title.contents.mediacontents"/></a></li>
								<li><a href="javascript:tabBox('1')"><spring:message code="course.title.contents.localfile"/></a></li>
								<li><a href="javascript:tabBox('2')"><spring:message code="course.title.contents.othersubject"/></a></li>
							</ul>
						</div>
						<div class="panel-body" id="leftWorkArea" style="border-top:0px;">

						</div>
					</div>
				</div>
				<div class="col-md-7 col-xs-18">
					<div class="panel panel-default">
						<div class="panel-heading">
							<font color="red"><spring:message code="course.title.content.help.message"/></font>
							<div class="pull-right">
								<a href="javascript:openAll()" class="btn btn-default btn-xs"><spring:message code="button.open.all"/></a>
								<a href="javascript:closeAll()" class="btn btn-default btn-xs"><spring:message code="button.close.all"/></a>
								<a href="javascript:pageprint()" class="btn btn-default btn-xs"><spring:message code="button.print"/></a>
							</div>
							<div style="clear:both"></div>
						</div>
						<div class="panel-body">
							<div id="sbjCntsTree" class="tree" style="width:100%;height:398px;overflow:auto;border:2px solid #4FADBC;font-size:14px;"></div>
							<div id="contentsWriteArea" style="border:2px solid #4FADBC;width:100%;height:283px;margin-top:7px;">
								<div id="contentsWriteForm" style="width:100%;display:none;">
									<form id="contentsForm" name="contentsForm" class="form-inline" onsubmit="return false">
									<input type="hidden" name="sbjCd" value="${vo.sbjCd}" />
									<input type="hidden" name="unitType" value="${vo.unitType}" />
									<input type="hidden" name="cntsTypeCd" value="${vo.cntsTypeCd}" />
									<input type="hidden" name="unitCd" value="${vo.unitCd}" />
									<input type="hidden" name="parUnitCd" value="${vo.parUnitCd}" />
									<input type="hidden" name="unitLvl" value="${vo.unitLvl}" />
									<input type="hidden" name="unitOdr" value="${vo.unitOdr}" />
									<input type="hidden" name="moveType" value="${vo.moveType}" />
									<input type="hidden" name="olcYn" value="${vo.olcYn}" />
									<table style="width:96%;margin-top:5px;margin-left:10px;font-size:14px;">
										<tr>
											<td style="width:22%;padding:2px;">
												<label for="unitNm"><spring:message code="course.title.contents.name"/></label>
											</td>
											<td colspan="2" style="padding:2px;">
												<input type="text" dispName="<spring:message code="course.title.contents.name"/>" style="width:100%" maxlength="100" name="unitNm" value="${vo.unitNm }"  onfocus="this.select()" class="form-control input-sm" id="unitNm" />
											</td>
										</tr>
										<tr>
											<td style="padding:2px;">
												<label for="unitFilePath"><spring:message code="course.title.contents.path"/></label>
											</td>
											<td colspan="2" style="padding:2px;">
												<div class="input-group" style="width:100%">
													<input type="text" style="background-color:#f3f3f3" dispName="<spring:message code="course.title.contents.path"/>" name="unitFilePath" value="${vo.unitFilePath }" readonly="true" class="form-control input-sm" id="unitFilePath" />
													<span class="input-group-addon" onclick="delPath('unitFilePath')" style="cursor:pointer;width:50px;">
														<i class="glyphicon glyphicon-remove"></i>
													</span>
												</div>
											</td>
										</tr>
										<tr>
											<td style="padding:2px;">
												<label for="atchFilePath"><spring:message code="course.title.contents.attach"/></label>
											</td>
											<td colspan="2" style="padding:2px;">
												<div class="input-group" style="width:100%">
													<input type="text" style="background-color:#f3f3f3" dispName="<spring:message code="course.title.contents.attach"/>" name="atchFilePath" value="${vo.atchFilePath }" readonly="true" class="form-control input-sm" id="atchFilePath" />
													<span class="input-group-addon" onclick="delPath('atchFilePath')" style="cursor:pointer;width:50px;">
														<i class="glyphicon glyphicon-remove"></i>
													</span>
												</div>
											</td>
										</tr>
										<tr>
											<td style="padding:2px;">
												<spring:message code="course.title.contents.progress.method"/>
											</td>
											<td colspan="2" style="padding:2px;">
												<label style="font-weight: normal;margin-right:20px;">
													<input type="radio" style="border:0" name="prgrChkType" value="TIME" <c:if test="${vo.prgrChkType eq 'TIME'}">checked</c:if> onclick="prgrChkType('TIME')" id="prgrChkTypeTIME"/> <spring:message code="course.title.contents.progress.time"/>
												</label>
												<label style="font-weight: normal;">
													<input type="radio" style="border:0" name="prgrChkType" value="PAGE" <c:if test="${vo.prgrChkType eq 'PAGE'}">checked</c:if> onclick="prgrChkType('PAGE')" id="prgrChkTypePAGE"/> <spring:message code="course.title.contents.progress.page"/>
												</label>
											</td>
										</tr>
										<tr>
											<td style="padding:2px;">
												<label for="critTm"><spring:message code="course.title.contents.default.time"/></label>
											</td>
											<td width="17%" style="padding:2px;">
												<input type="text" style="width:50px;text-align:right;float:left;" dispName="<spring:message code="course.title.contents.default.time"/>" maxlength="5" lenCheck="5" name="critTm" value="${vo.critTm }"  class="form-control input-sm" id="critTm"  onkeyup="isChkNumber(this)"/>
												<span style="float:left;line-height:30px;"><spring:message code="common.title.min"/></span>
											</td>
											<td align="right" style="padding:2px;">
												<label for="prgrChkYn"><spring:message code="course.title.course.ratio.progress.check"/></label> &nbsp;<input type="checkbox" name="prgrChkYn" style="border:0" value="Y" id="prgrChkYn"  <c:if test="${vo.prgrChkYn eq 'Y'}">checked</c:if>/>
											</td>
										</tr>
										<tr>
											<td height="30" colspan="4" class="text-right">
												<a href="javascript:contentsEdit()" class="btn btn-primary btn-sm"><spring:message code="button.add"/> </a>
												<a href="javascript:closeContentsEditForm()" class="btn btn-default btn-sm"><spring:message code="button.cancel"/> </a>
											</td>
										</tr>
									</table>
									<input type="submit" value="submit" style="display:none" />
									</form>
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
				var ItemDTO 	= new Object();
				var DragItem	= new Object();
				var modalBox 	= null;
				var fileTree	= null;

				//---- 페이지 초기화 함수
				$(document).ready(function() {
					//-- 기본 모달 박스 생성
					modalBox = new $M.ModalDialog({
						"modalid" : "modal1"
					});
					$('.inputNumber').inputNumber();	// 숫자만 입력되도록 설정.
					ItemDTO.sbjCd = "${vo.sbjCd}"; // 과목 코드 설정
					
					$('#tabMenuCnts a').click(function (e) {
						  $(this).tab('show');
					});
					tabBox(3);

					//-- 콘텐츠 목차 목록 생성
					//-- 콘텐츠 목록 조회
					$('#sbjCntsTree')
						.jstree({
							"plugins" : [ "themes", "json_data", "ui", "crrm", "dnd", "types", "hotkeys", "contextmenu", "touch" ],
							"json_data" : {
								"ajax" : {
									"url" : "/mng/course/contents/listContentsJson",
									"data" : function (n) {
										ItemDTO.parUnitCd = n.attr ? n.attr("id") : "";
										return {
											"sbjCd" : ItemDTO.sbjCd,
											"id" : n.attr ? n.attr("id") : ""
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
										"icon" : { "image" : "/img/framework/icon/filetype/file.png" },
										"start_drag" : true
									},
									"lesson" : {
										"valid_children" : ["html","media","chepter","lesson"],
										"icon" : { "image" : "/img/framework/icon/icon_lesson.gif" },
										"start_drag" : true
									},
									"chepter" : {
										"valid_children" : ["chepter", "lesson", "default", "html","media"],
										"icon" : { "image" : "/img/framework/icon/icon_contents.gif" },
										"start_drag" : true
									},
									"root" : {
										"valid_children" : ["chepter", "lesson", "default", "html","media"],
										"icon" : { "image" : "/img/framework/icon/icon_course.gif" },
										"start_drag" : false
									}
								}
							},
							"core" : { "initially_open" : [ "CNTSROOT" ], "check_callback" : true },
							"themes" : { "theme" : "default", "dots" : true },
							"crrm": { "move": {	"always_copy": "multitree" }},
							"rules" : {	"multitree" : true },
							"contextmenu" : {
								"items" : function (obj) {
									return {
										"create" : {
											"label" : "<spring:message code="course.title.contents.write"/>",
											"icon" : "/img/framework/icon/icon_function_department.gif",
											"separator_after"	: true,
											"_disabled" : false,
											"action" : function () { cntsWrite(obj); }
										},
										"rename" : {
											"label" : "<spring:message code="course.title.contents.edit"/>",
											"icon" : "/img/framework/icon/icon_function_edit.gif",
											"_disabled" : obj.attr("rel") == "root",
											"action" : function () { cntsEditForm(obj.attr("unitCd")); }
										},
										"remove" : {
											"label" : "<spring:message code="course.title.contents.delete"/>",
											"icon" : "/img/framework/icon/icon_delete.gif",
											"separator_after"	: true,
											"_disabled" : obj.attr("rel") == "root",
											"action" : function () { cntsRemove(obj); }
										},
										"preview" : {
											"label" : "<spring:message code="course.title.contents.preview"/>",
											"icon" : "/img/framework/icon/icon_function_search.gif",
											"_disabled" : obj.attr("unitFilePath") == "null" || obj.attr("unitFilePath") == undefined,
											"action" : function () { cntsPreview(obj); }
										}
									}
								}
							}
						})
						.bind("move_node.jstree", function (e, data) {
						    var sourceId = $(data.rslt.o).attr("id");
						    var targetId = $(data.rslt.np).attr("id");

						    var sourceTree = $("#"+sourceId).parents(".tree").attr("id");
						    if(sourceTree == 'localFileTree') {
						    	//-- 파일에서 등록시
								$("#contentsForm").find('input[name="sbjCd"]').val(ItemDTO.sbjCd);
								$("#contentsForm").find('input[name="unitCd"]').val("");
								$("#contentsForm").find('input[name="unitType"]').val("L");
								$("#contentsForm").find('input[name="parUnitCd"]').val(targetId);
								$("#contentsForm").find('input[name="unitLvl"]').val(parseInt($(data.rslt.np).attr("unitLvl"),10)+1);
								$("#contentsForm").find('input[name="unitNm"]').val($(data.rslt.o).attr("fileName"));
								$("#contentsForm").find('input[name="unitFilePath"]').val($(data.rslt.o).attr("filePath") != "" ? "/contents/${ORGCD}/"+$(data.rslt.o).attr("fileDir") : "");
								$("#contentsForm").find('input[name="atchFilePath"]').val("");
								$("#contentsForm").find('input[name="cntsTypeCd"]').val("LOCAL");
								$("#contentsForm").find('input[id="prgrChkTypeTIME"]').prop("checked", false);
								$("#contentsForm").find('input[id="prgrChkTypePAGE"]').prop("checked", false);
								//$("#contentsForm").find('input[name="prgrChkYn"]').prop("checked", false);

								$.ajaxSetup({async: false});

								$('#contentsForm').attr("action","/mng/course/contents/addContents");
								$('#contentsForm').ajaxSubmit(function (resultDTO) {
									var item = resultDTO.returnVO;
									if(resultDTO.result >= 0) {
										$('#sbjCntsTree').jstree('refresh');
										$("#sbjCntsTree").jstree("open_node","#"+targetId);
										$("#sbjCntsTree").jstree("select_node","#"+item.unitCd);
										cntsEditForm(item.unitCd);
									} else {

									}
								});
					            $.ajaxSetup({async: true});
						    } else if (sourceTree == 'otherCntsTree') {
						    	//-- 다른 과목 콘텐츠에서 등록시
								$.ajaxSetup({async: false});
								$.post(
									cUrl("/mng/course/contents/copyOtherContents"),
									{
										"sourceSbjCd" : DragItem.sbjCd,
										"sourceUnitCd" : sourceId,
										"targetSbjCd" : ItemDTO.sbjCd,
										"targetUnitCd" : targetId == "CNTSROOT" ? "" : targetId,
										"targetUnitLvl" : $(data.rslt.np).attr("unitLvl")
									},  function	(resultDTO) {
										var item = resultDTO.returnVO;
										if(resultDTO.result >= 0) {
											//$('#sbjCntsTree').jstree('refresh');
											//$("#sbjCntsTree").jstree("open_node","#"+targetId);
											//$("#sbjCntsTree").jstree("select_node","#"+item.unitCd);
										} else {

										}
									}
								);
					            $.ajaxSetup({async: true});
						    } else if (sourceTree == 'sbjCntsTree') {
								var childStr = "";
						    	$("#"+targetId).children('ul').children("li").each(function(){
						    		childStr += "|"+$(this).attr("id");
					    		});
						    	childStr = childStr.substring(1);

					    		$.ajaxSetup({async: false});
								$.post(
									cUrl("/mng/course/contents/sortContents"),
									{
										"sbjCd" : ItemDTO.sbjCd,
										"unitCd" : sourceId,
										"parUnitCd" : targetId == "CNTSROOT" ? "" : targetId,
										"unitCdStr" : childStr
									},  function	(resultDTO) {
										if(resultDTO.result > 0) {

										} else {
											$.jstree.rollback(data.rlbk);
										}
									}
								);
					            $.ajaxSetup({async: true});
						    }
						    $("#sbjCntsTree").jstree("refresh");

						})
						.bind("select_node.jstree", function (e, data) {
							DragItem.source = "contents";
						})
						.bind("dblclick.jstree", function (event) {
							var node = $(event.target).closest("li");
						    var unitCd = node.attr('unitCd');
							cntsEditForm(unitCd);
						});

					/**
					 * 교제 단원 목차 등록
					 */
					function cntsWrite(obj) {
						$("#contentsForm").find('input[name="sbjCd"]').val(ItemDTO.sbjCd);
						$("#contentsForm").find('input[name="unitCd"]').val("");
						$("#contentsForm").find('input[name="unitType"]').val("L");
						$("#contentsForm").find('input[name="parUnitCd"]').val((obj.attr("unitCd") == undefined) ? "" : obj.attr("unitCd"));
						$("#contentsForm").find('input[name="unitLvl"]').val(parseInt(obj.attr("unitLvl"),10)+1);
						$("#contentsForm").find('input[name="unitNm"]').val("New Unit Item");
						$("#contentsForm").find('input[name="unitFilePath"]').val("");
						$("#contentsForm").find('input[name="atchFilePath"]').val("");
						$("#contentsForm").find('input[name="cntsTypeCd"]').val("");
						$("#contentsForm").find('input[id="prgrChkTypeTIME"]').attr("checked", false);
						$("#contentsForm").find('input[id="prgrChkTypePAGE"]').attr("checked", false);
						$("#contentsForm").find('input[name="prgrChkYn"]').prop("checked", false);

						$.ajaxSetup({async: false});

						$('#contentsForm').attr("action","/mng/course/contents/addContents");
						$('#contentsForm').ajaxSubmit(function (resultDTO) {
							var item = resultDTO.returnVO;
							if(resultDTO.result >= 0) {
								$("#sbjCntsTree").jstree("refresh");
								$("#sbjCntsTree").jstree("open_node","#"+obj.attr("id"));
								$("#sbjCntsTree").jstree("select_node","#"+item.unitCd);
								cntsEditForm(item.unitCd);
							} else {

							}
						});
			            $.ajaxSetup({async: true});
					}

					/**
					 * 수정할 단원 정보를 가져온다.
					 */
					function cntsEditForm(unitCd) {
						$.getJSON( cUrl("/mng/course/contents/editFormContents"),	{
								sbjCd : ItemDTO.sbjCd,
								unitCd : unitCd
							}, function (resultDTO) {
								$("#contentsWriteForm").show();
								var item = resultDTO.returnVO;
								$("#contentsForm").find('input[name="sbjCd"]').val(ItemDTO.sbjCd);
								$("#contentsForm").find('input[name="unitCd"]').val(item.unitCd);
								$("#contentsForm").find('input[name="unitType"]').val(item.unitType);
								$("#contentsForm").find('input[name="parUnitCd"]').val(item.parUnitCd);
								$("#contentsForm").find('input[name="unitLvl"]').val(item.unitLvl);
								$("#contentsForm").find('input[name="unitOdr"]').val(item.unitOdr);
								$("#contentsForm").find('input[name="unitNm"]').val(item.unitNm);
								$("#contentsForm").find('input[name="cntsTypeCd"]').val(item.cntsTypeCd);
								$("#contentsForm").find('input[name="unitFilePath"]').val(item.unitFilePath);
								$("#contentsForm").find('input[name="atchFilePath"]').val(item.atchFilePath);
								$("#contentsForm").find('input[name="critTm"]').val(item.critTm);

								$("#contentsForm").find('input[id="prgrChkTypeTIME"]').prop("checked", false);
								$("#contentsForm").find('input[id="prgrChkTypePAGE"]').prop("checked", false);

								if(item.unitType == "C") {
									$("#contentsForm").find('input[id="prgrChkTypeTIME"]').prop("disabled", true);
									$("#contentsForm").find('input[id="prgrChkTypePAGE"]').prop("disabled", true);
									$("#contentsForm").find('input[name="critTm"]').prop("disabled",true);
									$("#contentsForm").find('input[name="prgrChkYn"]').prop("checked", false);
									$("#contentsForm").find('input[name="prgrChkYn"]').prop("disabled", true);
								} else {
									if(item.cntsTypeCd == "OLCCNTS"){
										$("#contentsForm").find('input[id="prgrChkTypeTIME"]').prop("disabled", true);
										$("#contentsForm").find('input[id="prgrChkTypePAGE"]').prop("disabled", false);
										$("#contentsForm").find('input[name="critTm"]').prop("disabled",true);
									} else if (item.cntsTypeCd == "MEDIA") {
										$("#contentsForm").find('input[id="prgrChkTypeTIME"]').prop("disabled", false);
										$("#contentsForm").find('input[id="prgrChkTypePAGE"]').prop("disabled", true);
										$("#contentsForm").find('input[name="critTm"]').prop("disabled",true);
									} else {
										$("#contentsForm").find('input[id="prgrChkTypeTIME"]').prop("disabled", false);
										$("#contentsForm").find('input[id="prgrChkTypePAGE"]').prop("disabled", false);
										$("#contentsForm").find('input[name="critTm"]').prop("disabled",false);
									}
									if(item.prgrChkType == 'TIME') {
										$("#contentsForm").find('input[id="prgrChkTypeTIME"]').prop("checked", true);
										$("#contentsForm").find('input[name="critTm"]').prop("disabled",false);
									} else if(item.prgrChkType == 'PAGE') {
										$("#contentsForm").find('input[id="prgrChkTypePAGE"]').prop("checked", true);
										$("#contentsForm").find('input[name="critTm"]').prop("disabled",true);
									}

									$("#contentsForm").find('input[name="prgrChkYn"]').prop("disabled", false);
									if(item.prgrChkYn == 'Y') {
										$("#contentsForm").find('input[name="prgrChkYn"]').prop("checked", true);
									} else {
										$("#contentsForm").find('input[name="prgrChkYn"]').prop("checked", false);
									}
								}
								// olc 콘텐츠의 경우 무조건 페이지 체크로 설정
							}
						);
					}

					function cntsRemove(obj) {
						closeContentsEditForm();
						$("#contentsForm").find('input[name="sbjCd"]').val(ItemDTO.sbjCd);
						$("#contentsForm").find('input[name="unitCd"]').val(obj.attr("unitCd"));
						$("#contentsForm").find('input[name="parUnitCd"]').val((obj.attr("unitCd") == undefined) ? "" : obj.attr("parUnitCd"));
						$("#contentsForm").find('input[name="unitOdr"]').val(obj.attr("unitOdr"));
						if(obj.attr("bookmarkCnt") > 0 ){
							alert("<spring:message code="course.message.contents.alert.delete"/>");
							return;
						}
						//-- 선택된 단원의 하위 단원을 추가 등록함.
						if(obj.attr("subCnt") > 0 ) {
							if(!confirm("<spring:message code="course.message.contents.confirm.delete.sub"/>")) {
								return;
							}
						}
						$('#contentsForm').attr("action","/mng/course/contents/deleteContents");
						$('#contentsForm').ajaxSubmit(function (resultDTO) {
							$("#sbjCntsTree").jstree("refresh");
						});
					}

				});

				function modalBoxClose() {
					modalBox.clear();
					modalBox.close();
				}

				function tabBox(str) {
					var ajaxUrl = cUrl("/mng/course/contents/leftWorkArea");
					var ajaxData;
					//-- Object 초기화
					fileTree	= null;
					if(str == '2') {
						ajaxData = {"workType":"othersbj"};
					} else if(str == '3') {
						ajaxData = {"workType":"olccnts"};
					} else if(str == '4') {
						ajaxData = {"workType":"mediacnts"};
					} else {
						ajaxData = {"workType":"file"};
					}
					$("#leftWorkArea").load(ajaxUrl, ajaxData);
				}

				function prgrChkType(str) {
					var f = document.contentsForm;
					if(str!="TIME")	f["critTm"].disabled = true;
					else f["critTm"].disabled = false;
				}

				function contentsEdit() {
					if(!validate(document.contentsForm)) return;
					$('#contentsForm').attr("action","/mng/course/contents/editContents");
					$('#contentsForm').ajaxSubmit(function (resultDTO) {
						var item = resultDTO.returnVO;
						if(resultDTO.result >= 0) {
							var parUnitCd = (item.parUnitCd == "") ? 'CNTSROOT' : item.parUnitCd;
							$('#sbjCntsTree').jstree('refresh',"#"+parUnitCd);
						}
					});
				}

				function closeContentsEditForm() {
					$("#contentsWriteForm").hide();
				}

				function contentsMove(str) {
					var f = document.contentsForm;
					f["sbjCd"].value = ItemDTO.sbjCd;
					f["unitCd"].value = ItemDTO.unitCd;
					f["parUnitCd"].value = (ItemDTO.parUnitCd == undefined) ? '':ItemDTO.parUnitCd;
					f["unitOdr"].value = ItemDTO.unitOdr;
					f["moveType"].value = str;

					process("moveContents");
				}


				function openAll(){
					$('#sbjCntsTree').jstree("open_all");
				}

				function closeAll(){
					$('#sbjCntsTree').jstree("close_all");
				}

				function pageprint(){
				    var printArea =  document.getElementById('sbjCntsTree').innerHTML;
					win = window.open();
					self.focus();
					win.document.open();

					win.document.write('<html><head><title></title><style>');
					win.document.write('body, td {font-falmily: Verdana; font-size: 10pt;}');
					win.document.write('</style></haed><body>');
					win.document.write(printArea);
			 		win.document.write('</body></html>');
					win.document.close();
					win.print();
					win.close();
				}

				//-- 콘텐츠 수정창의 입력창 텍스트 삭제
				function delPath(pathType) {
					var f = document.contentsForm;
					if(pathType == 'unitFilePath') {
						f["unitFilePath"].value = '';
					} else if(pathType == 'atchFilePath') {
						f["atchFilePath"].value = '';
					} else if(pathType == 'mobileFilePath2') {
						f["mobileFilePath2"].value = '';
					}
				}

				function cntsPreview(obj) {
					var cntsTypeCd = obj.attr("cntsTypeCd");
					if((cntsTypeCd == "OLCCNTS")) {
						var unitFilePath = obj.attr("unitFilePath");
						var url = generateUrl("/mng/library/clibShareCnts/previewOlcMain",{"cntsCd":unitFilePath});
						var winOption = "width=1100, height=770, top=10, left=10, scrollbars=0";
						var previewWin = window.open(url, "previewWin", winOption);
						previewWin.focus();
					} else if((cntsTypeCd == "MEDIA")) {
						var unitFilePath = obj.attr("unitFilePath");
						var url = generateUrl("/mng/library/clibShareCnts/previewMediaPop",{"cntsCd":unitFilePath});
						var winOption = "left=0,top=0,toolbar=no,location=no,directories=0,status=0,menubar=no,scrollbars=yes,resizable=no,width=800,height=480";
						var contentsWin = window.open(url, "contentsWin", winOption);
						contentsWin.focus();
					} else {
						var unitCd = obj.attr("uintCd");
						var url = generateUrl("/mng/course/contents/viewCntsMain",{"sbjCd":ItemDTO.sbjCd,"unitCd":unitCd});
						var winOption = "left=0,top=0,toolbar=0,location=0,directories=0,status=0,menubar=0,scrollbars=auto,resizable=yes,width=1100,height=700";
						var contentsWin = window.open(url, "contentsWin", winOption);
						contentsWin.focus();
					}
				}
			</script>
