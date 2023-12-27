<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
						<table class="table table-bordered wordbreak">
							<colgroup>
								<col style="width:50px"/>
								<col style="width:auto"/>
								<col style="width:150px"/>
								<col style="width:80px"/>
							</colgroup>
							<thead>
								<tr>
									<th scope='col'><spring:message code="common.title.no"/></th>
									<th scope='col'>IDE URL</th>
									<th scope='col'><spring:message code="common.title.useyn"/></th>
									<th scope='col'><spring:message code="common.title.delete"/></th>
								</tr>
							</thead>
							<tbody>
							<c:set var="num" value="${vo.listScale * (vo.pageIndex-1)}"/>
							<c:forEach items="${ideInfoList}" var="item" varStatus="status">
								<tr>
									<td class="text-center">${num + status.count}</td>
									<td class="text-left">${item.ideUrl}</td>
									<td class="text-center${status.count} ">
										<label style="font-weight:normal;">
											<input type="radio" name="useYn_${status.count}" id="useYn" value="Y" <c:if test="${item.useYn eq 'Y'}">checked</c:if> onclick="editUseYn('${item.ideUrl}','Y')" /> <spring:message code="common.title.useyn_y"/>
										</label>
										<label style="font-weight:normal; margin-left:10px;">
											<input type="radio" name="useYn_${status.count}" id="useYn" value="N" <c:if test="${item.useYn eq 'N'}">checked</c:if> onclick="editUseYn('${item.ideUrl}','N')" /> <spring:message code="common.title.useyn_n"/>
										</label>
									</td>
									<td class="text-center">
										<a href="javascript:delIde('${item.ideUrl}');" class="btn btn-warning btn-xs"><spring:message code="button.delete" /> </a>
									</td>
								</tr>
							</c:forEach>
							<c:if test="${empty ideInfoList}">
								<tr>
									<td colspan="5"><spring:message code="common.message.nodata"/></td>
								</tr>
							</c:if>
							</tbody>
						</table>
						<meditag:paging pageInfo="${pageInfo}" funcName="listPageing"/>
