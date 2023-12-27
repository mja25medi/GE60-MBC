<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="examQbankQstnList" value="${examQbankQstnList}"/>
<c:set var="pageInfo" value="${pageInfo}"/>

										<table summary="<spring:message code="lecture.title.exam.question.manage"/>" class="table table-bordered wordbreak">
											<colgroup>
												<col style="width:50px;">
												<col style="width:auto;">
												<col style="width:90px;">
												<col style="width:50px;">
											</colgroup>
											<thead>
												<tr>
													<th scope="col"><spring:message code="common.title.no"/></th>
													<th scope="col"><spring:message code="lecture.title.exam.question"/></th>
													<th scope="col"><spring:message code="lecture.title.exam.question.type"/></th>
													<th scope="col"><spring:message code="common.title.manage"/></th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="item" items="${examQbankQstnList}" varStatus="status">
												<tr>
													<td class="text-right">${status.count}</td>
													<td class="wordbreak"><a href="javascript:examBankPreview('${item.sbjCd}','${item.ctgrCd}','${item.qstnSn}');">${item.title}</a></td>
													<td class="text-center"><meditag:codename category="EXAM_QSTN_TYPE" code="${item.qstnType}"/></td>
													<td class="text-center">
														<a href="javascript:editQuestion('${item.qstnSn}');" class="btn btn-primary btn-sm"><spring:message code="button.edit"/> </a>
													</td>
												</tr>
												</c:forEach>
												<c:if test="${empty examQbankQstnList}">
												<tr>
													<td colspan="4"><spring:message code="lecture.message.exam.question.nodata"/></td>
												</tr>
												</c:if>
											</tbody>
										</table>
										<%--
										<meditag:paging pageInfo="${pageInfo}" funcName="listQuestion"/>
										--%>