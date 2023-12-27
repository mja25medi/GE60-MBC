<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="clibOlcCntsVO" value="${vo}"/>
<c:set var="clibOlcPageVO" value="${vo.clibOlcPageVO}"/>

<div class="contentList">
	<div class="head">
		<p title="${vo.cntsCts }">${vo.cntsCts }</p>
		<div class="btn-group" role="group" aria-label="Basic example" >
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

<form id="clibOlcCntsForm" name="clibOlcCntsForm" onsubmit="return false">
<input type="hidden" name="cntsCd" />
<input type="hidden" name="pageCd"  id="pageCd"/>
<input type="hidden" name="pageOdr" />
<input type="hidden" name="pageCds"  id="pageCds" val/>
</form>

<script type="text/javascript">
	var frameHeight = 0;
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

		frameHeight = parent.managePageFrame.innerHeight;

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
		$('#clibOlcCntsForm').attr("action", "/mng/library/clibOlcCnts/" + cmd);
		$('#clibOlcCntsForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		//alert(resultDTO.message);
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
		var url = cUrl("/mng/library/clibOlcCnts/listPage");
		$("#pageList").load(url, {"cntsCd":"${vo.cntsCd}","pageCd" :pageCd});
	}

	function pageWrite() {
		var url = generateUrl("/mng/library/clibOlcCnts/addPageFormPop",{"cntsCd":"${vo.cntsCd}"});
		var addContent  = "<iframe id='addPageFrame' name='addPageFrame' width='100%' height='"+frameHeight+"px' "
			+ "frameborder='0' scrolling='auto' src='"+url+"'/>";
		$("#workArea").html(addContent);
	}

	function editPage(pageCd) {
		$(".cnts-item").removeClass("list-group-item-warning");
		$(".listPage").removeClass("active");
		$("#list_"+pageCd).addClass("active");

		$("#pageCd").val(pageCd);
		$("#item-"+pageCd).addClass("list-group-item-warning");
		var url = generateUrl("/mng/library/clibOlcCnts/editPageFormPop",{ "cntsCd":"${vo.cntsCd}", "pageCd":pageCd});
		var addContent  = "<iframe id='addCntsFrame' name='addCntsFrame' width='100%' height='"+frameHeight+"px' "
			+ "frameborder='0' scrolling='auto' src='"+url+"'/>";
		$("#workArea").html(addContent);
	}

	function viewPage(pageCd) {
		$(".cnts-item").removeClass("list-group-item-warning");
		$(".listPage").removeClass("active");
		$("#list_"+pageCd).addClass("active");

		$("#pageCd").val(pageCd);
		$("#item-"+pageCd).addClass("list-group-item-warning");
		$("#list_"+pageCd).addClass("active");
		var url = generateUrl("/mng/library/clibOlcCnts/viewPagePop",{ "cntsCd":"${vo.cntsCd}", "pageCd":pageCd});
		var addContent  = "<iframe id='addCntsFrame' name='addCntsFrame' width='100%' height='"+frameHeight+"px' "
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
		$.getJSON(cUrl("/mng/library/clibOlcCnts/" + cmdStr), 	// url
				{ 
				  "cntsCd" : "${clibOlcCntsVO.cntsCd}",
				  "pageCd" : pageCd
				}, function(data) { listCnts() }					// params
			);
	}
</script>
