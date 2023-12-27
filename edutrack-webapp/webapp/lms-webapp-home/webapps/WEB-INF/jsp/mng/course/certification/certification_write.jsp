<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="gubun" value="${gubun}"/>
<c:set var="vo" value="${vo}"/>


	<form id="courseForm" name="courseForm" onsubmit="return false">
	<input type="hidden" name="crsCd" id="crsCd" value="${vo.crsCd}" /> 
	<input type="hidden" name="cmd" id="cmd"/> 
	<table summary="<spring:message code="course.title.course.manage"/>" class="table table-striped table-bordered">
		<colgroup>
			<col style="width:30%"/>
			<col style="width:auto"/>
		</colgroup>
		<tr >
			<th scope="row"><span style="color:red;">* </span><label for="creYear">회차명</label></th>
			<td >
				<input type="text" id="crsNm" name="crsNm" value="${vo.crsNm}" style="width:90%;" maxlength="20" isNull="N"   class="form-control input-sm" />
			</td>
		</tr>
		<tr >
			<th scope="row"><span style="color:red;">* </span><label for="enrlStartDttm">운영 기간</label></th>
			<td >
				<div class="input-group" style="float:left;width:150px;">
					<input type="text" maxlength="10" name="enrlStartDttm" id="enrlStartDttm" value="${vo.enrlStartDttm}" class="inputDate form-control input-sm" autocomplete="off"/>
					<span class="input-group-addon btn-sm" onclick="_clickCalendar('enrlStartDttm')" style="cursor:pointer">
						<i class="fa fa-calendar"></i>
					</span>
				</div>
				<div class="input-group text-center" style="float:left;padding-left:10px; padding-right:10px;"> ~ </div>
				<div class="input-group" style="float:left;width:150px;">
					<input type="text" maxlength="10" name="enrlEndDttm" id="enrlEndDttm" value="${vo.enrlEndDttm}" class="inputDate form-control input-sm" autocomplete="off"/>
					<span class="input-group-addon btn-sm" onclick="_clickCalendar('enrlEndDttm')" style="cursor:pointer">
						<i class="fa fa-calendar"></i>
					</span>
				</div>
				<meditag:datepicker name1="enrlStartDttm" name2="enrlEndDttm" />				
			</td>
		</tr>
		<tr >
			<th scope="row"><span style="color:red;">* </span><label for="scoreStartDttm">자격증 신청기간</label></th>
			<td >
				<div class="input-group" style="float:left;width:150px;">
					<input type="text" maxlength="10" name="scoreOpenStartDttm" id="scoreOpenStartDttm" value="${vo.scoreOpenStartDttm}" class="inputDate form-control input-sm" autocomplete="off"/>
					<span class="input-group-addon btn-sm" onclick="_clickCalendar('scoreOpenStartDttm')" style="cursor:pointer">
						<i class="fa fa-calendar"></i>
					</span>
				</div>
				<div class="input-group text-center" style="float:left;padding-left:10px; padding-right:10px;"> ~ </div>
				<div class="input-group" style="float:left;width:150px;">
					<input type="text" maxlength="10" name="scoreOpenEndDttm" id="scoreOpenEndDttm" value="${vo.scoreOpenEndDttm}" class="inputDate form-control input-sm" autocomplete="off"/>
					<span class="input-group-addon btn-sm" onclick="_clickCalendar('scoreOpenEndDttm')" style="cursor:pointer">
						<i class="fa fa-calendar"></i>
					</span>
				</div>
				<meditag:datepicker name1="scoreOpenStartDttm" name2="scoreOpenEndDttm" />
			</td>
		</tr>
	</table>
	<div class="text-right" style="margin-bottom:20px;">
		<c:if test="${gubun eq 'A'}">
			<button class="btn btn-primary btn-sm" onclick="addCourse()"><spring:message code="button.add"/></button>
		</c:if>
		<c:if test="${gubun eq 'E'}">
			<button class="btn btn-primary btn-sm" onclick="editCourse()"><spring:message code="button.add"/></button>
			<button class="btn btn-warning btn-sm" onclick="deleteCourse()"><spring:message code="button.delete"/></button>
		</c:if>
		<button class="btn btn-default btn-sm" onclick="parent.modalBoxClose()"><spring:message code="button.close"/></button>
	</div>
	</form>
		
<script type="text/javascript">

	$(document).ready(function() {
		$('.inputNumber').inputNumber();	// 숫자만 입력되도록 설정.
	});

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		$("#cmd").val(cmd);
		$('#courseForm').attr("action","/mng/course/certification/"+ cmd);
		$('#courseForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		if(resultDTO.result >= 0) {
			alert(resultDTO.message);
			parent.listCourse(1);
			parent.modalBoxClose();
		} else {
			// 비정상 처리
			alert(resultDTO.message);
		}
	}

	/**
	 * 기수 등록
	 */
	function addCourse() {
		if(!validate(document.courseForm)) return;
		process("addCourse");	// cmd
	}

	/**
	 * 기수 수정
	 */
	function editCourse() {
		if(!validate(document.courseForm)) return;
		process("editCourse");	// cmd
	}

	/**
	 * 기수 삭제
	 */
	function deleteCourse() {
		process("deleteCourse");	// cmd
	}

</script>
