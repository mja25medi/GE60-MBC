<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>

		<ul class="tabs mb0 two_list">
			<li><a href="/home/org/fac/listResMain" title="시설">시설</a></li>
			<li class="active"><a href="/home/org/equ/listRentMain" title="장비">장비</a></li>
		</ul>

		<!-- 본문 content 부분 -->
		<div class="content">
			<div id="dataList" style="width:100%;">
		    </div>
		</div>
		<!-- //본문 content 부분 -->
	
<script type="text/javascript">
	/** 페이지 초기화 */
	$(document).ready(function() {
		modalBox = new $M.ModalDialog({"modalid" : "test1"});
		listRent(1);
	});
	
	/**
	 * 시설 예약 목록 조회
	 */
	function listRent(page) {
		$("#loadingBar").show();
	    $("#dataList").load(
	    	cUrl("/home/org/equ/listRent"),{
	    		"curPage" : page
	    	},
	    	function () {
	    		$("#loadingBar").hide();
	    	}
	    );
	}
	
	$('#goSubmit').bind("click", function(event) {
	});

	function goFacMain() {
		document.location.href = "/";
	}
	
	function goSubmit() {
		document.location.href = "/";
	}
	
	function detailView(rentCd) {
		var url = '<c:url value="/home/org/equ/viewRentInfoPop"/>' + "?rentCd=" + rentCd;
		var addContent = "<iframe id='rentForm' name='rentForm' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='"+url+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(850, 450);
		modalBox.setTitle("예약 상세 조회");
		modalBox.show();
	}
</script>