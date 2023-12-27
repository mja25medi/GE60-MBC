<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="prjAssignmentVO" value="${prjAssignmentVO}"/>
<c:set var="prjAssignmentSendVO" value="${vo}"/>
<c:set var="prjTeamVO" value="${prjTeamVO}"/>
<c:set var="teamList" value="${teamList}"/>
<mhtml:class_html>
<mhtml:class_head>
	<meditag:css href="css/simpletab.css"/>
	<meditag:js src="/js/simpletab.js"/>
	<meditag:js src="/js/calendar.js"/>
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
	<!-- 과제제출  -->
	<br/>
	<form id="prjAssignmentForm" name="prjAssignmentForm" onsubmit="return false" ">
		<input type="hidden" name="crsCreCd" value="${vo.crsCreCd}" />
	 	<input type="hidden" name="prjtSn" value="${vo.prjtSn}" />
	 	<input type="hidden" name="prjtTeamSn" value="${vo.prjtTeamSn}" />
	 	<input type="hidden" name="asmtSn" value="${prjAssignmentVO.asmtSn}"/>
	 	<input type="hidden" name="attachFileSns" value="${vo.attachFileSns}" />
		<h3 class="ctit">과제 제출</h3>
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
				<th scope="row">과제 제출 내용</th>
				<td colspan="3">
					<textarea style="width:500px;height:120px" dispName="과제 제출 내용" lenCheck="1000" isNull="N" name="sendCts"/>
				</td>
			</tr>
			<tr>
				<th scope="row"> 첨부파일</th>
				<td colspan="3">
					<input type="file" name="uploadify" id="uploadify" title="첨부파일"/>
					<div id="fileQueue"></div>
					<div id="fileListview"></div>
				</td>
			</tr>
		</table>
		<meditag:buttonwrapper>
			<c:if  test="${gubun eq 'A'}">
				<div class="btn_right">
					<a href="javascript:addAssi()" class="btn02">저장</a>
				</div>
			</c:if>
			<c:if  test="${gubun eq 'E'}">
				<div class="btn_right">
					<a href="javascript:editAssi()" class="btn02">수정</a>
					<a href="javascript:listRead()" class="btn02">취소</a>
				</div>
			</c:if>
		</meditag:buttonwrapper>
	</form>

<script type="text/javascript">
	var atchFiles; // 첨부파일 목록
	callResize();
	$(document).ready(function() {

		// 파일 첨부 업로더 및 바인딩 및 초기화 @see /js/common_function.js 참조.
		atchFiles = new $M.AttachFiles(
				{	"varName"			: "atchFiles",		// 변수명과 동일한 명칭 설정
					"files"				: $.parseJSON('${prjAssignmentSendVO.attachFilesJson}'),
					"uploadifySetting"	: {
							"queueID"		: "fileQueue",
							"scriptData"	:	{
									"repository"	:	"PRJT_ASMT_SEND",
									"type"			:	"file"		}
		}});


	});


	/* 과제 제출 등록 */
	function addAssi() {
		$(':input:hidden[name=attachFileSns]').val(atchFiles.getFileSnAll());
		$('#uploadify').attr("disabled", true); //-- 파일 객체 disable

		$('#prjAssignmentForm').attr("action","/lec/prj/assignment/addSend");
		$('#prjAssignmentForm').submit();
	}

	/* 과제 제출 수정 */
	function editAssi() {
		$(':input:hidden[name=attachFileSns]').val(atchFiles.getFileSnAll());
		$('#uploadify').attr("disabled", true); //-- 파일 객체 disable

		$('#prjAssignmentForm').attr("action","/lec/prj/assignment/editSend");
		$('#prjAssignmentForm').submit();
	}

	/* 읽기 화면 으로 이동 */
	function listRead() {
		$('#prjAssignmentForm')
			.attr("action","/lec/prj/assignment/addFormSend")
			.submit();
	}

	/* 과제 제출 삭제 */
	function delAssi() {
		if(confirm('과제를 삭제하려고 합니다.\n\n삭제 하시겠습니까?')) {
			$('#prjAssignmentForm')
				.attr("action","/lec/prj/assignment/removePrjAssignment")
				.submit();
		} else {
			return;
		}
	}


	function callResize() {
        var height = document.body.scrollHeight+20;
        parent.resizeTopIframe(height);
	}

</script>
</mhtml:body>
</mhtml:class_html>