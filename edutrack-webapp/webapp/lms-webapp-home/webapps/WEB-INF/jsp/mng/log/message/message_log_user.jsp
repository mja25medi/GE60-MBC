<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<mhtml:html>
<mhtml:head titleName="${MENUNAME}">
	<meditag:js src="/js/selectbox.js"/>
	<meditag:js src="/js/contextmenu.js"/>
	<meditag:js src="/js/popupbox.js"/>
	<meditag:js src="/js/calendar.js"/>
	<meditag:js src="/js/common_paging.js"/>
</mhtml:head>

<mhtml:body>
	<mhtml:title title="${MENUNAME}" location="${MENUPATH}"/>
	<div class="dvcontents">
		<form name="Search" id="Search"action="javascript:listMessageUser(1)">
		<table style="width:100%;height:25px;" align="center" class="table_none">
			<tr>
				<td style="height:25px">발송일시 :
					 <input type="text" name="startDate" style="width:70px" class="txt" readonly="readonly"/>
					 <img src="<c:url value="/img/framework/icon/icon_calendar.gif" />" onClick="Calendar('달력', 'Search', 'startDate', '.', 'position=right,datetype=00,clear=yes');">
					 ~
					<input type="text" name="endDate" style="width:70px" class="txt" readonly="readonly"/>
					<img src="<c:url value="/img/framework/icon/icon_calendar.gif" />" onClick="Calendar('달력', 'Search', 'endDate', '.', 'position=right,datetype=00,clear=yes');">
					<input type="image" src="<c:url value="/img/framework/icon/icon_function_search.gif"/>" alt="검색" title="검색">
				</td>
			</tr>
		</table>
		<table class="table_list" summary="메시지 발송리스트" style="width: 100%">
			<colgroup>
				<col style="width:60px"/>
				<col style="width:80px"/>
				<col style="width:auto"/>
				<col style="width:100px"/>
				<col style="width:120px"/>
				<col style="width:100px"/>
				<col style="width:80px"/>
				<col style="width:120px"/>
			</colgroup>
			<thead>
			<tr>
				<th scope='col'>번호</th>
				<th scope='col'>발신구분</th>
				<th scope='col'>제목</th>
				<th scope='col'>발신상태<br>[완료/전체]</th>
				<th scope='col'>발신 메뉴</th>
				<th scope='col'>발신자명</th>
				<th scope='col'>등록자</th>
				<th scope='col'>등록일시</th>
			</tr>
			</thead>
			<tbody id="tbodyConfigCategory"></tbody>
		</table>
		</form>
		<div id="paging_area" style="height:50px;text-align:center;padding-top:10px"></div>
	</div>
<script type="text/javascript">
	// 팝업박스
	/** 페이지 초기화 */
	$(document).ready(function(){
		listMessageUser(1);
	});

	/**
	 * 시스템설정 카테고리 목록 조회
	 */
	function listMessageUser(curPage) {
		var f = document.Search;
		var startDate = f.startDate.value;
		var endDate = f.endDate.value;

		var chgStartDate = chgDate(startDate);
		var chgEndDate = chgDate(endDate);
		if(!dateCheck(chgStartDate, chgEndDate)) {
			alert("검색 종료일을 검색 시작일 보다 늦은 날짜를 선택하셔야 합니다.");
			return;
		}

		$.getJSON( cUrl("/LogMessageAdmin.do"),	// url
				{	"cmd"					: "listMessageUser",
					"messageDTO.startDttm"	: startDate,
					"messageDTO.endDttm"	: endDate,
					"curPage"				: curPage
				},			// params
				function (result) {
					var list = result.returnList;
					var listStr = "";
					var pageInfo = result.pageInfo;

					if(list.length == 0) {
						listStr +="	<tr><td colspan='8' align='center' class='b_td02'><font color=blud>* 등록된 이메일발송 로그가 없습니다.</font></td></tr>\n";
					}
					for (var i=0; i<list.length; i++) {
						var item = list[i];
						listStr +="	<tr>\n";
						listStr +="		<td align='center' class='b_td02'>"+item.msgSn+"</td>\n";
						listStr +="		<td align='center' class='b_td02'>"+item.msgDivCd+"</td>\n";

						if(item.msgDivCd =='EMAIL'){
							listStr +="		<td class='left'><a href=\""+cUrl("/LogMessageAdmin.do")+"?cmd=viewMessageUser&amp;messageDTO.msgSn="+item.msgSn+"&amp;messageDTO.msgDivCd="+item.msgDivCd+"\">"+item.title+"</a></td>\n";
						}else if(item.msgDivCd =='SMS'){
							listStr +="		<td class='left'><a href=\""+cUrl("/LogMessageAdmin.do")+"?cmd=viewMessageUser&amp;messageDTO.msgSn="+item.msgSn+"&amp;messageDTO.msgDivCd="+item.msgDivCd+"\">SMS확인</a></td>\n";
						}

						if(item.transFailCount > 0){
							listStr +="		<td align='center'>[<span class='accent'>"+item.transSuccessCount+"</span>(<span class='accent'>"+item.transDenialCount+"</span>)/<span class='accent'>"+item.transCount+"</span>]</td>\n";
						}else{
							listStr +="		<td align='center' class='b_td02'>[<span class='wblue'>"+item.transSuccessCount+"</span>(<span class='wgray'>"+item.transDenialCount+"</span>)/"+item.transCount+"]</td>\n";
						}

						listStr +="		<td align='center' class='b_td02'>"+item.sendMenuCdNm+"</td>\n";
						listStr +="		<td align='center' class='b_td02'>"+item.sendNm+"</td>\n";
						listStr +="		<td align='center' class='b_td02'>"+item.regNm+"</td>\n";
						listStr +="		<td align='center' class='b_td02'>"+addDateTimeFormatStr(item.regDttm)+"</td>\n";
						listStr +="	</tr>\n";
					}
					$("#tbodyConfigCategory").html(listStr);
					$("#paging_area").pagingHtml(pageInfo, "listMessageUser");
				}	// callback function
			);
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
</script>
</mhtml:body>
</mhtml:html>

