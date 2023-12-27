<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="prjTeamVO" value="${vo}"/>
<c:set var="prjTeamListVO" value="${prjTeamListVOVO}"/>
<mhtml:class_html>
<mhtml:class_head/>
<mhtml:body>
	<form id="prjTeamForm" name="prjTeamForm" onsubmit="return false">
 	<input type="hidden" name="crsCreCd" value="${vo.crsCreCd}" />
 	<input type="hidden" name="prjtSn" value="${vo.prjtSn}" />
 	<input type="hidden" name="prjtTeamSn" value="${vo.prjtTeamSn}" />
 		<div class="btn_right">
			<a href="javascript:addNewTeam()" class="btn02">신규팀등록</a>
			<a href="javascript:createTeam()" class="btn02">팀자동생성</a>
			<a href="javascript:delTeam()" class="btn02">팀삭제</a>
		</div>
		<br/>
		<table class="board_a">
			<colgroup>
				<col style="width:5%"/>
				<col style="width:5%"/>
				<col style="width:50%"/>
				<col style="width:10%"/>
				<col style="width:10%"/>
				<col style="width:10%"/>
				<col style="width:10%"/>
			</colgroup>
			<thead>
			<tr>
				<th scope="col"><input type="checkbox" name="chkAll" id="chkAll" value="N" style="border:0" title="전체 선택" onclick="checkAll()"/></th>
				<th scope="col">번호</th>
				<th scope="col">팀명</th>
				<th scope="col">팀장</th>
				<th scope="col">팀원수</th>
				<th scope="col">수정</th>
				<th scope="col">관리</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${prjTeamListVO}" var="item" varStatus="status">
				<tr>
					<td><input type="checkbox" name="sel" id="sel" value="${item.prjtTeamSn}" style="border:0" title="선택"/></td>
					<td>${status.count}</td>
					<td class="left">${item.prjtTeamNm}</td>
					<td>${item.teamLeader}</td>
					<td>${item.mbrCnt}</td>
					<td><a href="javascript:editTeam('${item.prjtTeamSn}')" class="btn_org"><span>수정</span></a></td>
					<td><a href="javascript:editTeamMbr('${item.prjtTeamSn}')" class="btn_org"><span>관리</span></a></td>
				</tr>
			</c:forEach>
			<c:if test="${empty prjTeamListVO}">
				<tr>
					<td colspan='7' align='center'><font color=blud>* 검색된 팀목록이 없습니다.</font></td>
				</tr>
			</c:if>
			</tbody>
		</table>
	</form>

<script type="text/javascript">
	var ItemDTO = new Object();
	$(document).ready(function() {
		ItemDTO.crsCreCd = '${prjTeamVO.crsCreCd}';
		ItemDTO.prjtSn = '${prjTeamVO.prjtSn}';

		callResize();
	});

	function addNewTeam() {
		var url = cUrl("/lec/prj/team/addFormPrjTeam")+"?prjtSn=${prjTeamVO.prjtSn}";
		var winOption = "left=0,top=0,toolbar=0,location=0,directories=0,status=yes,menubar=0,scrollbars=yes,resizable=yes,width=400,height=180";
		var teamWin = window.open(url, "teamWin", winOption);
		teamWin.focus();

	}

	//팀 수정
	function editTeam(prjtTeamSn) {
		var url = cUrl("/lec/prj/team//editFormPrjTeam")+"?prjtSn=${prjTeamVO.prjtSn}${AMPERSAND}prjtTeamSn="+prjtTeamSn;
		var winOption = "left=0,top=0,toolbar=0,location=0,directories=0,status=yes,menubar=0,scrollbars=yes,resizable=yes,width=400,height=180";
		var teamWin = window.open(url, "teamWin", winOption);
		teamWin.focus();

	}

	//팀 자동 생성
	function createTeam() {
		var url = cUrl("/lec/prj/team/addFormTeamCreate")+"${AMPERSAND}prjtSn=${prjTeamVO.prjtSn}";
		var winOption = "left=0,top=0,toolbar=0,location=0,directories=0,status=yes,menubar=0,scrollbars=yes,resizable=yes,width=650,height=300";
		var teamWin = window.open(url, "teamWin", winOption);
		teamWin.focus();

	}

	//팀원 관리
	function editTeamMbr(prjtTeamSn) {
		var url = cUrl("/lec/prj/member/mainPrjMember")+"?prjtSn=${prjTeamVO.prjtSn}${AMPERSAND}prjtTeamSn="+prjtTeamSn;
		var winOption = "left=0,top=0,toolbar=0,location=0,directories=0,status=yes,menubar=0,scrollbars=yes,resizable=yes,width=722,height=530";
		var memberWin = window.open(url, "memberWin", winOption);
		memberWin.focus();

	}

	//checkbox 전체선택
	function checkAll() {
	    var state = $('input[name=chkAll]:checked').size();
	    if(state == 1){
	   		$("#prjTeamForm input[name='sel']").attr({checked:true});
	    }else{
	    	$("#prjTeamForm input[name='sel']").attr({checked:false});
	    }
	}

	//팀삭제
	function delTeam() {
		if(setTeam()) {
			if(confirm("선택하신 팀을 삭제 하시겠습니까?")){
				process("remove");
			}
		}

	}

	function setTeam() {
		var chkTeam = "";
		$('input[name=sel]:checked').each(function() {
				chkTeam = chkTeam + "|" + $(this).val();
			}
		);
		chkTeam = chkTeam.substring(1);
		if(chkTeam == "") {
			alert("팀을 선택해 주세요.");
			return false;
		}
		document.prjTeamForm["prjtTeamSn"].value = chkTeam;
		return true;
	}

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		$('#prjTeamForm').attr("action","/lec/prj/team/"+cmd);
		$('#prjTeamForm').submit();
	}

	function callResize() {
         var height = document.body.scrollHeight;
         parent.resizeTopIframe(height);
	}
</script>
</mhtml:body>
</mhtml:class_html>





