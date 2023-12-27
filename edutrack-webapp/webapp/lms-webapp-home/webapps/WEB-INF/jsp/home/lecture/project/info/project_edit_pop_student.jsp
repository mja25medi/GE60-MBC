<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="projectVO" value="${vo}"/>
<mhtml:class_html>
<mhtml:class_head>
	<mhtml:head_module uploadify="y"/>
</mhtml:class_head>
<mhtml:body>
	<form id="projectForm" name="projectForm" onsubmit="return false" action="/lec/project">
 	<input type="hidden" name="crsCreCd" value="${vo.crsCreCd}" />
 	<input type="hidden" name="prjtSn" value="${vo.prjtSn}" />
 	<input type="hidden" name="attachFileSns" value="${vo.attachFileSns}" />
		<table class="board_b">
			<colgroup>
				<col style="width:20%"/>
				<col style="width:30%"/>
				<col style="width:20%"/>
				<col style="width:30%"/>
			</colgroup>
			<tr>
				<th scope="row" class="top">프로젝트 명</th>
				<td class="top" colspan="3">${projectVO.prjtTitle}</td>
			</tr>
			<tr>
				<th scope="row">프로젝트기간</th>
				<td colspan="3">${projectVO.prjtStartDttm} ~ ${projectVO.prjtEndDttm}</td>
			</tr>
			<tr>
				<th scope="row">성적공개여부</th>
				<td colspan="3">${projectVO.scoreOpenYnNm}</td>
			</tr>
			<c:if test="${projectVO.scoreOpenYn eq 'Y'}" >
			<tr>
				<th scope="row">성적공개일자</th>
				<td colspan="3">${projectVO.scoreCfrmDttm}</td>
			</tr>
			</c:if>
			<tr height="70">
					<th scope="row">프로젝트 내용</th>
					<td colspan="3">
						<textarea style="width:500px;height:50px" dispName="게시판 설명" lenCheck="1000" isNull="N" name="prjtCts" value="${vo.prjtCts}" id="prjtCts" readonly="true"/>
					</td>
				</tr>
			<tr>
				<th scope="row">첨부파일</th>
				<td colspan="3">
					<c:forEach var="fileItem" items="${projectVO.attachFiles}" varStatus="status">
					<div style="margin-left: 5px; float: left">${fileItem.downloadTag}</div>
				</c:forEach>
				</td>
			</tr>
		</table>
	</form>

<script type="text/javascript">
	var atchFiles; // 첨부파일 목록
	var ItemDTO = new Object();

	$(document).ready(function() {
		ItemDTO.crsCreCd = '${projectVO.crsCreCd}';
		ItemDTO.prjtSn = '${projectVO.prjtSn}';

		atchFiles = new $M.AttachFiles(
				{	"varName"			: "atchFiles",		// 변수명과 동일한 명칭 설정
					"maxcount"			: 5,
					"files"				: $.parseJSON('${projectVO.attachFilesJson}'),
					"uploadifySetting"	: {
							"queueID"		: "fileQueue",
							"fileDesc"		:	"문서 파일 (*.hwp, *.doc, *.xls, *.docx, *.xlsx, *.zip, *.ppt, *.pptx)",
							"fileExt"		:	"*.hwp; *.doc; *.xls; *.docx; *.xlsx; *.zip; *.ppt; *.pptx;",
							"scriptData"	:	{
									"repository"	:	"PRJT",
									"type"			:	"file"		}
				}});

		callResize();
	});

	function callResize() {
        var height = document.body.offsetHeight;
        parent.resizeTopIframe(height);
	}

</script>
</mhtml:body>
</mhtml:class_html>