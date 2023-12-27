<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>

	<table summary="" class="table table-bordered">
		<colgroup>
			<col width="20%">
			<col width="30%">
			<col width="20%">
			<col width="30%">
		</colgroup>
		<tr>
			<th scope="row">File No</th>
			<td colspan="3">${vo.fileSn}</td>
		</tr>
		<tr>
			<th scope="row">File MIME Type</th>
			<td colspan="3">${vo.mimeType}</td>
		</tr>
		<tr>
			<th scope="row">File Name</th>
			<td colspan="3">${vo.downloadTag}</td>
		</tr>
		<tr>
			<th scope="row">File Size</th>
			<td>${vo.fileSize}</td>
			<th scope="row">File Type</th>
			<td>${vo.fileType}</td>
		</tr>
		<tr>
			<th scope="row">Hits</th>
			<td>${vo.hits}</td>
			<th scope="row">View Date</th>
			<td><meditag:dateformat type="0" property="${vo.lastInqDttm}"/></td>
		</tr>
		<tr>
			<th scope="row">Repository</th>
			<td>${vo.repoNm} (${vo.repoCd})</td>
			<th scope="row">Related Table</th>
			<td>${vo.parTableNm}</td>
		</tr>
		<tr>
			<th scope="row">Save File Path</th>
			<td colspan="3">${vo.saveFilePath}</td>
		</tr>
		<c:if test="${vo.image}"><%--이미지일경우--%>
			<tr>
				<th scope="row">Thumb URL</th>
				<td colspan="3">${vo.thumbUrl}</td>
			</tr>
			<tr>
				<th scope="row">View URL</th>
				<td colspan="3">${vo.viewUrl}</td>
			</tr>
			<tr>
				<th scope="row">Preview</th>
				<td colspan="3"><img alt="${vo.fileNm} 미리보기" src="${vo.thumbUrl}"></td>
			</tr>
		</c:if>
		<c:if test="${vo.media}"><%--미디어일경우--%>
			<tr>
				<th scope="row">Player Embed Tag</th>
				<td colspan="3">
					<span class="dblue">&lt;embed src="${vo.viewUrl}" &gt;</span><br>
				</td>
			</tr>
			<tr>
				<th scope="row">Embed Preview</th>
				<td colspan="3"><embed src="${vo.viewUrl}" ></td>
			</tr>
		</c:if>
	</table>
	<div class="text-right" style="margin-top:10px">
		<button class="btn btn-warning btn-sm" onclick="deleteFile()" ><spring:message code="button.delete"/></button>
		<button class="btn btn-default btn-sm" onclick="parent.modalBoxClose()"><spring:message code="button.close"/></button>
	</div>

<script type="text/javascript">
	function deleteFile() {
		parent.deleteFile('${vo.fileSn}');
		parent.popupBox.close();
	}
</script>
