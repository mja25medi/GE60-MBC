<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="olcCartrgList" value="${olcCartrgList}"/>
<c:set var="olcCartrgVO" value="${vo}"/>
<mhtml:home_html>
<mhtml:home_head titleName="${MENUNAME}">
	<mhtml:head_module paging="Y"/>
	<meditag:js src="/js/jquery/jquery.jstree.js"/>
	<meditag:js src="/js/modaldialog.js"/>
</mhtml:home_head>

<mhtml:home_body>
				<mhtml:home_location />
			<p><spring:message code="olc.title.home.main.info.message"/></p>
			<form id="olcCartrgForm" name="olcCartrgForm" onsubmit="return false" action="/home/olc/cartrg">
				<div class="col-md-6 col-sm-6">
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
						<input type="text" name="ctgrNm" id="ctgrNm" class="form-control input-sm" style="width:140px;background-color:#ffffff;" readonly="readonly" value="${vo.ctgrNm}"/>
						<input type="hidden" name="ctgrCd" id="ctgrCd" value="${vo.ctgrCd}"/>
					</div>
				</div>
				<div style="width:100%;margin-bottom:5px;" class="text-right">
					<a href="javascript:ctgrManage()" class="btn btn-primary btn-sm"><spring:message code="etc.title.relatedsite.ctgr.write"/></a>
					<a href="javascript:fileManage()" class="btn btn-primary btn-sm"><spring:message code="button.manage.file"/></a>
					<a href="javascript:olcWrite()" class="btn btn-primary btn-sm">OLC <spring:message code="button.write"/></a>
				</div>

				<div id="cartrgList">
					<table class="table table-striped table-bordered" style="font-size:12px;">
						<caption class="sr-only"><spring:message code="olc.title.main.manage"/></caption>
						<colgroup>
							<col style="width:50px"/>
							<col style="width:100px;"/>
							<col style="width:auto"/>
							<col style="width:75px"/>
							<col style="width:80px"/>
							<col style="width:100px"/>
							<col style="width:75px"/>
							<col style="width:75px"/>
							<col style="width:70px"/>
						</colgroup>
						<thead>
							<tr>
								<th scope="col"><spring:message code="common.title.no"/></th>
								<th scope="col"><spring:message code="olc.title.category.name"/></th>
								<th scope="col"><spring:message code="olc.title.cartridge.name"/></th>
								<th scope="col"><spring:message code="common.title.manage"/></th>
								<th scope="col"><spring:message code="olc.title.cartridge.contents.cnt"/></th>
								<th scope="col"><spring:message code="common.title.regdate"/></th>
								<th scope="col"><spring:message code="common.title.edit"/></th>
								<th scope="col"><spring:message code="org.title.orginfo.design"/></th>
								<th scope="col"><spring:message code="olc.title.share.application"/></th>
							</tr>
						</thead>
						<tbody>
							<c:set var="isDisabled" value="" />
							<c:forEach items="${olcCartrgList}" var="item" varStatus="status">
							<c:choose>
								<c:when test="${item.knowShareCd eq '03' || item.cntsShareCd eq '03'}"><c:set var="isDisabled" value="disabled" /></c:when>
								<c:otherwise><c:set var="isDisabled" value="" /></c:otherwise>
							</c:choose>
							<tr>
								<td class="text-right">${status.count}</td>
								<td class="wordbreak">${item.ctgrNm}</td>
								<td class="wordbreak">

									<c:choose>
										<c:when test="${item.knowShareCd eq '03' || item.cntsShareCd eq '03'}"><a href="javascript:return;')"  >${item.cartrgNm}</a></c:when>
										<c:otherwise><a href="javascript:cntsManage('${item.cartrgCd}')"   >${item.cartrgNm}</a></c:otherwise>
									</c:choose>

									<c:if test="${item.cntsCnt > 0 }">
									<a href="javascript:preview('${item.userNo}', '${item.cartrgCd}', '${item.cartrgNm}');"><i class="glyphicon glyphicon-eye-open"></i></a>
									</c:if>
								</td>
								<td class="text-center">
									<c:choose>
										<c:when test="${status.last}"><a href="#" class="btn btn-default btn-xs"><i class="fa fa-arrow-down"></i></a></c:when>
										<c:otherwise><a href="javascript:moveOLC('down','${item.cartrgCd}')" class="btn btn-primary btn-xs" ><i style="color: #fff" class="fa fa-arrow-down"></i></a></c:otherwise>
									</c:choose>
									<c:choose>
										<c:when test="${status.first}"><a href="#" class="btn btn-default btn-xs"><i class="fa fa-arrow-up"></i></a></c:when>
										<c:otherwise><a href="javascript:moveOLC('up','${item.cartrgCd}')" class="btn btn-primary btn-xs"><i style="color: #fff" class="fa fa-arrow-up"></i></a></c:otherwise>
									</c:choose>
								</td>
								<td class="text-right">${item.cntsCnt}</td>
								<td class="text-center"><meditag:dateformat type="1" delimeter="." property="${item.regDttm}"/></td>
								<td class="text-center"><a href="javascript:olcEdit('${item.cartrgCd}')" class="btn btn-info btn-sm" ${isDisabled }><spring:message code="button.edit"/></a></td>
								<td class="text-center"><a href="javascript:olcEditDesign('${item.cartrgCd}')" class="btn btn-info btn-sm" ${isDisabled }><spring:message code="button.edit"/></a></td>
								<td class="text-center">
									<a href="javascript:olcAppl('${item.cartrgCd}')" class="btn btn-info btn-sm"><spring:message code="log.title.org.student.enroll"/></a>
								</td>
							</tr>
							</c:forEach>
							<c:if test="${empty olcCartrgList}">
							<tr>
								<td colspan="10"><spring:message code="common.message.nodata"/></td>
							</tr>
							</c:if>
						</tbody>
					</table>
				</div>
			</form>

