<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="cretermList" value="${cretermList}"/>
<c:set var="pageInfo" value="${pageInfo}"/>
<c:set var="vo" value="${vo}"/>

						<table summary="<spring:message code="course.title.course.manage"/>" class="table table-bordered wordbreak">
							<colgroup>
								<col style="width:60px"/>
								<col style="width:auto;min-width:90px;"/>
								<col style="width:auto"/>
								<col style="width:auto;min-width:90px;"/>
								<col style="width:auto;min-width:110px;"/>
								<col style="width:auto;min-width:110px;"/>
								<col style="width:auto;min-width:110px;"/>
								<col style="width:75px"/>
							</colgroup>
							<thead>
								<tr>
									<th scope="col">순서</th>
									<th scope="col"><spring:message code="course.title.term.code"/></th>
									<th scope="col"><spring:message code="course.title.term.name"/></th>
									<th scope="col"><spring:message code="course.title.term.status"/></th>
									<th scope="col"><spring:message code="course.title.createterm.aplc.date"/></th>
									<th scope="col"><spring:message code="course.title.createterm.score.date"/></th>
									<th scope="col"><spring:message code="course.title.createterm.enrl.date"/></th>
									<th scope="col"><spring:message code="common.title.manage"/></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${cretermList}" var="item" varStatus="status">
								<tr>
									<td class="text-center">${status.count}</td>
									<td class="text-center">${item.crsTermCd}</td>
									<td class="text-center">
									<a href="javascript:editCretermForm('${item.crsTermCd}')" >${item.creYear}</a>
									</td>
									<td class="text-center">현재상태</td>
									<td class="text-center">
									<c:if test="${item.enrlAplcStartDttm ne ''}"><meditag:dateformat type="1" delimeter="." property="${item.enrlAplcStartDttm}"/>~<meditag:dateformat type="1" delimeter="." property="${item.enrlAplcEndDttm}"/></c:if>
									</td>
									<td class="text-center">
									<c:if test="${item.scoreStartDttm ne ''}"><meditag:dateformat type="1" delimeter="." property="${item.scoreStartDttm}"/>~<meditag:dateformat type="1" delimeter="." property="${item.scoreEndDttm}"/></c:if>
									</td>
									<td class="text-center">
									<c:if test="${item.enrlStartDttm ne ''}"><meditag:dateformat type="1" delimeter="." property="${item.enrlStartDttm}"/>~<meditag:dateformat type="1" delimeter="." property="${item.enrlEndDttm}"/></c:if>
									</td>
									<td class="text-center">
										<button class="btn btn-info btn-sm" onclick="javascript:cretermInfoForm('${item.crsTermCd}');"><spring:message code="button.manage"/></button>
									</td>
								</tr>
								</c:forEach>
								<c:if test="${empty cretermList}">
								<tr>
									<td colspan="8"><spring:message code="common.message.nodata"/></td>
								</tr>
								</c:if>
							</tbody>
						</table>
