<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="olcShareRelList" value="${olcShareRelList}"/>
<c:set var="pageInfo" value="${pageInfo}"/>

						<table summary='<spring:message code="olc.title.main.manage"/>' class="table table-striped table-bordered">
							<colgroup>
								<col style="width:50px"/>
								<col style="width:auto"/>
								<col style="width:auto"/>
								<col style="width:80px"/>
								<col style="width:100px"/>
								<col style="width:75px"/>
							</colgroup>
							<thead>
								<tr>
									<th scope="col"><spring:message code="common.title.no"/></th>
									<th scope="col"><spring:message code="olc.title.category.name"/></th>
									<th scope="col"><spring:message code="olc.title.cartridge.name"/></th>
									<th scope="col"><spring:message code="olc.title.cartridge.contents.cnt"/></th>
									<th scope="col"><spring:message code="common.title.reguser"/></th>
									<th scope="col"><spring:message code="common.title.manage"/></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${olcShareRelList}" var="item" varStatus="status">
								<tr>
									<td class="text-right">${status.count}</td>
									<td class="wordbreak">${item.ctgrNm}</td>
									<td class="wordbreak">${item.cartrgNm} <a href="javascript:preview('${item.cartrgCd}', '${item.cartrgNm}');"><i class="glyphicon glyphicon-eye-open"></i></a></td>
									<td class="text-right">${item.cntsCnt }</td>
									<td>${item.userNm}</td>
									<td class="text-center">
										<a href="javascript:olcCtgrEdit('${item.cartrgCd}')" class="btn btn-primary btn-sm"><spring:message code="common.title.manage"/></a>
									</td>
								</tr>
								</c:forEach>
								<c:if test="${empty olcShareRelList}">
								<tr>
									<td colspan="6"><spring:message code="common.message.nodata"/></td>
								</tr>
								</c:if>
							</tbody>
						</table>
						<meditag:paging pageInfo="${pageInfo}" funcName="listPageing"/>