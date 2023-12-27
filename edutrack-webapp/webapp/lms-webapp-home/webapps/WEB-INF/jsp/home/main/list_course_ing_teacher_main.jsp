<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="img_base" value="/img/home" scope="request"/>
<c:set var="paging" value="Y"/>
<c:set var="listCourse" value="Y"/>

				<div class="row">
					<form name="Search" onsubmit="return false;" class="form-inline">
					<div class="col-md-2 col-xs-4" style="padding-right:2px;">
						<div class="input-group" style="float:left">
							<div class="input-group-btn btn-group">
								<button type="button" class="btn btn-default btn-sm dropdown-toggle" data-toggle="dropdown">
										<span class="caret"></span>
								</button>
								<ul class="dropdown-menu" role="menu">
								<c:forEach var="year" items="${yearList}">
									<li><a href="javascript:setYear('${year}')">${year}</a></li>
								</c:forEach>
								</ul>
							</div>
							<input type="text" style="min-width:60px;" id="tarYear" class="_enterBind form-control input-sm" title="<spring:message code="common.title.input.year"/>" value="${tarYear}"/>
						</div>
					</div>
					<div class="col-md-4 col-xs-8" style="padding-left:2px;">
						<div class="input-group">
							<input type="text" id="crsCreNm" class="_enterBind form-control input-sm" title="<spring:message code="common.title.input.coursename"/>" value="${crsCreNm}"/>
							<span class="input-group-addon btn_search" style="cursor:pointer">
								<i class="fa fa-search"></i>
							</span>
						</div>
					</div>
					</form>
				</div>
				<div class="row" style="margin-top:5px;">
					<div class="col-md-12">
						<ul class="list-group">
						<c:if test="${empty courseList}">
							<li class="list-group-item">
								<spring:message code="course.message.createcourse.teaching.nodata"/>
							</li>
						</c:if>
						<c:forEach var="item" items="${courseList}" varStatus="status">
							<li class="list-group-item">
								<h4 class="list-group-item-heading">
									${item.crsCreNm}
									<small>[${item.creYear} / ${item.creTerm}]</small>
									<span class="pull-right">
										<c:if test="${item.connectYn eq 'Y' || item.connectYn eq 'C'}">
											<c:choose>
				 							<c:when test="${fn:contains(USERTYPE, 'GROUP') == true}">
												<a href="<c:url value="/lec/main/goLecture?crsCreCd=${item.crsCreCd}&amp;stdNo=GROUP" />" class="btn btn-warning btn-xs"><spring:message code="common.title.enter"/></a>
											</c:when>
											<c:otherwise>
												<a href="<c:url value="/lec/main/goLecture?crsCreCd=${item.crsCreCd}&amp;stdNo=${item.stdNo}" />" class="btn btn-warning btn-xs"><spring:message code="common.title.enter"/></a>
											</c:otherwise>
											</c:choose>
										</c:if>
										<c:if test="${item.connectYn eq 'N' }">
										</c:if>
									</span>
								</h4>
								<ul class="list-inline" style="margin-top:10px;width:92%;">
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
									<li><spring:message code="course.title.createcourse.enrollcnt"/> : ${item.stuCnt}</li>
								</ul>
							</li>
						</c:forEach>
						</ul>
					</div>
				</div>


<script type="text/javascript">
	$(document).ready(function(){
		$("._enterBind").bind("keydown", function(event){
			if($M.Check.Event.isEnter(event)) {
				listCourse(1);
			}
		});

		$(".btn_search").bind("click", function(event) {
			listCourse(1);
		});
	});

	function listCourse(page) {
		var tarYear = $("#tarYear").val();
		var crsCreNm = $("#crsCreNm").val();
		document.location.href = cUrl("/home/main/listCourseIngTeacherMain")+"?curPage="+page+"${AMPERSAND}tarYear="+tarYear+"${AMPERSAND}crsCreNm="+crsCreNm;
	}

	function setYear(year) {
		$("#tarYear").val(year);
	}
</script>

