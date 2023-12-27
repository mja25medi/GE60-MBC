<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:if test="${not empty courseList }">
	<div class="well well-sm">
		<ul>
		<c:forEach var="item" items="${courseList}">
				<li style="line-height:30px;">
				<c:choose>
					<c:when test="${item.courseType eq 'EXAM' }">
						<c:if test="${item.courseStareYn eq 'Y' }"><i class="fa fa-check-square-o"></i></c:if>
						<c:if test="${item.courseStareYn eq 'N' }"><i class="fa fa-square-o"></i></c:if>
						<img src="/img/ToDoList/<spring:message code="course.src.exam.img"/>" alt="<spring:message code="lecture.title.classroom.status.exam"/>">
						${fn:substring(item.courseTitle,0, 23)}<c:if test="${fn:length(item.courseTitle) > 23 }">...</c:if>
						<span class="pull-right">
						<spring:message code="board.title.popup.enddate"/> : <meditag:dateformat type="1" delimeter="." property="${item.courseEndDttm}"/>&nbsp;|&nbsp;
						<c:if test="${item.courseStareYn eq 'Y' }"><spring:message code="lecture.title.exam.stareyn"/> : <font color="red"><spring:message code="lecture.title.exam.starey"/></font> </c:if>
						<c:if test="${item.courseStareYn eq 'N' }"><spring:message code="lecture.title.exam.stareyn"/> : <font color="black"><spring:message code="lecture.title.exam.staren"/></font> </c:if>
						</span>
					</c:when>
					<c:when test="${item.courseType eq 'FORUM' }">
						<c:if test="${item.atclCount != 0 && item.cmntCount != 0 }"><i class="fa fa-check-square-o"></i></c:if>
						<c:if test="${item.atclCount == 0 && item.cmntCount == 0 }"><i class="fa fa-square-o"></i></c:if>
						<img src="/img/ToDoList/<spring:message code="course.src.forum.img"/>" alt="<spring:message code="lecture.title.classroom.status.resh"/>">
						${fn:substring(item.courseTitle,0, 23)}<c:if test="${fn:length(item.courseTitle) > 23 }">...</c:if>
						<span class="pull-right">
						<spring:message code="board.title.popup.enddate"/> : <meditag:dateformat type="1" delimeter="." property="${item.courseEndDttm}"/>&nbsp;|&nbsp;
						<spring:message code="lecture.title.forum.write.cnt"/> : ${item.atclCount }/${item.cmntCount }<spring:message code="common.title.cnt.ea"/>
						</span>
					</c:when>
					<c:when test="${item.courseType eq 'ASMT' }">
						<c:if test="${item.courseStareYn eq 'Y' }"><i class="fa fa-check-square-o"></i></c:if>
						<c:if test="${item.courseStareYn eq 'N' }"><i class="fa fa-square-o"></i></c:if>
						<img src="/img/ToDoList/<spring:message code="course.src.asmt.img"/>" alt="<spring:message code="lecture.title.classroom.status.asmt"/>">
						${fn:substring(item.courseTitle,0, 23)}<c:if test="${fn:length(item.courseTitle) > 23 }">...</c:if>
						<span class="pull-right">
						<spring:message code="board.title.popup.enddate"/> : <meditag:dateformat type="1" delimeter="." property="${item.courseEndDttm}"/>&nbsp;|&nbsp;
						<c:if test="${item.courseStareYn eq 'Y' }"><spring:message code="lecture.title.assignment.sendyn"/> : <font color="red"><spring:message code="lecture.title.assignment.sendy"/></font> </c:if>
						<c:if test="${item.courseStareYn eq 'N' }"><spring:message code="lecture.title.assignment.sendyn"/> : <font color="black"><spring:message code="lecture.title.assignment.sendn"/></font> </c:if>
						</span>
					</c:when>
					<c:when test="${item.courseType eq 'RESH' }">
						<c:if test="${item.courseStareYn eq 'Y' }"><i class="fa fa-check-square-o"></i></c:if>
						<c:if test="${item.courseStareYn eq 'N' }"><i class="fa fa-square-o"></i></c:if>
						<img src="/img/ToDoList/<spring:message code="course.src.resh.img"/>" alt="<spring:message code="lecture.title.classroom.status.resh"/>">
						${fn:substring(item.courseTitle,0, 23)}<c:if test="${fn:length(item.courseTitle) > 23 }">...</c:if>
						<span class="pull-right">
						<spring:message code="board.title.popup.enddate"/> : <meditag:dateformat type="1" delimeter="." property="${item.courseEndDttm}"/>&nbsp;|&nbsp;
						<c:if test="${item.courseStareYn eq 'Y' }"><spring:message code="lecture.title.resh.sendyn"/> : <font color="red"><spring:message code="lecture.title.resh.sendy"/></font> </c:if>
						<c:if test="${item.courseStareYn eq 'N' }"><spring:message code="lecture.title.resh.sendyn"/> : <font color="black"><spring:message code="lecture.title.resh.sendn"/></font> </c:if>
						</span>
					</c:when>
				</c:choose>
				<div class="clearfix"></div>
			</li>
		</c:forEach>
		</ul>
	</div>
</c:if>