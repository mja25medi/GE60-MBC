<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="prjBbsVO" value="${prjBbsVO}"/>
<mhtml:class_html>
<mhtml:class_head>
	<meditag:js src="/js/selectbox.js"/>
	<meditag:css href="css/home/pop.css" />
</mhtml:class_head>
<mhtml:body>
	<form id="prjBbsForm" name="prjBbsForm" onsubmit="return false" >
		 <input type="hidden" name="crsCreCd" value="${vo.crsCreCd}" />
		 <input type="hidden" name="prjtSn" value="${vo.prjtSn}" />
		 <input type="hidden" name="bbsCd" value="${vo.bbsCd}" />
		 <input type="hidden" name="bbsTypeCd" value="BOARD"/>
		 <input type="hidden" name="detlViewCd" value="BOARD"/>

		 <table class="board_b">

			<colgroup>
				<col style="width:20%"/>
				<col style="width:30%"/>
				<col style="width:20%"/>
				<col style="width:30%"/>
			</colgroup>
			<tr>
				<th scope="row" class="top"><label for="bbsCd">게시판 명</label></th>
				<td class="top" colspan="3">
					<input type="text" style="width:97%" maxlength="100" dispName="게시판 명" isNull="N" lenCheck="100" name="bbsNm" value="${vo.bbsNm}" class="text" id="bbsNm"/>
				</td>
			</tr>
			<tr>
				<th scope="row">게시판 설명</th>
				<td colspan="3">
					<textarea style="width:97%;height:80px; border: 1px solid; border-color: #DADADA" lenCheck="1000" dispName="게시판 설명" isNull="N" name="bbsDesc">${vo.bbsDesc}</textarea>
				</td>
			</tr>
			<tr>
				<th scope="row">답글 사용 여부</th>
				<td>
					<c:forEach var="item" items="${codeUseYn}" varStatus="status">
						<input type="radio" name="ansrUseYn" value="${item.codeCd}" id="ansrUseYn_${item.codeCd}"  <c:if test="${vo.ansrUseYn eq item.codeCd}">checked</c:if>/>
						<label for="ansrUseYn_${item.codeCd}">${item.codeNm}</label>
						<c:choose>
							<c:when test="${status.last}"></c:when>
							<c:otherwise>,&nbsp;&nbsp;</c:otherwise>
						</c:choose>
					</c:forEach>
				</td>
				<th scope="row">댓글사용여부</th>
				<td>
					<c:forEach var="item" items="${codeUseYn}" varStatus="status">
						<input type="radio" name="cmntUseYn" value="${item.codeCd}" id="cmntUseYn_${item.codeCd}"  <c:if test="${vo.cmntUseYn eq item.codeCd}">checked</c:if>/>
						<label for="cmntUseYn_${item.codeCd}">${item.codeNm}</label>
						<c:choose>
							<c:when test="${status.last}"></c:when>
							<c:otherwise>,&nbsp;&nbsp;</c:otherwise>
						</c:choose>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<th scope="row">첨부 사용 여부</th>
				<td>
					<c:forEach var="item" items="${codeUseYn}" varStatus="status">
						<input type="radio" name="atchUseYn" value="${item.codeCd}" id="atchUseYn_${item.codeCd}"  <c:if test="${vo.atchUseYn eq item.codeCd}">checked</c:if> />
						<label for="atchUseYn_${item.codeCd}">${item.codeNm}</label>
						<c:choose>
							<c:when test="${status.last}"></c:when>
							<c:otherwise>,&nbsp;&nbsp;</c:otherwise>
						</c:choose>
					</c:forEach>
				</td>
				<th scope="row">목록 보기 수</th>
				<td>
					페이지당 : <input type="text" style="width:20px" maxlength="2" dispName="목록 보기 수" isNull="N" lenCheck="100" name="listViewCnt" value="${vo.listViewCnt}" class="text" id="listViewCnt"/> 라인
				</td>
			</tr>
			<tr>
				<th scope="row">다른팀 글조회 허용 여부</th>
				<td>
					<c:forEach var="item" items="${codeAllowYn}" varStatus="status">
						<input type="radio" name="inqAcptYn" value="${item.codeCd}" id="inqAcptYn_${item.codeCd}"  <c:if test="${vo.inqAcptYn eq item.codeCd}">checked</c:if> />
						<label for="inqAcptYn_${item.codeCd}">${item.codeNm}</label>
						<c:choose>
							<c:when test="${status.last}"></c:when>
							<c:otherwise>,&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</c:otherwise>
						</c:choose>
					</c:forEach>
				</td>
				<th scope="row">사용 여부</th>
				<td>
					<c:forEach var="item" items="${codeUseYn}" varStatus="status">
						<input type="radio" name="useYn" value="${item.codeCd}" id="useYn_${item.codeCd}"  <c:if test="${vo.useYn eq item.codeCd}">checked</c:if> />
						<label for="useYn_${item.codeCd}">${item.codeNm}</label>
						<c:choose>
							<c:when test="${status.last}"></c:when>
							<c:otherwise>,&nbsp;&nbsp;</c:otherwise>
						</c:choose>
					</c:forEach>
				</td>
			</tr>
		</table>
		<br/>
		<table class="board_b">
			<colgroup>
				<col style="width:20%"/>
				<col style="width:30%"/>
				<col style="width:20%"/>
				<col style="width:30%"/>
			</colgroup>
			<tr>
				<th scope="row" class="top">등록자</th>
				<td class="top">${prjBbsVO.regNo}</td>
				<th scope="row" class="top">등록일자</th>
				<td class="top">${prjBbsVO.regDttm}</td>
			</tr>
			<tr>
				<th scope="row">수정자</th>
				<td>${prjBbsVO.modNo}</td>
				<th scope="row">수정일자</th>
				<td>${prjBbsVO.modDttm}</td>
			</tr>
		</table>
		<div class="btn_right">
			<c:if test="${gubun eq 'A'}">
				<a href="javascript:addPrjBbs()" class="btn02">저장</a>
				<a href="javascript:goList()" class="btn01">닫기</a>
			</c:if>
			<c:if test="${gubun eq 'E'}">
				<a href="javascript:editPrjBbs()" class="btn02">수정</a>
				<a href="javascript:removePrjBbs()" class="btn02">삭제</a>
				<a href="javascript:goList()" class="btn01">닫기</a>
			</c:if>
		</div>
	</form>

