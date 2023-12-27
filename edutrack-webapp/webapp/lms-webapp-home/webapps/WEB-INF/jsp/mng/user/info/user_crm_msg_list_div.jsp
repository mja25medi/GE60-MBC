<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<style>
	.custom-table th {background-color: #eee;}
</style>
										<table summary="메시지 전송 내역" class="table table-bordered wordbreak custom-table" style="margin-top: 10px;">
											<colgroup>
												<col style="width:10%"/>
												<col style="width:auto"/>
												<col style="width:20%"/>
												<col style="width:15%"/>
												<col style="width:15%"/>
											</colgroup>
											<thead>
												<tr>
													<th scope="col"><spring:message code="common.title.no"/> </th>
													<th scope="col">제목</th>
													<th scope="col">보낸사람</th>
													<th scope="col">보낸시간</th>													
													<th scope="col">전송상태</th>
												</tr>
											</thead>
											<tbody>
												<c:if test="${not empty userMsgList }">
													<c:forEach var="item" items="${userMsgList }" varStatus="status">
														<tr class="text-center">
															<td>${pageInfo.totalRecordCount - ((pageInfo.currentPageNo- 1) * pageInfo.recordCountPerPage) - status.index }</td>			
															<td class="text-left"><a href="javascript:showCts('${status.count }')">${item.title }</a></td>
															<td>
															<c:choose>
																<c:when test="${msgVO.msgDivCd eq 'MSG'}">
																	${item.sendNm }(${item.sendId })
																</c:when>
																<c:otherwise>
																	${item.sendNm }(${item.sendAddr })
																</c:otherwise>
															</c:choose>
															</td>
															<td>
																<c:choose>
																	<c:when test="${msgVO.msgDivCd eq 'MSG'}">
																		<meditag:dateformat type="0" delimeter="." property="${item.regDttm }" />
																	</c:when>
																	<c:otherwise>
																		<meditag:dateformat type="0" delimeter="." property="${item.transDttm }" />															
																	</c:otherwise>
																</c:choose>															
															</td>
															<td>
																<c:choose>
																	<c:when test="${msgVO.msgDivCd eq 'MSG'}">
																		성공
																	</c:when>
																	<c:otherwise>
																		<c:if test="${item.transSts eq 'S' }">성공</c:if>
										                            	<c:if test="${item.transSts eq 'F' }">실패</c:if>
										                            	<c:if test="${item.transSts eq 'R' }">대기</c:if>
										                            	<c:if test="${item.transSts eq 'D' }">수신거부</c:if>																
																	</c:otherwise>
																</c:choose>															
															</td>
														</tr>
														<tr class="hide" name="sub_${status.count }"><td colspan="5">${item.cts }</td></tr>
													</c:forEach>
												</c:if>
												<c:if test="${empty userMsgList }">
													<tr>
														<td colspan="5">내역이 존재하지 않습니다.</td>
													</tr>
												</c:if>
											</tbody>
										</table>

										<meditag:paging pageInfo="${pageInfo}" funcName="listMsg"/>