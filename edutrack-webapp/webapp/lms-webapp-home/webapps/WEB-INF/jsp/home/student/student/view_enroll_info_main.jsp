<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<%pageContext.setAttribute("crlf", "\n");%>
<c:url var="img_base" value="/img/home" scope="request"/>

				<div class="row">
					<div class="col-lg-12" style="margin-bottom:20px;">

						<table class="table table-bordered">
							<%-- <caption>${createcourseVO.crsCreNm}</caption> --%>
							<colgroup>
								<col style="width:20%" />
								<col style="width:30%" />
								<col style="width:20%" />
								<col style="width:30%" />
							</colgroup>
							<tbody>
								<tr>
									<th scope="col"><spring:message code="course.title.education.course.name"/></th>
									<td colspan="3">${courseVO.crsNm}</td>
								</tr>
								<tr>
									<th scope="col"><spring:message code="course.title.createcourse.name"/></th>
									<td colspan="3">${createcourseVO.crsCreNm}</td>
								</tr>
								<tr>
									<th scope="col"><spring:message code="course.title.course.teacher"/></th>
									<td colspan="3">
										<c:forEach var="item" items="${teacherList}" varStatus="status">
										<c:if test="${status.count > 1}">,</c:if>
										<a href="javascript:tchInfo('${item.userNo}')">${item.userNm}</a>
										</c:forEach>
									</td>
								</tr>
								<tr>
									<th scope="col"><spring:message code="course.title.course.edumthd"/></th>
									<td>
										<meditag:codename code="${createcourseVO.crsOperMthd}" category="CRS_OPER_MTHD" />
									</td>
									<th scope="col"><spring:message code="course.title.course.stdcnt"/></th>
									<td>
										<c:if test="${createcourseVO.enrlNop > 0}">
										${createcourseVO.enrlNop}
										</c:if>
										<c:if test="${createcourseVO.enrlNop == 0}">
										-
										</c:if>
									</td>
								</tr>
								<tr>
									<th scope="col"><spring:message code="course.title.course.target"/></th>
									<td>${courseVO.eduTarget}</td>
									<th scope="col"><spring:message code="course.title.course.ing.stdcnt"/></th>
									<td class="wordbreak">
										<c:set var="stuCnt" value="${createcourseVO.cnfmCnt}"/>
										<c:if test="${createcourseVO.cnfmCnt > createcourseVO.enrlNop}"><c:set var="stuCnt" value="${createcourseVO.enrlNop}"/></c:if>
										${stuCnt}
									</td>
								</tr>
								<c:if test="${createcourseVO.crsOperMthd eq 'OF' || createcourseVO.crsOperMthd eq 'BL' }">
								<tr>
									<th scope="col"><spring:message code="course.title.course.place"/></th>
									<td colspan="3" class="wordbreak">${createcourseVO.oflnEduPlace}</td>
								</tr>
								</c:if>
								<tr>
									<th scope="col"><spring:message code="course.title.course.desc"/></th>
									<td colspan="3">
										${fn:replace(courseVO.crsDesc,crlf,"<br/>")}
									</td>
								</tr>
								<tr>
									<th scope="col"><spring:message code="course.title.course.goal"/></th>
									<td colspan="3">
										${fn:replace(courseVO.eduPrps,crlf,"<br/>")}
									</td>
								</tr>
								<tr>
									<th scope="col"><spring:message code="course.title.course.lecture.configuration"/></th>
									<td colspan="3" align="center">
										<div style="width:96%" class="text-left">
										<c:if test="${courseVO.crsOperMthd eq 'OF' || courseVO.crsOperMthd eq 'BL' }">
											<c:if test="${not empty timetableList}">
												<spring:message code="course.title.course.lecture.timetable"/>
												<table class="table" style="width:96%;border-top:2px solid #0088fe;">
													<caption>${createcourseVO.crsCreNm}</caption>
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
												<table class="table" style="width:96%;border-top:2px solid #0088fe;">
													<caption>${subjet.sbjNm}</caption>
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
														<td class="text-center">
															<c:if test="${contents.unitLvl == 1 }"><!-- contents.unitType eq 'C' &&  -->
																<c:set var="cnt" value="${cnt+1}"/>
																${cnt}
															</c:if>
														</td>
														<td class="text-left wordbreak" style="padding-left:${20 * (contents.unitLvl -1)}px;">${contents.unitNm}</td>
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
									<td colspan="3">
										<c:if test="${createcourseVO.prgrRatio > 0 }"><spring:message code="course.title.course.ratio.progress"/> : ${createcourseVO.prgrRatio}%</c:if>
										<c:if test="${createcourseVO.examRatio > 0 }"><spring:message code="course.title.course.ratio.exam"/> : ${createcourseVO.examRatio}%</c:if>
										<c:if test="${createcourseVO.asmtRatio > 0 }"><spring:message code="course.title.course.ratio.asmt"/> : ${createcourseVO.asmtRatio}%</c:if>
										<c:if test="${createcourseVO.forumRatio > 0 }"><spring:message code="course.title.course.ratio.forum"/> : ${createcourseVO.forumRatio}%</c:if>
										<c:if test="${createcourseVO.prjtRatio > 0 }"><spring:message code="course.title.course.ratio.project"/> : ${createcourseVO.prjtRatio}%</c:if>
										<c:if test="${createcourseVO.attdRatio > 0 }"><spring:message code="course.title.course.ratio.attend"/> : ${createcourseVO.attdRatio}%</c:if>
										<c:if test="${createcourseVO.joinRatio > 0 }"><spring:message code="course.title.course.ratio.join"/> : ${createcourseVO.joinRatio}%</c:if>
										<c:if test="${createcourseVO.etcRatio > 0 }"><spring:message code="course.title.course.ratio.etc"/> : ${createcourseVO.etcRatio}%</c:if>
										<br/><spring:message code="course.title.course.completescore"/> : <spring:message code="course.message.course.completescore.over" arguments="${createcourseVO.cpltScore}"/>
									</td>
								</tr>
							</tbody>
						</table>

						<div class="text-right">
							<a href="/home/main/goMenuPage?amp;mcd=${MENUCODE}" class="btn btn-default btn-sm"><spring:message code="button.list"/></a>
						</div>

					</div>
				</div>

<script type="text/javascript">
var modalBox = null;
$(document).ready(function() {
	modalBox = new $M.ModalDialog({
		"modalid" : "modal1"
	});
});
/**
 * 강사 정보 폼
 */
function tchInfo(userNo) {
	var addContent  = "<iframe id='addTeacherFrame' name='addTeacherFrame' width='100%' height='100%' "
		+ "frameborder='0' scrolling='auto' src='<c:url value="/home/tch/info/viewTch"/>"
		+ "?userNo="+userNo+"&amp;isPop=N&amp;CRSCRECD=${createcourseVO.crsCreCd}'/>";
	modalBox.clear();
	modalBox.addContents(addContent);
	modalBox.resize(800, 780);
	modalBox.setTitle("<spring:message code="teacher.title.teacherinfo.teacher.manage"/>");
	modalBox.show();
}

function modalBoxClose() {
	modalBox.clear();
	modalBox.close();
}

</script>

