<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="gubun" value="${gubun}"/>
<c:set var="vo" value="${vo}"/>


	<form id="courseForm" name="courseForm" onsubmit="return false">
	<input type="hidden" name="crsCd" id="crsCd" value="${vo.crsCd}" /> 
	<input type="hidden" name="crsNm" id="crsNm" value="${vo.crsNm}" /> 
	<input type="hidden" name="cmd" id="cmd"/> 
	<table summary="<spring:message code="course.title.course.manage"/>" class="table table-striped table-bordered">
		<colgroup>
			<col style="width:20%"/>
			<col style="width:30%"/>
			<col style="width:20%"/>
			<col style="width:30%"/>
		</colgroup>
		<tr >
			<th scope="row"><span style="color:red;">* </span><label for="creYear">기수 년도</label></th>
			<td>
				<input type="text" id="crsYear" name="crsYear" value="${vo.crsYear}" style="width:96%;" maxlength="4" isNull="N"   class="form-control input-sm" onkeyup="isChkNumber(this)"/>
			</td>
			<th scope="row"><span style="color:red;">* </span><label for="creTerm">기수</label></th>
			<td>
				<div class="input-group">
					<input type="text" id="crsTerm" name="crsTerm" value="${vo.crsTerm}"  style="width:120px;text-align:right;" maxlength="10" isNull="N"  class="form-control input-sm" onkeyup="isChkNumber(this)" />
				</div>
			</td>
		</tr>
		<tr >
			<th scope="row"><span style="color:red;">* </span><label for="enrlAplcStartDttm">접수 기간</label></th>
			<td>
				<div class="input-group" style="float:left;width:128px;">
					<input type="text" maxlength="10" name="enrlAplcStartDttm" id="enrlAplcStartDttm" value="${vo.enrlAplcStartDttm}" class="inputDate form-control input-sm" autocomplete="off"/>
					<span class="input-group-addon btn-sm" onclick="_clickCalendar('enrlAplcStartDttm')" style="cursor:pointer">
						<i class="fa fa-calendar"></i>
					</span>
				</div>
				<div class="input-group text-center" style="float:left;padding-left:10px; padding-right:10px;"> ~ </div>
				<div class="input-group" style="float:left;width:128px;">
					<input type="text" maxlength="10" name="enrlAplcEndDttm" id="enrlAplcEndDttm" value="${vo.enrlAplcEndDttm}" class="inputDate form-control input-sm" autocomplete="off"/>
					<span class="input-group-addon btn-sm" onclick="_clickCalendar('enrlAplcEndDttm')" style="cursor:pointer">
						<i class="fa fa-calendar"></i>
					</span>
				</div>
				<meditag:datepicker name1="enrlAplcStartDttm" name2="enrlAplcEndDttm" />				
			</td>
			<th scope="row"><span style="color:red;">* </span><label for="enrlStartDttm">수강 기간</label></th>
			<td>
				<div class="input-group" style="float:left;width:128px;">
					<input type="text" maxlength="10" name="enrlStartDttm" id="enrlStartDttm" value="${vo.enrlStartDttm}" class="inputDate form-control input-sm" autocomplete="off"/>
					<span class="input-group-addon btn-sm" onclick="_clickCalendar('enrlStartDttm')" style="cursor:pointer">
						<i class="fa fa-calendar"></i>
					</span>
				</div>
				<div class="input-group text-center" style="float:left;padding-left:10px; padding-right:10px;"> ~ </div>
				<div class="input-group" style="float:left;width:128px;">
					<input type="text" maxlength="10" name="enrlEndDttm" id="enrlEndDttm" value="${vo.enrlEndDttm}" class="inputDate form-control input-sm" autocomplete="off"/>
					<span class="input-group-addon btn-sm" onclick="_clickCalendar('enrlEndDttm')" style="cursor:pointer">
						<i class="fa fa-calendar"></i>
					</span>
				</div>
				<meditag:datepicker name1="enrlStartDttm" name2="enrlEndDttm" />			
			</td>
		</tr>
		<tr >
			<th scope="row"><span style="color:red;">* </span><label for="termEndDttm">최종 종강일</label></th>
			<td>
				<div class="input-group" style="float:left;width:128px;">
					<input type="text" maxlength="10" name="termEndDttm" id="termEndDttm" value="${vo.termEndDttm}" class="inputDate form-control input-sm" autocomplete="off"/>
					<span class="input-group-addon btn-sm" onclick="_clickCalendar('termEndDttm')" style="cursor:pointer">
						<i class="fa fa-calendar"></i>
					</span>
				</div>
				<meditag:datepicker name1="termEndDttm" />
			</td>
			<th scope="row"><span style="color:red;">* </span><label for="scoreStartDttm">성적열람기간</label></th>
			<td>
				<div class="input-group" style="float:left;width:128px;">
					<input type="text" maxlength="10" name="scoreOpenStartDttm" id="scoreOpenStartDttm" value="${vo.scoreOpenStartDttm}" class="inputDate form-control input-sm" autocomplete="off"/>
					<span class="input-group-addon btn-sm" onclick="_clickCalendar('scoreOpenStartDttm')" style="cursor:pointer">
						<i class="fa fa-calendar"></i>
					</span>
				</div>
				<div class="input-group text-center" style="float:left;padding-left:10px; padding-right:10px;"> ~ </div>
				<div class="input-group" style="float:left;width:128px;">
					<input type="text" maxlength="10" name="scoreOpenEndDttm" id="scoreOpenEndDttm" value="${vo.scoreOpenEndDttm}" class="inputDate form-control input-sm" autocomplete="off"/>
					<span class="input-group-addon btn-sm" onclick="_clickCalendar('scoreOpenEndDttm')" style="cursor:pointer">
						<i class="fa fa-calendar"></i>
					</span>
				</div>
				<meditag:datepicker name1="scoreOpenStartDttm" name2="scoreOpenEndDttm" />
			</td>
		</tr>
		<tr >
			<th scope="row"><span style="color:red;">* </span><label for="">기업명</label></th>
			<td colspan="3">
				<input type="hidden" id="deptCds" name="deptCds" value="${vo.deptCds}"/>
				<input type="text" id="deptNms" name="deptNms" value="${vo.deptNms}" readonly style="width:40%;margin-right: 5px;float: left;" maxlength="4" isNull="Y" class="form-control input-sm"/>
				<button class="btn btn-default btn-sm" onclick="selectDeptPop()"><spring:message code="common.title.select"/></button>
			</td>
		</tr>
		<tr >
			<th scope="row"><span style="color:red;">* </span><label for="termDesc">기수내용</label></th>
			<td colspan="3">
				<textarea id="crsDesc" name="crsDesc" lenCheck="1000" style="height:80px"  class="form-control input-sm">${vo.crsDesc}</textarea>
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
		<button class="btn btn-default btn-sm" onclick="closeWriteArea()"><spring:message code="button.close"/></button>
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
		$('#courseForm').attr("action","/mng/course/course/"+ cmd);
		$('#courseForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		if(resultDTO.result >= 0) {
			alert(resultDTO.message);
			if($("#cmd").val() != 'editCourse'){
				parent.closeWriteArea();
				parent.listCourse(1);
			}else{
				crsMngForm($("#crsCd").val());
			}
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

	function selectDeptPop() {
		var url = generateUrl("/mng/user/deptInfo/listDeptPop");
		var addContent  = "<iframe id='userInfoFrame' name='userInfoFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='"+url+"?searchFrom=courseWrite&searchValue=${vo.crsCd}'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(500, 700);
		modalBox.setTitle("기업 선택");
		modalBox.show();
	}
</script>
