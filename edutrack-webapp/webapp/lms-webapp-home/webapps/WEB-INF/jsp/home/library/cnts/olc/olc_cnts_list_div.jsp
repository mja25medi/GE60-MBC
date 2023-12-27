<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="clibOlcCntsList" value="${clibOlcCntsList}"/>
<c:set var="pageInfo" value="${pageInfo}"/>
<c:set var="clibOlcCntsVO" value="${vo}"/>
<c:set var="curPage" value="${vo.curPage}"/>

<mhtml:home_html>
<mhtml:home_head titleName="${MENUNAME}">
	<mhtml:head_module paging="Y" fileupload="y" colorpicker="y"/>
	<meditag:js src="/js/modaldialog.js"/>
	<meditag:js src="/js/jquery/jquery.cookie.js"/>
	<meditag:js src="/js/jquery/jquery.hotkeys.js"/>
	<meditag:js src="/js/jquery/jquery.jstree.js"/>
</mhtml:home_head>

<mhtml:home_body>
			<mhtml:home_location />
			<%-- <p><spring:message code="olc.title.home.main.info.message"/></p> --%>

			<div id="displayArea" style="margin-top: 10px;">
				<div class="col-md-8 col-sm-8">
					<div class="input-group">
						<div class="input-group-btn btn-group" style="margin-right: 5px;">
							<button onclick="clickDropdown()" type="button" class="btn btn-default btn-sm dropdown-toggle" >
								<span class="caret" style="margin-top: 6px; margin-bottom: 5px;"></span>
							</button>
							<ul class="dropdown-menu" role="menu" id="ctgrDrop">
								<li style="width:380px;">
									<div id="ctgrTreeArea" style="font-size:14px;line-height:18px;overflow:auto;width:100%;min-height:200px"></div>
								</li>
							</ul>
						</div>
						<input type="hidden" name="ctgrCdList" id="ctgrCdList" class="form-control input-sm"  value="${vo.ctgrCd}"/>
						<input type="text" name="ctgrNmList" id="ctgrNmList" class="form-control input-sm" style="width:140px;background-color:#ffffff;margin-right: 5px;" readonly="readonly" value=""/>
						<select name="searchKey" id="searchKey" class="form-control input-sm" style="width: 80px;margin-right: 5px;">
								<option value="ALL"><spring:message code="common.title.all"/></option>
								<option value="cntsNm" <c:if test="${vo.searchKey eq 'cntsNm' }">selected</c:if>><spring:message code="common.title.title"/></option>
								<option value="cntsTag" <c:if test="${vo.searchKey eq 'cntsTag' }">selected</c:if>><spring:message code="common.title.tag"/></option>
								<option value="regUser" <c:if test="${vo.searchKey eq 'regUser' }">selected</c:if>><spring:message code="common.title.writer.name"/></option>
							</select>
							<div class="input-group" style="width:180px;">
								<input type="text" name="searchValue" id="searchValue" class="_enterBind form-control input-sm" value="${vo.searchValue }">
								<span class="input-group-addon" onclick="listPageing(1)" style="cursor:pointer">
									<i class="fa fa-search"></i>
								</span>
							</div>
					</div>
				</div>
				<div style="float:right">
					<select name="listScale" id="listScale" onchange="listPageing('1')" class="form-control input-sm">
						<option value="10" <c:if test="${clibOlcCntsForm.listScale eq '10' }">selected</c:if>>10</option>
						<option value="20" <c:if test="${clibOlcCntsForm.listScale eq '20' }">selected</c:if>>20</option>
						<option value="40" <c:if test="${clibOlcCntsForm.listScale eq '40' }">selected</c:if>>40</option>
						<option value="60" <c:if test="${clibOlcCntsForm.listScale eq '60' }">selected</c:if>>60</option>
						<option value="80" <c:if test="${clibOlcCntsForm.listScale eq '80' }">selected</c:if>>80</option>
						<option value="100" <c:if test="${clibOlcCntsForm.listScale eq '100' }">selected</c:if>>100</option>
						<option value="200" <c:if test="${clibOlcCntsForm.listScale eq '200' }">selected</c:if>>200</option>
					</select>
				</div>
				<div style="float:right">
					<a href="javascript:ctgrManage()" class="btn btn-primary btn-sm"><spring:message code="etc.title.relatedsite.ctgr.write"/></a>
					<%-- <a href="javascript:fileManage()" class="btn btn-primary btn-sm"><spring:message code="button.manage.file"/></a> --%>
					<a href="javascript:cntsWrite()" class="btn btn-primary btn-sm">OLC <spring:message code="button.write"/></a>
				</div>

				<div class="panel-body"  id="cntsList" style="clear: both;">
					<ul class="list-group">
					<c:forEach var="item" items="${clibOlcCntsList}">
						<li class="list-group-item wordbreak">
							<c:if test="${not empty item.thumbFileSn && item.thumbFileSn > 0}">
							<div style="width:140px;float:left;">
								<img src="<c:url value="/app/file/thumb/${item.thumbFileSn}"/>" style="width: 130px;" alt="${item.cntsNm}"/>
							</div>
							</c:if>
							<c:if test="${item.useYn eq 'Y'}"><i class="glyphicon glyphicon-ok-circle" style="color:skyblue"></i></c:if>
    						<c:if test="${item.useYn ne 'Y'}"><i class="glyphicon glyphicon-ban-circle" style="color:orange"></i></c:if>
    						${item.cntsNm}
							<a href="javascript:cntsEdit('${item.cntsCd}')" >
								<%-- <spring:message code="button.edit"/> --%>
								<i class="fa fa-cog"></i>
							</a>
							<span class="pull-right" style="text-align: center;">
								<a href="javascript:pageManage('${item.cntsCd}','${item.cntsNm}')" class="btn btn-xs btn-default"><spring:message code="library.title.contents.olc.page.manage"/></a>
								<c:if test="${item.cntsCnt > 0}">
								<a href="javascript:preview('${item.userNo}', '${item.cntsCd}', '${item.cntsNm} }')" class="btn btn-xs btn-default"><spring:message code="button.preview"/></a>
								<a href="javascript:contentsShare('${item.cntsCd}')" class="btn btn-xs btn-default"><spring:message code="common.title.share"/></a>
								</c:if>
							</span>
							<div class="small">
								<spring:message code="common.title.regdate"/> : <meditag:dateformat type="1" delimeter="." property="${item.regDttm}"/>
								| <spring:message code="common.title.tag"/> : ${item.cntsTag }
							</div>
							<div style="clear:both"></div>
						</li>
					</c:forEach>
					<c:if test="${empty clibOlcCntsList}">
						<li class="list-group-item"><spring:message code="common.message.nodata"/></li>
					</c:if>
					</ul>
				</div>
				<div class="row" style="margin-bottom:20px;">
					<div class="col-lg-12">
						<meditag:paging pageInfo="${pageInfo}" funcName="listPageing"/>
					</div>
				</div>

			</div>
			<div id="workArea" style="dispaly:none" class="col-md-12">

			</div>


