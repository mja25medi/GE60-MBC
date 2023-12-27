<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="prjAssignmentVO" value="${prjAssignmentVO}"/>
<c:set var="prjAssignmentSendVO" value="${vo}"/>
<c:set var="prjTeamVO" value="${prjTeamVO}"/>
<mhtml:class_html>
<mhtml:class_head>
	<mhtml:head_module uploadify="y"/>
</mhtml:class_head>
<mhtml:body>
<h3 class="ctit">과제 정보</h3>
 	<table class="board_b">
		<colgroup>
			<col style="width:20%"/>
			<col style="width:30%"/>
			<col style="width:20%"/>
			<col style="width:30%"/>
		</colgroup>
		<tr>
			<th class="top" scope="row">과제명</th>
			<td class="top" colspan="3">${prjAssignmentVO.asmtTitle}</td>
		</tr>
		<tr>
			<th scope="row">제출기간</th>
			<td colspan="3">
					${prjAssignmentVO.asmtStartDate} ~ ${prjAssignmentVO.asmtEndDate}
			</td>
		</tr>
		<tr>
			<th scope="row">과제내용</th>
			<td colspan="3" colspan="3">
				 <div style="float:left;width:100%;min-height:130px;" class="tx-content-container">
				 	${prjAssignmentVO.asmtCts}
				 </div>
			</td>
		</tr>
		<tr>
			<th scope="row"> 첨부파일</th>
			<td colspan="3">
				<c:forEach var="fileItem" items="${prjAssignmentVO.attachFiles}" varStatus="status">
					<div style="margin-left: 5px; float: left">${fileItem.downloadTag}</div>
				</c:forEach>
			</td>
		</tr>
	</table>
<br/>
<h3 class="ctit">과제 제출 정보</h3>
	<table class="board_b">
		<colgroup>
			<col style="width:20%"/>
			<col style="width:30%"/>
			<col style="width:20%"/>
			<col style="width:30%"/>
		</colgroup>
		<tr>
			<th class="top" scope="row">제출팀</th>
			<td class="top" colspan="3">
				${prjTeamVO.prjtTeamNm}
			</td>
		</tr>
		<tr>
			<th scope="row">과제내용</th>
			<td colspan="3" colspan="3">
				 <div style="float:left;width:100%;min-height:130px;" class="tx-content-container">
				 	${prjAssignmentSendVO.sendCts}
				 </div>
			</td>
		</tr>
		<tr>
            <th scope="row">첨부파일</th>
            <td colspan="3">
				<c:forEach var="fileItem" items="${prjAssignmentSendVO.attachFiles}" varStatus="status">
					<div style="margin-left: 5px; float: left">${fileItem.downloadTag}</div>
				</c:forEach>
			</td>
       </tr>
	</table>
	<div class="btn_right">
		<a href="javascript:editAssi()" class="btn02">수정</a>
		<a href="javascript:delAssi()" class="btn02">삭제</a>
	</div>
<form id="prjAssignmentForm" name="prjAssignmentForm" onsubmit="return false" >
	<input type="hidden" name="crsCreCd" value="${vo.crsCreCd}" />
 	<input type="hidden" name="prjtSn" value="${vo.prjtSn}" />
 	<input type="hidden" name="prjtTeamSn" value="${vo.prjtTeamSn}" />
 	<input type="hidden" name="asmtSn" value="${vo.asmtSn}" />
</form>

<script type="text/javascript">

	var ItemDTO = new Object();

	$(document).ready(function() {
		ItemDTO.crsCreCd = '${prjAssignmentSendVO.crsCreCd}';
		ItemDTO.prjtSn = '${prjAssignmentSendVO.prjtSn}';
		ItemDTO.prjtTeamSn = '${prjAssignmentSendVO.prjtTeamSn}';
		ItemDTO.asmtSn = '${prjAssignmentSendVO.asmtSn}';

		//iframe 리사이즈
		callResize();

	});

	function editAssi(){
		document.location.href = cUrl("/lec/prj/assignment/editFormSend")+"?prjtSn="+ItemDTO.prjtSn+"${AMPERSAND}prjtTeamSn="+ItemDTO.prjtTeamSn+"${AMPERSAND}asmtSn="+ItemDTO.asmtSn;
	}

	function delAssi(){
		if(confirm('과제를 삭제하려고 합니다.\n\n삭제 하시겠습니까?')) {
			$('#prjAssignmentForm')
				.attr("action","/lec/prj/assignment/removeSend")
				.submit();
		} else {
			return;
		}
	}

	function callResize() {
        var height = document.body.scrollHeight;
        parent.resizeTopIframe(height);
	}

</script>
</mhtml:body>
</mhtml:class_html>