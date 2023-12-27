<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>
<%pageContext.setAttribute("crlf", "\n");%>
<style>
	.facList .slick-slide { width : 407px; height : 300px;}
</style>

				<c:if test="${not empty facInfoList }">
      				<c:forEach var="item" items="${facInfoList }">
      					<div class="fac_area">
							<div class="row">
		                        <div class="col-sm-4">
		                        	<div class="">
		                        		<ul class="facList">
			                            	<c:forEach var="file" items="${item.attachFiles }" >
			                            		<li>
			                            			<img src="/app/file/thumb/${file.fileSn}" class="thumb" alt="시설 장비 사진">
			                            		</li>
			                                </c:forEach>      
		                                </ul>      
		                            </div>
		                        </div>
		                        <div class="col-sm-8">
		                            <div class="table2">
		                                <div class="row">
		                                    <div class="col">
		                                        <span class="title">시설 분류</span>
		                                        <div>${item.facCtgrNm }</div>
		                                    </div>
		                                    <div class="col">
		                                        <span class="title">시설명</span>
		                                        <div>${item.facNm }</div>
		                                    </div>
		                                </div>
		                                
		                                <div class="row">
		                                    <div class="col">
		                                        <span class="title">위치
		                                        </span>
		                                        <div>${item.facLoc }</div>
		                                    </div>
		                                    <div class="col">
		                                        <span class="title">면적</span>
		                                        <div>
		                                            ${item.facArea } ㎡
		                                        </div>
		                                    </div>
		                                </div>
		                                
		                                <div class="row">
		                                    <div class="col">
		                                        <span class="title">수용인원</span>
		                                        <div>
		                                            ${item.facCap }명
		                                        </div>
		                                    </div>
		                                    <div class="col">
		                                        <span class="title">용도</span>
		                                        <div>
		                                            ${item.facUse }
		                                        </div>
		                                    </div>
		                                </div>
		
		                                <div class="row">
		                                    <div class="col">
		                                        <span class="title">운영시간</span>
		                                        <div>
													${fn:replace(item.facOperTime , crlf ,"<br/>")}
		                                        </div>
		                                    </div>
		                                </div>
		                                
		                                <div class="row">
		                                    <div class="col">
		                                        <span class="title">부속시설</span>
		                                        <div>
		                                            ${item.facEqu }
		                                        </div>
		                                    </div>
		                                </div>
		                            </div>
		                            
		                            <div class="btns right mt20">
										<button class="btn gray2" onclick="viewResCal('${item.facCd}')">시설대관신청</button>                       
		                            </div>
		                        </div>
		                    </div>
                    	</div>
					</c:forEach>
				
					<div class="board_pager">
						<span class="inner"> 
							<meditag:paging pageInfo="${pageInfo}" funcName="listFac" name="front"/>
						</span>
					</div>
				</c:if>
				
		        <c:if test="${empty facInfoList }">
					<div class="no-list">조회된 시설 목록이 없습니다.</div>
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
