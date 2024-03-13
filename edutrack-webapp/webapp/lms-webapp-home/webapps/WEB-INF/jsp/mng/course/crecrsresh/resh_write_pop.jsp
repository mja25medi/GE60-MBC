<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="gubun" value="${gubun}"/>
<c:set var="creCrsReshVO" value="${vo}"/>

	<form id="creCrsReshForm" name="creCrsReshForm" onsubmit="return false">
	<input type="hidden" name="crsCreCd" value="${vo.crsCreCd }"/>
	<input type="hidden" name="useYn" value="${vo.useYn }"/>
	<input type="hidden" name="stdAnsrCnt" id="stdAnsrCnt" value="${vo.stdAnsrCnt }"/>
	<input type="hidden" name="reshStareTypeCd" id="reshStareTypeCd" value="${vo.reshStareTypeCd }"/>
	<table summary="<spring:message code="course.title.resh.manage"/>"  class="table table-bordered wordbreak">
		<colgroup>
			<col style="width:25%"/>
			<col style="width:75%"/>
		</colgroup>
		<tbody>
		<tr>
			<th scope="row"><label for="reshSn"><spring:message code="course.title.resh.select.bank"/></label></th>
			<td>
				<c:if test="${gubun eq 'A'}">
					<select name="reshSn" id="reshSn" class="form-control input-sm" onchange="chk_resh()">
						<c:forEach var="item" items="${researchBankList}">
							<option value="${item.reshSn}" <c:if test="${item.reshSn eq creCrsReshVO.reshSn}">selected</c:if>>${item.reshTitle}</option>
						</c:forEach>
					</select>
				</c:if>
				<c:if test="${gubun eq 'E'}">
					${creCrsReshVO.reshTitle}
					<input type="hidden" name="reshSn" value="${vo.reshSn }"/>
				</c:if>
			</td>
		</tr>
		<tr id="reshDuration">
			<th scope="row"><label for="reshCts"><spring:message code="course.title.resh.duration"/></label></th>
			<td>
				<div class="input-group" style="float:left;width:128px;">
					<input type="text" dispName="<spring:message code="course.title.resh.startdate"/>" maxlength="50" isNull="N" lenCheck="50" name="startDttm" value="${vo.startDttm }" id="startDttm" class="inputDate form-control input-sm"/>
					<span class="input-group-addon btn-sm" onclick="_clickCalendar('startDttm')" style="cursor:pointer">
						<i class="fa fa-calendar"></i>
					</span>
				</div>
				<input type="text" style="width:40px;float:left;text-align:right;" dispName="<spring:message code="course.title.resh.start.hour"/>" maxlength="2" isNull="N" lenCheck="2" name="startHour" value="${vo.startHour }" id="startHour" class="form-control input-sm" onkeyup="isChkHours(this)" />
				<span style="float:left;line-height:30px;"><spring:message code="common.title.hour"/></span>
				<input type="text" style="width:40px;float:left;text-align:right;" dispName="<spring:message code="course.title.resh.start.min"/>" maxlength="2" isNull="N" lenCheck="2" name="startMin" value="${vo.startMin }" id="startMin" class="form-control input-sm" onkeyup="isChkMinute(this)" />
				<span style="float:left;line-height:30px;"><spring:message code="common.title.min"/></span>

				<div class="input-group text-center" style="float:left;line-heigth:30px;padding-left:10px; padding-right:10px;"> ~ </div>
				<div class="input-group" style="float:left;width:128px;">
					<input type="text" dispName="<spring:message code="course.title.resh.enddate"/>" maxlength="50" isNull="N" lenCheck="50" name="endDttm" value="${vo.endDttm }" id="endDttm" class="inputDate form-control input-sm"/>
					<span class="input-group-addon btn-sm" onclick="_clickCalendar('endDttm')" style="cursor:pointer">
						<i class="fa fa-calendar"></i>
					</span>
				</div>
				<input type="text" style="width:40px;float:left;text-align:right;" dispName="<spring:message code="course.title.resh.end.hour"/>" maxlength="2" isNull="N" lenCheck="2" name="endHour" value="${vo.endHour }" id="endHour" class="form-control input-sm" onkeyup="isChkHours(this)" />
				<span style="float:left;line-height:30px;"><spring:message code="common.title.hour"/></span>
				<input type="text" style="width:40px;float:left;text-align:right;" dispName="<spring:message code="course.title.resh.end.min"/>" maxlength="2" isNull="N" lenCheck="2" name="endMin" value="${vo.endMin }" id="endMin" class="form-control input-sm" onkeyup="isChkMinute(this)" />
				<span style="float:left;line-height:30px;"><spring:message code="common.title.min"/></span>
				<meditag:datepicker name1="startDttm" name2="endDttm"/>
			</td>
		</tr>
		<tr id="reshDayCnt">
			<th scope="row"><label for="reshCts"><spring:message code="course.title.resh.duration"/></label></th>
			<td>
				<span style="float:left;line-height:30px;"><spring:message code="lecture.title.research.day.start"/></span>
				<input type="text" style="width:60px;float:left;text-align:right;" maxlength="3" isNull="N" lenCheck="4" name="reshDayCnt" id="reshDayCnt" value="${vo.reshDayCnt }" onfocus="this.select()" class="inputSpecial inputNumber form-control input-sm" onkeyup="isChkInteger(this)"/>
				<span style="float:left;line-height:30px;"><spring:message code="lecture.title.research.day.end"/></span>
			</td>
		</tr>
		<tr>
			<th scope="row"><spring:message code="lecture.title.research.regyn"/></th>
			<td>
				<label style="font-weight: normal;"><input type="radio" style="border:0" name="regYn" value="Y" <c:if test="${vo.regYn eq 'Y'}">checked</c:if> /><spring:message code="lecture.title.research.regyn_y"/></label>
				<label style="font-weight: normal;margin-left:10px;"><input type="radio" style="border:0" name="regYn" value="N" <c:if test="${vo.regYn ne 'Y'}">checked</c:if> /><spring:message code="lecture.title.research.regyn_n"/></label>
			</td>
		</tr>
		</tbody>
	</table>
	<div class="text-right">
		<c:if test="${gubun eq 'A' }">
		<a href="javascript:add()" class="btn btn-primary btn-sm"><spring:message code="button.add"/> </a>
		</c:if>
		<c:if test="${gubun eq 'E' }">
		<a href="javascript:edit()" class="btn btn-primary btn-sm"><spring:message code="button.add"/> </a>
		<a href="javascript:remove()" class="btn btn-warning btn-sm"><spring:message code="button.delete"/> </a>
		</c:if>
		<a href="javascript:parent.modalBoxClose()" class="btn btn-default btn-sm"><spring:message code="button.close"/> </a>
	</div>
	<input type="submit" value="submit" style="display:none" />
	</form>