<script type="text/javascript">
var olcCtgrTree = null;
var modalBox = null;
var ItemDTO = { "nodeId" : "", "subCnt" : 0, "olcCnt" : 0, "nodeType" : "root"};

$(document).ready(function() {

	modalBox = new $M.ModalDialog({
		"modalid" : "modal1"
	});

	olcCtgrTree = $('#ctgrTreeArea').jstree({
		"plugins" : [ "themes", "json_data", "types", "ui", "wholerow", "state"],
		"json_data" : {
			"ajax" : {
				"url" : cUrl("/home/olc/cartrg/listCtgrJsTree"),
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
});

function clickDropdown() {
	$("#ctgrDrop").toggle();
}

function setSelectNode(data) {
	var id = data.rslt.obj.attr("id");
	var name = data.rslt.obj.attr("title");
	if("root" == id){
		name = "";
	}
	//-- form에 적용
	//$("#ctgrCd").val(id);
	$("#ctgrNm").val(name);
	$("#ctgrDrop").hide();
	location.href="/home/olc/cartrg/listCartrg?ctgrCd="+id+"&amp;ctgrNm="+name;
}

function modalBoxClose() {
	modalBox.clear();
	modalBox.close();
}
/**
 * OLC 분류 등록 폼
 */
function ctgrManage() {
	var addContent  = "<iframe id='addCCtgrFrame' name='addCtgrFrame' width='100%' height='100%' "
		+ "frameborder='0' scrolling='auto' src='<c:url value="/home/olc/cartrg/manageCtrg"/>"
		+ "'/>";
	$(".modal-title").css("color","#000000");
	modalBox.clear();
	modalBox.addContents(addContent);
	modalBox.resize(600, 400);
	modalBox.setTitle("<spring:message code="olc.title.cartridge.write"/>");
	modalBox.show();
}

/**
 * OLC 등록 폼
 */
function olcWrite() {
	var addContent  = "<iframe id='addCCtgrFrame' name='addCtgrFrame' width='100%' height='100%' "
		+ "frameborder='0' scrolling='auto' src='<c:url value="/home/olc/cartrg/addCartrgForm"/>"
		+ "'/>";
	$(".modal-title").css("color","#000000");
	modalBox.clear();
	modalBox.addContents(addContent);
	modalBox.resize(600, 400);
	modalBox.setTitle("<spring:message code="olc.title.cartridge.write"/>");
	modalBox.show();
}

/**
 * OLC 수정 폼
 */
function olcEdit(cartrgCd) {
	var addContent  = "<iframe id='addCCtgrFrame' name='addCtgrFrame' width='100%' height='100%' "
		+ "frameborder='0' scrolling='auto' src='<c:url value="/home/olc/cartrg/editCartrgForm"/>"
		+ "?cartrgCd="+cartrgCd+"'/>";
	$(".modal-title").css("color","#000000");
	modalBox.clear();
	modalBox.addContents(addContent);
	modalBox.resize(600, 400);
	modalBox.setTitle("<spring:message code="olc.title.cartridge.edit"/>");
	modalBox.show();
}

function olcEditDesign(cartrgCd) {
	var addContent  = "<iframe id='addCCtgrFrame' name='addCtgrFrame' width='100%' height='100%' "
		+ "frameborder='0' scrolling='auto' src='<c:url value="/home/olc/cartrg/editCartrgDesignForm"/>"
		+ "?cartrgCd="+cartrgCd+"'/>";
	$(".modal-title").css("color","#000000");
	modalBox.clear();
	modalBox.addContents(addContent);
	modalBox.resize(800, 660);
	modalBox.setTitle("<spring:message code="olc.title.cartridge.design.edit"/>");
	modalBox.show();
}

function changeColor(color){
	$(".modal-title").css("color",color);
}

function moveOLC(type, cartrgCd) {
	var cmdStr = "moveUpOLC";
	if(type == "down" ) cmdStr = "moveDownOLC";
	$.getJSON(cUrl("/home/olc/cartrg/" + cmdStr), 	// url
			{ 
			  //"olcCartrgVO.ctgrCd" : ItemDTO.nodeId,
			  "cartrgCd" : cartrgCd
			}, function(data) { location.href="/home/olc/cartrg/listCartrg"; }					// params
		);
}

/**
 * Contents 관리 폼
 */
function cntsManage(cartrgCd) {
	var addContent  = "<iframe id='addCntsFrame' name='addCntsFrame' width='100%' height='100%' "
		+ "frameborder='0' scrolling='no' src='<c:url value="/home/olc/cartrg/cntsMain"/>"
		+ "?olcCntsVO.cartrgCd="+cartrgCd+"'/>";
	$(".modal-title").css("color","#000000");
	modalBox.clear();
	modalBox.addContents(addContent);
	modalBox.resize(1100, 700);
	modalBox.setTitle("<spring:message code="olc.title.contents.manage"/>");
	modalBox.show();
}

/**
 * Contents 관리 폼
 */
function fileManage() {
	var addContent  = "<iframe id='manageFileFrame' name='manageFileFrame' width='100%' height='100%' "
		+ "frameborder='0' scrolling='no' src='<c:url value="/home/olc/userFile/main"/>"
		+ "'/>";
	$(".modal-title").css("color","#000000");
	modalBox.clear();
	modalBox.addContents(addContent);
	modalBox.resize(800, 600);
	modalBox.setTitle("<spring:message code="button.manage.file"/>");
	modalBox.show();
}

function preview(userNo, cartrgCd, cartrgNm) {
	var url = cUrl("/home/olc/cartrg/previewMain")+"?olcCntsVO.userNo="+userNo+"${AMPERSAND}olcCntsVO.cartrgCd="+cartrgCd;
	/*
	var addContent  = "<iframe id='addCCtgrFrame' name='addCtgrFrame' width='100%' height='100%' "
		+ "frameborder='0' scrolling='auto' src='<c:url value="/OlcCartrgHome.do"/>"
		+ "?cmd=previewMain&amp;olcCntsVO.userNo="+userNo+"&amp;olcCntsVO.cartrgCd="+cartrgCd+"'/>";
	$(".modal-title").css("color","#000000");
	modalBox.clear();
	modalBox.addContents(addContent);
	modalBox.resize(1100, 650);
	modalBox.setTitle(cartrgNm);
	modalBox.show();
	*/
	var winOption = "left=0,top=0,toolbar=no,location=no,directories=0,status=0,menubar=no,scrollbars=yes,resizable=no,width=1100,height=670";
	var contentsWin = window.open(url, "contentsWin", winOption);
	contentsWin.focus();
}

function cntsShareCdChange(cartrgCd, chkBox){
	var cntsShareCd = "";
	if(chkBox.checked ){
		cntsShareCd = "01";
	} else {
		cntsShareCd = "02";
	}

	$.getJSON(cUrl("/home/olc/cartrg/cntsShareCdChange"), 	// url
		{ 
		  //"olcCartrgVO.ctgrCd" : ItemDTO.nodeId,
		  "cartrgCd" : cartrgCd,
		  "cntsShareCd" : cntsShareCd
		}, function(data) { location.href="/home/olc/cartrg/listCartrg"; }					// params
	);
}

function knowShareCdChange(cartrgCd, chkBox){
	var knowShareCd = "";
	if(chkBox.checked ){
		knowShareCd = "01";
	} else {
		knowShareCd = "02";
	}
	$.getJSON(cUrl("/home/olc/cartrg/knowShareCdChange"), 	// url
			{ 
			  //"olcCartrgVO.ctgrCd" : ItemDTO.nodeId,
			  "cartrgCd" : cartrgCd,
			  "knowShareCd" : knowShareCd
			}, function(data) { location.href="/home/olc/cartrg/listCartrg"; }					// params
		);
}

function olcAppl(cartrgCd) {
	var addContent  = "<iframe id='addCntsFrame' name='addCntsFrame' width='100%' height='100%' "
		+ "frameborder='0' scrolling='no' src='<c:url value="/home/olc/cartrg/viewAppl"/>"
		+ "?cartrgCd="+cartrgCd+"'/>";
	$(".modal-title").css("color","#000000");
	modalBox.clear();
	modalBox.addContents(addContent);
	modalBox.resize(400, 300);
	modalBox.setTitle("<spring:message code="olc.title.contents.manage"/>");
	modalBox.show();
}

function changeColor(color){
	$(".modal-title").css("color",color);
}
</script>
</mhtml:home_body>
</mhtml:home_html>
