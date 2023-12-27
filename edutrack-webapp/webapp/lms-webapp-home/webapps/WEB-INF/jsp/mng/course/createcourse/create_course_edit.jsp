<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="gubun" value="${gubun}"/>
<c:set var="createCourseVO" value="${vo}"/>
<c:set var="courseVO" value="${courseVO}"/>


		<div id="creCrsViewLayer" style="display:block">
			<table summary="<spring:message code="course.title.createcourse.manage"/>" class="table table-bordered wordbreak">
				<colgroup>
					<col style="width:20%"/>
					<col style="width:30%"/>
					<col style="width:20%"/>
					<col style="width:30%"/>
				</colgroup>
				<tr>
					<th scope="row"><spring:message code="course.title.course.name"/></th>
					<td >
						${createCourseVO.crsCreNm}
					</td>
					<th scope="row"><spring:message code="course.title.createcourse.creyear"/> / <spring:message code="course.title.createcourse.creterm"/></th>
					<td >
						${createCourseVO.creYear}<spring:message code="common.title.year"/> / ${createCourseVO.creTerm}
					</td>
				</tr>
				<tr>
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
				</c:if>
			</table>
			<div style="width:100%;margin-top:10px;margin-bottom:10px;" class="text-right">
				<button class="btn btn-primary btn-sm" onclick="parent.editCreCrsPop('${createCourseVO.crsCreCd}')" ><spring:message code="button.edit"/></button>
				<button class="btn btn-default btn-sm" onclick="listCreateCourse('', '');"><spring:message code="button.list"/></button>
			</div>
		</div>
		<ul class="nav nav-tabs" id="tabMenuInfo">
  			<li class="active"><a href="javascript:onclickTab(0)"><spring:message code="course.title.createcourse.manage.teacher.tab"/></a></li>
  			<%-- <li><a href="javascript:onclickTab(1)"><spring:message code="course.title.createcourse.manage.subject.tab"/></a></li> --%>
  			<li><a href="javascript:onclickTab(2)"><spring:message code="course.title.createcourse.manage.decls.tab"/></a></li>
		</ul>
		<iframe name="subWorkFrame" id="subWorkFrame" frameborder="0" src="about:blank" scrolling="no" title="Sub Work Frame" style="width:100%;min-height:450px;"></iframe>

	</div>

<script type="text/javascript">
	var ItemDTO = new Object();

	$(document).ready(function() {
		$('.inputNumber').inputNumber();	// 숫자만 입력되도록 설정.
		$('.inputDate').inputDate();	// 날짜 형식만 입력되도록 설정.
		//-- 텝 초기 페이지 호출
		ItemDTO.crsCreCd = '${createCourseVO.crsCreCd}';
		ItemDTO.crsCreNm = '${createCourseVO.crsCreNm}';
		ItemDTO.crsOperMthd = '${courseVO.crsOperMthd}';

		$('#tabMenuInfo a').click(function (e) {
			  $(this).tab('show');
			})
		onclickTab(0);
	});


	function onclickTab(tab) {
		resetIframe3(document.getElementById("subWorkFrame"));
		var url = {};
		url['0'] = "/mng/course/createCourse/teacher/teacherMain?crsCreCd="+ItemDTO.crsCreCd;
		url['1'] = "/mng/course/createCourse/subject/subjectMain?crsCreCd="+ItemDTO.crsCreCd;
		url['2'] = "/mng/course/createCourse/decls/main?crsCreCd="+ItemDTO.crsCreCd;
		//url['3'] = "/OflnSbjTchManage.do?cmd=main&oflnSbjTchDTO.crsCreCd="+ItemDTO.crsCreCd;
		document.getElementById('subWorkFrame').contentWindow.location.href=cUrl(url[tab]);
		//document.subWorkFrame.location.href = cUrl(url[tab]);
	}
</script>
