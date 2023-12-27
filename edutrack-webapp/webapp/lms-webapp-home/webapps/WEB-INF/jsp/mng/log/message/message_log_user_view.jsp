<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<mhtml:html>
<mhtml:head titleName="${MENUNAME}">
	<meditag:js src="/js/selectbox.js"/>
	<meditag:js src="/js/contextmenu.js"/>
	<meditag:js src="/js/popupbox.js"/>
	<meditag:js src="/js/common_paging.js"/>
</mhtml:head>

<mhtml:body>
	<mhtml:title title="${MENUNAME}" location="${MENUPATH}"/>
	<div style="width:100%;height:25px;float:left">
		<div style="float:right">
        	<meditag:button value="메시지목록" title="메시지 목록 화면으로 이동합니다." href="LogMessageAdmin.do?cmd=messageMainUser" />
        </div>
    </div>
    <table summary="Email 정보" class="table_dtl" style="width:100%" align="center">
		<colgroup>
			<col width="8%">
			<col width="17%">
			<col width="8%">
			<col width="17%">
			<col width="8%">
			<col width="17%">
			<col width="8%">
			<col width="17%">
		</colgroup>
		<tr height="35">
			<th class="top" scope="row">발신자 </th>
			<td class="top">
				${messageDTO.sendNm}
			</td>
			<th class="top" scope="row">Email/<br/>연락처</th>
			<td class="top">
				${messageDTO.sendAddr}
			</td>
			<th class="top" scope="row">등록자</th>
			<td class="top">
				<c:if test="${messageDTO.regNm eq 'UnKnown'}">
					다문화전문인력 온라인교육
				</c:if>
				<c:if test="${messageDTO.regNm ne 'UnKnown'}">
					${messageDTO.regNm}
				</c:if>
			</td>
			<th class="top" scope="row">등록일시</th>
			<td class="top">
				<meditag:dateformat type="0" property="${messageDTO.regDttm}" delimeter="."/>
			</td>
		</tr>
		<c:if test="${messageDTO.msgDivCd eq 'EMAIL'}">
		<tr height="35">
			<th scope="row" width="10%">제목</th>
			<td align="left" colspan="7">
				${messageDTO.title}
			</td>
		</tr>
		</c:if>
		<c:if test="${not empty messageDTO.attachFiles}">
		<tr height="35">
			<th scope="row" width="10%">첨부파일</th>
			<td align="left" colspan="7">
				<c:forEach var="fileItem" items="${messageDTO.attachFiles}" varStatus="status">
					<div style="margin-left: 5px; float: left">${fileItem.downloadTag}</div>
				</c:forEach>
			</td>
		</tr>
		</c:if>
		<tr height="33">
			<th scope="row" style="width:10%">발송 내용</th>
			<td align="left" colspan="7">
				${messageDTO.cts}
			</td>
		</tr>
	</table>
	<br/>
	<form name="Search" id="Search"action="javascript:listMessageTrans(1)">
		<table style="width:100%;height:25px;" align="center" class="table_none">
			<tr>
				<td style="height:25px">
					<div style="float:left;width:100%;padding:5px 0px 5px 0px">
						<div style="float:left;padding-right:5px"><b>발송상태 :</b></div>
						<div style="float:left;padding-right:5px">
							<meditag:selectbox width="100" height="100" fieldName="transSts" formName="Search" onChange="listMessageTrans(1);"/>
							<meditag:selectboxOption formName="Search" fieldName="transSts" value="" text="전체"/>
							<c:forEach items="${transStsList}" var="list">
								<meditag:selectboxOption formName="Search" fieldName="transSts" value="${list.codeCd}" text="${list.codeNm}"/>
							</c:forEach>
						</div>
						<div style="float:left;padding-right:5px">
						전체 : ${messageDTO.transCount}건, 성공: ${messageDTO.transSuccessCount}건, 대기: ${messageDTO.transStandbyCount}건, 거부: ${messageDTO.transDenialCount}건, 실패 :${messageDTO.transFailCount}건
						</div>
					</div>
				</td>
			</tr>
		</table>
	</form>
	<table style="text-align: center; width: 100%" align="center">
		<tr>
			<td width="100%" valign="top">
				<table class="table_list" summary="email 발송리스트" style="width: 100%">
					<colgroup>
						<col style="width:60px;"/>
						<col style="width:150px;"/>
						<col style="width:150px;"/>
						<col style="width:150px;"/>
						<col style="width:150px;"/>
						<col style="width:auto;"/>
					</colgroup>
					<thead>
					<tr>
						<th scope='col' >번호</th>
						<th scope='col' >수신자 이름</th>
						<th scope='col' >수신자 Email</th>
						<th scope='col' >발송상태</th>
						<th scope='col' >발송일자</th>
						<th scope='col' >
							<c:choose>
								<c:when test="${messageDTO.msgDivCd eq 'EMAIL'}">
									에러코드
								</c:when>
								<c:otherwise>
									전송결과
								</c:otherwise>
							</c:choose>
						</th>
					</tr>
					</thead>
					<tbody id="tbodyMessageTrans"></tbody>
				</table>
				<div id="paging_area" style="height:50;text-align:center;padding-top:10px"></div>
			</td>
		</tr>
	</table>
<script type="text/javascript">
/** 페이지 초기화 */
$(document).ready(function(){
	listMessageTrans(1);
});

/**
 * SMS 전송 목록 조회
 */
function listMessageTrans(curPage) {
	var f = document.Search;
	var transSts = f["transSts"].value;

	$.getJSON( cUrl("/LogMessageAdmin.do"),	// url
			{	"cmd"					: "listMessageTrans",
				"messageTransDTO.msgSn"	: '${messageDTO.msgSn}',
				"messageTransDTO.transSts" : transSts,
				"curPage"				: curPage
			},			// params
			function (result) {
				var list = result.returnList;
				var listStr = "";
				var pageInfo = result.pageInfo;

				if(list.length == 0) {
					listStr +="	<tr><td colspan='6' align='center' class='b_td02'><font color=blud>* 등록된 ${messageDTO.msgDivCd} 발송 로그가 없습니다.</font></td></tr>\n";
				}
				for (var i=0; i<list.length; i++) {
					var item = list[i];

					listStr +="	<tr>\n";
					listStr +="		<td align='center' class='b_td02'>"+item.msgTransSn+"</td>\n";
					listStr +="		<td align='left'>"+item.recvNm+"</td>\n";
					listStr +="		<td align='center' class='b_td02'>"+item.recvAddr+"</td>\n";
					listStr +="		<td align='center' class='b_td02'>"+item.transStsNm+"("+item.transSts+")</td>\n";
					listStr +="		<td align='center' class='b_td02'>"+addDateTimeFormatStr(item.transDttm)+"</td>\n";
					listStr +="		<td align='center' class='b_td02'>"+item.errorMsg+"</td>\n";
					listStr +="	</tr>\n";
				}
				$("#tbodyMessageTrans").html(listStr);
				$("#paging_area").pagingHtml(pageInfo, "listMessageTrans");
			}	// callback function
		);
}
</script>
</mhtml:body>
</mhtml:html>
