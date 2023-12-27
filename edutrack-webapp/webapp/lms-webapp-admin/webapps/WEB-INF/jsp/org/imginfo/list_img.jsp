<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>


				<ul class="list-goupu" style="list-style: none; padding-left:0px;" id="${vo.imgTypeCd}_list">
					<c:if test="${empty orgImgInfoList}">
					<li class="noitem">
						<div class="well well-sm">
							<spring:message code="org.message.imginfo.nodata"/>
						</div>	
					<li>
					</c:if>
					<c:forEach var="item" items="${orgImgInfoList}">
					<li class="list-item" id="${item.imgSn}">
						<div class="well well-sm">
							<img src="<c:url value="/app/file/view2/${item.bkgrFileSn}"/>" alt="${item.bkgrImgNm}" style="max-width:800px;"/><br/>
							<p style="margin-top:5px;"><c:if test="${vo.imgTypeCd eq 'MAINIMG'}"><i class="glyphicon glyphicon-move" style="cursor:pointer"></i></c:if> ${item.imgTitle} 
							<a href="javascript:imgEdit('${vo.imgTypeCd}','${item.imgSn}');"><i class="glyphicon glyphicon-cog"></i></a></p>
						</div>	
					</li>
					</c:forEach>
				</ul>
<c:if test="${vo.imgTypeCd eq 'MAINIMG'}">
<script type="text/javascript">
	// 페이지 초기화
	$(document).ready(function() {
	    $("#MAINIMG_list").sortable({
	    	handle: 'i.glyphicon-move',
	    	stop : function() {
	    		sortString = "";
	    		$("#MAINIMG_list").children('li').each(function(){
	    			sortString += "|"+$(this).attr("id"); 
	    		});
	    		sortString = sortString.substring(1);
	    		$.getJSON(cUrl("/adm/org/imginfo/sort.do"), {"orgCd" : "${vo.orgCd}", "imgTypeCd"	: "${vo.imgTypeCd}", "imgSns" : sortString}, function(resultVO){
	    			if(resultVO.result > 0) callImgList();
	    		});
	    	}
	    });
	    $("#MAINIMG_list").disableSelection();
	});
</script>
</c:if>