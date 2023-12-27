<!DOCTYPE html>
<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="img_base" value="/img/home" />
<c:set var="gubun" value="${gubun}"/>
<c:set var="creCrsReshVO" value="${vo}"/>
<c:set var="reshSn_chk" value="-1" />
<mhtml:class_html>
<mhtml:class_head>

</mhtml:class_head>

<mhtml:class_body>
				<mhtml:class_location />
				<div class="row">
					<div class="col-lg-12">
						<form id="creCrsReshForm" name="creCrsReshForm" onsubmit="return false" >
						<input type="hidden" name="crsCreCd" value="${vo.crsCreCd}"/>
						<input type="hidden" name="useYn" value="${vo.useYn}" />

						<c:forEach var="chk_item" items="${creCrsReshList}" varStatus="status">
							<c:choose>
								<c:when test="${status.count ne '0' }"><c:set var="reshSn_chk" value="${reshSn_chk}|${chk_item.reshSn}" /></c:when>
							</c:choose>
						</c:forEach>
						<input type="hidden" name="reshSn_chk" Id="reshSn_chk" value="${reshSn_chk}" />
						<table class="table table-bordered wordbreak">
							<caption class="sr-only"><spring:message code="lecture.title.research.manage"/></caption>
							<colgroup>
								<col style="width:20%"/>
								<col style="width:80%"/>
							</colgroup>
							<tbody>
								<c:if test="${gubun eq 'A' }">
								<tr>
									<th scope="row"><spring:message code="lecture.title.research.select"/></th>
									<td >
									<c:choose>
										<c:when test="${not empty researchBankList}">
										<select name="reshSn"  id="reshSn" class="form-control input-sm">
											<c:forEach var="item" items="${researchBankList}">
											<option value="${item.reshSn}">${item.reshTitle}</option>
											</c:forEach>
										</select>
									</c:when>
									<c:otherwise>
										<spring:message code="lecture.message.research.noreg"/>
									</c:otherwise>
								</c:choose>
									</td>
								</tr>
								</c:if>
								<c:if test="${gubun eq 'E' }">
								<tr>
									<th scope="row"><spring:message code="lecture.title.research.name"/></th>
									<td >
										${creCrsReshVO.reshTitle}
										<input type="hidden" name="reshSn" value="${vo.curreshSnPage}"/>
									</td>
								</tr>
								</c:if>
								<tr >
									<th scope="row"><spring:message code="lecture.title.research.duration"/></th>
									<td >
										<div class="input-group" style="float:left;width:128px;">
											<input type="text" dispName="<spring:message code="lecture.title.research.startdate"/>" maxlength="50" isNull="N" lenCheck="50" name="startDttm" value="${vo.startDttm}" id="startDttm" class="inputDate reshStareTypeControl form-control input-sm"/>
											<span class="input-group-addon btn-sm" onclick="_clickCalendar('startDttm')" style="cursor:pointer">
												<i class="fa fa-calendar"></i>
											</span>
										</div>
										<input type="text" style="width:40px;float:left;text-align:right;" dispName="<spring:message code="course.title.resh.start.hour"/>" maxlength="2" isNull="N" lenCheck="2" name="startHour" value="${vo.startHour}" id="startHour" class="form-control input-sm inputNumber inputSpecial" onkeyup="isChkHours(this)" />
										<span style="float:left;line-height:30px;"><spring:message code="common.title.hour"/></span>
										<input type="text" style="width:40px;float:left;text-align:right;" dispName="<spring:message code="course.title.resh.start.min"/>" maxlength="2" isNull="N" lenCheck="2" name="startMin" value="${vo.startMin}" id="startMin" class="form-control input-sm inputNumber inputSpecial" onkeyup="isChkMinute(this)" />
										<span style="float:left;line-height:30px;"><spring:message code="common.title.min"/></span>

										<div class="input-group text-center" style="float:left;padding-left:10px; padding-right:10px;"> ~ </div>
										<div class="input-group" style="float:left;width:128px;">
											<input type="text" dispName="<spring:message code="lecture.title.research.enddate"/>" maxlength="50" isNull="N" lenCheck="50" name="endDttm" value="${vo.endDttm}" id="endDttm" class="inputDate reshStareTypeControl form-control input-sm"/>
											<span class="input-group-addon btn-sm" onclick="_clickCalendar('endDttm')" style="cursor:pointer">
												<i class="fa fa-calendar"></i>
											</span>
										</div>
										<input type="text" style="width:40px;float:left;text-align:right;" dispName="<spring:message code="course.title.resh.end.hour"/>" maxlength="2" isNull="N" lenCheck="2" name="endHour" value="${vo.endHour}" id="endHour" class="form-control input-sm inputNumber inputSpecial" onkeyup="isChkHours(this)" />
										<span style="float:left;line-height:30px;"><spring:message code="common.title.hour"/></span>
										<input type="text" style="width:40px;float:left;text-align:right;" dispName="<spring:message code="course.title.resh.end.min"/>" maxlength="2" isNull="N" lenCheck="2" name="endMin" value="${vo.endMin}" id="endMin" class="form-control input-sm inputNumber inputSpecial" onkeyup="isChkMinute(this)" />
										<span style="float:left;line-height:30px;"><spring:message code="common.title.min"/></span>
										<meditag:datepicker name1="startDttm" name2="endDttm"/>
									</td>
								</tr>
								<tr >
									<th scope="row"><spring:message code="lecture.title.research.regyn"/></th>
									<td >
										<input type="radio" style="border:0" name="regYn" value="Y" <c:if test="${vo.regYn eq 'Y'}">checked</c:if> /><spring:message code="lecture.title.research.regyn_y"/>
										<input type="radio" style="border:0" name="regYn" value="N" <c:if test="${vo.regYn ne 'Y'}">checked</c:if> /><spring:message code="lecture.title.research.regyn_n"/>
									</td>
								</tr>
							</tbody>
						</table>
						</form>
					</div>
				</div>
				<div class="row" style="margin-bottom:20px;">
					<div class="col-lg-12">
						<div class="text-right">
							<c:if test="${gubun eq 'A' && fn:length(researchBankList) > 0 }">
							<a href="#" onclick="addResh();" class="btn btn-primary btn-sm"><spring:message code="button.add"/></a>
							</c:if>
							<c:if test="${gubun eq 'E' }">
							<a href="#" onclick="editResh();" class="btn btn-primary btn-sm"><spring:message code="button.add"/></a>
							<a href="#" onclick="delResh();" class="btn btn-warning btn-sm"><spring:message code="button.delete"/></a>
							</c:if>
							<a href="<c:url value="/lec/creCrsResh/main"/>" class="btn btn-default btn-sm"><spring:message code="button.list"/></a>
						</div>
					</div>
				</div>

