<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="olcCartrgVO" value="${olcCartrgVO}"/>
<c:set var="olcCntsList" value="${olcCntsList}"/>
<mhtml:home_html>
<mhtml:home_head titleName="${MENUNAME}">

</mhtml:home_head>
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
<c:if test="${olcCartrgVO.olcNameColor ne null && olcCartrgVO.olcNameColor ne ''}"><c:set var="olcNameColor" value="#${olcCartrgVO.olcNameColor }"/></c:if>
<c:if test="${olcCartrgVO.listFontColor ne null && olcCartrgVO.listFontColor ne ''}"><c:set var="listFontColor" value="#${olcCartrgVO.listFontColor }"/></c:if>
<c:if test="${olcCartrgVO.listBgColor ne null && olcCartrgVO.listBgColor ne '' }"><c:set var="listColor" value="background-color:#${olcCartrgVO.listBgColor }"/></c:if>
<c:if test="${olcCartrgVO.bkgImgSn != 0 && olcCartrgVO.bkgImgSn ne null }"><c:set var="bkgImg" value="background:url(/app/file/view2/${olcCartrgVO.bkgImgSn});"/></c:if><!-- background-size:cover; -->
<c:if test="${olcCartrgVO.bkgImgType eq 'RESIZE' }"><c:set var="bkgImgType" value="background-size:100% 100%"/></c:if>

<body style="padding-top: 0px; ${bkgImg} ${bkgImgType }">

<div style="border-bottom:0px;">
<c:if test="${olcCartrgVO.topLogoImgSn != 0 && olcCartrgVO.topLogoImgSn ne null }">
<img alt="toplogo" src="/app/file/view2/${olcCartrgVO.topLogoImgSn}" height="60px;"  style="float: left;">
<c:set var="styleStr" value="padding:18px 10px 10px 10px;"/>
</c:if>
<h4 class="modal-title" id="modal_title" style="color: ${olcNameColor}; ${styleStr}">${olcCartrgVO.cartrgNm }</h4>
</div>

<table border="0" style="width:100%;height:100%; ">
	<colgroup>
<c:choose>
	<c:when test="${olcCartrgVO.listLocation eq 'LEFT'}">
		<col style="width:23%">
		<col style="width:1%">
		<col style="width:76%">
	</c:when>
	<c:when test="${olcCartrgVO.listLocation eq 'RIGHT'}">
		<col style="width:76%;">
		<col style="width:1%;">
		<col style="width:23%;">
	</c:when>
    <c:otherwise>
    	<col style="width:23%">
		<col style="width:1%">
		<col style="width:76%">
    </c:otherwise>
</c:choose>

	</colgroup>
	<tr>
		<td valign="top">

