<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:set var="prjTeamVO" value="${prjTeamVO}"/>
<c:set var="prjMemberVO" value="${prjMemberVO}"/>

<mhtml:mng_html>
<mhtml:mng_head titleName="${MENUNAME}">
	<meditag:js src="/js/selectbox.js"/>
</mhtml:mng_head>

<mhtml:frm_body cssTag="background-color:#ffffff;">
<br>
<form id="prjTeamForm" name="prjTeamForm" onsubmit="return false" action="/LecturePrjTeamAdmin.do">
	<input type="hidden" name="cmd" />
 	<input type="hidden" name="crsCreCd" />
 	<input type="hidden" name="prjtSn" />
 	<input type="hidden" name="selPrjtSn" />
 	<input type="hidden" name="learnerCnt" value="${prjMemberVO.learnerCnt }"/>

 		<div style="padding-left: 20px;">
			<div>
				<spring:message code="lecture.message.project.team.msg.autocreate"/>
			</div>
			<br>
			<div>
				<input type="radio" id="newTeam" name="teamCre" value="newTeam" checked="checked"/><span></span><b><spring:message code="lecture.title.project.team.create.new"/></b>&nbsp;&nbsp;
				<input type="radio" id="befoTeam" name="teamCre" value="befoTeam"/><b><spring:message code="lecture.title.project.team.otherproject"/></b> &nbsp;
				<b>(<spring:message code="lecture.title.project.team.stdcnt"/>${prjMemberVO.learnerCnt}<spring:message code="common.title.cnt.user"/>)</b>
			</div>
 		</div>
		<br>
		<div id="newTeamView" style="display:block">
			<table summary=<spring:message code="lecture.title.project.team.manage"/> style="width:96%" class="table_dtl" align="center">
				<colgroup>
					<col style="width:20%;"/>
					<col style="width:30%;"/>
					<col style="width:20%;"/>
					<col style="width:30%;"/>
				</colgroup>
				<tr height="35">
					<th class="top" scope="row"><spring:message code="lecture.title.project.team.create.teamcnt"/> </th>
					<td class="top">
						<input type="text" style="width:150px;" dispName="<spring:message code="lecture.title.project.team.create.teamcnt"/>" maxlength="100" isNull="N" lenCheck="100" value="" name="teamCnt" class="txt" id="teamCnt"/>
 					</td>
					<th class="top" scope="row"><spring:message code="lecture.title.project.team.teamalort"/> </th>
					<td class="top">
						<select name="learnerAssign" style="width:170px;" id="learnerAssign">
							<html:option value="random"><spring:message code="lecture.title.project.team.alortrandom" /></html:option>
							<html:option value="name"><spring:message code="lecture.title.project.team.alortname" /></html:option>
						</select>
					</td>
				</tr>
			</table>
		</div>
		<div id="befoTeamView" style="display:block">
			<table summary="<spring:message code="lecture.title.project.team.manage"?." style="width:96%" class="table_list" align="center">
				<thead>
				<tr>
					<th scope="col"><spring:message code="common.title.select"/></th>
					<th scope="col"><spring:message code="lecture.title.project.name"/></th>
					<th scope="col"><spring:message code="lecture.title.project.teamcnt"/></th>
					<th scope="col"><spring:message code="lecture.title.project.team.membercnt"/></th>
				</tr>
				</thead>
				<tbody id="teamList"></tbody>
			</table>
		</div>
		<table style="width:96%">
			<tr>
				<td>
					<meditag:buttonwrapper>
						<meditag:button value="button.create.team" title="button.create.team" func="createTeam()" />
						<meditag:button value="button.close" title="button.close" func="parent.projectPopBox.close();" />
					</meditag:buttonwrapper>
				</td>
			</tr>
		</table>
	</form>
<script type="text/javascript">
	$("#befoTeamView").hide();

	var ItemDTO = new Object();
	$(document).ready(function() {
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
		//프로젝트 목록 조회
		listProject();
	});

	//프로젝트 목록 조회
	function listProject(){
		$.getJSON(cUrl("/LectureProjectAdmin.do"),		//url
				{ "cmd" : "list",
				  "projectVO.crsCreCd" : ItemDTO.crsCreCd,
				},											//parameter
				listProjectCallback						//callback function
		);
		displayWorkProgress();
	}

	//프로젝트 목록Callback
	function listProjectCallback(ProcessResultListDTO){
		var retList = ProcessResultListDTO.returnList;
		var listStr = "";

		if(retList.length == 0) {
			listStr +="	<tr><td colspan='4' align='center'><font color=blud>* 프로젝트 목록이  없습니다.</font></td></tr>\n";
		}

		var cnt = 0;
		for (var i=0; i<retList.length; i++) {
			var item = retList[i];
			listStr +="	<tr>\n";
			listStr +="		<td><input type='radio' name='sel' id='sel' value='"+item.prjtSn+"' style='border:0' title='선택'></td>\n";
			listStr +="		<td>"+item.prjtTitle+"</td>\n";
			listStr +="		<td>"+item.teamCnt+"</td>\n";
			listStr +="		<td>"+item.mbrCnt+"</td>\n";
			listStr +="	</tr>\n";
		}

		$("#teamList").html(listStr);

		closeWorkProgress();
	}

	function createTeam(){
		if($('input[name=teamCre]:checked').val() == "newTeam"){

			var teamCnt = $("#teamCnt").val();
			var learnerCnt = document.prjTeamForm["prjTeamVO.learnerCnt"].value;

			if(parseInt(teamCnt, 10) > learnerCnt){
				alert("<spring:message code="lecture.messgae.project.team.alert.teamcnt"/>");
				return;
			}
			process("addTeamCreate");

		}else{
			var teamSn = $('input[name=sel]:checked').val();
			document.prjTeamForm["prjTeamVO.selPrjtSn"].value = teamSn;

			process("addProjectTeamCopy");
		}


	}

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		$('#prjTeamForm').find('input[name=cmd]').val(cmd);
		$('#prjTeamForm').ajaxSubmit(processCallback);
	}
	/**
	 * 처리 결과 표시 콜백
	 */
	function processCallback(resultDTO) {
		alert(resultDTO.message);

		if(resultDTO.result >= 0) {
			// 정상 처리
			parent.subWorkFrame.subWorkFrame2.listMember();
			parent.projectPopBox.close();
		} else {
			// 비정상 처리
		}
	}

	</script>
	</mhtml:frm_body>
</mhtml:mng_html>