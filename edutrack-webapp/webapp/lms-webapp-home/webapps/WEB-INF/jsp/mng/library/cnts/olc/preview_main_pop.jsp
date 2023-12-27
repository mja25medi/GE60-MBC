<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="clibOlcCntsVO" value="${clibOlcCntsVO}"/>
<c:set var="clibOlcPageList" value="${clibOlcPageList}"/>
<mhtml:mng_html>
<mhtml:mng_head titleName="${MENUNAME}">

</mhtml:mng_head>
<style type="text/css">
.list-group-item {
    position: relative;
    display: block;
    padding: 10px 15px;
    margin-bottom: -1px;
    border: 1px solid #ddd;
    background-color: rgba( 255, 255, 255, 0 );
}
.list-group-item-warning {
    font-weight: bold;
}
</style>
<c:set var="bkgImg" value=""/>
<c:set var="olcNameColor" value="#000"/>
<c:set var="listColor" value=""/>
<c:set var="listFontColor" value="#337ab7"/>
<c:set var="bkgImgType" value=""/>
<c:set var="styleStr" value=""/>
<c:if test="${clibOlcCntsVO.cntsTitleColor ne null && clibOlcCntsVO.cntsTitleColor ne ''}"><c:set var="olcNameColor" value="#${clibOlcCntsVO.cntsTitleColor }"/></c:if>
<c:if test="${clibOlcCntsVO.cntsTableFontColor ne null && clibOlcCntsVO.cntsTableFontColor ne ''}"><c:set var="listFontColor" value="#${clibOlcCntsVO.cntsTableFontColor }"/></c:if>
<c:if test="${clibOlcCntsVO.cntsTableBkgrColor ne null && clibOlcCntsVO.cntsTableBkgrColor ne '' }"><c:set var="listColor" value="background-color:#${clibOlcCntsVO.cntsTableBkgrColor }"/></c:if>
<c:if test="${clibOlcCntsVO.bkgrImgSn != 0 && clibOlcCntsVO.bkgrImgSn ne null }"><c:set var="bkgImg" value="background:url(/app/file/view2/${clibOlcCntsVO.bkgrImgSn});"/></c:if><!-- background-size:cover; -->
<c:if test="${clibOlcCntsVO.bkgrImgTypeCd eq 'RESIZE' }"><c:set var="bkgImgType" value="background-size:100% 100%"/></c:if>

<c:choose>
	<c:when test="${clibOlcCntsVO.pageNoPosCd eq 'RIGHT'}"><c:set var="align" value="text-right"/></c:when>
	<c:when test="${clibOlcCntsVO.pageNoPosCd eq 'LEFT'}"><c:set var="align" value="text-left"/></c:when>
	<c:when test="${clibOlcCntsVO.pageNoPosCd eq 'CENTER'}"><c:set var="align" value="text-center"/></c:when>
    <c:otherwise><c:set var="align" value="text-right"/></c:otherwise>
</c:choose>

<mhtml:frm_body cssTag="background-color:#ffffff; ${bkgImg} ${bkgImgType }">

