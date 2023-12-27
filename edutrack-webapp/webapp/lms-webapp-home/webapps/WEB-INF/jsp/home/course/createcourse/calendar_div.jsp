<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>

				<div class="panel panel-default calendar">
					<div class="panel-heading">
						<span class="panel-title"><spring:message code="course.title.createcourse.calendar.month"/></span>
					</div>
					<div class="panel-body">
						<span class="monthPre" style="font-size:12px;"><a href="#none" onclick="calendarDiv('${prevYearMonth}')"><i class="glyphicon glyphicon-chevron-left"></i><spring:message code="common.title.pervmonth"/></a></span>
						<span class="monthNext" style="font-size:12px;"><a href="#none" onclick="calendarDiv('${nextYearMonth}')"><spring:message code="common.title.nextmonth"/><i class="glyphicon glyphicon-chevron-right"></i></a></span>
						<strong>${curYear}. ${curMonth}</strong>
						<table>
							<colgroup>
								<col style="width:14%;">
								<col style="width:14%;">
								<col style="width:14%;">
								<col>
								<col style="width:14%;">
								<col style="width:14%;">
								<col style="width:14%;">
							</colgroup>
							<thead>
								<tr>
									<th scope="col" class="sun"><spring:message code="common.title.calendar.sun"/></th>
									<th scope="col" class="mon"><spring:message code="common.title.calendar.mon"/></th>
									<th scope="col" class="tue"><spring:message code="common.title.calendar.tue"/></th>
									<th scope="col" class="wed"><spring:message code="common.title.calendar.wed"/></th>
									<th scope="col" class="thd"><spring:message code="common.title.calendar.thu"/></th>
									<th scope="col" class="fri"><spring:message code="common.title.calendar.fri"/></th>
									<th scope="col" class="sat"><spring:message code="common.title.calendar.sat"/></th>
								</tr>
							</thead>
							<tbody>
								<c:url var="baseUrl" value="/home/course/listCalendarCourseMain?mcd=${crsCalendarMcd}&yearMonth=${yearMonth}"/>
								<c:forEach var="item" items="${calendarList}">
								<tr>
									<td class="sun">
										<c:if test="${not empty item.w0}">
											<c:set var="courseName" value=""/>
											<c:set var="oncls" value=""/>
											<c:set var="ontoday" value=""/>
											<c:set var="yearMonthDay" value="${yearMonth}${item.w0}"/>
											<c:if test="${not empty item.w0Str}">
												<c:forEach var="sche" items="${item.w0Str}" varStatus="stat">
													<c:choose>
														<c:when test="${stat.first}">
															<c:set var="courseName" value="${sche.value}"/>
														</c:when>
														<c:otherwise>
															<c:set var="courseName" value="${courseName}<br/>${sche.value}"/>
														</c:otherwise>
													</c:choose>
												</c:forEach>
												<c:set var="oncls" value="oncls"/>
											</c:if>
											<c:if test="${curDate eq yearMonthDay}"><c:set var="ontoday" value="ontoday"/></c:if>
										<a href="${baseUrl}&enrlStartDttm=${yearMonth}${item.w0}" class="${oncls} ${ontoday}" <c:if test="${not empty item.w0Str}">data-toggle="tooltip" data-placement="bottom" title="${courseName}"</c:if>>${item.w0}</a>
										</c:if>
									</td>
									<td>
										<c:if test="${not empty item.w1}">
											<c:set var="courseName" value=""/>
											<c:set var="oncls" value=""/>
											<c:set var="ontoday" value=""/>
											<c:set var="yearMonthDay" value="${yearMonth}${item.w1}"/>
											<c:if test="${not empty item.w1Str}">
												<c:forEach var="sche" items="${item.w1Str}" varStatus="stat">
													<c:choose>
														<c:when test="${stat.first}">
															<c:set var="courseName" value="${sche.value}"/>
														</c:when>
														<c:otherwise>
															<c:set var="courseName" value="${courseName}<br/>${sche.value}"/>
														</c:otherwise>
													</c:choose>
												</c:forEach>
												<c:set var="oncls" value="oncls"/>
											</c:if>
											<c:if test="${curDate eq  yearMonthDay}"><c:set var="ontoday" value="ontoday"/></c:if>
										<a href="${baseUrl}&enrlStartDttm=${yearMonth}${item.w1}" class="${oncls} ${ontoday}" <c:if test="${not empty item.w1Str}">data-toggle="tooltip" data-placement="bottom" title="${courseName}"</c:if>>${item.w1}</a>
										</c:if>
									</td>
									<td>
										<c:if test="${not empty item.w2}">
											<c:set var="courseName" value=""/>
											<c:set var="oncls" value=""/>
											<c:set var="ontoday" value=""/>
											<c:set var="yearMonthDay" value="${yearMonth}${item.w2}"/>
											<c:if test="${not empty item.w2Str}">
												<c:forEach var="sche" items="${item.w2Str}" varStatus="stat">
													<c:choose>
														<c:when test="${stat.first}">
															<c:set var="courseName" value="${sche.value}"/>
														</c:when>
														<c:otherwise>
															<c:set var="courseName" value="${courseName}<br/>${sche.value}"/>
														</c:otherwise>
													</c:choose>
												</c:forEach>
												<c:set var="oncls" value="oncls"/>
											</c:if>
											<c:if test="${curDate eq  yearMonthDay}"><c:set var="ontoday" value="ontoday"/></c:if>
										<a href="${baseUrl}&enrlStartDttm=${yearMonth}${item.w2}" class="${oncls} ${ontoday}" <c:if test="${not empty item.w2Str }">data-toggle="tooltip" data-placement="bottom" title="${courseName}"</c:if>>${item.w2}</a>
										</c:if>
									</td>
									<td>
										<c:if test="${not empty item.w3}">
											<c:set var="courseName" value=""/>
											<c:set var="oncls" value=""/>
											<c:set var="ontoday" value=""/>
											<c:set var="yearMonthDay" value="${yearMonth}${item.w3}"/>
											<c:if test="${not empty item.w3Str}">
												<c:forEach var="sche" items="${item.w3Str}" varStatus="stat">
													<c:choose>
														<c:when test="${stat.first}">
															<c:set var="courseName" value="${sche.value}"/>
														</c:when>
														<c:otherwise>
															<c:set var="courseName" value="${courseName}<br/>${sche.value}"/>
														</c:otherwise>
													</c:choose>
												</c:forEach>
												<c:set var="oncls" value="oncls"/>
											</c:if>
											<c:if test="${curDate eq  yearMonthDay}"><c:set var="ontoday" value="ontoday"/></c:if>
										<a href="${baseUrl}&enrlStartDttm=${yearMonth}${item.w3}" class="${oncls} ${ontoday}" <c:if test="${not empty item.w3Str }">data-toggle="tooltip" data-placement="bottom" title="${courseName}"</c:if>>${item.w3}</a>
										</c:if>
									</td>
									<td>
										<c:if test="${not empty item.w4}">
											<c:set var="courseName" value=""/>
											<c:set var="oncls" value=""/>
											<c:set var="ontoday" value=""/>
											<c:set var="yearMonthDay" value="${yearMonth}${item.w4}"/>
											<c:if test="${not empty item.w4Str}">
												<c:forEach var="sche" items="${item.w4Str}" varStatus="stat">
													<c:choose>
														<c:when test="${stat.first}">
															<c:set var="courseName" value="${sche.value}"/>
														</c:when>
														<c:otherwise>
															<c:set var="courseName" value="${courseName}<br/>${sche.value}"/>
														</c:otherwise>
													</c:choose>
												</c:forEach>
												<c:set var="oncls" value="oncls"/>
											</c:if>
											<c:if test="${curDate eq  yearMonthDay}"><c:set var="ontoday" value="ontoday"/></c:if>
										<a href="${baseUrl}&enrlStartDttm=${yearMonth}${item.w4}" class="${oncls} ${ontoday}" <c:if test="${not empty item.w4Str }">data-toggle="tooltip" data-placement="bottom" title="${courseName}"</c:if> >${item.w4}</a>
										</c:if>
									</td>
									<td>
										<c:if test="${not empty item.w5}">
											<c:set var="courseName" value=""/>
											<c:set var="oncls" value=""/>
											<c:set var="ontoday" value=""/>
											<c:set var="yearMonthDay" value="${yearMonth}${item.w5}"/>
											<c:if test="${not empty item.w5Str}">
												<c:forEach var="sche" items="${item.w5Str}" varStatus="stat">
													<c:choose>
														<c:when test="${stat.first}">
															<c:set var="courseName" value="${sche.value}"/>
														</c:when>
														<c:otherwise>
															<c:set var="courseName" value="${courseName}<br/>${sche.value}"/>
														</c:otherwise>
													</c:choose>
												</c:forEach>
												<c:set var="oncls" value="oncls"/>
											</c:if>
											<c:if test="${curDate eq  yearMonthDay}"><c:set var="ontoday" value="ontoday"/></c:if>
										<a href="${baseUrl}&enrlStartDttm=${yearMonth}${item.w5}" class="${oncls} ${ontoday}" <c:if test="${not empty item.w5Str }">data-toggle="tooltip" data-placement="bottom" title="${courseName}"</c:if>>${item.w5}</a>
										</c:if>
									</td>
									<td class="sat">
										<c:if test="${not empty item.w6}">
											<c:set var="courseName" value=""/>
											<c:set var="oncls" value=""/>
											<c:set var="ontoday" value=""/>
											<c:set var="yearMonthDay" value="${yearMonth}${item.w6}"/>
											<c:if test="${not empty item.w6Str}">
												<c:forEach var="sche" items="${item.w6Str}" varStatus="stat">
													<c:choose>
														<c:when test="${stat.first}">
															<c:set var="courseName" value="${sche.value}"/>
														</c:when>
														<c:otherwise>
															<c:set var="courseName" value="${courseName}<br/>${sche.value}"/>
														</c:otherwise>
													</c:choose>
												</c:forEach>
												<c:set var="oncls" value="oncls"/>
											</c:if>
											<c:if test="${curDate eq  yearMonthDay}"><c:set var="ontoday" value="ontoday"/></c:if>
										<a href="${baseUrl}&enrlStartDttm=${yearMonth}${item.w6}" class="${oncls} ${ontoday}" <c:if test="${not empty item.w6Str }">data-toggle="tooltip" data-placement="bottom" title="${courseName}"</c:if>>${item.w6}</a>
										</c:if>
									</td>
								</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
				<script type="text/javascript">
					$(function() {
						//--tooltip
						$('[data-toggle="tooltip"]').tooltip({html: true})
					});
				</script>