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
	<form id="olcCartrgForm" name="olcCartrgForm" onsubmit="return false" >
	<input type="hidden" name ="cartrgCd" value="${vo.cartrgCd }" />
	<input type="hidden" name ="cartrgOdr" value="${vo.cartrgOdr }" />
	<input type="hidden" name ="cntsCnt" value="${vo.cntsCnt }" />
	<input type="hidden" name ="userNo" value="${vo.userNo }" />
	<input type="hidden" name ="ctgrCd" id="ctgrCd" value="${vo.ctgrCd }"/>
	<input type="hidden" name ="cartrgNm" value="${vo.cartrgNm }"/>
	<input type="hidden" name ="cartrgTag" value="${vo.cartrgTag }"/>
	<input type="hidden" name ="useYn" value="${vo.useYn }"/>
	<input type="hidden" name ="cntsCnt" id="cntsCnt" value="${vo.cntsCnt }"/>
	<input type="hidden" name ="shareCnt" id="shareCnt" value="${vo.shareCnt }"/>
	<input type="hidden" name ="useCnt" id="useCnt" value="${vo.useCnt }"/>
	<table summary='<spring:message code="course.title.category.manage"/>' class="table table-striped table-bordered">
		<colgroup>
			<col style="width:30%"/>
			<col style="width:70%"/>
		</colgroup>
		<tr>
			<th scope="row"><label for="ctgrNm"><spring:message code="olc.title.category"/></label></th>
			<td>${olcCartrgVO.ctgrNm}</td>
		</tr>
		<tr>
			<th scope="row"><label for="cartrgNm"><spring:message code="olc.title.cartridge.name"/></label></th>
			<td>${olcCartrgVO.cartrgNm}</td>
		</tr>
		<tr>
			<th scope="row"><label for="cartrgTag"><spring:message code="common.title.tag"/></label></th>
			<td>${olcCartrgVO.cartrgTag}</td>
		</tr>
		<tr>
			<th scope="row"><spring:message code="common.title.useyn"/></th>
			<td>${olcCartrgVO.useYn}</td>
		</tr>
		<c:if test="${gubun eq 'E'}">
		<tr>
			<th scope="row"><spring:message code="common.title.shareyn"/></th>
			<td>
				<c:if test="${olcCartrgVO.ctgrDivCd eq 'C'}">
				<label style="font-weight: normal;"><input type = "checkbox" name ="openYn" id="openYn" value="Y" onclick="checkShareYn()" <c:if test="${vo.openYn eq 'Y'}">checked</c:if>/> <spring:message code="common.title.share"/> </label>
				</c:if>
				<c:if test="${olcCartrgVO.ctgrDivCd eq 'K'}">
				<label style="font-weight: normal;"><input type = "checkbox" name ="knowOpenYn" id="openYn" value="Y" onclick="checkShareYn()" <c:if test="${vo.knowOpenYn eq 'Y'}">checked</c:if> /> <spring:message code="common.title.share"/> </label>
				</c:if>
				<div id="shareCtgrArea" style="display:none">
					<div class="input-group">
						<div class="input-group-btn btn-group ">
							<button onclick="clickDropdown2()" type="button" class="btn btn-default btn-sm dropdown-toggle" >
								<span class="caret"></span>
							</button>
							<ul class="dropdown-menu" role="menu" id="shareCtgrDrop" style="top: 28px;">
								<li style="width:280px;">
									<div id="shareCtgrTreeArea" style="font-size:14px;line-height:18px;overflow:auto;width:100%;min-height:200px"></div>
								</li>
							</ul>
						</div>
						<input type="text" name="shareCtgrNm" id="shareCtgrNm" class="form-control input-sm" style="width:140px;" value=""/>
						<input type="hidden" name="shareCtgrCd" id="shareCtgrCd"/>
						<a href="javascript:addShareCtgr();" class="btn btn-primary btn-sm"><spring:message code="button.add"/></a>
					</div>
					<div id="crgrRelList">

					</div>
				</div>
			</td>
		</tr>
		</c:if>
	</table>

	<div class="text-right" style="margin-top:10px;">
		<c:if test="${gubun eq 'E'}">
			<button class="btn btn-primary btn-sm" onclick="editCartrg()" ><spring:message code="button.add"/></button>
			<button class="btn btn-warning btn-sm" onclick="delCartrg()" ><spring:message code="button.delete"/></button>
		</c:if>
		<button class="btn btn-default btn-sm" onclick="parent.modalBoxClose()" ><spring:message code="button.close"/></button>
	</div>
	<input type="submit" value="submit" style="display:none" />
	</form>
