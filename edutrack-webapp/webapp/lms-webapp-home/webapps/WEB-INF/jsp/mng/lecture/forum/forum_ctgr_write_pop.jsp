<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="forumVO" value="${vo}"/>
<c:set var="gubun" value="${gubun}"/>
	<form id="forumForm" name="forumForm" onsubmit="return false">
	<input type="hidden" name="crsCreCd" value="${vo.crsCreCd }" />
	<input type="hidden" name="forumSn" value="${vo.forumSn }" />
	<table summary="<spring:message code="lecture.title.forum.manage"/>"  class="table table-bordered wordbreak">
		<colgroup>
			<col style="20%"/>
			<col style="30%"/>
			<col style="20%"/>
			<col style="30%"/>
		</colgroup>
		<tr>
			<th scope="row"><spring:message code="lecture.title.forum.title"/></th>
			<td colspan="3">
				<input type="text" dispName="<spring:message code="lecture.title.forum.title"/>" maxlength="100" isNull="N" lenCheck="100" name="forumTitle" value="${vo.forumTitle }" onfocus="this.select()" class="form-control input-sm"/>
			</td>
		</tr>
		<tr>
			<th scope="row"><spring:message code="lecture.title.forum.duration"/></th>
			<td colspan="3">
				<div class="input-group" style="float:left;width:130px;">
					<input type="text" dispName="<spring:message code="lecture.title.forum.startdate"/>" maxlength="50" isNull="N" lenCheck="50" name="forumStartDttm" id="forumStartDttm" value="${vo.forumStartDttm }" class="inputDate form-control input-sm"/>
					<span class="input-group-addon btn-sm" onclick="_clickCalendar('forumStartDttm')" style="cursor:pointer">
						<i class="fa fa-calendar"></i>
					</span>
				</div>
				<input type="text" style="width:40px;float:left;text-align:right;" dispName="<spring:message code="lecture.title.forum.start.hour"/>" maxlength="2" isNull="N" lenCheck="2" name="forumStartHour" id="forumStartHour" value="${vo.forumStartHour }" class="form-control input-sm" onkeyup="isChkHours(this)" />
				<span style="float:left;line-height:30px;"><spring:message code="common.title.hour"/></span>
				<input type="text" style="width:40px;float:left;text-align:right;" dispName="<spring:message code="lecture.title.forum.start.min"/>" maxlength="2" isNull="N" lenCheck="2" name="forumStartMin" id="forumStartMin" value="${vo.forumStartMin }" class="form-control input-sm" onkeyup="isChkMinute(this)" />
				<span style="float:left;line-height:30px;"><spring:message code="common.title.min"/></span>

				<div class="input-group text-center" style="float:left;line-heigth:30px;padding-left:10px; padding-right:10px;"> ~ </div>

				<div class="input-group" style="float:left;width:130px;">
					<input type="text" dispName="<spring:message code="lecture.title.forum.enddate"/>" maxlength="50" isNull="N" lenCheck="50" name="forumEndDttm" id="forumEndDttm" value="${vo.forumEndDttm }" class="inputDate form-control input-sm"/>
					<span class="input-group-addon btn-sm" onclick="_clickCalendar('forumEndDttm')" style="cursor:pointer">
						<i class="fa fa-calendar"></i>
					</span>
				</div>
				<input type="text" style="width:40px;float:left;text-align:right;" dispName="<spring:message code="lecture.title.forum.end.hour"/>" maxlength="2" isNull="N" lenCheck="2" name="forumEndHour" id="forumEndHour" value="${vo.forumEndHour }" class="form-control input-sm" onkeyup="isChkHours(this)" />
				<span style="float:left;line-height:30px;"><spring:message code="common.title.hour"/></span>
				<input type="text" style="width:40px;float:left;text-align:right;" dispName="<spring:message code="lecture.title.forum.end.min"/>" maxlength="2" isNull="N" lenCheck="2" name="forumEndMin" id="forumEndMin" value="${vo.forumEndMin }" class="form-control input-sm" onkeyup="isChkMinute(this)" />
				<span style="float:left;line-height:30px;"><spring:message code="common.title.min"/></span>
				<meditag:datepicker name1="forumStartDttm" name2="forumEndDttm"/>
			</td>
		</tr>
		<tr>
			<th scope="row" ><spring:message code="lecture.title.forum.regyn"/></th>
			<td colspan="3">
				<c:forEach var="item" items="${regYnList}" varStatus="status">
					<c:set var="codeName" value="${item.codeNm}"/>
					<c:forEach var="lang" items="${item.codeLangList}">
						<c:if test="${lang.langCd eq LOCALEKEY}"><c:set var="codeName" value="${lang.codeNm}"/></c:if>
					</c:forEach>
					<label style="font-weight: normal;"><input type="radio" name="forumRegYn" value="${item.codeCd}" id="regYn_${item.codeCd}" <c:if test="${vo.forumRegYn eq item.codeCd}">checked</c:if>/>${codeName}</label>
				<c:choose><c:when test="${status.last}"></c:when><c:otherwise>&nbsp;&nbsp;</c:otherwise></c:choose>
				</c:forEach>
			</td>
		</tr>
		<tr>
			<th scope="row" style="width:15%"><spring:message code="lecture.title.forum.period.after.write"/></th>
			<td colspan="3">
				<input type="radio" name="periodAfterWriteYn" value="Y" id="periodAfterWriteY" <c:if test="${vo.periodAfterWriteYn eq 'Y'}">checked</c:if>/><label for="periodAfterWriteY"> <spring:message code="lecture.title.forum.writeY"/></label>
				&nbsp;&nbsp;
				<input type="radio" name="periodAfterWriteYn" value="N" id="periodAfterWriteN" <c:if test="${vo.periodAfterWriteYn eq 'N'}">checked</c:if>/><label for="periodAfterWriteN"> <spring:message code="lecture.title.forum.writeN"/></label>

			</td>
		</tr>
		<tr>
			<th scope="row"><spring:message code="lecture.title.forum.desc"/></th>
			<td colspan="3">
				<textarea style="height:100%;" rows="13" isNull="N" dispName="<spring:message code="lecture.title.forum.desc"/>" name="forumCts" class="form-control input-sm">${vo.forumCts}</textarea>
			</td>
		</tr>
	</table>
	<div class="text-right">
		<c:if test="${gubun eq 'A' }">
		<a href="javascript:addForum()" class="btn btn-primary btn-sm"><spring:message code="button.add"/> </a>
		</c:if>
		<c:if test="${gubun eq 'E' }">
		<a href="javascript:editForum()" class="btn btn-primary btn-sm"><spring:message code="button.add"/> </a>
		<a href="javascript:deleteForum()" class="btn btn-warning btn-sm"><spring:message code="button.delete"/> </a>
		</c:if>
		<a href="javascript:parent.modalBoxClose()" class="btn btn-default btn-sm"><spring:message code="button.close"/> </a>
    </div>
