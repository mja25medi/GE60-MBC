<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="olcCartrgList" value="${olcCartrgList}"/>
<c:set var="olcCartrgVO" value="${olcCartrgVO}"/>
<c:set var="pageInfo" value="${pageInfo}"/>
						<table summary='<spring:message code="olc.title.main.manage"/>' class="table table-striped table-bordered">
							<colgroup>
								<col style="width:50px"/>
								<col style="width:auto"/>
								<col style="width:80px"/>
								<col style="width:90px"/>
								<col style="width:90px"/>
								<col style="width:100px"/>
								<col style="width:100px"/>
								<col style="width:75px"/>
							</colgroup>
							<thead>
								<tr>
									<th scope="col"><spring:message code="common.title.no"/></th>
									<th scope="col">
									<c:choose>
										<c:when test="${fn:startsWith(olcCartrgVO.sortKey,'CARTRG_NM') == true}">
											<c:if test="${ olcCartrgVO.sortKey eq 'CARTRG_NM_ASC'}">
												<a href="javascript:setSortKey('CARTRG_NM_DESC');"><spring:message code="olc.title.cartridge.name"/><i class="fa fa-sort-asc"></i></a>
											</c:if>
											<c:if test="${ olcCartrgVO.sortKey eq 'CARTRG_NM_DESC'}">
												<a href="javascript:setSortKey('CARTRG_NM_ASC');"><spring:message code="olc.title.cartridge.name"/><i class="fa fa-sort-desc"></i></a>
											</c:if>
										</c:when>
										<c:otherwise>
											<a href="javascript:setSortKey('CARTRG_NM_ASC');"><spring:message code="olc.title.cartridge.name"/><i class="fa fa-sort" style="color:#ddd"></i></a>
										</c:otherwise>
									</c:choose>
									</th>
									<th scope="col"><spring:message code="olc.title.cartridge.contents.cnt"/></th>
									<th scope="col">
									<c:choose>
										<c:when test="${fn:startsWith(olcCartrgVO.sortKey,'REG_NM') == true}">
											<c:if test="${ olcCartrgVO.sortKey eq 'REG_NM_ASC'}">
												<a href="javascript:setSortKey('REG_NM_DESC');"><spring:message code="common.title.reguser"/><i class="fa fa-sort-asc"></i></a>
											</c:if>
											<c:if test="${ olcCartrgVO.sortKey eq 'REG_NM_DESC'}">
												<a href="javascript:setSortKey('REG_NM_ASC');"><spring:message code="common.title.reguser"/><i class="fa fa-sort-desc"></i></a>
											</c:if>
										</c:when>
										<c:otherwise>
											<a href="javascript:setSortKey('REG_NM_ASC');"><spring:message code="common.title.reguser"/><i class="fa fa-sort" style="color:#ddd"></i></a>
										</c:otherwise>
									</c:choose>
									</th>
									<th scope="col">
									<c:choose>
										<c:when test="${fn:startsWith(olcCartrgVO.sortKey,'SHARE_APLC_DTTM') == true}">
											<c:if test="${ olcCartrgVO.sortKey eq 'SHARE_APLC_DTTM_ASC'}">
												<a href="javascript:setSortKey('SHARE_APLC_DTTM_DESC');"><spring:message code="user.title.userinfo.date.reg"/><i class="fa fa-sort-asc"></i></a>
											</c:if>
											<c:if test="${ olcCartrgVO.sortKey eq 'SHARE_APLC_DTTM_DESC'}">
												<a href="javascript:setSortKey('SHARE_APLC_DTTM_ASC');"><spring:message code="user.title.userinfo.date.reg"/><i class="fa fa-sort-desc"></i></a>
											</c:if>
										</c:when>
										<c:otherwise>
											<a href="javascript:setSortKey('SHARE_APLC_DTTM_ASC');"><spring:message code="user.title.userinfo.date.reg"/><i class="fa fa-sort" style="color:#ddd"></i></a>
										</c:otherwise>
									</c:choose>
									</th>
									<th scope="col">
									<c:choose>
										<c:when test="${fn:startsWith(olcCartrgVO.sortKey,'CNTS_SHARE_CD') == true}">
											<c:if test="${ olcCartrgVO.sortKey eq 'CNTS_SHARE_CD_ASC'}">
												<a href="javascript:setSortKey('CNTS_SHARE_CD_DESC');"><spring:message code="olc.title.contents.share"/><i class="fa fa-sort-asc"></i></a>
											</c:if>
											<c:if test="${ olcCartrgVO.sortKey eq 'CNTS_SHARE_CD_DESC'}">
												<a href="javascript:setSortKey('CNTS_SHARE_CD_ASC');"><spring:message code="olc.title.contents.share"/><i class="fa fa-sort-desc"></i></a>
											</c:if>
										</c:when>
										<c:otherwise>
											<a href="javascript:setSortKey('CNTS_SHARE_CD_ASC');"><spring:message code="olc.title.contents.share"/><i class="fa fa-sort" style="color:#ddd"></i></a>
										</c:otherwise>
									</c:choose>

									</th>
									<th scope="col">
									<c:choose>
										<c:when test="${fn:startsWith(olcCartrgVO.sortKey,'KNOW_SHARE_CD') == true}">
											<c:if test="${ olcCartrgVO.sortKey eq 'KNOW_SHARE_CD_ASC'}">
												<a href="javascript:setSortKey('CNTS_SHARE_CD_DESC');"><spring:message code="olc.title.knowledge.share"/><i class="fa fa-sort-asc"></i></a>
											</c:if>
											<c:if test="${ olcCartrgVO.sortKey eq 'KNOW_SHARE_CD_DESC'}">
												<a href="javascript:setSortKey('KNOW_SHARE_CD_ASC');"><spring:message code="olc.title.knowledge.share"/><i class="fa fa-sort-desc"></i></a>
											</c:if>
										</c:when>
										<c:otherwise>
											<a href="javascript:setSortKey('KNOW_SHARE_CD_ASC');"><spring:message code="olc.title.knowledge.share"/><i class="fa fa-sort" style="color:#ddd"></i></a>
										</c:otherwise>
									</c:choose>
									</th>
									<th scope="col"><spring:message code="common.title.manage"/></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${olcCartrgList}" var="item" varStatus="status">
								<tr>
									<td class="text-right">${status.count}</td>
									<td class="wordbreak">
										<%-- <a href="javascript:cntsManage('${item.cartrgCd}')">${item.cartrgNm}</a> --%>
										${item.cartrgNm}
										<c:if test="${item.cntsCnt > 0 }">
										<a href="javascript:preview('${item.userNo}', '${item.cartrgCd}', '${item.cartrgNm}');"><i class="glyphicon glyphicon-eye-open"></i></a>
										</c:if>
									</td>
									<td class="text-right">${item.cntsCnt}</td>
									<td class="text-right"><a href="javascript:userInfo('${item.regNo }');">${item.regNm}</td>
									<td class="text-center"><meditag:dateformat type="1" delimeter="." property="${item.shareAplcDttm}"/></td>
									<td class="text-center">
										<font color="<c:if test="${item.cntsShareCd eq '01' }">red</c:if>">
										<c:choose>
										<c:when test="${item.cntsShareCd eq '02' }">-</c:when>
										<c:otherwise><meditag:codename code="${item.cntsShareCd}" category="CNTS_SHARE_CD" /></c:otherwise>
										</c:choose>
										</font>
									</td>
									<td class="text-center">
										<font color="<c:if test="${item.knowShareCd eq '01' }">red</c:if>">
										<c:choose>
										<c:when test="${item.knowShareCd eq '02' }">-</c:when>
										<c:otherwise><meditag:codename code="${item.knowShareCd}" category="KNOW_SHARE_CD" /></c:otherwise>
										</c:choose>
										</font>
									</td>
									<td class="text-center"><a href="javascript:olcEdit('${item.cartrgCd}')" class="btn btn-info btn-sm"><spring:message code="button.manage"/></a></td>
								</tr>
								</c:forEach>
								<c:if test="${empty olcCartrgList}">
								<tr>
									<td colspan="8"><spring:message code="common.message.nodata"/></td>
								</tr>
								</c:if>
							</tbody>
						</table>
						<meditag:paging pageInfo="${pageInfo}" funcName="listPageing"/>
