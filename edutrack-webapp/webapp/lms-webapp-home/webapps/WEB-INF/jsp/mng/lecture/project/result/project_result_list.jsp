<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="projectResultVO" value="${projectResultVO}"/>
<mhtml:html>
<mhtml:head titleName="평가관리 목록"/>
<mhtml:frm_body cssTag="background-color:#ffffff;">
	<form id="projectResultForm" name="projectResultForm" onsubmit="return false" action="/LectureProjectResultAdmin.do">
	<input type="hidden" name="cmd"/>
	<input type="hidden" name="crsCreCd" />
 	<input type="hidden" name="prjtSn" />
 	<div class="dvcontents">
		<div class="subhead">
			<div style="float:right">
				<meditag:button value="점수저장" title="점수저장" func="editScoreAll()" />
			</div>
		</div>
		<table summary="팀프로젝트 관리" style="width:100%" class="table_list">
			<colgroup>
				<col style="width:5%"/>
				<col style="width:auto;"/>
				<col style="width:10%"/>
				<col style="width:5%"/>
				<col style="width:5%"/>
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
			<tbody id="tbodyList"></tbody>
		</table>
	</div>
	</form>
<script type="text/javascript">

	var ItemDTO = new Object();
	$(document).ready(function() {
		ItemDTO.crsCreCd = '${projectResultVO.crsCreCd}';
		ItemDTO.prjtSn = '${projectResultVO.prjtSn}';
		ItemDTO.prjtTeamSn = '${projectResultVO.prjtTeamSn}';
		//프로젝트 평가관리목록 조회
		listProjectResult();
	});

	//프로젝트 목록 조회
	function listProjectResult(){
		$.getJSON(cUrl("/LectureProjectResultAdmin.do"),		//url
				{ "cmd" : "list",
			      "projectResultVO.crsCreCd" : ItemDTO.crsCreCd,
			      "projectResultVO.prjtSn" : ItemDTO.prjtSn
				},											//parameter
				listProjectResultCallback						//callback function
		);
		displayWorkProgress();
	}

	//프로젝트 목록Callback
	function listProjectResultCallback(ProcessResultListDTO){
		var retList = ProcessResultListDTO.returnList;
		var listStr = "";

		if(retList.length == 0) {
			listStr +="	<tr><td colspan='7' align='center'><font color=blud>* 팀 목록이  없습니다.</font></td></tr>\n";
		}
		var cnt = 0;
		for (var i=0; i<retList.length; i++) {
			var item = retList[i];
			listStr +="	<tr>\n";
			listStr +="		<input type='hidden' name='prjtTeamSn' value='"+item.prjtTeamSn+"'>\n";
			listStr +="		<td>"+(++cnt)+"</td>\n";
			listStr +="		<td class='left'>"+item.prjtTeamNm+"</td>\n";
			listStr +="		<td><input type='text' name='teamScore' style='width:26px' value='"+item.teamScore+"'/>점</td>\n";
			listStr +="		<td><a class='squarebutton' href='#none' onclick='showTeam(\""+item.prjtTeamSn+"\");' ><span>보기</span></a></td> \n";
			listStr +="		<td><a class='squarebutton' href='#none' onclick='showSi(\""+item.prjtTeamSn+"\");' ><span>보기</span></a></td> \n";
			listStr +="	</tr>\n";
		}
		$("#tbodyList").html(listStr);

		var iframeObj = parent.document.getElementById("subWorkFrame2");
		parent.resizeIframe3(iframeObj, $(document).height());

		closeWorkProgress();
	}

	//팀프로젝트 팀 점수 저장
	function editScoreAll() {
	    var TeamScoreObj = document.getElementsByName("teamScore");
		var PrjtTeamSnObj = document.getElementsByName("prjtTeamSn");

		var strTeamScore = "";
		var strPrjtTeamSn = "";

		for(var i=0; i < TeamScoreObj.length; i++) {

			if(TeamScoreObj[i].value>100){
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


		$.getJSON( cUrl("/LectureProjectResultAdmin.do"),		// url
				{ "cmd" : "editScoreAll",
			 	  "projectResultVO.crsCreCd" : ItemDTO.crsCreCd,
	      		  "projectResultVO.prjtSn" : ItemDTO.prjtSn,
	      		  "strTeamScore" : strTeamScore,
	      		  "strPrjtTeamSn": strPrjtTeamSn
				},			// params
				processAddScoreCallback				// callback function
		);
	}

	/**
	 * 처리 결과 표시 콜백
	 */
	function processAddScoreCallback(ProcessResultDTO) {
		alert(ProcessresultDTO.message);

		if(ProcessresultDTO.result >= 0) {
			// 정상 처리
			listProjectResult();
		} else {
			// 비정상 처리
		}
	}

	//팀원 보기
	function showTeam(prjtTeamSn){
		var addContent  = "<iframe id='ShowProjectResultForm' name='ShowProjectResultForm' width='100%' height='100%' "
			+ "frameborder='0' scrolling='no' src='<c:url value="/LectureProjectResultAdmin.do"/>"
			+ "?cmd=teamMain&amp;projectResultVO.crsCreCd="+ItemDTO.crsCreCd+""
			+ "&amp;projectResultVO.prjtSn="+ItemDTO.prjtSn+""
			+ "&amp;projectResultVO.prjtTeamSn="+prjtTeamSn+"'/>";

			/* parent.memberPopBox.clear();
			parent.memberPopBox.addContents(addContent);
			parent.memberPopBox.resize(700, 500);
			parent.memberPopBox.setTitle("팀원 보기");
			parent.memberPopBox.show();	 */
			parent.parent.projectPopBox.clear();
			parent.parent.projectPopBox.addContents(addContent);
			parent.parent.projectPopBox.resize(700, 500);
			parent.parent.projectPopBox.setTitle("팀원 관리");
			parent.parent.projectPopBox.show();
	}

	//팀원 보기
	function showSi(prjtTeamSn){
		var addContent  = "<iframe id='ShowSiInfoProjectResultForm' name='ShowSiInfoProjectResultForm' width='100%' height='100%' "
			+ "frameborder='0' scrolling='no' src='<c:url value="/LectureProjectResultAdmin.do"/>"
			+ "?cmd=siInfoMain&amp;projectResultVO.crsCreCd="+ItemDTO.crsCreCd+""
			+ "&amp;prjAssignmentSendVO.prjtTeamSn="+prjtTeamSn+""
			+ "&amp;projectResultVO.prjtSn="+ItemDTO.prjtSn+"'/>";

			parent.parent.projectPopBox.clear();
			parent.parent.projectPopBox.addContents(addContent);
			parent.parent.projectPopBox.resize(700, 500);
			parent.parent.projectPopBox.setTitle("제출 정보");
			parent.parent.projectPopBox.show();
	}

</script>
</mhtml:frm_body>
</mhtml:html>





