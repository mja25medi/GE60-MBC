<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>
<%pageContext.setAttribute("crlf", "\n");%>
<style>
	.equList .slick-slide { width : 407px; height : 300px;}
</style>
			<c:if test="${not empty equList }">
  				<c:forEach var="item" items="${equList }">
	  				<div class="fac_area">
						<div class="row">
	                        <div class="col-sm-4">
	                            <div class="">
	                            	<ul class="facList">
		                            	<c:forEach var="file" items="${item.attachFiles }" >
		                            		<li>
		                            			<img src="/app/file/thumb/${file.fileSn}" class="thumb" alt="장비 사진">
	                            			</li>
		                                </c:forEach>        
	                                </ul>    
	                            </div>
	                        </div>
	                        <div class="col-sm-8">
	                            <div class="table2">
	                                <div class="row">
	                                    <div class="col">
	                                        <span class="title">장비명</span>
	                                        <div>${item.equNm }</div>
	                                    </div>
	                                </div>
	                                
	                                <div class="row">
	                                    <div class="col">
	                                        <span class="title">장비설명</span>
	                                        <div>${fn:replace(item.equDesc , crlf ,"<br/>")}</div>
	                                    </div>
	                                </div>
	                                                        
	                                <div class="row">
	                                    <div class="col">
	                                        <span class="title">장비갯수</span>
	                                        <div>
	                                            ${item.itemCnt }대
	                                        </div>
	                                    </div>
	                                </div>
	                            </div>
	                            <div class="btns right mt20">
			                        <button class="btn gray2" onclick="viewRentCal('${item.equCd}')">장비대여신청</button>
			                    </div>	
	                        </div>
	                    </div>
	                </div>		
				</c:forEach>
				
				<div class="board_pager">
					<span class="inner"> 
						<meditag:paging pageInfo="${pageInfo}" funcName="listEqu" name="front"/>
					</span>
				</div>
			</c:if>
			        <c:if test="${empty equList }">
						<div class="no-list">조회된 장비 목록이 없습니다.</div>
					</c:if>

<script type="text/javascript">
	$(document).ready(function() {
        $('.slider').slick({
             arrows: true,
             dots: true,
             fade: true
         });
	 });
</script>
