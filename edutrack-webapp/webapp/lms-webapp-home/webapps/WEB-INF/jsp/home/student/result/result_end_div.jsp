<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
			
<div id="content" class="content">
        <div class="myCourseList col4 finish">
            <ul class="list_ul">
            	<c:forEach items="${stdScoreList}" var="item" varStatus="status">
            		<fmt:formatNumber type="number"  value = "${item.examScore}" pattern = "0" var = "examScore"/>
					<fmt:formatNumber type="number"  value = "${item.semiExamScore}" pattern = "0" var = "semiExamScore"/>
					<fmt:formatNumber type="number"  value = "${item.asmtScore}" pattern = "0" var = "asmtScore"/>
					<fmt:formatNumber type="number"  value = "${item.prgrScore}" pattern = "0" var = "prgrScore"/>
					<fmt:formatNumber type="number"  value = "${item.etcScore}" pattern = "0" var = "etcScore"/>
					<fmt:formatNumber type="number"  value = "${item.etcScore2}" pattern = "0" var = "etcScore2"/>
					<fmt:formatNumber type="number"  value = "${item.etcScore3}" pattern = "0" var = "etcScore3"/>
					<fmt:formatNumber type="number"  value = "${item.etcScore4}" pattern = "0" var = "etcScore4"/>
					<fmt:formatNumber type="number"  value = "${item.etcScore5}" pattern = "0" var = "etcScore5"/>
	                <li class="list_li">
	                    <div class="item">
	                        <div class="item_txt">  
	                            <div class="card_label">
	                            	<c:if test="${item.metaTag ne ''}">
	                                    <c:forEach items="${fn:split(item.metaTag, '|')}" var="tag" varStatus="status">
				                              <span class="label basic bcBlue">${tag}</span>
				                         </c:forEach>
			                         </c:if>
                                </div> 
	                            <div class="title">${item.crsCreNm }</div> 
	                            <ul class="grade_info">
	                                <li><span>진도</span>${prgrScore}%</li>
	                                <li><span>시험</span>${examScore}점</li>
	                                <li><span>과제</span>${asmtScore}점</li>
	                                <c:if test="${item.etcNm ne ''}">
	                                	<li><span>${item.etcNm }</span>${etcScore}점</li>
	                                </c:if>
	                                 <c:if test="${item.etcNm2 ne ''}">
	                                	<li><span>${item.etcNm2 }</span>${etcScore2}점</li>
	                                </c:if>
	                                 <c:if test="${item.etcNm3 ne ''}">
	                                	<li><span>${item.etcNm3 }</span>${etcScore3}점</li>
	                                </c:if>
	                                 <c:if test="${item.etcNm4 ne ''}">
	                                	<li><span>${item.etcNm4 }</span>${etcScore4}점</li>
	                                </c:if>
	                                 <c:if test="${item.etcNm5 ne ''}">
	                                	<li><span>${item.etcNm5 }</span>${etcScore5}점</li>
	                                </c:if>
	                                
	                                <li class="total"><span>총점</span>${item.totScore }점</li>
	                                <li class="cer_info">
	                                	<span>수료여부</span>
                                		<c:if test="${item.enrlSts eq 'C'}">수료</c:if>
										<c:if test="${item.enrlSts ne 'C'}">미수료</c:if>
	                                </li>
	                            </ul>
	                        </div>
	                        <div class="bottom_button">
										<button type="button" class="go<c:if test="${item.enrlSts ne 'C'}"> disabled</c:if>" onclick="javascript:printCerti('${item.crsCreCd}','${item.stdNo}');">수료증 보기</button>
	                        </div>                                        
	                    </div>
	                </li>
	             </c:forEach>
            </ul>
        </div><!--//myCourseList-->

        <div class="board_pager">
           <span class="inner">
               <meditag:paging pageInfo="${pageInfo}" funcName="listEndScore" name="front"/>
           </span>
        </div>
        <!-- //board_pager -->
           

    </div>
    <!-- //content -->			

