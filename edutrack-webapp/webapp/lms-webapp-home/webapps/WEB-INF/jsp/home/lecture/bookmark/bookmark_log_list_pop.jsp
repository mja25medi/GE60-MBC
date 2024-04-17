<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
						<div class="res_tbl_wrap">
							<table>
	                            <thead>
	                                <tr>
	                                    <th scope="col">과정명</th>
	                                    <th scope="col" width="20%">회차명</th>
	                                    <th scope="col">학습일시</th>
	                                    <th scope="col" width="15%">접속기기</th>
	                                    <th scope="col" width="15%">브라우저</th>
	                                </tr>
	                            </thead>
	                            <tbody>
	                            	<c:if test="${not empty unitLogList }">
										<c:forEach var="item" items="${unitLogList }">
												 <tr>
				                                    <td class="title" data-label="과정명">${item.crsCreNm }</td>
				                                    <td data-label="회차명">${item.unitNm }</td>
				                                    <td data-label="학습일시">
				                                    	<fmt:parseDate var="logDate" value="${item.loginDttm}"  pattern="yyyyMMddHHmmss"/>
														<fmt:formatDate value="${logDate}" pattern="yyyy년 MM월 dd일 HH시 mm분 ss초"/>
													</td>
				                                    <td data-label="접속기기">${item.deviceType }</td>
				                                    <td data-label="브라우저">${item.browserType }</td>
													
												 </tr>
											</c:forEach>
										</c:if>
									<c:if test="${empty unitLogList }">
										<tr>
											<td colspan="5">수강 기록이 없습니다.	</td>
										</tr>
									</c:if>
	                            </tbody>
	                          </table>
	                      </div>
	                    