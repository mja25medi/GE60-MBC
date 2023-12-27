<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="gubun" value="${gubun}"/>
<c:set var="olcCartrgVO" value="${vo}"/>
<mhtml:mng_html>
<mhtml:mng_head titleName="${MENUNAME}">
	<meditag:js src="/js/jquery/jquery.jstree.js"/>
	<mhtml:head_module fileupload="y"/>
</mhtml:mng_head>

<mhtml:frm_body>
	<form id="olcCartrgForm" name="olcCartrgForm" onsubmit="return false" >
	<input type="hidden" name="cartrgCd" value="${vo.cartrgCd }" />
	<input type="hidden" name="cartrgOdr" value="${vo.cartrgOdr }" />
	<input type="hidden" name="cntsCnt" id="cntsCnt" value="${vo.cntsCnt }"/>
	<input type="hidden" name="shareCnt" id="shareCnt" value="${vo.shareCnt }"/>
	<input type="hidden" name="useCnt" id="useCnt" value="${vo.useCnt }"/>
	<input type="hidden" name="thumbFileSn" value="${vo.thumbFileSn }"/>
	<input type="hidden" name="cntsShareCd" id="cntsShareCd" value="${vo.cntsShareCd }"/>
	<input type="hidden" name="knowShareCd" id="knowShareCd" value="${vo.knowShareCd }"/>
	<table summary='<spring:message code="course.title.category.manage"/>' class="table table-striped table-bordered">
		<colgroup>
			<col style="width:30%"/>
			<col style="width:70%"/>
		</colgroup>
		<tr>
			<th scope="row"><label for="ctgrNm"><spring:message code="olc.title.category"/></label></th>
			<td>
				<div class="input-group">
					<div class="input-group-btn btn-group">
						<button onclick="clickDropdown()" type="button" class="btn btn-default btn-sm dropdown-toggle" >
							<span class="caret"></span>
						</button>
						<ul class="dropdown-menu" role="menu" id="ctgrDrop">
							<li style="width:380px;">
								<div id="ctgrTreeArea" style="font-size:14px;line-height:18px;overflow:auto;width:100%;min-height:200px"></div>
							</li>
						</ul>
					</div>
					<input type="text" name="ctgrNm" id="ctgrNm" class="form-control input-sm" style="width:140px;background-color:#ffffff;" readonly="readonly" value="${olcCartrgVO.ctgrNm}"/>
					<input type="hidden" name="ctgrCd" id="ctgrCd" value="${vo.ctgrCd }"/>
				</div>
			</td>
		</tr>
		<tr>
			<th scope="row"><label for="cartrgNm"><spring:message code="olc.title.cartridge.name"/></label></th>
			<td>
				<input type="text" dispName="<spring:message code="olc.title.cartridge.name"/>" maxlength="50" isNull="N" lenCheck="50" name="cartrgNm" value="${vo.cartrgNm }" class="form-control input-sm" id="cartrgNm"/>
			</td>
		</tr>
		<tr>
			<th scope="row"><label for="cartrgTag"><spring:message code="common.title.tag"/></label></th>
			<td>
				<input type="text" dispName="<spring:message code="common.title.tag"/>" maxlength="500" isNull="Y" lenCheck="500" name="cartrgTag" value="${vo.cartrgTag }" class="form-control input-sm" id="cartrgTag"/>

			</td>
		</tr>
		<tr>
			<th scope="row"><label for="uploadifyThumb"><spring:message code="board.title.bbs.atcl.thumb"/></label></th>
			<td>
				<spring:message code="org.message.imginfo.size.main"  arguments="166|134" argumentSeparator="|"/></p>
				<div class="upload">
					<div class="upload_inbox">
						<a href="javascript:uploderclick('thumbuploader');" class="btn btn-primary btn-xs"><spring:message code="button.select.file"/></a>
						<input type="file" name="thumbuploader" id="thumbuploader" title="<spring:message code="board.title.bbs.atcl.thumb"/>" multiple style="display:none" accept="image/*" /><%-- 첨부파일 버튼 --%>
						<div id="thumbprogress" class="progress">
		    				<div class="progress-bar progress-bar-success"></div>
						</div>
					</div>
					<div id="thumbfiles" class="multi_inbox"></div>
				</div>
			</td>
		</tr>
		<tr>
			<th scope="row"><spring:message code="common.title.useyn"/></th>
			<td>
				<label style="font-weight: normal;"><input type="radio" style="border:0" name="useYn" value="Y" <c:if test="${vo.useYn eq 'Y'}">checked</c:if>/> <spring:message code="common.title.useyn_y"/></label>
				<label style="font-weight: normal; margin-left:10px;"><input type="radio" style="border:0" name="useYn" value="N" <c:if test="${vo.useYn ne 'Y'}">checked</c:if>/> <spring:message code="common.title.useyn_n"/></label>
			</td>
		</tr>
		<c:if test="${gubun eq 'E'}">
		<tr>
			<th scope="row"><spring:message code="olc.title.contents.share"/></th>
			<td>
				<label style="font-weight: normal;"><input type="checkbox" name="openYn" id="CntsShare" value="Y" onclick="checkShareYn('CntsShare')" <c:if test="${vo.openYn eq 'Y'}">checked</c:if>/> <spring:message code="common.title.share"/> </label>
				<div id="shareCtgrAreaC" style="display:none">
					<div class="input-group">
						<div class="input-group-btn btn-group dropup">
							<button onclick="clickDropdown2('C')" type="button" class="btn btn-default btn-sm dropdown-toggle" >
								<span class="caret"></span>
							</button>
							<ul class="dropdown-menu" role="menu" id="shareCtgrDropC">
								<li style="width:280px;">
									<div id="shareCtgrTreeAreaC" style="font-size:14px;line-height:18px;overflow:auto;width:100%;min-height:200px"></div>
								</li>
							</ul>
						</div>
						<input type="text" name="shareCtgrNmC" id="shareCtgrNmC" class="form-control input-sm" style="width:140px;" value=""/>
						<input type="hidden" name="shareCtgrCdC" id="shareCtgrCdC"/>
						<a href="javascript:addShareCtgr('C');" class="btn btn-primary btn-sm"><spring:message code="button.add"/></a>
					</div>
					<div id="crgrRelListC">

					</div>
				</div>
			</td>
		</tr>
		<tr>
			<th scope="row"><spring:message code="olc.title.knowledge.share"/></th>
			<td>
				<label style="font-weight: normal;"><input type="checkbox" name="knowOpenYn" id="KnowShare" value="Y" onclick="checkShareYn('KnowShare')" <c:if test="${vo.knowOpenYn eq 'Y'}">checked</c:if>/> <spring:message code="common.title.share"/> </label>
				<div id="shareCtgrAreaK" style="display:none">
					<div class="input-group">
						<div class="input-group-btn btn-group dropup">
							<button onclick="clickDropdown2('K')" type="button" class="btn btn-default btn-sm dropdown-toggle" >
								<span class="caret"></span>
							</button>
							<ul class="dropdown-menu" role="menu" id="shareCtgrDropK">
								<li style="width:280px;">
									<div id="shareCtgrTreeAreaK" style="font-size:14px;line-height:18px;overflow:auto;width:100%;min-height:200px"></div>
								</li>
							</ul>
						</div>
						<input type="text" name="shareCtgrNmK" id="shareCtgrNmK" class="form-control input-sm" style="width:140px;" value=""/>
						<input type="hidden" name="shareCtgrCdK" id="shareCtgrCdK"/>
						<a href="javascript:addShareCtgr('K');" class="btn btn-primary btn-sm"><spring:message code="button.add"/></a>
					</div>
					<div id="crgrRelListK">

					</div>
				</div>
			</td>
		</tr>
		</c:if>
	</table>

	<div class="text-right" style="margin-top:10px;">
		<c:if test="${gubun eq 'A'}">
			<button class="btn btn-primary btn-sm" onclick="addCartrg()" ><spring:message code="button.add"/></button>
		</c:if>
		<c:if test="${gubun eq 'E'}">
			<button class="btn btn-primary btn-sm" onclick="editCartrg()" ><spring:message code="button.add"/></button>
			<button class="btn btn-warning btn-sm" onclick="delCartrg()" ><spring:message code="button.delete"/></button>
		</c:if>
		<button class="btn btn-default btn-sm" onclick="parent.modalBoxClose()" ><spring:message code="button.close"/></button>
	</div>
	<input type="submit" value="submit" style="display:none" />
	</form>