<script type="text/javascript">
var olcCtgrTree = null;
var modalBox = null;
var ItemDTO = { "nodeId" : "", "subCnt" : 0, "olcCnt" : 0, "nodeType" : "root","nodeName" : "","page":""};

$(document).ready(function() {

	modalBox = new $M.ModalDialog({
		"modalid" : "modal1"
	});

	olcCtgrTree = $('#ctgrTreeArea').jstree({
		"plugins" : [ "themes", "json_data", "types", "ui", "wholerow", "state"],
		"json_data" : {
			"ajax" : {
				"url" : cUrl("/home/library/clibCntsCtgr/listCtgrJsTree"),
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

	<c:if test="${empty vo.ctgrNm }">$("#ctgrNmList").val("<spring:message code="library.title.category.all"/>");</c:if>
	<c:if test="${not empty vo.ctgrNm }">$("#ctgrNmList").val("${vo.ctgrNm }");</c:if>
});

function clickDropdown() {
	$("#ctgrDrop").toggle();
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
	$("#ctgrCdList").val(id);
	$("#ctgrNmList").val(name);
	$("#ctgrDrop").hide();
	listPageing(1);
}

/**
 * 콘텐츠 목록을 조회한다.
 */
function listPageing(page) {
	var id = $("#ctgrCdList").val();
	var name = $("#ctgrNmList").val();
	var searchKey = $("#searchKey option:selected").val();
	var searchValue = $('#searchValue').val();
	ItemDTO.page = page;
	if("root" == id){
		name = "";
	}
	$("#ctgrNmList").val(name);
	$("#ctgrDrop").hide();
	var listScale = $("#listScale option:selected").val();

	location.href="/home/library/clibOlcCnts/listCnts"
			+"?ctgrCd="+id+"${AMPERSAND}"
			+"ctgrNm="+name+"${AMPERSAND}"
			+"searchKey="+searchKey+"${AMPERSAND}"
			+"searchValue="+searchValue+"${AMPERSAND}"
			+"curPage="+page+"${AMPERSAND}"
			+"listScale="+listScale;

}



function modalBoxClose() {
	modalBox.clear();
	modalBox.close();
}

/**
 * 콘텐츠 등록 폼
 */
function cntsWrite() {
	$("#workArea").empty();
	$("#workArea").load(cUrl("/home/library/clibOlcCnts/addForm"),{"ctgrCd":ItemDTO.nodeId,"curPage":"${curPage}"});
	$("#displayArea").slideUp('slow');
	$("#workArea").show();
}

/**
 * 콘텐츠 수정 폼
 */
function cntsEdit(cntsCd) {
	$("#workArea").empty();
	$("#workArea").load(cUrl("/home/library/clibOlcCnts/editForm"),{"cntsCd":cntsCd,"curPage":"${curPage}"});
	$("#displayArea").slideUp('slow');
	$("#workArea").show();
}

/**
 * 콘텐츠 디자인 수정 폼
 */
function cntsDesignEdit(cntsCd) {
	$("#workArea").empty();
	$("#workArea").load(cUrl("/home/library/clibOlcCnts/editDesignForm"),{"cntsCd":cntsCd,"curPage":"${curPage}"});
	$("#displayArea").slideUp('slow');
	$("#workArea").show();
}

function closeWriteArea() {
	$("#displayArea").slideDown('slow');
	$("#workArea").empty();
	$("#workArea").slideUp('slow');
}


/**
 * OLC 분류 등록 폼
 */
function ctgrManage() {
	var addContent  = "<iframe id='addCCtgrFrame' name='addCtgrFrame' width='100%' height='100%' "
		+ "frameborder='0' scrolling='auto' src='<c:url value="/home/library/clibCntsCtgr/manageCtrg"/> "
		+ "'/>";
	$(".modal-title").css("color","#000000");
	modalBox.clear();
	modalBox.addContents(addContent);
	modalBox.resize(600, 400);
	modalBox.setTitle("<spring:message code="library.title.contents.category.write"/>");
	modalBox.show();
}

/**
 * Contents 관리 폼
 */
function pageManage(cntsCd) {
	var addContent  = "<iframe id='addCntsFrame' name='addCntsFrame' width='100%' height='100%' "
		+ "frameborder='0' scrolling='no' src='<c:url value="/home/library/clibOlcCnts/mainPage"/>"
		+ "?cntsCd="+cntsCd+"'/>";
	$(".modal-title").css("color","#000000");
	modalBox.clear();
	modalBox.addContents(addContent);
	modalBox.resize(1135, 700);
	modalBox.setTitle("<spring:message code="olc.title.contents.manage"/>");
	modalBox.show();
}

function preview(userNo, cntsCd, cntNm) {
	/*
	var addContent  = "<iframe id='addCCtgrFrame' name='addCtgrFrame' width='100%' height='100%' "
		+ "frameborder='0' scrolling='auto' src='<c:url value="/OlcCartrgManage.do"/>"
		+ "?cmd=previewMain&amp;olcCntsDTO.userNo="+userNo+"&amp;olcCntsDTO.cartrgCd="+cartrgCd+"'/>";
	$(".modal-title").css("color","#000000");
	modalBox.clear();
	modalBox.addContents(addContent);
	modalBox.resize(1100, 650);
	modalBox.setTitle(cartrgNm);
	modalBox.show();
	 */
	var url = cUrl("/home/library/clibOlcCnts/previewPop")+"?userNo="+userNo+"${AMPERSAND}cntsCd="+cntsCd;
	var winOption = "left=0,top=0,toolbar=no,location=no,directories=0,status=0,menubar=no,scrollbars=no,resizable=no,width=1100,height=773";
	var contentsPage = window.open(url, "contentsWin", winOption);
	contentsPage.focus();
}
/**
 * 파일 관리 폼
 */
function fileManage() {
	var addContent  = "<iframe id='manageFileFrame' name='manageFileFrame' width='100%' height='100%' "
		+ "frameborder='0' scrolling='no' src='<c:url value="/home/library/clibOlcFile/main"/>"
		+ "'/>";
	$(".modal-title").css("color","#000000");
	modalBox.clear();
	modalBox.addContents(addContent);
	modalBox.resize(800, 600);
	modalBox.setTitle("<spring:message code="button.manage.file"/>");
	modalBox.show();
}

function moveOLC(type, cntsCd) {
	var cmdStr = "moveUpCnt";
	if(type == "down" ) cmdStr = "moveDownCnt";
	$.getJSON(cUrl("/home/library/clibOlcCnts/" +cmdStr), 	// url
			{ 
			  //"olcCartrgDTO.ctgrCd" : ItemDTO.nodeId,
			  "cntsCd" : cntsCd
			}, function(data) { location.href="/home/library/clibOlcCnts/listCnts"; }					// params
		);
}


function contentsShare(cntsCd) {
    var addContent = "<iframe id='contentsShareFrame' name='contentsShareFrame' width='100%' height='100%' "
        + "frameborder='0' scrolling='auto' src='<c:url value="/home/library/clibOlcCnts/addShareForm"/>"
        + "?cntsCd="+cntsCd+"'/>";
	modalBox.clear();
	modalBox.addContents(addContent);
	modalBox.resize(600, 500);
	modalBox.setTitle("<spring:message code="library.title.contents.share"/>");
	modalBox.show();
}
</script>
</mhtml:home_body>
</mhtml:home_html>