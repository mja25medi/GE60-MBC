<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="subjectCategoryList" value="${subjectCategoryList}"/>

								<table summary="<spring:message code="org.title.orgmenu.manage"/>" class="table table-bordered wordbreak">
									<colgroup>
										<col style="width:auto"/>
										<col style="width:100px"/>
										<col style="width:120px"/>
									</colgroup>
									<thead>
										<tr>
											<th scope="col"><spring:message code="course.title.subject.category"/></th>
											<th scope="col"><spring:message code="common.title.useyn"/></th>
											<th scope="col"><spring:message code="course.title.subject.category.write_under"/></th>
										</tr>
									</thead>
									<tbody>
									<c:forEach var="item" items="${subjectCategoryList}" varStatus="status">
										<c:set var="marginleft" value="0" />
										<c:if test="${item.ctgrLvl == 1}"><c:set var="marginleft" value="15" /></c:if>
										<c:if test="${item.ctgrLvl == 2}"><c:set var="marginleft" value="30" /></c:if>
										<c:if test="${item.ctgrLvl == 3}"><c:set var="marginleft" value="45" /></c:if>
										<c:if test="${item.ctgrLvl == 4}"><c:set var="marginleft" value="60" /></c:if>
										<c:set var="ctgr_icon" value="fa fa-file fa-fw" />
										<c:if test="${item.subCnt > 0}"><c:set var="ctgr_icon" value="fa fa-folder-open fa-fw" /></c:if>
										<tr>
											<td style="padding-left:${marginleft}px;">
												<i class="${ctgr_icon}"></i> ${item.sbjCtgrNm}
												<a href="javascript:ctgrEdit('${item.sbjCtgrCd}')" class=""><i class="fa fa-cog"></i></a>
											</td>
											<td class="text-center">
												<c:if test="${item.useYn eq 'Y'}"><spring:message code="common.title.status.useyn_y"/></c:if>
												<c:if test="${item.useYn eq 'N'}"><spring:message code="common.title.status.useyn_n"/></c:if>
											</td>
											<td class="text-center">
												<c:if test="${item.ctgrLvl < 3}">
												<a href="javascript:subCtgrWrite('${item.sbjCtgrCd}')" class="btn btn-primary btn-sm"><spring:message code="button.write"/></a>
												</c:if>
												<c:if test="${item.ctgrLvl >= 3}">
												-
												</c:if>
											</td>
										</tr>
									</c:forEach>
									<c:if test="${empty subjectCategoryList}">
										<tr>
											<td colspan="4"><spring:message code="system.message.menu.nodata"/></td>
										</tr>
									</c:if>
									</tbody>
								</table>
