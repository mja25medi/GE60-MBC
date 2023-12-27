<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/page_init.jsp" %>
						<table summary="data" class="table table-bordered wordbreak">
							<colgroup>
								<col style="width:auto"/>
								<col style="width:17%"/>
								<col style="width:17%"/>
								<col style="width:20%"/>
								<col style="width:17%"/>
							</colgroup>
							<thead>
								<tr>
									<th scope='col'><spring:message code="log.title.know.name"/></th>
									<th scope='col'><spring:message code="log.title.org.know.cnt"/></th>
									<th scope='col'><spring:message code="log.title.course.status.complete.apply" /></th>
									<th scope='col'><spring:message code="log.title.course.apply.cnt"/></th>
									<th scope='col'><spring:message code="log.title.course.status.complete.cnt"/></th>
								</tr>
							</thead>
							<tbody>
							<c:set var="num" value="${vo.listScale * (vo.pageIndex-1)}"/>
							<c:forEach items="${knowStudyCompleteList}" var="item" varStatus="status">
								<tr>
									<td class="text-center">${item.knowNm}</td>
									<td class="text-center">${item.childKnowCnt}</td>
									<td class="text-center">
									<c:choose>
										<c:when test="${item.cpltUseYn eq 'Y'}">
											<spring:message code="know.title.study.support.completeyn_y"/>
										</c:when>
										<c:otherwise>
											<spring:message code="know.title.study.support.completeyn_n"/>
										</c:otherwise>
									</c:choose>
									</td>
									<td class="text-center">${item.applyCnt}</td>
									<td class="text-center">${item.completeCnt}</td>
								</tr>
							</c:forEach>
							<c:if test="${empty knowStudyCompleteList}">
								<tr>
									<td colspan="5"><spring:message code="common.message.nodata"/></td>
								</tr>
							</c:if>
							</tbody>
						</table>
						<meditag:paging pageInfo="${pageInfo}" funcName="listPageing"/>
