<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="clibOlcCntsVO" value="${vo}"/>
<c:set var="clibOlcPageVO" value="${vo.clibOlcPageVO}"/>
<mhtml:home_html>
<mhtml:home_head titleName="${MENUNAME}">

</mhtml:home_head>
<body style="padding-top: 0px;">

<div class="contentList">
	<div class="head">
		<p title="${vo.cntsCts }">${vo.cntsCts }</p>
		<div class="btn-group" role="group" aria-label="Basic example">
			<button type="button" class="btn btn-default btn-sm" id="pageAddBtn" style="width: 240px;">
				<i class="glyphicon glyphicon-film"></i><spring:message code="olc.title.contents.make.common"/>
			</button>
		</div>
	</div>
	<div id="pageList" class="body ui-sortable">

	</div>
</div>

<div class="contentView" id="workArea" style="width: 820px;">

</div>

<form id="clibOlcCntsForm" name="clibOlcCntsForm" onsubmit="return false" >
<input type="hidden" property="cntsCd" />
<input type="hidden" property="pageCd"  id="pageCd"/>
<input type="hidden" property="pageOdr" />
<input type="hidden" property="pageCds"  id="pageCds" />
</form>
<%--
<table border="0" style="width:100%;height:100%;">
	<colgroup>
		<col style="width:auto;">
		<col style="width:10px;">
		<col style="width:841px;">
	</colgroup>
	<tr>
		<td valign="top">
			<!-- <button type="button" class="btn btn-default btn-sm btn-block" id="pageAddBtn">
				<i class="glyphicon glyphicon-plus"></i>
			</button> -->

			<div class="head">
				<p>${clibOlcCntsVO.cntsNm }</p>
				<div class="btn-group" role="group" aria-label="Basic example">
					<button type="button" class="btn btn-default btn-sm"><i class="glyphicon glyphicon-film"></i> 콘텐츠 제작</button>
				</div>
			</div>
			<div id="pageList" style="width: 100%; margin-bottom: 5px;" class="body ui-sortable"></div>
		</td>
		<td>
		 	&nbsp;
		</td>
		<td valign="top" height="100%">
			<div id="workArea" style="width:100%;height:100%;min-height:666px;border:1px solid #dedede;"></div>
		</td>
	</tr>
</table>

 --%>

<script type="text/javascript">
	$(document).ready(function() {
		$("#pageAddBtn").bind("click", function(event) {
			pageWrite();
		});

		listPage(1);
		$(function() {
			$( "#pageList" ).sortable({
				placeholder: "ui-state-highlight",
				stop: function(event, div) {
			        sort();
			    }
			});
			$( "#pageList" ).disableSelection();

		});
	});

	function sort(){
 		var listDiv = $("#pageList .divPage");
		var itemList = "";
		for(var i=0; i<listDiv.length; i++){
			if(i==0) {
				itemList = listDiv[i].id;
			} else {
				itemList = itemList+'|'+listDiv[i].id;
			}
		}
		//alert(itemList);
		$("#pageCds").val(itemList);
		process('sort');
 	}

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		if(!validate(document.clibOlcCntsForm)) return;
		$('#clibOlcCntsForm').attr("action","/home/library/clibOlcCnts/" +cmd);
		$('#clibOlcCntsForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		//salert(resultDTO.message);
		if(resultDTO.result >= 0) {
			// 정상 처리
			parent.bkgImgList(1);
			parent.modalBoxClose();
		} else {
			// 비정상 처리
		}
	}

	function listPage(page) {
		var pageCd = $("#pageCd").val();
		var url = cUrl("/home/library/clibOlcCnts/listPage");
		$("#pageList").load(url, {"cntsCd":"${vo.cntsCd}","pageCd" :pageCd});
	}

	function pageWrite() {
		var popHeight= $(".modal-body", window.parent.document).height();
		var url = cUrl("/home/library/clibOlcCnts/addPageForm")+"?cntsCd=${vo.cntsCd}";
		var addContent  = "<iframe id='addPageFrame' name='addPageFrame' width='100%' height='"+popHeight+"px;' "
			+ "frameborder='0' scrolling='yes' src='"+url+"'/>";
		$("#workArea").html(addContent);
	}

	function editPage(pageCd) {
		var popHeight= $(".modal-body", window.parent.document).height();
		$(".cnts-item").removeClass("list-group-item-warning");
		$(".listPage").removeClass("active");
		$("#list_"+pageCd).addClass("active");

		$("#pageCd").val(pageCd);
		$("#item-"+pageCd).addClass("list-group-item-warning");
		var url = cUrl("/home/library/clibOlcCnts/editPageForm")+"?cntsCd=${vo.cntsCd}&amp;pageCd="+pageCd;
		var addContent  = "<iframe id='addCntsFrame' name='addCntsFrame' width='100%' height='"+popHeight+"px;' "
			+ "frameborder='0' scrolling='yes' src='"+url+"'/>";
		$("#workArea").html(addContent);
	}

	function viewPage(pageCd) {
		var popHeight= $(".modal-body", window.parent.document).height();
		$(".cnts-item").removeClass("list-group-item-warning");
		$(".listPage").removeClass("active");
		$("#list_"+pageCd).addClass("active");

		$("#pageCd").val(pageCd);
		$("#item-"+pageCd).addClass("list-group-item-warning");
		var url = cUrl("/home/library/clibOlcCnts/viewPage")+"?cntsCd=${vo.cntsCd}&amp;pageCd="+pageCd;
		var addContent  = "<iframe id='addCntsFrame' name='addCntsFrame' width='100%' height='"+popHeight+"px;' "
			+ "frameborder='0' scrolling='yes' src='"+url+"'/>";
		$("#workArea").html(addContent);
	}

	function closeWrite(pageCd) {
		$("#workArea").html("");
		viewPage(pageCd);
	}

	function closeWriteArea() {
		$("#workArea").html("");
	}

	function moveCnts(type, pageCd) {
		var cmdStr = "moveUpPage";
		if(type == "down" ) cmdStr = "moveDownPage";
		$.getJSON(cUrl("/mng/library/clibOlcCnts/"+ cmdStr), 	// url
				{ 
				  "cntsCd" : "${vo.cntsCd}",
				  "pageCd" : pageCd
				}, function(data) { listCnts() }					// params
			);
	}
</script>
</body>
</mhtml:home_html>
