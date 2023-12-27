<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="teacherVO" value="${vo}"/>
	<br>
	<form id="createCourseTeacherForm" name="createCourseTeacherForm" onsubmit="return false" >
	<input type="hidden" name="crsCreCd" value="${vo.crsCreCd }" />
	<input type="hidden" name="userNo" value="${vo.userNo }" />
	<table summary="<spring:message code="course.title.teacher.manage"/>" class="table_dtl" style="width:96%" align="center">
		<colgroup>
			<col style="width:20%" />
			<col style="width:80%" />
		</colgroup>
		<tbody>
		<tr>
			<th class="top"><spring:message code="user.title.userinfo.name"/></th>
			<td class="top">
				${teacherVO.userNm}
			</td>
		</tr>
		<tr>
			<th><label for="tchRateScore"><spring:message code="course.title.teacher.score"/></label></th>
			<td>
				<input type="text" style="width:30px" dispName="<spring:message code="course.title.teacher.score"/>" maxlength="3" isNull="N" lenCheck="3" name="tchRateScore" value="${vo.tchRateScore }" class="txt" id="tchRateScore"/><spring:message code="common.title.score"/>
			</td>
		</tr>
		</tbody>
	</table>
	<meditag:buttonwrapper style="padding: 6px; width: 96%">
		<meditag:button value="button.add" title="button.add" func="saveScore();" />
		<meditag:button value="button.close" title="button.close" func="parent.tchPopBox.close();" />
	</meditag:buttonwrapper>
	<input type="submit" value="submit" style="display:none" />
	</form>

<script type="text/javascript">

	var ItemDTO = new Object();
	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		if(!validate(document.createCourseTeacherForm)) return;

		$('#createCourseTeacherForm').attr("action","/mng/course/createCourse/teacher/" + cmd);
		$('#createCourseTeacherForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		alert(resultDTO.message);

		if(resultDTO.result >= 0) {
			// 정상 처리
			parent.subWorkFrame.listTeacher();
			parent.tchPopBox.close();
		} else {
			// 비정상 처리
		}
	}

	/**
	 * 과정 등록
	 */
	function saveScore() {
		process("addTeacherScore");	// cmd
	}
</script>
