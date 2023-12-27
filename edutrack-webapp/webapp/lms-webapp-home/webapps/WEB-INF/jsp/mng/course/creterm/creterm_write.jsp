<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="gubun" value="${gubun}"/>
<c:set var="vo" value="${vo}"/>


	<form id="cretermForm" name="cretermForm" onsubmit="return false">
	<input type="hidden" name="crsTermCd" id="crsTermCd" value="${vo.crsTermCd}" /> 
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
				<input type="text" id="creYear" name="creYear" value="${vo.creYear}" style="width:96%;" maxlength="4" isNull="Y"   class="form-control input-sm"/>
			</td>
			<th scope="row"><span style="color:red;">* </span><label for="creTerm">기수</label></th>
			<td>
				<div class="input-group">
					<input type="text" id="creTerm" name="creTerm" value="${vo.creTerm}"  style="width:120px;text-align:right;" maxlength="10" isNull="Y"  class="inputNumber form-control input-sm"  />
				</div>
			</td>
		</tr>
		<tr >
			<th scope="row"><span style="color:red;">* </span><label for="enrlAplcStartDttm">접수 기간</label></th>
			<td>
				<div class="input-group" style="float:left;width:128px;">
					<input type="text" maxlength="10" name="enrlAplcStartDttm" id="enrlAplcStartDttm" value="${vo.enrlAplcStartDttm}" class="inputDate form-control input-sm"/>
					<span class="input-group-addon btn-sm" onclick="_clickCalendar('enrlAplcStartDttm')" style="cursor:pointer">
						<i class="fa fa-calendar"></i>
					</span>
				</div>
				<div class="input-group text-center" style="float:left;padding-left:10px; padding-right:10px;"> ~ </div>
				<div class="input-group" style="float:left;width:128px;">
					<input type="text" maxlength="10" name="enrlAplcEndDttm" id="enrlAplcEndDttm" value="${vo.enrlAplcEndDttm}" class="inputDate form-control input-sm"/>
					<span class="input-group-addon btn-sm" onclick="_clickCalendar('enrlAplcEndDttm')" style="cursor:pointer">
						<i class="fa fa-calendar"></i>
					</span>
				</div>
				<meditag:datepicker name1="enrlAplcStartDttm" name2="enrlAplcEndDttm" />				
			</td>
			<th scope="row"><span style="color:red;">* </span><label for="enrlStartDttm">수강 기간</label></th>
			<td>
				<div class="input-group" style="float:left;width:128px;">
					<input type="text" maxlength="10" name="enrlStartDttm" id="enrlStartDttm" value="${vo.enrlStartDttm}" class="inputDate form-control input-sm"/>
					<span class="input-group-addon btn-sm" onclick="_clickCalendar('enrlStartDttm')" style="cursor:pointer">
						<i class="fa fa-calendar"></i>
					</span>
				</div>
				<div class="input-group text-center" style="float:left;padding-left:10px; padding-right:10px;"> ~ </div>
				<div class="input-group" style="float:left;width:128px;">
					<input type="text" maxlength="10" name="enrlEndDttm" id="enrlEndDttm" value="${vo.enrlEndDttm}" class="inputDate form-control input-sm"/>
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
					<input type="text" maxlength="10" name="termEndDttm" id="termEndDttm" value="${vo.termEndDttm}" class="inputDate form-control input-sm"/>
					<span class="input-group-addon btn-sm" onclick="_clickCalendar('termEndDttm')" style="cursor:pointer">
						<i class="fa fa-calendar"></i>
					</span>
				</div>
				<meditag:datepicker name1="termEndDttm" />
			</td>
			<th scope="row"><span style="color:red;">* </span><label for="scoreStartDttm">성적열람기간</label></th>
			<td>
				<div class="input-group" style="float:left;width:128px;">
					<input type="text" maxlength="10" name="scoreStartDttm" id="scoreStartDttm" value="${vo.scoreStartDttm}" class="inputDate form-control input-sm"/>
					<span class="input-group-addon btn-sm" onclick="_clickCalendar('scoreStartDttm')" style="cursor:pointer">
						<i class="fa fa-calendar"></i>
					</span>
				</div>
				<div class="input-group text-center" style="float:left;padding-left:10px; padding-right:10px;"> ~ </div>
				<div class="input-group" style="float:left;width:128px;">
					<input type="text" maxlength="10" name="scoreEndDttm" id="scoreEndDttm" value="${vo.scoreEndDttm}" class="inputDate form-control input-sm"/>
					<span class="input-group-addon btn-sm" onclick="_clickCalendar('scoreEndDttm')" style="cursor:pointer">
						<i class="fa fa-calendar"></i>
					</span>
				</div>
				<meditag:datepicker name1="scoreStartDttm" name2="scoreEndDttm" />
			</td>
		</tr>
		<tr >
			<th scope="row"><span style="color:red;">* </span><label for="">기업명</label></th>
			<td colspan="3">
				<input type="text" id="deptCd" name="deptCd" value="${vo.deptCd}" style="width:96%;" maxlength="4" isNull="Y"   class="form-control input-sm"/>
			</td>
		</tr>
		<tr >
			<th scope="row"><span style="color:red;">* </span><label for="termDesc">기수내용</label></th>
			<td colspan="3">
				<textarea id="termDesc" name="termDesc" lenCheck="1000" style="height:80px"  class="form-control input-sm">${vo.termDesc}</textarea>
			</td>
		</tr>							
	</table>
	<div class="text-right" style="margin-bottom:20px;">
		<c:if test="${gubun eq 'A'}">
			<button class="btn btn-primary btn-sm" onclick="addCrerterm()"><spring:message code="button.add"/></button>
		</c:if>
		<c:if test="${gubun eq 'E'}">
			<button class="btn btn-primary btn-sm" onclick="editCrerterm()"><spring:message code="button.add"/></button>
			<button class="btn btn-warning btn-sm" onclick="delCrerterm()"><spring:message code="button.delete"/></button>
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

		if(!validate(document.cretermForm)) return;
		$('#cretermForm').attr("action","/mng/course/creterm/"+ cmd);
		$('#cretermForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		if(resultDTO.result >= 0) {
			alert(resultDTO.message);
			parent.closeWriteArea();
			parent.listCreterm(1);
		} else {
			// 비정상 처리
			alert(resultDTO.message);
		}
	}

	/**
	 * 기수 등록
	 */
	function addCrerterm() {
		if(!validate(document.cretermForm)) return;
		process("addCrerterm");	// cmd
	}

	/**
	 * 기수 수정
	 */
	function editCrerterm() {
		if(!validate(document.cretermForm)) return;
		process("editCrerterm");	// cmd
	}

	/**
	 * 기수 삭제
	 */
	function delCrerterm() {
		process("deleteCrerterm");	// cmd
	}

</script>