<script type="text/javascript">
	var ItemDTO = new Object();
	$(document).ready(function() {
		ItemDTO.crsCreCd = '${prjBbsVO.crsCreCd}';
		ItemDTO.prjtSn = '${prjBbsVO.prjtSn}';

		//부모창 새로고침
		if("${refreshYn}" == "Y"){

			location.href = cUrl("/lec/prj/bbs/listPrjBbs")+"?prjtSn="+ItemDTO.prjtSn;
		}

		callResize();

	});

	//process
	function addPrjBbs() {
		var f = document.prjBbsForm;

		if(f["prjBbsVO.listViewCnt"].value == 0) {
			alert('목록 보기수를 입력해주세요.');
			return;
		}

		if(!validate(document.prjBbsForm)) return;
		$('#prjBbsForm').attr("action","/lec/prj/bbs/addPrjBbsStu");
		$('#prjBbsForm').submit();
	}

	//게시판 정보 수정
	function editPrjBbs() {
		var f = document.prjBbsForm;

		if(f["prjBbsVO.listViewCnt"].value == 0) {
			alert('목록 보기수를 입력해주세요.');
			return;
		}

		if(!validate(document.prjBbsForm)) return;
		$('#prjBbsForm').attr("action","/lec/prj/bbs/editPrjBbsStu");
		$("#prjBbsForm").submit();
	}

	//게시판 정보 삭제
	function removePrjBbs() {
		if(confirm("게시판 정보를 삭제하시겠습니까?")){
			if(!validate(document.prjBbsForm)) return;
			$('#prjBbsForm').attr("action","/lec/prj/bbs/removePrjBbsStu");
			$("#prjBbsForm").submit();
		}
	}

	//게시판 목록으로 이동
	function goList() {
		location.href = cUrl("/lec/prj/bbs/listPrjBbs")+"?prjtSn="+ItemDTO.prjtSn;
	}

	function callResize() {
        var height = document.body.scrollHeight;
        parent.resizeTopIframe(height);
	}
</script>
</mhtml:body>
</mhtml:class_html>