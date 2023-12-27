<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="img_base" value="/img/home" />
<c:set var="gubun" value="${researchForm.gubun}"/>
<c:set var="researchDTO" value="${researchForm.researchDTO}" />
<mhtml:class_html>
<mhtml:class_head>
	<mhtml:head_module simpletab="Y" />
	<meditag:js src="/js/calendar.js"/>
</mhtml:class_head>
<mhtml:class_body>
	<div id="content">
		<mhtml:class_location />


		<nested:form action="/ResearchLecture.do" onsubmit="return false" id="researchForm">
		<input type="hidden" name="cmd"/>
		<input type="hidden" property="researchDTO.crsCreCd" />
		<input type="hidden" property="researchDTO.reshSn" />
		<input type="hidden" property="researchDTO.regYn" />
		<input type="hidden" property="researchDTO.itemCnt" />
		<input type="hidden" property="researchDTO.ansrCnt" />
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
			<a href="<c:url value="/ResearchLecture.do"/>?cmd=mainResearch&amp;researchDTO.crsCreCd=${researchDTO.crsCreCd}" class="btn01">목록</a>
		</div>

	</div>

<script type="text/javascript">

	$(document).ready(function() {
		hideEditForm();
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
		if(cmd == "deleteResearch") $('#researchForm').ajaxSubmit(processDeleteCallback);
		else $('#researchForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		alert(resultDTO.message);
		if(resultDTO.result >= 0) {
			// 정상 처리
			document.location.href = cUrl("/ResearchLecture.do")+"?cmd=editFormResearch${AMPERSAND}researchDTO.crsCreCd=${researchDTO.crsCreCd}${AMPERSAND}researchDTO.reshSn=${researchDTO.reshSn}";
		} else {
			// 비정상 처리
		}
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processDeleteCallback(resultDTO) {
		alert(resultDTO.message);
		if(resultDTO.result >= 0) {
			// 정상 처리
			document.location.href = cUrl("/ResearchLecture.do")+"?cmd=mainResearch${AMPERSAND}researchDTO.crsCreCd=${researchDTO.crsCreCd}";
		} else {
			// 비정상 처리
		}
	}

	function delResearch() {
		if(parseInt(document.researchForm["researchDTO.itemCnt"].value,10) > 0) {
			alert('설문 문제가 등록되어 있어서 설문을 삭제 할 수 없습니다.');
			return;
		} else {
			process("deleteResearch");
		}
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

	function parentResize() {
		var iframeObj = parent.document.getElementById("subWorkFrame"); //-- $('#subWorkFrame', parent);
		parent.resizeIframe(iframeObj, 200);
	}

	/**
	 * 설문 문제 목록 조회
	 */
	function listItem() {
		$.getJSON( cUrl("/ResearchLecture.do"),		// url
				{ "cmd" : "listItem",
				  "researchItemDTO.crsCreCd" : '${researchDTO.crsCreCd}',
				  "researchItemDTO.reshSn" : '${researchDTO.reshSn}'
				},			// params
				listItemCallback				// callback function
			);
	}

	/**
	 * 설문 문제 목록 콜백
	 */
	function listItemCallback(ProcessResultListDTO) {

		var retList = ProcessResultListDTO.returnList;
		var listStr = "";

		if(retList.length == 0) {
			listStr +="	<tr><td colspan='5' align='center'><font color=blud>* 검색된 문제가 없습니다.</font></td></tr>\n";
		}

		var qstnCnt = 0;
		var lineStart = "N";

		for (var i=0; i<retList.length; i++) {
			var item = retList[i];

			var editLink = "<a href=\"javascript:itemEdit('"+item.reshItemSn+"')\">수정</a>";
			if($('#researchForm').find('input[name$=regYn]').val() == "Y") editLink = "<font color='gray'>수정</font>";

			listStr +="	<tr>\n";
			listStr +="		<td >"+(i+1)+"</td>\n";
			listStr +="		<td class='subject'>"+item.reshItemCts+"</td>\n";
			listStr +="		<td >"+item.reshItemTypeNm+"</td>\n";
			listStr +="		<td class='rnone'>"+editLink+"</td>\n";
			listStr +="	</tr>\n";

		}
		$("#itemList").html(listStr);

		var iframeObj = parent.document.getElementById("subWorkFrame"); //-- $('#subWorkFrame', parent);

	}


	/**
	 * 설문 문제 등록 폼 호출
	 */
	function itemWrite() {
		location.href= cUrl("ResearchLecture.do")+"?cmd=addFormItem${AMPERSAND}researchItemDTO.reshSn="+${researchDTO.reshSn};
	}

	function itemEdit(reshItemSn){
		location.href= cUrl("ResearchLecture.do")+"?cmd=editFormItem${AMPERSAND}researchItemDTO.reshSn="+${researchDTO.reshSn}+"${AMPERSAND}researchItemDTO.reshItemSn="+reshItemSn;
	}
	/**
	 * 결과 화면 보기
	 */
	function listResult(){
		$.getJSON( cUrl("/ResearchLecture.do"),		// url
				{ "cmd" : "listResult",
				  "researchItemDTO.crsCreCd" : '${researchDTO.crsCreCd}',
				  "researchItemDTO.reshSn" : '${researchDTO.reshSn}'
				},			// params
				listResResultCallback				// callback function
			);
	}

	/**
	 * 설문 결과 목록 콜백
	 */
	function listResResultCallback(ProcessResultListDTO) {

		var retList = ProcessResultListDTO.returnList;
		var listStr = "";
		var pageInfo = ProcessResultListDTO.pageInfo;

		if(retList.length == 0) {
			listStr +="	<tr><td colspan='9' align='center'><font color=blud>* 등록된 설문 문항이 없습니다.</font></td></tr>\n";
		}

		for (var i=0; i<retList.length; i++) {
			var item = retList[i];
			var empl1Ratio = "0"; var empl2Ratio = "0"; var empl3Ratio = "0"; var empl4Ratio = "0"; var empl5Ratio = "0";
			var empl6Ratio = "0"; var empl7Ratio = "0"; var empl8Ratio = "0"; var empl9Ratio = "0"; var empl0Ratio = "0";

			listStr +="	<tr>\n";
			listStr +="	 <td >"+(i+1)+"</td>\n";
			if(item.reshItemTypeCd !='D'){
				listStr +="	 <td class='subject' valign='top'><p class='subject_tit'>"+item.reshItemCts+"<em>("+item.reshItemTypeNm+")</em></p>\n";
				listStr +="     <table > \n";
				listStr +="		<tr>\n";
				listStr +="			<td class='subject' valign='top'>\n";
				listStr +="			<ul class='vote_lst'>\n";

				if(item.empl1 != "") {
					if(item.emplCnt == 0) empl1Ratio = "0";
					else empl1Ratio = item.empl1Cnt / item.emplCnt;
					listStr +="				<li>1. "+item.empl1+"<span>"+item.empl1Cnt+"표("+item.emplCnt+")</span></li> \n";
				}
				if(item.empl2 != "") {
					if(item.emplCnt == 0) empl2Ratio = "0";
					else empl2Ratio = item.empl2Cnt / item.emplCnt;
					listStr +="				<li>2."+item.empl2+"<span>"+item.empl2Cnt+"표("+item.emplCnt+")</span></li> \n";
				}
				if(item.empl3 != "") {
					if(item.emplCnt == 0) empl3Ratio = "0";
					else empl3Ratio = item.empl3Cnt / item.emplCnt;
					listStr +="				<li>3."+item.empl3+"<span>"+item.empl3Cnt+"표("+item.emplCnt+")</span></li> \n";
				}
				if(item.empl4 != "") {
					if(item.emplCnt == 0) empl41Ratio = "0";
					else empl4Ratio = item.empl4Cnt / item.emplCnt;
					listStr +="				<li>4."+item.empl4+"<span>"+item.empl4Cnt+"표("+item.emplCnt+")</span></li> \n";
				}
				if(item.empl5 != "") {
					if(item.emplCnt == 0) empl5Ratio = "0";
					else empl5Ratio = item.empl5Cnt / item.emplCnt;
					listStr +="				<li>5."+item.empl5+"<span>"+item.empl5Cnt+"표("+item.emplCnt+")</span></li> \n";
				}
				if(item.empl6 != "") {
					if(item.emplCnt == 0) empl6Ratio = "0";
					else empl6Ratio = item.empl6Cnt / item.emplCnt;
					listStr +="				<li>6."+item.empl6+"<span>"+item.empl6Cnt+"표("+item.emplCnt+")</span></li> \n";
				}
				if(item.empl7 != "") {
					if(item.emplCnt == 0) empl7Ratio = "0";
					else empl7Ratio = item.empl7Cnt / item.emplCnt;
					listStr +="				<li>7."+item.empl7+"<span>"+item.empl7Cnt+"표("+item.emplCnt+")</span></li> \n";
				}
				if(item.empl8 != "") {
					if(item.emplCnt == 0) empl8Ratio = "0";
					else empl8Ratio = item.empl8Cnt / item.emplCnt;
					listStr +="				<li>8."+item.empl8+"<span>"+item.empl8Cnt+"표("+item.emplCnt+")</span></li> \n";
				}
				if(item.empl9 != "") {
					if(item.emplCnt == 0) empl9Ratio = "0";
					else empl9Ratio = item.empl9Cnt / item.emplCnt;
					listStr +="				<li>9."+item.empl9+"<span>"+item.empl9Cnt+"표("+item.emplCnt+")</span></li> \n";
				}
				if(item.empl10 != "") {
					if(item.emplCnt == 0) empl10Ratio = "0";
					else empl10Ratio = item.empl10Cnt / item.emplCnt;
					listStr +="				<li>10."+item.empl10+"<span>"+item.empl10Cnt+"표("+item.emplCnt+")</span></li> \n";
				}
				listStr +="	            </ul>                                 ";
				listStr +="				</td>   							  ";
				listStr +="			</tr>   							  ";
				listStr +="			</table>   							  ";
				listStr +="		 </td>   							  ";

			}else if(item.reshItemTypeCd =='D'){
				listStr +="	 <td class='subject' valign='top'><p class='subject_tit'>"+item.reshItemCts+"<em>("+item.reshItemTypeNm+")</em></p></td>\n";
			}

			if(item.reshItemTypeCd != "D"){
				listStr +="		<td class='rnone' valign='top'>       ";
				listStr +="		<ul >         ";
				// 그래프부분
				if(item.empl1 != "") {
					if(item.emplCnt == 0) empl1Ratio = "0";
					else empl1Ratio = Math.round((item.empl1Cnt / item.emplCnt) *100);
					listStr +="				<li><div class='rate01'><p><br><br></p><div class='graphe'><p class='ingraphe'>\n";
					listStr +="					<img src='${img_base}/classroom/graphe_blue.gif' height='15' style='width:"+empl1Ratio+"%;' /> \n";
					listStr +="                  <strong>"+item.empl1Cnt+"표<span>("+empl1Ratio+"%)</strong></p> \n";
					listStr +="					</div></div></li>	\n";
				}
				if(item.empl2 != "") {
					if(item.emplCnt == 0) empl2Ratio = "0";
					else empl2Ratio = Math.round((item.empl2Cnt / item.emplCnt) *100);
					listStr +="				<li><div class='rate01'><div class='graphe'><p class='ingraphe'>\n";
					listStr +="					<img src='${img_base}/classroom/graphe_blue.gif' height='15' style='width:"+empl2Ratio+"%;' /> \n";
					listStr +="                  <strong>"+item.empl2Cnt+"표<span>("+empl2Ratio+"%)</strong> \n";
					listStr +="					</p></div></div></li>	\n";
				}
				if(item.empl3 != "") {
					if(item.emplCnt == 0) empl3Ratio = "0";
					else empl3Ratio = Math.round((item.empl3Cnt / item.emplCnt) *100);
					listStr +="				<li><div class='rate01'><div class='graphe'><p class='ingraphe'>\n";
					listStr +="					<img src='${img_base}/classroom/graphe_blue.gif' height='15' style='width:"+empl3Ratio+"%;' /> \n";
					listStr +="                  <strong>"+item.empl3Cnt+"표<span>("+empl3Ratio+"%)</strong> \n";
					listStr +="					</p></div></div></li>	\n";
				}
				if(item.empl4 != "") {
					if(item.emplCnt == 0) empl41Ratio = "0";
					else empl4Ratio = Math.round((item.empl4Cnt / item.emplCnt) *100);
					listStr +="				<li><div class='rate01'><div class='graphe'><p class='ingraphe'>\n";
					listStr +="					<img src='${img_base}/classroom/graphe_blue.gif' height='15' style='width:"+empl4Ratio+"%;' /> \n";
					listStr +="                  <strong>"+item.empl4Cnt+"표<span>("+empl4Ratio+"%)</strong> \n";
					listStr +="					</p></div></div></li>	\n";
				}
				if(item.empl5 != "") {
					if(item.emplCnt == 0) empl5Ratio = "0";
					else empl5Ratio = Math.round((item.empl5Cnt / item.emplCnt) *100);
					listStr +="				<li><div class='rate01'><div class='graphe'><p class='ingraphe'>\n";
					listStr +="					<img src='${img_base}/classroom/graphe_blue.gif' height='15' style='width:"+empl5Ratio+"%;' /> \n";
					listStr +="                  <strong>"+item.empl5Cnt+"표<span>("+empl5Ratio+"%)</strong> \n";
					listStr +="					</p></div></div></li>	\n";
				}
				if(item.empl6 != "") {
					if(item.emplCnt == 0) empl6Ratio = "0";
					else empl6Ratio = Math.round((item.empl6Cnt / item.emplCnt) *100);
					listStr +="				<li><div class='rate01'><div class='graphe'><p class='ingraphe'>\n";
					listStr +="					<img src='${img_base}/classroom/graphe_blue.gif' height='15' style='width:"+empl6Ratio+"%;' /> \n";
					listStr +="                  <strong>"+item.empl6Cnt+"표<span>("+empl6Ratio+"%)</strong> \n";
					listStr +="					</p></div></div></li>	\n";
				}
				if(item.empl7 != "") {
					if(item.emplCnt == 0) empl7Ratio = "0";
					else empl7Ratio = Math.round((item.empl7Cnt / item.emplCnt) *100);
					listStr +="				<li><div class='rate01'><div class='graphe'><p class='ingraphe'>\n";
					listStr +="					<img src='${img_base}/classroom/graphe_blue.gif' height='15' style='width:"+empl7Ratio+"%;' /> \n";
					listStr +="                  <strong>"+item.empl7Cnt+"표<span>("+empl7Ratio+"%)</strong> \n";
					listStr +="					</p></div></div></li>	\n";
				}
				if(item.empl8 != "") {
					if(item.emplCnt == 0) empl8Ratio = "0";
					else empl8Ratio =Math.round((item.empl8Cnt / item.emplCnt) *100);
					listStr +="				<li><div class='rate01'><div class='graphe'><p class='ingraphe'>\n";
					listStr +="					<img src='${img_base}/classroom/graphe_blue.gif' height='15' style='width:"+empl8Ratio+"%;' /> \n";
					listStr +="                  <strong>"+item.empl8Cnt+"표<span>("+empl8Ratio+"%)</strong> \n";
					listStr +="					</p></div></p></div></div></li>	\n";
				}
				if(item.empl9 != "") {
					if(item.emplCnt == 0) empl9Ratio = "0";
					else empl9Ratio = Math.round((item.empl9Cnt / item.emplCnt) *100);
					listStr +="				<li><div class='rate01'><div class='graphe'><p class='ingraphe'>\n";
					listStr +="					<img src='${img_base}/classroom/graphe_blue.gif' height='15' style='width:"+empl9Ratio+"%;' /> \n";
					listStr +="                  <strong>"+item.empl9Cnt+"표<span>("+empl9Ratio+"%)</strong> \n";
					listStr +="					</p></div></div></li>	\n";
				}
				if(item.empl10 != "") {
					if(item.emplCnt == 0) empl10Ratio = "0";
					else empl10Ratio =Math.round((item.empl10Cnt / item.emplCnt) *100);
					listStr +="				<li><div class='rate01'><div class='graphe'><p class='ingraphe'>\n";
					listStr +="					<img src='${img_base}/classroom/graphe_blue.gif' height='15' style='width:"+empl10Ratio+"%;' /> \n";
					listStr +="                  <strong>"+item.empl10Cnt+"표<span>("+empl10Ratio+"%)</strong> \n";
					listStr +="					</p></div></div></li>	\n";
				}

				listStr +="			</ui>\n";
				listStr +="		</td >\n";

			}else if(item.reshItemTypeCd == "D") {


				listStr +="				<td class='subject rnone' valign='top'>\n";
				listStr +="					<a href=\"javascript:listReshPop('"+item.reshItemSn+"')\"><img src='${img_base}/common/btn/btn_result.gif' width='39' height='21' alt='결과' /></a> \n";
				listStr +="					<a href='#' onclick='excelDownloadList()'><img src='${img_base}/common/btn/btn_excel.gif' width='50' height='21' alt='엑셀' /></a> \n";
				listStr +="				</td>";
			}

			listStr +="	</tr>\n";


		}
		$("#resultList").html(listStr);

		var iframeObj = parent.document.getElementById("subWorkFrame"); //-- $('#subWorkFrame', parent);

	}

	// 설문에 참가한 사람들 명단을 받아온다.
	function listJoinUser() {
		$.getJSON( cUrl("/ResearchLecture.do"),		// url
				{ "cmd" : "listJoinUser",
			 	 "researchAnswerDTO.reshSn" : '${researchDTO.reshSn}'
				},			// params
				listJoinResultCallback				// callback function
			);
	}

	function listReshPop(itemSn){
		var addContent  = "<iframe id='listFrame' name='listFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='no' src='<c:url value="/ResearchLecture.do"/>"
			+ "?cmd=listReshPop&amp;researchAnswerDTO.reshItemSn="+itemSn+"&amp;researchAnswerDTO.reshSn="+${researchDTO.reshSn}+"'/>";

			listbox.clear();
			listbox.addContents(addContent);
			listbox.resize(750, 720);
			listbox.setTitle("서술형 참가자");
			listbox.show();
	}

	/**
	 * 설문 문제 목록 콜백
	 */
	function listJoinResultCallback(ProcessResultListDTO) {

		var joinList = ProcessResultListDTO.returnList;
		var listStr = "";

		if(joinList.length == 0) {
			listStr +="	<tr><td colspan='6' align='center' class='rnone'><font color=blud>* 설문에 참여한 인원이 없습니다.</font></td></tr>\n";
		}


		for (var i=0; i<joinList.length; i++) {
			var item = joinList[i];

			listStr +="	<tr>\n";
			listStr +="		<td >"+(i+1)+"</td>\n";
			listStr +="		<td class='subject'>"+item.userNm+"</td>\n";
			listStr +="		<td class='subject'>"+item.userId+"</td>\n";
			listStr +="		<td >"+item.homePhoneno+"</td>\n";
			listStr +="		<td >"+item.mobileNo+"</td>\n";
			listStr +="		<td class='rnone'>"+item.email+"</td>\n";
			listStr +="	</tr>\n";

		}
		$("#joinList").html(listStr);

	}
	//서술형 설문에 등록한 참가인 명단을 받아온다.
	function excelDownloadList() {

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
		$("#researchForm").submit();
		$("#researchForm").removeAttr('target');
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
			alert("과제시작 시간을 등록하여 주시기 바랍니다." );
			f["researchDTO.reshStartHour"].focus();
			return false;
		}
		else if(asmtStartMin==""){
			alert("과제시작 시간(분)을 등록하여 주시기 바랍니다." );
			f["researchDTO.reshStartMin"].focus();
			return false;
		}
		else if(asmtEndHour==""){
			alert("과제종료 시간을 등록하여 주시기 바랍니다." );
			f["researchDTO.reshEndHour"].focus();
			return false;
		}
		else if(asmtEndMin==""){
			alert("과제종료 시간(분)을 등록하여 주시기 바랍니다." );
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

	function itemBank(){
		var addContent  = "<iframe id='bankFrame' name='bankFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/ResearchLecture.do"/>"
			+ "?cmd=editFormItemBank&amp;researchDTO.reshSn="+${researchDTO.reshSn}+"'/>";

		bankBox.clear();
		bankBox.addContents(addContent);
		bankBox.resize(750, 600);
		bankBox.setTitle("문제 은행");
		bankBox.show();
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
</script>
</mhtml:class_body>
</mhtml:class_html>
