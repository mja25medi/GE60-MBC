<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="img_base" value="/img/home" scope="request"/>
<c:set var="paging" value="Y"/>
<c:set var="listCourse" value="Y"/>

                    <div class="myCourseList col4">
                        <ul class="list_ul">
						<c:forEach var="item" items="${courseList}" varStatus="status">
                            <li class="list_li">
                                <div class="item">
                                    <a href="<c:url value="/lec/main/goLecture?crsCreCd=${item.crsCreCd}&amp;stdNo=${item.stdNo}" />">
                                        <div class="thumb_img">
                                            <span class="thumb">
                                            	<c:if test="${not empty item.thumbFileSn}">
                                            	<img src="<c:url value="/app/file/thumb/${item.thumbFileSn}"/>" alt="${item.crsCreNm}" /><br/>
                                            	</c:if>
                                             </span>  
                                        </div>                                       
                                    </a>  
                                    <div class="item_txt">  
                                        <div class="card_label">
                                            <c:forEach items="${item.metaTagArr}" var="tag" varStatus="status">
			                                    <span class="label basic bcBlue">${tag}</span>
			                                 </c:forEach>
                                        </div> 
                                        <div class="title">${item.crsCreNm}</div>                               
                                        <div class="etc_info">
                                            <span class="s_num">${item.sbjCnt }과목</span>
                                            <span class="date"><meditag:dateformat type="1" delimeter="." property="${item.startDttm }"/> ~ <meditag:dateformat type="1" delimeter="." property="${item.endDttm }"/></span>
                                        </div>
                                        <div class="my_prog_rate">
                                            <div class="progress">
                                                <div class="bar red_type" style="width: ${item.prgrRatio }%;"></div>
                                            </div>
                                            <span class="prog_num">진도율</span><span class="meta">${item.prgrRatio }%</span>                                                
                                        </div>
                                    </div>
                                    <div class="bottom_button">
                                        <button class="go" onclick="javascript:goLecture('${item.crsCreCd}','${item.stdNo}')">학습하기</button>
                                    </div>                                        
                                </div>
                            </li>
	
						</c:forEach>
                        <c:if test="${empty courseList }">
                        	<li class="list_li">
                        			수강중인 과정이 없습니다.
                        	</li>
                        </c:if>              
                        
 
                        </ul>
                    </div><!--//myCourseList-->



<script type="text/javascript">

	function goLecture(crsCreCd, stdNo) {
		location.href=cUrl("/lec/main/goLecture") + "?crsCreCd=" + crsCreCd + "&stdNo=" + stdNo ;
	}
</script>

