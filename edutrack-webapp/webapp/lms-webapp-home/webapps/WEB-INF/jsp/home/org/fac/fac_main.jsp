<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>
        <!-- 본문 content 부분 -->
					<ul class="tabs mb0 two_list">
						<li class="active"><a href="/home/org/fac/listFacMain" title="시설">시설</a></li>
						<li><a href="/home/org/equ/listEquMain" title="장비">장비</a></li>
					</ul>

                    <div class="board_info fac_select">
                        <h3 class="subMenu_title"></h3>
                        <form name="srhForm" method="post" action="board_list" class="board_search">
                            <fieldset class="form-row">
                            	<select class="form-select" name="facCtgrCd" id="facCtgrCd" onchange="listFac(1)" title="검색어 분류를 선택하세요.">
	                                <option value="" selected="selected">시설 분류 전체</option>
	                                <c:forEach var="item" items="${ctgrList }">
	                                	<option value="${item.facCtgrCd }">${item.facCtgrNm }</option>
	                                </c:forEach>
                            	</select>
                            </fieldset>
                        </form>
                    </div>
	
					<div id="infoListDiv"></div>
                     
<script type="text/javascript">

	$(document).ready(function() {
		listFac(1);
	});
	
	function listFac(page) {
		var facCtgrCd = $("#facCtgrCd").val();
		
		$("#infoListDiv").load(cUrl("/home/org/fac/listFac"),{
			"curPage" : page,
			"facCtgrCd" : facCtgrCd
		});
	}
	
	function viewResCal(facCd) {
		document.location.href = "/home/org/fac/resCalMain?facCd=" + facCd;
	}
</script>
