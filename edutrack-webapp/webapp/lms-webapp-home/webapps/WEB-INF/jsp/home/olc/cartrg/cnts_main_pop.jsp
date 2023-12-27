<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="olcCartrgVO" value="${olcCartrgVO}"/>
<c:set var="olcCntsVO" value="${vo}"/>
<mhtml:home_html>
<mhtml:home_head titleName="${MENUNAME}">

</mhtml:home_head>
<body style="padding-top: 0px;">
<table border="0" style="width:100%;height:100%;">
	<colgroup>
		<col style="width:auto;">
		<col style="width:10px;">
		<col style="width:841px;">
	</colgroup>
	<tr>
		<td valign="top">
			<ul class="list-group" style="margin-bottom:0px;">
				<li class="list-group-item list-group-item-success wordbreak" style="font-weight:bold;" data-type="root" data="${olcCartrgVO.cartrgCd}">
					${olcCartrgVO.cartrgNm}
					<div class="pull-right">
						<div class="btn-group" >
							<button type="button" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
								<i class="fa fa-chevron-down"></i>
							</button>
							<ul class="dropdown-menu dropdown-menu-right" role="menu" style="padding:3px;background-color:#f3f3f3;">
								<li style="padding:3px;">
									<a href="javascript:writeCnts('last','create','');" style="padding:0px;">
										<i class="glyphicon glyphicon-ok"></i> <spring:message code="olc.title.contents.make.common"/>
									</a>
								</li>
								<li style="padding:3px;">
									<a href="javascript:writeCnts('last','link','');" style="padding:0px;">
										<i class="glyphicon glyphicon-expand"></i> <spring:message code="olc.title.contents.link.common"/>
									</a>
								</li>
							</ul>
						</div>
					</div>
				</li>
			</ul>
			<div id="cntsList" style="width:100%;"></div>
		</td>
		<td>
		 	&nbsp;
		</td>
		<td valign="top" height="100%">
			<div id="workArea" style="width:100%;height:100%;min-height:666px;border:1px solid #dedede;"></div>
		</td>
	</tr>
</table>

<script type="text/javascript">
$(document).ready(function() {
	listCnts();
});

function listCnts() {
	var url = cUrl("/home/olc/cartrg/listCnts");
	$("#cntsList").load(url, {"cartrgCd":"${vo.cartrgCd}"});
}

function writeCnts(loc, type, code) {
	var url = cUrl("/home/olc/cartrg/addCntsForm")+"?cartrgCd=${vo.cartrgCd}&amp;cntsLoc="+loc+"&amp;cntsDiv="+type+"&amp;cntsCd="+code;
	var addContent  = "<iframe id='addCntsFrame' name='addCntsFrame' width='100%' height='660px;' "
		+ "frameborder='0' scrolling='yes' src='"+url+"'/>";
	$("#workArea").html(addContent);
}

function editCnts(code) {
	$(".cnts-item").removeClass("list-group-item-warning");
	$("#item-"+code).addClass("list-group-item-warning");
	var url = cUrl("/home/olc/cartrg/editCntsForm")+"?cartrgCd=${vo.cartrgCd}&amp;cntsCd="+code;
	var addContent  = "<iframe id='addCntsFrame' name='addCntsFrame' width='100%' height='660px;' "
		+ "frameborder='0' scrolling='yes' src='"+url+"'/>";
	$("#workArea").html(addContent);
}

function viewCnts(code) {
	$(".cnts-item").removeClass("list-group-item-warning");
	$("#item-"+code).addClass("list-group-item-warning");
	var url = cUrl("/home/olc/cartrg/viewCnts")+"?cartrgCd=${vo.cartrgCd}&amp;cntsCd="+code;
	var addContent  = "<iframe id='addCntsFrame' name='addCntsFrame' width='100%' height='660px;' "
		+ "frameborder='0' scrolling='yes' src='"+url+"'/>";
	$("#workArea").html(addContent);
}

function closeWrite() {
	$("#workArea").html("");
}

function moveCnts(type, cntsCd) {
	var cmdStr = "moveUpCnts";
	if(type == "down" ) cmdStr = "moveDownCnts";
	$.getJSON(cUrl("/home/olc/cartrg/" + cmdStr), 	// url
			{ 
			  "cartrgCd" : "${vo.cartrgCd}",
			  "cntsCd" : cntsCd
			}, function(data) { listCnts() }					// params
		);
}
</script>
</body>
</mhtml:home_html>