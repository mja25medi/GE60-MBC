<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="projectResultVO" value="${vo}"/>
<c:set var="projectResultList" value="${projectResultList}"/>
<mhtml:class_html>
<mhtml:class_head>
</mhtml:class_head>
<mhtml:body>
	<form id="projectResultForm" name="projectResultForm" onsubmit="return false" >
	<input type="hidden" name="strTeamScore"/>
	<input type="hidden" name="strPrjtTeamSn"/>
	<input type="hidden" name="crsCreCd" value="${vo.crsCreCd}" />
 	<input type="hidden" name="prjtSn" value="${vo.prjtSn}" />
		<div class="btn_right">
			<a href="javascript:editScoreAll()" class="btn02">점수저장</a>
		</div>
		<br/>
		<table class="board_a">
			<colgroup>
				<col style="width:5%"/>
				<col style="width:auto;"/>
				<col style="width:10%"/>
				<col style="width:10%"/>
				<col style="width:10%"/>
			</colgroup>
			<thead>
			<tr>
				<th scope="col">번호</th>
				<th scope="col">팀명</th>
				<th scope="col">팀 점수</th>
				<th scope="col">팀원</th>
				<th scope="col">제출정보</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${projectResultList}" var="item" varStatus="status">
				<input type='hidden' name='prjtTeamSn' value="${item.prjtTeamSn}"/>
				<tr>
					<td>${status.count}</td>
					<td class='left'>${item.prjtTeamNm}</td>
					<td><input type="text" name="teamScore" style="width:26px" value="${item.teamScore}"/>점</td>
					<td><a href="javascript:showTeam('${item.prjtTeamSn}');" class="btn_org"><span>보기</span></a></td>
					<td><a href="javascript:showSi('${item.prjtTeamSn}');" class="btn_org"><span>보기</span></a></td>
				</tr>
			</c:forEach>
			<c:if test="${empty projectResultList}">
				<tr>
					<td colspan="5" align="center"><font color=blud>* 검색된 팀목록이 없습니다.</font></td>
				</tr>
			</c:if>
			</tbody>
		</table>
	</form>

<script type="text/javascript">

	var ItemDTO = new Object();
	$(document).ready(function() {
		ItemDTO.crsCreCd = '${projectResultVO.crsCreCd}';
		ItemDTO.prjtSn = '${projectResultVO.prjtSn}';
		ItemDTO.prjtTeamSn = '${projectResultVO.prjtTeamSn}';

		callResize();
	});

	//팀프로젝트 팀 점수 저장
	function editScoreAll() {
	    var TeamScoreObj = document.getElementsByName("teamScore");
		var PrjtTeamSnObj = document.getElementsByName("prjtTeamSn");

		var strTeamScore = "";
		var strPrjtTeamSn = "";

		for(var i=0; i < TeamScoreObj.length; i++) {

			if(TeamScoreObj[i].value > 100){
				alert('팀원 점수는 최고 100점 입니다.');
				TeamScoreObj[i].value = "0";
				TeamScoreObj[i].focus();
				return;
	     		  //-- 값이 들어온것만 잡는다.
			}else if(!isEmpty(TeamScoreObj[i].value)){
				strTeamScore = strTeamScore+"|"+TeamScoreObj[i].value;
				strPrjtTeamSn = strPrjtTeamSn+"|"+PrjtTeamSnObj[i].value;
			}
		}

		strTeamScore = strTeamScore.substring(1);
		strPrjtTeamSn = strPrjtTeamSn.substring(1);

		document.projectResultForm["strTeamScore"].value = strTeamScore;
		document.projectResultForm["strPrjtTeamSn"].value = strPrjtTeamSn;

		process("editScoreAll");
	}

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		$('#projectResultForm').attr("action","/lec/prj/result/"+ cmd);
		$('#projectResultForm').submit();
	}

	//팀원 보기
	function showTeam(prjtTeamSn){

		var url = cUrl("/lec/prj/result/teamMain")+"?prjtSn=${projectResultVO.prjtSn}${AMPERSAND}crsCreCd=${projectResultVO.crsCreCd}${AMPERSAND}prjtTeamSn="+prjtTeamSn;
		var winOption = "left=0,top=0,toolbar=0,location=0,directories=0,status=yes,menubar=0,scrollbars=yes,resizable=yes,width=700,height=400";
		var teamWin = window.open(url, "teamWin", winOption);
		teamWin.focus();
	}

	//제출 정보 보기
	function showSi(prjtTeamSn){
		var url = cUrl("/lec/prj/result/siInfoMain")+"?prjtSn=${projectResultVO.prjtSn}${AMPERSAND}crsCreCd=${projectResultVO.crsCreCd}${AMPERSAND}prjtTeamSn="+prjtTeamSn;
		var winOption = "left=0,top=0,toolbar=0,location=0,directories=0,status=yes,menubar=0,scrollbars=yes,resizable=yes,width=700,height=550";
		var siinfoWin = window.open(url, "siinfoWin", winOption);
		siinfoWin.focus();
	}

	function callResize() {
        var height = document.body.scrollHeight;
        parent.resizeTopIframe(height);
	}
</script>
</mhtml:body>
</mhtml:class_html>





