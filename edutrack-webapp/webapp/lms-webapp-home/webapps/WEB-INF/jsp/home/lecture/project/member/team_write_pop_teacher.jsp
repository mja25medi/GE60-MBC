<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="prjTeamVO" value="${vo}"/>
<mhtml:class_html>
<mhtml:class_head>
	<meditag:css href="css/home/pop.css" />
</mhtml:class_head>
<mhtml:body>
<div class="wrap" style="width:100%;">
	<h1>신규 팀 등록</h1>
	<div class="contents">
		<form id="prjTeamForm" name="prjTeamForm" onsubmit="return false" >
			<input type="hidden" name="crsCreCd" value="${vo.crsCreCd}" />
		 	<input type="hidden" name="prjtSn" value="${vo.prjtSn}" />
		 	<input type="hidden" name="prjtTeamSn" value="${vo.prjtTeamSn}" />
			<table class="board_b" style="width: 100%;">
				<colgroup>
					<col style="width:40%"/>
					<col style="width:60%"/>
				</colgroup>
				<tr>
					<th class="top" scope="row">팀 명</th>
					<td class="top">
						<input type="text" style="width:150px;" dispName="팀 명" maxlength="100" isNull="N" lenCheck="100" name="prjtTeamNm" value="${vo.prjtTeamNm}" class="text" id="prjtTeamNm"/>
					</td>
				</tr>
			</table>
			<div class="btn_right">
				<c:if test="${gubun eq 'A'}">
					<a href="javascript:addMbr()" class="btn02">저장</a>
				</c:if>
				<c:if test="${gubun eq 'E'}">
					<a href="javascript:editMbr()" class="btn02">수정</a>
				</c:if>
				<a href="javascript:window.close()" class="btn01">닫기</a>
			</div>
		</form>
	</div>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		//부모창 새로고침
		if("${refreshYn}" == "Y"){
			parent.opener.location.reload();
			window.close();
		}
	});

	/**
	 * 신규팀 등록
	 */
	function addMbr() {
		process("addPrjTeam");
	}

	/**
	 * 팀 수정
	 */
	function editMbr() {
		process("editPrjTeam");
	}

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		$('#prjTeamForm').attr("action","/lec/prj/team/"+cmd);
		$('#prjTeamForm').submit();
	}

</script>
</mhtml:body>
</mhtml:class_html>