<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="img_base" value="/img/home" />
<c:set var="gubun" value="${researchForm.gubun}"/>
<mhtml:class_html>
<mhtml:class_head>
	<meditag:js src="/js/message_box.js"/>
	<meditag:js src="/js/calendar.js"/>
</mhtml:class_head>
<mhtml:class_body>
	<div id="content">
		<mhtml:class_location />

		<nested:form action="/ResearchLecture.do" onsubmit="return false" id="researchForm">
		<input type="hidden" property="researchDTO.crsCreCd" />
		<input type="hidden" property="researchDTO.reshSn" />
		<input type="hidden" name="cmd"/>

		<table class="board_b" >
			<caption>설문등록</caption>
			<colgroup>
				<col style="width:20%"/>
				<col style="width:80%"/>
			</colgroup>
			<tbody>
				<tr>
					<th scope="row">설문 제목</th>
					<td >
						<nested:text style="width:90%" dispName="설문 제목" maxlength="100" isNull="N" lenCheck="100" property="researchDTO.reshTitle" onfocus="this.select()" class="text"/>
					</td>
				</tr>
				<tr >
					<th scope="row">설문 기간</th>
					<td >
						<nested:text style="width:60px;" dispName="설문 시작일" maxlength="50" isNull="N" lenCheck="50" property="researchDTO.reshStartDttm" readonly="true" class="reshStareTypeControl text"/>일
						<img src="<c:url value="/img/framework/icon/icon_calendar.gif"/>" onclick="Calendar('달력', 'researchForm', 'researchDTO.reshStartDttm', '.', 'position=right,datetype=00,clear=yes');" align="absmiddle"/>
						<nested:text style="width:20px" dispName="설문 시작 시간" maxlength="2" isNull="N" lenCheck="2" property="researchDTO.reshStartHour" class="reshStareTypeControl inputSpecial inputNumber text"/>시
						<nested:text style="width:20px" dispName="설문 시작 분" maxlength="2" isNull="N" lenCheck="2" property="researchDTO.reshStartMin" class="reshStareTypeControl inputSpecial inputNumber text"/>분
						~
						<nested:text style="width:60px;" dispName="설문 종료일" maxlength="50" isNull="N" lenCheck="50" property="researchDTO.reshEndDttm" readonly="true" class="reshStareTypeControl text"/>일
						<img src="<c:url value="/img/framework/icon/icon_calendar.gif"/>" onclick="Calendar('달력', 'researchForm', 'researchDTO.reshEndDttm', '.', 'position=right,datetype=00,clear=yes');" align="absmiddle"/>
						<nested:text style="width:20px" dispName="설문 종료 시간" maxlength="2" isNull="N" lenCheck="2" property="researchDTO.reshEndHour" class="reshStareTypeControl inputSpecial inputNumber text"/>시
						<nested:text style="width:20px" dispName="설문 종료 분" maxlength="2" isNull="N" lenCheck="2" property="researchDTO.reshEndMin" class="reshStareTypeControl inputSpecial inputNumber text"/>분
					</td>
				</tr>
				<tr>
					<th scope="row">설문 설명</th>
					<td >
						<nested:textarea style="width:90%;height:160px" dispName="설문 설명" isNull="N" property="researchDTO.reshCts"cols="100" rows="25"  />
					</td>
				</tr>
			</tbody>
		</table>
		</nested:form>
		<div class="btn_right">
			<a href="#" onclick="addResearch();" class="btn02">저장</a>
			<a href="ResearchLecture.do?cmd=mainResearch" class="btn01">목록</a>
		</div>

	</div>

<script type="text/javascript">

	$(document).ready(function() {
		$('.inputNumber').inputNumber();	// 숫자만 입력되도록 설정.
		$('.inputSpecial').inputSpecial();  // 특수키가 입력이 들어오묜 false return;
	});

	/**
	 * 설문 등록
	 */
	function addResearch() {
		var f = document.researchForm;
		if(isEmpty(f["researchDTO.reshTitle"].value)) {
			alert("설문 제목을 입력해 주세요.");
			f["researchDTO.reshTitle"].focus();
			return;
		}
		var reshStartDttm = chgDate(f["researchDTO.reshStartDttm"].value);
		var reshEndDttm = chgDate(f["researchDTO.reshEndDttm"].value);

		if(!dateCheck(reshStartDttm, reshEndDttm)) {
			alert("종료일을 시작일 보다 늦은 날짜를 선택하셔야 합니다.");
			return;
		}

		if(validate(document.researchForm) ==false) return ;
		if(validateTime()==false) return;

		$('#researchForm').find('input[name=cmd]').val("addResearch");
		$('#researchForm').submit();
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

	/*입력한 시간의 유효성을 체크한다.
	*  폼 벨리데이션 체크를 하지 않아서 빈값까지 여기서 검증함
	*/
	function validateTime(){

		var f = document.researchForm;

		var asmtStartHour = chgDate(f["researchDTO.reshStartHour"].value);  //과제 시작일 시''
		var asmtStartMin = chgDate(f["researchDTO.reshStartMin"].value);   //과제 시작일 분''

		var asmtEndHour = chgDate(f["researchDTO.reshEndHour"].value);  //과제 종료일 시''
		var asmtEndMin = chgDate(f["researchDTO.reshEndMin"].value);   //과제 종료일 분''


		if(asmtStartHour==""){
			alert("설문시작 시간을 등록하여 주시기 바랍니다." );
			f["researchDTO.reshStartHour"].focus();
			return false;
		}
		if(asmtStartMin==""){
			alert("설문시작 시간(분)을 등록하여 주시기 바랍니다." );
			f["researchDTO.reshStartMin"].focus();
			return false;
		}
		if(asmtEndHour==""){
			alert("설문종료 시간을 등록하여 주시기 바랍니다." );
			f["researchDTO.reshEndHour"].focus();
			return false;
		}
		if(asmtEndMin==""){
			alert("설문료 시간(분)을 등록하여 주시기 바랍니다." );
			f["researchDTO.reshEndMin"].focus();
			return false;
		}

		if(asmtStartHour>24 || asmtStartHour>24 ){
			alert("시간의(시) 의 설정값은 00이상 23 이하로 등록하여 주시기 바랍니다");
			return false;
		}

		if(asmtStartMin>59 || asmtEndMin>59 ){
			alert("시간의(분) 의 설정값은 00이상 59 이하로 등록하여 주시기 바랍니다");
			return false;
		}

		return true;
	}

</script>
</mhtml:class_body>
</mhtml:class_html>