<script type="text/javascript">

	$(document).ready(function() {
		$('.inputDate').inputDate();  // 날짜 형식만 입력되도록 설정.
		$('.inputNumber').inputNumber();
		
		var creOperTypeCd ="${vo.creOperTypeCd}";
		if (creOperTypeCd == 'S') {
			$('#reshDuration').css("display", "none");
		}else{
			$('#reshDayCnt').css("display", "none");
		}
		<c:if test="${gubun eq 'A'}">
		var reshSn_chk = parent.subWorkFrame.document.getElementById("reshSn_chk").value;
		var reshSn_ar = reshSn_chk.split("|");
		var selectReshSn = document.getElementById("reshSn");

		for(var i=0; i<reshSn_ar.length; i++){
			for(var j=0; j<selectReshSn.length; j++){
				if(reshSn_ar[i] == selectReshSn.options[j].value ){
					selectReshSn.options[j]=null;
				}
			}
		}

		if(selectReshSn.length == 0){
			alert("<spring:message code="lecture.message.research.noreg"/>");
			parent.modalBoxClose();
		}
		</c:if>
	});

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		var f = document.creCrsReshForm;
		var creOperTypeCd ="${vo.creOperTypeCd}";
		if (creOperTypeCd == 'S') {
			f["reshStareTypeCd"].value="S";
		}else{
			f["reshStareTypeCd"].value="R";
			if(!validate(document.creCrsReshForm)) return;

			var startDttm = $("#startDttm").val();
			var endDttm = $("#endDttm").val();
			var startHour = $("#startHour").val();
			var startMin = $("#startMin").val();
			var endHour = $("#endHour").val();
			var endMin = $("#endMin").val();
			var startDttmArray = startDttm.split(".");
			var endDttmArray = endDttm.split(".");
			var StartDttmObj = new Date(startDttmArray[0], Number(startDttmArray[1])-1, startDttmArray[2], startHour, startMin, 01);
			var EndDttmObj = new Date(endDttmArray[0], Number(endDttmArray[1])-1, endDttmArray[2], endHour, endMin, 01);

			if(StartDttmObj >= EndDttmObj){
				alert('<spring:message code="lecture.message.resh.alert.result.date"/>');
				return;
			}
		}
		
		$('#creCrsReshForm').attr("action","/mng/course/creCrs/resh/" + cmd);
		$('#creCrsReshForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		alert(resultDTO.message);

		if(resultDTO.result >= 0) {
			// 정상 처리
			parent.subWorkFrame.listResearch();
			parent.modalBoxClose();
		} else {
			// 비정상 처리
		}
	}

	function add() {
		var reshSn = $("#reshSn opiton:selected").val();

		if(!parent.subWorkFrame.chk_reshSn($("#reshSn").val()) ){
			return;
		}

		if(reshSn == 0) {
			alert("<spring:message code="course.message.resh.alert.questionnaire"/>")
			return;
		}
		process("add");	// cmd
	}

	function edit() {
		process("edit");	// cmd
	}

	function remove() {
		if($("#stdAnsrCnt").val() > 0 ) {	//ENRL_STS가 D가 아닌 수강생들의 참여 수
			alert("<spring:message code="course.message.resh.alert.delete"/>");
			return;
		}
		if(confirm("<spring:message code="course.message.resh.confirm.delete"/>")) {
			process("remove");	// cmd
		}
		return;
	}
</script>
