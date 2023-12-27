<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="prjBbsAtclVO" value="${vo}"/>
<c:set var="prjBbsList" value="${prjBbsList}"/>
<c:set var="prjBbsAtclList" value="${prjBbsAtclList}"/>
<c:set var="pageInfo" value="${pageInfo}"/>
<mhtml:class_html>
<mhtml:class_head>
	<meditag:js src="/js/selectbox.js"/>
	<meditag:js src="/js/common_paging.js"/>
	<meditag:js src="/js/paginator3000.js"/>
	<meditag:css href="css/paginator3000.css"/>
</mhtml:class_head>
<mhtml:body>
	<form id="prjBbsAtclForm" name="prjBbsAtclForm" onsubmit="return false">
	<input type="hidden" name="curPage"/>
	<input type="hidden" name="crsCreCd" value="${vo.crsCreCd}" />
	<input type="hidden" name="prjtSn" value="${vo.prjtSn}" />
		<br/>
		<table>
			<tr>
				<td>
					<html:select name="bbsCd" id="bbsCd" onchange="listPrjBbsAtcl(1)" class="text">
						<html:option value="">게시판 선택</html:option>
						<c:forEach items="${prjBbsList}" var="item">
							<html:option value="${item.bbsCd}">${item.bbsNm}</html:option>
						</c:forEach>
					</html:select>
					<select name="searchKey" id="searchKey" class="_enterBind" style="width: 70px;">
						<option value="title">제 목</option>
						<option value="regNm">등록자</option>
					</select>
					<input type="text" name="searchValue" id="searchValue" class="_enterBind text" style="width: 100px;" value="${prjBbsAtclVO.searchValue }"/>
					<input type="image" src="<c:url value="/img/framework/icon/icon_function_search.gif"/>" alt="검색" title="검색" onclick="listPrjBbsAtcl(1)"/>
				</td>
				<td align="right">
					<a href="javascript:addFormPrjBbsAtcl()" class="btn02">글쓰기</a>
				</td>
			</tr>
		</table>
		<br/>
		<table class="board_a">
			<colgroup>
				<col style="width:30px;"/>
				<col style="width:120px;"/>
				<col style="width:auto;"/>
				<col style="width:80px;"/>
				<col style="width:40px;"/>
				<col style="width:100px;"/>
			</colgroup>
			<thead>
			<tr>
				<th scope="col">번호</th>
				<th scope="col">게시판 명</th>
				<th scope="col">제목</th>
				<th scope="col">등록자</th>
				<th scope="col">조회수</th>
				<th scope="col">등록일</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${prjBbsAtclList}" var="item" varStatus="status">
				<tr>
					<td >${pageInfo.totalRecordCount - ((pageInfo.currentPageNo - 1 ) * pageInfo.recordCountPerPage + status.index)}</td>
					<td >${item.bbsNm}</td>
					<td class="subject"><a href="javascript:readPrjBbsAtcl('${item.bbsCd}','${item.atclSn}')">${item.atclTitle}</a></td>
					<td >${item.regNm}</td>
					<td >${item.hits}</td>
					<td><meditag:dateformat type="1" delimeter="." property="${item.regDttm}"/></td>
				</tr>
			</c:forEach>
			<c:if test="${empty prjBbsAtclList}">
				<tr>
					<td colspan='6' align='center'><font color=blud>* 검색된 게시글 목록이 없습니다.</font></td>
				</tr>
			</c:if>
			</tbody>
		</table>
		<meditag:paging pageInfo="${pageInfo}" funcName="listPrjBbsAtcl"/>
	</form>

<script type="text/javascript">
	var ItemDTO = new Object();
	$(document).ready(function() {
		ItemDTO.crsCreCd = '${prjBbsAtclVO.crsCreCd}';
		ItemDTO.prjtSn = '${prjBbsAtclVO.prjtSn}';

		ItemDTO.curPage = 1;
		$("._enterBind").bind("keydown", eventForSearch);
		function eventForSearch(event) {
			if (event.keyCode == '13') {
				listPrjBbsAtcl(1);
			}
		}

		callResize();

		var strSelect = '${prjBbsAtclVO.searchKey}';
		$("#searchKey").val(strSelect).attr("selected", "selected");
	});

	/**
	 * 학습자 목록 조회
	 */
	function listPrjBbsAtcl(page){
		ItemDTO.curPage = page;
		var prjtSn = '${prjBbsAtclVO.prjtSn}';
		var bbsCd = $("#bbsCd option:selected").val();
		var searchKey = $("#searchKey option:selected").val();
		var searchValue = $("#searchValue").val();
		var url = cUrl("/lec/prj/bbsAtcl/mainPrjBbsAtcl")+"?prjtSn="+prjtSn+"${AMPERSAND}bbsCd="+bbsCd+"${AMPERSAND}searchKey="+searchKey+"${AMPERSAND}searchValue="+searchValue+"${AMPERSAND}curPage="+page;
		$(location).attr('href',url);
	}

	//글쓰기
	function addFormPrjBbsAtcl() {
		var bbsCd = $("#bbsCd option:selected").val();
		if(bbsCd == ""){
			alert("게시판을 선택하세요.");
			return;
		}
		location.href=cUrl("/lec/prj/bbsAtcl/addFormPrjBbsAtcl")+"?prjtSn="+ItemDTO.prjtSn+"${AMPERSAND}bbsCd="+bbsCd;
	}

	//게시글 상세보기
	function readPrjBbsAtcl(bbsCd,atclSn) {
		location.href=cUrl("/lec/prj/bbsAtcl/readPrjBbsAtcl")+"?prjtSn="+ItemDTO.prjtSn+"${AMPERSAND}bbsCd="+bbsCd+"${AMPERSAND}atclSn="+atclSn;
	}

	function callResize() {
        var height = document.body.scrollHeight;
        parent.resizeTopIframe(height);
	}
</script>
</mhtml:body>
</mhtml:class_html>
