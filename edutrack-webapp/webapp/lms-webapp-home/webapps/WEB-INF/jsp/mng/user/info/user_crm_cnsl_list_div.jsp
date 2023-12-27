<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<style>
	.custom-table th {background-color: #eee;}
</style>
										<table summary="유저 상담 내역" class="table table-bordered wordbreak custom-table" style="margin-top: 10px;">
											<colgroup>
												<col style="width:10%"/>
												<col style="width:auto"/>
												<col style="width:10%"/>
												<col style="width:25%"/>
												<col style="width:8%"/>
												<col style="width:15%"/>
											</colgroup>
											<thead>
												<tr>
													<th scope="col"><spring:message code="common.title.no"/> </th>
													<th scope="col">제목</th>	
													<th scope="col">게시판</th>
													<th scope="col">상담정보</th>
													<th scope="col">상태</th>
													<th scope="col">등록날짜</th>
												</tr>
											</thead>
											<tbody>
												<c:if test="${not empty userCnslList }">
													<c:forEach var="item" items="${userCnslList }" varStatus="status">
														<tr class="text-center">
															<td>${pageInfo.totalRecordCount - ((pageInfo.currentPageNo- 1) * pageInfo.recordCountPerPage) - status.index }</td>			
															<td class="text-left"><a href="javascript:showCts('${status.count }')">${item.title }</a></td>
															<td>
																<c:if test="${item.cnslCtgr eq 'QNA' }">1대1 문의</c:if>
																<c:if test="${item.cnslCtgr eq 'OBJT' }">이의제기</c:if>
																<c:if test="${item.cnslCtgr eq 'LECQNA' }">강의 질의응답</c:if>
															</td>
															<td>${item.cnslInfo }</td>
															<td>
																<c:choose>
																	<c:when test="${not empty item.ansrList }">답변완료</c:when>
																	<c:otherwise>답변대기</c:otherwise>
																</c:choose>
															</td>
															<td><meditag:dateformat type="0" delimeter="." property="${item.qstnDttm }" /></td>
														</tr>
														<tr class="hide" name="sub_${status.count }"><td colspan="6">${item.qstnCts }</td></tr>
														<c:forEach var="subItem" items="${item.ansrList }">
															<tr class="hide" name="sub_${status.count }">
																<th>${subItem.ansrRegNm }<br>(${subItem.regId })</th>
																<td colspan="5">${subItem.ansrCts } (<meditag:dateformat type="0" delimeter="." property="${subItem.ansrDttm}" />)</td>
															</tr>
														</c:forEach>
													</c:forEach>
												</c:if>
												<c:if test="${empty userCnslList }">
													<tr>
														<td colspan="6">내역이 존재하지 않습니다.</td>
													</tr>
												</c:if>
											</tbody>
										</table>

										<meditag:paging pageInfo="${pageInfo}" funcName="listCnsl"/>