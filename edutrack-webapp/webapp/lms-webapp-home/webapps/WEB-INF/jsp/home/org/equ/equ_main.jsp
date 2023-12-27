<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>
        <!-- 본문 content 부분 -->
			<ul class="tabs mb0 two_list">
				<li><a href="/home/org/fac/listFacMain" title="시설">시설</a></li>
				<li class="active"><a href="/home/org/equ/listEquMain" title="장비">장비</a></li>
			</ul>
			
			<div id="infoListDiv"></div>
                     
<script type="text/javascript">

	$(document).ready(function() {
		listEqu(1);
	});
	
	function listEqu(page) {
		
		$("#infoListDiv").load(cUrl("/home/org/equ/listEqu"),{
			"curPage" : page,
		});
	}
	
	function viewRentCal(equCd) {
		document.location.href = "/home/org/equ/rentCalMain?equCd=" + equCd;
	}
</script>
