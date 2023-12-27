<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="assignmentVO" value="${vo}" />
<c:set var="assignmentSendVO" value="${vo.assignmentSendVO}" />

<div class="wrap" style="width:100%;">
	<h1>엑셀 업로드</h1>
	<div class="contents">
		<form name="FileUpload" id="fileUpload">
		<table class="board_b">
			<tr>
				<td colspan="2"> * 엑셀 파일로 과제 점수를 일괄 등록 합니다.</td>
			</tr>
			<tr>
				<td colspan="2"> * 샘플파일을 다운받아 작성해서 업로드 해 주세요..</td>
			</tr>
			<tr>
				<th >파일등록</th>
				<td  style="text-align;left;height:100;padding-top:5px;padding-bottom:5px">
					<input type="file" name="uploadify" id="uploadify"/><%-- 첨부파일 버튼 --%>
					<div id="information" class="bold accent" style="display: none;"></div>
				</td>
			</tr>
		</table>
		<div class="btn_right">
        	<a href="javascript:window.close()" class="btn02">닫기</a>
        </div>
		</form>
	</div>
</div>
<form id="assignmentForm" name="assignmentForm" onsubmit="return false">
<input type="hidden" name="crsCreCd" value="${assignmentVO.crsCreCd}"/>
<input type="hidden" name="asmtSn" value="${assignmentVO.asmtSn}"/>
<input type="hidden" name="fileName" id="fileName"/>
<input type="hidden" name="filePath" id="filePath"/>
</form>

<script type="text/javascript">

	$(document).ready(function() {

		// 파일 업로더 초기화.
		$('#uploadify').uploadify({
			'uploader'		: cUrl('/libs/jquery.uploadify/uploadify.swf'),
			'cancelImg'		: cUrl('/libs/jquery.uploadify/cancel.png'),
			'buttonImg'		: cUrl('/libs/jquery.uploadify/btn_filesearch.png'),
			'width' : 60, 'height' : 26, 'rollover' : true, 'auto' : true,
			'multi'			: false,
			'script'		: cUrl('/app/excel/ajaxupload'),
			"fileDesc"		: "excel 파일 (*.xls, *.xlsx)",
			"fileExt"		: "*.xls; *.xlsx",
			'scriptData'	: { 'fileDir': '/lecutre/assignment/exupload', //
								'type'		: 'excel'},		// 추가 파라매터
			'onComplete'	: // 파일 전송 완료후 콜백
				function(event, queueID, fileObj, response, data) {
					var file = jQuery.parseJSON(response);
					excelProcess(file.fileSaveNm, file.filePath);
				}
		});

	});

	/**
	* 업로드 폼
	*/
	function excelProcess(fileName, filePath) {
		if(isEmpty(fileName) || isEmpty(filePath)) {
			alert("파일 업로드에 문제가 있어 더이상 처리를 할 수 없습니다.");
			return;
		}

		$("#assignmentForm").attr("action","/lec/assignment/addExcelUpload");
		$("#fileName").val(fileName);
		$("#filePath").val(filePath);
		$("#assignmentForm").ajaxSubmit(excelProcessCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function excelProcessCallback(resultDTO) {
		alert(resultDTO.message);
		if(resultDTO.result >= 0) {
			// 정상 처리
			window.close();
			parent.opener.location.reload();
		} else {
			// 비정상 처리
		}
	}
</script>