<script type="text/javascript">
	var thumbFile;
	var olcCtgrTree = null;
	var shareCtgrTreeC = null;
	var shareCtgrTreeK = null;
	var ItemDTO = {"curPage":1};
	$(document).ready(function() {
		olcCtgrTree = $('#ctgrTreeArea').jstree({
			"plugins" : [ "themes", "json_data", "types", "ui", "wholerow", "state"],
			"json_data" : {
				"ajax" : {
					"url" : cUrl("/mng/olc/cartrg/listCtgrJsTree),
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
		<c:if test="${gubun eq 'E'}">
		//콘텐츠 공유
		shareCtgrTreeC = $('#shareCtgrTreeAreaC').jstree({
			"plugins" : [ "themes", "json_data", "types", "ui", "wholerow", "state"],
			"json_data" : {
				"ajax" : {
					"url" : cUrl("/mng/olc/share/listCtgrJsTree"),
					"data" : function (n) {
						return {
							"ctgrDivCd" : "C",
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
			setSelectNodeShare(data,'C');
		}).bind("loaded.jstree", function(e, data){
			$(this).jstree("open_all");
		});
		checkShareYn('CntsShare');
		listCartrg('C');

		//지식 공유
		shareCtgrTreeK = $('#shareCtgrTreeAreaK').jstree({
			"plugins" : [ "themes", "json_data", "types", "ui", "wholerow", "state"],
			"json_data" : {
				"ajax" : {
					"url" : cUrl("/mng/olc/share/listCtgrJsTree"),
					"data" : function (n) {
						return {
							"ctgrDivCd" : "K",
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
			setSelectNodeShare(data,'K');
		}).bind("loaded.jstree", function(e, data){
			$(this).jstree("open_all");
		});
		checkShareYn('KnowShare');
		listCartrg('K');
		</c:if>

		thumbFile = new $M.JqueryFileUpload({
			"varName"			: "thumbFile",
			"files" 			: $.parseJSON('${olcCartrgVO.thumbFileJson}'),
			"uploaderId"		: "thumbuploader",
			"fileListId"		: "thumbfiles",
			"progressId"		: "thumbprogress",
			"maxcount"			: 1,
			"previewImage"		: true,
			"uploadSetting"		: {
				'formData'		: { 'repository': 'OLC_LESN_CARTRG',
	                                'organization' : "${USER_ORGCD}",
									'type'		: 'thumb' }
			}
		});
	});
	function uploderclick(str) {
		$("#"+str).click();
	}
	function clickDropdown() {
		$("#ctgrDrop").toggle();
	}

	function setSelectNode(data) {
		var id = data.rslt.obj.attr("id");
		var name = data.rslt.obj.attr("title");
		//-- form에 적용
		$("#ctgrCd").val(id);
		$("#ctgrNm").val(name);
		$("#ctgrDrop").hide();
	}

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		if(!validate(document.olcCartrgForm)) return;
		var _thumbFile = thumbFile.getFileSnAll();
		$(':input:hidden[name=thumbFileSn]').val(_thumbFile);

		// 이부분을 처리 하지 않으면 multiPartFile 호출 됨.
		$('#uploadifyThumb').attr('disabled',true);
		$('#olcCartrgForm').attr("action", "/mng/olc/cartrg/" + cmd);
		$('#olcCartrgForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		alert(resultDTO.message);

		if(resultDTO.result >= 0) {
			// 정상 처리
			parent.listPageing(1);
			parent.modalBoxClose();
		} else {
			// 비정상 처리
		}
	}

	/**
	 * 과정 분류 등록
	 */
	function addCartrg() {
		var ctgrCd = $("#ctgrCd").val();
		if(ctgrCd == 'root' || ctgrCd == '') {
			alert('<spring:message code="olc.message.category.select"/>');
			return;
		}
		process("addCartrg");	// cmd
	}

	/**
	 * 과정 분류 수정
	 */
	function editCartrg() {
		var ctgrCd = $("#ctgrCd").val();

		if(eval("$('#CntsShare')").is(":checked")){
			if($("#shareListSize_C").val() =="0" ){
				alert("<spring:message code="olc.message.category.alert.sharecnt"/>");
				return;
			}
		}
		if(eval("$('#KnowShare')").is(":checked")){
			if($("#shareListSize_K").val() == "0" ){
				alert("<spring:message code="olc.message.category.alert.sharecnt"/>");
				return;
			}
		}

		if(ctgrCd == 'root' || ctgrCd == '') {
			alert('<spring:message code="olc.message.category.select"/>');
			return;
		}
/* 		$('#cntsShareCd').val($('#CntsShare').val());
		$('#knowShareCd').val($('#KnowShare').val()); */
		process("editCartrg");	// cmd
	}

	/**
	 * 과정 분류 삭제
	 */
	function delCartrg() {
		if($("#cntsCnt").val() > 0) {
			alert("<spring:message code="olc.message.cartredge.alert.cntscnt"/>");
			return;
		}
		if($("#shareCnt").val() > 0) {
			alert("<spring:message code="olc.message.cartredge.alert.sharecnt"/>");
			return;
		}
		if($("#useCnt").val() > 0) {
			alert("<spring:message code="olc.message.cartredge.alert.usecnt"/>");
			return;
		}
		if(confirm('<spring:message code="olc.message.cartredge.confirm.delete"/>')) {
			process("deleteCartrg");	// cmd
		} else {
			return;
		}
	}

	<c:if test="${gubun eq 'E'}">
	function clickDropdown2(lastId) {
		eval("$('#shareCtgrDrop"+lastId+"')").toggle();
	}

	function checkShareYn(lastId) {
		var isCheck = eval("$('#"+lastId+"')").is(":checked");

		if(lastId == 'CntsShare'){
			lastId = 'C';
		}else{
			lastId = 'K';
		}

		if(isCheck) {
			eval("$('#shareCtgrArea"+lastId+"')").show();
		}else{
			eval("$('#shareCtgrArea"+lastId+"')").hide();
		}
	}

	function setSelectNodeShare(data, lastId) {
		var id = data.rslt.obj.attr("id");
		var name = data.rslt.obj.attr("title");
		//-- form에 적용
		eval("$('#shareCtgrCd"+lastId+"')").val(id);
		eval("$('#shareCtgrNm"+lastId+"')").val(name);
		eval("$('#shareCtgrDrop"+lastId+"')").hide();
	}

	function addShareCtgr(lastId) {
		var ctgrCd = eval("$('#shareCtgrCd"+lastId+"')").val();

		$.post(
			cUrl("/mng/olc/share/addShare"),
			{
				"userNo" : "${olcCartrgVO.userNo}",
				"ctgrCd" : ctgrCd,
				"cartrgCd" : "${olcCartrgVO.cartrgCd}"
			},
			function(resultDTO) {
				alert(resultDTO.message);
				if(resultDTO.result >= 0) {
					// 정상 처리
					eval("$('#shareCtgrCd"+lastId+"')").val("");
					eval("$('#shareCtgrNm"+lastId+"')").val("");
					listCartrg(lastId);
				} else {
					// 비정상 처리
				}
			}
		);
	}

	function deleteShareCtgr(userNo, ctgrCd, lastId) {
		$.post(
			cUrl("/mng/olc/share/deleteShare"),
			{
				"userNo" : userNo,
				"ctgrCd" : ctgrCd,
				"cartrgCd" : "${olcCartrgVO.cartrgCd}"
			},
			function(resultDTO) {
				alert(resultDTO.message);
				if(resultDTO.result >= 0) {
					// 정상 처리
					listCartrg(lastId);
				} else {
					// 비정상 처리
				}
			}
		);
	}

	function listCartrg(lastId) {
		var url = cUrl("/mng/olc/share/listCtgrRel");
		eval("$('#crgrRelList"+lastId+"')").load(url, { "cartrgCd" : "${olcCartrgVO.cartrgCd}", "ctgrDivCd" : lastId });
	}
	</c:if>
</script>
</mhtml:frm_body>
</mhtml:mng_html>