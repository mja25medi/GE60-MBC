<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="img_base" value="/img/home" />
<c:set var="researchDTO" value="${researchForm.researchDTO}" />
<c:set var="itemList" value="${researchForm.researchResultList}" />
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
			<input type="hidden" property="researchDTO.editMode" value="listResult"/>
			<input type="hidden" property="researchAnswerDTO.reshItemSn" id="reshItemSn"/>
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
			<li><strong>설문결과</strong></li>
			<li class="last"><a href="<c:url value="/ResearchLecture.do"/>?cmd=listJoinUser&amp;researchDTO.crsCreCd=${researchDTO.crsCreCd}&amp;researchDTO.reshSn=${researchDTO.reshSn}">참여자보기</a></li>
		</ul>
		<div class="btn_right" style="margin-top:10px;margin-bottom:10px;">
			<a href='#' onclick='excelResearchResultList()' class="btn02">엑셀다운로드</a>
		</div>
		<table class="board_lecture" summary="번호, 설문내용, 응답결과">
			<caption>설문결과</caption>
			<colgroup>
				<col width="6%" />
				<col width="auto" />
				<col width="40%" />
			</colgroup>
			<thead>
				<tr>
					<th scope="col">번호</th>
					<th scope="col">설문내용</th>
					<th scope="col" class="rnone">응답결과</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${itemList}" varStatus="status">
				<c:if test="${item.reshItemTypeCd eq 'D'}">
				<tr>
					<td>${status.count}</td>
					<td class="survay">${item.reshItemCts} (${item.reshItemTypeNm})</td>
					<td>
						<a href="#" onclick="listReshPop('${item.reshItemSn}')" class="btn02">결과</a>
						<a href="#" onclick="excelDownloadList('${item.reshItemSn}')" class="btn01">엑셀</a>
					</td>
				</tr>
				</c:if>
				<c:if test="${item.reshItemTypeCd eq 'K'}">
				<tr>
					<td>${status.count}</td>
					<td class="survay" colspan="2">
						${item.reshItemCts} (${item.reshItemTypeNm})
						<ul class="qngraph">
							<c:if test="${not empty item.empl1}">
							<c:if test="${item.emplCnt == 0}"><c:set var="empl1Ratio" value="0"/></c:if>
							<c:if test="${item.emplCnt > 0}"><c:set var="empl1Ratio" value="${item.empl1Cnt/item.emplCnt * 100}"/></c:if>
							<li>
								<p>1. ${item.empl1}</p>
								<div class="vote"><span style="width:${empl1Ratio}%;"></span><em>${item.empl1Cnt}표(${empl1Ratio}%)</em></div>
							</li>
							</c:if>

							<c:if test="${not empty item.empl2}">
							<c:if test="${item.emplCnt == 0}"><c:set var="empl2Ratio" value="0"/></c:if>
							<c:if test="${item.emplCnt > 0}"><c:set var="empl2Ratio" value="${item.empl2Cnt/item.emplCnt * 100}"/></c:if>
							<li>
								<p>2. ${item.empl2}</p>
								<div class="vote"><span style="width:${empl2Ratio}%;"></span><em>${item.empl2Cnt}표(${empl2Ratio}%)</em></div>
							</li>
							</c:if>

							<c:if test="${not empty item.empl3}">
							<c:if test="${item.emplCnt == 0}"><c:set var="empl3Ratio" value="0"/></c:if>
							<c:if test="${item.emplCnt > 0}"><c:set var="empl3Ratio" value="${item.empl3Cnt/item.emplCnt * 100}"/></c:if>
							<li>
								<p>3. ${item.empl3}</p>
								<div class="vote"><span style="width:${empl3Ratio}%;"></span><em>${item.empl3Cnt}표(${empl3Ratio}%)</em></div>
							</li>
							</c:if>

							<c:if test="${not empty item.empl4}">
							<c:if test="${item.emplCnt == 0}"><c:set var="empl4Ratio" value="0"/></c:if>
							<c:if test="${item.emplCnt > 0}"><c:set var="empl4Ratio" value="${item.empl4Cnt/item.emplCnt * 100}"/></c:if>
							<li>
								<p>4. ${item.empl4}</p>
								<div class="vote"><span style="width:${empl4Ratio}%;"></span><em>${item.empl4Cnt}표(${empl4Ratio}%)</em></div>
							</li>
							</c:if>

							<c:if test="${not empty item.empl5}">
							<c:if test="${item.emplCnt == 0}"><c:set var="empl5Ratio" value="0"/></c:if>
							<c:if test="${item.emplCnt > 0}"><c:set var="empl5Ratio" value="${item.empl5Cnt/item.emplCnt * 100}"/></c:if>
							<li>
								<p>5. ${item.empl5}</p>
								<div class="vote"><span style="width:${empl5Ratio}%;"></span><em>${item.empl5Cnt}표(${empl5Ratio}%)</em></div>
							</li>
							</c:if>

							<c:if test="${not empty item.empl6}">
							<c:if test="${item.emplCnt == 0}"><c:set var="empl6Ratio" value="0"/></c:if>
							<c:if test="${item.emplCnt > 0}"><c:set var="empl6Ratio" value="${item.empl6Cnt/item.emplCnt * 100}"/></c:if>
							<li>
								<p>6. ${item.empl6}</p>
								<div class="vote"><span style="width:${empl6Ratio}%;"></span><em>${item.empl6Cnt}표(${empl6Ratio}%)</em></div>
							</li>
							</c:if>

							<c:if test="${not empty item.empl7}">
							<c:if test="${item.emplCnt == 0}"><c:set var="empl7Ratio" value="0"/></c:if>
							<c:if test="${item.emplCnt > 0}"><c:set var="empl7Ratio" value="${item.empl7Cnt/item.emplCnt * 100}"/></c:if>
							<li>
								<p>7. ${item.empl7}</p>
								<div class="vote"><span style="width:${empl7Ratio}%;"></span><em>${item.empl7Cnt}표(${empl7Ratio}%)</em></div>
							</li>
							</c:if>

							<c:if test="${not empty item.empl8}">
							<c:if test="${item.emplCnt == 0}"><c:set var="empl8Ratio" value="0"/></c:if>
							<c:if test="${item.emplCnt > 0}"><c:set var="empl8Ratio" value="${item.empl8Cnt/item.emplCnt * 100}"/></c:if>
							<li>
								<p>8. ${item.empl8}</p>
								<div class="vote"><span style="width:${empl8Ratio}%;"></span><em>${item.empl8Cnt}표(${empl8Ratio}%)</em></div>
							</li>
							</c:if>

							<c:if test="${not empty item.empl9}">
							<c:if test="${item.emplCnt == 0}"><c:set var="empl9Ratio" value="0"/></c:if>
							<c:if test="${item.emplCnt > 0}"><c:set var="empl9Ratio" value="${item.empl9Cnt/item.emplCnt * 100}"/></c:if>
							<li>
								<p>9. ${item.empl9}</p>
								<div class="vote"><span style="width:${empl9Ratio}%;"></span><em>${item.empl9Cnt}표(${empl9Ratio}%)</em></div>
							</li>
							</c:if>

							<c:if test="${not empty item.empl10}">
							<c:if test="${item.emplCnt == 0}"><c:set var="empl10Ratio" value="0"/></c:if>
							<c:if test="${item.emplCnt > 0}"><c:set var="empl10Ratio" value="${item.empl10Cnt/item.emplCnt * 100}"/></c:if>
							<li>
								<p>10. ${item.empl10}</p>
								<div class="vote"><span style="width:${empl10Ratio}%;"></span><em>${item.empl10Cnt}표(${empl10Ratio}%)</em></div>
							</li>
							</c:if>
						</ul>
					</td>

				</tr>
				</c:if>
				</c:forEach>
				<c:if test="${empty itemList}">
				<tr>
					<td colspan="3">* 등록된 설문 문제가 없습니다.</td>
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
	* 셀문결과 엑셀 출력
	*/
	function excelResearchResultList() {
		var reshSn = '${researchDTO.reshSn}';
		// download용 iframe이 없으면 만든다.
		if ( $("#_m_download_iframe").length == 0 ) {
			iframeHtml =
				'<iframe id="_m_download_iframe" name="_m_download_iframe" style="visibility: none; display: none;"></iframe>';
			$("body").append(iframeHtml);
		}
		// 폼에 action을 설정하고 submit시킨다.
		$("#researchForm").attr('target', '_m_download_iframe');
		$("#researchForm").find('input[name=cmd]').val('listExcelDownloadlResearchResultList').find('input[$name=reshSn]').val(reshSn);
		$("#researchForm").submit();
		$("#researchForm").removeAttr('target');
	}

	function listReshPop(itemSn){
		var url = cUrl("/ResearchLecture.do")+"?cmd=listReshPop${AMPERSAND}researchAnswerDTO.crsCreCd=${researchDTO.crsCreCd}${AMPERSAND}researchAnswerDTO.reshItemSn="+itemSn+"${AMPERSAND}researchAnswerDTO.reshSn=${researchDTO.reshSn}";
		var option = "width=750px, height=720px, toolbar=no, menubar=no, scrollbars=no, resizable=no, copyhistory=no";
		var resultWin = window.open(url,'resultWin', option);
		resultWin.focus();
	}

	//서술형 설문에 등록한 참가인 명단을 받아온다.
	function excelDownloadList(itemSn) {
		var reshSn = '${researchDTO.reshSn}'
		// download용 iframe이 없으면 만든다.
		if ( $("#_m_download_iframe").length == 0 ) {
			iframeHtml =
				'<iframe id="_m_download_iframe" name="_m_download_iframe" style="visibility: none; display: none;"></iframe>';
			$("body").append(iframeHtml);
		}
		// 폼에 action을 설정하고 submit시킨다.
		$("#researchForm").attr('target', '_m_download_iframe');
		$("#researchForm").find('input[name=cmd]').val('listExcelDownloadConfirm').find('input[$name=reshSn]').val(reshSn);
		$("#reshItemSn").val(itemSn);
		$("#researchForm").submit();
		$("#researchForm").removeAttr('target');
	}

</script>
</mhtml:class_body>
</mhtml:class_html>
