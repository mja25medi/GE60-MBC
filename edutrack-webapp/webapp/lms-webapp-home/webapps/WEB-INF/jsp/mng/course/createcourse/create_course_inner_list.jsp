<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>

				<table summary="<spring:message code="course.title.createcourse.manage"/>"  class="table table-bordered wordbreak">
					<colgroup>
						<col style="width:50px"/>
						<col style="width:100px"/>
						<col style="width:auto"/>
						<col style="width:auto"/>
						<!-- 
						<col style="width:auto"/>
						<col style="width:auto"/>
						 -->
						<col style="width:200px;"/>
					</colgroup>
					<thead>
						<tr>
							<th scope="col"><spring:message code="common.title.no"/></th>
							<th scope="col">구분</th>
							<th scope="col">기업명</th>
							<th scope="col">과정명</th>
							<!-- 
							<th scope="col">결제금액</th>
							<th scope="col">모집인원</th>
							 -->
							<th scope="col"><spring:message code="common.title.manage"/></th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${not empty createCourseList }">
								<c:forEach items="${createCourseList }" var="item" varStatus="status">
									<tr>
										<td class="text-right">${status.count}</td>
										<td class="text-center">
											<c:if test="${item.creTypeCd eq 'ON' }">온라인</c:if>
											<c:if test="${item.creTypeCd eq 'OF' }">오프라인</c:if>
										</td>
										<td class="text-center">${item.deptNm }</td>
										<td class="text-center">${item.crsCreNm }</td>
										<%-- 
										<td class="text-center" id="eduPrice_${item.crsCreCd}"><fmt:formatNumber value="${item.eduPrice }" type="number"/></td>
										<td class="text-center" id="enrlNop_${item.crsCreCd}">${item.enrlNop }</td>
										 --%>
										<td class="text-center">
											<%-- <button class="btn btn-default btn-sm" onclick="saveCreateCourse('${item.crsCreCd}',this)" id="saveBtn_${item.crsCreCd}" style="display: none;"><spring:message code="button.add"/></button>
											<button class="btn btn-default btn-sm" onclick="editCreateCourse('${item.crsCreCd}')" id="editBtn_${item.crsCreCd}"><spring:message code="button.edit"/></button> --%>
											<button class="btn btn-default btn-sm" onclick="deleteCreateCourse('${item.crsCreCd}')"><spring:message code="button.delete"/></button>
										</td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr>
									<td colspan="6">개설된 과정이 없습니다.</td>
								</tr>
							</c:otherwise>
						</c:choose>
						
					</tbody>
				</table>		