<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/page_init.jsp" %>
						<table summary="data" class="table table-bordered wordbreak">
							<colgroup>
								<col style="width:auto"/>
								<col style="width:11%"/>
								<col style="width:6%"/>
								<col style="width:6%"/>
								<col style="width:6%"/>
								<col style="width:6%"/>
								<col style="width:6%"/>
								<col style="width:6%"/>
								<col style="width:6%"/>
<!-- 								<col style="width:6%"/> -->
<!-- 								<col style="width:6%"/> -->
							</colgroup>
							<thead>
								<tr>
									<th scope='col' rowspan="2"><spring:message code="log.title.know.name"/></th>
									<th scope='col' rowspan="2"><spring:message code="log.title.data.type"/></th>
									<th scope='col' colspan="5"><spring:message code="log.title.social.share.cnt" /></th>
									<th scope='col' rowspan="2"><spring:message code="log.title.view.cnt"/></th>
									<th scope='col' rowspan="2"><spring:message code="log.title.bookmark"/></th>
								</tr>
								<tr>
									<th scope='col'><spring:message code="log.title.sum"/></th>
									<th scope='col'>Facebook</th>
									<th scope='col'>Twitter</th>
									<th scope='col'>Google+</th>
<!-- 									<th scope='col'>Kakao</th> -->
<!-- 									<th scope='col'>Line</th> -->
									<th scope='col'>Band</th>
								</tr>
							</thead>
							<tbody>
							<c:set var="num" value="${vo.listScale * (vo.pageIndex-1)}"/>
							<c:forEach items="${knowShareList}" var="item" varStatus="status">
								<tr>
									<td class="text-center">${item.knowNm}</td>
									<td class="text-center"><meditag:codename code="${item.knowType}" category="KNOW_TYPE" /></td>
									<td class="text-center">${item.snsTotCnt}</td>
									<td class="text-center">${item.sns1Cnt}</td>
									<td class="text-center">${item.sns2Cnt}</td>
									<td class="text-center">${item.sns3Cnt}</td>
<%-- 									<td class="text-center">${item.sns4Cnt}</td> --%>
<%-- 									<td class="text-center">${item.sns5Cnt}</td> --%>
									<td class="text-center">${item.sns6Cnt}</td>
									<td class="text-center">${item.hits}</td>
									<td class="text-center">${item.bookmarkCnt}</td>
								</tr>
							</c:forEach>
							<c:if test="${empty knowShareList}">
								<tr>
									<td colspan="9"><spring:message code="common.message.nodata"/></td>
								</tr>
							</c:if>
							</tbody>
						</table>
						<meditag:paging pageInfo="${pageInfo}" funcName="listPageing"/>
