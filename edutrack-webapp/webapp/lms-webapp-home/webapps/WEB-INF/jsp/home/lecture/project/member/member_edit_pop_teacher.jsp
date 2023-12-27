<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="prjMemberVO" value="${vo}"/>
<c:set var="prjTeamVO" value="${prjTeamVO}"/>
<c:set var="teamList" value="${teamList}"/>
<c:set var="stdList" value="${stdList}"/>
<c:set var="mbrList" value="${mbrList}"/>
<mhtml:class_html>
<mhtml:class_head>
	<meditag:js src="/js/selectbox.js"/>
	<meditag:css href="css/home/pop.css" />
</mhtml:class_head>

<mhtml:body>
<div class="wrap" style="width:100%;">
	<h1>팀원 관리</h1>
	<div class="contents">
		<form id="prjMemberForm" name="prjMemberForm" onsubmit="return false">
			<input type="hidden" name="crsCreCd" value="${vo.crsCreCd}"/>
		 	<input type="hidden" name="prjtSn" value="${vo.prjtSn}"/>
		 	<input type="hidden" name="prjtTeamSn" value="${vo.prjtTeamSn}"/>
		 	<input type="hidden" name="prjtMbrSn" value="${vo.prjtMbrSn}"/>
		 	<input type="hidden" name="stdNo" value="${vo.stdNo}"/>
		 	<input type="hidden" name="teamLeaderDiv" value="${vo.teamLeaderDiv}"/>
			<table class="board_b" style="width: 100%;">
				<colgroup>
					<col style="width:20%"/>
					<col style="width:30%"/>
					<col style="width:20%"/>
					<col style="width:30%"/>
				</colgroup>
				<tr>
					<th class="top" scope="row">팀 명</th>
					<td class="top" colspan="3">
						<html:select name="prjtTeamSn" id="prjtTeamSn" onchange="listMember()" class="text">
							<c:forEach items="${teamList}" var="item">
								<html:option value="${item.prjtTeamSn}">${item.prjtTeamNm}</html:option>
							</c:forEach>
						</html:select>
					</td>
				</tr>
				<tr>
					<th scope="row">팀 장</th>
					<td colspan="3" colspan="3">
						${prjTeamVO.teamLeader}(${prjTeamVO.leaderId})
					</td>
				</tr>
			</table>
			<div style="padding: 15px;">
				팀원정보
			</div>
				<div style="width: 300px; height:250px; float: left; border: 1px solid; border-color: #DADADA; overflow: auto;">
					<table class="board_a" style="width: 100%;">
						<colgroup>
							<col style="width:10%"/>
							<col style="width:45%"/>
							<col style="width:45%"/>
						</colgroup>
						<thead>
						<tr>
							<th scope="col"><input type="checkbox" name="chkAll1" id="chkAll1" value="N" style="border:0" title="전체 선택" onclick="checkAll()"/></th>
							<th scope="col">이름</th>
							<th scope="col">아이디</th>
						</tr>
						</thead>
						<tbody>
						<c:forEach items="${stdList}" var="item" varStatus="status">
							<tr>
								<td><input type="checkbox" name="sel1" id="sel1" value="${item.stdNo}" style="border:0" title="선택"/></td>
								<td>${item.stdNm}</td>
								<td>${item.stdId}</td>
							</tr>
						</c:forEach>
						<c:if test="${empty stdList}">
							<tr>
								<td colspan='3' align='center'><font color=blud>* 검색된 학습자 목록이 없습니다.</font></td>
							</tr>
						</c:if>
						</tbody>
					</table>
				</div>
				<div style="float: left; width:50px; margin-top: 100px;">
					<table style="width:100%;">
						<tr>
							<td align="center">
								<a href="javascript:addTeamMbr()" class="btn01">▷</a><br/><br/>
								<a href="javascript:delTeamMbr()" class="btn01">◁</a>
							</td>
						</tr>
					</table>
				</div>
				<div style="width: 300px; height:250px; float: right; border: 1px solid; border-color: #DADADA; overflow: auto;">
					<table class="board_a" style="width: 100%;">
						<colgroup>
							<col style="width:10%"/>
							<col style="width:30%"/>
							<col style="width:30%"/>
							<col style="width:30%"/>
						</colgroup>
						<thead>
						<tr>
							<th scope="col"><input type="checkbox" name="chkAll2" id="chkAll2" value="N" style="border:0" title="전체 선택" onclick="checkAll()"/></th>
							<th scope="col">이름</th>
							<th scope="col">아이디</th>
							<th scope="col">팀장선정</th>
						</tr>
						</thead>
						<tbody>
						<c:forEach items="${mbrList}" var="item" varStatus="status">
						<tr>
							<td><input type="checkbox" name="sel2" id="sel2" value="${item.prjtMbrSn}" style="border:0"/></td>
							<td>${item.stdNm}</td>
							<td>${item.stdId}</td>
							<c:if test="${item.teamLeaderDiv == 'Y'}">
								<td><input type="radio" name="leader" id="leader" onclick="editTeam('${item.prjtTeamSn}')" value="${item.prjtMbrSn}" style="border:0" checked="checked"/></td>
							</c:if>
							<c:if test="${item.teamLeaderDiv == 'N'}">
								<td><input type="radio" name="leader" id="leader" onclick="editTeam('${item.prjtTeamSn}')" value="${item.prjtMbrSn}" style="border:0"/></td>
							</c:if>
						</tr>
						</c:forEach>
						<c:if test="${empty mbrList}">
							<tr>
								<td colspan='4' align='center'><font color=blud>* 검색된 팀원 목록이 없습니다.</font></td>
							</tr>
						</c:if>
						</tbody>
					</table>
				</div>
			<table style="width:96%">
				<tr>
					<td>
						<div class="btn_right">
							<a href="javascript:window.close()" class="btn01">닫기</a>
						</div>
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>

