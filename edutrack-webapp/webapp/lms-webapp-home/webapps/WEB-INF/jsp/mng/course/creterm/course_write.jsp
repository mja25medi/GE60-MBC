<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="gubun" value="${gubun}"/>
<c:set var="vo" value="${vo}"/>


	<form id="courseForm" name="courseForm" onsubmit="return false">
	<table summary="<spring:message code="course.title.course.manage"/>" class="table table-striped table-bordered">
		<colgroup>
			<col style="width:20%"/>
			<col style="width:30%"/>
			<col style="width:20%"/>
			<col style="width:30%"/>
		</colgroup>
		<tr >
			<th scope="row"><span style="color:red;">* </span><label for="">기수 년도</label></th>
			<td>
				<input type="text" id="" name="" value="" style="width:96%;" maxlength="4" isNull="Y"   class="form-control input-sm"/>
			</td>
			<th scope="row"><span style="color:red;">* </span><label for="">기수</label></th>
			<td>
				<div class="input-group">
					<input type="text" id="" name="" value=""  style="width:120px;text-align:right;" maxlength="10" isNull="Y"  class="inputNumber form-control input-sm"  />
				</div>
			</td>
		</tr>
		<tr >
			<th scope="row"><span style="color:red;">* </span><label for="">접수 기간</label></th>
			<td>
				<div class="input-group" style="float:left;width:128px;">
					<input type="text" maxlength="10" name="receiptStartDttm" id="receiptStartDttm" class="inputDate form-control input-sm"/>
					<span class="input-group-addon btn-sm" onclick="_clickCalendar('receiptStartDttm')" style="cursor:pointer">
						<i class="fa fa-calendar"></i>
					</span>
				</div>
				<div class="input-group text-center" style="float:left;padding-left:10px; padding-right:10px;"> ~ </div>
				<div class="input-group" style="float:left;width:128px;">
					<input type="text" maxlength="10" name="receiptEndDttm" id="receiptEndDttm" class="inputDate form-control input-sm"/>
					<span class="input-group-addon btn-sm" onclick="_clickCalendar('receiptEndDttm')" style="cursor:pointer">
						<i class="fa fa-calendar"></i>
					</span>
				</div>
				<meditag:datepicker name1="receiptStartDttm" name2="receiptEndDttm" />				
			</td>
			<th scope="row"><span style="color:red;">* </span><label for="">수강 기간</label></th>
			<td>
				<div class="input-group" style="float:left;width:128px;">
					<input type="text" maxlength="10" name="enrlStartDttm" id="enrlStartDttm" class="inputDate form-control input-sm"/>
					<span class="input-group-addon btn-sm" onclick="_clickCalendar('enrlStartDttm')" style="cursor:pointer">
						<i class="fa fa-calendar"></i>
					</span>
				</div>
				<div class="input-group text-center" style="float:left;padding-left:10px; padding-right:10px;"> ~ </div>
				<div class="input-group" style="float:left;width:128px;">
					<input type="text" maxlength="10" name="enrlEndDttm" id="enrlEndDttm" class="inputDate form-control input-sm"/>
					<span class="input-group-addon btn-sm" onclick="_clickCalendar('enrlEndDttm')" style="cursor:pointer">
						<i class="fa fa-calendar"></i>
					</span>
				</div>
				<meditag:datepicker name1="enrlStartDttm" name2="enrlEndDttm" />			
			</td>
		</tr>
		<tr >
			<th scope="row"><span style="color:red;">* </span><label for="">최종 종강일</label></th>
			<td>
				<div class="input-group" style="float:left;width:128px;">
					<input type="text" maxlength="10" name="auditEndDttm" id="auditEndDttm" class="inputDate form-control input-sm"/>
					<span class="input-group-addon btn-sm" onclick="_clickCalendar('auditEndDttm')" style="cursor:pointer">
						<i class="fa fa-calendar"></i>
					</span>
				</div>
				<meditag:datepicker name1="auditEndDttm" />
			</td>
			<th scope="row"><span style="color:red;">* </span><label for="">성적열람기간</label></th>
			<td>
				<div class="input-group" style="float:left;width:128px;">
					<input type="text" maxlength="10" name="resultStartDttm" id="resultStartDttm" class="inputDate form-control input-sm"/>
					<span class="input-group-addon btn-sm" onclick="_clickCalendar('resultStartDttm')" style="cursor:pointer">
						<i class="fa fa-calendar"></i>
					</span>
				</div>
				<div class="input-group text-center" style="float:left;padding-left:10px; padding-right:10px;"> ~ </div>
				<div class="input-group" style="float:left;width:128px;">
					<input type="text" maxlength="10" name="resultEndDttm" id="resultEndDttm" class="inputDate form-control input-sm"/>
					<span class="input-group-addon btn-sm" onclick="_clickCalendar('resultEndDttm')" style="cursor:pointer">
						<i class="fa fa-calendar"></i>
					</span>
				</div>
				<meditag:datepicker name1="resultStartDttm" name2="resultEndDttm" />
			</td>
		</tr>
		<tr >
			<th scope="row"><span style="color:red;">* </span><label for="">기업명</label></th>
			<td colspan="3">
				<input type="text" id="" name="" value="" style="width:96%;" maxlength="4" isNull="Y"   class="form-control input-sm"/>
			</td>
		</tr>
		<tr >
			<th scope="row"><span style="color:red;">* </span><label for="">기수내용</label></th>
			<td colspan="3">
				<textarea id="" name="" lenCheck="1000" style="height:80px"  class="form-control input-sm">테스트</textarea>
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

		if(!validate(document.courseForm)) return;
		$('#courseForm').attr("action","/mng/course/creterm/"+ cmd);
		$('#courseForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		if(resultDTO.result >= 0) {
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
		if(!validate(document.courseForm)) return;
		process("addCrerterm");	// cmd
	}

	/**
	 * 기수 수정
	 */
	function editCrerterm() {
		if(!validate(document.courseForm)) return;
		process("editCrerterm");	// cmd
	}

	/**
	 * 기수 삭제
	 */
	function delCrerterm() {
		process("deleteCrerterm");	// cmd
	}

</script>
