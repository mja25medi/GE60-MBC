<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
						<table summary="<spring:message code="prdt.title.prjt.info.prjt.list"/>" class="table table-bordered">
							<colgroup>
								<col style="width:60px"/>
								<col style="width:auto"/>
								<col style="width:auto"/>
								<col style="width:80px"/>
								<col style="width:100px"/>
								<col style="width:80px"/>
								<col style="width:80px"/>
							</colgroup>
							<thead>
								<tr>
									<th scope="col"><spring:message code="common.title.no"/></th>
									<th scope="col"><spring:message code="prdt.title.prjt.info.prjt.name"/> </th>
									<th scope="col"><spring:message code="prdt.title.prjt.info.prjt.code"/></th>
									<th scope="col"><spring:message code="common.title.useyn"/></th>
									<th scope="col"><spring:message code="common.title.regdate"/></th>
									<th scope="col"><spring:message code="system.title.code.manage"/></th>
									<th scope="col"><spring:message code="common.title.manage"/></th>
								</tr>
							</thead>
							<tbody>
							<c:forEach var="item" items="${prjtInfoList}" varStatus="status">
								<tr>
									<td class="text-center">${pageInfo.totalRecordCount -(pageInfo.recordCountPerPage * (pageInfo.currentPageNo -1)) - status.count + 1}</td>
									<td>${item.prjtNm} <a href="javascript:editPrjt('${item.prjtCd}')"><i class="fa fa-cog"></i></a></td>
									<td>${item.prjtCd}</td>
									<td class="text-center"><meditag:codename code="${item.useYn}" category="USE_YN"/> </td>
									<td class="text-center"><meditag:dateformat type="1" delimeter="." property="${item.regDttm}"/></td>
									<td class="text-center">
										<a href="javascript:manageCode('${item.prjtCd}')" class="btn btn-xs btn-default"><i class="fa fa-cog"></i> <spring:message code="system.title.code.manage"/></a>
									</td>
									<td class="text-center">
										<a href="" class="btn btn-xs btn-default"><i class="fa fa-cog"></i> <spring:message code="button.manage"/></a>
									</td>
								</tr>
							</c:forEach>
							<c:if test="${empty prjtInfoList}">
								<tr>
									<td colspan="6"><spring:message code="common.message.nodata"/></td>
								</tr>
							</c:if>
							</tbody>
						</table>
						<meditag:paging pageInfo="${pageInfo}" funcName="listPageing"/>