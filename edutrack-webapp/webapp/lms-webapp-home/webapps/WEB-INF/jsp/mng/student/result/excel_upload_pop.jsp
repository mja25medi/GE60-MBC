<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="eduResultVO" value="${vo}" />


	<form name="FileUpload">
	<table class="table_dtl" width="96%" summary="파일 업로드 폼">
		<tr height="35">
			<td> * 엑셀 파일로 성적을 업로드 합니다.</td>
		</tr>
		<tr>
			<td class="top" style="text-align;left;height:100px;padding-top:5px;padding-bottom:5px" valign=top>
				<input type="file" name="uploadify" id="uploadify" title="첨부파일"/><%-- 첨부파일 버튼 --%>
			</td>
		</tr>
	</table>
	<meditag:buttonwrapper style="padding: 6px; width: 96%">
		<meditag:button value="닫기" title="성적 등록창을 닫습니다." func="parent.stdPopBox.close();" />
	</meditag:buttonwrapper>
	<input type="submit" value="submit" style="display:none" />
	</form>

<script type="text/javascript">

	var ItemDTO = new Object();

	$(document).ready(function() {
		// 파일 업로더 초기화.
		$('#uploadify').uploadify({
			'uploader'		: cUrl('/libs/jquery.uploadify/uploadify.swf'),
			'cancelImg'		: cUrl('/libs/jquery.uploadify/cancel.png'),
			'buttonImg'		: cUrl('/libs/jquery.uploadify/btn_filesearch.png'),
			'width' : 60, 'height' : 26, 'rollover' : true, 'auto' : true,
			'multi'			: false,
			'script'		: cUrl('/app/excel/ajaxupload'),
			'scriptData'	: { 'fileDir': '/EduResult/${eduResultVO.crsCreCd}',	// 과정 코드
								'type'		: 'excel'},			// 추가 파라매터
			'onComplete'	: // 파일 전송 완료후 콜백
				function(event, queueID, fileObj, response, data) {
					var file = jQuery.parseJSON(response);
					excelProcess(file.fileSaveNm, file.filePath);
				}
		});

	});


	/**
	 * 사용자 목록 조회
	 *
	 */
	function excelProcess(fileName, filePath) {
		if(isEmpty(fileName) || isEmpty(filePath)) {
			alert("파일 업로드에 문제가 있어 더이상 처리를 할 수 없습니다.");
			return;
		}
		$.getJSON( cUrl("/StudentEduResultAdmin.do"),		// url
				{ "cmd" : "addExcelUpload",
				  "eduResultVO.crsCreCd" : '${eduResultVO.crsCreCd}',
				  "fileName" : fileName,
				  "filePath" : filePath
				},			// params
				processCallback				// callback function
			);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		alert(resultDTO.message);
		//-- 무조건 처리
		parent.subWorkFrame.listStudent(parent.subWorkFrame.ItemDTO.curPage);
		parent.stdPopBox.close();
	}
</script>
