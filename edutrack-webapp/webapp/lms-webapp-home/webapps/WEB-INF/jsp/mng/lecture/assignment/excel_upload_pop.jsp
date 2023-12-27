<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="assignmentVO" value="${vo}" />

<form name="FileUpload" id="fileUpload">
<table class="table_dtl" width="96%" align="center">
	<tr height="35">
		<td colspan="2"> * 엑셀 파일로 과제 점수를 일괄 등록 합니다.</td>
	</tr>
	<tr height="35">
		<td colspan="2"> * 샘플파일을 다운받아 작성해서 업로드 해 주세요..</td>
	</tr>
	<tr height="33">
		<th >파일등록</th>
		<td  style="text-align;left;height:100;padding-top:5px;padding-bottom:5px">
			<input type="file" name="uploadify" id="uploadify"/><%-- 첨부파일 버튼 --%>
			<div id="information" class="bold accent" style="display: none;"></div>
		</td>
	</tr>
</table>
<meditag:buttonwrapper style="padding: 6px; width: 96%">
	<meditag:button value="닫기" title=" 등록창을 닫습니다." func="parent.asmtPopBox.close();" />
</meditag:buttonwrapper>
</form>
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

		$("#fileName").val(fileName);
		$("#filePath").val(filePath);
		$('#assignmentForm').attr("action","/mng/lecture/assignment/addExcelUpload");
		$("#assignmentForm").ajaxSubmit(excelProcessCallback);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function excelProcessCallback(resultDTO) {
		alert(resultDTO.message);
		if(resultDTO.result >= 0) {
			// 정상 처리
			parent.asmtPopBox.close();
			parent.subWorkFrame.listStudent(1);
		} else {
			// 비정상 처리
		}
	}
</script>