<script type="text/javascript">
	$(document).ready(function() {
		$('.inputNumber').inputNumber();	// 숫자만 입력되도록 설정.
		$('.inputDate').inputDate();	// 숫자만 입력되도록 설정.
		$('.inputSpecial').inputSpecial();  // 특수키가 입력이 들어오묜 false return;
	});

	/**
	 * 설문 등록
	 */
	function addResh() {
		var reshSn_chk = document.getElementById("reshSn_chk").value;
		var pop_reshSn = document.getElementById("reshSn").value;
		var reshSn_ar = reshSn_chk.split("|");
		for(var i=0; i<reshSn_ar.length; i++){
			if(pop_reshSn == reshSn_ar[i]){
				alert("<spring:message code="course.message.resh.alert.duplicate"/>");
				return;
			}
		}

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
		$('#creCrsReshForm').attr("action","/lec/creCrsResh/add");

		document.creCrsReshForm.submit();
	}

	/**
	 * 설문 수정
	 */
	function editResh() {
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

		$('#creCrsReshForm').attr("action","/lec/creCrsResh/edit");
		document.creCrsReshForm.submit();
	}

	/**
	 * 설문 삭제
	 */
	function delResh() {
		if(parseInt("${creCrsReshVO.ansrCnt}",10) > 0){
			if(confirm("<spring:message code="lecture.message.research.delete.confirm"/>")){
				$('#creCrsReshForm').attr("action","/lec/creCrsResh/remove");
				document.creCrsReshForm.submit();
			}
		}else{
			if(confirm("<spring:message code="course.message.resh.confirm.delete"/>")) {
				$('#creCrsReshForm').attr("action","/lec/creCrsResh/remove");
				document.creCrsReshForm.submit();
			}
		}
	}

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

	//날짜 사이의 간격 구하기
	function getGapDate() {
		var f = document.f;
		var from = f.serviceStart.value;
		var to = f.serviceEnd.value;
		if(from != "" && to != "") {
			var days = jsGetDays(chgDate(from),chgDate(to));

			f.serviceDay.value = days;

		}
	}

</script>
</mhtml:class_body>
</mhtml:class_html>