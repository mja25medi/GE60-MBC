<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>


						<table summary="<spring:message code="user.title.userinfo.manage"/>" class="table table-bordered">
							<colgroup>
								<col style="width:30px"/>
								<col style="width:50px"/>
								<col style="width:auto"/>
								<col style="width:auto"/>
								<col style="width:auto"/>
								<col style="width:auto"/>
								<col style="width:80px"/>
								<col style="width:70px"/>
								<col style="width:50px"/>
							</colgroup>
							<thead>
								<tr>
									<th scope="col"><input type="checkbox" name="chkAll" id="chkAll" value="N" style="border:0" title="check all" onclick="checkAll()"/></th>
									<th scope="col"><spring:message code="common.title.no"/> </th>
									<th scope="col"><spring:message code="user.title.userinfo.userid"/></th>
									<th scope="col"><spring:message code="user.title.userinfo.name"/></th>
									<th scope="col"><spring:message code="user.title.userinfo.mobileno"/></th>
									<th scope="col"><spring:message code="user.title.userinfo.email"/></th>
									<th scope="col"><spring:message code="common.title.regdate"/></th>
									<th scope="col"><spring:message code="user.title.userinfo.stats"/></th>
									<th scope="col"><spring:message code="common.title.edit"/></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${userList}" var="item" varStatus="status">
								<tr>
									<td class="text-center"><input type='checkbox' name='sel' id='sel' value='${item.userNo}' style='border:0' title='check'></td>
									<td class="text-right">${pageInfo.firstRecordIndex + status.count}</td>
									<td>${item.userId}</td>
									<td><a href="#_none" onclick="userInfo('${item.userNo}')">${item.userNm}</a></td>
									<td>${item.mobileNo}</td>
									<td>${item.email}</td>
									<td class="text-center"><meditag:dateformat type="1" delimeter="." property="${item.regDttm}"/></td>
									<td class="text-center"><meditag:codename code="${item.userSts}" category="USER_STS"/></td>
									<td class="text-center">
										<a class="btn btn-primary btn-sm" href="javascript:userEdit('${item.userNo}');" title="<spring:message code="button.edit"/>"><spring:message code="button.edit"/></a>
									</td>
								</tr>
								</c:forEach>
								<c:if test="${empty userList}">
								<tr>
									<td colspan="10"><spring:message code="user.message.userinfo.nodata"/></td>
								</tr>
								</c:if>
							</tbody>
						</table>
						<meditag:paging pageInfo="${pageInfo}" funcName="listPageing"/>