<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="courseCategoryList" value="${courseCategoryList}"/>

								<table summary="<spring:message code="org.title.orgmenu.manage"/>" class="table table-bordered wordbreak">
									<colgroup>
										<col style="width:auto"/>
										<col style="width:100px"/>
										<col style="width:120px"/>
										<col style="width:80px"/>
									</colgroup>
									<thead>
										<tr>
											<th scope="col"><spring:message code="course.title.course.category"/></th>
											<th scope="col"><spring:message code="common.title.useyn"/></th>
											<th scope="col"><spring:message code="course.title.category.write"/></th>
											<th scope="col"><spring:message code="common.title.edit"/></th>
										</tr>
									</thead>
									<tbody>
									<c:forEach var="item" items="${courseCategoryList}" varStatus="status">
										<c:set var="marginleft" value="0" />
										<c:if test="${item.crsCtgrLvl == 1}"><c:set var="marginleft" value="15" /></c:if>
										<c:if test="${item.crsCtgrLvl == 2}"><c:set var="marginleft" value="30" /></c:if>
										<c:if test="${item.crsCtgrLvl == 3}"><c:set var="marginleft" value="45" /></c:if>
										<c:if test="${item.crsCtgrLvl == 4}"><c:set var="marginleft" value="60" /></c:if>
										<c:set var="ctgr_icon" value="fa fa-file fa-fw" />
										<c:if test="${item.subCnt > 0}"><c:set var="ctgr_icon" value="fa fa-folder-open fa-fw" /></c:if>
										<tr>
											<td style="padding-left:${marginleft}px;">
												<i class="${ctgr_icon}"></i> ${item.crsCtgrNm}
												<a href="javascript:ctgrEdit('${item.crsCtgrCd}')" class="" ></a>
											</td>
											<td class="text-center">
												<c:if test="${item.useYn eq 'Y'}"><spring:message code="common.title.status.useyn_y"/></c:if>
												<c:if test="${item.useYn eq 'N'}"><spring:message code="common.title.status.useyn_n"/></c:if>
											</td>
											<td class="text-center">
												<c:if test="${item.crsCtgrLvl < 3}">
												<a href="javascript:subCtgrWrite('${item.crsCtgrCd}')" class="btn btn-primary btn-sm"><spring:message code="button.write"/></a>
												</c:if>
												<c:if test="${item.crsCtgrLvl >= 3}">
												-
												</c:if>
											</td>
											<td class="text-center">
												<a href="javascript:ctgrEdit('${item.crsCtgrCd}')" class="btn btn-primary btn-sm"><spring:message code="button.edit"/></a>
											</td>
										</tr>
									</c:forEach>
									<c:if test="${empty courseCategoryList}">
										<tr>
											<td colspan="4"><spring:message code="system.message.menu.nodata"/></td>
										</tr>
									</c:if>
									</tbody>
								</table>
