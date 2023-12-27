<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="prjBbsVO" value="${vo}"/>
<c:set var="prjBbsList" value="${prjBbsListVO}"/>
<mhtml:class_html>
<mhtml:class_head>
</mhtml:class_head>
<mhtml:body>
	<form id="prjBbsForm" name="prjBbsForm" onsubmit="return false" action="/lec/prj/bbs">
		<input type="hidden" name="crsCreCd" value="${vo.crsCreCd}" />
		<input type="hidden" name="prjtSn" value="${vo.prjtSn}" />
	</form>
	<div class="btn_right">
		<a href="javascript:addPrjBbs()" class="btn02">게시판 등록</a>
	</div>
	<br/>
	<table class="board_a">
		<colgroup>
			<col style="width:60px;"/>
			<col style="width:auto;"/>
			<col style="width:80px;"/>
			<col style="width:80px;"/>
			<col style="width:80px;"/>
			<col style="width:80px;"/>
			<col style="width:60px;"/>
		</colgroup>
		<thead>
		<tr>
			<th scope="col">번호</th>
			<th scope="col">게시판 명</th>
			<th scope="col">답글</th>
			<th scope="col">댓글</th>
			<th scope="col">첨부</th>
			<th scope="col">사용여부</th>
			<th scope="col">수정</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${prjBbsList}" var="item" varStatus="status">
			<tr>
				<td>${status.count}</td>
				<td class="subject">${item.bbsNm}</td>
				<td>${item.ansrUseYnNm}</td>
				<td>${item.cmntUseYnNm}</td>
				<td>${item.atchUseYnNm}</td>
				<td>${item.useYnNm}</td>
				<td><a class="btn_org" href="#none" onclick="javascript:editPrjBbs('${item.bbsCd}')"><span>수정</span></a></td>
			</tr>
		</c:forEach>
		<c:if test="${empty prjBbsList}">
			<tr>
				<td colspan='7' align='center'><font color=blud>* 검색된 게시판 목록이 없습니다.</font></td>
			</tr>
		</c:if>
		</tbody>
	</table>

<script type="text/javascript">
	var ItemDTO = new Object();
	$(document).ready(function() {
		ItemDTO.crsCreCd = '${prjBbsVO.crsCreCd}';
		ItemDTO.prjtSn = '${prjBbsVO.prjtSn}';

		callResize();
	});

	//게시판 등록
	function addPrjBbs() {
 		var url = cUrl("/lec/prj/bbs/addFormPrjBbs")+"?prjtSn=${prjBbsVO.prjtSn}";
		var winOption = "left=0,top=0,toolbar=0,location=0,directories=0,status=yes,menubar=0,scrollbars=yes,resizable=yes,width=800,height=450";
		var prjBbsWin = window.open(url, "prjBbsWin", winOption);
		prjBbsWin.focus();

	}

	//게시판 수정
	function editPrjBbs(bbsCd) {
		var url = cUrl("/lec/prj/bbs/editFormPrjBbs")+"?prjtSn=${prjBbsVO.prjtSn}${AMPERSAND}bbsCd="+bbsCd;
		var winOption = "left=0,top=0,toolbar=0,location=0,directories=0,status=yes,menubar=0,scrollbars=yes,resizable=yes,width=800,height=450";
		var prjBbsWin = window.open(url, "prjBbsWin", winOption);
		prjBbsWin.focus();
	}

	function callResize() {
        var height = document.body.scrollHeight;
        parent.resizeTopIframe(height);
	}

</script>
</mhtml:body>
</mhtml:class_html>