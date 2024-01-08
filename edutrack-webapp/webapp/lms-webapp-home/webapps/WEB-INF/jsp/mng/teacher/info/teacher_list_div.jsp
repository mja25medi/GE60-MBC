<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="userInfoList" value="${userInfoList}"/>
<c:set var="pageInfo" value="${pageInfo}"/>

							<table summary="<spring:message code="user.title.userinfo.manage"/>" class="table table-bordered wordbreak">
								<colgroup>
									<col style="width:30px"/>
									<col style="width:70px"/>
									<col style="width:200px"/>
									<col style="width:200px"/>
									<col style="width:auto"/>
									<col style="width:110px"/>
									<col style="width:auto"/>
									<col style="width:110px"/>
									<col style="width:50px"/>
								</colgroup>
								<thead>
									<tr>
										<th scope="col"><input type="checkbox" name="chkAll" id="chkAll" value="N" style="border:0" title="check all" onclick="checkAll()"/></th>
										<th scope="col"><spring:message code="common.title.no"/></th>
										<th scope="col"><spring:message code="user.title.userinfo.name"/></th>
										<th scope="col"><spring:message code="user.title.userinfo.userid"/></th>
										<th scope="col"><spring:message code="teacher.title.teacherinfo.tchctgrcd"/></th>
										<th scope="col"><spring:message code="teacher.title.teacherinfo.tchdivcd"/></th>
										<th scope="col">IDE URL</th>
										<th scope="col"><spring:message code="common.title.regdate"/></th>
										<th scope="col"><spring:message code="common.title.edit"/></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${userInfoList}" var="item" varStatus="status">
									<tr>
										<td><input type='checkbox' name='sel' id='sel' value='${item.userNo}' style='border:0' title='<spring:message code="common.title.select"/>'></td>
										<td class="text-right">${pageInfo.totalRecordCount - ((pageInfo.currentPageNo- 1) * pageInfo.recordCountPerPage) - status.index }</td>
										<td><a href="#_none" onclick="tchInfo('${item.userNo}')">${item.userNm}</a></td>
										<td>${item.userId}</td>
										<td><meditag:codename code="${item.tchCtgrCd}" category="TCH_CTGR_CD" /></td>
										<td><meditag:codename code="${item.tchDivCd}" category="TCH_DIV_CD" /></td>
										<td>${item.ideUrl}</td>
										<td class="text-center"><meditag:dateformat type="1" delimeter="." property="${item.regDttm}"/></td>
										<td class="text-center">
											<a href="javascript:tchEdit('${item.userNo}');" class="btn btn-primary btn-sm"><spring:message code="button.edit"/> </a>
										</td>
									</tr>
									</c:forEach>
									<c:if test="${empty userInfoList}">
									<tr>
										<td colspan="9"><spring:message code="user.message.userinfo.nodata"/></td>
									</tr>
									</c:if>
								</tbody>
							</table>
							<meditag:paging pageInfo="${pageInfo}" funcName="listPageing"/>