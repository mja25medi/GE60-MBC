<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="img_base" value="/img/home" />
<c:set var="researchDTO" value="${researchForm.researchDTO}" />
<c:set var="itemList" value="${researchForm.researchAnswerListDTO}" />
<mhtml:class_html>
<mhtml:class_head>
	<meditag:js src="/js/calendar.js"/>
</mhtml:class_head>
<mhtml:class_body>
	<div id="content">
		<mhtml:class_location />

 		<div id="viewResearchInfo">
       		<table class="board_b">
       			<caption>설문 정보</caption>
       			<colgroup>
       				<col style="width:20%"/>
       				<col style="width:80%"/>
       			</colgroup>
       			<tbody>
				<tr>
					<th>설문 제목</th>
					<td>
						<strong>${researchDTO.reshTitle}</strong>
					</td>
				</tr>
				<tr>
					<th>설문 기간</th>
					<td><strong>${researchDTO.reshStartDttm} ~ ${researchDTO.reshEndDttm}</strong></td>
				</tr>
				</tbody>
			</table>
			<div class="btn_right">
				<a href="#" onclick="viewEditForm()" class="btn02">수정</a>
				<a href="<c:url value="/ResearchLecture.do"/>?cmd=mainResearch&amp;researchDTO.crsCreCd=${researchDTO.crsCreCd}" class="btn01" >목록</a>
			</div>
		</div>
		<!--  end of onload DIV -->

		<!-- 수정버튼 이벤트 DIV -->
		<div id="viewEditResearch" style="display:none">
			<nested:form action="/ResearchLecture.do" onsubmit="return false" id="researchForm">
			<input type="hidden" name="cmd"/>
			<input type="hidden" property="researchDTO.crsCreCd" />
			<input type="hidden" property="researchDTO.reshSn" />
			<input type="hidden" property="researchDTO.regYn" />
			<input type="hidden" property="researchDTO.itemCnt" />
			<input type="hidden" property="researchDTO.ansrCnt" />
			<input type="hidden" property="researchDTO.editMode" value="listJoinUser"/>
			<table class="board_b">
       			<caption>설문 수정</caption>
       			<colgroup>
       				<col style="width:20%"/>
       				<col style="width:80%"/>
       			</colgroup>
       			<tbody>
					<tr>
						<th>설문 제목</th>
						<td><nested:text style="width:90%" dispName="설문 제목" maxlength="100" isNull="N" lenCheck="100" property="researchDTO.reshTitle" onfocus="this.select()" class="text"/></td>
					</tr>
					<tr>
						<th>설문 기간</th>
						<td>
							<nested:text style="width:70px;" dispName="설문 시작일" maxlength="50" isNull="N" lenCheck="50" property="researchDTO.reshStartDttm" readonly="true" class="reshStareTypeControl text"/>일
							<img src="<c:url value="/img/framework/icon/icon_calendar.gif"/>" onclick="Calendar('달력', 'researchForm', 'researchDTO.reshStartDttm', '.', 'position=right,datetype=00,clear=yes');" align="absmiddle"/>
							<nested:text style="width:20px" dispName="설문 시작 시간" maxlength="2" isNull="N" lenCheck="2" property="researchDTO.reshStartHour" class="reshStareTypeControl text"/>시
							<nested:text style="width:20px" dispName="설문 시작 분" maxlength="2" isNull="N" lenCheck="2" property="researchDTO.reshStartMin" class="reshStareTypeControl text"/>분
							~
							<nested:text style="width:70px;" dispName="설문 종료일" maxlength="50" isNull="N" lenCheck="50" property="researchDTO.reshEndDttm" readonly="true" class="reshStareTypeControl text"/>일
							<img src="<c:url value="/img/framework/icon/icon_calendar.gif"/>" " onclick="Calendar('달력', 'researchForm', 'researchDTO.reshEndDttm', '.', 'position=right,datetype=00,clear=yes');" align="absmiddle"/>
							<nested:text style="width:20px" dispName="설문 종료 시간" maxlength="2" isNull="N" lenCheck="2" property="researchDTO.reshEndHour" class="reshStareTypeControl text"/>시
							<nested:text style="width:20px" dispName="설문 종료 분" maxlength="2" isNull="N" lenCheck="2" property="researchDTO.reshEndMin" class="reshStareTypeControl text"/>분
						</td>
					</tr>
					<tr >
						<th>설문 설명</th>
						<td>
							<nested:textarea style="width:90%;height:160px;" dispName="설문 설명" property="researchDTO.reshCts"/>
						</td>
					</tr>
				</tbody>
			</table>
			</nested:form>
			 <div class="btn_right">
				<a href="#" onclick="saveResearch()" class="btn02">저장</a>
				<a href="#" onclick="delResearch()" class="btn02">삭제</a>
				<a href="#" onclick="hideEditForm()" class="btn01">닫기</a>
			</div>
		</div>

		<ul class="tab_arrow">
			<li><a href="<c:url value="/ResearchLecture.do"/>?cmd=listItem&amp;researchDTO.crsCreCd=${researchDTO.crsCreCd}&amp;researchDTO.reshSn=${researchDTO.reshSn}">문제관리</a></li>
			<li><a href="<c:url value="/ResearchLecture.do"/>?cmd=listResult&amp;researchDTO.crsCreCd=${researchDTO.crsCreCd}&amp;researchDTO.reshSn=${researchDTO.reshSn}">설문결과</a></li>
			<li class="last"><strong>참여자보기</strong></li>
		</ul>
		<div class="btn_right" style="margin-top:10px;margin-bottom:10px;">
			<a href="#" onclick="excelDownloadJoinList()" class="btn02">엑셀다운로드</a>
		</div>

		<table class="board_lecture" summary="번호, 이름, 아이디, 휴대폰, E-MAIL">
			<caption>참여자목록</caption>
			<colgroup>
				<col width="6%" />
				<col width="15%" />
				<col width="20%" />
				<col width="15%" />
				<col width="auto" />
			</colgroup>
			<thead>
				<tr>
					<th scope="col">번호</th>
					<th scope="col">이름</th>
					<th scope="col">아이디</th>
					<th scope="col">휴대폰</th>
					<th scope="col">E-MAIL</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${itemList}" varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${item.userNm}</td>
					<td>${item.userId}</td>
					<td>${item.mobileNo}</td>
					<td>${item.email}</td>
				</tr>
				</c:forEach>
				<c:if test="${empty itemList}">
				<tr>
					<td colspan="5">* 참여한 수강생이 없습니다.</td>
				</tr>
				</c:if>
			</tbody>
		</table>

	</div>

