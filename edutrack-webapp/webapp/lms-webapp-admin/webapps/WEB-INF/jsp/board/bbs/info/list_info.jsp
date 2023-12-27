<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>


						<table summary="<spring:message code="board.title.bbs.info.list"/>" style="width:100%" class="table table-bordered">
							<colgroup>
								<col style="width:auto;"/>
								<col style="width:auto;"/>
								<col style="width:auto;"/>
								<col style="width:120px"/>
								<col style="width:80px"/>
								<col style="width:80px"/>
								<col style="width:80px"/>
								<col style="width:80px"/>
								<col style="width:80px"/>
							</colgroup>
							<thead>
							<tr>
								<th scope="col"><spring:message code="org.title.orginfo.orgname"/></th>
								<th scope="col"><spring:message code="board.title.bbs.info.bbscd"/></th>
								<th scope="col"><spring:message code="board.title.bbs.info.bbsnm"/></th>
								<th scope="col"><spring:message code="board.title.bbs.info.listtype"/></th>
								<th scope="col"><spring:message code="board.title.bbs.info.reply"/></th>
								<th scope="col"><spring:message code="board.title.bbs.info.comment"/></th>
								<th scope="col"><spring:message code="board.title.bbs.info.atache"/></th>
								<th scope="col"><spring:message code="common.title.edit"/></th>
								<th scope="col"><spring:message code="board.title.bbs.info.head"/></th>
							</tr>
							</thead>
							<tbody>
							<c:forEach var="item" items="${bbsInfoList}" varStatus="status">
								<tr>
									<td class="wordbreak">${item.orgNm}</td>
									<td>${item.bbsCd}</td>
									<td class="wordbreak">${item.bbsNm}</td>
									<td class="text-center"><meditag:codename code="${item.bbsTypeCd}" category="BBS_TYPE_CD" /></td>
									<td class="text-center"><meditag:codename code="${item.ansrUseYn}" category="USE_YN" /></td>
									<td class="text-center"><meditag:codename code="${item.cmntUseYn}" category="USE_YN" /></td>
									<td class="text-center"><meditag:codename code="${item.atchUseYn}" category="USE_YN" /></td>
									<td class="text-center">
										<button class="btn btn-primary btn-sm" onclick="javascript:editInfo('${item.orgCd}','${item.bbsCd}');" ><spring:message code="button.edit" /></button>
									</td>
									<td class="text-center">
										<button class="btn btn-info btn-sm" onclick="javascript:mngHead('${item.orgCd}','${item.bbsCd}');" ><spring:message code="button.manage" /></button>
									</td>
								</tr>
							</c:forEach>
							<c:if test="${empty bbsInfoList}">
								<tr>
									<td colspan="9"><spring:message code="board.message.bbs.info.nodata"/></td>
								</tr>
							</c:if>
							</tbody>
						</table>
						<meditag:paging pageInfo="${pageInfo}" funcName="listPageing"/>