<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<mhtml:html>
<mhtml:head titleName="${MENUNAME}">
	<mhtml:head_module uploadify="Y"/>
</mhtml:head>

<mhtml:frm_body cssTag="background-color:#ffffff;">

<form name="FileUpload" id="fileUpload">
<table class="table_dtl" width="96%" align="center">
	<tr height="35">
		<td colspan="2"> * 엑셀 파일로 교육생을 일괄 등록 합니다.</td>
	</tr>
	<tr height="35">
		<td colspan="2"> * 샘플파일을 다운받아 작성해서 업로드 해 주세요..</td>
	</tr>
	<tr height="35">
		<th class="top">샘플파일</th>
		<td class="top">
			<a href="/sample/student.xls"><img src="/img/framework/icon/filetype/xls.gif" align="absmiddle"/>엑셀파일다운로드</a>
		</td>
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
	<meditag:button value="닫기" title=" 등록창을 닫습니다." func="self.close();" />
</meditag:buttonwrapper>
</form>

<form id="studentForm" name="studentForm" onsubmit="return false" action="/StudentStudentAdmin.do">
	<input type="hidden" name="crsCreCd" id="crsCreCd" value="${vo.crsCreCd }" />
	<input type="hidden" name="fileName" id="fileName"/>
	<input type="hidden" name="filePath" id="filePath"/>	
</form>

<script type="text/javascript">

	var ItemDTO = new Object();

	$(document).ready(function() {

		ItemDTO.crsCreCd = '${vos.crsCreCd}';

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
			'scriptData'	: { 'fileDir': '/Student/exupload', //
								'type'		: 'excel'},		// 추가 파라매터
			'onComplete'	: // 파일 전송 완료후 콜백
				function(event, queueID, fileObj, response, data) {
					var file = jQuery.parseJSON(response);
					excelProcess(file.fileSaveNm, file.filePath);
				}
		});

	});

	function excelProcess(fileName, filePath) {
		if(isEmpty(fileName) || isEmpty(filePath)) {
			alert("파일 업로드에 문제가 있어 더이상 처리를 할 수 없습니다.");
			return;
		}
		$("#cmd").val("addCheckStudentExcel");
		$("#fileName").val(fileName);
		$("#filePath").val(filePath);
		$("#studentForm").submit();
	}
</script>
</mhtml:frm_body>
</mhtml:html>