<script type="text/javascript">
	$(document).ready(function() {

	});

	/**
	 * 수정 화면 토글
	 */
	function viewEditForm() {
		$("#viewResearchInfo").hide();
		$("#viewEditResearch").show();

	}

	/**
	 * 수정 화면 토글
	 */
	function hideEditForm() {
		$("#viewResearchInfo").show();
		$("#viewEditResearch").hide();
	}

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		$('#researchForm').find('input[name=cmd]').val(cmd);
		$('#researchForm').submit();
	}

	/**
	 * 설문 정보 수정
	 */
	function saveResearch() {
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

		process("editResearch");	// cmd
	}

	//-- 설문 정보 삭제
	function delResearch() {
		if(parseInt(document.researchForm["researchDTO.itemCnt"].value,10) > 0) {
			alert('설문 문제가 등록되어 있어서 설문을 삭제 할 수 없습니다.');
			return;
		} else {
			process("deleteResearch");
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
			alert("시작 시간을 등록하여 주시기 바랍니다." );
			f["researchDTO.reshStartHour"].focus();
			return false;
		}
		else if(asmtStartMin==""){
			alert("시작 시간(분)을 등록하여 주시기 바랍니다." );
			f["researchDTO.reshStartMin"].focus();
			return false;
		}
		else if(asmtEndHour==""){
			alert("종료 시간을 등록하여 주시기 바랍니다." );
			f["researchDTO.reshEndHour"].focus();
			return false;
		}
		else if(asmtEndMin==""){
			alert("종료 시간(분)을 등록하여 주시기 바랍니다." );
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

	/**
	* 참가한 인원 명단 엑셀 출력
	*/
	function excelDownloadJoinList() {

		var reshSn = '${researchDTO.reshSn}';

		// download용 iframe이 없으면 만든다.
		if ( $("#_m_download_iframe").length == 0 ) {
			iframeHtml =
				'<iframe id="_m_download_iframe" name="_m_download_iframe" style="visibility: none; display: none;"></iframe>';
			$("body").append(iframeHtml);
		}
		// 폼에 action을 설정하고 submit시킨다.
		$("#researchForm").attr('target', '_m_download_iframe');
		$("#researchForm").find('input[name=cmd]').val('listExcelDownloadJoinConfirm').find('input[$name=reshSn]').val(reshSn);
		$("#researchForm").submit();
		$("#researchForm").removeAttr('target');
	}
</script>
</mhtml:class_body>
</mhtml:class_html>
