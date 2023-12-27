<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="prjTeamVO" value="${vo}"/>
<c:set var="prjMemberVO" value="${prjMemberVO}"/>
<c:set var="projectListVO" value="${projectListVO}"/>
<mhtml:class_html>
<mhtml:class_head>
	<meditag:js src="/js/selectbox.js"/>
	<meditag:css href="css/home/pop.css" />
</mhtml:class_head>

<mhtml:body>
<div class="wrap" style="width:100%;">
	<h1>팀 자동생성</h1>
	<div class="contents">
		<form id="prjTeamForm" name="prjTeamForm" onsubmit="return false" >
	 	<input type="hidden" name="crsCreCd" value="${vo.crsCreCd}" />
	 	<input type="hidden" name="prjtSn" value="${vo.prjtSn}" />
	 	<input type="hidden" name="selPrjtSn" value="${vo.selPrjtSn}" />
	 	<input type="hidden" name="learnerCnt" value="${prjMemberVO.learnerCnt }"/>

		<div>
		* 등록된 팀 정보는 모두 삭제되고 새로 등록됩니다.<br/>
		       현 팀을 유지하면서 팀원을 변경하려면 <b>[관리]</b>메뉴를 이용해 주십시오.
		</div>
		<br/>
		<div>
			<input type="radio" id="newTeam" name="teamCre" value="newTeam" checked="checked"/><span></span><b>새로 생성</b>&nbsp;&nbsp;
			<input type="radio" id="befoTeam" name="teamCre" value="befoTeam"/><b>이전 프로젝트 팀 참조</b> &nbsp;
			<b>(총 수강생:${prjMemberVO.learnerCnt}명)</b>
		</div>
		<br/>
		<div id="newTeamView" style="display:block">
			<table class="board_b" style="width: 100%;">
				<colgroup>
					<col style="width:20%;"/>
					<col style="width:30%;"/>
					<col style="width:20%;"/>
					<col style="width:30%;"/>
				</colgroup>
				<tr>
					<th class="top" scope="row">생성할 팀 수 </th>
					<td class="top">
						<input type="text" style="width:130px;" dispName="팀 수" maxlength="100" isNull="N" lenCheck="100" value="" name="teamCnt" value="${vo.teamCnt}" class="txt" id="teamCnt"/>
 					</td>
					<th class="top" scope="row">수강생배정 </th>
					<td class="top">
						<select name="learnerAssign" style="width:140px;" id="learnerAssign" title="수강생배정">
							<html:option value="random">임의배정</html:option>
							<html:option value="name">이름순배정</html:option>
						</select>
					</td>
				</tr>
			</table>
			</div>
			<div id="befoTeamView" style="display:block">
				<table class="board_a" style="width: 100%;">
					<thead>
					<tr>
						<th scope="col">선택</th>
						<th scope="col">프로젝트명</th>
						<th scope="col">프로젝트 팀수</th>
						<th scope="col">팀원 수</th>
					</tr>
					</thead>
					<tbody>
					<c:forEach items="${projectListVO}" var="item" varStatus="status">
					<tr>
						<td><input type="radio" name="sel" id="sel" value="${item.prjtSn}" style="border:0" title="선택"/></td>
						<td>${item.prjtTitle}</td>
						<td>${item.teamCnt}</td>
						<td>${item.mbrCnt}</td>
					</tr>
					</c:forEach>
					<c:if test="${empty projectListVO}">
						<tr>
							<td colspan='4' align='center'><font color=blud>* 검색된 이전 프로젝트 목록이 없습니다.</font></td>
						</tr>
					</c:if>
					</tbody>
				</table>
			</div>
			<div class="btn_right">
				<a href="javascript:createTeam()" class="btn02">팀생성</a>
				<a href="javascript:window.close()" class="btn01">닫기</a>
			</div>
		</form>
	</div>
</div>

<script type="text/javascript">
	$("#befoTeamView").hide();

	var ItemDTO = new Object();
	$(document).ready(function() {

		//부모창 새로고침
		if("${refreshYn}" == "Y"){
			parent.opener.location.reload();
			window.close();
		}

		ItemDTO.crsCreCd = '${prjTeamVO.crsCreCd}';
		ItemDTO.prjtSn = '${prjTeamVO.prjtSn}';
		//새로생성 폼 보이기
		$("#newTeam").click(function(){
			$("#newTeamView").show();
			$("#befoTeamView").hide();
		});
		//이전 프로젝트 목록 보이기
		$("#befoTeam").click(function(){
			$("#newTeamView").hide();
			$("#befoTeamView").show();
		});
	});

	function createTeam(){
		if($('input[name=teamCre]:checked').val() == "newTeam"){

			var teamCnt = $("#teamCnt").val();
			var learnerCnt = document.prjTeamForm["learnerCnt"].value;

			if(parseInt(teamCnt, 10) > learnerCnt){
				alert("생성할 팀수는 총 수강생수 보다 작아야 합니다.");
				return;
			}
			process("addTeamCreate");

		}else{
			var teamSn = $('input[name=sel]:checked').val();
			document.prjTeamForm["selPrjtSn"].value = teamSn;

			process("addTeamCopy");
		}


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