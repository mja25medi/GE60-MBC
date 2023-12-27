<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<mhtml:html>
<mhtml:head titleName="${MENUNAME}">
	<mhtml:head_module paging="Y"/>
	<meditag:js src="/js/selectbox.js"/>
	<meditag:js src="/js/contextmenu.js"/>
	<meditag:js src="/js/popupbox.js"/>
	<meditag:js src="/js/calendar.js"/>
</mhtml:head>

<mhtml:body>
	<mhtml:title title="${MENUNAME}" location="${MENUPATH}"/>
	<div class="dvcontents">
		<form name="Search" id="Search" onsubmit="return false;">
		<table style="width:100%" align="center" class="table_none">
			<TR>
				<td style="height:25px">발송일시 :
					 <input type="text" name="startDate" id="startDate" style="width:70px" class="txt" value="${curDate}"/>
					 <img src="<c:url value="/img/framework/icon/icon_calendar.gif" />" onClick="Calendar('달력', 'Search', 'startDate', '.', 'position=right,datetype=00,clear=yes');">
					 ~
					<input type="text" name="endDate" id="endDate" style="width:70px" class="txt" value="${curDate}"/>
					<img src="<c:url value="/img/framework/icon/icon_calendar.gif" />" onClick="Calendar('달력', 'Search', 'endDate', '.', 'position=right,datetype=00,clear=yes');">
					<input type="image" src="<c:url value="/img/framework/icon/icon_function_search.gif"/>" alt="검색" title="검색" onclick="listPageing(1);">
				</td>
			</TR>
		</table>
		<div id="messageList">
		<table class="table_list" summary="메시지 발송리스트" style="width: 100%">
			<colgroup>
				<col style="width:60px;"/>
				<col style="width:auto;"/>
				<col style="width:100px;"/>
				<col style="width:120px;"/>
				<col style="width:80px;"/>
				<col style="width:80px;"/>
				<col style="width:120px;"/>
			</colgroup>
			<thead>
				<tr>
					<th scope='col'>번호</th>
					<th scope='col'>제목</th>
					<th scope='col'>발신상태<br>[완료/전체]</th>
					<th scope='col'>발신 메뉴</th>
					<th scope='col'>발신자명</th>
					<th scope='col'>등록자</th>
					<th scope='col'>등록일시</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td colspan="7">* 등록된 메시지 로그가 없습니다.</td>
				</tr>
			</tbody>
		</table>
		</div>
		</form>
	</div>
<script type="text/javascript">
	// 팝업박스
	/** 페이지 초기화 */
	$(document).ready(function(){
		listPageing(1);
	});

	/**
	 * 시스템설정 카테고리 목록 조회
	 */
	function listPageing(curPage) {
		var startDate = $("#startDate").val();
		var endDate = $("#endDate").val();

		if(!dateCheck(startDate.replace(/\./g,""), endDate.replace(/\./g,""))) {
			return;
		}

		$("#messageList")
			.load(
				cUrl("/MessageLogAdmin.do"),
				{ "cmd" : "listMessage",
				  "messageDTO.msgDivCd"	: "EMAIL",
				  "messageDTO.startDttm" : startDate,
				  "messageDTO.endDttm" : endDate,
				  "curPage" : curPage
				}
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