<script type="text/javascript">

	$(document).ready(function() {

	});

	function listMember(){

		var crsCreCd = '${prjMemberVO.crsCreCd}';
		var prjtSn = '${prjMemberVO.prjtSn}';
		var prjtTeamSn = $("#prjtTeamSn option:selected").val();
		var url = cUrl("/lec/prj/member/mainPrjMember")+"?prjtSn="+prjtSn+"${AMPERSAND}prjtTeamSn="+prjtTeamSn;
		$(location).attr('href',url);

	}

	//checkbox 전체선택
	function checkAll() {
	    var state1 = $('input[name=chkAll1]:checked').size();
	    var state2 = $('input[name=chkAll2]:checked').size();
	    if(state1 == 1){
	   		$("#prjMemberForm input[name='sel1']").attr({checked:true});
	    }else{
	    	$("#prjMemberForm input[name='sel1']").attr({checked:false});
	    }
	    if(state2 == 1){
	   		$("#prjMemberForm input[name='sel2']").attr({checked:true});
	    }else{
	    	$("#prjMemberForm input[name='sel2']").attr({checked:false});
	    }
	}

	//팀원 등록
	function addTeamMbr() {
		if(addTeam()) {
			process("addPrjMember");
		}

	}

	function addTeam() {
		var stdNo = "";
		$('input[name=sel1]:checked').each(function() {
			stdNo = stdNo + "|" + $(this).val();
			}
		);
		stdNo = stdNo.substring(1);
		if(stdNo == "") {
			alert("팀원을 선택해 주세요.");
			return false;
		}
		document.prjMemberForm["prjMemberVO.stdNo"].value = stdNo;

		return true;
	}

	//팀장 선정
	function editTeam(prjtTeamSn) {
		var chk = $("input[name=leader]:checked").val();

		document.prjMemberForm["prjtTeamSn"].value = prjtTeamSn;
		document.prjMemberForm["prjtMbrSn"].value = chk;
		document.prjMemberForm["teamLeaderDiv"].value = "Y";

		process("editPrjMember");
	}


	//팀원 삭제
	function delTeamMbr() {
		if(delTeam()) {
			process("removePrjMember");
		}

	}

	function delTeam() {
		var mbrNo = "";
		$('input[name=sel2]:checked').each(function() {
			mbrNo = mbrNo + "|" + $(this).val();
			}
		);
		mbrNo = mbrNo.substring(1);
		if(mbrNo == "") {
			alert("팀원을 선택해 주세요.");
			return false;
		}
		document.prjMemberForm["prjtMbrSn"].value = mbrNo;

		return true;
	}

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		$('#prjMemberForm').attr("action","/lec/prj/member/"+cmd);
		$('#prjMemberForm').submit();
	}

</script>
</mhtml:body>
</mhtml:class_html>