<div class="userView">

	<%-- <c:set var="styleStr" value="padding:18px 10px 10px 10px;"/> --%>

	<div class="preview-header" style="height: 65px;line-height:65px;">
	<c:if test="${clibOlcCntsVO.logoImgSn != 0 && clibOlcCntsVO.logoImgSn ne null }">
		<h3 style="margin-top: 15px; margin-bottom: 10px; margin-left: 10px;">
			<img src="/app/file/view2/${clibOlcCntsVO.logoImgSn}" alt="logo" >
		</h3>
	</c:if>
		<h4 class="modal-title" id="myModalLabel" style="color: ${olcNameColor}; ${styleStr} <c:if test="${empty clibOlcCntsVO.logoImgSn }">padding-top: 15px;</c:if> ">${clibOlcCntsVO.cntsNm }</h4>
	</div>
	<div class="preview-body" style="height: 660px; ">
	<c:choose>
		<c:when test="${clibOlcCntsVO.cntsTablePosCd eq 'LEFT'}">

		<div class="contentViewList" style="height: 660px;float:left; width:260px; margin-right:-260px; ${listColor}">
			<div id="sortable" class="body" >
			<c:forEach var="item" items="${clibOlcPageList}" varStatus="status">
				<div class="btn-group" role="group" >
					<button type="button" onclick="javascript:viewPage('${status.count}')" class="btn btn-default btn-sm ct_txt listPage" style="color: ${listFontColor}" title="${item.pageNm}" id="list_${item.pageCd }">
					${status.count}. ${item.pageNm}
					</button>
				</div>
			</c:forEach>
			</div>
		</div>
		<div class="contentViewView" id="workArea" style="margin-left:280px;"></div>

		</c:when>
		<c:when test="${clibOlcCntsVO.cntsTablePosCd eq 'RIGHT'}">

		<div class="contentViewView" id="workArea" style="float:left; width:820px; margin-right:-820px;"></div>

		<div class=contentViewList style="float:left;width:260px;margin-left:840px; ${listColor}">
			<div id="sortable" class="body" >
			<c:forEach var="item" items="${clibOlcPageList}" varStatus="status">
				<div class="btn-group" role="group" >
					<button type="button" onclick="javascript:viewPage('${status.count}')" class="btn btn-default btn-sm ct_txt listPage" style="color: ${listFontColor}" title="${item.pageNm}" id="list_${item.pageCd }">
					${status.count}. ${item.pageNm}
					</button>
				</div>
			</c:forEach>
			</div>
		</div>

		</c:when>
		<c:otherwise>

		<div class="contentViewList" style="height: 660px;float:left; width:260px; margin-right:-260px; ${listColor}">
			<div id="sortable" class="body" >
			<c:forEach var="item" items="${clibOlcPageList}" varStatus="status">
				<div class="btn-group" role="group" >
					<button type="button" onclick="javascript:viewPage('${status.count}')" class="btn btn-default btn-sm ct_txt listPage" style="color: ${listFontColor}" title="${item.pageNm}" id="list_${item.pageCd }">
					${status.count}. ${item.pageNm}
					</button>
				</div>
			</c:forEach>
			</div>
		</div>
		<div class="contentViewView" id="workArea" style="margin-left:280px;"></div>

		</c:otherwise>
	</c:choose>
	</div>

	<div class="preview-footer">
		<ul class="pg-mini ${align }" style="padding: 10px 10px 10px 10px;">
			<li><a href="javascript:prevCnts()"><i class="glyphicon glyphicon-chevron-left"></i></a></li>
			<li id="pageArea">1/10</li>
			<li><a href="javascript:nextCnts()"><i class="glyphicon glyphicon-chevron-right"></i></a></li>
		</ul>
	</div>

</div>
<script type="text/javascript">
	var pageList = {};
	var ItemDTO = {"current" : 1};
	var contentColor = "${contentColor}";
	$(document).ready(function() {
		<c:forEach var="item" items="${clibOlcPageList}" varStatus="status">
		pageList['${status.count}'] = "${item.pageCd}";</c:forEach>
		viewPage('1');
	});

	function prevCnts() {
		if(ItemDTO.current == 1) {
			alert("<spring:message code="olc.message.contents.firstpage"/>");
		} else {
			viewPage(parseInt(ItemDTO.current,10) - 1);
		}
	}

	function nextCnts() {
		if(ItemDTO.current == ${fn:length(clibOlcPageList)}) {
			alert("<spring:message code="olc.message.contents.lastpage"/>");
		} else {
			viewPage(parseInt(ItemDTO.current,10) + 1);
		}
	}

	function viewPage(page) {
		ItemDTO.current = page;
		var pageInfo = pageList[page];
		$(".cnts-item").removeClass("list-group-item-warning");
		$(".listPage").removeClass("active");
		$("#list_"+pageInfo).addClass("active");

		$("#item-"+pageInfo).addClass("list-group-item-warning");
		var url = cUrl("/mng/library/clibOlcCnts/viewPagePop")+"?cntsCd=${clibOlcCntsVO.cntsCd}&pageCd="+pageInfo;
		var addContent  = "<iframe id='viewCntsFrame' name='viewCntsFrame' width='100%' height='660px' allowtransparency='true' "
			+ "frameborder='0' scrolling='yes' style='overflow:hidden;background-color: ${contentColor};' src='"+url+"'/>";
		$("#workArea").html(addContent);
		$("#pageArea").html(ItemDTO.current+" / ${fn:length(clibOlcPageList)}");
	}
</script>
</mhtml:frm_body>
</mhtml:mng_html>
