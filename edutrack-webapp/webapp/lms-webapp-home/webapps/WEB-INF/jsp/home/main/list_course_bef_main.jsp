<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="img_base" value="/img/home" scope="request"/>
<c:set var="paging" value="Y"/>

				<div class="row">
					<div class="col-lg-12">
						<ul class="list-group">
						<c:if test="${empty courseList}">
							<li class="list-group-item">
								<spring:message code="course.message.createcourse.enroll.nodata"/>
							</li>
						</c:if>
						<c:forEach var="item" items="${courseList}" varStatus="status">
							<li class="list-group-item">
								<h4 class="list-group-item-heading">
									<a href="<c:url value="/home/student/viewEnroll"/>?stdNo=${item.stdNo}" title="<spring:message code="course.title.createcourse.view.courseinfo"/>">${item.crsCreNm}</a>
									<small>[${item.creYear} / ${item.creTerm}]</small>
									<span class="pull-right">
										<a href="javascript:cancelEnroll('${item.crsCreCd}','${item.stdNo}');" class="btn btn-warning btn-xs"><spring:message code="student.title.student.cancel.enroll"/></a>
									</span>
								</h4>
								<ul class="list-inline" style="margin-top:10px;">
									<li class="mr15"><spring:message code="course.title.course.edumthd"/> : <meditag:codename code="${item.crsOperMthd}" category="CRS_OPER_MTHD" /></li>
									<li class="mr15"><spring:message code="student.title.student.enrolldate"/> : <meditag:dateformat type="1" delimeter="." property="${item.enrlAplcDttm}"/></li>
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
										<c:if test="${item.enrlSts eq 'N'}"><spring:message code="student.title.student.status.cancel"/></c:if>
									</li>
								</ul>
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

	function cancelEnroll(crsCreCd, stdNo) {
		if(!confirm('<spring:message code="student.message.student.confirm.std.enroll.cancel"/>')) {
			return;
		} else {
			document.location.href = cUrl("/home/student/readFormCancel")+"?crsCreCd="+crsCreCd+"${AMPERSAND}stdNo="+stdNo;
		}
	}

	function listCourse(page) {
		document.location.href = "/home/main/listCourseBefMain?curPage="+page;
	}

</script>

