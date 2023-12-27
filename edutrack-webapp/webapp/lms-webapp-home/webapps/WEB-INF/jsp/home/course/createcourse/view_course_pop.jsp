<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<%pageContext.setAttribute("crlf", "\n");%>
<c:url var="img_base" value="/img/home" />

	<div class="cal_contpop" style="padding-top:10px;">
		<table class="table table-bordered wordbreak">
			<caption class="sr-only">${createCourseVO.crsCreNm}</caption>
			<colgroup>
				<col style="width:20%" />
				<col style="width:80%" />
			</colgroup>
			<tbody>
				<tr>
					<th scope="col"><spring:message code="course.title.education.course.name"/></th>
					<td>${courseVO.crsNm}</td>
				</tr>
				<tr>
					<th scope="col"><spring:message code="course.title.createcourse.name"/></th>
					<td>${createCourseVO.crsCreNm}</td>
				</tr>
				<tr>
					<th scope="col"><spring:message code="course.title.createcourse.duration.aplc"/></th>
					<td>
						${createCourseVO.enrlAplcStartDttm} ~ ${createCourseVO.enrlAplcEndDttm}
					</td>
				</tr>
			<c:if test="${createCourseVO.crsOperType eq 'S' }">
				<tr>
					<th scope="col"><spring:message code="course.title.createcourse.eduday"/> </th>
					<td>
						${createCourseVO.enrlDaycnt}<spring:message code="common.title.day"/>
					</td>
				</tr>
			</c:if>
			<c:if test="${createCourseVO.crsOperType eq 'R' }">
				<c:if test="${createCourseVO.crsOperMthd eq 'ON'}">
				<tr>
					<th scope="col"><spring:message code="course.title.createcourse.method.online"/> <spring:message code="course.title.createcourse.duration.edu"/></th>
					<td>
						${createCourseVO.enrlStartDttm} ~ ${createCourseVO.enrlEndDttm}
					</td>
				</tr>
				</c:if>
				<c:if test="${createCourseVO.crsOperMthd eq 'OF'}">
				<tr>
					<th scope="col"><spring:message code="course.title.createcourse.method.offline"/> <spring:message code="course.title.createcourse.duration.edu"/></th>
					<td>
						${createCourseVO.enrlStartDttm} ~ ${createCourseVO.enrlEndDttm}
					</td>
				</tr>
				</c:if>
				<c:if test="${createCourseVO.crsOperMthd eq 'BL'}">
				<tr>
					<th scope="col"><spring:message code="course.title.createcourse.method.online"/> <spring:message code="course.title.createcourse.duration.edu"/></th>
					<td>
						${createCourseVO.enrlStartDttm} ~ ${createCourseVO.enrlEndDttm}
					</td>
				</tr>
				<tr>
					<th scope="col"><spring:message code="course.title.createcourse.method.offline"/> <spring:message code="course.title.createcourse.duration.edu"/></th>
					<td>
						${createCourseVO.oflnStartDttm} ~ ${createCourseVO.oflnEndDttm}
					</td>
				</tr>
				</c:if>
			</c:if>
				<tr>
					<th scope="col"><spring:message code="course.title.course.teacher"/></th>
					<td class="wordbreak">
						<c:forEach var="item" items="${teacherList}" varStatus="status">
						<c:if test="${status.count > 1}">,</c:if>
						<a href="/home/tch/info/viewTch?tchInfoVO.userNo=${item.userNo}&amp;tchInfoVO.isPop=Y&amp;CRSCRECD=${createCourseVO.crsCreCd}">
						${item.userNm}
						</a>
						</c:forEach>
					</td>
				</tr>
				<tr>
					<th scope="col"><spring:message code="course.title.course.edumthd"/></th>
					<td class="wordbreak">
						<meditag:codename code="${createCourseVO.crsOperMthd}" category="CRS_OPER_MTHD" />
					</td>
				</tr>
				<tr>
					<th scope="col"><spring:message code="course.title.course.crstype"/></th>
					<td class="wordbreak">
						<meditag:codename code="${createCourseVO.crsOperType}" category="CRS_OPER_TYPE" />
					</td>
				</tr>
				<tr>
					<th scope="col"><spring:message code="course.title.course.edutime"/></th>
					<td class="wordbreak">
						<ul class="list-inline">
						<c:if test="${createCourseVO.crsOperMthd eq 'ON'}">
							<li class="mr20">${createCourseVO.onlnEduTm} <spring:message code="common.title.time"/></li>
						</c:if>
						<c:if test="${createCourseVO.crsOperMthd eq 'OF'}">
							<li class="mr20">${createCourseVO.oflnEduTm} <spring:message code="common.title.time"/></li>
						</c:if>
						<c:if test="${createCourseVO.crsOperMthd eq 'BL'}">
							<li class="mr20"><spring:message code="course.title.createcourse.method.online"/> <spring:message code="course.title.course.edutime"/> : ${createCourseVO.onlnEduTm} <spring:message code="common.title.time"/></li>
							<li class="mr20"><spring:message code="course.title.createcourse.method.offline"/> <spring:message code="course.title.course.edutime"/> : ${createCourseVO.oflnEduTm} <spring:message code="common.title.time"/></li>
						</c:if>
						</ul>
					</td>
				</tr>
				<tr>
					<th scope="col"><spring:message code="course.title.course.stdcnt"/></th>
					<td class="wordbreak">
						<c:if test="${createCourseVO.enrlNop > 0}">
						${createCourseVO.enrlNop}
						</c:if>
						<c:if test="${createCourseVO.enrlNop == 0}">
						-
						</c:if>
					</td>
				</tr>
				<tr>
					<th scope="col"><spring:message code="course.title.course.ing.stdcnt"/></th>
					<td class="wordbreak">
						<c:set var="stuCnt" value="${createCourseVO.cnfmCnt}"/>
						<c:if test="${createCourseVO.cnfmCnt > createCourseVO.enrlNop}"><c:set var="stuCnt" value="${createCourseVO.enrlNop}"/></c:if>
						${stuCnt}
					</td>
				</tr>
				<tr>
					<th scope="col"><spring:message code="course.title.course.target"/></th>
					<td class="wordbreak">${courseVO.eduTarget}</td>
				</tr>
				<c:if test="${createCourseVO.crsOperMthd eq 'OF' || createCourseVO.crsOperMthd eq 'BL' }">
				<tr>
					<th scope="col"><spring:message code="course.title.course.place"/></th>
					<td class="wordbreak">${createCourseVO.oflnEduPlace}</td>
				</tr>
				</c:if>
				<tr>
					<th scope="col"><spring:message code="course.title.course.edufee"/></th>
					<td class="wordbreak">
					<c:if test="${createCourseVO.eduPrice > 0 }">
						<c:if test="${sessionScope.MNTRYPOS eq 'PR'}">${sessionScope.MNTRYUNIT}</c:if><fmt:formatNumber value="${createCourseVO.eduPrice}"/><c:if test="${sessionScope.MNTRYPOS eq 'PO'}">${sessionScope.MNTRYUNIT}</c:if>
					</c:if>
					<c:if test="${createCourseVO.eduPrice == 0 }">
						<spring:message code="course.title.createcourse.free.edufee"/>
					</c:if>
					</td>
				</tr>
				<tr>
					<th scope="col"><spring:message code="course.title.course.desc"/></th>
					<td class="wordbreak">
						${fn:replace(courseVO.crsDesc,crlf,"<br/>")}
					</td>
				</tr>
				<tr>
					<th scope="col"><spring:message code="course.title.course.goal"/></th>
					<td class="wordbreak">
						${fn:replace(courseVO.eduPrps,crlf,"<br/>")}
					</td>
				</tr>
				<tr>
					<th scope="col"><spring:message code="course.title.course.lecture.configuration"/></th>
					<td class="wordbreak" align="center">
						<div style="width:96%" class="text-left">
						<c:if test="${courseVO.crsOperMthd eq 'OF' || courseVO.crsOperMthd eq 'BL' }">
							<c:if test="${not empty timetableList}">
								<spring:message code="course.title.course.lecture.timetable"/>
								<table class="table" style="width:100%;border-top:2px solid #0088fe;">
									<caption>${createCourseVO.crsCreNm}</caption>
									<colgroup>
										<col width="10%" />
										<col width="30%" />
										<col width="auto" />
									</colgroup>
									<thead>
									<tr>
										<th scope="col" class="pipe"><spring:message code="course.title.course.timetable.day"/></th>
										<th scope="col" class="pipe"><spring:message code="course.title.course.timetable.time"/></th>
										<th scope="col" class="rnone"><spring:message code="course.title.course.timetable.cnts"/></th>
									</tr>
									</thead>
									<tbody>
									<c:forEach items="${timetableList}" var="times">
									<tr>
										<td class="text-center">${times.tmtabDay}</td>
										<td class="text-center">${times.eduTm}</td>
										<td>
											<c:if test="${times.tmtabType eq 'LEC'}">
											${times.sbjNm}
											</c:if>
											<c:if test="${times.tmtabType eq 'ETC'}">
											${times.eduCts}
											</c:if>
										</td>
									</tr>
									</c:forEach>
									</tbody>
								</table>
							</c:if>
						</c:if>
						<c:if test="${courseVO.crsOperMthd eq 'ON' || courseVO.crsOperMthd eq 'BL' }">
							<c:forEach items="${onlineSubjectList}" var="subject">
								<c:if test="${not empty subject.contentsVO.subList}">
								<br/>■ ${subject.sbjNm}
								<table class="table" style="width:100%;border-top:2px solid #0088fe;">
									<caption class="sr-only">${subjet.sbjNm}</caption>
									<colgroup>
										<col width="10%" />
										<col width="auto" />
										<col width="10%" />
									</colgroup>
									<thead>
									<tr>
										<th scope="col" class="pipe"><spring:message code="common.title.no"/></th>
										<th scope="col" class="pipe"><spring:message code="course.title.contents.name"/></th>
										<th scope="col" class="rnone"><spring:message code="course.title.course.ratio.progress.check.title"/></th>
									</tr>
									</thead>
									<tbody>
									<c:forEach items="${subject.contentsVO.subList}" var="contents">
									<tr>
										<td class="text=center">
											<c:if test="${contents.unitLvl == 1 }"><!-- contents.unitType eq 'C' &&  -->
												<c:set var="cnt" value="${cnt+1}"/>
												${cnt}
											</c:if>
										</td>
										<td class="text-left" style="padding-left:${20 * (contents.unitLvl -1)}px;">${contents.unitNm}</td>
										<td class="text-center">
											<c:if test="${contents.unitType eq 'L' }">
												<c:if test="${contents.prgrChkYn eq 'Y'}"><spring:message code="course.title.contents.required"/></c:if>
												<c:if test="${contents.prgrChkYn eq 'N'}"><spring:message code="course.title.contents.option"/></c:if>
											</c:if>
										</td>
									</tr>
									</c:forEach>
									</tbody>
								</table>
								</c:if>
							</c:forEach>
						</c:if>
						</div>
					</td>
				</tr>
				<tr>
					<th scope="col"><spring:message code="course.title.course.scoreratio"/></th>
					<td class="wordbreak">
						<c:if test="${createCourseVO.prgrRatio > 0 }"><spring:message code="course.title.course.ratio.progress"/> : ${createCourseVO.prgrRatio}%</c:if>
						<c:if test="${createCourseVO.examRatio > 0 }"><spring:message code="course.title.course.ratio.exam"/> : ${createCourseVO.examRatio}%</c:if>
						<c:if test="${createCourseVO.asmtRatio > 0 }"><spring:message code="course.title.course.ratio.asmt"/> : ${createCourseVO.asmtRatio}%</c:if>
						<c:if test="${createCourseVO.forumRatio > 0 }"><spring:message code="course.title.course.ratio.forum"/> : ${createCourseVO.forumRatio}%</c:if>
						<c:if test="${createCourseVO.prjtRatio > 0 }"><spring:message code="course.title.course.ratio.project"/> : ${createCourseVO.prjtRatio}%</c:if>
						<c:if test="${createCourseVO.attdRatio > 0 }"><spring:message code="course.title.course.ratio.attend"/> : ${createCourseVO.attdRatio}%</c:if>
						<c:if test="${createCourseVO.joinRatio > 0 }"><spring:message code="course.title.course.ratio.join"/> : ${createCourseVO.joinRatio}%</c:if>
						<c:if test="${createCourseVO.etcRatio > 0 }"><spring:message code="course.title.course.ratio.etc"/> : ${createCourseVO.etcRatio}%</c:if>
						<br/><spring:message code="course.title.course.completescore"/> : <spring:message code="course.message.course.completescore.over" arguments="${createCourseVO.cpltScore}"/>
					</td>
				</tr>
			</tbody>
		</table>
		<div class="text-right">
			<a href="#none" onclick="parent.modalBoxClose();" class="btn btn-sm btn-default"><spring:message code="button.close"/></a>
		</div>
	</div>
