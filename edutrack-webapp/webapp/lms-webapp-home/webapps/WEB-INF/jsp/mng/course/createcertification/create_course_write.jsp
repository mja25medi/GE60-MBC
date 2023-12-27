<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="gubun" value="${gubun}"/>
<c:set var="courseVO" value="${courseVO}"/>
<c:set var="createCourseVO" value="${vo}"/>
	<form id="createCourseFrom" name="createCourseFrom" onsubmit="return false">
		<input type="hidden" name="crsCd" id="crsCd" value="${vo.crsCd }"/>
		<input type="hidden" name="crsCreCd" id="crsCreCd" value="${vo.crsCreCd }"/>
		<input type="hidden" name="creOperTypeCd" id="creOperTypeCd" value="${vo.creOperTypeCd }"/>
		<input type="submit" value="submit" style="display:none" />
		<div style="height:15px;"></div>
		<table summary="<spring:message code="course.title.createcourse.manage"/>" class="table table-striped table-bordered">
			<colgroup>
				<col style="width:30%"/>
				<col style="width:70%"/>
			</colgroup>
			<tr>
				<th scope="row"><span style="color:red;">* </span><label for="crsNm">회차명</label></th>
				<td>
					<div class="input-group" style="float:left;">
						${courseVO.crsNm}
					</div>
				</td>
			</tr>
			<tr>
				<th scope="row"><span style="color:red;">* </span><label for="crsCreNm"><spring:message code="course.title.course.name"/></label></th>
				<td>
					<input type="text" maxlength="100" name="crsCreNm" id="crsCreNm" class="form-control input-sm" value="${vo.crsCreNm}">
				</td>
			</tr>
			<tr>
				<th scope="row"><span style="color:red;">* </span>과정설명</th>
				<td>
					<textarea id="creDesc" name="creDesc" style="height:80px" class="form-control input-sm" >${vo.creDesc}</textarea>
				</td>
			</tr>
		</table>
	</form>
	<div class="text-right" style="margin-top:10px;">
		<c:if test="${gubun eq 'A'}">
			<button class="btn btn-primary" onclick="addCreateCourse()"><spring:message code="button.add"/></button>
		</c:if>
		<c:if test="${gubun eq 'E'}">
			<button class="btn btn-primary" onclick="editCreateCourse()"><spring:message code="button.add"/></button>
			<button class="btn btn-warning" onclick="delCreateCourse()"><spring:message code="button.delete"/></button>
		</c:if>
		<button class="btn btn-default" onclick="parent.modalBoxClose()"><spring:message code="button.close"/></button>
	</div>

<script type="text/javascript">

	$(document).ready(function() {

	});

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		$('#createCourseFrom').attr("action","/mng/course/createCertification/"+cmd);
		$('#createCourseFrom').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		if(resultDTO.result >= 0) {
			alert(resultDTO.message);
			parent.listCreateCourse();
			parent.modalBoxClose();
		} else {
			// 비정상 처리
		}
	}


	/**
	 * 과정 개설 등록
	 */
	function addCreateCourse() {
		process("addCreateCourse");	// cmd
	}
	
	/**
	 * 과정 개설 수정
	 */
	function editCreateCourse() {
		process("editCreateCourse");
	}

	/**
	 * 과정 개설 삭제
	 */
	function delCreateCourse() {
		if(confirm("과정과 연결된 응시자 정보도 모두 삭제됩니다. \n과정 정보를 삭제하시겠습니까?")) {
			process("deleteCreateCourse");	// cmd
		} else {
			return;
		}
	}
	
</script>