<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="img_base" value="/img/home" scope="request"/>
<c:set var="paging" value="Y"/>
<c:set var="listCourse" value="Y"/>


				<div class="row">
					<div class="col-lg-12">
						<ul class="list-group">
						<c:if test="${empty courseList}">
							<li class="list-group-item">
								<spring:message code="course.message.createcourse.history.nodata"/>
							</li>
						</c:if>
						<c:forEach var="item" items="${courseList}" varStatus="status">
							<li class="list-group-item">
								<h4 class="list-group-item-heading">
									<a href="<c:url value="/home/student/viewEnrollMain"/>?stdNo=${item.stdNo}" title="<spring:message code="course.title.createcourse.view.courseinfo"/>">${item.crsCreNm}</a>
									<small>[${item.creYear} / ${item.creTerm}]</small>
									<span class="pull-right">
										<c:if test="${item.enrlSts eq 'C'}">
										<a href="javascript:printCerti('${item.crsCreCd}','${item.stdNo}')"class="btn btn-default btn-xs"><i class="glyphicon glyphicon-print"></i> <spring:message code="button.print.certi"/></a>
										</c:if>
										<c:if test="${item.connectYn eq 'Y' || item.connectYn eq 'C'}">
										<a href="<c:url value="/MainLecture.do?cmd=goLecture&amp;crsCreCd=${item.crsCreCd}&amp;stdNo=${item.stdNo}" />" class="btn btn-warning btn-xs"><spring:message code="common.title.enter"/></a>
										</c:if>
										<a href="javascript:showLecture('${item.crsCreCd}','${item.stdNo}')" class="btn btn-xs btn-default"><i id="btn_${item.crsCreCd}_${item.stdNo}" class="fa fa-chevron-down"></i></a>
									</span>
								</h4>
								<ul class="list-inline" style="margin-top:10px;">
									<li class="mr15"><spring:message code="course.title.course.edumthd"/> : <meditag:codename code="${item.crsOperMthd}" category="CRS_OPER_MTHD" /></li>
									<c:choose>
										<c:when test="${item.crsOperType eq 'S' }">
											<li class="mr15"><spring:message code="course.title.createcourse.duration.edu"/> : ${item.enrlDaycnt }<spring:message code="common.title.day"/></li>
										</c:when>
										<c:otherwise>
											<c:if test="${item.crsOperMthd eq 'ON'}">
											<li class="mr15"><spring:message code="course.title.createcourse.method.online"/><spring:message code="course.title.createcourse.duration.edu"/> : <meditag:dateformat type="1" delimeter="." property="${item.startDttm}"/> ~ <meditag:dateformat type="1" delimeter="." property="${item.endDttm}"/></li>
											</c:if>
											<c:if test="${item.crsOperMthd eq 'OF'}">
											<li class="mr15"><spring:message code="course.title.createcourse.method.offline"/><spring:message code="course.title.createcourse.duration.edu"/> : <meditag:dateformat type="1" delimeter="." property="${item.startDttm}"/> ~ <meditag:dateformat type="1" delimeter="." property="${item.endDttm}"/></li>
											</c:if>
											<c:if test="${item.crsOperMthd eq 'BL'}">
											<li class="mr15"><spring:message code="course.title.createcourse.method.online"/><spring:message code="course.title.createcourse.duration.edu"/> : <meditag:dateformat type="1" delimeter="." property="${item.startDttm}"/> ~ <meditag:dateformat type="1" delimeter="." property="${item.endDttm}"/></li>
											<li class="mr15"><spring:message code="course.title.createcourse.method.offline"/><spring:message code="course.title.createcourse.duration.edu"/> : <meditag:dateformat type="1" delimeter="." property="${item.oflnStartDttm}"/> ~ <meditag:dateformat type="1" delimeter="." property="${item.oflnEndDttm}"/></li>
											</c:if>
										</c:otherwise>
									</c:choose>
									<li><spring:message code="student.title.student.status"/> :
										<c:if test="${item.enrlSts eq 'E'}"><spring:message code="student.title.student.status.enroll"/></c:if>
										<c:if test="${item.enrlSts eq 'S'}"><spring:message code="student.title.student.status.confirm"/></c:if>
										<c:if test="${item.enrlSts eq 'C'}"><spring:message code="student.title.student.status.complete"/></c:if>
										<c:if test="${item.enrlSts eq 'F'}"><spring:message code="student.title.student.status.failed"/></c:if>
										<c:if test="${item.enrlSts eq 'N'}"><spring:message code="student.title.student.status.cancel"/></c:if>
									</li>
								</ul>

								<div class="lecture-info" style="display: none;margin:10px 0px 10px 0px;list-style-type: none;" id="course_${item.crsCreCd }">

								</div>

							</li>
						</c:forEach>
						</ul>
					</div>
				</div>
				<div class="row" style="margin-bottom:20px;">
					<div class="col-lg-12">
						<meditag:paging pageInfo="${pageInfo}" funcName="listCourse"/>
					</div>
				</div>

<script type="text/javascript">
	$(document).ready(function(){

	});

	function listCourse(page) {
		document.location.href = "/home/main/listCourseHis?curPage="+page;
	}

	function printCerti(crsCreCd, stdNo) {
		// download용 iframe이 없으면 만든다.
		if ( $("#_m_pdf_iframe").length == 0 ) {
			iframeHtml =
				'<iframe id="_m_pdf_iframe" name="_m_pdf_iframe" style="visibility: none; display: none;"></iframe>';
			$("body").append(iframeHtml);
		}
		// 폼에 action을 설정하고 submit시킨다.
		var url = cUrl("/home/student/printCertificate?crsCreCd="+crsCreCd+"${AMPERSAND}stdNo="+stdNo);
		$("#_m_pdf_iframe").attr("src",url);
	}

	function showLecture(crsCreCd, stdNo){
		var btnClass = "down";
		if($("#btn_"+crsCreCd+"_"+stdNo).hasClass("fa-chevron-up") === true) {
			btnClass = "up";
		}
		if(btnClass == "down") {
			$('#course_'+crsCreCd).load(cUrl("/home/course/listCourseHisScore"), {
					"crsCreCd":crsCreCd,
					"stdNo":stdNo
				}, function () {
					var inHtml = $("#course_"+crsCreCd).html();
					if(inHtml.length > 2) {
						$("#btn_"+crsCreCd+"_"+stdNo).removeClass("fa-chevron-down");
						$("#btn_"+crsCreCd+"_"+stdNo).addClass("fa-chevron-up");
					}
				}
			);
			$("#course_"+crsCreCd).show();
		} else {
			$("#btn_"+crsCreCd+"_"+stdNo).removeClass("fa-chevron-up");
			$("#btn_"+crsCreCd+"_"+stdNo).addClass("fa-chevron-down");
			$("#course_"+crsCreCd).hide();
		}
	}

</script>

