<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="gubun" value="${gubun}"/>
<c:set var="olcCartrgVO" value="${vo}"/>
<mhtml:mng_html>
<mhtml:mng_head titleName="${MENUNAME}">
	<meditag:js src="/js/jquery/jquery.jstree.js"/>
</mhtml:mng_head>

<mhtml:frm_body>
	<form id="olcCartrgForm" name="olcCartrgForm" onsubmit="return false">
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
			<th scope="row"><label for="cartrgNm"><spring:message code="olc.title.category"/></label></th>
			<td>
				${olcCartrgVO.ctgrNm}
			</td>
		</tr>
		<tr>
			<th scope="row"><label for="cartrgNm"><spring:message code="olc.title.cartridge.name"/></label></th>
			<td>
				${olcCartrgVO.cartrgNm}
			</td>
		</tr>
		<tr>
			<th scope="row"><label for="cartrgTag"><spring:message code="common.title.tag"/></label></th>
			<td>
				${olcCartrgVO.cartrgTag}
			</td>
		</tr>
		<tr>
			<th scope="row"><label for="cartrgTag"><spring:message code="common.title.reguser"/></label></th>
			<td>
				${olcCartrgVO.regNm}
			</td>
		</tr>
	<c:if test="${gubun eq 'E'}">
		<c:if test="${olcCartrgVO.cntsShareCd ne null }">
		<tr>
			<th scope="row"><spring:message code="olc.title.contents.share"/></th>
			<td>
				<c:choose>
					<c:when test="${olcCartrgVO.cntsShareCd eq '02' }">
						<c:forEach var="item" items="${shareCdList }">
							<c:if test="${olcCartrgVO.cntsShareCd eq item.codeCd }"><label style="font-weight: normal;">${item.codeNm}</label></c:if>
						</c:forEach>
						<input type="hidden" name="CntsShare" id="CntsShare" value="${olcCartrgVO.cntsShareCd }" />
					</c:when>
					<c:otherwise>
						<div style="padding-bottom:3px">
						<select name="CntsShare" id="CntsShare" onChange="selectShare('CntsShare')" style="width:180px;margin-top:-4px;" class="form-control input-sm">
							<c:forEach var="item" items="${shareCdList }">
								<option value="${item.codeCd}" <c:if test="${olcCartrgVO.cntsShareCd eq item.codeCd }"> selected</c:if>>${item.codeNm}</option>
							</c:forEach>
						</select>
						</div>
					</c:otherwise>
				</c:choose>
				<!-- <label style="font-weight: normal;">&nbsp;</label> -->
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
		</c:if>
		<c:if test="${olcCartrgVO.knowShareCd ne null }">
		<tr>
			<th scope="row"><spring:message code="olc.title.knowledge.share"/></th>
			<td>
				<c:choose>
					<c:when test="${olcCartrgVO.knowShareCd eq '02' }">
						<c:forEach var="item" items="${shareCdList }">
							<c:if test="${olcCartrgVO.knowShareCd eq item.codeCd }"><label style="font-weight: normal;">${item.codeNm}</label></c:if>
						</c:forEach>
						<input type="hidden" name="KnowShare" id="KnowShare" value="${olcCartrgVO.knowShareCd }" />
					</c:when>
					<c:otherwise>
						<div class="text-right" style="padding-bottom:3px">
						<select name="KnowShare" id="KnowShare" onChange="selectShare('KnowShare')" style="width:180px;margin-top:-4px;" class="form-control input-sm">
							<c:forEach var="item" items="${shareCdList }">
								<option value="${item.codeCd}" <c:if test="${olcCartrgVO.knowShareCd eq item.codeCd }"> selected</c:if>>${item.codeNm}</option>
							</c:forEach>
						</select>
						</div>
					</c:otherwise>
				</c:choose>
				<!-- <label style="font-weight: normal;">&nbsp;</label> -->
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
	</c:if>
	</table>

	<div class="text-right" style="margin-top:10px;">
		<c:if test="${gubun eq 'E'}">
			<button class="btn btn-primary btn-sm" onclick="editCartrg()" ><spring:message code="button.add"/></button>
			<%-- <button class="btn btn-warning btn-sm" onclick="delCartrg()" ><spring:message code="button.delete"/></button> --%>
		</c:if>
		<button class="btn btn-default btn-sm" onclick="parent.modalBoxClose()" ><spring:message code="button.close"/></button>
	</div>
	<input type="submit" value="submit" style="display:none" />
	</form>
<script type="text/javascript">
	var shareCtgrTreeC = null;
	var shareCtgrTreeK = null;
	var ItemDTO = {"curPage":1};
	$(document).ready(function() {
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
		selectShare('CntsShare');
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
		selectShare('KnowShare');
		listCartrg('K');
		</c:if>
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
		$('#olcCartrgForm').attr("action","/mng/olc/cartrg/" +cmd);
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
	 * 과정 분류 수정
	 */
	function editCartrg() {
		if($("#CntsShare").val() =="03" && $("#shareListSize_C").val() =="0" ){
			alert("<spring:message code="olc.message.category.alert.sharecnt"/>");
			return;
		}
		if($("#KnowShare").val() == "03" && $("#shareListSize_K").val() == "0" ){
			alert("<spring:message code="olc.message.category.alert.sharecnt"/>");
			return;
		}

		$('#cntsShareCd').val($('#CntsShare').val());
		$('#knowShareCd').val($('#KnowShare').val());
		process("editCartrgShare");	// cmd
	}


	<c:if test="${gubun eq 'E'}">
	function clickDropdown2(lastId) {
		eval("$('#shareCtgrDrop"+lastId+"')").toggle();
	}

	function selectShare(lastId) {
		var str = eval("$('#"+lastId+"')").val();

		if(lastId == 'CntsShare'){
			lastId = 'C';
		}else{
			lastId = 'K';
		}
		if(str == '03'){
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
		if(ctgrCd == ''){
			alert("<spring:message code="olc.message.category.alert.sharecnt"/>");
			eval("$('#shareCtgrNm"+lastId+"')").focus();
			return;
		}
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