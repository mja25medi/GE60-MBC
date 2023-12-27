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
	<div style="width:100%;float:left;height:25px">
		<div style="float:right">
        	<meditag:button value="SMS 목록" title="SMS 목록 화면으로 이동합니다." href="/MessageLogAdmin.do?cmd=messageMain&amp;messageDTO.msgDivCd=SMS&amp;curPage=${messageForm.curPage}" />
        </div>
    </div>
    <table summary="SMS 정보" class="table_dtl" style="width:100%" align="center">
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
			<th class="top" scope="row">발신자</th>
			<td class="top">
				${messageDTO.sendNm}
			</td>
			<th class="top" scope="row">발신자 전화번호</th>
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
				<meditag:dateformat type="0" property="${messageDTO.regDttm}" />
			</td>
		</tr>
		<tr height="33">
			<th scope="row" style="width:10%">발송 내용</th>
			<td align="left" colspan="5">
				${messageDTO.cts}
			</td>
			<th scope="row" width="10%">예약발신일시</th>
			<td align="left">
				<c:choose>
					<c:when test="${empty messageDTO.rsrvSendDttm || messageDTO.rsrvSendDttm eq '00000000000000' }">즉시</c:when>
					<c:otherwise><meditag:dateformat type="0" property="${messageDTO.rsrvSendDttm}"/></c:otherwise>
				</c:choose>
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
						<!-- 실패자가 존재하고 재전송을 한번도 안한 경우 -->
						<c:if test="${messageDTO.sendSts ne 'RESEND' && messageDTO.transFailCount > 0 }">
						<div style="float:left;padding-right:5px">
							<meditag:button value="실패대상자 재전송" title="실패대상자 재전송" func="process('addResendMessage')" />
						</div>
						</c:if>
					</div>
				</td>
			</tr>
		</table>
	</form>
	<table style="text-align: center; width: 100%" align="center">
		<tr>
			<td width="100%" valign="top">
				<table class="table_list" summary="SMS 발송리스트" style="width: 100%">
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
						<th scope='col' >수신자 전화번호</th>
						<th scope='col' >발송상태</th>
						<th scope='col' >발송일자</th>
						<th scope='col' >전송결과</th>
					</tr>
					</thead>
					<tbody id="tbodyMessageTrans"></tbody>
				</table>
				<div id="paging_area" style="height:50;text-align:center;padding-top:10px"></div>
			</td>
		</tr>
	</table>
	<nested:form action="/MessageLogAdmin.do" onsubmit="return false" styleId="messgeForm">
	<input type="hidden" name="cmd" />
	<nested:hidden property="messageDTO.msgSn" value="${messageDTO.msgSn}" />
	<nested:hidden property="messageDTO.prevMsgSn" value="${messageDTO.msgSn}" />
	</nested:form>
<script type="text/javascript"><!--

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

		$.getJSON( cUrl("/MessageLogAdmin.do"),	// url
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
						listStr +="	<tr><td colspan='6' align='center' class='b_td02'><font color=blud>* 등록된 SMS 발송 로그가 없습니다.</font></td></tr>\n";
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

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		$('#messgeForm').find('input[name=cmd]').val(cmd);
		$('#messgeForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		alert(resultDTO.returnMessage);
		if(resultDTO.returnResult >= 0) {
			// 정상 처리
			document.location.href = cUrl("/LogMessageAdmin.do?cmd=viewMessage${AMPERSAND}messageDTO.msgSn="+resultDTO.returnDto.msgSn);
		} else {
			// 비정상 처리
		}
	}
--></script>
</mhtml:body>
</mhtml:html>