<c:choose>
	<c:when test="${olcCartrgVO.listLocation eq 'LEFT'}">
		<!-- 리스트 영역 -->
		<div id="cntsList" style="width:100%;height:610px;border:1px solid #dedede;overflow-y:auto;padding-top:10px;">
			<ul class="list-group" style="${listColor}">
			<c:forEach var="item" items="${olcCntsList}" varStatus="status">
				<li class="list-group-item cnts-item wordbreak" id="item-${item.cntsCd}" style="${listColor}" >
					<a href="javascript:viewCnts('${status.count}')" title="${status.count}. ${item.cntsNm}" style="color: ${listFontColor}">${status.count}. ${item.cntsNm}</a>
				</li>
			</c:forEach>
			</ul>
		</div>
		<!-- 리스트 영역 -->
	</c:when>
	<c:when test="${olcCartrgVO.listLocation eq 'RIGHT'}">
		<!-- 콘텐츠영역 -->
		<c:set var="align" value="text-right"/>
		<c:set var="contentColor" value="#fff"/>
		<c:choose>
			<c:when test="${olcCartrgVO.pageNoLocation eq 'RIGHT'}"><c:set var="align" value="text-right"/></c:when>
			<c:when test="${olcCartrgVO.pageNoLocation eq 'LEFT'}"><c:set var="align" value="text-left"/></c:when>
			<c:when test="${olcCartrgVO.pageNoLocation eq 'CENTER'}"><c:set var="align" value="text-center"/></c:when>
		    <c:otherwise><c:set var="align" value="text-right"/></c:otherwise>
		</c:choose>
		<c:if test="${olcCartrgVO.contentBgColor ne null && olcCartrgVO.contentBgColor ne ''  }"><c:set var="contentColor" value="#${olcCartrgVO.contentBgColor }"/></c:if>

		<div id="workArea" style="width:100%;min-height:540px;border:1px solid #dedede; background-color: ${contentColor};padding-top:10px;padding-left:10px;"></div>
		<div style="padding:15px; font-size:20px; margin-top:10px;width:100%;height:53px;border:1px solid #dedede;color: ${olcNameColor};" class="${align }">
			<a href="javascript:prevCnts()"><i class="glyphicon glyphicon-chevron-left"></i></a>
			<span id="pageArea">1 / 6</span>
			<a href="javascript:nextCnts()"><i class="glyphicon glyphicon-chevron-right"></i></a>

		</div>

		<!-- 콘텐츠영역 -->
	</c:when>
    <c:otherwise>
    	<!-- 리스트 영역 -->
		<div id="cntsList" style="width:100%;height:610px;border:1px solid #dedede;overflow-y:auto;padding-top:10px;">
			<ul class="list-group" style=" ${listColor}">
			<c:forEach var="item" items="${olcCntsList}" varStatus="status">
				<li class="list-group-item cnts-item wordbreak" id="item-${item.cntsCd}" style="${listColor}" >
					<a href="javascript:viewCnts('${status.count}')" title="${status.count}. ${item.cntsNm}" style="color: ${listFontColor}">${status.count}. ${item.cntsNm}</a>
				</li>
			</c:forEach>
			</ul>
		</div>
		<!-- 리스트 영역 -->
    </c:otherwise>
</c:choose>

		</td>
		<td>
		 	&nbsp;
		</td>
		<td valign="top" height="100%">