</form>
<script type="text/javascript">

	$(document).ready(function() {
		$('.inputDate').inputDate();  // 날짜 형식만 입력되도록 설정.
	});

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		var f = document.forumForm;
		if(!validate(f)) return;
		var startDttm = $("#forumStartDttm").val();
		var endDttm = $("#forumEndDttm").val();
		var startHour = $("#forumStartHour").val();
		var startMin = $("#forumStartMin").val();
		var endHour = $("#forumEndHour").val();
		var endMin = $("#forumEndMin").val();
		var startDttmArray = startDttm.split(".");
		var endDttmArray = endDttm.split(".");
		var StartDttmObj = new Date(startDttmArray[0], Number(startDttmArray[1])-1, startDttmArray[2], startHour, startMin, 01);
		var EndDttmObj = new Date(endDttmArray[0], Number(endDttmArray[1])-1, endDttmArray[2], endHour, endMin, 01);

		if(StartDttmObj >= EndDttmObj){
			alert('<spring:message code="lecture.message.forum.alert.result.date"/>');
			return;
		}
		$('#forumForm').attr("action", "/mng/lecture/forum/" + cmd);
		$('#forumForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		alert(resultDTO.message);

		if(resultDTO.result >= 0) {
			// 정상 처리
			parent.subWorkFrame.listForum();
			parent.modalBoxClose();
		} else {
			// 비정상 처리
		}
	}

	/**
	 * 토론 등록 창 닫기
	 */
	function popClose(){
		parent.forumPopBox.close();
	}

	<c:if test="${gubun eq 'A' }">
	/**
	 * 토론 등록
	 */
	function addForum() {
		process("addForum");	// cmd
	}
	</c:if>

	<c:if test="${gubun eq 'E' }">
	/**
	 * 토론 수정
	 */
	function editForum() {
		process("editForum");	// cmd
	}

	/**
	 * 토론 삭제
	 */
	function deleteForum() {
		process("deleteForum");	// cmd
	}
	</c:if>

	//Date Type YYYYMMDD
	function chgDate(date){
		var chgDate = date.replace(/\./g,"");
		return chgDate;
	}

	//날짜 체크하기.
	function dateCheck(startDate , endDate){
		if(startDate > endDate) {
			return false;
		}else{
			return true;
		}
	}

</script>