<script type="text/javascript">
	var shareCtgrTree = null;
	var ItemDTO = {"curPage":1, "ctgrDivCd":"${olcCartrgVO.ctgrDivCd}"};
	var ctgrDivCd = '${olcCartrgVO.ctgrDivCd}';
	var cntsShareCd = '${olcCartrgVO.cntsShareCd}';
	var knowShareCd = '${olcCartrgVO.knowShareCd}';
	$(document).ready(function() {
		<c:if test="${gubun eq 'E'}">
/* 		if(ctgrDivCd == 'K' && knowShareCd == '03'){
			$("input:checkbox[id='openYn']").attr("checked",true);
		}
		if(ctgrDivCd == 'C' && cntsShareCd == '03'){
			$("input:checkbox[id='openYn']").attr("checked",true);
		} */

		shareCtgrTree = $('#shareCtgrTreeArea').jstree({
			"plugins" : [ "themes", "json_data", "types", "ui", "wholerow", "state"],
			"json_data" : {
				"ajax" : {
					"url" : cUrl("/mng/olc/share/listCtgrJsTree"),
					"data" : function (n) {
						return {
							"ctgrDivCd" : ItemDTO.ctgrDivCd,
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
			setSelectNodeShare(data);
		}).bind("loaded.jstree", function(e, data){
			$(this).jstree("open_all");
		});
		checkShareYn();
		listCartrg();
		</c:if>

	});

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		if(!validate(document.olcCartrgForm)) return;

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
	 * 과정 분류 수정
	 */
	function editCartrg() {
		process("editCartrgCtgr");	// cmd
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
	function clickDropdown2() {
		$("#shareCtgrDrop").toggle();
	}

	function checkShareYn() {
		var share = "N";
		$("#shareCtgrArea").hide();
		if($("#openYn").is(":checked")) {
			share = "Y";
			$("#shareCtgrArea").show();
		}
	}

	function setSelectNodeShare(data) {
		var id = data.rslt.obj.attr("id");
		var name = data.rslt.obj.attr("title");
		//-- form에 적용
		$("#shareCtgrCd").val(id);
		$("#shareCtgrNm").val(name);
		$("#shareCtgrDrop").hide();
	}

	function addShareCtgr() {
		var ctgrCd = $("#shareCtgrCd").val();
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
					$("#shareCtgrCd").val("");
					$("#shareCtgrNm").val("");
					listCartrg();
					parent.listPageing();
				} else {
					// 비정상 처리
				}
			}
		);
	}

	function deleteShareCtgr(userNo, ctgrCd) {
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
					listCartrg();
					parent.listPageing();
				} else {
					// 비정상 처리
				}
			}
		);
	}

	function listCartrg() {
		var ctgrDivCd = ItemDTO.ctgrDivCd;

		var url = cUrl("/mng/olc/share/");
		$("#crgrRelList").load(url, { "cmd" : "listCtgrRel", "cartrgCd" : "${olcCartrgVO.cartrgCd}", "userNo" : "${olcCartrgVO.userNo}", "ctgrDivCd" : ctgrDivCd });
	}
	</c:if>
</script>
</mhtml:frm_body>
</mhtml:mng_html>