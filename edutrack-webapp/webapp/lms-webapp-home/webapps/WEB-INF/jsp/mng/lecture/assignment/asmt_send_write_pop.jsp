<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
	<br>
	<form id="assignmentForm" name="assignmentForm" onsubmit="return false" action="/LectureAssignmentAdmin.do">
	<input type="hidden" name="crsCreCd" value="${vo.crsCreCd }" />
	<input type="hidden" name="asmtSn" value="${vo.asmtSn }" />
	<input type="hidden" name="sendCnt" value="${vo.sendCnt }" />
	<input type="hidden" name="stdNo" value="${stdNo}"/>
	<input type="hidden" name="attachFileSns" value="${vo.attachFileSns }" />
	<table summary="과제 정보 폼" class="table_dtl" style="width:96%" align="center">
		<tr height="35">
			<th class="top" scope="row" style="width:20%">과제 제목</th>
			<td class="top" colspan="3">
				${vo.asmtTitle}
			</td>
		</tr>
		<tr height="35">
			<th scope="row" style="width:20%">제출 기간</th>
			<td style="width:30%">
				${vo.asmtStartDttm} ~ ${vo.asmtEndDttm}
			</td>
			<th scope="row" style="width:20%">제출 제한 횟수</th>
			<td style="width:30%">
				 ${vo.asmtLimitCnt} 회
			</td>
		</tr>
		<tr height="35">
			<th scope="row" style="width:20%">과제 내용</th>
			<td colspan="3">
				${vo.asmtCts}
			</td>
		</tr>
		<tr height="35">
			<th scope="row" style="width:20%">문제 제목</th>
			<td colspan="3">
				<meditag:selectbox width="300" height="120" fieldName="assignmentSendVO.asmtSubSn" formName="assignmentForm"/>
				<c:forEach items="${subList}" var="item">
				<meditag:selectboxOption value="${item.asmtSubSn}" text="${item.asmtTitle}" fieldName="assignmentSendVO.asmtSubSn" formName="assignmentForm" />
				</c:forEach>
			</td>
		</tr>
	</table>
	<br/>
	<table summary="과제 제출 폼" class="table_dtl" style="width:98%" align="center">
		<tr height="35">
			<th class="top" scope="row" style="width:20%">제출 제목</th>
			<td class="top" colspan="3">
				<input type="text" style="width:90%" dispName="과제 제목" maxlength="100" isNull="N" lenCheck="100" name="sendTitle" onfocus="this.select()" class="txt"/>
			</td>
		</tr>
		<tr height="35">
			<th scope="row" style="width:15%">제출파일</th>
			<td colspan="3">
				<table class="table_none" style="width:100%">
					<tr>
						<td style="width:50px">
							<input type="file" name="uploadify" id="uploadify"/><%-- 첨부파일 버튼 --%>
						</td>
						<td>
							<div id="fileQueue"></div>
							<div id="fileListview"></div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr height="35">
			<th scope="row" style="width:15%">제출 내용</th>
			<td colspan="3">
				<textarea name="sendCts" style="width:90%;height:80"></textarea>
			</td>
		</tr>
	</table>
	<meditag:buttonwrapper style="padding: 6px; width: 96%">
		<meditag:button value="저장" title="입력한 과제 문제 정보를 저장합니다." func="addSend()" />
		<meditag:button value="닫기" title="과정 개설 등록 창을 닫습니다." func="parent.asmtPopBox.close()" />
	</meditag:buttonwrapper>

	</form>

<script type="text/javascript">

	var atchFiles; // 첨부파일 목록

	$(document).ready(function() {
		// 파일 첨부 업로더 및 바인딩 및 초기화 @see /js/common_function.js 참조.
		atchFiles = new $M.AttachFiles(
				{	"varName"			: "atchFiles",		// 변수명과 동일한 명칭 설정
					"maxcount"			: 1,
					"uploadifySetting"	: {
							"queueID"		: "fileQueue",
							'multi'			: false,
							"scriptData"	:	{
									"repository"	:	"ASMT_SEND",
									"organization"	:	"${USER_ORGCD}",
									"type"			:	"file"		}
				}});
	});


	function addSend() {
		// 서버에서 파일에 대한 정보를 처리할 파라매터 항목을 추가.
		$(':input:hidden[name=attachFileSns]').val(atchFiles.getFileSnAll());

		process("addSend");
	}

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		if(!validate(document.assignmentForm)) return;
		$('#uploadify').attr('disabled',true);
		$('#assignmentForm').attr("action","/mng/lecture/assignment/" + cmd);
		$('#assignmentForm').ajaxSubmit(processCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		alert(resultDTO.message);
		if(resultDTO.result >= 0) {
			// 정상 처리
			parent.subWorkFrame.listStudent();
			parent.exmPopBox.close();
		} else {
			// 비정상 처리
		}
	}



</script>
