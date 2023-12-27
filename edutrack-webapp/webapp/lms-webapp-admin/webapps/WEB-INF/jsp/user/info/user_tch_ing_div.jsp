<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>

	<div class="tabbox" style="width:100%;float:left;position: fixed; background-color:#FFFFFF; ">
		<ul class="nav nav-tabs">
  			<li><a href="/adm/user/info/viewUserPop?userNo=${vo.userNo}"><spring:message code="user.title.userinfo.user.info.tab"/></a></li>
  			<c:if test="${tutorYn eq 'Y' || teacherYn eq 'Y'}">
 			<li class="active"><a href="/adm/user/info/viewTchIngPop?userNo=${vo.userNo}"><spring:message code="user.title.userinfo.tch.history.tab"/></a></li>
 			</c:if>
		</ul>
	</div>
<div id="infoArea" style="float:left;width:100%; margin-top:50px;">
	<table summary="<spring:message code="student.title.course.ing"/>" class="table table-bordered wordbreak">
		<colgroup>
			<col style="width:auto;"/>
			<col style="width:120px;"/>
			<col style="width:350px;"/>
			<!--
			<col style="width:80px;"/>
			<col style="width:80px;"/>
			 -->
		</colgroup>
		<thead>
			<tr>
				<th><spring:message code="course.title.course.name"/></th>
				<th><spring:message code="course.title.createcourse.creyear"/>/<spring:message code="course.title.createcourse.creterm"/></th>
				<th><spring:message code="course.title.createcourse.duration.edu"/></th>
				<%--
				<th><spring:message code="student.title.result.study.ratio"/></th>
				<th><spring:message code="student.title.student.status"/></th>
				 --%>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${courseListIng}">
			<tr>
				<td class="left" style="margin-left:5px;">${item.crsCreNm}</td>
				<td>${item.creYear}<spring:message code="common.title.year"/>/${item.creTerm}</td>
				<td>
					<c:choose>
						<c:when test="${item.crsOperType eq 'S' }">
							<meditag:dateformat type="1" delimeter="." property="${item.enrlAplcStartDttm }"/> ~ <meditag:dateformat type="1" delimeter="." property="${item.enrlAplcEndDttm }"/>/${item.enrlDaycnt }<spring:message code="common.title.day"/>
						</c:when>
						<c:otherwise>
							<c:if test="${item.crsOperMthd eq 'ON' || item.crsOperMthd eq 'OF'}">
								<meditag:dateformat type="1" delimeter="." property="${item.startDttm}"/> ~ <meditag:dateformat type="1" delimeter="." property="${item.endDttm}"/>
							</c:if>
							<c:if test="${item.crsOperMthd eq 'BL'}">
								<spring:message code="course.title.createcourse.cyber"/> : <meditag:dateformat type="1" delimeter="." property="${item.startDttm}"/> ~ <meditag:dateformat type="1" delimeter="." property="${item.endDttm}"/> <br/>
								<spring:message code="course.title.createcourse.offline"/> : <meditag:dateformat type="1" delimeter="." property="${item.oflnStartDttm}"/> ~ <meditag:dateformat type="1" delimeter="." property="${item.oflnEndDttm}"/>
							</c:if>

						</c:otherwise>
					</c:choose>
				</td>
				<%--
				<td>${item.prgrRatio}%</td>
				<td>
					<c:if test="${item.enrlSts eq 'E'}"><spring:message code="student.title.student.status.enroll"/></c:if>
					<c:if test="${item.enrlSts eq 'S'}"><spring:message code="student.title.student.status.study"/></c:if>
					<c:if test="${item.enrlSts eq 'C'}"><spring:message code="student.title.student.status.complete"/></c:if>
					<c:if test="${item.enrlSts eq 'F'}"><spring:message code="student.title.student.status.failed"/></c:if>
				</td>
				 --%>
			</tr>
			</c:forEach>
			<c:if test="${empty courseListIng}">
			<tr>
				<td colspan="5"><spring:message code="teacher.message.course.ing.nodata"/></td>
			</tr>
			</c:if>
		</tbody>
	</table>
	</div>