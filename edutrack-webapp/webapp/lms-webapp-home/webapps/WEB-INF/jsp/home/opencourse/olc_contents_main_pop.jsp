<!DOCTYPE html>
<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="bookmarkVO" value="${vo}"/>
<c:set var="credit" value="no-credit"/>
<c:set var="cmiMode" value="browse"/>

<mhtml:class_html>
<mhtml:class_head>

</mhtml:class_head>
<mhtml:frm_body cssTag="background-color:#ffffff;">
<table border="0" style="width:100%;height:100%;">
	<colgroup>
		<col style="width:auto;">
		<col style="width:10px;">
		<col style="width:802px;">
	</colgroup>
	<tr>
		<td colspan="3">
			<div style="border:1px solid #dedede;margin-bottom:10px;padding:10px 15px 10px 15px;font-size:20px;height:50px;">
				${olcCartrgVO.cartrgNm}
			</div>
		</td>
	</tr>
	<tr>
		<td valign="top">
			<div id="cntsList" style="width:100%;height:610px;;border:1px solid #dedede;overflow-y:auto;">
				<ul class="list-group">
				<c:forEach var="item" items="${olcContentsList}" varStatus="status">
					<li class="list-group-item cnts-item" id="item-${item.cntsCd}">
						<a href="javascript:viewCnts('${status.count}')" title="<spring:message code="button.preview"/>">${status.count}. ${item.cntsNm}</a>
					</li>
				</c:forEach>
				</ul>
			</div>
		</td>
		<td>
		 	&nbsp;
		</td>
		<td valign="top" height="100%">
			<div id="workArea" style="width:100%;height:100%;min-height:540px;border:1px solid #dedede;"></div>
			<div style="padding:15px; font-size:20px; margin-top:10px;width:100%;height:60px;border:1px solid #dedede;" class="text-right">
				<a href="javascript:prevCnts()"><i class="glyphicon glyphicon-chevron-left"></i></a>
				<span id="pageArea">1 / 6</span>
				<a href="javascript:nextCnts()"><i class="glyphicon glyphicon-chevron-right"></i></a>
			</div>
		</td>
	</tr>
</table>

<script type="text/javascript">
	var cntsList = {};
	var ItemDTO = {"current" : 1};
	$(document).ready(function() {
		console.log("open_course_olc_contents_frame_pop");
		window.resizeTo("1100", "740");
		<c:forEach var="item" items="${olcContentsList}" varStatus="status">
		cntsList['${status.count}'] = "${item.cntsCd}";</c:forEach>
		initEdutrackAPI();
		viewCnts('1');
	});

	window.onunload=function(){
		doPageQuit();
	};

	function prevCnts() {
		if(ItemDTO.current == 1) {
			alert("<spring:message code="olc.message.contents.firstpage"/>");
		} else {
			viewCnts(parseInt(ItemDTO.current,10) - 1);
		}
	}

	function nextCnts() {
		if(ItemDTO.current == ${fn:length(itemList)}) {
			alert("<spring:message code="olc.message.contents.lastpage"/>");
		} else {
			viewCnts(parseInt(ItemDTO.current,10) + 1);
		}
	}

	function viewCnts(page) {
		ItemDTO.current = page;

		var pagePerRatio = Math.round(1 / ${fn:length(olcContentsList)} * 100,0);
		var gapRatio = 100 - (pagePerRatio * ${fn:length(olcContentsList)});
		var pageRatio = pagePerRatio;
		if(page == 1) pageRatio = pagePerRatio + gapRatio;

		var pageInfo = cntsList[page];
		$(".cnts-item").removeClass("list-group-item-warning");
		$("#item-"+pageInfo).addClass("list-group-item-warning");
		var url = cUrl("/home/openCourse/viewOlc")+"?cartrgCd=${olcCartrgVO.cartrgCd}&amp;cntsCd="+pageInfo;
		var addContent  = "<iframe id='viewCntsFrame' name='viewCntsFrame' width='100%' height='534px' "
			+ "frameborder='0' scrolling='yes' src='"+url+"'/>";
		$("#workArea").html(addContent);
		$("#pageArea").html(ItemDTO.current+" / ${fn:length(olcContentsList)}");

		edutrackAPI.SetPage(pageInfo, pageRatio);
	}

	var result = "";
	var seekTime = "0";
	var prgrRatio = "0";
	//var edutrackAPI = window.dialogArguments.edutrackAPI;
	var edutrackAPI = opener.edutrackAPI;
	function initEdutrackAPI() {
		if(edutrackAPI != null) {
			var api = edutrackAPI;
			api.SetStdNo("${STUDENTNO}");
			api.SetSbjCd("${vo.sbjCd}");
			api.SetUnitCd("${vo.unitCd}");
			api.SetRegNo("${USERNO}");
			api.SetBookmarkType("${vo.prgrChkType}");
			api.SetTotalPageCnt("${vo.totalPage}");
			api.SetShowTime("${vo.critTm}");
			api.SetCredit("${credit}");
			result = api.Initialize("");
			seekTime = api.GetSeekTime();
			prgrRatio = api.GetValue("prgrRatio");
		}
	}
	function setSeekTime(time) {
		edutrackAPI.SetSeekTime(time);
	}

	function getSeekTime() {
		return seekTime;
	}

	function getPrgrRatio() {
		return prgrRatio;
	}

	function initAPI() {}

	function evtOnUnload() {
		result = edutrackAPI.Terminate("");
	}

	window.onunload = function(){
		evtOnUnload();
	}
</script>
</mhtml:frm_body>
</mhtml:class_html>