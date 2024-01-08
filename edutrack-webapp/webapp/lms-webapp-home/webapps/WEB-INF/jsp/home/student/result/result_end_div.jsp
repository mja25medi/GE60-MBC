<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
			
			<div class="res_tbl_wrap">
                        <table>
                            <caption>성적조회 목록</caption>
                            <thead>
                                <tr>
                                    <th scope="col" width="7%">번호</th>
                                    <th scope="col">과정명</th>
                                    <th scope="col" width="7%">진도</th>
                                    <th scope="col" width="7%">시험</th>
                                    <th scope="col" width="7%">과제</th>
                                    <th scope="col" width="7%">기타1</th>
                                    <th scope="col" width="7%">총점</th>
                                    <th scope="col" width="8%">수료여부</th>
                                    <th scope="col" width="9%">수료증</th>
                                </tr>
                            </thead>
                            <tbody>
                            	<c:forEach items="${stdScoreList}" var="item" varStatus="status">
                            		<fmt:formatNumber type="number"  value = "${item.examScore}" pattern = "0.0" var = "examScore"/>
									<fmt:formatNumber type="number"  value = "${item.semiExamScore}" pattern = "0.0" var = "semiExamScore"/>
									<fmt:formatNumber type="number"  value = "${item.asmtScore}" pattern = "0.0" var = "asmtScore"/>
									<fmt:formatNumber type="number"  value = "${item.prgrScore}" pattern = "0.0" var = "prgrScore"/>
									<fmt:formatNumber type="number"  value = "${item.etcScore}" pattern = "0.0" var = "etcScore"/>
	                                <tr>
	                                    <td scope="row" data-label="번호">${status.count }</td>
	                                    <td class="title" data-label="과정명">${item.crsCreNm }</td>
	                                    <td data-label="진도">${prgrScore} %</td>
	                                    <td data-label="시험">${examScore} 점</td>
	                                    <td data-label="과제">${asmtScore}</td>
	                                    <td data-label="기타1">${etcScore}</td>
	                                    <td data-label="총점">${item.totScore }</td>
	                                    <td data-label="수료여부">
	                                    	<c:if test="${item.enrlSts eq 'C'}">수료</c:if>
											<c:if test="${item.enrlSts ne 'C'}">미수료</c:if>
	                                    </td>
	                                    <td data-label="수료증">
			                                <c:choose>
												<c:when test="${item.enrlSts eq 'C'}">
													<button type="button" class="btn type6" onclick="javascript:printCerti('${item.crsCreCd}','${item.stdNo}');">발급</button>
												</c:when>
												<c:otherwise>-</c:otherwise>
											</c:choose>
	                                    </td>
	                                </tr>
                                </c:forEach>
                                <c:if test="${empty stdScoreList }">
                                	<tr><td colspan="9" data-title="noData">조회된 내용이 없습니다.</td></tr>
                                </c:if>
                            </tbody>
                        </table>
                    </div>
                    
                    <div class="board_pager">
                        <span class="inner">
                            <meditag:paging pageInfo="${pageInfo}" funcName="listEndScore" name="front"/>
                        </span>
                    </div>

