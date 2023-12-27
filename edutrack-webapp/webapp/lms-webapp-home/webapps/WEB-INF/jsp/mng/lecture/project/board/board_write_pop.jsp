<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="prjBbsVO" value="${prjBbsForm.prjBbsVO}"/>
<mhtml:html>
<mhtml:head titleName="게시판 관리 - 게시판 정보 등록">
	<meditag:js src="/js/selectbox.js"/>
</mhtml:head>
<mhtml:frm_body cssTag="background-color:#ffffff;">
	<br>
	<form id="prjBbsForm" name="prjBbsForm" onsubmit="return false" action="/LecturePrjBbsAdmin.do">
		 <input type="hidden" name="cmd" />
		 <input type="hidden" name="crsCreCd" />
		 <input type="hidden" name="prjtSn" />
		 <input type="hidden" name="bbsCd" />
		 <input type="hidden" name="bbsTypeCd" value="BOARD"/>
		 <input type="hidden" name="detlViewCd" value="BOARD"/>

		 <c:if test="${gubun eq 'A'}">
		 <table summary="게시판 정보 등록 폼" style="width:96%" class="table_dtl" align="center">
		 </c:if>
		 <c:if test="${gubun eq 'E'}">
		 <table summary="게시판 정보 수정 폼" style="width:100%" class="table_dtl" align="center">
		 </c:if>
			<colgroup>
				<col style="width:20%"/>
				<col style="width:30%"/>
				<col style="width:20%"/>
				<col style="width:30%"/>
			</colgroup>
			<tr height="35">
				<th scope="row" class="top"><label for="bbsCd">게시판 명</label></th>
				<td class="top" colspan="3">
					<input type="text" style="width:98%" maxlength="100" dispName="게시판 명" isNull="N" lenCheck="100" name="bbsNm" class="txt" id="bbsNm"/>
				</td>
			</tr>
			<tr height="115">
				<th scope="row">게시판 설명</th>
				<td colspan="3">
					<textarea style="width:99%;height:100px; border: 1px solid; border-color: #DADADA" lenCheck="1000" dispName="게시판 설명" isNull="N" name="bbsDesc"/>
				</td>
			</tr>
			<tr height="33">
				<th scope="row">답글 사용 여부</th>
				<td>
					<c:forEach var="item" items="${codeUseYn}" varStatus="status">
						<input type="radio" name="ansrUseYn" value="${item.codeCd}" id="ansrUseYn_${item.codeCd}" />
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
						<input type="radio" name="cmntUseYn" value="${item.codeCd}" id="cmntUseYn_${item.codeCd}"/>
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
						<input type="radio" name="atchUseYn" value="${item.codeCd}" id="atchUseYn_${item.codeCd}" />
						<label for="atchUseYn_${item.codeCd}">${item.codeNm}</label>
						<c:choose>
							<c:when test="${status.last}"></c:when>
							<c:otherwise>,&nbsp;&nbsp;</c:otherwise>
						</c:choose>
					</c:forEach>
				</td>
				<th scope="row">목록 보기 수</th>
				<td>
					페이지당 : <input type="text" style="width:20px" maxlength="2" dispName="목록 보기 수" isNull="N" lenCheck="100" name="listViewCnt" class="txt" id="listViewCnt"/> 라인
				</td>
			</tr>
			<tr>
				<th scope="row">다른팀 글조회 허용 여부</th>
				<td>
					<c:forEach var="item" items="${codeAllowYn}" varStatus="status">
						<input type="radio" name="inqAcptYn" value="${item.codeCd}" id="inqAcptYn_${item.codeCd}" />
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
						<input type="radio" name="useYn" value="${item.codeCd}" id="useYn_${item.codeCd}" />
						<label for="useYn_${item.codeCd}">${item.codeNm}</label>
						<c:choose>
							<c:when test="${status.last}"></c:when>
							<c:otherwise>,&nbsp;&nbsp;</c:otherwise>
						</c:choose>
					</c:forEach>
				</td>
			</tr>
		</table>
		<table style="width:98%">
		<tr>
			<td colspan="4" align="right" height="40">
				<meditag:buttonwrapper>
					<c:if test="${gubun eq 'A'}">
						<meditag:button value="저장" title="입력한 게시판 정보를 저장합니다." func="addPrjBbs()" />
						<meditag:button value="취소" title="게시판 정보 등록 창을 닫습니다." func="parent.projectPopBox.close()" />
					</c:if>
					<c:if test="${gubun eq 'E'}">
						<meditag:button value="수정" title="입력한 게시판 정보를 저장합니다." func="editPrjBbs()" />
						<meditag:button value="삭제" title="게시판 정보를 삭제합니다." func="removePrjBbs()" />
						<meditag:button value="취소" title="게시판 정보 등록 창을 닫습니다." func="goList()" />
					</c:if>
				</meditag:buttonwrapper>
			</td>
		</tr>
		</table>
	<input type="submit" value="submit" style="display:none" />
	</form>
<script type="text/javascript">

	var ItemDTO = new Object();
	$(document).ready(function() {
		ItemDTO.crsCreCd = '${prjBbsVO.crsCreCd}';
		ItemDTO.prjtSn = '${prjBbsVO.prjtSn}';

		ItemDTO.bbsCd = '${prjBbsVO.bbsCd}';
		if(ItemDTO.bbsCd != ""){
			setFrame();
		}
	});

	//process
	function addPrjBbs() {
		if(!validate(document.prjBbsForm)) return;
		$('#prjBbsForm').find('input[name=cmd]').val('add');
		$('#prjBbsForm').ajaxSubmit(processCallback);
	}

	//처리 결과 표시 콜백
	function processCallback(resultDTO) {
		alert(resultDTO.message);

		if(resultDTO.result >= 0) {
			parent.subWorkFrame.subWorkFrame2.listPjtBbs();
			parent.projectPopBox.close();
		} else {
			// 비정상 처리
		}
	}

	//게시판 정보 수정
	function editPrjBbs() {
		if(!validate(document.prjBbsForm)) return;
		$('#prjBbsForm').find('input[name=cmd]').val('edit');
		$("#prjBbsForm").submit();
	}

	//게시판 정보 삭제
	function removePrjBbs() {
		if(confirm("게시판 정보를 삭제하시겠습니까?")){
			if(!validate(document.prjBbsForm)) return;
			$('#prjBbsForm').find('input[name=cmd]').val('remove');
			$("#prjBbsForm").submit();
		}
	}

	//게시판 목록으로 이동
	function goList() {
		document.location.href = "/LecturePrjBbsAdmin.do?cmd=main${AMPERSAND}prjBbsVO.crsCreCd="+ItemDTO.crsCreCd+"${AMPERSAND}prjBbsVO.prjtSn="+ItemDTO.prjtSn;
	}

	function setFrame(){
		var iframeObj = parent.document.getElementById("subWorkFrame2");
		parent.resizeIframe3(iframeObj, $(document).height());
	}

</script>
</mhtml:frm_body>
</mhtml:html>