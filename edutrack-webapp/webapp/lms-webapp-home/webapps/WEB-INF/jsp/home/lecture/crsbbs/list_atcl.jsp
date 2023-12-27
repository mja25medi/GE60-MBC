<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="img_base" value="/img/home" />
<c:set var="crsBbsAtclList" value="${crsBbsAtclList}"/>
<c:set var="pageInfo" value="${pageInfo}"/>
<c:set var="crsBbsInfoVO" value="${crsBbsInfoVO}"/>
<c:set var="crsBbsAtclVO" value="${vo}"/>
<mhtml:class_html>
<mhtml:class_head>
	<mhtml:head_module paging="Y"/>
</mhtml:class_head>
<mhtml:class_body>
	<div id="content">
    	<mhtml:class_location />

		<form id="crsBbsAtclForm" name="crsBbsAtclForm" onsubmit="return false" >
		<input type="hidden" name="bbsCd" value="${vo.bbsCd}"/>
		<input type="hidden" name="atclSn" value="${vo.atclSn}"/>
		<input type="hidden" name="curPage" id="curPage" value="${vo.curPage}"/>
		<fieldset class="notice_search" style="padding:0px 0px 10px 0px;text-align:left">
			<legend>검색</legend>
			<select name="searchKey" style="width: 70px;" id="searchKey" class="_enterBind" title="검색 분류 선택">
				<html:option value="title">제 목</html:option>
				<html:option value="regNm">성 명</html:option>
			</select>
			<input type="text" name="searchValue" value="${vo.searchValue}" style="width:160px" id="searchValue" class="_enterBind text" maxlength="20" title="검색어입력"/>
			<button type="button" class="btn_search"><span class="hide">검색</span></button>
		</fieldset>
		</form>
		<c:if test="${CLASSUSERTYPE eq 'TCH'}">
		<a href="<c:url value="/lec/crsBbs/addForm?bbsCd=${crsBbsAtclVO.bbsCd}&amp;searchKey=${crsBbsAtclVO.searchKey}&amp;searchValue=${crsBbsAtclVO.searchValue}&amp;curPage=${crsBbsAtclForm.curPage}"/>" class="btn_write" style="float:right;margin:-32px 10px 0px 0px"><img src="${img_base}/common/btn/btn_write.gif" alt="글쓰기" /></a>
		</c:if>
		<table class="board_a">
			<caption>${crsBbsInfoVO.bbsNm}</caption>
			<colgroup>
				<col style="width:10%;" />
				<col style="width:auto;" />
				<col style="width:10%;" />
				<col style="width:10%;" />
				<col style="width:13%;" />
				<col style="width:9%;" />
			</colgroup>
			<thead>
				<tr>
					<th scope="col"><spring:message code="common.title.no"/></th>
					<th scope="col"><spring:message code="common.title.title"/></th>
					<th scope="col"><spring:message code="common.title.attachfile"/></th>
					<th scope="col">작성자</th>
					<th scope="col">등록일</th>
					<th scope="col">조회</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${crsBbsAtclList}" varStatus="status">
				<tr>
					<td>${pageInfo.totalRecordCount - ((pageInfo.currentPageNo - 1 ) * pageInfo.recordCountPerPage + status.index)}</td>
					<td class="subject"><a href="<c:url value="/lec/crsBbs/read?bbsCd=${crsBbsAtclVO.bbsCd}&amp;searchKey=${crsBbsAtclVO.searchKey}&amp;searchValue=${crsBbsAtclVO.searchValue}&amp;curPage=${crsBbsAtclForm.curPage}&amp;crsBbsAtclVO.atclSn=${item.atclSn}"/>">${item.title} <c:if test="${item.cmntCnt > 0}">(${item.cmntCnt})</c:if></a></td>
					<td>
						<c:forEach var="fileItem" items="${item.attachFiles}" varStatus="fiSts">
							<a href="<c:url value="/app/file/download/${fileItem.fileSn}"/>" onclick="javascript:fileDown('${fileItem.fileSn}');" title="Download: ${fileItem.fileNm}"><img src="<c:url value="/img/framework/icon/icon_file.gif"/>" style="border:0"/></a>
						</c:forEach>
					</td>
					<td>${item.regNm}</td>
					<td><meditag:dateformat type="1" delimeter="." property="${item.regDttm}"/></td>
					<td>${item.hits}</td>
				</tr>
				</c:forEach>
				<c:if test="${empty crsBbsAtclList}">
				<tr>
					<td colspan="6">* 등록된 게시물이 없습니다.</td>
				</tr>
				</c:if>
			</tbody>
		</table>
		<meditag:paging pageInfo="${pageInfo}" funcName="listPageing"/>
	</div>

<script type="text/javascript">
	$(document).ready(function(){
		$("._enterBind").bind("keydown", function(event){
			if($M.Check.Event.isEnter(event)) {
				listPageing(1);
			}
		});

		$(".btn_search").bind("click", function(event) {
			listPageing(1);
		});
	});

	function listPageing(page) {
		$('#crsBbsAtclForm')
			.find('input[name=curPage]').val(page).end()
			.attr("action","/lec/crsBbs/main")
			.submit();
	}
</script>
</mhtml:class_body>
</mhtml:class_html>