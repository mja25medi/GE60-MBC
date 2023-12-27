<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="courseList" value="${courseList}"/>
<c:set var="pageInfo" value="${pageInfo}"/>
<c:set var="courseVO" value="${courseVO}"/>

						<form name="crsPlanAllAddForm" method="post" action="/mng/course/coursePlan" onsubmit="return false" id="crsPlanAllAddForm">
						<input type="hidden" name="listSize" id="listSize" />
						<table summary="<spring:message code="course.title.course.manage"/>" style="width:100%" class="table table-bordered wordbreak">
							<colgroup>
								<col style="width:55px"/>
								<col style="width:auto;min-width:90px;"/>
								<col style="width:auto"/>
								<col style="width:auto;min-width:90px;"/>
								<col style="width:auto;min-width:110px;"/>
								<col style="width:90px"/>
								<col style="width:90px"/>
								<col style="width:90px"/>
								<col style="width:56px"/>
							</colgroup>
							<thead>
								<tr>
									<th scope="col"><spring:message code="common.title.year"/></th>
									<th scope="col">
									<c:choose>
										<c:when test="${fn:startsWith(courseVO.sortKey,'CRS_CTGR_NM') == true}">
											<c:if test="${courseVO.sortKey eq 'CRS_CTGR_NM_ASC'}">
										<a href="javascript:setSortKey('CRS_CTGR_NM_DESC')"><spring:message code="course.title.course.category"/> <i class="fa fa-sort-asc"></i></a>
											</c:if>
											<c:if test="${courseVO.sortKey eq 'CRS_CTGR_NM_DESC'}">
										<a href="javascript:setSortKey('CRS_CTGR_NM_ASC')"><spring:message code="course.title.course.category"/> <i class="fa fa-sort-desc"></i></a>
											</c:if>
										</c:when>
										<c:otherwise>
										<a href="javascript:setSortKey('CRS_CTGR_NM_ASC')"><spring:message code="course.title.course.category"/> <i class="fa fa-sort" style="color:#ddd"></i></a>
										</c:otherwise>
									</c:choose>
									</th>
									<th scope="col">
									<c:choose>
										<c:when test="${fn:startsWith(courseVO.sortKey,'CRS_NM') == true}">
											<c:if test="${courseVO.sortKey eq 'CRS_NM_ASC'}">
										<a href="javascript:setSortKey('CRS_NM_DESC')"><spring:message code="course.title.course.name"/> <i class="fa fa-sort-asc"></i></a>
											</c:if>
											<c:if test="${courseVO.sortKey eq 'CRS_NM_DESC'}">
										<a href="javascript:setSortKey('CRS_NM_ASC')"><spring:message code="course.title.course.name"/> <i class="fa fa-sort-desc"></i></a>
											</c:if>
										</c:when>
										<c:otherwise>
										<a href="javascript:setSortKey('CRS_NM_ASC')"><spring:message code="course.title.course.name"/> <i class="fa fa-sort" style="color:#ddd"></i></a>
										</c:otherwise>
									</c:choose>
									</th>
									<th scope="col"><spring:message code="course.title.course.edumthd"/></th>
									<th scope="col"><spring:message code="course.title.course.crstype"/></th>
									<th scope="col"><spring:message code="course.title.createcourse.educnt"/></th>
									<th scope="col"><spring:message code="course.title.plan.personnel.term"/></th>
									<th scope="col"><spring:message code="course.title.plan.personnel.year"/></th>
									<th scope="col"><spring:message code="common.title.add"/></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${courseList}" var="item" varStatus="status">
								<tr>
									<td>${creYear}</td>
									<td class="wordbreak">${item.crsCtgrNm}</td>
									<td class="wordbreak">${item.crsNm}</td>
									<td class="text-center"><meditag:codename code="${item.crsOperMthd}" category="CRS_OPER_MTHD" /></td>
									<td class="text-center"><meditag:codename code="${item.crsOperType}" category="CRS_OPER_TYPE" /></td>
									<td class="text-center"><input type="text" style="text-align:right;" class="inputNumber inputSpecial form-control input-sm creCntClass" value="${item.creCnt}" name="creCnt_${item.crsCd}" id="creCnt_${item.crsCd}" onfocus="this.select()" onblur="checkInteger(this)"/></td>
									<td class="text-center"><input type="text" style="text-align:right;" class="inputNumber inputSpecial form-control input-sm termNopCntClass" value="${item.termNopCnt}" name="termNopCnt_${item.crsCd}" id="termNopCnt_${item.crsCd}" onfocus="this.select()" onblur="checkInteger(this)"/></td>
									<td class="text-center"><input type="text" style="text-align:right;" class="inputNumber inputSpecial form-control input-sm yearNopCntClass" value="${item.yearNopCnt}" name="yearNopCnt_${item.crsCd}" id="yearNopCnt_${item.crsCd}" onfocus="this.select()" onblur="checkInteger(this)"/></td>
									<td class="text-center">
										<button class="btn btn-primary btn-sm" onclick="javascript:addPlan('${item.crsCd}');" ><spring:message code="button.add"/></button>
									</td>
									<input type="hidden" class="creCdClass" name="creCd_${item.crsCd}" value="${item.crsCd}" />
									<input type="hidden" class="creYearClass" name="creYear_${item.crsCd}" value="" />
								</tr>
								</c:forEach>
								<c:if test="${empty courseList}">
								<tr>
									<td colspan="9"><spring:message code="course.message.course.nodata"/></td>
								</tr>
								</c:if>
							</tbody>
						</table>
						</form>
						<meditag:paging pageInfo="${pageInfo}" funcName="listCourse"/>
						<script type="text/javascript">
							$(document).ready(function() {
								$('.inputNumber').inputNumber();	// 숫자만 입력되도록 설정.
								$('.inputSpecial').inputSpecial();  // 특수키가 입력이 들어오묜 false return;
							});
						</script>