<c:choose>
	<c:when test="${olcCartrgVO.listLocation eq 'LEFT'}">
		<!-- 콘텐츠영역 -->
		<c:set var="align" value="text-right"/>
		<c:set var="contentColor" value="#fff"/>
		<c:choose>
			<c:when test="${olcCartrgVO.pageNoLocation eq 'RIGHT'}"><c:set var="align" value="text-right"/></c:when>
			<c:when test="${olcCartrgVO.pageNoLocation eq 'LEFT'}"><c:set var="align" value="text-left"/></c:when>
			<c:when test="${olcCartrgVO.pageNoLocation eq 'CENTER'}"><c:set var="align" value="text-center"/></c:when>
		    <c:otherwise><c:set var="align" value="text-right"/></c:otherwise>
		</c:choose>
		<c:if test="${olcCartrgVO.contentBgColor ne null && olcCartrgVO.contentBgColor ne '' }"><c:set var="contentColor" value="#${olcCartrgVO.contentBgColor }"/></c:if>

		<div id="workArea" style="width:100%;min-height:540px;border:1px solid #dedede; background-color: ${contentColor};padding-top:10px;padding-left:10px;"></div>
		<div style="padding:15px; font-size:20px; margin-top:10px;width:100%;height:53px;border:1px solid #dedede;color: ${olcNameColor};" class="${align }">
			<a href="javascript:prevCnts()"><i class="glyphicon glyphicon-chevron-left"></i></a>
			<span id="pageArea">1 / 6</span>
			<a href="javascript:nextCnts()"><i class="glyphicon glyphicon-chevron-right"></i></a>

		</div>

		<!-- 콘텐츠영역 -->
	</c:when>
	<c:when test="${olcCartrgVO.listLocation eq 'RIGHT'}">
		<!-- 리스트 영역 -->
		<div id="cntsList" style="width:100%;height:610px;border:1px solid #dedede;overflow-y:auto;padding-top:10px;">
			<ul class="list-group" style=" ${listColor}">
			<c:forEach var="item" items="${olcCntsList}" varStatus="status">
				<li class="list-group-item cnts-item wordbreak" id="item-${item.cntsCd}" style=" ${listColor}" >
					<a href="javascript:viewCnts('${status.count}')" title="${status.count}. ${item.cntsNm}" style="color: ${listFontColor}">${status.count}. ${item.cntsNm}</a>
				</li>
			</c:forEach>
			</ul>
		</div>
		<!-- 리스트 영역 -->
	</c:when>
    <c:otherwise>
    	<!-- 콘텐츠영역 -->
		<c:set var="align" value="text-right"/>
		<c:set var="contentColor" value="#fff"/>
		<c:choose>
			<c:when test="${olcCartrgVO.pageNoLocation eq 'RIGHT'}"><c:set var="align" value="text-right"/></c:when>
			<c:when test="${olcCartrgVO.pageNoLocation eq 'LEFT'}"><c:set var="align" value="text-left"/></c:when>
			<c:when test="${olcCartrgVO.pageNoLocation eq 'CENTER'}"><c:set var="align" value="text-center"/></c:when>
		    <c:otherwise><c:set var="align" value="text-right"/></c:otherwise>
		</c:choose>
		<c:if test="${olcCartrgVO.contentBgColor ne null && olcCartrgVO.contentBgColor ne '' }"><c:set var="contentColor" value="#${olcCartrgVO.contentBgColor }"/></c:if>

		<div id="workArea" style="width:100%;min-height:540px;border:1px solid #dedede; background-color: ${contentColor};padding-top:10px;padding-left:10px;"></div>
		<div style="padding:15px; font-size:20px; margin-top:10px;width:100%;height:53px;border:1px solid #dedede;color: ${olcNameColor};" class="${align }">
			<a href="javascript:prevCnts()"><i class="glyphicon glyphicon-chevron-left"></i></a>
			<span id="pageArea">1 / 6</span>
			<a href="javascript:nextCnts()"><i class="glyphicon glyphicon-chevron-right"></i></a>

		</div>

		<!-- 콘텐츠영역 -->
    </c:otherwise>
</c:choose>
		</td>
	</tr>
</table>

<script type="text/javascript">
var cntsList = {};
var ItemDTO = {"current" : 1};
var contentColor = "${contentColor}";
$(document).ready(function() {
	<c:forEach var="item" items="${olcCntsList}" varStatus="status">
	cntsList['${status.count}'] = "${item.cntsCd}";</c:forEach>
	viewCnts('1');
});

function prevCnts() {
	if(ItemDTO.current == 1) {
		alert("<spring:message code="olc.message.contents.firstpage"/>");
	} else {
		viewCnts(parseInt(ItemDTO.current,10) - 1);
	}
}

function nextCnts() {
	if(ItemDTO.current == ${fn:length(olcCntsList)}) {
		alert("<spring:message code="olc.message.contents.lastpage"/>");
	} else {
		viewCnts(parseInt(ItemDTO.current,10) + 1);
	}
}

function viewCnts(page) {
	ItemDTO.current = page;
	var pageInfo = cntsList[page];
	$(".cnts-item").removeClass("list-group-item-warning");
	$("#item-"+pageInfo).addClass("list-group-item-warning");

	var url = cUrl("/home/olc/cartrg/viewCnts?cartrgCd=${olcCartrgVO.cartrgCd}&amp;cntsCd="+pageInfo;
	var addContent  = "<iframe id='viewCntsFrame' name='viewCntsFrame' width='100%' height='534px' "
		+ "frameborder='0' scrolling='yes' style='overflow:hidden' src='"+url+"'/>";
	$("#workArea").html(addContent);
	$("#pageArea").html(ItemDTO.current+" / ${fn:length(olcCntsList)}");
}
</script>
</body>
</mhtml:home_html>
