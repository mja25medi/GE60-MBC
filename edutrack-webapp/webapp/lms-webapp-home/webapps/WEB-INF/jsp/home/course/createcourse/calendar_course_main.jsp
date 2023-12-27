<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="img_base" value="/img/home" />

				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default" style="margin-top:40px;">
							<div class="panel-heading">
								<span class="panel-title" style="font-size: 14px;"><i class="glyphicon glyphicon-calendar"></i> <spring:message code="course.message.createcourse.msg.calendar"/></span><br/>
								<span class="panel-title" style="font-size: 14px;"><i class="glyphicon glyphicon-calendar"></i> <spring:message code="course.message.createcourse.msg.calendar2"/></span>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default" style="margin-bottom:10px;">
							<div class="panel-heading">
								<div class="cal_month" style="text-align:center;" >
									<c:url var="linkBase" value="/home/course/listCalendarCourseMain"/>
									<c:choose>
										<c:when test="${isPreMonth }">
											<i class="glyphicon glyphicon-chevron-left"></i> <spring:message code="common.title.pervmonth"/>
										</c:when>
										<c:otherwise>
											<a href="${linkBase}?yearMonth=${prevYearMonth}"><i class="glyphicon glyphicon-chevron-left"></i> <spring:message code="common.title.pervmonth"/></a>
										</c:otherwise>
									</c:choose>
									<!-- <span style="margin-left:20px;margin-right:20px;width=200px;"> -->
										<select name="creYear" id="creYear" onchange="javascript:listCalendarCourse();" style="width:90px;height: 34px;line-height: 1.42857143;color: #555;background-color: #fff;background-image: none;border: 1px solid #ccc;border-radius: 4px;" class="input-sm">
										<c:forEach items="${yearList}" var="year">
											<c:if test="${year == curYear}">
											<option value="${year}" selected="selected">${year}</option>
											</c:if>
											<c:if test="${year != curYear}">
											<option value="${year}">${year}</option>
											</c:if>
										</c:forEach>
										</select>
										<select name="creMonth" id="creMonth" onchange="javascript:listCalendarCourse();" style="width:70px;height: 34px;line-height: 1.42857143;color: #555;background-color: #fff;background-image: none;border: 1px solid #ccc;border-radius: 4px;" class="form-control33 input-sm">
										<c:forEach items="${monthList}" var="month">
											<c:if test="${month == curMonth}">
											<option value="${month}" selected="selected">${month}</option>
											</c:if>
											<c:if test="${month != curMonth}">
											<option value="${month}">${month}</option>
											</c:if>
										</c:forEach>
										</select>
									<!-- </span> -->
									<a href="${linkBase}?yearMonth=${nextYearMonth}"><spring:message code="common.title.nextmonth"/> <i class="glyphicon glyphicon-chevron-right"></i></a>
								</div>
							</div>
							<table  class="table table-bordered wordbreak">
								<caption class="sr-only"><spring:message code="course.title.createcourse.calendar"/></caption>
								<colgroup>
									<col style="width:10%;" />
									<col style="width:10%;" />
									<col style="width:10%;" />
									<col style="width:10%;" />
									<col style="width:10%;" />
									<col style="width:10%;" />
									<col style="width:10%;" />
								</colgroup>
								<thead>
									<tr>
										<th scope="col" class="sun"><spring:message code="common.title.calendar.sun"/></th>
										<th scope="col"><spring:message code="common.title.calendar.mon"/></th>
										<th scope="col"><spring:message code="common.title.calendar.tue"/></th>
										<th scope="col"><spring:message code="common.title.calendar.wed"/></th>
										<th scope="col"><spring:message code="common.title.calendar.thu"/></th>
										<th scope="col"><spring:message code="common.title.calendar.fri"/></th>
										<th scope="col" class="sat"><spring:message code="common.title.calendar.sat"/></th>
									</tr>
								</thead>
								<tbody>
									<c:set var="linkDAY" value="${linkBase}?yearMonth=${curYear}${curMonth}"/>
									<c:forEach var="item" items="${calendarList}">
									<tr>
										<td class="sun <c:if test="${not empty item.w0Str}">oncls</c:if> <c:if test="${fn:substring(curDate,6,8) eq item.w0}">oncalB</c:if>">
											<c:if test="${not empty item.w0}">
												<c:set var="courseName" value=""/>
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
												</c:if>
											<a href="${linkDAY}&amp;enrlStartDttm=${curYear}${curMonth}${item.w0}" <c:if test="${not empty item.w0Str}">data-toggle="tooltip" data-placement="bottom" title="${courseName}"</c:if>>${item.w0}</a>
											</c:if>
										</td>
										<td class="<c:if test="${not empty item.w1Str}">oncls</c:if> <c:if test="${fn:substring(curDate,6,8) eq item.w1}">oncalB</c:if>">
											<c:if test="${not empty item.w1}">
												<c:set var="courseName" value=""/>
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
												</c:if>
											<a href="${linkDAY}&amp;enrlStartDttm=${curYear}${curMonth}${item.w1}" <c:if test="${not empty item.w1Str}">data-toggle="tooltip" data-placement="bottom" title="${courseName}"</c:if>>${item.w1}</a>
											</c:if>
										</td>
										<td class="<c:if test="${not empty item.w2Str}">oncls</c:if> <c:if test="${fn:substring(curDate,6,8) eq item.w2}">oncalB</c:if>">
											<c:if test="${not empty item.w2}">
												<c:set var="courseName" value=""/>
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
												</c:if>
											<a href="${linkDAY}&amp;enrlStartDttm=${curYear}${curMonth}${item.w2}" <c:if test="${not empty item.w2Str}">data-toggle="tooltip" data-placement="bottom" title="${courseName}"</c:if>>${item.w2}</a>
											</c:if>
										</td>
										<td class="<c:if test="${not empty item.w3Str}">oncls</c:if> <c:if test="${fn:substring(curDate,6,8) eq item.w3}">oncalB</c:if>">
											<c:if test="${not empty item.w3}">
												<c:set var="courseName" value=""/>
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
												</c:if>
											<a href="${linkDAY}&amp;enrlStartDttm=${curYear}${curMonth}${item.w3}" <c:if test="${not empty item.w3Str}">data-toggle="tooltip" data-placement="bottom" title="${courseName}"</c:if>>${item.w3}</a>
											</c:if>
										</td>
										<td class="<c:if test="${not empty item.w4Str}">oncls</c:if> <c:if test="${fn:substring(curDate,6,8) eq item.w4}">oncalB</c:if>">
											<c:if test="${not empty item.w4}">
												<c:set var="courseName" value=""/>
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
												</c:if>
											<a href="${linkDAY}&amp;enrlStartDttm=${curYear}${curMonth}${item.w4}" <c:if test="${not empty item.w4Str}">data-toggle="tooltip" data-placement="bottom" title="${courseName}"</c:if>>${item.w4}</a>
											</c:if>
										</td>
										<td class="<c:if test="${not empty item.w5Str}">oncls</c:if> <c:if test="${fn:substring(curDate,6,8) eq item.w5}">oncalB</c:if>">
											<c:if test="${not empty item.w5}">
												<c:set var="courseName" value=""/>
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
												</c:if>
											<a href="${linkDAY}&amp;enrlStartDttm=${curYear}${curMonth}${item.w5}" <c:if test="${not empty item.w5Str}">data-toggle="tooltip" data-placement="bottom" title="${courseName}"</c:if>>${item.w5}</a>
											</c:if>
										</td>
										<td class="sat <c:if test="${not empty item.w6Str}">oncls</c:if> <c:if test="${fn:substring(curDate,6,8) eq item.w6}">oncalB</c:if>">
											<c:if test="${not empty item.w6}">
												<c:set var="courseName" value=""/>
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
												</c:if>
											<a href="${linkDAY}&amp;enrlStartDttm=${curYear}${curMonth}${item.w6}" <c:if test="${not empty item.w6Str}">data-toggle="tooltip" data-placement="bottom" title="${courseName}"</c:if>>${item.w6}</a>
											</c:if>
										</td>
									</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<p>※ <spring:message code="course.message.createcourse.info.msg.calendar"/></p>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default" style="margin-top:40px;">
							<div class="panel-heading">
								<span class="panel-title">
									<i class="glyphicon glyphicon-calendar"></i>
									<c:if test="${fn:length(curDate) == 6}">
									${fn:substring(curDate,0,4)} / ${fn:substring(curDate,4,6)}
									</c:if>
									<c:if test="${fn:length(curDate) == 8}">
									${fn:substring(curDate,0,4)} / ${fn:substring(curDate,4,6)} / ${fn:substring(curDate,6,8)}
									</c:if>
							</span>
							</div>

							<ul class="list-group">
							<c:if test="${empty courseList}">
								<li class="list-group-item">
									<spring:message code="course.message.createcourse.nodata"/>
								</li>
							</c:if>
							<c:forEach var="item" items="${courseList}">
								<li class="list-group-item">
									<h4 class="list-group-item-heading">
										<a href="#_none" onclick="javascript:showCreCrs('${item.crsCreCd}');">${item.crsCreNm}</a>
										<small>[ ${item.creYear} / ${item.creTerm} ]</small>
										<span class="pull-right">
											<c:choose>
												<c:when test="${item.enrlYn eq 'Y' }">
													<c:choose>
														<c:when test="${item.enrlAplcYn eq 'N'}">
															<a href="javascript:alert('<spring:message code="course.message.createcourse.alert.enroll.eduend"/>')" title="<spring:message code="course.title.createcourse.eduend"/>" class="btn btn-default btn-xs"><spring:message code="course.title.createcourse.eduend"/></a>
														</c:when>
														<c:otherwise>
															<a href="javascript:alert('<spring:message code="course.message.createcourse.alert.enroll.enrollend"/>')" title="<spring:message code="course.title.createcourse.enrollend"/>" class="btn btn-primary btn-xs"><spring:message code="course.title.createcourse.enrollend"/></a>
														</c:otherwise>
													</c:choose>
												</c:when>
												<c:otherwise>
													<c:if test="${item.enrlAplcYn eq 'B'}">
														<a href="javascript:alert('<spring:message code="course.message.createcourse.alert.enroll.noduration"/>')" title="<spring:message code="course.title.createcourse.noduration"/>" class="btn btn-default btn-xs"><spring:message code="course.title.createcourse.noduration"/></a>
													</c:if>
													<c:if test="${item.enrlAplcYn eq 'Y'}">
														<c:choose>
															<c:when test="${item.nopLimitYn eq 'Y' && (item.enrlCnt + item.cnfmCnt) >= item.enrlNop }">
																<a href="javascript:alert('<spring:message code="course.message.createcourse.alert.enroll.stdover"/>')" title="<spring:message code="course.title.createcourse.stdover"/>" class="btn btn-default btn-xs"><spring:message code="course.title.createcourse.stdover"/></a>
															</c:when>
															<c:otherwise>
																<c:if test="${not empty USERNO}">
																<%-- <a href="/StudentHome.do?cmd=enrollCourse&amp;studentDTO.crsCtgrCd=${item.crsCtgrCd}&amp;studentDTO.crsCd=${item.crsCd}&amp;studentDTO.crsCreCd=${item.crsCreCd}" title="<spring:message code="course.title.createcourse.enroll"/>" class="btn btn-warning btn-xs"><spring:message code="course.title.createcourse.enroll"/></a> --%>
																<a href="javascript:showCreCrsEnroll('${item.crsCreCd}');" title="<spring:message code="course.title.createcourse.enroll"/>" class="btn btn-warning btn-xs"><spring:message code="course.title.createcourse.enroll"/></a>
																</c:if>
																<c:if test="${empty USERNO}">
																<a href="javascript:alert('<spring:message code="course.message.createcourse.alert.enroll.login"/>')" title="<spring:message code="course.title.createcourse.enroll"/>" class="btn btn-warning btn-xs"><spring:message code="course.title.createcourse.enroll"/></a>
																</c:if>
															</c:otherwise>
														</c:choose>
													</c:if>
													<c:if test="${item.enrlAplcYn eq 'I'}">
														<a href="javascript:alert('<spring:message code="course.message.createcourse.alert.enroll.noduration"/>')" title="<spring:message code="course.title.createcourse.studying"/>" class="btn btn-default btn-xs"><spring:message code="course.title.createcourse.studying"/></a>
													</c:if>
													<c:if test="${item.enrlAplcYn eq 'N'}">
														<a href="javascript:alert('<spring:message code="course.message.createcourse.alert.enroll.eduend"/>')" title="<spring:message code="course.title.createcourse.eduend"/>" class="btn btn-default btn-xs"><spring:message code="course.title.createcourse.eduend"/></a>
													</c:if>
												</c:otherwise>
											</c:choose>
										</span>
									</h4>
									<ul class="list-inline list-group-item-text" style="margin-top:10px;">
										<li><spring:message code="course.title.course.edumthd"/> : <meditag:codename code="${item.crsOperMthd}" category="CRS_OPER_MTHD" /></li>
										<li style="padding-right:15px;"><spring:message code="course.title.course.crstype"/> : <meditag:codename code="${item.crsOperType}" category="CRS_OPER_TYPE" /></li>
										<li><spring:message code="course.title.course.edufee"/> :
											<c:if test="${item.eduPrice > 0 }">
												<c:if test="${sessionScope.MNTRYPOS eq 'PR'}">${sessionScope.MNTRYUNIT}</c:if><fmt:formatNumber value="${item.eduPrice}"/><c:if test="${sessionScope.MNTRYPOS eq 'PO'}">${sessionScope.MNTRYUNIT}</c:if>
											</c:if>
											<c:if test="${item.eduPrice == 0 }">
												<spring:message code="course.title.createcourse.free.edufee"/>
											</c:if>
										</li>

										<li>
											<c:if test="${item.nopLimitYn eq 'Y' }">
												<c:set var="stuCnt" value="${item.cnfmCnt}"/>
												<c:if test="${item.cnfmCnt > item.enrlNop}"><c:set var="stuCnt" value="${item.enrlNop}"/></c:if>
												<spring:message code="course.title.course.ing.stdcnt"/>/<spring:message code="course.title.course.stdcnt"/> : ${stuCnt}/${item.enrlNop}
											</c:if>
											<c:if test="${item.nopLimitYn eq 'N' }">
												<spring:message code="course.title.course.ing.stdcnt"/> : ${item.cnfmCnt}
											</c:if>
											<spring:message code="common.title.cnt.user"/>
										</li>
										<li><spring:message code="course.title.course.target"/> : ${item.eduTarget}</li>
										<c:if test="${item.crsOperMthd eq 'OF' || item.crsOperMthd eq 'BL' }">
										<li class="wordbreak"><spring:message code="course.title.course.place"/> : ${item.oflnEduPlace}</li>
										</c:if>
										<li><spring:message code="course.title.createcourse.duration.aplc"/> : <meditag:dateformat type="1" delimeter="." property="${item.enrlAplcStartDttm}"/>~<meditag:dateformat type="1" delimeter="." property="${item.enrlAplcEndDttm}"/></li>

										<c:choose>
											<c:when test="${item.crsOperType eq 'S' }">
										<li style="padding-right:15px;">
												<spring:message code="course.title.createcourse.eduday"/> : ${item.enrlDaycnt}<spring:message code="common.title.day"/>
										</li>
											</c:when>
											<c:otherwise>

												<c:choose>
													<c:when test="${item.crsOperMthd eq 'ON' }">
										<li style="padding-right:15px;"><spring:message code="course.title.createcourse.method.online"/><spring:message code="course.title.createcourse.duration.edu"/> : <meditag:dateformat type="1" delimeter="." property="${item.enrlStartDttm}"/>~<meditag:dateformat type="1" delimeter="." property="${item.enrlEndDttm}"/></li>
													</c:when>
													<c:when test="${item.crsOperMthd eq 'OF' }">
										<li style="padding-right:15px;"><spring:message code="course.title.createcourse.method.offline"/><spring:message code="course.title.createcourse.duration.edu"/> : <meditag:dateformat type="1" delimeter="." property="${item.enrlStartDttm}"/>~<meditag:dateformat type="1" delimeter="." property="${item.enrlEndDttm}"/></li>
													</c:when>
													<c:when test="${item.crsOperMthd eq 'BL' }">
										<li style="padding-right:15px;"><spring:message code="course.title.createcourse.method.online"/><spring:message code="course.title.createcourse.duration.edu"/> : <meditag:dateformat type="1" delimeter="." property="${item.enrlStartDttm}"/>~<meditag:dateformat type="1" delimeter="." property="${item.enrlEndDttm}"/></li>
										<li style="padding-right:15px;"><spring:message code="course.title.createcourse.method.offline"/><spring:message code="course.title.createcourse.duration.edu"/> : <meditag:dateformat type="1" delimeter="." property="${item.oflnStartDttm}"/>~<meditag:dateformat type="1" delimeter="." property="${item.oflnEndDttm}"/></li>
													</c:when>
													<c:otherwise>
										<li style="padding-right:15px;"><spring:message code="course.title.createcourse.duration.edu"/> : <meditag:dateformat type="1" delimeter="." property="${item.enrlStartDttm}"/>~<meditag:dateformat type="1" delimeter="." property="${item.enrlEndDttm}"/></li>
													</c:otherwise>
												</c:choose>

											</c:otherwise>
										</c:choose>
									</ul>
								</li>
							</c:forEach>
							</ul>
						</div>
					</div>
				</div>

<script type="text/javascript">
	var modalBox = null;

	$(document).ready(function() {
		modalBox = new $M.ModalDialog({
			"modalid" : "modal1"
		});

		$('[data-toggle="tooltip"]').tooltip({html: true});
	});

	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}

	/**
	 * 개설 과정 정보 보기
	 */
	function showCreCrs(crsCreCd) {
		var addContent  = "<iframe id='courseInfoFrame' name='courseInfoFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='yes' src='<c:url value="/home/course/readCoursePop"/>"
			+ "?crsCreCd="+crsCreCd+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(700, 650);
		modalBox.setTitle("<spring:message code="course.title.course.info"/>");
		modalBox.show();
	}

	function listCalendarCourse(){
		var year = $('#creYear').val();
		var month = $('#creMonth').val();
		location.href="${linkBase}${AMPERSAND}yearMonth="+year+month;
	}

	function showCreCrsEnroll(crsCreCd) {
		location.href = "/home/course/enrollCourseViewMain?crsCreCd="+crsCreCd;
	}
</script>
