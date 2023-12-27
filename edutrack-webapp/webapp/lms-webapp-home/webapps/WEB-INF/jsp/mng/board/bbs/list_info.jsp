<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
										<table summary="<spring:message code="board.title.bbs.info.list"/>" style="width:100%" class="table table-bordered wordbreak">
											<colgroup>
												<col style="width:auto;"/>
												<col style="width:auto;"/>
												<col style="width:120px"/>
												<col style="width:80px"/>
												<col style="width:80px"/>
												<col style="width:80px"/>
												<col style="width:80px"/>
												<col style="width:80px"/>
												<col style="width:90px"/>
												<col style="width:80px"/>
											</colgroup>
											<thead>
											<tr>
												<th scope="col"><spring:message code="board.title.bbs.info.bbscd"/></th>
												<th scope="col"><spring:message code="board.title.bbs.info.bbsnm"/></th>
												<th scope="col"><spring:message code="board.title.bbs.info.listtype"/></th>
												<th scope="col"><spring:message code="board.title.bbs.info.reply"/></th>
												<th scope="col"><spring:message code="board.title.bbs.info.comment"/></th>
												<th scope="col"><spring:message code="board.title.bbs.info.atache"/></th>
												<th scope="col"><spring:message code="common.title.useyn"/></th>
												<th scope="col"><spring:message code="board.title.bbs.info.atcl.cnt"/></th>
												<th scope="col"><spring:message code="board.title.bbs.info.atcl.new"/></th>
												<th scope="col"><spring:message code="board.title.bbs.info.head"/></th>
											</tr>
											</thead>
											<tbody>
											<c:forEach var="item" items="${bbsInfoList}" varStatus="status">
												<c:url var="atclUrl" value="/mng/brd/bbs/listAtclMain?bbsCd=${item.bbsCd}"/>
												<tr>
													<td><a href="${atclUrl}">${item.bbsCd}</a></td>
													<td class="left">
														<a href="${atclUrl}">${item.bbsNm}</a>
														<a href="javascript:editInfo('${item.orgCd}','${item.bbsCd}');" ><i class="fa fa-cog"></i></a>
													</td>
													<td class="text-center"><meditag:codename code="${item.bbsTypeCd}" category="BBS_TYPE_CD" /></td>
													<td class="text-center"><meditag:codename code="${item.ansrUseYn}" category="USE_YN" /></td>
													<td class="text-center"><meditag:codename code="${item.cmntUseYn}" category="USE_YN" /></td>
													<td class="text-center"><meditag:codename code="${item.atchUseYn}" category="USE_YN" /></td>
													<td class="text-center"><meditag:codename code="${item.useYn}" category="USE_YN" /></td>
													<td class="text-right">${item.atclCnt}</td>
													<td class="text-center">${item.isNewAtcl}</td>
													<td class="text-center">
														<button class="btn btn-info btn-sm" onclick="javascript:mngHead('${item.orgCd}','${item.bbsCd}');" ><spring:message code="button.manage" /></button>
													</td>
												</tr>
											</c:forEach>
											<c:if test="${empty bbsInfoList}">
												<tr>
													<td colspan="8"><spring:message code="board.message.bbs.info.nodata"/></td>
												</tr>
											</c:if>
											</tbody>
										</table>
										<meditag:paging pageInfo="${pageInfo}" funcName="listInfo"/>