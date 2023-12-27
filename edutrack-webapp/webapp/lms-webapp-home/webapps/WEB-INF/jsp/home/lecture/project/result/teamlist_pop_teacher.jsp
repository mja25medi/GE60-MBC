<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="projectResultVO" value="${vo}"/>
<c:set var="teamList" value="${teamList}"/>
<mhtml:class_html>
<mhtml:class_head>
	<meditag:js src="/js/selectbox.js"/>
	<meditag:css href="css/home/pop.css" />
</mhtml:class_head>

<mhtml:body>
<div class="wrap" style="width:100%;">
	<h1>팀원 보기</h1>
	<div class="contents">
		<form id="projectResultForm" name="projectResultForm" onsubmit="return false" />
			<input type="hidden" name="strMbrScore"/>
			<input type="hidden" name="strPrjtMbrSn"/>
			<input type="hidden" name="crsCreCd" value="${vo.crsCreCd}"/>
		 	<input type="hidden" name="prjtSn" value="${vo.prjtSn}"/>
		 	<input type="hidden" name="prjtTeamSn" value="${vo.prjtTeamSn}"/>
			<table class="board_b">
				<colgroup>
					<col style="width:20%"/>
					<col style="width:30%"/>
					<col style="width:20%"/>
					<col style="width:30%"/>
				</colgroup>
				<tr>
					<th class="top" scope="row">팀 명</th>
					<td class="top" colspan="3">
						${projectResultVO.prjtTeamNm}
						</td>
				</tr>
				<tr>
					<th scope="row">팀 장</th>
					<td colspan="3" colspan="3">
						 ${projectResultVO.teamLeader}(${projectResultVO.leaderId})
					</td>
				</tr>
			</table>
			<br/>
			<table class="board_a">
				<colgroup>
					<col style="width:10%"/>
					<col style="width:15%"/>
					<col style="width:15%"/>
					<col style="width:15%"/>
					<col style="width:35%"/>
					<col style="width:10%"/>
				</colgroup>
				<thead>
				<tr>
					<th scope="col">번호</th>
					<th scope="col">이름</th>
					<th scope="col">아이디</th>
					<th scope="col">수강번호</th>
					<th scope="col">역할 정의</th>
					<th scope="col">점수</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach items="${teamList}" var="item" varStatus="status">
					<input type="hidden" name="prjtMbrSn" value="${item.prjtMbrSn}"/>
					<c:set var="mbrRole" value="${item.mbrRole}"/>
					<c:if test="${fn:length(item.mbrRole) > 12}">
						<c:set var="mbrRole" value="${item.mbrRole}..."/>
					</c:if>
					<tr>
						<td>${status.count}</td>
						<td>${item.teamLeader}</td>
						<td>${item.leaderId}</td>
						<td>${item.stdNo}</td>
						<td>${mbrRole}</td>
						<td class="center"><input type="text" name="mbrScore" style="width:26px" value="${item.mbrScore}"/>점</td>
					</tr>
				</c:forEach>
				<c:if test="${empty teamList}">
					<tr>
						<td colspan="5" align="center"><font color=blud>* 검색된 팀목록이 없습니다.</font></td>
					</tr>
				</c:if>
				</tbody>
			</table>
			<div class="btn_right">
				<a href="javascript:editTeamScoreAll()" class="btn02">저장</a>
				<a href="javascript:window.close()" class="btn01">닫기</a>
			</div>
		</form>
	</div>
</div>

<script type="text/javascript">

	var ItemDTO = new Object();

	$(document).ready(function() {
		ItemDTO.crsCreCd = '${projectResultVO.crsCreCd}';
		ItemDTO.prjtSn = '${projectResultVO.prjtSn}';
		ItemDTO.prjtTeamSn = '${projectResultVO.prjtTeamSn}';

		//부모창 새로고침
		if("${refreshYn}" == "Y"){
			parent.opener.location.reload();
		}
	});

	//프로젝트 팀 회원 점수저장
	function editTeamScoreAll() {
	    var MbrScoreObj = document.getElementsByName("mbrScore");
		var PrjtMbrSnObj = document.getElementsByName("prjtMbrSn");

		var strMbrScore = "";
		var strPrjtMbrSn = "";

		for(var i=0; i < MbrScoreObj.length; i++) {

			if(MbrScoreObj[i].value>100){
				alert('팀원 점수는 최고 100점 입니다.');
				MbrScoreObj[i].value = "0";
				MbrScoreObj[i].focus();
				return;
	     		  //-- 값이 들어온것만 잡는다.
			}else if(!isEmpty(MbrScoreObj[i].value)){
					strMbrScore = strMbrScore+"|"+MbrScoreObj[i].value;
					strPrjtMbrSn = strPrjtMbrSn+"|"+PrjtMbrSnObj[i].value;
				}
		}

		strMbrScore = strMbrScore.substring(1);
		strPrjtMbrSn = strPrjtMbrSn.substring(1);

		document.projectResultForm["strMbrScore"].value = strMbrScore;
		document.projectResultForm["strPrjtMbrSn"].value = strPrjtMbrSn;

		process("editMbScoreAll");
	}

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		$('#projectResultForm').attr("action","/lec/prj/result/" + cmd);
		$('#projectResultForm').submit();
	}
</script>
</mhtml:body>
</mhtml:class_html>