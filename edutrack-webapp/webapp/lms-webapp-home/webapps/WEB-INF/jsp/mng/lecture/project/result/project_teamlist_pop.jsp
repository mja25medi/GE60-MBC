<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="projectResultVO" value="${projectResultVO}"/>
<mhtml:html>
<mhtml:head titleName="평가관리">
	<meditag:js src="/js/selectbox.js"/>
</mhtml:head>

<mhtml:frm_body cssTag="background-color:#ffffff;">
	<br>
<form id="projectResultForm" name="projectResultForm" onsubmit="return false" action="/LectureProjectResultAdmin.do">
	<input type="hidden" name="cmd" />
	<%-- <input type="hidden" property="projectResultVO.crsCreCd"/>
 	<input type="hidden" property="projectResultVO.prjtSn"/>
 	<input type="hidden" property="projectResultVO.prjtTeamSn"/>
 	<input type="hidden" property="projectResultVO.prjtMbrSn"/> --%>
	<table summary="팀원보기" style="width:96%" class="table_dtl" align="center">
		<colgroup>
			<col style="width:20%"/>
			<col style="width:30%"/>
			<col style="width:20%"/>
			<col style="width:30%"/>
		</colgroup>
		<tr height="35">
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
		<table summary="사용자 목록" style="width:96%;" class="table_list" align="center">
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
				<tbody id="teamList"></tbody>
			</table>

	<table style="width:96%">
		<tr>
			<td>
				<meditag:buttonwrapper>
					<meditag:button value="저장" title="저장" func="editTeamScoreAll()" />
					<meditag:button value="닫기" title="닫기" func="parent.projectPopBox.close();" />
				</meditag:buttonwrapper>
			</td>
		</tr>
	</table>

	</form>
<script type="text/javascript">
	var ItemDTO = new Object();
	$(document).ready(function() {
		ItemDTO.crsCreCd = '${projectResultVO.crsCreCd}';
		ItemDTO.prjtSn = '${projectResultVO.prjtSn}';
		ItemDTO.prjtTeamSn = '${projectResultVO.prjtTeamSn}';
		//팀원 정보 조회
		listPrjResult();
		//listMemberCallback();
	});

	//팀원 정보 조회
	function listPrjResult(){
		$.getJSON(cUrl("/LectureProjectResultAdmin.do"),		//url
				{ "cmd" : "teamList",
				  "projectResultVO.crsCreCd" : ItemDTO.crsCreCd,
				  "projectResultVO.prjtSn" : ItemDTO.prjtSn,
				  "projectResultVO.prjtTeamSn" : ItemDTO.prjtTeamSn
				},											//parameter
				listMemberCallback							//callback function
		);
		displayWorkProgress();
	}

	//팀원 정보 조회Callback
	function listMemberCallback(ProcessResultListDTO){
		var retList = ProcessResultListDTO.returnList;
		var listStr = "";

		if(retList.length == 0) {
			listStr +="	<tr><td colspan='6' align='center'><font color=blud>* 팀원 목록이  없습니다.</font></td></tr>\n";
		}

		var cnt = 0;
		for (var i=0; i<retList.length; i++) {
			var item = retList[i];
			listStr +="	<tr>\n";
			listStr +="		<input type='hidden' name='prjtMbrSn' value='"+item.prjtMbrSn+"'>\n";
			listStr +="		<td>"+(++cnt)+"</td>\n";
			listStr +="		<td>"+item.teamLeader+"</td>\n";
			listStr +="		<td>"+item.leaderId+"</td>\n";
			listStr +="		<td>"+item.stdNo+"</td>\n";
			if(item.mbrRole.length > 12){
				listStr +="		<td>"+item.mbrRole+"...</td>\n";
			}else{
				listStr +="		<td>"+item.mbrRole+"</td>\n";
			}

			listStr +="		<td class='center'><input type='text' name='mbrScore' style='width:26px' value='"+item.mbrScore+"'>점</td>\n";
			listStr +="	</tr>\n";
		}

		$("#teamList").html(listStr);

		closeWorkProgress();
	}


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


		$.getJSON( cUrl("/LectureProjectResultAdmin.do"),		// url
				{ "cmd" : "editMbScoreAll",
			 	  "projectResultVO.crsCreCd" : ItemDTO.crsCreCd,
	      		  "projectResultVO.prjtSn" : ItemDTO.prjtSn,
	      		  "projectResultVO.prjtTeamSn" : ItemDTO.prjtTeamSn,
	      		  "strMbrScore" : strMbrScore,
	      		  "strPrjtMbrSn": strPrjtMbrSn
				},			// params
				processAddScoreCallback				// callback function
		);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processAddScoreCallback(resultDTO) {
		alert(resultDTO.message);

		if(resultDTO.result >= 0) {
			////정상 처리
			listPrjResult();
		} else {
			// 비정상 처리
		}
	}


</script>
	</mhtml:frm_body>
</mhtml:html>