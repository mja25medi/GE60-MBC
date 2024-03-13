<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="createCourseVO" value="${vo}"/>
<c:set var="courseVO" value="${courseVO}"/>

		<table summary="<spring:message code="course.title.createcourse.manage"/>" class="table table-bordered wordbreak">
			<colgroup>
				<col style="width:20%"/>
				<col style="width:30%"/>
				<col style="width:20%"/>
				<col style="width:30%"/>
			</colgroup>
			<tr height="35">
				<th class="top" scope="row"><spring:message code="course.title.course.name"/></th>
				<td class="top">
					${createCourseVO.crsCreNm}
				</td>
				<th class="top" scope="row"><spring:message code="course.title.createcourse.creterm"/></th>
				<td class="top">
					${createCourseVO.creTerm} 회차
				</td>
			</tr>
			<th scope="row"><spring:message code="course.title.createcourse.duration.aplc"/></th>
				<td>
					${createCourseVO.enrlAplcStartDttm} ~ ${createCourseVO.enrlAplcEndDttm}
				</td>
				<th scope="row">
					<spring:message code="course.title.createcourse.duration.edu"/>
				</th>
				<td>
					${createCourseVO.enrlStartDttm} ~ ${createCourseVO.enrlEndDttm}
				</td>
			</tr>
			<%-- <tr>
				<th scope="row"><spring:message code="course.title.createcourse.duration.aplc"/></th>
				<td>
					${createCourseVO.enrlAplcStartDttm} ~ ${createCourseVO.enrlAplcEndDttm}
				</td>
				<th scope="row">
					<c:if test="${courseVO.crsOperMthd eq 'BL' || courseVO.crsOperMthd eq 'ON' }"><spring:message code="course.title.createcourse.method.online"/></c:if>
					<c:if test="${courseVO.crsOperMthd eq 'OF' }"><spring:message code="course.title.createcourse.method.offline"/></c:if>
					<spring:message code="course.title.createcourse.duration.edu"/>
				</th>
				<td>
					<c:if test="${courseVO.crsOperType eq 'R'}">
					${createCourseVO.enrlStartDttm} ~ ${createCourseVO.enrlEndDttm}
					</c:if>
					<c:if test="${courseVO.crsOperType eq 'S'}">
						${createCourseVO.enrlDaycnt} <spring:message code="common.title.day"/>
					</c:if>
				</td>
			</tr>
			<c:if test="${courseVO.crsOperMthd eq 'BL'}">
			<tr>
				<th scope="row"><spring:message code="course.title.createcourse.duration.offedu"/></th>
				<td colspan="3">
					${createCourseVO.oflnStartDttm} ~ ${createCourseVO.oflnEndDttm}
				</td>
			</tr>
			</c:if> --%>
		</table>
		<div style="width:100%;margin-top:10px;margin-bottom:10px;" class="text-right">
			<button class="btn btn-default btn-sm" onclick="listCreateCourse()"><spring:message code="button.list"/></button>
		</div>
		<ul class="nav nav-tabs" id="tabMenuOper">
			<meditag:tab pageCode="course_manage" tabCode="subject" className="active" authGroup="${MNGTYPE}" messageKey="course.title.course.manage.subject.tab" funcName="onclickTab(2)"/>
			<meditag:tab pageCode="course_manage" tabCode="teacher" className="" authGroup="${MNGTYPE}" messageKey="담임/부담임 관리" funcName="onclickTab(4)"/>
			<%-- <li><a href="javascript:onclickTab(5)"><spring:message code="course.title.createcourse.manage.decls.tab"/></a></li> --%>
			<meditag:tab pageCode="course_manage" tabCode="student" className="" authGroup="${MNGTYPE}" messageKey="student.title.student.manage" funcName="onclickTab(7)"/>
			<meditag:tab pageCode="course_manage" tabCode="exam" className="" authGroup="${MNGTYPE}" messageKey="course.title.createcourse.manage.exam.tab" funcName="onclickTab(0)"/>
			<meditag:tab pageCode="course_manage" tabCode="asmt" className="" authGroup="${MNGTYPE}" messageKey="course.title.createcourse.manage.asmt.tab" funcName="onclickTab(1)"/>
  			<%-- <li><a href="javascript:onclickTab(2)"><spring:message code="course.title.createcourse.manage.forum.tab"/></a></li> --%>
			<meditag:tab pageCode="course_manage" tabCode="resh" className="" authGroup="${MNGTYPE}" messageKey="course.title.createcourse.manage.resh.tab" funcName="onclickTab(3)"/>
  			<%-- <li><a href="javascript:onclickTab(6)"><spring:message code="course.title.createcourse.manage.semiExam.tab"/></a></li> --%>
 			<meditag:tab pageCode="course_manage" tabCode="score" className="" authGroup="${MNGTYPE}" messageKey="course.title.createcourse.manage.score.tab" funcName="onclickTab(9)"/>
  			<%-- <c:if test="${createCourseVO.creTypeCd eq 'OF' }">
  				<li><a href="javascript:onclickTab(7)">출결관리</a></li>
  			</c:if> --%>
		</ul>
		<iframe name="subWorkFrame" id="subWorkFrame" frameborder="0" src="about:blank" scrolling="yes" title="Sub Work Frame" style="width:100%;min-height:100vh;"></iframe>

<script type="text/javascript">

	var ItemDTO = new Object();

	$(document).ready(function() {
		$('.inputNumber').inputNumber();	// 숫자만 입력되도록 설정.
		//-- 텝 초기 페이지 호출
		ItemDTO.crsCd = "${courseVO.crsCd}";
		ItemDTO.crsCreCd = "${createCourseVO.crsCreCd}";
		ItemDTO.crsCreNm = "${createCourseVO.crsCreNm}";
		ItemDTO.creOperTypeCd = "${createCourseVO.creOperTypeCd}";

		$('#tabMenuOper a').click(function (e) {
			  $(this).tab('show');
			})
		onclickTab(2);
	});

	function onclickTab(tab) {
		var url = {};
		url['0'] = "/mng/lecture/exam/examMain?crsCreCd="+ItemDTO.crsCreCd+"&semiExamYn=N";
		url['1'] = "/mng/lecture/assignment/asmtMain?crsCreCd="+ItemDTO.crsCreCd;
		/* url['2'] = "/mng/lecture/forum/main?crsCreCd="+ItemDTO.crsCreCd; */
		url['2'] = "/mng/course/createCourse/subject/subjectMain?crsCreCd="+ItemDTO.crsCreCd;
		url['3'] = "/mng/course/creCrs/resh/main?crsCreCd="+ItemDTO.crsCreCd+"&creOperTypeCd="+ItemDTO.creOperTypeCd;
		url['4'] = "/mng/course/createCourse/teacher/teacherMain?crsCreCd="+ItemDTO.crsCreCd;
		url['5'] = "/mng/course/createCourse/decls/main?crsCreCd="+ItemDTO.crsCreCd;
		url['6'] = "/mng/lecture/exam/examMain?crsCreCd="+ItemDTO.crsCreCd+"&semiExamYn=Y";
		url['7'] = "/mng/course/std/courseStdPayMain?crsCreCd="+ItemDTO.crsCreCd; // 수강생관리
		url['9'] = "/mng/std/eduResult/resultMain?crsCreCd="+ItemDTO.crsCreCd;

		document.getElementById('subWorkFrame').contentWindow.location.href=cUrl(url[tab]);
		//document.subWorkFrame.location.href = cUrl(url[tab]);
	}
	
	function listCreateCourse(){
		location.href = "/mng/main/goMenuPage?mcd=MC10000007";
	}
</script>
