<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<!-- <c:set var="gubun" value="${createCourseForm.gubun}"/> -->

		<form id="createCourseFrom" name="createCourseFrom" onsubmit="return false" action="/mng/course/createCourse">
		<input type="hidden" name="crsCreCd" value="${createCourseVO.crsCreCd }" />
		<div id="viewCreateCourse" style="display:block">
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
					<th class="top" scope="row"><spring:message code="course.title.createcourse.creyear"/> / <spring:message code="course.title.createcourse.creterm"/></th>
					<td class="top">
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
			<div class="text-right" style="margin-top:10px;">
				<button class="btn btn-default" onclick="listCreateCourse('', '')" ><spring:message code="button.list"/></button>
			</div>
		</div>
		<input type="submit" value="submit" style="display:none" />
		</form>
		<ul class="nav nav-tabs" id="tabMenuStd">
  			<li class="active"><a href="javascript:onclickTab(9)">성적관리</a></li>
  			<li><a href="javascript:onclickTab(2)"><spring:message code="student.title.manage.complete.tab"/></a></li>
		</ul>
		<iframe name="subWorkFrame" id="subWorkFrame" frameborder="0" src="about:blank" scrolling="no" title="Sub Work Frame" style="width:100%;min-height:300px;"></iframe>
	</div>
<script type="text/javascript">
	var ItemDTO = new Object();

	$(document).ready(function() {
		$('.inputNumber').inputNumber();	// 숫자만 입력되도록 설정.
		//-- 텝 초기 페이지 호출
		ItemDTO.crsCreCd = '${createCourseVO.crsCreCd}';
		ItemDTO.crsCreNm = '${createCourseVO.crsCreNm}';

		$('#tabMenuStd a').click(function (e) {
			  $(this).tab('show');
			})
		onclickTab(9);
	});

	function resizeForm() {
		var iframeObj = parent.document.getElementById("addCreateFrame");
		parent.resizeIframe3(iframeObj, $(document).height());
	}

	function onclickTab(tab) {
		var url = {};
		url['0'] = "/mng/std/student/enrollStudentForm?crsCreCd="+ItemDTO.crsCreCd;
		url['1'] = "/mng/std/student/eduNoStudentForm?crsCreCd="+ItemDTO.crsCreCd;
		url['2'] = "/mng/std/student/completeStudentForm?crsCreCd="+ItemDTO.crsCreCd;
		url['3'] = "/mng/std/student/declsStudentForm?crsCreCd="+ItemDTO.crsCreCd;
		url['4'] = "/mng/std/student/repayStudentForm?crsCreCd="+ItemDTO.crsCreCd;
		url['4'] = "/mng/std/student/repayStudentForm?crsCreCd="+ItemDTO.crsCreCd;
		url['9'] = "/mng/std/eduResult/resultMain?crsCreCd="+ItemDTO.crsCreCd;

		document.getElementById('subWorkFrame').contentWindow.location.href=cUrl(url[tab]);
		//document.subWorkFrame.location.href = cUrl(url[tab]);
	}

</script>
